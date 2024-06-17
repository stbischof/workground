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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;

import mondrian.rolap.RolapRuntimeException;
import mondrian.rolap.sql.SqlQuery;

public class ExpressionUtil {

    public static String getExpression(MappingExpression expression, final SqlQuery query) {
        if (expression instanceof MappingColumn) {
            return query.getDialect().quoteIdentifier(expression.getTable(), expression.getName());
        }
        if (expression instanceof MappingExpressionView expressionView) {
            SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();
            expressionView.sqls().forEach(e -> codeSet.put(e.dialect(), e.content()));
            return codeSet.chooseQuery(query.getDialect());
        }
        throw new RolapRuntimeException("getExpression error. Expression is not ExpressionView or Column");
    }

    public static int hashCode(MappingExpression expression) {
        if (expression instanceof MappingColumn) {
            return expression.getName().hashCode() ^ (expression.getTable()==null ? 0 : expression.getTable().hashCode());
        }
        if (expression instanceof MappingExpressionView expressionView) {
            int h = 17;
            for (int i = 0; i < expressionView.sqls().size(); i++) {
                h = 37 * h + SQLUtil.hashCode(expressionView.sqls().get(i));
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
            if (expressionView.sqls().size() != that.sqls().size()) {
                return false;
            }
            for (int i = 0; i < expressionView.sqls().size(); i++) {
                if (! expressionView.sqls().get(i).equals(that.sqls().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return expression.equals(obj);
    }

    public static String genericExpression(MappingExpression expression) {
        if (expression instanceof MappingColumn column) {
            return column.getGenericExpression();
        }
        if (expression instanceof MappingExpressionView expressionView) {
            for (int i = 0; i < expressionView.sqls().size(); i++) {
                if (expressionView.sqls().get(i).dialect().equals("generic")) {
                    return expressionView.sqls().get(i).content();
                }
            }
            return ((MappingExpressionView) expression).sqls().get(0).content();
        }
        throw new RolapRuntimeException("genericExpression error");
    }

    public static String toString(MappingExpression expression) {
        if (expression instanceof MappingExpressionView expressionView) {
            return expressionView.sqls().get(0).content();
        }
        return expression.toString();
    }

    public String getExpression(MappingExpressionView expression, SqlQuery query) {
        return SQLUtil.toCodeSet(expression.sqls()).chooseQuery(query.getDialect());
    }

    public static String getTableAlias(MappingExpression expression) {
        if (expression instanceof MappingColumn) {
            return expression.getTable();
        }
        if (expression instanceof MappingExpressionView) {
            return null;
        }
        throw new RolapRuntimeException("Expression getTableAlias error");
    }
}
