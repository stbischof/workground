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

import org.eclipse.daanse.mdx.model.api.UpdateStatement;
import org.eclipse.daanse.mdx.model.api.expression.ObjectIdentifier;
import org.eclipse.daanse.mdx.parser.api.MdxParserException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateStatementTest {

	@Test
	void test1() throws MdxParserException {
        UpdateStatement clause = new MdxParserWrapper("UPDATE CUBE Cube_Name").parseUpdateStatement();
        assertThat(clause).isNotNull();
        assertThat(clause.cubeName()).isNotNull();
        assertThat(clause.cubeName().name()).isEqualTo("Cube_Name");
        assertThat(clause.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.UNQUOTED);
	}

    @Test
    void test2() throws MdxParserException {
        UpdateStatement clause = new MdxParserWrapper("UPDATE CUBE [Cube_Name]").parseUpdateStatement();
        assertThat(clause).isNotNull();
        assertThat(clause.cubeName()).isNotNull();
        assertThat(clause.cubeName().name()).isEqualTo("Cube_Name");
        assertThat(clause.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.QUOTED);
    }

}
