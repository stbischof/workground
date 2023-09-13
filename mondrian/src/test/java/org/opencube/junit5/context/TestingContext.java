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
package org.opencube.junit5.context;

import java.sql.SQLException;
import java.util.Map.Entry;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.olap4j.OlapConnection;

import mondrian.olap.Util.PropertyList;

public interface TestingContext {

	void init(Entry<PropertyList, Context> dataSource);

	/**
	 * Returns the olap.Connection.
	 */
	Connection createConnection();

	OlapConnection createOlap4jConnection() throws SQLException;

	public String getJDBCConnectString();

	String getOlapConnectString();

	void setProperty(String key, String value);

	Context getContext();
}
