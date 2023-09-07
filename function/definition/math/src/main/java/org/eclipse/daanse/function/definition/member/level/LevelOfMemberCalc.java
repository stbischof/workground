package org.eclipse.daanse.function.definition.member.level;

import org.eclipse.daanse.olap.api.model.Level;
import org.eclipse.daanse.olap.api.model.Member;
import org.eclipse.daanse.olap.calc.api.MemberCalc;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedLevelCalc;

import mondrian.olap.Evaluator;
import mondrian.olap.type.Type;

public class LevelOfMemberCalc extends AbstractProfilingNestedLevelCalc<MemberCalc> {

	public LevelOfMemberCalc(Type type, MemberCalc memberCalc) {
		super(type, new MemberCalc[] { memberCalc });
	}

	@Override
	public Level evaluate(Evaluator evaluator) {
		Member member = getFirstChildCalc().evaluate(evaluator);
		return member.getLevel();
	}
}