/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.calc.api.Calc;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleCursor;
import mondrian.calc.TupleIterable;
import mondrian.calc.TupleList;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Util;

/**
 * Evaluation context to be able to expand a generic expression that returns a
 * set
 *
 * @author pedro alves
 * @since September 14, 2012
 */
class RolapSetEvaluator
    implements Evaluator.SetEvaluator, TupleList.PositionCallback
{

    private final RolapResult.RolapResultEvaluatorRoot rrer;
    private final Exp exp;
    /**
     * Value of this named set; set on first use.
     */
    private TupleList list;
    /**
     * Dummy list used as a marker to detect re-entrant calls to
     * {@link #ensureList}.
     */
    private static final TupleList DUMMY_LIST =
            TupleCollections.createList(1);
    /**
     * Ordinal of current iteration through the named set. Used to implement the
     * &lt;Named Set&gt;.CurrentOrdinal and &lt;Named Set&gt;.Current functions.
     */
    private int currentOrdinal;

    /**
     * Creates a RolapNamedSetEvaluator.
     *
     * @param rrer Evaluation root context
     * @param exp Expression
     */
    public RolapSetEvaluator(
        RolapResult.RolapResultEvaluatorRoot rrer,
        Exp exp)
    {
        this.rrer = rrer;
        this.exp = exp;
    }

    @Override
	public TupleIterable evaluateTupleIterable() {
        ensureList();
        return list;
    }

    /**
     * Evaluates and saves the value of this named set, if it has not been
     * evaluated already.
     */
    private void ensureList() {
        if (list != null) {
            if (list == DUMMY_LIST) {
                throw rrer.result.slicerEvaluator.newEvalException(
                    null,
                    new StringBuilder("Illegal attempt to reference value of a set '")
                    .append(getExpression()).append("' while evaluating itself").toString());
            }
            return;
        }
        if (RolapResult.LOGGER.isDebugEnabled()) {
            RolapResult.LOGGER.debug(
                "Set {}: starting evaluation", exp);
        }
        list = DUMMY_LIST; // recursion detection
        try {
            final Calc calc =
                rrer.getCompiled(
                    exp, false, ResultStyle.ITERABLE);
            TupleIterable iterable =
                    (TupleIterable) rrer.result.evaluateExp(
                        calc,
                        rrer.result.slicerEvaluator, null);

            // Axes can be in two forms: list or iterable. If iterable, we
            // need to materialize it, to ensure that all cell values are in
            // cache.
            final TupleList rawList;
            if (iterable instanceof TupleList) {
                rawList = (TupleList) iterable;
            } else {
                rawList = TupleCollections.createList(iterable.getArity());
                TupleCursor cursor = iterable.tupleCursor();
                while (cursor.forward()) {
                    rawList.addCurrent(cursor);
                }
            }
            if (RolapResult.LOGGER.isDebugEnabled()) {
                RolapResult.LOGGER.debug(generateDebugMessage(calc, rawList));
            }
            // Wrap list so that currentOrdinal is updated whenever the list
            // is accessed. The list is immutable, because we don't override
            // AbstractList.set(int, Object).
            this.list = rawList.withPositionCallback(this);
        } finally {
            if (this.list == DUMMY_LIST) {
                this.list = null;
            }
        }
    }

    private String generateDebugMessage(Calc calc, TupleList rawList) {
        final StringBuilder buf = new StringBuilder();
        buf.append(this);
        buf.append(": ");
        buf.append("Set expression ");
        buf.append(getExpression());
        buf.append(" evaluated to:");
        buf.append(Util.NL);
        int arity = calc.getType().getArity();
        int rowCount = 0;
        final int maxRowCount = 100;
        if (arity == 1) {
            for (Member t : rawList.slice(0)) {
                if (rowCount++ > maxRowCount) {
                    buf.append("...");
                    buf.append(Util.NL);
                    break;
                }
                buf.append(t);
                buf.append(Util.NL);
            }
        } else {
            for (List<Member> t : rawList) {
                if (rowCount++ > maxRowCount) {
                    buf.append("...");
                    buf.append(Util.NL);
                    break;
                }
                int k = 0;
                for (Member member : t) {
                    if (k++ > 0) {
                        buf.append(", ");
                    }
                    buf.append(member);
                }
                buf.append(Util.NL);
            }
        }
        return buf.toString();
    }

    private String getExpression() {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        exp.unparse(printWriter);
        return result.toString();
    }

    @Override
	public int currentOrdinal() {
        return currentOrdinal;
    }

    @Override
	public void onPosition(int index) {
        this.currentOrdinal = index;
    }

    @Override
	public Member[] currentTuple() {
        final List<Member> tuple = list.get(currentOrdinal);
        return tuple.toArray(new Member[tuple.size()]);
    }

    @Override
	public Member currentMember() {
        return list.get(0, currentOrdinal);
    }
}
