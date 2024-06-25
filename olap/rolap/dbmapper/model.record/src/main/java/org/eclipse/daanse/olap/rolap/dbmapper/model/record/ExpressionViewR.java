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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;

public record ExpressionViewR(List<MappingSqlSelectQuery> sqls,
                              String table,
                              String name) implements MappingExpressionView {



	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof MappingExpressionView that)) {
            return false;
        }
        if (sqls().size() != that.sqls().size()) {
            return false;
        }
        for (int i = 0; i < sqls().size(); i++) {
            if (! sqls().get(i).equals(that.sqls().get(i))) {
                return false;
            }
        }
        return true;
    }

    public  ExpressionViewR(List<MappingSqlSelectQuery> sqls,
            String table,
            String name)  {
				this.sqls = sqls;
				this.table = table;
				this.name = name;

    }

    public List<MappingSqlSelectQuery> getSqls() {
        return sqls;
    }

    public String getTable() {
        return table;
    }

    public String getName() {
        return name;
    }
}
