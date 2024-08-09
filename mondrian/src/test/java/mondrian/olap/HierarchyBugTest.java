/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2003-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// All Rights Reserved.
//
// remberson, Jan 31, 2006
*/
package mondrian.olap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.opencube.junit5.TestUtil.withSchema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.api.query.component.AxisOrdinal;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.result.Axis;
import org.eclipse.daanse.olap.api.result.Position;
import org.eclipse.daanse.olap.api.result.Result;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.DimensionTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.TableR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.HierarchyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.LevelRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.PrivateDimensionRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.modifier.record.RDbMappingSchemaModifier;
import org.eclipse.daanse.rolap.mapping.modifier.AbstractMappingModifier;
import org.eclipse.daanse.rolap.mapping.modifier.PojoMappingModifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.TestUtil;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

import mondrian.rolap.RolapConnectionPropsR;

class HierarchyBugTest {


	@BeforeEach
	public void beforeEach() {

	}

	@AfterEach
	public void afterEach() {
		SystemWideProperties.instance().populateInitial();
	}

    /**
     * This is code that demonstrates a bug that appears when using
     * JPivot with the current version of Mondrian. With the previous
     * version of Mondrian (and JPivot), pre compilation Mondrian,
     * this was not a bug (or at least Mondrian did not have a null
     * hierarchy).
     * Here the Time dimension is not returned in axis == 0, rather
     * null is returned. This causes a NullPointer exception in JPivot
     * when it tries to access the (null) hierarchy's name.
     * If the Time hierarchy is miss named in the query string, then
     * the parse ought to pick it up.
     **/
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNoHierarchy(Context foodMartContext) {
        String queryString =
            "select NON EMPTY "
            + "Crossjoin(Hierarchize(Union({[Time].[Time].LastSibling}, "
            + "[Time].[Time].LastSibling.Children)), "
            + "{[Measures].[Unit Sales],      "
            + "[Measures].[Store Cost]}) ON columns, "
            + "NON EMPTY Hierarchize(Union({[Store].[All Stores]}, "
            + "[Store].[All Stores].Children)) ON rows "
            + "from [Sales]";

        Connection conn = foodMartContext.getConnection();
        Query query = conn.parseQuery(queryString);

        String failStr = null;
        int len = query.getAxes().length;
        for (int i = 0; i < len; i++) {
            Hierarchy[] hs =
                query.getMdxHierarchiesOnAxis(
                    AxisOrdinal.StandardAxisOrdinal.forLogicalOrdinal(i));
            if (hs == null) {
            } else {
                for (Hierarchy h : hs) {
                    // This should NEVER be null, but it is.
                    if (h == null) {
                        failStr =
                            "Got a null Hierarchy, "
                            + "Should be Time Hierarchy";
                    }
                }
            }
        }
        if (failStr != null) {
            fail(failStr);
        }
    }

    /**
     * Test cases for <a href="http://jira.pentaho.com/browse/MONDRIAN-1126">
     * MONDRIAN-1126:
     * member getHierarchy vs. level.getHierarchy differences in Time Dimension
     * </a>
     */
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
	void testNamesIdentitySsasCompatibleTimeHierarchy(Context foodMartContext) {

        SystemWideProperties.instance().SsasCompatibleNaming = true;
        String mdxTime = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time].[Time].[Year].Members ON ROWS\n"
            + "FROM [Sales]";
       Connection conn= foodMartContext.getConnection();


        Result resultTime = TestUtil.executeQuery(conn, mdxTime);
        verifyMemberLevelNamesIdentityMeasureAxis(
            resultTime.getAxes()[0], "[Measures]");
        verifyMemberLevelNamesIdentityDimAxis(
            resultTime.getAxes()[1], "[Time].[Time]");

TestUtil.flushSchemaCache(conn);
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasCompatibleWeeklyHierarchy(Context foodMartContext) {
        SystemWideProperties.instance().SsasCompatibleNaming = true;
        String mdxWeekly = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time].[Weekly].[Year].Members ON ROWS\n"
            + "FROM [Sales]";

       // Fresh sets this before get new Conn
       //  RolapConnectionProperties.UseSchemaPool.name(), false);
       //foodMartContext.setProperty(RolapConnectionProperties.UseSchemaPool.name(), Boolean.toString(false));

        Connection conn = foodMartContext.getConnection(new RolapConnectionPropsR(
            List.of(), false, Locale.getDefault(), -1, TimeUnit.SECONDS, Optional.empty(), Optional.empty()
        ));

        Result resultWeekly =TestUtil.executeQuery(conn, mdxWeekly);
        verifyMemberLevelNamesIdentityMeasureAxis(
            resultWeekly.getAxes()[0], "[Measures]");
        verifyMemberLevelNamesIdentityDimAxis(
            resultWeekly.getAxes()[1], "[Time].[Weekly]");
        TestUtil.flushSchemaCache(conn);
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasInCompatibleTimeHierarchy(Context foodMartContext) {
        // SsasCompatibleNaming defaults to false
        String mdxTime = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time].[Year].Members ON ROWS\n"
            + "FROM [Sales]";

        Connection conn=foodMartContext.getConnection();
        Result resultTime =TestUtil.executeQuery(conn, mdxTime);
        verifyMemberLevelNamesIdentityMeasureAxis(
            resultTime.getAxes()[0], "[Measures]");
        verifyMemberLevelNamesIdentityDimAxis(
            resultTime.getAxes()[1], "[Time]");
        TestUtil.flushSchemaCache(conn);
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasInCompatibleWeeklyHierarchy(Context foodMartContext) {
        String mdxWeekly = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time.Weekly].[Year].Members ON ROWS\n"
            + "FROM [Sales]";

        Connection conn=foodMartContext.getConnection();
        Result resultWeekly =TestUtil.executeQuery(conn, mdxWeekly);

        verifyMemberLevelNamesIdentityMeasureAxis(
            resultWeekly.getAxes()[0], "[Measures]");
        verifyMemberLevelNamesIdentityDimAxis(
            resultWeekly.getAxes()[1], "[Time.Weekly]");
        TestUtil.flushSchemaCache(conn);
    }

    private String verifyMemberLevelNamesIdentityMeasureAxis(
        Axis axis, String expected)
    {
        OlapElement unitSales =
            axis.getPositions().get(0).get(0);
        String unitSalesHierarchyName =
            unitSales.getHierarchy().getUniqueName();
        assertEquals(expected, unitSalesHierarchyName);
        return unitSalesHierarchyName;
    }

    private void verifyMemberLevelNamesIdentityDimAxis(
        Axis axis, String expected)
    {
        Member year1997 = axis.getPositions().get(0).get(0);
        String year1997HierarchyName = year1997.getHierarchy().getUniqueName();
        assertEquals(expected, year1997HierarchyName);
        Level year = year1997.getLevel();
        String yearHierarchyName = year.getHierarchy().getUniqueName();
        assertEquals(year1997HierarchyName, yearHierarchyName);
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasCompatibleOlap4j(Context foodMartContext) throws SQLException {
        SystemWideProperties.instance().SsasCompatibleNaming = true;
        verifyLevelMemberNamesIdentityOlap4jTimeHierarchy(foodMartContext, "[Time].[Time]");
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasInCompatibleOlap4j(Context foodMartContext) throws SQLException {
        // SsasCompatibleNaming defaults to false
        verifyLevelMemberNamesIdentityOlap4jTimeHierarchy(foodMartContext, "[Time]");
    }

    private void verifyLevelMemberNamesIdentityOlap4jTimeHierarchy(Context foodMartContext, String expected)
        throws SQLException
    {
        // essential here, in time hierarchy, is hasAll="false"
        // so that we expect "[Time]"
        String mdx = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time].[Time].[Year].Members ON ROWS\n"
            + "FROM [Sales]";
        verifyLevelMemberNamesIdentityOlap4j(mdx, foodMartContext, expected);
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasCompatibleOlap4jWeekly(Context foodMartContext)
        throws SQLException
    {
        SystemWideProperties.instance().SsasCompatibleNaming = true;
        String mdx = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time].[Weekly].[Year].Members ON ROWS\n"
            + "FROM [Sales]";
        verifyLevelMemberNamesIdentityOlap4j(
            mdx, foodMartContext, "[Time].[Weekly]");
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasInCompatibleOlap4jWeekly(Context foodMartContext)
        throws SQLException
    {
        // SsasCompatibleNaming defaults to false
        String mdx = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Time.Weekly].[Year].Members ON ROWS\n"
            + "FROM [Sales]";
        verifyLevelMemberNamesIdentityOlap4j(
            mdx, foodMartContext, "[Time.Weekly]");
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasCompatibleOlap4jDateDim(Context foodMartContext)
        throws SQLException
    {

        SystemWideProperties.instance().SsasCompatibleNaming = true;
        verifyMemberLevelNamesIdentityOlap4jDateDim(foodMartContext, "[Date].[Date]");
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesSsasInCompatibleOlap4jDateDim(Context foodMartContext)
        throws SQLException
    {
        // SsasCompatibleNaming defaults to false
        verifyMemberLevelNamesIdentityOlap4jDateDim(foodMartContext, "[Date]");
    }

    private void verifyMemberLevelNamesIdentityOlap4jDateDim(Context context, String expected)
        throws SQLException
    {
        String mdx =
            "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Date].[Date].[Year].Members ON ROWS\n"
            + "FROM [Sales]";

        /*
        String dateDim  =
            "<Dimension name=\"Date\" type=\"TimeDimension\" foreignKey=\"time_id\">\n"
            + "    <Hierarchy hasAll=\"false\" primaryKey=\"time_id\">\n"
            + "      <Table name=\"time_by_day\"/>\n"
            + "      <Level name=\"Year\" column=\"the_year\" type=\"Numeric\" uniqueMembers=\"true\"\n"
            + "          levelType=\"TimeYears\"/>\n"
            + "      <Level name=\"Quarter\" column=\"quarter\" uniqueMembers=\"false\"\n"
            + "          levelType=\"TimeQuarters\"/>\n"
            + "      <Level name=\"Month\" column=\"month_of_year\" uniqueMembers=\"false\" type=\"Numeric\"\n"
            + "          levelType=\"TimeMonths\"/>\n"
            + "    </Hierarchy>\n"
            + "  </Dimension>";
        */
        class VerifyMemberLevelNamesIdentityOlap4jDateDimModifier extends PojoMappingModifier {

            public VerifyMemberLevelNamesIdentityOlap4jDateDimModifier(MappingSchema mappingSchema) {
                super(mappingSchema);
            }

            @Override
            protected List<MappingCubeDimension> cubeDimensionUsageOrDimensions(MappingCube cube) {
                List<MappingCubeDimension> result = new ArrayList<>();
                result.addAll(super.cubeDimensionUsageOrDimensions(cube));
                if ("Sales".equals(cube.name())) {

                    MappingCubeDimension dimension = PrivateDimensionRBuilder
                        .builder()
                        .name("Date")
                        .type(DimensionTypeEnum.TIME_DIMENSION)
                        .foreignKey("time_id")
                        .hierarchies(List.of(
                            HierarchyRBuilder.builder()
                                .hasAll(false)
                                .primaryKey("time_id")
                                .relation(new TableR("time_by_day"))
                                .levels(List.of(
                                    LevelRBuilder.builder()
                                        .name("Year")
                                        .column("the_year")
                                        .type(TypeEnum.NUMERIC)
                                        .uniqueMembers(true)
                                        .levelType(LevelTypeEnum.TIME_YEARS)
                                        .build(),
                                    LevelRBuilder.builder()
                                        .name("Quarter")
                                        .column("quarter")
                                        .uniqueMembers(false)
                                        .levelType(LevelTypeEnum.TIME_QUARTERS)
                                        .build(),
                                    LevelRBuilder.builder()
                                        .name("Month")
                                        .column("month_of_year")
                                        .uniqueMembers(false)
                                        .type(TypeEnum.NUMERIC)
                                        .levelType(LevelTypeEnum.TIME_MONTHS)
                                        .build()
                                ))
                                .build()
                        ))
                        .build();
                    result.add(dimension);
                }
                return result;
            }
        }
       /*
       ((BaseTestContext)context).update(SchemaUpdater.createSubstitutingCube("Sales", dateDim));
        */
        withSchema(context, VerifyMemberLevelNamesIdentityOlap4jDateDimModifier::new);
        verifyLevelMemberNamesIdentityOlap4j(mdx, context, expected);
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasCompatibleOlap4jDateWeekly(Context context)
        throws SQLException
    {

        SystemWideProperties.instance().SsasCompatibleNaming = true;
        String mdx = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Date].[Weekly].[Year].Members ON ROWS\n"
            + "FROM [Sales]";
        verifyMemberLevelNamesIdentityOlap4jWeekly(context,mdx,"[Date].[Weekly]");
    }
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNamesIdentitySsasInCompatibleOlap4jDateDim(Context context)
        throws SQLException
    {
        // SsasCompatibleNaming defaults to false
        String mdx = "SELECT\n"
            + "   [Measures].[Unit Sales] ON COLUMNS,\n"
            + "   [Date.Weekly].[Year].Members ON ROWS\n"
            + "FROM [Sales]";
        verifyMemberLevelNamesIdentityOlap4jWeekly(context,mdx, "[Date.Weekly]");
    }

    private void verifyMemberLevelNamesIdentityOlap4jWeekly(Context context,
        String mdx, String expected) throws SQLException
    {

        String dateDim =
            "<Dimension name=\"Date\" type=\"TimeDimension\" foreignKey=\"time_id\">\n"
            + "    <Hierarchy hasAll=\"true\" name=\"Weekly\" primaryKey=\"time_id\">\n"
            + "      <Table name=\"time_by_day\"/>\n"
            + "      <Level name=\"Year\" column=\"the_year\" type=\"Numeric\" uniqueMembers=\"true\"\n"
            + "          levelType=\"TimeYears\"/>\n"
            + "      <Level name=\"Week\" column=\"week_of_year\" type=\"Numeric\" uniqueMembers=\"false\"\n"
            + "          levelType=\"TimeWeeks\"/>\n"
            + "      <Level name=\"Day\" column=\"day_of_month\" uniqueMembers=\"false\" type=\"Numeric\"\n"
            + "          levelType=\"TimeDays\"/>\n"
            + "    </Hierarchy>\n"
            + "  </Dimension>";


        //((BaseTestContext)context).update(SchemaUpdater.createSubstitutingCube("Sales", dateDim));
        class VerifyMemberLevelNamesIdentityOlap4jWeeklyModifier extends RDbMappingSchemaModifier {

            public VerifyMemberLevelNamesIdentityOlap4jWeeklyModifier(MappingSchema mappingSchema) {
                super(mappingSchema);
            }

            @Override
            protected List<MappingCubeDimension> cubeDimensionUsageOrDimensions(MappingCube cube) {
                List<MappingCubeDimension> result = new ArrayList<>();
                result.addAll(super.cubeDimensionUsageOrDimensions(cube));
                if ("Sales".equals(cube.name())) {
                    MappingCubeDimension dimension = PrivateDimensionRBuilder
                        .builder()
                        .name("Date")
                        .type(DimensionTypeEnum.TIME_DIMENSION)
                        .foreignKey("time_id")
                        .hierarchies(List.of(
                            HierarchyRBuilder.builder()
                                .hasAll(true)
                                .name("Weekly")
                                .primaryKey("time_id")
                                .relation(new TableR("time_by_day"))
                                .levels(List.of(
                                    LevelRBuilder.builder()
                                        .name("Year")
                                        .column("the_year")
                                        .type(TypeEnum.NUMERIC)
                                        .uniqueMembers(true)
                                        .levelType(LevelTypeEnum.TIME_YEARS)
                                        .build(),
                                    LevelRBuilder.builder()
                                        .name("Week")
                                        .column("week_of_year")
                                        .type(TypeEnum.NUMERIC)
                                        .uniqueMembers(false)
                                        .levelType(LevelTypeEnum.TIME_WEEKS)
                                        .build(),
                                    LevelRBuilder.builder()
                                        .name("Day")
                                        .column("day_of_month")
                                        .uniqueMembers(false)
                                        .type(TypeEnum.NUMERIC)
                                        .levelType(LevelTypeEnum.TIME_DAYS)
                                        .build()
                                ))
                                .build()
                        ))
                        .build();
                    result.add(dimension);
                }
                return result;
            }
        }
        withSchema(context, VerifyMemberLevelNamesIdentityOlap4jWeeklyModifier::new);
        verifyLevelMemberNamesIdentityOlap4j(mdx, context, expected);
    }

    private void verifyLevelMemberNamesIdentityOlap4j(
        String mdx, Context context, String expected)
    {
    Connection connection =	context.getConnection();
        org.eclipse.daanse.olap.api.result.CellSet result = connection.createStatement().executeQuery(mdx);

        List<Position> positions =
            result.getAxes().get(1).getPositions();
        Member year1997 =
            positions.get(0).getMembers().get(0);
        String year1997HierarchyName = year1997.getHierarchy().getUniqueName();
        assertEquals(expected, year1997HierarchyName);

        Level year = year1997.getLevel();
        String yearHierarchyName = year.getHierarchy().getUniqueName();
        assertEquals(year1997HierarchyName, yearHierarchyName);

        TestUtil.flushSchemaCache(context.getConnection());
    }


}
