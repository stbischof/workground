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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinedQueryElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;

public class JoinedQueryElementR implements MappingJoinedQueryElement {

    private String alias;
    private String key;
    private MappingQuery query;

    public JoinedQueryElementR(
        String alias,
        String key,
        MappingQuery query
    ) {
        this.alias = alias;
        this.key = key;
        this.query = query;
    }

    public String getAlias() {
        return alias;
    }

    public String getKey() {
        return key;
    }

    public MappingQuery getQuery() {
        return query;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void setQuery(MappingQuery query) {
        this.query = query;
    }
}
