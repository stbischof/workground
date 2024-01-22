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
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.adapter.TypeAdaptor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculatedColumnDef")
public class CalculatedColumnDefImpl implements MappingCalculatedColumnDef {

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(TypeAdaptor.class)
    private TypeEnum type;

    @XmlAttribute(name = "internalType")
    private String internalType;

    @XmlElements({ @XmlElement(name = "Column", type = ColumnImpl.class),
        @XmlElement(name = "ExpressionView", type = ExpressionViewImpl.class) })
    private MappingExpression expression;

    @Override
    public TypeEnum type() {
        return type;
    }

    @Override
    public String internalType() {
        return internalType;
    }


    @Override
    public String name() {
        return name;
    }

    @Override
    public MappingExpression expression() {
        return expression;
    }
}
