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

import static mondrian.rolap.util.JoinUtil.left;
import static mondrian.rolap.util.JoinUtil.right;

import java.util.Objects;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingViewQuery;
import org.eclipse.daanse.rolap.mapping.api.model.JoinQueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.QueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.RelationalQueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.SqlSelectQueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.TableQueryMapping;

import mondrian.rolap.RolapRuntimeException;

public class RelationUtil {

    private RelationUtil() {
        // constructor
    }

    public static RelationalQueryMapping find(QueryMapping relationOrJoin, String tableName) {
        if (relationOrJoin instanceof MappingInlineTableQuery inlineTable) {
            return tableName.equals(inlineTable.getAlias()) ? (RelationalQueryMapping) relationOrJoin : null;
        }
        if (relationOrJoin instanceof MappingTableQuery table) {
            if (tableName.equals(table.getName())) {
                return (RelationalQueryMapping) relationOrJoin;
            } else {
                    return null; //old version of code had wrong condition with equals
            }
        }
        if (relationOrJoin instanceof SqlSelectQueryMapping view) {
            if (tableName.equals(view.getAlias())) {
                return (RelationalQueryMapping) relationOrJoin;
            } else {
                return null;
            }
        }
        if (relationOrJoin instanceof JoinQueryMapping join) {
            QueryMapping relation = find(left(join), tableName);
            if (relation == null) {
                relation = find(right(join), tableName);
            }
            return (RelationalQueryMapping) relation;

        }

        throw new RolapRuntimeException("Rlation: find error");
    }

    public static String getAlias(RelationalQueryMapping relation) {
        if (relation instanceof TableQueryMapping table) {
            return (table.getAlias() != null) ? table.getAlias() : table.getName();
        }
        else {
            return relation.getAlias();
        }
    }

    public static boolean equals(MappingRelationQuery relation, Object o) {
        if (relation instanceof MappingViewQuery view) {
            if (o instanceof MappingViewQuery that) {
                if (!Objects.equals(relation.getAlias(), that.getAlias())) {
                    return false;
                }
                if (view.sql() == null || that.sql() == null
                    || view.sql().sqls() == null || that.sql().sqls() == null
                    || view.sql().sqls().size() != that.sql().sqls().size()) {
                    return false;
                }
                for (int i = 0; i < view.sql().sqls().size(); i++) {
                    if (!Objects.equals(view.sql().sqls().get(i).statement(), that.sql().sqls().get(i).statement()))
                    {
                        return false;
                    }
                    if (view.sql().sqls().get(i).dialects() == null || that.sql().sqls().get(i).dialects() == null
                        || view.sql().sqls().get(i).dialects().size() != that.sql().sqls().get(i).dialects().size()) {
                        return false;
                    }
                    for (int j = 0; j< view.sql().sqls().get(i).dialects().size(); j++) {
                        if (!view.sql().sqls().get(i).dialects().get(j).equals(that.sql().sqls().get(i).dialects().get(j))) {
                            return false;
                        }
                    }
                    return true;
                }
                return true;
            } else {
                return false;
            }
        }
        if (relation instanceof MappingTableQuery table) {
            if (o instanceof MappingTableQuery that) {
                return table.getName().equals(that.getName()) &&
                    Objects.equals(relation.getAlias(), that.getAlias()) &&
                    Objects.equals(table.getSchema(), that.getSchema());
            } else {
                return false;
            }
        }
        if (relation instanceof MappingInlineTableQuery) {
            if (o instanceof MappingInlineTableQuery that) {
                return relation.getAlias().equals(that.getAlias());
            } else {
                return false;
            }

        }
        return relation == o;
    }

    public static int hashCode(MappingRelationQuery relation) {
        if (relation instanceof MappingTableQuery) {
            return toString(relation).hashCode();
        }
        if (relation instanceof MappingInlineTableQuery) {
            return toString(relation).hashCode();
        }
        return System.identityHashCode(relation);
    }

    private static Object toString(MappingRelationQuery relation) {
        if (relation instanceof TableQueryMapping table) {
            return (table.getSchema() == null) ?
                table.getName() :
                new StringBuilder(table.getSchema()).append(".").append(table.getName()).toString();
        }
        if (relation instanceof JoinQueryMapping join) {
            return new StringBuilder("(").append(left(join)).append(") join (").append(right(join)).append(") on ")
                .append(join.getLeft().getAlias()).append(".").append(join.getLeft().getKey()).append(" = ")
                .append(join.getRight().getAlias()).append(".").append(join.getRight().getKey()).toString();
        }
        if (relation instanceof MappingInlineTableQuery) {
            return "<inline data>";
        }
        return relation.toString();
    }

}
