/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// Copyright (C) 2021 Sergei Semenkov
// All Rights Reserved.
*/
package mondrian.olap.fun;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.StringCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.constant.ConstantStringCalc;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedStringCalc;
import org.eclipse.daanse.olap.calc.base.util.HirarchyDependsChecker;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleCursor;
import mondrian.calc.TupleIterable;
import mondrian.calc.TupleIteratorCalc;
import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.Validator;
import mondrian.olap.type.NumericType;
import mondrian.olap.type.SetType;
import mondrian.olap.type.StringType;
import mondrian.olap.type.Type;
import mondrian.olap.type.TypeUtil;
import mondrian.server.Execution;
import mondrian.server.Locus;
import mondrian.util.CancellationChecker;

/**
 * Definition of the <code>Generate</code> MDX function.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class GenerateFunDef extends FunDefBase {
    static final ReflectiveMultiResolver ListResolver =
        new ReflectiveMultiResolver(
            "Generate",
            "Generate(<Set1>, <Set2>[, ALL])",
            "Applies a set to each member of another set and joins the resulting sets by union.",
            new String[] {"fxxx", "fxxxy"},
            GenerateFunDef.class);

    static final ReflectiveMultiResolver StringResolver =
        new ReflectiveMultiResolver(
            "Generate",
            "Generate(<Set>, <String>[, <String>])",
            "Applies a set to a string expression and joins resulting sets by string concatenation.",
            new String[] {"fSxS", "fSxSS", "fSxnS"},
            GenerateFunDef.class);

    private static final String[] ReservedWords = new String[] {"ALL"};

    public GenerateFunDef(FunctionDefinition dummyFunDef) {
        super(dummyFunDef);
    }

    @Override
	public Type getResultType(Validator validator, Exp[] args) {
        final Type type = args[1].getType();
        if (type instanceof StringType || type instanceof NumericType) {
            // Generate(<Set>, <String>[, <String>])
            return new StringType();
        } else {
            final Type memberType = TypeUtil.toMemberOrTupleType(type);
            return new SetType(memberType);
        }
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleIteratorCalc tupleIteratorCalc = compiler.compileIter(call.getArg(0));
        if (call.getArg(1).getType() instanceof StringType
                || call.getArg(1).getType() instanceof NumericType) {
            final StringCalc stringCalc;
            if(call.getArg(1).getType() instanceof StringType) {
                stringCalc = compiler.compileString(call.getArg(1));
            } else {
                //NumericType
                mondrian.mdx.UnresolvedFunCallImpl unresolvedFunCall = new mondrian.mdx.UnresolvedFunCallImpl(
                        "str",
                        mondrian.olap.Syntax.Function,
                        new Exp[] {call.getArg(1)});
                stringCalc = compiler.compileString(unresolvedFunCall.accept(compiler.getValidator()));
            }
            final StringCalc delimCalc;
            if (call.getArgCount() == 3) {
                delimCalc = compiler.compileString(call.getArg(2));
            } else {
                delimCalc = new ConstantStringCalc(new StringType(), "");
            }

            return new GenerateStringCalcImpl(
                call, tupleIteratorCalc, stringCalc, delimCalc);
        } else {
            final TupleListCalc listCalc2 =
                compiler.compileList(call.getArg(1));
            final String literalArg = FunUtil.getLiteralArg(call, 2, "", GenerateFunDef.ReservedWords);
            final boolean all = literalArg.equalsIgnoreCase("ALL");
            final int arityOut = call.getType().getArity();
            return new GenerateListCalcImpl(
                call, tupleIteratorCalc, listCalc2, arityOut, all);
        }
    }

    private static class GenerateListCalcImpl extends AbstractListCalc {
        private final TupleIteratorCalc iterCalc1;
        private final TupleListCalc listCalc2;
        private final int arityOut;
        private final boolean all;

        public GenerateListCalcImpl(
        		ResolvedFunCall call,
            TupleIteratorCalc tupleIteratorCalc,
            TupleListCalc listCalc2,
            int arityOut,
            boolean all)
        {
            super(call.getType(), new Calc[]{tupleIteratorCalc, listCalc2});
            this.iterCalc1 = tupleIteratorCalc;
            this.listCalc2 = listCalc2;
            this.arityOut = arityOut;
            this.all = all;
        }

        @Override
		public TupleList evaluateList(Evaluator evaluator) {
            final int savepoint = evaluator.savepoint();
            try {
                evaluator.setNonEmpty(false);
                final TupleIterable iterable1 =
                        iterCalc1.evaluateIterable(evaluator);
                evaluator.restore(savepoint);
                TupleList result = TupleCollections.createList(arityOut);
                Execution execution = Locus.peek().execution;
                if (all) {
                    final TupleCursor cursor = iterable1.tupleCursor();
                    int rowCount = 0;
                    while (cursor.forward()) {
                        CancellationChecker.checkCancelOrTimeout(
                            rowCount++, execution);
                        cursor.setContext(evaluator);
                        final TupleList result2 =
                            listCalc2.evaluateList(evaluator);
                        result.addAll(result2);
                    }
                } else {
                    final Set<List<Member>> emitted =
                            new HashSet<>();
                    final TupleCursor cursor = iterable1.tupleCursor();

                    int rowCount = 0;
                    while (cursor.forward()) {
                        CancellationChecker.checkCancelOrTimeout(
                            rowCount++, execution);
                        cursor.setContext(evaluator);
                        final TupleList result2 =
                                listCalc2.evaluateList(evaluator);
                        GenerateListCalcImpl.addDistinctTuples(result, result2, emitted);
                    }
                }
                return result;
            } finally {
                evaluator.restore(savepoint);
            }
        }

        private static void addDistinctTuples(
            TupleList result,
            TupleList result2,
            Set<List<Member>> emitted)
        {
            for (List<Member> row : result2) {
                // wrap array for correct distinctness test
                if (emitted.add(row)) {
                    result.add(row);
                }
            }
        }

        @Override
		public boolean dependsOn(Hierarchy hierarchy) {
            return HirarchyDependsChecker.checkAnyDependsButFirst(getChildCalcs(), hierarchy);
        }
    }

    private static class GenerateStringCalcImpl extends AbstractProfilingNestedStringCalc {
        private final TupleIteratorCalc tupleIteratorCalc;
        private final StringCalc stringCalc;
        private final StringCalc sepCalc;

        public GenerateStringCalcImpl(
        	ResolvedFunCall call,
            TupleIteratorCalc tupleIteratorCalc,
            StringCalc stringCalc,
            StringCalc sepCalc)
        {
            super(call.getType(), new Calc[]{tupleIteratorCalc, stringCalc});
            this.tupleIteratorCalc = tupleIteratorCalc;
            this.stringCalc = stringCalc;
            this.sepCalc = sepCalc;
        }

        @Override
		public String evaluate(Evaluator evaluator) {
            final int savepoint = evaluator.savepoint();
            try {
                StringBuilder buf = new StringBuilder();
                int k = 0;
                final TupleIterable iter11 =
                    tupleIteratorCalc.evaluateIterable(evaluator);
                final TupleCursor cursor = iter11.tupleCursor();
                int currentIteration = 0;
                Execution execution =
                    evaluator.getQuery().getStatement().getCurrentExecution();
                while (cursor.forward()) {
                    CancellationChecker.checkCancelOrTimeout(
                        currentIteration++, execution);
                    cursor.setContext(evaluator);
                    if (k++ > 0) {
                        String sep = sepCalc.evaluate(evaluator);
                        buf.append(sep);
                    }
                    final String result2 =
                        stringCalc.evaluate(evaluator);
                    buf.append(result2);
                }
                return buf.toString();
            } finally {
                evaluator.restore(savepoint);
            }
        }

        @Override
		public boolean dependsOn(Hierarchy hierarchy) {
            return HirarchyDependsChecker.checkAnyDependsButFirst(getChildCalcs(), hierarchy);
        }
    }
}
