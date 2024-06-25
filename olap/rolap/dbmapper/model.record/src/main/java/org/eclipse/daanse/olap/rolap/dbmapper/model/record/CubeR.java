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

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;

public record CubeR(String name,
                    String description,
                    List<MappingAnnotation> annotations,
                    String caption,
                    Boolean visible,
                    String defaultMeasure,
                    List<MappingCubeDimension> dimensionUsageOrDimensions,
                    List<MappingMeasure> measures,
                    List<MappingCalculatedMember> calculatedMembers,
                    List<MappingNamedSet> namedSets,
                    List<MappingDrillThroughAction> drillThroughActions,
                    Optional<MappingWritebackTable> writebackTable,
                    Boolean enabled,
                    Boolean cache,
                    MappingRelationQuery fact,
                    List<MappingAction> actions,
                    List<MappingKpi> kpis
                    )
        implements MappingCube {




	public  CubeR(String name,
            String description,
            List<MappingAnnotation> annotations,
            String caption,
            Boolean visible,
            String defaultMeasure,
            List<MappingCubeDimension> dimensionUsageOrDimensions,
            List<MappingMeasure> measures,
            List<MappingCalculatedMember> calculatedMembers,
            List<MappingNamedSet> namedSets,
            List<MappingDrillThroughAction> drillThroughActions,
            Optional<MappingWritebackTable> writebackTable,
            Boolean enabled,
            Boolean cache,
            MappingRelationQuery fact,
            List<MappingAction> actions,
            List<MappingKpi> kpis
            ) {
				this.name = name;
				this.description = description;
				this.annotations = annotations == null ? List.of() : annotations;
				this.caption = caption;
				this.visible = visible== null ? Boolean.TRUE : visible;
				this.defaultMeasure = defaultMeasure;
				this.dimensionUsageOrDimensions = dimensionUsageOrDimensions == null ? List.of() : dimensionUsageOrDimensions;
				this.measures = measures == null ? List.of() : measures;
				this.calculatedMembers = calculatedMembers == null ? List.of() : calculatedMembers;
				this.namedSets = namedSets == null ? List.of() : namedSets;
				this.drillThroughActions = drillThroughActions == null ? List.of() : drillThroughActions;
				this.writebackTable = writebackTable;
				this.enabled = enabled == null ? Boolean.TRUE : enabled;
				this.cache = cache == null ? Boolean.TRUE : cache;
				this.fact = fact;
				this.actions = actions == null ? List.of() : actions;
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

    public List<MappingCubeDimension> getDimensionUsageOrDimensions() {
        return dimensionUsageOrDimensions;
    }

    public List<MappingMeasure> getMeasures() {
        return measures;
    }

    public List<MappingCalculatedMember> getCalculatedMembers() {
        return calculatedMembers;
    }

    public List<MappingNamedSet> getNamedSets() {
        return namedSets;
    }

    public List<MappingDrillThroughAction> getDrillThroughActions() {
        return drillThroughActions;
    }

    public Optional<MappingWritebackTable> getWritebackTable() {
        return writebackTable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getCache() {
        return cache;
    }

    public MappingRelationQuery getFact() {
        return fact;
    }

    public List<MappingAction> getActions() {
        return actions;
    }

    public List<MappingKpi> getKpis() {
        return kpis;
    }
}
