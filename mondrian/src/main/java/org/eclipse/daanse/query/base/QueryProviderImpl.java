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
package org.eclipse.daanse.query.base;

import mondrian.olap.DmvQueryImpl;
import mondrian.olap.DrillThroughImpl;
import mondrian.olap.Exp;
import mondrian.olap.ExplainImpl;
import mondrian.olap.QueryAxisImpl;
import mondrian.olap.QueryImpl;
import mondrian.olap.RefreshImpl;
import mondrian.olap.UpdateImpl;
import mondrian.olap.api.DmvQuery;
import mondrian.olap.api.DrillThrough;
import mondrian.olap.api.Explain;
import mondrian.olap.api.Formula;
import mondrian.olap.api.Query;
import mondrian.olap.api.QueryPart;
import mondrian.olap.api.Refresh;
import mondrian.olap.api.Subcube;
import mondrian.olap.api.Update;
import mondrian.server.Statement;
import org.eclipse.daanse.mdx.model.api.DMVStatement;
import org.eclipse.daanse.mdx.model.api.DrillthroughStatement;
import org.eclipse.daanse.mdx.model.api.ExplainStatement;
import org.eclipse.daanse.mdx.model.api.MdxStatement;
import org.eclipse.daanse.mdx.model.api.RefreshStatement;
import org.eclipse.daanse.mdx.model.api.SelectStatement;
import org.eclipse.daanse.mdx.model.api.UpdateStatement;
import org.eclipse.daanse.query.api.QueryProvider;

import java.util.ArrayList;
import java.util.List;

import static org.eclipse.daanse.query.base.QueryUtil.getColumns;
import static org.eclipse.daanse.query.base.QueryUtil.getFormulaList;
import static org.eclipse.daanse.query.base.QueryUtil.getName;
import static org.eclipse.daanse.query.base.QueryUtil.getParameterList;
import static org.eclipse.daanse.query.base.QueryUtil.getQueryAxis;
import static org.eclipse.daanse.query.base.QueryUtil.getQueryAxisList;
import static org.eclipse.daanse.query.base.QueryUtil.getSubcube;

public class QueryProviderImpl implements QueryProvider {

    @Override
    public QueryPart createQuery(MdxStatement mdxStatement) {

        if (mdxStatement instanceof SelectStatement selectStatement) {
            return createQuery(selectStatement);
        }
        if (mdxStatement instanceof DrillthroughStatement drillthroughStatement) {
            return createDrillThrough(drillthroughStatement);
        }
        if (mdxStatement instanceof ExplainStatement explainStatement) {
            return createExplain(explainStatement);
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
        return new RefreshImpl(getName(refreshStatement.cubeName()));
    }

    @Override
    public Update createUpdate(UpdateStatement updateStatement) {
        return new UpdateImpl(getName(updateStatement.cubeName()), List.of());
    }

    @Override
    public DmvQuery createDMV(DMVStatement dmvStatement) {
        String tableName = getName(dmvStatement.table());
        List<String> columns = new ArrayList<>();
        if (dmvStatement.columns() != null) {
            dmvStatement.columns().forEach(c -> columns.addAll(getColumns(c.objectIdentifiers())));
        }
        Exp whereExpression = null;
        return new DmvQueryImpl(tableName,
            columns,
            whereExpression);
    }

    @Override
    public Explain createExplain(ExplainStatement explainStatement) {
        QueryPart queryPart = createQuery(explainStatement.mdxStatement());
        return new ExplainImpl(queryPart);
    }

    @Override
    public DrillThrough createDrillThrough(DrillthroughStatement drillthroughStatement) {
        Query query = createQuery(drillthroughStatement.selectStatement());
        List<Exp> returnList = List.of();
        return new DrillThroughImpl(query,
            drillthroughStatement.maxRows().orElse(0),
            drillthroughStatement.firstRowSet().orElse(0),
            returnList);
    }

    @Override
    public Query createQuery(SelectStatement selectStatement) {
        Statement statement = null;
        boolean strictValidation = false;
        Subcube subcube = getSubcube(selectStatement.selectSubcubeClause());
        List<Formula> formulaList = getFormulaList(selectStatement.selectWithClauses());
        List<QueryAxisImpl> axesList = getQueryAxisList(selectStatement.selectQueryClause());
        QueryAxisImpl slicerAxis = getQueryAxis(selectStatement.selectSlicerAxisClause());
        List<QueryPart> cellProps = getParameterList(selectStatement.selectCellPropertyListClause());

        return new QueryImpl(
            statement,
            formulaList.toArray(Formula[]::new),
            axesList.toArray(QueryAxisImpl[]::new),
            subcube,
            slicerAxis,
            cellProps.toArray(QueryPart[]::new),
            strictValidation);
    }

}
