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
package org.eclipse.daanse.olap.calc.api.profile;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.olap.api.ResultStyle;

import mondrian.olap.type.Type;

public interface CalculationProfile {

	Class<?> clazz();

	Type type();

	ResultStyle resultStyle();

	Optional<Instant> start();

	Optional<Instant> end();

	Map<String, Object> additionalValues();

	List<CalcEvaluationProfile> evaluationProfiles();

	List<CalculationProfile> childProfiles();

	Duration duration();

}