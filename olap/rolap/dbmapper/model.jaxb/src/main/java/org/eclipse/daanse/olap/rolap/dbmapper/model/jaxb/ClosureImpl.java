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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingClosure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "table" })
public class ClosureImpl implements MappingClosure {

    @XmlElement(name = "Table", required = true, type = TableImpl.class)
    protected MappingTableQuery table;
    @XmlAttribute(name = "parentColumn", required = true)
    protected String parentColumn;
    @XmlAttribute(name = "childColumn", required = true)
    protected String childColumn;

    @Override
    public MappingTableQuery table() {
        return table;
    }

    public void setTable(MappingTableQuery value) {
        this.table = value;
    }

    @Override
    public String parentColumn() {
        return parentColumn;
    }

    public void setParentColumn(String value) {
        this.parentColumn = value;
    }

    @Override
    public String childColumn() {
        return childColumn;
    }

    public void setChildColumn(String value) {
        this.childColumn = value;
    }

}
