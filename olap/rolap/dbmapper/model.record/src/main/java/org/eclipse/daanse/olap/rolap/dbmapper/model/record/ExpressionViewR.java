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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;

public record ExpressionViewR(MappingSqlSelectQuery sql,
                              String table,
                              String name) implements MappingExpressionView {



	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof MappingExpressionView that)) {
            return false;
        }
        if (sql() == null || that.sql() == null) {
            return false;
        }
        return sql().equals(that.sql());
    }

    public  ExpressionViewR(MappingSqlSelectQuery sql,
            String table,
            String name)  {
				this.sql = sql;
				this.table = table;
				this.name = name;

    }

    public MappingSqlSelectQuery getSql() {
        return sql;
    }

    public String getTable() {
        return table;
    }

    public String getName() {
        return name;
    }
}
