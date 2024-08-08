/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package mondrian.rolap.util;

import java.util.Objects;

import mondrian.rolap.Column;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.rolap.mapping.api.model.SQLExpressionMapping;

import mondrian.rolap.RolapRuntimeException;
import mondrian.rolap.sql.SqlQuery;

public class ExpressionUtil {

    public static String getExpression1(SQLExpressionMapping expression, final SqlQuery query) {
        if (expression instanceof Column c) {
            return query.getDialect().quoteIdentifier(c.getTable(), c.getName());
        }
        
        SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();
        expression.getSqls().forEach(e -> e.getDialects().forEach(d ->codeSet.put(d, e.getStatement())));
        return codeSet.chooseQuery(query.getDialect());
    }

    public static int hashCode(MappingExpression expression) {
        if (expression instanceof MappingColumn) {
            return expression.getName().hashCode() ^ (expression.getTable()==null ? 0 : expression.getTable().hashCode());
        }
        if (expression instanceof MappingExpressionView expressionView) {
            int h = 17;
            for (int i = 0; i < expressionView.sql().sqls().size(); i++) {
                h = 37 * h + SQLUtil.hashCode(expressionView.sql().sqls().get(i));
            }
            return h;
        }
        return expression.hashCode();
    }

    public static boolean equals(MappingExpression expression, Object obj) {
        if (expression instanceof MappingColumn) {
            if (!(obj instanceof MappingColumn that)) {
                return false;
            }
            return expression.getName().equals(that.getName()) &&
                Objects.equals(expression.getTable(), that.getTable());
        }
        if (expression instanceof MappingExpressionView expressionView) {
            if (!(obj instanceof MappingExpressionView that)) {
                return false;
            }
            if (expressionView.sql().sqls().size() != that.sql().sqls().size()) {
                return false;
            }
            for (int i = 0; i < expressionView.sql().sqls().size(); i++) {
                if (! expressionView.sql().sqls().get(i).equals(that.sql().sqls().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return expression.equals(obj);
    }

    public static String genericExpression(SQLExpressionMapping expression) {
            for (int i = 0; i < expression.getSqls().size(); i++) {
                if (expression.getSqls().get(i).getDialects().stream().anyMatch(d ->  "generic".equals(d))) {
                    return expression.getSqls().get(i).getStatement();
                }
            }
            return ((MappingExpressionView) expression).sql().sqls().get(0).statement();
    }

    public static String toString(SQLExpressionMapping expression) {
    	if (expression != null && expression.getSqls() != null && !expression.getSqls().isEmpty()) {
    		return expression.getSqls().get(0).getStatement();
    	}
    	return null;
    }

    public static String getExpression(SQLExpressionMapping expression, SqlQuery query) {
        return SQLUtil.toCodeSet(expression.getSqls()).chooseQuery(query.getDialect());
    }

    public static String getTableAlias(SQLExpressionMapping expression) {
        if (expression instanceof Column c) {
            return c.getTable();
        }        
        return null;
    }
}
