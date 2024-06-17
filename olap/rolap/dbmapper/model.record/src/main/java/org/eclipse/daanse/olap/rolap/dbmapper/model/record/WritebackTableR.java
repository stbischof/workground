/*
 * Copyright (c) 0 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License .0
 * which is available at https://www.eclipse.org/legal/epl-.0/
 *
 * SPDX-License-Identifier: EPL-.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;

public record WritebackTableR(String schema,
                              String name,
                              List<MappingWritebackColumn> columns)
    implements MappingWritebackTable {

    public WritebackTableR(
        String schema,
        String name,
        List<MappingWritebackColumn> columns
    ) {
        this.schema = schema;
        this.name = name;
        this.columns = columns == null ? List.of() : columns;

    }

    public String getSchema() {
        return schema;
    }

    public String getName() {
        return name;
    }

    public List<MappingWritebackColumn> getColumns() {
        return columns;
    }
}
