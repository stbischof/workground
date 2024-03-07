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
package org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.jdbc;

import org.eclipse.daanse.common.jdbc.db.api.DatabaseService;
import org.eclipse.daanse.common.jdbc.db.api.sql.ColumnDefinition;
import org.eclipse.daanse.common.jdbc.db.api.sql.ColumnReference;
import org.eclipse.daanse.common.jdbc.db.api.sql.SchemaReference;
import org.eclipse.daanse.common.jdbc.db.api.sql.TableReference;
import org.eclipse.daanse.common.jdbc.db.record.sql.element.ColumnReferenceR;
import org.eclipse.daanse.common.jdbc.db.record.sql.element.SchemaReferenceR;
import org.eclipse.daanse.common.jdbc.db.record.sql.element.TableReferenceR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Cause;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.AbstractSchemaWalker;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaExplorer;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.VerificationResultR;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Cause.DATABASE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Level.ERROR;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.AGGREGATOR_IS_NOT_VALID_FOR_THE_DATA_TYPE_OF_THE_COLUMN;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COLUMN_0_DOES_NOT_EXIST_IN_DIMENSION_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COLUMN_DEFINED_IN_FIELD_DOES_NOT_EXIST_IN_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COLUMN_S_DEFINED_IN_FIELD_DOES_NOT_EXIST_IN_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COLUMN_S_DEFINED_IN_FIELD_S_DOES_NOT_EXIST_IN_TABLE_S2;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COLUMN_S_DOES_NOT_EXIST_IN_LEVEL_TABLE_S;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOCH_CHECK_EXISTANCE_OF_TABLE_0;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_CHECK_EXISTANCE_OF_FACT_TABLE_0_DOES_NOT_EXIST_IN_DATABASE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_CHECK_EXISTANCE_OF_SCHEMA_S;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_EVALUEATE_DOES_COLUMN_EXIST_SCHEMA_TABLE_COLUMN;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_DEFINED_IN_FIELD_IN_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_S_DEFINED_IN_FIELD_S_IN;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_S_DEFINED_IN_FIELD_S_IN_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_S_DEFINED_IN_FIELD_S_IN_TABLE_24;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.COULD_NOT_QUERY_COLUMN_DATA_TYPE_ON_SCHEMA_TABLE_COLUMN;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.CUBE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.CUBE_DIMENSION;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.CUBE_DIMENSION_FOREIGN_KEY_S_DOES_NOT_EXIST_IN_FACT_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.CUBE_MUST_CONTAIN_MEASURES;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.DATABASE_DOES_NOW_ANSWER_WITH_DATA_TYPE_FOR_AGGREGATOR_COLUMN;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.DEGENERATE_DIMENSION_VALIDATION_CHECK_COLUMN_S_DOES_NOT_EXIST_IN_FACT_TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.DEGENERATE_DIMENSION_VALIDATION_CHECK_COLUMN_S_DOES_NOT_EXIST_IN_FACT_TABLE1;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.FACT_TABLE_0_DOES_NOT_EXIST_IN_DATABASE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.FOREIGN_KEY;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.HIERARCHY;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.HIERARCHY_PRIMARY_KEY;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.LEVEL;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.MEASURE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.PARENT_TABLE_NAME;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.PRIMARY_KEY;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.PROPERTY;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.RELATON;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.SCHEMA_SPACE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.SCHEMA_S_DOES_NOT_EXIST;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.TABLE;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.TABLE_S_DOES_NOT_EXIST_IN_DATABASE;

public class JDBCSchemaWalker extends AbstractSchemaWalker {

    private DatabaseService databaseService;
    private DatabaseVerifierConfig config;
    private DatabaseMetaData databaseMetaData;

    public JDBCSchemaWalker(DatabaseVerifierConfig config, DatabaseService databaseService, DatabaseMetaData databaseMetaData) {
        this.config = config;
        this.databaseService = databaseService;
        this.databaseMetaData = databaseMetaData;
    }

    @Override
    protected void checkCube(MappingCube cube) {
        super.checkCube(cube);

        if (cube.fact() instanceof MappingTable table) {
            String schemaName = table.schema();
            String factTable = table.name();
            TableReference tableReference = getTableReference(schemaName, factTable);
            try {
                if (!databaseService.tableExists(databaseMetaData, tableReference)) {
                    String msg = String.format(CUBE_MUST_CONTAIN_MEASURES, orNotSet(cube.name()));
                    results.add(new VerificationResultR(CUBE, msg,
                        ERROR, DATABASE));

                    String message = String.format(FACT_TABLE_0_DOES_NOT_EXIST_IN_DATABASE, factTable,
                        ((schemaName == null || schemaName.equals("")) ? "." : SCHEMA_SPACE + schemaName));
                    results.add(new VerificationResultR(CUBE, message, ERROR, DATABASE));
                }
            } catch (SQLException e) {
                String message = String.format(COULD_NOT_CHECK_EXISTANCE_OF_FACT_TABLE_0_DOES_NOT_EXIST_IN_DATABASE,
                    factTable,
                    ((schemaName == null || schemaName.equals("")) ? "." : SCHEMA_SPACE + schemaName));
                results.add(new VerificationResultR(CUBE, message, ERROR, DATABASE));
            }
        }

    }

    @Override
    protected void checkMeasure(MappingMeasure measure, MappingCube cube) {
        super.checkMeasure(measure, cube);
        if (cube != null && cube.fact() != null && cube.fact() instanceof MappingTable factTable) {
            // Database validity check, if database connection is
            // successful
            String column = measure.column();
            TableReference tableReference = getTableReference(factTable.schema(), factTable.name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
            try {
                if (databaseService.columnExists(databaseMetaData, columnReference)) {
                    // Check for aggregator type only if column
                    // exists in table.

                    checkMeasureColumnDataType(measure, factTable);
                }
            } catch (SQLException e) {

                String msg = String.format(COULD_NOT_EVALUEATE_DOES_COLUMN_EXIST_SCHEMA_TABLE_COLUMN,
                    factTable.schema(), factTable.name(), column);
                results.add(new VerificationResultR(MEASURE, msg, ERROR, DATABASE));
            }
        }
    }

    @Override
    protected void checkCubeDimension(MappingCubeDimension cubeDimension, MappingCube cube) {
        super.checkCubeDimension(cubeDimension, cube);

        if (cubeDimension instanceof MappingPrivateDimension &&
            !isEmpty((cubeDimension).foreignKey()) &&
            cube != null &&
            cube.fact() instanceof MappingTable factTable) {

            String foreignKey = (cubeDimension).foreignKey();
            TableReference tableReference = getTableReference(factTable.schema(), factTable.name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), foreignKey);

            try {
                if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                    String msg = String.format(CUBE_DIMENSION_FOREIGN_KEY_S_DOES_NOT_EXIST_IN_FACT_TABLE,
                        foreignKey);
                    results.add(new VerificationResultR(CUBE_DIMENSION, msg, ERROR, DATABASE));
                }
            } catch (SQLException e) {
                String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_DEFINED_IN_FIELD_IN_TABLE,
                    isEmpty(foreignKey.trim()) ? "' '" : foreignKey, FOREIGN_KEY,
                    factTable.name());
                results.add(new VerificationResultR(CUBE_DIMENSION, msg, ERROR, DATABASE));
            }

        }
        // TODO: Need to add validation for Views

    }

    @Override
    protected void checkHierarchy(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension, MappingCube cube) {
        super.checkHierarchy(hierarchy, cubeDimension, cube);

        // Validates that value in primaryKey exists in Table.
        String schema = null;
        String pkTable = null;
        if (hierarchy.relation() instanceof MappingJoin) {
            String[] schemaAndTable = SchemaExplorer.getTableNameForAlias(hierarchy.relation(),
                hierarchy.primaryKeyTable());
            schema = schemaAndTable[0];
            pkTable = schemaAndTable[1];
        } else if (hierarchy.relation() instanceof MappingTable table) {
            pkTable = table.name();
            schema = table.schema();
        }
        try {
            TableReference tableReference = getTableReference(schema, pkTable);
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), hierarchy.primaryKey());
            if (pkTable != null && !databaseService.columnExists(databaseMetaData, columnReference)) {
                String msg = String.format(COLUMN_S_DEFINED_IN_FIELD_DOES_NOT_EXIST_IN_TABLE,
                    isEmpty(hierarchy.primaryKey()
                        .trim()) ? "' '" : hierarchy.primaryKey(),
                    PRIMARY_KEY, pkTable);
                results.add(new VerificationResultR(HIERARCHY, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_DEFINED_IN_FIELD_IN_TABLE,
                isEmpty(hierarchy.primaryKey().trim()) ? "' '" : hierarchy.primaryKey(), HIERARCHY_PRIMARY_KEY,
                pkTable);
            results.add(new VerificationResultR(HIERARCHY, msg, ERROR, DATABASE));
        }
    }

    @Override
    protected void checkProperty(
        MappingProperty property, org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingHierarchy hierarchy, MappingCube cube
    ) {
        super.checkProperty(property, level, hierarchy, cube);
        // Check 'column' exists in 'table' if [level table] is
        // specified otherwise :: case of join.

        // It should exist in [hierarchy relation table] if it is
        // specified otherwise :: case of table.

        // It should exist in [fact table] :: case of degenerate
        // dimension where dimension columns exist in fact table and
        // there is no separate table.

        // check property's column is in table
        String column = property.column();
        String table = null;
        if (level != null) {
            // specified table for level's column'
            table = level.table();
        }
        if (isEmpty(table)) {
            if (hierarchy != null) {
                checkPropertyHierarchy(column, hierarchy, cube);
            }
        } else {
            try {
                TableReference tableReference = getTableReference(null, table);
                ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
                if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                    String msg = String.format(COLUMN_S_DOES_NOT_EXIST_IN_LEVEL_TABLE_S, column, table);
                    results.add(new VerificationResultR(PROPERTY, msg, ERROR, DATABASE));
                }
            } catch (SQLException e) {
                String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_DEFINED_IN_FIELD_IN_TABLE,
                    isEmpty(column.trim()) ? "' '" : column, "-", table);
                results.add(new VerificationResultR(PROPERTY, msg, ERROR, DATABASE));
            }
        }

    }

    @Override
    protected void checkTable(MappingTable table) {
        super.checkTable(table);
        String tableName = table.name();
        try {
            TableReference tableReference = getTableReference(null, tableName);
            if (!databaseService.tableExists(databaseMetaData, tableReference)) {
                String msg = String.format(TABLE_S_DOES_NOT_EXIST_IN_DATABASE, tableName);
                results.add(new VerificationResultR(TABLE, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String message = String.format(COULD_NOCH_CHECK_EXISTANCE_OF_TABLE_0, tableName);
            results.add(new VerificationResultR(TABLE, message, ERROR, DATABASE));
        }

        String theSchema = table.schema();
        try {
            List<SchemaReference> schemaList = databaseService.getSchemas(databaseMetaData);
            if (!isEmpty(theSchema) &&  !schemaList.stream().filter(sr -> theSchema.equals(sr.name())).findFirst().isPresent()) {
                String msg = String.format(SCHEMA_S_DOES_NOT_EXIST, theSchema);
                results.add(new VerificationResultR(TABLE, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String msg = String.format(COULD_NOT_CHECK_EXISTANCE_OF_SCHEMA_S, theSchema);

            results.add(new VerificationResultR(TABLE, msg, ERROR, DATABASE));

        }
    }

    /**
     * Validates a column, and returns an error message if it is invalid.
     *
     * @param column          Column
     * @param fieldName       Field name
     * @param level           Level
     * @param cube            Cube
     * @param parentHierarchy Hierarchy
     */
    @Override
    protected void checkColumn(
        String column,
        String fieldName,
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingCube cube,
        MappingHierarchy parentHierarchy
    ) {
        super.checkColumn(column, fieldName, level, cube, parentHierarchy);
        if (!isEmpty(column)) {
            String table = level.table();
            if (isEmpty(table)) {
                checkColumnIfLevelTableEmpty(column, fieldName, cube, parentHierarchy);

            } else {
                checkColumnIfLevelTableNotEmpty(table, column, fieldName, parentHierarchy);
            }
        }
    }

    @Override
    protected boolean isSchemaRequired() {
        return config.isSchemaRequired();
    }

    private void checkPropertyHierarchy(String column, MappingHierarchy hierarchy, MappingCube cube) {
        if (hierarchy.relation() == null && cube != null) {
            checkPropertyHierarchyRelationNull(column, cube);
        } else if (hierarchy.relation() instanceof MappingTable parentTable) {
            checkPropertyHierarchyRelationTable(parentTable, column);
            checkTable(parentTable);
        }
    }

    private void checkPropertyHierarchyRelationTable(MappingTable parentTable, String column) {
        try {
            TableReference tableReference = getTableReference(parentTable.schema(), parentTable.name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
            if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                String msg = String.format(COLUMN_0_DOES_NOT_EXIST_IN_DIMENSION_TABLE,
                    parentTable.name());
                results.add(new VerificationResultR(PROPERTY, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_S_DEFINED_IN_FIELD_S_IN +
                    "table {2}",
                isEmpty(column.trim()) ? "' '" : column, PARENT_TABLE_NAME, parentTable.name());
            results.add(new VerificationResultR(PROPERTY, msg, ERROR, DATABASE));
        }
    }

    private void checkPropertyHierarchyRelationNull(String column, MappingCube cube) {
        // Case of degenerate dimension within cube,
        // hierarchy table not specified
        final MappingTable factTable = (MappingTable) cube.fact();
        try {
            TableReference tableReference = getTableReference(factTable.schema(), factTable.name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
            if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                String msg = String.format(
                    DEGENERATE_DIMENSION_VALIDATION_CHECK_COLUMN_S_DOES_NOT_EXIST_IN_FACT_TABLE,
                    column);
                results.add(new VerificationResultR(PROPERTY, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_DEFINED_IN_FIELD_IN_TABLE,
                isEmpty(column.trim()) ? "' '" : column, RELATON, factTable.name());
            results.add(new VerificationResultR(PROPERTY, msg, ERROR, DATABASE));
        }
    }

    private void checkMeasureColumnDataType(MappingMeasure measure, MappingTable factTable) {
        // Check if aggregator selected is valid on
        // the data type of the column selected.
        Optional<ColumnDefinition> optionalColumnDefinition = Optional.empty();
        try {
            TableReference tableReference = getTableReference(factTable.schema(), factTable.name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), measure.column());
            List<ColumnDefinition> columnDefinitionList = databaseService.getColumnDefinitions(databaseMetaData, columnReference);
            optionalColumnDefinition  = columnDefinitionList.stream().findFirst();
        } catch (SQLException e) {

            String msg = String.format(COULD_NOT_QUERY_COLUMN_DATA_TYPE_ON_SCHEMA_TABLE_COLUMN,
                measure.aggregator(), measure.column());
            results.add(new VerificationResultR(MEASURE, msg, ERROR, Cause.SCHEMA));
        }
        // Coltype of 2, 4,5, 7, 8, -5 is numeric types
        // whereas 1, 12 are char varchar string
        // and 91 is date type.
        // Types are enumerated in java.sql.Types.
        int agIndex = -1;
        if ("sum".equals(measure.aggregator()) || "avg".equals(measure.aggregator())) {
            // aggregator = sum or avg, column should
            // be numeric
            agIndex = 0;
        }
        if (optionalColumnDefinition.isPresent() && optionalColumnDefinition.get().columnType() != null) {
            int colType = optionalColumnDefinition.get().columnType().dataType().getVendorTypeNumber();
            if (!(agIndex == -1 || (colType >= 2 && colType <= 8) || colType == -5 || colType == -6)) {
                String msg = String.format(AGGREGATOR_IS_NOT_VALID_FOR_THE_DATA_TYPE_OF_THE_COLUMN,
                    measure.aggregator(), measure.column());
                results.add(new VerificationResultR(MEASURE, msg, ERROR, Cause.SCHEMA));
            }
        } else {
            String msg = String.format(DATABASE_DOES_NOW_ANSWER_WITH_DATA_TYPE_FOR_AGGREGATOR_COLUMN,
                measure.aggregator(), measure.column());
            results.add(new VerificationResultR(MEASURE, msg, ERROR, DATABASE));

        }
    }

    private void checkColumnIfLevelTableEmpty(String column, String fieldName, MappingCube cube, MappingHierarchy parentHierarchy) {
        if (parentHierarchy != null) {
            if (parentHierarchy.relation() == null && cube != null) {
                // case of degenerate dimension within cube,
                // hierarchy table not specified
                checkColumnWithCubeFctTable(column, fieldName, cube);
            } else if (parentHierarchy.relation() instanceof MappingTable parentTable) {
                checkColumnWithHierarchyRelationTable(parentTable, column, fieldName, parentHierarchy);
            }
        }
    }

    private void checkColumnWithHierarchyRelationTable(MappingTable parentTable, String column, String fieldName, MappingHierarchy parentHierarchy) {
        try {
            TableReference tableReference = getTableReference(parentTable.schema(), parentTable.name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
            if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                String msg = String.format(COLUMN_DEFINED_IN_FIELD_DOES_NOT_EXIST_IN_TABLE,
                    isEmpty(column.trim()) ? "' '" : column, fieldName,
                    parentTable.name());
                results.add(new VerificationResultR(LEVEL, msg, ERROR, DATABASE));
                checkTable((MappingTable) parentHierarchy.relation());
            }
        } catch (SQLException e) {
            String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_DEFINED_IN_FIELD_IN_TABLE,
                isEmpty(column.trim()) ? "' '" : column, fieldName, parentTable.name());
            results.add(new VerificationResultR(LEVEL, msg, ERROR, DATABASE));
        }
    }

    private void checkColumnWithCubeFctTable(String column, String fieldName, MappingCube cube) {
        try {
            TableReference tableReference = getTableReference(((MappingTable) cube.fact()).schema(), ((MappingTable) cube.fact()).name());
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
            if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                String msg =
                    String.format(DEGENERATE_DIMENSION_VALIDATION_CHECK_COLUMN_S_DOES_NOT_EXIST_IN_FACT_TABLE1, column);
                results.add(new VerificationResultR(LEVEL, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String msg =
                String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_S_DEFINED_IN_FIELD_S_IN_TABLE_24,
                    isEmpty(column.trim()) ? "' '" : column, fieldName, ((MappingTable) cube.fact()).name());
            results.add(new VerificationResultR(LEVEL, msg, ERROR, DATABASE));
        }
    }

    private void checkColumnIfLevelTableNotEmpty(String table, String column, String fieldName, MappingHierarchy parentHierarchy) {
        String schema = null;
        // if using Joins then gets the table name for doesColumnExist
        // validation.
        if (parentHierarchy != null && parentHierarchy.relation() instanceof MappingJoin join) {
            String[] schemaAndTable = SchemaExplorer.getTableNameForAlias(parentHierarchy.relation(), table);
            schema = schemaAndTable[0];
            table = schemaAndTable[1];
            checkJoin(join);
        }
        try {
            TableReference tableReference = getTableReference(schema, table);
            ColumnReference columnReference = new ColumnReferenceR(Optional.of(tableReference), column);
            if (!databaseService.columnExists(databaseMetaData, columnReference)) {
                String msg = String.format(COLUMN_S_DEFINED_IN_FIELD_S_DOES_NOT_EXIST_IN_TABLE_S2,
                    isEmpty(column.trim()) ? "' '" : column, fieldName, table);
                results.add(new VerificationResultR(LEVEL, msg, ERROR, DATABASE));
            }
        } catch (SQLException e) {
            String msg = String.format(COULD_NOT_LOOKUP_EXISTANCE_OF_COLUMN_S_DEFINED_IN_FIELD_S_IN_TABLE,
                isEmpty(column.trim()) ? "' '" : column, fieldName, table);
            results.add(new VerificationResultR(LEVEL, msg, ERROR, DATABASE));

        }
    }

    private TableReference getTableReference(String schemaName, String tableName) {
        return new TableReferenceR(Optional.ofNullable(schemaName != null ? new SchemaReferenceR(schemaName) : null),
            tableName);
    }
}
