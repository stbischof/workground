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

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MeasureDataTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.util.SchemaTransformer;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaCreatorService;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaCreatorServiceFactory;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaInitData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import org.sqlite.SQLiteDataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
class SchemaCreatorServiceImplTest {

    public static final String COMPONENT_NAME = "org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.basic.SchemaCreatorServiceFactoryImpl";

    @Test
    @SuppressWarnings("java:S5961")
    void testPopulationDatabase(@InjectService(filter = "(component.name=" + COMPONENT_NAME + ")") SchemaCreatorServiceFactory schemaCreatorServiceFactory) throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        //dataSource.setUrl("jdbc:sqlite:/home/oem/repod/deMondrian/olap/rolap/dbmapper/schemacreator.basic/src/test/resources/population.sqlite");
        dataSource.setUrl("jdbc:sqlite:../../../../src/test/resources/population.sqlite");
        dataSource.setDatabaseName("population");
        SchemaCreatorService schemaCreatorService = schemaCreatorServiceFactory.create(dataSource);
        SchemaInitData schemaInitData = new SchemaInitData();
        schemaInitData.setFactTables(List.of("population"));
        MappingSchema s = schemaCreatorService.createSchema(schemaInitData);
        assertThat(s).isNotNull();
        assertThat(s.dimensions()).isNotNull().hasSize(4);

        MappingPrivateDimension d = getPrivateDimension(s.dimensions(), "Dimension State");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(1);
        MappingHierarchy h =  d.hierarchies().get(0);
        List <MappingLevel> levels = h.levels();
        assertThat(levels).isNotNull().hasSize(3);
        // check level0
        MappingLevel level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Continent");
        assertThat(level.column()).isEqualTo("id");
        assertThat(level.table()).isEqualTo("continent");
        assertThat(level.nameColumn()).isEqualTo("name");
        assertThat(level.description()).isEqualTo("Continent");
        assertThat(level.type()).isEqualTo(TypeEnum.INTEGER);
        // check level1
        level = levels.get(1);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Country");
        assertThat(level.column()).isEqualTo("id");
        assertThat(level.table()).isEqualTo("country");
        assertThat(level.nameColumn()).isEqualTo("name");
        assertThat(level.description()).isEqualTo("Country");
        assertThat(level.type()).isEqualTo(TypeEnum.INTEGER);
        // check level2
        level = levels.get(2);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("State");
        assertThat(level.column()).isEqualTo("id");
        assertThat(level.table()).isEqualTo("state");
        assertThat(level.nameColumn()).isEqualTo("name");
        assertThat(level.description()).isEqualTo("State");
        assertThat(level.type()).isEqualTo(TypeEnum.INTEGER);

        d = getPrivateDimension(s.dimensions(), "Dimension Gender");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(1);
        levels = d.hierarchies().get(0).levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Gender");
        assertThat(level.column()).isEqualTo("gender_id");
        assertThat(level.table()).isEqualTo("gender");
        assertThat(level.nameColumn()).isEqualTo("name");
        assertThat(level.description()).isEqualTo("Gender");
        assertThat(level.type()).isEqualTo(TypeEnum.INTEGER);

        d = getPrivateDimension(s.dimensions(), "Dimension Year");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(1);
        levels = d.hierarchies().get(0).levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Year");
        assertThat(level.column()).isEqualTo("year");
        assertThat(level.table()).isEqualTo("year");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Year");
        assertThat(level.type()).isEqualTo(TypeEnum.INTEGER);

        d = getPrivateDimension(s.dimensions(), "Dimension AgeGroups");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(1);
        levels = d.hierarchies().get(0).levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("AgeGroups");
        assertThat(level.column()).isEqualTo("age");
        assertThat(level.table()).isEqualTo("ageGroups");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("AgeGroups");
        assertThat(level.type()).isEqualTo(TypeEnum.INTEGER);

        // check cubes
        assertThat(s.cubes()).isNotNull().hasSize(1);
        MappingCube c = s.cubes().get(0);
        assertThat(c.name()).isEqualTo("Population");
        assertThat(c.description()).isEqualTo("Population");
        assertThat(c.caption()).isEqualTo("Population");
        assertThat(c.dimensionUsageOrDimensions()).isNotNull().hasSize(4);
        List<MappingDimensionUsage> dimensionUsageList = c.dimensionUsageOrDimensions()
            .stream().filter(du -> (du instanceof MappingDimensionUsage))
            .map(du -> (MappingDimensionUsage) du).collect(Collectors.toList());
        assertThat(dimensionUsageList).hasSize(4);

        MappingDimensionUsage du = getDimensionUsage(dimensionUsageList, "Dimension State");
        assertThat(du).isNotNull();
        assertThat(du.source()).isEqualTo("Dimension State");
        assertThat(du.foreignKey()).isEqualTo("state_id");

        du = getDimensionUsage(dimensionUsageList, "Dimension Gender");
        assertThat(du).isNotNull();
        assertThat(du.source()).isEqualTo("Dimension Gender");
        assertThat(du.foreignKey()).isEqualTo("gender_id");

        du = getDimensionUsage(dimensionUsageList, "Dimension Year");
        assertThat(du).isNotNull();
        assertThat(du.source()).isEqualTo("Dimension Year");
        assertThat(du.foreignKey()).isEqualTo("year");

        du = getDimensionUsage(dimensionUsageList, "Dimension AgeGroups");
        assertThat(du).isNotNull();
        assertThat(du.source()).isEqualTo("Dimension AgeGroups");
        assertThat(du.foreignKey()).isEqualTo("age");

        assertThat(s.cubes()).hasSize(1);
        MappingTableQuery t = (MappingTableQuery)c.fact();
        assertThat(t.getName()).isEqualTo("population");

        marshallSchema(s);
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testEmployeesDatabase(@InjectService(filter = "(component.name=" + COMPONENT_NAME + ")") SchemaCreatorServiceFactory schemaCreatorServiceFactory) throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:../../../../src/test/resources/employees.sqlite");
        SchemaCreatorService schemaCreatorService = schemaCreatorServiceFactory.create(dataSource);
        SchemaInitData schemaInitData = new SchemaInitData();
        schemaInitData.setFactTables(List.of("employees"));
        MappingSchema s = schemaCreatorService.createSchema(schemaInitData);
        assertThat(s).isNotNull();
        assertThat(s.dimensions()).isNotNull().hasSize(3);

        // check Dimension Jobs
        MappingPrivateDimension d = getPrivateDimension(s.dimensions(), "Dimension Jobs");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(1);
        MappingHierarchy h =  d.hierarchies().get(0);
        List <MappingLevel> levels = h.levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        MappingLevel level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Jobs");
        assertThat(level.column()).isEqualTo("job_id");
        assertThat(level.table()).isEqualTo("jobs");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Jobs");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);

        // check Dimension Departments
        d = getPrivateDimension(s.dimensions(), "Dimension Departments");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(1);
        h =  d.hierarchies().get(0);
        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(4);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Regions");
        assertThat(level.column()).isEqualTo("region_id");
        assertThat(level.table()).isEqualTo("regions");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Regions");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        // check level1
        level = levels.get(1);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Countries");
        assertThat(level.column()).isEqualTo("country_id");
        assertThat(level.table()).isEqualTo("countries");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Countries");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        // check level2
        level = levels.get(2);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Locations");
        assertThat(level.column()).isEqualTo("location_id");
        assertThat(level.table()).isEqualTo("locations");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Locations");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        // check level3
        level = levels.get(3);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Departments");
        assertThat(level.column()).isEqualTo("department_id");
        assertThat(level.table()).isEqualTo("departments");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Departments");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);

        d = getPrivateDimension(s.dimensions(), "Dimension Employees");
        assertThat(d).isNotNull();
        assertThat(d.hierarchies()).isNotNull().hasSize(2);

        h =  d.hierarchies().get(0);
        assertThat(h.name()).isEqualTo("employees_employee_id");
        assertThat(h.hasAll()).isTrue();
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_employee_id");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_employee_id");
        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(5);

        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Regions");
        assertThat(level.column()).isEqualTo("region_id");
        assertThat(level.table()).isEqualTo("regions");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Regions");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        level = levels.get(1);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Countries");
        assertThat(level.column()).isEqualTo("country_id");
        assertThat(level.table()).isEqualTo("countries");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Countries");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        level = levels.get(2);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Locations");
        assertThat(level.column()).isEqualTo("location_id");
        assertThat(level.table()).isEqualTo("locations");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Locations");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        level = levels.get(3);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Departments");
        assertThat(level.column()).isEqualTo("department_id");
        assertThat(level.table()).isEqualTo("departments");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Departments");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        level = levels.get(4);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("employee_id");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        h =  d.hierarchies().get(1);
        levels = h.levels();
        assertThat(h.name()).isEqualTo("employees_employee_id_1");
        assertThat(h.hasAll()).isTrue();
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_employee_id");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_employee_id");
        assertThat(levels).isNotNull().hasSize(2);

        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Jobs");
        assertThat(level.column()).isEqualTo("job_id");
        assertThat(level.table()).isEqualTo("jobs");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Jobs");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        level = levels.get(1);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("employee_id");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.NUMERIC);
        assertThat(level.visible()).isTrue();
        assertThat(level.uniqueMembers()).isTrue();

        assertThat(s.cubes()).hasSize(1);
        MappingCube c = s.cubes().get(0);
        assertThat(c.name()).isEqualTo("Employees");
        assertThat(c.caption()).isEqualTo("Employees");
        assertThat(c.description()).isEqualTo("Employees");
        assertThat(c.cache()).isTrue();
        assertThat(c.enabled()).isTrue();
        assertThat(c.visible()).isTrue();
        assertThat(c.fact()).isNotNull();
        assertThat(c.fact()).isInstanceOf(MappingTableQuery.class);
        MappingTableQuery t = (MappingTableQuery)c.fact();
        assertThat(t.getName()).isEqualTo("employees");
        assertThat(c.dimensionUsageOrDimensions()).hasSize(8);
        assertThat(c.dimensionUsageOrDimensions().get(0)).isInstanceOf(MappingDimensionUsage.class);
        assertThat(c.dimensionUsageOrDimensions().get(1)).isInstanceOf(MappingDimensionUsage.class);
        assertThat(c.dimensionUsageOrDimensions().get(2)).isInstanceOf(MappingDimensionUsage.class);

        assertThat(c.dimensionUsageOrDimensions().get(3)).isInstanceOf(MappingPrivateDimension.class);
        assertThat(c.dimensionUsageOrDimensions().get(4)).isInstanceOf(MappingPrivateDimension.class);
        assertThat(c.dimensionUsageOrDimensions().get(5)).isInstanceOf(MappingPrivateDimension.class);
        assertThat(c.dimensionUsageOrDimensions().get(6)).isInstanceOf(MappingPrivateDimension.class);
        assertThat(c.dimensionUsageOrDimensions().get(7)).isInstanceOf(MappingPrivateDimension.class);

        MappingDimensionUsage du = (MappingDimensionUsage) c.dimensionUsageOrDimensions().get(0);
        assertThat(du.name()).isEqualTo("Dimension Departments");
        assertThat(du.source()).isEqualTo("Dimension Departments");
        assertThat(du.foreignKey()).isEqualTo("department_id");
        assertThat(du.caption()).isEqualTo("departments");
        assertThat(du.description()).isEqualTo("Dimension for department_id");
        assertThat(du.visible()).isTrue();

        du = (MappingDimensionUsage) c.dimensionUsageOrDimensions().get(1);
        assertThat(du.name()).isEqualTo("Dimension Employees");
        assertThat(du.source()).isEqualTo("Dimension Employees");
        assertThat(du.foreignKey()).isEqualTo("manager_id");
        assertThat(du.caption()).isEqualTo("employees");
        assertThat(du.description()).isEqualTo("Dimension for manager_id");
        assertThat(du.visible()).isTrue();

        du = (MappingDimensionUsage) c.dimensionUsageOrDimensions().get(2);
        assertThat(du.name()).isEqualTo("Dimension Jobs");
        assertThat(du.source()).isEqualTo("Dimension Jobs");
        assertThat(du.foreignKey()).isEqualTo("job_id");
        assertThat(du.caption()).isEqualTo("jobs");
        assertThat(du.description()).isEqualTo("Dimension for job_id");
        assertThat(du.visible()).isTrue();

        d = (MappingPrivateDimension) c.dimensionUsageOrDimensions().get(3);
        assertThat(d.name()).isEqualTo("first_name");
        assertThat(d.caption()).isEqualTo("first_name");
//        assertThat(d.description()).isEqualTo("first_name");
        assertThat(d.foreignKey()).isEqualTo("first_name");
        assertThat(d.visible()).isTrue();
        assertThat(d.hierarchies()).hasSize(1);
        h = d.hierarchies().get(0);
        assertThat(h.name()).isEqualTo("employees_first_name");
        assertThat(h.primaryKey()).isEqualTo("first_name");
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_first_name");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_first_name");
        assertThat(h.visible()).isTrue();

        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("first_name");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();

        d = (MappingPrivateDimension) c.dimensionUsageOrDimensions().get(4);
        assertThat(d.name()).isEqualTo("last_name");
        assertThat(d.caption()).isEqualTo("last_name");
//        assertThat(d.description()).isEqualTo("last_name");
        assertThat(d.foreignKey()).isEqualTo("last_name");
        assertThat(d.visible()).isTrue();
        assertThat(d.hierarchies()).hasSize(1);
        h = d.hierarchies().get(0);
        assertThat(h.name()).isEqualTo("employees_last_name");
        assertThat(h.primaryKey()).isEqualTo("last_name");
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_last_name");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_last_name");
        assertThat(h.visible()).isTrue();

        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("last_name");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();

        d = (MappingPrivateDimension) c.dimensionUsageOrDimensions().get(5);
        assertThat(d.name()).isEqualTo("email");
        assertThat(d.caption()).isEqualTo("email");
//        assertThat(d.description()).isEqualTo("email");
        assertThat(d.foreignKey()).isEqualTo("email");
        assertThat(d.visible()).isTrue();
        assertThat(d.hierarchies()).hasSize(1);
        h = d.hierarchies().get(0);
        assertThat(h.name()).isEqualTo("employees_email");
        assertThat(h.primaryKey()).isEqualTo("email");
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_email");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_email");
        assertThat(h.visible()).isTrue();

        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("email");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();

        d = (MappingPrivateDimension) c.dimensionUsageOrDimensions().get(6);
        assertThat(d.name()).isEqualTo("phone_number");
        assertThat(d.caption()).isEqualTo("phone_number");
//        assertThat(d.description()).isEqualTo("phone_number");
        assertThat(d.foreignKey()).isEqualTo("phone_number");
        assertThat(d.visible()).isTrue();
        assertThat(d.hierarchies()).hasSize(1);
        h = d.hierarchies().get(0);
        assertThat(h.name()).isEqualTo("employees_phone_number");
        assertThat(h.primaryKey()).isEqualTo("phone_number");
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_phone_number");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_phone_number");
        assertThat(h.visible()).isTrue();

        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("phone_number");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();

        d = (MappingPrivateDimension) c.dimensionUsageOrDimensions().get(7);
        assertThat(d.name()).isEqualTo("hire_date");
        assertThat(d.caption()).isEqualTo("hire_date");
//        assertThat(d.description()).isEqualTo("hire_date");
        assertThat(d.foreignKey()).isEqualTo("hire_date");
        assertThat(d.visible()).isTrue();
        assertThat(d.hierarchies()).hasSize(1);
        h = d.hierarchies().get(0);
        assertThat(h.name()).isEqualTo("employees_hire_date");
        assertThat(h.primaryKey()).isEqualTo("hire_date");
        assertThat(h.caption()).isEqualTo("Caption for hierarchyemployees_hire_date");
        assertThat(h.description()).isEqualTo("Description for hierarchyemployees_hire_date");
        assertThat(h.visible()).isTrue();

        levels = h.levels();
        assertThat(levels).isNotNull().hasSize(1);
        // check level0
        level = levels.get(0);
        assertThat(level).isNotNull();
        assertThat(level.name()).isEqualTo("Employees");
        assertThat(level.column()).isEqualTo("hire_date");
        assertThat(level.table()).isEqualTo("employees");
        assertThat(level.nameColumn()).isNull();
        assertThat(level.description()).isEqualTo("Employees");
        assertThat(level.type()).isEqualTo(TypeEnum.STRING);
        assertThat(level.visible()).isTrue();

        assertThat(c.measures()).hasSize(3);
        MappingMeasure m = c.measures().get(0);
        assertThat(m.name()).isEqualTo("employee_id");
        assertThat(m.column()).isEqualTo("employee_id");
//        assertThat(m.caption()).isEqualTo("employee_id");
        assertThat(m.description()).isEqualTo("employee_id");
        assertThat(m.datatype()).isEqualTo(MeasureDataTypeEnum.NUMERIC);
//        assertThat(m.formatString()).isEqualTo("Standard");
        assertThat(m.aggregator()).isEqualTo("sum");
        assertThat(m.visible()).isTrue();

        m = c.measures().get(1);
        assertThat(m.name()).isEqualTo("salary");
        assertThat(m.column()).isEqualTo("salary");
//        assertThat(m.caption()).isEqualTo("salary");
        assertThat(m.description()).isEqualTo("salary");
        assertThat(m.datatype()).isEqualTo(MeasureDataTypeEnum.NUMERIC);
//        assertThat(m.formatString()).isEqualTo("Standard");
        assertThat(m.aggregator()).isEqualTo("sum");
        assertThat(m.visible()).isTrue();

        m = c.measures().get(2);
        assertThat(m.name()).isEqualTo("commission_pct");
        assertThat(m.column()).isEqualTo("commission_pct");
//        assertThat(m.caption()).isEqualTo("commission_pct");
        assertThat(m.description()).isEqualTo("commission_pct");
        assertThat(m.datatype()).isEqualTo(MeasureDataTypeEnum.NUMERIC);
//        assertThat(m.formatString()).isEqualTo("Standard");
        assertThat(m.aggregator()).isEqualTo("sum");
        assertThat(m.visible()).isTrue();

        marshallSchema(s);
    }

    private void marshallSchema(MappingSchema s) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SchemaImpl.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(SchemaTransformer.transformSchema(s), System.out);
        } catch (JAXBException e) {
            fail("marshalling error");
        }
    }

    private MappingPrivateDimension getPrivateDimension(List<MappingPrivateDimension> dimensions, String name) {
        return dimensions.stream().filter(d -> name.equals(d.name()))
            .findAny()
            .orElse(null);
    }

    private MappingDimensionUsage getDimensionUsage(List<MappingDimensionUsage> dimensions, String name) {
        return dimensions.stream().filter(d -> name.equals(d.name()))
            .findAny()
            .orElse(null);
    }
}
