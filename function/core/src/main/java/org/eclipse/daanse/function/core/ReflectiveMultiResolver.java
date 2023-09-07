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

import mondrian.olap.Exp;
import mondrian.olap.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.daanse.function.api.FunctionDefinition;
import org.eclipse.daanse.function.api.FunctionResolver;

/**
 * Resolver which uses reflection to instantiate a {@link FunctionDefinition}.
 * This reduces the amount of anonymous classes.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
public class ReflectiveMultiResolver extends MultiResolver {
    private final Constructor constructor;
    private final String[] reservedWords;

    public ReflectiveMultiResolver(
        String name,
        String signature,
        String description,
        String[] signatures,
        Class clazz)
    {
        this(name, signature, description, signatures, clazz, null);
    }

    public ReflectiveMultiResolver(
        String name,
        String signature,
        String description,
        String[] signatures,
        Class clazz,
        String[] reservedWords)
    {
        super(name, signature, description, signatures);
        try {
            this.constructor = clazz.getConstructor(new Class[] {FunctionDefinition.class});
        } catch (NoSuchMethodException e) {
            throw Util.newInternal(
                e, new StringBuilder("Error while registering resolver class ").append(clazz).toString());
        }
        this.reservedWords = reservedWords;
    }

    @Override
	protected FunctionDefinition createFunDef(Exp[] args, FunctionDefinition dummyFunDef) {
        try {
            return (FunctionDefinition) constructor.newInstance(new Object[] {dummyFunDef});
        } catch (InstantiationException e) {
            throw Util.newInternal(
                e, new StringBuilder("Error while instantiating FunDef '").append(getSignature()).append("'").toString());
        } catch (IllegalAccessException e) {
            throw Util.newInternal(
                e, new StringBuilder("Error while instantiating FunDef '").append(getSignature()).append("'").toString());
        } catch (InvocationTargetException e) {
            throw Util.newInternal(
                e, new StringBuilder("Error while instantiating FunDef '").append(getSignature()).append("'").toString());
        }
    }

    @Override
	public String[] getReservedWords() {
        if (reservedWords != null) {
            return reservedWords;
        }
        return super.getReservedWords();
    }

    @Override
    public int compareTo(FunctionResolver resolver) {
        return 0;
        //TODO
    }
}
