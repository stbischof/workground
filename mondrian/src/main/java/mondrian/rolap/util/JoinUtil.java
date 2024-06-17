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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;

import mondrian.olap.Util;
import mondrian.rolap.RolapRuntimeException;

public class JoinUtil {

    private JoinUtil() {
        // constructor
    }

    public static MappingRelationOrJoin left(MappingJoin join) {
        if (join.getRelations() != null && !join.getRelations().isEmpty()) {
            return join.getRelations().get(0);
        }
        throw new RolapRuntimeException("Join left error");
    }

    public static MappingRelationOrJoin right(MappingJoin join) {
        if (join.getRelations() != null && join.getRelations().size() > 1) {
            return join.getRelations().get(1);
        }
        throw new RolapRuntimeException("Join left error");
    }

    public static void changeLeftRight(MappingJoin join, MappingRelationOrJoin left, MappingRelationOrJoin right) {
        join.setRelations(List.of(left, right));
    }

    /**
     * Returns the alias of the left join key, defaulting to left's
     * alias if left is a table.
     */
    public static String getLeftAlias(MappingJoin join) {
        if (join.getLeftAlias() != null) {
            return join.getLeftAlias();
        }
        MappingRelationOrJoin left = left(join);
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
    public static String getRightAlias(MappingJoin join) {
        if (join.getRightAlias() != null) {
            return join.getRightAlias();
        }
        MappingRelationOrJoin right = right(join);
        if (right instanceof MappingRelation relation) {
            return RelationUtil.getAlias(relation);
        }
        if (right instanceof MappingJoin j) {
            return getLeftAlias(j);
        }
        throw Util.newInternal(
            new StringBuilder("alias is required because ").append(right).append(" is not a table").toString());
    }

}
