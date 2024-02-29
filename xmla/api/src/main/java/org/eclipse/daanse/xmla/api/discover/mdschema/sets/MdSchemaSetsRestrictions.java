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
package org.eclipse.daanse.xmla.api.discover.mdschema.sets;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface MdSchemaSetsRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_CUBE_NAME = "CUBE_NAME";
    String RESTRICTIONS_SET_NAME = "SET_NAME";
    String RESTRICTIONS_SCOPE = "SCOPE";
    String RESTRICTIONS_SET_CAPTION = "SET_CAPTION";
    String RESTRICTIONS_CUBE_SOURCE = "CUBE_SOURCE";
    String RESTRICTIONS_HIERARCHY_UNIQUE_NAME = "HIERARCHY_UNIQUE_NAME";

    /**
     * @return The name of the database.
     */
    @Restriction(name = RESTRICTIONS_CATALOG_NAME, type = XSD_STRING, order = 0)
    Optional<String> catalogName();


    /**
     * @return The name of the schema.
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = XSD_STRING, order = 1)
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    @Restriction(name = RESTRICTIONS_CUBE_NAME, type = XSD_STRING, order = 2)
    Optional<String> cubeName();

    /**
     * The name of the set, as specified in the CREATE SET
     * statement.
     */
    @Restriction(name = RESTRICTIONS_SET_NAME, type = XSD_STRING, order = 3)
    Optional<String> setName();

    /**
     * The scope of the set. The set can be a session-defined
     * set or a global-defined set.
     * This column can have one of the following values:
     * 1 - Global
     * 2 – Session
     */
    @Restriction(name = RESTRICTIONS_SCOPE, type = "xsd:int", order = 4)
    Optional<ScopeEnum> scope();

    @Restriction(name = RESTRICTIONS_SET_CAPTION, type = XSD_STRING, order = 5)
    Optional<String> setCaption();

    /**
     * A bitmask with one of the following valid values:
     * 0x01 - Cube
     * 0x02 - Dimension<233>
     * The default restriction is a value of 1.
     */
    //@Restriction(name = RESTRICTIONS_CUBE_SOURCE, type = "xsd:int", order = 6)
    Optional<CubeSourceEnum> cubeSource();

    /**
     * The unique name of the hierarchy that contains the set.
     */
    //@Restriction(name = RESTRICTIONS_HIERARCHY_UNIQUE_NAME, type = XSD_STRING, order = 7)
    Optional<String> hierarchyUniqueName();
}
