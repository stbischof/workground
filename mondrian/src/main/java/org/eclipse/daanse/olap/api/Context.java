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
package org.eclipse.daanse.olap.api;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.db.statistics.api.StatisticsProvider;
import org.eclipse.daanse.olap.api.query.QueryProvider;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;

/**
 * The Context gives access to all resources and configurations that are needed
 * to calculate and Data Cubes
 *
 * @author stbischof
 *
 */
public interface Context {

    /**
     * Gives access to the {@link DataSource} that holds the {@link Connection}s to
     * the Database.
     *
     * @return DataSource
     */
    DataSource getDataSource();

    /**
     * Gives access to the {@link Dialect} that must be used to generate SQL querys
     * against the {@link DataSource}.
     *
     * @return DataSource
     */
    Dialect getDialect();

    /**
     * Gives access to a {@link StatisticsProvider} that is initialized with the
     * {@link DataSource} of this {@link Context}.
     *
     * @return StatisticsProvider
     */
    StatisticsProvider getStatisticsProvider();

    /**
     * Gives access to a {@link List} of {@link DatabaseMappingSchemaProvider}s.
     *
     * @return {@link List} of {@link DatabaseMappingSchemaProvider}s.
     */
    List<DatabaseMappingSchemaProvider> getDatabaseMappingSchemaProviders();

    /**
     * Gives access to a {@link QueryProvider}.
     *
     * @return {@link QueryProvider}.
     */
//    QueryProvider getQueryProvider();
	/*
	 * The human readable name of the Context. By default the name of the Schema.
	 * May be overridden.
	 */
	String getName();

	/*
	 * The human readable description of the Context. By default the getDescription
	 * of the Schema. May be overridden.
	 */
	Optional<String> getDescription();

    ContextConfig getConfig();
}
