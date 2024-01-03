/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.xmla;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.eclipse.daanse.olap.api.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestContextWrapper;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.olap.Util;
import mondrian.olap.Util.PropertyList;
import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.rolap.RolapConnectionProperties;
import mondrian.test.DiffRepository;

/**
 * This test creates 2 catalogs and constraints on one of them.
 * Then it runs a few queries to check that the filtering
 * occurs as expected.
 */
@Disabled
@Deprecated
class XmlaMetaDataConstraintsTest extends XmlaBaseTestCase {

    @BeforeEach
    public void beforeEach() throws ClassNotFoundException {
        Class.forName(MondrianOlap4jDriver.class.getName());
    }

    @AfterEach
    public void afterEach() {
        tearDown();
    }


    @Override
	protected Map<String, String> getCatalogNameUrls(Connection connection) {
        if (catalogNameUrls == null) {
            catalogNameUrls = new TreeMap<>();

            String catalog = connection.getCatalogName();

            // read the catalog and copy it to another temp file.
            File outputFile1 = null;
            File outputFile2 = null;
            try {
                // Output
                outputFile1 = File.createTempFile("cat1", ".xml");
                outputFile2 = File.createTempFile("cat2", ".xml");
                outputFile1.deleteOnExit();
                outputFile2.deleteOnExit();
                BufferedWriter bw1 =
                    new BufferedWriter(new FileWriter(outputFile1));
                BufferedWriter bw2 =
                    new BufferedWriter(new FileWriter(outputFile2));

                // Input
                DataInputStream in =
                    new DataInputStream(Util.readVirtualFile(catalog));
                BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));

                String strLine;
                while ((strLine = br.readLine()) != null)   {
                    bw1.write(
                        strLine.replaceAll("FoodMart", "FoodMart1schema"));
                    bw1.newLine();
                    bw2.write(
                        strLine.replaceAll("FoodMart", "FoodMart2schema"));
                    bw2.newLine();
                }

                in.close();
                bw1.close();
                bw2.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            catalogNameUrls.put("FoodMart1", outputFile1.getAbsolutePath());
            catalogNameUrls.put("FoodMart2", outputFile2.getAbsolutePath());
        }
        return catalogNameUrls;
    }

    @Override
	protected String filterConnectString(String original) {
        PropertyList props = Util.parseConnectString(original);
        if (props.get(RolapConnectionProperties.Catalog.name()) != null) {
            props.remove(RolapConnectionProperties.Catalog.name());
        }
        return props.toString();
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testDBSchemataFiltered(TestContextWrapper context) throws Exception {
        Connection connection = context.createConnection();
        doTest(
            RowsetDefinition.DBSCHEMA_SCHEMATA.name(), "FoodMart2", connection);
        doTest(
            RowsetDefinition.DBSCHEMA_SCHEMATA.name(), "FoodMart1", connection);
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testDBSchemataFilteredByRestraints(TestContextWrapper context) throws Exception {
        Connection connection = context.createConnection();
        doTest(
            RowsetDefinition.DBSCHEMA_SCHEMATA.name(), "FoodMart2", connection);
        doTest(
            RowsetDefinition.DBSCHEMA_SCHEMATA.name(), "FoodMart1", connection);
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCatalogsFiltered(TestContextWrapper context) throws Exception {
        //Catalog in the properties has not filter DBSCHEMA_CATALOGS. Only if is set in restrictions.
        Connection connection = context.createConnection();
        doTest(
            RowsetDefinition.DBSCHEMA_CATALOGS.name(), "FoodMart2", connection);
        doTest(
            RowsetDefinition.DBSCHEMA_CATALOGS.name(), "FoodMart1", connection);
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCatalogsFilteredByRestraints(TestContextWrapper context) throws Exception {
        Connection connection = context.createConnection();
        doTest(
            RowsetDefinition.DBSCHEMA_CATALOGS.name(), "FoodMart2", connection);
        doTest(
            RowsetDefinition.DBSCHEMA_CATALOGS.name(), "FoodMart1", connection);
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCubesFiltered(TestContextWrapper context) throws Exception {
        Connection connection = context.createConnection();
        doTest(
            RowsetDefinition.MDSCHEMA_CUBES.name(), "FoodMart2", connection);
        doTest(
            RowsetDefinition.MDSCHEMA_CUBES.name(), "FoodMart1", connection);
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCubesFilteredByRestraints(TestContextWrapper context) throws Exception {
        Connection connection = context.createConnection();
        doTest(
            RowsetDefinition.MDSCHEMA_CUBES.name(), "FoodMart2", connection);
        doTest(
            RowsetDefinition.MDSCHEMA_CUBES.name(), "FoodMart1", connection);
    }

    private void doTest(String requestType, String catalog, Connection connection)
        throws Exception
    {
        Properties props = new Properties();
        props.setProperty(REQUEST_TYPE_PROP, requestType);
        props.setProperty(DATA_SOURCE_INFO_PROP, DATA_SOURCE_INFO);
        props.setProperty(CATALOG_NAME_PROP, catalog);
        java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(connection.getContext()));
        try {
            doTest(requestType, props, connection);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new Exception(t);
        }
    }

    @Override
	protected DiffRepository getDiffRepos() {
        return DiffRepository.lookup(XmlaMetaDataConstraintsTest.class);
    }

    @Override
	protected Class<? extends XmlaRequestCallback> getServletCallbackClass() {
        return null;
    }

    @Override
	protected String getSessionId(Action action) {
        throw new UnsupportedOperationException();
    }
}
