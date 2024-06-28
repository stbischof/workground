
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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingViewQuery;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "View", propOrder = { "sql" })
public class ViewImpl implements MappingViewQuery {

    @XmlElement(name = "SqlSelectQuery", required = true, type = SqlSelectQueryImpl.class)
    protected MappingSqlSelectQuery sql;
    @XmlAttribute(name = "alias", required = true)
    protected String alias;

    @Override
    public MappingSqlSelectQuery sql() {
        return this.sql;
    }

    @Override
    public void addCode(String dialect, String code) {
        if (sql == null) {
            sql = new SqlSelectQueryImpl();
        }
        Optional<MappingSQL> oSql = sql.sqls().stream().filter(s -> code.equals(s.statement())).findAny();
        if (oSql.isPresent()) {
            MappingSQL sql = oSql.get();
            sql.dialects().add(dialect);
        } else {
            SQLImpl sql = new SQLImpl();
            List<String> ds = new ArrayList<>();
            ds.add(dialect);
            sql.setDialects(ds);
            sql.setStatement(code);
            this.sql.sqls().add(sql);
        }
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String value) {
        this.alias = value;
    }

    public void setSql(MappingSqlSelectQuery sql) {
        this.sql = sql;
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

    @Override
	public String toString() {
        return sql.sqls().get(0).statement();
    }

}
