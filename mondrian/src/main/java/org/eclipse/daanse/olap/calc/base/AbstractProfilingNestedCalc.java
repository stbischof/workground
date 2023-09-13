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

import java.util.List;
import java.util.stream.Stream;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.profile.CalculationProfile;
import org.eclipse.daanse.olap.calc.api.profile.ProfilingCalc;
import org.eclipse.daanse.olap.calc.base.util.HirarchyDependsChecker;

import mondrian.olap.type.Type;

public abstract class AbstractProfilingNestedCalc<E, C extends Calc<?>> extends AbstractProfilingCalc<E>
		implements Calc<E> {

	private final C[] childCalcs;

	/**
	 * {@inheritDoc}
	 *
	 * Holds the childCalcs witch are accessible using {@link #getChildCalcs()}.
	 * Enhances its own {@link CalculationProfile} with the Children's
	 * {@link CalculationProfile}.
	 * 
	 * @param calcs Child {@link Calc}s that are needed to calculate this.
	 */
	protected AbstractProfilingNestedCalc(Type type, C[] childCalcs) {
		super(type);
		this.childCalcs = childCalcs;

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

	public C[] getChildCalcs() {
		return childCalcs;
	}

	protected C getFirstChildCalc() {
		return getChildCalcs()[0];
	}

	@Override
	public boolean dependsOn(Hierarchy hierarchy) {
		return HirarchyDependsChecker.checkAnyDependsOnChilds(getChildCalcs(), hierarchy);
	}

	@Override
	public ResultStyle getResultStyle() {
		return ResultStyle.VALUE;
	}

	@Override
	protected List<CalculationProfile> getChildProfiles() {
		List<CalculationProfile> childProfiles = Stream.of(getChildCalcs()).filter(ProfilingCalc.class::isInstance)
				.map(ProfilingCalc.class::cast).map(ProfilingCalc::getCalculationProfile).toList();

		return childProfiles;
	}

}
