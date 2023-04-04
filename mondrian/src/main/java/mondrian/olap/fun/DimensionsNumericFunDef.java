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

import org.eclipse.daanse.olap.api.model.Hierarchy;

import mondrian.calc.Calc;
import mondrian.calc.ExpCompiler;
import mondrian.calc.IntegerCalc;
import mondrian.calc.impl.AbstractHierarchyCalc;
import mondrian.mdx.ResolvedFunCall;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Validator;
import mondrian.olap.type.HierarchyType;
import mondrian.olap.type.Type;
import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapHierarchy;

/**
 * Definition of the <code>Dimensions(&lt;Numeric Expression&gt;)</code>
 * MDX builtin function.
 *
 * <p>NOTE: Actually returns a hierarchy. This is consistent with Analysis
 * Services.
 *
 * @author jhyde
 * @since Jul 20, 2009
 */
class DimensionsNumericFunDef extends FunDefBase {
    public static final FunDefBase INSTANCE = new DimensionsNumericFunDef();
    public static final String DIMENSIONS_NUMERIC_FUN_DESCRIPTION = "Returns the hierarchy whose zero-based position within the cube is specified by a numeric expression.";

    private DimensionsNumericFunDef() {
        super(
            "Dimensions",
            DIMENSIONS_NUMERIC_FUN_DESCRIPTION,
            "fhn");
    }

    public Type getResultType(Validator validator, Exp[] args) {
        return HierarchyType.Unknown;
    }

    public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler)
    {
        final IntegerCalc integerCalc =
            compiler.compileInteger(call.getArg(0));
        return new AbstractHierarchyCalc(call.getFunName(),call.getType(), new Calc[] {integerCalc})
        {
            public Hierarchy evaluateHierarchy(Evaluator evaluator) {
                int n = integerCalc.evaluateInteger(evaluator);
                return nthHierarchy(evaluator, n);
            }
        };
    }

    RolapHierarchy nthHierarchy(Evaluator evaluator, int n) {
        RolapCube cube = (RolapCube) evaluator.getCube();
        List<RolapHierarchy> hierarchies = cube.getHierarchies();
        if (n >= hierarchies.size() || n < 0) {
            throw FunUtil.newEvalException(
                this, new StringBuilder("Index '").append(n).append("' out of bounds").toString());
        }
        return hierarchies.get(n);
    }
}
