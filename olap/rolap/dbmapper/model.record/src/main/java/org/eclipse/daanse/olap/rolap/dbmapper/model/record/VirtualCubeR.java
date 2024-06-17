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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;

public record VirtualCubeR(String name,
                           String description,
                           List<MappingAnnotation> annotations,
                           String caption,
                           Boolean visible,
                           String defaultMeasure,
                           Boolean enabled,
                           List<MappingCubeUsage> cubeUsages,
                           List<MappingVirtualCubeDimension> virtualCubeDimensions,
                           List<MappingVirtualCubeMeasure> virtualCubeMeasures,
                           List<MappingCalculatedMember> calculatedMembers,
                           List<MappingNamedSet> namedSets,
                           List<MappingKpi> kpis
) implements MappingVirtualCube {

    public VirtualCubeR(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String defaultMeasure,
        Boolean enabled,
        List<MappingCubeUsage> cubeUsages,
        List<MappingVirtualCubeDimension> virtualCubeDimensions,
        List<MappingVirtualCubeMeasure> virtualCubeMeasures,
        List<MappingCalculatedMember> calculatedMembers,
        List<MappingNamedSet> namedSets,
        List<MappingKpi> kpis
    ) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.caption = caption;
        this.visible = visible == null ? Boolean.TRUE : visible;
        this.defaultMeasure = defaultMeasure;
        this.enabled = enabled == null ? Boolean.TRUE : enabled;
        this.cubeUsages = cubeUsages == null ? List.of() : cubeUsages;
        this.virtualCubeDimensions = virtualCubeDimensions == null ? List.of() : virtualCubeDimensions;
        this.virtualCubeMeasures = virtualCubeMeasures == null ? List.of() : virtualCubeMeasures;
        this.calculatedMembers = calculatedMembers == null ? List.of() : calculatedMembers;
        this.namedSets = namedSets == null ? List.of() : namedSets;
        this.kpis = kpis == null ? List.of() : kpis;
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

    public String getDefaultMeasure() {
        return defaultMeasure;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public List<MappingCubeUsage> getCubeUsages() {
        return cubeUsages;
    }

    public List<MappingVirtualCubeDimension> getVirtualCubeDimensions() {
        return virtualCubeDimensions;
    }

    public List<MappingVirtualCubeMeasure> getVirtualCubeMeasures() {
        return virtualCubeMeasures;
    }

    public List<MappingCalculatedMember> getCalculatedMembers() {
        return calculatedMembers;
    }

    public List<MappingNamedSet> getNamedSets() {
        return namedSets;
    }

    public List<MappingKpi> getKpis() {
        return kpis;
    }
}
