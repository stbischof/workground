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
 * Definition of the <code>Covariance</code> and
 * <code>CovarianceN</code> MDX functions.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class CovarianceFunDef extends FunDefBase {
    static final ReflectiveMultiResolver CovarianceResolver =
        new ReflectiveMultiResolver(
            "Covariance",
            "Covariance(<Set>, <Numeric Expression>[, <Numeric Expression>])",
            "Returns the covariance of two series evaluated over a set (biased).",
            new String[]{"fnxn", "fnxnn"},
            CovarianceFunDef.class);

    static final MultiResolver CovarianceNResolver =
        new ReflectiveMultiResolver(
            "CovarianceN",
            "CovarianceN(<Set>, <Numeric Expression>[, <Numeric Expression>])",
            "Returns the covariance of two series evaluated over a set (unbiased).",
            new String[]{"fnxn", "fnxnn"},
            CovarianceFunDef.class);

    private final boolean biased;

    public CovarianceFunDef(FunctionDefinition dummyFunDef) {
        super(dummyFunDef);
        this.biased = dummyFunDef.getName().equals("Covariance");
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleListCalc tupleListCalc =
            compiler.compileList(call.getArg(0));
        final Calc calc1 =
            compiler.compileScalar(call.getArg(1), true);
        final Calc calc2 =
            call.getArgCount() > 2
            ? compiler.compileScalar(call.getArg(2), true)
            : new ValueCalc(call.getType());
        return new AbstractProfilingNestedDoubleCalc(call.getType(), new Calc[] {tupleListCalc, calc1, calc2})
        {
            @Override
			public Double evaluate(Evaluator evaluator) {
                TupleList memberList = tupleListCalc.evaluateList(evaluator);
                final int savepoint = evaluator.savepoint();
                try {
                    evaluator.setNonEmpty(false);
                    return (Double) FunUtil.covariance(
                            evaluator,
                            memberList,
                            calc1,
                            calc2,
                            biased);
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
