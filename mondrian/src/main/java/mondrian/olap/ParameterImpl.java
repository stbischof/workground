/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2000-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.olap;

import java.util.List;

import org.eclipse.daanse.olap.api.query.component.Literal;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.api.compiler.ParameterCompilable;
import org.eclipse.daanse.olap.calc.api.compiler.ParameterSlot;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleList;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.calc.impl.GenericCalc;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.NumericType;
import mondrian.olap.type.SetType;
import mondrian.olap.type.StringType;
import mondrian.olap.type.Type;

/**
 * Implementation of {@link Parameter}.
 *
 * @author jhyde
 * @since Jul 22, 2006
 */
public class ParameterImpl
    implements Parameter, ParameterCompilable {

    private final String name;
    private String description;
    private Exp defaultExp;
    private Type type;
    private ParameterSlot slot = new ParameterSlot() {
        Object value;
        boolean assigned;

        @Override
		public Object getCachedDefaultValue() {
            throw new UnsupportedOperationException();
        }

        @Override
		public Calc getDefaultValueCalc() {
            throw new UnsupportedOperationException();
        }

        @Override
		public int getIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
		public Parameter getParameter() {
            return ParameterImpl.this;
        }

        @Override
		public Object getParameterValue() {
            return value;
        }

        @Override
		public boolean isParameterSet() {
            return assigned;
        }

        @Override
		public void unsetParameterValue() {
            this.assigned = false;
            this.value = null;
        }

        @Override
		public void setCachedDefaultValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setParameterValue(Object value, boolean assigned) {
            this.assigned = true;
            this.value = value;

            // make sure caller called convert first
            assert !(value instanceof List && !(value instanceof TupleList));
            assert !(value instanceof MemberExpression);
            assert !(value instanceof Literal);
        }
    };

    public ParameterImpl(
        String name,
        Exp defaultExp,
        String description,
        Type type
    ) {
        this.name = name;
        this.defaultExp = defaultExp;
        this.description = description;
        this.type = type;
        assert defaultExp != null;
        assert type instanceof StringType
            || type instanceof NumericType
            || type instanceof MemberType;
    }

    @Override
	public Scope getScope() {
        return Scope.Statement;
    }

    @Override
	public Type getType() {
        return type;
    }

    @Override
	public Exp getDefaultExp() {
        return defaultExp;
    }

    @Override
	public String getName() {
        return name;
    }

    @Override
	public Object getValue() {
        if (slot == null) {
            // query has not been resolved yet, so it's not possible for the
            // parameter to have a value
            return null;
        } else {
            final Object value = slot.getParameterValue();
            return convertBack(value);
        }
    }

    @Override
	public void setValue(Object value) {
        slot.setParameterValue(convert(value), true);
    }

    @Override
	public boolean isSet() {
        return slot != null
            && slot.isParameterSet();
    }

    @Override
	public void unsetValue() {
        slot.unsetParameterValue();
    }

    @Override
	public String getDescription() {
        return description;
    }

    // For the purposes of type inference and expression substitution, a
    // parameter is atomic; therefore, we ignore the child member, if any.
    public Object[] getChildren() {
        return null;
    }

    /**
     * Returns whether this parameter is equal to another, based upon name,
     * type and value
     */
    @Override
	public boolean equals(Object other) {
        if (!(other instanceof ParameterImpl that)) {
            return false;
        }
        return that.getName().equals(this.getName())
            && that.defaultExp.equals(this.defaultExp);
    }

    @Override
	public int hashCode() {
        return Util.hash(getName().hashCode(), defaultExp.hashCode());
    }

    /**
     * Returns whether the parameter can be modified.
     */
    @Override
	public boolean isModifiable() {
        return true;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(Type type) {
        assert type instanceof StringType
            || type instanceof NumericType
            || type instanceof MemberType
            || (type instanceof SetType
            && ((SetType) type).getElementType() instanceof MemberType)
            : type;
        this.type = type;
    }

    public void setDefaultExp(Exp defaultExp) {
        assert defaultExp != null;
        this.defaultExp = defaultExp;
    }

    @Override
	public Calc compile(ExpressionCompiler compiler) {
        final ParameterSlot slot = compiler.registerParameter(this);
        if (this.slot != null) {
            // save previous value
            if (this.slot.isParameterSet()) {
                slot.setParameterValue(
                    this.slot.getParameterValue(),
                    true);
            }
        }
        this.slot = slot;
        if (type instanceof SetType) {
            return new MemberListParameterCalc(slot);
        } else {
            return new ParameterCalc(slot);
        }
    }

    protected Object convert(Object value) {
        // Convert from old-style tuple list (list of member or member[])
        // to new-style list (TupleList).
        if (value instanceof List list && !(value instanceof TupleList)) {
            return TupleCollections.asTupleList(list);
        }
        if (value instanceof MemberExpression) {
            return ((MemberExpression) value).getMember();
        }
        if (value instanceof Literal) {
            return ((Literal) value).getValue();
        }
        return value;
    }

    public static Object convertBack(Object value) {
        if (value instanceof TupleList tupleList) {
            if (tupleList.getArity() == 1) {
                return tupleList.slice(0);
            } else {
                return TupleCollections.asMemberArrayList(tupleList);
            }
        }
        return value;
    }

    /**
     * Compiled expression which yields the value of a scalar, member, level,
     * hierarchy or dimension parameter.
     *
     * <p>It uses a slot which has a unique id within the execution environment.
     *
     * @see MemberListParameterCalc
     */
    private static class ParameterCalc
        extends GenericCalc {

        private final ParameterSlot slot;

        /**
         * Creates a ParameterCalc.
         *
         * @param slot Slot
         */
        public ParameterCalc(ParameterSlot slot) {
            super(slot.getParameter().getType(), new Calc[0]);
            this.slot = slot;
        }

        @Override
		public Object evaluate(Evaluator evaluator) {
            Object value = evaluator.getParameterValue(slot);
            if (!slot.isParameterSet()) {
                // save value if not set (setting the default value)
                slot.setParameterValue(value, false);
            }
            return value;
        }
    }

    /**
     * Compiled expression which yields the value of parameter whose type is
     * a list of members.
     *
     * <p>It uses a slot which has a unique id within the execution environment.
     *
     * @see ParameterCalc
     */
    private static class MemberListParameterCalc
        extends AbstractListCalc {

        private final ParameterSlot slot;

        /**
         * Creates a MemberListParameterCalc.
         *
         * @param slot Slot
         */
        public MemberListParameterCalc(ParameterSlot slot) {
			super( slot.getParameter().getType(), new Calc[0]);
            this.slot = slot;
        }

        @Override
		public TupleList evaluateList(Evaluator evaluator) {
            TupleList value = (TupleList) evaluator.getParameterValue(slot);
            if (!slot.isParameterSet()) {
                // save value if not set (setting the default value)
                slot.setParameterValue(value, false);
            }
            return value;
        }
    }
}
