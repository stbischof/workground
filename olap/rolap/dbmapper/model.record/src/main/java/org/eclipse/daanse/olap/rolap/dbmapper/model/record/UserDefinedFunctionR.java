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
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingScript;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;

public record UserDefinedFunctionR(String name,
                                   String className, MappingScript script)
        implements MappingUserDefinedFunction {

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public MappingScript getScript() {
        return script;
    }
}
