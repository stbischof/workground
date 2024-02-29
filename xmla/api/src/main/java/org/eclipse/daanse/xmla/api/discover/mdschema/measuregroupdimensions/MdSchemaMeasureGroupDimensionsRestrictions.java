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
package org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_INTEGER;
import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface MdSchemaMeasureGroupDimensionsRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_CUBE_NAME = "CUBE_NAME";
    String RESTRICTIONS_MEASUREGROUP_NAME = "MEASUREGROUP_NAME";
    String RESTRICTIONS_DIMENSION_UNIQUE_NAME = "DIMENSION_UNIQUE_NAME";
    String RESTRICTIONS_DIMENSION_VISIBILITY = "DIMENSION_VISIBILITY";

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
     * @return The name of the measure group.
     */
    @Restriction(name = RESTRICTIONS_MEASUREGROUP_NAME, type = XSD_STRING, order = 3)
    Optional<String> measureGroupName();

    /**
     * The unique name for the dimension.
     */
    @Restriction(name = RESTRICTIONS_DIMENSION_UNIQUE_NAME, type = XSD_STRING, order = 4)
    Optional<String> dimensionUniqueName();

    /**
     * @return A bitmask with one of these valid values:
     * 0x01 - Visible
     * 0x02 - Not Visible
     * The default restriction is a value of 1.
     */
    //@Restriction(name = RESTRICTIONS_DIMENSION_VISIBILITY, type = XSD_INTEGER, order = 5)
    Optional<VisibilityEnum> dimensionVisibility();
}
