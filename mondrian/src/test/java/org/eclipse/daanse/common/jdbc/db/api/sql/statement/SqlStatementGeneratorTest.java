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
package org.eclipse.daanse.common.jdbc.db.api.sql.statement;

import org.eclipse.daanse.common.jdbc.db.api.SqlStatementGenerator;
import org.eclipse.daanse.common.jdbc.db.api.meta.IdentifierInfo;
import org.eclipse.daanse.common.jdbc.db.api.meta.MetaInfo;
import org.eclipse.daanse.common.jdbc.db.api.sql.CatalogReference;
import org.eclipse.daanse.common.jdbc.db.api.sql.ColumnDefinition;
import org.eclipse.daanse.common.jdbc.db.api.sql.ColumnMetaData;
import org.eclipse.daanse.common.jdbc.db.api.sql.ColumnReference;
import org.eclipse.daanse.common.jdbc.db.api.sql.SchemaReference;
import org.eclipse.daanse.common.jdbc.db.api.sql.TableReference;
import org.eclipse.daanse.common.jdbc.db.core.SqlStatementGeneratorImpl;
import org.eclipse.daanse.common.jdbc.db.record.sql.element.ColumnDefinitionR;
import org.eclipse.daanse.common.jdbc.db.record.sql.element.ColumnMetaDataR;
import org.eclipse.daanse.common.jdbc.db.record.sql.element.ColumnReferenceR;
import org.eclipse.daanse.common.jdbc.db.record.sql.statement.CreateSchemaSqlStatementR;
import org.eclipse.daanse.common.jdbc.db.record.sql.statement.DropContainerSqlStatementR;
import org.eclipse.daanse.common.jdbc.db.record.sql.statement.DropSchemaSqlStatementR;
import org.eclipse.daanse.common.jdbc.db.record.sql.statement.InsertSqlStatementR;
import org.eclipse.daanse.common.jdbc.db.record.sql.statement.TruncateTableSqlStatementR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

class SqlStatementGeneratorTest {

    @Mock
    SchemaReference schemaReference;

    @Mock
    CatalogReference catalogReference;

    @Mock
    MetaInfo metaInfo;

    @Mock
    IdentifierInfo identifierInfo;

    @Mock
    TableReference tableReference;

    SqlStatementGenerator sqlStatementGenerator;

    List<ColumnReference> columns;

    List<String> values;

    List<ColumnDefinition> columnDefinitions;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        when(identifierInfo.quoteString()).thenReturn(" ");
        when(metaInfo.identifierInfo()).thenReturn(identifierInfo);
        schemaReference = mock(SchemaReference.class);
        catalogReference = mock(CatalogReference.class);

        when(schemaReference.name()).thenReturn("SchemaName");
        when(schemaReference.catalog()).thenReturn(Optional.of(catalogReference));

        when(tableReference.name()).thenReturn("TableName");
        when(tableReference.type()).thenReturn(TableReference.TYPE_TABLE);
        when(tableReference.schema()).thenReturn(Optional.of(schemaReference));

        ColumnReference columnReference1  = new ColumnReferenceR(Optional.of(tableReference), "column1");
        ColumnReference columnReference2  = new ColumnReferenceR(Optional.of(tableReference), "column2");
        ColumnReference columnReference3  = new ColumnReferenceR(Optional.of(tableReference), "column3");

        columns = List.of(columnReference1, columnReference2, columnReference3);
        values = List.of("value1", "value2", "value3");

        ColumnMetaData columnType1 = new ColumnMetaDataR(
            java.sql.JDBCType.CHAR,
            Optional.of(10),
            Optional.of(10),
            Optional.empty());

        ColumnMetaData columnType2 = new ColumnMetaDataR(
            java.sql.JDBCType.INTEGER,
            Optional.empty(),
            Optional.empty(),
            Optional.empty());

        ColumnMetaData columnType3 = new ColumnMetaDataR(
            java.sql.JDBCType.BOOLEAN,
            Optional.empty(),
            Optional.empty(),
            Optional.empty());

        ColumnDefinition columnDefinition1 = new ColumnDefinitionR(columnReference1, columnType1);
        ColumnDefinition columnDefinition2 = new ColumnDefinitionR(columnReference2, columnType2);
        ColumnDefinition columnDefinition3 = new ColumnDefinitionR(columnReference3, columnType3);

        columnDefinitions = List.of(columnDefinition1, columnDefinition2, columnDefinition3);
        sqlStatementGenerator = new SqlStatementGeneratorImpl(metaInfo);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false})
    void dropContainerTest(boolean ifExists) {
        DropContainerSqlStatement dropContainerSqlStatement = new DropContainerSqlStatementR(tableReference, ifExists);
        String sql = sqlStatementGenerator.getSqlOfStatement(dropContainerSqlStatement);
        if (ifExists) {
            assertThat(sql).isEqualTo("DROP TABLE IF EXISTS SchemaName.TableName");
        } else {
            assertThat(sql).isEqualTo("DROP TABLE SchemaName.TableName");
        }

    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false})
    void dropSchemaTest(boolean ifExists) {
        DropSchemaSqlStatement dropSchemaSqlStatement = new DropSchemaSqlStatementR(schemaReference, ifExists);
        String sql = sqlStatementGenerator.getSqlOfStatement(dropSchemaSqlStatement);
        if (ifExists) {
            assertThat(sql).isEqualTo("DROP SCHEMA IF EXISTS SchemaName");
        } else {
            assertThat(sql).isEqualTo("DROP SCHEMA SchemaName");
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false})
    void createSchemaTest(boolean ifExists) {
        CreateSchemaSqlStatement createSchemaSqlStatement = new CreateSchemaSqlStatementR(schemaReference, ifExists);
        String sql = sqlStatementGenerator.getSqlOfStatement(createSchemaSqlStatement);
        if (ifExists) {
            assertThat(sql).isEqualTo("CREATE SCHEMA IF NOT EXISTS SchemaName");
        } else {
            assertThat(sql).isEqualTo("CREATE SCHEMA SchemaName");
        }
    }

    @Test
    void truncateTableTest() {
        TruncateTableSqlStatement truncateTableSqlStatement = new TruncateTableSqlStatementR(tableReference);
        String sql = sqlStatementGenerator.getSqlOfStatement(truncateTableSqlStatement);
        assertThat(sql).isEqualTo("TRUNCATE TABLE SchemaName.TableName");
    }

    @Test
    void insertSqlTest() {
        InsertSqlStatement insertSqlStatement = new InsertSqlStatementR(tableReference, columns, values);
        String sql = sqlStatementGenerator.getSqlOfStatement(insertSqlStatement);
        assertThat(sql).isEqualTo("INSERT INTO SchemaName.TableName(column1, column2, column3) VALUES (value1, value2, value3)");
    }

    @Test
    void createSqlTest() {
        CreateSqlStatement createSqlStatement = mock(CreateSqlStatement.class);
        when(createSqlStatement.table()).thenReturn(tableReference);
        when(createSqlStatement.columnDefinitions()).thenReturn(columnDefinitions);
        when(createSqlStatement.ifNotExists()).thenReturn(true);
        String sql = sqlStatementGenerator.getSqlOfStatement(createSqlStatement);
        assertThat(sql).isEqualTo("CREATE TABLE IF NOT EXISTS SchemaName.TableName( column1 CHAR(10,10), column2 INTEGER, column3 BOOLEAN)");
    }
}
