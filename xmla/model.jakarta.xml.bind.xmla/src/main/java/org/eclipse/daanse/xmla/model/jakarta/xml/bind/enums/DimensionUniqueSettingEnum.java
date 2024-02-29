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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.util.stream.Stream;

@XmlType(name = "DimensionUniqueSetting")
@XmlEnum
public enum DimensionUniqueSettingEnum {

    @XmlEnumValue("0x00000000")
    NONE(0x00000000),

    @XmlEnumValue("0x00000001")
    MEMBER_KEY(0x00000001),

    @XmlEnumValue("0x00000002")
    MEMBER_NAME(0x00000002);

    private final int value;

    DimensionUniqueSettingEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static DimensionUniqueSettingEnum fromValue(int v) {
        return Stream.of(DimensionUniqueSettingEnum.values()).filter(e -> (e.value == v)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                new StringBuilder("DimensionUniqueSettingEnum Illegal argument ").append(v)
                    .toString())
            );
    }
}
