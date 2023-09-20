/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2017 Hitachi Vantara
// All Rights Reserved.
*/
package mondrian.olap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.MemberProperty;
import org.eclipse.daanse.olap.api.query.component.ParameterExpression;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.eclipse.daanse.olap.api.query.component.QueryPart;

import mondrian.mdx.ParameterExpressionImpl;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.mdx.UnresolvedFunCallImpl;
import mondrian.olap.fun.FunctionResolver;
import mondrian.olap.type.Type;
import mondrian.olap.type.TypeUtil;
import mondrian.resource.MondrianResource;
import mondrian.util.ArrayStack;

/**
 * Default implementation of {@link mondrian.olap.Validator}.
 *
 * <p>Uses a stack to help us guess the type of our parent expression
 * before we've completely resolved our children -- necessary,
 * unfortunately, when figuring out whether the "*" operator denotes
 * multiplication or crossjoin.
 *
 * <p>Keeps track of which nodes have already been resolved, so we don't
 * try to resolve nodes which have already been resolved. (That would not
 * be wrong, but can cause resolution to be an <code>O(2^N)</code>
 * operation.)
 *
 * <p>The concrete implementing class needs to implement
 * {@link #getQuery()} and {@link #defineParameter(Parameter)}.
 *
 * @author jhyde
 */
abstract class ValidatorImpl implements Validator {
    protected final ArrayStack<QueryPart> stack = new ArrayStack<>();
    private final FunctionTable funTable;
    private final Map<QueryPart, QueryPart> resolvedNodes =
        new HashMap<>();
    private static final QueryPart placeHolder = NumericLiteralImpl.zero;

    /**
     * Creates a ValidatorImpl.
     *
     * @param funTable Function table
     *
     * @param resolvedIdentifiers map of already resolved Ids
     * @pre funTable != null
     */
    protected ValidatorImpl(
        FunctionTable funTable, Map<QueryPart, QueryPart> resolvedIdentifiers)
    {
        Util.assertPrecondition(funTable != null, "funTable != null");
        this.funTable = funTable;
        resolvedNodes.putAll(resolvedIdentifiers);
    }

    @Override
	public Exp validate(Exp exp, boolean scalar, boolean caseSensitive) {
        Exp resolved;
        try {
            resolved = (Exp) resolvedNodes.get(exp);
        } catch (ClassCastException e) {
            // A classcast exception will occur if there is a String
            // placeholder in the map. This is an internal error -- should
            // not occur for any query, valid or invalid.
            throw Util.newInternal(
                e,
                new StringBuilder("Infinite recursion encountered while validating '")
                .append(Util.unparse(exp, caseSensitive)).append("'").toString());
        }
        if (resolved == null) {
            try {
                stack.push((QueryPart) exp);
                // To prevent recursion, put in a placeholder while we're
                // resolving.
                resolvedNodes.put((QueryPart) exp, placeHolder);
                resolved = exp.accept(this, caseSensitive);
                Util.assertTrue(resolved != null);
                resolvedNodes.put((QueryPart) exp, (QueryPart) resolved);
            } finally {
            	if (!stack.isEmpty()) {
            		stack.pop();
            	}
            }
        }

        if (scalar) {
            final Type type = resolved.getType(caseSensitive);
            if (!TypeUtil.canEvaluate(type)) {
                String exprString = Util.unparse(resolved, caseSensitive);
                throw MondrianResource.instance().MdxMemberExpIsSet.ex(
                    exprString);
            }
        }

        return resolved;
    }

    @Override
	public void validate(ParameterExpressionImpl parameterExpr, boolean caseSensitive) {
        ParameterExpression resolved =
            (ParameterExpression) resolvedNodes.get(parameterExpr);
        if (resolved != null) {
            return; // already resolved
        }
        try {
            stack.push(parameterExpr);
            resolvedNodes.put(parameterExpr, placeHolder);
            resolved = (ParameterExpression) parameterExpr.accept(this, caseSensitive);
            assert resolved != null;
            resolvedNodes.put(parameterExpr, resolved);
        } finally {
            stack.pop();
        }
    }

    @Override
	public void validate(MemberProperty memberProperty) {
        MemberProperty resolved =
            (MemberProperty) resolvedNodes.get(memberProperty);
        if (resolved != null) {
            return; // already resolved
        }
        try {
            stack.push(memberProperty);
            resolvedNodes.put(memberProperty, placeHolder);
            memberProperty.resolve(this);
            resolvedNodes.put(memberProperty, memberProperty);
        } finally {
            stack.pop();
        }
    }

    @Override
	public void validate(QueryAxis axis, boolean caseSensitive) {
        final QueryAxisImpl resolved = (QueryAxisImpl) resolvedNodes.get(axis);
        if (resolved != null) {
            return; // already resolved
        }
        try {
            stack.push(axis);
            resolvedNodes.put(axis, placeHolder);
            axis.resolve(this, caseSensitive);
            resolvedNodes.put(axis, axis);
        } finally {
            stack.pop();
        }
    }

    @Override
	public void validate(Formula formula, boolean caseSensitive) {
        final Formula resolved = (Formula) resolvedNodes.get(formula);
        if (resolved != null) {
            return; // already resolved
        }
        try {
            stack.push(formula);
            resolvedNodes.put(formula, placeHolder);
            formula.accept(this, caseSensitive);
            resolvedNodes.put(formula, formula);
        } finally {
            stack.pop();
        }
    }

    @Override
	public FunctionDefinition getDef(
        Exp[] args,
        String funName,
        Syntax syntax, boolean caseSensitive)
    {
        // Compute signature first. It makes debugging easier.
        final String signature =
            syntax.getSignature(
                funName, Category.UNKNOWN, ExpBase.getTypes(args));

        // Resolve function by its upper-case name first.  If there is only one
        // function with that name, stop immediately.  If there is more than
        // function, use some custom method, which generally involves looking
        // at the type of one of its arguments.
        List<FunctionResolver> resolvers = funTable.getResolvers(funName, syntax);
        assert resolvers != null;

        final List<FunctionResolver.Conversion> conversionList =
            new ArrayList<>();
        int minConversionCost = Integer.MAX_VALUE;
        List<FunctionDefinition> matchDefs = new ArrayList<>();
        List<FunctionResolver.Conversion> matchConversionList = null;
        for (FunctionResolver resolver : resolvers) {
            conversionList.clear();
            FunctionDefinition def = resolver.resolve(args, this, conversionList, caseSensitive);
            if (def != null) {
                int conversionCost = sumConversionCost(conversionList);
                if (conversionCost < minConversionCost) {
                    minConversionCost = conversionCost;
                    matchDefs.clear();
                    matchDefs.add(def);
                    matchConversionList =
                        new ArrayList<>(conversionList);
                } else if (conversionCost == minConversionCost) {
                    matchDefs.add(def);
                } else {
                    // ignore this match -- it required more coercions than
                    // other overloadings we've seen
                }
            }
        }
        switch (matchDefs.size()) {
        case 0:
            throw MondrianResource.instance().NoFunctionMatchesSignature.ex(
                signature);
        case 1:
            break;
        default:
            final StringBuilder buf = new StringBuilder();
            for (FunctionDefinition matchDef : matchDefs) {
                if (buf.length() > 0) {
                    buf.append(", ");
                }
                buf.append(matchDef.getSignature());
            }
            throw MondrianResource.instance()
                .MoreThanOneFunctionMatchesSignature.ex(
                    signature,
                    buf.toString());
        }

        final FunctionDefinition matchDef = matchDefs.get(0);
        for (FunctionResolver.Conversion conversion : matchConversionList) {
            conversion.checkValid();
            conversion.apply(this, Arrays.asList(args));
        }

        return matchDef;
    }

    @Override
	public boolean alwaysResolveFunDef() {
        return false;
    }

    private int sumConversionCost(
        List<FunctionResolver.Conversion> conversionList)
    {
        int cost = 0;
        for (FunctionResolver.Conversion conversion : conversionList) {
            cost += conversion.getCost();
        }
        return cost;
    }

    @Override
	public boolean canConvert(
        int ordinal, Exp fromExp, int to, List<FunctionResolver.Conversion> conversions, boolean caseSensitive)
    {
        return TypeUtil.canConvert(
            ordinal,
            fromExp.getType(caseSensitive),
            to,
            conversions);
    }

    @Override
	public boolean requiresExpression() {
        return requiresExpression(stack.size() - 1);
    }

    private boolean requiresExpression(int n) {
        if (n < 1) {
            return false;
        }
        final Object parent = stack.get(n - 1);
        if (parent instanceof Formula formula) {
            return formula.isMember();
        } else if (parent instanceof ResolvedFunCallImpl funCall) {
            if (funCall.getFunDef().getSyntax() == Syntax.Parentheses) {
                return requiresExpression(n - 1);
            } else {
                int k = whichArg(funCall, (Exp) stack.get(n));
                if (k < 0) {
                    // Arguments of call have mutated since call was placed
                    // on stack. Presumably the call has already been
                    // resolved correctly, so the answer we give here is
                    // irrelevant.
                    return false;
                }
                final FunctionDefinition funDef = funCall.getFunDef();
                final int[] parameterTypes = funDef.getParameterCategories();
                return parameterTypes[k] != Category.SET;
            }
        } else if (parent instanceof UnresolvedFunCallImpl funCall) {
            if (funCall.getSyntax() == Syntax.Parentheses
                || funCall.getFunName().equals("*"))
            {
                return requiresExpression(n - 1);
            } else {
                int k = whichArg(funCall, (Exp) stack.get(n));
                if (k < 0) {
                    // Arguments of call have mutated since call was placed
                    // on stack. Presumably the call has already been
                    // resolved correctly, so the answer we give here is
                    // irrelevant.
                    return false;
                }
                return requiresExpression(funCall, k);
            }
        } else {
            return false;
        }
    }

    /**
     * Returns whether the <code>k</code>th argument to a function call
     * has to be an expression.
     */
    boolean requiresExpression(
        UnresolvedFunCallImpl funCall,
        int k)
    {
        // The function call has not been resolved yet. In fact, this method
        // may have been invoked while resolving the child. Consider this:
        //   CrossJoin([Measures].[Unit Sales] * [Measures].[Store Sales])
        //
        // In order to know whether to resolve '*' to the multiplication
        // operator (which returns a scalar) or the crossjoin operator
        // (which returns a set) we have to know what kind of expression is
        // expected.
        List<FunctionResolver> resolvers =
            funTable.getResolvers(
                funCall.getFunName(),
                funCall.getSyntax());
        for (FunctionResolver resolver2 : resolvers) {
            if (!resolver2.requiresExpression(k)) {
                // This resolver accepts a set in this argument position,
                // therefore we don't REQUIRE a scalar expression.
                return false;
            }
        }
        return true;
    }

    @Override
	public FunctionTable getFunTable() {
        return funTable;
    }

    @Override
	public Parameter createOrLookupParam(
        boolean definition,
        String name,
        Type type,
        Exp defaultExp,
        String description)
    {
        final SchemaReader schemaReader = getQuery().getSchemaReader(false);
        Parameter param = schemaReader.getParameter(name);

        if (definition) {
            if (param != null) {
                if (param.getScope() == Parameter.Scope.Statement) {
                    ParameterImpl paramImpl = (ParameterImpl) param;
                    paramImpl.setDescription(description);
                    paramImpl.setDefaultExp(defaultExp);
                    paramImpl.setType(type);
                }
                return param;
            }
            param = new ParameterImpl(
                name,
                defaultExp, description, type);

            // Append it to the list of known parameters.
            defineParameter(param);
            return param;
        } else {
            if (param != null) {
                return param;
            }
            throw MondrianResource.instance().UnknownParameter.ex(name);
        }
    }

    private int whichArg(final FunCall node, final Exp arg) {
        final Exp[] children = node.getArgs();
        for (int i = 0; i < children.length; i++) {
            if (children[i] == arg) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Defines a parameter.
     *
     * @param param Parameter
     */
    protected abstract void defineParameter(Parameter param);
}

// End ValidatorImpl.java

