/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2013-2014 Pentaho
// All Rights Reserved.
*/
package mondrian.rolap.sql;

import mondrian.rolap.RolapSchema;
import mondrian.rolap.physicalschema.PhysColumn;
import mondrian.rolap.physicalschema.PhysRelation;

/**
* Model for building SQL query.
 *
 * <p>Higher level than {@link SqlQuery}.</p>
 */
public class SqlQueryBuilder {

    public interface Joiner {
        void addColumn(
            SqlQueryBuilder queryBuilder,
            PhysColumn column);
        void addRelation(
            SqlQueryBuilder queryBuilder,
            PhysRelation relation);
    }

}

// End SqlQueryBuilder.java
