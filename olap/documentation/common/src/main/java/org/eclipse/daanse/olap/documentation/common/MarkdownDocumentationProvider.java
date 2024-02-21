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
package org.eclipse.daanse.olap.documentation.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.documentation.api.ConntextDocumentationProvider;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingView;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import static java.lang.StringTemplate.RAW;
import static java.lang.StringTemplate.STR;

@Component(service = ConntextDocumentationProvider.class, scope = ServiceScope.SINGLETON, immediate = true)
public class MarkdownDocumentationProvider extends AbstractContextDocumentationProvider {

    public static final String EMPTY_STRING = "";
    private static String ENTER = System.lineSeparator();
    private static StringTemplate schemaTemplate = RAW.
        """
        ### Schema {schemaName}:

        """;

    private static StringTemplate publicDimensionTemplate = RAW.
            """
            ### Public Dimensions:
            {dimensions}

            """;

    private static StringTemplate publicDimensionTemplate1 = RAW.
            """
            ##### Dimension {dimension}:

            Hierarchies:

            {hierarchies}

            """;

    private static StringTemplate hierarchyTemplate = RAW.
            """
            ##### Hierarchy {name}:

            Tables: {tables}
            Levels: {levels}

            """;

    private static StringTemplate levelTemplate = RAW.
            """
            ###### Level {name} :

            column(s): {columns}

            """;

    private static StringTemplate cubesTemplate = RAW.
            """
            ### Cubes :

            {cubes}

            """;

    private static StringTemplate cubeTemplate = RAW.
            """
            #### Cube {name}:

            {description}

            ##### Table: {table}

            """;

    private static StringTemplate dimensionUsageTemplate = RAW.
            """
            ##### Dimension: {name} -> {source}:

            """;

    private static StringTemplate roleTemplate = RAW.
            """
            ##### Role: {name}

            """;

    @Override
    public void createDocumentation(Context context, Path path) throws Exception {

        File file = path.toFile();

        if (file.exists()) {
            Files.delete(path);
        }

        FileWriter writer = new FileWriter(file);
        writer.write("# Documentation");
        writer.write(ENTER);
        writer.write("CatalogName : " + getCatalogName(context.getName()));
        writer.write("## Olap Context Details:");
        writeSchemas(writer, context);
        writer.flush();
        writer.close();

    }

    private String getCatalogName(String path) {
        int index = path.lastIndexOf("\\");
        if (path.length() < index) {
            return path.substring(index + 1);
        }
        return path;
    }

    private void writeSchemas(FileWriter writer, Context context) throws IOException {
        writer.write("## Schemas:\n");
        context.getDatabaseMappingSchemaProviders().forEach(p -> {
            MappingSchema schema = p.get();
            writeSchema(writer, schema);
        });
    }

    private void writeSchema(FileWriter writer, MappingSchema schema) {
        try {
            String schemaName = schema.name();
            String dimensions = schema.dimensions().stream().map(d -> d.name())
                .collect(Collectors.joining(","));
            writer.write(STR.process(schemaTemplate));
            writer.write(STR.process(publicDimensionTemplate));
            writeList(writer, schema.dimensions(), this::writePublicDimension);
            String cubes = schema.cubes().stream().map(c -> c.name())
                .collect(Collectors.joining(","));
            writer.write(STR.process(cubesTemplate));
            writeList(writer, schema.cubes(), this::writeCube);
            //write roles
            writeRoles(writer, schema.roles());
            //write database

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRoles(FileWriter writer, List<MappingRole> roles) {
        try {
            if (roles != null && !roles.isEmpty()) {
                writer.write("### Roles :");
                writeList(writer, roles, this::writeRole);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRole(FileWriter writer, MappingRole role) {
        try {
            String name = role.name();
            writer.write(STR.process(roleTemplate));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeCube(FileWriter writer, MappingCube cube) {
        try {
            String description = cube.description();
            String name = cube.name();
            String table = getTable(cube.fact());
            writer.write(STR.process(cubeTemplate));
            writeCubeDimensions(writer, cube.dimensionUsageOrDimensions());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCubeDimensions(FileWriter writer, List<MappingCubeDimension> dimensionUsageOrDimensions) {
        try {
            writer.write("##### Dimensions:");
            writeList(writer, dimensionUsageOrDimensions, this::writeCubeDimension);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCubeDimension(FileWriter writer, MappingCubeDimension d) {
        if (d instanceof MappingDimensionUsage du) {
            writeDimensionUsage(writer, du);
        }
        if (d instanceof MappingPrivateDimension pd) {
            writePublicDimension(writer, pd);
        }
    }

    private void writeDimensionUsage(FileWriter writer, MappingDimensionUsage du) {
        try {
            String name = du.name();
            String source = du.source();
            writer.write(STR.process(dimensionUsageTemplate));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePublicDimension(FileWriter writer, MappingPrivateDimension d) {
        try {
            String dimension = d.name();
            String description = d.description();
            String hierarchies = d.hierarchies().stream().map(h -> h.name())
                .collect(Collectors.joining(","));
            writer.write(STR.process(publicDimensionTemplate1));
            writeList(writer, d.hierarchies(), this::writeHierarchy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHierarchy(FileWriter writer, MappingHierarchy h) {
        try {
            String name = h.name();
            String tables = getTable(h.relation());
            String levels = h.levels() != null ? h.levels().stream().map(l -> l.name())
                .collect(Collectors.joining(",")) : EMPTY_STRING;
            writer.write(STR.process(hierarchyTemplate));
            writeList(writer, h.levels(), this::writeLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLevel(FileWriter writer, MappingLevel level) {
        try {
            String name = level.name();
            String description = level.description();
            writer.write(STR.process(levelTemplate));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private <T> void writeList(FileWriter writer, List<T> list, BiConsumer<FileWriter, T> consumer) {
        if (list != null) {
            list.forEach(h -> consumer.accept(writer, h));
        }
    }

    private String getTable(MappingRelationOrJoin relation) {
        if (relation instanceof MappingTable mt) {
            return mt.name();
        }
        if (relation instanceof MappingInlineTable it) {
            //TODO
        }
        if (relation instanceof MappingView mv) {
            StringBuilder sb = new StringBuilder();
            if (mv.sqls() != null) {
                mv.sqls().stream().filter(s -> "generic".equals(s.dialect()))
                    .findFirst().ifPresent(s -> sb.append(s.content()));
            }
            return sb.toString();
        }
        if (relation instanceof MappingJoin mj) {
            if (mj.relations() != null) {
                return mj.relations().stream().map(this::getTable).collect(Collectors.joining(","));
            }
        }
        return "";
    }



    /*
     * # General
     *
     * Name: ${ContextName}
     *
     * Description: ${ContextDescription}
     *
     * # Olap Context Details:
     *
     * ## Schemas
     *
     * Overview Table on Schemas (with count of cubes and dimension)
     *
     * ### Schema ${SchemaName}
     *
     * Description: ${SchemaDescription}
     *
     * Overview Table on Public Dimensions
     *
     * Overview Table on Cubes
     *
     * Overview Table on Roles
     *
     *
     * #### Public Dimensions
     *
     * Overview Table on Public Dimensions
     *
     * ##### Public Dimension { DimName}
     *
     * Description: ${CubeDescription}
     *
     * ... Hierarchies
     *
     * #### Cubes
     *
     * Overview Table on Cubes
     *
     * #### Cubes ${CubeName} #### Cubes ${CubeName}
     *
     * Description: ${CubeDescription}
     *
     * .... Publi
     *
     * #### Roles
     *
     * # SQL Context Details:
     *
     *
     * List of all Tables that are used in Olap with column and type and description
     * in database.
     *
     *
     * PRINT_FIRST_N_ROWS
     *
     *
     *
     * # Checks:
     *
     * errors in Mapping all errors we have in the verifyer
     *
     *
     *
     */

    private static String PRINT_FIRST_N_ROWS = """
        	Fact:

        		| DIM_KEY  |      VALUE    |
        		|----------|:-------------:|
        		|   1      |       42      |
        		|   2      |       21      |
        		|   3      |       84      |

        		Level1:

        		|   KEY    |     NAME      |
        		|----------|:-------------:|
        		|   1      |      A        |
        		|   2      |      B        |

        		Level2:

        		|   KEY    |     NAME   |   L1_KEY   |
        		|----------|:----------:|:----------:|
        		|   1      |      AA    |     1      |
        		|   2      |      AB    |     1      |
        		|   3      |      BA    |     2      |
        """;

    /**
     * Step 2
     *
     *
     *
     *
     * # class diagram for lebel properties
     * https://mermaid.js.org/syntax/classDiagram.html
     *
     * #use ERD Diagrams for sql Table and the joins defines ion olapmapping
     * https://mermaid.js.org/syntax/entityRelationshipDiagram.html
     *
     * #use class Diagrams for olap Cubes -> Dimensions -> Hirarchies -> levels ->
     * private Dim
     *
     * each type a custom color https://mermaid.js.org/syntax/classDiagram.html
     *
     * # Analyses Cubes
     *
     * Y-AXIS : rows in fact table
     *
     * X-Axis : number of hierarchies
     *
     * https://mermaid.js.org/syntax/quadrantChart.html
     *
     *
     */

}
