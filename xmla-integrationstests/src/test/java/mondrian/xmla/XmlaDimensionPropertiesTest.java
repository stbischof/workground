/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2005-2018 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.xmla;

import java.util.Properties;

import org.eclipse.daanse.olap.api.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestContextWrapper;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.test.DiffRepository;

/**
 * Test of dimension properties in xmla response.
 * Checks each property is added to its own hierarchy.
 *  - fix for MONDRIAN-2302 issue.
 *
 * @author Yury_Bakhmutski.
 */
class XmlaDimensionPropertiesTest extends XmlaBaseTestCase {

	@BeforeEach
    public void beforeEach() throws ClassNotFoundException {
        Class.forName(MondrianOlap4jDriver.class.getName());
    }

    @AfterEach
    public void afterEach() {
        tearDown();
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testOneHierarchyProperties(TestContextWrapper context) throws Exception {
        executeTest(context.createConnection(), "HR");
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testTwoHierarchiesProperties(TestContextWrapper context) throws Exception {
        executeTest(context.createConnection(),"HR");
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testMondrian2342(TestContextWrapper context) throws Exception {
        executeTest(context.createConnection(), "Sales");
    }

    private void executeTest(Connection connection, String cubeName) throws Exception {
        //TestContext context = getTestContext().withCube(cubeName);
        String requestType = "EXECUTE";
        Properties props = getDefaultRequestProperties(requestType);
        props.setProperty(CUBE_NAME_PROP, cubeName);
        java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(connection.getContext()));
        doTest( requestType, props, connection);
    }

    @Override
    protected DiffRepository getDiffRepos() {
        return DiffRepository.lookup(XmlaDimensionPropertiesTest.class);
    }

    @Override
    protected Class<? extends XmlaRequestCallback> getServletCallbackClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getSessionId(Action action) {
        throw new UnsupportedOperationException();
    }
}
