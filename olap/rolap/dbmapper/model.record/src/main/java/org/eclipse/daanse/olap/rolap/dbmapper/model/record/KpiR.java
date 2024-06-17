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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTranslation;

import java.util.List;

public record KpiR(String name,
                   String description,
                   String caption,
                   List<MappingAnnotation> annotations,
                   String id,
                   List<MappingTranslation> translations,
                   String displayFolder,
                   String associatedMeasureGroupID,
                   String value,
                   String goal,
                   String status,
                   String trend,
                   String weight,
                   String trendGraphic,
                   String statusGraphic,
                   String currentTimeMember,
                   String parentKpiID
) implements MappingKpi {

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCaption() {
        return caption;
    }

    public List<MappingAnnotation> getAnnotations() {
        return annotations;
    }

    public String getId() {
        return id;
    }

    public List<MappingTranslation> getTranslations() {
        return translations;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public String getAssociatedMeasureGroupID() {
        return associatedMeasureGroupID;
    }

    public String getValue() {
        return value;
    }

    public String getGoal() {
        return goal;
    }

    public String getStatus() {
        return status;
    }

    public String getTrend() {
        return trend;
    }

    public String getWeight() {
        return weight;
    }

    public String getTrendGraphic() {
        return trendGraphic;
    }

    public String getStatusGraphic() {
        return statusGraphic;
    }

    public String getCurrentTimeMember() {
        return currentTimeMember;
    }

    public String getParentKpiID() {
        return parentKpiID;
    }
}
