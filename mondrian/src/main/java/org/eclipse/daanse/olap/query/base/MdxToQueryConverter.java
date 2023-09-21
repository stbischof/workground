/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.olap.query.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.mdx.model.api.expression.CallExpression;
import org.eclipse.daanse.mdx.model.api.expression.CompoundId;
import org.eclipse.daanse.mdx.model.api.expression.KeyObjectIdentifier;
import org.eclipse.daanse.mdx.model.api.expression.Literal;
import org.eclipse.daanse.mdx.model.api.expression.MdxExpression;
import org.eclipse.daanse.mdx.model.api.expression.NameObjectIdentifier;
import org.eclipse.daanse.mdx.model.api.expression.NullLiteral;
import org.eclipse.daanse.mdx.model.api.expression.NumericLiteral;
import org.eclipse.daanse.mdx.model.api.expression.ObjectIdentifier;
import org.eclipse.daanse.mdx.model.api.expression.StringLiteral;
import org.eclipse.daanse.mdx.model.api.expression.SymbolLiteral;
import org.eclipse.daanse.mdx.model.api.select.Axis;
import org.eclipse.daanse.mdx.model.api.select.CreateMemberBodyClause;
import org.eclipse.daanse.mdx.model.api.select.CreateSetBodyClause;
import org.eclipse.daanse.mdx.model.api.select.SelectCellPropertyListClause;
import org.eclipse.daanse.mdx.model.api.select.SelectDimensionPropertyListClause;
import org.eclipse.daanse.mdx.model.api.select.SelectQueryAsteriskClause;
import org.eclipse.daanse.mdx.model.api.select.SelectQueryAxesClause;
import org.eclipse.daanse.mdx.model.api.select.SelectQueryAxisClause;
import org.eclipse.daanse.mdx.model.api.select.SelectQueryClause;
import org.eclipse.daanse.mdx.model.api.select.SelectQueryEmptyClause;
import org.eclipse.daanse.mdx.model.api.select.SelectSlicerAxisClause;
import org.eclipse.daanse.mdx.model.api.select.SelectSubcubeClause;
import org.eclipse.daanse.mdx.model.api.select.SelectSubcubeClauseName;
import org.eclipse.daanse.mdx.model.api.select.SelectSubcubeClauseStatement;
import org.eclipse.daanse.mdx.model.api.select.SelectWithClause;
import org.eclipse.daanse.olap.api.NameSegment;
import org.eclipse.daanse.olap.api.Quoting;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.SubtotalVisibility;
import org.eclipse.daanse.olap.api.Syntax;
import org.eclipse.daanse.olap.api.query.component.AxisOrdinal;
import org.eclipse.daanse.olap.api.query.component.CellProperty;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.Id;
import org.eclipse.daanse.olap.api.query.component.Subcube;

import mondrian.mdx.UnresolvedFunCallImpl;
import mondrian.olap.CellPropertyImpl;
import mondrian.olap.FormulaImpl;
import mondrian.olap.IdImpl;
import mondrian.olap.NullLiteralImpl;
import mondrian.olap.NumericLiteralImpl;
import mondrian.olap.QueryAxisImpl;
import mondrian.olap.StringLiteralImpl;
import mondrian.olap.SubcubeImpl;
import mondrian.olap.SymbolLiteralImpl;

public class MdxToQueryConverter {

	private MdxToQueryConverter() {
		// constructor
	}

	static List<String> convertColumns(List<? extends ObjectIdentifier> objectIdentifiers) {
		List<String> columns = new ArrayList<>();
		if (objectIdentifiers != null) {
			objectIdentifiers.forEach(oi -> {
				if (oi instanceof KeyObjectIdentifier keyObjectIdentifier) {
					List<? extends NameObjectIdentifier> l = keyObjectIdentifier.nameObjectIdentifiers();
					if (l != null) {
                        columns.addAll(l.stream().map(MdxToQueryConverter::convertName).toList());
					}
				}
				if (oi instanceof NameObjectIdentifier nameObjectIdentifier) {
					columns.add(convertName(nameObjectIdentifier));
				}
			});
		}
		return columns;
	}

	static List<CellProperty> convertParameterList(
			Optional<SelectCellPropertyListClause> selectCellPropertyListClauseOptional) {
		if (selectCellPropertyListClauseOptional.isPresent()) {
			SelectCellPropertyListClause selectCellPropertyListClause = selectCellPropertyListClauseOptional.get();
			if (selectCellPropertyListClause.properties() != null) {
				List<Segment> list = selectCellPropertyListClause.properties().stream()
						.map(p -> ((Segment) new IdImpl.NameSegmentImpl(p))).toList();
				return List.of(new CellPropertyImpl(list));
			}
		}
		return List.of();
	}

	static QueryAxisImpl convertQueryAxis(Optional<SelectSlicerAxisClause> selectSlicerAxisClauseOptional) {
		if (selectSlicerAxisClauseOptional.isPresent()) {
			SelectSlicerAxisClause selectSlicerAxisClause = selectSlicerAxisClauseOptional.get();
			MdxExpression expression = selectSlicerAxisClause.expression();
			Expression exp = getExpression(expression);
			boolean nonEmpty = false;
			Id[] dimensionProperties = new Id[0];

			new QueryAxisImpl(nonEmpty, exp, AxisOrdinal.StandardAxisOrdinal.SLICER, SubtotalVisibility.Undefined,
					dimensionProperties);
		}
		return null;
	}

	static List<QueryAxisImpl> convertQueryAxisList(SelectQueryClause selectQueryClause) {
		if (selectQueryClause instanceof SelectQueryAsteriskClause) {
			return selectQueryAsteriskClauseToQueryAxisList();
		}
		if (selectQueryClause instanceof SelectQueryAxesClause selectQueryAxesClause) {
			return selectQueryAxesClauseToQueryAxisList(selectQueryAxesClause);
		}
		if (selectQueryClause instanceof SelectQueryEmptyClause) {
			return selectQueryEmptyClauseToQueryAxisList();
		}
		return List.of();
	}

	static List<QueryAxisImpl> selectQueryEmptyClauseToQueryAxisList() {
		return List.of();
	}

	static List<QueryAxisImpl> selectQueryAxesClauseToQueryAxisList(SelectQueryAxesClause selectQueryAxesClause) {
		if (selectQueryAxesClause.selectQueryAxisClauses() != null) {
			return selectQueryAxesClause.selectQueryAxisClauses().stream()
					.map(MdxToQueryConverter::selectQueryAxisClauseToQueryAxisList).toList();
		}
		return List.of();
	}

	static QueryAxisImpl selectQueryAxisClauseToQueryAxisList(SelectQueryAxisClause selectQueryAxisClause) {
		Expression exp = getExpression(selectQueryAxisClause.expression());
		AxisOrdinal axisOrdinal = getAxisOrdinal(selectQueryAxisClause.axis());
		List<IdImpl> dimensionProperties = getDimensionProperties(
				selectQueryAxisClause.selectDimensionPropertyListClause());

		return new QueryAxisImpl(selectQueryAxisClause.nonEmpty(), exp, axisOrdinal, SubtotalVisibility.Undefined,
				dimensionProperties.stream().toArray(IdImpl[]::new));
	}

	static List<IdImpl> getDimensionProperties(SelectDimensionPropertyListClause selectDimensionPropertyListClause) {
		if (selectDimensionPropertyListClause != null && selectDimensionPropertyListClause.properties() != null) {
			return selectDimensionPropertyListClause.properties().stream().map(MdxToQueryConverter::getExpressionByCompoundId)
					.toList();
		}
		return List.of();
	}

	static AxisOrdinal getAxisOrdinal(Axis axis) {
		return AxisOrdinal.StandardAxisOrdinal.forLogicalOrdinal(axis.ordinal());
	}

	static List<QueryAxisImpl> selectQueryAsteriskClauseToQueryAxisList() {
		return List.of();
	}

	static Subcube convertSubcube(SelectSubcubeClause selectSubcubeClause) {
		if (selectSubcubeClause instanceof SelectSubcubeClauseName selectSubcubeClauseName) {
			return getSubcubeBySelectSubcubeClauseName(selectSubcubeClauseName);
		}
		if (selectSubcubeClause instanceof SelectSubcubeClauseStatement selectSubcubeClauseStatement) {
			return getSubcubeBySelectSubcubeClauseStatement(selectSubcubeClauseStatement);
		}
		return null;
	}

	static Subcube getSubcubeBySelectSubcubeClauseStatement(SelectSubcubeClauseStatement selectSubcubeClauseStatement) {
		Subcube subcube = convertSubcube(selectSubcubeClauseStatement.selectSubcubeClause());
		List<QueryAxisImpl> axes = convertQueryAxisList(selectSubcubeClauseStatement.selectQueryClause());
		String cubeName = null;
		QueryAxisImpl slicerAxis = convertQueryAxis(selectSubcubeClauseStatement.selectSlicerAxisClause());
		return new SubcubeImpl(cubeName, subcube, axes.stream().toArray(QueryAxisImpl[]::new), slicerAxis);
	}

	static Subcube getSubcubeBySelectSubcubeClauseName(SelectSubcubeClauseName selectSubcubeClauseName) {
		NameObjectIdentifier nameObjectIdentifier = selectSubcubeClauseName.cubeName();
		String cubeName = convertName(nameObjectIdentifier);
		return new SubcubeImpl(cubeName, null, null, null);
	}

	static List<Formula> convertFormulaList(List<? extends SelectWithClause> selectWithClauses) {
		List<Formula> formulaList = new ArrayList<>();
		if (selectWithClauses != null) {
			for (SelectWithClause swc : selectWithClauses) {
				if (swc instanceof CreateMemberBodyClause createMemberBodyClause) {
					IdImpl id = getId(createMemberBodyClause.compoundId());
					Expression exp = getExpression(createMemberBodyClause.expression());
					formulaList.add(new FormulaImpl(id, exp));
				}
				if (swc instanceof CreateSetBodyClause createSetBodyClause) {
					IdImpl id = getId(createSetBodyClause.compoundId());
					Expression exp = getExpression(createSetBodyClause.expression());
					formulaList.add(new FormulaImpl(id, exp));
				}
			}
		}
		return formulaList;
	}

	static Expression getExpression(MdxExpression expression) {
		if (expression instanceof CallExpression callExpression) {
			return getExpressionByCallExpression(callExpression);
		}
		if (expression instanceof Literal literal) {
			return getExpressionByLiteral(literal);
		}
		if (expression instanceof CompoundId compoundId) {
			return getExpressionByCompoundId(compoundId);
		}
		if (expression instanceof ObjectIdentifier objectIdentifier) {
			return getExpressionByObjectIdentifier(objectIdentifier);
		}
		return null;
	}

	static Expression getExpressionByObjectIdentifier(ObjectIdentifier objectIdentifier) {
		if (objectIdentifier instanceof KeyObjectIdentifier keyObjectIdentifier) {
			return new IdImpl(getExpressionByKeyObjectIdentifier(keyObjectIdentifier));
		}
		if (objectIdentifier instanceof NameObjectIdentifier nameObjectIdentifier) {
			return new IdImpl(getExpressionByNameObjectIdentifier(nameObjectIdentifier));
		}
		return null;
	}

	static Segment getExpressionByNameObjectIdentifier(NameObjectIdentifier nameObjectIdentifier) {
		switch (nameObjectIdentifier.quoting()) {
		case QUOTED:
			return new IdImpl.NameSegmentImpl(nameObjectIdentifier.name(), Quoting.QUOTED);
		case UNQUOTED:
			return new IdImpl.NameSegmentImpl(nameObjectIdentifier.name(), Quoting.UNQUOTED);
		case KEY:
			return new IdImpl.KeySegment(new IdImpl.NameSegmentImpl(nameObjectIdentifier.name()));
		}
		return null;
	}

	static List<Segment> getExpressionByKeyObjectIdentifier(KeyObjectIdentifier keyObjectIdentifier) {
		return keyObjectIdentifier.nameObjectIdentifiers().stream().map(MdxToQueryConverter::getExpressionByNameObjectIdentifier)
				.toList();
	}

	static IdImpl getExpressionByCompoundId(CompoundId compoundId) {
		List<Segment> list = new ArrayList<>();
		compoundId.objectIdentifiers().forEach(it -> {
			if (it instanceof KeyObjectIdentifier keyObjectIdentifier) {
				list.addAll(getExpressionByKeyObjectIdentifier(keyObjectIdentifier));
			}
			if (it instanceof NameObjectIdentifier nameObjectIdentifier) {
				list.add(getExpressionByNameObjectIdentifier(nameObjectIdentifier));
			}
		});
		return new IdImpl(list);
	}

	static Expression getExpressionByLiteral(Literal literal) {
		if (literal instanceof NumericLiteral numericLiteral) {
			return NumericLiteralImpl.create(numericLiteral.value());
		}
		if (literal instanceof StringLiteral stringLiteral) {
			return StringLiteralImpl.create(stringLiteral.value());
		}
		if (literal instanceof NullLiteral) {
			return NullLiteralImpl.nullValue;
		}
		if (literal instanceof SymbolLiteral symbolLiteral) {
			return SymbolLiteralImpl.create(symbolLiteral.value());
		}
		return null;
	}

	static Expression getExpressionByCallExpression(CallExpression callExpression) {
		Expression x;
		Expression y;
		List<Expression> list;
		switch (callExpression.type()) {
		case TERM_INFIX:
			x = getExpression(callExpression.expressions().get(0));
			y = getExpression(callExpression.expressions().get(1));
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Infix, new Expression[] { x, y });
		case TERM_PREFIX:
			x = getExpression(callExpression.expressions().get(0));
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Prefix, new Expression[] { x });
		case FUNCTION:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Function,
					list.stream().toArray(Expression[]::new));
		case PROPERTY:
			x = getExpression(callExpression.expressions().get(0));
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Property, new Expression[] { x });
		case PROPERTY_QUOTED:
			x = getExpression(callExpression.expressions().get(0));
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.QuotedProperty, new Expression[] { x });
		case PROPERTY_AMPERS_AND_QUOTED:
			x = getExpression(callExpression.expressions().get(0));
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.AmpersandQuotedProperty,
					new Expression[] { x });
		case METHOD:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Method,
					list.stream().toArray(Expression[]::new));
		case BRACES:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Braces,
					list.stream().toArray(Expression[]::new));
		case PARENTHESES:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Parentheses,
					list.stream().toArray(Expression[]::new));
		case INTERNAL:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Internal,
					list.stream().toArray(Expression[]::new));
		case EMPTY:
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Empty, new Expression[] {});
		case TERM_POSTFIX:
			x = getExpression(callExpression.expressions().get(0));
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Postfix, new Expression[] { x });
		case TERM_CASE:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Case,
					list.stream().toArray(Expression[]::new));
		case CAST:
			list = getExpressionList(callExpression.expressions());
			return new UnresolvedFunCallImpl(callExpression.name(), Syntax.Cast,
					list.stream().toArray(Expression[]::new));
		}
		return null;
	}

	static List<Expression> getExpressionList(List<? extends MdxExpression> expressions) {
		if (expressions != null) {
			return expressions.stream().map(MdxToQueryConverter::getExpression).toList();
		}
		return List.of();
	}

	static IdImpl getId(CompoundId compoundId) {
		List<Segment> segmentList = getIdSegmentList(compoundId.objectIdentifiers());
		return new IdImpl(segmentList);
	}

	static List<Segment> getIdSegmentList(List<? extends ObjectIdentifier> objectIdentifiers) {
		List<Segment> segmentList = new ArrayList<>();
		if (objectIdentifiers != null) {
			for (ObjectIdentifier oi : objectIdentifiers) {
				segmentList.add(getIdSegment(oi));
			}
		}
		return segmentList;
	}

	static Segment getIdSegment(ObjectIdentifier oi) {
		if (oi instanceof KeyObjectIdentifier keyObjectIdentifier) {
			return new IdImpl.KeySegment(getNameSegmentList(keyObjectIdentifier.nameObjectIdentifiers()));
		}
		if (oi instanceof NameObjectIdentifier nameObjectIdentifier) {
			return new IdImpl.NameSegmentImpl(nameObjectIdentifier.name(), Quoting.UNQUOTED);
		}
		return null;
	}

	static List<NameSegment> getNameSegmentList(List<? extends NameObjectIdentifier> nameObjectIdentifiers) {
		List<NameSegment> res = new ArrayList<>();
		if (nameObjectIdentifiers != null) {
			for (NameObjectIdentifier noi : nameObjectIdentifiers) {
				res.add(new IdImpl.NameSegmentImpl(noi.name(), getQuoting(noi.quoting())));
			}
		}
		return res;
	}

	static Quoting getQuoting(ObjectIdentifier.Quoting quoting) {
		switch (quoting) {
		case QUOTED:
			return Quoting.QUOTED;
		case UNQUOTED:
			return Quoting.UNQUOTED;
		case KEY:
			return Quoting.KEY;
		default:
			return Quoting.UNQUOTED;
		}
	}

	static String convertName(NameObjectIdentifier nameObjectIdentifier) {
		switch (nameObjectIdentifier.quoting()) {
		case QUOTED:
			return quoted(nameObjectIdentifier.name());
		case UNQUOTED:
			return nameObjectIdentifier.name();
		case KEY:
			return key(nameObjectIdentifier.name());
		default:
			return nameObjectIdentifier.name();
		}
	}

	static String quoted(String name) {
		StringBuilder sb = new StringBuilder();
		return sb.append("[").append(name).append("]").toString();
	}

	static String key(String name) {
		StringBuilder sb = new StringBuilder();
		return sb.append("&").append(name).append("]").toString();
	}

}
