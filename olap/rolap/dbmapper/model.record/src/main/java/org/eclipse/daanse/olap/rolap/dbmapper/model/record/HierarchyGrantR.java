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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.AccessEnum;

public record HierarchyGrantR(
    String hierarchy,
    AccessEnum access,
    String topLevel,
    String bottomLevel,
    String rollupPolicy,
    List<MappingMemberGrant> memberGrants

) implements MappingHierarchyGrant {

    public HierarchyGrantR(
        String hierarchy,
        AccessEnum access,
        String topLevel,
        String bottomLevel,
        String rollupPolicy,
        List<MappingMemberGrant> memberGrants

    ) {
        this.hierarchy = hierarchy;
        this.access = access ;//== null ? AccessEnum.NONE : access;
        this.topLevel = topLevel;
        this.bottomLevel = bottomLevel;
        this.rollupPolicy = rollupPolicy;
        this.memberGrants = memberGrants == null ? List.of() : memberGrants;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public String getTopLevel() {
        return topLevel;
    }

    public String getBottomLevel() {
        return bottomLevel;
    }

    public String getRollupPolicy() {
        return rollupPolicy;
    }

    public List<MappingMemberGrant> getMemberGrants() {
        return memberGrants;
    }
}
