package org.eclipse.daanse.db.jdbc.metadata.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.db.jdbc.metadata.api.JdbcMetaDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcMetaDataServiceLiveImpl implements JdbcMetaDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger( JdbcMetaDataServiceLiveImpl.class );

    private DatabaseMetaData metadata;
    private String catalogName;

    public JdbcMetaDataServiceLiveImpl(Connection connection) throws SQLException {
        metadata = connection.getMetaData();
        catalogName = connection.getCatalog();
    }

    @Override
    public Optional<Integer> getColumnDataType(String schemaName, String tableName, String columnName)
            throws SQLException {
        try (ResultSet rs = metadata.getColumns(catalogName, schemaName, tableName, columnName);) {
            while (rs.next()) {
                return Optional.ofNullable(rs.getInt("DATA_TYPE"));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean doesColumnExist(String schemaName, String tableName, String columnName) throws SQLException {
        try (ResultSet rs = metadata.getColumns(catalogName, schemaName, tableName, columnName);) {
            return rs.next();
        }
    }

    @Override
    public boolean doesTableExist(String schemaName, String tableName) throws SQLException {
        try (ResultSet rs = metadata.getTables(catalogName, schemaName, tableName, null);) {
            return rs.next();
        }
    }

    @Override
    public boolean doesSchemaExist(String schemaName) throws SQLException {
        try (ResultSet rs = metadata.getSchemas(catalogName, schemaName);) {
            return rs.next();
        }
    }

    @Override
    public List<ForeignKey> getForeignKeys(String schemaName, String tableName) {
        List<ForeignKey> result = new ArrayList<>();
        try (ResultSet rs = metadata.getImportedKeys(catalogName, schemaName, tableName)) {
            if (rs == null) {
                return List.of();
            }
            while (rs.next()) {
                String pkTableName =
                rs.getString("PKTABLE_NAME");
                String pkColumnName =
                    rs.getString("PKCOLUMN_NAME");
                String fkTableName =
                    rs.getString("FKTABLE_NAME");
                String fkColumnName =
                    rs.getString("FKCOLUMN_NAME");
                result.add(new ForeignKey(pkTableName, pkColumnName, fkTableName, fkColumnName));
            }
        } catch (Exception e) {
            LOGGER.error("getForeignKeys", e);
        }
        return result;
    }

    @Override
    public List<Column> getColumns(String schemaName, String tableName) {
        List<Column> result = new ArrayList<>();
        try (ResultSet rs = metadata.getColumns(catalogName, schemaName, tableName, null)) {
            if (rs == null) {
                return List.of();
            }
            while (rs.next()) {
                result.add(new Column(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE")));
            }
        } catch (Exception e) {
            LOGGER.error("getForeignKeys", e);
        }
        return result;
    }

    @Override
    public List<String> getTables(String dbName) {
        List result = new ArrayList();
        try (ResultSet rs = getTablesResultSet(dbName)) {
            if (rs != null) {
                while (rs.next()) {
                    result.add(rs.getString("TABLE_NAME"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("getTables error", e);
        }
        return result;
    }

    private ResultSet getTablesResultSet(String dbName) throws SQLException {
        try {
            return metadata.getTables(
                "",
                dbName,
                null,
                new String[]{"TABLE", "VIEW"});
        } catch (Exception e) {
            // this is a workaround for databases that throw an exception
            // when views are requested.
            return metadata.getTables(
                "", dbName, null, new String[]{"TABLE"});
        }
    }

}
