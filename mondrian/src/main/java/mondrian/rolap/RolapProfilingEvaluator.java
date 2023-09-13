/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import java.util.List;

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.api.profile.ProfilingCalc;

import mondrian.calc.impl.DelegatingExpCompiler;
import mondrian.olap.Exp;

/**
 * Evaluator that collects profiling information as it evaluates expressions.
 *
 * <p>
 * TODO: Cleanup tasks as part of explain/profiling project:
 *
 * <p>
 * 1. Obsolete AbstractCalc.calcs member, AbstractCalc.getCalcs(), and Calc[]
 * constructor parameter to many Calc subclasses. Store the tree structure
 * (children of a calc, parent of a calc) in RolapEvaluatorRoot.compiledExps.
 *
 * <p>
 * Rationale: Children calcs are used in about 50 places, but mostly for
 * dependency-checking (e.g.
 * {@link org.eclipse.daanse.olap.calc.base.AbstractProfilingNestedCalc#anyDepends}). A few places uses the
 * calcs array but should use more strongly typed members. e.g.
 * FilterFunDef.MutableMemberIterCalc should have data members 'MemberListCalc
 * listCalc' and 'BooleanCalc conditionCalc'.
 *
 * <p>
 * 2. Split Query into parse tree, plan, statement. Fits better into the
 * createStatement - prepare - execute JDBC lifecycle. Currently Query has
 * aspects of all of these, and some other state is held in RolapResult
 * (computed in the constructor, unfortunately) and RolapEvaluatorRoot. This
 * cleanup may not be essential for the explain/profiling task but should happen
 * soon afterwards.
 *
 * @author jhyde
 * @since October, 2010
 */
public class RolapProfilingEvaluator extends RolapEvaluator {

	/**
	 * Creates a profiling evaluator.
	 *
	 * @param root Shared context between this evaluator and its children
	 */
	RolapProfilingEvaluator(RolapEvaluatorRoot root) {
		super(root);
	}

	/**
	 * Creates a child evaluator.
	 *
	 * @param root      Root evaluation context
	 * @param evaluator Parent evaluator
	 */
	private RolapProfilingEvaluator(RolapEvaluatorRoot root, RolapProfilingEvaluator evaluator,
			List<List<Member>> aggregationList) {
		super(root, evaluator, aggregationList);
	}

	@Override
	protected RolapEvaluator pushClone(List<List<Member>> aggregationList) {
		return new RolapProfilingEvaluator(root, this, aggregationList);
	}

	/**
	 * Expression compiler which introduces dependency testing.
	 *
	 * <p>
	 * It also checks that the caller does not modify lists unless it has explicitly
	 * asked for a mutable list.
	 */
	static class ProfilingEvaluatorCompiler extends DelegatingExpCompiler {
		ProfilingEvaluatorCompiler(ExpressionCompiler compiler) {
			super(compiler);
		}

		@Override
		protected ProfilingCalc<?> afterCompile(Exp exp, Calc calc, boolean mutable) {
			calc = super.afterCompile(exp, calc, mutable);
			if (calc == null) {
				return null;
			}

			if (calc instanceof ProfilingCalc pc) {
				return pc;
			}
			
			throw new RuntimeException("MUST BE PROFILING");
		}
	}



}
