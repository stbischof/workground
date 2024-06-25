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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;

import mondrian.rolap.sql.SqlQuery;

public class SQLUtil {
    /**
     * Converts an array of SQL to a
     * {@link mondrian.rolap.sql.SqlQuery.CodeSet} object.
     */
    public static SqlQuery.CodeSet toCodeSet(List<? extends MappingSqlSelectQuery> sqls) {
        SqlQuery.CodeSet codeSet = new SqlQuery.CodeSet();
        for (MappingSqlSelectQuery sql : sqls) {
            codeSet.put(sql.dialect(), sql.content());
        }
        return codeSet;
    }

    public static int hashCode(MappingSqlSelectQuery sql) {
        return sql.dialect().hashCode();
    }

    public boolean equals(MappingSqlSelectQuery sql, Object obj) {
        if (!(obj instanceof MappingSqlSelectQuery that)) {
            return false;
        }
        return sql.dialect().equals(that.dialect()) &&
            Objects.equals(sql.content(), that.content());
    }
}
