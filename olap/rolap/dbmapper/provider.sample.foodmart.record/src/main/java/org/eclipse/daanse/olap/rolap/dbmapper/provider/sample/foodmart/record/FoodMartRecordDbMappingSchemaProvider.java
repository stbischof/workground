/*
 * Copyright (c) 0 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License .0
 * which is available at https://www.eclipse.org/legal/epl-.0/
 *
 * SPDX-License-Identifier: EPL-.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.provider.sample.foodmart.record;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.AccessEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.DimensionTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.HideMemberIfEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MemberGrantAccessEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.PropertyTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.AggExcludeR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.AnnotationR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.CalculatedMemberPropertyR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.CalculatedMemberR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.ClosureR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.CubeR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.ExpressionViewR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.HierarchyR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinedQueryElementR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.LevelR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.MeasureR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.NamedSetR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.PrivateDimensionR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.PropertyR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.RoleR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.SchemaR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.TableR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.VirtualCubeDimensionR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.VirtualCubeMeasureR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.VirtualCubeR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggColumnNameRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggExcludeRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggForeignKeyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggLevelRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggMeasureRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AggNameRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.AnnotationRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.CalculatedMemberPropertyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.CalculatedMemberRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.ClosureRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.CubeGrantRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.CubeRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.DimensionUsageRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.ExpressionViewRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.FormulaRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.HierarchyGrantRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.HierarchyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.LevelRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.MeasureRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.MemberGrantRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.NamedSetRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.PrivateDimensionRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.RoleRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.SQLRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.SchemaGrantRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.SqlSelectQueryRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.VirtualCubeDimensionRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.VirtualCubeMeasureRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.VirtualCubeRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(service = DatabaseMappingSchemaProvider.class, configurationPid = FoodMartRecordDbMappingSchemaProvider.PID,
scope = ServiceScope.SINGLETON, property = {"sample.name=FoodMart",
    "sample.type=record"})
public class FoodMartRecordDbMappingSchemaProvider implements DatabaseMappingSchemaProvider {

    public static final String PID="org.eclipse.daanse.olap.rolap.dbmapper.provider.sample.foodmart.record.FoodMartRecordDbMappingSchemaProvider";
	private static final String CONCAT_CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME =
        "CONCAT(CONCAT(\"customer\".\"fname\", ' '), \"customer\".\"lname\")";
	private static final String STORE_MANAGER_COLUMN = "store_manager";
	private static final String STORE_COUNTRY_COLUMN = "store_country";
	private static final String STORE_CITY_COLUMN = "store_city";
	private static final String STORE_TABLE_NAME = "store";
	private static final String SALES_FACT_1997 = "sales_fact_1997";
	private static final String PROMOTION_ID = "promotion_id";
	private static final String PRODUCT_ID = "product_id";
	private static final String PRODUCT_CLASS = "product_class";
	private static final String PRODUCT_TABLE_NAME = "product";
	private static final String POSITION_ID = "position_id";
	private static final String MEAT_SQFT_COLUMN = "meat_sqft";
	private static final String MARITAL_STATUS_COLUMN = "marital_status";
	private static final String SNOWFLAKE = "snowflake";
	private static final String TERADATA = "teradata";
	private static final String NEOVIEW = "neoview";
	private static final String LUCIDDB = "luciddb";
	private static final String DB2 = "db2";
	private static final String MSSQL = "mssql";
	private static final String MARIADB = "mariadb";
	private static final String MYSQL = "mysql";
	private static final String POSTGRES = "postgres";
	private static final String H2 = "h2";
	private static final String HIVE = "hive";
	private static final String ORACLE = "oracle";
	private static final String HSQLDB = "hsqldb";
	private static final String GROCERY_SQFT_COLUMN = "grocery_sqft";
	private static final String GENERIC = "generic";
	private static final String GENDER2 = "gender";
	private static final String FULLNAME = "fullname";
	private static final String FROZEN_SQFT_COLUMN = "frozen_sqft";
	private static final String EDUCATION = "education";
	private static final String DISTINCT_COUNT = "distinct-count";
	private static final String DERBY = "derby";
	private static final String DEPARTMENT_ID = "department_id";
	private static final String COUNT = "count";
	private static final String COFFEE_BAR = "coffee_bar";
	private static final String AGG_C_SPECIAL_SALES_FACT_1997 = "agg_c_special_sales_fact_1997";
	private static final String ACCESS = "access";
	private static final String WAREHOUSE_SALES_INVENTORY_FACT_1997_WAREHOUSE_COST = "`warehouse_sales` - `inventory_fact_1997`.`warehouse_cost`";
	private static final String FNAME_LNAME2 = "\"fname\" || ' ' || \"lname\"";
	private static final String STORE_STATE_COLUMN = "store_state";
	private static final String EMPLOYEE = "employee";
	private static final String FNAME_LNAME = "fname + ' ' + lname";
	private static final String EMPLOYEE_ID = "employee_id";
	private static final String STORE_NAME_COLUMN = "store_name";
	private static final String STORE_SALES_COLUMN = "store_sales";
	private static final String STORE_SQFT_COLUMN = "store_sqft";
	private static final String STORE_STREET_ADDRESS = "store_street_address";
	private static final String STORE_TYPE_COLUMN = "store_type";
	private static final String THE_YEAR = "the_year";
	private static final String TIME_BY_DAY = "time_by_day";
	private static final String CUSTOMER_ID = "customer_id";
	private static final String CUSTOMER_FULLNAME = "\"customer\".\"fullname\"";
	private static final String YEARLY_INCOME = "Yearly Income";
	private static final String WAREHOUSE = "Warehouse";
	private static final String STORE = "Store";
	private static final String STORE_TYPE = "Store Type";
	private static final String STORE_STATE = "Store State";
	private static final String STORE_SQFT = "Store Sqft";
	private static final String STORE_SIZE_IN_SQFT = "Store Size in SQFT";
	private static final String STORE_SALES = "Store Sales";
	private static final String STORE_NAME = "Store Name";
	private static final String STORE_MANAGER = "Store Manager";
	private static final String STORE_COUNTRY = "Store Country";
	private static final String STORE_COST = "Store Cost";
	private static final String STORE_CITY = "Store City";
	private static final String STATE_PROVINCE = "State Province";
	private static final String STANDARD = "Standard";
	private static final String SALES_COUNT = "Sales Count";
	private static final String PROMOTIONS = "Promotions";
	private static final String PROMOTION_MEDIA = "Promotion Media";
	private static final String PRODUCT = "Product";
	private static final String MEAT_SQFT = "Meat Sqft";
	private static final String MEASURES = "Measures";
	private static final String MARITAL_STATUS = "Marital Status";
	private static final String MEMBER_ORDINAL = "MEMBER_ORDINAL";
	private static final String HAS_COFFEE_BAR = "Has coffee bar";
	private static final String GROCERY_SQFT = "Grocery Sqft";
	private static final String GENDER = "Gender";
	private static final String FROZEN_SQFT = "Frozen Sqft";
	private static final String ALL_MEDIA = "All Media";
	private static final String STORE_ID_KEY = "store_id";
	private static final String TIME_ID_KEY = "time_id";
	private static final String UNIT_SALES = "Unit Sales";
	private static final String UNIT_SALES_KEY = "unit_sales";
	private static final String YEARLY_INCOME_COLUMN = "yearly_income";
	private static final String SALES = "Sales";
	private static final String FORMAT_STRING_NAME = "FORMAT_STRING";
	private static final String EDUCATION_LEVEL = "Education Level";
	private static final String CUSTOMERS = "Customers";
	private static final String CUSTOMER_COUNT = "Customer Count";
	private static final String CURRENCY = "Currency";
	private static final String COUNTRY = "Country";
	private static final String CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME = "CONCAT(`customer`.`fname`, ' ', `customer`.`lname`)";
	private static final String CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997 =
        "(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else \"sales_fact_1997\".\"store_sales\" end)";
	private static final String FORMAT_STRING_0_00 = "$#,##0.00";
	private static final String FORMAT_STRING_00 = "#,###.00";
	private static final String FORMAT_STRING = "#,###";
	private static final String SCHEMA_NAME = "FoodMart";
    private static final String CUBE_NAME_1 = SALES;

    private static final TableR TABLE_1 = new TableR("promotion");
    private static final TableR TABLE_2 = TABLE_1;
    private static final TableR TABLE_3 = new TableR("customer");
    private static final TableR TABLE_4 = TABLE_3;
    private static final TableR TABLE_5 = TABLE_3;
    private static final TableR TABLE_6 = TABLE_3;
    private static final TableR TABLE_7 = TABLE_3;

    private static final TableR TABLE_4_1 = new TableR(TIME_BY_DAY);
    private static final TableR TABLE_4_5 = new TableR(EMPLOYEE);
    private static final TableR TABLE_4_6 = new TableR("department");
    private static final TableR TABLE_4_7 = new TableR(EMPLOYEE);

    private static final TableR TABLE_5_1 = new TableR("store_ragged");
    private static final TableR TABLE_5_2 = TABLE_5_1;
    private static final TableR TABLE_5_3 = new TableR("promotion");
    private static final TableR TABLE_5_4 = TABLE_5_3;
    private static final TableR TABLE_5_5 = new TableR("customer");
    private static final TableR TABLE_5_6 = TABLE_5_5;
    private static final TableR TABLE_5_7 = TABLE_5_5;
    private static final TableR TABLE_5_8 = TABLE_5_5;
    private static final TableR TABLE_5_9 = TABLE_5_5;

    private static final TableR TABLE_6_1 = TABLE_5_5;

    private static final TableR TABLE_SHARED_1 = new TableR(STORE_TABLE_NAME);
    private static final TableR TABLE_SHARED_2 = new TableR(STORE_TABLE_NAME);
    private static final TableR TABLE_SHARED_3 = new TableR(STORE_TABLE_NAME);
    private static final TableR TABLE_SHARED_4_A = new TableR(TIME_BY_DAY);
    private static final TableR TABLE_SHARED_4_B = new TableR(TIME_BY_DAY);
    private static final TableR TABLE_SHARED_6 = new TableR("warehouse");

    private static final TableR TABLE_JOIN_1_A = new TableR(PRODUCT_TABLE_NAME);
    private static final TableR TABLE_JOIN_1_B = new TableR(PRODUCT_CLASS);
    private static final TableR TABLE_JOIN_4_2_A = new TableR(EMPLOYEE);
    private static final TableR TABLE_JOIN_4_2_B = new TableR(STORE_TABLE_NAME);
    private static final TableR TABLE_JOIN_4_3_A = new TableR(EMPLOYEE);
    private static final TableR TABLE_JOIN_4_3_B = new TableR("position");
    private static final TableR TABLE_JOIN_4_4_A = new TableR(EMPLOYEE);
    private static final TableR TABLE_JOIN_4_4_B = new TableR(STORE_TABLE_NAME);

    private static final TableR TABLE_CLOSURE_4_7 = new TableR("employee_closure");

	private static final PropertyR PROPERTY_1_A = new PropertyR(STORE_TYPE, null, null, STORE_TYPE_COLUMN, null, null, null, null);
	private static final PropertyR PROPERTY_1_B =new PropertyR(STORE_MANAGER, null, null, STORE_MANAGER_COLUMN, null, null, null, null);
	private static final PropertyR PROPERTY_1_C = new PropertyR(STORE_SQFT,null, null, STORE_SQFT_COLUMN , PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_1_D = new PropertyR(GROCERY_SQFT,null, null, GROCERY_SQFT_COLUMN  ,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_1_E = new PropertyR(FROZEN_SQFT,null, null, FROZEN_SQFT_COLUMN,  PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_1_F = new PropertyR(MEAT_SQFT,null, null, MEAT_SQFT_COLUMN,  PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_1_G = new PropertyR(HAS_COFFEE_BAR,null, null, COFFEE_BAR,  PropertyTypeEnum.BOOLEAN,null,null,null);
	private static final PropertyR PROPERTY_1_H = new PropertyR("Street address",null, null, STORE_STREET_ADDRESS,  PropertyTypeEnum.STRING,null,null,null);
	private static final PropertyR PROPERTY_3_4_1 = new PropertyR(GENDER, null, null, GENDER2, null,null,null, null);
	private static final PropertyR PROPERTY_3_4_2 = new PropertyR("Marital Status", null, null, "marital_status", null, null,			null, null);
	private static final PropertyR PROPERTY_3_4_3 = new PropertyR("Education",null, null,  EDUCATION, null, null, null,			null);
	private static final PropertyR PROPERTY_3_4_4 = new PropertyR(YEARLY_INCOME, null, null, YEARLY_INCOME_COLUMN, null,null,null,			null);
	private static final PropertyR PROPERTY_4_2_D_a = new PropertyR(STORE_TYPE, null, null, STORE_TYPE_COLUMN, null,	null,null,		null);
	private static final PropertyR PROPERTY_4_2_D_b = new PropertyR(STORE_MANAGER, null, null, STORE_MANAGER_COLUMN, null, null,			null, null);
	private static final PropertyR PROPERTY_4_2_D_c = new PropertyR(STORE_SQFT,null, null, "store_sqft",PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_4_2_D_d = new PropertyR(GROCERY_SQFT,null, null, GROCERY_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_4_2_D_e = new PropertyR(FROZEN_SQFT,null, null, FROZEN_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_4_2_D_f = new PropertyR(MEAT_SQFT,null, null, MEAT_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_4_2_D_g = new PropertyR(HAS_COFFEE_BAR,null, null, COFFEE_BAR,PropertyTypeEnum.BOOLEAN,null,null,null);
	private static final PropertyR PROPERTY_4_2_D_h = new PropertyR("Street address",null, null, STORE_STREET_ADDRESS,PropertyTypeEnum.STRING,null,null,null);
	private static final PropertyR PROPERTY_4_7_A = new PropertyR(MARITAL_STATUS,null, null,  MARITAL_STATUS_COLUMN, null, null,			null, null);
	private static final PropertyR PROPERTY_4_7_B = new PropertyR("Position Title", null, null, "position_title", null,	null,null,		null);
	private static final PropertyR PROPERTY_4_7_C = new PropertyR(GENDER, null, null, GENDER2, null,null,null, null);
	private static final PropertyR PROPERTY_4_7_D = new PropertyR("Salary", null, null, "salary", null, null, null,			null);
	private static final PropertyR PROPERTY_4_7_E = new PropertyR(EDUCATION_LEVEL,null, null,  "education_level", null,		null,null,	null);
	private static final PropertyR PROPERTY_4_7_F = new PropertyR("Management Role",null, null,  "management_role", null, null,			null, null);
	private static final PropertyR PROPERTY_5_1_D_1 = new PropertyR(STORE_TYPE,null, null,  STORE_TYPE_COLUMN, null,null,null,			null);
	private static final PropertyR PROPERTY_5_1_D_2 = new PropertyR(STORE_MANAGER, null, null, STORE_MANAGER_COLUMN, null, null,			null, null);
	private static final PropertyR PROPERTY_5_1_D_3 = new PropertyR(STORE_SQFT,null, null, STORE_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_5_1_D_4 = new PropertyR(GROCERY_SQFT,null, null, GROCERY_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_5_1_D_5 = new PropertyR(FROZEN_SQFT,null, null, FROZEN_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_5_1_D_6 = new PropertyR(MEAT_SQFT,null, null, MEAT_SQFT_COLUMN,PropertyTypeEnum.NUMERIC,null,null,null);
	private static final PropertyR PROPERTY_5_1_D_7 = new PropertyR(HAS_COFFEE_BAR,null, null, COFFEE_BAR,PropertyTypeEnum.BOOLEAN,null,null,null);
	private static final PropertyR PROPERTY_5_1_D_8 = new PropertyR("Street address",null, null, STORE_STREET_ADDRESS,PropertyTypeEnum.STRING,null,null,null);
	private static final PropertyR PROPERTY_5_5_D_1 = new PropertyR(GENDER,null, null, GENDER2, null, null, null,			null);
	private static final PropertyR PROPERTY_5_5_D_2 = new PropertyR(MARITAL_STATUS, null, null, MARITAL_STATUS_COLUMN, null, null,			null, null);
	private static final PropertyR PROPERTY_5_5_D_3 = new PropertyR("Education",null, null, EDUCATION, null, null,			null, null);
	private static final PropertyR PROPERTY_5_5_D_4 = new PropertyR(YEARLY_INCOME, null, null, YEARLY_INCOME_COLUMN, null, null,			null, null);
    private static final ExpressionViewR NAMEEXPRESSION_1 = ExpressionViewRBuilder
        .builder().sql(SqlSelectQueryRBuilder.builder()
            .sqls(List.of(
                SQLRBuilder.builder().dialects(List.of(ORACLE)).statement(FNAME_LNAME2).build(),
                SQLRBuilder.builder().dialects(List.of(H2)).statement(FNAME_LNAME2).build(),
                SQLRBuilder.builder().dialects(List.of(HIVE)).statement("`customer`.`fullname`").build(),
                SQLRBuilder.builder().dialects(List.of(HSQLDB)).statement(FNAME_LNAME2).build(),
                SQLRBuilder.builder().dialects(List.of(ACCESS)).statement(FNAME_LNAME).build(),
                SQLRBuilder.builder().dialects(List.of(POSTGRES)).statement(FNAME_LNAME2).build(),
                SQLRBuilder.builder().dialects(List.of(MYSQL)).statement(CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME).build(),
                SQLRBuilder.builder().dialects(List.of(MARIADB)).statement(CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME).build(),
                SQLRBuilder.builder().dialects(List.of(MSSQL)).statement(FNAME_LNAME).build(),
                SQLRBuilder.builder().dialects(List.of(DERBY)).statement(CUSTOMER_FULLNAME).build(),
                SQLRBuilder.builder().dialects(List.of(DB2)).statement(CONCAT_CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME).build(),
                SQLRBuilder.builder().dialects(List.of(LUCIDDB)).statement(FNAME_LNAME2).build(),
                SQLRBuilder.builder().dialects(List.of(NEOVIEW)).statement(CUSTOMER_FULLNAME).build(),
                SQLRBuilder.builder().dialects(List.of(TERADATA)).statement(FNAME_LNAME2).build(),
                SQLRBuilder.builder().dialects(List.of(SNOWFLAKE)).statement(CUSTOMER_FULLNAME).build(),
                SQLRBuilder.builder().dialects(List.of(GENERIC)).statement(FULLNAME).build()
            ))
            .build())
        .build();
    private static final ExpressionViewR ORDINAL_EXPRESSION_1 = ExpressionViewRBuilder
        .builder().sql(SqlSelectQueryRBuilder.builder()
        .sqls(List.of(
            SQLRBuilder.builder().dialects(List.of(ORACLE)).statement(FNAME_LNAME2).build(),
            SQLRBuilder.builder().dialects(List.of(H2)).statement(FNAME_LNAME2).build(),
            SQLRBuilder.builder().dialects(List.of(HSQLDB)).statement(FNAME_LNAME2).build(),
            SQLRBuilder.builder().dialects(List.of(ACCESS)).statement(FNAME_LNAME).build(),
            SQLRBuilder.builder().dialects(List.of(POSTGRES)).statement(FNAME_LNAME2).build(),
            SQLRBuilder.builder().dialects(List.of(MYSQL)).statement(CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME).build(),
            SQLRBuilder.builder().dialects(List.of(MARIADB)).statement(CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME
            ).build(),
            SQLRBuilder.builder().dialects(List.of(MSSQL)).statement(FNAME_LNAME).build(),
            SQLRBuilder.builder().dialects(List.of(NEOVIEW)).statement(CUSTOMER_FULLNAME).build(),
            SQLRBuilder.builder().dialects(List.of(DERBY)).statement(CUSTOMER_FULLNAME).build(),
            SQLRBuilder.builder().dialects(List.of(DB2)).statement(CONCAT_CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME).build(),
            SQLRBuilder.builder().dialects(List.of(LUCIDDB)).statement(FNAME_LNAME2).build(),
            SQLRBuilder.builder().dialects(List.of(SNOWFLAKE)).statement(CUSTOMER_FULLNAME).build(),
            SQLRBuilder.builder().dialects(List.of(GENERIC)).statement(FULLNAME).build()
        )).build())
        .build();
    private static final ExpressionViewR MEASUREEXPRESSION_1 = ExpressionViewRBuilder
        .builder().sql(SqlSelectQueryRBuilder.builder()
        .sqls(List.of(
            SQLRBuilder.builder().dialects(List.of(ACCESS))
                .statement("Iif(\"sales_fact_1997\".\"promotion_id\" = 0, 0, \"sales_fact_1997\".\"store_sales\")")
                .build(),
            SQLRBuilder.builder().dialects(List.of(ORACLE))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(H2))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(HSQLDB))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(POSTGRES))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(MYSQL))
                .statement("(case when `sales_fact_1997`.`promotion_id` = 0 then 0 else `sales_fact_1997`" +
                    ".`store_sales` end)")
                .build(),
            SQLRBuilder.builder().dialects(List.of(MARIADB))
                .statement("(case when `sales_fact_1997`.`promotion_id` = 0 then 0 else `sales_fact_1997`" +
                    ".`store_sales` end)")
                .build(),
            SQLRBuilder.builder().dialects(List.of(NEOVIEW))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of("infobright"))
                .statement("`sales_fact_1997`.`store_sales`")
                .build(),
            SQLRBuilder.builder().dialects(List.of(DERBY))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(LUCIDDB))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(DB2))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of("nuodb"))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(SNOWFLAKE))
                .statement(CASE_WHEN_SALES_FACT_1997_PROMOTION_ID_0_THEN_0_ELSE_SALES_FACT_1997)
                .build(),
            SQLRBuilder.builder().dialects(List.of(GENERIC))
                .statement("(case when sales_fact_1997.promotion_id = 0 then 0 else sales_fact_1997.store_sales end)")
                .build()
        )).build())
        .build();
    private static final ExpressionViewR MEASUREEXPRESSION_2_7 = ExpressionViewRBuilder
        .builder().sql(SqlSelectQueryRBuilder.builder()
        .sqls(List.of(
            SQLRBuilder.builder().dialects(List.of(H2))
                .statement(WAREHOUSE_SALES_INVENTORY_FACT_1997_WAREHOUSE_COST)
                .build(),
            SQLRBuilder.builder().dialects(List.of(MYSQL))
                .statement(WAREHOUSE_SALES_INVENTORY_FACT_1997_WAREHOUSE_COST)
                .build(),
            SQLRBuilder.builder().dialects(List.of(MARIADB))
                .statement(WAREHOUSE_SALES_INVENTORY_FACT_1997_WAREHOUSE_COST)
                .build(),
            SQLRBuilder.builder().dialects(List.of("infobright"))
                .statement(WAREHOUSE_SALES_INVENTORY_FACT_1997_WAREHOUSE_COST)
                .build(),
            SQLRBuilder.builder().dialects(List.of(GENERIC))
                .statement("&quot;warehouse_sales&quot; - &quot;inventory_fact_1997&quot;.&quot;warehouse_cost&quot;")
                .build()
        )).build())
        .build();
    private static final ClosureR
        CLOSURE_4_7 = ClosureRBuilder
        .builder()
        .parentColumn("supervisor_id")
        .childColumn(EMPLOYEE_ID)
        .table(TABLE_CLOSURE_4_7)
        .build();

    private static final LevelR LEVEL_1_1 = LevelRBuilder
        .builder()
        .name("Media Type")
        .column("media_type")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_1_2 = LevelRBuilder
        .builder()
        .name("Promotion Name")
        .column("promotion_name")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_1_3_A = LevelRBuilder
        .builder()
        .name(COUNTRY)
        .column("country")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_1_3_B = LevelRBuilder
        .builder()
        .name(STATE_PROVINCE)
        .column("state_province")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_1_3_C = LevelRBuilder
        .builder()
        .name("City")
        .column("city")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_1_3_D = LevelRBuilder
        .builder()
        .name("Name")
        .column(CUSTOMER_ID)
        .uniqueMembers(true)
        .type(TypeEnum.NUMERIC)
        .nameExpression(NAMEEXPRESSION_1)
        .ordinalExpression(ORDINAL_EXPRESSION_1)
        .properties(List.of(
            PROPERTY_3_4_1,
            PROPERTY_3_4_2,
            PROPERTY_3_4_3,
            PROPERTY_3_4_4))
        .build();
    private static final LevelR LEVEL_1_4 = LevelRBuilder
        .builder()
        .name(EDUCATION_LEVEL)
        .column(EDUCATION)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_1_5 = LevelRBuilder
        .builder()
        .name(GENDER)
        .column(GENDER2)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_1_6 = LevelRBuilder
        .builder()
        .name(MARITAL_STATUS)
        .column(MARITAL_STATUS_COLUMN)
        .uniqueMembers(true)
        .approxRowCount("111")
        .build();
    private static final LevelR LEVEL_1_7 = LevelRBuilder
        .builder()
        .name(YEARLY_INCOME)
        .column(YEARLY_INCOME_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_3_1 = LevelRBuilder
        .builder()
        .name(STORE_TYPE)
        .column(STORE_TYPE_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_3_2 = LevelRBuilder
        .builder()
        .name(HAS_COFFEE_BAR)
        .column(COFFEE_BAR)
        .uniqueMembers(true)
        .type(TypeEnum.BOOLEAN)
        .build();
    private static final LevelR LEVEL_4_1_A = LevelRBuilder
        .builder()
        .name("Year")
        .column(THE_YEAR)
        .type(TypeEnum.NUMERIC)
        .uniqueMembers(true)
        .levelType(LevelTypeEnum.TIME_YEARS)
        .build();
    private static final LevelR LEVEL_4_1_B = LevelRBuilder
        .builder()
        .name("Quarter")
        .column("quarter")
        .uniqueMembers(false)
        .levelType(LevelTypeEnum.TIME_QUARTERS)
        .build();
    private static final LevelR LEVEL_4_1_C = LevelRBuilder
        .builder()
        .name("Month")
        .column("month_of_year")
        .nameColumn("the_month")
        .uniqueMembers(false)
        .type(TypeEnum.NUMERIC)
        .levelType(LevelTypeEnum.TIME_MONTHS)
        .build();
    private static final LevelR LEVEL_4_2_A = LevelRBuilder
        .builder()
        .name(STORE_COUNTRY)
        .table(STORE_TABLE_NAME)
        .column(STORE_COUNTRY_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_4_2_B = LevelRBuilder
        .builder()
        .name(STORE_STATE)
        .table(STORE_TABLE_NAME)
        .column(STORE_STATE_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_4_2_C = LevelRBuilder
        .builder()
        .name(STORE_CITY)
        .table(STORE_TABLE_NAME)
        .column(STORE_CITY_COLUMN)
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_4_2_D = LevelRBuilder
        .builder()
        .name(STORE_NAME)
        .table(STORE_TABLE_NAME)
        .column(STORE_NAME_COLUMN)
        .uniqueMembers(true)
        .properties(List.of(
            PROPERTY_4_2_D_a,
            PROPERTY_4_2_D_b,
            PROPERTY_4_2_D_c,
            PROPERTY_4_2_D_d,
            PROPERTY_4_2_D_e,
            PROPERTY_4_2_D_f,
            PROPERTY_4_2_D_g,
            PROPERTY_4_2_D_h))
        .build();
    private static final LevelR LEVEL_4_3 = LevelRBuilder
        .builder()
        .name("Pay Type")
        .table("position")
        .column("pay_type")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_4_4 = LevelRBuilder
        .builder()
        .name(STORE_TYPE)
        .table(STORE_TABLE_NAME)
        .column(STORE_TYPE_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_4_5_A = LevelRBuilder
        .builder()
        .name("Management Role")
        .uniqueMembers(true)
        .column("management_role")
        .build();
    private static final LevelR LEVEL_4_5_B = LevelRBuilder
        .builder()
        .name("Position Title")
        .uniqueMembers(false)
        .column("position_title")
        .ordinalColumn(POSITION_ID)
        .build();
    private static final LevelR LEVEL_4_6 = LevelRBuilder
        .builder()
        .name("Department Description")
        .type(TypeEnum.NUMERIC)
        .uniqueMembers(true)
        .column(DEPARTMENT_ID)
        .build();
    private static final LevelR LEVEL_4_7 = LevelRBuilder
        .builder()
        .name("Employee Id")
        .type(TypeEnum.NUMERIC)
        .uniqueMembers(true)
        .column(EMPLOYEE_ID)
        .parentColumn("supervisor_id")
        .nameColumn("full_name")
        .nullParentValue("0")
        .closure(CLOSURE_4_7)
        .properties(List.of(PROPERTY_4_7_A,
            PROPERTY_4_7_B,
            PROPERTY_4_7_C,
            PROPERTY_4_7_D,
            PROPERTY_4_7_E,
            PROPERTY_4_7_F))
        .build();
    private static final LevelR LEVEL_5_1_A = LevelRBuilder
        .builder()
        .name(STORE_COUNTRY)
        .column(STORE_COUNTRY_COLUMN)
        .uniqueMembers(true)
        .hideMemberIf(HideMemberIfEnum.NEVER)
        .build();
    private static final LevelR LEVEL_5_1_B = LevelRBuilder
        .builder()
        .name(STORE_STATE)
        .column(STORE_STATE_COLUMN)
        .uniqueMembers(true)
        .hideMemberIf(HideMemberIfEnum.IF_PARENTS_NAME)
        .build();
    private static final LevelR LEVEL_5_1_C = LevelRBuilder
        .builder()
        .name(STORE_CITY)
        .column(STORE_CITY_COLUMN)
        .uniqueMembers(false)
        .hideMemberIf(HideMemberIfEnum.IF_BLANK_NAME)
        .build();
    private static final LevelR LEVEL_5_1_D = LevelRBuilder
        .builder()
        .name(STORE_NAME)
        .column(STORE_NAME_COLUMN)
        .uniqueMembers(true)
        .hideMemberIf(HideMemberIfEnum.NEVER)
        .properties(List.of(PROPERTY_5_1_D_1,
            PROPERTY_5_1_D_2,
            PROPERTY_5_1_D_3,
            PROPERTY_5_1_D_4,
            PROPERTY_5_1_D_5,
            PROPERTY_5_1_D_6,
            PROPERTY_5_1_D_7,
            PROPERTY_5_1_D_8))
        .build();
    private static final LevelR LEVEL_5_2_A = LevelRBuilder
        .builder()
        .name(COUNTRY)
        .column(STORE_COUNTRY_COLUMN)
        .uniqueMembers(true)
        .hideMemberIf(HideMemberIfEnum.NEVER)
        .build();
    private static final LevelR LEVEL_5_2_B = LevelRBuilder
        .builder()
        .name("State")
        .column(STORE_STATE_COLUMN)
        .uniqueMembers(true)
        .hideMemberIf(HideMemberIfEnum.IF_PARENTS_NAME)
        .build();
    private static final LevelR LEVEL_5_2_C = LevelRBuilder
        .builder()
        .name("City")
        .column(STORE_CITY_COLUMN)
        .uniqueMembers(false)
        .hideMemberIf(HideMemberIfEnum.IF_BLANK_NAME)
        .build();
    private static final LevelR LEVEL_5_3 = LevelRBuilder
        .builder()
        .name("Media Type")
        .column("media_type")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_4 = LevelRBuilder
        .builder()
        .name("Promotion Name")
        .column("promotion_name")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_5_A = LevelRBuilder
        .builder()
        .name(COUNTRY)
        .column("country")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_5_B = LevelRBuilder
        .builder()
        .name(STATE_PROVINCE)
        .column("state_province")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_5_C = LevelRBuilder
        .builder()
        .name("City")
        .column("city")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_5_5_D = LevelRBuilder
        .builder()
        .name("Name")
        .uniqueMembers(true)
        //KeyExpressions missing
        .keyExpression(ExpressionViewRBuilder.builder()
            .sql(SqlSelectQueryRBuilder.builder()
                .sqls(List.of(
                    SQLRBuilder.builder().dialects(List.of(ORACLE)).statement(FNAME_LNAME2).build(),
                    SQLRBuilder.builder().dialects(List.of(H2)).statement(FNAME_LNAME2).build(),
                    SQLRBuilder.builder().dialects(List.of(HSQLDB)).statement(FNAME_LNAME2).build(),
                    SQLRBuilder.builder().dialects(List.of(ACCESS)).statement(FNAME_LNAME).build(),
                    SQLRBuilder.builder().dialects(List.of(POSTGRES)).statement(FNAME_LNAME2).build(),
                    SQLRBuilder.builder().dialects(List.of(MYSQL)).statement("CONCAT(`customer`.`fname`, ' ', `customer`" +
                        ".`lname`)").build(),
                    SQLRBuilder.builder().dialects(List.of(MARIADB)).statement("CONCAT(`customer`.`fname`, ' ', `customer`" +
                        ".`lname`)").build(),
                    SQLRBuilder.builder().dialects(List.of(MSSQL)).statement(FNAME_LNAME).build(),
                    SQLRBuilder.builder().dialects(List.of(DERBY)).statement(CUSTOMER_FULLNAME).build(),
                    SQLRBuilder.builder().dialects(List.of(DB2)).statement(CONCAT_CONCAT_CUSTOMER_FNAME_CUSTOMER_LNAME).build(),
                    SQLRBuilder.builder().dialects(List.of(LUCIDDB)).statement(FNAME_LNAME2).build(),
                    SQLRBuilder.builder().dialects(List.of(NEOVIEW)).statement(CUSTOMER_FULLNAME).build(),
                    SQLRBuilder.builder().dialects(List.of(SNOWFLAKE)).statement(CUSTOMER_FULLNAME).build(),
                    SQLRBuilder.builder().dialects(List.of(GENERIC)).statement(FULLNAME).build()
                )).build())
            .build())
        .properties(List.of(
            PROPERTY_5_5_D_1,
            PROPERTY_5_5_D_2,
            PROPERTY_5_5_D_3,
            PROPERTY_5_5_D_4))
        .build();
    private static final LevelR LEVEL_5_6 = LevelRBuilder
        .builder()
        .name(EDUCATION_LEVEL)
        .column(EDUCATION)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_7 = LevelRBuilder
        .builder()
        .name(GENDER)
        .column(GENDER2)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_8 = LevelRBuilder
        .builder()
        .name(MARITAL_STATUS)
        .column(MARITAL_STATUS_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_5_9 = LevelRBuilder
        .builder()
        .name(YEARLY_INCOME)
        .column(YEARLY_INCOME_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_6_1 = LevelRBuilder
        .builder()
        .name(GENDER)
        .column(GENDER2)
        .uniqueMembers(true)
        .build();

    private static final LevelR LEVEL_SHARED_1_A = LevelRBuilder
        .builder()
        .name(STORE_COUNTRY)
        .column(STORE_COUNTRY_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_1_B = LevelRBuilder
        .builder()
        .name(STORE_STATE)
        .column(STORE_STATE_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_1_C = LevelRBuilder
        .builder()
        .name(STORE_CITY)
        .column(STORE_CITY_COLUMN)
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_SHARED_1_D = LevelRBuilder
        .builder()
        .name(STORE_NAME)
        .column(STORE_NAME_COLUMN)
        .uniqueMembers(true)
        .properties(List.of(PROPERTY_1_A,
            PROPERTY_1_B,
            PROPERTY_1_C,
            PROPERTY_1_D,
            PROPERTY_1_E,
            PROPERTY_1_F,
            PROPERTY_1_G,
            PROPERTY_1_H
        ))
        .build();
    private static final LevelR LEVEL_SHARED_2 = LevelRBuilder
        .builder()
        .name(STORE_SQFT)
        .column(STORE_SQFT_COLUMN)
        .type(TypeEnum.NUMERIC)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_3 = LevelRBuilder
        .builder()
        .name(STORE_TYPE)
        .column(STORE_TYPE_COLUMN)
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_4_A_1 = LevelRBuilder
        .builder()
        .name("Year")
        .column(THE_YEAR)
        .uniqueMembers(true)
        .type(TypeEnum.NUMERIC)
        .levelType(LevelTypeEnum.TIME_YEARS)
        .build();
    private static final LevelR LEVEL_SHARED_4_A_2 = LevelRBuilder
        .builder()
        .name("Quarter")
        .column("quarter")
        .uniqueMembers(false)
        .levelType(LevelTypeEnum.TIME_QUARTERS)
        .build();
    private static final LevelR LEVEL_SHARED_4_A_3 = LevelRBuilder
        .builder()
        .name("Month")
        .column("month_of_year")
        .uniqueMembers(false)
        .type(TypeEnum.NUMERIC)
        .levelType(LevelTypeEnum.TIME_MONTHS)
        .build();
    private static final LevelR LEVEL_SHARED_4_B_1 = LevelRBuilder
        .builder()
        .name("Year")
        .column(THE_YEAR)
        .type(TypeEnum.NUMERIC)
        .uniqueMembers(true)
        .levelType(LevelTypeEnum.TIME_YEARS)
        .build();
    private static final LevelR LEVEL_SHARED_4_B_2 = LevelRBuilder
        .builder()
        .name("Week")
        .column("week_of_year")
        .type(TypeEnum.NUMERIC)
        .uniqueMembers(false)
        .levelType(LevelTypeEnum.TIME_WEEKS)
        .build();
    private static final LevelR LEVEL_SHARED_4_B_3 = LevelRBuilder
        .builder()
        .name("Day")
        .column("day_of_month")
        .uniqueMembers(false)
        .type(TypeEnum.NUMERIC)
        .levelType(LevelTypeEnum.TIME_DAYS)
        .build();
    private static final LevelR LEVEL_SHARED_5_1 = LevelRBuilder
        .builder()
        .name("Product Family")
        .table(PRODUCT_CLASS)
        .column("product_family")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_5_2 = LevelRBuilder
        .builder()
        .name("Product Department")
        .table(PRODUCT_CLASS)
        .column("product_department")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_SHARED_5_3 = LevelRBuilder
        .builder()
        .name("Product Category")
        .table(PRODUCT_CLASS)
        .column("product_category")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_SHARED_5_4 = LevelRBuilder
        .builder()
        .name("Product Subcategory")
        .table(PRODUCT_CLASS)
        .column("product_subcategory")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_SHARED_5_5 = LevelRBuilder
        .builder()
        .name("Brand Name")
        .table(PRODUCT_TABLE_NAME)
        .column("brand_name")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_SHARED_5_6 = LevelRBuilder
        .builder()
        .name("Product Name")
        .table(PRODUCT_TABLE_NAME)
        .column("product_name")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_6_1 = LevelRBuilder
        .builder()
        .name(COUNTRY)
        .column("warehouse_country")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_6_2 = LevelRBuilder
        .builder()
        .name(STATE_PROVINCE)
        .column("warehouse_state_province")
        .uniqueMembers(true)
        .build();
    private static final LevelR LEVEL_SHARED_6_3 = LevelRBuilder
        .builder()
        .name("City")
        .column("warehouse_city")
        .uniqueMembers(false)
        .build();
    private static final LevelR LEVEL_SHARED_6_4 = LevelRBuilder
        .builder()
        .name("Warehouse Name")
        .column("warehouse_name")
        .uniqueMembers(true)
        .build();

    private static final JoinR JOIN_SHARED_1 = new JoinR(
        new JoinedQueryElementR(null, "product_class_id", TABLE_JOIN_1_A),
        new JoinedQueryElementR(null, "product_class_id", TABLE_JOIN_1_B));
    private static final JoinR JOIN_4_2 = new JoinR(
        new JoinedQueryElementR(null, STORE_ID_KEY, TABLE_JOIN_4_2_A),
        new JoinedQueryElementR(null, STORE_ID_KEY, TABLE_JOIN_4_2_B));
    private static final JoinR JOIN_4_3 = new JoinR(
        new JoinedQueryElementR(null, POSITION_ID, TABLE_JOIN_4_3_A),
        new JoinedQueryElementR(null, POSITION_ID, TABLE_JOIN_4_3_B));
    private static final JoinR JOIN_4_4 = new JoinR(
        new JoinedQueryElementR(null, STORE_ID_KEY, TABLE_JOIN_4_4_A),
        new JoinedQueryElementR(null, STORE_ID_KEY, TABLE_JOIN_4_4_B));

    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_1 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(FORMAT_STRING_NAME)
        .value(FORMAT_STRING_0_00)
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_2_1 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(FORMAT_STRING_NAME)
        .value(FORMAT_STRING_0_00)
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_2_2 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("18")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_3 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(FORMAT_STRING_NAME)
        .value("0.0%")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_1 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("1")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_2 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("2")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_3 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("3")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_4 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("6")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_5 = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("7")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_1_A = CalculatedMemberPropertyRBuilder
        .builder()
        .name(FORMAT_STRING_NAME)
        .value(FORMAT_STRING_0_00)
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_1_B = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("4")
        .build();
    private static final CalculatedMemberPropertyR CALCULATEDMEMBER_PROPERTY_6_2_A = CalculatedMemberPropertyRBuilder
        .builder()
        .name(MEMBER_ORDINAL)
        .value("5")
        .build();
    private static final MeasureR MEASURE_1_1 = MeasureRBuilder
        .builder()
        .name(UNIT_SALES)
        .column(UNIT_SALES_KEY)
        .aggregator("sum")
        .formatString(STANDARD)
        .build();
    private static final MeasureR MEASURE_1_2 = MeasureRBuilder
        .builder()
        .name(STORE_COST)
        .column("store_cost")
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .build();
    private static final MeasureR MEASURE_1_3 = MeasureRBuilder
        .builder()
        .name(STORE_SALES)
        .column(STORE_SALES_COLUMN)
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .build();
    private static final MeasureR MEASURE_1_4 = MeasureRBuilder
        .builder()
        .name(SALES_COUNT)
        .column(PRODUCT_ID)
        .aggregator(COUNT)
        .formatString(FORMAT_STRING)
        .build();
    private static final MeasureR MEASURE_1_5 = MeasureRBuilder
        .builder()
        .name(CUSTOMER_COUNT)
        .column(CUSTOMER_ID)
        .aggregator(DISTINCT_COUNT)
        .formatString(FORMAT_STRING)
        .build();
    private static final MeasureR MEASURE_1_6 = MeasureRBuilder
        .builder()
        .name("Promotion Sales")
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .measureExpression(MEASUREEXPRESSION_1)
        .build();
    private static final MeasureR MEASURE_2_1 = MeasureRBuilder
        .builder()
        .name("Store Invoice")
        .column("store_invoice")
        .aggregator("sum")
        .build();
    private static final MeasureR MEASURE_2_2 = MeasureRBuilder
        .builder()
        .name("Supply Time")
        .column("supply_time")
        .aggregator("sum")
        .build();
    private static final MeasureR MEASURE_2_3 = MeasureRBuilder
        .builder()
        .name("Warehouse Cost")
        .column("warehouse_cost")
        .aggregator("sum")
        .build();
    private static final MeasureR MEASURE_2_4 = MeasureRBuilder
        .builder()
        .name("Warehouse Sales")
        .column("warehouse_sales")
        .aggregator("sum")
        .build();
    private static final MeasureR MEASURE_2_5 = MeasureRBuilder
        .builder()
        .name("Units Shipped")
        .column("units_shipped")
        .aggregator("sum")
        .formatString("#.0")
        .build();
    private static final MeasureR MEASURE_2_6 = MeasureRBuilder
        .builder()
        .name("Units Ordered")
        .column("units_ordered")
        .aggregator("sum")
        .formatString("#.0")
        .build();
    private static final MeasureR MEASURE_2_7 = MeasureRBuilder
        .builder()
        .name("Warehouse Profit")
        .aggregator("sum")
        .measureExpression(MEASUREEXPRESSION_2_7)
        .build();
    private static final MeasureR MEASURE_3_1 = MeasureRBuilder
        .builder()
        .name(STORE_SQFT)
        .column(STORE_SQFT_COLUMN)
        .aggregator("sum")
        .formatString(FORMAT_STRING)
        .build();
    private static final MeasureR MEASURE_3_2 = MeasureRBuilder
        .builder()
        .name(GROCERY_SQFT)
        .column(GROCERY_SQFT_COLUMN)
        .aggregator("sum")
        .formatString(FORMAT_STRING)
        .build();
    private static final MeasureR MEASURE_4_1 = MeasureRBuilder
        .builder()
        .name("Org Salary")
        .column("salary_paid")
        .aggregator("sum")
        .formatString(CURRENCY)
        .build();
    private static final MeasureR MEASURE_4_2 = MeasureRBuilder
        .builder()
        .name("Count")
        .column(EMPLOYEE_ID)
        .aggregator(COUNT)
        .formatString("#,#")
        .build();
    private static final MeasureR MEASURE_4_3 = MeasureRBuilder
        .builder()
        .name("Number of Employees")
        .column(EMPLOYEE_ID)
        .aggregator(DISTINCT_COUNT)
        .formatString("#,#")
        .build();
    private static final MeasureR MEASURE_5_1 = MeasureRBuilder
        .builder()
        .name(UNIT_SALES)
        .column(UNIT_SALES_KEY)
        .aggregator("sum")
        .formatString(STANDARD)
        .build();
    private static final MeasureR MEASURE_5_2 = MeasureRBuilder
        .builder()
        .name(STORE_COST)
        .column("store_cost")
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .build();
    private static final MeasureR MEASURE_5_3 = MeasureRBuilder
        .builder()
        .name(STORE_SALES)
        .column(STORE_SALES_COLUMN)
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .build();
    private static final MeasureR MEASURE_5_4 = MeasureRBuilder
        .builder()
        .name(SALES_COUNT)
        .column(PRODUCT_ID)
        .aggregator(COUNT)
        .formatString(FORMAT_STRING)
        .build();
    private static final MeasureR MEASURE_5_5 = MeasureRBuilder
        .builder()
        .name(CUSTOMER_COUNT)
        .column(CUSTOMER_ID)
        .aggregator(DISTINCT_COUNT)
        .formatString(FORMAT_STRING)
        .build();
    private static final MeasureR MEASURE_6_1 = MeasureRBuilder
        .builder()
        .name(SALES_COUNT)
        .column(PRODUCT_ID)
        .aggregator(COUNT)
        .formatString(FORMAT_STRING)
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_1))
        .build();
    private static final MeasureR MEASURE_6_2 = MeasureRBuilder
        .builder()
        .name(UNIT_SALES)
        .column(UNIT_SALES_KEY)
        .aggregator("sum")
        .formatString(STANDARD)
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_2))
        .build();
    private static final MeasureR MEASURE_6_3 = MeasureRBuilder
        .builder()
        .name(STORE_SALES)
        .column(STORE_SALES_COLUMN)
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_3))
        .build();
    private static final MeasureR MEASURE_6_4 = MeasureRBuilder
        .builder()
        .name(STORE_COST)
        .column("store_cost")
        .aggregator("sum")
        .formatString(FORMAT_STRING_00)
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_4))
        .build();
    private static final MeasureR MEASURE_6_5 = MeasureRBuilder
        .builder()
        .name(CUSTOMER_COUNT)
        .column(CUSTOMER_ID)
        .aggregator(DISTINCT_COUNT)
        .formatString(FORMAT_STRING)
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_5))
        .build();

    private static final HierarchyR HIERARCHY_1 = HierarchyRBuilder
        .builder()
        .allMemberName(ALL_MEDIA)
        .hasAll(true)
        .primaryKey(PROMOTION_ID)
        .defaultMember(ALL_MEDIA)
        .relation(TABLE_1)
        .levels(List.of(LEVEL_1_1))
        .build();
    private static final HierarchyR HIERARCHY_2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Promotions")
        .primaryKey(PROMOTION_ID)
        .defaultMember("[All Promotions]")
        .relation(TABLE_2)
        .levels(List.of(LEVEL_1_2))
        .build();
    private static final HierarchyR HIERARCHY_3 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Customers")
        .primaryKey("customer_id")
        .relation(TABLE_3)
        .levels(List.of(LEVEL_1_3_A,
            LEVEL_1_3_B,
            LEVEL_1_3_C,
            LEVEL_1_3_D))
        .build();
    private static final HierarchyR HIERARCHY_4 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_4)
        .levels(List.of(LEVEL_1_4))
        .build();
    private static final HierarchyR HIERARCHY_5 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Gender")
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_5)
        .levels(List.of(LEVEL_1_5))
        .build();
    private static final HierarchyR HIERARCHY_6 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Marital Status")
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_6)
        .levels(List.of(LEVEL_1_6))
        .build();
    private static final HierarchyR HIERARCHY_7 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_7)
        .levels(List.of(LEVEL_1_7))
        .build();
    private static final HierarchyR HIERARCHY_3_1 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .levels(List.of(LEVEL_3_1))
        .build();
    private static final HierarchyR HIERARCHY_3_2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .levels(List.of(LEVEL_3_2))
        .build();
    private static final HierarchyR HIERARCHY_4_1 = HierarchyRBuilder
        .builder()
        .hasAll(false)
        .primaryKey("the_date")
        .relation(TABLE_4_1)
        .levels(List.of(
            LEVEL_4_1_A,
            LEVEL_4_1_B,
            LEVEL_4_1_C))
        .build();
    private static final HierarchyR HIERARCHY_4_2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(EMPLOYEE_ID)
        .primaryKeyTable(EMPLOYEE)
        .relation(JOIN_4_2)
        .levels(List.of(
            LEVEL_4_2_A,
            LEVEL_4_2_B,
            LEVEL_4_2_C,
            LEVEL_4_2_D))
        .build();
    private static final HierarchyR HIERARCHY_4_3 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(EMPLOYEE_ID)
        .primaryKeyTable(EMPLOYEE)
        .relation(JOIN_4_3)
        .levels(List.of(LEVEL_4_3))
        .build();
    private static final HierarchyR HIERARCHY_4_4 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKeyTable(EMPLOYEE)
        .primaryKey(EMPLOYEE_ID)
        .relation(JOIN_4_4)
        .levels(List.of(LEVEL_4_4))
        .build();
    private static final HierarchyR HIERARCHY_4_5 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Position")
        .primaryKey(EMPLOYEE_ID)
        .relation(TABLE_4_5)
        .levels(List.of(LEVEL_4_5_A,
            LEVEL_4_5_B))
        .build();
    private static final HierarchyR HIERARCHY_4_6 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(DEPARTMENT_ID)
        .relation(TABLE_4_6)
        .levels(List.of(LEVEL_4_6))
        .build();
    private static final HierarchyR HIERARCHY_4_7 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Employees")
        .primaryKey(EMPLOYEE_ID)
        .relation(TABLE_4_7)
        .levels(List.of(LEVEL_4_7))
        .build();
    private static final HierarchyR HIERARCHY_5_1 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(STORE_ID_KEY)
        .relation(TABLE_5_1)
        .levels(List.of(LEVEL_5_1_A,
            LEVEL_5_1_B,
            LEVEL_5_1_C,
            LEVEL_5_1_D))
        .build();
    private static final HierarchyR HIERARCHY_5_2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(STORE_ID_KEY)
        .relation(TABLE_5_2)
        .levels(List.of(LEVEL_5_2_A,
            LEVEL_5_2_B,
            LEVEL_5_2_C))
        .build();
    private static final HierarchyR HIERARCHY_5_3 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName(ALL_MEDIA)
        .primaryKey(PROMOTION_ID)
        .relation(TABLE_5_3)
        .levels(List.of(LEVEL_5_3))
        .build();
    private static final HierarchyR HIERARCHY_5_4 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Promotions")
        .primaryKey(PROMOTION_ID)
        .relation(TABLE_5_4)
        .levels(List.of(LEVEL_5_4))
        .build();
    private static final HierarchyR HIERARCHY_5_5 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Customers")
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_5_5)
        .levels(List.of(LEVEL_5_5_A,
            LEVEL_5_5_B,
            LEVEL_5_5_C,
            LEVEL_5_5_D))
        .build();
    private static final HierarchyR HIERARCHY_5_6 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_5_6)
        .levels(List.of(LEVEL_5_6))
        .build();
    private static final HierarchyR HIERARCHY_5_7 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Gender")
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_5_7)
        .levels(List.of(LEVEL_5_7))
        .build();
    private static final HierarchyR HIERARCHY_5_8 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Marital Status")
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_5_8)
        .levels(List.of(LEVEL_5_8))
        .build();
    private static final HierarchyR HIERARCHY_5_9 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_5_9)
        .levels(List.of(LEVEL_5_9))
        .build();
    private static final HierarchyR HIERARCHY_6_1 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .allMemberName("All Gender")
        .primaryKey(CUSTOMER_ID)
        .relation(TABLE_6_1)
        .levels(List.of(LEVEL_6_1))
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_1 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(STORE_ID_KEY)
        .relation(TABLE_SHARED_1)
        .levels(List.of(LEVEL_SHARED_1_A,
            LEVEL_SHARED_1_B,
            LEVEL_SHARED_1_C,
            LEVEL_SHARED_1_D
        ))
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(STORE_ID_KEY)
        .relation(TABLE_SHARED_2)
        .levels(List.of(LEVEL_SHARED_2))
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_3 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey(STORE_ID_KEY)
        .relation(TABLE_SHARED_3)
        .levels(List.of(LEVEL_SHARED_3))
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_4_1 = HierarchyRBuilder
        .builder()
        .hasAll(false)
        .primaryKey(TIME_ID_KEY)
        .relation(TABLE_SHARED_4_A)
        .levels(List.of(LEVEL_SHARED_4_A_1,
            LEVEL_SHARED_4_A_2,
            LEVEL_SHARED_4_A_3
        ))
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_4_2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name("Weekly")
        .primaryKey(TIME_ID_KEY)
        .relation(TABLE_SHARED_4_B)
        .levels(List.of(LEVEL_SHARED_4_B_1,
            LEVEL_SHARED_4_B_2,
            LEVEL_SHARED_4_B_3
        ))
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_5 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey("product_id")
        .primaryKeyTable("product")
        .levels(List.of(LEVEL_SHARED_5_1,
            LEVEL_SHARED_5_2,
            LEVEL_SHARED_5_3,
            LEVEL_SHARED_5_4,
            LEVEL_SHARED_5_5,
            LEVEL_SHARED_5_6))
        .relation(JOIN_SHARED_1)
        .build();
    private static final HierarchyR HIERARCHY_SCHEMA_6 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .primaryKey("warehouse_id")
        .relation(TABLE_SHARED_6)
        .levels(List.of(LEVEL_SHARED_6_1,
            LEVEL_SHARED_6_2,
            LEVEL_SHARED_6_3,
            LEVEL_SHARED_6_4))
        .build();

    private static final MappingDimensionUsage DIMENSION_USAGE_1 = DimensionUsageRBuilder
        .builder()
        .name(STORE)
        .source(STORE)
        .foreignKey(STORE_ID_KEY)
        .build();
    private static final MappingDimensionUsage DIMENSION_USAGE_2 = DimensionUsageRBuilder
        .builder()
        .name(STORE_SIZE_IN_SQFT)
        .source(STORE_SIZE_IN_SQFT)
        .foreignKey(STORE_ID_KEY)
        .build();
    private static final MappingDimensionUsage DIMENSION_USAGE_3 = DimensionUsageRBuilder
        .builder()
        .name(STORE_TYPE)
        .source(STORE_TYPE)
        .foreignKey(STORE_ID_KEY)
        .build();
    private static final MappingDimensionUsage DIMENSION_USAGE_4 = DimensionUsageRBuilder
        .builder()
        .name("Time")
        .source("Time")
        .foreignKey(TIME_ID_KEY)
        .build();
    private static final MappingDimensionUsage DIMENSION_USAGE_5 = DimensionUsageRBuilder
        .builder()
        .name(PRODUCT)
        .source(PRODUCT)
        .foreignKey(PRODUCT_ID)
        .build();
    private static final MappingDimensionUsage DIMENSION_USAGE_6 = DimensionUsageRBuilder
        .builder()
        .name(WAREHOUSE)
        .source(WAREHOUSE)
        .foreignKey("warehouse_id")
        .build();
    private static final MappingDimensionUsage DIMENSION_USAGE_3_1 = DimensionUsageRBuilder
        .builder()
        .name(STORE)
        .source(STORE)
        .build();

    private static final PrivateDimensionR DIMENSION_1 = PrivateDimensionRBuilder
        .builder()
        .name(PROMOTION_MEDIA)
        .foreignKey(PROMOTION_ID)
        .hierarchies(List.of(HIERARCHY_1))
        .build();
    private static final PrivateDimensionR DIMENSION_2 = PrivateDimensionRBuilder
        .builder()
        .name(PROMOTIONS)
        .foreignKey(PROMOTION_ID)
        .hierarchies(List.of(HIERARCHY_2))
        .build();
    private static final PrivateDimensionR DIMENSION_3 = PrivateDimensionRBuilder
        .builder()
        .name(CUSTOMERS)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_3))
        .build();
    private static final PrivateDimensionR DIMENSION_4 = PrivateDimensionRBuilder
        .builder()
        .name(EDUCATION_LEVEL)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_4))
        .build();
    private static final PrivateDimensionR DIMENSION_5 = PrivateDimensionRBuilder
        .builder()
        .name(GENDER)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_5))
        .build();
    private static final PrivateDimensionR DIMENSION_6 = PrivateDimensionRBuilder
        .builder()
        .name("Marital Status")
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_6))
        .build();
    private static final PrivateDimensionR DIMENSION_7 = PrivateDimensionRBuilder
        .builder()
        .name(YEARLY_INCOME)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_7))
        .build();
    private static final PrivateDimensionR DIMENSION_3_1 = PrivateDimensionRBuilder
        .builder()
        .name(STORE_TYPE)
        .hierarchies(List.of(HIERARCHY_3_1))
        .build();
    private static final PrivateDimensionR DIMENSION_3_2 = PrivateDimensionRBuilder
        .builder()
        .name(HAS_COFFEE_BAR)
        .hierarchies(List.of(HIERARCHY_3_2))
        .build();
    private static final PrivateDimensionR DIMENSION_4_1 = PrivateDimensionRBuilder
        .builder()
        .name("Time")
        .type(DimensionTypeEnum.TIME_DIMENSION)
        .foreignKey("pay_date")
        .hierarchies(List.of(HIERARCHY_4_1))
        .build();
    private static final PrivateDimensionR DIMENSION_4_2 = PrivateDimensionRBuilder
        .builder()
        .name(STORE)
        .foreignKey(EMPLOYEE_ID)
        .hierarchies(List.of(HIERARCHY_4_2))
        .build();
    private static final PrivateDimensionR DIMENSION_4_3 = PrivateDimensionRBuilder
        .builder()
        .name("Pay Type")
        .foreignKey(EMPLOYEE_ID)
        .hierarchies(List.of(HIERARCHY_4_3))
        .build();
    private static final PrivateDimensionR DIMENSION_4_4 = PrivateDimensionRBuilder
        .builder()
        .name(STORE_TYPE)
        .foreignKey(EMPLOYEE_ID)
        .hierarchies(List.of(HIERARCHY_4_4))
        .build();
    private static final PrivateDimensionR DIMENSION_4_5 = PrivateDimensionRBuilder
        .builder()
        .name("Position")
        .foreignKey(EMPLOYEE_ID)
        .hierarchies(List.of(HIERARCHY_4_5))
        .build();
    private static final PrivateDimensionR DIMENSION_4_6 = PrivateDimensionRBuilder
        .builder()
        .name("Department")
        .foreignKey(DEPARTMENT_ID)
        .hierarchies(List.of(HIERARCHY_4_6))
        .build();
    private static final PrivateDimensionR DIMENSION_4_7 = PrivateDimensionRBuilder
        .builder()
        .name("Employees")
        .foreignKey(EMPLOYEE_ID)
        .hierarchies(List.of(HIERARCHY_4_7))
        .build();
    private static final PrivateDimensionR DIMENSION_5_1 = PrivateDimensionRBuilder
        .builder()
        .name(STORE)
        .foreignKey(STORE_ID_KEY)
        .hierarchies(List.of(HIERARCHY_5_1))
        .build();
    private static final PrivateDimensionR DIMENSION_5_2 = PrivateDimensionRBuilder
        .builder()
        .name("Geography")
        .foreignKey(STORE_ID_KEY)
        .hierarchies(List.of(HIERARCHY_5_2))
        .build();
    private static final PrivateDimensionR DIMENSION_5_3 = PrivateDimensionRBuilder
        .builder()
        .name(PROMOTION_MEDIA)
        .foreignKey(PROMOTION_ID)
        .hierarchies(List.of(HIERARCHY_5_3))
        .build();
    private static final PrivateDimensionR DIMENSION_5_4 = PrivateDimensionRBuilder
        .builder()
        .name(PROMOTIONS)
        .foreignKey(PROMOTION_ID)
        .hierarchies(List.of(HIERARCHY_5_4))
        .build();
    private static final PrivateDimensionR DIMENSION_5_5 = PrivateDimensionRBuilder
        .builder()
        .name(CUSTOMERS)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_5_5))
        .build();
    private static final PrivateDimensionR DIMENSION_5_6 = PrivateDimensionRBuilder
        .builder()
        .name(EDUCATION_LEVEL)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_5_6))
        .build();
    private static final PrivateDimensionR DIMENSION_5_7 = PrivateDimensionRBuilder
        .builder()
        .name(GENDER)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_5_7))
        .build();
    private static final PrivateDimensionR DIMENSION_5_8 = PrivateDimensionRBuilder
        .builder()
        .name(MARITAL_STATUS)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_5_8))
        .build();
    private static final PrivateDimensionR DIMENSION_5_9 = PrivateDimensionRBuilder
        .builder()
        .name(YEARLY_INCOME)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_5_9))
        .build();
    private static final PrivateDimensionR DIMENSION_6_1 = PrivateDimensionRBuilder
        .builder()
        .name(GENDER)
        .foreignKey(CUSTOMER_ID)
        .hierarchies(List.of(HIERARCHY_6_1))
        .build();
    private static final PrivateDimensionR DIMENSION_SCHEMA_1 = PrivateDimensionRBuilder
        .builder()
        .name(STORE)
        .hierarchies(List.of(HIERARCHY_SCHEMA_1))
        .build();
    private static final PrivateDimensionR DIMENSION_SCHEMA_2 = PrivateDimensionRBuilder
        .builder()
        .name(STORE_SIZE_IN_SQFT)
        .hierarchies(List.of(HIERARCHY_SCHEMA_2))
        .build();
    private static final PrivateDimensionR DIMENSION_SCHEMA_3 = PrivateDimensionRBuilder
        .builder()
        .name(STORE_TYPE)
        .hierarchies(List.of(HIERARCHY_SCHEMA_3))
        .build();
    private static final PrivateDimensionR DIMENSION_SCHEMA_4 = PrivateDimensionRBuilder
        .builder()
        .name("Time")
        .type(DimensionTypeEnum.TIME_DIMENSION)
        .hierarchies(List.of(HIERARCHY_SCHEMA_4_1, HIERARCHY_SCHEMA_4_2))
        .build();
    private static final PrivateDimensionR DIMENSION_SCHEMA_5 = PrivateDimensionRBuilder
        .builder()
        .name(PRODUCT)
        .hierarchies(List.of(HIERARCHY_SCHEMA_5))
        .build();
    private static final PrivateDimensionR DIMENSION_SCHEMA_6 = PrivateDimensionRBuilder
        .builder()
        .name(WAREHOUSE)
        .hierarchies(List.of(HIERARCHY_SCHEMA_6))
        .build();

    private static final AnnotationR ANNOTATION_1 = AnnotationRBuilder
        .builder()
        .name("caption.de_DE")
        .content("Verkaufen")
        .build();
    private static final AnnotationR ANNOTATION_2 = AnnotationRBuilder
        .builder()
        .name("caption.fr_FR")
        .content("Ventes")
        .build();
    private static final AnnotationR ANNOTATION_3 = AnnotationRBuilder
        .builder()
        .name("description.fr_FR")
        .content("Cube des ventes")
        .build();
    private static final AnnotationR ANNOTATION_4 = AnnotationRBuilder
        .builder()
        .name("description.de")
        .content("Cube Verkaufen")
        .build();
    private static final AnnotationR ANNOTATION_5 = AnnotationRBuilder
        .builder()
        .name("description.de_AT")
        .content("Cube den Verkaufen")
        .build();

    private static final AggExcludeR CUBE1_TABLE_AGG_EXCLUDE1 = AggExcludeRBuilder
        .builder()
        .name(AGG_C_SPECIAL_SALES_FACT_1997)
        .build();
    private static final AggExcludeR CUBE1_TABLE_AGG_EXCLUDE2 = AggExcludeRBuilder
        .builder()
        .name("agg_lc_100_sales_fact_1997")
        .build();
    private static final AggExcludeR CUBE1_TABLE_AGG_EXCLUDE3 = AggExcludeRBuilder
        .builder()
        .name("agg_lc_10_sales_fact_1997")
        .build();
    private static final AggExcludeR CUBE1_TABLE_AGG_EXCLUDE4 = AggExcludeRBuilder
        .builder()
        .name("agg_pc_10_sales_fact_1997")
        .build();

    private static final MappingAggTable CUBE1_TABLE_AGG_TABLE1 = AggNameRBuilder
        .builder()
        .name(AGG_C_SPECIAL_SALES_FACT_1997)
        .aggFactCount(
            AggColumnNameRBuilder.builder().column("FACT_COUNT").build())
        .aggIgnoreColumns(List.of(
            AggColumnNameRBuilder.builder().column("foo").build(),
            AggColumnNameRBuilder.builder().column("bar").build()
        ))
        .aggForeignKeys(List.of(
            AggForeignKeyRBuilder.builder().factColumn(PRODUCT_ID).aggColumn("PRODUCT_ID").build(),
            AggForeignKeyRBuilder.builder().factColumn(CUSTOMER_ID).aggColumn("CUSTOMER_ID").build(),
            AggForeignKeyRBuilder.builder().factColumn(PROMOTION_ID).aggColumn("PROMOTION_ID").build(),
            AggForeignKeyRBuilder.builder().factColumn(STORE_ID_KEY).aggColumn("STORE_ID").build()
        ))
        .aggMeasures(List.of(
            AggMeasureRBuilder.builder().name("[Measures].[Unit Sales]").column("UNIT_SALES_SUM").build(),
            AggMeasureRBuilder.builder().name("[Measures].[Store Cost]").column("STORE_COST_SUM").build(),
            AggMeasureRBuilder.builder().name("[Measures].[Store Sales]").column("STORE_SALES_SUM").build()
        ))
        .aggLevels(List.of(
            AggLevelRBuilder.builder().name("[Time].[Year]").column("TIME_YEAR").build(),
            AggLevelRBuilder.builder().name("[Time].[Quarter]").column("TIME_QUARTER").build(),
            AggLevelRBuilder.builder().name("[Time].[Month]").column("TIME_MONTH").build()
        ))
        .build();

    private static final TableR CUBE1_TABLE = new TableR(SALES_FACT_1997,
        List.of(
            CUBE1_TABLE_AGG_EXCLUDE1,
            CUBE1_TABLE_AGG_EXCLUDE2,
            CUBE1_TABLE_AGG_EXCLUDE3,
            CUBE1_TABLE_AGG_EXCLUDE4
        ),
        List.of(
            CUBE1_TABLE_AGG_TABLE1
        ));
    private static final TableR CUBE2_TABLE = new TableR("inventory_fact_1997");
    private static final TableR CUBE3_TABLE = new TableR(STORE_TABLE_NAME);
    private static final TableR CUBE4_TABLE = new TableR("salary");
    private static final TableR CUBE5_TABLE = new TableR(SALES_FACT_1997,
        List.of(
            AggExcludeRBuilder.builder().name("agg_pc_10_sales_fact_1997").build(),
            AggExcludeRBuilder.builder().name("agg_lc_10_sales_fact_1997").build()
        ),
        null);
    private static final TableR CUBE6_TABLE = new TableR(SALES_FACT_1997);

    private static final CalculatedMemberR CALCULATEDMEMBER_1 = CalculatedMemberRBuilder
        .builder()
        .name("Profit")
        .dimension(MEASURES)
        .formulaElement(FormulaRBuilder.builder().cdata("[Measures].[Store Sales] - [Measures].[Store Cost]").build())
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_1))
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_2 = CalculatedMemberRBuilder
        .builder()
        .name("Profit last Period")
        .dimension(MEASURES)
        .formula("COALESCEEMPTY((Measures.[Profit], [Time].[Time].PREVMEMBER),    Measures.[Profit])")
        .visible(false)
        .calculatedMemberProperties(List.of(
            CALCULATEDMEMBER_PROPERTY_2_1,
            CALCULATEDMEMBER_PROPERTY_2_2))
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_3 = CalculatedMemberRBuilder
        .builder()
        .name("Profit Growth")
        .dimension(MEASURES)
        .formula("([Measures].[Profit] - [Measures].[Profit last Period]) / [Measures].[Profit last Period]")
        .visible(true)
        .caption("Gewinn-Wachstum")
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_3))
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_2_1 = CalculatedMemberRBuilder
        .builder()
        .name("Average Warehouse Sale")
        .dimension(MEASURES)
        .formulaElement(FormulaRBuilder.builder().cdata("[Measures].[Warehouse Sales] / [Measures].[Warehouse Cost]").build())
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_2_1))
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_4_1 = CalculatedMemberRBuilder
        .builder()
        .name("Employee Salary")
        .dimension(MEASURES)
        .formatString(CURRENCY)
        .formula("([Employees].currentmember.datamember, [Measures].[Org Salary])")
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_4_2 = CalculatedMemberRBuilder
        .builder()
        .name("Avg Salary")
        .dimension(MEASURES)
        .formatString(CURRENCY)
        .formula("[Measures].[Org Salary]/[Measures].[Number of Employees]")
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_6_1 = CalculatedMemberRBuilder
        .builder()
        .name("Profit")
        .dimension(MEASURES)
        .formulaElement(FormulaRBuilder.builder().cdata("[Measures].[Store Sales] - [Measures].[Store Cost]").build())
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_1_A,
            CALCULATEDMEMBER_PROPERTY_6_1_B))
        .build();
    private static final CalculatedMemberR CALCULATEDMEMBER_6_2 = CalculatedMemberRBuilder
        .builder()
        .name("Profit last Period")
        .dimension(MEASURES)
        .formula("COALESCEEMPTY((Measures.[Profit], [Time].[Time].PREVMEMBER),    Measures.[Profit])")
        .visible(false)
        .calculatedMemberProperties(List.of(CALCULATEDMEMBER_PROPERTY_6_2_A))
        .build();
    private static final CalculatedMemberR VIRTUAL_CALCULATED_MEMBER_1 = CalculatedMemberRBuilder
        .builder()
        .name("Profit Per Unit Shipped")
        .dimension(MEASURES)
        .formulaElement(FormulaRBuilder
            .builder()
            .cdata("[Measures].[Profit] / [Measures].[Units Shipped]")
            .build())
        .build();

    private static final NamedSetR NAMED_SET_2_1 = NamedSetRBuilder.builder()
        .name("Top Sellers")
        .formulaElement(
            FormulaRBuilder.builder()
                .cdata("TopCount([Warehouse].[Warehouse Name].MEMBERS, 5, [Measures].[Warehouse Sales])").build())
        .build();

    private static final CubeR CUBE_1 = CubeRBuilder
        .builder()
        .name(CUBE_NAME_1)
        .defaultMeasure(UNIT_SALES)
        .annotations(List.of(ANNOTATION_1,
            ANNOTATION_2,
            ANNOTATION_3,
            ANNOTATION_4,
            ANNOTATION_5))
        .fact(CUBE1_TABLE)
        .dimensionUsageOrDimensions(List.of(
            DIMENSION_USAGE_1,
            DIMENSION_USAGE_2,
            DIMENSION_USAGE_3,
            DIMENSION_USAGE_4,
            DIMENSION_USAGE_5,
            DIMENSION_1,
            DIMENSION_2,
            DIMENSION_3,
            DIMENSION_4,
            DIMENSION_5,
            DIMENSION_6,
            DIMENSION_7))
        .measures(List.of(
            MEASURE_1_1,
            MEASURE_1_2,
            MEASURE_1_3,
            MEASURE_1_4,
            MEASURE_1_5,
            MEASURE_1_6))
        .calculatedMembers(List.of(
            CALCULATEDMEMBER_1,
            CALCULATEDMEMBER_2,
            CALCULATEDMEMBER_3))
        .build();
    private static final CubeR CUBE_2 = CubeRBuilder
        .builder()
        .name(WAREHOUSE)
        .fact(CUBE2_TABLE)
        .dimensionUsageOrDimensions(List.of(DIMENSION_USAGE_1,
            DIMENSION_USAGE_2,
            DIMENSION_USAGE_3,
            DIMENSION_USAGE_4,
            DIMENSION_USAGE_5,
            DIMENSION_USAGE_6))
        .measures(List.of(
            MEASURE_2_1,
            MEASURE_2_2,
            MEASURE_2_3,
            MEASURE_2_4,
            MEASURE_2_5,
            MEASURE_2_6,
            MEASURE_2_7))
        .calculatedMembers(List.of(CALCULATEDMEMBER_2_1))
        .namedSets(List.of(NAMED_SET_2_1))
        .build();
    private static final CubeR CUBE_3 = CubeRBuilder
        .builder()
        .name(STORE)
        .fact(CUBE3_TABLE)
        .dimensionUsageOrDimensions(List.of(
            DIMENSION_3_1,
            DIMENSION_USAGE_3_1,
            DIMENSION_3_2))
        .measures(List.of(
            MEASURE_3_1,
            MEASURE_3_2))
        .build();
    private static final CubeR CUBE_4 = CubeRBuilder
        .builder()
        .name("HR")
        .fact(CUBE4_TABLE)
        .dimensionUsageOrDimensions(List.of(
            DIMENSION_4_1,
            DIMENSION_4_2,
            DIMENSION_4_3,
            DIMENSION_4_4,
            DIMENSION_4_5,
            DIMENSION_4_6,
            DIMENSION_4_7))
        .measures(List.of(
            MEASURE_4_1,
            MEASURE_4_2,
            MEASURE_4_3))
        .calculatedMembers(List.of(
            CALCULATEDMEMBER_4_1,
            CALCULATEDMEMBER_4_2))
        .build();
    private static final CubeR CUBE_5 = CubeRBuilder
        .builder()
        .name("Sales Ragged")
        .fact(CUBE5_TABLE)
        .measures(List.of(
            MEASURE_5_1,
            MEASURE_5_2,
            MEASURE_5_3,
            MEASURE_5_4,
            MEASURE_5_5))
        .dimensionUsageOrDimensions(List.of(
            DIMENSION_5_1, //Story
            DIMENSION_5_2, //Geography
            DIMENSION_USAGE_2, //Store Size in SQFT
            DIMENSION_USAGE_3, // Store Type
            DIMENSION_USAGE_4, // Time
            DIMENSION_USAGE_5, //Product
            DIMENSION_5_3, //Promotion Media
            DIMENSION_5_4, //Promotion
            DIMENSION_5_5, //Customers
            DIMENSION_5_6, //Education Level
            DIMENSION_5_7, //Gender
            DIMENSION_5_8, //Marital Status
            DIMENSION_5_9)) //Yearly Income
        .build();
    private static final CubeR CUBE_6 = CubeRBuilder
        .builder()
        .name("Sales 2")
        .fact(CUBE6_TABLE)
        .measures(List.of(MEASURE_6_1,
            MEASURE_6_2,
            MEASURE_6_3,
            MEASURE_6_4,
            MEASURE_6_5))
        .calculatedMembers(List.of(CALCULATEDMEMBER_6_1,
            CALCULATEDMEMBER_6_2))
        .dimensionUsageOrDimensions(List.of(DIMENSION_USAGE_4,
            DIMENSION_USAGE_5,
            DIMENSION_6_1))
        .build();

    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_1 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(CUSTOMERS)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_2 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(EDUCATION_LEVEL)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_3 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(GENDER)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_4 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(MARITAL_STATUS)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_5 = VirtualCubeDimensionRBuilder
        .builder()
        .name(PRODUCT)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_6 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(PROMOTION_MEDIA)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_7 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(PROMOTIONS)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_8 = VirtualCubeDimensionRBuilder
        .builder()
        .name(STORE)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_9 = VirtualCubeDimensionRBuilder
        .builder()
        .name("Time")
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_10 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(SALES)
        .name(YEARLY_INCOME)
        .build();
    private static final VirtualCubeDimensionR VIRTUAL_DIMENSION_11 = VirtualCubeDimensionRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name(WAREHOUSE)
        .build();

    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_1 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(SALES)
        .name("[Measures].[Sales Count]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_2 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(SALES)
        .name("[Measures].[Store Cost]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_3 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(SALES)
        .name("[Measures].[Store Sales]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_4 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(SALES)
        .name("[Measures].[Unit Sales]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_5 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(SALES)
        .name("[Measures].[Profit]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_6 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(SALES)
        .name("[Measures].[Profit Growth]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_7 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Store Invoice]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_8 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Supply Time]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_9 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Units Ordered]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_10 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Units Shipped]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_11 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Warehouse Cost]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_12 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Warehouse Profit]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_13 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Warehouse Sales]")
        .build();
    private static final VirtualCubeMeasureR VIRTUAL_MEASURE_14 = VirtualCubeMeasureRBuilder
        .builder()
        .cubeName(WAREHOUSE)
        .name("[Measures].[Average Warehouse Sale]")
        .build();

    private static final VirtualCubeR VIRTUAL_CUBE_1 = VirtualCubeRBuilder
        .builder()
        .name("Warehouse and Sales")
        .defaultMeasure(STORE_SALES)
        .calculatedMembers(List.of(VIRTUAL_CALCULATED_MEMBER_1))
        .virtualCubeMeasures(List.of(VIRTUAL_MEASURE_1,
            VIRTUAL_MEASURE_2,
            VIRTUAL_MEASURE_3,
            VIRTUAL_MEASURE_4,
            VIRTUAL_MEASURE_5,
            VIRTUAL_MEASURE_6,
            VIRTUAL_MEASURE_7,
            VIRTUAL_MEASURE_8,
            VIRTUAL_MEASURE_9,
            VIRTUAL_MEASURE_10,
            VIRTUAL_MEASURE_11,
            VIRTUAL_MEASURE_12,
            VIRTUAL_MEASURE_13,
            VIRTUAL_MEASURE_14))
        .virtualCubeDimensions(List.of(VIRTUAL_DIMENSION_1,
            VIRTUAL_DIMENSION_2,
            VIRTUAL_DIMENSION_3,
            VIRTUAL_DIMENSION_4,
            VIRTUAL_DIMENSION_5,
            VIRTUAL_DIMENSION_6,
            VIRTUAL_DIMENSION_7,
            VIRTUAL_DIMENSION_8,
            VIRTUAL_DIMENSION_9,
            VIRTUAL_DIMENSION_10,
            VIRTUAL_DIMENSION_11))
        .build();

    private static final RoleR ROLE_1 = RoleRBuilder
        .builder().name("California manager")
        .schemaGrants(List.of(SchemaGrantRBuilder
            .builder()
            .access(AccessEnum.NONE)
            .cubeGrants(List.of(CubeGrantRBuilder
                .builder()
                .cube(SALES)
                .access("all")
                .hierarchyGrants(List.of(HierarchyGrantRBuilder
                        .builder()
                        .hierarchy("[Store]")
                        .access(AccessEnum.CUSTOM)
                        .topLevel("[Store].[Store Country]")
                        .memberGrants(List.of(MemberGrantRBuilder
                                .builder()
                                .member("[Store].[USA].[CA]")
                                .access(MemberGrantAccessEnum.ALL)
                                .build(),
                            MemberGrantRBuilder
                                .builder()
                                .member("[Store].[USA].[CA].[Los Angeles]")
                                .access(MemberGrantAccessEnum.NONE)
                                .build()))
                        .build(),
                    HierarchyGrantRBuilder
                        .builder()
                        .hierarchy("[Customers]")
                        .access(AccessEnum.CUSTOM)
                        .topLevel("[Customers].[State Province]")
                        .bottomLevel("[Customers].[City]")
                        .memberGrants(List.of(MemberGrantRBuilder
                                .builder()
                                .member("[Customers].[USA].[CA]")
                                .access(MemberGrantAccessEnum.ALL)
                                .build(),
                            MemberGrantRBuilder
                                .builder()
                                .member("[Customers].[USA].[CA].[Los Angeles]")
                                .access(MemberGrantAccessEnum.NONE)
                                .build()))
                        .build(),
                    HierarchyGrantRBuilder
                        .builder()
                        .hierarchy("[Gender]")
                        .access(AccessEnum.NONE)
                        .build()))
                .build()))
            .build()))
        .build();
    private static final RoleR ROLE_2 = RoleRBuilder
        .builder()
        .name("No HR Cube")
        .schemaGrants(List.of(SchemaGrantRBuilder
            .builder()
            .access(AccessEnum.ALL)
            .cubeGrants(List.of(CubeGrantRBuilder
                .builder()
                .cube("HR")
                .access("none")
                .build()))
            .build()))
        .build();
    private static final RoleR ROLE_3 = RoleRBuilder
        .builder()
        .name("Administrator")
        .schemaGrants(List.of(SchemaGrantRBuilder
            .builder()
            .access(AccessEnum.ALL)
            .build()))
        .build();

    private static final MappingSchema
        SCHEMA = new SchemaR(
        SCHEMA_NAME,
        null,
        List.of(),
        null,
        null,
        List.of(),
        List.of(
            DIMENSION_SCHEMA_1,
            DIMENSION_SCHEMA_2,
            DIMENSION_SCHEMA_3,
            DIMENSION_SCHEMA_4,
            DIMENSION_SCHEMA_5,
            DIMENSION_SCHEMA_6
        ),
        List.of(CUBE_1,
            CUBE_2,
            CUBE_3,
            CUBE_4,
            CUBE_5,
            CUBE_6),
        List.of(VIRTUAL_CUBE_1),
        List.of(),
        List.of(ROLE_1, ROLE_2, ROLE_3),
        List.of(),
        Optional.empty()
       );

    @Override
    public MappingSchema get() {
        return SCHEMA;
    }

}
