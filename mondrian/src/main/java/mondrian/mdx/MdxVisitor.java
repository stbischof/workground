/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.mdx;

import mondrian.olap.FormulaImpl;
import mondrian.olap.IdImpl;

import org.eclipse.daanse.olap.api.query.component.DimensionExpression;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.Id;
import org.eclipse.daanse.olap.api.query.component.LevelExpression;
import org.eclipse.daanse.olap.api.query.component.Literal;
import org.eclipse.daanse.olap.api.query.component.NamedSetExpression;
import org.eclipse.daanse.olap.api.query.component.ParameterExpression;

import mondrian.olap.AbstractLiteralImpl;
import mondrian.olap.QueryImpl;
import mondrian.olap.QueryAxisImpl;

/**
 * Interface for a visitor to an MDX parse tree.
 *
 * @author jhyde
 * @since Jul 21, 2006
 */
public interface MdxVisitor {
    /**
     * @return Indicates whether the visitee should call accept on it's children
     */
    boolean shouldVisitChildren();

    /**
     * Visits a Query.
     *
     * @see QueryImpl#accept(MdxVisitor)
     */
    Object visit(QueryImpl query);

    /**
     * Visits a QueryAxis.
     *
     * @see QueryAxisImpl#accept(MdxVisitor)
     */
    Object visit(QueryAxisImpl queryAxis);

    /**
     * Visits a Formula.
     *
     * @see FormulaImpl#accept(MdxVisitor)
     */
    Object visit(Formula formula);

    /**
     * Visits an UnresolvedFunCall.
     *
     * @see UnresolvedFunCallImpl#accept(MdxVisitor)
     */
    Object visit(UnresolvedFunCallImpl call);

    /**
     * Visits a ResolvedFunCall.
     *
     * @see ResolvedFunCallImpl#accept(MdxVisitor)
     */
    Object visit(ResolvedFunCallImpl call, boolean caseSensitive);

    /**
     * Visits an Id.
     *
     * @see IdImpl#accept(MdxVisitor)
     */
    Object visit(Id id);

    /**
     * Visits a Parameter.
     *
     * @see ParameterExpression )
     */
    Object visit(ParameterExpression parameterExpr, boolean caseSensitive);

    /**
     * Visits a DimensionExpr.
     *
     * @see DimensionExpression (MdxVisitor)
     */
    Object visit(DimensionExpression dimensionExpr);

    /**
     * Visits a HierarchyExpr.
     *
     * @see HierarchyExpressionImpl#accept(MdxVisitor)
     */
    Object visit(HierarchyExpressionImpl hierarchyExpr);

    /**
     * Visits a LevelExpr.
     *
     * @see LevelExpression (MdxVisitor)
     */
    Object visit(LevelExpression levelExpr);

    /**
     * Visits a MemberExpr.
     *
     * @see MemberExpressionImpl#accept(MdxVisitor)
     */
    Object visit(MemberExpressionImpl memberExpr, boolean caseSensitive);

    /**
     * Visits a NamedSetExpr.
     *
     * @see NamedSetExpression )
     */
    Object visit(NamedSetExpression namedSetExpr);

    /**
     * Visits a Literal.
     *
     * @see AbstractLiteralImpl#accept(MdxVisitor)
     */
    Object visit(Literal literal);
}
