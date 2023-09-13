
package org.eclipse.daanse.olap.calc.base.nested;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.TupleIterableCalc;
import org.eclipse.daanse.olap.calc.base.AbstractProfilingNestedCalc;

import mondrian.calc.TupleIterable;
import mondrian.olap.type.SetType;
import mondrian.olap.type.Type;

public abstract class AbstractProfilingNestedTupleIterableCalc<C extends Calc<?>>
		extends AbstractProfilingNestedCalc<TupleIterable, C> implements TupleIterableCalc {

	protected AbstractProfilingNestedTupleIterableCalc(Type type, C[] calcs) {
		super(type, calcs);
	}

	@Override
	public SetType getType() {
		return (SetType) super.getType();
	}

	@Override
	public ResultStyle getResultStyle() {
		return ResultStyle.ITERABLE;
	}

}
