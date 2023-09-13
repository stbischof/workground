/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.calc.impl;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.base.AbstractProfilingNestedCalc;

import mondrian.calc.TupleIteratorCalc;
import mondrian.calc.TupleIterable;
import mondrian.olap.Evaluator;
import mondrian.olap.type.SetType;
import mondrian.olap.type.Type;

/**
 * Abstract implementation of the {@link mondrian.calc.TupleIteratorCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateIterable(mondrian.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian.olap.Evaluator)} method will call it.
 *
 * @see mondrian.calc.impl.AbstractListCalc
 *
 * @author jhyde
 * @since Oct 24, 2008
 */
public abstract class AbstractIterCalc
extends AbstractProfilingNestedCalc<Object,Calc<?>>
implements TupleIteratorCalc
{
    /**
     * Creates an abstract implementation of a compiled expression which returns
     * a {@link TupleIterable}.
     *
     * @param exp Expression which was compiled
     * @param calcs List of child compiled expressions (for dependency
     *   analysis)
     */
    protected AbstractIterCalc( Type type, Calc[] calcs) {
        super(type, calcs);
    }

    @Override
    public SetType getType() {
        return (SetType) super.getType();
    }

    @Override
    public final Object evaluate(Evaluator evaluator) {
        return evaluateIterable(evaluator);
    }

    @Override
    public ResultStyle getResultStyle() {
        return ResultStyle.ITERABLE;
    }

    @Override
    public String toString() {
        return "AbstractIterCalc object";
    }
}
