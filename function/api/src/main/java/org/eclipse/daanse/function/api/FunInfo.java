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
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.function.api;

import mondrian.olap.Syntax;

public interface FunInfo extends Comparable<FunInfo>{

    String[] getSignatures();

    /**
     * Returns the syntactic type of the function.
     */
    Syntax getSyntax();

    /**
     * Returns the name of this function.
     */
    String getName();

    /**
     * Returns the description of this function.
     */
    String getDescription();

    /**
     * Returns the type of value returned by this function. Values are the same
     * as those returned by {@link mondrian.olap.Exp#getCategory()}.
     */
    int[] getReturnCategories();

    /**
     * Returns the types of the arguments of this function. Values are the same
     * as those returned by {@link mondrian.olap.Exp#getCategory()}. The
     * 0<sup>th</sup> argument of methods and properties are the object they
     * are applied to. Infix operators have two arguments, and prefix operators
     * have one argument.
     */
    int[][] getParameterCategories();


}
