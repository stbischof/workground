/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/
package mondrian.xmla;

import static mondrian.enums.DatabaseProduct.getDatabaseProduct;
import static org.opencube.junit5.TestUtil.getDialect;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestContextWrapper;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.enums.DatabaseProduct;
import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.test.DiffRepository;

/**
 * Test suite for compatibility of Mondrian XMLA with Cognos8.2 connected via
 * Simba O2X bridge.
 *
 * @author Thiyagu, Shishir
 */

class XmlaCognosTest extends XmlaBaseTestCase {


    @AfterEach
    public void afterEach() {
        tearDown();
    }

    protected String filter(Connection connection,
        String testCaseName, String filename, String content)
    {
        if ("testWithFilter".equals(testCaseName)
            && filename.equals("response"))
        {
            Dialect dialect = getDialect(connection);
            switch (getDatabaseProduct(dialect.getDialectName())) {
            case DERBY:
            case VERTICA:
                content = content.replace(
                    "<Value xsi:type=\"xsd:double\">",
                    "<Value xsi:type=\"xsd:int\">");
                break;
            }
        } else if ("testCognosMDXSuiteHR_001".equals(testCaseName)
            && filename.equals("response")
            && getDatabaseProduct(getDialect(connection).getDialectName())
                .equals(DatabaseProduct.VERTICA))
        {
            content = content.replaceAll(
                "(<Cell CellOrdinal=\\\"(?:5|6|7|8|9|10|12|14|15|16|17|19|21|22|24)\\\">\\s*"
                + "<Value xsi:type=\\\"xsd):double", "$1:int");
        } else if ("testCognosMDXSuiteHR_002".equals(testCaseName)
            && filename.equals("response")
            && getDatabaseProduct(getDialect(connection).getDialectName())
                .equals(DatabaseProduct.VERTICA))
        {
            content = content.replaceAll(
                "(<Cell CellOrdinal=\\\"(?:1|2|3|4|5|7|9|10|11|12|13|14|16|18|19|20|21|22|23|25|27|28|29|30|32|34|35|37)\\\">\\s*"
                + "<Value xsi:type=\\\"xsd):double", "$1:int");
        }
        return content;
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCognosMDXSuiteHR_001(TestContextWrapper context) throws Exception {
        //java.sql.DriverManager.registerDriver(new XmlaOlap4jDriver());// finy out why this dies not happend automatically

        java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(context.getContext()));// finy out why this dies not happend automatically

        Dialect dialect = getDialect(context.createConnection());
        switch (getDatabaseProduct(dialect.getDialectName())) {
        case DERBY:
            // Derby gives right answer, but many cells have wrong xsi:type.
            return;
        }
        executeMDX(context.createConnection());
    }

//    void testCognosMDXSuiteHR_002() throws Exception {
//        Dialect dialect = TestContext.instance().getDialect();
//        switch (getDatabaseProduct(dialect.getDialectName())) {
//        case DERBY:
//            // Derby gives right answer, but many cells have wrong xsi:type.
//            return;
//        }
//        executeMDX();
//    }

//    void testCognosMDXSuiteSales_001() throws Exception {
//        executeMDX();
//    }

//    void testCognosMDXSuiteSales_002() throws Exception {
//        executeMDX();
//    }

//    void testCognosMDXSuiteSales_003() throws Exception {
//        executeMDX();
//    }

//    void testCognosMDXSuiteSales_004() throws Exception {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_003()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_005()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_006()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_007()
//        throws Exception
//    {
//        executeMDX();
//    }

    // disabled because runs out of memory/hangs
//    public void _testCognosMDXSuiteConvertedAdventureWorksToFoodMart_009()
//        throws Exception
//    {
//        executeMDX();
//    }

    // disabled because runs out of memory/hangs
//    public void _testCognosMDXSuiteConvertedAdventureWorksToFoodMart_012()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_013()
//        throws Exception
//    {
//        executeMDX();
//    }

    // disabled because runs out of memory/hangs
//    public void _testCognosMDXSuiteConvertedAdventureWorksToFoodMart_014()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_015()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_016()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_017()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_020()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_021()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_024()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_028()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testCognosMDXSuiteConvertedAdventureWorksToFoodMart_029()
//        throws Exception
//    {
//        executeMDX();
//    }

//    void testDimensionPropertyForPercentageIssue() throws Exception {
//        executeMDX();
//    }

//    void testNegativeSolveOrder() throws Exception {
//        executeMDX();
//    }

//    void testNonEmptyWithCognosCalcOneLiteral() throws Exception {
//        final BooleanProperty enableNonEmptyOnAllAxes =
//                MondrianProperties.instance().EnableNonEmptyOnAllAxis;
//        boolean nonEmptyAllAxesCurrentState = enableNonEmptyOnAllAxes.get();
//
//        final BooleanProperty enableNativeNonEmpty =
//                MondrianProperties.instance().EnableNativeNonEmpty;
//        boolean nativeNonemptyCurrentState = enableNativeNonEmpty.get();
//
//        try {
//            enableNonEmptyOnAllAxes.set(true);
//            enableNativeNonEmpty.set(false);
//            executeMDX();
//            if (Bug.BugMondrian446Fixed) {
//                enableNativeNonEmpty.set(true);
//                executeMDX();
//            }
//        } finally {
//            enableNativeNonEmpty.set(nativeNonemptyCurrentState);
//            enableNonEmptyOnAllAxes.set(nonEmptyAllAxesCurrentState);
//        }
//    }



//    void testCellProperties() throws Exception {
//        executeMDX();
//    }

//    void testCrossJoin() throws Exception {
//        executeMDX();
//    }

//    void testWithFilterOn3rdAxis() throws Exception {
//        executeMDX();
//    }

//    void testWithSorting() throws Exception {
//        executeMDX();
//    }

//    void testWithFilter() throws Exception {
//        if (getTestContext().getDialect().getDatabaseProduct()
//            == DatabaseProduct.ACCESS)
//        {
//            // Disabled because of bug on access: generates query with
//            // distinct-count even though access does not support it. Bug
//            // 2685902, "Mondrian generates invalid count distinct on access"
//            // logged.
//            return;
//        }
//        executeMDX();
//    }

//    void testWithAggregation() throws Exception {
//        executeMDX();
//    }

    private void executeMDX(Connection connection) throws Exception {
        String requestType = "EXECUTE";
        doTest(
            requestType, getDefaultRequestProperties(requestType),
                connection);
    }

    @Override
	protected DiffRepository getDiffRepos() {
        return DiffRepository.lookup(XmlaCognosTest.class);
    }

    @Override
	protected Class<? extends XmlaRequestCallback> getServletCallbackClass() {
        return null;
    }

    @Override
	protected String getSessionId(Action action) {
        return null;
    }
}
