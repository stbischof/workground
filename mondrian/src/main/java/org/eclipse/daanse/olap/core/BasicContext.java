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

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.dialect.api.DialectResolver;
import org.eclipse.daanse.db.statistics.api.StatisticsProvider;
import org.eclipse.daanse.mdx.parser.api.MdxParserProvider;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.ConnectionProps;
import org.eclipse.daanse.olap.api.function.FunctionService;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompilerFactory;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.osgi.namespace.unresolvable.UnresolvableNamespace;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.util.converter.Converter;
import org.osgi.util.converter.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aQute.bnd.metatype.annotations.Designate;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapConnectionPropsR;
import mondrian.rolap.RolapResultShepherd;
import mondrian.rolap.agg.AggregationManager;
import mondrian.server.NopEventBus;

@Designate(ocd = BasicContextConfig.class, factory = true)
@Component(service = Context.class, scope = ServiceScope.SINGLETON)
public class BasicContext extends AbstractBasicContext {

	public static final String PID = "org.eclipse.daanse.olap.core.BasicContext";

	public static final String REF_NAME_DIALECT_RESOLVER = "dialectResolver";
	public static final String REF_NAME_STATISTICS_PROVIDER = "statisticsProvider";
	public static final String REF_NAME_DATA_SOURCE = "dataSource";
//	public static final String REF_NAME_QUERY_PROVIDER = "queryProvier";
	public static final String REF_NAME_DB_MAPPING_SCHEMA_PROVIDER = "databaseMappingSchemaProviders";
    public static final String REF_NAME_MDX_PARSER_PROVIDER = "mdxParserProvider";
	public static final String REF_NAME_EXPRESSION_COMPILER_FACTORY = "expressionCompilerFactory";

	private static final String ERR_MSG_DIALECT_INIT = "Could not activate context. Error on initialisation of Dialect";

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicContext.class);
	private static final Converter CONVERTER = Converters.standardConverter();

	@Reference(name = REF_NAME_DATA_SOURCE, target = UnresolvableNamespace.UNRESOLVABLE_FILTER)
	private DataSource dataSource = null;

	@Reference(name = REF_NAME_DIALECT_RESOLVER)
	private DialectResolver dialectResolver = null;

	@Reference(name = REF_NAME_STATISTICS_PROVIDER, target = UnresolvableNamespace.UNRESOLVABLE_FILTER)
	private StatisticsProvider statisticsProvider = null;

	@Reference(name = REF_NAME_DB_MAPPING_SCHEMA_PROVIDER, target = UnresolvableNamespace.UNRESOLVABLE_FILTER, cardinality = ReferenceCardinality.AT_LEAST_ONE)
	private List<DatabaseMappingSchemaProvider> databaseMappingSchemaProviders;

	@Reference(name = REF_NAME_EXPRESSION_COMPILER_FACTORY, target = UnresolvableNamespace.UNRESOLVABLE_FILTER)
	private ExpressionCompilerFactory expressionCompilerFactory = null;

	@Reference
	private FunctionService functionService;

	private BasicContextConfig config;

	private Dialect dialect = null;

	private Semaphore queryLimitSemaphore;

    @Reference(name = REF_NAME_MDX_PARSER_PROVIDER, target = UnresolvableNamespace.UNRESOLVABLE_FILTER)
    private MdxParserProvider mdxParserProvider;

	@Activate
	public void activate(Map<String, Object> coniguration) throws Exception {
		activate1(CONVERTER.convert(coniguration).to(BasicContextConfig.class));
	}

	public void activate1(BasicContextConfig configuration) throws Exception {

        this.config = configuration;
		this.monitor = new NopEventBus();
		//TODO: rm was just for testing
		statisticsProvider=new StatisticsProvider(){
	        @Override
	        public void initialize(DataSource dataSource, Dialect dialect) {

	        }

	        @Override
	        public long getTableCardinality(String catalog, String schema, String table) {
	            return 0;
	        }

	        @Override
	        public long getQueryCardinality(String sql) {
	            return 0;
	        }

	        @Override
	        public long getColumnCardinality(String catalog, String schema, String table, String column) {
	            return 0;
	        }
	    };

		queryLimitSemaphore = new Semaphore(config.queryLimit());

		try (Connection connection = dataSource.getConnection()) {
			Optional<Dialect> optionalDialect = dialectResolver.resolve(dataSource);
			dialect = optionalDialect.orElseThrow(() -> new Exception(ERR_MSG_DIALECT_INIT));
		}
		statisticsProvider.initialize(dataSource, getDialect());
		shepherd = new RolapResultShepherd(config.rolapConnectionShepherdThreadPollingInterval(),
				config.rolapConnectionShepherdThreadPollingIntervalUnit(), config.rolapConnectionShepherdNbThreads());
		aggMgr = new AggregationManager(this);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("new MondrianServer: id=" + getId());
		}
	}

	@Deactivate
	public void deactivate(Map<String, Object> coniguration) throws Exception {
		shutdown();
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public Dialect getDialect() {
		return dialect;
	}

	@Override
	public StatisticsProvider getStatisticsProvider() {
		return statisticsProvider;
	}

	@Override
	public String getName() {
		return config.name();
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.ofNullable(config.description());
	}

	@Override
	public List<DatabaseMappingSchemaProvider> getDatabaseMappingSchemaProviders() {
		return databaseMappingSchemaProviders;
	}
//
//	@Override
//	public QueryProvider getQueryProvider() {
//		return queryProvider;
//	}

	@Override
	public ExpressionCompilerFactory getExpressionCompilerFactory() {
		return expressionCompilerFactory;
	}

	@Override
	public org.eclipse.daanse.olap.api.Connection getConnection() {
		return getConnection(new RolapConnectionPropsR());
	}

	@Override
	public org.eclipse.daanse.olap.api.Connection getConnection(ConnectionProps props) {
		return new RolapConnection(this, props);
	}

	@Override
	public Scenario createScenario() {
        return getConnection().createScenario();
	}

	@Override
	public BasicContextConfig getConfig() {
		return config;
	}

	@Override
	public Semaphore getQueryLimitSemaphore() {
		return queryLimitSemaphore;
	}

	@Override
	public Optional<Map<Object, Object>> getSqlMemberSourceValuePool() {
		return Optional.empty(); //Caffein Cache is an option
	}

	@Override
    public FunctionService getFunctionService() {
        return functionService;
    }

    @Override
    public MdxParserProvider getMdxParserProvider() {
        return mdxParserProvider;
    }
}
