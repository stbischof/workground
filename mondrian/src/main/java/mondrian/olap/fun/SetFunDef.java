/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// Copyright (C) 2021-2022 Sergei Semenkov
// All Rights Reserved.
*/

package mondrian.olap.fun;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.MemberCalc;
import org.eclipse.daanse.olap.calc.api.TupleCalc;
import org.eclipse.daanse.olap.calc.api.VoidCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedVoidCalc;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleCursor;
import mondrian.calc.TupleIterable;
import mondrian.calc.TupleIteratorCalc;
import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.calc.impl.AbstractIterCalc;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.calc.impl.AbstractTupleCursor;
import mondrian.calc.impl.AbstractTupleIterable;
import mondrian.calc.impl.ListTupleList;
import mondrian.calc.impl.UnaryTupleList;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.ExpBase;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.ResultStyleException;
import mondrian.olap.Syntax;
import mondrian.olap.Validator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.SetType;
import mondrian.olap.type.Type;
import mondrian.olap.type.TypeUtil;
import mondrian.resource.MondrianResource;

/**
 * <code>SetFunDef</code> implements the 'set' function (whose syntax is the
 * brace operator, <code>{ ... }</code>).
 *
 * @author jhyde
 * @since 3 March, 2002
 */
public class SetFunDef extends FunDefBase {
    static final ResolverImpl Resolver = new ResolverImpl();

    SetFunDef(FunctionResolver resolver, int[] argTypes) {
        super(resolver, Category.SET, argTypes);
    }

    @Override
	public void unparse(Exp[] args, PrintWriter pw) {
        ExpBase.unparseList(pw, args, "{", ", ", "}");
    }

    @Override
	public Type getResultType(Validator validator, Exp[] args) {
        // All of the members in {<Member1>[,<MemberI>]...} must have the same
        // Hierarchy.  But if there are no members, we can't derive a
        // hierarchy.
        Type type0 = null;
        if (args.length == 0) {
            // No members to go on, so we can't guess the hierarchy.
            type0 = MemberType.Unknown;
        } else {
            for (int i = 0; i < args.length; i++) {
                Exp arg = args[i];
                Type type = arg.getType();
                type = TypeUtil.toMemberOrTupleType(type);
                if (i == 0) {
                    type0 = type;
                } else {
                    if (!TypeUtil.isUnionCompatible(type0, type)) {
                        throw MondrianResource.instance()
                            .ArgsMustHaveSameHierarchy.ex(getName());
                    }
                }
            }
        }
        return new SetType(type0);
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final Exp[] args = call.getArgs();
        if (args.length == 0) {
            // Special treatment for empty set, because we don't know whether it
            // is a set of members or tuples, and so we need it to implement
            // both MemberListCalc and TupleListCalc.
            return new EmptyListCalc(call);
        }
        if (args.length == 1
            && args[0].getType() instanceof SetType)
        {
            // Optimized case when there is only one argument. This occurs quite
            // often, because people write '{Foo.Children} on 1' when they could
            // write 'Foo.Children on 1'.
            return args[0].accept(compiler);
        }
        return new SetListCalc(
            call.getType(), args, compiler, ResultStyle.LIST_MUTABLELIST);
    }

    /**
     * Compiled expression to implement the MDX set function, <code>{ ...
     * }</code>, applied to a set of tuples, as a list.
     *
     * <p>The set function can contain expressions which yield sets together
     * with expressions which yield individual tuples, provided that
     * they all have the same type. It automatically removes null
     * or partially-null tuples from the list.
     *
     * <p>Also, does not process high-cardinality dimensions specially.
     */
    public static class SetListCalc extends AbstractListCalc {
        private TupleList result;
        private final VoidCalc[] voidCalcs;

        public SetListCalc(
            Type type,
            Exp[] args,
            ExpressionCompiler compiler,
            List<ResultStyle> resultStyles)
        {
            super(type, null);
            voidCalcs = compileSelf(args, compiler, resultStyles);
            result = TupleCollections.createList(getType().getArity());
        }

        @Override
		public Calc[] getChildCalcs() {
            return voidCalcs;
        }

        private VoidCalc[] compileSelf(
            Exp[] args,
            ExpressionCompiler compiler,
            List<ResultStyle> resultStyles)
        {
            VoidCalc[] voidCalcs = new VoidCalc[args.length];
            for (int i = 0; i < args.length; i++) {
                voidCalcs[i] = createCalc(args[i], compiler, resultStyles);
            }
            return voidCalcs;
        }

        private VoidCalc createCalc(
            Exp arg,
            ExpressionCompiler compiler,
            List<ResultStyle> resultStyles)
        {
            final Type type = arg.getType();
            if (type instanceof SetType) {
                // TODO use resultStyles
                final TupleListCalc tupleListCalc = compiler.compileList(arg);
                return new AbstractProfilingNestedVoidCalc(type, new Calc[] {tupleListCalc}) {
                	// name "Sublist..."
                    @Override
					public Void evaluate(Evaluator evaluator) {
                        TupleList list =
                            tupleListCalc.evaluateList(evaluator);
                        // Add only tuples which are not null. Tuples with
                        // any null members are considered null.
                        outer:
                        for (List<Member> members : list) {
                            for (Member member : members) {
                                if (member == null || member.isNull()) {
                                    continue outer;
                                }
                            }
                            result.add(members);
                        }
						return null;
                    }

                };
            } else if (type instanceof mondrian.olap.type.LevelType) {
                mondrian.mdx.UnresolvedFunCallImpl unresolvedFunCall = new mondrian.mdx.UnresolvedFunCallImpl(
                        "Members",
                        mondrian.olap.Syntax.Property,
                        new Exp[] {arg});
                final TupleListCalc tupleListCalc = compiler.compileList(unresolvedFunCall.accept(compiler.getValidator()));
                return new AbstractProfilingNestedVoidCalc(type, new Calc[] {tupleListCalc}) {
                    @Override
					public Void evaluate(Evaluator evaluator) {
                        TupleList list =
                                tupleListCalc.evaluateList(evaluator);
                        result = list;
						return null;
                    }
                };
            } else if (type.getArity() == 1 && arg instanceof MemberType) {
                final MemberCalc memberCalc = compiler.compileMember(arg);
                return new AbstractProfilingNestedVoidCalc(type, new Calc[]{memberCalc}) {
                    final Member[] members = {null};
                    @Override
					public Void evaluate(Evaluator evaluator) {
                        // Don't add null or partially null tuple to result.
                        Member member = memberCalc.evaluate(evaluator);
                        if (member == null || member.isNull()) {
                            return null;
                        }
                        members[0] = member;
                        result.addTuple(members);
						return null;
                    }
                };
            } else {
                final TupleCalc tupleCalc = compiler.compileTuple(arg);
                return new AbstractProfilingNestedVoidCalc(type, new Calc[]{tupleCalc}) {
                    @Override
					public Void evaluate(Evaluator evaluator) {
                        // Don't add null or partially null tuple to result.
                        Member[] members = tupleCalc.evaluate(evaluator);
                        if (members == null
                            || FunUtil.tupleContainsNullMember(members))
                        {
                            return null;
                        }
                        result.addTuple(members);
						return null;
                    }
                };
            }
        }

        @Override
		public TupleList evaluateList(final Evaluator evaluator) {
            result.clear();
            for (VoidCalc voidCalc : voidCalcs) {
                voidCalc.evaluate(evaluator);
            }
            return result.copyList(-1);
        }
    }

    private static List<Calc> compileSelf(
        Exp[] args,
        ExpressionCompiler compiler,
        List<ResultStyle> resultStyles)
    {
        List<Calc> calcs = new ArrayList<>(args.length);
        for (Exp arg : args) {
            calcs.add(SetFunDef.createCalc(arg, compiler, resultStyles));
        }
        return calcs;
    }

    private static TupleIteratorCalc createCalc(
        Exp arg,
        ExpressionCompiler compiler,
        List<ResultStyle> resultStyles)
    {
        final Type type = arg.getType();
        if (type instanceof SetType) {
            final Calc calc = compiler.compileAs(arg, null, resultStyles);
            switch (calc.getResultStyle()) {
            case ITERABLE:
                final TupleIteratorCalc tupleIteratorCalc = (TupleIteratorCalc) calc;
                return new AbstractIterCalc(type, new Calc[]{calc}) {
                	// name "Sublist..."
                    @Override
					public TupleIterable evaluateIterable(
                        Evaluator evaluator)
                    {
                        return tupleIteratorCalc.evaluateIterable(evaluator);
                    }

     
                };
            case LIST:
            case MUTABLE_LIST:
                final TupleListCalc tupleListCalc = (TupleListCalc) calc;
                return new AbstractIterCalc(type, new Calc[]{calc}) {
                	// name "Sublist..."
                    @Override
					public TupleIterable evaluateIterable(
                        Evaluator evaluator)
                    {
                        TupleList list = tupleListCalc.evaluateList(
                            evaluator);
                        TupleList result = list.copyList(list.size());
                        // Add only tuples which are not null. Tuples with
                        // any null members are considered null.
                        list:
                        for (List<Member> members : list) {
                            for (Member member : members) {
                                if (member == null || member.isNull()) {
                                    continue list;
                                }
                            }
                            result.add(members);
                        }
                        return result;
                    }

                };
            }
            throw ResultStyleException.generateBadType(
                ResultStyle.ITERABLE_LIST_MUTABLELIST,
                calc.getResultStyle());
        } else if (TypeUtil.couldBeMember(type)) {
            final MemberCalc memberCalc = compiler.compileMember(arg);
            final ResolvedFunCall call = SetFunDef.wrapAsSet(arg);
            return new AbstractIterCalc(type, new Calc[] {memberCalc}) {
            	// name "Sublist..."// name "Sublist..."
                @Override
				public TupleIterable evaluateIterable(
                    Evaluator evaluator)
                {
                    final Member member =
                        memberCalc.evaluate(evaluator);
                    return member == null
                        ? TupleCollections.createList(1)
                        : new UnaryTupleList(Collections.singletonList(member));
                }

 
            };
        } else {
            final TupleCalc tupleCalc = compiler.compileTuple(arg);
            final ResolvedFunCall call = SetFunDef.wrapAsSet(arg);
            return new AbstractIterCalc(call.getType(), new Calc[] {tupleCalc}) {
                @Override
				public TupleIterable evaluateIterable(
                    Evaluator evaluator)
                {
                    final Member[] members = tupleCalc.evaluate(evaluator);
                    return new ListTupleList(
                        tupleCalc.getType().getArity(),
                        Arrays.asList(members));
                }
            };
        }
    }

    /**
     * Creates a call to the set operator with a given collection of
     * expressions.
     *
     * <p>There must be at least one expression. Each expression may be a set of
     * members/tuples, or may be a member/tuple, but method assumes that
     * expressions have compatible types.
     *
     * @param args Expressions
     * @return Call to set operator
     */
    public static ResolvedFunCall wrapAsSet(Exp... args) {
        assert args.length > 0;
        final int[] categories = new int[args.length];
        Type type = null;
        for (int i = 0; i < args.length; i++) {
            final Exp arg = args[i];
            categories[i] = arg.getCategory();
            final Type argType = arg.getType();
            if (argType instanceof SetType) {
                type = ((SetType) argType).getElementType();
            } else {
                type = argType;
            }
        }
        return new ResolvedFunCallImpl(
            new SetFunDef(SetFunDef.Resolver, categories),
            args,
            new SetType(type));
    }

    /**
     * Compiled expression that evaluates one or more expressions, each of which
     * yields a tuple or a set of tuples, and returns the result as a tuple
     * iterator.
     */
    public static class ExprIterCalc extends AbstractIterCalc {
        private final TupleIteratorCalc[] tupleIteratorCalcs;

        public ExprIterCalc(
			Type type,
            Exp[] args,
            ExpressionCompiler compiler,
            List<ResultStyle> resultStyles)
        {
            super(type, null);
            final List<Calc> calcList =
                SetFunDef.compileSelf(args, compiler, resultStyles);
            tupleIteratorCalcs = calcList.toArray(new TupleIteratorCalc[calcList.size()]);
        }

        // override return type
        @Override
		public TupleIteratorCalc[] getChildCalcs() {
            return tupleIteratorCalcs;
        }

        @Override
		public TupleIterable evaluateIterable(
            final Evaluator evaluator)
        {
            return new AbstractTupleIterable(getType().getArity()) {
                @Override
				public TupleCursor tupleCursor() {
                    return new AbstractTupleCursor(arity) {
                        Iterator<TupleIteratorCalc> calcIterator =
                            Arrays.asList(tupleIteratorCalcs).iterator();
                        TupleCursor currentCursor =
                            TupleCollections.emptyList(1).tupleCursor();

                        @Override
						public boolean forward() {
                            while (true) {
                                if (currentCursor.forward()) {
                                    return true;
                                }
                                if (!calcIterator.hasNext()) {
                                    return false;
                                }
                                currentCursor =
                                    calcIterator.next()
                                        .evaluateIterable(evaluator)
                                        .tupleCursor();
                            }
                        }

                        @Override
						public List<Member> current() {
                            return currentCursor.current();
                        }

                        @Override
                        public void setContext(Evaluator evaluator) {
                            currentCursor.setContext(evaluator);
                        }

                        @Override
                        public void currentToArray(
                            Member[] members, int offset)
                        {
                            currentCursor.currentToArray(members, offset);
                        }

                        @Override
                        public Member member(int column) {
                            return currentCursor.member(column);
                        }
                    };
                }
            };
        }
    }

    private static class ResolverImpl extends ResolverBase {
        public ResolverImpl() {
            super(
                "{}",
                "{<Member> [, <Member>...]}",
                "Brace operator constructs a set.",
                Syntax.Braces);
        }

        @Override
		public FunctionDefinition resolve(
            Exp[] args,
            Validator validator,
            List<Conversion> conversions)
        {
            int[] parameterTypes = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                if (validator.canConvert(
                        i, args[i], Category.MEMBER, conversions))
                {
                    parameterTypes[i] = Category.MEMBER;
                    continue;
                }
                if (validator.canConvert(
                        i, args[i], Category.TUPLE, conversions))
                {
                    parameterTypes[i] = Category.TUPLE;
                    continue;
                }
                if (validator.canConvert(
                        i, args[i], Category.SET, conversions))
                {
                    parameterTypes[i] = Category.SET;
                    continue;
                }
                return null;
            }
            return new SetFunDef(this, parameterTypes);
        }
    }

    /**
     * Compiled expression that returns an empty list of members or tuples.
     */
    private static class EmptyListCalc extends AbstractListCalc {
        private final TupleList list;

        /**
         * Creates an EmptyListCalc.
         *
         * @param call Expression which was compiled
         */
        EmptyListCalc(ResolvedFunCall call) {
            super(call.getType(), new Calc[0]);

            list = TupleCollections.emptyList(call.getType().getArity());
        }

        @Override
		public TupleList evaluateList(Evaluator evaluator) {
            return list;
        }
    }
}
