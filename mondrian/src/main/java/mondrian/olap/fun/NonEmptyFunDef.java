/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2021 Sergei Semenkov
// All Rights Reserved.
*/
package mondrian.olap.fun;

import java.util.List;

import org.eclipse.daanse.olap.api.model.Hierarchy;
import org.eclipse.daanse.olap.api.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.calc.Calc;
import mondrian.calc.ExpCompiler;
import mondrian.calc.ListCalc;
import mondrian.calc.TupleCollections;
import mondrian.calc.TupleList;
import mondrian.calc.impl.AbstractCalc;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.mdx.ResolvedFunCall;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.FunDef;
import mondrian.olap.Validator;
import mondrian.olap.type.Type;

class NonEmptyFunDef extends FunDefBase {
    private static final Logger LOGGER = LoggerFactory.getLogger( NonEmptyFunDef.class );
    static final ReflectiveMultiResolver Resolver =
            new ReflectiveMultiResolver(
                    "NonEmpty",
                    "NonEmpty(<Set1>[, <Set2>])",
                    "Returns the set of tuples that are not empty from a specified set, based on the cross product " +
                            "of the specified set with a second set.",
                    new String[] {"fxx", "fxxx"},
                    NonEmptyFunDef.class);

    public NonEmptyFunDef(FunDef dummyFunDef) {
        super(dummyFunDef);
    }

    public Type getResultType(Validator validator, Exp[] args) {
        return args[0].getType();
    }

    public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler) {
        final ListCalc listCalc1 = compiler.compileList(call.getArg(0));
        ListCalc listCalc2 = null;
        if(call.getArgCount() == 2) {
            listCalc2 = compiler.compileList(call.getArg(1));
        }

        return new NonEmptyListCalcImpl(call, listCalc1, listCalc2);
    }

    private static class NonEmptyListCalcImpl extends AbstractListCalc {
        private final ListCalc listCalc1;
        private final ListCalc listCalc2;

        public NonEmptyListCalcImpl(
                ResolvedFunCall call,
                ListCalc listCalc1,
                ListCalc listCalc2)
        {
            super(call.getFunName(),call.getType(), new Calc[]{listCalc1, listCalc2});
            this.listCalc1 = listCalc1;
            this.listCalc2 = listCalc2;
        }

        public TupleList evaluateList(Evaluator evaluator) {
            final int savepoint = evaluator.savepoint();
            try {
                evaluator.setNonEmpty(true);

                TupleList leftTuples = listCalc1.evaluateList(evaluator);
                if (leftTuples.isEmpty()) {
                    return TupleCollections.emptyList(leftTuples.getArity());
                }
                TupleList rightTuples = null;
                if(this.listCalc2 != null) {
                    rightTuples = listCalc2.evaluateList(evaluator);
                }
                TupleList result =
                        TupleCollections.createList(leftTuples.getArity());


                for (List<Member> leftTuple : leftTuples) {
                    if(rightTuples != null && !rightTuples.isEmpty()) {
                        for (List<Member> rightTuple : rightTuples) {
                            evaluator.setContext(leftTuple);
                            evaluator.setContext(rightTuple);
                            Object tupleResult = evaluator.evaluateCurrent();
                            if (tupleResult != null)
                            {
                                result.add(leftTuple);
                                break;
                            }
                        }
                    }
                    else {
                        evaluator.setContext(leftTuple);
                        Object tupleResult = evaluator.evaluateCurrent();
                        if (tupleResult != null)
                        {
                            result.add(leftTuple);
                        }
                    }
                }
                return result;
            } finally {
                evaluator.restore(savepoint);
            }
        }

        public boolean dependsOn(Hierarchy hierarchy) {
            return AbstractCalc.anyDependsButFirst(getCalcs(), hierarchy);
        }
    }
}

