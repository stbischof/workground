package mondrian.rolap.physicalschema;

import mondrian.rolap.RolapConnection;

import mondrian.rolap.sql.SqlQuery;
import mondrian.server.Execution;
import mondrian.server.Statement;
import mondrian.spi.impl.SqlStatisticsProviderNew;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Context;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides and caches statistics of relations and columns.
 *
 * <p>Wrapper around a chain of {@link mondrian.spi.StatisticsProvider}s,
 * followed by a cache to store the results.</p>
 */
public class PhysStatistic {
    private final Map<List, Long> columnMap =
        new HashMap<List, Long>();
    private final Map<List, Long> tableMap =
        new HashMap<List, Long>();
    private final Map<String, Long> queryMap =
        new HashMap<String, Long>();
    private final Dialect dialect;
    private final Context context;
    private final Statement internalStatement;
    private final DataSource dataSource;

    PhysStatistic(
        Context context,
        Dialect dialect,
        RolapConnection internalConnection)
    {
        this.context = context;
        this.dialect = dialect;
        this.internalStatement = internalConnection.getInternalStatement();
        this.dataSource = internalConnection.getDataSource();
    }

    public long getRelationCardinality(
        PhysRelation relation,
        String alias,
        int approxRowCount)
    {
        if (approxRowCount >= 0) {
            return approxRowCount;
        }
        if (relation instanceof PhysTable) {
            final PhysTable table =
                (PhysTable) relation;
            return getTableCardinality(
                null, table.getSchemaName(), table.name);
        } else {
            final SqlQuery sqlQuery = new SqlQuery(dialect);
            sqlQuery.addSelect("1", null);
            sqlQuery.addFrom(relation, null, true);
            return getQueryCardinality(sqlQuery.toString());
        }
    }

    private long getTableCardinality(
        String catalog,
        String schema,
        String table)
    {
        final List<String> key = Arrays.asList(catalog, schema, table);
        long rowCount = -1;
        if (tableMap.containsKey(key)) {
            rowCount = tableMap.get(key);
        } else {
            final List<SqlStatisticsProviderNew> statisticsProviders = List.of(new SqlStatisticsProviderNew());
            final Execution execution =
                new Execution(internalStatement, 0);
            for (SqlStatisticsProviderNew statisticsProvider
                : statisticsProviders)
            {
                rowCount = statisticsProvider.getTableCardinality(
                    context,
                    catalog,
                    schema,
                    table,
                    execution);
                if (rowCount >= 0) {
                    break;
                }
            }

            // Note: If all providers fail, we put -1 into the cache,
            // to ensure that we won't try again.
            tableMap.put(key, rowCount);
        }
        return rowCount;
    }

    private long getQueryCardinality(String sql) {
        long rowCount = -1;
        if (queryMap.containsKey(sql)) {
            rowCount = queryMap.get(sql);
        } else {
            //final List<StatisticsProvider> statisticsProviders =
            //    dialect.getStatisticsProviders();
            final List<SqlStatisticsProviderNew> statisticsProviders = List.of(new SqlStatisticsProviderNew());
            final Execution execution =
                new Execution(
                    internalStatement,
                    0);
            for (SqlStatisticsProviderNew statisticsProvider
                : statisticsProviders)
            {
                rowCount = statisticsProvider.getQueryCardinality(
                    context, sql, execution);
                if (rowCount >= 0) {
                    break;
                }
            }

            // Note: If all providers fail, we put -1 into the cache,
            // to ensure that we won't try again.
            queryMap.put(sql, rowCount);
        }
        return rowCount;
    }

    public long getColumnCardinality(
        PhysRelation relation,
        PhysColumn column,
        int approxCardinality)
    {
        if (approxCardinality >= 0) {
            return approxCardinality;
        }
        if (relation instanceof PhysTable
            && column instanceof PhysRealColumn)
        {
            final PhysTable table =
                (PhysTable) relation;
            return getColumnCardinality(
                null,
                table.getSchemaName(),
                table.name,
                column.name);
        } else {
            final SqlQuery sqlQuery = new SqlQuery(dialect);
            sqlQuery.setDistinct(true);
            sqlQuery.addSelect(column.toSql(), null);
            sqlQuery.addFrom(relation, null, true);
            return getQueryCardinality(sqlQuery.toString());
        }
    }

    private long getColumnCardinality(
        String catalog,
        String schema,
        String table,
        String column)
    {
        final List<String> key =
            Arrays.asList(catalog, schema, table, column);
        long rowCount = -1;
        if (columnMap.containsKey(key)) {
            rowCount = columnMap.get(key);
        } else {
            //final List<StatisticsProvider> statisticsProviders =
            //    dialect.getStatisticsProviders();
            final List<SqlStatisticsProviderNew> statisticsProviders = List.of(new SqlStatisticsProviderNew());
            final Execution execution =
                new Execution(
                    internalStatement,
                    0);
            for (SqlStatisticsProviderNew statisticsProvider
                : statisticsProviders)
            {
                rowCount = statisticsProvider.getColumnCardinality(
                    context,
                    catalog,
                    schema,
                    table,
                    column,
                    execution);
                if (rowCount >= 0) {
                    break;
                }
            }

            // Note: If all providers fail, we put -1 into the cache,
            // to ensure that we won't try again.
            columnMap.put(key, rowCount);
        }
        return rowCount;
    }
}
