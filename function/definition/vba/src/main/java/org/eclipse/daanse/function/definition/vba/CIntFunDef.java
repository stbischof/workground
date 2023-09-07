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
package org.eclipse.daanse.function.definition.vba;

import mondrian.olap.Syntax;


public class CIntFunDef extends JavaFunDef {
    public static final String NAME = "CInt";
    public static final String SIGNATURE = "CInt(expression)";
    public  static final String DESCRIPTION = """
    Returns an expression that has been converted to a Variant of subtype Integer.
    """;

    private static final int[] params = {getCategory(Object.class)};
    private static final int ret = getCategory(int.class);
    private static final Function<Object, Object> f = CIntFunDef::cInt;


    public CIntFunDef(
    ) {
        super(NAME, DESCRIPTION, Syntax.Function, ret, params, f);
    }

    @Override
    public Class<?>[] getParameterTypes() {
        Class<?>[] res = {Object.class};
        return res;
    }


    public static int cInt(Object expression) {
        if (expression instanceof Number number) {
            final int intValue = number.intValue();
            if (number instanceof Float || number instanceof Double) {
                final double doubleValue = number.doubleValue();
                if (doubleValue == (double) intValue) {
                    // Number is already an integer
                    return intValue;
                }
                final double doubleDouble = doubleValue * 2d;
                if (doubleDouble == Math.floor(doubleDouble)) {
                    // Number ends in .5 - round towards even required
                    return (int) Math.round(doubleValue / 2d) * 2;
                }
                return (int) Math.round(doubleValue);
            }
            return intValue;
        } else {
            // Try to parse as integer before parsing as double. More
            // efficient, and avoids loss of precision.
            final String s = String.valueOf(expression);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return Double.valueOf(s).intValue();
            }
        }
    }

}
