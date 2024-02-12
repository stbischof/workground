/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 1998-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
//
// Shishir, 08 May, 2007
*/

package mondrian.olap;

import static mondrian.olap.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.query.component.CellProperty;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.QueryComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

import mondrian.server.Statement;

/**
 * Query test.
 */
class QueryTest {
    private CellProperty[] cellProps = {
        new CellPropertyImpl(Segment.toList("Value")),
        new CellPropertyImpl(Segment.toList("Formatted_Value")),
        new CellPropertyImpl(Segment.toList("Format_String")),
    };
    private QueryAxisImpl[] axes = new QueryAxisImpl[0];
    private Formula[] formulas = new Formula[0];
    private QueryImpl queryWithCellProps;
    private QueryImpl queryWithoutCellProps;


    private void beforeTest(Context context)
    {

        ConnectionBase connection =
                (ConnectionBase) context.getConnection();
        final Statement statement =
                connection.getInternalStatement();

        try {
            queryWithCellProps =
                    new QueryImpl(
                            statement, formulas, axes, "Sales",
                            null, cellProps, false);
            queryWithoutCellProps =
                    new QueryImpl(
                            statement, formulas, axes, "Sales",
                            null, new CellProperty[0], false);
        } finally {
            statement.close();
        }
    }

    @AfterEach
    public void afterEach() {
        queryWithCellProps = null;
        queryWithoutCellProps = null;
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class)
    void testHasCellPropertyWhenQueryHasCellProperties(Context context) {
        beforeTest(context);
        assertTrue(queryWithCellProps.hasCellProperty("Value"));
        assertFalse(queryWithCellProps.hasCellProperty("Language"));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class)
    void testIsCellPropertyEmpty(Context context) {
        beforeTest(context);
        assertTrue(queryWithoutCellProps.isCellPropertyEmpty());
    }
}
