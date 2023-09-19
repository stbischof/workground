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

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.NamedSet;
import org.eclipse.daanse.olap.api.element.OlapElement;

import mondrian.mdx.MdxVisitor;
import mondrian.olap.Exp;
import mondrian.olap.Validator;

public non-sealed interface Formula extends QueryPart {

    boolean isMember();

    NamedSet getNamedSet();

    Id getIdentifier();

    String getName();

    String getCaption();

    Exp getExpression();

    Exp setExpression(Exp exp);

    Member getMdxMember();

    Number getSolveOrder(boolean caseSensitive);

    void compile();

    Id getId();

    Exp getExp();

    NamedSet getMdxSet();

    MemberProperty[] getPemberProperties();

    void accept(Validator validator, boolean caseSensitive);

    Object accept(MdxVisitor visitor, boolean caseSensitive);

    void createElement(Query q);

    OlapElement getElement();

    String getUniqueName();

    void rename(String newName);
}
