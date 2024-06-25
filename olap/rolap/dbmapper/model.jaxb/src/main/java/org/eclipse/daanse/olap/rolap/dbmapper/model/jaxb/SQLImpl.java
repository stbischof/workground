
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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQL", propOrder = { "content" })
public class SQLImpl implements MappingSqlSelectQuery {

    @XmlValue
    protected String content;
    @XmlAttribute(name = "dialect", required = true)
    protected String dialect;

    @Override
    public String content() {
        return content != null ? content.trim() : content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    @Override
    public String dialect() {
        return dialect != null ? dialect : "generic";
    }

    public void setDialect(String value) {
        this.dialect = value;
    }

}
