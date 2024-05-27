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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "relations" })
public class JoinImpl implements MappingJoin {

    @XmlElements({ @XmlElement(name = "Table", type = TableImpl.class),
            @XmlElement(name = "View", type = ViewImpl.class), @XmlElement(name = "Join", type = JoinImpl.class),
            @XmlElement(name = "InlineTable", type = InlineTableImpl.class) })
    protected List<MappingRelationOrJoin> relations;
    @XmlAttribute(name = "leftAlias")
    protected String leftAlias;
    @XmlAttribute(name = "leftKey")
    protected String leftKey;
    @XmlAttribute(name = "rightAlias")
    protected String rightAlias;
    @XmlAttribute(name = "rightKey")
    protected String rightKey;

    @Override
    public List<MappingRelationOrJoin> relations() {
        if (relations == null) {
            relations = new ArrayList<>();
        }
        return this.relations;
    }

    @Override
    public String leftAlias() {
        return leftAlias;
    }

    @Override
	public void setLeftAlias(String value) {
        this.leftAlias = value;
    }

    @Override
    public String leftKey() {
        return leftKey;
    }

    @Override
	public void setLeftKey(String value) {
        this.leftKey = value;
    }

    @Override
    public String rightAlias() {
        return rightAlias;
    }

    @Override
	public void setRightAlias(String value) {
        this.rightAlias = value;
    }

    @Override
    public String rightKey() {
        return rightKey;
    }

    @Override
	public void setRightKey(String value) {
        this.rightKey = value;
    }

    @Override
    public void setRelations(List<MappingRelationOrJoin> relations) {
        this.relations = relations;
    }
}
