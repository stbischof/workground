/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import static mondrian.rolap.util.ExpressionUtil.getExpression1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;
import org.eclipse.daanse.rolap.mapping.api.model.RelationalQueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.SQLExpressionMapping;

import mondrian.rolap.sql.SqlQuery;
import mondrian.server.ExecutionImpl;
import mondrian.spi.impl.SqlStatisticsProviderNew;

/**
 * Provides and caches statistics.
 *
 * <p>Wrapper around a chain of {@link mondrian.spi.StatisticsProvider}s,
 * followed by a cache to store the results.</p>
 */
public class RolapStatisticsCache {
    private final RolapStar star;
    private final Map<List, Long> columnMap = new HashMap<>();
    private final Map<List, Long> tableMap = new HashMap<>();
    private final Map<String, Long> queryMap =
        new HashMap<>();

    public RolapStatisticsCache(RolapStar star) {
        this.star = star;
    }

    public long getRelationCardinality(
        RelationalQueryMapping relation,
        String alias,
        long approxRowCount)
    {
        if (approxRowCount >= 0) {
            return approxRowCount;
        }
        if (relation instanceof MappingTableQuery table) {
            return getTableCardinality(
                null, table.getSchema(), table.getName());
        } else {
            final SqlQuery sqlQuery = star.getSqlQuery();
            sqlQuery.addSelect("*", null);
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
            final Dialect dialect = star.getSqlQueryDialect();
            //final List<StatisticsProvider> statisticsProviders =
            //    dialect.getStatisticsProviders();
            final List<SqlStatisticsProviderNew> statisticsProviders = List.of(new SqlStatisticsProviderNew());
            final ExecutionImpl execution =
                new ExecutionImpl(
                    star.getSchema().getInternalConnection()
                        .getInternalStatement(),
                    star.getSchema().getInternalConnection().getContext().getConfig().executeDurationValue());
            for (SqlStatisticsProviderNew statisticsProvider : statisticsProviders) {
                rowCount = statisticsProvider.getTableCardinality(
                    star.getContext(),
                    catalog,
                    schema,
                    table,
                    execution);
                if (rowCount >= 0) {
                    break;
                }
            }

            // Note: If all providers fail, we put -1 into the cache, to ensure
            // that we won't try again.
            tableMap.put(key, rowCount);
        }
        return rowCount;
    }

    private long getQueryCardinality(String sql) {
        long rowCount = -1;
        if (queryMap.containsKey(sql)) {
            rowCount = queryMap.get(sql);
        } else {
            final Dialect dialect = star.getSqlQueryDialect();
            //final List<StatisticsProvider> statisticsProviders =
            //    dialect.getStatisticsProviders();
            final List<SqlStatisticsProviderNew> statisticsProviders = List.of(new SqlStatisticsProviderNew());
            final ExecutionImpl execution =
                new ExecutionImpl(
                    star.getSchema().getInternalConnection()
                        .getInternalStatement(),
                    star.getSchema().getInternalConnection().getContext().getConfig().executeDurationValue());
            for (SqlStatisticsProviderNew statisticsProvider : statisticsProviders) {
                rowCount = statisticsProvider.getQueryCardinality( star.getContext(), sql, execution);
                if (rowCount >= 0) {
                    break;
                }
            }

            // Note: If all providers fail, we put -1 into the cache, to ensure
            // that we won't try again.
            queryMap.put(sql, rowCount);
        }
        return rowCount;
    }

    public long getColumnCardinality(
    	RelationalQueryMapping relation,
    	SQLExpressionMapping expression,
        long approxCardinality)
    {
        if (approxCardinality >= 0) {
            return approxCardinality;
        }
        if (relation instanceof MappingTableQuery table
            && expression instanceof MappingColumn column)
        {
            return getColumnCardinality(
                null,
                table.getSchema(),
                table.getName(),
                column.getName());
        } else {
            final SqlQuery sqlQuery = star.getSqlQuery();
            sqlQuery.setDistinct(true);
            sqlQuery.addSelect(getExpression1( expression, sqlQuery), null);
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
        final List<String> key = Arrays.asList(catalog, schema, table, column);
        long rowCount = -1;
        if (columnMap.containsKey(key)) {
            rowCount = columnMap.get(key);
        } else {
            final Dialect dialect = star.getSqlQueryDialect();
            final List<SqlStatisticsProviderNew> statisticsProviders = List.of(new SqlStatisticsProviderNew());
            //final List<StatisticsProvider> statisticsProviders =
            //    dialect.getStatisticsProviders();
            final ExecutionImpl execution =
                new ExecutionImpl(
                    star.getSchema().getInternalConnection()
                        .getInternalStatement(),
                    star.getSchema().getInternalConnection().getContext().getConfig().executeDurationValue());
            for (SqlStatisticsProviderNew statisticsProvider : statisticsProviders) {
                rowCount = statisticsProvider.getColumnCardinality(
                    star.getContext(),
                    catalog,
                    schema,
                    table,
                    column,
                    execution);
                if (rowCount >= 0) {
                    break;
                }
            }

            // Note: If all providers fail, we put -1 into the cache, to ensure
            // that we won't try again.
            columnMap.put(key, rowCount);
        }
        return rowCount;
    }

    public int getColumnCardinality2(
        DataSource dataSource,
        Dialect dialect,
        String catalog,
        String schema,
        String table,
        String column)
    {
        return -1;
    }
}
