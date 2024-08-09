/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.PrivateDimensionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.VirtualCubeDimensionImpl;
import org.eclipse.daanse.rolap.mapping.api.model.DimensionMapping;
import org.eclipse.daanse.rolap.mapping.pojo.StandardDimensionMappingImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RolapCubeDimensionTest {

  private RolapCubeDimension stubRolapCubeDimension(boolean virtualCube) {
    RolapCube cube = mock(RolapCube.class);
    doReturn(virtualCube).when(cube).isVirtual();

    RolapDimension rolapDim = mock(TestPublicRolapDimension.class);
    Hierarchy[] rolapDim_hierarchies = new Hierarchy[]{};
    doReturn(rolapDim_hierarchies).when(rolapDim).getHierarchies();
    
    DimensionMapping cubeDim = StandardDimensionMappingImpl.builder().build();
    cubeDim.setCaption("StubCubeDimCaption");
    cubeDim.setDescription("StubCubeDimDescription");
    cubeDim.setVisible(true);
    String name = "StubCubeName";
    int cubeOrdinal = 0;
    List<RolapHierarchy> hierarchyList = null;

    return new RolapCubeDimension(
        cube,
        rolapDim,
        cubeDim,
        name,
        cubeOrdinal,
        hierarchyList);
  }

  @Test
  void testLookupCube_null() {
    RolapCubeDimension rcd = stubRolapCubeDimension(false);

    assertEquals(null, rcd.lookupFactCube(null, null));
  }

  @Test
  void testLookupCube_notVirtual() {
    RolapCubeDimension rcd = stubRolapCubeDimension(false);
    DimensionMapping cubeDim = StandardDimensionMappingImpl.builder().build();
    RolapSchema schema = mock(RolapSchema.class);

    assertEquals(null, rcd.lookupFactCube(cubeDim, schema));
    verify(schema, times(0)).lookupCube(anyString());
    verify(schema, times(0)).lookupCube(anyString(), anyBoolean());
  }

  @Test
  void testLookupCube_noSuchCube() {
    RolapCubeDimension rcd = stubRolapCubeDimension(false);
    DimensionMapping cubeDim = StandardDimensionMappingImpl.builder().build();
    RolapSchema schema = mock(RolapSchema.class);
    final String cubeName = "TheCubeName";
    cubeDim.setCubeName(cubeName);
    // explicit doReturn - just to make it evident
    doReturn(null).when(schema).lookupCube(anyString());

    assertEquals(null, rcd.lookupFactCube(cubeDim, schema));
    Mockito.verify(schema).lookupCube(cubeName);
  }

  @Test
  void testLookupCube_found() {
    RolapCubeDimension rcd = stubRolapCubeDimension(false);
    VirtualCubeDimensionImpl cubeDim = new VirtualCubeDimensionImpl();
    RolapSchema schema = mock(RolapSchema.class);
    RolapCube factCube = mock(RolapCube.class);
    final String cubeName = "TheCubeName";
    cubeDim.setCubeName(cubeName);
    doReturn(factCube).when(schema).lookupCube(cubeName);

    assertEquals(factCube, rcd.lookupFactCube(cubeDim, schema));
  }
}
