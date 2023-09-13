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
package org.opencube.junit5.dbprovider;

import java.io.IOException;
import java.util.Map.Entry;

import org.eclipse.daanse.olap.api.Context;

import mondrian.olap.Util.PropertyList;

public class EmptyDBProvider implements DatabaseProvider {

	@Override
	public void close() throws IOException {

	}

	@Override
	public String getJdbcUrl() {
		return "";
	}

	@Override
	public Entry<PropertyList, Context> activate() {
		return null;
	}

}
