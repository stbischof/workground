/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.IntegerCalc;
import org.eclipse.daanse.olap.calc.api.StringCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedMemberCalc;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedTupleCalc;

import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.Syntax;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.SetType;
import mondrian.olap.type.StringType;
import mondrian.olap.type.TupleType;
import mondrian.olap.type.Type;

/**
 * Definition of the <code>&lt;Set&gt;.Item</code> MDX function.
 *
 * <p>Syntax:
 * <blockquote><code>
 * &lt;Set&gt;.Item(&lt;Index&gt;)<br/>
 * &lt;Set&gt;.Item(&lt;String Expression&gt; [, ...])
 * </code></blockquote>
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class SetItemFunDef extends FunDefBase {
    static final FunctionResolver intResolver =
        new ReflectiveMultiResolver(
            "Item",
            "<Set>.Item(<Index>)",
            "Returns a tuple from the set specified in <Set>. The tuple to be returned is specified by the zero-based position of the tuple in the set in <Index>.",
            new String[] {"mmxn"},
            SetItemFunDef.class);

    static final FunctionResolver stringResolver =
        new ResolverBase(
            "Item",
            "<Set>.Item(<String> [, ...])",
            "Returns a tuple from the set specified in <Set>. The tuple to be returned is specified by the member name (or names) in <String>.",
            Syntax.Method)
    {
        @Override
		public FunctionDefinition resolve(
            Exp[] args,
            Validator validator,
            List<Conversion> conversions)
        {
            if (args.length < 1) {
                return null;
            }
            final Exp setExp = args[0];
            if (!(setExp.getType() instanceof SetType)) {
                return null;
            }
            final SetType setType = (SetType) setExp.getType();
            final int arity = setType.getArity();
            // All args must be strings.
            for (int i = 1; i < args.length; i++) {
                if (!validator.canConvert(
                        i, args[i], Category.STRING, conversions))
                {
                    return null;
                }
            }
            if (args.length - 1 != arity) {
                throw Util.newError(
                    "Argument count does not match set's cardinality " + arity);
            }
            final int category = arity == 1 ? Category.MEMBER : Category.TUPLE;
            FunctionDefinition dummy = FunUtil.createDummyFunDef(this, category, args);
            return new SetItemFunDef(dummy);
        }
    };

    public SetItemFunDef(FunctionDefinition dummyFunDef) {
        super(dummyFunDef);
    }

    @Override
	public Type getResultType(Validator validator, Exp[] args) {
        SetType setType = (SetType) args[0].getType();
        return setType.getElementType();
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleListCalc tupleListCalc =
            compiler.compileList(call.getArg(0));
        final Type elementType =
            ((SetType) tupleListCalc.getType()).getElementType();
        final boolean isString =
            call.getArgCount() < 2
            || call.getArg(1).getType() instanceof StringType;
        final IntegerCalc indexCalc;
        final StringCalc[] stringCalcs;
        List<Calc> calcList = new ArrayList<>();
        calcList.add(tupleListCalc);
        if (isString) {
            indexCalc = null;
            stringCalcs = new StringCalc[call.getArgCount() - 1];
            for (int i = 0; i < stringCalcs.length; i++) {
                stringCalcs[i] = compiler.compileString(call.getArg(i + 1));
                calcList.add(stringCalcs[i]);
            }
        } else {
            stringCalcs = null;
            indexCalc = compiler.compileInteger(call.getArg(1));
            calcList.add(indexCalc);
        }
        Calc[] calcs = calcList.toArray(new Calc[calcList.size()]);
        if (elementType instanceof TupleType tupleType) {
            final Member[] nullTuple = FunUtil.makeNullTuple(tupleType);
            if (isString) {
                return new AbstractProfilingNestedTupleCalc(call.getType(), calcs) {
                    @Override
					public Member[] evaluate(Evaluator evaluator) {
                        final int savepoint = evaluator.savepoint();
                        final TupleList list;
                        try {
                            evaluator.setNonEmpty(false);
                            list = tupleListCalc.evaluateList(evaluator);
                            assert list != null;
                        } finally {
                            evaluator.restore(savepoint);
                        }
                        try {
                            String[] results = new String[stringCalcs.length];
                            for (int i = 0; i < stringCalcs.length; i++) {
                                results[i] =
                                    stringCalcs[i].evaluate(evaluator);
                            }
                            listLoop:
                            for (List<Member> members : list) {
                                for (int j = 0; j < results.length; j++) {
                                    String result = results[j];
                                    final Member member = members.get(j);
                                    if (!SetItemFunDef.matchMember(member, result)) {
                                        continue listLoop;
                                    }
                                }
                                // All members match. Return the current one.
                                return members.toArray(
                                    new Member[members.size()]);
                            }
                        } finally {
                            evaluator.restore(savepoint);
                        }
                        // We use 'null' to represent the null tuple. Don't
                        // know why.
                        return null;
                    }
                };
            } else {
                return new AbstractProfilingNestedTupleCalc(call.getType(), calcs) {
                    @Override
					public Member[] evaluate(Evaluator evaluator) {
                        final int savepoint = evaluator.savepoint();
                        final TupleList list;
                        try {
                            evaluator.setNonEmpty(false);
                            list =
                                tupleListCalc.evaluateList(evaluator);
                        } finally {
                            evaluator.restore(savepoint);
                        }
                        assert list != null;
                        try {
                            final Integer index =
                                indexCalc.evaluate(evaluator);
                            int listSize = list.size();
                            if (index >= listSize || index < 0) {
                                return nullTuple;
                            } else {
                                final List<Member> members =
                                    list.get(index);
                                return members.toArray(
                                    new Member[members.size()]);
                            }
                        } finally {
                            evaluator.restore(savepoint);
                        }
                    }
                };
            }
        } else {
            final MemberType memberType = (MemberType) elementType;
            final Member nullMember = FunUtil.makeNullMember(memberType);
            if (isString) {
                return new AbstractProfilingNestedMemberCalc(call.getType(), calcs) {
                    @Override
					public Member evaluate(Evaluator evaluator) {
                        final int savepoint = evaluator.savepoint();
                        final List<Member> list;
                        try {
                            evaluator.setNonEmpty(false);
                            list =
                                tupleListCalc.evaluateList(evaluator).slice(0);
                            assert list != null;
                        } finally {
                            evaluator.restore(savepoint);
                        }
                        try {
                            final String result =
                                stringCalcs[0].evaluate(evaluator);
                            for (Member member : list) {
                                if (SetItemFunDef.matchMember(member, result)) {
                                    return member;
                                }
                            }
                            return nullMember;
                        } finally {
                            evaluator.restore(savepoint);
                        }
                    }
                };
            } else {
                return new AbstractProfilingNestedMemberCalc(call.getType(), calcs) {
                    @Override
					public Member evaluate(Evaluator evaluator) {
                        final int savepoint = evaluator.savepoint();
                        final List<Member> list;
                        try {
                            evaluator.setNonEmpty(false);
                            list =
                                tupleListCalc.evaluateList(evaluator).slice(0);
                            assert list != null;
                        } finally {
                            evaluator.restore(savepoint);
                        }
                        try {
                            final Integer index =
                                indexCalc.evaluate(evaluator);
                            int listSize = list.size();
                            if (index >= listSize || index < 0) {
                                return nullMember;
                            } else {
                                return list.get(index);
                            }
                        } finally {
                            evaluator.restore(savepoint);
                        }
                    }
                };
            }
        }
    }

    private static boolean matchMember(final Member member, String name) {
        return member.getName().equals(name);
    }
}
