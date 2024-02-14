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

public enum CubeSourceEnum {

    CUBE(0x01),

    DIMENSION(0x02);

    private final int value;

    CubeSourceEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static CubeSourceEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int val;
        try {
            val = Integer.decode(v);
        } catch (NumberFormatException e) {
            val = 1;
        }
        int vi = val;
        return Stream.of(CubeSourceEnum.values())
            .filter(e -> (e.value == vi))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                new StringBuilder("CubeSourceEnum Illegal argument ").append(v)
                    .toString())
            );
    }
}
