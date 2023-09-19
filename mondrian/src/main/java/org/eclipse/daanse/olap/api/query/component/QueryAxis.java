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
package org.eclipse.daanse.olap.api.query.component;

import mondrian.calc.ExpCompiler;
import mondrian.calc.ResultStyle;
import mondrian.mdx.MdxVisitor;
import mondrian.olap.Exp;
import mondrian.olap.QueryAxisImpl;
import mondrian.olap.Validator;
import mondrian.olap.api.SubtotalVisibility;

import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.calc.api.Calc;

public non-sealed interface QueryAxis extends QueryPart {

    String getAxisName();

    AxisOrdinal getAxisOrdinal();

    boolean isNonEmpty();

    void setNonEmpty(boolean nonEmpty);

    Exp getSet();

    void setSet(Exp set);

    Calc compile(ExpCompiler compiler, ResultStyle resultStyle, boolean caseSensitive);

    Object accept(MdxVisitor visitor, boolean caseSensitive);

    Id[] getDimensionProperties();

    SubtotalVisibility getSubtotalVisibility();

    void validate(Validator validator, boolean caseSensitive);

    void addLevel(Level level);

    void resolve(Validator validator, boolean caseSensitive);

    boolean isOrdered();

    void setOrdered(boolean ordered);

}
