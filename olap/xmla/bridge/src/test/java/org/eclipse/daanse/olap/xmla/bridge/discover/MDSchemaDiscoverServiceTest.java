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
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */

package org.eclipse.daanse.olap.xmla.bridge.discover;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.ContextGroup;
import org.eclipse.daanse.olap.api.DataType;
import org.eclipse.daanse.olap.api.Syntax;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.NamedSet;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.olap.api.function.FunctionAtom;
import org.eclipse.daanse.olap.api.function.FunctionMetaData;
import org.eclipse.daanse.olap.api.function.FunctionTable;
import org.eclipse.daanse.olap.api.result.Property;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.eclipse.daanse.olap.xmla.bridge.ContextsSupplyerImpl;
import org.eclipse.daanse.xmla.api.common.enums.DimensionCardinalityEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionUniqueSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.HierarchyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.MeasureAggregatorEnum;
import org.eclipse.daanse.xmla.api.common.enums.MemberTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.common.enums.SetEvaluationContextEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureEnum;
import org.eclipse.daanse.xmla.api.discover.Properties;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRestrictions;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRestrictions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MDSchemaDiscoverServiceTest {

    @Mock
    private Context context1;

    @Mock
    private Context context2;

    @Mock
    private DatabaseMappingSchemaProvider dmsp1;

    @Mock
    private DatabaseMappingSchemaProvider dmsp2;

    @Mock
    private MappingSchema mappingSchema1;

    @Mock
    private MappingSchema mappingSchema2;

    @Mock
    private Schema schema1;

    @Mock
    private Schema schema2;

    @Mock
    private MappingCube mappingCube1;

    @Mock
    private MappingCube mappingCube2;

    @Mock
    private Cube cube1;

    @Mock
    private Cube cube2;

    @Mock
    private Dimension dimension1;

    @Mock
    private Dimension dimension2;

    @Mock
    private Hierarchy hierarchy1;

    @Mock
    private Hierarchy hierarchy2;

    @Mock
    private Level level1;

    @Mock
    private Level level2;

    @Mock
    private Member measure1;

    @Mock
    private Member measure2;

    @Mock
    private Connection connection;
    @Mock
    private ContextGroup contextGroup;

    private MDSchemaDiscoverService service;

    private ContextsSupplyerImpl cls;

    @BeforeEach
    void setup() {
        /*
         * empty list, but override with:
         * when(cls.get()).thenReturn(List.of(context1,context2));`
         */

        cls = Mockito.spy(new ContextsSupplyerImpl(contextGroup));
        service = new MDSchemaDiscoverService(cls);
    }

    @Test
    void mdSchemaActions() {
        MdSchemaActionsRequest request = mock(MdSchemaActionsRequest.class);
        MdSchemaActionsRestrictions restrictions = mock(MdSchemaActionsRestrictions.class);

        List<MdSchemaActionsResponseRow> rows = service.mdSchemaActions(request);
        assertThat(rows).isNull();
    }

    @Test
    void mdSchemaCubes() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaCubesRequest request = mock(MdSchemaCubesRequest.class);
        MdSchemaCubesRestrictions restrictions = mock(MdSchemaCubesRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn("foo");

        when(mappingSchema1.name()).thenReturn("schema1Name");

        when(mappingSchema2.name()).thenReturn("schema2Name");

        when(mappingCube1.name()).thenReturn("cube1Name");
        when(mappingCube2.name()).thenReturn("cube2Name");
        when(mappingCube2.description()).thenReturn("cube2description");
        when(mappingCube1.visible()).thenReturn(true);
        when(mappingCube2.visible()).thenReturn(false).thenReturn(true);
        ;

        when(mappingSchema1.cubes()).thenAnswer(setupDummyListAnswer(mappingCube1, mappingCube2));
        when(mappingSchema2.cubes()).thenAnswer(setupDummyListAnswer(mappingCube1, mappingCube2));

        when(dmsp1.get()).thenReturn(mappingSchema1);
        when(dmsp2.get()).thenReturn(mappingSchema2);

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<MdSchemaCubesResponseRow> rows = service.mdSchemaCubes(request);
        verify(context1, times(1)).getName();
        assertThat(rows).isNotNull().hasSize(3);
        MdSchemaCubesResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("foo");
        assertThat(row.schemaName()).contains("schema1Name");
        assertThat(row.description()).contains("foo Schema - cube1Name Cube");

        row = rows.get(1);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("foo");
        assertThat(row.schemaName()).contains("schema2Name");
        assertThat(row.description()).contains("foo Schema - cube1Name Cube");

        row = rows.get(2);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("foo");
        assertThat(row.schemaName()).contains("schema2Name");
        assertThat(row.description()).contains("cube2description");
    }

    @Test
    void mdSchemaDimensions() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaDimensionsRequest request = mock(MdSchemaDimensionsRequest.class);
        MdSchemaDimensionsRestrictions restrictions = mock(MdSchemaDimensionsRestrictions.class);
        Properties properties = mock(Properties.class);

        when(properties.deep()).thenReturn(Optional.of(true));

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));
        when(request.properties()).thenReturn(properties);

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(hierarchy1.getUniqueName()).thenReturn("hierarchy1UniqueName");
        when(hierarchy1.getUniqueName()).thenReturn("hierarchy1UniqueName");

        //when(hierarchy2.getUniqueName()).thenReturn("hierarchy1UniqueName");

        when(dimension1.getName()).thenReturn("dimension1Name");
        when(dimension1.getUniqueName()).thenReturn("dimension1UniqueName");
        when(dimension1.getCaption()).thenReturn("dimension1Caption");
        when(dimension1.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(dimension2.getName()).thenReturn("dimension2Name");
        when(dimension2.getUniqueName()).thenReturn("dimension2UniqueName");
        when(dimension2.getCaption()).thenReturn("dimension2Caption");
        when(dimension2.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");
        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        //when(cube2.getDescription()).thenReturn("cube2description");

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        //when(context1.getConnection()).thenReturn(connection);
        when(context2.getConnection()).thenReturn(connection);

        //when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<MdSchemaDimensionsResponseRow> rows = service.mdSchemaDimensions(request);
        verify(context1, times(1)).getName();
        //verify(context2, times(1)).getName();
        assertThat(rows).isNotNull().hasSize(8);
        checkMdSchemaDimensionsResponseRow(rows.get(0), "foo",
            "schema1Name", "cube1Name",
            "dimension1Name", "dimension1UniqueName",
            "dimension1Caption", 0, 1,
            "hierarchy1UniqueName",
            "cube1Name Cube - dimension1Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(1), "foo",
            "schema1Name", "cube1Name",
            "dimension2Name", "dimension2UniqueName",
            "dimension2Caption", 1, 1,
            "hierarchy1UniqueName",
            "cube1Name Cube - dimension2Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(2), "foo",
            "schema1Name", "cube2Name",
            "dimension1Name", "dimension1UniqueName",
            "dimension1Caption", 0, 1,
            "hierarchy1UniqueName",
            "cube2Name Cube - dimension1Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(3), "foo",
            "schema1Name", "cube2Name",
            "dimension2Name", "dimension2UniqueName",
            "dimension2Caption", 1, 1,
            "hierarchy1UniqueName",
            "cube2Name Cube - dimension2Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(4), "foo",
            "schema2Name", "cube1Name",
            "dimension1Name", "dimension1UniqueName",
            "dimension1Caption", 0, 1,
            "hierarchy1UniqueName",
            "cube1Name Cube - dimension1Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(5), "foo",
            "schema2Name", "cube1Name",
            "dimension2Name", "dimension2UniqueName",
            "dimension2Caption", 1, 1,
            "hierarchy1UniqueName",
            "cube1Name Cube - dimension2Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(6), "foo",
            "schema2Name", "cube2Name",
            "dimension1Name", "dimension1UniqueName",
            "dimension1Caption", 0, 1,
            "hierarchy1UniqueName",
            "cube2Name Cube - dimension1Name Dimension");

        checkMdSchemaDimensionsResponseRow(rows.get(7), "foo",
            "schema2Name", "cube2Name",
            "dimension2Name", "dimension2UniqueName",
            "dimension2Caption", 1, 1,
            "hierarchy1UniqueName",
            "cube2Name Cube - dimension2Name Dimension");

    }

    @Test
    void mdSchemaFunctions() {
        when(cls.get()).thenReturn(List.of(context1));

        MdSchemaFunctionsRequest request = mock(MdSchemaFunctionsRequest.class);
        MdSchemaFunctionsRestrictions restrictions = mock(MdSchemaFunctionsRestrictions.class);
        FunctionTable functionTable = mock(FunctionTable.class);
        FunctionAtom functionAtom1 = mock(FunctionAtom.class);
        FunctionAtom functionAtom2 = mock(FunctionAtom.class);
        when(functionAtom1.name()).thenReturn("functionAtom1Name");
        when(functionAtom1.syntax()).thenReturn(Syntax.Function);

        when(functionAtom2.name()).thenReturn("functionAtom2Name");
        when(functionAtom2.syntax()).thenReturn(Syntax.Method);

        FunctionMetaData functionMetaData1 = mock(FunctionMetaData.class);
        FunctionMetaData functionMetaData2 = mock(FunctionMetaData.class);
        when(functionMetaData1.parameterDataTypes()).thenAnswer(setupDummyArrayAnswer(DataType.INTEGER,
            DataType.MEMBER));
        when(functionMetaData1.returnCategory()).thenReturn(DataType.INTEGER);
        when(functionMetaData1.description()).thenReturn("functionMetaData1Description");
        when(functionMetaData2.parameterDataTypes()).thenAnswer(setupDummyArrayAnswer(DataType.CUBE));
        when(functionMetaData2.returnCategory()).thenReturn(DataType.MEMBER);
        when(functionMetaData2.description()).thenReturn("functionMetaData2Description");

        when(functionMetaData1.functionAtom()).thenReturn(functionAtom1);
        when(functionMetaData2.functionAtom()).thenReturn(functionAtom2);

        when(functionTable.getFunctionMetaDatas()).thenAnswer(setupDummyListAnswer(functionMetaData1,
            functionMetaData2));

        when(request.restrictions()).thenReturn(restrictions);
        when(schema1.getFunTable()).thenReturn(functionTable);
        when(connection.getSchema()).thenReturn(schema1);

        when(context1.getConnection()).thenReturn(connection);

        List<MdSchemaFunctionsResponseRow> rows = service.mdSchemaFunctions(request);
        assertThat(rows).isNotNull().hasSize(2);
        checkMdSchemaFunctionsResponseRow(rows.get(0), "functionAtom1Name",
            "functionMetaData1Description", "Integer, Member",
            2, OriginEnum.MSOLAP, "functionAtom1Name");
        checkMdSchemaFunctionsResponseRow(rows.get(1), "functionAtom2Name",
            "functionMetaData2Description", "Cube",
            12, OriginEnum.MSOLAP, "functionAtom2Name");

    }

    @Test
    void mdSchemaHierarchies() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaHierarchiesRequest request = mock(MdSchemaHierarchiesRequest.class);
        MdSchemaHierarchiesRestrictions restrictions = mock(MdSchemaHierarchiesRestrictions.class);
        Properties properties = mock(Properties.class);

        when(properties.deep()).thenReturn(Optional.of(true));

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));
        when(request.properties()).thenReturn(properties);

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(hierarchy1.getUniqueName()).thenReturn("hierarchy1UniqueName");
        when(hierarchy2.getUniqueName()).thenReturn("hierarchy2UniqueName");
        when(hierarchy1.getName()).thenReturn("hierarchy1Name");
        when(hierarchy2.getName()).thenReturn("hierarchy2Name");

        when(dimension1.getName()).thenReturn("dimension1Name");
        when(dimension1.getUniqueName()).thenReturn("dimension1UniqueName");
        when(dimension1.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(dimension2.getName()).thenReturn("dimension2Name");
        when(dimension2.getUniqueName()).thenReturn("dimension2UniqueName");
        when(dimension2.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");
        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaHierarchiesResponseRow> rows = service.mdSchemaHierarchies(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(16);

        checkMdSchemaHierarchiesResponseRow(rows.get(0), "foo", "schema1Name", "cube1Name",
            "dimension1UniqueName", "cube1Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 0, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(1), "foo", "schema1Name", "cube1Name",
            "dimension1UniqueName", "cube1Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 1, "hierarchy2UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(2), "foo", "schema1Name", "cube1Name",
            "dimension2UniqueName", "cube1Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 2, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(3), "foo", "schema1Name", "cube1Name",
            "dimension2UniqueName", "cube1Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 3, "hierarchy2UniqueName");

        checkMdSchemaHierarchiesResponseRow(rows.get(4), "foo", "schema1Name", "cube2Name",
            "dimension1UniqueName", "cube2Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 0, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(5), "foo", "schema1Name", "cube2Name",
            "dimension1UniqueName", "cube2Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 1, "hierarchy2UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(6), "foo", "schema1Name", "cube2Name",
            "dimension2UniqueName", "cube2Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 2, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(7), "foo", "schema1Name", "cube2Name",
            "dimension2UniqueName", "cube2Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 3, "hierarchy2UniqueName");

        checkMdSchemaHierarchiesResponseRow(rows.get(8), "foo", "schema2Name", "cube1Name",
            "dimension1UniqueName", "cube1Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 0, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(9), "foo", "schema2Name", "cube1Name",
            "dimension1UniqueName", "cube1Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 1, "hierarchy2UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(10), "foo", "schema2Name", "cube1Name",
            "dimension2UniqueName", "cube1Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 2, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(11), "foo", "schema2Name", "cube1Name",
            "dimension2UniqueName", "cube1Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 3, "hierarchy2UniqueName");

        checkMdSchemaHierarchiesResponseRow(rows.get(12), "foo", "schema2Name", "cube2Name",
            "dimension1UniqueName", "cube2Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 0, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(13), "foo", "schema2Name", "cube2Name",
            "dimension1UniqueName", "cube2Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 1, "hierarchy2UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(14), "foo", "schema2Name", "cube2Name",
            "dimension2UniqueName", "cube2Name Cube - hierarchy1Name Hierarchy",
            "hierarchy1Name", 2, "hierarchy1UniqueName");
        checkMdSchemaHierarchiesResponseRow(rows.get(15), "foo", "schema2Name", "cube2Name",
            "dimension2UniqueName", "cube2Name Cube - hierarchy2Name Hierarchy",
            "hierarchy2Name", 3, "hierarchy2UniqueName");

    }

    @Test
    void mdSchemaKpis() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaKpisRequest request = mock(MdSchemaKpisRequest.class);
        MdSchemaKpisRestrictions restrictions = mock(MdSchemaKpisRestrictions.class);
        Properties properties = mock(Properties.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaKpisResponseRow> rows = service.mdSchemaKpis(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(0);

    }

    @Test
    void mdSchemaLevels() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaLevelsRequest request = mock(MdSchemaLevelsRequest.class);
        MdSchemaLevelsRestrictions restrictions = mock(MdSchemaLevelsRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(level1.getName()).thenReturn("level1Name");
        when(level2.getName()).thenReturn("level2Name");
        when(level1.getUniqueName()).thenReturn("level1UniqueName");
        when(level2.getUniqueName()).thenReturn("level2UniqueName");
        when(level1.isAll()).thenReturn(true);
        when(level2.isAll()).thenReturn(true);
        when(level1.getCaption()).thenReturn("level1Caption");
        when(level2.getCaption()).thenReturn("level2Caption");
        when(level2.getDescription()).thenReturn("level2Description");

        when(hierarchy1.getName()).thenReturn("hierarchy1Name");
        when(hierarchy2.getName()).thenReturn("hierarchy2Name");
        when(hierarchy1.getUniqueName()).thenReturn("hierarchy1UniqueName");
        when(hierarchy2.getUniqueName()).thenReturn("hierarchy2UniqueName");

        when(hierarchy1.getLevels()).thenAnswer(setupDummyArrayAnswer(level1, level2));
        when(hierarchy2.getLevels()).thenAnswer(setupDummyArrayAnswer(level1, level2));

        when(dimension1.getUniqueName()).thenReturn("dimension1UniqueName");
        when(dimension1.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(dimension2.getUniqueName()).thenReturn("dimension2UniqueName");
        when(dimension2.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");
        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaLevelsResponseRow> rows = service.mdSchemaLevels(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(32);
        checkMdSchemaLevelsResponseRow(rows.get(0),
            "foo", "schema1Name", "cube1Name",
            "dimension1UniqueName", "hierarchy1UniqueName",
            "level1Name", "level1UniqueName",
            "level1Caption", 0,
            1, LevelTypeEnum.ALL,
            "cube1Name Cube - hierarchy1Name Hierarchy - level1Name Level");
        checkMdSchemaLevelsResponseRow(rows.get(1),
            "foo", "schema1Name", "cube1Name",
            "dimension1UniqueName", "hierarchy1UniqueName",
            "level2Name", "level2UniqueName",
            "level2Caption", 0,
            1, LevelTypeEnum.ALL,
            "level2Description");
        checkMdSchemaLevelsResponseRow(rows.get(31),
            "foo", "schema2Name", "cube2Name",
            "dimension2UniqueName", "hierarchy2UniqueName",
            "level2Name", "level2UniqueName",
            "level2Caption", 0,
            1, LevelTypeEnum.ALL,
            "level2Description");

    }

    @Test
    void mdSchemaMeasureGroupDimensions() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaMeasureGroupDimensionsRequest request = mock(MdSchemaMeasureGroupDimensionsRequest.class);
        MdSchemaMeasureGroupDimensionsRestrictions restrictions =
            mock(MdSchemaMeasureGroupDimensionsRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(dimension1.getUniqueName()).thenReturn("dimension1UniqueName");
        when(dimension2.getUniqueName()).thenReturn("dimension2UniqueName");

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");
        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaMeasureGroupDimensionsResponseRow> rows = service.mdSchemaMeasureGroupDimensions(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(8);
        checkMdSchemaMeasureGroupDimensionsResponseRow(rows.get(0),
            "foo", "schema1Name", "cube1Name",
            "ONE", "dimension1UniqueName", DimensionCardinalityEnum.MANY,
            false, false
        );
        checkMdSchemaMeasureGroupDimensionsResponseRow(rows.get(7),
            "foo", "schema2Name", "cube2Name",
            "ONE", "dimension2UniqueName", DimensionCardinalityEnum.MANY,
            false, false
        );
    }

    @Test
    void mdSchemaMeasureGroups() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaMeasureGroupsRequest request = mock(MdSchemaMeasureGroupsRequest.class);
        MdSchemaMeasureGroupsRestrictions restrictions = mock(MdSchemaMeasureGroupsRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(mappingSchema1.name()).thenReturn("schema1Name");

        when(mappingSchema2.name()).thenReturn("schema2Name");

        when(mappingCube1.name()).thenReturn("cube1Name");
        when(mappingCube2.name()).thenReturn("cube2Name");

        when(mappingSchema1.cubes()).thenAnswer(setupDummyListAnswer(mappingCube1, mappingCube2));
        when(mappingSchema2.cubes()).thenAnswer(setupDummyListAnswer(mappingCube1, mappingCube2));

        when(dmsp1.get()).thenReturn(mappingSchema1);
        when(dmsp2.get()).thenReturn(mappingSchema2);

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<MdSchemaMeasureGroupsResponseRow> rows = service.mdSchemaMeasureGroups(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(4);
        checkMdSchemaMeasureGroupsResponseRow(rows.get(0), "foo", "schema1Name", "cube1Name",
            "", false);
        checkMdSchemaMeasureGroupsResponseRow(rows.get(1), "foo", "schema1Name", "cube2Name",
            "", false);
        checkMdSchemaMeasureGroupsResponseRow(rows.get(2), "foo", "schema2Name", "cube1Name",
            "", false);
        checkMdSchemaMeasureGroupsResponseRow(rows.get(3), "foo", "schema2Name", "cube2Name",
            "", false);
    }

    @Test
    void mdSchemaMeasures() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaMeasuresRequest request = mock(MdSchemaMeasuresRequest.class);
        MdSchemaMeasuresRestrictions restrictions = mock(MdSchemaMeasuresRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(dimension1.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(dimension2.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");
        when(measure1.getName()).thenReturn("measure1Name");
        when(measure2.getName()).thenReturn("measure2Name");
        when(measure1.getUniqueName()).thenReturn("measure1UniqueName");
        when(measure2.getUniqueName()).thenReturn("measure2UniqueName");
        when(measure1.getCaption()).thenReturn("measure1Caption");
        when(measure2.getCaption()).thenReturn("measure2Caption");
        when(measure1.getPropertyValue(Property.StandardMemberProperty.$visible.getName())).thenReturn(Boolean.TRUE);
        when(measure2.getPropertyValue(Property.StandardMemberProperty.$visible.getName())).thenReturn(Boolean.TRUE);
        when(measure1.getPropertyValue(Property.StandardCellProperty.FORMAT_STRING.getName())).thenReturn(
            "formatString1");
        when(measure2.getPropertyValue(Property.StandardCellProperty.FORMAT_STRING.getName())).thenReturn(
            "formatString2");

        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube1.getMeasures()).thenAnswer(setupDummyListAnswer(measure1, measure2));
        when(cube2.getMeasures()).thenAnswer(setupDummyListAnswer(measure1, measure2));

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaMeasuresResponseRow> rows = service.mdSchemaMeasures(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(8);
        checkMdSchemaMeasuresResponseRow(rows.get(0),
            "foo", "schema1Name", "cube1Name", "measure1Name",
            "measure1UniqueName", "measure1Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube1Name Cube - measure1Name Member", true, "", "", "formatString1"
        );
        checkMdSchemaMeasuresResponseRow(rows.get(1),
            "foo", "schema1Name", "cube1Name", "measure2Name",
            "measure2UniqueName", "measure2Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube1Name Cube - measure2Name Member", true, "", "", "formatString2"
        );

        checkMdSchemaMeasuresResponseRow(rows.get(2),
            "foo", "schema1Name", "cube2Name", "measure1Name",
            "measure1UniqueName", "measure1Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube2Name Cube - measure1Name Member", true, "", "", "formatString1"
        );
        checkMdSchemaMeasuresResponseRow(rows.get(3),
            "foo", "schema1Name", "cube2Name", "measure2Name",
            "measure2UniqueName", "measure2Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube2Name Cube - measure2Name Member", true, "", "", "formatString2"
        );

        checkMdSchemaMeasuresResponseRow(rows.get(4),
            "foo", "schema2Name", "cube1Name", "measure1Name",
            "measure1UniqueName", "measure1Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube1Name Cube - measure1Name Member", true, "", "", "formatString1"
        );
        checkMdSchemaMeasuresResponseRow(rows.get(5),
            "foo", "schema2Name", "cube1Name", "measure2Name",
            "measure2UniqueName", "measure2Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube1Name Cube - measure2Name Member", true, "", "", "formatString2"
        );

        checkMdSchemaMeasuresResponseRow(rows.get(6),
            "foo", "schema2Name", "cube2Name", "measure1Name",
            "measure1UniqueName", "measure1Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube2Name Cube - measure1Name Member", true, "", "", "formatString1"
        );
        checkMdSchemaMeasuresResponseRow(rows.get(7),
            "foo", "schema2Name", "cube2Name", "measure2Name",
            "measure2UniqueName", "measure2Caption", MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN,
            LevelDbTypeEnum.DBTYPE_WSTR, "cube2Name Cube - measure2Name Member", true, "", "", "formatString2"
        );
    }

    @Test
    void mdSchemaMembers() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaMembersRequest request = mock(MdSchemaMembersRequest.class);
        MdSchemaMembersRestrictions restrictions = mock(MdSchemaMembersRestrictions.class);
        Properties properties = mock(Properties.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(request.properties()).thenReturn(properties);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(level1.getUniqueName()).thenReturn("level1UniqueName");
        when(level2.getUniqueName()).thenReturn("level2UniqueName");
        when(hierarchy1.getUniqueName()).thenReturn("hierarchy1UniqueName");
        when(hierarchy2.getUniqueName()).thenReturn("hierarchy2UniqueName");
        when(hierarchy1.getDimension()).thenReturn(dimension1);
        when(hierarchy2.getDimension()).thenReturn(dimension2);


        when(hierarchy1.getLevels()).thenAnswer(setupDummyArrayAnswer(level1, level2));
        when(hierarchy2.getLevels()).thenAnswer(setupDummyArrayAnswer(level1, level2));

        when(dimension1.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));
        when(dimension2.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));
        when(dimension1.getUniqueName()).thenReturn("dimension1UniqueName");
        when(dimension2.getUniqueName()).thenReturn("dimension2UniqueName");

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");
        when(measure1.getName()).thenReturn("measure1Name");
        when(measure2.getName()).thenReturn("measure2Name");
        when(measure1.getUniqueName()).thenReturn("measure1UniqueName");
        when(measure2.getUniqueName()).thenReturn("measure2UniqueName");
        when(measure1.getCaption()).thenReturn("measure1Caption");
        when(measure2.getCaption()).thenReturn("measure2Caption");
        when(measure1.getPropertyValue(Property.StandardMemberProperty.$visible.getName())).thenReturn(Boolean.TRUE);
        when(measure2.getPropertyValue(Property.StandardMemberProperty.$visible.getName())).thenReturn(Boolean.TRUE);
        when(measure1.getDescription()).thenReturn("measure1Description");
        when(measure2.getDescription()).thenReturn("measure2Description");
        when(measure1.getLevel()).thenReturn(level1);
        when(measure2.getLevel()).thenReturn(level2);

        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(level1.getMembers()).thenAnswer(setupDummyListAnswer(measure1, measure2));
        when(level2.getMembers()).thenAnswer(setupDummyListAnswer(measure1, measure2));
        when(level1.getHierarchy()).thenReturn(hierarchy1);
        when(level2.getHierarchy()).thenReturn(hierarchy2);

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaMembersResponseRow> rows = service.mdSchemaMembers(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(64);
        checkMdSchemaMembersResponseRow(rows.get(0), "foo",
            "schema1Name", "cube1Name", "dimension1UniqueName",
            "hierarchy1UniqueName", "level1UniqueName", 0, 0, "measure1Name",
            "measure1UniqueName", MemberTypeEnum.REGULAR_MEMBER, "measure1Caption", 100, 0,
            Optional.empty(), 0, "measure1Description"
        );
        checkMdSchemaMembersResponseRow(rows.get(63), "foo",
                "schema2Name", "cube2Name", "dimension2UniqueName",
                "hierarchy2UniqueName", "level2UniqueName", 0, 0, "measure2Name",
                "measure2UniqueName", MemberTypeEnum.REGULAR_MEMBER, "measure2Caption", 100, 0,
                Optional.empty(), 0, "measure2Description"
            );

    }

    @Test
    void mdSchemaProperties() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaPropertiesRequest request = mock(MdSchemaPropertiesRequest.class);
        MdSchemaPropertiesRestrictions restrictions = mock(MdSchemaPropertiesRestrictions.class);
        mondrian.olap.Property property1 = mock(mondrian.olap.Property.class);
        mondrian.olap.Property property2 = mock(mondrian.olap.Property.class);
        Properties properties = mock(Properties.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(property1.getName()).thenReturn("property1Name");
        when(property2.getName()).thenReturn("property2Name");
        when(property1.getCaption()).thenReturn("property1Caption");
        when(property2.getCaption()).thenReturn("property2Caption");

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(level1.getUniqueName()).thenReturn("level1UniqueName");
        when(level1.getHierarchy()).thenReturn(hierarchy1);
        when(level1.getProperties()).thenAnswer(setupDummyArrayAnswer(property1, property2));
        when(level1.getName()).thenReturn("level1Name");

        when(level2.getUniqueName()).thenReturn("level2UniqueName");
        when(level2.getHierarchy()).thenReturn(hierarchy2);
        when(level2.getProperties()).thenAnswer(setupDummyArrayAnswer(property1, property2));
        when(level2.getName()).thenReturn("level2Name");

        when(hierarchy1.getName()).thenReturn("hierarchy1Name");
        when(hierarchy1.getUniqueName()).thenReturn("hierarchy1UniqueName");
        when(hierarchy1.getDimension()).thenReturn(dimension1);
        when(hierarchy1.getLevels()).thenAnswer(setupDummyArrayAnswer(level1, level2));

        when(hierarchy2.getName()).thenReturn("hierarchy2Name");
        when(hierarchy2.getUniqueName()).thenReturn("hierarchy2UniqueName");
        when(hierarchy2.getDimension()).thenReturn(dimension2);
        when(hierarchy2.getLevels()).thenAnswer(setupDummyArrayAnswer(level1, level2));

        when(dimension1.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));
        when(dimension2.getHierarchies()).thenAnswer(setupDummyArrayAnswer(hierarchy1, hierarchy2));
        when(dimension1.getUniqueName()).thenReturn("dimension1UniqueName");
        when(dimension2.getUniqueName()).thenReturn("dimension2UniqueName");

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");

        when(cube1.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));
        when(cube2.getDimensions()).thenAnswer(setupDummyArrayAnswer(dimension1, dimension2));


        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaPropertiesResponseRow> rows = service.mdSchemaProperties(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(64);
        checkMdSchemaPropertiesResponseRow(rows.get(0), "foo", "schema1Name", "cube1Name",
            "dimension1UniqueName", "hierarchy1UniqueName", "level1UniqueName", PropertyTypeEnum.PROPERTY_MEMBER,
            "property1Name", "property1Caption", LevelDbTypeEnum.DBTYPE_WSTR,
            "cube1Name Cube - hierarchy1Name Hierarchy - level1Name Level - property1Name Property",
            PropertyContentTypeEnum.REGULAR);
        checkMdSchemaPropertiesResponseRow(rows.get(63), "foo", "schema2Name", "cube2Name",
                "dimension2UniqueName", "hierarchy2UniqueName", "level2UniqueName", PropertyTypeEnum.PROPERTY_MEMBER,
                "property2Name", "property2Caption", LevelDbTypeEnum.DBTYPE_WSTR,
                "cube2Name Cube - hierarchy2Name Hierarchy - level2Name Level - property2Name Property",
                PropertyContentTypeEnum.REGULAR);

    }

    @Test
    void mdSchemaSets() {
        when(cls.get()).thenReturn(List.of(context1, context2));

        MdSchemaSetsRequest request = mock(MdSchemaSetsRequest.class);
        MdSchemaSetsRestrictions restrictions = mock(MdSchemaSetsRestrictions.class);
        NamedSet namedSet1 = mock(NamedSet.class);
        NamedSet namedSet2 = mock(NamedSet.class);
        when(namedSet1.getName()).thenReturn("set1Name");
        when(namedSet2.getName()).thenReturn("set2Name");
        when(namedSet1.getDescription()).thenReturn("set1Description");
        when(namedSet2.getDescription()).thenReturn("set2Description");
        when(namedSet1.getCaption()).thenReturn("set1Caption");
        when(namedSet2.getCaption()).thenReturn("set2Caption");

        Properties properties = mock(Properties.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(schema1.getName()).thenReturn("schema1Name");

        when(schema2.getName()).thenReturn("schema2Name");

        when(cube1.getName()).thenReturn("cube1Name");
        when(cube2.getName()).thenReturn("cube2Name");

        when(cube1.getNamedSets()).thenAnswer(setupDummyArrayAnswer(namedSet1, namedSet2));
        when(cube2.getNamedSets()).thenAnswer(setupDummyArrayAnswer(namedSet1, namedSet2));

        when(schema1.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));
        when(schema2.getCubes()).thenAnswer(setupDummyArrayAnswer(cube1, cube2));

        when(connection.getSchemas()).thenAnswer(setupDummyListAnswer(schema1, schema2));

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");

        when(context2.getConnection()).thenReturn(connection);

        List<MdSchemaSetsResponseRow> rows = service.mdSchemaSets(request);
        verify(context1, times(1)).getName();
        verify(context2, times(3)).getName();
        assertThat(rows).isNotNull().hasSize(8);
        checkMdSchemaSetsResponseRow(rows.get(0), "foo", "schema1Name", "cube1Name",
            "set1Name", ScopeEnum.GLOBAL, "set1Description", Optional.empty(),
            "", "set1Caption", Optional.empty(), SetEvaluationContextEnum.STATIC);
        checkMdSchemaSetsResponseRow(rows.get(7), "foo", "schema2Name", "cube2Name",
                "set2Name", ScopeEnum.GLOBAL, "set2Description", Optional.empty(),
                "", "set2Caption", Optional.empty(), SetEvaluationContextEnum.STATIC);
    }

    private static <N> Answer<List<N>> setupDummyListAnswer(N... values) {
        final List<N> someList = new ArrayList<>(Arrays.asList(values));

        Answer<List<N>> answer = new Answer<>() {
            @Override
            public List<N> answer(InvocationOnMock invocation) throws Throwable {
                return someList;
            }
        };
        return answer;
    }

    private void checkMdSchemaDimensionsResponseRow(
        MdSchemaDimensionsResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String dimensionName,
        String dimensionUniqueName,
        String dimensionCaption,
        int dimensionOrdinal,
        int dimensionCardinality,
        String defaultHierarchy,
        String description
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.dimensionName()).contains(dimensionName);
        assertThat(row.dimensionUniqueName()).contains(dimensionUniqueName);
        assertThat(row.dimensionCaption()).contains(dimensionCaption);
        assertThat(row.dimensionOptional()).contains(dimensionOrdinal);
        assertThat(row.dimensionType()).isEmpty();
        assertThat(row.dimensionCardinality()).contains(dimensionCardinality);
        assertThat(row.defaultHierarchy()).contains(defaultHierarchy);
        assertThat(row.description()).contains(description);
    }

    private void checkMdSchemaFunctionsResponseRow(
        MdSchemaFunctionsResponseRow row,
        String functionalName,
        String description,
        String parameterList,
        int returnType,
        OriginEnum origin,
        String caption
    ) {
        assertThat(row).isNotNull();
        assertThat(row.functionalName()).contains(functionalName);
        assertThat(row.description()).contains(description);
        assertThat(row.parameterList()).contains(parameterList);
        assertThat(row.returnType()).contains(returnType);
        assertThat(row.origin()).contains(origin);
        assertThat(row.caption()).contains(caption);
    }

    private void checkMdSchemaHierarchiesResponseRow(
        MdSchemaHierarchiesResponseRow row,
        String catalogName, String schemaName, String cubeName, String dimensionUniqueName,
        String description, String hierarchyName, int hierarchyOrdinal, String hierarchyUniqueName
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.dimensionUniqueName()).contains(dimensionUniqueName);
        assertThat(row.description()).contains(description);
        assertThat(row.dimensionIsShared()).contains(true);
        assertThat(row.dimensionIsVisible()).contains(false);
        assertThat(row.dimensionUniqueSettings()).contains(DimensionUniqueSettingEnum.MEMBER_KEY);
        assertThat(row.hierarchyCardinality()).contains(0);
        assertThat(row.hierarchyIsVisible()).contains(false);
        assertThat(row.hierarchyName()).contains(hierarchyName);
        assertThat(row.hierarchyOrdinal()).contains(hierarchyOrdinal);
        assertThat(row.hierarchyOrigin()).contains(HierarchyOriginEnum.USER_DEFINED);
        assertThat(row.hierarchyUniqueName()).contains(hierarchyUniqueName);
        assertThat(row.isReadWrite()).contains(false);
        assertThat(row.isVirtual()).contains(false);
        assertThat(row.structure()).contains(StructureEnum.HIERARCHY_FULLY_BALANCED);
    }

    private void checkMdSchemaLevelsResponseRow(
        MdSchemaLevelsResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String dimensionUniqueName,
        String hierarchyUniqueName,
        String levelName,
        String levelUniqueName,
        String levelCaption,
        Integer levelNumber,
        Integer levelCardinality,
        LevelTypeEnum levelType,
        String description
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.dimensionUniqueName()).contains(dimensionUniqueName);
        assertThat(row.hierarchyUniqueName()).contains(hierarchyUniqueName);
        assertThat(row.levelName()).contains(levelName);
        assertThat(row.levelUniqueName()).contains(levelUniqueName);
        assertThat(row.levelCaption()).contains(levelCaption);
        assertThat(row.levelNumber()).contains(levelNumber);
        assertThat(row.levelCardinality()).contains(levelCardinality);
        assertThat(row.levelType()).contains(levelType);
        assertThat(row.description()).contains(description);
    }

    private void checkMdSchemaMeasureGroupDimensionsResponseRow(
        MdSchemaMeasureGroupDimensionsResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String measureGroupCardinality,
        String dimensionUniqueName,
        DimensionCardinalityEnum dimensionCardinality,
        Boolean dimensionIsVisible,
        Boolean dimensionIsFactDimension
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.measureGroupName()).contains(cubeName);
        assertThat(row.measureGroupCardinality()).contains(measureGroupCardinality);
        assertThat(row.dimensionUniqueName()).contains(dimensionUniqueName);
        assertThat(row.dimensionCardinality()).contains(dimensionCardinality);
        assertThat(row.dimensionIsVisible()).contains(dimensionIsVisible);
        assertThat(row.dimensionIsFactDimension()).contains(dimensionIsFactDimension);
    }

    private void checkMdSchemaMeasureGroupsResponseRow(
        MdSchemaMeasureGroupsResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String description,
        Boolean isWriteEnabled
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.measureGroupName()).contains(cubeName);
        assertThat(row.description()).contains(description);
        assertThat(row.isWriteEnabled()).contains(isWriteEnabled);
        assertThat(row.measureGroupCaption()).contains(cubeName);

    }

    private void checkMdSchemaMeasuresResponseRow(
        MdSchemaMeasuresResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String measureName,
        String measureUniqueName,
        String measureCaption,
        MeasureAggregatorEnum mdmeasureAggregator,
        LevelDbTypeEnum dataType,
        String description,
        boolean measureIsVisible,
        String measureLevelsList,
        String measureDisplayFolder,
        String defaultFormatString
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.measureName()).contains(measureName);
        assertThat(row.measureUniqueName()).contains(measureUniqueName);
        assertThat(row.measureCaption()).contains(measureCaption);
        assertThat(row.measureAggregator()).contains(mdmeasureAggregator);
        assertThat(row.dataType()).contains(dataType);
        assertThat(row.description()).contains(description);
        assertThat(row.measureIsVisible()).contains(measureIsVisible);
        assertThat(row.levelsList()).contains(measureLevelsList);
        assertThat(row.measureDisplayFolder()).contains(measureDisplayFolder);
        assertThat(row.defaultFormatString()).contains(defaultFormatString);
    }

    private void checkMdSchemaMembersResponseRow(
        MdSchemaMembersResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String dimensionUniqueName,
        String hierarchyUniqueName,
        String levelUniqueName,
        int levelNumber,
        int memberOrdinal,
        String memberName,
        String memberUniqueName,
        MemberTypeEnum memberType,
        String memberCaption,
        int childrenCardinality,
        int parentLevel,
        Optional<String> oParentUniqueName,
        int parentCount,
        String description
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.dimensionUniqueName()).contains(dimensionUniqueName);
        assertThat(row.hierarchyUniqueName()).contains(hierarchyUniqueName);
        assertThat(row.levelUniqueName()).contains(levelUniqueName);
        assertThat(row.levelNumber()).contains(levelNumber);
        assertThat(row.memberOrdinal()).contains(memberOrdinal);
        assertThat(row.memberName()).contains(memberName);
        assertThat(row.memberUniqueName()).contains(memberUniqueName);
        assertThat(row.memberType()).contains(memberType);
        assertThat(row.memberCaption()).contains(memberCaption);
        assertThat(row.childrenCardinality()).contains(childrenCardinality);
        assertThat(row.parentLevel()).contains(parentLevel);
        assertThat(row.parentUniqueName()).isEqualTo(oParentUniqueName);
        assertThat(row.parentCount()).contains(parentCount);
        assertThat(row.description()).contains(description);
    }

    private void checkMdSchemaPropertiesResponseRow(
        MdSchemaPropertiesResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String dimensionUniqueName,
        String hierarchyUniqueName,
        String levelUniqueName,
        PropertyTypeEnum propertyType,
        String propertyName,
        String propertyCaption,
        LevelDbTypeEnum dbType,
        String description,
        PropertyContentTypeEnum propertyContentType
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.dimensionUniqueName()).contains(dimensionUniqueName);
        assertThat(row.hierarchyUniqueName()).contains(hierarchyUniqueName);
        assertThat(row.levelUniqueName()).contains(levelUniqueName);
        assertThat(row.propertyType()).contains(propertyType);
        assertThat(row.propertyName()).contains(propertyName);
        assertThat(row.propertyCaption()).contains(propertyCaption);
        assertThat(row.dataType()).contains(dbType);
        assertThat(row.description()).contains(description);
        assertThat(row.propertyContentType()).contains(propertyContentType);
    }

    private void checkMdSchemaSetsResponseRow(
        MdSchemaSetsResponseRow row,
        String catalogName,
        String schemaName,
        String cubeName,
        String setName,
        ScopeEnum scope,
        String description,
        Optional<String> oExpression,
        String dimension,
        String setCaption,
        Optional<String> oSetDisplayFolder,
        SetEvaluationContextEnum setEvaluationContext
    ) {
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains(catalogName);
        assertThat(row.schemaName()).contains(schemaName);
        assertThat(row.cubeName()).contains(cubeName);
        assertThat(row.setName()).contains(setName);
        assertThat(row.scope()).contains(scope);
        assertThat(row.description()).contains(description);
        assertThat(row.expression()).isEqualTo(oExpression);
        assertThat(row.dimension()).contains(dimension);
        assertThat(row.setCaption()).contains(setCaption);
        assertThat(row.setDisplayFolder()).isEqualTo(oSetDisplayFolder);
        assertThat(row.setEvaluationContext()).contains(setEvaluationContext);
    }

    private static <N> Answer<N[]> setupDummyArrayAnswer(N... values) {

        Answer<N[]> answer = new Answer<>() {
            @Override
            public N[] answer(InvocationOnMock invocation) throws Throwable {
                return values;
            }
        };
        return answer;
    }

}
