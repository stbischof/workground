/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedDoubleCalc;
import org.eclipse.daanse.olap.calc.base.util.HirarchyDependsChecker;

import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.calc.impl.ValueCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.FunctionDefinition;

/**
 * Definition of the <code>Var</code> MDX builtin function
 * (and its synonym <code>Variance</code>).
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class VarFunDef extends AbstractAggregateFunDef {
    static final FunctionResolver VarResolver =
        new ReflectiveMultiResolver(
            "Var",
            "Var(<Set>[, <Numeric Expression>])",
            "Returns the variance of a numeric expression evaluated over a set (unbiased).",
            new String[]{"fnx", "fnxn"},
            VarFunDef.class);

    static final FunctionResolver VarianceResolver =
        new ReflectiveMultiResolver(
            "Variance",
            "Variance(<Set>[, <Numeric Expression>])",
            "Alias for Var.",
            new String[]{"fnx", "fnxn"},
            VarFunDef.class);

    public VarFunDef(FunctionDefinition dummyFunDef) {
        super(dummyFunDef);
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleListCalc tupleListCalc =
            compiler.compileList(call.getArg(0));
        final Calc calc =
            call.getArgCount() > 1
            ? compiler.compileScalar(call.getArg(1), true)
            : new ValueCalc(call.getType());
        return new AbstractProfilingNestedDoubleCalc(call.getType(), new Calc[] {tupleListCalc, calc}) {
            @Override
			public Double evaluate(Evaluator evaluator) {
                final int savepoint = evaluator.savepoint();
                try {
                    evaluator.setNonEmpty(false);
                    TupleList list = AbstractAggregateFunDef.evaluateCurrentList(tupleListCalc, evaluator);
                    return (Double) FunUtil.var(
                        evaluator, list, calc, false);
                } finally {
                    evaluator.restore(savepoint);
                }
            }

            @Override
			public boolean dependsOn(Hierarchy hierarchy) {
                return HirarchyDependsChecker.checkAnyDependsButFirst(getChildCalcs(), hierarchy);
            }
        };
    }
}
