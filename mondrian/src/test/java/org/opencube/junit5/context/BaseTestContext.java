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
import java.util.Properties;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.olap4j.OlapConnection;
import org.olap4j.OlapWrapper;
import org.opencube.junit5.propupdator.PropertyUpdater;

import mondrian.olap.DriverManager;
import mondrian.olap.Util;
import mondrian.olap.Util.PropertyList;
import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.rolap.RolapConnectionProperties;

public class BaseTestContext implements TestingContext {

	private Context context;
	private Util.PropertyList properties = new Util.PropertyList();

	@Override
	public void init(Entry<PropertyList, Context> contextEntry) {
		this.context = contextEntry.getValue();
		this.properties = Util.PropertyList.newInstance(contextEntry.getKey());
		init();

	}

	public void update(PropertyUpdater updater) {
		properties = updater.update(properties);
	}

	private void init() {

		properties.put(RolapConnectionProperties.Provider.name(), provider());

//		olapConnectString = "jdbc:mondrian:" + olapConnectString;
	}

	@Override
	public Connection createConnection() {

		if (context == null) {
			return DriverManager.getConnection(getJDBCConnectString(), null);
		}
		return DriverManager.getConnection(properties, null, context);

	}

	protected String getCatalogContent() {
		return "";
	}

	protected String provider() {
		return "mondrian";
	}

	/*
	 * must be done before getConection;
	 */
	public void setProperty(String key, boolean value) {

		properties.put(key, value ? "true" : "false");

	}

	@Override
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

    @Override
    public Context getContext() {
        return context;
    }

    @Override
	public OlapConnection createOlap4jConnection() throws SQLException {

		//final java.sql.Connection connection = java.sql.DriverManager.getConnection("jdbc:mondrian:" + getOlapConnectString());
        MondrianOlap4jDriver driver = new MondrianOlap4jDriver(context);
        final java.sql.Connection connection =  driver.connect("jdbc:mondrian:" + getOlapConnectString(), new Properties());
		return ((OlapWrapper) connection).unwrap(OlapConnection.class);
	}

	@Override
	public String getJDBCConnectString() {

		// may exist better ways
		return properties.get(RolapConnectionProperties.Jdbc.name());

	}

	@Override
	public String getOlapConnectString() {

		return properties.toString();
	}

	@Override
	public String toString() {

		return getJDBCConnectString();

//
//		Connection con = createConnection();
//		if (con == null||dataSource==null) {
//			return super.toString();
//		}
//		try {
//			return jdbcURL().orElse(		dataSource.getConnection().getMetaData().getDriverName());
//		} catch (SQLException e) {
//		return	super.toString();
//		}

	}

}
