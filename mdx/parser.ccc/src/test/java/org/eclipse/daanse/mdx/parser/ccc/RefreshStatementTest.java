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
package org.eclipse.daanse.mdx.parser.ccc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.daanse.mdx.parser.ccc.CubeTest.propertyWords;

import org.eclipse.daanse.mdx.model.api.RefreshStatement;
import org.eclipse.daanse.mdx.model.api.expression.ObjectIdentifier;
import org.eclipse.daanse.mdx.parser.api.MdxParserException;
import org.junit.jupiter.api.Test;

class RefreshStatementTest {

	@Test
	void test1() throws MdxParserException {
        RefreshStatement clause = new MdxParserWrapper("REFRESH CUBE Cube_Name", propertyWords).parseRefreshStatement();
        assertThat(clause).isNotNull();
        assertThat(clause.cubeName()).isNotNull();
        assertThat(clause.cubeName().name()).isEqualTo("Cube_Name");
        assertThat(clause.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.UNQUOTED);
	}

    @Test
    void test2() throws MdxParserException {
        RefreshStatement clause = new MdxParserWrapper("REFRESH CUBE [Cube_Name]", propertyWords).parseRefreshStatement();
        assertThat(clause).isNotNull();
        assertThat(clause.cubeName()).isNotNull();
        assertThat(clause.cubeName().name()).isEqualTo("Cube_Name");
        assertThat(clause.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.QUOTED);
    }

}
