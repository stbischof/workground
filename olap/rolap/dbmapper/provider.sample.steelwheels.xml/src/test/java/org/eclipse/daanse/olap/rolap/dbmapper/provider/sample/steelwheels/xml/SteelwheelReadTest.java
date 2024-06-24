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
package org.eclipse.daanse.olap.rolap.dbmapper.provider.sample.steelwheels.xml;

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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.junit.jupiter.api.Test;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.test.common.annotation.InjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequireServiceComponentRuntime
class SteelwheelReadTest {

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
    // steelWheelHierarchy0
    private static final Map<String, Object> steelWheelLevel00 = new HashMap<>();

    static {
        steelWheelLevel00.put(NAME, "Territory");
        steelWheelLevel00.put(TABLE, null);
        steelWheelLevel00.put(COLUMN, "TERRITORY");
        steelWheelLevel00.put(ORDINAL_COLUMN, null);
        steelWheelLevel00.put(TYPE, "String");
        steelWheelLevel00.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel00.put(LEVEL_TYPE, "Regular");
        steelWheelLevel00.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel01 = new HashMap<>();

    static {
        steelWheelLevel01.put(NAME, "Country");
        steelWheelLevel01.put(TABLE, null);
        steelWheelLevel01.put(COLUMN, "COUNTRY");
        steelWheelLevel01.put(ORDINAL_COLUMN, null);
        steelWheelLevel01.put(TYPE, "String");
        steelWheelLevel01.put(UNIQUE_MEMBERS, "false");
        steelWheelLevel01.put(LEVEL_TYPE, "Regular");
        steelWheelLevel01.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel02 = new HashMap<>();

    static {
        steelWheelLevel02.put(NAME, "State Province");
        steelWheelLevel02.put(TABLE, null);
        steelWheelLevel02.put(COLUMN, "STATE");
        steelWheelLevel02.put(ORDINAL_COLUMN, null);
        steelWheelLevel02.put(TYPE, "String");
        steelWheelLevel02.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel02.put(LEVEL_TYPE, "Regular");
        steelWheelLevel02.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel03 = new HashMap<>();

    static {
        steelWheelLevel03.put(NAME, "City");
        steelWheelLevel03.put(TABLE, null);
        steelWheelLevel03.put(COLUMN, "CITY");
        steelWheelLevel03.put(ORDINAL_COLUMN, null);
        steelWheelLevel03.put(TYPE, "String");
        steelWheelLevel03.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel03.put(LEVEL_TYPE, "Regular");
        steelWheelLevel03.put(HIDE_MEMBER_IF, "Never");
    }

    private static final List<Map<String, Object>> steelWheelLevelList0 = List.of(
        steelWheelLevel00,
        steelWheelLevel01,
        steelWheelLevel02,
        steelWheelLevel03
    );
    private static final Map<String, Object> steelWheelHierarchy0 = new HashMap<>();

    static {
        steelWheelHierarchy0.put(ALL_MEMBER_NAME, "All Markets");
        steelWheelHierarchy0.put(PRIMARY_KEY, "CUSTOMERNUMBER");
        steelWheelHierarchy0.put(PRIMARY_KEY_TABLE, "");
        steelWheelHierarchy0.put(CAPTION, null);
        steelWheelHierarchy0.put(TABLE, "customer_w_ter");
        steelWheelHierarchy0.put(NAME, null);
        steelWheelHierarchy0.put(HAS_ALL, "true");
        steelWheelHierarchy0.put(LEVEL, steelWheelLevelList0);
    }

    private static final List<Map<String, Object>> steelWheelHierarchyList0 = List.of(
        steelWheelHierarchy0
    );
    // steelWheelHierarchy1
    private static final Map<String, Object> steelWheelLevel10 = new HashMap<>();

    static {
        steelWheelLevel10.put(NAME, "Customer");
        steelWheelLevel10.put(COLUMN, "CUSTOMERNAME");
        steelWheelLevel10.put(ORDINAL_COLUMN, null);
        steelWheelLevel10.put(TYPE, "String");
        steelWheelLevel10.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel10.put(LEVEL_TYPE, "Regular");
        steelWheelLevel10.put(HIDE_MEMBER_IF, "Never");
    }

    private static final List<Map<String, Object>> steelWheelLevelList1 = List.of(
        steelWheelLevel10
    );
    private static final Map<String, Object> steelWheelHierarchy1 = new HashMap<>();

    static {
        steelWheelHierarchy1.put(ALL_MEMBER_NAME, "All Customers");
        steelWheelHierarchy1.put(PRIMARY_KEY, "CUSTOMERNUMBER");
        steelWheelHierarchy1.put(PRIMARY_KEY_TABLE, null);
        steelWheelHierarchy1.put(CAPTION, null);
        steelWheelHierarchy1.put(TABLE, "customer_w_ter");
        steelWheelHierarchy1.put(NAME, null);
        steelWheelHierarchy1.put(HAS_ALL, "true");
        steelWheelHierarchy1.put(LEVEL, steelWheelLevelList1);
    }

    private static final List<Map<String, Object>> steelWheelHierarchyList1 = List.of(
        steelWheelHierarchy1
    );

    // steelWheelHierarchy2
    private static final Map<String, Object> steelWheelLevel20 = new HashMap<>();

    static {
        steelWheelLevel20.put(NAME, "Line");
        steelWheelLevel20.put(TABLE, "products");
        steelWheelLevel20.put(COLUMN, "PRODUCTLINE");
        steelWheelLevel20.put(ORDINAL_COLUMN, null);
        steelWheelLevel20.put(TYPE, "String");
        steelWheelLevel20.put(UNIQUE_MEMBERS, "false");
        steelWheelLevel20.put(LEVEL_TYPE, "Regular");
        steelWheelLevel20.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel21 = new HashMap<>();

    static {
        steelWheelLevel21.put(NAME, "Vendor");
        steelWheelLevel21.put(TABLE, "products");
        steelWheelLevel21.put(COLUMN, "PRODUCTVENDOR");
        steelWheelLevel21.put(ORDINAL_COLUMN, null);
        steelWheelLevel21.put(TYPE, "String");
        steelWheelLevel21.put(UNIQUE_MEMBERS, "false");
        steelWheelLevel21.put(LEVEL_TYPE, "Regular");
        steelWheelLevel21.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel22 = new HashMap<>();

    static {
        steelWheelLevel22.put(NAME, "Product");
        steelWheelLevel22.put(TABLE, "products");
        steelWheelLevel22.put(COLUMN, "PRODUCTNAME");
        steelWheelLevel22.put(ORDINAL_COLUMN, null);
        steelWheelLevel22.put(TYPE, "String");
        steelWheelLevel22.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel22.put(LEVEL_TYPE, "Regular");
        steelWheelLevel22.put(HIDE_MEMBER_IF, "Never");
    }

    private static final List<Map<String, Object>> steelWheelLevelList2 = List.of(
        steelWheelLevel20,
        steelWheelLevel21,
        steelWheelLevel22
    );

    private static final Map<String, Object> steelWheelHierarchy2 = new HashMap<>();

    static {
        steelWheelHierarchy2.put(ALL_MEMBER_NAME, "All Products");
        steelWheelHierarchy2.put(PRIMARY_KEY, "PRODUCTCODE");
        steelWheelHierarchy2.put(PRIMARY_KEY_TABLE, "products");
        steelWheelHierarchy2.put(CAPTION, "");
        steelWheelHierarchy2.put(TABLE, "products");
        steelWheelHierarchy2.put(NAME, "");
        steelWheelHierarchy2.put(HAS_ALL, "true");
        steelWheelHierarchy2.put(LEVEL, steelWheelLevelList2);
    }

    private static final List<Map<String, Object>> steelWheelHierarchyList2 = List.of(
        steelWheelHierarchy2
    );

    // steelWheelHierarchy3
    private static final Map<String, Object> steelWheelLevel30 = new HashMap<>();

    static {
        steelWheelLevel30.put(NAME, "Years");
        steelWheelLevel30.put(TABLE, null);
        steelWheelLevel30.put(COLUMN, "YEAR_ID");
        steelWheelLevel30.put(ORDINAL_COLUMN, null);
        steelWheelLevel30.put(TYPE, "Integer");
        steelWheelLevel30.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel30.put(LEVEL_TYPE, "TimeYears");
        steelWheelLevel30.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel31 = new HashMap<>();

    static {
        steelWheelLevel31.put(NAME, "Quarters");
        steelWheelLevel31.put(TABLE, null);
        steelWheelLevel31.put(COLUMN, "QTR_NAME");
        steelWheelLevel31.put(ORDINAL_COLUMN, "QTR_ID");
        steelWheelLevel31.put(TYPE, "String");
        steelWheelLevel31.put(UNIQUE_MEMBERS, "false");
        steelWheelLevel31.put(LEVEL_TYPE, "TimeQuarters");
        steelWheelLevel31.put(HIDE_MEMBER_IF, "Never");
    }

    private static final Map<String, Object> steelWheelLevel32 = new HashMap<>();

    static {
        steelWheelLevel32.put(NAME, "Months");
        steelWheelLevel32.put(TABLE, null);
        steelWheelLevel32.put(COLUMN, "MONTH_NAME");
        steelWheelLevel32.put(ORDINAL_COLUMN, "MONTH_ID");
        steelWheelLevel32.put(TYPE, "String");
        steelWheelLevel32.put(UNIQUE_MEMBERS, "false");
        steelWheelLevel32.put(LEVEL_TYPE, "TimeMonths");
        steelWheelLevel32.put(HIDE_MEMBER_IF, "Never");
    }

    private static final List<Map<String, Object>> steelWheelLevelList3 = List.of(
        steelWheelLevel30,
        steelWheelLevel31,
        steelWheelLevel32
    );
    private static final Map<String, Object> steelWheelHierarchy3 = new HashMap<>();

    static {
        steelWheelHierarchy3.put(ALL_MEMBER_NAME, "All Years");
        steelWheelHierarchy3.put(PRIMARY_KEY, "TIME_ID");
        steelWheelHierarchy3.put(PRIMARY_KEY_TABLE, null);
        steelWheelHierarchy3.put(CAPTION, null);
        steelWheelHierarchy3.put(TABLE, "time");
        steelWheelHierarchy3.put(NAME, null);
        steelWheelHierarchy3.put(HAS_ALL, "true");
        steelWheelHierarchy3.put(LEVEL, steelWheelLevelList3);
    }

    private static final List<Map<String, Object>> steelWheelHierarchyList3 = List.of(
        steelWheelHierarchy3
    );

    // steelWheelHierarchy4
    private static final Map<String, Object> steelWheelLevel40 = new HashMap<>();

    static {
        steelWheelLevel40.put(NAME, "Type");
        steelWheelLevel40.put(TABLE, null);
        steelWheelLevel40.put(COLUMN, "STATUS");
        steelWheelLevel40.put(ORDINAL_COLUMN, null);
        steelWheelLevel40.put(TYPE, "String");
        steelWheelLevel40.put(UNIQUE_MEMBERS, "true");
        steelWheelLevel40.put(LEVEL_TYPE, "Regular");
        steelWheelLevel40.put(HIDE_MEMBER_IF, "Never");
    }

    private static final List<Map<String, Object>> steelWheelLevelList4 = List.of(
        steelWheelLevel40
    );
    private static final Map<String, Object> steelWheelHierarchy4 = new HashMap<>();

    static {
        steelWheelHierarchy4.put(ALL_MEMBER_NAME, "All Status Types");
        steelWheelHierarchy4.put(PRIMARY_KEY, "STATUS");
        steelWheelHierarchy4.put(PRIMARY_KEY_TABLE, null);
        steelWheelHierarchy4.put(CAPTION, null);
        steelWheelHierarchy4.put(TABLE, null);
        steelWheelHierarchy4.put(NAME, null);
        steelWheelHierarchy4.put(HAS_ALL, "true");
        steelWheelHierarchy4.put(LEVEL, steelWheelLevelList4);
    }

    private static final List<Map<String, Object>> steelWheelHierarchyList4 = List.of(
        steelWheelHierarchy4
    );

    // steelWheelDimension0
    private static final Map<String, Object> steelWheelDimension0 = new HashMap<>();

    static {
        steelWheelDimension0.put(FOREIGN_KEY, "CUSTOMERNUMBER");
        steelWheelDimension0.put(NAME, "Markets");
        steelWheelDimension0.put(HIERARCHY, steelWheelHierarchyList0);
    }

    // steelWheelDimension1
    private static final Map<String, Object> steelWheelDimension1 = new HashMap<>();

    static {
        steelWheelDimension1.put(FOREIGN_KEY, "CUSTOMERNUMBER");
        steelWheelDimension1.put(NAME, "Customers");
        steelWheelDimension1.put(HIERARCHY, steelWheelHierarchyList1);
    }

    // steelWheelDimension2
    private static final Map<String, Object> steelWheelDimension2 = new HashMap<>();

    static {
        steelWheelDimension2.put(FOREIGN_KEY, "PRODUCTCODE");
        steelWheelDimension2.put(NAME, "Product");
        steelWheelDimension2.put(HIERARCHY, steelWheelHierarchyList2);
    }

    // steelWheelDimension3
    private static final Map<String, Object> steelWheelDimension3 = new HashMap<>();

    static {
        steelWheelDimension3.put(TYPE, "TimeDimension");
        steelWheelDimension3.put(FOREIGN_KEY, "TIME_ID");
        steelWheelDimension3.put(NAME, "Time");
        steelWheelDimension3.put(HIERARCHY, steelWheelHierarchyList3);
    }

    // steelWheelDimension4
    private static final Map<String, Object> steelWheelDimension4 = new HashMap<>();

    static {
        steelWheelDimension4.put(FOREIGN_KEY, "STATUS");
        steelWheelDimension4.put(NAME, "Order Status");
        steelWheelDimension4.put(HIERARCHY, steelWheelHierarchyList4);
    }

    private static final List<Map<String, Object>> steelWheelDimensionList = List.of(
        steelWheelDimension0,
        steelWheelDimension1,
        steelWheelDimension2,
        steelWheelDimension3,
        steelWheelDimension4
    );

    private static final Map<String, Object> steelWheelMeasure0 = new HashMap<>();

    static {
        steelWheelMeasure0.put(NAME, "Quantity");
        steelWheelMeasure0.put(COLUMN, "QUANTITYORDERED");
        steelWheelMeasure0.put(FORMAT_STRING, "#,###");
        steelWheelMeasure0.put(AGGREGATOR, "sum");
    }

    private static final Map<String, Object> steelWheelMeasure1 = new HashMap<>();

    static {
        steelWheelMeasure1.put(NAME, "Sales");
        steelWheelMeasure1.put(COLUMN, "TOTALPRICE");
        steelWheelMeasure1.put(FORMAT_STRING, "#,###");
        steelWheelMeasure1.put(AGGREGATOR, "sum");
    }

    private static final List<Map<String, Object>> steelWheelMeasureList = List.of(
        steelWheelMeasure0,
        steelWheelMeasure1
    );


    @Test
    void test_SteelWheel(@InjectService(filter = "(&(sample.type=xml)(sample.name=SteelWheels))") DatabaseMappingSchemaProvider provider) throws Exception {
        MappingSchema schema = provider.get();
        assertThat(schema).isNotNull();
        assertEquals("SteelWheels", schema.name());
        assertThat(schema.cubes()).isNotNull();
        assertEquals(1, schema.cubes().size());

        MappingCube cube = schema.cubes().get(0);
        assertThat(cube).isNotNull();
        assertEquals("SteelWheelsSales", cube.name());
		assertThat(cube.annotations()).isNotNull().isEmpty();

        assertNotNull(cube.calculatedMembers());
        assertNull(cube.caption());
        assertNull(cube.defaultMeasure());
        assertNull(cube.description());
        assertNotNull(cube.drillThroughActions());
        assertNotNull(cube.namedSets());
        //assertNull(cube.view());
        assertNotNull(cube.writebackTable());
        assertTrue(cube.cache());
        assertTrue(cube.enabled());
        assertNotNull(cube.measures());
        checkMeasure(cube, steelWheelMeasureList);

        List<? extends Object> dimensions = cube.dimensionUsageOrDimensions();
        assertThat(dimensions).isNotNull();
        assertEquals(dimensions.size(), steelWheelDimensionList.size());
        for (int i = 0; i < dimensions.size(); i++) {
            checkDimension(dimensions.get(i), steelWheelDimensionList.get(i));
        }

        List<? extends Object> measuries = cube.measures();
        assertThat(measuries).isNotNull();
        assertEquals(2, measuries.size());
    }



    private void checkRoleItem(MappingRole role, Map<String, Object> map) {
        assertNull(role.annotations());
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
        assertEquals(schemaGrant.access() == null ? null : schemaGrant.access().getValue(), get(ACCESS, map) == null ? "none" : get(ACCESS, map));
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
        assertEquals(hierarchyGrant.access() == null ?  null : hierarchyGrant.access().getValue(), get(ACCESS, map));
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
        assertNull(virtualCube.annotations());
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
        assertNull(calculatedMember.annotations());
        //TODO formula
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
        assertNull(virtualCubeMeasure.annotations());
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

    private void checkPrivateDimension(
        MappingSchema schema,
        List<? extends MappingPrivateDimension> dimensions,
        List<Map<String, Object>> list
    ) {
        assertNotNull(dimensions);
        assertEquals(dimensions.size(), list.size(), "Wrong dimensions size for schema " + schema.name());
        for (int i = 0; i < dimensions.size(); i++) {
            checkPrivateDimensionItem(schema, dimensions.get(i), list.get(i), i);
        }

    }

    private void checkPrivateDimensionItem(
        MappingSchema schema,
        MappingPrivateDimension sharedDimension,
        Map<String, Object> map,
        int i
    ) {
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
        //assertNull(cube.view());
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
        assertEquals(map.get(TYPE) == null ? "String" : map.get(TYPE) , level.type() == null ? null : level.type().getValue());
        assertEquals(Boolean.valueOf((String) map.get(UNIQUE_MEMBERS)), level.uniqueMembers());
        assertEquals(get(LEVEL_TYPE, map) == null ? "Regular" : get(LEVEL_TYPE, map), level.levelType() == null ? null : level.levelType().getValue());
        assertEquals(get(HIDE_MEMBER_IF, map) == null ? "Never" : get(HIDE_MEMBER_IF, map), level.hideMemberIf() == null ? null : level.hideMemberIf().getValue());
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
        assertEquals(map.get(NAME), measure.name(),
            new StringBuilder("Wrong measure name ")
                .append(ixdex).append(" for cube ").append(cube.name()).toString());
        assertEquals(map.get(COLUMN), measure.column(),
            new StringBuilder("Wrong measure column ")
                .append(ixdex).append(" for cube ").append(cube.name()).toString());
        assertNull(measure.datatype());
        assertEquals(map.get(FORMAT_STRING), measure.formatString(),
            new StringBuilder("Wrong measure format ")
                .append(ixdex).append(" for cube ").append(cube.name()).toString());
        assertEquals(map.get(AGGREGATOR), measure.aggregator(),
            new StringBuilder("Wrong measure aggregator ")
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
            assertNull(annotations);
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
