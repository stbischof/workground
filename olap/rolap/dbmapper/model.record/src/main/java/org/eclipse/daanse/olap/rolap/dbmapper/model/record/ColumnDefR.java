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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTableColumnDefinition;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;

public record ColumnDefR(String name,
                         TypeEnum type)
        implements MappingInlineTableColumnDefinition {

    public ColumnDefR(String name, TypeEnum type) {
        this.name = name;
        this.type = type;// == null ? TypeEnum.STRING : type;
    }

    public String getName() {
        return name;
    }

    public TypeEnum getType() {
        return type;
    }
}
