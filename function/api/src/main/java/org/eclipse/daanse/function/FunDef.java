/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 1999-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package org.eclipse.daanse.function;

import mondrian.olap.Exp;
import mondrian.olap.Syntax;

import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import java.io.PrintWriter;

/**
 * Definition of an MDX function..
 *
 * @author jhyde, 21 April, 1999
 */
public interface FunDef {

    Syntax getSyntax();

    String getName();

    int getReturnCategory();

    int[] getParameterCategories();

    String getDescription();

    String getSignature();

    /**
     * Converts a call to this function into executable objects.
     *
     * <p>The result must implement the appropriate interface for the result
     * type. For example, a function which returns an integer must return
     * an object which implements {@link org.eclipse.daanse.olap.calc.api.IntegerCalc}.
     */
    Calc compileCall(ResolvedFunCall call, ExpressionCompiler compiler);

    void unparse(Exp[] args, PrintWriter pw);
}
