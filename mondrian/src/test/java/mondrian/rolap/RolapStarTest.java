/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2018 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Relation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.RelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Table;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SQLImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.TableImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestingContext;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.rolap.RolapStar.Column;
import mondrian.rolap.util.RelationUtil;

/**
 * Unit test for {@link RolapStar}.
 *
 * @author pedrovale
 */
class RolapStarTest {

    static class RolapStarForTests extends RolapStar {
        public RolapStarForTests(
            final RolapSchema schema,
            final Context context,
            final Relation fact)
        {
            super(schema, context, fact);
        }

        public RelationOrJoin cloneRelationForTests(
            Relation rel,
            String possibleName)
        {
            return cloneRelation(rel, possibleName);
        }
    }

    RolapStar getRolapStar(Connection con, String starName) {
        RolapCube cube =
            (RolapCube) con.getSchema().lookupCube(starName, true);
        return cube.getStar();
    }

    RolapStarForTests getStar(Connection connection, String starName) {
        RolapStar rs =  getRolapStar(connection, starName);

        return new RolapStarForTests(
            rs.getSchema(),
            rs.getContext(),
            rs.getFactTable().getRelation());
    }

    /**
     * Tests that given a {@link Table}, cloneRelation
     * respects the existing filters.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testCloneRelationWithFilteredTable(TestingContext context) {
      RolapStarForTests rs = getStar(context.createConnection(), "sales");
      TableImpl original = new TableImpl();
      original.setName("TestTable");
      original.setAlias("Alias");
      original.setSchema("Sechema");
      original.setSql(new SQLImpl());
      original.sql().setDialect("generic");
      original.sql().setContent("Alias.clicked = 'true'");

      Table cloned = (Table)rs.cloneRelationForTests(
          original,
          "NewAlias");

      assertEquals("NewAlias", RelationUtil.getAlias(cloned));
      assertEquals("TestTable", cloned.name());
      assertNotNull(cloned.sql());
      assertEquals("NewAlias.clicked = 'true'", cloned.sql().content());
  }

   //Below there are tests for mondrian.rolap.RolapStar.ColumnComparator
   @Test
   void testTwoColumnsWithDifferentNamesNotEquals() {
     RolapStar.ColumnComparator colComparator =
         RolapStar.ColumnComparator.instance;
     Column column1 = getColumnMock("Column1", "Table1");
     Column column2 = getColumnMock("Column2", "Table1");
     assertNotSame(column1, column2);
     assertEquals(-1, colComparator.compare(column1, column2));
   }

   @Test
   void testTwoColumnsWithEqualsNamesButDifferentTablesNotEquals() {
     RolapStar.ColumnComparator colComparator =
         RolapStar.ColumnComparator.instance;
     Column column1 = getColumnMock("Column1", "Table1");
     Column column2 = getColumnMock("Column1", "Table2");
     assertNotSame(column1, column2);
     assertEquals(-1, colComparator.compare(column1, column2));
   }

   @Test
   void testTwoColumnsEquals() {
     RolapStar.ColumnComparator colComparator =
         RolapStar.ColumnComparator.instance;
     Column column1 = getColumnMock("Column1", "Table1");
     Column column2 = getColumnMock("Column1", "Table1");
     assertNotSame(column1, column2);
     assertEquals(0, colComparator.compare(column1, column2));
   }

   private static Column getColumnMock(
       String columnName,
       String tableName)
   {
     Column colMock = mock(Column.class);
     RolapStar.Table tableMock = mock(RolapStar.Table.class);
     when(colMock.getName()).thenReturn(columnName);
     when(colMock.getTable()).thenReturn(tableMock);
     when(tableMock.getAlias()).thenReturn(tableName);
    return colMock;
   }
}
