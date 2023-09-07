/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package org.eclipse.daanse.function.definition.member.level;

import java.io.PrintWriter;

import org.eclipse.daanse.function.api.FunctionDefinition;
import org.eclipse.daanse.olap.api.model.Level;
import org.eclipse.daanse.olap.api.model.Member;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.MemberCalc;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedLevelCalc;

import mondrian.calc.ExpCompiler;
import mondrian.mdx.ResolvedFunCall;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Syntax;
import mondrian.olap.Validator;
import mondrian.olap.type.LevelType;
import mondrian.olap.type.Type;

/**
 * Definition of the <code>&lt;Member&gt;.Level</code> MDX builtin function.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
public class LevelOfMemberFunDef implements FunctionDefinition {
    static final LevelOfMemberFunDef instance = new LevelOfMemberFunDef();


    @Override
	public Type getResultType(Validator validator, Exp[] args) {
        final Type argType = args[0].getType();
        return LevelType.forType(argType);
    }

    @Override
	public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler) {
        final MemberCalc memberCalc =
                compiler.compileMember(call.getArg(0));
        return new LevelOfMemberCalc(call.getType(), memberCalc);
    }

	@Override
	public Syntax getSyntax() {
		return Syntax.Property;
	}

	@Override
	public String getName() {
		return "Level";
	}

	@Override
	public int getReturnCategory() {
		return Category.LEVEL;
	}

	@Override
	public int[] getParameterCategories() {
		return new int[] {Category.MEMBER};
	}

	@Override
	public String getDescription() {
		return "Returns a member's level.";
	}

	@Override
	public String getSignature() {
		return null;
	}

	@Override
	public void unparse(Exp[] args, PrintWriter pw) {
		
	}

	@Override
	public Exp createCall(org.eclipse.daanse.function.api.Validator validator, Exp[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
