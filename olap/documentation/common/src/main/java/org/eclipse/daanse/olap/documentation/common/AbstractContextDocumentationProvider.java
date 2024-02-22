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
*   SmartCity Jena - initial
*/
package org.eclipse.daanse.olap.documentation.common;

import org.eclipse.daanse.olap.documentation.api.ConntextDocumentationProvider;

import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractContextDocumentationProvider implements ConntextDocumentationProvider {

    protected static final Map<Integer, String> TYPE_MAP;
    static {
        Map<Integer, String> typeMapInitial = new HashMap<>();
        typeMapInitial.put(Types.SMALLINT, "INTEGER");
        typeMapInitial.put(Types.INTEGER, "INTEGER");
        typeMapInitial.put(Types.BOOLEAN, "BOOLEAN");
        typeMapInitial.put(Types.DOUBLE, "DOUBLE");
        typeMapInitial.put(Types.FLOAT, "DOUBLE");
        typeMapInitial.put(Types.BIGINT, "LONG");
        typeMapInitial.put(Types.DATE, "DATE");
        typeMapInitial.put(Types.TIMESTAMP, "TIMESTAMP");
        typeMapInitial.put(Types.TIME, "TIME");
        typeMapInitial.put(Types.VARCHAR, "STRING");

        TYPE_MAP = Collections.unmodifiableMap(typeMapInitial);
    }
}
