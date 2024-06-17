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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.AccessEnum;

public record SchemaGrantR(List<MappingCubeGrant> cubeGrants,
                           AccessEnum access)
    implements MappingSchemaGrant {

    public SchemaGrantR(
        List<MappingCubeGrant> cubeGrants,
        AccessEnum access
    ) {
        this.cubeGrants = cubeGrants == null ? List.of() : cubeGrants;
        this.access = access ;//== null ? AccessEnum.NONE : access;

    }

    public List<MappingCubeGrant> getCubeGrants() {
        return cubeGrants;
    }

    public AccessEnum getAccess() {
        return access;
    }
}
