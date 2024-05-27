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
package org.eclipse.daanse.olap.rolap.dbmapper.model.api;

import java.util.List;
import java.util.Optional;

public interface MappingCube extends MappingBaseInterface{

    List<MappingCubeDimension> dimensionUsageOrDimensions();

    List<MappingMeasure> measures();

    List<MappingCalculatedMember> calculatedMembers();

    List<MappingNamedSet> namedSets();

    List<MappingDrillThroughAction> drillThroughActions();

    Optional<MappingWritebackTable> writebackTable();

    String defaultMeasure();

    Boolean cache();

    Boolean enabled();

    Boolean visible();

    MappingRelation fact();

    List<MappingAction> actions();

    List<MappingKpi> kpis();
}
