/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package org.eclipse.daanse.function.definition;

import mondrian.calc.TupleIterable;
import mondrian.calc.impl.AbstractIterCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.QueryImpl;

import org.eclipse.daanse.function.FunDefBase;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

/**
 * Definition of the <code>AS</code> MDX operator.
 *
 * <p>Using <code>AS</code>, you can define an alias for an MDX expression
 * anywhere it appears in a query, and use that alias as you would a calculated
 * yet.
 *
 * @author jhyde
 * @since Oct 7, 2009
 */
class AsFunDef extends FunDefBase {
    private final QueryImpl.ScopedNamedSet scopedNamedSet;

    /**
     * Creates an AsFunDef.
     *
     * @param scopedNamedSet Named set definition
     */
     AsFunDef(QueryImpl.ScopedNamedSet scopedNamedSet) {
        super(
            "AS",
            "<Expression> AS <Name>",
            "Assigns an alias to an expression",
            "ixxn");
        this.scopedNamedSet = scopedNamedSet;
    }

    @Override
	public Calc compileCall(ResolvedFunCall call, ExpressionCompiler compiler) {
        // Argument 0, the definition of the set, has been resolved since the
        // scoped named set was created. Implicit conversions, like converting
        // a member to a set, have been performed. Use the new expression.
        scopedNamedSet.setExp(call.getArg(0));

        return new AbstractIterCalc(call.getType(), new Calc[0]) {
            @Override
			public TupleIterable evaluateIterable(
                Evaluator evaluator)
            {
                final Evaluator.NamedSetEvaluator namedSetEvaluator =
                    evaluator.getNamedSetEvaluator(scopedNamedSet, false);
                return namedSetEvaluator.evaluateTupleIterable(evaluator);
            }
        };
    }
}
