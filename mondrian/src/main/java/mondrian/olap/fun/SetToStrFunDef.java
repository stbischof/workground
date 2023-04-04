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

import org.eclipse.daanse.olap.api.model.Member;

import mondrian.calc.Calc;
import mondrian.calc.ExpCompiler;
import mondrian.calc.ListCalc;
import mondrian.calc.TupleCursor;
import mondrian.calc.TupleList;
import mondrian.calc.impl.AbstractStringCalc;
import mondrian.mdx.ResolvedFunCall;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;

/**
 * Definition of the <code>SetToStr</code> MDX function.
 *
 * @author jhyde
 * @since Aug 3, 2006
 */
class SetToStrFunDef extends FunDefBase {
    public static final FunDefBase instance = new SetToStrFunDef();

    private SetToStrFunDef() {
        super("SetToStr", "Constructs a string from a set.", "fSx");
    }

    public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler) {
        Exp arg = call.getArg(0);
        final ListCalc listCalc = compiler.compileList(arg);
        return new AbstractStringCalc(call.getFunName(),call.getType(), new Calc[]{listCalc}) {
            public String evaluateString(Evaluator evaluator) {
                final TupleList list = listCalc.evaluateList(evaluator);
                if (list.getArity() == 1) {
                    return SetToStrFunDef.memberSetToStr(list.slice(0));
                } else {
                    return SetToStrFunDef.tupleSetToStr(list);
                }
            }
        };
    }

    static String memberSetToStr(List<Member> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        int k = 0;
        for (Member member : list) {
            if (k++ > 0) {
                buf.append(", ");
            }
            buf.append(member.getUniqueName());
        }
        buf.append("}");
        return buf.toString();
    }

    static String tupleSetToStr(TupleList list) {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        int k = 0;
        Member[] members = new Member[list.getArity()];
        final TupleCursor cursor = list.tupleCursor();
        while (cursor.forward()) {
            if (k++ > 0) {
                buf.append(", ");
            }
            cursor.currentToArray(members, 0);
            FunUtil.appendTuple(buf, members);
        }
        buf.append("}");
        return buf.toString();
    }
}
