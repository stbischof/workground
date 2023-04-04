/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2015-2017 Hitachi Vantara.
// All Rights Reserved.
*/
package mondrian.rolap;

import static mondrian.enums.DatabaseProduct.getDatabaseProduct;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import mondrian.enums.DatabaseProduct;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mondrian.olap.Exp;
import mondrian.olap.Literal;
import mondrian.olap.fun.MondrianEvaluationException;
import mondrian.olap.type.NullType;
import mondrian.olap.type.TypeWrapperExp;
import mondrian.rolap.sql.SqlQuery;

/**
 * @author Andrey Khayrutdinov
 */
public class NumberSqlCompilerTest {

    private static RolapNativeSql.NumberSqlCompiler compiler;

    @BeforeAll
    public static void beforeAll() throws Exception {
        Dialect dialect = mock(Dialect.class);
        when(dialect.getDialectName())
            .thenReturn("mysql");

        when(dialect.quoteDecimalLiteral("1"))
            .thenReturn(new StringBuilder("1"));

        when(dialect.quoteDecimalLiteral("+1.01"))
        .thenReturn(new StringBuilder("+1.01"));

        when(dialect.quoteDecimalLiteral("-.00001"))
        .thenReturn(new StringBuilder("-.00001"));

        when(dialect.quoteDecimalLiteral("-1"))
        .thenReturn(new StringBuilder("-1"));

        SqlQuery query = mock(SqlQuery.class);
        when(query.getDialect()).thenReturn(dialect);

        RolapNativeSql sql = new RolapNativeSql(query, null, null, null);
        compiler = sql.new NumberSqlCompiler();
    }

    @AfterAll
    public static void afterAll() throws Exception {
        compiler = null;
    }

    @Test
    public void testRejectsNonLiteral() {
        Exp exp = new TypeWrapperExp(new NullType());
        assertNull(compiler.compile(exp));
    }

    @Test
    public void testAcceptsNumeric() {
        Exp exp = Literal.create(BigDecimal.ONE);
        assertNotNull(compiler.compile(exp));
    }

    @Test
    public void testAcceptsString_Int() {
        checkAcceptsString("1");
    }

    @Test
    public void testAcceptsString_Negative() {
        checkAcceptsString("-1");
    }

    @Test
    public void testAcceptsString_ExplicitlyPositive() {
        checkAcceptsString("+1.01");
    }

    @Test
    public void testAcceptsString_NoIntegerPart() {
        checkAcceptsString("-.00001");
    }

    private void checkAcceptsString(String value) {
        Exp exp = Literal.createString(value);
        assertNotNull(value, compiler.compile(exp).toString());
    }


    @Test
    public void testRejectsString_SelectStatement() {
        checkRejectsString("(select 100)");
    }

    @Test
    public void testRejectsString_NaN() {
        checkRejectsString("NaN");
    }

    @Test
    public void testRejectsString_Infinity() {
        checkRejectsString("Infinity");
    }

    @Test
    public void testRejectsString_TwoDots() {
        checkRejectsString("1.0.");
    }

    @Test
    public void testRejectsString_OnlyDot() {
        checkRejectsString(".");
    }

    @Test
    public void testRejectsString_DoubleNegation() {
        checkRejectsString("--1.0");
    }

    private void checkRejectsString(String value) {
        Exp exp = Literal.createString(value);
        try {
            compiler.compile(exp);
        } catch (MondrianEvaluationException e) {
            return;
        }
        fail("Expected to get MondrianEvaluationException for " + value);
    }
}
