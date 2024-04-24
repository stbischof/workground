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

}
