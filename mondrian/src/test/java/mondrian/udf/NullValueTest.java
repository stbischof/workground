/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2003-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// All Rights Reserved.
//
// jhyde, Feb 14, 2003
*/

package mondrian.udf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.result.Cell;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.TestUtil;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

/**
 * <code>NullValueTest</code> is a test case which tests simple queries
 * expressions.
 *
 * @author <a>Richard M. Emberson</a>
 * @since Mar 01 2007
 */
class NullValueTest{


	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void testNullValue(Context context) {
		Connection connection=context.getConnection();
		String cubeName="Sales";
        Cell c=  TestUtil.executeExprRaw(connection,cubeName," NullValue()/NullValue() ");
        String s=c.getFormattedValue();
        assertEquals("", s);

        c = TestUtil.executeExprRaw(connection,cubeName," NullValue()/NullValue() = NULL ");
        s=c.getFormattedValue();

        assertEquals("false", s);

        boolean hasException = false;
        try {
            c = TestUtil.executeExprRaw(connection,cubeName," NullValue() IS NULL ");
            s=c.getFormattedValue();
        } catch (Exception ex) {
            hasException = true;
        }
        assertTrue(hasException);

        // I believe that these IsEmpty results are correct.
        // The NullValue function does not represent a cell.
        c = TestUtil.executeExprRaw(connection,cubeName," IsEmpty(NullValue()) ");
        s=c.getFormattedValue();

        assertEquals("false", s);

        // NullValue()/NullValue() evaluates to DoubleNull
        // but DoubleNull evaluates to null, so this seems
        // to be broken??
        // s = executeExpr(" IsEmpty(NullValue()/NullValue()) ");
        // assertEquals("false", s);

        c = TestUtil.executeExprRaw(connection,cubeName," 4 + NullValue() ");
        s=c.getFormattedValue();
        assertEquals("4", s);

        c = TestUtil.executeExprRaw(connection,cubeName," NullValue() - 4 ");
        s=c.getFormattedValue();
        assertEquals("-4", s);

        c = TestUtil.executeExprRaw(connection,cubeName," 4*NullValue() ");
        s=c.getFormattedValue();
        assertEquals("", s);

        c = TestUtil.executeExprRaw(connection,cubeName," NullValue()*4 ");
        s=c.getFormattedValue();
        assertEquals("", s);

        c = TestUtil.executeExprRaw(connection,cubeName," 4/NullValue() ");
        s=c.getFormattedValue();
        assertEquals("Infinity", s);

        c = TestUtil.executeExprRaw(connection,cubeName," NullValue()/4 ");
        s=c.getFormattedValue();
        assertEquals("", s);
/*
*/
    }
}
