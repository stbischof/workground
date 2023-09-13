/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package org.eclipse.daanse.function.definition;

import mondrian.calc.impl.GenericCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;

import org.eclipse.daanse.function.FunDef;
import org.eclipse.daanse.function.FunDefBase;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.constant.ConstantCalcs;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition of the matched <code>CASE</code> MDX operator.
 *
 * Syntax is:
 * <blockquote><pre><code>Case &lt;Expression&gt;
 * When &lt;Expression&gt; Then &lt;Expression&gt;
 * [...]
 * [Else &lt;Expression&gt;]
 * End</code></blockquote>.
 *
 * @see CaseTestFunDef
 * @author jhyde
 * @since Mar 23, 2006
 */
class CaseMatchFunDef extends FunDefBase {

    CaseMatchFunDef(FunDef dummyFunDef) {
        super(dummyFunDef);
    }

    @Override
	public Calc compileCall(ResolvedFunCall call, ExpressionCompiler compiler) {
        final Exp[] args = call.getArgs();
        final List<Calc> calcList = new ArrayList<>();
        final Calc valueCalc =
                compiler.compileScalar(args[0], true);
        calcList.add(valueCalc);
        final int matchCount = (args.length - 1) / 2;
        final Calc[] matchCalcs = new Calc[matchCount];
        final Calc[] exprCalcs = new Calc[matchCount];
        for (int i = 0, j = 1; i < exprCalcs.length; i++) {
            matchCalcs[i] = compiler.compileScalar(args[j++], true);
            calcList.add(matchCalcs[i]);
            exprCalcs[i] = compiler.compile(args[j++]);
            calcList.add(exprCalcs[i]);
        }
        final Calc defaultCalc =
            args.length % 2 == 0
            ? compiler.compile(args[args.length - 1])
            : ConstantCalcs.nullCalcOf(call.getType());
        calcList.add(defaultCalc);
        final Calc[] calcs = calcList.toArray(new Calc[calcList.size()]);

        return new GenericCalc(call.getType()) {
            @Override
			public Object evaluate(Evaluator evaluator) {
                Object value = valueCalc.evaluate(evaluator);
                for (int i = 0; i < matchCalcs.length; i++) {
                    Object match = matchCalcs[i].evaluate(evaluator);
                    if (match.equals(value)) {
                        return exprCalcs[i].evaluate(evaluator);
                    }
                }
                return defaultCalc.evaluate(evaluator);
            }

            @Override
			public Calc[] getChildCalcs() {
                return calcs;
            }
        };
    }
}
