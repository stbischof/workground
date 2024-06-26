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
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggExclude;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQueryOptimisationHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Table", propOrder = { "sql", "aggExcludes", "aggTables", "hints" })
@XmlRootElement(name = "Table")
public class TableImpl implements MappingTableQuery {

    @XmlElement(name = "SqlSelectQuery", type = SQLImpl.class)
    protected MappingSQL sql;
    @XmlElement(name = "AggExclude", type = AggExcludeImpl.class)
    protected List<MappingAggExclude> aggExcludes;
    @XmlElements({ @XmlElement(name = "AggName", type = AggNameImpl.class),
            @XmlElement(name = "AggPattern", type = AggPatternImpl.class) })
    protected List<MappingAggTable> aggTables;
    @XmlElement(name = "Hint", type = HintImpl.class)
    protected List<MappingTableQueryOptimisationHint> hints;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "schema")
    protected String schema;
    @XmlAttribute(name = "alias")
    protected String alias;

    @Override
    public MappingSQL getSql() {
        return sql;
    }

    public void setSql(MappingSQL value) {
        this.sql = value;
    }

    @Override
    public List<MappingAggExclude> getAggExcludes() {
        if (aggExcludes == null) {
            aggExcludes = new ArrayList<>();
        }
        return this.aggExcludes;
    }

    @Override
    public List<MappingAggTable> getAggTables() {
        if (aggTables == null) {
            aggTables = new ArrayList<>();
        }
        return this.aggTables;
    }

    @Override
    public List<MappingTableQueryOptimisationHint> getHints() {
        if (hints == null) {
            hints = new ArrayList<>();
        }
        return this.hints;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public String getSchema() {
        return schema;
    }

    public void setSchema(String value) {
        this.schema = value;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String value) {
        this.alias = value;
    }

    @Override
	public boolean equals(Object o) {
        if (o instanceof MappingTableQuery that) {
            return this.name.equals(that.getName()) &&
                Objects.equals(this.alias, that.getAlias()) &&
                Objects.equals(this.schema, that.getSchema());
        } else {
            return false;
        }
    }

    @Override
	public String toString() {
        return (schema == null) ?
            name :
            new StringBuilder(schema).append(".").append(name).toString();
    }
    @Override
	public int hashCode() {
        return toString().hashCode();
    }

    public void setHints(List<MappingTableQueryOptimisationHint> hints) {
        this.hints = hints;
    }

    public void setAggExcludes(List<MappingAggExclude> aggExcludes) {
        this.aggExcludes = aggExcludes;
    }

    public void setAggTables(List<MappingAggTable> aggTables) {
        this.aggTables = aggTables;
    }
}
