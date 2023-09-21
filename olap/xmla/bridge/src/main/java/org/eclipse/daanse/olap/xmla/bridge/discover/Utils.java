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

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.CalculatedMemberProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Cube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.CubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.DimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Hierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Measure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.PrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Role;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Schema;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataResponseRowR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesResponseRowR;
import org.olap4j.metadata.XmlaConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
        // constructor
    }

    static Optional<String> getRoles(List<Role> roles) {
        if (roles != null) {
            return Optional.of(roles.stream().map(Role::name).collect(Collectors.joining(",")));
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
            Schema schema = dsp.get();
            return getDbSchemaColumnsResponseRow(schema, oTableName, oColumnName, oColumnOlapType);
        }).flatMap(Collection::stream).toList();
    }

    static List<DbSchemaColumnsResponseRow> getDbSchemaColumnsResponseRow(
        Schema schema,
        Optional<String> oTableName,
        Optional<String> oColumnName,
        Optional<ColumnOlapTypeEnum> oColumnOlapType
    ) {
        return schema.cubes().stream().sorted(Comparator.comparing(Cube::name))
            .map(c -> getDbSchemaColumnsResponseRow(schema.name(), c, oTableName, oColumnName, oColumnOlapType))
            .flatMap(Collection::stream).toList();
    }

    static List<DbSchemaColumnsResponseRow> getDbSchemaColumnsResponseRow(
        String schemaName,
        Cube cube,
        Optional<String> oTableName,
        Optional<String> oColumnName,
        Optional<ColumnOlapTypeEnum> oColumnOlapType
    ) {
        int ordinalPosition = 1;
        List<DbSchemaColumnsResponseRow> result = new ArrayList<>();
        if (!oTableName.isPresent() || (oTableName.isPresent() && oTableName.get().equals(cube.name()))) {
            final boolean emitInvisibleMembers = true; //TODO
            for (CubeDimension dimension : cube.dimensionUsageOrDimensions()) {
                if (dimension instanceof PrivateDimension pd) {
                    for (Hierarchy hierarchy : pd.hierarchies()) {
                        ordinalPosition =
                            populateHierarchy(
                                schemaName,
                                cube, hierarchy,
                                ordinalPosition, result);
                    }
                }
                if (dimension instanceof DimensionUsage du) {
                    //TODO find dimension in shared? Ask Sergei
                }
            }
            for (Measure measure : cube.measures()) {

                Boolean visible = true;
                Optional<CalculatedMemberProperty> oP = measure.calculatedMemberProperties()
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
                    Optional.of(schemaName),
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

    static int populateHierarchy(
        String schemaName,
        Cube cube,
        Hierarchy hierarchy,
        int ordinalPosition,
        List<DbSchemaColumnsResponseRow> result
    ) {
        String cubeName = cube.name();
        String hierarchyName = hierarchy.name();
        result.add(new DbSchemaColumnsResponseRowR(
            Optional.of(schemaName),
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
        Optional<String> oTableSchema
    ) {
        if (oTableSchema.isPresent()) {
            return databaseMappingSchemaProviders.stream().filter(dmsp -> dmsp.get().name().equals(oTableSchema.get())).toList();
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
        Schema schema = dmsp.get();
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
                .map(dmsp -> getMdSchemaCubesResponseRow(context.getName(), dmsp.get(), cubeName, baseCubeName, cubeSource))
                .flatMap(Collection::stream)
                .toList();
        }
        return List.of();
    }

    private static List<MdSchemaCubesResponseRow> getMdSchemaCubesResponseRow(
        String catalogName,
        Schema schema,
        Optional<String> cubeName,
        Optional<String> baseCubeName,
        Optional<CubeSourceEnum> cubeSource
    ) {
        if (schema.cubes() != null) {
            return getCubesWithFilter(schema.cubes(), cubeName).stream().
                map(c -> getMdSchemaCubesResponseRow(catalogName, schema.name(), c)).
                flatMap(Collection::stream).toList();
        }
        return List.of();
    }

    private static List<MdSchemaCubesResponseRow> getMdSchemaCubesResponseRow(
        String catalogName,
        String schemaName,
        Cube cube) {
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

    private static List<Cube> getCubesWithFilter(List<Cube> cubes, Optional<String> cubeName) {
        if (cubeName.isPresent()) {
            return cubes.stream().filter(c -> cubeName.get().equals(c.name())).toList();
        }
        return cubes;
    }

}
