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
package org.eclipse.daanse.olap.rolap.dbmapper.provider.sample.population.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.HierarchyR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinedQueryElementR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.LevelR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.PrivateDimensionR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.TableR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.CubeRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.HierarchyRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.LevelRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.PrivateDimensionRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.builder.SchemaRBuilder;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(service = DatabaseMappingSchemaProvider.class, scope = ServiceScope.SINGLETON, property = {"sample.name=Population",
    "sample.type=record"})
public class PopulationRecordDbMappingSchemaProvider implements DatabaseMappingSchemaProvider {

    public static final String STATE = "state";
    public static final String GENDER = "Gender";
    public static final String GENDER_ID = "gender_id";
    public static final String AGE_GROUP = "AgeGroup";
    public static final String GEOGRAPHICAL = "Geographical";

    private static final String POPULATION = "Population";
    private static final TableR TABLE_FACT = new TableR("population");
    private static final TableR TABLE1 = new TableR("year");
    private static final TableR TABLE22 = new TableR("country");
    private static final TableR TABLE23 = new TableR("continent");
    private static final TableR TABLE21 = new TableR(STATE);
    private static final TableR TABLE3 = new TableR("gender");
    private static final TableR TABLE4 = new TableR("ageGroups");
    private static final JoinR JOIN21 = new JoinR(
        new JoinedQueryElementR(null,
            "continent_id", TABLE22),
        new JoinedQueryElementR(null,
            "id", TABLE23));

    private static final MappingJoinQuery JOIN1 = new JoinR(
        new JoinedQueryElementR(null,
            "contry_id", TABLE21),
        new JoinedQueryElementR(null,
            "id", JOIN21));

    private static final LevelR LEVEL1 = LevelRBuilder
        .builder()
        .name("Year")
        .column("year")
        .ordinalColumn("ordinal")
        .description("Year")
        .build();

    private static final LevelR LEVEL21 = LevelRBuilder
        .builder()
        .name("Continent")
        .column("id")
        .nameColumn("name")
        .type(TypeEnum.INTEGER)
        .table("continent")
        .description("Continent")
        .build();

    private static final LevelR LEVEL22 = LevelRBuilder
        .builder()
        .name("Country")
        .column("id")
        .nameColumn("name")
        .type(TypeEnum.INTEGER)
        .table("country")
        .description("Country")
        .build();

    private static final LevelR LEVEL23 = LevelRBuilder
        .builder()
        .name("State")
        .column("id")
        .nameColumn("name")
        .type(TypeEnum.INTEGER)
        .table(STATE)
        .description("State")
        .build();

    private static final LevelR LEVEL3 = LevelRBuilder
        .builder()
        .name(GENDER)
        .column(GENDER_ID)
        .nameColumn("name")
        .type(TypeEnum.INTEGER)
        .description(GENDER)
        .build();

    private static final LevelR LEVEL41 = LevelRBuilder
        .builder()
        .name("Age")
        .column("age")
        .description("Age")
        .build();

    private static final LevelR LEVEL42 = LevelRBuilder
        .builder()
        .name(AGE_GROUP)
        .column("H1")
        .ordinalColumn("H1_Order")
        .description("Age Group H1")
        .build();

    private static final LevelR LEVEL43 = LevelRBuilder
        .builder()
        .name(AGE_GROUP)
        .column("H2")
        .ordinalColumn("H2_Order")
        .description("Age Group H2")
        .build();

    private static final LevelR LEVEL44 = LevelRBuilder
        .builder()
        .name(AGE_GROUP)
        .column("H9")
        .ordinalColumn("H9_Order")
        .description("Age Group H9")
        .build();

    private static final HierarchyR HIERARCHY1 = HierarchyRBuilder
        .builder()
        .hasAll(false)
        .name("Year")
        .primaryKey("year")
        .description("Year")
        .relation(TABLE1)
        .levels(List.of(LEVEL1))
        .build();

    private static final HierarchyR HIERARCHY2 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name(GEOGRAPHICAL)
        .primaryKey("id")
        .primaryKeyTable(STATE)
        .description(GEOGRAPHICAL)
        .relation(JOIN1)
        .levels(List.of(LEVEL21, LEVEL22, LEVEL23))
        .build();

    private static final HierarchyR HIERARCHY3 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name("Gender (m/f/d)")
        .primaryKey(GENDER_ID)
        .description(GENDER)
        .relation(TABLE3)
        .levels(List.of(LEVEL3))
        .build();

    private static final HierarchyR HIERARCHY41 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name("Age (single vintages)")
        .primaryKey("age")
        .description("Age (single vintages)")
        .relation(TABLE4)
        .levels(List.of(LEVEL41))
        .build();

    private static final HierarchyR HIERARCHY42 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name("Age group (Standard)")
        .primaryKey("age")
        .description("Age group (Standard)")
        .relation(TABLE4)
        .levels(List.of(LEVEL42, LEVEL41))
        .build();

    private static final HierarchyR HIERARCHY43 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name("Age group (children)")
        .primaryKey("age")
        .description("Age group (children)")
        .relation(TABLE4)
        .levels(List.of(LEVEL43, LEVEL41))
        .build();

    private static final HierarchyR HIERARCHY44 = HierarchyRBuilder
        .builder()
        .hasAll(true)
        .name("Age group (10-year groups)")
        .primaryKey("age")
        .description("Age group (10-year groups)")
        .relation(TABLE4)
        .levels(List.of(LEVEL44, LEVEL41))
        .build();

    private static final PrivateDimensionR DIMENSION1 = PrivateDimensionRBuilder
        .builder()
        .name("Year")
        .foreignKey("year")
        .hierarchies(List.of(HIERARCHY1))
        .build();

    private static final PrivateDimensionR DIMENSION2 = PrivateDimensionRBuilder
        .builder()
        .name(GEOGRAPHICAL)
        .foreignKey("state_id")
        .hierarchies(List.of(HIERARCHY2))
        .build();

    private static final PrivateDimensionR DIMENSION3 = PrivateDimensionRBuilder
        .builder()
        .name(GENDER)
        .foreignKey(GENDER_ID)
        .hierarchies(List.of(HIERARCHY3))
        .build();

    private static final PrivateDimensionR DIMENSION4 = PrivateDimensionRBuilder
        .builder()
        .name("Age")
        .foreignKey("age")
        .hierarchies(List.of(HIERARCHY41, HIERARCHY42, HIERARCHY43, HIERARCHY44))
        .build();

    private static final MappingCube CUBE = CubeRBuilder
        .builder()
        .name(POPULATION)
        .description("Population Cube")
        .fact(TABLE_FACT)
        .dimensionUsageOrDimensions(List.of(
            DIMENSION1,
            DIMENSION2,
            DIMENSION3,
            DIMENSION4))
        .build();

    private static final MappingSchema
        SCHEMA = SchemaRBuilder.builder()
        .name(POPULATION)
        .cubes(List.of(CUBE))
        .build();

    @Override
    public MappingSchema get() {
        return SCHEMA;
    }

}
