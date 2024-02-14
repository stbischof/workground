/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * History:
 *  This files came from the mondrian project. Some of the Flies
 *  (mostly the Tests) did not have License Header.
 *  But the Project is EPL Header. 2002-2022 Hitachi Vantara.
 *
 * Contributors:
 *   Hitachi Vantara.
 *   SmartCity Jena - initial  Java 8, Junit5
 */
package org.opencube.junit5;

import java.util.Locale;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MondrianRuntimeExtension
		implements ExecutionCondition, BeforeAllCallback, ExtensionContext.Store.CloseableResource {

	@Override
	public void beforeAll(ExtensionContext context) {
		System.out.println("### MondrianRuntimeExtension:beforeAll");
		defineLocale();
	}

	private void defineLocale() {
		Locale.setDefault(Locale.US);
	}

	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

		try {
			DockerSupport.available();
			return ConditionEvaluationResult.enabled("Docker is available");
		} catch (Throwable ex) {
			return ConditionEvaluationResult.disabled("Docker is not available", ex.getMessage());
		}

	}

	@Override
	public void close() throws Throwable {

	}

}
