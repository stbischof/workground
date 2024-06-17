/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.provider.sample.foodmart.xml;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingClosure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.junit.jupiter.api.Test;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.test.common.annotation.InjectService;

@RequireConfigurationAdmin
@RequireServiceComponentRuntime
class FoodMartReadTest {

	private static final String BOTTOM_LEVEL = "bottomLevel";
	private static final String ROLLUP_POLICY = "rollupPolicy";
	private static final String SCHEMA_GRANT = "schemaGrant";
	private static final String MEMBER_GRANT = "memberGrant";
	private static final String TOP_LEVEL = "topLevel";
	private static final String MEMBER = "member";
	private static final String HIERARCHY_GRANT = "hierarchyGrant";
	private static final String ACCESS = "access";
	private static final String CUBE = "cube";
	private static final String CUBE_GRANT = "cubeGrant";
	private static final String CUBE_NAME = "cubeName";
	private static final String FORMULA = "Formula";
	private static final String CALCULATED_MEMBER = "CalculatedMember";
	private static final String VIRTUAL_CUBE_DIMENSION = "VirtualCubeDimension";
	private static final String VIRTUAL_CUBE_MEASURE = "VirtualCubeMeasure";
	private static final String CHILD_COLUMN = "childColumn";
	private static final String APPROX_ROW_COUNT = "approxRowCount";
	private static final String KEY_EXPRESSION = "KeyExpression";
	private static final String DEFAULT_MEMBER = "defaultMember";
	private static final String HIGH_CARDINALITY = "highCardinality";
	private static final String SOURCE = "source";
	private static final String NULL_PARENT_VALUE = "nullParentValue";
	private static final String NAME_COLUMN = "nameColumn";
	private static final String PARENT_COLUMN = "parentColumn";
	private static final String CLOSURE = "Closure";
	private static final String ORDINAL_EXPRESSION = "OrdinalExpression";
	private static final String CAPTION_EXPRESSION = "captionExpression";
	private static final String NAME_EXPRESSION = "NameExpression";
	private static final String RIGHT_KEY = "rightKey";
	private static final String LEFT_KEY = "leftKey";
	private static final String LEFT_ALIAS = "leftAlias";
	private static final String RIGHT_ALIAS = "rightAlias";
	private static final String JOIN = "join";
	private static final String RELATION = "relation";
	private static final String PROPERTY = "Property";
	private static final String AGGREGATOR = "aggregator";
	private static final String FORMAT_STRING = "formatString";
	private static final String ORDINAL_COLUMN = "ordinalColumn";
	private static final String LEVEL = "level";
	private static final String HAS_ALL = "hasAll";
	private static final String HIDE_MEMBER_IF = "hideMemberIf";
	private static final String LEVEL_TYPE = "levelType";
	private static final String UNIQUE_MEMBERS = "uniqueMembers";
	private static final String COLUMN = "column";
	private static final String CAPTION = "caption";
	private static final String HIERARCHY = "hierarchy";
	private static final String TABLE = "table";
	private static final String PRIMARY_KEY_TABLE = "primaryKeyTable";
	private static final String PRIMARY_KEY = "primaryKey";
	private static final String ALL_MEMBER_NAME = "allMemberName";
	private static final String NAME = "name";
	private static final String FOREIGN_KEY = "foreignKey";
	private static final String TYPE = "type";
	private static final String DEFAULT_MEASURE = "defaultMeasure";

	private static final String MEASURE = "Measure";
	private static final String ANNOTATIONS = "annotations";
	private static final String DIMENSION = "Dimension";
	private static final String CONTENT = "content";
	private static final String DIALECT = "dialect";
	private static final String MEASURE_EXPRESSION = "MeasureExpression";

	// ****** foodmart Schema
	// ****** foodmartDimension
	List<Map<String, Object>> foodmartRoleList = List.of(
			Map.of(NAME, "California manager", SCHEMA_GRANT,
					List.of(Map.of(CUBE_GRANT,
							List.of(Map.of(CUBE, "Sales", ACCESS, "all", HIERARCHY_GRANT, List.of(
									Map.of(HIERARCHY, "[Store]", ACCESS, "custom", TOP_LEVEL, "[Store].[Store Country]",
											MEMBER_GRANT,
											List.of(Map.of(MEMBER, "[Store].[USA].[CA]", ACCESS, "all"),
													Map.of(MEMBER, "[Store].[USA].[CA].[Los Angeles]", ACCESS,
															"none"))),
									Map.of(HIERARCHY, "[Customers]", ACCESS, "custom", TOP_LEVEL,
											"[Customers].[State Province]", BOTTOM_LEVEL, "[Customers].[City]",
											MEMBER_GRANT,
											List.of(Map.of(MEMBER, "[Customers].[USA].[CA]", ACCESS, "all"),
													Map.of(MEMBER, "[Customers].[USA].[CA].[Los Angeles]", ACCESS,
															"none"))),
									Map.of(HIERARCHY, "[Gender]", ACCESS, "none"))))))),
			Map.of(NAME, "No HR Cube", SCHEMA_GRANT,
					List.of(Map.of(ACCESS, "all", CUBE_GRANT, List.of(Map.of(CUBE, "HR", ACCESS, "none"))))),
			Map.of(NAME, "Administrator", SCHEMA_GRANT, List.of(Map.of(ACCESS, "all"))));

	List<Map<String, Object>> foodmartDimensionList = List.of(
			Map.of(NAME, "Store", HIERARCHY, List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "store_id", TABLE, "store",
					LEVEL,
					List.of(Map.of(NAME, "Store Country", COLUMN, "store_country", UNIQUE_MEMBERS, "true"),
							Map.of(NAME, "Store State", COLUMN, "store_state", UNIQUE_MEMBERS, "true"),
							Map.of(NAME, "Store City", COLUMN, "store_city", UNIQUE_MEMBERS, "false"),
							Map.of(NAME, "Store Name", COLUMN, "store_name", UNIQUE_MEMBERS, "true", PROPERTY,
									List.of(Map.of(NAME, "Store Type", COLUMN, "store_type"),
											Map.of(NAME, "Store Manager", COLUMN, "store_manager"),
											Map.of(NAME, "Store Sqft", COLUMN, "store_sqft", TYPE, "Numeric"),
											Map.of(NAME, "Grocery Sqft", COLUMN, "grocery_sqft", TYPE, "Numeric"),
											Map.of(NAME, "Frozen Sqft", COLUMN, "frozen_sqft", TYPE, "Numeric"),
											Map.of(NAME, "Meat Sqft", COLUMN, "meat_sqft", TYPE, "Numeric"),
											Map.of(NAME, "Has coffee bar", COLUMN, "coffee_bar", TYPE, "Boolean"),
											Map.of(NAME, "Street address", COLUMN, "store_street_address", TYPE,
													"String"))))))),
			Map.of(NAME, "Store Size in SQFT", HIERARCHY,
					List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "store_id", TABLE, "store", LEVEL,
							List.of(Map.of(NAME, "Store Sqft", COLUMN, "store_sqft", TYPE, "Numeric", UNIQUE_MEMBERS,
									"true"))))),
			Map.of(NAME, "Store Type", HIERARCHY,
					List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "store_id", TABLE, "store", LEVEL,
							List.of(Map.of(NAME, "Store Type", COLUMN, "store_type", UNIQUE_MEMBERS, "true"))))),
			Map.of(NAME, "Time", TYPE, "TimeDimension", HIERARCHY, List.of(
					Map.of(HAS_ALL, "false", PRIMARY_KEY, "time_id", TABLE, "time_by_day", LEVEL,
							List.of(Map.of(NAME, "Year", COLUMN, "the_year", TYPE, "Numeric", UNIQUE_MEMBERS, "true",
									"levelType", "TimeYears"),
									Map.of(NAME, "Quarter", COLUMN, "quarter", UNIQUE_MEMBERS, "false", "levelType",
											"TimeQuarters"),
									Map.of(NAME, "Month", COLUMN, "month_of_year", UNIQUE_MEMBERS, "false", TYPE,
											"Numeric", "levelType", "TimeMonths"))),
					Map.of(HAS_ALL, "true", PRIMARY_KEY, "time_id", NAME, "Weekly", TABLE, "time_by_day", LEVEL,
							List.of(Map.of(NAME, "Year", COLUMN, "the_year", TYPE, "Numeric", UNIQUE_MEMBERS, "true",
									"levelType", "TimeYears"),
									Map.of(NAME, "Week", COLUMN, "week_of_year", TYPE, "Numeric", UNIQUE_MEMBERS,
											"false", "levelType", "TimeWeeks"),
									Map.of(NAME, "Day", COLUMN, "day_of_month", UNIQUE_MEMBERS, "false", TYPE,
											"Numeric", "levelType", "TimeDays"))))),
			Map.of(NAME, "Product", HIERARCHY, List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "product_id",
					"primaryKeyTable", "product", JOIN,
					Map.of(LEFT_KEY, "product_class_id", RIGHT_KEY, "product_class_id", RELATION,
							List.of(Map.of(NAME, "product"), Map.of(NAME, "product_class"))),
					LEVEL,
					List.of(Map.of(NAME, "Product Family", TABLE, "product_class", COLUMN, "product_family",
							UNIQUE_MEMBERS, "true"),
							Map.of(NAME, "Product Department", TABLE, "product_class", COLUMN, "product_department",
									UNIQUE_MEMBERS, "false"),
							Map.of(NAME, "Product Category", TABLE, "product_class", COLUMN, "product_category",
									UNIQUE_MEMBERS, "false"),
							Map.of(NAME, "Product Subcategory", TABLE, "product_class", COLUMN, "product_subcategory",
									UNIQUE_MEMBERS, "false"),
							Map.of(NAME, "Brand Name", TABLE, "product", COLUMN, "brand_name", UNIQUE_MEMBERS, "false"),
							Map.of(NAME, "Product Name", TABLE, "product", COLUMN, "product_name", UNIQUE_MEMBERS,
									"true"))))),
			Map.of(NAME, "Warehouse", HIERARCHY, List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "warehouse_id", TABLE,
					"warehouse", LEVEL,
					List.of(Map.of(NAME, "Country", COLUMN, "warehouse_country", UNIQUE_MEMBERS, "true"),
							Map.of(NAME, "State Province", COLUMN, "warehouse_state_province", UNIQUE_MEMBERS, "true"),
							Map.of(NAME, "City", COLUMN, "warehouse_city", UNIQUE_MEMBERS, "false"),
							Map.of(NAME, "Warehouse Name", COLUMN, "warehouse_name", UNIQUE_MEMBERS, "true"))))));

	List foodmartVirtualCubeList = List.of(Map.of(NAME, "Warehouse and Sales", DEFAULT_MEASURE, "Store Sales",
			VIRTUAL_CUBE_DIMENSION,
			List.of(Map.of(CUBE_NAME, "Sales", NAME, "Customers"), Map.of(CUBE_NAME, "Sales", NAME, "Education Level"),
					Map.of(CUBE_NAME, "Sales", NAME, "Gender"), Map.of(CUBE_NAME, "Sales", NAME, "Marital Status"),
					Map.of(NAME, "Product"), Map.of(CUBE_NAME, "Sales", NAME, "Promotion Media"),
					Map.of(CUBE_NAME, "Sales", NAME, "Promotions"), Map.of(NAME, "Store"), Map.of(NAME, "Time"),
					Map.of(CUBE_NAME, "Sales", NAME, "Yearly Income"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "Warehouse")),
			VIRTUAL_CUBE_MEASURE,
			List.of(Map.of(CUBE_NAME, "Sales", NAME, "[Measures].[Sales Count]"),
					Map.of(CUBE_NAME, "Sales", NAME, "[Measures].[Store Cost]"),
					Map.of(CUBE_NAME, "Sales", NAME, "[Measures].[Store Sales]"),
					Map.of(CUBE_NAME, "Sales", NAME, "[Measures].[Unit Sales]"),
					Map.of(CUBE_NAME, "Sales", NAME, "[Measures].[Profit]"),
					Map.of(CUBE_NAME, "Sales", NAME, "[Measures].[Profit Growth]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Store Invoice]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Supply Time]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Units Ordered]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Units Shipped]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Warehouse Cost]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Warehouse Profit]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Warehouse Sales]"),
					Map.of(CUBE_NAME, "Warehouse", NAME, "[Measures].[Average Warehouse Sale]")),
			CALCULATED_MEMBER, List.of(Map.of(NAME, "Profit Per Unit Shipped", DIMENSION, "Measures", FORMULA,
					List.of(Map.of(FORMULA, "[Measures].[Profit] / [Measures].[Units Shipped]"))))));

	// ****** foodmartCube0
	private static final Map<String, Object> foodmartMesureCube0_0 = new HashMap<>();

	static {
		foodmartMesureCube0_0.put(NAME, "Unit Sales");
		foodmartMesureCube0_0.put(COLUMN, "unit_sales");
		foodmartMesureCube0_0.put(FORMAT_STRING, "Standard");
		foodmartMesureCube0_0.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube0_1 = new HashMap<>();

	static {
		foodmartMesureCube0_1.put(NAME, "Store Cost");
		foodmartMesureCube0_1.put(COLUMN, "store_cost");
		foodmartMesureCube0_1.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube0_1.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube0_2 = new HashMap<>();

	static {
		foodmartMesureCube0_2.put(NAME, "Store Sales");
		foodmartMesureCube0_2.put(COLUMN, "store_sales");
		foodmartMesureCube0_2.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube0_2.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube0_3 = new HashMap<>();

	static {
		foodmartMesureCube0_3.put(NAME, "Sales Count");
		foodmartMesureCube0_3.put(COLUMN, "product_id");
		foodmartMesureCube0_3.put(FORMAT_STRING, "#,###");
		foodmartMesureCube0_3.put(AGGREGATOR, "count");
	}

	private static final Map<String, Object> foodmartMesureCube0_4 = new HashMap<>();

	static {
		foodmartMesureCube0_4.put(NAME, "Customer Count");
		foodmartMesureCube0_4.put(COLUMN, "customer_id");
		foodmartMesureCube0_4.put(FORMAT_STRING, "#,###");
		foodmartMesureCube0_4.put(AGGREGATOR, "distinct-count");
	}

	private static final List<Map<String, Object>> foodmartMesureCube0_5MeasureExpression = List.of(
			Map.of(DIALECT, ACCESS, CONTENT,
					"Iif(\"sales_fact_1997\".\"promotion_id\" = 0, 0, \"sales_fact_1997\"" + ".\"store_sales\")"),
			Map.of(DIALECT, "oracle", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "hsqldb", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "postgres", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "mysql", CONTENT,
					"(case when `sales_fact_1997`.`promotion_id` = 0 then 0 else `sales_fact_1997`.`store_sales` end)"),
			Map.of(DIALECT, "mariadb", CONTENT,
					"(case when `sales_fact_1997`.`promotion_id` = 0 then 0 else `sales_fact_1997`.`store_sales` end)"),
			Map.of(DIALECT, "neoview", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "infobright", CONTENT, "`sales_fact_1997`.`store_sales`"),
			Map.of(DIALECT, "derby", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "luciddb", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "db2", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "nuodb", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "snowflake", CONTENT,
					"(case when \"sales_fact_1997\".\"promotion_id\" = 0 then 0 else "
							+ "\"sales_fact_1997\".\"store_sales\" end)"),
			Map.of(DIALECT, "generic", CONTENT,
					"(case when sales_fact_1997.promotion_id = 0 then 0 else sales_fact_1997.store_sales end)"));
	private static final Map<String, Object> foodmartMesureCube0_5 = new HashMap<>();

	static {
		foodmartMesureCube0_5.put(NAME, "Promotion Sales");
		foodmartMesureCube0_5.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube0_5.put(AGGREGATOR, "sum");
		foodmartMesureCube0_5.put(MEASURE_EXPRESSION, foodmartMesureCube0_5MeasureExpression);
	}

	private static final List<Map<String, Object>> foodmartMesureListCube0 = List.of(foodmartMesureCube0_0,
			foodmartMesureCube0_1, foodmartMesureCube0_2, foodmartMesureCube0_3, foodmartMesureCube0_4,
			foodmartMesureCube0_5);
	private static final List<Map<String, Object>> annotationsCube0 = List.of(
			Map.of(NAME, "caption.de_DE", CONTENT, "Verkaufen"), Map.of(NAME, "caption.fr_FR", CONTENT, "Ventes"),
			Map.of(NAME, "description.fr_FR", CONTENT, "Cube des ventes"),
			Map.of(NAME, "description.de", CONTENT, "Cube Verkaufen"),
			Map.of(NAME, "description.de_AT", CONTENT, "Cube den Verkaufen"));
	private static final Map<String, Object> foodmartCube0 = new HashMap<>();

	static {
		foodmartCube0.put(NAME, "Sales");
		foodmartCube0.put(TABLE, "sales_fact_1997");
		foodmartCube0.put(DEFAULT_MEASURE, "Unit Sales");
		foodmartCube0.put(MEASURE, foodmartMesureListCube0);
		foodmartCube0.put(ANNOTATIONS, annotationsCube0);
		foodmartCube0.put(DIMENSION, List.of(Map.of(NAME, "Store", SOURCE, "Store", FOREIGN_KEY, "store_id"),
				Map.of(NAME, "Store Size in SQFT", SOURCE, "Store Size in SQFT", FOREIGN_KEY, "store_id"),
				Map.of(NAME, "Store Type", SOURCE, "Store Type", FOREIGN_KEY, "store_id"),
				Map.of(NAME, "Time", SOURCE, "Time", FOREIGN_KEY, "time_id"),
				Map.of(NAME, "Product", SOURCE, "Product", FOREIGN_KEY, "product_id"),
				Map.of(NAME, "Promotion Media", FOREIGN_KEY, "promotion_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", ALL_MEMBER_NAME, "All Media", PRIMARY_KEY, "promotion_id",
								TABLE, "promotion", DEFAULT_MEMBER, "All Media", LEVEL,
								List.of(Map.of(NAME, "Media Type", COLUMN, "media_type", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Promotions", FOREIGN_KEY, "promotion_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", ALL_MEMBER_NAME, "All Promotions", PRIMARY_KEY, "promotion_id",
								TABLE, "promotion", DEFAULT_MEMBER, "[All Promotions]", LEVEL,
								List.of(Map.of(NAME, "Promotion Name", COLUMN, "promotion_name", UNIQUE_MEMBERS,
										"true"))))),
				Map.of(NAME, "Customers", FOREIGN_KEY, "customer_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						ALL_MEMBER_NAME, "All Customers", PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
						List.of(Map.of(NAME, "Country", COLUMN, "country", UNIQUE_MEMBERS, "true"),
								Map.of(NAME, "State Province", COLUMN, "state_province", UNIQUE_MEMBERS, "true"),
								Map.of(NAME, "City", COLUMN, "city", UNIQUE_MEMBERS, "false"),
								Map.of(NAME, "Name", COLUMN, "customer_id", TYPE, "Numeric", UNIQUE_MEMBERS, "true",
										NAME_EXPRESSION,
										List.of(Map.of(DIALECT, "oracle", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "hive", CONTENT, "`customer`.`fullname`"),
												Map.of(DIALECT, "hsqldb", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, ACCESS, CONTENT, "fname + ' ' + lname"),
												Map.of(DIALECT, "postgres", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "mysql", CONTENT,
														"CONCAT(`customer`.`fname`, ' ', `customer`" + ".`lname`)"),
												Map.of(DIALECT, "mariadb", CONTENT,
														"CONCAT(`customer`.`fname`, ' ', `customer`" + ".`lname`)"),
												Map.of(DIALECT, "mssql", CONTENT, "fname + ' ' + lname"),
												Map.of(DIALECT, "derby", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "db2", CONTENT,
														"CONCAT(CONCAT(\"customer\".\"fname\", ' '), "
																+ "\"customer\".\"lname\")"),
												Map.of(DIALECT, "luciddb", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "neoview", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "teradata", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "snowflake", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "generic", CONTENT, "fullname")),
										ORDINAL_EXPRESSION,
										List.of(Map.of(DIALECT, "oracle", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "hsqldb", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, ACCESS, CONTENT, "fname + ' ' + lname"),
												Map.of(DIALECT, "postgres", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "mysql", CONTENT,
														"CONCAT(`customer`.`fname`, ' ', `customer`" + ".`lname`)"),
												Map.of(DIALECT, "mariadb", CONTENT,
														"CONCAT(`customer`.`fname`, ' ', `customer`" + ".`lname`)"),
												Map.of(DIALECT, "mssql", CONTENT, "fname + ' ' + lname"),
												Map.of(DIALECT, "neoview", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "derby", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "db2", CONTENT,
														"CONCAT(CONCAT(\"customer\".\"fname\", ' '), "
																+ "\"customer\".\"lname\")"),
												Map.of(DIALECT, "luciddb", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "snowflake", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "generic", CONTENT, "fullname")),
										PROPERTY,
										List.of(Map.of(NAME, "Gender", COLUMN, "gender"),
												Map.of(NAME, "Marital Status", COLUMN, "marital_status"),
												Map.of(NAME, "Education", COLUMN, "education"),
												Map.of(NAME, "Yearly Income", COLUMN, "yearly_income"))))))),
				Map.of(NAME, "Education Level", FOREIGN_KEY, "customer_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
								List.of(Map.of(NAME, "Education Level", COLUMN, "education", UNIQUE_MEMBERS,
										"true"))))),
				Map.of(NAME, "Gender", FOREIGN_KEY, "customer_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", "allMemberName", "All Gender", PRIMARY_KEY, "customer_id",
								TABLE, "customer", LEVEL,
								List.of(Map.of(NAME, "Gender", COLUMN, "gender", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Marital Status", FOREIGN_KEY, "customer_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", "allMemberName", "All Marital Status", PRIMARY_KEY,
								"customer_id", TABLE, "customer", LEVEL,
								List.of(Map.of(NAME, "Marital Status", COLUMN, "marital_status", UNIQUE_MEMBERS, "true",
										APPROX_ROW_COUNT, "111"))))),
				Map.of(NAME, "Yearly Income", FOREIGN_KEY, "customer_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
						List.of(Map.of(NAME, "Yearly Income", COLUMN, "yearly_income", UNIQUE_MEMBERS, "true")))))));
	}

	// ****** foodmartCube1 "Warehouse"
	private static final Map<String, Object> foodmartMesureCube1_0 = new HashMap<>();

	static {
		foodmartMesureCube1_0.put(NAME, "Store Invoice");
		foodmartMesureCube1_0.put(COLUMN, "store_invoice");
		foodmartMesureCube1_0.put(FORMAT_STRING, null);
		foodmartMesureCube1_0.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube1_1 = new HashMap<>();

	static {
		foodmartMesureCube1_1.put(NAME, "Supply Time");
		foodmartMesureCube1_1.put(COLUMN, "supply_time");
		foodmartMesureCube1_1.put(FORMAT_STRING, null);
		foodmartMesureCube1_1.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube1_2 = new HashMap<>();

	static {
		foodmartMesureCube1_2.put(NAME, "Warehouse Cost");
		foodmartMesureCube1_2.put(COLUMN, "warehouse_cost");
		foodmartMesureCube1_2.put(FORMAT_STRING, null);
		foodmartMesureCube1_2.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube1_3 = new HashMap<>();

	static {
		foodmartMesureCube1_3.put(NAME, "Warehouse Sales");
		foodmartMesureCube1_3.put(COLUMN, "warehouse_sales");
		foodmartMesureCube1_3.put(FORMAT_STRING, null);
		foodmartMesureCube1_3.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube1_4 = new HashMap<>();

	static {
		foodmartMesureCube1_4.put(NAME, "Units Shipped");
		foodmartMesureCube1_4.put(COLUMN, "units_shipped");
		foodmartMesureCube1_4.put(FORMAT_STRING, "#.0");
		foodmartMesureCube1_4.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube1_5 = new HashMap<>();

	static {
		foodmartMesureCube1_5.put(NAME, "Units Ordered");
		foodmartMesureCube1_5.put(COLUMN, "units_ordered");
		foodmartMesureCube1_5.put(FORMAT_STRING, "#.0");
		foodmartMesureCube1_5.put(AGGREGATOR, "sum");
	}

	private static final List<Map<String, Object>> foodmartMesureCube1_6MeasureExpression = List.of(
			Map.of(DIALECT, "mysql", CONTENT, "`warehouse_sales` - `inventory_fact_1997`.`warehouse_cost`"),
			Map.of(DIALECT, "mariadb", CONTENT, "`warehouse_sales` - `inventory_fact_1997`.`warehouse_cost`"),
			Map.of(DIALECT, "infobright", CONTENT, "`warehouse_sales` - `inventory_fact_1997`.`warehouse_cost`"),
			Map.of(DIALECT, "generic", CONTENT, "\"warehouse_sales\" - \"inventory_fact_1997\".\"warehouse_cost\""));
	private static final Map<String, Object> foodmartMesureCube1_6 = new HashMap<>();

	static {
		foodmartMesureCube1_6.put(NAME, "Warehouse Profit");
		foodmartMesureCube1_6.put(COLUMN, null);
		foodmartMesureCube1_6.put(FORMAT_STRING, null);
		foodmartMesureCube1_6.put(AGGREGATOR, "sum");
		foodmartMesureCube1_6.put(MEASURE_EXPRESSION, foodmartMesureCube1_6MeasureExpression);
	}

	private static final List<Map<String, Object>> foodmartMesureListCube1 = List.of(foodmartMesureCube1_0,
			foodmartMesureCube1_1, foodmartMesureCube1_2, foodmartMesureCube1_3, foodmartMesureCube1_4,
			foodmartMesureCube1_5, foodmartMesureCube1_6);
	private static final Map<String, Object> foodmartCube1 = new HashMap<>();

	static {
		foodmartCube1.put(NAME, "Warehouse");
		foodmartCube1.put(TABLE, "inventory_fact_1997");
		foodmartCube1.put(MEASURE, foodmartMesureListCube1);
		foodmartCube1.put(DIMENSION,
				List.of(Map.of(NAME, "Store", SOURCE, "Store", FOREIGN_KEY, "store_id"),
						Map.of(NAME, "Store Size in SQFT", SOURCE, "Store Size in SQFT", FOREIGN_KEY, "store_id"),
						Map.of(NAME, "Store Type", SOURCE, "Store Type", FOREIGN_KEY, "store_id"),
						Map.of(NAME, "Time", SOURCE, "Time", FOREIGN_KEY, "time_id"),
						Map.of(NAME, "Product", SOURCE, "Product", FOREIGN_KEY, "product_id"),
						Map.of(NAME, "Warehouse", SOURCE, "Warehouse", FOREIGN_KEY, "warehouse_id")));
	}

	// ****** foodmartCube2
	private static final Map<String, Object> foodmartMesureCube2_0 = new HashMap<>();

	static {
		foodmartMesureCube2_0.put(NAME, "Store Sqft");
		foodmartMesureCube2_0.put(COLUMN, "store_sqft");
		foodmartMesureCube2_0.put(FORMAT_STRING, "#,###");
		foodmartMesureCube2_0.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube2_1 = new HashMap<>();

	static {
		foodmartMesureCube2_1.put(NAME, "Grocery Sqft");
		foodmartMesureCube2_1.put(COLUMN, "grocery_sqft");
		foodmartMesureCube2_1.put(FORMAT_STRING, "#,###");
		foodmartMesureCube2_1.put(AGGREGATOR, "sum");
	}

	private static final List<Map<String, Object>> foodmartMesureListCube2 = List.of(foodmartMesureCube2_0,
			foodmartMesureCube2_1);
	private static final Map<String, Object> foodmartCube2 = new HashMap<>();

	static {
		foodmartCube2.put(NAME, "Store");
		foodmartCube2.put(TABLE, "store");
		foodmartCube2.put(MEASURE, foodmartMesureListCube2);
		foodmartCube2.put(DIMENSION, List.of(
				Map.of(NAME, "Store Type", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", LEVEL,
								List.of(Map.of(NAME, "Store Type", COLUMN, "store_type", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Store", SOURCE, "Store"),
				Map.of(NAME, "Has coffee bar", HIERARCHY, List.of(Map.of(HAS_ALL, "true", LEVEL, List.of(Map.of(NAME,
						"Has coffee bar", COLUMN, "coffee_bar", UNIQUE_MEMBERS, "true", TYPE, "Boolean")))))));
	}

	// ****** foodmartCube3
	private static final Map<String, Object> foodmartMesureCube3_0 = new HashMap<>();

	static {
		foodmartMesureCube3_0.put(NAME, "Org Salary");
		foodmartMesureCube3_0.put(COLUMN, "salary_paid");
		foodmartMesureCube3_0.put(FORMAT_STRING, "Currency");
		foodmartMesureCube3_0.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube3_1 = new HashMap<>();

	static {
		foodmartMesureCube3_1.put(NAME, "Count");
		foodmartMesureCube3_1.put(COLUMN, "employee_id");
		foodmartMesureCube3_1.put(FORMAT_STRING, "#,#");
		foodmartMesureCube3_1.put(AGGREGATOR, "count");
	}

	private static final Map<String, Object> foodmartMesureCube3_2 = new HashMap<>();

	static {
		foodmartMesureCube3_2.put(NAME, "Number of Employees");
		foodmartMesureCube3_2.put(COLUMN, "employee_id");
		foodmartMesureCube3_2.put(FORMAT_STRING, "#,#");
		foodmartMesureCube3_2.put(AGGREGATOR, "distinct-count");
	}

	private static final List<Map<String, Object>> foodmartMesureListCube3 = List.of(foodmartMesureCube3_0,
			foodmartMesureCube3_1, foodmartMesureCube3_2);
	private static final Map<String, Object> foodmartCube3 = new HashMap<>();

	static {
		foodmartCube3.put(NAME, "HR");
		foodmartCube3.put(TABLE, "salary");
		foodmartCube3.put(MEASURE, foodmartMesureListCube3);
		foodmartCube3.put(DIMENSION, List.of(Map.of(NAME, "Time", TYPE, "TimeDimension", FOREIGN_KEY, "pay_date",
				HIERARCHY,
				List.of(Map.of(HAS_ALL, "false", PRIMARY_KEY, "the_date", TABLE, "time_by_day", LEVEL, List.of(
						Map.of(NAME, "Year", COLUMN, "the_year", TYPE, "Numeric", UNIQUE_MEMBERS, "true", LEVEL_TYPE,
								"TimeYears"),
						Map.of(NAME, "Quarter", COLUMN, "quarter", UNIQUE_MEMBERS, "false", LEVEL_TYPE, "TimeQuarters"),
						Map.of(NAME, "Month", COLUMN, "month_of_year", NAME_COLUMN, "the_month", TYPE, "Numeric",
								UNIQUE_MEMBERS, "false", LEVEL_TYPE, "TimeMonths"))))),
				Map.of(NAME, "Store", FOREIGN_KEY, "employee_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						PRIMARY_KEY, "employee_id", "primaryKeyTable", "employee", JOIN,
						Map.of(LEFT_KEY, "store_id", RIGHT_KEY, "store_id", RELATION,
								List.of(Map.of(NAME, "employee"), Map.of(NAME, "store"))),
						LEVEL,
						List.of(Map.of(NAME, "Store Country", TABLE, "store", COLUMN, "store_country", UNIQUE_MEMBERS,
								"true"),
								Map.of(NAME, "Store State", TABLE, "store", COLUMN, "store_state", UNIQUE_MEMBERS,
										"true"),
								Map.of(NAME, "Store City", TABLE, "store", COLUMN, "store_city", UNIQUE_MEMBERS,
										"false"),
								Map.of(NAME, "Store Name", TABLE, "store", COLUMN, "store_name", UNIQUE_MEMBERS, "true",
										PROPERTY,
										List.of(Map.of(NAME, "Store Type", COLUMN, "store_type"),
												Map.of(NAME, "Store Manager", COLUMN, "store_manager"),
												Map.of(NAME, "Store Sqft", COLUMN, "store_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Grocery Sqft", COLUMN, "grocery_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Frozen Sqft", COLUMN, "frozen_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Meat Sqft", COLUMN, "meat_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Has coffee bar", COLUMN, "coffee_bar", TYPE, "Boolean"),
												Map.of(NAME, "Street address", COLUMN, "store_street_address", TYPE,
														"String"))))))),
				Map.of(NAME, "Pay Type", FOREIGN_KEY, "employee_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						PRIMARY_KEY, "employee_id", "primaryKeyTable", "employee", JOIN,
						Map.of(LEFT_KEY, "position_id", RIGHT_KEY, "position_id", RELATION,
								List.of(Map.of(NAME, "employee"), Map.of(NAME, "position"))),
						LEVEL,
						List.of(Map.of(NAME, "Pay Type", TABLE, "position", COLUMN, "pay_type", UNIQUE_MEMBERS,
								"true"))))),
				Map.of(NAME, "Store Type", FOREIGN_KEY, "employee_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "employee_id", "primaryKeyTable", "employee", JOIN,
								Map.of(LEFT_KEY, "store_id", RIGHT_KEY, "store_id", RELATION,
										List.of(Map.of(NAME, "employee"), Map.of(NAME, "store"))),
								LEVEL,
								List.of(Map.of(NAME, "Store Type", TABLE, "store", COLUMN, "store_type", UNIQUE_MEMBERS,
										"true"))))),
				Map.of(NAME, "Position", FOREIGN_KEY, "employee_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						PRIMARY_KEY, "employee_id", "allMemberName", "All Position", TABLE, "employee", LEVEL,
						List.of(Map.of(NAME, "Management Role", COLUMN, "management_role", UNIQUE_MEMBERS, "true"),
								Map.of(NAME, "Position Title", COLUMN, "position_title", UNIQUE_MEMBERS, "false",
										"ordinalColumn", "position_id"))))),
				Map.of(NAME, "Department", FOREIGN_KEY, "department_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "department_id", TABLE, "department", LEVEL,
								List.of(Map.of(NAME, "Department Description", TYPE, "Numeric", COLUMN, "department_id",
										UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Employees", FOREIGN_KEY, "employee_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", "allMemberName", "All Employees", PRIMARY_KEY, "employee_id",
								TABLE, "employee", LEVEL,
								List.of(Map.of(NAME, "Employee Id", TYPE, "Numeric", COLUMN, "employee_id",
										UNIQUE_MEMBERS, "true", PARENT_COLUMN, "supervisor_id", NAME_COLUMN,
										"full_name", NULL_PARENT_VALUE, "0", CLOSURE,
										Map.of(PARENT_COLUMN, "supervisor_id", CHILD_COLUMN, "employee_id", TABLE,
												"employee_closure"),
										PROPERTY,
										List.of(Map.of(NAME, "Marital Status", COLUMN, "marital_status"),
												Map.of(NAME, "Position Title", COLUMN, "position_title"),
												Map.of(NAME, "Gender", COLUMN, "gender"),
												Map.of(NAME, "Salary", COLUMN, "salary"),
												Map.of(NAME, "Education Level", COLUMN, "education_level"),
												Map.of(NAME, "Management Role", COLUMN, "management_role")))))))));
	}

	// ****** foodmartCube4
	private static final Map<String, Object> foodmartMesureCube4_0 = new HashMap<>();

	static {
		foodmartMesureCube4_0.put(NAME, "Unit Sales");
		foodmartMesureCube4_0.put(COLUMN, "unit_sales");
		foodmartMesureCube4_0.put(FORMAT_STRING, "Standard");
		foodmartMesureCube4_0.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube4_1 = new HashMap<>();

	static {
		foodmartMesureCube4_1.put(NAME, "Store Cost");
		foodmartMesureCube4_1.put(COLUMN, "store_cost");
		foodmartMesureCube4_1.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube4_1.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube4_2 = new HashMap<>();

	static {
		foodmartMesureCube4_2.put(NAME, "Store Sales");
		foodmartMesureCube4_2.put(COLUMN, "store_sales");
		foodmartMesureCube4_2.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube4_2.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube4_3 = new HashMap<>();

	static {
		foodmartMesureCube4_3.put(NAME, "Sales Count");
		foodmartMesureCube4_3.put(COLUMN, "product_id");
		foodmartMesureCube4_3.put(FORMAT_STRING, "#,###");
		foodmartMesureCube4_3.put(AGGREGATOR, "count");
	}

	private static final Map<String, Object> foodmartMesureCube4_4 = new HashMap<>();

	static {
		foodmartMesureCube4_4.put(NAME, "Customer Count");
		foodmartMesureCube4_4.put(COLUMN, "customer_id");
		foodmartMesureCube4_4.put(FORMAT_STRING, "#,###");
		foodmartMesureCube4_4.put(AGGREGATOR, "distinct-count");
	}

	private static final List<Map<String, Object>> foodmartMesureListCube4 = List.of(foodmartMesureCube4_0,
			foodmartMesureCube4_1, foodmartMesureCube4_2, foodmartMesureCube4_3, foodmartMesureCube4_4);
	private static final Map<String, Object> foodmartCube4 = new HashMap<>();

	static {
		foodmartCube4.put(NAME, "Sales Ragged");
		foodmartCube4.put(TABLE, "sales_fact_1997");
		foodmartCube4.put(MEASURE, foodmartMesureListCube4);
		foodmartCube4.put(DIMENSION, List.of(
				Map.of(NAME, "Store", FOREIGN_KEY, "store_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "store_id", TABLE, "store_ragged", LEVEL, List.of(
								Map.of(NAME, "Store Country", COLUMN, "store_country", UNIQUE_MEMBERS, "true",
										"hideMemberIf", "Never"),
								Map.of(NAME, "Store State", COLUMN, "store_state", UNIQUE_MEMBERS, "true",
										"hideMemberIf", "IfParentsName"),
								Map.of(NAME, "Store City", COLUMN, "store_city", UNIQUE_MEMBERS, "false",
										"hideMemberIf", "IfBlankName"),
								Map.of(NAME, "Store Name", COLUMN, "store_name", UNIQUE_MEMBERS, "true", "hideMemberIf",
										"Never", PROPERTY,
										List.of(Map.of(NAME, "Store Type", COLUMN, "store_type"),
												Map.of(NAME, "Store Manager", COLUMN, "store_manager"),
												Map.of(NAME, "Store Sqft", COLUMN, "store_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Grocery Sqft", COLUMN, "grocery_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Frozen Sqft", COLUMN, "frozen_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Meat Sqft", COLUMN, "meat_sqft", TYPE, "Numeric"),
												Map.of(NAME, "Has coffee bar", COLUMN, "coffee_bar", TYPE, "Boolean"),
												Map.of(NAME, "Street address", COLUMN, "store_street_address", TYPE,
														"String"))))))),
				Map.of(NAME, "Geography", FOREIGN_KEY, "store_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "store_id", TABLE, "store_ragged", LEVEL,
								List.of(Map.of(NAME, "Country", COLUMN, "store_country", UNIQUE_MEMBERS, "true",
										"hideMemberIf", "Never"),
										Map.of(NAME, "State", COLUMN, "store_state", UNIQUE_MEMBERS, "true",
												"hideMemberIf", "IfParentsName"),
										Map.of(NAME, "City", COLUMN, "store_city", UNIQUE_MEMBERS, "false",
												"hideMemberIf", "IfBlankName"))))),
				Map.of(NAME, "Store Size in SQFT", FOREIGN_KEY, "store_id", SOURCE, "Store Size in SQFT"),
				Map.of(NAME, "Store Type", FOREIGN_KEY, "store_id", SOURCE, "Store Type"),
				Map.of(NAME, "Time", FOREIGN_KEY, "time_id", SOURCE, "Time"),
				Map.of(NAME, "Product", FOREIGN_KEY, "product_id", SOURCE, "Product"),
				Map.of(NAME, "Promotion Media", FOREIGN_KEY, "promotion_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", ALL_MEMBER_NAME, "All Media", PRIMARY_KEY, "promotion_id",
								TABLE, "promotion", LEVEL,
								List.of(Map.of(NAME, "Media Type", COLUMN, "media_type", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Promotions", FOREIGN_KEY, "promotion_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						ALL_MEMBER_NAME, "All Promotions", PRIMARY_KEY, "promotion_id", TABLE, "promotion", LEVEL,
						List.of(Map.of(NAME, "Promotion Name", COLUMN, "promotion_name", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Customers", FOREIGN_KEY, "customer_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						ALL_MEMBER_NAME, "All Customers", PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
						List.of(Map.of(NAME, "Country", COLUMN, "country", UNIQUE_MEMBERS, "true"),
								Map.of(NAME, "State Province", COLUMN, "state_province", UNIQUE_MEMBERS, "true"),
								Map.of(NAME, "City", COLUMN, "city", UNIQUE_MEMBERS, "false"),
								Map.of(NAME, "Name", UNIQUE_MEMBERS, "true", KEY_EXPRESSION,
										List.of(Map.of(DIALECT, "oracle", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "hsqldb", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, ACCESS, CONTENT, "fname + ' ' + lname"),
												Map.of(DIALECT, "postgres", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "mysql", CONTENT,
														"CONCAT(`customer`.`fname`, ' ', `customer`" + ".`lname`)"),
												Map.of(DIALECT, "mariadb", CONTENT,
														"CONCAT(`customer`.`fname`, ' ', `customer`" + ".`lname`)"),
												Map.of(DIALECT, "mssql", CONTENT, "fname + ' ' + lname"),
												Map.of(DIALECT, "derby", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "db2", CONTENT,
														"CONCAT(CONCAT(\"customer\".\"fname\", ' '), "
																+ "\"customer\".\"lname\")"),
												Map.of(DIALECT, "luciddb", CONTENT, "\"fname\" || ' ' || \"lname\""),
												Map.of(DIALECT, "neoview", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "snowflake", CONTENT, "\"customer\".\"fullname\""),
												Map.of(DIALECT, "generic", CONTENT, "fullname")),
										PROPERTY,
										List.of(Map.of(NAME, "Gender", COLUMN, "gender"),
												Map.of(NAME, "Marital Status", COLUMN, "marital_status"),
												Map.of(NAME, "Education", COLUMN, "education"),
												Map.of(NAME, "Yearly Income", COLUMN, "yearly_income"))))))),
				Map.of(NAME, "Education Level", FOREIGN_KEY, "customer_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
								List.of(Map.of(NAME, "Education Level", COLUMN, "education", UNIQUE_MEMBERS,
										"true"))))),
				Map.of(NAME, "Gender", FOREIGN_KEY, "customer_id", HIERARCHY,
						List.of(Map.of(HAS_ALL, "true", "allMemberName", "All Gender", PRIMARY_KEY, "customer_id",
								TABLE, "customer", LEVEL,
								List.of(Map.of(NAME, "Gender", COLUMN, "gender", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Marital Status", FOREIGN_KEY, "customer_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						"allMemberName", "All Marital Status", PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
						List.of(Map.of(NAME, "Marital Status", COLUMN, "marital_status", UNIQUE_MEMBERS, "true"))))),
				Map.of(NAME, "Yearly Income", FOREIGN_KEY, "customer_id", HIERARCHY, List.of(Map.of(HAS_ALL, "true",
						PRIMARY_KEY, "customer_id", TABLE, "customer", LEVEL,
						List.of(Map.of(NAME, "Yearly Income", COLUMN, "yearly_income", UNIQUE_MEMBERS, "true")))))));
	}

	// ****** foodmartCube5
	private static final Map<String, Object> foodmartMesureCube5_0 = new HashMap<>();

	static {
		foodmartMesureCube5_0.put(NAME, "Sales Count");
		foodmartMesureCube5_0.put(COLUMN, "product_id");
		foodmartMesureCube5_0.put(FORMAT_STRING, "#,###");
		foodmartMesureCube5_0.put(AGGREGATOR, "count");
	}

	private static final Map<String, Object> foodmartMesureCube5_1 = new HashMap<>();

	static {
		foodmartMesureCube5_1.put(NAME, "Unit Sales");
		foodmartMesureCube5_1.put(COLUMN, "unit_sales");
		foodmartMesureCube5_1.put(FORMAT_STRING, "Standard");
		foodmartMesureCube5_1.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube5_2 = new HashMap<>();

	static {
		foodmartMesureCube5_2.put(NAME, "Store Sales");
		foodmartMesureCube5_2.put(COLUMN, "store_sales");
		foodmartMesureCube5_2.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube5_2.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube5_3 = new HashMap<>();

	static {
		foodmartMesureCube5_3.put(NAME, "Store Cost");
		foodmartMesureCube5_3.put(COLUMN, "store_cost");
		foodmartMesureCube5_3.put(FORMAT_STRING, "#,###.00");
		foodmartMesureCube5_3.put(AGGREGATOR, "sum");
	}

	private static final Map<String, Object> foodmartMesureCube5_4 = new HashMap<>();

	static {
		foodmartMesureCube5_4.put(NAME, "Customer Count");
		foodmartMesureCube5_4.put(COLUMN, "customer_id");
		foodmartMesureCube5_4.put(FORMAT_STRING, "#,###");
		foodmartMesureCube5_4.put(AGGREGATOR, "distinct-count");
	}

	private static final List<Map<String, Object>> foodmartMesureListCube5 = List.of(foodmartMesureCube5_0,
			foodmartMesureCube5_1, foodmartMesureCube5_2, foodmartMesureCube5_3, foodmartMesureCube5_4);
	private static final Map<String, Object> foodmartCube5 = new HashMap<>();

	static {
		foodmartCube5.put(NAME, "Sales 2");
		foodmartCube5.put(TABLE, "sales_fact_1997");
		foodmartCube5.put(MEASURE, foodmartMesureListCube5);
		foodmartCube5.put(DIMENSION,
				List.of(Map.of(NAME, "Time", SOURCE, "Time", FOREIGN_KEY, "time_id"),
						Map.of(NAME, "Product", SOURCE, "Product", FOREIGN_KEY, "product_id"),
						Map.of(NAME, "Gender", FOREIGN_KEY, "customer_id", HIERARCHY,
								List.of(Map.of(HAS_ALL, "true", "allMemberName", "All Gender", PRIMARY_KEY,
										"customer_id", TABLE, "customer", LEVEL,
										List.of(Map.of(NAME, "Gender", COLUMN, "gender", UNIQUE_MEMBERS, "true")))))));
	}

	private static final List<Map<String, Object>> foodmartCubeList = List.of(foodmartCube0, foodmartCube1,
			foodmartCube2, foodmartCube3, foodmartCube4, foodmartCube5);

	@Test
	void test_Foodmart(
			@InjectService(timeout = 100000,filter = "(&(sample.type=xml)(sample.name=FoodMart))") DatabaseMappingSchemaProvider provider)
			throws Exception {

		MappingSchema schema = provider.get();
		assertThat(schema).isNotNull();
		assertEquals("FoodMart", schema.name());
		checkPrivateDimension(schema, schema.dimensions(), foodmartDimensionList);
		assertNotNull(schema.dimensions());
		assertEquals(6, schema.dimensions().size());
		checkCubes(schema.cubes(), foodmartCubeList);
		checkVirtualCubes(schema.virtualCubes(), foodmartVirtualCubeList);
		checkRoles(schema.roles(), foodmartRoleList);
	}

	private void checkRoles(List<? extends MappingRole> roles, List<Map<String, Object>> roleList) {
		assertThat(roles).isNotNull();
		assertEquals(roles.size(), roleList.size());
		for (int i = 0; i < roles.size(); i++) {
			checkRoleItem(roles.get(i), roleList.get(i));
		}
	}

	private void checkRoleItem(MappingRole role, Map<String, Object> map) {
		assertThat(role.annotations()).isNotNull().isEmpty();
		checkGrants(role.schemaGrants(), get(SCHEMA_GRANT, map));
		assertNull(role.union());
		assertEquals(role.name(), get(NAME, map));
	}

	private void checkGrants(List<? extends MappingSchemaGrant> schemaGrant, Object o) {
		if (o == null) {
			assertNull(schemaGrant);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(schemaGrant.size(), list.size());
			for (int i = 0; i < schemaGrant.size(); i++) {
				checkGrantItem(schemaGrant.get(i), list.get(i));
			}
		}
	}

	private void checkGrantItem(MappingSchemaGrant schemaGrant, Map<String, Object> map) {
		checkCubeGrant(schemaGrant.cubeGrants(), get(CUBE_GRANT, map));
		assertEquals(schemaGrant.access() == null ? null : schemaGrant.access().getValue(),
				get(ACCESS, map) == null ? "none" : get(ACCESS, map));
	}

	private void checkCubeGrant(List<? extends MappingCubeGrant> cubeGrant, Object o) {
		if (o == null) {
			assertNotNull(cubeGrant);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(cubeGrant.size(), list.size());
			for (int i = 0; i < cubeGrant.size(); i++) {
				checkCubeGrantItem(cubeGrant.get(i), list.get(i));
			}
		}
	}

	private void checkCubeGrantItem(MappingCubeGrant cubeGrant, Map<String, Object> map) {
		assertNotNull(cubeGrant.dimensionGrants());
		checkHierarchyGrant(cubeGrant.hierarchyGrants(), get(HIERARCHY_GRANT, map));
		assertEquals(cubeGrant.cube(), get(CUBE, map));
		assertEquals(cubeGrant.access(), get(ACCESS, map));
	}

	private void checkHierarchyGrant(List<? extends MappingHierarchyGrant> hierarchyGrant, Object o) {
		if (o == null) {
			assertNotNull(hierarchyGrant);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(hierarchyGrant.size(), list.size());
			for (int i = 0; i < hierarchyGrant.size(); i++) {
				checkhierarchyGrantItem(hierarchyGrant.get(i), list.get(i));
			}
		}
	}

	private void checkhierarchyGrantItem(MappingHierarchyGrant hierarchyGrant, Map<String, Object> map) {
		checkMemberGrant(hierarchyGrant.memberGrants(), get(MEMBER_GRANT, map));
		assertEquals(hierarchyGrant.hierarchy(), get(HIERARCHY, map));
		assertEquals(hierarchyGrant.access() == null ? null : hierarchyGrant.access().getValue(), get(ACCESS, map));
		assertEquals(hierarchyGrant.topLevel(), get(TOP_LEVEL, map));
		assertEquals(hierarchyGrant.bottomLevel(), get(BOTTOM_LEVEL, map));
		assertEquals(hierarchyGrant.rollupPolicy(), get(ROLLUP_POLICY, map) == null ? "full" : get(ROLLUP_POLICY, map));
	}

	private void checkMemberGrant(List<? extends MappingMemberGrant> memberGrant, Object o) {
		if (o == null) {
			assertNotNull(memberGrant);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(memberGrant.size(), list.size());
			for (int i = 0; i < memberGrant.size(); i++) {
				checkMemberGrantItem(memberGrant.get(i), list.get(i));
			}
		}
	}

	private void checkMemberGrantItem(MappingMemberGrant memberGrant, Map<String, Object> map) {
		assertEquals(memberGrant.member(), get(MEMBER, map));
		assertEquals(memberGrant.access() == null ? null : memberGrant.access().getValue(), get(ACCESS, map));
	}

	private void checkVirtualCubes(List<? extends MappingVirtualCube> virtualCubes, List vrtualCubeList) {
		assertThat(virtualCubes).isNotNull();
		assertEquals(virtualCubes.size(), vrtualCubeList.size());
		for (int i = 0; i < virtualCubes.size(); i++) {
			checkVirtualCubeItem(virtualCubes.get(i), (Map) vrtualCubeList.get(i));
		}
	}

	private void checkVirtualCubeItem(MappingVirtualCube virtualCube, Map<String, Object> map) {
		assertThat(virtualCube.annotations()).isNotNull().isEmpty();
		assertNull(virtualCube.cubeUsages());
		checkVirtualCubeDimension(virtualCube.virtualCubeDimensions(), get(VIRTUAL_CUBE_DIMENSION, map));
		checkVirtualCubeMeasure(virtualCube.virtualCubeMeasures(), get(VIRTUAL_CUBE_MEASURE, map));
		checkVirtualCubeCalculatedMember(virtualCube.calculatedMembers(), get(CALCULATED_MEMBER, map));
		assertNotNull(virtualCube.namedSets());
		assertTrue(virtualCube.enabled());
		assertEquals(virtualCube.name(), get(NAME, map));
		assertEquals(virtualCube.defaultMeasure(), get(DEFAULT_MEASURE, map));
		assertNull(virtualCube.caption());
		assertNull(virtualCube.description());
	}

	private void checkVirtualCubeCalculatedMember(List<? extends MappingCalculatedMember> calculatedMember, Object o) {
		if (o == null) {
			assertNull(calculatedMember);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(calculatedMember.size(), list.size());
			for (int i = 0; i < calculatedMember.size(); i++) {
				checkVirtualCubeCalculatedMemberItem(calculatedMember.get(i), list.get(i));
			}
		}
	}

	private void checkVirtualCubeCalculatedMemberItem(MappingCalculatedMember calculatedMember, Map<String, Object> map) {
		assertThat(calculatedMember.annotations()).isNotNull().isEmpty();
		// TODO formula
		assertNull(calculatedMember.formula());
		assertNotNull(calculatedMember.calculatedMemberProperties());
		assertEquals(calculatedMember.name(), get(NAME, map));
		assertNull(calculatedMember.formatString());
		assertNull(calculatedMember.caption());
		assertNull(calculatedMember.description());
		assertEquals(calculatedMember.dimension(), get(DIMENSION, map));
		assertTrue(calculatedMember.visible());
		assertNull(calculatedMember.displayFolder());
	}

	private void checkVirtualCubeMeasure(List<? extends MappingVirtualCubeMeasure> virtualCubeMeasure, Object o) {
		if (o == null) {
			assertNull(virtualCubeMeasure);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(virtualCubeMeasure.size(), list.size());
			for (int i = 0; i < virtualCubeMeasure.size(); i++) {
				checkVirtualCubeMeasureItem(virtualCubeMeasure.get(i), list.get(i));
			}
		}
	}

	private void checkVirtualCubeMeasureItem(MappingVirtualCubeMeasure virtualCubeMeasure, Map<String, Object> map) {
		assertThat(virtualCubeMeasure.annotations()).isNotNull().isEmpty();
		assertEquals(virtualCubeMeasure.cubeName(), get(CUBE_NAME, map));
		assertEquals(virtualCubeMeasure.name(), get(NAME, map));
		assertTrue(virtualCubeMeasure.visible());
	}

	private void checkVirtualCubeDimension(List<? extends MappingVirtualCubeDimension> virtualCubeDimension, Object o) {
		if (o == null) {
			assertNull(virtualCubeDimension);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(virtualCubeDimension.size(), list.size());
			for (int i = 0; i < virtualCubeDimension.size(); i++) {
				checkVirtualCubeDimensionItem(virtualCubeDimension.get(i), list.get(i));
			}
		}
	}

	private void checkVirtualCubeDimensionItem(MappingVirtualCubeDimension virtualCubeDimension, Map<String, Object> map) {
		assertEquals(virtualCubeDimension.cubeName(), get(CUBE_NAME, map));
		assertEquals(virtualCubeDimension.name(), get(NAME, map));
	}

	private void checkPrivateDimension(MappingSchema schema, List<? extends MappingPrivateDimension> dimensions,
			List<Map<String, Object>> list) {
		assertNotNull(dimensions);
		assertEquals(dimensions.size(), list.size(), "Wrong dimensions size for schema " + schema.name());
		for (int i = 0; i < dimensions.size(); i++) {
			checkPrivateDimensionItem(schema, dimensions.get(i), list.get(i), i);
		}

	}

	private void checkPrivateDimensionItem(MappingSchema schema, MappingPrivateDimension sharedDimension, Map<String, Object> map,
			int i) {
		assertNotNull(sharedDimension.annotations());
		checkHierarchy(sharedDimension.hierarchies(), (List) map.get(HIERARCHY));
		assertEquals(sharedDimension.name(), get(NAME, map));
		assertEquals(sharedDimension.type() == null ? null : sharedDimension.type().getValue(), get(TYPE, map));
		assertEquals(sharedDimension.caption(), get(CAPTION, map));
		assertEquals(sharedDimension.description(), get("description", map));
	}

	private void checkCubes(List<? extends MappingCube> cubes, List<Map<String, Object>> foodmartCubeList) {
		assertThat(cubes).isNotNull();
		assertEquals(cubes.size(), foodmartCubeList.size());
		for (int i = 0; i < cubes.size(); i++) {
			checkCubeItem(cubes.get(i), foodmartCubeList.get(i));
		}
	}

	private void checkCubeItem(MappingCube cube, Map<String, Object> map) {
		assertThat(cube).isNotNull();
		assertEquals(map.get(NAME), cube.name());
		checkCubeAnnotations(cube.annotations(), get(ANNOTATIONS, map));
		assertNotNull(cube.calculatedMembers());
		assertNull(cube.caption());
		assertEquals(get(DEFAULT_MEASURE, map), cube.defaultMeasure());
		assertNull(cube.description());
		assertNotNull(cube.drillThroughActions());
		assertNotNull(cube.namedSets());
		// assertNull(cube.view());
		assertNotNull(cube.writebackTable());
		assertTrue(cube.cache());
		assertTrue(cube.enabled());
		assertNotNull(cube.measures());
		checkMeasure(cube, (List<Map<String, Object>>) map.get(MEASURE));

		List<? extends Object> dimensions = cube.dimensionUsageOrDimensions();
		assertThat(dimensions).isNotNull();
		assertEquals(dimensions.size(), ((List) map.get(DIMENSION)).size());
		for (int i = 0; i < dimensions.size(); i++) {
			checkDimension(dimensions.get(i), (Map<String, Object>) ((List) map.get(DIMENSION)).get(i));
		}
	}

	private Object get(String key, Map<String, Object> map) {
		return map.containsKey(key) ? map.get(key) : null;
	}

	private void checkDimension(Object object, Map<String, Object> map) {
		if (object instanceof MappingPrivateDimension dimension) {
			assertEquals(map.get(NAME), dimension.name());
			assertEquals(map.get(FOREIGN_KEY), dimension.foreignKey());
			assertEquals(get(TYPE, map), dimension.type() == null ? null : dimension.type().getValue());
			assertNotNull(dimension.annotations());
			assertNull(dimension.caption());
			assertNull(dimension.description());
			checkHierarchy(dimension.hierarchies(), (List) map.get(HIERARCHY));
		}
		if (object instanceof MappingDimensionUsage dimension) {
			assertNotNull(dimension.annotations());
			assertEquals(map.get(NAME), dimension.name());
			assertEquals(get(SOURCE, map), dimension.source());
			assertNull(dimension.level());
			assertNull(dimension.usagePrefix());
			assertEquals(map.get(FOREIGN_KEY), dimension.foreignKey());
		}
	}

	private void checkHierarchy(List<? extends MappingHierarchy> hierarchy, List<Map<String, Object>> listHierarchy) {
		assertThat(hierarchy).isNotNull();
		assertEquals(hierarchy.size(), listHierarchy.size());
		for (int i = 0; i < hierarchy.size(); i++) {
			checkHierarchyItem(hierarchy.get(i), listHierarchy.get(i));
		}
	}

	private void checkHierarchyItem(MappingHierarchy hierarchy, Map<String, Object> map) {
		assertThat(hierarchy.annotations()).isNotNull().isEmpty();
		assertNotNull(hierarchy.levels());
		checkLevel(hierarchy.levels(), (List) map.get(LEVEL));
		assertNotNull(hierarchy.memberReaderParameters());
		assertEquals(map.get(NAME), hierarchy.name());
		assertEquals(Boolean.valueOf((String) map.get(HAS_ALL)), hierarchy.hasAll());
		assertEquals(map.get(ALL_MEMBER_NAME), hierarchy.allMemberName());
		assertNull(hierarchy.allMemberCaption());
		assertNull(hierarchy.allLevelName());
		assertEquals(map.get(PRIMARY_KEY), hierarchy.primaryKey());
		assertEquals(map.get(PRIMARY_KEY_TABLE), hierarchy.primaryKeyTable());
		assertEquals(get(DEFAULT_MEMBER, map), hierarchy.defaultMember());
		assertNull(hierarchy.memberReaderClass());
		assertEquals(map.get(CAPTION), hierarchy.caption());
		assertNull(hierarchy.description());
		assertNull(hierarchy.uniqueKeyLevelName());
	}

	private void checkHierarchyJoin(MappingJoin join, Object o) {
		if (o == null) {
			assertNull(join);
		} else {
			Map<String, Object> map = (Map<String, Object>) o;
			assertNotNull(join);
			assertEquals(get(LEFT_KEY, map), join.getLeftKey());
			assertEquals(get(RIGHT_KEY, map), join.getRightKey());
			assertEquals(get(LEFT_ALIAS, map), join.getLeftAlias());
			assertEquals(get(RIGHT_ALIAS, map), join.getRightAlias());
			List<MappingRelationOrJoin> relations = join.getRelations();
			assertEquals(relations.size(), ((List) get(RELATION, map)).size());
			for (int i = 0; i < relations.size(); i++) {
				checkHierarchyJoinRelationItem(relations.get(i), (Map) ((List) get(RELATION, map)).get(i));
			}
		}
	}

	private void checkHierarchyJoinRelationItem(Object relation, Map<String, Object> map) {
		if (relation instanceof MappingTable) {
			checkTableItem((MappingTable) relation, map);
		}
	}

	private void checkTableItem(MappingTable table, Map<String, Object> map) {
		assertNull(table.getSql());
		assertNotNull(table.getAggExcludes());
		assertNotNull(table.getAggTables());
		assertNotNull(table.getHints());
		assertEquals(table.getName(), get(NAME, map));
		assertNull(table.getSchema());
		assertNull(table.getAlias());
	}

	private void checkLevel(List<? extends MappingLevel> level, List<Map<String, Object>> list) {
		assertThat(level).isNotNull();
		assertEquals(level.size(), list.size());
		for (int i = 0; i < level.size(); i++) {
			checkLevelItem(level.get(i), list.get(i));
		}
	}

	private void checkLevelItem(MappingLevel level, Map<String, Object> map) {
		assertThat(level.annotations()).isNotNull().isEmpty();
		checkExpression(level.keyExpression(), get(KEY_EXPRESSION, map));
		checkExpression(level.nameExpression(), get(NAME_EXPRESSION, map));
		checkExpression(level.captionExpression(), get(CAPTION_EXPRESSION, map));
		checkExpression(level.ordinalExpression(), get(ORDINAL_EXPRESSION, map));
		assertNull(level.parentExpression());
		checkClosure(level.closure(), get(CLOSURE, map));
		assertNotNull(level.properties());
		assertEquals(get(APPROX_ROW_COUNT, map) == null ? null : ((String) get(APPROX_ROW_COUNT, map)),
				level.approxRowCount());
		assertEquals(map.get(NAME), level.name());
		assertEquals(map.get(TABLE), level.table());
		assertEquals(map.get(COLUMN), level.column());
		assertEquals(map.get(NAME_COLUMN), level.nameColumn());
		assertEquals(map.get(ORDINAL_COLUMN), level.ordinalColumn());
		assertEquals(level.parentColumn(), get(PARENT_COLUMN, map));
		assertEquals(level.nullParentValue(), get(NULL_PARENT_VALUE, map));
		assertEquals(map.get(TYPE) == null ? "String" : map.get(TYPE),
				level.type() == null ? null : level.type().getValue());
		assertEquals(Boolean.valueOf((String) map.get(UNIQUE_MEMBERS)), level.uniqueMembers());
		assertEquals(get(LEVEL_TYPE, map) == null ? "Regular" : get(LEVEL_TYPE, map),
				level.levelType() == null ? null : level.levelType().getValue());
		assertEquals(get(HIDE_MEMBER_IF, map) == null ? "Never" : get(HIDE_MEMBER_IF, map),
				level.hideMemberIf() == null ? null : level.hideMemberIf().getValue());
		assertNull(level.formatter());
		assertNull(level.caption());
		assertNull(level.description());
		assertNull(level.captionColumn());
	}

	private void checkClosure(MappingClosure closure, Object o) {
		if (o == null) {
			assertNull(closure);
		} else {
			Map<String, Object> map = (Map<String, Object>) o;
			assertEquals(closure.table().getName(), get(TABLE, map));
			assertEquals(closure.parentColumn(), get(PARENT_COLUMN, map));
			assertEquals(closure.childColumn(), get(CHILD_COLUMN, map));
		}
	}

	private void checkMeasure(MappingCube cube, List<Map<String, Object>> list) {
		List<? extends MappingMeasure> measureList = cube.measures();
		assertThat(measureList).isNotNull();
		assertEquals(measureList.size(), list.size(), "Wrong measuries size for cube " + cube.name());
		for (int i = 0; i < measureList.size(); i++) {
			checkMeasureItem(cube, measureList.get(i), list.get(i), i);
		}
	}

	private void checkMeasureItem(MappingCube cube, MappingMeasure measure, Map<String, Object> map, int ixdex) {
		assertThat(measure.annotations()).isNotNull().isEmpty();
		checkExpression(measure.measureExpression(), get(MEASURE_EXPRESSION, map));
		assertNotNull(measure.calculatedMemberProperties());
		assertEquals(map.get(NAME), measure.name(), new StringBuilder("Wrong measure name ").append(ixdex)
				.append(" for cube ").append(cube.name()).toString());
		assertEquals(map.get(COLUMN), measure.column(), new StringBuilder("Wrong measure column ").append(ixdex)
				.append(" for cube ").append(cube.name()).toString());
		assertNull(measure.datatype());
		assertEquals(map.get(FORMAT_STRING), measure.formatString(), new StringBuilder("Wrong measure format ")
				.append(ixdex).append(" for cube ").append(cube.name()).toString());
		assertEquals(map.get(AGGREGATOR), measure.aggregator(), new StringBuilder("Wrong measure aggregator ")
				.append(ixdex).append(" for cube ").append(cube.name()).toString());
		assertNull(measure.formatter());
		assertNull(measure.caption());
		assertNull(measure.description());
		assertTrue(measure.visible());
		assertNull(measure.displayFolder());
	}

	private void checkExpression(MappingExpression expression, Object o) {
		if (o == null) {
			assertNull(expression);
		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			if (expression instanceof MappingExpressionView expressionView) {
				assertEquals(expressionView.sqls().size(), list.size());
				for (int i = 0; i < expressionView.sqls().size(); i++) {
					checkExpressionItem(expressionView.sqls().get(i), list.get(i));
				}
			}
		}
	}

	private void checkExpressionItem(MappingSQL sql, Map<String, Object> map) {
		assertEquals(sql.content().trim(), get(CONTENT, map));
		assertEquals(sql.dialect(), get(DIALECT, map));
	}

	private void checkCubeAnnotations(List<? extends MappingAnnotation> annotations, Object o) {
		if (o == null) {
			assertThat(annotations).isNotNull().isEmpty();

		} else {
			List<Map<String, Object>> list = (List<Map<String, Object>>) o;
			assertEquals(annotations.size(), list.size());
			for (int i = 0; i < annotations.size(); i++) {
				checkCubeAnnotationItem(annotations.get(i), list.get(i));
			}
		}
	}

	private void checkCubeAnnotationItem(MappingAnnotation annotation, Map<String, Object> map) {
		assertEquals(annotation.content(), get(CONTENT, map));
		assertEquals(annotation.name(), get(NAME, map));
	}
}
