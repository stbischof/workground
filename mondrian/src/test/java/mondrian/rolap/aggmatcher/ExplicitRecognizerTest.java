/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2017 Hitachi Vantara
// All Rights Reserved.
*/
package mondrian.rolap.aggmatcher;

import mondrian.olap.SystemWideProperties;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggExclude;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggColumnNameRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggForeignKeyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggLevelPropertyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggLevelRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggMeasureRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggNameRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.PropertyRBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextArgumentsProvider;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.TestUtil;
import org.opencube.junit5.context.TestConfig;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.opencube.junit5.TestUtil.assertQueryReturns;
import static org.opencube.junit5.TestUtil.getDialect;
import static org.opencube.junit5.TestUtil.withSchema;

class ExplicitRecognizerTest extends AggTableTestCase {

	@BeforeAll
	public static void beforeAll() {
	      ContextArgumentsProvider.dockerWasChanged = true;
	}

    @Override
	@BeforeEach
    public void beforeEach() {
        super.beforeEach();
    }

    @Override
	@AfterEach
    public void afterEach() {
        SystemWideProperties.instance().populateInitial();
    }

    @Override
    protected String getFileName() {
        return "explicit_aggs.csv";
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testExplicitAggExtraColsRequiringJoin(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
                List.of(AggNameRBuilder.builder()
                        .name("agg_g_ms_pcat_sales_fact_1997")
                        .aggFactCount(AggColumnNameRBuilder.builder()
                        .column("FACT_COUNT")
                        .build())
                        .aggMeasures(List.of(
                        AggMeasureRBuilder.builder()
                            .name("[Measures].[Unit Sales]")
                            .column("UNIT_SALES")
                            .build()
                        ))
                        .aggLevels(List.of(
                            AggLevelRBuilder.builder()
                                .name("[Gender].[Gender]")
                                .column("gender")
                                .build(),
                            AggLevelRBuilder.builder()
                                .name("[TimeExtra].[Year]")
                                .column("the_year")
                                .build(),
                            AggLevelRBuilder.builder()
                                .name("[TimeExtra].[Quarter]")
                                .column("quarter")
                                .build(),
                            AggLevelRBuilder.builder()
                                .name("[TimeExtra].[Month]")
                                .column("month_of_year")
                                .build()
                        ))
                        .build()
                    ),
            "the_year",
            "quarter",
            "month_of_year", "the_month", "month_of_year", null,
            List.of());

        String query =
            "select {[Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[TimeExtra].[Month].members},{[Gender].[M]}) on rows "
            + "from [ExtraCol] ";
        TestUtil.flushSchemaCache(context.getConnection());
        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`the_year` as `c0`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`quarter` as `c1`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`month_of_year` as `c2`,\n"
                + "    `time_by_day`.`the_month` as `c3`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`gender` as `c4`\n"
                + "from\n"
                + "    `agg_g_ms_pcat_sales_fact_1997` as `agg_g_ms_pcat_sales_fact_1997`,\n"
                + "    `time_by_day` as `time_by_day`\n"
                + "where\n"
                + "    `time_by_day`.`month_of_year` = `agg_g_ms_pcat_sales_fact_1997`.`month_of_year`\n"
                + "and\n"
                + "    (`agg_g_ms_pcat_sales_fact_1997`.`gender` = 'M')\n"
                + "group by\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`the_year`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`quarter`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`month_of_year`,\n"
                + "    `time_by_day`.`the_month`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`gender`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c2`) ASC, `c2` ASC,\n"
                    + "    ISNULL(`c4`) ASC, `c4` ASC"
                    : "    ISNULL(`agg_g_ms_pcat_sales_fact_1997`.`the_year`) ASC, `agg_g_ms_pcat_sales_fact_1997`.`the_year` ASC,\n"
                    + "    ISNULL(`agg_g_ms_pcat_sales_fact_1997`.`quarter`) ASC, `agg_g_ms_pcat_sales_fact_1997`.`quarter` ASC,\n"
                    + "    ISNULL(`agg_g_ms_pcat_sales_fact_1997`.`month_of_year`) ASC, `agg_g_ms_pcat_sales_fact_1997`.`month_of_year` ASC,\n"
                    + "    ISNULL(`agg_g_ms_pcat_sales_fact_1997`.`gender`) ASC, `agg_g_ms_pcat_sales_fact_1997`.`gender` ASC")));
        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`the_year` as `c0`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`quarter` as `c1`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`month_of_year` as `c2`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`gender` as `c3`,\n"
                + "    sum(`agg_g_ms_pcat_sales_fact_1997`.`unit_sales`) as `m0`\n"
                + "from\n"
                + "    `agg_g_ms_pcat_sales_fact_1997` as `agg_g_ms_pcat_sales_fact_1997`\n"
                + "where\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`the_year` = 1997\n"
                + "and\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`gender` = 'M'\n"
                + "group by\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`the_year`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`quarter`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`month_of_year`,\n"
                + "    `agg_g_ms_pcat_sales_fact_1997`.`gender`"));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testExplicitForeignKey(Context context) {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("agg_c_14_sales_fact_1997")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggForeignKeys(List.of(
                    AggForeignKeyRBuilder.builder()
                        .factColumn("store_id")
                        .aggColumn("store_id")
                        .build()
                ))
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("unit_sales")
                        .build(),
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Store Cost]")
                        .column("store_cost")
                        .build()
                ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("the_year")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Quarter]")
                        .column("quarter")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Month]")
                        .column("month_of_year")
                        .build()
                ))
                .build()
            ),
            "the_year",
            "quarter",
            "month_of_year", "the_month", "month_of_year", null,
            List.of());


        String query =
            "select {[Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[TimeExtra].[Month].members},{[Store].[Store Name].members}) on rows "
            + "from [ExtraCol] ";
        // Run the query twice, verifying both the SqlTupleReader and
        // Segment load queries.
        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `agg_c_14_sales_fact_1997`.`the_year` as `c0`,\n"
                + "    `agg_c_14_sales_fact_1997`.`quarter` as `c1`,\n"
                + "    `agg_c_14_sales_fact_1997`.`month_of_year` as `c2`,\n"
                + "    `time_by_day`.`the_month` as `c3`,\n"
                + "    `store`.`store_country` as `c4`,\n"
                + "    `store`.`store_state` as `c5`,\n"
                + "    `store`.`store_city` as `c6`,\n"
                + "    `store`.`store_name` as `c7`,\n"
                + "    `store`.`store_street_address` as `c8`\n"
                + "from\n"
                + "    `agg_c_14_sales_fact_1997` as `agg_c_14_sales_fact_1997`,\n"
                + "    `time_by_day` as `time_by_day`,\n"
                + "    `store` as `store`\n"
                + "where\n"
                + "    `time_by_day`.`month_of_year` = `agg_c_14_sales_fact_1997`.`month_of_year`\n"
                + "and\n"
                + "    `agg_c_14_sales_fact_1997`.`store_id` = `store`.`store_id`\n"
                + "group by\n"
                + "    `agg_c_14_sales_fact_1997`.`the_year`,\n"
                + "    `agg_c_14_sales_fact_1997`.`quarter`,\n"
                + "    `agg_c_14_sales_fact_1997`.`month_of_year`,\n"
                + "    `time_by_day`.`the_month`,\n"
                + "    `store`.`store_country`,\n"
                + "    `store`.`store_state`,\n"
                + "    `store`.`store_city`,\n"
                + "    `store`.`store_name`,\n"
                + "    `store`.`store_street_address`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c2`) ASC, `c2` ASC,\n"
                    + "    ISNULL(`c4`) ASC, `c4` ASC,\n"
                    + "    ISNULL(`c5`) ASC, `c5` ASC,\n"
                    + "    ISNULL(`c6`) ASC, `c6` ASC,\n"
                    + "    ISNULL(`c7`) ASC, `c7` ASC"
                    : "    ISNULL(`agg_c_14_sales_fact_1997`.`the_year`) ASC, `agg_c_14_sales_fact_1997`.`the_year` ASC,\n"
                    + "    ISNULL(`agg_c_14_sales_fact_1997`.`quarter`) ASC, `agg_c_14_sales_fact_1997`.`quarter` ASC,\n"
                    + "    ISNULL(`agg_c_14_sales_fact_1997`.`month_of_year`) ASC, `agg_c_14_sales_fact_1997`.`month_of_year` ASC,\n"
                    + "    ISNULL(`store`.`store_country`) ASC, `store`.`store_country` ASC,\n"
                    + "    ISNULL(`store`.`store_state`) ASC, `store`.`store_state` ASC,\n"
                    + "    ISNULL(`store`.`store_city`) ASC, `store`.`store_city` ASC,\n"
                    + "    ISNULL(`store`.`store_name`) ASC, `store`.`store_name` ASC")));

        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `agg_c_14_sales_fact_1997`.`the_year` as `c0`,\n"
                + "    `agg_c_14_sales_fact_1997`.`quarter` as `c1`,\n"
                + "    `agg_c_14_sales_fact_1997`.`month_of_year` as `c2`,\n"
                + "    `store`.`store_name` as `c3`,\n"
                + "    sum(`agg_c_14_sales_fact_1997`.`unit_sales`) as `m0`\n"
                + "from\n"
                + "    `agg_c_14_sales_fact_1997` as `agg_c_14_sales_fact_1997`,\n"
                + "    `store` as `store`\n"
                + "where\n"
                + "    `agg_c_14_sales_fact_1997`.`the_year` = 1997\n"
                + "and\n"
                + "    `agg_c_14_sales_fact_1997`.`store_id` = `store`.`store_id`\n"
                + "group by\n"
                + "    `agg_c_14_sales_fact_1997`.`the_year`,\n"
                + "    `agg_c_14_sales_fact_1997`.`quarter`,\n"
                + "    `agg_c_14_sales_fact_1997`.`month_of_year`,\n"
                + "    `store`.`store_name`"));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testExplicitAggOrdinalOnAggTable(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("exp_agg_test")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("test_unit_sales")
                        .build()
                ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("testyear")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Quarter]")
                        .column("testqtr")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Month]")
                        .column("testmonthname")
                        .ordinalColumn("testmonthord")
                        .build()
                ))
                .build()
            ),
            "the_year",
            "quarter",
            "the_month", null,  "month_of_year", null,
            List.of());

        String query =
            "select {[Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[TimeExtra].[Month].members},{[Gender].[M]}) on rows "
            + "from [ExtraCol] ";

        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test`.`testyear` as `c0`,\n"
                + "    `exp_agg_test`.`testqtr` as `c1`,\n"
                + "    `exp_agg_test`.`testmonthname` as `c2`,\n"
                + "    `exp_agg_test`.`testmonthord` as `c3`,\n"
                + "    `exp_agg_test`.`gender` as `c4`\n"
                + "from\n"
                + "    `exp_agg_test` as `exp_agg_test`\n"
                + "where\n"
                + "    (`exp_agg_test`.`gender` = 'M')\n"
                + "group by\n"
                + "    `exp_agg_test`.`testyear`,\n"
                + "    `exp_agg_test`.`testqtr`,\n"
                + "    `exp_agg_test`.`testmonthname`,\n"
                + "    `exp_agg_test`.`testmonthord`,\n"
                + "    `exp_agg_test`.`gender`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c3`) ASC, `c3` ASC,\n"
                    + "    ISNULL(`c4`) ASC, `c4` ASC"
                    : "    ISNULL(`exp_agg_test`.`testyear`) ASC, `exp_agg_test`.`testyear` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`testqtr`) ASC, `exp_agg_test`.`testqtr` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`testmonthord`) ASC, `exp_agg_test`.`testmonthord` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`gender`) ASC, `exp_agg_test`.`gender` ASC")));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testExplicitAggCaptionOnAggTable(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("exp_agg_test")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("test_unit_sales")
                        .build()
                ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("testyear")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Quarter]")
                        .column("testqtr")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Month]")
                        .column("testmonthname")
                        .captionColumn("testmonthcap")
                        .build()
                ))
                .build()
            ),
            "the_year",
            "quarter",
            "the_month",  "month_of_year", null, null,
            List.of());

        String query =
            "select {[Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[TimeExtra].[Month].members},{[Gender].[M]}) on rows "
            + "from [ExtraCol] ";

        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test`.`testyear` as `c0`,\n"
                + "    `exp_agg_test`.`testqtr` as `c1`,\n"
                + "    `exp_agg_test`.`testmonthname` as `c2`,\n"
                + "    `exp_agg_test`.`testmonthcap` as `c3`,\n"
                + "    `exp_agg_test`.`gender` as `c4`\n"
                + "from\n"
                + "    `exp_agg_test` as `exp_agg_test`\n"
                + "where\n"
                + "    (`exp_agg_test`.`gender` = 'M')\n"
                + "group by\n"
                + "    `exp_agg_test`.`testyear`,\n"
                + "    `exp_agg_test`.`testqtr`,\n"
                + "    `exp_agg_test`.`testmonthname`,\n"
                + "    `exp_agg_test`.`testmonthcap`,\n"
                + "    `exp_agg_test`.`gender`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c2`) ASC, `c2` ASC,\n"
                    + "    ISNULL(`c4`) ASC, `c4` ASC"
                    : "    ISNULL(`exp_agg_test`.`testyear`) ASC, `exp_agg_test`.`testyear` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`testqtr`) ASC, `exp_agg_test`.`testqtr` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`testmonthname`) ASC, `exp_agg_test`.`testmonthname` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`gender`) ASC, `exp_agg_test`.`gender` ASC")));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testExplicitAggNameColumnOnAggTable(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("exp_agg_test")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("test_unit_sales")
                        .build()
                ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("testyear")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Quarter]")
                        .column("testqtr")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Month]")
                        .column("testmonthname")
                        .nameColumn("testmonthcap")
                        .properties(Stream.of(
                            AggLevelPropertyRBuilder.builder()
                                .name("aProperty")
                                .column("testmonprop1")
                                .build()
                        ).collect(Collectors.toList()))
                        .build()
                ))
                .build()
            ),
            "the_year",
            "quarter",
            "the_month", null, null,  "month_of_year",
            List.of(PropertyRBuilder.builder()
                .name("aProperty")
                .column("fiscal_period")
                .build()));

        String query =
            "select {[Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[TimeExtra].[Month].members},{[Gender].[M]}) on rows "
            + "from [ExtraCol] ";

        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test`.`testyear` as `c0`,\n"
                + "    `exp_agg_test`.`testqtr` as `c1`,\n"
                + "    `exp_agg_test`.`testmonthname` as `c2`,\n"
                + "    `exp_agg_test`.`testmonthcap` as `c3`,\n"
                + "    `exp_agg_test`.`testmonprop1` as `c4`,\n"
                + "    `exp_agg_test`.`gender` as `c5`\n"
                + "from\n"
                + "    `exp_agg_test` as `exp_agg_test`\n"
                + "where\n"
                + "    (`exp_agg_test`.`gender` = 'M')\n"
                + "group by\n"
                + "    `exp_agg_test`.`testyear`,\n"
                + "    `exp_agg_test`.`testqtr`,\n"
                + "    `exp_agg_test`.`testmonthname`,\n"
                + "    `exp_agg_test`.`testmonthcap`,\n"
                + "    `exp_agg_test`.`testmonprop1`,\n"
                + "    `exp_agg_test`.`gender`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c2`) ASC, `c2` ASC,\n"
                    + "    ISNULL(`c5`) ASC, `c5` ASC"
                    : "    ISNULL(`exp_agg_test`.`testyear`) ASC, `exp_agg_test`.`testyear` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`testqtr`) ASC, `exp_agg_test`.`testqtr` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`testmonthname`) ASC, `exp_agg_test`.`testmonthname` ASC,\n"
                    + "    ISNULL(`exp_agg_test`.`gender`) ASC, `exp_agg_test`.`gender` ASC")));
    }


    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testExplicitAggPropertiesOnAggTable(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("exp_agg_test_distinct_count")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("unit_s")
                        .build(),
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Customer Count]")
                        .column("cust_cnt")
                        .build()
                    ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("testyear")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store Country]")
                        .column("store_country")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store State]")
                        .column("store_st")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store City]")
                        .column("store_cty")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store Name]")
                        .column("store_name")
                        .properties(List.of(
                            AggLevelPropertyRBuilder.builder()
                                .name("Street address")
                                .column("store_add")
                                .build()
                        ))
                        .build()

                        ))
                        .build()
                ),
            "the_year",
            "quarter",
            "month_of_year", "the_month", "month_of_year", null,
            List.of());

        String query =
            "with member measures.propVal as 'Store.CurrentMember.Properties(\"Street Address\")'"
            + "select { measures.[propVal], measures.[Customer Count], [Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[Gender].Gender.members},{[Store].[USA].[WA].[Spokane].[Store 16]}) on rows "
            + "from [ExtraCol]";
        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test_distinct_count`.`gender` as `c0`,\n"
                + "    `exp_agg_test_distinct_count`.`store_country` as `c1`,\n"
                + "    `exp_agg_test_distinct_count`.`store_st` as `c2`,\n"
                + "    `exp_agg_test_distinct_count`.`store_cty` as `c3`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name` as `c4`,\n"
                + "    `exp_agg_test_distinct_count`.`store_add` as `c5`\n"
                + "from\n"
                + "    `exp_agg_test_distinct_count` as `exp_agg_test_distinct_count`\n"
                + "where\n"
                + "    (`exp_agg_test_distinct_count`.`store_name` = 'Store 16')\n"
                + "group by\n"
                + "    `exp_agg_test_distinct_count`.`gender`,\n"
                + "    `exp_agg_test_distinct_count`.`store_country`,\n"
                + "    `exp_agg_test_distinct_count`.`store_st`,\n"
                + "    `exp_agg_test_distinct_count`.`store_cty`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name`,\n"
                + "    `exp_agg_test_distinct_count`.`store_add`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c2`) ASC, `c2` ASC,\n"
                    + "    ISNULL(`c3`) ASC, `c3` ASC,\n"
                    + "    ISNULL(`c4`) ASC, `c4` ASC"
                    : "    ISNULL(`exp_agg_test_distinct_count`.`gender`) ASC, `exp_agg_test_distinct_count`.`gender` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_country`) ASC, `exp_agg_test_distinct_count`.`store_country` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_st`) ASC, `exp_agg_test_distinct_count`.`store_st` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_cty`) ASC, `exp_agg_test_distinct_count`.`store_cty` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_name`) ASC, `exp_agg_test_distinct_count`.`store_name` ASC")));

        assertQueryReturns(context.getConnection(),
            "Store Address Property should be '5922 La Salle Ct'",
            query,
            "Axis #0:\n"
            + "{}\n"
            + "Axis #1:\n"
            + "{[Measures].[propVal]}\n"
            + "{[Measures].[Customer Count]}\n"
            + "{[Measures].[Unit Sales]}\n"
            + "Axis #2:\n"
            + "{[Gender].[F], [Store].[USA].[WA].[Spokane].[Store 16]}\n"
            + "{[Gender].[M], [Store].[USA].[WA].[Spokane].[Store 16]}\n"
            + "Row #0: 5922 La Salle Ct\n"
            + "Row #0: 45\n"
            + "Row #0: 12,068\n"
            + "Row #1: 5922 La Salle Ct\n"
            + "Row #1: 39\n"
            + "Row #1: 11,523\n");
        // Should use agg table for distinct count measure
        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test_distinct_count`.`testyear` as `c0`,\n"
                + "    `exp_agg_test_distinct_count`.`gender` as `c1`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name` as `c2`,\n"
                + "    `exp_agg_test_distinct_count`.`unit_s` as `m0`,\n"
                + "    `exp_agg_test_distinct_count`.`cust_cnt` as `m1`\n"
                + "from\n"
                + "    `exp_agg_test_distinct_count` as `exp_agg_test_distinct_count`\n"
                + "where\n"
                + "    `exp_agg_test_distinct_count`.`testyear` = 1997\n"
                + "and\n"
                + "    `exp_agg_test_distinct_count`.`store_name` = 'Store 16'"));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testCountDistinctAllowableRollup(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("exp_agg_test_distinct_count")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("unit_s")
                        .build(),
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Customer Count]")
                        .column("cust_cnt")
                        .build()
                ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("testyear")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store Country]")
                        .column("store_country")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store State]")
                        .column("store_st")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store City]")
                        .column("store_cty")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store Name]")
                        .column("store_name")
                        .properties(List.of(
                            AggLevelPropertyRBuilder.builder()
                                .name("Street address")
                                .column("store_add")
                                .build()
                        ))
                        .build()

                ))
                .build()),
            "the_year",
            "quarter",
            "month_of_year", "the_month", "month_of_year", null,
            List.of(), "Customer Count");

        // Query brings in Year and Store Name, omitting Gender.
        // It's okay to roll up the agg table in this case
        // since Customer Count is dependent on Gender.
        String query =
            "select { measures.[Customer Count], [Measures].[Unit Sales]} on columns, "
            + "non empty CrossJoin({[TimeExtra].Year.members},{[Store].[USA].[WA].[Spokane].[Store 16]}) on rows "
            + "from [ExtraCol]";

        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test_distinct_count`.`testyear` as `c0`,\n"
                + "    `exp_agg_test_distinct_count`.`store_country` as `c1`,\n"
                + "    `exp_agg_test_distinct_count`.`store_st` as `c2`,\n"
                + "    `exp_agg_test_distinct_count`.`store_cty` as `c3`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name` as `c4`,\n"
                + "    `exp_agg_test_distinct_count`.`store_add` as `c5`\n"
                + "from\n"
                + "    `exp_agg_test_distinct_count` as `exp_agg_test_distinct_count`\n"
                + "where\n"
                + "    (`exp_agg_test_distinct_count`.`store_name` = 'Store 16')\n"
                + "group by\n"
                + "    `exp_agg_test_distinct_count`.`testyear`,\n"
                + "    `exp_agg_test_distinct_count`.`store_country`,\n"
                + "    `exp_agg_test_distinct_count`.`store_st`,\n"
                + "    `exp_agg_test_distinct_count`.`store_cty`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name`,\n"
                + "    `exp_agg_test_distinct_count`.`store_add`\n"
                + "order by\n"
                + (getDialect(context.getConnection()).requiresOrderByAlias()
                    ? "    ISNULL(`c0`) ASC, `c0` ASC,\n"
                    + "    ISNULL(`c1`) ASC, `c1` ASC,\n"
                    + "    ISNULL(`c2`) ASC, `c2` ASC,\n"
                    + "    ISNULL(`c3`) ASC, `c3` ASC,\n"
                    + "    ISNULL(`c4`) ASC, `c4` ASC"
                    : "    ISNULL(`exp_agg_test_distinct_count`.`testyear`) ASC, `exp_agg_test_distinct_count`.`testyear` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_country`) ASC, `exp_agg_test_distinct_count`.`store_country` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_st`) ASC, `exp_agg_test_distinct_count`.`store_st` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_cty`) ASC, `exp_agg_test_distinct_count`.`store_cty` ASC,\n"
                    + "    ISNULL(`exp_agg_test_distinct_count`.`store_name`) ASC, `exp_agg_test_distinct_count`.`store_name` ASC")));

        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `exp_agg_test_distinct_count`.`testyear` as `c0`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name` as `c1`,\n"
                + "    sum(`exp_agg_test_distinct_count`.`unit_s`) as `m0`,\n"
                + "    sum(`exp_agg_test_distinct_count`.`cust_cnt`) as `m1`\n"
                + "from\n"
                + "    `exp_agg_test_distinct_count` as `exp_agg_test_distinct_count`\n"
                + "where\n"
                + "    `exp_agg_test_distinct_count`.`testyear` = 1997\n"
                + "and\n"
                + "    `exp_agg_test_distinct_count`.`store_name` = 'Store 16'\n"
                + "group by\n"
                + "    `exp_agg_test_distinct_count`.`testyear`,\n"
                + "    `exp_agg_test_distinct_count`.`store_name`"));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testCountDisallowedRollup(Context context) throws SQLException {
        ((TestConfig)context.getConfig()).setGenerateFormattedSql(true);
        ((TestConfig)context.getConfig()).setUseAggregates(true);
        ((TestConfig)context.getConfig()).setReadAggregates(true);
        ((TestConfig)context.getConfig()).setDisableCaching(true);
        prepareContext(context);
        setupMultiColDimCube(context,
            List.of(AggNameRBuilder.builder()
                .name("exp_agg_test_distinct_count")
                .aggFactCount(AggColumnNameRBuilder.builder()
                    .column("FACT_COUNT")
                    .build())
                .aggMeasures(List.of(
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Unit Sales]")
                        .column("unit_s")
                        .build(),
                    AggMeasureRBuilder.builder()
                        .name("[Measures].[Customer Count]")
                        .column("cust_cnt")
                        .build()
                ))
                .aggLevels(List.of(
                    AggLevelRBuilder.builder()
                        .name("[TimeExtra].[Year]")
                        .column("testyear")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Gender].[Gender]")
                        .column("gender")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store Country]")
                        .column("store_country")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store State]")
                        .column("store_st")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store City]")
                        .column("store_cty")
                        .build(),
                    AggLevelRBuilder.builder()
                        .name("[Store].[Store Name]")
                        .column("store_name")
                        .properties(List.of(
                            AggLevelPropertyRBuilder.builder()
                                .name("Street address")
                                .column("store_add")
                                .build()
                        ))
                        .build()

                ))
                .build()
            ),
            "the_year",
            "quarter",
            "month_of_year", "the_month", "month_of_year", null,
            List.of(), "Customer Count");

        String query =
            "select { measures.[Customer Count]} on columns, "
            + "non empty CrossJoin({[TimeExtra].Year.members},{[Gender].[F]}) on rows "
            + "from [ExtraCol]";


        // Seg load query should not use agg table, since the independent
        // attributes for store are on the aggStar bitkey and not part of the
        // request and rollup is not safe
        assertQuerySql(
            context.getConnection(),
            query,
            mysqlPattern(
                "select\n"
                + "    `time_by_day`.`the_year` as `c0`,\n"
                + "    `customer`.`gender` as `c1`,\n"
                + "    count(distinct `sales_fact_1997`.`customer_id`) as `m0`\n"
                + "from\n"
                + "    `sales_fact_1997` as `sales_fact_1997`,\n"
                + "    `time_by_day` as `time_by_day`,\n"
                + "    `customer` as `customer`\n"
                + "where\n"
                + "    `sales_fact_1997`.`time_id` = `time_by_day`.`time_id`\n"
                + "and\n"
                + "    `time_by_day`.`the_year` = 1997\n"
                + "and\n"
                + "    `sales_fact_1997`.`customer_id` = `customer`.`customer_id`\n"
                + "and\n"
                + "    `customer`.`gender` = 'F'\n"
                + "group by\n"
                + "    `time_by_day`.`the_year`,\n"
                + "    `customer`.`gender`"));
    }

    public static void setupMultiColDimCube(
        Context context, List<MappingAggTable> aggTables, String yearCols, String qtrCols, String monthCols,
        String monthCaptionCol, String monthOrdinalCol, String monthNameCol, List<MappingProperty> monthProp)
    {
        setupMultiColDimCube(context,
            aggTables, yearCols, qtrCols, monthCols, monthCaptionCol, monthOrdinalCol, monthNameCol, monthProp, "Unit Sales");
    }

    public static void setupMultiColDimCube(
        Context context, List<MappingAggTable> aggTables, String yearCol, String qtrCol, String monthCol,
        String monthCaptionCol, String monthOrdinalCol, String monthNameCol,
        List<MappingProperty> monthProp, String defaultMeasure)
    {
        class ExplicitRecognizerTestModifierInner extends ExplicitRecognizerTestModifier {

            @Override
            protected List<MappingProperty> getMonthProp() {
                return monthProp;
            }

            @Override
            protected String getMonthOrdinalCol() {
                return monthOrdinalCol;
            }

            @Override
            protected String getMonthNameCol() {
                return monthNameCol;
            }

            @Override
            protected String getMonthCaptionCol() {
                return monthCaptionCol;
            }

            public ExplicitRecognizerTestModifierInner(MappingSchema mappingSchema) {
                super(mappingSchema);
            }

            @Override
            protected List<MappingAggTable> getAggTables() {
                return aggTables;
            }

            @Override
            protected List<MappingAggExclude> getAggExcludes() {
                return List.of();
            }

            @Override
            protected String getdefaultMeasure() {
                return defaultMeasure;
            }

            @Override
            protected String getQuarterCol() {
                return qtrCol;
            }

            @Override
            protected String getMonthCol() {
                return monthCol;
            }

            @Override
            protected String getYearCol() {
                return yearCol;
            }

        }

        withSchema(context, ExplicitRecognizerTestModifierInner::new);
    }

}
