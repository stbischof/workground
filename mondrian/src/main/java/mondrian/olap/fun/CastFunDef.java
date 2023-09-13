/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import java.util.Date;
import java.util.List;

import org.eclipse.daanse.olap.api.query.component.Literal;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.impl.GenericCalc;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.Syntax;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.type.Type;
import mondrian.olap.type.TypeUtil;
import mondrian.resource.MondrianResource;

/**
 * Definition of the <code>CAST</code> MDX operator.
 *
 * <p><code>CAST</code> is a mondrian-specific extension to MDX, because the MDX
 * standard does not define how values are to be converted from one
 * type to another. Microsoft Analysis Services, for Resolver, uses the Visual
 * Basic functions <code>CStr</code>, <code>CInt</code>, etc.
 * The syntax for this operator was inspired by the <code>CAST</code> operator
 * in the SQL standard.
 *
 * <p>Examples:<ul>
 * <li><code>CAST(1 + 2 AS STRING)</code></li>
 * <li><code>CAST('12.' || '56' AS NUMERIC)</code></li>
 * <li><code>CAST('tr' || 'ue' AS BOOLEAN)</code></li>
 * </ul>
 *
 * @author jhyde
 * @since Sep 3, 2006
 */
public class CastFunDef extends FunDefBase {
    static final ResolverBase Resolver = new ResolverImpl();

    private CastFunDef(FunctionDefinition dummyFunDef) {
        super(dummyFunDef);
    }

    @Override
	public Calc compileCall(ResolvedFunCall call, ExpressionCompiler compiler) {
        final Type targetType = call.getType();
        final Exp arg = call.getArg(0);
        final Calc calc = compiler.compileScalar(arg, false);
        return new CastCalcImpl(arg, calc, targetType);
    }

    private static RuntimeException cannotConvert(
        Object o,
        final Type targetType)
    {
        return Util.newInternal(
            new StringBuilder("cannot convert value '").append(o)
            .append("' to targetType '").append(targetType)
            .append("'").toString());
    }

    public static Integer toInt(
        Object o,
        final Type targetType)
    {
        if (o == null) {
            return null;
        }
        if (o instanceof String str) {
            return Integer.parseInt(str);
        }
        if (o instanceof Number number) {
            return number.intValue();
        }
        throw CastFunDef.cannotConvert(o, targetType);
    }

    private static double toDouble(Object o, final Type targetType) {
        if (o == null) {
            return FunUtil.DOUBLE_NULL;
        }
		if (o instanceof Boolean bool) {
			if (Boolean.TRUE.equals(bool)) {
				return 1;
			} else {
				return 0;
			}
		}
        if (o instanceof String str) {
            return Double.valueOf(str);
        }
        if (o instanceof Number number) {
            return number.doubleValue();
        }
        throw CastFunDef.cannotConvert(o, targetType);
    }

    public static boolean toBoolean(Object o, final Type targetType) {
        if (o == null) {
            return FunUtil.BOOLEAN_NULL;
        }
        if (o instanceof Boolean bool) {
            return bool;
        }
        if (o instanceof String str) {
            return Boolean.valueOf(str);
        }
        if (o instanceof Number number) {
            return number.doubleValue() > 0;
        }
        throw CastFunDef.cannotConvert(o, targetType);
    }

    /**
     * Resolves calls to the CAST operator.
     */
    private static class ResolverImpl extends ResolverBase {

        public ResolverImpl() {
            super(
                "Cast", "Cast(<Expression> AS <Type>)",
                "Converts values to another type.", Syntax.Cast);
        }

        @Override
		public FunctionDefinition resolve(
            Exp[] args, Validator validator, List<Conversion> conversions)
        {
            if (args.length != 2) {
                return null;
            }
            if (!(args[1] instanceof Literal literal)) {
                return null;
            }
            String typeName = (String) literal.getValue();
            int returnCategory;
            if (typeName.equalsIgnoreCase("String")) {
                returnCategory = Category.STRING;
            } else if (typeName.equalsIgnoreCase("Numeric")) {
                returnCategory = Category.NUMERIC;
            } else if (typeName.equalsIgnoreCase("Boolean")) {
                returnCategory = Category.LOGICAL;
            } else if (typeName.equalsIgnoreCase("Integer")) {
                returnCategory = Category.INTEGER;
            } else {
                throw MondrianResource.instance().CastInvalidType.ex(typeName);
            }
            final FunctionDefinition dummyFunDef =
                FunUtil.createDummyFunDef(this, returnCategory, args);
            return new CastFunDef(dummyFunDef);
        }
    }

    private static class CastCalcImpl extends GenericCalc {
        private final Calc calc;
        private final Type targetType;
        private final int targetCategory;

        public CastCalcImpl(Exp arg, Calc calc, Type targetType) {
            super(arg.getType());
            this.calc = calc;
            this.targetType = targetType;
            this.targetCategory = TypeUtil.typeToCategory(targetType);
        }

        @Override
		public Calc[] getChildCalcs() {
            return new Calc[] {calc};
        }

        @Override
		public Object evaluate(Evaluator evaluator) {
            switch (targetCategory) {
            case Category.STRING:
                return evaluateString(evaluator);
            case Category.INTEGER:
                return evaluateInteger(evaluator);
            case Category.NUMERIC:
                return evaluateDouble(evaluator);
            case Category.DATE_TIME:
                return evaluateDateTime(evaluator);
            case Category.LOGICAL:
                return evaluateBoolean(evaluator);
            default:
                throw Util.newInternal("category " + targetCategory);
            }
        }


		public String evaluateString(Evaluator evaluator) {
            final Object o = calc.evaluate(evaluator);
            if (o == null) {
                return null;
            }
            return String.valueOf(o);
        }


		public Integer evaluateInteger(Evaluator evaluator) {
            final Object o = calc.evaluate(evaluator);
            return CastFunDef.toInt(o, targetType);
        }


		public Double evaluateDouble(Evaluator evaluator) {
            final Object o = calc.evaluate(evaluator);
            return CastFunDef.toDouble(o, targetType);
        }

		public boolean evaluateBoolean(Evaluator evaluator) {
            final Object o = calc.evaluate(evaluator);
            return CastFunDef.toBoolean(o, targetType);
        }
		public Date evaluateDateTime(Evaluator evaluator) {
            final Object o = calc.evaluate(evaluator);
            if(o instanceof Date d) {
            	return d;
            }
			throw evaluator.newEvalException(null, "must be Date but was: " + o);
		}
    }

}
