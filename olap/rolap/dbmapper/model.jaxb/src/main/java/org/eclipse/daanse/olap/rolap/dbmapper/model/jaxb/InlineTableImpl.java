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

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableColumnDefinition;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableRow;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InlineTable", propOrder = { "columnDefs", "rows" })
public class InlineTableImpl implements MappingInlineTableQuery {

    @XmlElementWrapper(name = "ColumnDefs")
    @XmlElement(name = "ColumnDef", required = true, type = ColumnDefImpl.class)
    protected List<MappingInlineTableColumnDefinition> columnDefs;
    @XmlElementWrapper(name = "Rows", required = true)
    @XmlElement(name = "Row", required = true, type = RowImpl.class)
    protected List<MappingInlineTableRow> rows;
    @XmlAttribute(name = "alias")
    private String alias;

    @Override
    public List<MappingInlineTableColumnDefinition> columnDefs() {
        return columnDefs;
    }

    public void setColumnDefs(List<MappingInlineTableColumnDefinition> value) {
        this.columnDefs = value;
    }

    @Override
    public List<MappingInlineTableRow> rows() {
        return rows;
    }

    public void setRows(List<MappingInlineTableRow> value) {
        this.rows = value;
    }

    @Override
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
	public boolean equals(Object o) {
        if (o instanceof MappingInlineTableQuery that) {
            return getAlias().equals(that.getAlias());
        } else {
            return false;
        }
    }

    @Override
	public String toString() {
        return "<inline data>";
    }

    @Override
	public int hashCode() {
        return toString().hashCode();
    }
}
