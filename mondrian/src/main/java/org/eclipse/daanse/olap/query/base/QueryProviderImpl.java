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
import org.eclipse.daanse.olap.api.Statement;
import org.eclipse.daanse.olap.api.query.QueryProvider;
import org.eclipse.daanse.olap.api.query.component.CellProperty;
import org.eclipse.daanse.olap.api.query.component.DmvQuery;
import org.eclipse.daanse.olap.api.query.component.DrillThrough;
import org.eclipse.daanse.olap.api.query.component.Explain;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.Query;
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

public class QueryProviderImpl implements QueryProvider {

    @Override
    public QueryComponent createQuery(Statement statement, MdxStatement mdxStatement) {

        if (mdxStatement instanceof SelectStatement selectStatement) {
            return createQuery(statement, selectStatement);
        }
        if (mdxStatement instanceof DrillthroughStatement drillthroughStatement) {
            return createDrillThrough(statement, drillthroughStatement);
        }
        if (mdxStatement instanceof ExplainStatement explainStatement) {
            return createExplain(statement, explainStatement);
        }
        if (mdxStatement instanceof DMVStatement dmvStatement) {
            return createDMV(dmvStatement);
        }
        if (mdxStatement instanceof RefreshStatement refreshStatement) {
            return createRefresh(refreshStatement);
        }
        return null;
    }

    @Override
    public Refresh createRefresh(RefreshStatement refreshStatement) {
        return new RefreshImpl(convertName(refreshStatement.cubeName()));
    }

    @Override
    public Update createUpdate(UpdateStatement updateStatement) {
        return new UpdateImpl(convertName(updateStatement.cubeName()), List.of());
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
    public Explain createExplain(Statement statement, ExplainStatement explainStatement) {
        QueryComponent queryPart = createQuery(statement, explainStatement.mdxStatement());
        return new ExplainImpl(queryPart);
    }

    @Override
    public DrillThrough createDrillThrough(Statement statement, DrillthroughStatement drillthroughStatement) {
        Query query = createQuery(statement, drillthroughStatement.selectStatement());
        List<Expression> returnList = List.of();
        return new DrillThroughImpl(query,
            drillthroughStatement.maxRows().orElse(0),
            drillthroughStatement.firstRowSet().orElse(0),
            returnList);
    }

    @Override
    public Query createQuery(Statement statement, SelectStatement selectStatement) {
        boolean strictValidation = false;
        Subcube subcube = convertSubcube(selectStatement.selectSubcubeClause());
        List<Formula> formulaList = convertFormulaList(selectStatement.selectWithClauses());
        List<QueryAxisImpl> axesList = convertQueryAxisList(selectStatement.selectQueryClause());
        QueryAxisImpl slicerAxis = convertQueryAxis(selectStatement.selectSlicerAxisClause());
        List<CellProperty> cellProps = convertParameterList(selectStatement.selectCellPropertyListClause());

        return new QueryImpl(
            statement,
            formulaList.toArray(Formula[]::new),
            axesList.toArray(QueryAxisImpl[]::new),
            subcube,
            slicerAxis,
            cellProps.toArray(CellProperty[]::new),
            strictValidation);
    }

}
