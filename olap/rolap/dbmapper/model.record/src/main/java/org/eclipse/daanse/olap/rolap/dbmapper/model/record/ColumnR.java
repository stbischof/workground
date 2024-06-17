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

import java.util.Objects;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;

public class ColumnR implements MappingColumn {

    private String table;
    private String name;
    private String genericExpression;

    public ColumnR(String table, String name) {
        this.table = table;
        this.name = name;
        this.genericExpression = table == null ? name : (new StringBuilder(table).append(".").append(name).toString());
    }

    public String getTable() {
        return table;
    }

    public String getName() {
        return name;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getGenericExpression() {
        return genericExpression;
    }

    @Override
	public boolean equals(Object obj) {
        if (!(obj instanceof MappingColumn that)) {
            return false;
        }
        return getName().equals(that.getName()) &&
            Objects.equals(getTable(), that.getTable());
    }

    @Override
	public int hashCode() {
        return getName().hashCode() ^ (getTable()==null ? 0 : getTable().hashCode());
    }
}
