/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import java.util.List;

import org.eclipse.daanse.olap.api.query.component.NamedSetExpression;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.TupleIterable;
import mondrian.calc.impl.AbstractIterCalc;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.QueryImpl;
import mondrian.olap.Syntax;
import mondrian.olap.Validator;


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
    public static final FunctionResolver RESOLVER = new ResolverImpl();
    private final QueryImpl.ScopedNamedSet scopedNamedSet;

    /**
     * Creates an AsFunDef.
     *
     * @param scopedNamedSet Named set definition
     */
    private AsFunDef(QueryImpl.ScopedNamedSet scopedNamedSet) {
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

    private static class ResolverImpl extends ResolverBase {
        public ResolverImpl() {
            super("AS", null, null, Syntax.Infix);
        }

        @Override
		public FunctionDefinition resolve(
            Exp[] args,
            Validator validator,
            List<Conversion> conversions)
        {
            if (!validator.canConvert(
                    0, args[0], Category.SET, conversions))
            {
                return null;
            }

            // By the time resolve is called, the id argument has already been
            // resolved... to a named set, namely itself. That's not pretty.
            // We'd rather it stayed as an id, and we'd rather that a named set
            // was not visible in the scope that defines it. But we can work
            // with this.

            final QueryImpl.ScopedNamedSet scopedNamedSet =
                (QueryImpl.ScopedNamedSet) ((NamedSetExpression) args[1]).getNamedSet();
            return new AsFunDef(scopedNamedSet);
        }
    }
}
