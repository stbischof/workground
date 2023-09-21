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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.eclipse.daanse.olap.xmla.bridge.ContextsSupplyerImpl;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRestrictions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import javax.sql.DataSource;

@ExtendWith(MockitoExtension.class)
class DbSchemaDiscoverServiceTest {

	@Mock
	private Context context1;

	@Mock
	private Context context2;

    @Mock
    private DatabaseMappingSchemaProvider dmsp1;

    @Mock
    private DatabaseMappingSchemaProvider dmsp2;

    @Mock
    private MappingSchema schema1;

    @Mock
    private MappingSchema schema2;

    @Mock
    private MappingRole role1;

    @Mock
    private MappingRole role2;

    @Mock
    private MappingCube cube1;

    @Mock
    private MappingCube cube2;

    @Mock
    private MappingPrivateDimension dimension1;

    @Mock
    private MappingPrivateDimension dimension2;

    @Mock
    private MappingHierarchy hierarchy1;

    @Mock
    private MappingHierarchy hierarchy2;

    @Mock
    private MappingMeasure measure;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    private DBSchemaDiscoverService service;

	private ContextsSupplyerImpl cls;

	@BeforeEach
	void setup() {
		/*
		 * empty list, but override with:
		 * when(cls.get()).thenReturn(List.of(context1,context2));`
		 */

		cls = Mockito.spy(new ContextsSupplyerImpl(List.of()));
		service = new DBSchemaDiscoverService(cls);
	}

	@Test
	void dbSchemaCatalogs() {
		when(cls.get()).thenReturn(List.of(context1,context2));

		DbSchemaCatalogsRequest request = mock(DbSchemaCatalogsRequest.class);
		DbSchemaCatalogsRestrictions restrictions = mock(DbSchemaCatalogsRestrictions.class);

		when(request.restrictions()).thenReturn(restrictions);
		when(restrictions.catalogName()).thenReturn(Optional.of("foo"));

        when(schema1.name()).thenReturn("schema1Name");
        when(schema1.description()).thenReturn("schema1Description");

        when(schema2.name()).thenReturn("schema2Name");
        when(schema2.description()).thenReturn("schema2Description");

        when(role1.name()).thenReturn("role1");
        when(role2.name()).thenReturn("role2");

        when(schema1.roles()).thenAnswer(setupDummyListAnswer(role1, role2));
        when(schema2.roles()).thenAnswer(setupDummyListAnswer(role1, role2));

        when(dmsp1.get()).thenReturn(schema1);
        when(dmsp2.get()).thenReturn(schema2);

		when(context1.getName()).thenReturn("bar");
		when(context2.getName()).thenReturn("foo");
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<DbSchemaCatalogsResponseRow> rows = service.dbSchemaCatalogs(request);
		verify(context1,times(1)).getName();
		verify(context2,times(1)).getName();
        assertThat(rows).isNotNull().hasSize(2);
        DbSchemaCatalogsResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("schema1Name");
        assertThat(row.description()).contains("schema1Description");
        assertThat(row.roles()).contains("role1,role2");

        row = rows.get(1);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("schema2Name");
        assertThat(row.description()).contains("schema2Description");
        assertThat(row.roles()).contains("role1,role2");

	}

    @Test
    void dbSchemaColumns() {
        when(cls.get()).thenReturn(List.of(context1,context2));

        DbSchemaColumnsRequest request = mock(DbSchemaColumnsRequest.class);
        DbSchemaColumnsRestrictions restrictions = mock(DbSchemaColumnsRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.tableCatalog()).thenReturn(Optional.of("foo"));

        when(schema1.name()).thenReturn("schema1Name");

        when(schema2.name()).thenReturn("schema2Name");

        when(hierarchy1.name()).thenReturn("hierarchy1Name");
        when(hierarchy2.name()).thenReturn("hierarchy2Name");

        when(dimension1.hierarchies()).thenAnswer(setupDummyListAnswer(hierarchy1, hierarchy2));

        when(measure.name()).thenReturn("measureName");

        when(cube1.name()).thenReturn("cube1Name");
        when(cube1.dimensionUsageOrDimensions()).thenAnswer(setupDummyListAnswer(dimension1, dimension2));
        when(cube1.measures()).thenAnswer(setupDummyListAnswer(measure));

        when(cube2.name()).thenReturn("cube2Name");
        when(cube2.dimensionUsageOrDimensions()).thenAnswer(setupDummyListAnswer(dimension1, dimension2));
        when(cube2.measures()).thenAnswer(setupDummyListAnswer(measure));

        when(schema1.cubes()).thenAnswer(setupDummyListAnswer(cube1, cube2));
        when(schema2.cubes()).thenAnswer(setupDummyListAnswer(cube1, cube2));

        when(dmsp1.get()).thenReturn(schema1);
        when(dmsp2.get()).thenReturn(schema2);

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<DbSchemaColumnsResponseRow> rows = service.dbSchemaColumns(request);
        verify(context1,times(1)).getName();
        assertThat(rows).isNotNull().hasSize(20);
        DbSchemaColumnsResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.tableCatalog()).contains("foo");
        assertThat(row.tableSchema()).contains("schema1Name");

        assertThat(rows)
            .extracting(DbSchemaColumnsResponseRow::columnName)
            .contains(Optional.of("hierarchy1Name:(All)!NAME"))
            .contains(Optional.of("hierarchy1Name:(All)!UNIQUE_NAME"))
            .contains(Optional.of("hierarchy2Name:(All)!NAME"))
            .contains(Optional.of("hierarchy2Name:(All)!UNIQUE_NAME"))
            .contains(Optional.of("Measures:measureName"));
    }

    @Test
    void dbSchemaProviderTypes() {

        DbSchemaProviderTypesRequest request = mock(DbSchemaProviderTypesRequest.class);
        DbSchemaProviderTypesRestrictions restrictions = mock(DbSchemaProviderTypesRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.dataType()).thenReturn(Optional.empty());

        List<DbSchemaProviderTypesResponseRow> rows = service.dbSchemaProviderTypes(request);
        assertThat(rows).isNotNull().hasSize(6);
        DbSchemaProviderTypesResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.typeName()).contains("INTEGER");
        assertThat(row.dataType()).contains(LevelDbTypeEnum.DBTYPE_I4);
        assertThat(row.columnSize()).contains(8);
        assertThat(row.isNullable()).contains(true);
        assertThat(row.unsignedAttribute()).contains(false);
        assertThat(row.fixedPrecScale()).contains(false);
        assertThat(row.autoUniqueValue()).contains(false);
        assertThat(row.isLong()).contains(false);
        assertThat(row.bestMatch()).contains(true);

        row = rows.get(1);
        assertThat(row).isNotNull();
        assertThat(row.typeName()).contains("DOUBLE");
        assertThat(row.dataType()).contains(LevelDbTypeEnum.DBTYPE_R8);
        assertThat(row.columnSize()).contains(16);
        assertThat(row.isNullable()).contains(true);
        assertThat(row.unsignedAttribute()).contains(false);
        assertThat(row.fixedPrecScale()).contains(false);
        assertThat(row.autoUniqueValue()).contains(false);
        assertThat(row.isLong()).contains(false);
        assertThat(row.bestMatch()).contains(true);

        row = rows.get(2);
        assertThat(row).isNotNull();
        assertThat(row.typeName()).contains("CURRENCY");
        assertThat(row.dataType()).contains(LevelDbTypeEnum.DBTYPE_CY);
        assertThat(row.columnSize()).contains(8);
        assertThat(row.isNullable()).contains(true);
        assertThat(row.unsignedAttribute()).contains(false);
        assertThat(row.fixedPrecScale()).contains(false);
        assertThat(row.autoUniqueValue()).contains(false);
        assertThat(row.isLong()).contains(false);
        assertThat(row.bestMatch()).contains(true);

        row = rows.get(3);
        assertThat(row).isNotNull();
        assertThat(row.typeName()).contains("BOOLEAN");
        assertThat(row.dataType()).contains(LevelDbTypeEnum.DBTYPE_BOOL);
        assertThat(row.columnSize()).contains(1);
        assertThat(row.isNullable()).contains(true);
        assertThat(row.unsignedAttribute()).contains(false);
        assertThat(row.fixedPrecScale()).contains(false);
        assertThat(row.autoUniqueValue()).contains(false);
        assertThat(row.isLong()).contains(false);
        assertThat(row.bestMatch()).contains(true);

        row = rows.get(4);
        assertThat(row).isNotNull();
        assertThat(row.typeName()).contains("LARGE_INTEGER");
        assertThat(row.dataType()).contains(LevelDbTypeEnum.DBTYPE_I8);
        assertThat(row.columnSize()).contains(16);
        assertThat(row.isNullable()).contains(true);
        assertThat(row.unsignedAttribute()).contains(false);
        assertThat(row.fixedPrecScale()).contains(false);
        assertThat(row.autoUniqueValue()).contains(false);
        assertThat(row.isLong()).contains(false);
        assertThat(row.bestMatch()).contains(true);

        row = rows.get(5);
        assertThat(row).isNotNull();
        assertThat(row.typeName()).contains("STRING");
        assertThat(row.dataType()).contains(LevelDbTypeEnum.DBTYPE_WSTR);
        assertThat(row.columnSize()).contains(255);
        assertThat(row.literalPrefix()).contains("\"");
        assertThat(row.literalSuffix()).contains("\"");
        assertThat(row.isNullable()).contains(true);
        assertThat(row.unsignedAttribute()).isEmpty();
        assertThat(row.fixedPrecScale()).contains(false);
        assertThat(row.autoUniqueValue()).contains(false);
        assertThat(row.isLong()).contains(false);
        assertThat(row.bestMatch()).contains(true);
    }

    @Test
    void dbSchemaSchemata() {
        when(cls.get()).thenReturn(List.of(context1,context2));

        DbSchemaSchemataRequest request = mock(DbSchemaSchemataRequest.class);
        DbSchemaSchemataRestrictions restrictions = mock(DbSchemaSchemataRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn("foo");

        when(schema1.name()).thenReturn("schema1Name");

        when(schema2.name()).thenReturn("schema2Name");

        when(dmsp1.get()).thenReturn(schema1);
        when(dmsp2.get()).thenReturn(schema2);

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<DbSchemaSchemataResponseRow> rows = service.dbSchemaSchemata(request);
        verify(context1,times(1)).getName();
        assertThat(rows).isNotNull().hasSize(2);
        DbSchemaSchemataResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("foo");
        assertThat(row.schemaName()).contains("schema1Name");

        assertThat(rows)
            .extracting(DbSchemaSchemataResponseRow::schemaName)
            .contains("schema1Name")
            .contains("schema2Name");
    }

    @Test
    void dbSchemaSourceTables() throws SQLException {
        when(cls.get()).thenReturn(List.of(context1,context2));

        DbSchemaSourceTablesRequest request = mock(DbSchemaSourceTablesRequest.class);
        DbSchemaSourceTablesRestrictions restrictions = mock(DbSchemaSourceTablesRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.catalogName()).thenReturn(Optional.of("foo"));
        when(restrictions.tableType()).thenReturn(TableTypeEnum.TABLE);

        when(schema1.name()).thenReturn("schema1Name");

        when(schema2.name()).thenReturn("schema2Name");

        when(hierarchy1.name()).thenReturn("hierarchy1Name");
        when(hierarchy2.name()).thenReturn("hierarchy2Name");

        when(dimension1.hierarchies()).thenAnswer(setupDummyListAnswer(hierarchy1, hierarchy2));

        when(measure.name()).thenReturn("measureName");

        when(cube1.name()).thenReturn("cube1Name");
        when(cube1.dimensionUsageOrDimensions()).thenAnswer(setupDummyListAnswer(dimension1, dimension2));
        when(cube1.measures()).thenAnswer(setupDummyListAnswer(measure));

        when(cube2.name()).thenReturn("cube2Name");
        when(cube2.dimensionUsageOrDimensions()).thenAnswer(setupDummyListAnswer(dimension1, dimension2));
        when(cube2.measures()).thenAnswer(setupDummyListAnswer(measure));

        when(schema1.cubes()).thenAnswer(setupDummyListAnswer(cube1, cube2));
        when(schema2.cubes()).thenAnswer(setupDummyListAnswer(cube1, cube2));

        when(dmsp1.get()).thenReturn(schema1);
        when(dmsp2.get()).thenReturn(schema2);

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("TABLE_CAT")).thenReturn("tableCatalog");
        when(resultSet.getString("TABLE_SCHEM")).thenReturn("tableSchema");
        when(resultSet.getString("TABLE_NAME")).thenReturn("tableName");
        when(resultSet.getString("TABLE_TYPE")).thenReturn("TABLE");

        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
        when(metaData.getTables(any(), any(), any(), any())).thenReturn(resultSet);
        when(connection.getMetaData()).thenReturn(metaData);
        when(dataSource.getConnection()).thenReturn(connection);

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getDataSource()).thenReturn(dataSource);
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<DbSchemaSourceTablesResponseRow> rows = service.dbSchemaSourceTables(request);
        verify(context1,times(1)).getName();
        assertThat(rows).isNotNull().hasSize(20);
        DbSchemaSourceTablesResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.catalogName()).contains("foo");
        assertThat(row.schemaName()).contains("schema1Name");

        assertThat(rows)
            .extracting(DbSchemaSourceTablesResponseRow::tableName)
            .contains("hierarchy1Name:(All)!NAME")
            .contains("hierarchy1Name:(All)!UNIQUE_NAME")
            .contains("hierarchy2Name:(All)!NAME")
            .contains("hierarchy2Name:(All)!UNIQUE_NAME")
            .contains("Measures:measureName");
    }
    private static  <N> Answer<List<N>> setupDummyListAnswer(N... values) {
        final List<N> someList = new ArrayList<>(Arrays.asList(values));

        Answer<List<N>> answer = new Answer<>() {
            @Override
            public List<N> answer(InvocationOnMock invocation) throws Throwable {
                return someList;
            }
        };
        return answer;
    }


}
