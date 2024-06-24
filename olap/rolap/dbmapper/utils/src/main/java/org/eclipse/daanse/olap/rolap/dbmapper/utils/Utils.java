package org.eclipse.daanse.olap.rolap.dbmapper.utils;

import org.eclipse.daanse.db.jdbc.util.impl.Column;
import org.eclipse.daanse.db.jdbc.util.impl.Constraint;
import org.eclipse.daanse.db.jdbc.util.impl.DBStructure;
import org.eclipse.daanse.db.jdbc.util.impl.SqlType;
import org.eclipse.daanse.db.jdbc.util.impl.Type;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Utils {

    private Utils() {
        // constructor
    }

    public static DBStructure getDBStructure(MappingSchema schema) {

        String schemaName = schema.name();
        Map<String, Table> tables = new HashMap<>();
        if (schema.dimensions() != null) {
            schema.dimensions().forEach(d -> processingDimension(d, tables, null, schemaName));
        }
        if (schema.cubes() != null) {
            schema.cubes().forEach(c -> processingCube(c, tables, schemaName));
        }
        List<org.eclipse.daanse.db.jdbc.util.impl.Table> tList = tables.values().stream().map(t -> new org.eclipse.daanse.db.jdbc.util.impl.Table(
            t.getSchema(),
            t.getName(),
            t.getConstraint().values().stream().toList(),
            t.getColumns().values().stream().toList())).toList();
        return new DBStructure(schemaName, tList);
    }

    private static void processingCube(MappingCube cube, Map<String, Table> tables, String schemaName) {
        if (cube != null) {
            String tableName = null;
            if (cube.fact() != null) {
                tableName = processingRelation(cube.fact(), tables, schemaName);
            }
            if (cube.dimensionUsageOrDimensions() != null) {
                String tName = tableName;
                cube.dimensionUsageOrDimensions().forEach(d -> processingDimension(d, tables, tName, schemaName));
            }
            if (cube.measures() != null) {
                String tName = tableName;
                cube.measures().forEach(m -> processingMeasure(m, tables, tName, schemaName));
            }
        }
    }

    private static void processingMeasure(MappingMeasure m, Map<String, Table> tables, String tableName, String schemaName) {

        if (m.column() != null) {
            String columnName = m.column();
            if (tableName != null) {
                Table t = getTableOrCreateNew(tables, tableName, schemaName);
                getColumnOrCreateNew(
                    t.getColumns(), columnName, getSqlType(m.datatype()));
            }
        }
    }

    private static void processingDimension(MappingCubeDimension d, Map<String, Table> tables, String tableName, String schemaName) {
        if (d instanceof MappingPrivateDimension privateDimension && privateDimension.hierarchies() != null) {
            privateDimension.hierarchies().forEach(h -> processingHierarchy(h, tables, schemaName));
        }
        if (tableName != null) {
            Table table = getTableOrCreateNew(tables, tableName, schemaName);
            if (d.foreignKey() != null) {
                getColumnOrCreateNew(table.getColumns(), d.foreignKey(), getSqlType(Type.INTEGER));
                getConstraintOrCreateNew(table.getConstraint(), d.foreignKey(),
                    true, List.of(d.foreignKey()));
            }
        }
    }

    private static void processingHierarchy(MappingHierarchy h, Map<String, Table> tables, String schemaName) {
        if (h.relation() != null) {
            String tName = processingRelation(h.relation(), tables, schemaName);
            if (h.levels() != null) {
                h.levels().forEach(l -> processingLevel(l, tables, tName, schemaName));
            }
            if (h.primaryKey() != null && (tName != null || h.primaryKeyTable() != null)) {
                String t = h.primaryKeyTable() != null ? h.primaryKeyTable() : tName;
                Table table = getTableOrCreateNew(tables, t, schemaName);
                getColumnOrCreateNew(table.getColumns(), h.primaryKey(), getSqlType(Type.INTEGER));
                getConstraintOrCreateNew(table.getConstraint(), h.primaryKey(), true, List.of(h.primaryKey()));
            }
        }
    }

    private static String processingRelation(MappingQuery relation, Map<String, Table> tables, String schemaName) {
        if (relation instanceof org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable table) {
            return processingTable(table, tables, schemaName);
        }
        if (relation instanceof MappingJoinQuery join) {
            return processingJoin(join, tables, schemaName);
        }
        if (relation instanceof MappingInlineTable inlineTable) {
            return processingInlineTable(inlineTable, tables, schemaName);
        }
        return null;
    }

    private static String processingInlineTable(MappingInlineTable table, Map<String, Table> tables, String schemaName) {
        if (table.getAlias() != null) {
            Table t = getTableOrCreateNew(tables, table.getAlias(), schemaName);
            if (table.columnDefs() != null) {
                table.columnDefs().forEach(c -> processingColumnDef(c, t.getColumns()));
            }
            return table.getAlias();
        }
        return null;
    }

    private static void processingColumnDef(MappingColumnDef c, Map<String, Column> columns) {
        if (!columns.containsKey(c.name())) {
            SqlType sqlType = getSqlType(c.type());
            columns.put(c.name(), new Column(c.name(), sqlType));
        }
    }


    private static SqlType getSqlType(Enum typeEnum) {
        Type type = Type.fromName(typeEnum != null ? typeEnum.name() : null);
        Optional<String> length = Optional.empty();
        if (Type.STRING.equals(type)) {
            length = Optional.of("(255)");
        }
        return new SqlType(type, length);
    }

    private static String processingTable(
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable table,
        Map<String, Table> tables,
        String schemaName
    ) {
        if (table.getName() != null) {
            getTableOrCreateNew(tables, table.getName(), schemaName);
            return table.getName();
        }
        return null;
    }

    private static String processingJoin(MappingJoinQuery relation, Map<String, Table> tables, String schemaName) {
        String name = null;
        if (relation.left() != null) {
            String tableName = processingRelation(relation.left().getQuery(), tables, schemaName);
            String columnName = relation.left().getKey();
            Table t = tables.get(tableName);
            getColumnOrCreateNew(t.getColumns(), columnName, getSqlType(Type.INTEGER));
            name = tableName;
        }
        if (relation.right() != null) {
            String tableName = processingRelation(relation.right().getQuery(), tables, schemaName);
            String columnName = relation.right().getKey();
            Table t = tables.get(tableName);
            getColumnOrCreateNew(t.getColumns(), columnName, getSqlType(Type.INTEGER));
        }
        return name;
    }

    private static void processingLevel(MappingLevel level, Map<String, Table> tables, String tableName, String schema) {
        String tName = level.table() != null ? level.table() : tableName;
        if (tName != null) {
            Table t = getTableOrCreateNew(tables, tName, schema);
            Type type = Type.fromName(level.type() != null ? level.type().name() : null);
            if (level.column() != null && !level.column().isBlank()) {
                getColumnOrCreateNew(t.getColumns(), level.column(), getSqlType(type));
            }
            if (level.parentColumn() != null && !level.parentColumn().isBlank()) {
                getColumnOrCreateNew(t.getColumns(), level.parentColumn(), getSqlType(type));
            }
            if (level.nameColumn() != null && !level.nameColumn().isBlank()) {
                getColumnOrCreateNew(t.getColumns(), level.nameColumn(), getSqlType(Type.STRING));
            }
            if (level.captionColumn() != null && !level.captionColumn().isBlank()) {
                getColumnOrCreateNew(t.getColumns(), level.captionColumn(), getSqlType(Type.STRING));
            }
            if (level.ordinalColumn() != null && !level.ordinalColumn().isBlank()) {
                getColumnOrCreateNew(t.getColumns(), level.ordinalColumn(), getSqlType(Type.INTEGER));
            }
        }
        if (level.properties() != null) {
            level.properties().forEach(p -> processingProperty(p, tables, tName, schema));
        }

    }

    private static void processingProperty(MappingProperty property, Map<String, Table> tables, String tableName, String schema) {
        if (property.column() != null) {
            String columnName = property.column();
            if (tableName != null) {
                Table t = getTableOrCreateNew(tables, tableName, schema);
                getColumnOrCreateNew(t.getColumns(), columnName, getSqlType(property.type()));
            }
        }
    }

    private static Table getTableOrCreateNew(Map<String, Table> tables, String tableName, String schema) {
        Table table;
        if (!tables.containsKey(tableName)) {
            table = new Table(tableName, schema);
            tables.put(tableName, table);
        } else {
            table = tables.get(tableName);
        }
        return table;
    }

    private static Column getColumnOrCreateNew(Map<String, Column> columnsMap, String columnName, SqlType type) {
        Column column;
        if (!columnsMap.containsKey(columnName)) {
            column = new Column(columnName, type);
            columnsMap.put(columnName, column);
        } else {
            column = columnsMap.get(columnName);
        }
        return column;
    }

    private static void getConstraintOrCreateNew(Map<String, Constraint> constraintMap, String primaryKey, boolean unique, List<String> columnNames) {
        constraintMap.computeIfAbsent(primaryKey, k -> new Constraint(primaryKey, unique, columnNames));
    }

}
