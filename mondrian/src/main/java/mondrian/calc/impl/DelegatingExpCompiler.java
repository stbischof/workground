/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.calc.impl;

import java.io.PrintWriter;
import java.util.List;

import org.eclipse.daanse.olap.api.query.component.QueryPart;
import org.eclipse.daanse.olap.api.query.component.WrapExpression;
import org.eclipse.daanse.olap.calc.api.BooleanCalc;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.DateTimeCalc;
import org.eclipse.daanse.olap.calc.api.DimensionCalc;
import org.eclipse.daanse.olap.calc.api.DoubleCalc;
import org.eclipse.daanse.olap.calc.api.HierarchyCalc;
import org.eclipse.daanse.olap.calc.api.IntegerCalc;
import org.eclipse.daanse.olap.calc.api.LevelCalc;
import org.eclipse.daanse.olap.calc.api.MemberCalc;
import org.eclipse.daanse.olap.calc.api.StringCalc;
import org.eclipse.daanse.olap.calc.api.TupleCalc;

import mondrian.calc.ExpCompiler;
import mondrian.calc.TupleIteratorCalc;
import mondrian.calc.TupleListCalc;
import mondrian.calc.ParameterSlot;
import mondrian.calc.ResultStyle;
import mondrian.mdx.MdxVisitor;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Parameter;
import mondrian.olap.AbstractQueryPart;
import mondrian.olap.Validator;
import mondrian.olap.type.Type;

/**
 * Abstract implementation of {@link mondrian.calc.ExpCompiler}
 *
 * @author jhyde
 * @since Jan 2, 2006
 */
public class DelegatingExpCompiler implements ExpCompiler {
    private final ExpCompiler parent;

    protected DelegatingExpCompiler(ExpCompiler parent) {
        this.parent = parent;
    }

    /**
     * Hook for post-processing.
     *
     * @param exp Expression to compile
     * @param calc Calculator created by compiler
     * @param mutable Whether the result is mutuable
     * @return Calculator after post-processing
     */
    protected Calc afterCompile(Exp exp, Calc calc, boolean mutable) {
        return calc;
    }

    @Override
    public Evaluator getEvaluator() {
        return parent.getEvaluator();
    }

    @Override
    public Validator getValidator() {
        return parent.getValidator();
    }

    @Override
    public Calc compile(Exp exp) {
        final Calc calc = parent.compile(wrap(exp));
        return afterCompile(exp, calc, false);
    }

    @Override
    public Calc compileAs(
            Exp exp,
            Type resultType,
            List<ResultStyle> preferredResultTypes, boolean caseSensitive)
    {
        return parent.compileAs(wrap(exp), resultType, preferredResultTypes, caseSensitive);
    }

    @Override
    public MemberCalc compileMember(Exp exp, boolean caseSensitive) {
        final MemberCalc calc = parent.compileMember(wrap(exp), caseSensitive);
        return (MemberCalc) afterCompile(exp, calc, false);
    }

    @Override
    public LevelCalc compileLevel(Exp exp, boolean caseSensitive) {
        final LevelCalc calc = parent.compileLevel(wrap(exp), caseSensitive);
        return (LevelCalc) afterCompile(exp, calc, false);
    }

    @Override
    public DimensionCalc compileDimension(Exp exp, boolean caseSensitive) {
        final DimensionCalc calc = parent.compileDimension(wrap(exp), caseSensitive);
        return (DimensionCalc) afterCompile(exp, calc, false);
    }

    @Override
    public HierarchyCalc compileHierarchy(Exp exp, boolean caseSensitive) {
        final HierarchyCalc calc = parent.compileHierarchy(wrap(exp), caseSensitive);
        return (HierarchyCalc) afterCompile(exp, calc, false);
    }

    @Override
    public IntegerCalc compileInteger(Exp exp, boolean caseSensitive) {
        final IntegerCalc calc = parent.compileInteger(wrap(exp), caseSensitive);
        return (IntegerCalc) afterCompile(exp, calc, false);
    }

    @Override
    public StringCalc compileString(Exp exp, boolean caseSensitive) {
        final StringCalc calc = parent.compileString(wrap(exp), caseSensitive);
        return (StringCalc) afterCompile(exp, calc, false);
    }

    @Override
    public DateTimeCalc compileDateTime(Exp exp, boolean caseSensitive) {
        final DateTimeCalc calc = parent.compileDateTime(wrap(exp), caseSensitive);
        return (DateTimeCalc) afterCompile(exp, calc, false);
    }

    @Override
    public final TupleListCalc compileList(Exp exp, boolean caseSensitive) {
        return compileList(exp, false, caseSensitive);
    }

    @Override
    public TupleListCalc compileList(Exp exp, boolean mutable, boolean caseSensitive) {
        final TupleListCalc calc = parent.compileList(wrap(exp), mutable);
        return (TupleListCalc) afterCompile(exp, calc, mutable);
    }

    @Override
    public TupleIteratorCalc compileIter(Exp exp, boolean caseSensitive) {
        final TupleIteratorCalc calc = parent.compileIter(wrap(exp), caseSensitive);
        return (TupleIteratorCalc) afterCompile(exp, calc, false);
    }

    @Override
    public BooleanCalc compileBoolean(Exp exp, boolean caseSensitive) {
        final BooleanCalc calc = parent.compileBoolean(wrap(exp), caseSensitive);
        return (BooleanCalc) afterCompile(exp, calc, false);
    }

    @Override
    public DoubleCalc compileDouble(Exp exp, boolean caseSensitive) {
        final DoubleCalc calc = parent.compileDouble(wrap(exp), caseSensitive);
        return (DoubleCalc) afterCompile(exp, calc, false);
    }

    @Override
    public TupleCalc compileTuple(Exp exp) {
        final TupleCalc calc = parent.compileTuple(wrap(exp));
        return (TupleCalc) afterCompile(exp, calc, false);
    }

    @Override
    public Calc compileScalar(Exp exp, boolean scalar, boolean caseSensitive) {
        final Calc calc = parent.compileScalar(wrap(exp), scalar, caseSensitive);
        return afterCompile(exp, calc, false);
    }

    @Override
    public ParameterSlot registerParameter(Parameter parameter, boolean caseSensitive) {
        return parent.registerParameter(parameter, caseSensitive);
    }

    @Override
    public List<ResultStyle> getAcceptableResultStyles() {
        return parent.getAcceptableResultStyles();
    }

    /**
     * Wrapping an expression ensures that when it is visited, it calls
     * back to this compiler rather than our parent (wrapped) compiler.
     *
     * <p>All methods that pass an expression to the delegate compiler should
     * wrap expressions in this way. Hopefully the delegate compiler doesn't
     * use {@code instanceof}; it should be using the visitor pattern instead.
     *
     * <p>If we didn't do this, the decorator would get forgotten at the first
     * level of recursion. It's not pretty, and I thought about other ways
     * of combining Visitor + Decorator. For instance, I tried replacing
     * {@link #afterCompile(mondrian.olap.Exp, mondrian.calc.Calc, boolean)}
     * with a callback (Strategy), but the exit points in ExpCompiler not
     * clear because there are so many methods.
     *
     * @param e Expression to be wrapped
     * @return wrapper expression
     */
    private Exp wrap(Exp e) {
        return new WrapExpressionImpl(e, this);
    }

    /**
     * See {@link mondrian.calc.impl.DelegatingExpCompiler#wrap}.
     */
    private static class WrapExpressionImpl extends AbstractQueryPart implements Exp, WrapExpression {
        private final Exp e;
        private final ExpCompiler wrappingCompiler;

        WrapExpressionImpl(
                Exp e,
                ExpCompiler wrappingCompiler)
        {
            this.e = e;
            this.wrappingCompiler = wrappingCompiler;
        }

        @Override
        public Exp cloneExp() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getCategory() {
            return e.getCategory();
        }

        @Override
        public Type getType() {
            return e.getType();
        }

        @Override
        public void unparse(PrintWriter pw) {
            e.unparse(pw);
        }

        @Override
        public Exp accept(Validator validator) {
            return e.accept(validator);
        }

        @Override
        public Calc accept(ExpCompiler compiler) {
            return e.accept(wrappingCompiler);
        }

        @Override
        public Object accept(MdxVisitor visitor, boolean caseSensitive) {
            return e.accept(visitor, caseSensitive);
        }

        @Override
		public void explain(PrintWriter pw) {
            if (e instanceof QueryPart queryPart) {
                queryPart.explain(pw);
            } else {
                super.explain(pw);
            }
        }
    }
}
