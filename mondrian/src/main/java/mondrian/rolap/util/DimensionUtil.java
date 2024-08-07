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
package mondrian.rolap.util;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.rolap.mapping.api.model.DimensionMapping;
import org.eclipse.daanse.rolap.mapping.api.model.SchemaMapping;

import mondrian.olap.Util;
import mondrian.rolap.RolapRuntimeException;

public class DimensionUtil {

    public static final String SCHEMA_NULL = "schema != null";

    private DimensionUtil() {
        // constructor
    }

    public static DimensionMapping getDimension(SchemaMapping schema, DimensionMapping dimension) {
    	/*
        if (dimension instanceof MappingDimensionUsage dimensionUsage) {
            Util.assertPrecondition(schema != null, SCHEMA_NULL);
            for (int i = 0; i < schema.dimensions().size(); i++) {
                if (schema.dimensions().get(i).name().equals(dimensionUsage.source())) {
                    return schema.dimensions().get(i);
                }
            }
            throw Util.newInternal(
                new StringBuilder("Cannot find shared dimension '")
                    .append(dimensionUsage.source()).append("'").toString());
        }
        */
        if (dimension instanceof MappingPrivateDimension privateDimension) {
            Util.assertPrecondition(schema != null, SCHEMA_NULL);
            return privateDimension;
        }
        if (dimension instanceof MappingVirtualCubeDimension virtualCubeDimension) {
            Util.assertPrecondition(schema != null, SCHEMA_NULL);
            if (virtualCubeDimension.cubeName() == null) {
                return getPublicDimension(schema, dimension.getName());
            } else {
                MappingCube cube = getCube(schema, virtualCubeDimension.cubeName());
                return getDimension(cube, schema, dimension.getName());
            }
        }
        throw new RolapRuntimeException("getDimension error");
    }

    private static MappingPrivateDimension  getPublicDimension(MappingSchema schema, String dimensionName) {
        for (int i = 0; i < schema.dimensions().size(); i++) {
            if (schema.dimensions().get(i).name().equals(dimensionName)) {
                return schema.dimensions().get(i);
            }
        }
        throw Util.newInternal(
            new StringBuilder("Cannot find public dimension '").append(dimensionName).append("'").toString());
    }


    private static MappingCube getCube(MappingSchema schema, String cubeName) {
        for (int i = 0; i < schema.cubes().size(); i++) {
            if (schema.cubes().get(i).name().equals(cubeName)) {
                return schema.cubes().get(i);
            }
        }
        throw Util.newInternal(new StringBuilder("Cannot find cube '").append(cubeName).append("'").toString());
    }

    private static MappingPrivateDimension getDimension(MappingCube cube, MappingSchema schema, String dimensionName) {
        for (int i = 0; i < cube.dimensionUsageOrDimensions().size(); i++) {
            if (cube.dimensionUsageOrDimensions().get(i).name().equals(dimensionName)) {
                return getDimension(schema, cube.dimensionUsageOrDimensions().get(i));
            }
        }
        throw Util.newInternal(
            new StringBuilder("Cannot find dimension '").append(dimensionName).append("' in cube '")
                .append(cube.name()).append("'").toString());

    }
}
