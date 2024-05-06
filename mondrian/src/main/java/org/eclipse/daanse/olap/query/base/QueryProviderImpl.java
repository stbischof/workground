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

import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertColumns;
import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertFormulaList;
import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertName;
import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertParameterList;
import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertQueryAxis;
import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertQueryAxisList;
import static org.eclipse.daanse.olap.query.base.MdxToQueryConverter.convertSubcube;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.mdx.model.api.DMVStatement;
import org.eclipse.daanse.mdx.model.api.DrillthroughStatement;
import org.eclipse.daanse.mdx.model.api.ExplainStatement;
import org.eclipse.daanse.mdx.model.api.MdxStatement;
import org.eclipse.daanse.mdx.model.api.RefreshStatement;
import org.eclipse.daanse.mdx.model.api.SelectStatement;
import org.eclipse.daanse.mdx.model.api.UpdateStatement;
import org.eclipse.daanse.mdx.model.api.expression.MdxExpression;
import org.eclipse.daanse.mdx.model.api.select.Allocation;
import org.eclipse.daanse.olap.api.Statement;
import org.eclipse.daanse.olap.api.query.QueryProvider;
import org.eclipse.daanse.olap.api.query.component.CellProperty;
import org.eclipse.daanse.olap.api.query.component.DmvQuery;
import org.eclipse.daanse.olap.api.query.component.DrillThrough;
import org.eclipse.daanse.olap.api.query.component.Explain;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.eclipse.daanse.olap.api.query.component.QueryComponent;
import org.eclipse.daanse.olap.api.query.component.Refresh;
import org.eclipse.daanse.olap.api.query.component.Subcube;
import org.eclipse.daanse.olap.api.query.component.Update;

import mondrian.olap.DmvQueryImpl;
import mondrian.olap.DrillThroughImpl;
import mondrian.olap.ExplainImpl;
import mondrian.olap.QueryAxisImpl;
import mondrian.olap.QueryImpl;
import mondrian.olap.RefreshImpl;
import mondrian.olap.UpdateImpl;
import org.eclipse.daanse.olap.api.query.component.UpdateClause;

public class QueryProviderImpl implements QueryProvider {

    @Override
    public QueryComponent createQuery(Statement statement, MdxStatement mdxStatement, boolean strictValidation) {

        if (mdxStatement instanceof SelectStatement selectStatement) {
            return createQuery(statement, selectStatement, strictValidation);
        }
        if (mdxStatement instanceof DrillthroughStatement drillthroughStatement) {
            return createDrillThrough(statement, drillthroughStatement, strictValidation);
        }
        if (mdxStatement instanceof ExplainStatement explainStatement) {
            return createExplain(statement, explainStatement, strictValidation);
        }
        if (mdxStatement instanceof DMVStatement dmvStatement) {
            return createDMV(dmvStatement);
        }
        if (mdxStatement instanceof RefreshStatement refreshStatement) {
            return createRefresh(refreshStatement);
        }
        if (mdxStatement instanceof UpdateStatement updateStatement) {
            return createUpdate(updateStatement);
        }
        return null;
    }

    @Override
    public Refresh createRefresh(RefreshStatement refreshStatement) {
        return new RefreshImpl(convertName(refreshStatement.cubeName()));
    }

    @Override
    public Update createUpdate(UpdateStatement updateStatement) {
        return new UpdateImpl(convertName(updateStatement.cubeName()), convertUpdateClauses(updateStatement.updateClauses()));
    }

    private List<UpdateClause> convertUpdateClauses(List<org.eclipse.daanse.mdx.model.api.select.UpdateClause> updateClauses) {
        if (updateClauses != null) {
            return updateClauses.stream().map(this::convertUpdateClause).toList();
        }
        return List.of();
    }

    private UpdateClause convertUpdateClause(org.eclipse.daanse.mdx.model.api.select.UpdateClause updateClause) {
        Expression tuple = MdxToQueryConverter.getExpression(updateClause.tupleExp());
        Expression value = MdxToQueryConverter.getExpression(updateClause.valueExp());
        UpdateImpl.Allocation allocation = convertAllocation(updateClause.allocation());
        Expression weight = MdxToQueryConverter.getExpression(updateClause.weight());
        return  new UpdateImpl.UpdateClauseImpl(tuple, value, allocation, weight);
    }

    private UpdateImpl.Allocation convertAllocation(Allocation allocation) {
        if (allocation != null) {
            switch (allocation) {
                case NO_ALLOCATION:
                    return UpdateImpl.Allocation.NO_ALLOCATION;
                case USE_EQUAL_ALLOCATION:
                    return UpdateImpl.Allocation.USE_EQUAL_ALLOCATION;
                case USE_EQUAL_INCREMENT:
                    return UpdateImpl.Allocation.USE_EQUAL_INCREMENT;
                case USE_WEIGHTED_ALLOCATION:
                    return UpdateImpl.Allocation.USE_WEIGHTED_ALLOCATION;
                case USE_WEIGHTED_INCREMENT:
                    return UpdateImpl.Allocation.USE_WEIGHTED_INCREMENT;
            }
        }
        return null;
    }

    @Override
    public DmvQuery createDMV(DMVStatement dmvStatement) {
        String tableName = convertName(dmvStatement.table());
        List<String> columns = new ArrayList<>();
        if (dmvStatement.columns() != null) {
            dmvStatement.columns().forEach(c -> columns.addAll(convertColumns(c.objectIdentifiers())));
        }
        Expression whereExpression = null;
        return new DmvQueryImpl(tableName,
            columns,
            whereExpression);
    }

    @Override
    public Explain createExplain(Statement statement, ExplainStatement explainStatement, boolean strictValidation) {
        QueryComponent queryPart = createQuery(statement, explainStatement.mdxStatement(), strictValidation);
        return new ExplainImpl(queryPart);
    }

    @Override
    public DrillThrough createDrillThrough(Statement statement, DrillthroughStatement drillthroughStatement, boolean strictValidation) {
        Query query = createQuery(statement, drillthroughStatement.selectStatement(), strictValidation);
        List<Expression> returnList = List.of();
        return new DrillThroughImpl(query,
            drillthroughStatement.maxRows().orElse(0),
            drillthroughStatement.firstRowSet().orElse(0),
            returnList);
    }

    @Override
    public Query createQuery(Statement statement, SelectStatement selectStatement, boolean strictValidation) {
        Subcube subcube = convertSubcube(selectStatement.selectSubcubeClause());
        List<Formula> formulaList = convertFormulaList(selectStatement.selectWithClauses());
        List<QueryAxisImpl> axesList = convertQueryAxisList(selectStatement.selectQueryClause());
        QueryAxisImpl slicerAxis = convertQueryAxis(selectStatement.selectSlicerAxisClause());
        List<CellProperty> cellProps = convertParameterList(selectStatement.selectCellPropertyListClause());

        return createQuery(
            statement,
            formulaList.toArray(Formula[]::new),
            axesList.toArray(QueryAxisImpl[]::new),
            subcube,
            slicerAxis,
            cellProps.toArray(CellProperty[]::new),
            strictValidation);
    }

    @Override
    public Query createQuery(Statement statement,
                             Formula[] formula,
                             QueryAxis[] axes,
                             Subcube subcube,
                             QueryAxisImpl slicerAxis,
                             CellProperty[] cellProps,
                             boolean strictValidation) {
        return new QueryImpl(
            statement,
            formula,
            axes,
            subcube,
            slicerAxis,
            cellProps,
            strictValidation);
    }

    }
