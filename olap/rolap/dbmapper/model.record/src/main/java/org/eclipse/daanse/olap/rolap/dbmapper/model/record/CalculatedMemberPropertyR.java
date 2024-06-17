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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMemberProperty;

public record CalculatedMemberPropertyR(String name,
										String description,
                                        String caption,
                                        String expression,
                                        String value)
        implements MappingCalculatedMemberProperty {

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCaption() {
        return caption;
    }

    public String getExpression() {
        return expression;
    }

    public String getValue() {
        return value;
    }
}
