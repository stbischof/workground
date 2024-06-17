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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRoleUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUnion;

public record UnionR(List<MappingRoleUsage> roleUsages) implements MappingUnion {

	public  UnionR(List<MappingRoleUsage> roleUsages)   {
		this.roleUsages = roleUsages == null ? List.of() : roleUsages;

	}

    public List<MappingRoleUsage> getRoleUsages() {
        return roleUsages;
    }
}
