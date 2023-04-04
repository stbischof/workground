/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.calc.impl;

import mondrian.calc.Calc;
import mondrian.calc.IterCalc;
import mondrian.calc.ListCalc;
import mondrian.calc.TupleCollections;
import mondrian.calc.TupleCursor;
import mondrian.calc.TupleIterable;
import mondrian.calc.TupleList;
import mondrian.olap.Evaluator;
import mondrian.olap.type.SetType;
import mondrian.olap.type.Type;

/**
 * Adapter which computes a set expression and converts it to any list or
 * iterable type.
 *
 * @author jhyde
 * @since Nov 7, 2008
 */
public abstract class GenericIterCalc
extends AbstractCalc
implements ListCalc, IterCalc
{
    /**
     * Creates a GenericIterCalc without specifying child calculated
     * expressions.
     *
     * <p>Subclass should override {@link #getCalcs()}.
     *
     * @param exp Source expression
     */
    protected GenericIterCalc(String name, Type type) {
        super( name,  type, null);
    }

    /**
     * Creates an GenericIterCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    protected GenericIterCalc(String name, Type type, Calc[] calcs) {
        super(name,type, calcs);
    }

    @Override
    public SetType getType() {
        return (SetType) type;
    }

    @Override
    public TupleList evaluateList(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        if (o instanceof TupleList) {
            return (TupleList) o;
        }
        // Iterable
        final TupleIterable iterable = (TupleIterable) o;
        final TupleList tupleList =
                TupleCollections.createList(iterable.getArity());
        final TupleCursor cursor = iterable.tupleCursor();
        while (cursor.forward()) {
            tupleList.addCurrent(cursor);
        }
        return tupleList;
    }

    @Override
    public TupleIterable evaluateIterable(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        return (TupleIterable) o;
    }
}
