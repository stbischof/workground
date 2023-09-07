/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package org.eclipse.daanse.function.core;

import java.io.PrintWriter;

import org.eclipse.daanse.function.api.FunctionDefinition;
import org.eclipse.daanse.function.api.Validator;

import mondrian.mdx.ResolvedFunCall;
import mondrian.olap.Category;
import mondrian.olap.Exp;
import mondrian.olap.Syntax;
import mondrian.olap.Util;
import mondrian.olap.type.Type;

public abstract class AbstractFunctionDefinition implements FunctionDefinition {

	@Override
	public Exp createCall(Validator validator, Exp[] args) {
		int[] categories = getParameterCategories();
		Util.assertTrue(categories.length == args.length);
		for (int i = 0; i < args.length; i++) {
			args[i] = validateArg(validator, args, i, categories[i]);
		}
		final Type type = getResultType(validator, args);
		if (type == null) {
			throw Util.newInternal("could not derive type");
		}
		return new ResolvedFunCall(this, args, type);
	}

	/**
	 * Validates an argument to a call to this function.
	 *
	 * <p>
	 * The default implementation of this method adds an implicit conversion to the
	 * correct type. Derived classes may override.
	 *
	 * @param validator Validator
	 * @param args      Arguments to this function
	 * @param i         Ordinal of argument
	 * @param category  Expected {@link Category category} of argument
	 * @return Validated argument
	 */
	protected Exp validateArg(Validator validator, Exp[] args, int i, int category) {
		return args[i];
	}

	/**
	 * Returns the type of a call to this function with a given set of arguments.
	 * <p/>
	 *
	 * The default implementation makes the coarse assumption that the return type
	 * is in some way related to the type of the first argument. Operators whose
	 * arguments don't follow the requirements of this implementation should
	 * override this method.
	 * <p/>
	 *
	 * If the function definition says it returns a literal type (numeric, string,
	 * symbol) then it's a fair guess that the function call returns the same kind
	 * of value.
	 * <p/>
	 *
	 * If the function definition says it returns an object type (cube, dimension,
	 * hierarchy, level, member) then we check the first argument of the function.
	 * Suppose that the function definition says that it returns a hierarchy, and
	 * the first argument of the function happens to be a member. Then it's
	 * reasonable to assume that this function returns a member.
	 *
	 * @param validator Validator
	 * @param args      Arguments to the call to this operator
	 * @return result type of a call this function
	 */
	public abstract Type getResultType(Validator validator, Exp[] args);

	@Override
	public String getSignature() {
		return getSyntax().getSignature(getName(), getReturnCategory(), getParameterCategories());
	}

	@Override
	public void unparse(Exp[] args, PrintWriter pw) {
		getSyntax().unparse(getName(), args, pw);
	}

}
