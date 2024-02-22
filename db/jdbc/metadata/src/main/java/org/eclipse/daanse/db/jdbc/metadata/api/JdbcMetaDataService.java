package org.eclipse.daanse.db.jdbc.metadata.api;

import org.eclipse.daanse.db.jdbc.metadata.impl.Column;
import org.eclipse.daanse.db.jdbc.metadata.impl.ForeignKey;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JdbcMetaDataService {

    Optional<Integer> getColumnDataType(String schemaName, String tableName, String columnName)
            throws SQLException;

    boolean doesColumnExist(String schemaName, String tableName, String columnName) throws SQLException;

    boolean doesTableExist(String schemaName, String tableName) throws SQLException;

    boolean doesSchemaExist(String schemaName) throws SQLException;

    List<ForeignKey> getForeignKeys(String schemaName, String tableName);

    List<Column> getColumns(String schemaName, String tableName);

    List<String> getTables(String schemaName);
}
