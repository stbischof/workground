/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ObjectExpansionEnum;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataRestrictions;

public record DiscoverXmlMetaDataRestrictionsR(
    Optional<String> objectType,
    Optional<String> databaseId,
    Optional<String> dimensionId,
    Optional<String> cubeId,
    Optional<String> measureGroupId,
    Optional<String> partitionId,
    Optional<String> perspectiveId,
    Optional<String> dimensionPermissionId,
    Optional<String> roleId,
    Optional<String> databasePermissionId,
    Optional<String> miningModelId,
    Optional<String> miningModelPermissionId,
    Optional<String> dataSourceId,
    Optional<String> miningStructureId,
    Optional<String> aggregationDesignId,
    Optional<String> traceId,
    Optional<String> miningStructurePermissionId,
    Optional<String> cubePermissionId,
    Optional<String> assemblyId,
    Optional<String> mdxScriptId,
    Optional<String> dataSourceViewId,
    Optional<String> dataSourcePermissionId,
    Optional<ObjectExpansionEnum> objectExpansion) implements DiscoverXmlMetaDataRestrictions {

}
