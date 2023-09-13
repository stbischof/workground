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

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.FunctionDefinition;

/**
 * Definition of the <code>EXISTS</code> MDX function.
 *
 * @author kvu
 * @since Mar 23, 2008
 */
class ExistsFunDef extends FunDefBase
{
    static final FunctionResolver resolver =
        new ReflectiveMultiResolver(
            "Exists",
            "Exists(<Set1>, <Set2>])",
            "Returns the the set of tuples of the first set that exist with one or more tuples of the second set.",
            new String[] {"fxxx"},
            ExistsFunDef.class);

    public ExistsFunDef(FunctionDefinition dummyFunDef)
    {
        super(dummyFunDef);
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleListCalc listCalc1 = compiler.compileList(call.getArg(0));
        final TupleListCalc listCalc2 = compiler.compileList(call.getArg(1));

        return new AbstractListCalc(call.getType(), new Calc[] {listCalc1, listCalc2}) {
            @Override
			public TupleList evaluateList(Evaluator evaluator) {
                TupleList leftTuples = listCalc1.evaluateList(evaluator);
                if (leftTuples.isEmpty()) {
                    return TupleCollections.emptyList(leftTuples.getArity());
                }
                TupleList rightTuples = listCalc2.evaluateList(evaluator);
                if (rightTuples.isEmpty()) {
                    return TupleCollections.emptyList(leftTuples.getArity());
                }
                TupleList result =
                    TupleCollections.createList(leftTuples.getArity());

                List<Hierarchy> leftDims = ExistsFunDef.getHierarchies(leftTuples.get(0));
                List<Hierarchy> rightDims = ExistsFunDef.getHierarchies(rightTuples.get(0));

                leftLoop:
                for (List<Member> leftTuple : leftTuples) {
                    for (List<Member> rightTuple : rightTuples) {
                        if (FunUtil.existsInTuple(leftTuple, rightTuple,
                            leftDims, rightDims, null))
                        {
                            result.add(leftTuple);
                            continue leftLoop;
                        }
                    }
                }
                return result;
            }
        };
    }

    private static List<Hierarchy> getHierarchies(final List<Member> members)
    {
        List<Hierarchy> hierarchies = new ArrayList<>();
        for (Member member : members) {
            hierarchies.add(member.getHierarchy());
        }
        return hierarchies;
    }

}
