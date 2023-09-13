/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.mdx;

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.constant.ConstantMemberCalc;

import mondrian.olap.Category;
import mondrian.olap.Exp;
import mondrian.olap.ExpBase;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.Type;

/**
 * Usage of a {@link org.eclipse.daanse.olap.api.element.Member} as an MDX expression.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public class MemberExpressionImpl extends ExpBase implements Exp, MemberExpression {
    private final Member member;
    private MemberType type;

    /**
     * Creates a member expression.
     *
     * @param member Member
     * @pre member != null
     */
    public MemberExpressionImpl(Member member) {
        Util.assertPrecondition(member != null, "member != null");
        this.member = member;
    }

    /**
     * Returns the member.
     *
     * @post return != null
     */
    @Override
    public Member getMember() {
        return member;
    }

    @Override
	public String toString() {
        return member.getUniqueName();
    }

    @Override
	public Type getType() {
        if (type == null) {
            type = MemberType.forMember(member);
        }
        return type;
    }

    @Override
	public MemberExpressionImpl cloneExp() {
        return new MemberExpressionImpl(member);
    }

    @Override
	public int getCategory() {
        return Category.MEMBER;
    }

    @Override
	public Exp accept(Validator validator) {
        return this;
    }

    @Override
	public Calc accept(ExpressionCompiler compiler) {
        return ConstantMemberCalc.of(member);
    }

    @Override
	public Object accept(MdxVisitor visitor) {
        return visitor.visit(this);
    }
}
