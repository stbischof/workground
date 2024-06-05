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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum ActionTypeEnum {

    /**
     * Action type is URL.
     */
    URL(0x01),

    /**
     * Action type is HTML.
     */
    HTML(0x02),

    /**
     * Action type is Statement.
     */
    STATEMENT(0x04),

    /**
     * Action type is Dataset.
     */
    DATASET(0x08),

    /**
     * Action type is Rowset.
     */
    ROW_SET(0x10),

    /**
     * Action type is Commandline.
     */
    COMMANDLINE(0x20),

    /**
     * Action type is Proprietary.
     */
    PROPRIETARY(0x40),

    /**
     * Action type is Report.
     */
    REPORT(0x80),

    /**
     * Action type is DrillThrough.
     */
    DRILL_THROUGH(0x100);

    private final int value;

    ActionTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static ActionTypeEnum fromValue(String v) {
        if (v == null){
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(ActionTypeEnum.values())
            .filter(e -> (e.getValue() == vi))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                new StringBuilder("ActionTypeEnum Illegal argument ").append(v)
                    .toString())
            );
    }

    public static List<ActionTypeEnum> parseActionType(String v)
    {
        List<ActionTypeEnum> apList = new ArrayList<ActionTypeEnum>();
        if (v == null) {
            int val = Integer.decode(v);
            for (ActionTypeEnum ap : values()) {
                if ((val & ap.getValue()) != 0) {
                    apList.add(ap);
                }
            }
        }
        return apList;
    }
}
