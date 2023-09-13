/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap4j;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.RowIdLifetime;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Struct;
import java.util.List;
import java.util.Properties;

import org.eclipse.daanse.olap.api.Context;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;

import mondrian.rolap.RolapConnection;

/**
 * Abstract JDBC classes, for JDBC 4.0 and 4.1.
 *
 * @author jhyde
 * @since Jun 14, 2007
 */
class FactoryJdbc4Plus {

    private FactoryJdbc4Plus() {
        // constructor
    }

    // Inner classes

    protected abstract static class AbstractEmptyResultSet
        extends EmptyResultSet
    {
        AbstractEmptyResultSet(
            MondrianOlap4jConnection olap4jConnection,
            List<String> headerList,
            List<List<Object>> rowList)
        {
            super(olap4jConnection, headerList, rowList);
        }

        // implement java.sql.ResultSet methods
        // introduced in JDBC 4.0/JDK 1.6

        @Override
		public RowId getRowId(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public RowId getRowId(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateRowId(int columnIndex, RowId x) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateRowId(String columnLabel, RowId x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public int getHoldability() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean isClosed() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNString(
            int columnIndex, String nString) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNString(
            String columnLabel, String nString) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(int columnIndex, NClob nClob)
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            String columnLabel, NClob nClob) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public NClob getNClob(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public NClob getNClob(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public SQLXML getSQLXML(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public SQLXML getSQLXML(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateSQLXML(
            int columnIndex, SQLXML xmlObject) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateSQLXML(
            String columnLabel, SQLXML xmlObject) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public String getNString(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public String getNString(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Reader getNCharacterStream(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Reader getNCharacterStream(String columnLabel)
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            int columnIndex, Reader x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            int columnIndex, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            int columnIndex, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            int columnIndex, Reader x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            String columnLabel, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            String columnLabel, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            int columnIndex,
            InputStream inputStream,
            long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            String columnLabel,
            InputStream inputStream,
            long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            int columnIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            int columnIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            int columnIndex, Reader x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            int columnIndex, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            int columnIndex, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            int columnIndex, Reader x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            String columnLabel, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            String columnLabel, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            int columnIndex, InputStream inputStream) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            String columnLabel, InputStream inputStream) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(int columnIndex, Reader reader)
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            int columnIndex, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    abstract static class AbstractConnection
        extends MondrianOlap4jConnection
        implements OlapConnection
    {
        AbstractConnection(
            Factory factory,
            MondrianOlap4jDriver driver,
            String url,
            Properties info, Context context) throws SQLException
        {
            super(factory, driver, url, info, context);
        }

        // implement java.sql.Connection methods
        // introduced in JDBC 4.0/JDK 1.6

        @Override
		public Clob createClob() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Blob createBlob() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public NClob createNClob() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public SQLXML createSQLXML() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean isValid(int timeout) throws SQLException {
            return !isClosed();
        }

        @Override
		public void setClientInfo(
            String name, String value) throws SQLClientInfoException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setClientInfo(Properties properties)
            throws SQLClientInfoException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public String getClientInfo(String name) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Properties getClientInfo() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Array createArrayOf(
            String typeName, Object[] elements) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Struct createStruct(
            String typeName, Object[] attributes) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    abstract static class AbstractCellSet
        extends MondrianOlap4jCellSet
    {
        protected AbstractCellSet(
            MondrianOlap4jStatement olap4jStatement)
        {
            super(olap4jStatement);
        }

        // implement java.sql.CellSet methods
        // introduced in JDBC 4.0/JDK 1.6

        @Override
		public RowId getRowId(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public RowId getRowId(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateRowId(int columnIndex, RowId x) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateRowId(String columnLabel, RowId x)
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public int getHoldability() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean isClosed() throws SQLException {
            return closed;
        }

        @Override
		public void updateNString(
            int columnIndex, String nString) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNString(
            String columnLabel, String nString) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(int columnIndex, NClob nClob)
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            String columnLabel, NClob nClob) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public NClob getNClob(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public NClob getNClob(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public SQLXML getSQLXML(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public SQLXML getSQLXML(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateSQLXML(
            int columnIndex, SQLXML xmlObject) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateSQLXML(
            String columnLabel, SQLXML xmlObject) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public String getNString(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public String getNString(String columnLabel) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Reader getNCharacterStream(int columnIndex) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public Reader getNCharacterStream(
            String columnLabel) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            int columnIndex, Reader x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            int columnIndex, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            int columnIndex, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            int columnIndex, Reader x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            String columnLabel, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            String columnLabel, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            int columnIndex,
            InputStream inputStream,
            long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            String columnLabel,
            InputStream inputStream,
            long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            int columnIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            int columnIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            String columnLabel, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            int columnIndex, Reader x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNCharacterStream(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            int columnIndex, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            int columnIndex, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            int columnIndex, Reader x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateAsciiStream(
            String columnLabel, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBinaryStream(
            String columnLabel, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateCharacterStream(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            int columnIndex, InputStream inputStream) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateBlob(
            String columnLabel, InputStream inputStream) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            int columnIndex, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateClob(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            int columnIndex, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void updateNClob(
            String columnLabel, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    // No need for an "AbstractStatement" class. Statement API is the same
    // JDBC 3 and JDBC 4.

    abstract static class AbstractPreparedStatement
        extends MondrianOlap4jPreparedStatement
    {
        protected AbstractPreparedStatement(
            MondrianOlap4jConnection olap4jConnection,
            String mdx)
            throws OlapException
        {
            super(olap4jConnection, mdx);
        }

        // implement java.sql.PreparedStatement methods
        // introduced in JDBC 4.0/JDK 1.6

        @Override
		public void setRowId(int parameterIndex, RowId x) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNString(
            int parameterIndex, String value) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNCharacterStream(
            int parameterIndex, Reader value, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNClob(
            int parameterIndex, NClob value) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setClob(
            int parameterIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setBlob(
            int parameterIndex,
            InputStream inputStream,
            long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNClob(
            int parameterIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setSQLXML(
            int parameterIndex, SQLXML xmlObject) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setAsciiStream(
            int parameterIndex, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setBinaryStream(
            int parameterIndex, InputStream x, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setCharacterStream(
            int parameterIndex, Reader reader, long length) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setAsciiStream(
            int parameterIndex, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setBinaryStream(
            int parameterIndex, InputStream x) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setCharacterStream(
            int parameterIndex, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNCharacterStream(
            int parameterIndex, Reader value) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setClob(
            int parameterIndex, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setBlob(
            int parameterIndex, InputStream inputStream) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void setNClob(
            int parameterIndex, Reader reader) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }

    abstract static class AbstractDatabaseMetaData
        extends MondrianOlap4jDatabaseMetaData
    {
        protected AbstractDatabaseMetaData(
            MondrianOlap4jConnection olap4jConnection,
            RolapConnection mondrianConnection)
        {
            super(olap4jConnection, mondrianConnection);
        }

        // implement java.sql.DatabaseMetaData methods
        // introduced in JDBC 4.0/JDK 1.6

        @Override
		public RowIdLifetime getRowIdLifetime() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public ResultSet getSchemas(
            String catalog, String schemaPattern) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean supportsStoredFunctionsUsingCallSyntax()
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean autoCommitFailureClosesAllResultSets()
            throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public ResultSet getClientInfoProperties() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
		public ResultSet getFunctions(
            String catalog,
            String schemaPattern,
            String functionNamePattern) throws SQLException
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public ResultSet getFunctionColumns(
            String catalog,
            String schemaPattern,
            String functionNamePattern,
            String columnNamePattern) throws SQLException
        {
            throw new UnsupportedOperationException();
        }
    }
}
