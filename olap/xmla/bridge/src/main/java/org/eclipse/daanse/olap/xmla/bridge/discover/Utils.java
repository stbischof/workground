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

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.DataType;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.NamedSet;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.olap.api.function.FunctionMetaData;
import org.eclipse.daanse.olap.api.result.Property;
import org.eclipse.daanse.olap.impl.XmlaConstants;
import org.eclipse.daanse.olap.operation.api.EmptyOperationAtom;
import org.eclipse.daanse.olap.operation.api.InternalOperationAtom;
import org.eclipse.daanse.olap.operation.api.ParenthesesOperationAtom;
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
import org.eclipse.daanse.xmla.api.common.enums.CustomRollupSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionCardinalityEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionUniqueSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.HierarchyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelUniqueSettingsEnum;
import org.eclipse.daanse.xmla.api.common.enums.MeasureAggregatorEnum;
import org.eclipse.daanse.xmla.api.common.enums.MemberTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.common.enums.SetEvaluationContextEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureEnum;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TreeOpEnum;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsResponseRowR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.olap.DimensionType;
import mondrian.olap.SystemWideProperties;
import mondrian.olap.Util;
import mondrian.rolap.RolapAggregator;
import mondrian.rolap.RolapStoredMeasure;
import mondrian.xmla.VarType;

public class Utils {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

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

    static List<MdSchemaFunctionsResponseRow> getMdSchemaFunctionsResponseRow(
        Context c,
        Optional<String> oLibraryName,
        Optional<InterfaceNameEnum> oInterfaceName,
        Optional<OriginEnum> oOrigin
    ) {
        List<MdSchemaFunctionsResponseRow> result = new ArrayList<>();
        List<FunctionMetaData> fmList = c.getConnection().getContext().getFunctionService().getFunctionMetaDatas();
        StringBuilder buf = new StringBuilder(50);
        for (FunctionMetaData fm : fmList) {
			if (fm.operationAtom() instanceof EmptyOperationAtom//
					|| fm.operationAtom() instanceof InternalOperationAtom//
					|| fm.operationAtom() instanceof ParenthesesOperationAtom//
			) {
				continue;
			}

            DataType[] paramCategories = fm.parameterDataTypes();
            DataType returnCategory = fm.returnCategory();

            // Convert Windows newlines in 'description' to UNIX format.
            String description = fm.description();
            if (description != null) {
                description = fm.description().replace(
                    "\r",
                    "");
            }
            if ((paramCategories == null)
                || (paramCategories.length == 0)) {
                result.add(
                    new MdSchemaFunctionsResponseRowR(
                        Optional.ofNullable(fm.operationAtom().name()),
                        Optional.ofNullable(description),
                        "(none)",
                        Optional.of(1),
                        Optional.of(OriginEnum.MSOLAP),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.ofNullable(fm.operationAtom().name()),
                        Optional.empty(),
                        Optional.empty()));
            } else {

                buf.setLength(0);
                for (int j = 0; j < paramCategories.length; j++) {
                    DataType v = paramCategories[j];
                    if (j > 0) {
                        buf.append(", ");
                    }
                    buf.append(v.getPrittyName());
                }

                VarType varType = VarType.forCategory(returnCategory);
                result.add(
                    new MdSchemaFunctionsResponseRowR(
                        Optional.ofNullable(fm.operationAtom().name()),
                        Optional.ofNullable(description),
                        buf.toString(),
                        Optional.of(varType.ordinal()),
                        Optional.of(OriginEnum.MSOLAP),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.ofNullable(fm.operationAtom().name()),
                        Optional.empty(),
                        Optional.empty()));
            }
        }
        return result;
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
                return List.of(new MdSchemaCubesResponseRowR(
                    catalogName,
                    Optional.ofNullable(schemaName),
                    Optional.ofNullable(cube.name()),
                    Optional.of(CubeTypeEnum.CUBE), //TODO get cube type from olap
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(), //TODO get create date from olap
                    Optional.empty(),
                    Optional.of(LocalDateTime.now()),
                    Optional.empty(),
                    Optional.ofNullable(desc),
                    Optional.of(true),
                    Optional.of(false),
                    Optional.of(false),
                    Optional.of(false),
                    Optional.ofNullable(cube.caption()),
                    Optional.empty(),
                    Optional.of(CubeSourceEnum.CUBE),
                    Optional.empty())
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
        return getDatabaseMappingSchemaProviderWithFilter(context.getDatabaseMappingSchemaProviders(), oTableSchema)
            .stream().filter(dmsp -> dmsp != null && dmsp.get() != null)
            .map(dmsp -> getDbSchemaTablesResponseRow(context.getName(), dmsp.get(), oTableName, oTableType))
            .flatMap(Collection::stream).toList();
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
        if (SystemWideProperties.instance().SsasCompatibleNaming
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

    static List<MdSchemaDimensionsResponseRow> getMdSchemaDimensionsResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oDimensionName,
        Optional<String> oDimensionUniqueName,
        Optional<CubeSourceEnum> cubeSource,
        Optional<VisibilityEnum> oDimensionVisibility,
        Optional<Boolean> deep
    ) {
        List<Schema> schemas = context.getConnection().getSchemas();
        if (schemas != null) {
            return getSchemasWithFilter(schemas, oSchemaName).stream()
                .map(s -> getMdSchemaDimensionsResponseRow(context.getName(), s, oCubeName
                    , oDimensionName, oDimensionUniqueName, cubeSource, oDimensionVisibility, deep))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    static List<MdSchemaMeasureGroupDimensionsResponseRow> getMdSchemaMeasureGroupDimensionsResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oMeasureGroupName,
        Optional<String> oDimensionUniqueName,
        Optional<VisibilityEnum> oDimensionVisibility
    ) {
        List<Schema> schemas = context.getConnection().getSchemas();
        if (schemas != null) {
            return getSchemasWithFilter(schemas, oSchemaName).stream()
                .map(s -> getMdSchemaMeasureGroupDimensionsResponseRow(context.getName(), s, oCubeName,
                    oMeasureGroupName, oDimensionUniqueName, oDimensionVisibility))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelUniqueName,
        Optional<Integer> oLevelNumber,
        Optional<String> oMemberName,
        Optional<String> oMemberUniqueName,
        Optional<MemberTypeEnum> oMemberType,
        Optional<String> oMemberCaption,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<TreeOpEnum> oTreeOp,
        Optional<Boolean> emitInvisibleMembers
    ) {
        List<Schema> schemas = context.getConnection().getSchemas();
        if (schemas != null) {
            return getSchemasWithFilter(schemas, oSchemaName).stream()
                .map(s -> getMdSchemaMembersResponseRow(context.getName(), s, oCubeName,
                    oDimensionUniqueName, oHierarchyUniqueName, oLevelUniqueName, oLevelNumber,
                    oMemberName, oMemberUniqueName, oMemberType, oCubeSource, oTreeOp, emitInvisibleMembers))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    static List<MdSchemaKpisResponseRow> getMdSchemaKpisResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oKpiName
    ) {
        List<Schema> schemas = context.getConnection().getSchemas();
        if (schemas != null) {
            return getSchemasWithFilter(schemas, oSchemaName).stream()
                .map(s -> getMdSchemaKpisResponseRow(context.getName(), s, oCubeName, oKpiName))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    static List<MdSchemaSetsResponseRow> getMdSchemaSetsResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oSetName,
        Optional<ScopeEnum> oScope,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<String> oHierarchyUniqueName
    ) {
        List<Schema> schemas = context.getConnection().getSchemas();
        if (schemas != null) {
            return getSchemasWithFilter(schemas, oSchemaName).stream()
                .map(s -> getMdSchemaSetsResponseRow(context.getName(), s, oCubeName, oSetName, oScope, oCubeSource,
                    oHierarchyUniqueName))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    private static List<MdSchemaSetsResponseRow> getMdSchemaSetsResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oSetName,
        Optional<ScopeEnum> oScope,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<String> oHierarchyUniqueName
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream().
            map(c -> getMdSchemaSetsResponseRow(catalogName, schema.getName(), c,
                oSetName, oScope, oCubeSource,
                oHierarchyUniqueName)).
            flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaSetsResponseRow> getMdSchemaSetsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oSetName,
        Optional<ScopeEnum> oScope,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<String> oHierarchyUniqueName
    ) {
        List<NamedSet> sets = cube.getNamedSets() == null ? List.of() : Arrays.asList(cube.getNamedSets());
        return getNamedSetsWithFilter(sets, oSetName).stream().
            map(s -> getMdSchemaSetsResponseRow(catalogName, schemaName, cube.getName(), s,
                oScope, oCubeSource,
                oHierarchyUniqueName))
            .toList();

    }

    private static MdSchemaSetsResponseRow getMdSchemaSetsResponseRow(
        String catalogName,
        String schemaName,
        String cubeName,
        NamedSet set,
        Optional<ScopeEnum> oScope,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<String> oHierarchyUniqueName
    ) {

        String dimensions = set.getHierarchies()
            .stream().map(it -> it.getUniqueName()).collect(Collectors.joining(","));

        return new MdSchemaSetsResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.ofNullable(cubeName),
            Optional.ofNullable(set.getName()),
            Optional.of(ScopeEnum.GLOBAL),
            Optional.ofNullable(set.getDescription()),
            Optional.ofNullable(set.getExp() != null ? set.getExp().toString() : null),
            Optional.ofNullable(dimensions),
            Optional.ofNullable(set.getCaption()),
            Optional.ofNullable(set.getDisplayFolder()),
            Optional.of(SetEvaluationContextEnum.STATIC)
        );
    }

    private static List<NamedSet> getNamedSetsWithFilter(List<NamedSet> sets, Optional<String> oSetName) {
        if (oSetName.isPresent()) {
            return sets.stream().filter(s -> oSetName.get().equals(s.getName())).toList();
        }
        return sets;
    }

    private static List<MdSchemaKpisResponseRow> getMdSchemaKpisResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oKpiName
    ) {
        return List.of();
    }

    private static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelUniqueName,
        Optional<Integer> oLevelNumber,
        Optional<String> oMemberName,
        Optional<String> oMemberUniqueName,
        Optional<MemberTypeEnum> oMemberType,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<TreeOpEnum> oTreeOp,
        Optional<Boolean> emitInvisibleMembers
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream().
            map(c -> getMdSchemaMembersResponseRow(catalogName, schema.getName(), c,
                oDimensionUniqueName, oHierarchyUniqueName, oLevelUniqueName, oLevelNumber,
                oMemberName, oMemberUniqueName, oMemberType, oCubeSource, oTreeOp, emitInvisibleMembers)).
            flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelUniqueName,
        Optional<Integer> oLevelNumber,
        Optional<String> oMemberName,
        Optional<String> oMemberUniqueName,
        Optional<MemberTypeEnum> oMemberType,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<TreeOpEnum> oTreeOp,
        Optional<Boolean> emitInvisibleMembers
    ) {
        if (oMemberUniqueName.isPresent()) {
            //TODO
            return List.of();
        } else {
            if (oLevelUniqueName.isPresent()) {
                Optional<Level> l = lookupLevel(cube, oLevelUniqueName.get());
                if (l.isPresent()) {
                    return getMdSchemaMembersResponseRow(catalogName, schemaName, cube, l.get().getMembers(),
                        oMemberUniqueName, oMemberType, emitInvisibleMembers);
                }
            }
            List<Dimension> dimensions = cube.getDimensions() == null ? List.of() : Arrays.asList(cube.getDimensions());
            return getDimensionsWithFilterByUniqueName(dimensions, oDimensionUniqueName)
                .stream()
                .map(d -> getMdSchemaMembersResponseRow(catalogName, schemaName, cube, d,
                    oHierarchyUniqueName, oLevelNumber,
                    oMemberUniqueName,
                    oMemberType,
                    emitInvisibleMembers))
                .flatMap(Collection::stream).toList();
        }
    }

    private static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        String catalogName, String schemaName, Cube cube, Dimension dimension,
        Optional<String> oHierarchyUniqueName, Optional<Integer> oLevelNumber,
        Optional<String> oMemberUniqueName,
        Optional<MemberTypeEnum> oMemberType,
        Optional<Boolean> emitInvisibleMembers
    ) {
        List<Hierarchy> hierarchies = dimension.getHierarchies() == null ? List.of() :
            Arrays.asList(dimension.getHierarchies());
        return getHierarchiesWithFilterByUniqueName(hierarchies, oHierarchyUniqueName)
            .stream()
            .map(h -> getMdSchemaMembersResponseRow(catalogName, schemaName, cube, h,
                oLevelNumber, oMemberUniqueName, oMemberType, emitInvisibleMembers))
            .flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        String catalogName, String schemaName,
        Cube cube, Hierarchy hierarchy,
        Optional<Integer> oLevelNumber,
        Optional<String> oMemberUniqueName,
        Optional<MemberTypeEnum> oMemberType,
        Optional<Boolean> emitInvisibleMembers
    ) {
        if (oLevelNumber.isPresent()) {
            int levelNumber = oLevelNumber.get();
            if (levelNumber == -1) {
                LOGGER.warn(
                    "LevelNumber invalid");
                return List.of();
            }
            Level[] levels = hierarchy.getLevels();
            if (levelNumber >= levels.length) {
                LOGGER.warn(
                    "LevelNumber ("
                        + levelNumber
                        + ") is greater than number of levels ("
                        + levels.length
                        + ") for hierarchy \""
                        + hierarchy.getUniqueName()
                        + "\"");
                return List.of();
            }

            Level level = levels[levelNumber];
            List<Member> members = level.getMembers();
            return getMdSchemaMembersResponseRow(catalogName, schemaName, cube, members,
                oMemberUniqueName, oMemberType, emitInvisibleMembers);
        } else {
            // At this point we get ALL of the members associated with
            // the Hierarchy (rather than getting them one at a time).
            // The value returned is not used at this point but they are
            // now cached in the SchemaReader.
            List<Level> levels = hierarchy.getLevels() == null ? List.of() : Arrays.asList(hierarchy.getLevels());
            return levels.stream().map(l -> getMdSchemaMembersResponseRow(catalogName, schemaName, cube,
                l.getMembers(), oMemberUniqueName, oMemberType, emitInvisibleMembers)).
                flatMap(Collection::stream).toList();
        }

    }

    private static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        String catalogName, String schemaName,
        Cube cube, List<Member> members,
        Optional<String> oMemberUniqueName,
        Optional<MemberTypeEnum> oMemberType, Optional<Boolean> emitInvisibleMembers
    ) {
        return getMembersWithFilterByType(getMembersWithFilterByUniqueName(members, oMemberUniqueName), oMemberType).stream().map(m -> getMdSchemaMembersResponseRow(
            catalogName, schemaName, cube, m, emitInvisibleMembers))
            .flatMap(Collection::stream).toList();
    }

    private static List<Member> getMembersWithFilterByType(List<Member> members, Optional<MemberTypeEnum> oMemberType) {
        if (oMemberType.isPresent()) {
            return members.stream().filter(m -> oMemberType.get().equals(getMemberTypeEnum(m.getMemberType()))).toList();
        }
        return members;
    }

    private static MemberTypeEnum getMemberTypeEnum(Member.MemberType memberType) {
        if (memberType != null) {
            switch (memberType) {
                case REGULAR:
                    return MemberTypeEnum.REGULAR_MEMBER;
                case ALL:
                    return MemberTypeEnum.ALL_MEMBER;
                case MEASURE:
                    return MemberTypeEnum.MEASURE;
                case FORMULA:
                    return MemberTypeEnum.FORMULA;
                case UNKNOWN:
                    return MemberTypeEnum.UNKNOWN;
                default:
                    return MemberTypeEnum.REGULAR_MEMBER;
            }
        }
        return MemberTypeEnum.REGULAR_MEMBER;
    }

    private static List<Member> getMembersWithFilterByUniqueName(
        List<Member> members,
        Optional<String> oMemberUniqueName
    ) {
        if (oMemberUniqueName.isPresent()) {
            return members.stream().filter(m -> oMemberUniqueName.get().equals(m.getUniqueName())).toList();
        }
        return members;
    }

    private static List<MdSchemaMembersResponseRow> getMdSchemaMembersResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Member member,
        Optional<Boolean> emitInvisibleMembers
    ) {

        // Check whether the member is visible, otherwise do not dump.
        Boolean visible = true;
        if (member.getPropertyValue("$visible") != null) {
            visible = (Boolean) member.getPropertyValue("$visible");
        }
        if (!visible && emitInvisibleMembers.isPresent() && !emitInvisibleMembers.get()) {
            return List.of();
        }

        final Level level = member.getLevel();
        final Hierarchy hierarchy = level.getHierarchy();
        final Dimension dimension = hierarchy.getDimension();

        int adjustedLevelDepth = level.getDepth();

        String parentUniqueName = null;
        if (adjustedLevelDepth != 0) {
            final Member parentMember = member.getParentMember();
            if (parentMember != null) {
                parentUniqueName = parentMember.getUniqueName();
            }
        }

        //TODO Depth absent in result
        //member.getDepth();

        MdSchemaMembersResponseRow r = new MdSchemaMembersResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.ofNullable(cube.getName()),
            Optional.ofNullable(dimension.getUniqueName()),
            Optional.ofNullable(hierarchy.getUniqueName()),
            Optional.ofNullable(level.getUniqueName()),
            Optional.ofNullable(adjustedLevelDepth),
            Optional.ofNullable(0),
            Optional.ofNullable(member.getName()),
            Optional.ofNullable(member.getUniqueName()),
            Optional.ofNullable(getMemberTypeEnum(member.getMemberType())),
            Optional.empty(),
            Optional.ofNullable(member.getCaption()),
            Optional.ofNullable(100),
            Optional.ofNullable(adjustedLevelDepth == 0 ? 0 : adjustedLevelDepth - 1),
            Optional.ofNullable(parentUniqueName),
            Optional.ofNullable(member.getParentMember() == null ? 0 : 1),
            Optional.ofNullable(member.getDescription()),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
        return List.of(r);
    }

    private static List<Hierarchy> getHierarchiesWithFilterByUniqueName(
        List<Hierarchy> hierarchies,
        Optional<String> oHierarchyUniqueName
    ) {
        if (oHierarchyUniqueName.isPresent()) {
            return hierarchies.stream().filter(h -> oHierarchyUniqueName.get().equals(h.getUniqueName())).toList();
        }
        return hierarchies;
    }

    private static Optional<Level> lookupLevel(Cube cube, String levelUniqueName) {
        for (Dimension dimension : cube.getDimensions()) {
            for (Hierarchy hierarchy : dimension.getHierarchies()) {
                for (Level level : hierarchy.getLevels()) {
                    if (level.getUniqueName().equals(levelUniqueName)) {
                        return Optional.of(level);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static List<MdSchemaMeasureGroupDimensionsResponseRow> getMdSchemaMeasureGroupDimensionsResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oMeasureGroupName,
        Optional<String> oDimensionUniqueName,
        Optional<VisibilityEnum> oDimensionVisibility
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream().
            map(c -> getMdSchemaMeasureGroupDimensionsResponseRow(catalogName, schema.getName(), c,
                oDimensionUniqueName, oDimensionVisibility)).
            flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaMeasureGroupDimensionsResponseRow> getMdSchemaMeasureGroupDimensionsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oDimensionUniqueName,
        Optional<VisibilityEnum> oDimensionVisibility
    ) {
        List<Dimension> dimensions = cube.getDimensions() == null ? List.of() : Arrays.asList(cube.getDimensions());
        return getDimensionsWithFilterByUniqueName(dimensions, oDimensionUniqueName)
            .stream()
            .map(d -> getMdSchemaMeasureGroupDimensionsResponseRow(catalogName, schemaName, cube, d,
                oDimensionVisibility))
            .toList();
    }

    private static MdSchemaMeasureGroupDimensionsResponseRow getMdSchemaMeasureGroupDimensionsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Dimension dimension,
        Optional<VisibilityEnum> oDimensionVisibility
    ) {

        return new MdSchemaMeasureGroupDimensionsResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.ofNullable(cube.getName()),
            Optional.ofNullable(cube.getName()),
            Optional.of("ONE"),
            Optional.ofNullable(dimension.getUniqueName()),
            Optional.of(DimensionCardinalityEnum.MANY),
            Optional.ofNullable(dimension.isVisible()),
            Optional.of(false),
            Optional.of(List.of()), //?? this parameter absent in old implementation
            Optional.of("")
        );
    }

    private static List<MdSchemaDimensionsResponseRow> getMdSchemaDimensionsResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oDimensionName,
        Optional<String> oDimensionUniqueName,
        Optional<CubeSourceEnum> cubeSource,
        Optional<VisibilityEnum> oDimensionVisibility,
        Optional<Boolean> deep
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream().
            map(c -> getMdSchemaDimensionsResponseRow(catalogName, schema.getName(), c, oDimensionName,
                oDimensionUniqueName, cubeSource, oDimensionVisibility, deep)).
            flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaDimensionsResponseRow> getMdSchemaDimensionsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oDimensionName,
        Optional<String> oDimensionUniqueName,
        Optional<CubeSourceEnum> cubeSource,
        Optional<VisibilityEnum> oDimensionVisibility,
        Optional<Boolean> deep
    ) {
        List<Dimension> dimensions = cube.getDimensions() == null ? List.of() : Arrays.asList(cube.getDimensions());
        return getDimensionsWithFilterByName(
            getDimensionsWithFilterByUniqueName(dimensions, oDimensionUniqueName), oDimensionName)
            .stream()
            .map(d -> getMdSchemaDimensionsResponseRow(catalogName, schemaName, cube, d, cubeSource,
                oDimensionVisibility, deep))
            .toList();
    }

    private static MdSchemaDimensionsResponseRow getMdSchemaDimensionsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Dimension dimension,
        Optional<CubeSourceEnum> cubeSource,
        Optional<VisibilityEnum> oDimensionVisibility,
        Optional<Boolean> deep
    ) {
        String cubeName = cube.getName();
        String desc = dimension.getDescription();
        if (desc == null) {
            desc =
                cube.getName() + " Cube - "
                    + dimension.getName() + " Dimension";
        }
        List<Dimension> dimensions = cube.getDimensions() != null ? Arrays.asList(cube.getDimensions()) : List.of();
        //Is this the number of primaryKey members there are??
        // According to microsoft this is:
        //    "The number of members in the key attribute."
        // There may be a better way of doing this but
        // this is what I came up with. Note that I need to
        // add '1' to the number inorder for it to match
        // match what microsoft SQL Server is producing.
        // The '1' might have to do with whether or not the
        // hierarchy has a 'all' member or not - don't know yet.
        // large data set total for Orders cube 0m42.923s

        String firstHierarchyUniqueName = null;
        Level lastLevel = null;
        if (dimension.getHierarchies() != null && dimension.getHierarchies().length > 0) {
            Hierarchy firstHierarchy = dimension.getHierarchies()[0];
            firstHierarchyUniqueName = firstHierarchy.getUniqueName();
            if (firstHierarchy.getLevels() != null && firstHierarchy.getLevels().length > 0) {
                lastLevel = firstHierarchy.getLevels()[firstHierarchy.getLevels().length - 1];
            }
        }
            /*
            if override config setting is set
                if approxRowCount has a value
                    use it
            else
                                    do default
            */

        // Added by TWI to returned cached row numbers

        //int n = getExtra(connection).getLevelCardinality(lastLevel);
        int n = lastLevel == null ? 0 : lastLevel.getCardinality();
        int dimensionCardinality = n + 1;
        boolean isVirtual = false;
        // SQL Server always returns false
        boolean isReadWrite = false;

        // TODO: don't know what to do here
        // Are these the levels with uniqueMembers == true?
        // How are they mapped to specific column numbers?
        //but was 0
        DimensionUniqueSettingEnum dimensionUniqueSetting = DimensionUniqueSettingEnum.MEMBER_KEY;

        if (deep.isPresent() && deep.get()) {
            //TODO add MdSchemaHierarchiesResponse to response
            getMdSchemaHierarchiesResponseRow(catalogName,
                schemaName,
                cubeName,
                dimension,
                0,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                deep
            );
        }

        return new MdSchemaDimensionsResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.ofNullable(cubeName),
            Optional.ofNullable(dimension.getName()),
            Optional.ofNullable(dimension.getUniqueName()),
            Optional.empty(),
            Optional.ofNullable(dimension.getCaption()),
            Optional.ofNullable(dimensions.indexOf(dimension)),
            Optional.ofNullable(getDimensionType(dimension.getDimensionType())),
            Optional.of(dimensionCardinality),
            Optional.ofNullable(firstHierarchyUniqueName),
            Optional.of(desc),
            Optional.of(isVirtual),
            Optional.of(isReadWrite),
            Optional.ofNullable(dimensionUniqueSetting),
            Optional.empty(),
            Optional.ofNullable(dimension.isVisible())
        );

    }

    private static DimensionTypeEnum getDimensionType(DimensionType dimensionType) {
        if (dimensionType != null) {
            switch (dimensionType) {
                case STANDARD_DIMENSION:
                    return DimensionTypeEnum.OTHER;
                case MEASURES_DIMENSION:
                    return DimensionTypeEnum.MEASURE;
                case TIME_DIMENSION:
                    return DimensionTypeEnum.TIME;
                default:
                    throw new RuntimeException("Wrong dimension type");
            }
        }
        return null;
    }

    private static List<Schema> getSchemasWithFilter(List<Schema> schemas, Optional<String> oSchemaName) {
        if (oSchemaName.isPresent()) {
            return schemas.stream().filter(s -> oSchemaName.get().equals(s.getName())).toList();
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
            return cubes.stream().filter(c -> oCubeName.get().equals(c.getName())).toList();
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
        		//.stream().sorted((d1, d2) -> DimensionType.MEASURES_DIMENSION.equals(d2.getDimensionType()) ? -1 : 0).toList();
        return getDimensionsWithFilterByUniqueName(dimensions, oDimensionUniqueName)
            .stream()
            .map(d -> getMdSchemaLevelsResponseRow(catalogName, schemaName, cube.getName(), d, oHierarchyUniqueName,
                oLevelName, oLevelUniqueName,
                oLevelVisibility))
            .flatMap(Collection::stream).toList();
    }

    private static List<Dimension> getDimensionsWithFilterByUniqueName(
        List<Dimension> dimensions,
        Optional<String> oDimensionUniqueName
    ) {
        if (oDimensionUniqueName.isPresent()) {
            return dimensions.stream().filter(d -> oDimensionUniqueName.get().equals(d.getHierarchy().getUniqueName())).toList();
        }
        return dimensions;
    }

    private static List<Dimension> getDimensionsWithFilterByName(
        List<Dimension> dimensions,
        Optional<String> oDimensionName
    ) {
        if (oDimensionName.isPresent()) {
            return dimensions.stream().filter(d -> oDimensionName.get().equals(d.getName())).toList();
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
        List<Hierarchy> hierarchies = dimension.getHierarchies() == null ? List.of() :
            Arrays.asList(dimension.getHierarchies());
        return getHierarchiesWithFilterByUniqueName(hierarchies, oHierarchyUniqueName)
            .stream().map(h -> getMdSchemaLevelsResponseRow(
                catalogName,
                schemaName,
                cubeName,
                dimension.getUniqueName(),
                h,
                oLevelName,
                oLevelUniqueName,
                oLevelVisibility)).flatMap(Collection::stream).toList();
    }

    private static List<Hierarchy> getHierarchiesWithFilterByName(
        List<Hierarchy> hierarchies,
        Optional<String> oHierarchyName
    ) {
        if (oHierarchyName.isPresent()) {
            return hierarchies.stream().filter(h -> oHierarchyName.get().equals(h.getName())).toList();
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
                if (level.isUnique()) {
                    uniqueSettings |= 1;
                }
                // Get level cardinality
                // According to microsoft this is:
                //   "The number of members in the level."
                //int levelCardinality = extra.getLevelCardinality(level); //TODO
                int levelCardinality = 1; //TODO

                return (MdSchemaLevelsResponseRow) new MdSchemaLevelsResponseRowR(
                    Optional.ofNullable(catalogName),
                    Optional.ofNullable(schemaName),
                    Optional.ofNullable(cubeName),
                    Optional.ofNullable(dimensionUniqueName),
                    Optional.ofNullable(h.getUniqueName()),
                    Optional.ofNullable(level.getName()),
                    Optional.ofNullable(level.getUniqueName()),
                    Optional.empty(),
                    Optional.ofNullable(level.getCaption()),
                    Optional.ofNullable(level.getDepth()),
                    Optional.of(levelCardinality),
                    Optional.of(getLevelType(level)),
                    Optional.ofNullable(desc),
                    Optional.of(CustomRollupSettingEnum.NONE),
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
                    Optional.of(LevelOriginEnum.NONE)
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
            return levels.stream().filter(l -> oLevelName.get().equals(l.getName())).toList();
        }
        return levels;
    }

    private static List<Level> getLevelsWithFilterByUniqueName(
        List<Level> levels,
        Optional<String> oLevelUniqueNameName
    ) {
        if (oLevelUniqueNameName.isPresent()) {
            return levels.stream().filter(l -> oLevelUniqueNameName.get().equals(l.getUniqueName())).toList();
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

    static List<DbSchemaSourceTablesResponseRow> getDbSchemaSourceTablesResponseRow(
        Context context,
        List<String> tableTypeList
    ) {
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
        Optional<Integer> oHierarchyOrigin,
        Optional<Boolean> deep
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
                oHierarchyOrigin,
                deep
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
        Optional<Integer> oHierarchyOrigin,
        Optional<Boolean> deep
    ) {
        List<Cube> cubes = s.getCubes() == null ? List.of() : Arrays.asList(s.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream()
            .map(c -> getMdSchemaHierarchiesResponseRow(
                catalogName,
                s.getName(),
                c,
                oDimensionUniqueName,
                oHierarchyName,
                oHierarchyUniqueName,
                oHierarchyVisibility,
                oHierarchyOrigin,
                deep
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
        Optional<Integer> oHierarchyOrigin,
        Optional<Boolean> deep
    ) {
        List<MdSchemaHierarchiesResponseRow> result = new ArrayList<>();
        List<Dimension> dimensions = c.getDimensions() == null ? List.of() : Arrays.asList(c.getDimensions());
        int ordinal = 0;
        for (Dimension dimension : dimensions) {
            if (!oDimensionUniqueName.isPresent() || oDimensionUniqueName.get().equals(dimension.getUniqueName())) {
                result.addAll(getMdSchemaHierarchiesResponseRow(
                    catalogName,
                    schemaName,
                    c.getName(),
                    dimension,
                    ordinal,
                    oHierarchyName,
                    oHierarchyUniqueName,
                    oHierarchyVisibility,
                    oHierarchyOrigin,
                    deep));
            }
            ordinal += dimension.getHierarchies().length;
        }
        return result;
    }

    private static List<MdSchemaHierarchiesResponseRow> getMdSchemaHierarchiesResponseRow(
        String catalogName,
        String schemaName,
        String cubeName,
        Dimension dimension,
        int ordinal,
        Optional<String> oHierarchyName,
        Optional<String> oHierarchyUniqueName,
        Optional<VisibilityEnum> oHierarchyVisibility,
        Optional<Integer> oHierarchyOrigin,
        Optional<Boolean> deep
    ) {
        List<Hierarchy> hierarchies = dimension.getHierarchies() == null ? List.of() :
            Arrays.asList(dimension.getHierarchies());

        return getHierarchiesWithFilterByName(getHierarchiesWithFilterByUniqueName(hierarchies, oHierarchyName),
oHierarchyName)
            .stream().map(h -> getMdSchemaHierarchiesResponseRow(
                catalogName,
                schemaName,
                cubeName,
                dimension,
                ordinal + hierarchies.indexOf(h),
                h,
                oHierarchyVisibility,
                oHierarchyOrigin, deep))
            .toList();

    }

    private static MdSchemaHierarchiesResponseRow getMdSchemaHierarchiesResponseRow(
        String catalogName,
        String schemaName,
        String cubeName,
        Dimension dimension,
        int ordinal,
        Hierarchy hierarchy,
        Optional<VisibilityEnum> oHierarchyVisibility,
        Optional<Integer> oHierarchyOrigin,
        Optional<Boolean> deep
    ) {
        String desc = hierarchy.getDescription();
        if (desc == null) {
            desc =
                cubeName + " Cube - "
                    + getHierarchyName(hierarchy.getName(), dimension.getName()) + " Hierarchy";
        }

        //mondrian.olap4j.MondrianOlap4jHierarchy mondrianOlap4jHierarchy =
        //    (mondrian.olap4j.MondrianOlap4jHierarchy)hierarchy;

        // Bitmask
        // MD_ORIGIN_USER_DEFINED 0x00000001
        // MD_ORIGIN_ATTRIBUTE 0x00000002
        // MD_ORIGIN_KEY_ATTRIBUTE 0x00000004
        // MD_ORIGIN_INTERNAL 0x00000008
        int hierarchyOrigin;
        if (dimension.getUniqueName().equals(org.eclipse.daanse.olap.api.element.Dimension.MEASURES_UNIQUE_NAME)) {
            hierarchyOrigin = 6;
        } else {
            hierarchyOrigin = hierarchy.origin() != null ? Integer.parseInt(hierarchy.origin()) : 1;
        }

        //String displayFolder = mondrianOlap4jHierarchy.getDisplayFolder();
        String displayFolder = hierarchy.getDisplayFolder();
        if (displayFolder == null) {
            displayFolder = "";
        }

        //row.set(ParentChild.name, extra.isHierarchyParentChild(hierarchy));
        //TODO ParentChild
        if (deep.isPresent() && deep.get()) {
            //TODO add MdSchemaLevelsResponse to response
            getMdSchemaLevelsResponseRow(catalogName,
                schemaName,
                cubeName,
                dimension.getUniqueName(),
                hierarchy,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            );
        }
        return new MdSchemaHierarchiesResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.ofNullable(cubeName),
            Optional.ofNullable(dimension.getUniqueName()),
            Optional.ofNullable(hierarchy.getName()),
            Optional.ofNullable(hierarchy.getUniqueName()),
            Optional.empty(),
            Optional.ofNullable(hierarchy.getCaption()),
            Optional.ofNullable(getDimensionType(dimension.getDimensionType())),
            Optional.of(getHierarchyCardinality(hierarchy)),
            Optional.ofNullable(hierarchy.getDefaultMember() == null ? null :
                hierarchy.getDefaultMember().getUniqueName()),
            Optional.ofNullable((hierarchy.hasAll() && hierarchy.getRootMembers() != null && hierarchy.getRootMembers().size() > 0) ? hierarchy.getRootMembers().get(0).getUniqueName() : null),
            Optional.ofNullable(desc),
            Optional.of(StructureEnum.HIERARCHY_FULLY_BALANCED),
            Optional.of(false),
            Optional.of(false),
            // NOTE that SQL Server returns '0' not '1'.
            Optional.of(DimensionUniqueSettingEnum.MEMBER_KEY),
            Optional.empty(),
            Optional.ofNullable(dimension.isVisible()),
            Optional.of(ordinal),
            Optional.of(true),
            Optional.ofNullable(hierarchy.isVisible()),
            Optional.of(HierarchyOriginEnum.fromValue(String.valueOf(hierarchyOrigin))),
            Optional.ofNullable(displayFolder),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        );


    }

    private static int getHierarchyCardinality(Hierarchy hierarchy) {
        int cardinality = 0;
        if (hierarchy.getLevels() != null) {
            for (Level level : hierarchy.getLevels()) {
                cardinality += level.getCardinality();
            }
        }
        return cardinality;
    }

    static List<MdSchemaMeasureGroupsResponseRow> getMdSchemaMeasureGroupsResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oMeasureGroupName
    ) {
        return getDatabaseMappingSchemaProviderWithFilter(context.getDatabaseMappingSchemaProviders(), oSchemaName)
            .stream().filter(dmsp -> (dmsp != null && dmsp.get() != null))
            .map(dmsp -> getMdSchemaMeasureGroupsResponseRow(context.getName(), dmsp.get(), oCubeName,
                oMeasureGroupName))
            .flatMap(Collection::stream).toList();

    }

    private static List<MdSchemaMeasureGroupsResponseRow> getMdSchemaMeasureGroupsResponseRow(
        String catalogName,
        MappingSchema schema,
        Optional<String> oCubeName,
        Optional<String> oMeasureGroupName
    ) {
        return getMappingCubeWithFilter(schema.cubes(), oCubeName).stream()
            .map(c -> getMdSchemaMeasureGroupsResponseRow(catalogName, schema.name(), c, oMeasureGroupName))
            .toList();
    }

    private static MdSchemaMeasureGroupsResponseRow getMdSchemaMeasureGroupsResponseRow(
        String catalogName,
        String schemaName,
        MappingCube c,
        Optional<String> oMeasureGroupName
    ) {
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

    static List<MdSchemaPropertiesResponseRow> getMdSchemaPropertiesResponseRowCell() {
        List<MdSchemaPropertiesResponseRow> result = new ArrayList<>();
        for (Property.StandardCellProperty property
            : Property.StandardCellProperty.values()) {

            int type = Property.TypeFlag.getDictionary()
                .toMask(
                    property.getType());
            int dataType = property.getDatatype().xmlaOrdinal();

            result.add(new MdSchemaPropertiesResponseRowR(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.ofNullable(PropertyTypeEnum.fromValue(String.valueOf(type))),
                Optional.ofNullable(property.name()),
                Optional.ofNullable(property.getCaption()),
                Optional.ofNullable(LevelDbTypeEnum.fromValue(String.valueOf(dataType))),
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
                Optional.of(CubeSourceEnum.DIMENSION),
                Optional.of(VisibilityEnum.VISIBLE)
            ));
        }
        return result;
    }

    static List<MdSchemaPropertiesResponseRow> getMdSchemaPropertiesResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelUniqueName,
        Optional<String> oMemberUniqueName,
        Optional<String> oPropertyName,
        Optional<PropertyOriginEnum> oPropertyOrigin,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<VisibilityEnum> oPropertyVisibility
    ) {
        return getSchemasWithFilter(context.getConnection().getSchemas(), oSchemaName)
            .stream().map(s -> getMdSchemaPropertiesResponseRow(
                context.getName(),
                s,
                oCubeName,
                oDimensionUniqueName,
                oHierarchyUniqueName,
                oLevelUniqueName,
                oMemberUniqueName,
                oPropertyName,
                oPropertyOrigin,
                oCubeSource,
                oPropertyVisibility
            )).flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaPropertiesResponseRow> getMdSchemaPropertiesResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelUniqueName,
        Optional<String> oMemberUniqueName,
        Optional<String> oPropertyName,
        Optional<PropertyOriginEnum> oPropertyOrigin,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<VisibilityEnum> oPropertyVisibility
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream()
            .map(c -> getMdSchemaPropertiesResponseRow(catalogName, schema.getName(), c, oDimensionUniqueName,
                oHierarchyUniqueName,
                oLevelUniqueName,
                oPropertyName,
                oPropertyOrigin,
                oCubeSource,
                oPropertyVisibility
            ))
            .flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaPropertiesResponseRow> getMdSchemaPropertiesResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oDimensionUniqueName,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oLevelUniqueName,
        Optional<String> oPropertyName,
        Optional<PropertyOriginEnum> oPropertyOrigin,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<VisibilityEnum> oPropertyVisibility
    ) {
        if (oLevelUniqueName.isPresent()) {
            // Note: If the LEVEL_UNIQUE_NAME has been specified, then
            // the dimension and hierarchy are specified implicitly.
            String levelUniqueName = oLevelUniqueName.get();
            if (levelUniqueName == null) {
                // The query specified two or more unique names
                // which means that nothing will match.
                return List.of();
            }
            Optional<Level> oLevel = lookupLevel(cube, levelUniqueName);
            if (!oLevel.isPresent()) {
                return List.of();
            }
            return getMdSchemaPropertiesResponseRow(
                catalogName,
                schemaName,
                cube,
                oLevel.get(),
                oPropertyName,
                oPropertyOrigin,
                oCubeSource,
                oPropertyVisibility);
        } else {
            List<Dimension> dimensions = cube.getDimensions() == null ? List.of() : Arrays.asList(cube.getDimensions());
            return getDimensionsWithFilterByUniqueName(dimensions, oDimensionUniqueName)
                .stream().filter(d -> d != null)
                .map(d -> getMdSchemaMeasuresResponseRow(catalogName, schemaName, cube, d,
                    oHierarchyUniqueName,
                    oPropertyName,
                    oPropertyOrigin,
                    oCubeSource,
                    oPropertyVisibility))
                .flatMap(Collection::stream).toList();
        }

    }

    private static List<MdSchemaPropertiesResponseRow> getMdSchemaMeasuresResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Dimension dimension,
        Optional<String> oHierarchyUniqueName,
        Optional<String> oPropertyName,
        Optional<PropertyOriginEnum> oPropertyOrigin,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<VisibilityEnum> oPropertyVisibility
    ) {
        List<Hierarchy> hierarchies = dimension.getHierarchies() == null ? List.of() :
            Arrays.asList(dimension.getHierarchies());
        return getHierarchiesWithFilterByUniqueName(hierarchies, oHierarchyUniqueName)
            .stream().filter(h -> h != null)
            .map(h -> getMdSchemaPropertiesResponseRow(catalogName, schemaName, cube, h,
                oPropertyName,
                oPropertyOrigin,
                oCubeSource,
                oPropertyVisibility))
            .flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaPropertiesResponseRow> getMdSchemaPropertiesResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Hierarchy hierarchy,
        Optional<String> oPropertyName,
        Optional<PropertyOriginEnum> oPropertyOrigin,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<VisibilityEnum> oPropertyVisibility
    ) {
        List<Level> levels = hierarchy.getLevels() == null ? List.of() : Arrays.asList(hierarchy.getLevels());
        return levels.stream().map(l -> getMdSchemaPropertiesResponseRow(catalogName,
            schemaName, cube, l, oPropertyName, oPropertyOrigin, oCubeSource, oPropertyVisibility))
            .flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaPropertiesResponseRow> getMdSchemaPropertiesResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Level level,
        Optional<String> oPropertyName,
        Optional<PropertyOriginEnum> oPropertyOrigin,
        Optional<CubeSourceEnum> oCubeSource,
        Optional<VisibilityEnum> oPropertyVisibility
    ) {
        List<mondrian.olap.Property> properties = level.getProperties() == null ? List.of() :
         Arrays.asList(level.getProperties());
        return getPropertiesWithFilterByUniqueName(properties, oPropertyName)
            .stream().filter(p -> p != null)
            .map(p -> getMdSchemaPropertiesResponseRow(catalogName, schemaName, cube, level, p))
            .toList();
    }

    private static List<mondrian.olap.Property> getPropertiesWithFilterByUniqueName(
        List<mondrian.olap.Property> properties,
        Optional<String> oPropertyName
    ) {
        if (oPropertyName.isPresent()) {
            return properties.stream().filter(p -> oPropertyName.get().equals(p.getName())).toList();
        }
        return properties;
    }

    private static MdSchemaPropertiesResponseRow getMdSchemaPropertiesResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Level level,
        mondrian.olap.Property property
    ) {
        Hierarchy hierarchy = level.getHierarchy();
        Dimension dimension = hierarchy.getDimension();
        String propertyName = property.getName();
        LevelDbTypeEnum dbType = getDBTypeFromProperty(property);

        String desc =
            cube.getName() + " Cube - "
                + getHierarchyName(hierarchy.getName(), dimension.getName()) + " Hierarchy - "
                + level.getName() + " Level - "
                + property.getName() + " Property";

        return new MdSchemaPropertiesResponseRowR(
            Optional.ofNullable(catalogName),
            Optional.ofNullable(schemaName),
            Optional.ofNullable(cube.getName()),
            Optional.ofNullable(dimension.getUniqueName()),
            Optional.ofNullable(hierarchy.getUniqueName()),
            Optional.ofNullable(level.getUniqueName()),
            //TODO: what is the correct value here? ""?
            Optional.empty(),
            Optional.of(PropertyTypeEnum.PROPERTY_MEMBER),
            Optional.ofNullable(propertyName),
            Optional.ofNullable(property.getCaption()),
            Optional.of(dbType),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.ofNullable(desc),
            Optional.of(PropertyContentTypeEnum.REGULAR),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(CubeSourceEnum.DIMENSION),
            Optional.of(VisibilityEnum.VISIBLE)
        );

    }

    private static LevelDbTypeEnum getDBTypeFromProperty(mondrian.olap.Property prop) {
    	if (prop.getType() != null) {
        switch (prop.getType()) {
            case TYPE_STRING:
                return LevelDbTypeEnum.DBTYPE_WSTR;
            case TYPE_INTEGER, TYPE_LONG, TYPE_NUMERIC:
                return LevelDbTypeEnum.DBTYPE_R8;
            case TYPE_BOOLEAN:
                return LevelDbTypeEnum.DBTYPE_BOOL;
            default:
                // TODO: what type is it really, its not a string
                return LevelDbTypeEnum.DBTYPE_WSTR;
        }
    	}
    	return LevelDbTypeEnum.DBTYPE_WSTR;
    }

    static List<MdSchemaMeasuresResponseRow> getMdSchemaMeasuresResponseRow(
        Context context,
        Optional<String> oSchemaName,
        Optional<String> oCubeName,
        Optional<String> oMeasureName,
        Optional<String> oMeasureUniqueName,
        Optional<String> oMeasureGroupName,
        boolean shouldEmitInvisibleMembers
    ) {
        return getSchemasWithFilter(context.getConnection().getSchemas(), oSchemaName)
            .stream().filter(s -> s != null)
            .map(s -> getMdSchemaMeasuresResponseRow(context.getName(), s, oCubeName, oMeasureName,
                oMeasureUniqueName, oMeasureGroupName, shouldEmitInvisibleMembers))
            .flatMap(Collection::stream).toList();

    }

    private static List<MdSchemaMeasuresResponseRow> getMdSchemaMeasuresResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> oCubeName,
        Optional<String> oMeasureName,
        Optional<String> oMeasureUniqueName,
        Optional<String> oMeasureGroupName,
        boolean shouldEmitInvisibleMembers
    ) {
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        return getCubesWithFilter(cubes, oCubeName).stream()
            .map(c -> getMdSchemaMeasuresResponseRow(catalogName, schema.getName(), c, oMeasureName,
                oMeasureUniqueName, oMeasureGroupName, shouldEmitInvisibleMembers))
            .flatMap(Collection::stream).toList();
    }

    private static List<MdSchemaMeasuresResponseRow> getMdSchemaMeasuresResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oMeasureName,
        Optional<String> oMeasureUniqueName,
        Optional<String> oMeasureGroupName,
        boolean shouldEmitInvisibleMembers
    ) {
        List<MdSchemaMeasuresResponseRow> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int j = 0;
        for (Dimension dimension : cube.getDimensions()) {
            if (!DimensionType.MEASURES_DIMENSION.equals(dimension.getDimensionType())) {
                for (Hierarchy hierarchy : dimension.getHierarchies()) {
                    Level[] levels = hierarchy.getLevels();
                    if (levels != null && levels.length > 0) {
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
            getMeasureWithFilterByUniqueName(getMeasureWithFilterByName(cube.getMeasures(), oMeasureName),
                oMeasureUniqueName);
        measures.stream().filter(m -> !m.isCalculated()).forEach(m -> populateMeasures(catalogName, schemaName,
            cube.getName(), levelListStr, shouldEmitInvisibleMembers, m, result));
        measures.stream().filter(m -> m.isCalculated()).forEach(m -> populateMeasures(catalogName, schemaName,
            cube.getName(), null, shouldEmitInvisibleMembers, m, result));
        return result;
    }

    private static void populateMeasures(
        String catalogName,
        String schemaName,
        String cubeName,
        String levelListStr,
        boolean shouldEmitInvisibleMembers,
        Member m,
        List<MdSchemaMeasuresResponseRow> result
    ) {
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
            final MeasureAggregatorEnum measureAggregator = getAggregator(m);

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
                Optional.ofNullable(formatString),
                Optional.empty(),
                Optional.of(VisibilityEnum.VISIBLE))
            );
        }
    }

    private static MeasureAggregatorEnum getAggregator(Member m) {
        if (m instanceof RolapStoredMeasure rolapStoredMeasure) {
            RolapAggregator rolapAggregator = rolapStoredMeasure.getAggregator();
            if (rolapAggregator != null) {
                if (rolapAggregator == RolapAggregator.Avg) {
                    return MeasureAggregatorEnum.MDMEASURE_AGGR_AVG;
                }
                if (rolapAggregator == RolapAggregator.Sum) {
                    return MeasureAggregatorEnum.MDMEASURE_AGGR_SUM;
                }
                if (rolapAggregator == RolapAggregator.Count) {
                    return MeasureAggregatorEnum.MDMEASURE_AGGR_COUNT;
                }
                if (rolapAggregator == RolapAggregator.DistinctCount) {
                    return MeasureAggregatorEnum.MDMEASURE_AGGR_DST;
                }
                if (rolapAggregator == RolapAggregator.Max) {
                    return MeasureAggregatorEnum.MDMEASURE_AGGR_MAX;
                }
                if (rolapAggregator == RolapAggregator.Min) {
                    return MeasureAggregatorEnum.MDMEASURE_AGGR_MIN;
                }
            }
        }
        return MeasureAggregatorEnum.MDMEASURE_AGGR_UNKNOWN;
    }

    private static List<org.eclipse.daanse.olap.api.element.Member> getMeasureWithFilterByName(
        List<org.eclipse.daanse.olap.api.element.Member> measures,
        Optional<String> oMeasureName
    ) {
        if (oMeasureName.isPresent()) {
            return measures.stream().filter(m -> oMeasureName.get().equals(m.getName())).toList();
        }
        return measures;
    }

    private static List<org.eclipse.daanse.olap.api.element.Member> getMeasureWithFilterByUniqueName(
        List<org.eclipse.daanse.olap.api.element.Member> measures,
        Optional<String> oMeasureUniqueName
    ) {
        if (oMeasureUniqueName.isPresent()) {
            return measures.stream().filter(m -> oMeasureUniqueName.get().equals(m.getUniqueName())).toList();
        }
        return measures;
    }
}
