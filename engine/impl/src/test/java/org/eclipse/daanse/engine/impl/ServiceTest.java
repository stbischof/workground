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
package org.eclipse.daanse.engine.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.osgi.test.common.dictionary.Dictionaries.dictionaryOf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.statistics.api.StatisticsProvider;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompilerFactory;
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
    DataSource dataSource;

    @Mock
    Connection connection;

    @Mock
    StatisticsProvider statisticsProvider;
    
//    @Mock
//    QueryProvider queryProvider;
//    
//  @Mock
//  DataBaseMappingSchemaProvider dataBaseMappingSchemaProvider;
    
	@Mock
	ExpressionCompilerFactory expressionCompilerFactory;
    
    

    @BeforeEach
    public void setup() throws SQLException {

    }

    @Test
    public void serviceExists(
            @InjectConfiguration(withFactoryConfig = @WithFactoryConfiguration(factoryPid = BasicContext.PID, name = "name1")) Configuration c,
            @InjectService(cardinality = 0) ServiceAware<Context> saContext) throws Exception {

        when(dataSource.getConnection()).thenReturn(connection);
        when(dialect.initialize(connection)).thenReturn(true);

        assertThat(saContext).isNotNull()
                .extracting(ServiceAware::size)
                .isEqualTo(0);

        ServiceReferenceAssert.assertThat(saContext.getServiceReference())
                .isNull();

        bc.registerService(DataSource.class, dataSource, dictionaryOf("ds", "1"));
        bc.registerService(Dialect.class, dialect, dictionaryOf("d", "2"));
        bc.registerService(StatisticsProvider.class, statisticsProvider, dictionaryOf("sp", "3"));
        bc.registerService(ExpressionCompilerFactory.class, expressionCompilerFactory, dictionaryOf("ecf", "1"));
//        bc.registerService(QueryProvider.class, queryProvider, dictionaryOf("qp", "1"));
//        bc.registerService(DataBaseMappingSchemaProvider.class, dataBaseMappingSchemaProvider, dictionaryOf("dbmsp", "1"));

        Dictionary<String, Object> props = new Hashtable<>();

        props.put(BasicContext.REF_NAME_DATA_SOURCE + TARGET_EXT, "(ds=1)");
        props.put(BasicContext.REF_NAME_DIALECT + TARGET_EXT, "(d=2)");
        props.put(BasicContext.REF_NAME_STATISTICS_PROVIDER + TARGET_EXT, "(sp=3)");
        props.put(BasicContext.REF_NAME_EXPRESSION_COMPILER_FACTORY + TARGET_EXT, "(ecf=1)");
//        props.put(BasicContext.REF_NAME_QUERY_PROVIDER+ TARGET_EXT, "(qp=1)");
//        props.put(BasicContext.REF_NAME_DB_MAPPING_SCHEMA_PROVIDER + TARGET_EXT, "(dbmsp=1)");

        String theName = "theName";
        String theDescription = "theDescription";
        props.put("name", theName);
        props.put("description", theDescription);
        c.update(props);
        Context ctx = saContext.waitForService(1000);

        assertThat(saContext).isNotNull()
                .extracting(ServiceAware::size)
                .isEqualTo(1);

        assertThat(ctx).satisfies(x -> {
            assertThat(x.getName()).isEqualTo(theName);
            assertThat(x.getDescription()
                    .isPresent()).isTrue();
            assertThat(x.getDescription()
                    .get()).isEqualTo(theDescription);
            assertThat(x.getDataSource()).isEqualTo(dataSource);
            assertThat(x.getDialect()).isEqualTo(dialect);
            assertThat(x.getStatisticsProvider()).isEqualTo(statisticsProvider);
            assertThat(x.getExpressionCompilerFactory()).isEqualTo(expressionCompilerFactory);
//            assertThat(x.getQueryProvider()).isEqualTo(queryProvider);
//            assertThat(x.getDataBaseMappingSchemaProvider()).isEqualTo(dataBaseMappingSchemaProvider);
        });

    }
}
