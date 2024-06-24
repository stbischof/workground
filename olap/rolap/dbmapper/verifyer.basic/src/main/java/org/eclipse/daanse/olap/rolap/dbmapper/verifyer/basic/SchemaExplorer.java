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
package org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;

import java.util.SortedSet;

public class SchemaExplorer {

    private SchemaExplorer() {
        //constructor
    }

    public static String[] getTableNameForAlias(MappingQuery relation, String table) {
        String theTableName = table;
        String schemaName = null;

        // EC: Loops join tree and finds the table name for an alias.
        if (relation instanceof MappingJoinQuery join) {
            MappingQuery theRelOrJoinL = left(join);
            MappingQuery theRelOrJoinR = right(join);
            for (int i = 0; i < 2; i++) {
                // Searches first using the Left Join and then the Right.
                MappingQuery theCurrentRelOrJoin = (i == 0) ? theRelOrJoinL : theRelOrJoinR;
                if (theCurrentRelOrJoin instanceof MappingTable theTable) {
                    if (theTable.getAlias() != null && theTable.getAlias()
                            .equals(table)) {
                        // If the alias was found get its table name and return
                        // it.
                        theTableName = theTable.getName();
                        schemaName = theTable.getSchema();
                    }
                } else {
                    // otherwise continue down the join tree.
                    String[] result = getTableNameForAlias(theCurrentRelOrJoin, table);
                    schemaName = result[0];
                    theTableName = result[1];
                }
            }
        }
        return new String[] { schemaName, theTableName };
    }

    public static void getTableNamesForJoin(MappingQuery relation, SortedSet<String> joinTables) {
        // EC: Loops join tree and collects table names.
        if (relation instanceof MappingJoinQuery join) {
            MappingQuery theRelOrJoinL = left(join);
            MappingQuery theRelOrJoinR = right(join);
            for (int i = 0; i < 2; i++) {
                // Searches first using the Left Join and then the Right.
                MappingQuery theCurrentRelOrJoin = (i == 0) ? theRelOrJoinL : theRelOrJoinR;
                if (theCurrentRelOrJoin instanceof MappingTable theTable) {
                    String theTableName = (theTable.getAlias() != null && theTable.getAlias()
                            .trim()
                            .length() > 0) ? theTable.getAlias() : theTable.getName();
                    joinTables.add(theTableName);
                } else {
                    // Calls recursively collecting all table names down the
                    // join tree.
                    getTableNamesForJoin(theCurrentRelOrJoin, joinTables);
                }
            }
        }

    }

    private static MappingQuery left(MappingJoinQuery join) {
        if (join != null && join.left() != null) {
            return join.left().getQuery();
        }
        throw new SchemaExplorerException("Join left error");
    }

    private static MappingQuery right(MappingJoinQuery join) {
        if (join != null && join.right() != null) {
            return join.right().getQuery();
        }
        throw new SchemaExplorerException("Join right error");
    }
}
