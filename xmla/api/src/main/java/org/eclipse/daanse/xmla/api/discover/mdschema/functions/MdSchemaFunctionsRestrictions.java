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
package org.eclipse.daanse.xmla.api.discover.mdschema.functions;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_INTEGER;
import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface MdSchemaFunctionsRestrictions {

    String RESTRICTIONS_FUNCTION_NAME = "FUNCTION_NAME";
    String RESTRICTIONS_ORIGIN = "ORIGIN";
    String RESTRICTIONS_INTERFACE_NAME = "INTERFACE_NAME";
    String RESTRICTIONS_LIBRARY_NAME = "LIBRARY_NAME";

    @Restriction(name = RESTRICTIONS_FUNCTION_NAME, type = XSD_STRING, order = 0)
    Optional<String> functionName();

    /**
     * @return The possible values are as follows:
     * (0x1) MSOLAP
     * (0x2) UDF
     * (0x3) RELATIONAL
     * (0x4) SCALAR
     */
    @Restriction(name = RESTRICTIONS_ORIGIN, type = XSD_INTEGER, order = 1)
    Optional<OriginEnum> origin();

    /**
     * @return A logical classification of the type of function. For
     * example:
     * DATETIME
     * LOGICAL
     * FILTER
     */
    @Restriction(name = RESTRICTIONS_INTERFACE_NAME, type = XSD_STRING, order = 2)
    Optional<InterfaceNameEnum> interfaceName();

    /**
     * @return The library that implements the function.
     */
    @Restriction(name = RESTRICTIONS_LIBRARY_NAME, type = XSD_STRING, order = 3)
    Optional<String> libraryName();
}
