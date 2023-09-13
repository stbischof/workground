/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2014-2017 Hitachi Vantara
// All rights reserved.
*/
package mondrian.olap.fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleIterable;
import mondrian.calc.TupleIteratorCalc;
import mondrian.calc.TupleList;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Validator;
import mondrian.olap.type.Type;

/**
 * Existing keyword limits a set to what exists within the current context, ie
 * as if context members of the same dimension as the set were in the slicer.
 */
public class ExistingFunDef extends FunDefBase {

    static final ExistingFunDef instance = new ExistingFunDef();

    protected ExistingFunDef() {
      super(
          "Existing",
          "Existing <Set>",
          "Forces the set to be evaluated within the current context.",
          "Pxx");
    }

    @Override
	public Type getResultType(Validator validator, Exp[] args) {
        return args[0].getType();
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final TupleIteratorCalc setArg = compiler.compileIter(call.getArg(0));
        final Type myType = call.getArg(0).getType();

        return new AbstractListCalc(call.getType(), new Calc[] {setArg}) {
            @Override
			public boolean dependsOn(Hierarchy hierarchy) {
                return myType.usesHierarchy(hierarchy, false);
            }

            @Override
			public TupleList evaluateList(Evaluator evaluator) {
                TupleIterable setTuples = setArg.evaluateIterable(evaluator);

                TupleList result =
                    TupleCollections.createList(setTuples.getArity());
                List<Member> contextMembers =
                    Arrays.asList(evaluator.getMembers());

                List<Hierarchy> argDims = null;
                List<Hierarchy> contextDims = ExistingFunDef.getHierarchies(contextMembers);

                for (List<Member> tuple : setTuples) {
                    if (argDims == null) {
                        argDims = ExistingFunDef.getHierarchies(tuple);
                    }
                    if (FunUtil.existsInTuple(tuple, contextMembers,
                        argDims, contextDims, evaluator))
                    {
                        result.add(tuple);
                    }
                }
                return result;
            }
        };
    }

    private static List<Hierarchy> getHierarchies(final List<Member> members) {
        List<Hierarchy> hierarchies = new ArrayList<>(members.size());
        for (Member member : members) {
            hierarchies.add(member.getHierarchy());
        }
        return hierarchies;
    }

}