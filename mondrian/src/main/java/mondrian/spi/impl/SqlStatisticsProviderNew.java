/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.spi.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Context;

import mondrian.rolap.RolapUtil;
import mondrian.rolap.SqlStatement;
import mondrian.server.Execution;
import mondrian.server.Locus;

/**
 * Implementation of {@link mondrian.spi.StatisticsProvider} that generates
 * SQL queries to count rows and distinct values.
 */
//TODO remove this class when new SqlStatisticsProvider will ready
public class SqlStatisticsProviderNew  {
    public long getTableCardinality(
        Context context,
        String catalog,
        String schema,
        String table,
        Execution execution)
    {
        StringBuilder buf = new StringBuilder("select count(*) from ");
        context.getDialect().quoteIdentifier(buf, catalog, schema, table);
        final String sql = buf.toString();
        SqlStatement stmt =
            RolapUtil.executeQuery(
                context,
                sql,
                new Locus(
                    execution,
                    "SqlStatisticsProvider.getTableCardinality",
                    "Reading row count from table "
                        + Arrays.asList(catalog, schema, table)));
        try {
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                ++stmt.rowCount;
                return resultSet.getInt(1);
            }
            return -1; // huh?
        } catch (SQLException e) {
            throw stmt.handle(e);
        } finally {
            stmt.close();
        }
    }

    public long getQueryCardinality(
        Context context,
        String sql,
        Execution execution)
    {
        Dialect dialect=context.getDialect();
        final StringBuilder buf = new StringBuilder();
        buf.append(
            "select count(*) from (").append(sql).append(")");
        if (dialect.requiresAliasForFromQuery()) {
            if (dialect.allowsAs()) {
                buf.append(" as ");
            } else {
                buf.append(" ");
            }
            dialect.quoteIdentifier(buf, "init");
        }
        final String countSql = buf.toString();
        SqlStatement stmt =
            RolapUtil.executeQuery(
                context,
                countSql,
                new Locus(
                    execution,
                    "SqlStatisticsProvider.getQueryCardinality",
                    new StringBuilder("Reading row count from query [").append(sql).append("]").toString()));
        try {
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                ++stmt.rowCount;
                return resultSet.getInt(1);
            }
            return -1; // huh?
        } catch (SQLException e) {
            throw stmt.handle(e);
        } finally {
            stmt.close();
        }
    }

    public long getColumnCardinality(
       Context context,
        String catalog,
        String schema,
        String table,
        String column,
        Execution execution)
    {
        final String sql =
            generateColumnCardinalitySql(
                    context.getDialect(), schema, table, column);
        if (sql == null) {
            return -1;
        }
        SqlStatement stmt =
            RolapUtil.executeQuery(
                context,
                sql,
                new Locus(
                    execution,
                    "SqlStatisticsProvider.getColumnCardinality",
                    "Reading cardinality for column "
                        + Arrays.asList(catalog, schema, table, column)));
        try {
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                ++stmt.rowCount;
                return resultSet.getInt(1);
            }
            return -1; // huh?
        } catch (SQLException e) {
            throw stmt.handle(e);
        } finally {
            stmt.close();
        }
    }

    private static String generateColumnCardinalitySql(
        Dialect dialect,
        String schema,
        String table,
        String column)
    {
        final StringBuilder buf = new StringBuilder();
        StringBuilder exprStringBuilder = dialect.quoteIdentifier(column);
        if (dialect.allowsCountDistinct()) {
            // e.g. "select count(distinct product_id) from product"
            buf.append("select count(distinct ")
                .append(exprStringBuilder)
                .append(") from ");
            dialect.quoteIdentifier(buf, schema, table);
            return buf.toString();
        } else if (dialect.allowsFromQuery()) {
            // Some databases (e.g. Access) don't like 'count(distinct)',
            // so use, e.g., "select count(*) from (select distinct
            // product_id from product)"
            buf.append("select count(*) from (select distinct ")
                .append(exprStringBuilder)
                .append(" from ");
            dialect.quoteIdentifier(buf, schema, table);
            buf.append(")");
            if (dialect.requiresAliasForFromQuery()) {
                if (dialect.allowsAs()) {
                    buf.append(" as ");
                } else {
                    buf.append(' ');
                }
                dialect.quoteIdentifier(buf, "init");
            }
            return buf.toString();
        } else {
            // Cannot compute cardinality: this database neither supports COUNT
            // DISTINCT nor SELECT in the FROM clause.
            return null;
        }
    }

}
