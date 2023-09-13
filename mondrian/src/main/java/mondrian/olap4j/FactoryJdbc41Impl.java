/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.eclipse.daanse.olap.api.Context;
import org.olap4j.OlapException;

import mondrian.rolap.RolapConnection;

/**
 * Implementation of {@link mondrian.olap4j.Factory} for JDBC 4.1.
 *
 * @author jhyde
 */
class FactoryJdbc41Impl implements Factory {
    @Override
	public Connection newConnection(
        MondrianOlap4jDriver driver,
        String url,
        Properties info, Context context)
        throws SQLException
    {
        return new MondrianOlap4jConnectionJdbc41(this, driver, url, info, context);
    }

    @Override
	public EmptyResultSet newEmptyResultSet(
        MondrianOlap4jConnection olap4jConnection)
    {
        List<String> headerList = Collections.emptyList();
        List<List<Object>> rowList = Collections.emptyList();
        return new EmptyResultSetJdbc41(
            olap4jConnection, headerList, rowList);
    }

    @Override
	public ResultSet newFixedResultSet(
        MondrianOlap4jConnection olap4jConnection,
        List<String> headerList,
        List<List<Object>> rowList)
    {
        return new EmptyResultSetJdbc41(
            olap4jConnection, headerList, rowList);
    }

    @Override
	public MondrianOlap4jCellSet newCellSet(
        MondrianOlap4jStatement olap4jStatement)
    {
        return new MondrianOlap4jCellSetJdbc41(olap4jStatement);
    }

    @Override
	public MondrianOlap4jStatement newStatement(
        MondrianOlap4jConnection olap4jConnection)
    {
        return new MondrianOlap4jStatementJdbc41(olap4jConnection);
    }

    @Override
	public MondrianOlap4jPreparedStatement newPreparedStatement(
        String mdx,
        MondrianOlap4jConnection olap4jConnection)
        throws OlapException
    {
        return new MondrianOlap4jPreparedStatementJdbc41(olap4jConnection, mdx);
    }

    @Override
	public MondrianOlap4jDatabaseMetaData newDatabaseMetaData(
        MondrianOlap4jConnection olap4jConnection,
        RolapConnection mondrianConnection)
    {
        return new MondrianOlap4jDatabaseMetaDataJdbc41(
            olap4jConnection, mondrianConnection);
    }

    // Inner classes

    private static class EmptyResultSetJdbc41
        extends FactoryJdbc4Plus.AbstractEmptyResultSet
    {
        EmptyResultSetJdbc41(
            MondrianOlap4jConnection olap4jConnection,
            List<String> headerList,
            List<List<Object>> rowList)
        {
            super(olap4jConnection, headerList, rowList);
        }

        @Override
		public <T> T getObject(
            int columnIndex,
            Class<T> type) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public <T> T getObject(
            String columnLabel,
            Class<T> type) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    private static class MondrianOlap4jConnectionJdbc41
        extends FactoryJdbc4Plus.AbstractConnection
    {
        MondrianOlap4jConnectionJdbc41(
            Factory factory,
            MondrianOlap4jDriver driver,
            String url,
            Properties info, Context context) throws SQLException
        {
            super(factory, driver, url, info, context);
        }

        @Override
		public void abort(Executor executor) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNetworkTimeout(
            Executor executor,
            int milliseconds) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public int getNetworkTimeout() throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    private static class MondrianOlap4jCellSetJdbc41
        extends FactoryJdbc4Plus.AbstractCellSet
    {
        public MondrianOlap4jCellSetJdbc41(
            MondrianOlap4jStatement olap4jStatement)
        {
            super(olap4jStatement);
        }

        @Override
		public <T> T getObject(
            int columnIndex,
            Class<T> type) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public <T> T getObject(
            String columnLabel,
            Class<T> type) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    private static class MondrianOlap4jStatementJdbc41
        extends MondrianOlap4jStatement
    {
        public MondrianOlap4jStatementJdbc41(
            MondrianOlap4jConnection olap4jConnection)
        {
            super(olap4jConnection);
        }

        @Override
		public void closeOnCompletion() throws SQLException {
            closeOnCompletion = true;
        }

        @Override
		public boolean isCloseOnCompletion() throws SQLException {
            return closeOnCompletion;
        }
    }

    private static class MondrianOlap4jPreparedStatementJdbc41
        extends FactoryJdbc4Plus.AbstractPreparedStatement
    {
        public MondrianOlap4jPreparedStatementJdbc41(
            MondrianOlap4jConnection olap4jConnection,
            String mdx)
            throws OlapException
        {
            super(olap4jConnection, mdx);
        }

        @Override
		public void closeOnCompletion() throws SQLException {
            closeOnCompletion = true;
        }

        @Override
		public boolean isCloseOnCompletion() throws SQLException {
            return closeOnCompletion;
        }
    }

    private static class MondrianOlap4jDatabaseMetaDataJdbc41
        extends FactoryJdbc4Plus.AbstractDatabaseMetaData
    {
        public MondrianOlap4jDatabaseMetaDataJdbc41(
            MondrianOlap4jConnection olap4jConnection,
            RolapConnection mondrianConnection)
        {
            super(olap4jConnection, mondrianConnection);
        }

        @Override
		public ResultSet getPseudoColumns(
            String catalog,
            String schemaPattern,
            String tableNamePattern,
            String columnNamePattern) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean generatedKeyAlwaysReturned() throws SQLException {
            throw new UnsupportedOperationException();
        }
    }
}
