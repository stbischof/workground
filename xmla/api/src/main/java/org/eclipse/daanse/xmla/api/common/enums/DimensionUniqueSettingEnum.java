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
package org.eclipse.daanse.xmla.api.common.enums;

import java.util.stream.Stream;

public enum DimensionUniqueSettingEnum {

    NONE(0x00000000),
    MEMBER_KEY(0x00000001),
    MEMBER_NAME(0x00000002);

    private final int value;

    DimensionUniqueSettingEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static DimensionUniqueSettingEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(DimensionUniqueSettingEnum.values())
            .filter(e -> (e.value == vi))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                new StringBuilder("DimensionUniqueSettingEnum Illegal argument ").append(v)
                    .toString())
            );
    }
}
