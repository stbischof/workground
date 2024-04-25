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
package org.eclipse.daanse.olap.rolap.dbmapper.model.api;

import java.util.List;

public interface MappingKpi {
    String name();

    String id();

    String description();

    String caption();

    List<MappingTranslation> translations();

    String displayFolder();

    String associatedMeasureGroupID();

    String value();

    String goal();

    String status();

    String trend();

    String weight();

    String trendGraphic();

    String statusGraphic();

    String currentTimeMember();

    String parentKpiID();

    List<MappingAnnotation> annotations();

}
