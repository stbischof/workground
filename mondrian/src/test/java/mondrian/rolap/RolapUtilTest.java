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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.TableImpl;
import org.junit.jupiter.api.Test;
import org.opencube.junit5.SchemaUtil;

class RolapUtilTest {

  private static final String FILTER_QUERY =
      "`TableAlias`.`promotion_id` = 112";
  private static final String FILTER_DIALECT = "mysql";
  private static final String TABLE_ALIAS = "TableAlias";
  private static final String RELATION_ALIAS = "RelationAlias";
  private static final String FACT_NAME = "order_fact";
  private MappingRelation fact;

  @Test
  void testMakeRolapStarKeyUnmodifiable() throws Exception {
    try {
      fact = SchemaUtil.parse(getFactTableWithSQLFilter(), TableImpl.class);
      List<String> polapStarKey = RolapUtil.makeRolapStarKey(FACT_NAME);
      assertNotNull(polapStarKey);
      polapStarKey.add("OneMore");
      fail(
          "It should not be allowed to change the rolap star key."
          + "UnsupportedOperationException expected but was not  been appeared.");
      } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  void testMakeRolapStarKey_ByFactTableName() throws Exception {
    fact = SchemaUtil.parse(getFactTableWithSQLFilter(), TableImpl.class);
    List<String> polapStarKey = RolapUtil.makeRolapStarKey(FACT_NAME);
    assertNotNull(polapStarKey);
    assertEquals(1, polapStarKey.size());
    assertEquals(FACT_NAME, polapStarKey.get(0));
  }

  @Test
  void testMakeRolapStarKey_FactTableWithSQLFilter() throws Exception {
    fact = SchemaUtil.parse(getFactTableWithSQLFilter(), TableImpl.class);
    List<String> polapStarKey = RolapUtil.makeRolapStarKey(fact);
    assertNotNull(polapStarKey);
    assertEquals(3, polapStarKey.size());
    assertEquals(TABLE_ALIAS, polapStarKey.get(0));
    assertEquals(FILTER_DIALECT, polapStarKey.get(1));
    assertEquals(FILTER_QUERY, polapStarKey.get(2));
  }

  @Test
  void testMakeRolapStarKey_FactTableWithEmptyFilter()
      throws Exception {
    fact = SchemaUtil.parse(getFactTableWithEmptySQLFilter(), TableImpl.class);
    List<String> polapStarKey = RolapUtil.makeRolapStarKey(fact);
    assertNotNull(polapStarKey);
    assertEquals(1, polapStarKey.size());
    assertEquals(TABLE_ALIAS, polapStarKey.get(0));
  }

  @Test
  void testMakeRolapStarKey_FactTableWithoutSQLFilter()
      throws Exception {
    fact = SchemaUtil.parse(getFactTableWithoutSQLFilter(), TableImpl.class);
    List<String> polapStarKey = RolapUtil.makeRolapStarKey(fact);
    assertNotNull(polapStarKey);
    assertEquals(1, polapStarKey.size());
    assertEquals(TABLE_ALIAS, polapStarKey.get(0));
  }

  @Test
  void testMakeRolapStarKey_FactRelation() throws Exception {
    List<String> polapStarKey = RolapUtil.makeRolapStarKey(
        getFactRelationMock());
    assertNotNull(polapStarKey);
    assertEquals(1, polapStarKey.size());
    assertEquals(RELATION_ALIAS, polapStarKey.get(0));
  }

  private static String getFactTableWithSQLFilter() {
    String fact =
        "<Table name=\"sales_fact_1997\" alias=\"TableAlias\">\n"
        + " <SQL dialect=\"mysql\">\n"
        + "     `TableAlias`.`promotion_id` = 112\n"
        + " </SQL>\n"
        + "</Table>";
    return fact;
  }

  private static String getFactTableWithEmptySQLFilter() {
    String fact =
        "<Table name=\"sales_fact_1997\" alias=\"TableAlias\">\n"
        + " <SQL dialect=\"mysql\"/>\n"
        + "</Table>";
    return fact;
  }

  private static String getFactTableWithoutSQLFilter() {
    String fact =
        "<Table name=\"sales_fact_1997\" alias=\"TableAlias\">\n"
        + "</Table>";
    return fact;
  }

  private static MappingRelation getFactRelationMock() throws Exception {
    MappingRelation factMock = mock(MappingInlineTable.class);
    when(factMock.alias()).thenReturn(RELATION_ALIAS);
    return factMock;
  }

}
