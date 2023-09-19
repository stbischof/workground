/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.olap.api.query.component;

import mondrian.olap.Exp;
import mondrian.olap.FunCall;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.type.Type;

public non-sealed interface ResolvedFunCall extends QueryPart, FunCall {

    int getArgCount();

    FunctionDefinition getFunDef();

    Exp getArg(int i);

    Exp[] getArgs();

    Type getType(boolean caseSensitive);
}
