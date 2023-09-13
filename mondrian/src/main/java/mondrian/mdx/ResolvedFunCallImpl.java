/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 1998-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.mdx;

import java.io.PrintWriter;

import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.olap.Exp;
import mondrian.olap.ExpBase;
import mondrian.olap.FunCall;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.Syntax;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.fun.FunUtil;
import mondrian.olap.type.Type;

/**
 * A <code>ResolvedFunCall</code> is a function applied to a list of operands,
 * which has been validated and resolved to a
 * {@link FunctionDefinition function definition}.
 *
 * @author jhyde
 * @since Jan 6, 2006
 */
public final class ResolvedFunCallImpl extends ExpBase implements  ResolvedFunCall {

    /**
     * The arguments to the function call.  Note that for methods, 0-th arg is
     * 'this'.
     */
    private final Exp[] args;

    /**
     * Return type of this function call.
     */
    private final Type returnType;

    /**
     * Function definition.
     */
    private final FunctionDefinition funDef;

    /**
     * Creates a function call.
     *
     * @param funDef Function definition
     * @param args Arguments
     * @param returnType Return type
     */
    public ResolvedFunCallImpl(FunctionDefinition funDef, Exp[] args, Type returnType) {
        if (funDef == null || args == null || returnType == null) {
            throw new IllegalArgumentException("ResolvedFunCall params be not null");
        }
        this.funDef = funDef;
        this.args = args;
        this.returnType = returnType;
    }

    @Override
	public String toString() {
        return Util.unparse(this);
    }

    @Override
	@SuppressWarnings({"CloneDoesntCallSuperClone"})
    public ResolvedFunCallImpl cloneExp() {
        return new ResolvedFunCallImpl(
            funDef, ExpBase.cloneArray(args), returnType);
    }

    /**
     * Returns the Exp argument at the specified index.
     *
     * @param      index   the index of the Exp.
     * @return     the Exp at the specified index of this array of Exp.
     *             The first Exp is at index <code>0</code>.
     * @see #getArgs()
     */
    @Override
	public Exp getArg(int index) {
        return args[index];
    }

    /**
     * Returns the internal array of Exp arguments.
     *
     * <p>Note: this does NOT do a copy.
     *
     * @return the array of expressions
     */
    @Override
	public Exp[] getArgs() {
        return args;
    }

    /**
     * Returns the number of arguments.
     *
     * @return number of arguments.
     * @see #getArgs()
     */
    @Override
	public final int getArgCount() {
        return args.length;
    }

    @Override
	public String getFunName() {
        return funDef.getName();
    }

    @Override
	public Syntax getSyntax() {
        return funDef.getSyntax();
    }

    @SuppressWarnings("java:S4144")
    @Override
	public Object[] getChildren() {
        return args;
    }

    /**
     * Returns the definition of the function which is being called.
     *
     * @return function definition
     */
    @Override
    public FunctionDefinition getFunDef() {
        return funDef;
    }

    @Override
	public final int getCategory() {
        return funDef.getReturnCategory();
    }

    @Override
	public final Type getType() {
        return returnType;
    }

    @Override
	public Exp accept(Validator validator) {
        // even though the function has already been validated, we need
        // to walk through the arguments to determine which measures are
        // referenced
        Exp[] newArgs = new Exp[args.length];
        FunUtil.resolveFunArgs(
            validator, funDef, args, newArgs, getFunName(), getSyntax());

        return this;
    }

    @Override
	public void unparse(PrintWriter pw) {
        funDef.unparse(args, pw);
    }

    @Override
	public Calc accept(ExpressionCompiler compiler) {
        return funDef.compileCall(this, compiler);
    }

    @Override
	public Object accept(MdxVisitor visitor) {
        final Object o = visitor.visit(this);
        if (visitor.shouldVisitChildren()) {
            // visit the call's arguments
            for (Exp arg : args) {
                arg.accept(visitor);
            }
        }
        return o;
    }
}
