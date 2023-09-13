/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package org.eclipse.daanse.function.definition;

import mondrian.calc.impl.GenericCalc;
import mondrian.olap.Category;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Syntax;
import mondrian.olap.Util;

import org.eclipse.daanse.function.FunDefBase;
import org.eclipse.daanse.function.FunUtil;
import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.DoubleCalc;
import org.eclipse.daanse.olap.calc.api.IntegerCalc;
import org.eclipse.daanse.olap.calc.api.StringCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.AbstractProfilingNestedCalc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MDX function which is implemented by a Java method. When the function is
 * executed, the method is invoked via reflection.
 *
 * @author wgorman, jhyde
 * @since Jan 5, 2008
*/
abstract class JavaFunDef extends FunDefBase {
    private static final Map<Class, Integer> mapClazzToCategory =
        new HashMap<>();
    private static final String CLASS_NAME = JavaFunDef.class.getName();

    static {
        JavaFunDef.mapClazzToCategory.put(String.class, Category.STRING);
        JavaFunDef.mapClazzToCategory.put(Double.class, Category.NUMERIC);
        JavaFunDef.mapClazzToCategory.put(double.class, Category.NUMERIC);
        JavaFunDef.mapClazzToCategory.put(Integer.class, Category.INTEGER);
        JavaFunDef.mapClazzToCategory.put(int.class, Category.INTEGER);
        JavaFunDef.mapClazzToCategory.put(boolean.class, Category.LOGICAL);
        JavaFunDef.mapClazzToCategory.put(Object.class, Category.VALUE);
        JavaFunDef.mapClazzToCategory.put(Date.class, Category.DATE_TIME);
        JavaFunDef.mapClazzToCategory.put(float.class, Category.NUMERIC);
        JavaFunDef.mapClazzToCategory.put(long.class, Category.NUMERIC);
        JavaFunDef.mapClazzToCategory.put(double[].class, Category.ARRAY);
        JavaFunDef.mapClazzToCategory.put(char.class, Category.STRING);
        JavaFunDef.mapClazzToCategory.put(byte.class, Category.INTEGER);
    }
    private Function<Object, Object> function;


    /**
     * Creates a JavaFunDef.
     *
     * @param name Name
     * @param desc Description
     * @param syntax Syntax
     * @param returnCategory Return type
     * @param paramCategories Parameter types
     */
    public JavaFunDef(
        String name,
        String desc,
        Syntax syntax,
        int returnCategory,
        int[] paramCategories,
        Function<Object, Object> function)
    {
        super(name, null, desc, syntax, returnCategory, paramCategories);
        this.function = function;
    }



    @Override
	public Calc compileCall(
        ResolvedFunCall call,
        ExpressionCompiler compiler)
    {
        final Calc[] calcs = new Calc[parameterCategories.length];
        final Class<?>[] parameterTypes = getParameterTypes();
        for (int i = 0; i < calcs.length;i++) {
            calcs[i] =
                JavaFunDef.compileTo(
                    compiler, call.getArgs()[i], parameterTypes[i]);
        }
        return new JavaMethodCalc(call, calcs, function, getParameterTypes());
    }

    protected static int getCategory(Class clazz) {
        return mapClazzToCategory.get(clazz);
    }

    abstract public Class<?>[] getParameterTypes();

    /**
     * Compiles an expression to a calc of the required result type.
     *
     * <p>Since the result of evaluating the calc will be passed to the method
     * using reflection, it is important that the calc returns
     * <em>precisely</em> the correct type: if a method requires an
     * <code>int</code>, you can pass an {@link Integer} but not a {@link Long}
     * or {@link Float}.
     *
     * <p>If it can be determined that the underlying calc will never return
     * null, generates an optimal form with one fewer object instantiation.
     *
     * @param compiler Compiler
     * @param exp Expression to compile
     * @param clazz Desired class
     * @return compiled expression
     */
    private static Calc compileTo(ExpressionCompiler compiler, Exp exp, Class clazz) {
        if (clazz == String.class) {
            return compiler.compileString(exp);
        } else if (clazz == Date.class) {
            return compiler.compileDateTime(exp);
        } else if (clazz == boolean.class) {
            return compiler.compileBoolean(exp);
        } else if (clazz == byte.class) {
            final IntegerCalc integerCalc = compiler.compileInteger(exp);
            if (integerCalc.getResultStyle() == ResultStyle.VALUE_NOT_NULL) {
                // We know that the calculation will never return a null value,
                // so generate optimized code.
                return new AbstractCalc2(exp, integerCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {

						Integer i = integerCalc.evaluate(evaluator);
						if (i == null) {
							return null;
						}
						return i.byteValue();
					}
                };
            } else {
                return new AbstractCalc2(exp, integerCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
                        Integer i = (Integer) integerCalc.evaluate(evaluator);
                        return i == null ? null : (byte) i.intValue();
                    }
                };
            }
        } else if (clazz == char.class) {
            final StringCalc stringCalc = compiler.compileString(exp);
            return new AbstractCalc2(exp, stringCalc) {
                @Override
				public Object evaluate(Evaluator evaluator) {
                    final String string =
                        stringCalc.evaluate(evaluator);
                    return
                        Character.valueOf(
                            string == null
                            || string.length() < 1
                                ? (char) 0
                                : string.charAt(0));
                }
            };
        } else if (clazz == short.class) {
            final IntegerCalc integerCalc = compiler.compileInteger(exp);
            if (integerCalc.getResultStyle() == ResultStyle.VALUE_NOT_NULL) {
                return new AbstractCalc2(exp, integerCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
						Integer i = integerCalc.evaluate(evaluator);
						if (i == null) {
							return null;
						}
						return i.shortValue();
					}
                };
            } else {
                return new AbstractCalc2(exp, integerCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
                        Integer i = (Integer) integerCalc.evaluate(evaluator);
                        return i == null ? null : (short) i.intValue();
                    }
                };
            }
        } else if (clazz == int.class) {
            return compiler.compileInteger(exp);
        } else if (clazz == long.class) {
            final IntegerCalc integerCalc = compiler.compileInteger(exp);
            if (integerCalc.getResultStyle() == ResultStyle.VALUE_NOT_NULL) {
                return new AbstractCalc2(exp, integerCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
						Integer i = integerCalc.evaluate(evaluator);
						if (i == null) {
							return null;
						}
						return i.longValue();
					}
                };
            } else {
                return new AbstractCalc2(exp, integerCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
                        Integer i = (Integer) integerCalc.evaluate(evaluator);
                        return i == null ? null : (long) i.intValue();
                    }
                };
            }
        } else if (clazz == float.class) {
            final DoubleCalc doubleCalc = compiler.compileDouble(exp);
            if (doubleCalc.getResultStyle() == ResultStyle.VALUE_NOT_NULL) {
                return new AbstractCalc2(exp, doubleCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
                        Double v = (Double) doubleCalc.evaluate(evaluator);
                        return v == null ? null : v.floatValue();
                    }
                };
            } else {
                return new AbstractCalc2(exp, doubleCalc) {
                    @Override
					public Object evaluate(Evaluator evaluator) {
                        return  doubleCalc.evaluate(evaluator).floatValue();
                    }
                };
            }
        } else if (clazz == double.class) {
            return compiler.compileDouble(exp);
        } else if (clazz == Object.class) {
            return compiler.compileScalar(exp, false);
        } else {
            throw Util.newInternal("expected primitive type, got " + clazz);
        }
    }

    /**
     * Annotation which allows you to tag a Java method with the name of the
     * MDX function it implements.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface FunctionName
    {
        public abstract String value();
    }

    /**
     * Annotation which allows you to tag a Java method with the description
     * of the MDX function it implements.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Description
    {
        public abstract String value();
    }

    /**
     * Annotation which allows you to tag a Java method with the signature of
     * the MDX function it implements.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Signature
    {
        public abstract String value();
    }

    /**
     * Annotation which allows you to tag a Java method with the syntax of the
     * MDX function it implements.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface SyntaxDef
    {
        public abstract Syntax value();
    }

    /**
     * Base class for adapter calcs that convert arguments into the precise
     * type needed.
     */
    private abstract static class AbstractCalc2 extends AbstractProfilingNestedCalc {
        /**
         * Creates an AbstractCalc2.
         *
         * @param exp Source expression
         * @param calc Child compiled expression
         */
        protected AbstractCalc2(Exp exp, Calc calc) {
            super(exp.getType(), new Calc[] {calc});
        }
    }

    /**
     * Calc which calls a Java method.
     */
    private static class JavaMethodCalc extends GenericCalc {
        private final Function<Object, Object> function;
        private final Class<?>[] parameterTypes;
        private final Object[] args;

        /**
         * Creates a JavaMethodCalc.
         *
         * @param call Function call being implemented
         * @param calcs Calcs for arguments of function call
         * @param function Method to call
         */
        public JavaMethodCalc(
            ResolvedFunCall call, Calc[] calcs, Function<Object, Object> function, Class<?>[] parameterTypes)
        {
            super(call.getType(), calcs);
            this.function = function;
            this.args = new Object[calcs.length];
            this.parameterTypes = parameterTypes;
        }

        @Override
		public Object evaluate(Evaluator evaluator) {
            final Calc[] calcs = getChildCalcs();
            for (int i = 0; i < args.length; i++) {
                args[i] = calcs[i].evaluate(evaluator);
                if (args[i] == null || Objects.equals(args[i], FunUtil.DOUBLE_NULL)) {
                    return Util.nullValue;
                }
            }
            try {
                return function.apply(args);
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("argument type mismatch")) {
                    StringBuilder buf =
                        new StringBuilder(
                            "argument type mismatch: parameters (");
                    int k = 0;
                    for (Class<?> parameterType : parameterTypes) {
                        if (k++ > 0) {
                            buf.append(", ");
                        }
                        buf.append(parameterType.getName());
                    }
                    buf.append("), actual (");
                    k = 0;
                    for (Object arg : args) {
                        if (k++ > 0) {
                            buf.append(", ");
                        }
                        buf.append(
                            arg == null
                                ? "null"
                                : arg.getClass().getName());
                    }
                    buf.append(")");
                    throw Util.newInternal(buf.toString());
                } else {
                    throw e;
                }
            }
        }
    }
}
