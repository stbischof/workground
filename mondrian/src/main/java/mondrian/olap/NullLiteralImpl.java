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

import org.eclipse.daanse.olap.api.query.component.NullLiteral;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.constant.ConstantStringCalc;

import mondrian.mdx.MdxVisitor;
import mondrian.olap.type.NullType;
import mondrian.olap.type.StringType;
import mondrian.olap.type.Type;

public class NullLiteralImpl extends AbstractLiteralImpl<Object> implements NullLiteral {

	public static final NullLiteralImpl nullValue = new NullLiteralImpl();

	private NullLiteralImpl() {
		super(null);
	}

	@Override
	public Object accept(MdxVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public int getCategory() {
		return Category.NULL;
	}

	@Override
	public Type getType() {
		return new NullType();
	}

	@Override
	public Calc<?> accept(ExpressionCompiler compiler) {

		return new ConstantStringCalc(new StringType(), null);
	}

	@Override
	public void unparse(PrintWriter pw) {
		pw.print("NULL");
	}

}
