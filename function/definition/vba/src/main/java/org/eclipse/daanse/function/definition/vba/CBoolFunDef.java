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

import static org.eclipse.daanse.function.definition.vba.CIntFunDef.cInt;

import mondrian.olap.Syntax;

public class CBoolFunDef extends JavaFunDef {
    public static final String NAME = "CBool";
    public static final String SIGNATURE = "CBool(expression)";
    public  static final String DESCRIPTION = """
    Returns an expression that has been converted to a Variant of subtype Boolean.
    """;
    private static final int[] params = {getCategory(Object.class)};
    private static final int ret = getCategory(boolean.class);
    private static final Function<Object, Object> f = CBoolFunDef::cBool;


    public CBoolFunDef(
    ) {
        super(NAME, DESCRIPTION, Syntax.Function, ret, params, f);
    }

    @Override
    public Class<?>[] getParameterTypes() {
        Class<?>[] res = {Object.class};
        return res;
    }

    public static boolean cBool(Object expression) {
        if (expression instanceof Boolean bool) {
            return bool;
        } else {
            int i = cInt(expression);
            return i != 0;
        }
    }

}
