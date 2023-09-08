/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.mdx.parser.cccx;

import org.eclipse.daanse.mdx.model.api.UpdateStatement;
import org.eclipse.daanse.mdx.model.api.expression.CallExpression;
import org.eclipse.daanse.mdx.model.api.expression.NumericLiteral;
import org.eclipse.daanse.mdx.model.api.expression.ObjectIdentifier;
import org.eclipse.daanse.mdx.model.api.select.UpdateClause;
import org.eclipse.daanse.mdx.parser.api.MdxParserException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

class UpdateStatementTest {

	@Test
	void test1() throws MdxParserException {
        UpdateStatement clause = new MdxParserWrapper("UPDATE CUBE Cube_Name SET ([Measures].[Sales Count], [Time].[Time].[1997].[Q1]) = 55, ([Measures].[Sales Count], [Time].[Time].[1997].[Q2]) = 33").parseUpdateStatement();
        assertThat(clause).isNotNull();
        assertThat(clause.cubeName()).isNotNull();
        assertThat(clause.cubeName().name()).isEqualTo("Cube_Name");
        assertThat(clause.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.UNQUOTED);
        assertThat(clause.updateClauses()).isNotNull().hasSize(2);
        assertThat(clause.updateClauses().get(0)).isNotNull();
        assertThat(clause.updateClauses().get(1)).isNotNull();
        assertThat(clause.updateClauses().get(0)).isInstanceOf(UpdateClause.class);
        assertThat(clause.updateClauses().get(1)).isInstanceOf(UpdateClause.class);        
        UpdateClause updateClause1 = clause.updateClauses().get(0);
        UpdateClause updateClause2 = clause.updateClauses().get(1);
        assertThat(updateClause1.tupleExp()).isNotNull().isInstanceOf(CallExpression.class);
        assertThat(updateClause1.valueExp()).isNotNull().isInstanceOf(NumericLiteral.class);
        CallExpression callExpression = (CallExpression)updateClause1.tupleExp();
        NumericLiteral numericLiteral = (NumericLiteral)updateClause1.valueExp();
        assertThat(numericLiteral.value()).isEqualTo(BigDecimal.valueOf(55));
        assertThat(callExpression.name()).isEqualTo("()");
        assertThat(callExpression.type()).isEqualTo(CallExpression.Type.PARENTHESES);
        assertThat(callExpression.expressions()).hasSize(2);
	}

    @Test
    void test2() throws MdxParserException {
        UpdateStatement clause = new MdxParserWrapper("UPDATE CUBE [Cube_Name] SET ([Measures].[Sales Count], [Time].[Time].[1997].[Q1]) = 55, ([Measures].[Sales Count], [Time].[Time].[1997].[Q2]) = 33").parseUpdateStatement();
        assertThat(clause).isNotNull();
        assertThat(clause.cubeName()).isNotNull();
        assertThat(clause.cubeName().name()).isEqualTo("Cube_Name");
        assertThat(clause.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.QUOTED);
        assertThat(clause.updateClauses()).isNotNull().hasSize(2);
        assertThat(clause.updateClauses().get(0)).isNotNull();
        assertThat(clause.updateClauses().get(1)).isNotNull();
        assertThat(clause.updateClauses().get(0)).isInstanceOf(UpdateClause.class);
        assertThat(clause.updateClauses().get(1)).isInstanceOf(UpdateClause.class);        
        UpdateClause updateClause1 = clause.updateClauses().get(0);
        UpdateClause updateClause2 = clause.updateClauses().get(1);
        assertThat(updateClause1.tupleExp()).isNotNull().isInstanceOf(CallExpression.class);
        assertThat(updateClause1.valueExp()).isNotNull().isInstanceOf(NumericLiteral.class);
        CallExpression callExpression = (CallExpression)updateClause1.tupleExp();
        NumericLiteral numericLiteral = (NumericLiteral)updateClause1.valueExp();
        assertThat(numericLiteral.value()).isEqualTo(BigDecimal.valueOf(55));
        assertThat(callExpression.name()).isEqualTo("()");
        assertThat(callExpression.type()).isEqualTo(CallExpression.Type.PARENTHESES);
        assertThat(callExpression.expressions()).hasSize(2);
    }

}
