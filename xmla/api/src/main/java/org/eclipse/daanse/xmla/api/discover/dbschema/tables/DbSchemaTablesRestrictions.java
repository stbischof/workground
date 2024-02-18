/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.api.discover.dbschema.tables;

import org.eclipse.daanse.xmla.api.annotation.Restriction;

import java.util.Optional;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface DbSchemaTablesRestrictions {
    String RESTRICTIONS_TABLE_CATALOG = "TABLE_CATALOG";
    String RESTRICTIONS_TABLE_SCHEMA = "TABLE_SCHEMA";
    String RESTRICTIONS_TABLE_NAME = "TABLE_NAME";
    String RESTRICTIONS_TABLE_TYPE = "TABLE_TYPE";

    /**
     * @return The name of the database.
     */
    @Restriction(name = RESTRICTIONS_TABLE_CATALOG, type = XSD_STRING, order = 0)
    Optional<String> tableCatalog();

    /**
     * @return The name of the schema.
     */
    @Restriction(name = RESTRICTIONS_TABLE_SCHEMA, type = XSD_STRING, order = 1)
    Optional<String> tableSchema();

    /**
     * @return The name of the table.
     */
    @Restriction(name = RESTRICTIONS_TABLE_NAME, type = XSD_STRING, order = 2)
    Optional<String> tableName();

    /**
     * @return The type of table:
     * TABLE for measure group.
     * SYSTEM TABLE for dimension.
     * SCHEMA for schema rowset table.
     */
    @Restriction(name = RESTRICTIONS_TABLE_TYPE, type = "xsd:string", order = 3)
    Optional<String> tableType();

}
