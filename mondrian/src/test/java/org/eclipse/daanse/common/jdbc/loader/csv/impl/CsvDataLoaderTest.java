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
package org.eclipse.daanse.common.jdbc.loader.csv.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.osgi.test.common.dictionary.Dictionaries.dictionaryOf;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

import javax.sql.DataSource;

import org.eclipse.daanse.common.io.fs.watcher.api.FileSystemWatcherWhiteboardConstants;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.dialect.api.DialectResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(MockitoExtension.class)
@RequireConfigurationAdmin
class CsvDataLoaderTest {

	public static final String COMPONENT_NAME = "org.eclipse.daanse.common.jdbc.loader.csv.impl.CsvDataLoader";
	@TempDir(cleanup = CleanupMode.ON_SUCCESS)
	Path path;

	@Mock
	DialectResolver dialectResolver;
	@Mock
	Dialect dialect;
	@Mock
	Connection connection;
	@Mock
	Statement statement;
	@Mock
	PreparedStatement preparedStatement;
	@Mock
	DataSource dataSource;

	@InjectBundleContext
	BundleContext bc;

	@InjectService
	ConfigurationAdmin ca;

	Configuration conf;

	@BeforeEach
	void beforeEach() throws SQLException, IOException {

		when(dialectResolver.resolve(any(DataSource.class))).thenReturn(Optional.of(dialect));
		when(dataSource.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(any())).thenReturn(preparedStatement);
		when(connection.createStatement()).thenReturn(statement);
		bc.registerService(DialectResolver.class, dialectResolver, dictionaryOf("dr", "1"));
		bc.registerService(DataSource.class, dataSource, dictionaryOf("ds", "1"));
	}

	private void copy(String... files) throws IOException {

		for (String file : files) {
			InputStream is = bc.getBundle().getResource(file).openConnection().getInputStream();
			Files.copy(is, path.resolve(file));
		}
	}

	@AfterEach
	void afterEach() throws IOException {
		if (conf != null) {
			conf.delete();
		}
	}

	private void setupCsvDataLoadServiceImpl(Boolean lineSeparatorDetectionEnabled, String nullValue,
			Character quoteEscape, Character quote, String delimiter, String encoding, Boolean quoteDetectionEnabled,
			Boolean clearTableBeforeLoad, String stringPath) throws IOException {
		conf = ca.getFactoryConfiguration(CsvDataLoaderTest.COMPONENT_NAME, "1", "?");
		Dictionary<String, Object> dict = new Hashtable<>();
		if (lineSeparatorDetectionEnabled != null) {

			dict.put("lineSeparatorDetectionEnabled", lineSeparatorDetectionEnabled);
		}
		if (nullValue != null) {

			dict.put("nullValue", nullValue);
		}
		if (quoteEscape != null) {
			dict.put("quoteEscape", quoteEscape);
		}
		if (quote != null) {
			dict.put("quote", quote);
		}
		if (delimiter != null) {
			dict.put("delimiter", delimiter);
		}
		if (encoding != null) {
			dict.put("encoding", encoding);
		}
		if (quoteDetectionEnabled != null) {
			dict.put("quoteDetectionEnabled", quoteDetectionEnabled);
		}
		if (quoteDetectionEnabled != null) {
			dict.put("clearTableBeforeLoad", quoteDetectionEnabled);
		}
		dict.put(FileSystemWatcherWhiteboardConstants.FILESYSTEM_WATCHER_PATH, stringPath != null ? path.resolve(stringPath).toAbsolutePath().toString()
				: path.toAbsolutePath().toString());
		dict.put(FileSystemWatcherWhiteboardConstants.FILESYSTEM_WATCHER_RECURSIVE, true);
		conf.update(dict);
	}

	@Test
	void testBatch() throws IOException, URISyntaxException, SQLException, InterruptedException {
		Path p = path.resolve("csv");
		Files.createDirectories(p);

		setupCsvDataLoadServiceImpl(true, "NULL", '\\', '\"', ",", "UTF-8", true, true, "csv");
		when(dialect.supportBatchOperations()).thenReturn(true);
		when(dialect.quoteIdentifier(null, "test")).thenReturn("test");
		Thread.sleep(200);
		copy("csv/test.csv");
		Thread.sleep(200);
		ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

		verify(connection, (times(1))).prepareStatement(stringCaptor.capture());
		assertThat(stringCaptor.getValue()).contains("test");

		verify(preparedStatement, (times(1))).executeBatch();
	}

	@Test
	void testWithoutBach() throws IOException, URISyntaxException, SQLException, InterruptedException {
		Path p = path.resolve("csv");
		Files.createDirectories(p);

		setupCsvDataLoadServiceImpl(true, "NULL", '\\', '\"', ",", "UTF-8", true, true, "csv");
		when(dialect.supportBatchOperations()).thenReturn(false);
		when(dialect.quoteIdentifier(null, "test")).thenReturn("test");
		Thread.sleep(200);
		copy("csv/test.csv");
		Thread.sleep(200);
		ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<Boolean> booleanCaptor = ArgumentCaptor.forClass(Boolean.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<Integer> integerCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Double> doubleCaptor = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<Short> shortCaptor = ArgumentCaptor.forClass(Short.class);
		ArgumentCaptor<Timestamp> timestampCaptor = ArgumentCaptor.forClass(Timestamp.class);

		verify(connection, (times(1))).prepareStatement(stringCaptor.capture());
		assertThat(stringCaptor.getValue()).contains("test");
        verify(statement, (times(2))).execute(stringCaptor.capture());
		assertThat(stringCaptor.getValue()).contains("VARCHAR(40)");
		verify(preparedStatement, (times(4))).setInt(integerCaptor.capture(), integerCaptor.capture());
		verify(preparedStatement, (times(2))).setLong(integerCaptor.capture(), longCaptor.capture());
		verify(preparedStatement, (times(2))).setBoolean(integerCaptor.capture(), booleanCaptor.capture());
		verify(preparedStatement, (times(2))).setDate(integerCaptor.capture(), dateCaptor.capture());
		verify(preparedStatement, (times(2))).setDouble(integerCaptor.capture(), doubleCaptor.capture());
		verify(preparedStatement, (times(2))).setShort(integerCaptor.capture(), shortCaptor.capture());
		verify(preparedStatement, (times(2))).setTimestamp(integerCaptor.capture(), timestampCaptor.capture());
		verify(preparedStatement, (times(2))).setString(integerCaptor.capture(), stringCaptor.capture());

		verify(preparedStatement, (times(2))).executeUpdate();
	}

	@Test
	void testWithoutBacSubDir() throws IOException, URISyntaxException, SQLException, InterruptedException {
		Path p = path.resolve("csv/schema1");
		Files.createDirectories(p);

		setupCsvDataLoadServiceImpl(true, "NULL", '\\', '\"', ",", "UTF-8", true, true, "csv");
		when(dialect.supportBatchOperations()).thenReturn(false);
		when(dialect.quoteIdentifier("schema1", "test1")).thenReturn("schema1.test1");
		Thread.sleep(200);
		copy("csv/schema1/test1.csv");
		Thread.sleep(200);
		ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<Boolean> booleanCaptor = ArgumentCaptor.forClass(Boolean.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<Integer> integerCaptor = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Double> doubleCaptor = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<Short> shortCaptor = ArgumentCaptor.forClass(Short.class);
		ArgumentCaptor<Timestamp> timestampCaptor = ArgumentCaptor.forClass(Timestamp.class);

		verify(connection, (times(1))).prepareStatement(stringCaptor.capture());
		assertThat(stringCaptor.getValue()).contains("schema1.test1");
        verify(statement, (times(3))).execute(stringCaptor.capture());
        assertThat(stringCaptor.getValue()).contains("VARCHAR(40)");
		verify(preparedStatement, (times(4))).setInt(integerCaptor.capture(), integerCaptor.capture());
		verify(preparedStatement, (times(2))).setLong(integerCaptor.capture(), longCaptor.capture());
		verify(preparedStatement, (times(2))).setBoolean(integerCaptor.capture(), booleanCaptor.capture());
		verify(preparedStatement, (times(2))).setDate(integerCaptor.capture(), dateCaptor.capture());
		verify(preparedStatement, (times(2))).setDouble(integerCaptor.capture(), doubleCaptor.capture());
		verify(preparedStatement, (times(2))).setShort(integerCaptor.capture(), shortCaptor.capture());
		verify(preparedStatement, (times(2))).setTimestamp(integerCaptor.capture(), timestampCaptor.capture());
		verify(preparedStatement, (times(2))).setString(integerCaptor.capture(), stringCaptor.capture());

		verify(preparedStatement, (times(2))).executeUpdate();
	}

}
