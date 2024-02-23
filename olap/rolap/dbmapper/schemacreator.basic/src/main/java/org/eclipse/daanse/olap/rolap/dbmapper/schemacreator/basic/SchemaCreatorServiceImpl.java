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
package org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.basic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.eclipse.daanse.db.jdbc.metadata.api.JdbcMetaDataService;
import org.eclipse.daanse.db.jdbc.metadata.api.JdbcMetaDataServiceFactory;
import org.eclipse.daanse.db.jdbc.metadata.impl.Column;
import org.eclipse.daanse.db.jdbc.metadata.impl.ForeignKey;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.DimensionTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MeasureDataTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.CubeR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.DimensionUsageR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.HierarchyR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.LevelR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.MeasureR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.PrivateDimensionR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.SchemaR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.TableR;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaCreatorService;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaInitData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaCreatorServiceImpl implements SchemaCreatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaCreatorServiceImpl.class);
    private final Map<String, LevelTypeEnum> dateTypeMap =
        Map.of(
            "second", LevelTypeEnum.TIME_SECONDS,
            "minute", LevelTypeEnum.TIME_MINUTES,
            "day", LevelTypeEnum.TIME_DAYS,
            "week", LevelTypeEnum.TIME_WEEKS,
            "quarter", LevelTypeEnum.TIME_QUARTERS,
            "month", LevelTypeEnum.TIME_MONTHS,
            "year", LevelTypeEnum.TIME_YEARS
            );
    private final List<String> dateColumnNameList = List.of(
        "second", "minute", "day",
        "week", "quarter", "month", "year");


    private final JdbcMetaDataServiceFactory jmdsf;
    private final DataSource dataSource;

    public SchemaCreatorServiceImpl(DataSource dataSource, JdbcMetaDataServiceFactory jmdsf) {
        this.dataSource = dataSource;
        this.jmdsf = jmdsf;
    }

    @Override
    public MappingSchema createSchema(SchemaInitData sid) {
        try (Connection connection = dataSource.getConnection()) {
            JdbcMetaDataService jmds = jmdsf.create(connection);
            String schemaName = connection.getSchema();
            String description = schemaName;
            String measuresCaption = null;
            String defaultRole = null;
            List<MappingAnnotation> annotations = List.of();
            List<MappingParameter> parameters = List.of();
            Map<String, MappingPrivateDimension> sharedDimensionsMap = getSharedDimensions(schemaName, sid.getFactTables(),
                jmds);
            List<MappingPrivateDimension> sharedDimensions = new ArrayList<>(sharedDimensionsMap.values());
            List<MappingCube> cubes = getCubes(schemaName, sid.getFactTables(), sharedDimensionsMap, jmds);
            List<MappingVirtualCube> virtualCubes = List.of();
            List<MappingNamedSet> namedSets = List.of();
            List<MappingRole> roles = List.of();
            List<MappingUserDefinedFunction> userDefinedFunctions = List.of();

            return new SchemaR(schemaName,
                description,
                annotations,
                measuresCaption,
                defaultRole,
                parameters,
                sharedDimensions,
                cubes,
                virtualCubes,
                namedSets,
                roles,
                userDefinedFunctions,
                Optional.empty());

        } catch (SQLException e) {
            LOGGER.error("createSchema error");
            throw new SchemaCreatorException("createSchema error", e);
        }
    }

    private Map<String, MappingPrivateDimension> getSharedDimensions(
        String schemaName,
        List<String> factTables,
        JdbcMetaDataService jmds
    ) {

        if (factTables != null) {
            List<ForeignKey> list = factTables.stream()
                .flatMap(ft -> jmds.getForeignKeys(schemaName, ft).stream())
                .toList();
            if (!list.isEmpty()) {
                //get origen foreign key by Pk
                Map<String, ForeignKey> foreignKeyMap = list.stream().collect(
                    Collectors.toMap(this::getKey, fk -> fk,
                        (oldValue, newValue) -> oldValue
                    )
                );
                return foreignKeyMap.values()
                    .stream().collect(
                        Collectors.toMap(ForeignKey::getPkTableName, fk -> getSharedDimensions(schemaName, fk, jmds,
                            List.of(fk.getFkTableName())),
                            (oldValue, newValue) -> oldValue)
                    );

            }

        }
        return Map.of();
    }

    private String getKey(ForeignKey fk) {
        return new StringBuilder()
            .append(fk.getPkTableName())
            .append(fk.getPkColumnName())
            .toString();
    }

    private MappingPrivateDimension getSharedDimensions(
        String schemaName,
        ForeignKey fk,
        JdbcMetaDataService jmds,
        List<String> ignoreTables
    ) {
        List<MappingHierarchy> hierarchies = getHierarchies(schemaName, fk, jmds, ignoreTables);
        String description = new StringBuilder("Dimension for ").append(fk.getFkColumnName()).toString();
        return new PrivateDimensionR(
        		getDimensionName(fk),
        		description,
        		List.of(),
        		getDimensionCaption(fk),
        		true,
        		getDimensionType(schemaName,
        				fk.getPkTableName(),
        				fk.getPkColumnName(), jmds),
            null, //key is null for share dimension PkColumnName
            hierarchies,
            null);
    }

    private DimensionTypeEnum getDimensionType(String schemaName, String tableName, String columnName,  JdbcMetaDataService jmds) {
        try {
            Optional<Integer> optionalType = jmds.getColumnDataType(schemaName, tableName, columnName);
            TypeEnum type = getDatatype(optionalType);
            if (TypeEnum.TIME.equals(type)
                || TypeEnum.TIMESTAMP.equals(type)
                || TypeEnum.DATE.equals(type)) {
                return DimensionTypeEnum.TIME_DIMENSION;
            }
            if (TypeEnum.INTEGER.equals(type) && isFromDateColumnName(columnName)) {
                return DimensionTypeEnum.TIME_DIMENSION;
            }
            return  DimensionTypeEnum.STANDARD_DIMENSION;
        } catch (SQLException e) {
            throw new SchemaCreatorException("getDimensionType error", e);
        }
    }

    private boolean isFromDateColumnName(String columnName) {
        return dateColumnNameList.stream().anyMatch(c -> c.equals(columnName));
    }

    private String getDimensionCaption(ForeignKey fk) {
        return new StringBuilder().append(fk.getPkTableName()).toString();
    }

    private List<MappingHierarchy> getHierarchies(
        String schemaName,
        ForeignKey fk,
        JdbcMetaDataService jmds,
        List<String> ignoreTables
    ) {
        List<MappingRelationOrJoin> relationList = getHierarchyRelation(schemaName, fk, jmds, ignoreTables);
        List<MappingHierarchy> result = new ArrayList<>();
        Map<String, Integer> hierarchyNamesMap = new HashMap<>();
        for (MappingRelationOrJoin relation : relationList) {
            result.add(
                getHierarchy(schemaName, fk.getPkTableName(), fk.getPkColumnName(),
                    relation, hierarchyNamesMap, jmds)
            );
        }
        return result;
    }

    private List<MappingRelationOrJoin> getHierarchyRelation(
        String schemaName,
        ForeignKey fk,
        JdbcMetaDataService jmds,
        List<String> ignoreTables
    ) {

        List<ForeignKey> listFKAll = jmds.getForeignKeys(schemaName, fk.getPkTableName());
        List<ForeignKey> listFK = null;
        if (listFKAll != null && !listFKAll.isEmpty()) {
            listFK = listFKAll.stream()
                .filter(k -> ignoreTables.stream().noneMatch(t -> t.equals(k.getPkTableName())))
                .toList();
        }
        if (listFK != null && !listFK.isEmpty()) {
            List<MappingRelationOrJoin> result = new ArrayList<>();
            for (ForeignKey foreignKey : listFK) {
                List<String> ignoreTab = new ArrayList<>(ignoreTables);
                ignoreTab.add(foreignKey.getFkTableName());
                List<MappingRelationOrJoin> rightRelations = getHierarchyRelation(schemaName, foreignKey, jmds, ignoreTab);
                for (MappingRelationOrJoin relationOrJoin : rightRelations) {
                    List<MappingRelationOrJoin> relations = List.of(
                        new TableR(fk.getPkTableName()),
                        relationOrJoin);
                    result.add(new JoinR(relations,
                        null,
                        listFK.get(0).getFkColumnName(),
                        null,
                        foreignKey.getPkColumnName()));
                }
            }
            return result;
        }
        return List.of(new TableR(fk.getPkTableName()));
    }

    private List<MappingLevel> getHierarchyLevels(
        String schemaName,
        MappingRelationOrJoin relation,
        String tableName,
        String columnName,
        JdbcMetaDataService jmds
    ) {
        List<MappingLevel> result = new ArrayList<>();
        result.addAll(getHierarchyLevelsForJoin(schemaName, relation,
            tableName, columnName, jmds));
        result.addAll(getHierarchyLevelsForTable(schemaName, relation,
            tableName, columnName, jmds));
        return result;
    }

    private Collection<? extends MappingLevel> getHierarchyLevelsForTable(String schemaName,
                                                                   MappingRelationOrJoin relation,
                                                                   String tableName,
                                                                   String columnName,
                                                                   JdbcMetaDataService jmds
    ) {
        List<MappingLevel> result = new ArrayList<>();
        if (relation instanceof MappingTable table) {
            MappingLevel l = new LevelR(
            		getLevelName(tableName),
            		getLevelDescription(table.name()),
            		List.of(),
            		getLevelCaption(),
            		true,
                tableName,
                columnName,
                getColumnNameByPostfix(schemaName, table.name(), columnName, "name", jmds),
                getLevelOrdinalName(),
                getLevelParentColumn(),
                getLevelNullParentValue(),
                getLevelColumnType(schemaName, table.name(), columnName, jmds),
                getLevelApproxRowCount(),
                true,
                getLevelType(schemaName, table.name(), columnName, jmds),
                null,
                null,
                getLevelCaptionColumn(),
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(),
                null,
                null);
            result.add(l);
        }

        return result;
    }

    private Collection<? extends MappingLevel> getHierarchyLevelsForJoin(
        String schemaName,
        MappingRelationOrJoin relation,
        String tableName,
        String columnName,
        JdbcMetaDataService jmds
    ) {
        List<MappingLevel> result = new ArrayList<>();
        if (relation instanceof MappingJoin join && join.relations() != null && join.relations().size() > 1) {
            result.addAll(getHierarchyLevelsForJoinRight(schemaName,
                join.relations().get(1),
                tableName,
                jmds));
            if (join.relations().get(0) instanceof MappingTable t) {
                result.addAll(getHierarchyLevels(schemaName, t, tableName, columnName, jmds));
            }
        }
        return result;
    }

    private Collection<? extends MappingLevel> getHierarchyLevelsForJoinRight(
        String schemaName,
        MappingRelationOrJoin relationRight,
        String tableName,
        JdbcMetaDataService jmds
    ) {
        List<MappingLevel> result = new ArrayList<>();
        if (relationRight instanceof MappingTable t) {
            List<ForeignKey> listFK = jmds.getForeignKeys(schemaName, tableName);
            ForeignKey key =
                listFK.stream().filter(k -> t.name().equals(k.getPkTableName())).findFirst().orElse(null);
            if (key != null) {
                result.addAll(getHierarchyLevels(schemaName, t, key.getPkTableName(), key.getPkColumnName(),
                    jmds));
            }
        } else if (relationRight instanceof MappingJoin j) {
            MappingTable t = getFistTable(j);
            if (t != null) {
                List<ForeignKey> listFK = jmds.getForeignKeys(schemaName, tableName);
                ForeignKey key =
                    listFK.stream().filter(k -> t.name().equals(k.getPkTableName())).findFirst().orElse(null);
                if (key != null) {
                    result.addAll(getHierarchyLevels(schemaName, j, key.getPkTableName(),
                        key.getPkColumnName(), jmds));
                }
            }
        }
        return result;
    }

    private String getLevelName(String tableName) {
        return capitalize(tableName);
    }

    private MappingTable getFistTable(MappingJoin j) {
        if (j.relations().get(0) instanceof MappingTable t) {
            return t;
        }
        if (j.relations().get(1) instanceof MappingTable t) {
            return t;
        }
        if (j.relations().get(0) instanceof MappingJoin join) {
            return getFistTable(join);
        }
        return null;
    }

    private String getLevelCaptionColumn() {
        return null;
    }

    private String getLevelDescription(String table) {
        return capitalize(table);
    }

    private String getLevelCaption() {
        return null;
    }

    private LevelTypeEnum getLevelType(String schemaName, String tableName, String columnName, JdbcMetaDataService jmds) {
        try {
            TypeEnum type = getDatatype(jmds.getColumnDataType(schemaName, tableName, columnName));
            if (TypeEnum.DATE.equals(type)) {
                return LevelTypeEnum.TIME_DAYS;
            }
            if (TypeEnum.INTEGER.equals(type) && isFromDateColumnName(columnName)) {
                return dateTypeMap.get(columnName);
            }
        } catch (SQLException e) {
            throw new SchemaCreatorException("getLevelType error", e);
        }
        return null;
    }

    private String getLevelApproxRowCount() {
        return null;
    }

    private TypeEnum getLevelColumnType(String schemaName, String table, String columnName, JdbcMetaDataService jmds) {
        try {
            Optional<Integer> type = jmds.getColumnDataType(schemaName, table, columnName);
            return getDatatype(type);
        } catch (SQLException e) {
            throw new SchemaCreatorException("getLevelColumnType error", e);
        }
    }

    private String getLevelNullParentValue() {
        return null;
    }

    private String getLevelParentColumn() {
        return null;
    }

    private String getLevelOrdinalName() {
        return null;
    }

    private String getColumnNameByPostfix(
        String schemaName, String tableName, String columnName, String postfix, JdbcMetaDataService jmds
    ) {
        try {
            if (jmds.doesColumnExist(schemaName, tableName, postfix)) {
                return postfix;
            }
            String cName = new StringBuilder(columnName).append("_").append(postfix).toString();
            if (jmds.doesColumnExist(schemaName, tableName, cName)) {
                return cName;
            }
        } catch (SQLException e) {
            throw new SchemaCreatorException("ColumnExist error", e);
        }
        return null;
    }

    private String getHierarchyDescription(String tableName, String columnName) {
        return new StringBuilder("Description for hierarchy")
            .append(tableName)
            .append("_")
            .append(columnName).toString();
    }

    private String getHierarchyCaption(String tableName, String columnName) {
        return new StringBuilder("Caption for hierarchy")
            .append(tableName)
            .append("_")
            .append(columnName).toString();
    }

    private String getHierarchyName(String tableName, String columnName, Map<String, Integer> hierarchyNamesMap) {
        String name = new StringBuilder(tableName)
            .append("_")
            .append(columnName).toString();
        Integer index  = hierarchyNamesMap.computeIfAbsent(name, k -> 0);
        if (index > 0) {
            hierarchyNamesMap.put(name, index + 1);
            return new StringBuilder(name).append("_")
                .append(index).toString();
        }
        hierarchyNamesMap.put(name, index + 1);
        return name;
    }

    private String getDimensionName(ForeignKey fk) {
        return new StringBuilder("Dimension ").append(capitalize(fk.getPkTableName())).toString();
    }

    private List<MappingCube> getCubes(
        String schemaName,
        List<String> tables,
        Map<String, MappingPrivateDimension> sharedDimensionsMap,
        JdbcMetaDataService jmds
    ) {
        if (tables != null) {
            return tables.stream().map(t -> getCube(schemaName, t, sharedDimensionsMap, jmds)).toList();
        }
        return List.of();
    }

    private MappingCube getCube(
        String schemaName,
        String tableName,
        Map<String, MappingPrivateDimension> sharedDimensionsMap,
        JdbcMetaDataService jmds
    ) {
        String name = getCubName(tableName);
        String caption = getCubCaption(tableName);
        String description = getCubDescription(tableName);
        String defaultMeasure = null;
        List<MappingCubeDimension> dimensionUsageOrDimensions = getCubeDimensions(schemaName, tableName, sharedDimensionsMap
            , jmds);
        List<MappingMeasure> measures = getMeasures(schemaName, tableName, jmds);
        MappingRelation fact = new TableR(tableName);
        return new CubeR(name,
        		description,
        		List.of(),
            caption,
            true,
            defaultMeasure,
            dimensionUsageOrDimensions,
            measures,
            List.of(),
            List.of(),
    		List.of(),
            List.of(),
            true,
            true,
            fact,
            List.of());
    }

    private String getCubDescription(String tableName) {
        return capitalize(tableName);
    }

    private String getCubName(String tableName) {
        return capitalize(tableName);
    }

    private String getCubCaption(String tableName) {
        return capitalize(tableName);
    }

    private List<MappingMeasure> getMeasures(String schemaName, String tableName, JdbcMetaDataService jmds) {
        List<MappingMeasure> result = new ArrayList<>();
        List<Column> columns = jmds.getColumns(schemaName, tableName);
        // cub Measures for numeric fields sum and count
        List<ForeignKey> foreignKeyList = jmds.getForeignKeys(schemaName, tableName);
        if (columns != null && !columns.isEmpty()) {
            for (Column column : columns) {
                if (isNumericType(column.getType())
                    && (foreignKeyList == null ||
                    foreignKeyList.stream().noneMatch(fk -> fk.getFkColumnName().equals(column.getName())))) {
                    //<Measure name="Unit Sales" column="unit_sales" aggregator="sum" formatString="Standard"/>
                    result.add(new MeasureR(
                    	getMeasureName(column.getName()),
                    	getMeasureDescription(column.getName()),
                    	List.of(),
                    	"Standard",
                    	true,
                        column.getName(),
                        getMeasureDataType(column.getType()),
                        null,
                        "sum",
                        column.getName(),
                        null,
                        null,
                        List.of(),
                        null,
                        null)
                    );
                }
            }
        }

        return result;
    }

    private MeasureDataTypeEnum getMeasureDataType(Integer type) {
        switch (type) {
            case Types.TINYINT, Types.SMALLINT, Types.INTEGER:
                return MeasureDataTypeEnum.INTEGER;
            case Types.FLOAT, Types.REAL, Types.DOUBLE, Types.NUMERIC, Types.DECIMAL, Types.BIGINT:
                return MeasureDataTypeEnum.NUMERIC;
            default:
                return MeasureDataTypeEnum.STRING;
        }
    }

    private String getMeasureDescription(String columnName) {
        return columnName;
    }

    private String getMeasureName(String columnName) {
        return columnName;
    }

    private List<MappingCubeDimension> getCubeDimensions(
        String schemaName,
        String tableName,
        Map<String, MappingPrivateDimension> sharedDimensionsMap,
        JdbcMetaDataService jmds
    ) {
        List<MappingCubeDimension> result = new ArrayList<>();
        List<ForeignKey> foreignKeyList = jmds.getForeignKeys(schemaName, tableName);
        if (foreignKeyList != null && !foreignKeyList.isEmpty()) {
            // cub dimension usage for fields with foreign keys
            result.addAll(getCubDimensionUsage(foreignKeyList, sharedDimensionsMap));
        }
        List<Column> columns = jmds.getColumns(schemaName, tableName);
        // cub dimension for not numeric fields
        result.addAll(getCubDimensionForNotNumericFields(schemaName, tableName, columns, foreignKeyList, jmds));

        return result;
    }

    private Collection<? extends MappingCubeDimension> getCubDimensionForNotNumericFields(
        String schemaName, String tableName,
        List<Column> columns, List<ForeignKey> foreignKeyList, JdbcMetaDataService jmds
    ) {
        List<MappingCubeDimension> result = new ArrayList<>();
        if (columns != null && !columns.isEmpty()) {
            Map<String, Integer> hierarchyNamesMap = new HashMap<>();
            for (Column column : columns) {
                if (!isNumericType(column.getType())
                    && (foreignKeyList == null ||
                    foreignKeyList.stream().noneMatch(fk -> fk.getFkColumnName().equals(column.getName())))) {
                    List<MappingHierarchy> hierarchyList = List.of(getHierarchy(schemaName, tableName,
                        column.getName(), new TableR(tableName), hierarchyNamesMap, jmds));
                    result.add(new PrivateDimensionR(
                    		column.getName(),
                    		null,
                    		List.of(),
                    		column.getName(),
                    		true,
                        getDimensionType(schemaName, tableName, column.getName(), jmds),
                        column.getName(),
                        hierarchyList,
                        null));
                }
            }
        }
        return result;
    }

    private MappingHierarchy getHierarchy(String schemaName, String tableName,
                                   String columnName, MappingRelationOrJoin relation,
                                   Map<String, Integer> hierarchyNamesMap, JdbcMetaDataService jmds) {

        return new HierarchyR(
        		getHierarchyName(tableName, columnName, hierarchyNamesMap),
        		getHierarchyDescription(tableName, columnName),
        		List.of(),
        		getHierarchyCaption(tableName, columnName),
        		true,
            getHierarchyLevels(schemaName, relation, tableName, columnName, jmds),
            List.of(),
            true,
            null,
            null,
            null,
            columnName,
            null,
            null,
            null,
            null,
            null,
            relation,
            null);
    }

    private Collection<? extends MappingCubeDimension> getCubDimensionUsage(
        List<ForeignKey> foreignKeyList,
        Map<String, MappingPrivateDimension> sharedDimensionsMap
    ) {
        List<MappingCubeDimension> result = new ArrayList<>();
        for (ForeignKey foreignKey : foreignKeyList) {
            if (sharedDimensionsMap.containsKey(foreignKey.getPkTableName())) {
                MappingPrivateDimension privateDimension = sharedDimensionsMap.get(foreignKey.getPkTableName());
                result.add(new DimensionUsageR(
                		getDimensionName(foreignKey),
                		privateDimension.description(),
                		privateDimension.annotations(),
                		privateDimension.caption(),
                        privateDimension.visible(),
                    privateDimension.name(),
                    null,
                    privateDimension.usagePrefix(),
                    foreignKey.getFkColumnName()));
            }
        }
        return result;
    }

    private String capitalize(String str) {
        if (str != null && !str.isBlank()) {
            return new StringBuilder(str.substring(0, 1).toUpperCase())
                .append(str.substring(1)).toString();
        }
        return str;
    }

    private TypeEnum getDatatype(Optional<Integer> optionalType) {
        Integer type = optionalType.orElseThrow(
            () -> new SchemaCreatorException("getLevelColumnType error type is absent")
        );
        switch (type) {
            case Types.TINYINT, Types.SMALLINT, Types.INTEGER:
                return TypeEnum.INTEGER;
            case Types.FLOAT, Types.REAL, Types.DOUBLE, Types.NUMERIC, Types.DECIMAL, Types.BIGINT:
                return TypeEnum.NUMERIC;
            case Types.BOOLEAN:
                return TypeEnum.BOOLEAN;
            case Types.DATE:
                return TypeEnum.DATE;
            case Types.TIME:
                return TypeEnum.TIME;
            case Types.TIMESTAMP:
                return TypeEnum.TIMESTAMP;
            case Types.CHAR, Types.VARCHAR:
            default:
                return TypeEnum.STRING;
        }
    }

    private boolean isNumericType(int javaType) {
        switch (javaType) {
            case Types.TINYINT, Types.SMALLINT, Types.INTEGER:
                return true;
            case Types.FLOAT, Types.REAL, Types.DOUBLE, Types.NUMERIC, Types.DECIMAL, Types.BIGINT:
                return true;
            default:
                return false;
        }
    }
}
