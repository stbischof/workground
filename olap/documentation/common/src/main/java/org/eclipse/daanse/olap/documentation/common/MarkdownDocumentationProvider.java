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

import org.eclipse.daanse.db.jdbc.metadata.impl.Column;
import org.eclipse.daanse.db.jdbc.metadata.impl.JdbcMetaDataServiceLiveImpl;
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
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Level;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.VerificationResult;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.mandantory.MandantoriesVerifyer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.StringTemplate.STR;

@Component(service = ConntextDocumentationProvider.class, scope = ServiceScope.SINGLETON, immediate = true)
public class MarkdownDocumentationProvider extends AbstractContextDocumentationProvider {

    public static final String EMPTY_STRING = "";
    private static String ENTER = System.lineSeparator();
    //TODO use injection
    private MandantoriesVerifyer verifyer = new MandantoriesVerifyer();

    @Override
    public void createDocumentation(Context context, Path path) throws Exception {

        File file = path.toFile();

        if (file.exists()) {
            Files.delete(path);
        }
        String dbName = getCatalogName(context.getName());
        FileWriter writer = new FileWriter(file);
        writer.write("# Documentation");
        writer.write(ENTER);
        writer.write("### CatalogName : " + dbName);
        writer.write(ENTER);
        writer.write("## Olap Context Details:");
        writer.write(ENTER);
        writeSchemas(writer, context);
        writeDatabaseInfo(writer, context);
        writeVerifyer(writer, context);
        writer.flush();
        writer.close();

    }

    private void writeVerifyer(FileWriter writer, Context context) {
        context.getDatabaseMappingSchemaProviders().forEach(p -> {
            MappingSchema schema = p.get();
            writeSchemaVerifyer(writer, schema, context);
        });

    }

    private void writeSchemaVerifyer(FileWriter writer, MappingSchema schema, Context context) {
        try {
            List<VerificationResult> verifyResult = verifyer.verify(schema, context.getDataSource());
            if (verifyResult != null) {
                writer.write("## Validation result for schema " + schema.name());
                writer.write(ENTER);
                for (Level l : Level.values()) {
                    Map<String, VerificationResult> map = getVerificationResultMap(verifyResult, l);
                    Optional<VerificationResult> first = map.values().stream().findFirst();
                    if (first.isPresent()) {
                        String levelName = getColoredLevel(first.get().level());
                        writer.write(STR."## \{levelName} : ");
                        writer.write(ENTER);
                        writer.write("|Type|   |");
                        writer.write(ENTER);
                        writer.write("|----|---|");
                        writer.write(ENTER);
                        map.values().stream()
                            .sorted((r1, r2) -> r1.cause().compareTo(r2.cause()))
                            .forEach(r -> {
                                writeVerifyResult(writer, r);
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getColoredLevel(Level level) {
        switch (level) {
            case ERROR:
                return "<span style='color: red;'>" + level.name() + "</span>";
            case WARNING:
                return "<span style='color: blue;'>" + level.name() + "</span>";
            case INFO:
                return "<span style='color: yellow;'>" + level.name() + "</span>";
            case QUALITY:
                return "<span style='green: yellow;'>" + level.name() + "</span>";
            default:
                return "<span style='color: red;'>" + level.name() + "</span>";
        }
    }

    private Map<String, VerificationResult> getVerificationResultMap(List<VerificationResult> verifyResult, Level l) {
        return verifyResult.stream().filter(r -> l.equals(r.level()))
            .collect(Collectors.toMap(VerificationResult::description,  Function.identity(), (o1, o2) -> o1));
    }

    private void writeVerifyResult(FileWriter writer, VerificationResult r) {
        try {
            writer.write("|" + r.cause().name()  + "|" + r.description() + "|");
            writer.write(ENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getCatalogName(String path) {
        int index = path.lastIndexOf(File.separator);
        if (path.length() > index) {
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
            String dimensions = schema.dimensions().stream().map(d -> d.name())
                .collect(Collectors.joining(", "));
            String schemaName = schema.name();
            writer.write(STR."### Schema \{schemaName} : ");
            writer.write(ENTER);
            if (schema.documentation().isPresent() && schema.documentation().get().documentation() != null) {
                writer.write(schema.documentation().get().documentation());
                writer.write(ENTER);
            }
            writer.write(STR. """
                ### Public Dimensions:

                    \{dimensions}

                """);
            writeList(writer, schema.dimensions(), this::writePublicDimension);
            String cubes = schema.cubes().stream().map(c -> c.name())
                .collect(Collectors.joining(", "));
            writer.write(STR. """
                ---
                ### Cubes :

                    \{cubes}

                """);
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
            writer.write(STR. """
                ##### Role: "\{name}"

                """);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeCube(FileWriter writer, MappingCube cube) {
        try {
            String description = cube.description() != null ? cube.description() : EMPTY_STRING;
            String name = cube.name();
            String table = getTable(cube.fact());
            writer.write(STR. """
                ---
                #### Cube "\{name}":

                    \{description}

                ##### Table: "\{table}"

                """);
            writeCubeDimensions(writer, cube.dimensionUsageOrDimensions());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCubeDimensions(FileWriter writer, List<MappingCubeDimension> dimensionUsageOrDimensions) {
        try {
            writer.write("##### Dimensions:");
            writer.write(ENTER);
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
            writer.write(STR. """
                ##### Dimension: "\{name} -> \{source}":

                """);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePublicDimension(FileWriter writer, MappingPrivateDimension d) {
        try {
            String dimension = d.name();
            String description = d.description();
            AtomicInteger index = new AtomicInteger();
            String hierarchies = d.hierarchies().stream().map(h -> h.name() == null ?
                "Hierarchy" + index.getAndIncrement() : h.name())
                .collect(Collectors.joining(", "));
            writer.write(STR. """
                ##### Dimension "\{dimension}":

                Hierarchies:

                    \{hierarchies}

                """);
            writeHierarchies(writer, d.hierarchies());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHierarchies(FileWriter writer, List<MappingHierarchy> hierarchies) {
        if (hierarchies != null) {
            AtomicInteger index = new AtomicInteger();
            hierarchies.forEach(h -> writeHierarchy(writer, index.getAndIncrement(), h));
        }
    }

    private void writeHierarchy(FileWriter writer, int index, MappingHierarchy h) {
        try {
            String name = h.name() == null ? "Hierarchy" + index : h.name();
            String tables = getTable(h.relation());
            String levels = h.levels() != null ? h.levels().stream().map(l -> l.name())
                .collect(Collectors.joining(", ")) : EMPTY_STRING;
            writer.write(STR. """
                ##### Hierarchy \{name}:

                Tables: "\{tables}"

                Levels: "\{levels}"

                """);
            writeList(writer, h.levels(), this::writeLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLevel(FileWriter writer, MappingLevel level) {
        try {
            String name = level.name();
            String description = level.description();
            String columns = level.column();
            writer.write(STR. """
                ###### Level "\{name}" :

                    column(s): \{columns}

                """);
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

    private void writeDatabaseInfo(FileWriter writer, Context context) {
        try (Connection connection = context.getDataSource().getConnection()) {
            JdbcMetaDataServiceLiveImpl jdbcMetaDataService = new JdbcMetaDataServiceLiveImpl(connection);
            List<String> tables = jdbcMetaDataService.getTables(null);
            writeTables(writer, tables, jdbcMetaDataService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeTables(
        final FileWriter writer,
        final List<String> tables,
        final JdbcMetaDataServiceLiveImpl jdbcMetaDataService
    ) {
        try {
            if (tables != null && !tables.isEmpty()) {
                writer.write("### Database :");
                tables.forEach(t -> writeTable(writer, t, jdbcMetaDataService));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTable(FileWriter writer, String name, JdbcMetaDataServiceLiveImpl jdbcMetaDataService) {
        try {
            List<Column> columnList = jdbcMetaDataService.getColumns(null, name);
            String columns = new StringBuilder("|")
                .append(columnList.stream().map(c -> c.getName()).collect(Collectors.joining("|")))
                .append("|").toString();
            String line = new StringBuilder("|")
                .append(columnList.stream().map(c -> "---").collect(Collectors.joining("|")))
                .append("|").toString();
            String types = new StringBuilder("|")
                .append(columnList.stream().map(c -> TYPE_MAP.get(c.getType())).collect(Collectors.joining("|")))
                .append("|").toString();

            writer.write(STR. """
                "\{name}":

                \{columns}
                \{line}
                \{types}

                """);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
