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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Cube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Hierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.PrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Role;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Schema;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.eclipse.daanse.olap.xmla.bridge.ContextsSupplyerImpl;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRestrictions;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRestrictions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

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
    private Schema schema1;

    @Mock
    private Schema schema2;

    @Mock
    private Role role1;

    @Mock
    private Role role2;

    @Mock
    private Cube cube1;

    @Mock
    private Cube cube2;

    @Mock
    private PrivateDimension dimension1;

    @Mock
    private PrivateDimension dimension2;

    @Mock
    private Hierarchy hierarchy1;

    @Mock
    private Hierarchy hierarchy2;

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

		DbSchemaCatalogsRequest request = Mockito.mock(DbSchemaCatalogsRequest.class);
		DbSchemaCatalogsRestrictions restrictions = Mockito.mock(DbSchemaCatalogsRestrictions.class);

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

        DbSchemaColumnsRequest request = Mockito.mock(DbSchemaColumnsRequest.class);
        DbSchemaColumnsRestrictions restrictions = Mockito.mock(DbSchemaColumnsRestrictions.class);

        when(request.restrictions()).thenReturn(restrictions);
        when(restrictions.tableCatalog()).thenReturn(Optional.of("foo"));

        when(schema1.name()).thenReturn("schema1Name");
        when(schema1.description()).thenReturn("schema1Description");

        when(schema2.name()).thenReturn("schema2Name");
        when(schema2.description()).thenReturn("schema2Description");

        when(role1.name()).thenReturn("role1");
        when(role2.name()).thenReturn("role2");

        when(schema1.roles()).thenAnswer(setupDummyListAnswer(role1, role2));
        when(schema2.roles()).thenAnswer(setupDummyListAnswer(role1, role2));

        when(dimension1.name()).thenReturn("dimension1Name");
        when(dimension1.description()).thenReturn("dimension1Description");
        when(dimension1.hierarchies()).thenAnswer(setupDummyListAnswer(hierarchy1, hierarchy2));

        when(cube1.name()).thenReturn("cube1Name");
        when(cube1.description()).thenReturn("cube1Description");
        when(cube1.dimensionUsageOrDimensions()).thenAnswer(setupDummyListAnswer(dimension1, dimension2));

        when(cube2.name()).thenReturn("cube2Name");
        when(cube2.description()).thenReturn("cube2Description");
        when(cube2.dimensionUsageOrDimensions()).thenAnswer(setupDummyListAnswer(dimension1, dimension2));

        when(schema1.cubes()).thenAnswer(setupDummyListAnswer(cube1, cube2));
        when(schema2.cubes()).thenAnswer(setupDummyListAnswer(cube1, cube2));

        when(dmsp1.get()).thenReturn(schema1);
        when(dmsp2.get()).thenReturn(schema2);

        when(context1.getName()).thenReturn("bar");
        when(context2.getName()).thenReturn("foo");
        when(context2.getDatabaseMappingSchemaProviders()).thenAnswer(setupDummyListAnswer(dmsp1, dmsp2));

        List<DbSchemaColumnsResponseRow> rows = service.dbSchemaColumns(request);
        verify(context1,times(1)).getName();
        verify(context2,times(1)).getName();
        assertThat(rows).isNotNull().hasSize(2);
        DbSchemaColumnsResponseRow row = rows.get(0);
        assertThat(row).isNotNull();
        assertThat(row.tableCatalog()).contains("foo");
        assertThat(row.tableSchema()).contains("schema1Name");
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
