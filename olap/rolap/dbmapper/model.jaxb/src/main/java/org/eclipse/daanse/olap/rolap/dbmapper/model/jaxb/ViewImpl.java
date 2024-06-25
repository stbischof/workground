
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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingViewQuery;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "View", propOrder = { "sqls" })
public class ViewImpl implements MappingViewQuery {

    @XmlElement(name = "SQL", required = true, type = SQLImpl.class)
    protected List<MappingSqlSelectQuery> sqls;
    @XmlAttribute(name = "alias", required = true)
    protected String alias;

    @Override
    public List<MappingSqlSelectQuery> sqls() {
        if (sqls == null) {
            sqls = new ArrayList<>();
        }
        return this.sqls;
    }

    @Override
    public void addCode(String dialect, String code) {
        if (sqls == null) {
            sqls = new ArrayList<>();
        }
        SQLImpl sqlImpl = new SQLImpl();
        sqlImpl.setDialect(dialect);
        sqlImpl.setContent(code);
        sqls.add(sqlImpl);
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String value) {
        this.alias = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sqls, alias);
    }

    @Override
	public boolean equals(Object o) {
        if (o instanceof MappingViewQuery that) {
            if (!Objects.equals(getAlias(), that.getAlias())) {
                return false;
            }
            if (sqls() == null || that.sqls() == null || sqls().size() != that.sqls().size()) {
                return false;
            }
            for (int i = 0; i < sqls().size(); i++) {
                if (!Objects.equals(sqls().get(i).dialect(), that.sqls().get(i).dialect())
                    || !Objects.equals(sqls().get(i).content(), that.sqls().get(i).content()))
                {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
	public String toString() {
        return sqls.get(0).content();
    }

    public void setSqls(List<MappingSqlSelectQuery> sqls) {
        this.sqls = sqls;
    }
}
