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

import java.io.PrintWriter;
import java.util.List;

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.MemberCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedTupleCalc;

import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.ExpBase;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.Syntax;
import mondrian.olap.Validator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.TupleType;
import mondrian.olap.type.Type;
import mondrian.olap.type.TypeUtil;

/**
 * <code>TupleFunDef</code> implements the '(...)' operator which builds
 * tuples, as in <code>([Time].CurrentMember,
 * [Stores].[USA].[California])</code>.
 *
 * @author jhyde
 * @since 3 March, 2002
 */
public class TupleFunDef extends FunDefBase {
    private final int[] argTypes;
    static final ResolverImpl Resolver = new ResolverImpl();

    private TupleFunDef(int[] argTypes) {
        super(
            "()",
            "(<Member> [, <Member>]...)",
            "Parenthesis operator constructs a tuple.  If there is only one member, the expression is equivalent to the member expression.",
            Syntax.Parentheses,
            Category.TUPLE,
            argTypes);
        this.argTypes = argTypes;
    }

    @Override
	public int getReturnCategory() {
        return Category.TUPLE;
    }

    @Override
	public int[] getParameterCategories() {
        return argTypes;
    }

    @Override
	public void unparse(Exp[] args, PrintWriter pw) {
        ExpBase.unparseList(pw, args, "(", ", ", ")");
    }

    @Override
	public Type getResultType(Validator validator, Exp[] args) {
        // _Tuple(<Member1>[,<MemberI>]...), which is written
        // (<Member1>[,<MemberI>]...), has type [Hie1] x ... x [HieN].
        //
        // If there is only one member, it merely represents a parenthesized
        // expression, whose Hierarchy is that of the member.
        if (args.length == 1  && !(args[0].getType() instanceof MemberType)) {
            return args[0].getType();
        } else {
            MemberType[] types = new MemberType[args.length];
            for (int i = 0; i < args.length; i++) {
                Exp arg = args[i];
                types[i] = TypeUtil.toMemberType(arg.getType());
            }
            TupleType.checkHierarchies(types);
            return new TupleType(types);
        }
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final Exp[] args = call.getArgs();
        final MemberCalc[] memberCalcs = new MemberCalc[args.length];
        for (int i = 0; i < args.length; i++) {
            memberCalcs[i] = compiler.compileMember(args[i]);
        }
        return new CurrentMemberCalc(call, memberCalcs);
    }

    public static class CurrentMemberCalc extends AbstractProfilingNestedTupleCalc {
        private final MemberCalc[] memberCalcs;

        public CurrentMemberCalc(ResolvedFunCall call, MemberCalc[] memberCalcs) {
            super(call.getType(), memberCalcs);
            this.memberCalcs = memberCalcs;
        }

        @Override
		public Member[] evaluate(Evaluator evaluator) {
            final Member[] members = new Member[memberCalcs.length];
            for (int i = 0; i < members.length; i++) {
                final Member member =
                    members[i] =
                    memberCalcs[i].evaluate(evaluator);
                if (member == null || member.isNull()) {
                    return null;
                }
            }
            return members;
        }

        public MemberCalc[] getMemberCalcs() {
            return memberCalcs;
        }
    }

    private static class ResolverImpl extends ResolverBase {
        public ResolverImpl() {
            super("()", null, null, Syntax.Parentheses);
        }

        @Override
		public FunctionDefinition resolve(
            Exp[] args,
            Validator validator,
            List<Conversion> conversions)
        {
            // Compare with TupleFunDef.getReturnCategory().  For example,
            //   ([Gender].members) is a set,
            //   ([Gender].[M]) is a member,
            //   (1 + 2) is a numeric,
            // but
            //   ([Gender].[M], [Marital Status].[S]) is a tuple.
            if (args.length == 1 && !(args[0].getType() instanceof MemberType)) {
                return new ParenthesesFunDef(args[0].getCategory());
            } else {
                final int[] argTypes = new int[args.length];
                boolean hasSet = false;
                for (int i = 0; i < args.length; i++) {
                    // Arg must be a member:
                    //  OK: ([Gender].[S], [Time].[1997])   (member, member)
                    //  OK: ([Gender], [Time])           (dimension, dimension)
                    // Not OK:
                    //  ([Gender].[S], [Store].[Store City]) (member, level)
                    if (validator.canConvert(
                            i, args[i], Category.MEMBER, conversions)) {
                        argTypes[i] = Category.MEMBER;
                    } else if(validator.canConvert(
                            i, args[i], Category.SET, conversions)){
                        hasSet = true;
                        argTypes[i] = Category.SET;
                    }
                    else {
                        return null;
                    }
                }
                if(hasSet){
                    FunctionDefinition dummy = FunUtil.createDummyFunDef(this, Category.SET, args);
                    return new CrossJoinFunDef(dummy);
                }
                else {
                    return new TupleFunDef(argTypes);
                }
            }
        }
    }
}
