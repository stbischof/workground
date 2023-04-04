/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara.
 * Copyright (C) 2021 Sergei Semenkov
 * All rights reserved.
 */

package mondrian.calc.impl;

import java.util.List;

import org.eclipse.daanse.olap.api.model.Member;

import mondrian.calc.Calc;
import mondrian.calc.ListCalc;
import mondrian.calc.MemberCalc;
import mondrian.calc.ResultStyle;
import mondrian.calc.TupleCalc;
import mondrian.calc.TupleList;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.TupleType;
import mondrian.olap.type.Type;

/**
 * Enhanced expression compiler. It can generate code to convert between
 * scalar types.
 *
 * @author jhyde
 * @since Sep 29, 2005
 */
public class BetterExpCompiler extends AbstractExpCompiler {
    public BetterExpCompiler(Evaluator evaluator, Validator validator) {
        super(evaluator, validator);
    }

    public BetterExpCompiler(
            Evaluator evaluator,
            Validator validator,
            List<ResultStyle> resultStyles)
    {
        super(evaluator, validator, resultStyles);
    }

    @Override
    public TupleCalc compileTuple(Exp exp) {
        final Calc calc = compile(exp);
        final Type type = exp.getType();
        if (
                type instanceof mondrian.olap.type.DimensionType
                || type instanceof mondrian.olap.type.HierarchyType
                ) {
            final mondrian.mdx.UnresolvedFunCall unresolvedFunCall = new mondrian.mdx.UnresolvedFunCall(
                    "DefaultMember",
                    mondrian.olap.Syntax.Property,
                    new Exp[] {exp});
            final Exp defaultMember = unresolvedFunCall.accept(getValidator());
            return compileTuple(defaultMember);
        }
        if (type instanceof TupleType) {
            assert calc instanceof TupleCalc;
            return (TupleCalc) calc;
        } else if (type instanceof MemberType) {
            assert calc instanceof MemberCalc;
            final MemberCalc memberCalc = (MemberCalc) calc;
            return new AbstractTupleCalc("SomeNameToBeBetterEvaluated",type, new Calc[] {memberCalc}) {
                @Override
                public Member[] evaluateTuple(Evaluator evaluator) {
                    final Member member = memberCalc.evaluateMember(evaluator);
                    if(member == null) {
                        //<Tuple>.Item(-1)
                        return null;
                    }
                    else {
                        return new Member[]{memberCalc.evaluateMember(evaluator)};
                    }
                }
            };
        } else {
            throw Util.newInternal("cannot cast " + exp);
        }
    }

    @Override
    public ListCalc compileList(Exp exp, boolean mutable) {
        final ListCalc listCalc = super.compileList(exp, mutable);
        if (mutable && listCalc.getResultStyle() == ResultStyle.LIST) {
            // Wrap the expression in an expression which creates a mutable
            // copy.
            return new CopyListCalc(listCalc);
        }
        return listCalc;
    }

    private static class CopyListCalc extends AbstractListCalc {
        private final ListCalc listCalc;

        public CopyListCalc(ListCalc listCalc) {
			super("DummyExp", listCalc.getType(), new Calc[] { listCalc });
            this.listCalc = listCalc;
        }

        @Override
        public TupleList evaluateList(Evaluator evaluator) {
            final TupleList list = listCalc.evaluateList(evaluator);
            return list.cloneList(-1);
        }
    }
}
