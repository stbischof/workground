package org.opencube.junit5.context;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.statistics.api.StatisticsProvider;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompilerFactory;
import org.eclipse.daanse.olap.calc.base.compiler.BaseExpressionCompilerFactory;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SchemaImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.sample.foodmart.record.FoodMartRecordDbMappingSchemaProvider;
import org.opencube.junit5.Constants;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public abstract class AbstractTestContext implements Context {

	private Dialect dialect;
	private StatisticsProvider statisticsProvider = new StatisticsProvider() {
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
	private DataSource dataSource;

	private ExpressionCompilerFactory expressionCompilerFactory = new BaseExpressionCompilerFactory();

	public AbstractTestContext(DataSource dataSource) {
		this.dataSource = dataSource;
		try (Connection connection = dataSource.getConnection()) {
			dialect = createDialect(connection);
		} catch (SQLException e) {
			new RuntimeException(e);
		}

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
	public Optional<String> getDescription() {
		return Optional.empty();
	}

	@Override
	public List<DatabaseMappingSchemaProvider> getDatabaseMappingSchemaProviders() {
		DatabaseMappingSchemaProvider dbp = new FoodMartRecordDbMappingSchemaProvider();
		return List.of(dbp);
	}
    public SchemaImpl read(InputStream inputStream) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(SchemaImpl.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (SchemaImpl) jaxbUnmarshaller.unmarshal(inputStream);

    }
	@Override
	public ExpressionCompilerFactory getExpressionCompilerFactory() {
		return expressionCompilerFactory;
	}

	abstract Dialect createDialect(Connection connection);

	@Override
	public org.eclipse.daanse.olap.api.Connection getConnection() {
		return null;
	}

}
