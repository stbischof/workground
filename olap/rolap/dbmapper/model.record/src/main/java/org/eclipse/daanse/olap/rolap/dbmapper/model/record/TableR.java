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
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;
import java.util.Objects;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggExclude;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;

public class TableR implements MappingTable {

    private MappingSQL sql=null;
    private String alias=null;
    private List<MappingAggExclude> aggExcludes;
    private String name;
    private String schema=null;
    private List<MappingHint> hints;
    private List<MappingAggTable> aggTables;

    public TableR(MappingTable table) {
        this(table.getSchema(), table.getName(), table.getAlias(), table.getHints());
    }

    public TableR(String name, List<MappingAggExclude> aggExcludes, List<MappingAggTable> aggTables) {
        this.name = name;
        this.aggExcludes = aggExcludes == null ? List.of() : aggExcludes;
        this.aggTables = aggTables == null ? List.of() : aggTables;
        this.hints =List.of();
    }
    public TableR(String schema, String name, String alias, List<MappingHint> hints) {
        this.name = name;
        this.schema = schema;
        this.alias = alias;
        this.hints = hints == null ? List.of() : hints;
        this.aggTables = List.of();
        this.aggExcludes = List.of();
    }

    public TableR(String schema, String name, String alias,
                  List<MappingHint> hints, MappingSQL sql,
                  List<MappingAggExclude> aggExcludes, List<MappingAggTable> aggTables) {
        this.name = name;
        this.schema = schema;
        this.alias = alias;
        this.hints = hints == null ? List.of() : hints;
        this.sql = sql;
        this.aggExcludes = aggExcludes == null ? List.of() : aggExcludes;
        this.aggTables = aggTables == null ? List.of() : aggTables;
    }

    public TableR(MappingTable tbl, String possibleName) {
        this(tbl.getSchema(), tbl.getName(), possibleName, tbl.getHints());

        // Remake the filter with the new alias
        if (tbl.getSql() != null) {
            String aliasOrName = tbl.getAlias() == null ? tbl.getName() : tbl.getAlias();
            this.sql = new SQLR(tbl.getSql().content() != null ? tbl.getSql().content().replace(aliasOrName, possibleName) : null,
                tbl.getSql().dialect());
        }
    }

    public TableR(String name, MappingSQL sql) {
        this.name = name;
        this.schema = null;
        this.alias = null;
        this.hints = List.of();
        this.aggExcludes = List.of();
        this.aggTables = List.of();
        this.sql = sql;
    }

    public TableR(String name) {
    	this.name = name;
    	this.schema = null;
    	this.alias = null;
    	this.hints = List.of();
    	this.aggExcludes = List.of();
    	this.aggTables = List.of();
    }

    public String getAlias() {
        return alias;
    }

    public MappingSQL getSql() {
        return sql;
    }

    public List<MappingAggExclude> getAggExcludes() {
        return aggExcludes;
    }

    public List<MappingAggTable> getAggTables() {
        return aggTables;
    }

    public List<MappingHint> getHints() {
        return hints;
    }

    public String getName() {
        return name;
    }

    public String getSchema() {
        return schema;
    }

    @Override
	public boolean equals(Object o) {
        if (o instanceof MappingTable that) {
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
}
