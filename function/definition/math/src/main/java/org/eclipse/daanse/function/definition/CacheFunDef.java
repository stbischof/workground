/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package org.eclipse.daanse.function.definition;

import mondrian.calc.ExpCompiler;
import mondrian.calc.ResultStyle;
import mondrian.calc.impl.GenericCalc;
import mondrian.calc.impl.GenericIterCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.ExpCacheDescriptor;
import mondrian.olap.Syntax;
import mondrian.olap.type.SetType;
import mondrian.olap.type.Type;
import org.eclipse.daanse.function.FunDefBase;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eigenbase.xom.XOMUtil;

import java.io.PrintWriter;

/**
 * Definition of the <code>Cache</code> system function, which is smart enough
 * to evaluate its argument only once.
 *
 * @author jhyde
 * @since 2005/8/14
 */
public class CacheFunDef extends FunDefBase {

    public CacheFunDef(
        String name,
        String signature,
        String description,
        Syntax syntax,
        int category,
        Type type)
    {
        super(
            name, signature, description, syntax,
            category, new int[] {category});
        XOMUtil.discard(type);
    }

    @Override
	public void unparse(Exp[] args, PrintWriter pw, boolean caseSensitive) {
        args[0].unparse(pw, caseSensitive);
    }

    @Override
	public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler, boolean caseSensitive) {
        final Exp exp = call.getArg(0);
        final ExpCacheDescriptor cacheDescriptor =
                new ExpCacheDescriptor(exp, compiler);
        if (call.getType() instanceof SetType) {
            return new GenericIterCalc(call.getType()) {
                @Override
				public Object evaluate(Evaluator evaluator, boolean caseSensitive) {
                    return evaluator.getCachedResult(cacheDescriptor);
                }

                @Override
				public Calc[] getChildCalcs() {
                    return new Calc[] {cacheDescriptor.getCalc()};
                }

                @Override
				public ResultStyle getResultStyle() {
                    // cached lists are immutable
                    return ResultStyle.LIST;
                }
            };
        } else {
            return new GenericCalc(call.getType()) {
                @Override
				public Object evaluate(Evaluator evaluator, boolean caseSensitive) {
                    return evaluator.getCachedResult(cacheDescriptor);
                }

                @Override
				public Calc[] getChildCalcs() {
                    return new Calc[] {cacheDescriptor.getCalc()};
                }

                @Override
				public ResultStyle getResultStyle() {
                    return ResultStyle.VALUE;
                }
            };
        }
    }
}
