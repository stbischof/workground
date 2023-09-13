package org.eclipse.daanse.olap.calc.base.nested;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.TupleListCalc;
import org.eclipse.daanse.olap.calc.base.AbstractProfilingNestedCalc;

import mondrian.calc.TupleList;
import mondrian.olap.type.SetType;
import mondrian.olap.type.Type;

public abstract class AbstractProfilingNestedTupleListCalc<C extends Calc<?>>
		extends AbstractProfilingNestedCalc<TupleList, C> implements TupleListCalc {
	private final boolean mutable;

	protected AbstractProfilingNestedTupleListCalc(Type type, C[] calcs) {
		this(type, calcs, true);
	}

	protected AbstractProfilingNestedTupleListCalc(Type type, C[] calcs, boolean mutable) {
		super(type, calcs);
		this.mutable = mutable;
		requiresType(SetType.class);
	}

	@Override
	public SetType getType() {
		return (SetType) super.getType();
	}

	@Override
	public ResultStyle getResultStyle() {
		return mutable ? ResultStyle.MUTABLE_LIST : ResultStyle.LIST;
	}

}
