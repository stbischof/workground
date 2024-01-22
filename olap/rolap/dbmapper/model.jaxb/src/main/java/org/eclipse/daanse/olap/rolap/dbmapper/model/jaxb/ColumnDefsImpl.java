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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDefs;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.RealOrCalcColumnDef;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColumnDefs", propOrder = { "columns" })
public class ColumnDefsImpl implements MappingColumnDefs {

    @XmlElements({ @XmlElement(name = "ColumnDef", type = ColumnDefImpl.class),
        @XmlElement(name = "CalculatedColumnDef", type = CalculatedColumnDefImpl.class) })
    private List<RealOrCalcColumnDef> columns;

    @Override
    public List<RealOrCalcColumnDef> columns() {
        return columns;
    }

    public void setColumns(List<RealOrCalcColumnDef> columns) {
        this.columns = columns;
    }
}
