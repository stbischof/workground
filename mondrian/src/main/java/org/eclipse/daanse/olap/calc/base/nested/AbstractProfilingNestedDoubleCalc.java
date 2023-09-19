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

package org.eclipse.daanse.olap.calc.base.nested;

import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.DoubleCalc;
import org.eclipse.daanse.olap.calc.base.AbstractProfilingNestedCalc;

import mondrian.olap.Evaluator;
import mondrian.olap.fun.FunUtil;
import mondrian.olap.type.NumericType;
import mondrian.olap.type.Type;

/**
 * Abstract implementation of the {@link org.eclipse.daanse.olap.calc.api.IntegerCalc} interface.
 *
 * Handles nested child and profiling
 *
 */
public abstract class AbstractProfilingNestedDoubleCalc<C  extends Calc<?>>
extends AbstractProfilingNestedCalc<Double,C>
implements DoubleCalc
{
    /**
     * {@inheritDoc}
     *
     */
    protected AbstractProfilingNestedDoubleCalc(Type type, C[] calcs) {
        super(type, calcs);
        requiresType(NumericType.class);
    }

    @Override
    public Double evaluate(Evaluator evaluator, boolean caseSensitive) {
        final Double d = evaluate(evaluator, caseSensitive);
        if (d == FunUtil.DOUBLE_NULL) {
            return null;
        }
        return Double.valueOf(d);
    }
}
