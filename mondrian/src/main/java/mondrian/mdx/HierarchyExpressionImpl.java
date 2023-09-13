/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.mdx;

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.query.component.HierarchyExpression;
import org.eclipse.daanse.olap.calc.api.HierarchyCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.constant.ConstantHierarchyCalc;

import mondrian.olap.Category;
import mondrian.olap.Exp;
import mondrian.olap.ExpBase;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.type.HierarchyType;
import mondrian.olap.type.Type;

/**
 * Usage of a {@link org.eclipse.daanse.olap.api.element.Hierarchy} as an MDX expression.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public class HierarchyExpressionImpl extends ExpBase implements Exp, HierarchyExpression {
    private final Hierarchy hierarchy;

    /**
     * Creates a hierarchy expression.
     *
     * @param hierarchy Hierarchy
     * @pre hierarchy != null
     */
    public HierarchyExpressionImpl(Hierarchy hierarchy) {
        Util.assertPrecondition(hierarchy != null, "hierarchy != null");
        this.hierarchy = hierarchy;
    }

    /**
     * Returns the hierarchy.
     *
     * @post return != null
     */
    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    @Override
	public String toString() {
        return hierarchy.getUniqueName();
    }

    @Override
	public Type getType() {
        return HierarchyType.forHierarchy(hierarchy);
    }

    @Override
	public HierarchyExpressionImpl cloneExp() {
        return new HierarchyExpressionImpl(hierarchy);
    }

    @Override
	public int getCategory() {
        return Category.HIERARCHY;
    }

    @Override
	public Exp accept(Validator validator) {
        return this;
    }

    @Override
	public HierarchyCalc accept(ExpressionCompiler compiler) {
        return ConstantHierarchyCalc.of(hierarchy);
    }

    @Override
	public Object accept(MdxVisitor visitor) {
        return visitor.visit(this);
    }
}
