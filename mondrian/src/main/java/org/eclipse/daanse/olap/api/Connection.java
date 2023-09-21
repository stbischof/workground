/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (C) 2000-2005 Julian Hyde
 * Copyright (C) 2005-2017 Hitachi Vantara and others
 * All Rights Reserved.
 *
 * For more information please visit the Project: Hitachi Vantara - Mondrian
 *
 * ---- All changes after Fork in 2023 ------------------------
 *
 * Project: Eclipse daanse
 *
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors after Fork in 2023:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */

package org.eclipse.daanse.olap.api;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.olap.api.query.component.DrillThrough;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.QueryComponent;
import org.eclipse.daanse.olap.api.result.Result;

import mondrian.olap.DriverManager;
import mondrian.olap.QueryImpl;

/**
 * Connection to a multi-dimensional database.
 *
 * @see DriverManager
 *
 * @author jhyde
 */
public interface Connection {

    /**
     * Get the Connect String associated with this Connection.
     *
     * @return the Connect String (never null).
     */
    String getConnectString();

    /**
     * Get the name of the Catalog associated with this Connection.
     *
     * @return the Catalog name (never null).
     */
    String getCatalogName();

    /**
     * Get the Schema associated with this Connection.
     *
     * @return the Schema (never null).
     */
    Schema getSchema();

    /**
     * Get the Schema associated with this Connection.
     *
     * @return the Schema (never null).
     */
    List<Schema> getSchemas();

    /**
     * Closes this <code>Connection</code>. You may not use this
     * <code>Connection</code> after closing it.
     */
    void close();

    /**
     * Executes a query.
     *
     * @throws RuntimeException if another thread cancels the query's statement.
     *
     * @deprecated This method is deprecated and will be removed in
     * mondrian-4.0. It operates by internally creating a statement. Better
     * to use olap4j and explicitly create a statement.
     */
    @Deprecated
	Result execute(QueryImpl query);

    Statement createStatement();

    /**
     * Returns the locale this connection belongs to.  Determines, for example,
     * the currency string used in formatting cell values.
     *
     * @see mondrian.util.Format
     */
    Locale getLocale();

    /**
     * Parses an expresion.
     */
    Expression parseExpression(String s);

    /**
     * Parses a query.
     */
    QueryImpl parseQuery(String s);

    /**
     * Parses a statement.
     *
     * @param mdx MDX string
     * @return A {@link QueryImpl} if it is a SELECT statement, a
     *   {@link DrillThrough} if it is a DRILLTHROUGH statement
     */
    QueryComponent parseStatement(String mdx);

    /**
     * Sets the privileges for the this connection.
     *
     * @pre role != null
     * @pre role.isMutable()
     */
    void setRole(Role role);

    /**
     * Returns the access-control profile for this connection.
     * @post role != null
     * @post role.isMutable()
     */
    Role getRole();

    /**
     * Returns a schema reader with access control appropriate to the current
     * role.
     */
    SchemaReader getSchemaReader();

    /**
     * Returns the value of a connection property.
     *
     * @param name Name of property, for example "JdbcUser".
     * @return Value of property, or null if property is not defined.
     */
    Object getProperty(String name);

    /**
     * Returns an object with which to explicitly control the contents of the
     * cache.
     *
     * @param pw Writer to which to write logging information; may be null
     */
    CacheControl getCacheControl(PrintWriter pw);

    /**
     * Returns the data source this connection uses to create connections
     * to the underlying JDBC database.
     *
     * @return Data source
     */
    DataSource getDataSource();

    Context getContext();
}
