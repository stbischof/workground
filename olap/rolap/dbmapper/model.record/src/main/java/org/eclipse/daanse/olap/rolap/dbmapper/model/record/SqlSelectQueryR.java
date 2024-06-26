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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;

import java.util.ArrayList;
import java.util.List;

public record SqlSelectQueryR(List<MappingSQL> sqls)
    implements MappingSqlSelectQuery {

    public SqlSelectQueryR(List<MappingSQL> sqls) {
        this.sqls = sqls == null ? new ArrayList<>() : sqls;
    }
}
