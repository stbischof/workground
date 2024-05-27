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
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;

public class JoinR implements MappingJoin {

    private List<MappingRelationOrJoin> relations;
    private String leftAlias;
    private String leftKey;
    private String rightAlias;
    private String rightKey;

    public JoinR(List<MappingRelationOrJoin> relations,
                    String leftAlias,
                    String leftKey,
                    String rightAlias,
                    String rightKey)
    {
        this.relations = relations == null ? List.of() : relations;
        this.leftAlias = leftAlias;
        this.leftKey = leftKey;
        this.rightAlias = rightAlias;
        this.rightKey = rightKey;
    }

    @Override
    public List<MappingRelationOrJoin> relations() {
        return relations;
    }

    @Override
    public String leftAlias() {
        return leftAlias;
    }

    @Override
    public String leftKey() {
        return leftKey;
    }

    @Override
    public String rightAlias() {
        return rightAlias;
    }

    @Override
    public String rightKey() {
        return rightKey;
    }

    @Override
    public void setLeftAlias(String leftAlias) {
        this.leftAlias = leftAlias;
    }

    @Override
    public void setLeftKey(String leftKey) {
        this.leftKey = leftKey;
    }

    @Override
    public void setRightAlias(String rightAlias) {
        this.rightAlias = rightAlias;
    }

    @Override
    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
    }

    @Override
    public void setRelations(List<MappingRelationOrJoin> relations) {
        this.relations  = relations;
    }

}
