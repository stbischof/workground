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
package org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface DbSchemaSourceTablesRestrictions {

    String RESTRICTIONS_TABLE_CATALOG = "TABLE_CATALOG";
    String RESTRICTIONS_SCHEMA_NAME = "TABLE_SCHEMA";
    String RESTRICTIONS_TABLE_NAME = "TABLE_NAME";
    String RESTRICTIONS_TABLE_TYPE = "TABLE_TYPE";

    /**
     * @return Catalog name. NULL if the provider does not support
     * catalogs.
     */
    @Restriction(name = RESTRICTIONS_TABLE_CATALOG, type = XSD_STRING, order = 0)
    Optional<String> catalogName();

    /**
     * @return Unqualified schema name. NULL if the provider does not
     * support schemas.
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = XSD_STRING, order = 1)
    Optional<String> schemaName();

    /**
     * @return Table name
     */
    @Restriction(name = RESTRICTIONS_TABLE_NAME, type = XSD_STRING, order = 2)
    String tableName();

    /**
     * @return Table type. One of the following or a provider-specific
     * value: ALIAS, TABLE, SYNONYM, SYSTEM TABLE, VIEW, GLOBAL "
     * TEMPORARY, LOCAL TEMPORARY, EXTERNAL TABLE, SYSTEM VIEW
     */
    @Restriction(name = RESTRICTIONS_TABLE_TYPE, type = XSD_STRING, order = 3)
    TableTypeEnum tableType();
}
