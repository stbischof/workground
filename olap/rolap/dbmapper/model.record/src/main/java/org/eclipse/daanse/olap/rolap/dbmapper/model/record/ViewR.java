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
import java.util.Objects;
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingViewQuery;

public record ViewR(String alias,
                    MappingSqlSelectQuery sql)
        implements MappingViewQuery {

    public ViewR(MappingViewQuery view, String alias) {
        this(alias,  view.sql());
    }

    public ViewR(String alias, MappingSqlSelectQuery sql) {
        this.alias = alias;
        this.sql = sql != null ? sql : new SqlSelectQueryR(new ArrayList<>());
    }

    @Override
    public void addCode(String dialect, String code) {
        Optional<MappingSQL> oSql = sql.sqls().stream().filter(s -> code.equals(s.statement())).findAny();
        if (oSql.isPresent()) {
            MappingSQL sql = oSql.get();
            sql.dialects().add(dialect);
        } else {
            List<String> ds = new ArrayList<>();
            ds.add(dialect);
            SQLR sl = new SQLR(code, ds);
            sql.sqls().add(sl);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(sql, alias);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MappingViewQuery that) {
            if (!Objects.equals(getAlias(), that.getAlias())) {
                return false;
            }
            if (sql() == null || that.sql() == null ||
                sql().sqls() == null || that.sql().sqls() == null ||
                sql().sqls().size() != that.sql().sqls().size()) {
                return false;
            }
            return sql().equals(that.sql());
        } else {
            return false;
        }
    }

    public String getAlias() {
        return alias;
    }

    public MappingSqlSelectQuery getSql() {
        return sql;
    }
}
