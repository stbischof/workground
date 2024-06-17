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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;

public record CubeGrantR(String cube,
                         String access,
                         List<MappingDimensionGrant> dimensionGrants,
                         List<MappingHierarchyGrant> hierarchyGrants)
        implements MappingCubeGrant {




	public  CubeGrantR(String cube,
            String access,
            List<MappingDimensionGrant> dimensionGrants,
            List<MappingHierarchyGrant> hierarchyGrants)
 {
	this.cube = cube;
	this.access = access;
	this.dimensionGrants = dimensionGrants == null ? List.of() : dimensionGrants;
	this.hierarchyGrants = hierarchyGrants == null ? List.of() : hierarchyGrants;

 }

    public String getCube() {
        return cube;
    }

    public String getAccess() {
        return access;
    }

    public List<MappingDimensionGrant> getDimensionGrants() {
        return dimensionGrants;
    }

    public List<MappingHierarchyGrant> getHierarchyGrants() {
        return hierarchyGrants;
    }
}
