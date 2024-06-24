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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingView;

import mondrian.rolap.RolapRuntimeException;

public class RelationUtil {

    private RelationUtil() {
        // constructor
    }

    public static MappingRelation find(MappingQuery relationOrJoin, String tableName) {
        if (relationOrJoin instanceof MappingInlineTable inlineTable) {
            return tableName.equals(inlineTable.getAlias()) ? (MappingRelation) relationOrJoin : null;
        }
        if (relationOrJoin instanceof MappingTable table) {
            if (tableName.equals(table.getName())) {
                return (MappingRelation) relationOrJoin;
            } else {
                    return null; //old version of code had wrong condition with equals
            }
        }
        if (relationOrJoin instanceof MappingView view) {
            if (tableName.equals(view.getAlias())) {
                return (MappingRelation) relationOrJoin;
            } else {
                return null;
            }
        }
        if (relationOrJoin instanceof MappingJoinQuery join) {
            MappingQuery relation = find(left(join), tableName);
            if (relation == null) {
                relation = find(right(join), tableName);
            }
            return (MappingRelation) relation;

        }

        throw new RolapRuntimeException("Rlation: find error");
    }

    public static String getAlias(MappingRelation relation) {
        if (relation instanceof MappingTable table) {
            return (relation.getAlias() != null) ? relation.getAlias() : table.getName();
        }
        else {
            return relation.getAlias();
        }
    }

    public static boolean equals(MappingRelation relation, Object o) {
        if (relation instanceof MappingView view) {
            if (o instanceof MappingView that) {
                if (!Objects.equals(relation.getAlias(), that.getAlias())) {
                    return false;
                }
                if (view.sqls() == null || that.sqls() == null || view.sqls().size() != that.sqls().size()) {
                    return false;
                }
                for (int i = 0; i < view.sqls().size(); i++) {
                    if (!Objects.equals(view.sqls().get(i).dialect(), that.sqls().get(i).dialect())
                        || !Objects.equals(view.sqls().get(i).content(), that.sqls().get(i).content()))
                    {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        if (relation instanceof MappingTable table) {
            if (o instanceof MappingTable that) {
                return table.getName().equals(that.getName()) &&
                    Objects.equals(relation.getAlias(), that.getAlias()) &&
                    Objects.equals(table.getSchema(), that.getSchema());
            } else {
                return false;
            }
        }
        if (relation instanceof MappingInlineTable) {
            if (o instanceof MappingInlineTable that) {
                return relation.getAlias().equals(that.getAlias());
            } else {
                return false;
            }

        }
        return relation == o;
    }

    public static int hashCode(MappingRelation relation) {
        if (relation instanceof MappingTable) {
            return toString(relation).hashCode();
        }
        if (relation instanceof MappingInlineTable) {
            return toString(relation).hashCode();
        }
        return System.identityHashCode(relation);
    }

    private static Object toString(MappingRelation relation) {
        if (relation instanceof MappingTable table) {
            return (table.getSchema() == null) ?
                table.getName() :
                new StringBuilder(table.getSchema()).append(".").append(table.getName()).toString();
        }
        if (relation instanceof MappingJoinQuery join) {
            return new StringBuilder("(").append(left(join)).append(") join (").append(right(join)).append(") on ")
                .append(join.left().getAlias()).append(".").append(join.left().getKey()).append(" = ")
                .append(join.right().getAlias()).append(".").append(join.right().getKey()).toString();
        }
        if (relation instanceof MappingInlineTable) {
            return "<inline data>";
        }
        return relation.toString();
    }

}
