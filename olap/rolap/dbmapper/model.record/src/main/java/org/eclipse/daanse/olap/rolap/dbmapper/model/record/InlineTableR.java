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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRow;

public record InlineTableR(List<MappingColumnDef> columnDefs,
                           List<MappingRow> rows, String alias)
        implements MappingInlineTable {

    public InlineTableR(MappingInlineTable inlineTable) {
        this(new ArrayList<>(inlineTable.columnDefs()), new ArrayList<>(inlineTable.rows()), inlineTable.getAlias());
    }

    public InlineTableR(MappingInlineTable inlineTable, String alias) {
        this(new ArrayList<>(inlineTable.columnDefs()), new ArrayList<>(inlineTable.rows()), alias);
    }

    public InlineTableR(List<MappingColumnDef> columnDefs, List<MappingRow> rows, String alias) {
        this.columnDefs = columnDefs == null ? List.of() : columnDefs;
        this.rows = rows == null ? List.of() : rows;
        this.alias = alias;
    }

    @Override
	public boolean equals(Object o) {
        if (o instanceof MappingInlineTable that) {
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

    @Override
    public List<MappingColumnDef> columnDefs() {
        return columnDefs;
    }

    @Override
    public List<MappingRow> rows() {
        return rows;
    }

    @Override
    public String getAlias() {
        return alias;
    }
}
