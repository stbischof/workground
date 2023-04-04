/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.calc.impl;

import java.util.Date;

import org.eclipse.daanse.olap.api.model.Dimension;
import org.eclipse.daanse.olap.api.model.Hierarchy;
import org.eclipse.daanse.olap.api.model.Level;
import org.eclipse.daanse.olap.api.model.Member;

import mondrian.calc.BooleanCalc;
import mondrian.calc.Calc;
import mondrian.calc.DateTimeCalc;
import mondrian.calc.DimensionCalc;
import mondrian.calc.DoubleCalc;
import mondrian.calc.HierarchyCalc;
import mondrian.calc.IntegerCalc;
import mondrian.calc.LevelCalc;
import mondrian.calc.MemberCalc;
import mondrian.calc.StringCalc;
import mondrian.calc.TupleCalc;
import mondrian.calc.VoidCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.fun.FunUtil;
import mondrian.olap.type.Type;

/**
 * Adapter which computes a scalar or tuple expression and converts it to any
 * required type.
 *
 * @see mondrian.calc.impl.GenericIterCalc
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public abstract class GenericCalc
extends AbstractCalc
implements TupleCalc,
StringCalc, IntegerCalc, DoubleCalc, BooleanCalc, DateTimeCalc,
VoidCalc, MemberCalc, LevelCalc, HierarchyCalc, DimensionCalc
{
    /**
     * Creates a GenericCalc without specifying child calculated expressions.
     *
     * <p>Subclass should override {@link #getCalcs()}.
     *
     * @param exp Source expression
     */
    protected GenericCalc(String name, Type type) {
        super(name,type, null);
    }

    /**
     * Creates an GenericCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    protected GenericCalc(String name, Type type, Calc[] calcs) {
        super(name,type, calcs);
    }

  

	@Override
    public Member[] evaluateTuple(Evaluator evaluator) {
        return (Member[]) evaluate(evaluator);
    }

    private String msg(TypeEnum expectedType, Object o) {
        final TypeEnum actualType = GenericCalc.actualType(o);
        return new StringBuilder("Expected value of type ").append(expectedType).append("; got value '").append(o)
                .append("' (").append((actualType == null ? o.getClass() : actualType)).append(")").toString();
    }

    private static TypeEnum actualType(Object o) {
        if (o == null) {
            return TypeEnum.NULL;
        }
        if (o instanceof String) {
            return TypeEnum.STRING;
        } else if (o instanceof Boolean) {
            return TypeEnum.BOOLEAN;
        } else if (o instanceof Number) {
            return TypeEnum.NUMERIC;
        } else if (o instanceof Date) {
            return TypeEnum.DATETIME;
        } else if (o instanceof Member) {
            return TypeEnum.MEMBER;
        } else if (o instanceof Level) {
            return TypeEnum.LEVEL;
        } else if (o instanceof Hierarchy) {
            return TypeEnum.HIERARCHY;
        } else if (o instanceof Dimension) {
            return TypeEnum.DIMENSION;
        } else {
            return null;
        }
    }

    @Override
    public String evaluateString(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (String) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.STRING, o));
        }
    }

    @Override
    public int evaluateInteger(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            final Number number = (Number) o;
            return number == null
                    ? FunUtil.IntegerNull
                            : number.intValue();
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.NUMERIC, o));
        }
    }

    @Override
    public double evaluateDouble(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            final Number number = (Number) o;
            return GenericCalc.numberToDouble(number);
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.NUMERIC, o));
        }
    }

    public static double numberToDouble(Number number) {
        return number == null
                ? FunUtil.DoubleNull
                        : number.doubleValue();
    }

    @Override
    public boolean evaluateBoolean(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (Boolean) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.BOOLEAN, o));
        }
    }

    @Override
    public Date evaluateDateTime(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (Date) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.DATETIME, o));
        }
    }

    @Override
    public void evaluateVoid(Evaluator evaluator) {
        final Object result = evaluate(evaluator);
        assert result == null;
    }

    @Override
    public Member evaluateMember(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (Member) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.MEMBER, o));
        }
    }

    @Override
    public Level evaluateLevel(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (Level) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.LEVEL, o));
        }
    }

    @Override
    public Hierarchy evaluateHierarchy(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (Hierarchy) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.HIERARCHY, o));
        }
    }

    @Override
    public Dimension evaluateDimension(Evaluator evaluator) {
        final Object o = evaluate(evaluator);
        try {
            return (Dimension) o;
        } catch (final ClassCastException e) {
            throw evaluator.newEvalException(null, msg(TypeEnum.DIMENSION, o));
        }
    }

    private enum TypeEnum {
        NULL,
        BOOLEAN, STRING, NUMERIC, DATETIME,
        MEMBER, LEVEL, HIERARCHY, DIMENSION
    }
}
