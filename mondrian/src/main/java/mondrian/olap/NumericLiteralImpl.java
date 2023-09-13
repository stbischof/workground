/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package mondrian.olap;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

import org.eclipse.daanse.olap.api.query.component.NumericLiteral;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.constant.ConstantDoubleCalc;
import org.eclipse.daanse.olap.calc.base.constant.ConstantStringCalc;
import org.olap4j.impl.UnmodifiableArrayMap;

import mondrian.mdx.MdxVisitor;
import mondrian.olap.type.NumericType;
import mondrian.olap.type.StringType;
import mondrian.olap.type.Type;

public class NumericLiteralImpl extends AbstractLiteralImpl<BigDecimal> implements NumericLiteral {
	private static final NumericLiteralImpl negativeOne = new NumericLiteralImpl(BigDecimal.ONE.negate());
	private static final NumericLiteralImpl one = new NumericLiteralImpl(BigDecimal.ONE);
	public static final NumericLiteralImpl zero = new NumericLiteralImpl(BigDecimal.ZERO);
	private static final Map<BigDecimal, NumericLiteralImpl> MAP = UnmodifiableArrayMap.of(BigDecimal.ZERO, zero,
			BigDecimal.ONE, one, BigDecimal.ONE.negate(), negativeOne);

	private NumericLiteralImpl(BigDecimal bigDecimal) {
		super(bigDecimal);
	}

	/**
	 * Creates a numeric literal.
	 *
	 * <p>
	 * Using a {@link BigDecimal} allows us to store the precise value that the user
	 * typed. We will have to fit the value into a native {@code double} or
	 * {@code int} later on, but parse time is not the time to be throwing away
	 * information.
	 */
	public static NumericLiteralImpl create(BigDecimal bigDecimal) {
		final NumericLiteralImpl literal = MAP.get(bigDecimal);
		if (literal != null) {
			return literal;
		}
		return new NumericLiteralImpl(bigDecimal);
	}

	@Override
	public Object accept(MdxVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public int getCategory() {
		return Category.NUMERIC;
	}

	@Override
	public Type getType() {
		return new NumericType();
	}

	@Override
	public void unparse(PrintWriter pw) {
		pw.print(getValue());
	}
	
	@Override
	public Calc<?> accept(ExpressionCompiler compiler) {
		return new ConstantDoubleCalc(new NumericType(), getValue().doubleValue());
	}

	@Override
	public int getIntValue() {
		return getValue().intValue();
	}
}