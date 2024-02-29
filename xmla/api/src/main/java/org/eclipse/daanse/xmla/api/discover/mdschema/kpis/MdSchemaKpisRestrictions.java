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
package org.eclipse.daanse.xmla.api.discover.mdschema.kpis;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface MdSchemaKpisRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_CUBE_NAME = "CUBE_NAME";
    String RESTRICTIONS_KPI_NAME = "KPI_NAME";
    String RESTRICTIONS_CUBE_SOURCE = "CUBE_SOURCE";

    /**
     * @return The name of the database.
     */
    @Restriction(name = RESTRICTIONS_CATALOG_NAME, type = XSD_STRING, order = 0)
    Optional<String> catalogName();


    /**
     * @return The name of the schema.
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = "xsd:string", order = 1)
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    @Restriction(name = RESTRICTIONS_CUBE_NAME, type = "xsd:string", order = 2)
    Optional<String> cubeName();

    /**
     * The name of the set, as specified in the CREATE SET
     * statement.
     */
    @Restriction(name = RESTRICTIONS_KPI_NAME, type = "xsd:string", order = 3)
    Optional<String> kpiName();

    /**
     * A bitmask with one of the following valid values:
     * 0x01 - Cube
     * 0x02 - Dimension
     * The default restriction is a value of 1.
     */
    //@Restriction(name = RESTRICTIONS_CUBE_SOURCE, type = "xsd:int", order = 4)
    Optional<CubeSourceEnum> cubeSource();
}
