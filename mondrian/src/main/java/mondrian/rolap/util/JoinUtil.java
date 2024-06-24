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

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;

import mondrian.olap.Util;
import mondrian.rolap.RolapRuntimeException;

public class JoinUtil {

    private JoinUtil() {
        // constructor
    }

    public static MappingQuery left(MappingJoinQuery join) {
        if (join != null && join.left() != null) {
            return join.left().getQuery();
        }
        throw new RolapRuntimeException("Join left error");
    }

    public static MappingQuery right(MappingJoinQuery join) {
        if (join != null && join.right() != null) {
            return join.right().getQuery();
        }
        throw new RolapRuntimeException("Join right error");
    }

    public static void changeLeftRight(MappingJoinQuery join, MappingQuery left, MappingQuery right) {
        join.left().setQuery(left);
        join.right().setQuery(right);
    }

    /**
     * Returns the alias of the left join key, defaulting to left's
     * alias if left is a table.
     */
    public static String getLeftAlias(MappingJoinQuery join) {
        if (join.left() != null && join.left().getAlias() != null) {
            return join.left().getAlias();
        }
        MappingQuery left = left(join);
        if (left instanceof MappingRelation relation) {
            return RelationUtil.getAlias(relation);
        }
        throw Util.newInternal(
            new StringBuilder("alias is required because ").append(left).append(" is not a table").toString());
    }

    /**
     * Returns the alias of the right join key, defaulting to right's
     * alias if right is a table.
     */
    public static String getRightAlias(MappingJoinQuery join) {
        if (join.right() != null && join.right().getAlias() != null) {
            return join.right().getAlias();
        }
        MappingQuery right = right(join);
        if (right instanceof MappingRelation relation) {
            return RelationUtil.getAlias(relation);
        }
        if (right instanceof MappingJoinQuery j) {
            return getLeftAlias(j);
        }
        throw Util.newInternal(
            new StringBuilder("alias is required because ").append(right).append(" is not a table").toString());
    }

}
