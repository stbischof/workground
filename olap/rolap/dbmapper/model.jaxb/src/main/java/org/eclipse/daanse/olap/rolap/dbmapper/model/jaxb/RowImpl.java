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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableRow;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableRowCell;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
public class RowImpl implements MappingInlineTableRow {

    @XmlElement(name = "Value", required = true, type = ValueImpl.class)
    protected List<MappingInlineTableRowCell> value;

    @Override
    public List<MappingInlineTableRowCell> values() {
        if (value == null) {
            value = new ArrayList<>();
        }
        return this.value;
    }

    public void setValues(List<MappingInlineTableRowCell> values) {
        this.value = values;
    }
}
