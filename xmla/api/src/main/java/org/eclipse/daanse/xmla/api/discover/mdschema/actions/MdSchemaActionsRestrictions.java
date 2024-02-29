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
package org.eclipse.daanse.xmla.api.discover.mdschema.actions;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.InvocationEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_INTEGER;
import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface MdSchemaActionsRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_CUBE_NAME = "CUBE_NAME";
    String RESTRICTIONS_ACTION_NAME = "ACTION_NAME";
    String RESTRICTIONS_ACTION_TYPE = "ACTION_TYPE";
    String RESTRICTIONS_COORDINATE = "COORDINATE";
    String RESTRICTIONS_COORDINATE_TYPE = "COORDINATE_TYPE";
    String RESTRICTIONS_INVOCATION = "INVOCATION";
    String RESTRICTIONS_CUBE_SOURCE = "CUBE_SOURCE";

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
    String cubeName();

    /**
     * @return The name of this action.
     */
    @Restriction(name = RESTRICTIONS_ACTION_NAME, type = XSD_STRING, order = 3)
    Optional<String> actionName();

    /**
     * @return A bitmask that is used to specify the action type.
     * 0x01 - Action type is URL.
     * 0x02 - Action type is HTML.
     * 0x04 - Action type is Statement.
     * 0x08 - Action type is Dataset.
     * 0x10 - Action type is Rowset.
     * 0x20 - Action type is Commandline.
     * 0x40 - Action type is Proprietary.
     * 0x80 - Action type is Report.
     * 0x100 - Action type is DrillThrough.
     * If the action is PROPRIETARY (0x40), then a value MUST be
     * provided in the APPLICATION column.
     */
   // @Restriction(name = RESTRICTIONS_ACTION_TYPE, type = XSD_INTEGER, order = 4)
    Optional<ActionTypeEnum> actionType();

    /**
     * @return An MDX expression that specifies an object or a coordinate in
     * the multidimensional space in which the action is performed.
     * The COORDINATE MUST resolve to the object specified in
     * COORDINATE_TYPE.
     */
    @Restriction(name = RESTRICTIONS_COORDINATE, type = XSD_STRING, order = 5)
    Optional<String> coordinate();

    /**
     * @return An enumeration that specifies how the COORDINATE restriction
     * column is interpreted. The possible values are as follows:
     * 1 - Action coordinate refers to the cube.
     * 2 - Action coordinate refers to a dimension.
     * 3 - Action coordinate refers to a level.
     * 4 - Action coordinate refers to a member.
     * 5 - Action coordinate refers to a set.
     * 6 - Action coordinate refers to a cell.
     */
    @Restriction(name = RESTRICTIONS_COORDINATE_TYPE, type = XSD_INTEGER, order = 6)
    CoordinateTypeEnum coordinateType();

    /**
     * @return Information about how to invoke the action:
     * 1 - Indicates a regular action used during normal
     * operations. This is the default value for this column.
     * 2 - Indicates that the action is performed when the cube is
     * first opened.
     * 4 - Indicates that the action is performed as part of a batch
     * operation.
     */
    //@Restriction(name = RESTRICTIONS_INVOCATION, type = XSD_INTEGER, order = 7)
    InvocationEnum invocation();

    /**
     * @return A bitmask with one of these valid values:
     * 0x01 - Cube
     * 0x02 - Dimension
     * The default restriction is a value of 1.
     */
    //@Restriction(name = RESTRICTIONS_CUBE_SOURCE, type = XSD_INTEGER, order = 8)
    Optional<CubeSourceEnum> cubeSource();
}
