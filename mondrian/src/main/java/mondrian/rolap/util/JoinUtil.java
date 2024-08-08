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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.rolap.mapping.api.model.JoinQueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.QueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.RelationalQueryMapping;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;

import mondrian.olap.Util;
import mondrian.rolap.RolapRuntimeException;

public class JoinUtil {

    private JoinUtil() {
        // constructor
    }

    public static QueryMapping left(JoinQueryMapping join) {
        if (join != null && join.getLeft() != null) {
            return join.getLeft().getQuery();
        }
        throw new RolapRuntimeException("Join left error");
    }

    public static QueryMapping right(JoinQueryMapping join) {
        if (join != null && join.getRight() != null) {
            return join.getRight().getQuery();
        }
        throw new RolapRuntimeException("Join right error");
    }

    public static void changeLeftRight(JoinQueryMapping join, QueryMapping left, QueryMapping right) {
        join.getLeft().setQuery(left);
        join.getRight().setQuery(right);
    }

    /**
     * Returns the alias of the left join key, defaulting to left's
     * alias if left is a table.
     */
    public static String getLeftAlias(JoinQueryMapping join) {
        if (join.getLeft() != null && join.getLeft().getAlias() != null) {
            return join.getLeft().getAlias();
        }
        QueryMapping left = left(join);
        if (left instanceof RelationalQueryMapping relation) {
            return RelationUtil.getAlias(relation);
        }
        throw Util.newInternal(
            new StringBuilder("alias is required because ").append(left).append(" is not a table").toString());
    }

    /**
     * Returns the alias of the right join key, defaulting to right's
     * alias if right is a table.
     */
    public static String getRightAlias(JoinQueryMapping join) {
        if (join.getRight() != null && join.getRight().getAlias() != null) {
            return join.getRight().getAlias();
        }
        QueryMapping right = right(join);
        if (right instanceof RelationalQueryMapping relation) {
            return RelationUtil.getAlias(relation);
        }
        if (right instanceof JoinQueryMapping j) {
            return getLeftAlias(j);
        }
        throw Util.newInternal(
            new StringBuilder("alias is required because ").append(right).append(" is not a table").toString());
    }

}
