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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinedQueryElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JoinedQueryElement", propOrder = { "query" })
public class JoinedQueryElementImpl implements MappingJoinedQueryElement {

    @XmlElements({ @XmlElement(name = "Table", type = TableImpl.class),
        @XmlElement(name = "View", type = ViewImpl.class), @XmlElement(name = "Join", type = JoinQueryImpl.class),
        @XmlElement(name = "InlineTable", type = InlineTableImpl.class) })
    protected MappingQuery query;

    @XmlAttribute(name = "alias")
    protected String alias;
    @XmlAttribute(name = "key")
    protected String key;

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public MappingQuery getQuery() {
        return query;
    }

    @Override
    public void setQuery(MappingQuery query) {
        this.query = query;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }
}
