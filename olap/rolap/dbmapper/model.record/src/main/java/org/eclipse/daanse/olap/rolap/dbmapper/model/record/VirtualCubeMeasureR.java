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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;

public record VirtualCubeMeasureR(String name,
                                  String description,
                                  List<MappingAnnotation> annotations,
                                  Boolean visible,
                                  String cubeName
)
    implements MappingVirtualCubeMeasure {

    public VirtualCubeMeasureR(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        Boolean visible,
        String cubeName
    ) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.visible = visible == null ? Boolean.TRUE : visible;
        this.cubeName = cubeName;
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

    public Boolean getVisible() {
        return visible;
    }

    public String getCubeName() {
        return cubeName;
    }
}
