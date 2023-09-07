/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package org.eclipse.daanse.function.core;

import org.eclipse.daanse.function.api.FunctionDefinition;
import org.eclipse.daanse.function.api.FunctionResolver;

import mondrian.olap.Syntax;

/**
 * <code>ResolverBase</code> provides a skeleton implementation of
 * <code>interface {@link FunctionResolver}</code>
 *
 * @author jhyde
 * @since 3 March, 2002
 */
public abstract class AbstractFunctionResolver implements FunctionResolver {
    private final String name;
    private final String signature;
    private final String description;
    private final Syntax syntax;

    public AbstractFunctionResolver(
        String name,
        String signature,
        String description,
        Syntax syntax)
    {
        this.name = name;
        this.signature = signature;
        this.description = description;
        this.syntax = syntax;
    }

    @Override
	public String getName() {
        return name;
    }

    @Override
	public String getSignature() {
        return signature;
    }

    @Override
	public FunctionDefinition getRepresentativeFunDef() {
        return null;
    }

    @Override
	public String getDescription() {
        return description;
    }

    @Override
	public Syntax getSyntax() {
        return syntax;
    }

    @Override
	public boolean requiresExpression(int k) {
        return false;
    }

    @Override
	public String[] getReservedWords() {
        return new String[] {};
    }
}
