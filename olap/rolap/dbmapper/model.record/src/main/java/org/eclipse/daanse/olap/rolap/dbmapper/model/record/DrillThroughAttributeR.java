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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAttribute;

public record DrillThroughAttributeR(String dimension,
                                     String level,
                                     String hierarchy,
                                     String property)
        implements MappingDrillThroughAttribute {

    public String getDimension() {
        return dimension;
    }

    public String getLevel() {
        return level;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public String getProperty() {
        return property;
    }
}
