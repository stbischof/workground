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
package mondrian.rolap.util;

import java.util.List;
import java.util.Objects;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.rolap.mapping.api.model.SQLMapping;

import mondrian.rolap.sql.SqlQuery;

public class SQLUtil {
    /**
     * Converts an array of SQL to a
     * {@link mondrian.rolap.sql.SqlQuery.CodeSet} object.
     */
    public static SqlQuery.CodeSet toCodeSet(List<? extends SQLMapping> sqls) {
        SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();
        for (SQLMapping sql : sqls) {
            for (String dialect : sql.getDialects()) {
                codeSet.put(dialect, sql.getStatement());
            }
        }
        return codeSet;
    }

    public static int hashCode(MappingSQL sql) {
        return sql.dialects().hashCode();
    }

    public boolean equals(MappingSQL sql, Object obj) {
        if (!(obj instanceof MappingSQL that)) {
            return false;
        }
        if (sql.dialects().size() != that.dialects().size()) {
            return false;
        }
        if (!sql.statement().equals(that.statement())) {
            return false;
        }

        for (int i = 0; i < sql.dialects().size(); i++) {
            if (sql.dialects().get(i).equals(that.dialects().get(i))) {
                return false;
            }
        }
        return true;
    }
}
