/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2005-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// All Rights Reserved.
*/

package mondrian.olap;

import java.util.List;

import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.MemberProperty;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;

import mondrian.mdx.ParameterExpressionImpl;
import mondrian.olap.fun.FunctionResolver;
import mondrian.olap.type.Type;

/**
 * Provides context necessary to resolve identifiers to objects, function
 * calls to specific functions.
 *
 * <p>An expression calls {@link #validate} on each of its children,
 * which in turn calls {@link Exp#accept}.
 *
 * @author jhyde
 */
public interface Validator {
    /**
     * Returns the {@link Query} which is being validated.
     */
    Query getQuery();

    /**
     * Validates an expression, and returns the expression it resolves to.
     *
     * @param exp Expression to validate
     * @param scalar Whether the context requires that the expression is
     *   evaluated to a value, as opposed to a tuple
     */
    Exp validate(Exp exp, boolean scalar, boolean caseSensitive);

    /**
     * Validates a usage of a parameter.
     *
     * <p>It must resolve to the same object (although sub-objects may change).
     */
    void validate(ParameterExpressionImpl parameterExpr, boolean caseSensitive);

    /**
     * Validates a child member property.
     *
     * <p>It must resolve to the same object (although sub-objects may change).
     */
    void validate(MemberProperty memberProperty);

    /**
     * Validates an axis.
     *
     * It must resolve to the same object (although sub-objects may change).
     */
    void validate(QueryAxis axis, boolean caseSensitive);

    /**
     * Validates a formula.
     *
     * It must resolve to the same object (although sub-objects may change).
     */
    void validate(Formula formula, boolean caseSensitive );

    /**
     * Returns whether the current context requires an expression.
     */
    boolean requiresExpression();

    /**
     * Returns whether we can convert an argument to a parameter type.
     *
     * @param ordinal argument ordinal
     * @param fromExp argument type
     * @param to   parameter type
     * @param conversions List of conversions performed;
     *             method adds an element for each non-trivial conversion (for
     *             example, converting a member to a level).
     * @return Whether we can convert an argument to a parameter type
     */
    boolean canConvert(
        int ordinal,
        Exp fromExp,
        int to,
        List<FunctionResolver.Conversion> conversions, boolean caseSensitive);

    /**
     * Returns the table of function and operator definitions.
     */
    FunctionTable getFunTable();

    /**
     * Creates or retrieves the parameter corresponding to a "Parameter" or
     * "ParamRef" function call.
     */
    Parameter createOrLookupParam(
        boolean definition,
        String name,
        Type type,
        Exp defaultExp,
        String description);

    /**
     * Resolves a function call to a particular function. If the function is
     * overloaded, returns as precise a match to the argument types as
     * possible.
     */
    FunctionDefinition getDef(
        Exp[] args,
        String name,
        Syntax syntax, boolean caseSensitive);

    /**
     * Whether to resolve function name and arguments to a function definition
     * each time a node is validated, not just the first time.
     *
     * <p>Default implementation returns {@code false}.
     *
     * @return whether to resolve function each time
     */
    boolean alwaysResolveFunDef();

    /**
     * Returns the schema reader with which to resolve names of MDX objects
     * (dimensions, hierarchies, levels, members, named sets).
     *
     * <p>The schema reader is initially in the context of the query's cube,
     * and during a traversal it may change if named sets are introduced using
     * the 'expr AS alias' construct.
     *
     * @return Schema reader
     */
    SchemaReader getSchemaReader();
}
