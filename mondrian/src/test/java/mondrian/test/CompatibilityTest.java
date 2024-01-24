/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2005-2005 SAS Institute, Inc.
// Copyright (C) 2006-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.test;

import static mondrian.enums.DatabaseProduct.getDatabaseProduct;
import static org.eclipse.daanse.olap.api.result.Olap4jUtil.discard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import mondrian.rolap.SchemaModifiers;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.result.Cell;
import org.eclipse.daanse.olap.api.result.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.TestUtil;
import org.opencube.junit5.context.TestContext;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

import mondrian.enums.DatabaseProduct;
import mondrian.olap.MondrianProperties;
import mondrian.olap.Util;


/**
 * Test for MDX syntax compatibility with Microsoft and SAS servers.
 *
 * <p>There is no MDX spec document per se, so compatibility with de facto
 * standards from the major vendors is important. Uses the FoodMart
 * database.</p>
 *
 * @see Ssas2005CompatibilityTest
 * @author sasebb
 * @since March 30, 2005
 */
class CompatibilityTest {
	private PropertySaver5 propSaver;
    private static boolean originalNeedDimensionPrefix;
    private final MondrianProperties props = MondrianProperties.instance();

    @BeforeAll
    public static void beforeAll() {
    	originalNeedDimensionPrefix = MondrianProperties.instance().NeedDimensionPrefix.get();
    }

	@BeforeEach
	public void beforeEach() {
		propSaver = new PropertySaver5();
	}

	@AfterEach
	public void afterEach() {
		propSaver.reset();
	}

    /**
     * Cube names are case insensitive.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testCubeCase(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        String queryFrom = "select {[Measures].[Unit Sales]} on columns from ";
        String result =
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[Unit Sales]}\n"
            + "Row #0: 266,773\n";

        TestUtil.assertQueryReturns(connection, queryFrom + "[Sales]", result);
        TestUtil.assertQueryReturns(connection, queryFrom + "[SALES]", result);
        TestUtil.assertQueryReturns(connection, queryFrom + "[sAlEs]", result);
        TestUtil.assertQueryReturns(connection, queryFrom + "[sales]", result);
    }

    /**
     * Brackets around cube names are optional.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testCubeBrackets(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        String queryFrom = "select {[Measures].[Unit Sales]} on columns from ";
        String result =
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[Unit Sales]}\n"
            + "Row #0: 266,773\n";

        TestUtil.assertQueryReturns(connection, queryFrom + "Sales", result);
        TestUtil.assertQueryReturns(connection, queryFrom + "SALES", result);
        TestUtil.assertQueryReturns(connection, queryFrom + "sAlEs", result);
        TestUtil.assertQueryReturns(connection, queryFrom + "sales", result);
    }

    /**
     * See how we are at diagnosing reserved words.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testReservedWord(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
    	TestUtil.assertAxisThrows(
    		connection,
            "with member [Measures].ordinal as '1'\n"
            + " select {[Measures].ordinal} on columns from Sales",
            "Syntax error");
    	TestUtil.assertQueryReturns(
    		connection,
            "with member [Measures].[ordinal] as '1'\n"
            + " select {[Measures].[ordinal]} on columns from Sales",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[ordinal]}\n"
            + "Row #0: 1\n");
    }

    /**
     * Dimension names are case insensitive.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testDimensionCase(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        checkAxis(connection, "[Measures].[Unit Sales]", "[Measures].[Unit Sales]");
        checkAxis(connection, "[Measures].[Unit Sales]", "[MEASURES].[Unit Sales]");
        checkAxis(connection, "[Measures].[Unit Sales]", "[mEaSuReS].[Unit Sales]");
        checkAxis(connection, "[Measures].[Unit Sales]", "[measures].[Unit Sales]");

        checkAxis(connection, "[Customers].[All Customers]", "[Customers].[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "[CUSTOMERS].[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "[cUsToMeRs].[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "[customers].[All Customers]");
    }

    /**
     * Brackets around dimension names are optional.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testDimensionBrackets(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        checkAxis(connection, "[Measures].[Unit Sales]", "Measures.[Unit Sales]");
        checkAxis(connection, "[Measures].[Unit Sales]", "MEASURES.[Unit Sales]");
        checkAxis(connection, "[Measures].[Unit Sales]", "mEaSuReS.[Unit Sales]");
        checkAxis(connection, "[Measures].[Unit Sales]", "measures.[Unit Sales]");

        checkAxis(connection, "[Customers].[All Customers]", "Customers.[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "CUSTOMERS.[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "cUsToMeRs.[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "customers.[All Customers]");
    }

    /**
     * Member names are case insensitive.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testMemberCase(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        checkAxis(connection, "[Measures].[Unit Sales]", "[Measures].[UNIT SALES]");
        checkAxis(connection, "[Measures].[Unit Sales]", "[Measures].[uNiT sAlEs]");
        checkAxis(connection, "[Measures].[Unit Sales]", "[Measures].[unit sales]");

        checkAxis(connection, "[Measures].[Profit]", "[Measures].[Profit]");
        checkAxis(connection, "[Measures].[Profit]", "[Measures].[pRoFiT]");
        checkAxis(connection, "[Measures].[Profit]", "[Measures].[PROFIT]");
        checkAxis(connection, "[Measures].[Profit]", "[Measures].[profit]");

        checkAxis(connection, "[Customers].[All Customers]", "[Customers].[All Customers]");
        checkAxis(connection, "[Customers].[All Customers]", "[Customers].[ALL CUSTOMERS]");
        checkAxis(connection, "[Customers].[All Customers]", "[Customers].[aLl CuStOmErS]");
        checkAxis(connection, "[Customers].[All Customers]", "[Customers].[all customers]");

        checkAxis(connection, "[Customers].[Mexico]", "[Customers].[Mexico]");
        checkAxis(connection, "[Customers].[Mexico]", "[Customers].[MEXICO]");
        checkAxis(connection, "[Customers].[Mexico]", "[Customers].[mExIcO]");
        checkAxis(connection, "[Customers].[Mexico]", "[Customers].[mexico]");
    }

    /**
     * Calculated member names are case insensitive.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testCalculatedMemberCase(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        propSaver.set(MondrianProperties.instance().CaseSensitive, false);
        TestUtil.assertQueryReturns(
    		connection,
            "with member [Measures].[CaLc] as '1'\n"
            + " select {[Measures].[CaLc]} on columns from Sales",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[CaLc]}\n"
            + "Row #0: 1\n");
        TestUtil.assertQueryReturns(
    		connection,
            "with member [Measures].[CaLc] as '1'\n"
            + " select {[Measures].[cAlC]} on columns from Sales",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[CaLc]}\n"
            + "Row #0: 1\n");
        TestUtil.assertQueryReturns(
    		connection,
            "with member [mEaSuReS].[CaLc] as '1'\n"
            + " select {[MeAsUrEs].[cAlC]} on columns from Sales",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[CaLc]}\n"
            + "Row #0: 1\n");
    }

    /**
     * Solve order is case insensitive.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testSolveOrderCase(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        checkSolveOrder(connection, "SOLVE_ORDER");
        checkSolveOrder(connection, "SoLvE_OrDeR");
        checkSolveOrder(connection, "solve_order");
    }

    private void checkSolveOrder(Connection connection, String keyword) {
        TestUtil.assertQueryReturns(
    		connection,
            "WITH\n"
            + "   MEMBER [Store].[StoreCalc] as '0', " + keyword + "=0\n"
            + "   MEMBER [Product].[ProdCalc] as '1', " + keyword + "=1\n"
            + "SELECT\n"
            + "   { [Product].[ProdCalc] } ON columns,\n"
            + "   { [Store].[StoreCalc] } ON rows\n"
            + "FROM Sales",

            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Product].[ProdCalc]}\n"
            + "Axis #2:\n"
            + "{[Store].[StoreCalc]}\n"
            + "Row #0: 1\n");
    }

    /**
     * Brackets around member names are optional.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testMemberBrackets(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        checkAxis(connection, "[Measures].[Profit]", "[Measures].Profit");
        checkAxis(connection, "[Measures].[Profit]", "[Measures].pRoFiT");
        checkAxis(connection, "[Measures].[Profit]", "[Measures].PROFIT");
        checkAxis(connection, "[Measures].[Profit]", "[Measures].profit");

        checkAxis(
    		connection,
            "[Customers].[Mexico]",
            "[Customers].Mexico");
        checkAxis(
    		connection,
            "[Customers].[Mexico]",
            "[Customers].MEXICO");
        checkAxis(
    		connection,
            "[Customers].[Mexico]",
            "[Customers].mExIcO");
        checkAxis(
    		connection,
            "[Customers].[Mexico]",
            "[Customers].mexico");
    }

    /**
     * Hierarchy names of the form [Dim].[Hier], [Dim.Hier], and
     * Dim.Hier are accepted.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testHierarchyNames(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        checkAxis(connection, "[Customers].[All Customers]", "[Customers].[All Customers]");
        checkAxis(
    		connection,
            "[Customers].[All Customers]",
            "[Customers].[Customers].[All Customers]");
        checkAxis(
    		connection,
            "[Customers].[All Customers]",
            "Customers.[Customers].[All Customers]");
        checkAxis(
    		connection,
            "[Customers].[All Customers]",
            "[Customers].Customers.[All Customers]");
        if (false) {
            // don't know if this makes sense
            checkAxis(
        		connection,
                "[Customers].[All Customers]",
                "[Customers.Customers].[All Customers]");
        }
    }

    private void checkAxis(Connection connection, String result, String expression) {
        assertEquals(
            result, TestUtil.executeSingletonAxis(connection, expression).toString());
    }

    protected boolean isDefaultNullMemberRepresentation() {
        return MondrianProperties.instance().NullMemberRepresentation.get()
                .equals("#null");
    }

    /**
     * Tests that a #null member on a Hiearchy Level of type String can
     * still be looked up when case sensitive is off.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testCaseInsensitiveNullMember(TestContext foodMartContext) throws SQLException, IOException {
    	Connection connection = foodMartContext.getConnection();
        DataSource dataSource = connection.getDataSource();
        final Dialect dialect = foodMartContext.getDialect();
        if (getDatabaseProduct(dialect.getDialectName()) == DatabaseProduct.LUCIDDB) {
            // TODO jvs 29-Nov-2006:  LucidDB is strict about
            // null literals (type can't be inferred in this context);
            // maybe enhance the inline table to use the columndef
            // types to apply a CAST.
            return;
        }
        if (!isDefaultNullMemberRepresentation()) {
            return;
        }
        /*
        final String cubeName = "Sales_inline";
        String baseSchema = TestUtil.getRawSchema(foodMartContext);
        String schema = SchemaUtil.getSchema(
    		baseSchema,
            null,
            "<Cube name=\"" + cubeName + "\">\n"
            + "  <Table name=\"sales_fact_1997\"/>\n"
            + "  <DimensionUsage name=\"Time\" source=\"Time\" foreignKey=\"time_id\"/>\n"
            + "  <Dimension name=\"Alternative Promotion\" foreignKey=\"promotion_id\">\n"
            + "    <Hierarchy hasAll=\"true\" primaryKey=\"promo_id\">\n"
            + "      <InlineTable alias=\"alt_promotion\">\n"
            + "        <ColumnDefs>\n"
            + "          <ColumnDef name=\"promo_id\" type=\"Numeric\"/>\n"
            + "          <ColumnDef name=\"promo_name\" type=\"String\"/>\n"
            + "        </ColumnDefs>\n"
            + "        <Rows>\n"
            + "          <Row>\n"
            + "            <Value column=\"promo_id\">0</Value>\n"
            + "            <Value column=\"promo_name\">Promo0</Value>\n"
            + "          </Row>\n"
            + "          <Row>\n"
            + "            <Value column=\"promo_id\">1</Value>\n"
            + "          </Row>\n"
            + "        </Rows>\n"
            + "      </InlineTable>\n"
            + "      <Level name=\"Alternative Promotion\" column=\"promo_name\" uniqueMembers=\"true\"/> \n"
            + "    </Hierarchy>\n"
            + "  </Dimension>\n"
            + "  <Measure name=\"Unit Sales\" column=\"unit_sales\" aggregator=\"sum\"\n"
            + "      formatString=\"Standard\" visible=\"false\"/>\n"
            + "  <Measure name=\"Store Sales\" column=\"store_sales\" aggregator=\"sum\"\n"
            + "      formatString=\"#,###.00\"/>\n"
            + "</Cube>",
            null,
            null,
            null,
            null);
         */
        TestUtil.withSchema(foodMartContext, SchemaModifiers.Ssas2005CompatibilityTestModifier4::new);
        connection = foodMartContext.getConnection();

        // This test should work irrespective of the case-sensitivity setting.
        discard(props.CaseSensitive.get());

        TestUtil.assertQueryReturns(
    		connection,
            "select {[Measures].[Unit Sales]} ON COLUMNS,\n"
            + "  {[Alternative Promotion].[#null]} ON ROWS \n"
            + "  from [Sales_inline]",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[Unit Sales]}\n"
            + "Axis #2:\n"
            + "{[Alternative Promotion].[#null]}\n"
            + "Row #0: \n");
    }

    /**
     * Tests that data in Hierarchy.Level attribute "nameColumn" can be null.
     * This will map to the #null memeber.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNullNameColumn(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        DataSource dataSource = connection.getDataSource();
        final Dialect dialect = foodMartContext.getDialect();
        switch (getDatabaseProduct(dialect.getDialectName())) {
        case LUCIDDB:
            // TODO jvs 29-Nov-2006:  See corresponding comment in
            // testCaseInsensitiveNullMember
            return;
        case HSQLDB:
            // This test exposes a bug in hsqldb. The following query should
            // return 1 row, but returns none.
            //
            // select "alt_promotion"."promo_id" as "c0",
            //   "alt_promotion"."promo_name" as "c1"
            // from (
            //    select 0 as "promo_id", null as "promo_name"
            //    from "days" where "day" = 1
            //    union all
            //    select 1 as "promo_id", 'Promo1' as "promo_name"
            //    from "days" where "day" = 1) as "alt_promotion"
            // where UPPER("alt_promotion"."promo_name") = UPPER('Promo1')
            // group by "alt_promotion"."promo_id",
            //    "alt_promotion"."promo_name"
            // order by
            //   CASE WHEN "alt_promotion"."promo_id" IS NULL THEN 1 ELSE 0 END,
            //   "alt_promotion"."promo_id" ASC
            return;
        }
        if (!isDefaultNullMemberRepresentation()) {
            return;
        }
        final String cubeName = "Sales_inline";
        /*
        String schema = SchemaUtil.getSchema(
    		baseSchema,
            null,
            "<Cube name=\"" + cubeName + "\">\n"
            + "  <Table name=\"sales_fact_1997\"/>\n"
            + "  <DimensionUsage name=\"Time\" source=\"Time\" foreignKey=\"time_id\"/>\n"
            + "  <Dimension name=\"Alternative Promotion\" foreignKey=\"promotion_id\">\n"
            + "    <Hierarchy hasAll=\"true\" primaryKey=\"promo_id\">\n"
            + "      <InlineTable alias=\"alt_promotion\">\n"
            + "        <ColumnDefs>\n"
            + "          <ColumnDef name=\"promo_id\" type=\"Numeric\"/>\n"
            + "          <ColumnDef name=\"promo_name\" type=\"String\"/>\n"
            + "        </ColumnDefs>\n"
            + "        <Rows>\n"
            + "          <Row>\n"
            + "            <Value column=\"promo_id\">0</Value>\n"
            + "          </Row>\n"
            + "          <Row>\n"
            + "            <Value column=\"promo_id\">1</Value>\n"
            + "            <Value column=\"promo_name\">Promo1</Value>\n"
            + "          </Row>\n"
            + "        </Rows>\n"
            + "      </InlineTable>\n"
            + "      <Level name=\"Alternative Promotion\" column=\"promo_id\" nameColumn=\"promo_name\" uniqueMembers=\"true\"/> \n"
            + "    </Hierarchy>\n"
            + "  </Dimension>\n"
            + "  <Measure name=\"Unit Sales\" column=\"unit_sales\" aggregator=\"sum\"\n"
            + "      formatString=\"Standard\" visible=\"false\"/>\n"
            + "  <Measure name=\"Store Sales\" column=\"store_sales\" aggregator=\"sum\"\n"
            + "      formatString=\"#,###.00\"/>\n"
            + "</Cube>", null, null, null, null);
        */
        TestUtil.withSchema(foodMartContext, SchemaModifiers.CompatibilityTestModifier::new);
        connection = foodMartContext.getConnection();

        TestUtil.assertQueryReturns(
    		connection,
            "select {"
            + "[Alternative Promotion].[#null], "
            + "[Alternative Promotion].[Promo1]} ON COLUMNS\n"
            + "from [" + cubeName + "] ",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Alternative Promotion].[#null]}\n"
            + "{[Alternative Promotion].[Promo1]}\n"
            + "Row #0: 195,448\n"
            + "Row #0: \n");
    }

    /**
     * Tests that NULL values sort last on all platforms. On some platforms,
     * such as MySQL, NULLs naturally come before other values, so we have to
     * generate a modified ORDER BY clause.
      */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNullCollation(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        DataSource dataSource = connection.getDataSource();
        final Dialect dialect = foodMartContext.getDialect();
        if (dialect.supportsGroupByExpressions()) {
            // Derby does not support expressions in the GROUP BY clause,
            // therefore this testing strategy of using an expression for the
            // store key won't work. Give the test a bye.
            return;
        }
        final String cubeName = "Store_NullsCollation";
        TestUtil.withSchema(foodMartContext, SchemaModifiers.CompatibilityTestModifier2::new);
        connection = foodMartContext.getConnection();

        TestUtil.assertQueryReturns(
    		connection,
            "select { [Measures].[Store Sqft] } on columns,\n"
            + " NON EMPTY topcount(\n"
            + "    {[Store].[Store Name].members},\n"
            + "    5,\n"
            + "    [measures].[store sqft]) on rows\n"
            + "from [" + cubeName + "] ",
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[Store Sqft]}\n"
            + "Axis #2:\n"
            + "{[Store].[Store 3]}\n"
            + "{[Store].[Store 18]}\n"
            + "{[Store].[Store 9]}\n"
            + "{[Store].[Store 10]}\n"
            + "{[Store].[Store 20]}\n"
            + "Row #0: 39,696\n"
            + "Row #1: 38,382\n"
            + "Row #2: 36,509\n"
            + "Row #3: 34,791\n"
            + "Row #4: 34,452\n");
    }

    /**
     * Tests that property names are case sensitive iff the
     * "mondrian.olap.case.sensitive" property is set.
     *
     * <p>The test does not alter this property: for testing coverage, we assume
     * that you run the test once with mondrian.olap.case.sensitive=true,
     * and once with mondrian.olap.case.sensitive=false.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testPropertyCaseSensitivity(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        boolean caseSensitive = props.CaseSensitive.get();

        // A user-defined property of a member.
        TestUtil.assertExprReturns(
    		connection,
            "[Store].[USA].[CA].[Beverly Hills].[Store 6].Properties(\"Store Type\")",
            "Gourmet Supermarket");

        if (caseSensitive) {
        	TestUtil.assertExprThrows(
        		connection,
                "[Store].[USA].[CA].[Beverly Hills].[Store 6].Properties(\"store tYpe\")",
                "Property 'store tYpe' is not valid for member '[Store].[USA].[CA].[Beverly Hills].[Store 6]'");
        } else {
        	TestUtil.assertExprReturns(
        		connection,
                "[Store].[USA].[CA].[Beverly Hills].[Store 6].Properties(\"store tYpe\")",
                "Gourmet Supermarket");
        }

        // A builtin property of a member.
        TestUtil.assertExprReturns(
    		connection,
            "[Store].[USA].[CA].[Beverly Hills].[Store 6].Properties(\"LEVEL_NUMBER\")",
            "4");
        if (caseSensitive) {
        	TestUtil.assertExprThrows(
        		connection,
                "[Store].[USA].[CA].[Beverly Hills].[Store 6].Properties(\"Level_Number\")",
                "Property 'store tYpe' is not valid for member '[Store].[USA].[CA].[Beverly Hills].[Store 6]'");
        } else {
        	TestUtil.assertExprReturns(
        		connection,
                "[Store].[USA].[CA].[Beverly Hills].[Store 6].Properties(\"Level_Number\")",
                "4");
        }

        // Cell properties.
        Result result = TestUtil.executeQuery(
    		connection,
            "select {[Measures].[Unit Sales],[Measures].[Store Sales]} on columns,\n"
            + " {[Gender].[M]} on rows\n"
            + "from Sales");
        Cell cell = result.getCell(new int[]{0, 0});
        assertEquals("135,215", cell.getPropertyValue("FORMATTED_VALUE"));
        if (caseSensitive) {
            assertNull(cell.getPropertyValue("Formatted_Value"));
        } else {
            assertEquals("135,215", cell.getPropertyValue("Formatted_Value"));
        }
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testWithDimensionPrefix(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        assertAxisWithDimensionPrefix(connection, true);
        assertAxisWithDimensionPrefix(connection, false);
    }

    private void assertAxisWithDimensionPrefix(Connection connection, boolean prefixNeeded) {
        propSaver.set(
            props.NeedDimensionPrefix,
            prefixNeeded);
        TestUtil.assertAxisReturns(connection, "[Gender].[M]", "[Gender].[M]");
        TestUtil.assertAxisReturns(connection, "[Gender].[All Gender].[M]", "[Gender].[M]");
        TestUtil.assertAxisReturns(connection, "[Store].[USA]", "[Store].[USA]");
        TestUtil.assertAxisReturns(connection, "[Store].[All Stores].[USA]", "[Store].[USA]");
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testWithNoDimensionPrefix(TestContext foodMartContext) {
    	Connection connection = foodMartContext.getConnection();
        propSaver.set(
            props.NeedDimensionPrefix,
            false);
        TestUtil.assertAxisReturns(connection, "{[M]}", "[Gender].[M]");
        TestUtil.assertAxisReturns(connection, "{M}", "[Gender].[M]");
        TestUtil.assertAxisReturns(connection, "{[USA].[CA]}", "[Store].[USA].[CA]");
        TestUtil.assertAxisReturns(connection, "{USA.CA}", "[Store].[USA].[CA]");
        propSaver.set(
            props.NeedDimensionPrefix,
            true);
        TestUtil.assertAxisThrows(
    		connection,
            "{[M]}",
            "Mondrian Error:MDX object '[M]' not found in cube 'Sales'");
        TestUtil.assertAxisThrows(
    		connection,
            "{M}",
            "Mondrian Error:MDX object 'M' not found in cube 'Sales'");
        TestUtil.assertAxisThrows(
    		connection,
            "{[USA].[CA]}",
            "Mondrian Error:MDX object '[USA].[CA]' not found in cube 'Sales'");
        TestUtil.assertAxisThrows(
    		connection,
            "{USA.CA}",
            "Mondrian Error:MDX object 'USA.CA' not found in cube 'Sales'");
    }
}
