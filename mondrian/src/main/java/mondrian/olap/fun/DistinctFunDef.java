/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Evaluator;

/**
 * Definition of the <code>Distinct</code> MDX function.
 *
 * <p>Syntax:
 * <blockquote><code>Distinct(&lt;Set&gt;)</code></blockquote>
 *
 * @author jhyde
 * @since Jun 10, 2007
*/
class DistinctFunDef extends FunDefBase {
    public static final DistinctFunDef instance = new DistinctFunDef();

    private DistinctFunDef() {
        super(
            "Distinct",
            "Eliminates duplicate tuples from a set.",
            "fxx");
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleListCalc tupleListCalc =
            compiler.compileList(call.getArg(0));
        return new CalcImpl(call, tupleListCalc);
    }

    static class CalcImpl extends AbstractListCalc {
        private final TupleListCalc tupleListCalc;

        public CalcImpl(ResolvedFunCall call, TupleListCalc tupleListCalc) {
            super(call.getType(), new Calc[]{tupleListCalc});
            this.tupleListCalc = tupleListCalc;
        }

        @Override
		public TupleList evaluateList(Evaluator evaluator) {
            TupleList list = tupleListCalc.evaluateList(evaluator);
            Set<List<Member>> set = new HashSet<>(list.size());
            TupleList result = list.copyList(list.size());
            for (List<Member> element : list) {
                if (set.add(element)) {
                    result.add(element);
                }
            }
            return result;
        }
    }
}
