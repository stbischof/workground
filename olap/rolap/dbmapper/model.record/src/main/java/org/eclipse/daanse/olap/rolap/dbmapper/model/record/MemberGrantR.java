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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MemberGrantAccessEnum;

public record MemberGrantR(String member,
                           MemberGrantAccessEnum access)
        implements MappingMemberGrant {

    public MemberGrantR(String member, MemberGrantAccessEnum access) {
        this.member = member;
        this.access = access ;//== null ? MemberGrantAccessEnum.NONE : access;
    }

    public String getMember() {
        return member;
    }

    public MemberGrantAccessEnum getAccess() {
        return access;
    }
}
