/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.LevelCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.TupleList;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.olap.Evaluator;

/**
 * Definition of the <code>&lt;Level&gt;.Members</code> MDX function.
 *
 * @author jhyde
 * @since Jan 17, 2009
 */
public class LevelMembersFunDef extends FunDefBase {
    public static final LevelMembersFunDef INSTANCE = new LevelMembersFunDef();

    private LevelMembersFunDef() {
        super("Members", "Returns the set of members in a level.", "pxl");
    }

    @Override
	public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler) {
        final LevelCalc levelCalc =
            compiler.compileLevel(call.getArg(0));
        return new AbstractListCalc(call.getType(), new Calc[] {levelCalc}) {
            @Override
			public TupleList evaluateList(Evaluator evaluator) {
                Level level = levelCalc.evaluate(evaluator);
                return FunUtil.levelMembers(level, evaluator, false);
            }
        };
    }
}
