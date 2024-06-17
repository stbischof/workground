/*
 * Copyright (c) 0 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License .0
 * which is available at https://www.eclipse.org/legal/epl-.0/
 *
 * SPDX-License-Identifier: EPL-.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;

public record DimensionUsageR(String name,
                              String description,
                              List<MappingAnnotation> annotations,
                              String caption,
                              Boolean visible,
                              String source,
                              String level,
                              String usagePrefix,
                              String foreignKey
)
    implements MappingDimensionUsage {

    public DimensionUsageR(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String source,
        String level,
        String usagePrefix,
        String foreignKey
    ) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.caption = caption;
        this.visible = visible == null ? Boolean.TRUE : visible;
        this.source = source;
        this.level = level;
        this.usagePrefix = usagePrefix;
        this.foreignKey = foreignKey;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<MappingAnnotation> getAnnotations() {
        return annotations;
    }

    public String getCaption() {
        return caption;
    }

    public Boolean getVisible() {
        return visible;
    }

    public String getSource() {
        return source;
    }

    public String getLevel() {
        return level;
    }

    public String getUsagePrefix() {
        return usagePrefix;
    }

    public String getForeignKey() {
        return foreignKey;
    }
}
