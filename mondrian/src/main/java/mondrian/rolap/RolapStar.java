/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2018 Hitachi Vantara and others
// All Rights Reserved.
//
// jhyde, 12 August, 2001
*/
package mondrian.rolap;

import static mondrian.rolap.util.ExpressionUtil.genericExpression;
import static mondrian.rolap.util.JoinUtil.getLeftAlias;
import static mondrian.rolap.util.JoinUtil.getRightAlias;
import static mondrian.rolap.util.JoinUtil.left;
import static mondrian.rolap.util.JoinUtil.right;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import mondrian.olap.Property;
import org.eclipse.daanse.db.dialect.api.BestFitColumnType;
import org.eclipse.daanse.db.dialect.api.Datatype;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingViewQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.ColumnR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.InlineTableR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinedQueryElementR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.TableR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.ViewR;
import org.eclipse.daanse.rolap.mapping.api.model.QueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.RelationalQueryMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import mondrian.olap.MondrianException;
import mondrian.olap.SystemWideProperties;
import mondrian.olap.Util;
import mondrian.rolap.agg.Aggregation;
import mondrian.rolap.agg.AggregationKey;
import mondrian.rolap.agg.AggregationManager;
import mondrian.rolap.agg.CellRequest;
import mondrian.rolap.agg.SegmentWithData;
import mondrian.rolap.aggmatcher.AggStar;
import mondrian.rolap.sql.SqlQuery;
import mondrian.rolap.util.RelationUtil;
import mondrian.server.LocusImpl;
import mondrian.util.Bug;

/**
 * A <code>RolapStar</code> is a star schema. It is the means to read cell
 * values.
 *
 * <p>todo: Move this class into a package that specializes in relational
 * aggregation, doesn't know anything about hierarchies etc.
 *
 * @author jhyde
 * @since 12 August, 2001
 */
public class RolapStar {
    private static final Logger LOGGER = LoggerFactory.getLogger(RolapStar.class);

    private final RolapSchema schema;

    private final Context context;

    private final Table factTable;

    /**
     * Number of columns (column and columnName).
     */
    private int columnCount;

    /**
     * Keeps track of the columns across all tables. Should have
     * a number of elements equal to columnCount.
     */
    private final List<Column> columnList = new ArrayList<>();


    /**
     * If true, then database aggregation information is cached, otherwise
     * it is flushed after each query.
     */
    private boolean cacheAggregations;

    /**
     * Partially ordered list of AggStars associated with this RolapStar's fact
     * table.
     */
    private final List<AggStar> aggStars = new LinkedList<>();


    // temporary model, should eventually use RolapStar.Table and
    // RolapStar.Column
    private StarNetworkNode factNode;
    private Map<String, StarNetworkNode> nodeLookup =
        new HashMap<>();

    private final RolapStatisticsCache statisticsCache;
    private final static String illegalLeftDeepJoin =
        "Left side of join must not be a join; mondrian only supports right-deep joins.";

    /**
     * Creates a RolapStar. Please use
     * {@link RolapSchema.RolapStarRegistry#getOrCreateStar} to create a
     * {@link RolapStar}.
     */
    RolapStar(
        final RolapSchema schema,
        final Context context,
        final QueryMapping fact)
    {
        this.cacheAggregations = true;
        this.schema = schema;
        this.context = context;
        this.factTable = new RolapStar.Table(this, fact, null, null);

        // phase out and replace with Table, Column network
        this.factNode =
            new StarNetworkNode(null, null, null, null);

        this.statisticsCache = new RolapStatisticsCache(this);
    }

    /**
     * Retrieves the value of the cell identified by a cell request, if it
     * can be found in the local cache of the current statement (thread).
     *
     * <p>If it is not in the local cache, returns null. The client's next
     * step will presumably be to request a segment that contains the cell
     * from the global cache, external cache, or by issuing a SQL statement.
     *
     * <p>Returns {@link Util#nullValue} if a segment contains the cell and the
     * cell's value is null.
     *
     * <p>If <code>pinSet</code> is not null, pins the segment that holds it
     * into the local cache. <code>pinSet</code> ensures that a segment is
     * only pinned once.
     *
     * @param request Cell request
     *
     * @param pinSet Set into which to pin the segment; or null
     *
     * @return Cell value, or {@link Util#nullValue} if the cell value is null,
     * or null if the cell is not in any segment in the local cache.
     */
    public Object getCellFromCache(
        CellRequest request,
        RolapAggregationManager.PinSet pinSet)
    {
        // REVIEW: Is it possible to optimize this so not every cell lookup
        // causes an AggregationKey to be created?
        AggregationKey aggregationKey = new AggregationKey(request);

        final Bar bar = localBars.get();
        for (SegmentWithData segment : Util.GcIterator.over(bar.segmentRefs)) {
            if (!segment.getConstrainedColumnsBitKey().equals(
                    request.getConstrainedColumnsBitKey()))
            {
                continue;
            }

            if (!segment.matches(aggregationKey, request.getMeasure())) {
                continue;
            }

            Object o = segment.getCellValue(request.getSingleValues());
            if (o != null) {
                if (pinSet != null) {
                    ((AggregationManager.PinSetImpl) pinSet).add(segment);
                }
                return o;
            }
        }
        // No segment contains the requested cell.
        return null;
    }

    public Object getCellFromAllCaches(final CellRequest request, RolapConnection rolapConnection) {
        // First, try the local/thread cache.
        Object result = getCellFromCache(request, null);
        if (result != null) {
            return result;
        }
        // Now ask the segment cache manager.
        return getCellFromExternalCache(request, rolapConnection);
    }

    private Object getCellFromExternalCache(CellRequest request, RolapConnection rolapConnection) {
        final SegmentWithData segment =
            LocusImpl.peek().getContext().getAggregationManager()
                .getCacheMgr(rolapConnection).peek(request);
        if (segment == null) {
            return null;
        }
        return segment.getCellValue(request.getSingleValues());
    }

    public void register(SegmentWithData segment) {
        localBars.get().segmentRefs.add(
            new SoftReference<>(segment));
    }

    public RolapStatisticsCache getStatisticsCache() {
        return statisticsCache;
    }

    public void remove() {
        localBars.remove();
    }

    public static String generateExprString(MappingExpression expression, SqlQuery query) {
        if(expression instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn) {
            return query.getDialect().quoteIdentifier(expression.getTable(),
                expression.getName());
        }
        if(expression instanceof MappingExpressionView expressionView) {
            SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();
            expressionView.sql().sqls().forEach(e -> e.dialects().forEach(d -> codeSet.put(d, e.statement())));
            return codeSet.chooseQuery(query.getDialect());
        }
        return null;
    }

    /**
     * Temporary. Contains the local cache for a particular thread. Because
     * it is accessed via a thread-local, the data structures can be accessed
     * without acquiring locks.
     *
     */
    public static class Bar {
        /** Holds all thread-local aggregations of this star. */


        private final Cache<AggregationKey, Aggregation> aggregations =Caffeine.newBuilder().weakKeys().weakValues().build();

        private final List<SoftReference<SegmentWithData>> segmentRefs =
            new ArrayList<>();
    }

    private final ThreadLocal<Bar> localBars = ThreadLocal.withInitial(Bar::new);

    private static class StarNetworkNode {
        private StarNetworkNode parent;
        private MappingRelationQuery origRel;
        private String foreignKey;
        private String joinKey;

        private StarNetworkNode(
            StarNetworkNode parent,
            MappingRelationQuery origRel,
            String foreignKey,
            String joinKey)
        {
            this.parent = parent;
            this.origRel = origRel;
            this.foreignKey = foreignKey;
            this.joinKey = joinKey;
        }

        private boolean isCompatible(
            StarNetworkNode compatibleParent,
            MappingRelationQuery rel,
            String compatibleForeignKey,
            String compatibleJoinKey)
        {
            return parent == compatibleParent
                && origRel.getClass().equals(rel.getClass())
                && foreignKey.equals(compatibleForeignKey)
                && joinKey.equals(compatibleJoinKey);
        }
    }

    protected MappingQuery cloneRelation(
        MappingRelationQuery rel,
        String possibleName)
    {
        if (rel instanceof MappingTableQuery tbl) {
            return new TableR(
                tbl,
                possibleName);
        } else if (rel instanceof MappingViewQuery view) {
            return new ViewR(view, possibleName);
        } else if (rel instanceof MappingInlineTableQuery inlineTable) {
            return new InlineTableR(inlineTable, possibleName);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Generates a unique relational join to the fact table via re-aliasing
     * Relations
     *
     * currently called in the RolapCubeHierarchy constructor.  This should
     * eventually be phased out and replaced with RolapStar.Table and
     * RolapStar.Column references
     *
     * @param rel the relation needing uniqueness
     * @param factForeignKey the foreign key of the fact table
     * @param primaryKey the join key of the relation
     * @param primaryKeyTable the join table of the relation
     * @return if necessary a new relation that has been re-aliased
     */
    public MappingQuery getUniqueRelation(
        MappingQuery rel,
        String factForeignKey,
        String primaryKey,
        String primaryKeyTable)
    {
        return getUniqueRelation(
            factNode, rel, factForeignKey, primaryKey, primaryKeyTable);
    }

    private MappingQuery getUniqueRelation(
        StarNetworkNode parent,
        MappingQuery relOrJoin,
        String foreignKey,
        String joinKey,
        String joinKeyTable)
    {
        if (relOrJoin == null) {
            return null;
        } else if (relOrJoin instanceof MappingRelationQuery rel) {
            int val = 0;
            String newAlias =
                joinKeyTable != null ? joinKeyTable : RelationUtil.getAlias(rel);
            while (true) {
                StarNetworkNode node = nodeLookup.get(newAlias);
                if (node == null) {
                    if (val != 0) {
                        rel = (MappingRelationQuery)
                            cloneRelation(rel, newAlias);
                    }
                    node =
                        new StarNetworkNode(
                            parent, rel, foreignKey, joinKey);
                    nodeLookup.put(newAlias, node);
                    return rel;
                } else if (node.isCompatible(
                        parent, rel, foreignKey, joinKey))
                {
                    return node.origRel;
                }
                newAlias = new StringBuilder(RelationUtil.getAlias(rel)).append("_").append(++val).toString();
            }
        } else if (relOrJoin instanceof MappingJoinQuery join) {
            if (left(join) instanceof MappingJoinQuery) {
                throw new MondrianException(illegalLeftDeepJoin);
            }
            final MappingQuery left;
            final MappingQuery right;
            if (getLeftAlias(join).equals(joinKeyTable)) {
                // first manage left then right
                left =
                    getUniqueRelation(
                        parent, left(join), foreignKey,
                        joinKey, joinKeyTable);
                parent = nodeLookup.get(
                    RelationUtil.getAlias(((MappingRelationQuery) left)));
                right =
                    getUniqueRelation(
                        parent, right(join), join.left().getKey(),
                        join.right().getKey(), getRightAlias(join));
            } else if (getRightAlias(join).equals(joinKeyTable)) {
                // right side must equal
                right =
                    getUniqueRelation(
                        parent, right(join), foreignKey,
                        joinKey, joinKeyTable);
                parent = nodeLookup.get(
                    RelationUtil.getAlias(((MappingRelationQuery) right)));
                left =
                    getUniqueRelation(
                        parent, left(join), join.right().getKey(),
                        join.left().getKey(), getLeftAlias(join));
            } else {
                throw new MondrianException(
                    "failed to match primary key table to join tables");
            }

            if (left(join) != left || right(join) != right) {
                join =
                    new JoinR(new JoinedQueryElementR(
                        left instanceof MappingRelationQuery relation
                            ? RelationUtil.getAlias(relation)
                            : null,
                        join.left().getKey(), left),
                        new JoinedQueryElementR(
                        right instanceof MappingRelationQuery relation
                            ? RelationUtil.getAlias(relation)
                            : null,
                        join.right().getKey(), right));
            }
            return join;
        }
        return null;
    }

    /**
     * Returns this RolapStar's column count. After a star has been created with
     * all of its columns, this is the number of columns in the star.
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * This is used by the {@link Column} constructor to get a unique id (per
     * its parent {@link RolapStar}).
     */
    private int nextColumnCount() {
        return columnCount++;
    }

    /**
     * Place holder in case in the future we wish to be able to
     * reload aggregates. In that case, if aggregates had already been loaded,
     * i.e., this star has some aggstars, then those aggstars are cleared.
     */
    public void prepareToLoadAggregates() {
        aggStars.clear();
    }

    /**
     * Adds an {@link AggStar} to this star.
     *
     * <p>Internally the AggStars are added in sort order, smallest row count
     * to biggest, so that the most efficient AggStar is encountered first;
     * ties do not matter.
     */
    public void addAggStar(AggStar aggStar) {
        // Add it before the first AggStar which is larger, if there is one.
        boolean chooseAggregateByVolume = schema.getInternalConnection().getContext().getConfig().chooseAggregateByVolume();
        long size = aggStar.getSize(chooseAggregateByVolume);
        ListIterator<AggStar> lit = aggStars.listIterator();
        while (lit.hasNext()) {
            AggStar as = lit.next();
            if (as.getSize(chooseAggregateByVolume) >= size) {
                lit.previous();
                lit.add(aggStar);
                return;
            }
        }

        // There is no larger star. Add at the end of the list.
        aggStars.add(aggStar);
    }

    /**
     * Clears the list of agg stars.
     */
    void clearAggStarList() {
        aggStars.clear();
    }

    /**
     * Reorder the list of aggregate stars. This should be called if the
     * algorithm used to order the AggStars has been changed.
     */
    public void reOrderAggStarList() {
        List<AggStar> oldList = new ArrayList<>(aggStars);
        aggStars.clear();
        for (AggStar aggStar : oldList) {
            addAggStar(aggStar);
        }
    }

    /**
     * Returns this RolapStar's aggregate table AggStars, ordered in ascending
     * order of size.
     */
    public List<AggStar> getAggStars() {
        return aggStars;
    }

    /**
     * Returns the fact table at the center of this RolapStar.
     *
     * @return fact table
     */
    public Table getFactTable() {
        return factTable;
    }

    /**
     * Clones an existing SqlQuery to create a new one (this cloning creates one
     * with an empty sql query).
     */
    public SqlQuery getSqlQuery() {
        return new SqlQuery(getSqlQueryDialect(), context.getConfig().generateFormattedSql());
    }

    /**
     * Returns this RolapStar's SQL dialect.
     */
    public Dialect getSqlQueryDialect() {
        return context.getDialect();
    }

    /**
     * Sets whether to cache database aggregation information; if false, cache
     * is flushed after each query.
     *
     * <p>This method is called only by the RolapCube and is only called if
     * caching is to be turned off. Note that the same RolapStar can be
     * associated with more than on RolapCube. If any one of those cubes has
     * caching turned off, then caching is turned off for all of them.
     *
     * @param cacheAggregations Whether to cache database aggregation
     */
    void setCacheAggregations(boolean cacheAggregations) {
        // this can only change from true to false
        this.cacheAggregations = cacheAggregations;
        clearCachedAggregations(false);
    }

    /**
     * Returns whether the this RolapStar cache aggregates.
     *
     * @see #setCacheAggregations(boolean)
     */
    boolean isCacheAggregations() {
        return this.cacheAggregations;
    }

    boolean isCacheDisabled() {
        return context.getConfig().disableCaching();
    }

    /**
     * Clears the aggregate cache. This only does something if aggregate caching
     * is disabled (see {@link #setCacheAggregations(boolean)}).
     *
     * @param forced If true, clears cached aggregations regardless of any other
     *   settings.  If false, clears only cache from the current thread
     */
    void clearCachedAggregations(boolean forced) {
        if (forced || !cacheAggregations || isCacheDisabled()) {
            if (LOGGER.isDebugEnabled()) {
                StringBuilder buf = new StringBuilder(100);
                buf.append("RolapStar.clearCachedAggregations: schema=");
                buf.append(schema.getName());
                buf.append(", star=");
                buf.append(getFactTable().getAlias());
                LOGGER.debug(buf.toString());
            }

            // Clear aggregation cache for the current thread context.
            localBars.get().aggregations.cleanUp();
            localBars.get().segmentRefs.clear();
        }
    }

    /**
     * Looks up an aggregation or creates one if it does not exist in an
     * atomic (synchronized) operation.
     *
     * <p>When a new aggregation is created, it is marked as thread local.
     *
     * @param aggregationKey this is the constrained column bitkey
     */
    public Aggregation lookupOrCreateAggregation(
        AggregationKey aggregationKey)
    {
        Aggregation aggregation = lookupSegment(aggregationKey);
        if (aggregation != null) {
            return aggregation;
        }

        aggregation =
            new Aggregation(
                aggregationKey, SystemWideProperties.instance().MaxConstraints);

        localBars.get().aggregations.put(
            aggregationKey, aggregation);


        return aggregation;
    }

    /**
     * Looks for an existing aggregation over a given set of columns, in the
     * local segment cache, returning <code>null</code> if there is none.
     *
     * <p>Must be called from synchronized context.
     *
     */
    public Aggregation lookupSegment(AggregationKey aggregationKey) {
        return localBars.get().aggregations.getIfPresent(aggregationKey);
    }



    /**
     * Returns the DataSource used to connect to the underlying DBMS.
     *
     * @return DataSource
     */
    public DataSource getDataSource() {
        return context.getDataSource();
    }


    /**
     * Returns the Context
     *
     * @return Context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Retrieves the {@link RolapStar.Measure} in which a measure is stored.
     */
    public static Measure getStarMeasure(Member member) {
        return (Measure) ((RolapStoredMeasure) member).getStarMeasure();
    }

    /**
     * Retrieves a named column, returns null if not found.
     */
    public Column[] lookupColumns(String tableAlias, String columnName) {
        final Table table = factTable.findDescendant(tableAlias);
        return (table == null) ? null : table.lookupColumns(columnName);
    }

    /**
     * This is used by TestAggregationManager only.
     */
    public Column lookupColumn(String tableAlias, String columnName) {
        final Table table = factTable.findDescendant(tableAlias);
        return (table == null) ? null : table.lookupColumn(columnName);
    }

    public BitKey getBitKey(String[] tableAlias, String[] columnName) {
        BitKey bitKey = BitKey.Factory.makeBitKey(getColumnCount());
        Column starColumn;
        for (int i = 0; i < tableAlias.length; i ++) {
            starColumn = lookupColumn(tableAlias[i], columnName[i]);
            if (starColumn != null) {
                bitKey.set(starColumn.getBitPosition());
            }
        }
        return bitKey;
    }

    /**
     * Returns a list of all aliases used in this star.
     */
    public List<String> getAliasList() {
        List<String> aliasList = new ArrayList<>();
        if (factTable != null) {
            collectAliases(aliasList, factTable);
        }
        return aliasList;
    }

    /**
     * Finds all of the table aliases in a table and its children.
     */
    private static void collectAliases(List<String> aliasList, Table table) {
        aliasList.add(table.getAlias());
        for (Table child : table.children) {
            collectAliases(aliasList, child);
        }
    }

    /**
     * Collects all columns in this table and its children.
     * If <code>joinColumn</code> is specified, only considers child tables
     * joined by the given column.
     */
    public static void collectColumns(
        Collection<Column> columnList,
        Table table,
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn joinColumn)
    {
        if (joinColumn == null) {
            columnList.addAll(table.columnList);
        }
        for (Table child : table.children) {
            if (joinColumn == null
                || child.getJoinCondition().left.equals(joinColumn))
            {
                collectColumns(columnList, child, null);
            }
        }
    }

    /**
     * Adds a column to the star's list of all columns across all tables.
     *
     * @param c the column to add
     */
    private void addColumn(Column c) {
        columnList.add(c.getBitPosition(), c);
    }

    /**
     * Look up the column at the given bit position.
     *
     * @param bitPos bit position to look up
     * @return column at the given position
     */
    public Column getColumn(int bitPos) {
        return columnList.get(bitPos);
    }

    public RolapSchema getSchema() {
        return schema;
    }

    /**
     * Generates a SQL statement to read all instances of the given attributes.
     *
     * <p>The SQL statement is of the form {@code SELECT ... FROM ... JOIN ...
     * GROUP BY ...}. It is useful for populating an aggregate table.
     *
     * @param columnList List of columns (attributes and measures)
     * @param columnNameList List of column names (must have same cardinality
     *     as {@code columnList})
     * @return SQL SELECT statement
     */
    public String generateSql(
        List<Column> columnList,
        List<String> columnNameList)
    {
        final SqlQuery query = new SqlQuery(context.getDialect(), true);
        query.addFrom(
            factTable.relation,
            RelationUtil.getAlias(factTable.relation),
            false);
        int k = -1;
        for (Column column : columnList) {
            ++k;
            column.table.addToFrom(query,  false, true);
            String columnExpr = column.generateExprString(query);
            if (column instanceof Measure measure) {
                columnExpr = measure.getAggregator().getExpression(columnExpr).toString();
            }
            final String columnName = columnNameList.get(k);
            String alias = query.addSelect(columnExpr, null, columnName);
            if (!(column instanceof Measure)) {
                query.addGroupBy(columnExpr, alias);
            }
        }
        // remove whitespace from query - in particular, the trailing newline
        return query.toString().trim();
    }

    @Override
	public String toString() {
        StringWriter sw = new StringWriter(256);
        PrintWriter pw = new PrintWriter(sw);
        print(pw, "", true);
        pw.flush();
        return sw.toString();
    }

    /**
     * Prints the state of this <code>RolapStar</code>
     *
     * @param pw Writer
     * @param prefix Prefix to print at the start of each line
     * @param structure Whether to print the structure of the star
     */
    public void print(PrintWriter pw, String prefix, boolean structure) {
        if (structure) {
            pw.print(prefix);
            pw.println("RolapStar:");
            String subprefix = new StringBuilder(prefix).append("  ").toString();
            factTable.print(pw, subprefix);

            for (AggStar aggStar : getAggStars()) {
                aggStar.print(pw, subprefix);
            }
        }
    }


    // -- Inner classes --------------------------------------------------------

    /**
     * A column in a star schema.
     */
    public static class Column {
        public static final Comparator<Column> COMPARATOR =
            new Comparator<>() {
                @Override
				public int compare(
                    Column object1,
                    Column object2)
                {
                    return Integer.compare(
                        object1.getBitPosition(),
                        object2.getBitPosition());
                }
        };

        private final Table table;
        private final MappingExpression expression;
        private final Datatype datatype;
        private final BestFitColumnType internalType;
        private final String name;

        /**
         * When a Column is a column, and not a Measure, the parent column
         * is the coloumn associated with next highest Level.
         */
        private final Column parentColumn;

        /**
         * This is used during both aggregate table recognition and aggregate
         * table generation. For multiple dimension usages, multiple shared
         * dimension or unshared dimension with the same column names,
         * this is used to disambiguate aggregate column names.
         */
        private final String usagePrefix;
        /**
         * This is only used in RolapAggregationManager and adds
         * non-constraining columns making the drill-through queries easier for
         * humans to understand.
         */
        private final Column nameColumn;

        private boolean isNameColumn;

        /** this has a unique value per star */
        private final int bitPosition;
        /**
         * The estimated cardinality of the column.
         * {@link Integer#MIN_VALUE} means unknown.
         */
        private AtomicLong approxCardinality = new AtomicLong(
            Long.MIN_VALUE);

        private Column(
            String name,
            Table table,
            MappingExpression expression,
            Datatype datatype)
        {
            this(
                name, table, expression, datatype, null, null,
                null, null, Integer.MIN_VALUE, table.star.nextColumnCount());
        }

        private Column(
            String name,
            Table table,
            MappingExpression expression,
            Datatype datatype,
            BestFitColumnType internalType,
            Column nameColumn,
            Column parentColumn,
            String usagePrefix,
            int approxCardinality,
            int bitPosition)
        {
            this.name = name;
            this.table = table;
            this.expression = expression;
            assert expression == null
                || genericExpression(expression) != null;
            this.datatype = datatype;
            this.internalType = internalType;
            this.bitPosition = bitPosition;
            this.nameColumn = nameColumn;
            this.parentColumn = parentColumn;
            this.usagePrefix = usagePrefix;
            this.approxCardinality.set(approxCardinality);
            if (nameColumn != null) {
                nameColumn.isNameColumn = true;
            }
            if (table != null) {
                table.star.addColumn(this);
            }
        }

        /**
         * Fake column.
         *
         * @param datatype Datatype
         */
        protected Column(Datatype datatype)
        {
            this(
                null,
                null,
                null,
                datatype,
                null,
                null,
                null,
                null,
                Integer.MIN_VALUE,
                0);
        }

        @Override
		public boolean equals(Object obj) {
            if (! (obj instanceof RolapStar.Column other)) {
                return false;
            }
            // Note: both columns have to be from the same table
            return
                other.table == this.table
                && Objects.equals(other.expression, this.expression)
                && other.datatype == this.datatype
                && other.name.equals(this.name);
        }

        @Override
		public int hashCode() {
            int h = name.hashCode();
            h = Util.hash(h, table);
            return h;
        }

        public String getName() {
            return name;
        }

        public int getBitPosition() {
            return bitPosition;
        }

        public RolapStar getStar() {
            return table.star;
        }

        public RolapStar.Table getTable() {
            return table;
        }

        public SqlQuery getSqlQuery() {
            return getTable().getStar().getSqlQuery();
        }

        public RolapStar.Column getNameColumn() {
            return nameColumn;
        }

        public RolapStar.Column getParentColumn() {
            return parentColumn;
        }

        public String getUsagePrefix() {
            return usagePrefix;
        }

        public boolean isNameColumn() {
            return isNameColumn;
        }

        public MappingExpression getExpression() {
            return expression;
        }

        /**
         * Generates a SQL expression, which typically this looks like
         * this: <code><i>tableName</i>.<i>columnName</i></code>.
         */
        public String generateExprString(SqlQuery query) {
            return RolapStar.generateExprString(getExpression(), query);
        }

        /**
         * Get column cardinality from the schema cache if possible;
         * otherwise issue a select count(distinct) query to retrieve
         * the cardinality and stores it in the cache.
         *
         * @return the column cardinality.
         */
        public long getCardinality() {
            if (approxCardinality.get() < 0) {
                approxCardinality.set(
                    table.star.getStatisticsCache().getColumnCardinality(
                        table.relation, expression, approxCardinality.get()));
            }
            return approxCardinality.get();
        }

        /**
         * Generates a predicate that a column matches one of a list of values.
         *
         * <p>
         * Several possible outputs, depending upon whether the there are
         * nulls:<ul>
         *
         * <li>One not-null value: <code>foo.bar = 1</code>
         *
         * <li>All values not null: <code>foo.bar in (1, 2, 3)</code></li
         *
         * <li>Null and not null values:
         * <code>(foo.bar is null or foo.bar in (1, 2))</code></li>
         *
         * <li>Only null values:
         * <code>foo.bar is null</code></li>
         *
         * <li>String values: <code>foo.bar in ('a', 'b', 'c')</code></li>
         *
         * </ul>
         */
        public static String createInExpr(
            final String expr,
            StarColumnPredicate predicate,
            Datatype datatype,
            SqlQuery sqlQuery)
        {
            // Sometimes a column predicate is created without a column. This
            // is unfortunate, and we will fix it some day. For now, create
            // a fake column with all of the information needed by the toSql
            // method, and a copy of the predicate wrapping that fake column.
            if (!Bug.BugMondrian313Fixed
                || !Bug.BugMondrian314Fixed
                && predicate.getConstrainedColumn() == null)
            {
                Column column = new Column(datatype) {
                    @Override
					public String generateExprString(SqlQuery query) {
                        return expr;
                    }
                };
                predicate = predicate.cloneWithColumn(column);
            }

            StringBuilder buf = new StringBuilder(64);
            predicate.toSql(sqlQuery, buf);
            return buf.toString();
        }

        @Override
		public String toString() {
            StringWriter sw = new StringWriter(256);
            PrintWriter pw = new PrintWriter(sw);
            print(pw, "");
            pw.flush();
            return sw.toString();
        }

        /**
         * Prints this column.
         *
         * @param pw Print writer
         * @param prefix Prefix to print first, such as spaces for indentation
         */
        public void print(PrintWriter pw, String prefix) {
            SqlQuery sqlQuery = getSqlQuery();
            pw.print(prefix);
            pw.print(getName());
            pw.print(" (");
            pw.print(getBitPosition());
            pw.print("): ");
            pw.print(generateExprString(sqlQuery));
        }

        public Datatype getDatatype() {
            return datatype;
        }

        /**
         * Returns a string representation of the datatype of this column, in
         * the dialect specified. For example, 'DECIMAL(10, 2) NOT NULL'.
         *
         * @param dialect Dialect
         * @return String representation of column's datatype
         */
        public String getDatatypeString(Dialect dialect, boolean formatted) {
            final SqlQuery query = new SqlQuery(dialect, formatted);
            query.addFrom(
                table.star.factTable.relation, table.star.factTable.alias,
                false);
            query.addFrom(table.relation, table.alias, false);
            query.addSelect(RolapStar.generateExprString(expression, query), null);
            final String sql = query.toString();
            Connection jdbcConnection = null;
            try {
                jdbcConnection = table.star.context.getDataSource().getConnection();
                final PreparedStatement pstmt =
                    jdbcConnection.prepareStatement(sql);
                final ResultSetMetaData resultSetMetaData =
                    pstmt.getMetaData();
                assert resultSetMetaData.getColumnCount() == 1;
                final String type = resultSetMetaData.getColumnTypeName(1);
                int precision = resultSetMetaData.getPrecision(1);
                final int scale = resultSetMetaData.getScale(1);
                if (type.equals("DOUBLE")) {
                    precision = 0;
                }
                String typeString;
                if (precision == 0) {
                    typeString = type;
                } else if (scale == 0) {
                    typeString = new StringBuilder(type).append("(").append(precision).append(")").toString();
                } else {
                    typeString = new StringBuilder(type).append("(")
                        .append(precision).append(", ").append(scale).append(")").toString();
                }
                pstmt.close();
                jdbcConnection.close();
                jdbcConnection = null;
                return typeString;
            } catch (SQLException e) {
                throw Util.newError(
                    e,
                    "Error while deriving type of column " + toString());
            } finally {
                if (jdbcConnection != null) {
                    try {
                        jdbcConnection.close();
                    } catch (SQLException e) {
                        // ignore
                    }
                }
            }
        }

        public BestFitColumnType getInternalType() {
            return internalType;
        }
    }

    /**
     * Definition of a measure in a star schema.
     *
     * <p>A measure is basically just a column; except that its
     * {@link #aggregator} defines how it is to be rolled up.
     */
    public static class Measure extends Column {
        private final String cubeName;
        private final RolapAggregator aggregator;

        public Measure(
            String name,
            String cubeName,
            RolapAggregator aggregator,
            Table table,
            MappingExpression expression,
            Datatype datatype)
        {
            super(name, table, expression, datatype);
            this.cubeName = cubeName;
            this.aggregator = aggregator;
        }

        public RolapAggregator getAggregator() {
            return aggregator;
        }

        @Override
		public boolean equals(Object o) {
            if (! (o instanceof RolapStar.Measure that)) {
                return false;
            }
            if (!super.equals(that)) {
                return false;
            }
            // Measure names are only unique within their cube - and remember
            // that a given RolapStar can support multiple cubes if they have
            // the same fact table.
            if (!cubeName.equals(that.cubeName)) {
                return false;
            }
            // Note: both measure have to have the same aggregator
            return (that.aggregator == this.aggregator);
        }

        @Override
		public int hashCode() {
            int h = super.hashCode();
            h = Util.hash(h, aggregator);
            return h;
        }

        @Override
		public void print(PrintWriter pw, String prefix) {
            SqlQuery sqlQuery = super.getSqlQuery();
            pw.print(prefix);
            pw.print(getName());
            pw.print(" (");
            pw.print(getBitPosition());
            pw.print("): ");
            pw.print(
                aggregator.getExpression(
                    getExpression() == null
                        ? null
                        : super.generateExprString(sqlQuery)));
        }

        public String getCubeName() {
            return cubeName;
        }
    }

    /**
     * Definition of a table in a star schema.
     *
     * <p>A 'table' is defined by a
     * {@link MappingQuery} so may, in fact, be a
     * view.
     *
     * <p>Every table in the star schema except the fact table has a parent
     * table, and a condition which specifies how it is joined to its parent.
     * So the star schema is, in effect, a hierarchy with the fact table at
     * its root.
     */
    public static class Table {
        private final RolapStar star;
        private final RelationalQueryMapping relation;
        private final List<Column> columnList;
        private final Table parent;
        private List<Table> children;
        private final Condition joinCondition;
        private final String alias;

        private Table(
            RolapStar star,
            RelationalQueryMapping relation,
            Table parent,
            Condition joinCondition)
        {
            this.star = star;
            this.relation = relation;
            this.alias = chooseAlias();
            this.parent = parent;
            final AliasReplacer aliasReplacer =
                    new AliasReplacer(RelationUtil.getAlias(relation), this.alias);
            this.joinCondition = aliasReplacer.visit(joinCondition);
            if (this.joinCondition != null) {
                this.joinCondition.table = this;
            }
            this.columnList = new ArrayList<>();
            this.children = Collections.emptyList();
            Util.assertTrue((parent == null) == (joinCondition == null));
        }

        /**
         * Returns the condition by which a dimension table is connected to its
         * {@link #getParentTable() parent}; or null if this is the fact table.
         */
        public Condition getJoinCondition() {
            return joinCondition;
        }

        /**
         * Returns this table's parent table, or null if this is the fact table
         * (which is at the center of the star).
         */
        public Table getParentTable() {
            return parent;
        }

        private void addColumn(Column column) {
            columnList.add(column);
        }

        /**
         * Adds to a list all columns of this table or a child table
         * which are present in a given bitKey.
         *
         * <p>Note: This method is slow, but that's acceptable because it is
         * only used for tracing. It would be more efficient to store an
         * array in the {@link RolapStar} mapping column ordinals to columns.
         */
        private void collectColumns(BitKey bitKey, List<Column> list) {
            for (Column column : getColumns()) {
                if (bitKey.get(column.getBitPosition())) {
                    list.add(column);
                }
            }
            for (Table table : getChildren()) {
                table.collectColumns(bitKey, list);
            }
        }

        /**
         * Returns an array of all columns in this star with a given name.
         */
        public Column[] lookupColumns(String columnName) {
            List<Column> l = new ArrayList<>();
            for (Column column : getColumns()) {
                if (column.getExpression() instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn columnExpr) {
                    if (columnExpr.getName().equals(columnName)) {
                        l.add(column);
                    }
                } else if (column.getExpression()
                        instanceof MappingExpressionView columnExpr && columnExpr.toString().equals(columnName))
                {
                    l.add(column);
                }
            }
            return l.toArray(new Column[l.size()]);
        }

        public Column lookupColumn(String columnName) {
            for (Column column : getColumns()) {
                if (column.getExpression() instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn columnExpr) {
                    if (columnExpr.getName().equals(columnName)) {
                        return column;
                    }
                } else if (column.getExpression()
                        instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView columnExpr)
                {
                    if (columnExpr.toString().equals(columnName)) {
                        return column;
                    }
                } else if (column.getName().equals(columnName)) {
                    return column;
                }
            }
            return null;
        }

        /**
         * Given a Expression return a column with that expression
         * or null.
         */
        public Column lookupColumnByExpression(MappingExpression expr) {
            for (Column column : getColumns()) {
                if (column instanceof Measure) {
                    continue;
                }
                if (column.getExpression().equals(expr)) {
                    return column;
                }
            }
            return null;
        }

        public boolean containsColumn(Column column) {
            return getColumns().contains(column);
        }

        /**
         * Look up a {@link Measure} by its name.
         * Returns null if not found.
         */
        public Measure lookupMeasureByName(String cubeName, String name) {
            for (Column column : getColumns()) {
                if (column instanceof Measure measure && measure.getName().equals(name)
                        && measure.getCubeName().equals(cubeName)) {
                        return measure;
                }
            }
            return null;
        }

        RolapStar getStar() {
            return star;
        }
        private SqlQuery getSqlQuery() {
            return getStar().getSqlQuery();
        }
        public RelationalQueryMapping getRelation() {
            return relation;
        }

        /** Chooses an alias which is unique within the star. */
        private String chooseAlias() {
            List<String> aliasList = star.getAliasList();
            for (int i = 0;; ++i) {
                String candidateAlias = RelationUtil.getAlias(relation);
                if (i > 0) {
                    candidateAlias += "_" + i;
                }
                if (!aliasList.contains(candidateAlias)) {
                    return candidateAlias;
                }
            }
        }

        public String getAlias() {
            return alias;
        }

        /**
         * Sometimes one need to get to the "real" name when the table has
         * been given an alias.
         */
        public String getTableName() {
            if (relation instanceof MappingTableQuery t) {
                return t.getName();
            } else {
                return null;
            }
        }

        synchronized void makeMeasure(RolapBaseCubeMeasure measure) {
            // Remove assertion to allow cube to be recreated
            RolapStar.Measure starMeasure = new RolapStar.Measure(
                measure.getName(),
                measure.getCube().getName(),
                measure.getAggregator(),
                this,
                measure.getMondrianDefExpression(),
                measure.getDatatype());

            measure.setStarMeasure(starMeasure); // reverse mapping

            if (containsColumn(starMeasure)) {
                decrementColumnCount();
            } else {
                addColumn(starMeasure);
            }
        }

        /**
         * Decrements the column counter; used if a newly
         * created column is found to already exist.
         */
        private int decrementColumnCount() {
            return star.columnCount--;
        }

        /**
         * This is only called by RolapCube. If the RolapLevel has a non-null
         * name expression then two columns will be made, otherwise only one.
         * Updates the RolapLevel to RolapStar.Column mapping associated with
         * this cube.
         *
         * @param cube Cube
         * @param level Level
         * @param parentColumn Parent column
         */
        synchronized Column makeColumns(
            RolapCube cube,
            RolapCubeLevel level,
            Column parentColumn,
            String usagePrefix)
        {
            Column nameColumn = null;
            if (level.getNameExp() != null) {
                // make a column for the name expression
                nameColumn = makeColumnForLevelExpr(
                    level,
                    level.getName(),
                    level.getNameExp(),
                    Datatype.STRING,
                    null,
                    null,
                    null,
                    null);
            }

            // select the column's name depending upon whether or not a
            // "named" column, above, has been created.
            String name = (level.getNameExp() == null)
                ? level.getName()
                : new StringBuilder(level.getName()).append(" (Key)").toString();

            // If the nameColumn is not null, then it is associated with this
            // column.
            Column column = makeColumnForLevelExpr(
                level,
                name,
                level.getKeyExp(),
                level.getDatatype(),
                level.getInternalType(),
                nameColumn,
                parentColumn,
                usagePrefix);

            if (column != null) {
                level.setStarKeyColumn(column);
            }
            RolapProperty[] properties = level.getProperties();
            if (properties != null) {
                for (RolapProperty property : properties) {
                    Column propertyColumn = makeColumnForPropertyExpr(
                        property,
                        level,
                        property.getName(),
                        property.getExp(),
                        convertPropertyType(property.getType()),
                        level.getInternalType(),
                        nameColumn,
                        parentColumn,
                        usagePrefix);
                    property.setColumn(propertyColumn);
                }
            }

            return column;
        }

        private Datatype convertPropertyType(Property.Datatype type) {
            switch (type) {
                case TYPE_STRING:
                    return Datatype.STRING;
                case TYPE_NUMERIC:
                    return Datatype.NUMERIC;
                case TYPE_INTEGER:
                    return Datatype.INTEGER;
                case TYPE_LONG:
                    return Datatype.INTEGER;
                case TYPE_BOOLEAN:
                case TYPE_DATE:
                     return Datatype.DATE;
                case TYPE_TIME:
                     return Datatype.TIME;
                case TYPE_TIMESTAMP:
                     return Datatype.TIMESTAMP;
                case TYPE_OTHER:
                     return Datatype.STRING;
                default:
                    return Datatype.STRING;
            }
        }

        private Column makeColumnForLevelExpr(
            RolapLevel level,
            String name,
            MappingExpression expr,
            Datatype datatype,
            BestFitColumnType internalType,
            Column nameColumn,
            Column parentColumn,
            String usagePrefix)
        {
            Table table = this;
            if (expr instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn column) {
                String tableName = column.getTable();
                table = findAncestor(tableName);
                if (table == null) {
                    throw Util.newError(
                        new StringBuilder("Level '").append(level.getUniqueName())
                            .append("' of cube '")
                            .append(this)
                            .append("' is invalid: table '").append(tableName)
                            .append("' is not found in current scope")
                            .append(Util.NL)
                            .append(", star:")
                            .append(Util.NL)
                            .append(getStar()).toString());
                }
                RolapStar.AliasReplacer aliasReplacer =
                    new RolapStar.AliasReplacer(tableName, table.getAlias());
                expr = aliasReplacer.visit(expr);
            }
            // does the column already exist??
            Column c = lookupColumnByExpression(expr);

            RolapStar.Column column;
            // Verify Column is not null and not the same as the
            // nameColumn created previously (bug 1438285)
            if (c != null && !c.equals(nameColumn)) {
                // Yes, well just reuse it
                // You might wonder why the column need be returned if it
                // already exists. Well, it might have been created for one
                // cube, but for another cube using the same fact table, it
                // still needs to be put into the cube level to column map.
                // Trust me, return null and a junit test fails.
                column = c;
            } else {
                // Make a new column and add it
                column = new RolapStar.Column(
                    name,
                    table,
                    expr,
                    datatype,
                    internalType,
                    nameColumn,
                    parentColumn,
                    usagePrefix,
                    level.getApproxRowCount(),
                    star.nextColumnCount());
                addColumn(column);
            }
            return column;
        }

        private Column makeColumnForPropertyExpr(
            RolapProperty property,
            RolapLevel level,
            String name,
            MappingExpression expr,
            Datatype datatype,
            BestFitColumnType internalType,
            Column nameColumn,
            Column parentColumn,
            String usagePrefix)
        {
            Table table = this;
            if (expr instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn column) {
                String tableName = column.getTable();
                table = findAncestor(tableName);
                if (table == null) {
                    throw Util.newError(
                        new StringBuilder("Level '").append(level.getUniqueName())
                            .append("' Property '").append(property.name)
                            .append("' of cube '")
                            .append(this)
                            .append("' is invalid: table '").append(tableName)
                            .append("' is not found in current scope")
                            .append(Util.NL)
                            .append(", star:")
                            .append(Util.NL)
                            .append(getStar()).toString());
                }
                RolapStar.AliasReplacer aliasReplacer =
                    new RolapStar.AliasReplacer(tableName, table.getAlias());
                expr = aliasReplacer.visit(expr);
            }
            // does the column already exist??
            Column c = lookupColumnByExpression(expr);

            RolapStar.Column column;
            // Verify Column is not null and not the same as the
            // nameColumn created previously (bug 1438285)
            if (c != null && !c.equals(nameColumn)) {
                // Yes, well just reuse it
                // You might wonder why the column need be returned if it
                // already exists. Well, it might have been created for one
                // cube, but for another cube using the same fact table, it
                // still needs to be put into the cube level to column map.
                // Trust me, return null and a junit test fails.
                column = c;
            } else {
                // Make a new column and add it
                column = new RolapStar.Column(
                    name,
                    table,
                    expr,
                    datatype,
                    internalType,
                    nameColumn,
                    parentColumn,
                    usagePrefix,
                    level.getApproxRowCount(),
                    star.nextColumnCount());
                addColumn(column);
            }
            return column;
        }
        /**
         * Extends this 'leg' of the star by adding <code>relation</code>
         * joined by <code>joinCondition</code>. If the same expression is
         * already present, does not create it again. Stores the unaliased
         * table names to RolapStar.Table mapping associated with the
         * input <code>cube</code>.
         */
        synchronized Table addJoin(
            RolapCube cube,
            MappingQuery relationOrJoin,
            RolapStar.Condition joinCondition)
        {
            if (relationOrJoin instanceof MappingRelationQuery relationInner) {
                RolapStar.Table starTable =
                    findChild(relationInner, joinCondition);
                if (starTable == null) {
                    starTable = new RolapStar.Table(
                        star, relationInner, this, joinCondition);
                    if (this.children.isEmpty()) {
                        this.children = new ArrayList<>();
                    }
                    this.children.add(starTable);
                }
                return starTable;
            } else if (relationOrJoin instanceof MappingJoinQuery join) {
                RolapStar.Table leftTable =
                    addJoin(cube, left(join), joinCondition);
                String leftAlias = getLeftAlias(join);
                if (leftAlias == null) {
                    // REVIEW: is cast to Relation valid?
                    leftAlias = RelationUtil.getAlias(((MappingRelationQuery) left(join)));
                    if (leftAlias == null) {
                        throw Util.newError(
                            "missing leftKeyAlias in " + relationOrJoin);
                    }
                }
                assert leftTable.findAncestor(leftAlias) == leftTable;
                // switch to uniquified alias
                leftAlias = leftTable.getAlias();

                String rightAlias = getRightAlias(join);
                if (rightAlias == null) {
                    // the right relation of a join may be a join
                    // if so, we need to use the right relation join's
                    // left relation's alias.
                    if (right(join) instanceof MappingJoinQuery joinright) {
                        // REVIEW: is cast to Relation valid?
                        rightAlias =
                            RelationUtil.getAlias(((MappingRelationQuery) left(joinright)));
                    } else {
                        // REVIEW: is cast to Relation valid?
                        rightAlias =
                            RelationUtil.getAlias(((MappingRelationQuery) right(join)));
                    }
                    if (rightAlias == null) {
                        throw Util.newError(
                            "missing rightKeyAlias in " + relationOrJoin);
                    }
                }
                joinCondition = new RolapStar.Condition(
                    new ColumnR(leftAlias, join.left().getKey()),
                    new ColumnR(rightAlias, join.right().getKey()));
                return leftTable.addJoin(
                    cube, right(join), joinCondition);

            } else {
                throw Util.newInternal("bad relation type " + relationOrJoin);
            }
        }

        /**
         * Returns a child relation which maps onto a given relation, or null
         * if there is none.
         */
        public Table findChild(
            MappingRelationQuery relation,
            Condition joinCondition)
        {
            for (Table child : getChildren()) {
                if (child.relation.equals(relation)) {
                    Condition condition = joinCondition;
                    if (!Util.equalName(RelationUtil.getAlias(relation), child.alias)) {
                        // Make the two conditions comparable, by replacing
                        // occurrence of this table's alias with occurrences
                        // of the child's alias.
                        AliasReplacer aliasReplacer = new AliasReplacer(
                            RelationUtil.getAlias(relation), child.alias);
                        condition = aliasReplacer.visit(joinCondition);
                    }
                    if (child.joinCondition.equals(condition)) {
                        return child;
                    }
                }
            }
            return null;
        }

        /**
         * Returns a descendant with a given alias, or null if none found.
         */
        public Table findDescendant(String seekAlias) {
            if (getAlias().equals(seekAlias)) {
                return this;
            }
            for (Table child : getChildren()) {
                Table found = child.findDescendant(seekAlias);
                if (found != null) {
                    return found;
                }
            }
            return null;
        }

        /**
         * Returns an ancestor with a given alias, or null if not found.
         */
        public Table findAncestor(String tableName) {
            for (Table t = this; t != null; t = t.parent) {
                if (RelationUtil.getAlias(t.relation).equals(tableName)) {
                    return t;
                }
            }
            return null;
        }

        public boolean equalsTableName(String tableName) {
            return (this.relation instanceof MappingTableQuery mt && mt.getName().equals(tableName));
        }

        /**
         * Adds this table to the FROM clause of a query, and also, if
         * <code>joinToParent</code>, any join condition.
         *
         * @param query Query to add to
         * @param failIfExists Pass in false if you might have already added
         *     the table before and if that happens you want to do nothing.
         * @param joinToParent Pass in true if you are constraining a cell
         *     calculation, false if you are retrieving members.
         */
        public void addToFrom(
            SqlQuery query,
            boolean failIfExists,
            boolean joinToParent)
        {
            query.addFrom(relation, alias, failIfExists);
            Util.assertTrue((parent == null) == (joinCondition == null));
            if (joinToParent) {
                if (parent != null) {
                    parent.addToFrom(query, failIfExists, joinToParent);
                }
                if (joinCondition != null) {
                    query.addWhere(joinCondition.toString(query));
                }
            }
        }

        /**
         * Returns a list of child {@link Table}s.
         */
        public List<Table> getChildren() {
            return children;
        }

        /**
         * Returns a list of this table's {@link Column}s.
         */
        public List<Column> getColumns() {
            return columnList;
        }

        /**
         * Finds the child table of the fact table with the given columnName
         * used in its left join condition. This is used by the AggTableManager
         * while characterizing the fact table columns.
         */
        public RolapStar.Table findTableWithLeftJoinCondition(
            final String columnName)
        {
            for (Table child : getChildren()) {
                Condition condition = child.joinCondition;
                if (condition != null && condition.left instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn mcolumn && mcolumn.getName().equals(columnName)) {
                    return child;
                }
            }
            return null;
        }

        /**
         * This is used during aggregate table validation to make sure that the
         * mapping from for the aggregate join condition is valid. It returns
         * the child table with the matching left join condition.
         */
        public RolapStar.Table findTableWithLeftCondition(
            final MappingExpression left)
        {
            for (Table child : getChildren()) {
                Condition condition = child.joinCondition;
                if (condition != null && condition.left instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn mcolumn && mcolumn.equals(left)) {
                    return child;
                }
            }
            return null;
        }

        /**
         * Note: I do not think that this is ever true.
         */
        public boolean isFunky() {
            return (relation == null);
        }

        @Override
		public boolean equals(Object obj) {
            if (!(obj instanceof Table other)) {
                return false;
            }
            return getAlias().equals(other.getAlias());
        }
        @Override
		public int hashCode() {
            return getAlias().hashCode();
        }

        @Override
		public String toString() {
            StringWriter sw = new StringWriter(256);
            PrintWriter pw = new PrintWriter(sw);
            print(pw, "");
            pw.flush();
            return sw.toString();
        }

        /**
         * Prints this table and its children.
         */
        public void print(PrintWriter pw, String prefix) {
            pw.print(prefix);
            pw.println("Table:");
            String subprefix = new StringBuilder(prefix).append("  ").toString();

            pw.print(subprefix);
            pw.print("alias=");
            pw.println(getAlias());

            if (this.relation != null) {
                pw.print(subprefix);
                pw.print("relation=");
                pw.println(relation);
            }

            pw.print(subprefix);
            pw.println("Columns:");
            String subsubprefix = new StringBuilder(subprefix).append("  ").toString();

            for (Column column : getColumns()) {
                column.print(pw, subsubprefix);
                pw.println();
            }

            if (this.joinCondition != null) {
                this.joinCondition.print(pw, subprefix);
            }
            for (Table child : getChildren()) {
                child.print(pw, subprefix);
            }
        }

        /**
         * Returns whether this table has a column with the given name.
         */
        public boolean containsColumn(String columnName) {
            if (relation instanceof MappingRelationQuery) {
                return containsColumn(
                    RelationUtil.getAlias((relation)),
                    columnName);
            } else {
                // todo: Deal with join.
                return false;
            }
        }

        private boolean containsColumn(String tableName, String columnName) {
            Connection jdbcConnection;
            try {
                jdbcConnection = star.context.getDataSource().getConnection();
            } catch (SQLException e1) {
                throw Util.newInternal(
                    e1, "Error while creating connection from data source");
            }
            try {
                final DatabaseMetaData metaData = jdbcConnection.getMetaData();
                final ResultSet columns =
                    metaData.getColumns(null, null, tableName, columnName);
                return columns.next();
            } catch (SQLException e) {
                throw Util.newInternal(
                    new StringBuilder("Error while retrieving metadata for table '").append(tableName)
                        .append("', column '").append(columnName).append("'").toString());
            } finally {
                try {
                    jdbcConnection.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }

    }

    public static class Condition {
        private static final Logger LOGGER = LoggerFactory.getLogger(Condition.class);

        private final MappingExpression left;
        private final MappingExpression right;
        // set in Table constructor
        Table table;

        Condition(
            MappingExpression left,
            MappingExpression right)
        {
            assert left != null;
            assert right != null;

            if (!(left instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn)) {
                // TODO: Will this ever print?? if not then left should be
                // of type Column.
                LOGGER.debug("Condition.left NOT Column: {}", left.getClass().getName());
            }
            this.left = left;
            this.right = right;
        }
        public MappingExpression getLeft() {
            return left;
        }
        public String getLeft(final SqlQuery query) {
            return RolapStar.generateExprString(this.left, query);
        }
        public MappingExpression getRight() {
            return right;
        }
        public String getRight(final SqlQuery query) {
            return RolapStar.generateExprString(this.right, query);
        }
        public String toString(SqlQuery query) {
            return new StringBuilder(RolapStar.generateExprString(left, query)).append(" = ")
                .append(RolapStar.generateExprString(right, query)).toString();
        }
        @Override
		public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
		public boolean equals(Object obj) {
            if (!(obj instanceof Condition that)) {
                return false;
            }
            return this.left.equals(that.left)
                && this.right.equals(that.right);
        }

        @Override
		public String toString() {
            StringWriter sw = new StringWriter(256);
            PrintWriter pw = new PrintWriter(sw);
            print(pw, "");
            pw.flush();
            return sw.toString();
        }

        /**
         * Prints this table and its children.
         */
        public void print(PrintWriter pw, String prefix) {
            SqlQuery sqlQueuy = table.getSqlQuery();
            pw.print(prefix);
            pw.println("Condition:");
            String subprefix = new StringBuilder(prefix).append("  ").toString();

            pw.print(subprefix);
            pw.print("left=");
            // print the foreign key bit position if we can figure it out
            if (left instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn c) {
                Column col = table.star.getFactTable().lookupColumn(c.getName());
                if (col != null) {
                    pw.print(" (");
                    pw.print(col.getBitPosition());
                    pw.print(") ");
                }
             }
            pw.println(RolapStar.generateExprString(left, sqlQueuy));

            pw.print(subprefix);
            pw.print("right=");
            pw.println(RolapStar.generateExprString(right,sqlQueuy));
        }
    }

    /**
     * Creates a copy of an expression, everywhere replacing one alias
     * with another.
     */
    public static class AliasReplacer {
        private final String oldAlias;
        private final String newAlias;

        public AliasReplacer(String oldAlias, String newAlias) {
            this.oldAlias = oldAlias;
            this.newAlias = newAlias;
        }

        private Condition visit(Condition condition) {
            if (condition == null) {
                return null;
            }
            if (newAlias.equals(oldAlias)) {
                return condition;
            }
            return new Condition(
                visit(condition.left),
                visit(condition.right));
        }

        public MappingExpression visit(MappingExpression expression) {
            if (expression == null) {
                return null;
            }
            if (newAlias.equals(oldAlias)) {
                return expression;
            }
            if (expression instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn column) {
                return new ColumnR(visit(column.getTable()), column.getName());
            } else {
                throw Util.newInternal("need to implement " + expression);
            }
        }

        private String visit(String table) {
            return table.equals(oldAlias)
                ? newAlias
                : table;
        }
    }

    /**
     * Comparator to compare columns based on their name and table that contains them
     */
    public static class ColumnComparator implements Comparator<Column> {

        public static final ColumnComparator instance = new ColumnComparator();

        private ColumnComparator() {
        }

        /* Compares two columns by their names.
         * If the names of the columns do not differ,
         * compare the tables to which the columns belong
         */
        @Override
		public int compare(Column o1, Column o2) {
          int result = o1.getName().compareTo(o2.getName());
          if (result == 0) {
            result =
                o1.getTable().getAlias().compareTo(o2.getTable().getAlias());
          }
          return result;
        }
    }
}
