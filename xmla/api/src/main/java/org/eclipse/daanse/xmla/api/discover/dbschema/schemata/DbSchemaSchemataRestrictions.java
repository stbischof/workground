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
package org.eclipse.daanse.xmla.api.discover.dbschema.schemata;

import org.eclipse.daanse.xmla.api.annotation.Restriction;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface DbSchemaSchemataRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_SCHEMA_OWNER = "SCHEMA_OWNER";

    /**
     * @return Catalog name
     */
    @Restriction(name = RESTRICTIONS_CATALOG_NAME, type = XSD_STRING, order = 0)
    String catalogName();

    /**
     * Schema name
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = XSD_STRING, order = 1)
    String schemaName();

    /**
     * @return Schema owner
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_OWNER, type = XSD_STRING, order = 2)
    String schemaOwner();
}
