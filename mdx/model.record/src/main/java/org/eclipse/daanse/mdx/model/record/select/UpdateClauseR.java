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
package org.eclipse.daanse.mdx.model.record.select;

import org.eclipse.daanse.mdx.model.api.expression.Expression;
import org.eclipse.daanse.mdx.model.api.select.Allocation;
import org.eclipse.daanse.mdx.model.api.select.UpdateClause;

public record UpdateClauseR(Expression tupleExp, Expression valueExp, Allocation allocation, Expression weight) implements UpdateClause {

}
