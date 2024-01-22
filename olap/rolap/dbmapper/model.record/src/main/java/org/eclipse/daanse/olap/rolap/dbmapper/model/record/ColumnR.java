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

    public ColumnR(String table, String name, String genericExpression) {
        this.table = table;
        this.name = name;
        this.genericExpression = genericExpression;
    }


    public ColumnR(String table, String name) {
        this.table = table;
        this.name = name;
        this.genericExpression = table == null ? name : (new StringBuilder(table).append(".").append(name).toString());
    }

    @Override
    public String table() {
        return table;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String genericExpression() {
        return genericExpression;
    }

    @Override
	public boolean equals(Object obj) {
        if (!(obj instanceof MappingColumn that)) {
            return false;
        }
        return name().equals(that.name()) &&
            Objects.equals(table(), that.table());
    }

    @Override
	public int hashCode() {
        return name().hashCode() ^ (table()==null ? 0 : table().hashCode());
    }
}
