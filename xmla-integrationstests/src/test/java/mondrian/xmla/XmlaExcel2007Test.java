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
import static org.opencube.junit5.TestUtil.assertQueryReturns;
import static org.opencube.junit5.TestUtil.getDialect;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestContextWrapper;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.test.DiffRepository;

/**
 * Test suite for compatibility of Mondrian XMLA with Excel 2007.
 *
 * @author Richard M. Emberson
 */
class XmlaExcel2007Test extends XmlaBaseTestCase {

    @Override
	protected String getSessionId(Action action) {
        return getSessionId("XmlaExcel2000Test", action);
    }

    static class Callback extends XmlaRequestCallbackImpl {
        Callback() {
            super("XmlaExcel2000Test");
        }
    }

    @Override
	protected Class<? extends XmlaRequestCallback> getServletCallbackClass() {
        return Callback.class;
    }

    @Override
	protected DiffRepository getDiffRepos() {
        return DiffRepository.lookup(XmlaExcel2007Test.class);
    }

    protected String filter(
        Connection connection,
        String testCaseName,
        String filename,
        String content)
    {
        if ((testCaseName.startsWith("testMemberPropertiesAndSlicer")
             || testCaseName.equals("testBugMondrian761"))
            && filename.equals("response"))
        {
            Dialect dialect = getDialect(connection);
            switch (getDatabaseProduct(dialect.getDialectName())) {
            case MYSQL:
            case MARIADB:
            case VERTICA:
                content =
                    foo(content, "Has_x0020_coffee_x0020_bar", "1", "true");
                content =
                    foo(content, "Has_x0020_coffee_x0020_bar", "0", "false");
                break;
            case ACCESS:
                content =
                    foo(content, "Has_x0020_coffee_x0020_bar", "1", "true");
                content =
                    foo(content, "Has_x0020_coffee_x0020_bar", "0", "false");
                content =
                    foo(content, "Store_x0020_Sqft", "23688", "23688.0");
                content = foo(
                    content, "Grocery_x0020_Sqft", "15337",
                    "15336.753169821777");
                content = foo(
                    content, "Frozen_x0020_Sqft", "5011", "5010.748098106934");
                content = foo(
                    content, "Meat_x0020_Sqft", "3340", "3340.4987320712894");
                content = foo(content, "Store_x0020_Sqft", "23598", "23598.0");
                content = foo(
                    content, "Grocery_x0020_Sqft", "14210",
                    "14210.378025591175");
                content = foo(
                    content, "Frozen_x0020_Sqft", "5633", "5632.5731846452945");
                content = foo(
                    content, "Meat_x0020_Sqft", "3755", "3755.0487897635303");
                break;
            }
        }
        return content;
    }

    @AfterEach
    public void afterEach() {
        tearDown();
    }


    private String foo(String content, String tag, String from, String to) {
        String start = "<" + tag + ">";
        String end = "</" + tag + ">";
        final String s = content.replace(
            start + from + end, start + to + end);
        assert !s.contains(start + from + end);
        return s;
    }

    /**
     * <p>Testcase for <a href="http://jira.pentaho.com/browse/MONDRIAN-679">
     * bug MONDRIAN-679, "VisualTotals gives ClassCastException when called via
     * XMLA"</a>.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void test01(TestContextWrapper context) {
        helperTest(context, false);
    }

    /**
     * Test that checks that (a) member properties are in correct format for
     * Excel 2007, (b) the slicer axis is in the correct format for Excel 2007.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testMemberPropertiesAndSlicer(TestContextWrapper context) {
        helperTestExpect(context, true);
    }

    /**
     * Test that executes MDSCHEMA_PROPERTIES with
     * {@link org.olap4j.metadata.Property.TypeFlag#MEMBER}.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testMdschemaPropertiesMember(TestContextWrapper context) {
        helperTest(context, true);
    }

    /**
     * Test that executes MDSCHEMA_PROPERTIES with
     * {@link org.olap4j.metadata.Property.TypeFlag#CELL}.
     *
     * @throws Exception on error
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testMdschemaPropertiesCell(TestContextWrapper context) {
        helperTest(context, true);
    }

    /**
     * Tests that mondrian can correctly answer the extra queries generated by
     * Excel 2007 in bug <a href="http://jira.pentaho.com/browse/MONDRIAN-726">
     * MONDRIAN-726, "Change 13509 is not Excel 2007 compatible"</a>.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testUniqueName(TestContextWrapper context) {
        Connection connection = context.createConnection();
        assertQueryReturns(connection,
            "WITH MEMBER [Store].[XL_PT0] AS 'strtomember(\"[Store].[USA].[CA]\").UniqueName' SELECT {[Store].[XL_PT0]} ON 0 FROM \n"
            + "[HR] CELL PROPERTIES VALUE ",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Store].[XL_PT0]}\n"
            + "Row #0: [Store].[USA].[CA]\n");
        assertQueryReturns(connection,
            "WITH MEMBER [Store].[XL_PT0] AS 'strtomember(\"[Store].[All Stores].[USA].[CA]\").UniqueName' SELECT {[Store].[XL_PT0]} ON 0 FROM \n"
            + "[HR] CELL PROPERTIES VALUE ",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Store].[XL_PT0]}\n"
            + "Row #0: [Store].[USA].[CA]\n");
    }

    /**
     * Tests that executed MDX query with CELL PROPERTIES included; bug
     * <a href="http://jira.pentaho.com/browse/MONDRIAN-708">MONDRIAN-708,
     * "After change 13351 all Excel pivots fail to update. CellInfo element in
     * XMLA response is wrong"</a>.
     *
     * <p>CellInfo element should always contain all requested cell properties.
     * Cell itself can contain fewer properties than requested.
     *
     * <p>Currently most properties are not implemented or not defined.
     * If they get implemented then test needs to be changed.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCellInfo(TestContextWrapper context) {
        helperTest(context, true);
    }

    /**
     * <p>Testcase for <a href="http://jira.pentaho.com/browse/MONDRIAN-761">
     * bug MONDRIAN-761, "VisualTotalMember cannot be cast to
     * RolapCubeMember"</a>.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBugMondrian761(TestContextWrapper context) {
        helperTest(context, false);
    }
}
