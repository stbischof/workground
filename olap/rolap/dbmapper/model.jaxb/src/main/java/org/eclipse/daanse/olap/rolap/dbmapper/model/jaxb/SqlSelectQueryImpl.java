
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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSqlSelectQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SqlSelectQuery", propOrder = { "sqls" })
public class SqlSelectQueryImpl implements MappingSqlSelectQuery {

    @XmlElement(name = "SQL", type = SQLImpl.class)
    protected List<MappingSQL> sqls = new ArrayList<MappingSQL>();

    public SqlSelectQueryImpl() {
    }

    public SqlSelectQueryImpl(List<MappingSQL> sqls) {
        this.sqls = sqls;
    }

    @Override
    public List<MappingSQL> sqls() {    	
        return sqls;
    }

    public void setSqls(List<MappingSQL> sqls) {    	
        this.sqls = sqls != null ? sqls : new ArrayList<MappingSQL>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(sqls);
    }

    public boolean equals(Object o) {
        if (o instanceof MappingSqlSelectQuery that) {
            if (sqls() == null || that.sqls() == null ||
                sqls().size() != that.sqls().size()) {
                return false;
            }
            for (int i = 0; i < sqls().size(); i++) {
                if (!sqls().get(i).equals(that.sqls().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
