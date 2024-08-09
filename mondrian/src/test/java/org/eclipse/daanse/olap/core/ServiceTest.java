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
package org.eclipse.daanse.olap.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.osgi.test.common.dictionary.Dictionaries.dictionaryOf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.dialect.api.DialectResolver;
import org.eclipse.daanse.db.statistics.api.StatisticsProvider;
import org.eclipse.daanse.mdx.parser.api.MdxParserProvider;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompilerFactory;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.Configuration;
import org.osgi.test.assertj.servicereference.ServiceReferenceAssert;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.config.InjectConfiguration;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;

@ExtendWith(ConfigurationExtension.class)
@ExtendWith(MockitoExtension.class)
class ServiceTest {

	private static final String TARGET_EXT = ".target";
	@InjectBundleContext
	BundleContext bc;
	@Mock
	Dialect dialect;

	@Mock
	DialectResolver dialectResolver;

	@Mock
	DataSource dataSource;

	@Mock
	Connection connection;

	@Mock
	StatisticsProvider statisticsProvider;

//    @Mock
//    QueryProvider queryProvider;
//
	@Mock
	DatabaseMappingSchemaProvider databaseMappingSchemaProvider;

	@Mock
	ExpressionCompilerFactory expressionCompilerFactory;
	
	@Mock
	MdxParserProvider mdxParserProvider;
	
	@Mock
	MappingSchema mappingSchema;

	@BeforeEach
	public void setup() throws SQLException {

	}

	@Test
    public void serviceExists(
            @InjectConfiguration(withFactoryConfig = @WithFactoryConfiguration(factoryPid = BasicContext.PID, name = "name1")) Configuration c,
            @InjectService(cardinality = 0) ServiceAware<Context> saContext) throws Exception {

        when(dataSource.getConnection()).thenReturn(connection);
        when(dialectResolver.resolve((DataSource)any())).thenReturn(Optional.of(dialect));
        when(databaseMappingSchemaProvider.get()).thenReturn(mappingSchema);
        when(mappingSchema.name()).thenReturn("schemaName");

        assertThat(saContext).isNotNull()
                .extracting(ServiceAware::size)
                .isEqualTo(0);

        ServiceReferenceAssert.assertThat(saContext.getServiceReference())
                .isNull();

        bc.registerService(DataSource.class, dataSource, dictionaryOf("ds", "1"));
        bc.registerService(DialectResolver.class, dialectResolver, dictionaryOf("dr", "2"));
        bc.registerService(StatisticsProvider.class, statisticsProvider, dictionaryOf("sp", "3"));
        bc.registerService(ExpressionCompilerFactory.class, expressionCompilerFactory, dictionaryOf("ecf", "1"));
        bc.registerService(DatabaseMappingSchemaProvider.class, databaseMappingSchemaProvider, dictionaryOf("dbmsp", "1"));
        bc.registerService(MdxParserProvider.class, mdxParserProvider, dictionaryOf("parser", "1"));
        //        bc.registerService(QueryProvider.class, queryProvider, dictionaryOf("qp", "1"));

        Dictionary<String, Object> props = new Hashtable<>();

        props.put(BasicContext.REF_NAME_DATA_SOURCE + TARGET_EXT, "(ds=1)");
        props.put(BasicContext.REF_NAME_DIALECT_RESOLVER + TARGET_EXT, "(dr=2)");
        props.put(BasicContext.REF_NAME_STATISTICS_PROVIDER + TARGET_EXT, "(sp=3)");
        props.put(BasicContext.REF_NAME_EXPRESSION_COMPILER_FACTORY + TARGET_EXT, "(ecf=1)");
        props.put(BasicContext.REF_NAME_DB_MAPPING_CATALOG_SUPPLIER + TARGET_EXT, "(dbmsp=1)");
        props.put(BasicContext.REF_NAME_MDX_PARSER_PROVIDER + TARGET_EXT, "(parser=1)");
        //        props.put(BasicContext.REF_NAME_QUERY_PROVIDER+ TARGET_EXT, "(qp=1)");

        String theName = "theName";
        String theDescription = "theDescription";

        props.put("name", theName);
        props.put("description", theDescription);
        c.update(props);
        Context ctx = saContext.waitForService(1000000);

        assertThat(saContext).isNotNull()
                .extracting(ServiceAware::size)
                .isEqualTo(1);
        assertThat(ctx.getConnection()).isNotNull();
        assertThat(ctx).satisfies(x -> {
            assertThat(x.getName()).isEqualTo(theName);
            assertThat(x.getDescription()
                    .isPresent());
            assertThat(x.getDescription()
                    .get()).isEqualTo(theDescription);
            assertThat(x.getDataSource()).isEqualTo(dataSource);
            assertThat(x.getDialect()).isEqualTo(dialect);
            //assertThat(x.getStatisticsProvider()).isEqualTo(statisticsProvider);
            assertThat(x.getExpressionCompilerFactory()).isEqualTo(expressionCompilerFactory);
            assertThat(x.getDatabaseMappingSchemaProviders()).hasSize(1).contains(databaseMappingSchemaProvider);
            //            assertThat(x.getQueryProvider()).isEqualTo(queryProvider);
        });

    }
}
