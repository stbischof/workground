/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.olap.type;

import java.io.PrintWriter;

import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.mdx.MdxVisitor;
import mondrian.olap.Exp;
import mondrian.olap.Validator;

/**
 * TypeWrappingExp expression which exists only to wrap a
 * {@link mondrian.olap.type.Type}.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public class TypeWrapperExp implements Exp {
    private final Type type;

    public TypeWrapperExp(Type type) {
        this.type = type;
    }

    @Override
    public TypeWrapperExp cloneExp() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCategory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void unparse(PrintWriter pw) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Exp accept(Validator validator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Calc accept(ExpressionCompiler compiler) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object accept(MdxVisitor visitor) {
        throw new UnsupportedOperationException();
    }

}
