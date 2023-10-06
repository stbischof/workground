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
package org.eclipse.daanse.olap.xmla.bridge.discover;

import mondrian.olap.DimensionType;
import mondrian.olap.MondrianProperties;
import mondrian.olap.Util;
import mondrian.rolap.RolapLevel;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMemberProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelUniqueSettingsEnum;
import org.eclipse.daanse.xmla.api.common.enums.MeasureAggregatorEnum;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresResponseRowR;
import org.olap4j.metadata.Property;
import org.olap4j.metadata.XmlaConstants;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
        // constructor
    }

    static Optional<String> getRoles(List<MappingRole> roles) {
        if (roles != null) {
            return Optional.of(roles.stream().map(MappingRole::name).collect(Collectors.joining(",")));
        }
        return Optional.empty();
    }

    static List<DbSchemaColumnsResponseRow> getDbSchemaColumnsResponseRow(
        Context context,
        Optional<String> oTableSchema,
        Optional<String> oTableName,
        Optional<String> oColumnName,
        Optional<ColumnOlapTypeEnum> oColumnOlapType
    ) {

        List<DatabaseMappingSchemaProvider> schemas =
            getDatabaseMappingSchemaProviderWithFilter(context.getDatabaseMappingSchemaProviders(), oTableSchema);
        return schemas.stream().map(dsp -> {
            MappingSchema schema = dsp.get();
            return getDbSchemaColumnsResponseRow(context.getName(), schema, oTableName, oColumnName, oColumnOlapType,
                schema.dimensions());
        }).flatMap(Collection::stream).toList();
    }

    static List<DbSchemaColumnsResponseRow> getDbSchemaColumnsResponseRow(
        String catalogName,
        MappingSchema schema,
        Optional<String> oTableName,
        Optional<String> oColumnName,
        Optional<ColumnOlapTypeEnum> oColumnOlapType,
        List<MappingPrivateDimension> dimensions
    ) {
        return schema.cubes().stream().sorted(Comparator.comparing(MappingCube::name))
            .map(c -> getDbSchemaColumnsResponseRow(catalogName, schema.name(), c, oTableName, oColumnName,
                oColumnOlapType, schema.dimensions()))
            .flatMap(Collection::stream).toList();
    }

    static List<DbSchemaColumnsResponseRow> getDbSchemaColumnsResponseRow(
        String catalogName,
        String schemaName,
        MappingCube cube,
        Optional<String> oTableName,
        Optional<String> oColumnName,
        Optional<ColumnOlapTypeEnum> oColumnOlapType,
        List<MappingPrivateDimension> dimensions
    ) {
        int ordinalPosition = 1;
        List<DbSchemaColumnsResponseRow> result = new ArrayList<>();
        if (!oTableName.isPresent() || (oTableName.isPresent() && oTableName.get().equals(cube.name()))) {
            final boolean emitInvisibleMembers = true; //TODO
            for (MappingCubeDimension dimension : cube.dimensionUsageOrDimensions()) {
                if (dimension instanceof MappingPrivateDimension pd) {
                    populateDimensionForDbSchemaColumns(
                        catalogName,
                        schemaName,
                        cube, pd,
                        ordinalPosition, result);
                }
                if (dimension instanceof MappingDimensionUsage du) {
                    Optional<MappingPrivateDimension> od =
                        dimensions.stream().filter(d -> d.name().equals(du.source())).findFirst();
                    if (od.isPresent()) {
                        populateDimensionForDbSchemaColumns(
                            catalogName,
                            schemaName,
                            cube, od.get(),
                            ordinalPosition, result);
                    }
                }
            }
            for (MappingMeasure measure : cube.measures()) {

                Boolean visible = true;
                Optional<MappingCalculatedMemberProperty> oP = measure.calculatedMemberProperties()
                    .stream()
                    .filter(p -> "$visible".equals(p.name())).findFirst();
                if (oP.isPresent()) {
                    visible = Boolean.valueOf(oP.get().value());
                }
                if (!emitInvisibleMembers && !visible) {
                    continue;
                }

                String memberName = measure.name();
                final String columnName = "Measures:" + memberName;
                if (oColumnName.isPresent() && oColumnName.get().equals(columnName)) {
                    continue;
                }
                String cubeName = cube.name();
                result.add(new DbSchemaColumnsResponseRowR(
                    Optional.of(cubeName),
                    Optional.of(schemaName),
                    Optional.of(cubeName),
                    Optional.of(columnName),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(ordinalPosition++),
                    Optional.of(false),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(false),
                    Optional.of(XmlaConstants.DBType.R8.xmlaOrdinal()),
                    Optional.empty(),
                    Optional.of(0),
                    Optional.of(0),
                    Optional.of(16),
                    Optional.of(255),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()
                ));
            }
        }

        return result;
    }

    private static void populateDimensionForDbSchemaColumns(
        String catalogName,
        String schemaName,
        MappingCube cube,
        MappingPrivateDimension dimension,
        int ordinalPosition,
        List<DbSchemaColumnsResponseRow> result
    ) {
        for (MappingHierarchy hierarchy : dimension.hierarchies()) {
            ordinalPosition =
                populateHierarchyForDbSchemaColumns(
                    catalogName,
                    schemaName,
                    cube, hierarchy,
                    ordinalPosition, result);
        }

    }

    static int populateHierarchyForDbSchemaColumns(
        String catalogName,
        String schemaName,
        MappingCube cube,
        MappingHierarchy hierarchy,
        int ordinalPosition,
        List<DbSchemaColumnsResponseRow> result
    ) {
        String cubeName = cube.name();
        String hierarchyName = hierarchy.name();
        result.add(new DbSchemaColumnsResponseRowR(
            Optional.of(catalogName),
            Optional.of(schemaName),
            Optional.of(cubeName),
            Optional.of(new StringBuilder(hierarchyName).append(":(All)!NAME").toString()),
            Optional.empty(),
            Optional.empty(),
            Optional.of(ordinalPosition++),
            Optional.of(false),
            Optional.empty(),
            Optional.empty(),
            Optional.of(false),
            Optional.of(XmlaConstants.DBType.WSTR.xmlaOrdinal()),
            Optional.empty(),
            Optional.of(0),
            Optional.of(0),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        ));

        result.add(new DbSchemaColumnsResponseRowR(
            Optional.of(schemaName),
            Optional.of(schemaName),
            Optional.of(cubeName),
            Optional.of(new StringBuilder(hierarchyName).append(":(All)!UNIQUE_NAME").toString()),
            Optional.empty(),
            Optional.empty(),
            Optional.of(ordinalPosition++),
            Optional.of(false),
            Optional.empty(),
            Optional.empty(),
            Optional.of(false),
            Optional.of(XmlaConstants.DBType.WSTR.xmlaOrdinal()),
            Optional.empty(),
            Optional.of(0),
            Optional.of(0),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        ));
        return ordinalPosition;
    }

    static List<DatabaseMappingSchemaProvider> getDatabaseMappingSchemaProviderWithFilter(
        List<DatabaseMappingSchemaProvider> databaseMappingSchemaProviders,
        Optional<String> oSchemaName
    ) {
        if (oSchemaName.isPresent()) {
            return databaseMappingSchemaProviders.stream().filter(dmsp -> dmsp.get().name().equals(oSchemaName.get())).toList();
        }
        return databaseMappingSchemaProviders;
    }

    static List<DbSchemaSchemataResponseRow> getDbSchemaSchemataResponseRow(
        Context context,
        String schemaName,
        String schemaOwner
    ) {
        List<DatabaseMappingSchemaProvider> schemas = context.getDatabaseMappingSchemaProviders();
        if (schemas != null) {
            return getDatabaseMappingSchemaProviderWithFilter(schemas, schemaName).stream()
                .filter(dmsp -> (dmsp != null && dmsp.get() != null))
                .map(dmsp -> getDbSchemaSchemataResponseRow(context.getName(), dmsp, schemaOwner)).toList();
        }
        return List.of();
    }

    static DbSchemaSchemataResponseRow getDbSchemaSchemataResponseRow(
        String catalogName,
        DatabaseMappingSchemaProvider dmsp,
        String schemaOwner
    ) {
        MappingSchema schema = dmsp.get();
        return new DbSchemaSchemataResponseRowR(
            catalogName,
            schema.name(),
            "");
    }

    static List<DatabaseMappingSchemaProvider> getDatabaseMappingSchemaProviderWithFilter(
        List<DatabaseMappingSchemaProvider> schemas,
        String schemaName
    ) {
        if (schemaName != null) {
            return schemas.stream().filter(dmsp -> dmsp.get().name().equals(schemaName)).toList();
        }
        return schemas;
    }

    static List<MdSchemaCubesResponseRow> getMdSchemaCubesResponseRow(
        Context context,
        Optional<String> schemaName,
        Optional<String> cubeName,
        Optional<String> baseCubeName,
        Optional<CubeSourceEnum> cubeSource
    ) {
        List<DatabaseMappingSchemaProvider> schemas = context.getDatabaseMappingSchemaProviders();
        if (schemas != null) {
            return getDatabaseMappingSchemaProviderWithFilter(schemas, schemaName).stream()
                .filter(dmsp -> (dmsp != null && dmsp.get() != null))
                .map(dmsp -> getMdSchemaCubesResponseRow(context.getName(), dmsp.get(), cubeName, baseCubeName,
                    cubeSource))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    private static List<MdSchemaCubesResponseRow> getMdSchemaCubesResponseRow(
        String catalogName,
        MappingSchema schema,
        Optional<String> cubeName,
        Optional<String> baseCubeName,
        Optional<CubeSourceEnum> cubeSource
    ) {
        if (schema.cubes() != null) {
            return Utils.getMappingCubeWithFilter(schema.cubes(), cubeName).stream().
                map(c -> getMdSchemaCubesResponseRow(catalogName, schema.name(), c)).
                flatMap(Collection::stream).toList();
        }
        return List.of();
    }

    private static List<MdSchemaCubesResponseRow> getMdSchemaCubesResponseRow(
        String catalogName,
        String schemaName,
        MappingCube cube
    ) {
        List<MdSchemaCubesResponseRow> result = new ArrayList<>();
        if (cube != null) {
            if (cube.visible()) {
                String desc = cube.description();
                if (desc == null) {
                    desc = new StringBuilder(catalogName)
                        .append(" Schema - ")
                        .append(cube.name())
                        .append(" Cube")
                        .toString();
                }
                new MdSchemaCubesResponseRowR(
                    catalogName,
                    Optional.of(schemaName),
                    Optional.of(cube.name()),
                    Optional.of(CubeTypeEnum.CUBE), //TODO get cube type from olap
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(), //TODO get create date from olap
                    Optional.empty(),
                    Optional.of(LocalDateTime.now()),
                    Optional.empty(),
                    Optional.of(desc),
                    Optional.of(true),
                    Optional.of(false),
                    Optional.of(false),
                    Optional.of(false),
                    Optional.of(cube.caption()),
                    Optional.empty(),
                    Optional.of(CubeSourceEnum.CUBE),
                    Optional.empty()
                );
            }
        }
        return List.of();
    }

    private static List<MappingCube> getMappingCubeWithFilter(List<MappingCube> cubes, Optional<String> cubeName) {
        if (cubeName.isPresent()) {
            return cubes.stream().filter(c -> cubeName.get().equals(c.name())).toList();
        }
        return cubes;
    }

    static boolean isDataTypeCond(XmlaConstants.DBType wstr, Optional<LevelDbTypeEnum> oLevelDbType) {
        if (oLevelDbType.isPresent()) {
            return wstr.xmlaOrdinal() == oLevelDbType.get().ordinal();
        }
        return true;
    }

    static List<DbSchemaTablesResponseRow> getDbSchemaTablesResponseRow(
        Context context,
        Optional<String> oTableSchema,
        Optional<String> oTableName,
        Optional<String> oTableType
    ) {
        getDatabaseMappingSchemaProviderWithFilter(context.getDatabaseMappingSchemaProviders(), oTableSchema)
            .stream().filter(dmsp -> dmsp != null && dmsp.get() != null)
            .map(dmsp -> getDbSchemaTablesResponseRow(context.getName(), dmsp.get(), oTableName, oTableType))
            .flatMap(Collection::stream).toList();
        return List.of();
    }

    private static List<DbSchemaTablesResponseRow> getDbSchemaTablesResponseRow(
        String catalogName,
        MappingSchema schema,
        Optional<String> oTableName,
        Optional<String> oTableType
    ) {
        return Utils.getMappingCubeWithFilter(schema.cubes(), oTableName).stream()
            .map(c -> getDbSchemaTablesResponseRow(catalogName, schema.name(), c, oTableType, schema.dimensions()))
            .flatMap(Collection::stream).toList();
    }

    private static List<DbSchemaTablesResponseRow> getDbSchemaTablesResponseRow(
        String catalogName,
        String schemaName,
        MappingCube cube,
        Optional<String> oTableType,
        List<MappingPrivateDimension> dimensions
    ) {
        List<DbSchemaTablesResponseRow> result = new ArrayList<>();
        if (cube != null) {
            String desc = cube.description();
            if (desc == null) {
                desc =
                    new StringBuilder(catalogName).append(" - ")
                        .append(cube.name()).append(" Cube").toString();
            }
            if (isTableType(oTableType, "TABLE")) {
                result.add(new DbSchemaTablesResponseRowR(
                    Optional.of(catalogName),
                    Optional.of(schemaName),
                    Optional.of(cube.name()),
                    Optional.of("TABLE"),
                    Optional.empty(),
                    Optional.of(desc),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()
                ));
            }
            if (isTableType(oTableType, "SYSTEM TABLE")) {
                for (MappingCubeDimension dimension : cube.dimensionUsageOrDimensions()) {
                    if (dimension instanceof MappingPrivateDimension mappingPrivateDimension) {
                        populateDimensionForDbSchemaTables(
                            catalogName,
                            schemaName,
                            cube,
                            mappingPrivateDimension,
                            result
                        );
                    }
                    if (dimension instanceof MappingDimensionUsage mappingDimensionUsage) {
                        Optional<MappingPrivateDimension> od =
                            dimensions.stream().filter(d -> d.name().equals(mappingDimensionUsage.source())).findFirst();
                        if (od.isPresent()) {
                            populateDimensionForDbSchemaTables(
                                catalogName,
                                schemaName,
                                cube,
                                od.get(),
                                result
                            );
                        }
                    }
                }
            }
        }
        return result;

    }

    private static void populateDimensionForDbSchemaTables(
        String catalogName,
        String schemaName,
        MappingCube cube,
        MappingPrivateDimension dimension,
        List<DbSchemaTablesResponseRow> result
    ) {
        if (dimension != null) {
            for (MappingHierarchy hierarchy
                : dimension.hierarchies()) {
                populateHierarchyForDbSchemaTables(
                    catalogName,
                    schemaName,
                    cube,
                    dimension.name(),
                    hierarchy,
                    result);
            }
        }
    }

    private static void populateHierarchyForDbSchemaTables(
        String catalogName,
        String schemaName,
        MappingCube cube,
        String dimensionName,
        MappingHierarchy hierarchy,
        List<DbSchemaTablesResponseRow> result
    ) {
        if (hierarchy.levels() != null) {
            result.addAll(hierarchy.levels().stream().map(l -> {
                String cubeName = cube.name();
                String hierarchyName = getHierarchyName(hierarchy.name(), dimensionName);
                String levelName = l.name();

                String tableName =
                    cubeName + ':' + hierarchyName + ':' + levelName;

                String desc = l.description();
                if (desc == null) {
                    desc =
                        new StringBuilder(schemaName).append(" - ")
                            .append(cubeName).append(" Cube - ")
                            .append(hierarchyName).append(" Hierarchy - ")
                            .append(levelName).append(" Level").toString();
                }

                return new DbSchemaTablesResponseRowR(
                    Optional.of(catalogName),
                    Optional.of(schemaName),
                    Optional.of(tableName),
                    Optional.of("SYSTEM TABLE"),
                    Optional.empty(),
                    Optional.of(desc),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());

            }).toList());
        }
    }

    private static String getHierarchyName(String hierarchyName, String dimensionName) {
        //TODO use Properties from context
        if (MondrianProperties.instance().SsasCompatibleNaming.get()
            && !hierarchyName.equals(dimensionName)) {
            hierarchyName =
                dimensionName + "." + hierarchyName;
        }
        return hierarchyName;
    }

    private static boolean isTableType(Optional<String> oTableType, String type) {
        if (oTableType.isPresent()) {
            return oTableType.get().equals(type);
        }
        return true;
    }

    static List<DbSchemaTablesInfoResponseRow> getDbSchemaTablesInfoResponseRow(
        Context context,
        Optional<String> oSchemaName,
        String tableName,
        TableTypeEnum tableType
    ) {
        return getDatabaseMappingSchemaProviderWithFilter(context.getDatabaseMappingSchemaProviders(), oSchemaName)
            .stream().filter(dmsp -> (dmsp != null && dmsp.get() != null))
            .map(dmsp -> getDbSchemaTablesInfoResponseRow(context.getName(), dmsp.get(), tableName, tableType))
            .flatMap(Collection::stream).toList();
    }

    private static List<DbSchemaTablesInfoResponseRow> getDbSchemaTablesInfoResponseRow(
        String catalogName, MappingSchema schema, String tableName, TableTypeEnum tableType
    ) {
        if (schema.cubes() != null) {
            return schema.cubes().stream()
                .sorted(Comparator.comparing(MappingCube::name))
                .map(c -> getDbSchemaTablesInfoResponseRow(catalogName, schema.name(), c, tableName, tableType))
                .toList();
        }
        return List.of();
    }

    private static DbSchemaTablesInfoResponseRow getDbSchemaTablesInfoResponseRow(
        String catalogName, String schemaName, MappingCube cube, String tableName, TableTypeEnum tableType
    ) {
        String cubeName = cube.name();
        String desc = cube.description();
        if (desc == null) {
            desc = catalogName + " - " + cubeName + " Cube";
        }
        //TODO: SQL Server returns 1000000 for all tables
        long cardinality = 1000000l;
        return new DbSchemaTablesInfoResponseRowR(
            Optional.of(catalogName),
            Optional.of(schemaName),
            cubeName,
            TableTypeEnum.TABLE.name(),
            Optional.empty(),
            Optional.of(false),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(cardinality),
            Optional.of(desc),
            Optional.empty()
        );
    }

    static List<MdSchemaLevelsResponseRow> getMdSchemaLevelsResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelName,
        Optional<String> oLevelUniqueName,
        Optional<VisibilityEnum> oLevelVisibility
    ) {
        return getSchemasWithFilter(context.getConnection().getSchemas(), oSchemaName)
            .stream()
            .map(s -> getMdSchemaLevelsResponseRow(context.getName(), s, oCubeName, oDimensionUniqueName,
                oHierarchyUniqueName, oLevelName, oLevelUniqueName, oLevelVisibility))
            .flatMap(Collection::stream).toList();
    }

    private static List<Schema> getSchemasWithFilter(List<Schema> schemas, Optional<String> oSchemaName) {
        if (oSchemaName.isPresent()) {
            schemas.stream().filter(s -> oSchemaName.get().equals(s.getName())).toList();
        }
        return schemas;
    }

    private static List<MdSchemaLevelsResponseRow> getMdSchemaLevelsResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelName,
        Optional<String> oLevelUniqueName,
        Optional<VisibilityEnum> oLevelVisibility
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName)
            .stream()
            .sorted(Comparator.comparing(Cube::getName))
            .map(cube -> getMdSchemaLevelsResponseRow(
                catalogName,
                schema.getName(),
                cube,
                oDimensionUniqueName,
                oHierarchyUniqueName,
                oLevelName,
                oLevelUniqueName,
                oLevelVisibility))
            .flatMap(Collection::stream).toList();
    }

    private static List<Cube> getCubesWithFilter(List<Cube> cubes, Optional<String> oCubeName) {
        if (oCubeName.isPresent()) {
            cubes.stream().filter(c -> oCubeName.get().equals(c.getName())).toList();
        }
        return cubes;
    }

    private static List<MdSchemaLevelsResponseRow> getMdSchemaLevelsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelName,
        Optional<String> oLevelUniqueName,
        Optional<VisibilityEnum> oLevelVisibility
    ) {
        List<Dimension> dimensions = cube.getDimensions() == null ? List.of() : Arrays.asList(cube.getDimensions());
        return getDimensionsWithFilter(dimensions, oDimensionUniqueName)
            .stream()
            .map(d -> getMdSchemaLevelsResponseRow(catalogName, schemaName, cube.getName(), d, oHierarchyUniqueName,
                oLevelName, oLevelUniqueName,
                oLevelVisibility))
            .flatMap(Collection::stream).toList();
    }

    private static List<Dimension> getDimensionsWithFilter(List<Dimension> dimensions, Optional<String> oDimensionUniqueName) {
        if (oDimensionUniqueName.isPresent()) {
            dimensions.stream().filter(d -> oDimensionUniqueName.get().equals(d.getUniqueName())).toList();
        }
        return dimensions;
    }

    private static List<MdSchemaLevelsResponseRow> getMdSchemaLevelsResponseRow(
        String catalogName,
        String schemaName,
        String cubeName,
        Dimension dimension,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelName,
        Optional<String> oLevelUniqueName,
        Optional<VisibilityEnum> oLevelVisibility
    ) {
        List<Hierarchy> hierarchies =  dimension.getHierarchies() == null ? List.of() : Arrays.asList(dimension.getHierarchies());
        return getHierarchiesWithFilter1(hierarchies, oHierarchyUniqueName)
            .stream().map(h -> getMdSchemaLevelsResponseRow(
                catalogName,
                schemaName,
                cubeName,
                dimension.getName(),
                h,
                oLevelName,
                oLevelUniqueName,
                oLevelVisibility)).flatMap(Collection::stream).toList();
    }

    private static List<Hierarchy> getHierarchiesWithFilter1(List<Hierarchy> hierarchies, Optional<String> oHierarchyUniqueName) {
        if (oHierarchyUniqueName.isPresent()) {
            hierarchies.stream().filter(h -> oHierarchyUniqueName.get().equals(h.getUniqueName())).toList();
        }
        return hierarchies;
    }

    private static List<MdSchemaLevelsResponseRow> getMdSchemaLevelsResponseRow(
        String catalogName,
        String schemaName,
        String cubeName,
        String dimensionUniqueName,
        Hierarchy h,
        Optional<String> oLevelName,
        Optional<String> oLevelUniqueName,
        Optional<VisibilityEnum> oLevelVisibility
    ) {
        List<Level> levels = h.getLevels() == null ? List.of() : Arrays.asList(h.getLevels());
        return getLevelsWithFilterByUniqueName(getLevelsWithFilterByName(levels, oLevelName), oLevelUniqueName)
            .stream()
            .map(level -> {
                String desc = level.getDescription();
                if (desc == null) {
                    desc =
                        cubeName + " Cube - "
                            + getHierarchyName(h.getName(), dimensionUniqueName) + " Hierarchy - "
                            + level.getName() + " Level";
                }

                int uniqueSettings = 0;
                if (level.isAll()) {
                    uniqueSettings |= 2;
                }
                if (level instanceof RolapLevel rolapLevel && rolapLevel.isUnique()) {
                    uniqueSettings |= 1;
                }
                // Get level cardinality
                // According to microsoft this is:
                //   "The number of members in the level."
                //int levelCardinality = extra.getLevelCardinality(level); //TODO
                int levelCardinality = 1; //TODO

                return (MdSchemaLevelsResponseRow)new MdSchemaLevelsResponseRowR(
                    Optional.ofNullable(catalogName),
                    Optional.ofNullable(schemaName),
                    Optional.ofNullable(cubeName),
                    Optional.ofNullable(dimensionUniqueName),
                    Optional.ofNullable(h.getName()),
                    Optional.ofNullable(level.getName()),
                    Optional.ofNullable(level.getUniqueName()),
                    Optional.empty(),
                    Optional.ofNullable(level.getCaption()),
                    Optional.ofNullable(level.getDepth()),
                    Optional.of(levelCardinality),
                    Optional.of(getLevelType(level)),
                    Optional.ofNullable(desc),
                    Optional.empty(),//TODO need 0 in old implementation but 0 is absent in enum
                    Optional.of(LevelUniqueSettingsEnum.fromValue(String.valueOf(uniqueSettings))),
                    Optional.ofNullable(level.isVisible()),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty() //TODO need 0 in old imlpementation but 0 absent in enum
                );
            }).toList();
    }

    private static LevelTypeEnum getLevelType(Level level) {
        if (level.isAll()) {
            return LevelTypeEnum.ALL;
        }
        switch (level.getLevelType()) {
            case REGULAR:
                return LevelTypeEnum.REGULAR;
            case TIME_YEARS:
                return LevelTypeEnum.TIME_YEARS;
            case TIME_HALF_YEARS:
                return LevelTypeEnum.TIME_HALF_YEARS;
            case TIME_QUARTERS:
                return LevelTypeEnum.TIME_QUARTERS;
            case TIME_MONTHS:
                return LevelTypeEnum.TIME; //TODO
            case TIME_WEEKS:
                return LevelTypeEnum.TIME_WEEKS;
            case TIME_DAYS:
                return LevelTypeEnum.TIME_DAYS;
            case TIME_HOURS:
                return LevelTypeEnum.TIME; //TODO
            case TIME_MINUTES:
                return LevelTypeEnum.TIME; //TODO
            case TIME_SECONDS:
                return LevelTypeEnum.TIME_SECONDS;
            case TIME_UNDEFINED:
                return LevelTypeEnum.TIME_UNDEFINED;
            case GEO_CONTINENT:
                return LevelTypeEnum.GEOGRAPHY_CONTINENT;
            case GEO_REGION:
                return LevelTypeEnum.GEOGRAPHY_REGION;
            case GEO_COUNTRY:
                return LevelTypeEnum.GEOGRAPHY_COUNTRY;
            case GEO_STATE_OR_PROVINCE:
                return LevelTypeEnum.GEOGRAPHY_STATE_OR_PROVINCE;
            case GEO_COUNTY:
                return LevelTypeEnum.GEOGRAPHY_COUNTY;
            case GEO_CITY:
                return LevelTypeEnum.GEOGRAPHY_CITY;
            case GEO_POSTALCODE:
                return LevelTypeEnum.POSTAL_CODE;
            case GEO_POINT:
                return LevelTypeEnum.GEOGRAPHY_POINT;
            case ORG_UNIT:
                return LevelTypeEnum.ORGANIZATION_UNIT;
            case BOM_RESOURCE:
                return LevelTypeEnum.BILL_OF_MATERIAL_RESOURCE;
            case QUANTITATIVE:
                return LevelTypeEnum.QUANTITATIVE;
            case ACCOUNT:
                return LevelTypeEnum.ACCOUNT;
            case CUSTOMER:
                return LevelTypeEnum.CUSTOMER;
            case CUSTOMER_GROUP:
                return LevelTypeEnum.CUSTOMER_GROUP;
            case CUSTOMER_HOUSEHOLD:
                return LevelTypeEnum.CUSTOMER_HOUSEHOLD;
            case PRODUCT:
                return LevelTypeEnum.PRODUCT;
            case PRODUCT_GROUP:
                return LevelTypeEnum.PRODUCT_GROUP;
            case SCENARIO:
                return LevelTypeEnum.SCENARIO;
            case UTILITY:
                return LevelTypeEnum.UTILITY;
            case PERSON:
                return LevelTypeEnum.PERSON;
            case COMPANY:
                return LevelTypeEnum.COMPANY;
            case CURRENCY_SOURCE:
                return LevelTypeEnum.CURRENCY_SOURCE;
            case CURRENCY_DESTINATION:
                return LevelTypeEnum.CURRENCY_DESTINATION;
            case CHANNEL:
                return LevelTypeEnum.CHANNEL;
            case REPRESENTATIVE:
                return LevelTypeEnum.REPRESENTATIVE;
            case PROMOTION:
                return LevelTypeEnum.PROMOTION;
            case NULL:
            default:
                throw Util.unexpected(level.getLevelType());
        }
    }

    private static List<Level> getLevelsWithFilterByName(
        List<Level> levels,
        Optional<String> oLevelName
    ) {
        if (oLevelName.isPresent()) {
            levels.stream().filter(l -> oLevelName.get().equals(l.getName()));
        }
        return levels;
    }

    private static List<Level> getLevelsWithFilterByUniqueName(
        List<Level> levels,
        Optional<String> oLevelUniqueNameName
    ) {
        if (oLevelUniqueNameName.isPresent()) {
            levels.stream().filter(l -> oLevelUniqueNameName.get().equals(l.getUniqueName()));
        }
        return levels;
    }

    private static List<MappingHierarchy> getHierarchiesWithFilter(
        List<MappingHierarchy> hierarchies,
        Optional<String> oHierarchyUniqueName
    ) {
        if (oHierarchyUniqueName.isPresent()) {
            return hierarchies.stream().filter(h -> h.name().equals(oHierarchyUniqueName.get())).toList();
        }
        return hierarchies;
    }

    private static List<MappingCubeDimension> getMappingCubeDimensionsWithFilter(
        List<MappingCubeDimension> dimensions, Optional<String> oDimensionUniqueName
    ) {
        if (oDimensionUniqueName.isPresent()) {
            return dimensions.stream().filter(d -> oDimensionUniqueName.get().equals(d.name())).toList();
        }
        return dimensions;
    }

    static List<DbSchemaSourceTablesResponseRow> getDbSchemaSourceTablesResponseRow(Context context, List<String> tableTypeList) {
        List<DbSchemaSourceTablesResponseRow> result = new ArrayList<>();
        try (java.sql.Connection sqlConnection = context.getDataSource().getConnection()) {

            java.sql.DatabaseMetaData databaseMetaData = sqlConnection.getMetaData();
            String[] tableTypeRestriction = null;
            if (tableTypeList != null) {
                tableTypeRestriction = tableTypeList.toArray(new String[0]);
            }
            java.sql.ResultSet resultSet = databaseMetaData.getTables(null, null, null, tableTypeRestriction);

            while (resultSet.next()) {

                final String tableCatalog = resultSet.getString("TABLE_CAT");
                final String tableSchema = resultSet.getString("TABLE_SCHEM");
                final String tableName = resultSet.getString("TABLE_NAME");
                final String tableType = resultSet.getString("TABLE_TYPE");

                result.add(new DbSchemaSourceTablesResponseRowR(
                    Optional.of(tableCatalog),
                    Optional.of(tableSchema),
                    tableName,
                    TableTypeEnum.fromValue(tableType)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    static List<MdSchemaHierarchiesResponseRow> getMdSchemaHierarchiesResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyName,
        Optional<String> oHierarchyUniqueName,
        Optional<VisibilityEnum> oHierarchyVisibility,
        Optional<Integer> oHierarchyOrigin
    ) {
        return getSchemasWithFilter(context.getConnection().getSchemas(), oSchemaName)
            .stream().map(s -> getMdSchemaHierarchiesResponseRow(
            context.getName(),
            s,
            oCubeName,
            oCubeSource,
            oDimensionUniqueName,
            oHierarchyName,
            oHierarchyUniqueName,
            oHierarchyVisibility,
            oHierarchyOrigin
        )).flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaHierarchiesResponseRow> getMdSchemaHierarchiesResponseRow(
        String catalogName,
        Schema s,
        Optional<String> oCubeName,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyName,
        Optional<String> oHierarchyUniqueName,
        Optional<VisibilityEnum> oHierarchyVisibility,
        Optional<Integer> oHierarchyOrigin
    ) {
        List<Cube> cubes = s.getCubes() == null ? List.of() :  Arrays.asList(s.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream()
            .map(c -> getMdSchemaHierarchiesResponseRow(
                catalogName,
                s.getName(),
                c,
                oDimensionUniqueName,
                oHierarchyName,
                oHierarchyUniqueName,
                oHierarchyVisibility,
                oHierarchyOrigin
            )).flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaHierarchiesResponseRow> getMdSchemaHierarchiesResponseRow(
        String catalogName,
        String schemaName,
        Cube c,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyName,
        Optional<String> oHierarchyUniqueName,
        Optional<VisibilityEnum> oHierarchyVisibility,
        Optional<Integer> oHierarchyOrigin
    ) {
        List<Dimension> dimensions = c.getDimensions() == null ? List.of() : Arrays.asList(c.getDimensions());
        return getDimensionsWithFilter(dimensions, oDimensionUniqueName)
            .stream()
            .map(d -> getMdSchemaHierarchiesResponseRow(
                catalogName,
                schemaName,
                c.getName(),
                d,
                oHierarchyName,
                oHierarchyUniqueName,
                oHierarchyVisibility,
                oHierarchyOrigin
            )).flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaHierarchiesResponseRow> getMdSchemaHierarchiesResponseRow(
        String catalogName,
        String schemaName,
        String name,
        Dimension d,
        Optional<String> oHierarchyName,
        Optional<String> oHierarchyUniqueName,
        Optional<VisibilityEnum> oHierarchyVisibility,
        Optional<Integer> oHierarchyOrigin
    ) {
        return List.of(); //TODO
    }

    static List<MdSchemaMeasureGroupsResponseRow> getMdSchemaMeasureGroupsResponseRow(Context context, Optional<String> oSchemaName, Optional<String> oCubeName, Optional<String> oMeasureGroupName) {
        return getDatabaseMappingSchemaProviderWithFilter(context.getDatabaseMappingSchemaProviders(),oSchemaName)
            .stream().filter(dmsp -> (dmsp != null && dmsp.get() != null))
            .map(dmsp -> getMdSchemaMeasureGroupsResponseRow(context.getName(), dmsp.get(), oCubeName, oMeasureGroupName))
            .flatMap(Collection::stream).toList();

    }

    private static List<MdSchemaMeasureGroupsResponseRow> getMdSchemaMeasureGroupsResponseRow(String catalogName, MappingSchema schema, Optional<String> oCubeName, Optional<String> oMeasureGroupName) {
        return getMappingCubeWithFilter(schema.cubes(), oCubeName).stream()
            .map(c -> getMdSchemaMeasureGroupsResponseRow(catalogName, schema.name(), c, oMeasureGroupName))
            .toList();
    }

    private static MdSchemaMeasureGroupsResponseRow getMdSchemaMeasureGroupsResponseRow(String catalogName, String schemaName, MappingCube c, Optional<String> oMeasureGroupName) {
        return new MdSchemaMeasureGroupsResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.of(c.name()),
            Optional.of(c.name()),
            Optional.of(""),
            Optional.of(false),
            Optional.of(c.name())
        );
    }

    static List<MdSchemaMeasuresResponseRow> getMdSchemaMeasuresResponseRow(Context context, Optional<String> oSchemaName, Optional<String> oCubeName, Optional<String> oMeasureName, Optional<String> oMeasureUniqueName, Optional<String> oMeasureGroupName, boolean shouldEmitInvisibleMembers) {
        return getSchemasWithFilter(context.getConnection().getSchemas(), oSchemaName)
            .stream().filter(s -> s != null)
            .map(s -> getMdSchemaMeasuresResponseRow(context.getName(), s, oCubeName, oMeasureName, oMeasureUniqueName, oMeasureGroupName, shouldEmitInvisibleMembers))
            .flatMap(Collection::stream).toList();

    }

    private static List<MdSchemaMeasuresResponseRow> getMdSchemaMeasuresResponseRow(String catalogName, Schema schema, Optional<String> oCubeName, Optional<String> oMeasureName, Optional<String> oMeasureUniqueName, Optional<String> oMeasureGroupName, boolean shouldEmitInvisibleMembers) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() :  Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream()
            .map(c -> getMdSchemaMeasuresResponseRow(catalogName, schema.getName(), c, oMeasureName, oMeasureUniqueName, oMeasureGroupName, shouldEmitInvisibleMembers))
            .flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaMeasuresResponseRow> getMdSchemaMeasuresResponseRow(String catalogName, String schemaName, Cube cube, Optional<String> oMeasureName, Optional<String> oMeasureUniqueName, Optional<String> oMeasureGroupName, boolean shouldEmitInvisibleMembers) {
        List<MdSchemaMeasuresResponseRow> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int j = 0;
        for (Dimension dimension : cube.getDimensions()) {
            if (!dimension.getDimensionType().equals(DimensionType.MEASURES_DIMENSION)) {
                for (Hierarchy hierarchy : dimension.getHierarchies()) {
                    Level[] levels = hierarchy.getLevels();
                    if (levels.length > 0) {
                        Level lastLevel = levels[levels.length - 1];
                        if (j++ > 0) {
                            builder.append(',');
                        }
                        builder.append(lastLevel.getUniqueName());
                    }
                }
            }
        }
        String levelListStr = builder.toString();
        List<org.eclipse.daanse.olap.api.element.Member> measures =
            getMeasureWithFilterByUniqueName(getMeasureWithFilterByName(cube.getMeasures(), oMeasureName), oMeasureUniqueName);
        measures.stream().filter(m -> !m.isCalculated()).forEach(m -> populateMeasures(catalogName, schemaName, cube.getName(), levelListStr, shouldEmitInvisibleMembers, m,result ));
        measures.stream().filter(m -> m.isCalculated()).forEach(m -> populateMeasures(catalogName, schemaName, cube.getName(), null, shouldEmitInvisibleMembers, m,result ));
        return result;
    }

    private static void populateMeasures(String catalogName, String schemaName, String cubeName, String levelListStr, boolean shouldEmitInvisibleMembers, Member m, List<MdSchemaMeasuresResponseRow> result) {
        Boolean visible =
            (Boolean) m.getPropertyValue(Property.StandardMemberProperty.$visible.getName());
        if (visible == null) {
            visible = true;
        }
        if (visible || shouldEmitInvisibleMembers) {
            //TODO: currently this is always null
            String desc = m.getDescription();
            if (desc == null) {
                desc =
                    cubeName + " Cube - "
                        + m.getName() + " Member";
            }
            final String formatString =
                (String) m.getPropertyValue(Property.StandardCellProperty.FORMAT_STRING.getName());
            final MeasureAggregatorEnum measureAggregator = MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN;

            // DATA_TYPE DBType best guess is string
            XmlaConstants.DBType dbType = XmlaConstants.DBType.WSTR;
            String datatype = (String)
                m.getPropertyValue(Property.StandardCellProperty.DATATYPE.getName());
            if (datatype != null) {
                if (datatype.equals("Integer")) {
                    dbType = XmlaConstants.DBType.I4;
                } else if (datatype.equals("Numeric")) {
                    dbType = XmlaConstants.DBType.R8;
                } else {
                    dbType = XmlaConstants.DBType.WSTR;
                }
            }

            String displayFolder = "";
            result.add(new MdSchemaMeasuresResponseRowR(
                Optional.ofNullable(catalogName),
                Optional.ofNullable(schemaName),
                Optional.ofNullable(cubeName),
                Optional.ofNullable(m.getName()),
                Optional.ofNullable(m.getUniqueName()),
                Optional.ofNullable(m.getCaption()),
                Optional.empty(),
                Optional.ofNullable(measureAggregator),
                Optional.ofNullable(LevelDbTypeEnum.fromValue(String.valueOf(dbType.xmlaOrdinal()))),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.ofNullable(desc),
                Optional.empty(),
                Optional.ofNullable(visible),
                Optional.ofNullable(levelListStr),
                Optional.empty(),
                Optional.empty(),
                Optional.ofNullable(cubeName),
                Optional.ofNullable(displayFolder),
                Optional.ofNullable(formatString))
            );
        }
    };

private static List<org.eclipse.daanse.olap.api.element.Member> getMeasureWithFilterByName(List<org.eclipse.daanse.olap.api.element.Member> measures, Optional<String> oMeasureName) {
        if (oMeasureName.isPresent()) {
            return measures.stream().filter(m -> oMeasureName.get().equals(m.getName())).toList();
        }
        return measures;
    }

    private static List<org.eclipse.daanse.olap.api.element.Member> getMeasureWithFilterByUniqueName(List<org.eclipse.daanse.olap.api.element.Member> measures, Optional<String> oMeasureUniqueName) {
        if (oMeasureUniqueName.isPresent()) {
            return measures.stream().filter(m -> oMeasureUniqueName.get().equals(m.getUniqueName())).toList();
        }
        return measures;
    }

}
