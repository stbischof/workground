/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// Copyright (C) 2021 Sergei Semenkov
// All Rights Reserved.
//
// jhyde, 22 December, 2001
*/
package mondrian.rolap;

import static mondrian.rolap.util.RelationUtil.getAlias;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Consumer;

import org.eclipse.daanse.db.dialect.api.BestFitColumnType;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.Evaluator;
import org.eclipse.daanse.olap.api.MatchType;
import org.eclipse.daanse.olap.api.NameSegment;
import org.eclipse.daanse.olap.api.Quoting;
import org.eclipse.daanse.olap.api.SchemaReader;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRow;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingValue;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ViewImpl;
import mondrian.olap.StringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.olap.MondrianException;
import mondrian.olap.MondrianProperties;
import mondrian.olap.NativeEvaluationUnsupportedException;
import mondrian.olap.Util;
import mondrian.olap.fun.FunUtil;
import mondrian.resource.MondrianResource;
import mondrian.rolap.RolapHierarchy.LimitedRollupMember;
import mondrian.server.Execution;
import mondrian.server.Locus;
import mondrian.server.Statement;
import mondrian.util.ClassResolver;

/**
 * Utility methods for classes in the <code>mondrian.rolap</code> package.
 *
 * @author jhyde
 * @since 22 December, 2001
 */
public class RolapUtil {

    private RolapUtil() {
        // constructor
    }

    public static final Logger MDX_LOGGER = LoggerFactory.getLogger("mondrian.mdx");
    public static final Logger SQL_LOGGER = LoggerFactory.getLogger("mondrian.sql");
    public static final Logger MONITOR_LOGGER =
        LoggerFactory.getLogger("mondrian.server.monitor");
    public static final Logger PROFILE_LOGGER =
        LoggerFactory.getLogger("mondrian.profile");

    static final Logger LOGGER = LoggerFactory.getLogger(RolapUtil.class);

    /**
     * Special cell value indicates that the value is not in cache yet.
     */
    public static final Object valueNotReadyException = Double.valueOf(0);

    /**
     * Hook to run when a query is executed. This should not be
     * used at runtime but only for testing.
     */
    private static ExecuteQueryHook queryHook = null;

    /**
     * Special value represents a null key.
     */
    public static final Comparable<?> sqlNullValue =
        RolapUtilComparable.INSTANCE;

    public static Consumer<java.sql.Statement> getDefaultCallback(final Locus locus) {
        return stmt -> locus.execution.registerStatement(locus, stmt);
    }

    /**
     * Wraps a schema reader in a proxy so that each call to schema reader
     * has a locus for profiling purposes.
     *
     * @param connection Connection
     * @param schemaReader Schema reader
     * @return Wrapped schema reader
     */
    public static SchemaReader locusSchemaReader(
        RolapConnection connection,
        final SchemaReader schemaReader)
    {
        final Statement statement = connection.getInternalStatement();
        final Execution execution = new Execution(statement, 0);
        final Locus locus =
            new Locus(
                execution,
                "Schema reader",
                null);
        return (SchemaReader) Proxy.newProxyInstance(
            SchemaReader.class.getClassLoader(),
            new Class[]{SchemaReader.class},
            new InvocationHandler() {
                @Override
				public Object invoke(
                    Object proxy,
                    Method method,
                    Object[] args)
                    throws Throwable
                {
                    Locus.push(locus);
                    try {
                        return method.invoke(schemaReader, args);
                    } catch (InvocationTargetException e) {
                        throw e.getCause();
                    } finally {
                        Locus.pop(locus);
                    }
                }
            }
        );
    }

    /**
     * Sets the query-execution hook used by tests. This method and
     * {@link #setHook(mondrian.rolap.RolapUtil.ExecuteQueryHook)} are
     * synchronized to ensure a memory barrier.
     *
     * @return Query execution hook
     */
    public static synchronized ExecuteQueryHook getHook() {
        return queryHook;
    }

    public static synchronized void setHook(ExecuteQueryHook hook) {
        queryHook = hook;
    }

    /**
     * Comparable value, equal only to itself. Used to represent the NULL value,
     * as returned from a SQL query.
     */
    private static final class RolapUtilComparable
        implements Comparable, Serializable
    {
        private static final long serialVersionUID = -2595758291465179116L;

        public static final RolapUtilComparable INSTANCE =
            new RolapUtilComparable();

        // singleton
        private RolapUtilComparable() {
        }

        // do not override equals and hashCode -- use identity

        @Override
		public String toString() {
            return "#null";
        }

        @Override
		public int compareTo(Object o) {
            // collates after everything (except itself)
            return o == this ? 0 : -1;
        }
    }

    /**
     * A comparator singleton instance which can handle the presence of
     * {@link RolapUtilComparable} instances in a collection.
     */
    public static final Comparator ROLAP_COMPARATOR =
        new RolapUtilComparator();

    private static final class RolapUtilComparator<T extends Comparable<T>>
        implements Comparator<T>
    {
        @Override
		public int compare(T o1, T o2) {
            try {
                return o1.compareTo(o2);
            } catch (ClassCastException cce) {
                if (o2 == RolapUtilComparable.INSTANCE) {
                    return 1;
                }
                throw new MondrianException(cce);
            }
        }
    }

    /**
     * Runtime NullMemberRepresentation property change not taken into
     * consideration
     */
    private static String mdxNullLiteral = null;
    public static final String SQL_NULL_LITERAL = "null";

    public static String mdxNullLiteral() {
        if (mdxNullLiteral == null) {
            reloadNullLiteral();
        }
        return mdxNullLiteral;
    }

    public static void reloadNullLiteral() {
        mdxNullLiteral =
            MondrianProperties.instance().NullMemberRepresentation.get();
    }

    /**
     * Names of classes of drivers we've loaded (or have tried to load).
     *
     * <p>NOTE: Synchronization policy: Lock the {@link RolapConnection} class
     * before modifying or using this member.
     */
    private static final Set<String> loadedDrivers = new HashSet<>();

    static RolapMember[] toArray(List<RolapMember> v) {
        return v.isEmpty()
            ? new RolapMember[0]
            : v.toArray(new RolapMember[v.size()]);
    }

    static RolapMember lookupMember(
        MemberReader reader,
        List<Segment> uniqueNameParts,
        boolean failIfNotFound)
    {
        RolapMember member =
            lookupMemberInternal(
                uniqueNameParts, null, reader, failIfNotFound);
        if (member != null) {
            return member;
        }

        // If this hierarchy has an 'all' member, we can omit it.
        // For example, '[Gender].[(All Gender)].[F]' can be abbreviated
        // '[Gender].[F]'.
        final List<RolapMember> rootMembers = reader.getRootMembers();
        if (rootMembers.size() == 1) {
            final RolapMember rootMember = rootMembers.get(0);
            if (rootMember.isAll()) {
                member =
                    lookupMemberInternal(
                        uniqueNameParts, rootMember, reader, failIfNotFound);
            }
        }
        return member;
    }

    private static RolapMember lookupMemberInternal(
        List<Segment> segments,
        RolapMember member,
        MemberReader reader,
        boolean failIfNotFound)
    {
        for (Segment segment : segments) {
            if (!(segment instanceof NameSegment nameSegment)) {
                break;
            }
            List<RolapMember> children;
            if (member == null) {
                children = reader.getRootMembers();
            } else {
                children = new ArrayList<>();
                reader.getMemberChildren(member, children);
                member = null;
            }
            for (RolapMember child : children) {
                if (child.getName().equals(nameSegment.getName())) {
                    member = child;
                    break;
                }
            }
            if (member == null) {
                break;
            }
        }
        if (member == null && failIfNotFound) {
            throw MondrianResource.instance().MdxCantFindMember.ex(
                Util.implode(segments));
        }
        return member;
    }

    /**
     * Executes a query, printing to the trace log if tracing is enabled.
     *
     * <p>If the query fails, it wraps the {@link SQLException} in a runtime
     * exception with <code>message</code> as description, and closes the result
     * set.
     *
     * <p>If it succeeds, the caller must call the {@link SqlStatement#close}
     * method of the returned {@link SqlStatement}.
     *
     * @param dataSource DataSource
     * @param sql SQL string
     * @param locus Locus of execution
     * @return ResultSet
     */
    public static SqlStatement executeQuery(
        Context context,
        String sql,
        Locus locus)
    {
        return executeQuery(
                context, sql, null, 0, 0, locus, -1, -1,
            getDefaultCallback(locus));
    }

    /**
     * Executes a query.
     *
     * <p>If the query fails, it wraps the {@link SQLException} in a runtime
     * exception with <code>message</code> as description, and closes the result
     * set.
     *
     * <p>If it succeeds, the caller must call the {@link SqlStatement#close}
     * method of the returned {@link SqlStatement}.
     *
     *
     * @param dataSource DataSource
     * @param sql SQL string
     * @param types Suggested types of columns, or null;
     *     if present, must have one element for each SQL column;
     *     each not-null entry overrides deduced JDBC type of the column
     * @param maxRowCount Maximum number of rows to retrieve, <= 0 if unlimited
     * @param firstRowOrdinal Ordinal of row to skip to (1-based), or 0 to
     *   start from beginning
     * @param locus Execution context of this statement
     * @param resultSetType Result set type, or -1 to use default
     * @param resultSetConcurrency Result set concurrency, or -1 to use default
     * @return ResultSet
     */
    public static SqlStatement executeQuery(
        Context context,
        String sql,
        List<BestFitColumnType> types,
        int maxRowCount,
        int firstRowOrdinal,
        Locus locus,
        int resultSetType,
        int resultSetConcurrency,
        Consumer<java.sql.Statement> callback)
    {
        SqlStatement stmt =
            new SqlStatement(
                    context, sql, types, maxRowCount, firstRowOrdinal, locus,
                resultSetType, resultSetConcurrency,
                callback == null
                    ? getDefaultCallback(locus)
                    : callback);
        stmt.execute();
        return stmt;
    }

    /**
     * Raises an alert that native SQL evaluation could not be used
     * in a case where it might have been beneficial, but some
     * limitation in Mondrian's implementation prevented it.
     * (Do not call this in cases where native evaluation would
     * have been wasted effort.)
     *
     * @param functionName name of function for which native evaluation
     * was skipped
     *
     * @param reason reason why native evaluation was skipped
     */
    public static void alertNonNative(
        String functionName,
        String reason)
        throws NativeEvaluationUnsupportedException
    {
        // No i18n for log message, but yes for excn
        String alertMsg =
            new StringBuilder("Unable to use native SQL evaluation for '").append(functionName)
            .append("'; reason:  ").append(reason).toString();

        StringProperty alertProperty =
            MondrianProperties.instance().AlertNativeEvaluationUnsupported;
        String alertValue = alertProperty.get();

        if (alertValue.equalsIgnoreCase(
                "WARN"))
        {
            LOGGER.warn(alertMsg);
        } else if (alertValue.equalsIgnoreCase(
                "ERROR"))
        {
            LOGGER.error(alertMsg);
            throw MondrianResource.instance().NativeEvaluationUnsupported.ex(
                functionName);
        }
    }

    /**
     * Loads a set of JDBC drivers.
     *
     * @param jdbcDrivers A string consisting of the comma-separated names
     *  of JDBC driver classes. For example
     *  <code>"sun.jdbc.odbc.JdbcOdbcDriver,com.mysql.jdbc.Driver"</code>.
     */
    public static synchronized void loadDrivers(String jdbcDrivers) {
        StringTokenizer tok = new StringTokenizer(jdbcDrivers, ",");
        while (tok.hasMoreTokens()) {
            String jdbcDriver = tok.nextToken();
            if (loadedDrivers.add(jdbcDriver)) {
                try {
                    ClassResolver.INSTANCE.forName(jdbcDriver, true);
                    LOGGER.info(
                        "Mondrian: JDBC driver {} loaded successfully", jdbcDriver);
                } catch (ClassNotFoundException e) {
                    LOGGER.warn(
                        "Mondrian: Warning: JDBC driver {} not found", jdbcDriver);
                }
            }
        }
    }

    /**
     * Creates a compiler which will generate programs which will test
     * whether the dependencies declared via
     * {@link mondrian.calc.Calc#dependsOn(MappingHierarchy)} are accurate.
     */
    public static ExpressionCompiler createDependencyTestingCompiler(
        ExpressionCompiler compiler)
    {
        return new RolapDependencyTestingEvaluator.DteCompiler(compiler);
    }

    /**
     * Locates a member specified by its member name, from an array of
     * members.  If an exact match isn't found, but a matchType of BEFORE
     * or AFTER is specified, then the closest matching member is returned.
     *
     *
     * @param members array of members to search from
     * @param parent parent member corresponding to the member being searched
     * for
     * @param level level of the member
     * @param searchName member name
     * @param matchType match type
     * @return matching member (if it exists) or the closest matching one
     * in the case of a BEFORE or AFTER search
     */
    public static Member findBestMemberMatch(
        List<? extends Member> members,
        RolapMember parent,
        RolapLevel level,
        Segment searchName,
        MatchType matchType)
    {
        if (!(searchName instanceof NameSegment nameSegment)) {
            return null;
        }
        switch (matchType) {
        case FIRST:
            return members.get(0);
        case LAST:
            return members.get(members.size() - 1);
        default:
            // fall through
        }
        // create a member corresponding to the member we're trying
        // to locate so we can use it to hierarchically compare against
        // the members array
        Member searchMember =
            level.getHierarchy().createMember(
                parent, level, nameSegment.getName(), null);
        Member bestMatch = null;
        for (Member member : members) {
            int rc;
            if (searchName.getQuoting() == Quoting.KEY
                && member instanceof RolapMember rolapMember
                && rolapMember.getKey().toString().equals(
                nameSegment.getName()))
            {
                return member;
            }
            if (matchType.isExact()) {
                rc = Util.compareName(member.getName(), nameSegment.getName());
            } else {
                rc =
                    FunUtil.compareSiblingMembers(
                        member,
                        searchMember);
            }
            if (rc == 0) {
                return member;
            }
            if (matchType == MatchType.BEFORE) {
                if (rc < 0
                    && (bestMatch == null
                        || FunUtil.compareSiblingMembers(member, bestMatch)
                        > 0))
                {
                    bestMatch = member;
                }
            } else if (matchType == MatchType.AFTER && rc > 0
                    && (bestMatch == null
                        || FunUtil.compareSiblingMembers(member, bestMatch)
                        < 0)) {
                bestMatch = member;
            }
        }
        if (matchType.isExact()) {
            return null;
        }
        return bestMatch;
    }

    public static MappingRelation convertInlineTableToRelation(
        MappingInlineTable inlineTable,
        final Dialect dialect)
    {
        ViewImpl view = new ViewImpl();
        view.setAlias(getAlias(inlineTable));

        final int columnCount = inlineTable.columnDefs().size();
        List<String> columnNames = new ArrayList<>();
        List<String> columnTypes = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            columnNames.add(inlineTable.columnDefs().get(i).name());
            columnTypes.add(inlineTable.columnDefs().get(i).type().getValue());
        }
        List<String[]> valueList = new ArrayList<>();
        for (MappingRow row : inlineTable.rows()) {
            String[] values = new String[columnCount];
            for (MappingValue value : row.values()) {
                final int columnOrdinal = columnNames.indexOf(value.column());
                if (columnOrdinal < 0) {
                    throw Util.newError(
                        new StringBuilder("Unknown column '").append(value.column()).append("'").toString());
                }
                values[columnOrdinal] = value.content();
            }
            valueList.add(values);
        }
        view.addCode(
            "generic",
            dialect.generateInline(
                columnNames,
                columnTypes,
                valueList).toString());
        return view;
    }

    public static RolapMember strip(RolapMember member) {
        if (member instanceof RolapCubeMember rolapCubeMember) {
            return rolapCubeMember.getRolapMember();
        }
        return member;
    }

    public static ExpressionCompiler createProfilingCompiler(ExpressionCompiler compiler) {
        return new RolapProfilingEvaluator.ProfilingEvaluatorCompiler(
            compiler);
    }

    /**
     * Creates a dummy evaluator.
     */
    public static Evaluator createEvaluator(
        Statement statement)
    {
        Execution dummyExecution = new Execution(statement, 0);
        final RolapResult result = new RolapResult(dummyExecution, false);
        return result.getRootEvaluator();
    }

    public static interface ExecuteQueryHook {
        void onExecuteQuery(String sql);
    }

    /**
     * Modifies a bitkey so that it includes the proper bits
     * for members in an array which should be considered
     * as a limited rollup member.
     */
    public static void constraintBitkeyForLimitedMembers(
        Evaluator evaluator,
        Member[] members,
        RolapCube cube,
        BitKey levelBitKey)
    {
        // Limited Rollup Members have to be included in the bitkey
        // so that we can pick the correct agg table.
        for (Member curMember : members) {
            if (curMember instanceof LimitedRollupMember limitedRollupMember) {
                final int savepoint = evaluator.savepoint();
                try {
                    // set NonEmpty to false to avoid the possibility of
                    // constraining member retrieval by context, which itself
                    // requires determination of limited members, resulting
                    // in infinite loop.
                    evaluator.setNonEmpty(false);
                    List<Member> lowestMembers =
                        ((RolapHierarchy)curMember.getHierarchy())
                            .getLowestMembersForAccess(
                                evaluator,
                                limitedRollupMember
                                    .hierarchyAccess,
                                FunUtil.getNonEmptyMemberChildrenWithDetails(
                                    evaluator,
                                    curMember));

                    assert !lowestMembers.isEmpty();

                    Member lowMember = lowestMembers.get(0);

                    while (true) {
                        RolapStar.Column curColumn =
                            ((RolapCubeLevel)lowMember.getLevel())
                                .getBaseStarKeyColumn(cube);

                        if (curColumn != null) {
                            levelBitKey.set(curColumn.getBitPosition());
                        }

                        // If the level doesn't have unique members, we have to
                        // add the parent levels until the keys are unique,
                        // or all of them are added.
                        if (!((RolapCubeLevel)lowMember
                            .getLevel()).isUnique())
                        {
                            lowMember = lowMember.getParentMember();
                            if (lowMember.isAll()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } finally {
                    evaluator.restore(savepoint);
                }
            }
        }
    }

    /**Generates rolap star key based on the fact
     * using fact alias and SQl filter data
     * if this one is present in the fact
     * @param fact the fact based on which is generated the rolap star key
     * @return the rolap star key
     */
    public static List<String> makeRolapStarKey(
        final MappingRelation fact)
    {
      List<String> rlStarKey = new ArrayList<>();
      MappingTable table = null;
      rlStarKey.add(getAlias(fact));
      if (fact instanceof MappingTable t) {
        table = t;
      }
      // Add SQL filter to the key
      if (!Util.isNull(table) && table != null && !Util.isNull(table.sql())
          && !Util.isBlank(table.sql().content()))
      {
        rlStarKey.add(table.sql().dialect());
        rlStarKey.add(table.sql().content());
      }
      return Collections.unmodifiableList(rlStarKey);
    }

    /**Generates rolap star key based on the fact table name.
     * @param factTableName the fact table name
     * based on which is generated the rolap star key
     * @return the rolap star key
     */
    public static List<String> makeRolapStarKey(String factTableName) {
      return Collections.unmodifiableList(Arrays.asList(factTableName));
    }

    /**
     * <p>Determines whether the GROUP BY clause is required, based on the
     * schema definitions of the hierarchy and level properties.</p>
     *
     * <p>The GROUP BY clause may only be eliminated if the level identified by
     * the uniqueKeyLevelName exists, the query is at a depth to include it, and all properties in the included levels are
     * functionally dependent on their level values.</p>
     *
     * @param hierarchy  Hierarchy of the cube
     * @param levels     Levels in this hierarchy
     * @param levelDepth Level depth at which the query is occuring
     * @return whether the GROUP BY is needed
     */
    public static boolean isGroupByNeeded(
            RolapHierarchy hierarchy,
            RolapLevel[] levels,
            int levelDepth ) {
        // Figure out if we need to generate GROUP BY at all.  It may only be
        // eliminated if we are at a depth that includes the unique key level,
        // and all properties of included levels depend on the level value.
        boolean needsGroupBy = false;  // figure out if we need GROUP BY at all

        if ( hierarchy.getUniqueKeyLevelName() == null ) {
            needsGroupBy = true;
        } else {
            boolean foundUniqueKeyLevelName = false;
            for ( int i = 0; i <= levelDepth; i++ ) {
                RolapLevel lvl = levels[ i ];

                // can ignore the "all" level
                if ( !( lvl.isAll() ) ) {
                    if ( hierarchy.getUniqueKeyLevelName().equals(
                            lvl.getName() ) ) {
                        foundUniqueKeyLevelName = true;
                    }
                    for ( RolapProperty p : lvl.getProperties() ) {
                        if ( !p.dependsOnLevelValue() ) {
                            needsGroupBy = true;
                            // GROUP BY is required, so break out of
                            // properties loop
                            break;
                        }
                    }
                    if ( needsGroupBy ) {
                        // GROUP BY is required, so break out of levels loop
                        break;
                    }
                }
            }
            if ( !foundUniqueKeyLevelName ) {
                // if we're not deep enough to be unique,
                // then the GROUP BY is required
                needsGroupBy = true;
            }
        }

        return needsGroupBy;
    }

}
