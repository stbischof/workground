/*
* Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.olap.calc.base;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.element.Hierarchy;

import mondrian.olap.Evaluator;
import mondrian.olap.type.Type;

public abstract class AbstractProfilingValueCalc<T> extends AbstractProfilingCalc<T> {

	public AbstractProfilingValueCalc(Type type) {
		super(type);
	}

	@Override
	public T evaluate(Evaluator evaluator) {
		return convertCurrentValue(evaluator.evaluateCurrent());
	}

	protected abstract T convertCurrentValue(Object evaluateCurrent);

	@Override
	public boolean dependsOn(Hierarchy hierarchy) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * by default check isInstance.
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) {
		return iface.isInstance(this);
	}

	/**
	 * {@inheritDoc}
	 *
	 * by default just cast.
	 */
	@Override
	public <T> T unwrap(Class<T> iface) {
		return iface.cast(this);
	}

	@Override
	public ResultStyle getResultStyle() {
		return ResultStyle.VALUE;
	}

}
