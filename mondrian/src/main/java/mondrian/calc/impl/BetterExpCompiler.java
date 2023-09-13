/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara.
 * Copyright (C) 2021 Sergei Semenkov
 * All rights reserved.
 */

package mondrian.calc.impl;

import java.util.List;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.MemberCalc;
import org.eclipse.daanse.olap.calc.api.TupleCalc;
import org.eclipse.daanse.olap.calc.base.type.member.UnknownToMemberCalc;
import org.eclipse.daanse.olap.calc.base.type.tuple.MemberCalcToTupleCalc;
import org.eclipse.daanse.olap.calc.base.type.tuple.UnknownToTupleCalc;

import mondrian.calc.TupleListCalc;
import mondrian.calc.TupleList;
import mondrian.olap.Evaluator;
import mondrian.olap.Exp;
import mondrian.olap.Util;
import mondrian.olap.Validator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.TupleType;
import mondrian.olap.type.Type;

/**
 * Enhanced expression compiler. It can generate code to convert between scalar
 * types.
 *
 * @author jhyde
 * @since Sep 29, 2005
 */
public class BetterExpCompiler extends AbstractExpCompiler {
	public BetterExpCompiler(Evaluator evaluator, Validator validator) {
		super(evaluator, validator);
	}

	public BetterExpCompiler(Evaluator evaluator, Validator validator, List<ResultStyle> resultStyles) {
		super(evaluator, validator, resultStyles);
	}

	@Override
	public TupleCalc compileTuple(Exp exp) {
		final Calc<?> calc = compile(exp);
		final Type type = exp.getType();
		if (type instanceof mondrian.olap.type.DimensionType || type instanceof mondrian.olap.type.HierarchyType) {
			final mondrian.mdx.UnresolvedFunCallImpl unresolvedFunCall = new mondrian.mdx.UnresolvedFunCallImpl("DefaultMember",
					mondrian.olap.Syntax.Property, new Exp[] { exp });
			final Exp defaultMember = unresolvedFunCall.accept(getValidator());
			return compileTuple(defaultMember);
		}
		if (type instanceof TupleType) {

			if (calc instanceof TupleCalc tc) {
				return tc;
			}

			TupleCalc tc = new UnknownToTupleCalc( type, calc);
			return tc;
		} else if (type instanceof MemberType) {
			MemberCalc tmpCalc = null;
			if (calc instanceof MemberCalc mc) {
				tmpCalc = mc;
			} else {
				tmpCalc = new UnknownToMemberCalc( type, calc);
			}
			final MemberCalc memberCalc = tmpCalc;
			return new MemberCalcToTupleCalc( type,  memberCalc);

		} else {
			throw Util.newInternal("cannot cast " + exp);
		}
	}

	@Override
	public TupleListCalc compileList(Exp exp, boolean mutable) {
		final TupleListCalc tupleListCalc = super.compileList(exp, mutable);
		if (mutable && tupleListCalc.getResultStyle() == ResultStyle.LIST) {
			// Wrap the expression in an expression which creates a mutable
			// copy.
			return new CopyListCalc(tupleListCalc);
		}
		return tupleListCalc;
	}

	

	private static class CopyListCalc extends AbstractListCalc {
		private final TupleListCalc tupleListCalc;

		public CopyListCalc(TupleListCalc tupleListCalc) {
			super( tupleListCalc.getType(), new Calc[] { tupleListCalc });
			this.tupleListCalc = tupleListCalc;
		}

		@Override
		public TupleList evaluateList(Evaluator evaluator) {
			final TupleList list = tupleListCalc.evaluateList(evaluator);
			return list.copyList(-1);
		}
	}
}
