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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKey;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Key", propOrder = { "columns" })
public class KeyImpl implements MappingKey {

    @XmlElement(name = "Column", type = ColumnImpl.class)
    private List<MappingColumn> columns;

    @XmlAttribute(name = "name")
    private String name;

    @Override
    public List<MappingColumn> columns() {
        return columns;
    }

    @Override
    public String name() {
        return name;
    }

    public void setColumns(List<MappingColumn> columns) {
        this.columns = columns;
    }

    public void setName(String name) {
        this.name = name;
    }
}
