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

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map.Entry;

import org.eclipse.daanse.olap.api.Context;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.context.SQLLiteContext;
import org.opencube.junit5.context.TestingContext;
import org.opencube.junit5.dataloader.DataLoader;
import org.opencube.junit5.dbprovider.DatabaseProvider;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.olap.Util.PropertyList;

class TestTest {

	public static class ExampleDatabaseProvider implements DatabaseProvider {

		@Override
		public void close() throws IOException {

		}

		@Override
		public Entry<PropertyList, Context> activate() {
			PropertyList props = new PropertyList();
			props.put("x", "y");
			return new AbstractMap.SimpleEntry<>(props, new SQLLiteContext());
		}

		@Override
		public String getJdbcUrl() {
			return "jdbc:example";
		}

	}

	public static class ExampleDataLoader implements DataLoader {

		public ExampleDataLoader() {
		}
		@Override
		public boolean loadData(Context context) throws Exception {
			return true;
		}

	}

	@ParameterizedTest
	@ContextSource(database = ExampleDatabaseProvider.class, dataloader = ExampleDataLoader.class,propertyUpdater = AppandFoodMartCatalogAsFile.class)
	protected void runTest(TestingContext context) {
		System.out.println(context);
	}
}
