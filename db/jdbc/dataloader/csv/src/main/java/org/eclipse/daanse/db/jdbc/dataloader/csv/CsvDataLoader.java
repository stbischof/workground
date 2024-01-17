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
package org.eclipse.daanse.db.jdbc.dataloader.csv;

import static org.eclipse.daanse.db.jdbc.util.impl.Utils.createTable;
import static org.eclipse.daanse.db.jdbc.util.impl.Utils.parseTypeString;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.dialect.api.DialectResolver;
import org.eclipse.daanse.db.jdbc.util.impl.Column;
import org.eclipse.daanse.db.jdbc.util.impl.SqlType;
import org.eclipse.daanse.db.jdbc.util.impl.Type;
import org.eclipse.daanse.util.io.watcher.api.EventKind;
import org.eclipse.daanse.util.io.watcher.api.PathListener;
import org.eclipse.daanse.util.io.watcher.api.PathListenerConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd = CsvDataLoaderConfig.class, factory = true)
@Component(scope = ServiceScope.SINGLETON, service = PathListener.class)
@PathListenerConfig(kinds = EventKind.ENTRY_MODIFY, pattern = ".*.csv", recursive = true)
public class CsvDataLoader implements PathListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvDataLoader.class);

	private Queue<Path> initialPaths = new ArrayDeque<>();

	@Reference
	private DialectResolver dialectResolver;

	@Reference
	private DataSource dataSource;

	private CsvDataLoaderConfig config;

	private Path basePath;

	@Activate
	public void activate(CsvDataLoaderConfig config) {
		this.config = config;
	}

	@Deactivate
	public void deactivate() {
		config = null;
	}

	private void loadData(Path path) {

		if (Files.isDirectory(path)) {
			return;
		}
		if (!path.toString().endsWith(".csv")) {
			return;
		}

		Optional<Dialect> dialectOptional = this.dialectResolver.resolve(dataSource);
		if (dialectOptional.isPresent()) {
			Dialect dialect = dialectOptional.get();

			try (Connection connection = dataSource.getConnection()) {

				loadTable(connection, dialect, path);
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error("Database connection error", e);
			}
		} else {
			LOGGER.error("Database dialect did not determinate");
		}

	}

	private void loadTable(Connection connection, Dialect dialect, Path path) {
		String fileName = getFileNameWithoutExtension(path.getFileName().toString());
		LOGGER.debug("Load table {}", fileName);
		String databaseSchemaName = getDatabaseSchemaNameFromPath(path);

		if (databaseSchemaName != null && !databaseSchemaName.isEmpty()) {

			try {
				String statementCreateSchema = dialect.createSchema(databaseSchemaName, true);
				connection.createStatement().execute(statementCreateSchema);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (Boolean.TRUE.equals(config.clearTableBeforeLoad())) {
			try {

				String statementDropTable = dialect.dropTable(databaseSchemaName, fileName, true);
				connection.createStatement().execute(statementDropTable);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (!path.toFile().exists()) {

			LOGGER.warn("File does not exist - {} {}", fileName, path);
			return;
		}
		try {
            CsvReader<NamedCsvRecord> record = CsvReader.builder()
                .fieldSeparator(config.fieldSeparator())
                .quoteCharacter(config.quoteCharacter())
                .skipEmptyLines(config.skipEmptyLines())
                .commentCharacter(config.commentCharacter())
                .ignoreDifferentFieldCount(config.ignoreDifferentFieldCount())
                .ofNamedCsvRecord(path);
            NamedCsvRecord types = record.stream().findFirst().orElse(null);
            record = CsvReader.builder()
                .fieldSeparator(config.fieldSeparator())
                .quoteCharacter(config.quoteCharacter())
                .skipEmptyLines(config.skipEmptyLines())
                .commentCharacter(config.commentCharacter())
                .ignoreDifferentFieldCount(config.ignoreDifferentFieldCount())
                .ofNamedCsvRecord(path);
			List<Column> headersTypeList = getHeadersTypeList(types);
			if (!headersTypeList.isEmpty()) {
				createTable(connection, dialect, headersTypeList, databaseSchemaName, fileName);
				StringBuilder b = new StringBuilder();
				b.append("INSERT INTO ");
				b.append(dialect.quoteIdentifier(databaseSchemaName, fileName));
				b.append(" ( ");
				b.append(headersTypeList.stream().map(e -> dialect.quoteIdentifier(e.name()))
						.collect(Collectors.joining(",")));
				b.append(" ) VALUES ");
				b.append(" ( ");
				b.append(headersTypeList.stream().map(i -> "?").collect(Collectors.joining(",")));
				b.append(" ) ");

				System.out.println(b);
				try (PreparedStatement ps = connection.prepareStatement(b.toString())) {
					if (dialect.supportBatchOperations()) {
						batchExecute(connection, ps, record, headersTypeList);
					} else {
						execute(ps, record, headersTypeList);
					}
				} catch (SQLException e) {
					throw new CsvDataLoaderException("Load data error", e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getDatabaseSchemaNameFromPath(Path path) {
		Path parent = path.getParent();
		if (basePath.equals(parent)) {
			return null;
		}
		return parent.getFileName().toString();
	}

	private void execute(PreparedStatement ps, CsvReader<NamedCsvRecord> records, List<Column> columns) throws SQLException {
		boolean typeSkiped = false;
		long start = System.currentTimeMillis();
		for (NamedCsvRecord r : records) {
			if (typeSkiped) {
                for (Column column : columns) {
                    processingTypeValues(ps, column, r);
                }
                ps.executeUpdate();
                ps.clearParameters();
            }
			else {
                typeSkiped = true;
            }
		}

		LOGGER.debug("---");
		LOGGER.debug("execute time {}", (System.currentTimeMillis() - start));

	}

	private void batchExecute(Connection connection, PreparedStatement ps, CsvReader<NamedCsvRecord> record,
			List<Column> columns) throws SQLException {

		connection.setAutoCommit(false);
		long start = System.currentTimeMillis();
		int count = 0;
        for (NamedCsvRecord r : record) {
            if (count != 0) {
                columns.stream().forEach(column -> processingTypeValues(ps, column, r));
                ps.addBatch();
                ps.clearParameters();
                if (count % config.batchSize() == 0) {
                    ps.executeBatch();
                    LOGGER.debug("execute batch time {}", (System.currentTimeMillis() - start));
                    ps.getConnection().commit();
                    LOGGER.debug("execute commit time {}", (System.currentTimeMillis() - start));
                    start = System.currentTimeMillis();
                }
            }
            count++;
        }
		LOGGER.debug("---");

		ps.executeBatch();
		LOGGER.debug("execute batch time {}", (System.currentTimeMillis() - start));

		connection.commit();
		LOGGER.debug("execute commit time {}", (System.currentTimeMillis() - start));
		connection.setAutoCommit(true);
	}

	private void processingTypeValues(PreparedStatement ps, Column column, NamedCsvRecord r) {
		try {

			int i = column.index();
			String columnName = column.name();
			SqlType type = column.type();
            String field = r.getField(columnName);
			if (field == null || field.equals(config.nullValue())) {
				ps.setObject(i, null);
			} else if (type.getType().equals(Type.LONG)) {
				ps.setLong(i, field.equals("") ? 0l : Long.valueOf(field));

			} else if (type.getType().equals(Type.BOOLEAN)) {
				ps.setBoolean(i, field.equals("") ? Boolean.FALSE : Boolean.valueOf(field));

			} else if (type.getType().equals(Type.DATE)) {
				ps.setDate(i, Date.valueOf(field));

			} else if (type.getType().equals(Type.INTEGER)) {
				ps.setInt(i, field.equals("") ? 0 : Integer.valueOf(field));

			} else if (type.getType().equals(Type.NUMERIC)) {
				ps.setDouble(i, field.equals("") ? 0.0 : Double.valueOf(field));

			} else if (type.getType().equals(Type.SMALLINT)) {
				ps.setShort(i, field.equals("") ? 0 : Short.valueOf(field));

			} else if (type.getType().equals(Type.TIMESTAMP)) {
				ps.setTimestamp(i, Timestamp.valueOf(field));

			} else if (type.getType().equals(Type.STRING)) {
				ps.setString(i, field);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Column> getHeadersTypeList(NamedCsvRecord types) {
		List<Column> result = new ArrayList<>();
		int i = 1;
        if (types != null) {
            for (String header : types.getHeader()) {
                try {
                    SqlType sqlType = parseTypeString(types.getField(header));
                    Column dbc = new Column(i++, header, sqlType);
                    result.add(dbc);
                } catch (Exception e) {
                    LOGGER.error("Load data error: Create data type for Csv loader error", e);
                }
            }
        }
		return result;
	}

	private String getFileNameWithoutExtension(String fileName) {
		if (fileName.indexOf(".") > 0) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		} else {
			return fileName;
		}
	}

	private void delete(Path path) {
		String tableName = getFileNameWithoutExtension(path.getFileName().toString());
		LOGGER.debug("Drop table {}", tableName);
		Optional<Dialect> dialectOptional = dialectResolver.resolve(dataSource);
		if (dialectOptional.isPresent()) {
			Dialect dialect = dialectOptional.get();
			try (Connection connection = dataSource.getConnection()) {

				String statementText = dialect.dropTable(getDatabaseSchemaNameFromPath(path), tableName, true);
				connection.createStatement().execute(statementText);
			} catch (SQLException e) {
				LOGGER.error("Database connection error", e);
			}
		} else {
			LOGGER.error("Database dialect did not determinate");
		}
	}

	@Override
	public void handleInitialPaths(List<Path> initialPaths) {
		this.initialPaths.addAll(initialPaths);
		for (Path path : initialPaths) {

			loadData(path);
		}

	}

	@Override
	public void handlePathEvent(Path path, Kind<Path> kind) {
		System.err.println(path);
		if (Files.isDirectory(path)) {
			return;
		}
		if (kind.name().equals(StandardWatchEventKinds.ENTRY_MODIFY.name())) {
			loadData(path);
		}
		if (kind.name().equals(StandardWatchEventKinds.ENTRY_DELETE.name())) {
			delete(path);
		}
	}

	@Override
	public void handleBasePath(Path basePath) {
		this.basePath = basePath;

	}

}
