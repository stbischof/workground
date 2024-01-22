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
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTimeDomain;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.adapter.TypeAdaptor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ColumnDefImpl implements MappingColumnDef {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(TypeAdaptor.class)
    protected TypeEnum type;
    @XmlElement(name = "timeDomain", type = TimeDomainImpl.class)
    private MappingTimeDomain timeDomain;
    @XmlAttribute(name = "internalType")
    protected String internalType;

    @Override
    public MappingTimeDomain timeDomain() {
        return timeDomain;
    }

    public void setTimeDomain(MappingTimeDomain timeDomain) {
        this.timeDomain = timeDomain;
    }

    @Override
    public String name() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public TypeEnum type() {
        return type;
    }

    @Override
    public String internalType() {
        return internalType;
    }

    @Override
    public void setType(TypeEnum type) {
        this.type = type;
    }
}
