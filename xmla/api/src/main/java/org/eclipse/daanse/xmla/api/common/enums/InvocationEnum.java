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

public enum InvocationEnum {

    /**
     * Indicates a regular action used during normal operations. This is the default value for this column.
     */
    NORMAL_OPERATION(1),

    /**
     * Indicates that the action is performed when the cube is first opened.
     */
    FIRST_OPENED_CUBE(2),

    /**
     * Indicates that the action is performed as part of a batch
     */
    BATCH_OPERATION(4);

    private final int value;

    InvocationEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static InvocationEnum fromValue(String v) {
    	if (v != null) {
    		int vi = Integer.parseInt(v);
    		return Stream.of(InvocationEnum.values())
    				.filter(e -> (e.value == vi))
    				.findFirst()
    				.orElseThrow(() -> new IllegalArgumentException(
    						new StringBuilder("InvocationEnum Illegal argument ").append(v)
    						.toString())
    						);
    	}
    	return null;
    }
}
