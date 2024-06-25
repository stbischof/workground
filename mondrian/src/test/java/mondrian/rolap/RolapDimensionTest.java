/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2015-2017 Hitachi Vantara..  All rights reserved.
*/
package mondrian.rolap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.HideMemberIfEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.HierarchyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.LevelImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.PrivateDimensionImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import mondrian.olap.SystemWideProperties;



class RolapDimensionTest {

  private RolapSchema schema;
  private RolapCube cube;
  private PrivateDimensionImpl xmlDimension;
  private MappingCubeDimension xmlCubeDimension;
  private HierarchyImpl hierarchy;


  @BeforeEach
  public void beforeEach() {

    schema = Mockito.mock(RolapSchema.class);
    cube = Mockito.mock(RolapCube.class);
    MappingRelationQuery fact = Mockito.mock(MappingRelationQuery.class);

    Mockito.when(cube.getSchema()).thenReturn(schema);
    Mockito.when(cube.getFact()).thenReturn(fact);

    xmlDimension = new PrivateDimensionImpl();
    hierarchy = new HierarchyImpl();
    LevelImpl level = new LevelImpl();
    xmlCubeDimension = new PrivateDimensionImpl();

    xmlDimension.setName("dimensionName");
    xmlDimension.setVisible(true);
    xmlDimension.setHierarchies(List.of(hierarchy));


    hierarchy.setVisible(true);
    hierarchy.setHasAll(false);
    hierarchy.setLevels(List.of(level));

    level.setVisible(true);
    level.setProperties(List.of());
    level.setUniqueMembers(true);
    level.setType(TypeEnum.STRING);
    level.setHideMemberIf(HideMemberIfEnum.NEVER);
    level.setLevelType(LevelTypeEnum.REGULAR);
  }

  @AfterEach
  public void afterEach() {
    SystemWideProperties.instance().populateInitial();
  }

  @Disabled("disabled for CI build") //disabled for CI build
  @Test
  void testHierarchyRelation() {
    MappingRelationQuery hierarchyTable = Mockito
            .mock(MappingRelationQuery.class);
    hierarchy.setRelation(hierarchyTable);

    new RolapDimension(schema, cube, xmlDimension, xmlCubeDimension);
    assertNotNull(hierarchy);
    assertEquals(hierarchyTable, hierarchy.relation());
  }

  /**
   * Check that hierarchy.relation is not set to cube.fact
   */
  @Disabled("disabled for CI build") //disabled for CI build
  @Test
  void testHierarchyRelationNotSet() {
    new RolapDimension(schema, cube, xmlDimension, xmlCubeDimension);

    assertNotNull(hierarchy);
    assertNull(hierarchy.relation());
  }

}
