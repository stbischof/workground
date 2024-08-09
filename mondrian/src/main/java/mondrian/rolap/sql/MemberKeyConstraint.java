/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/
package mondrian.rolap.sql;

import java.util.List;

import org.eclipse.daanse.db.dialect.api.Datatype;
import org.eclipse.daanse.olap.api.Evaluator;
import org.eclipse.daanse.rolap.mapping.api.model.SQLExpressionMapping;

import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapLevel;
import mondrian.rolap.RolapMember;
import mondrian.rolap.SqlConstraintUtils;
import mondrian.rolap.aggmatcher.AggStar;
import mondrian.util.Pair;

/**
 * Restricts the SQL result set to members where particular columns have
 * particular values.
 *
 * @version $Id$
 */
public class MemberKeyConstraint
    implements TupleConstraint
{
    private final Pair<List<SQLExpressionMapping>, List<Comparable>> cacheKey;
    private final List<SQLExpressionMapping> columnList;
    private final List<Datatype> datatypeList;
    private final List<Comparable> valueList;

    public MemberKeyConstraint(
        List<SQLExpressionMapping> columnList,
        List<Datatype> datatypeList,
        List<Comparable> valueList)
    {
        this.columnList = columnList;
        this.datatypeList = datatypeList;
        this.valueList = valueList;
        cacheKey = Pair.of(columnList, valueList);
    }

    @Override
	public void addConstraint(
        SqlQuery sqlQuery, RolapCube baseCube, AggStar aggStar)
    {
        for (int i = 0; i < columnList.size(); i++) {
        	SQLExpressionMapping expression = columnList.get(i);
            final Comparable value = valueList.get(i);
            final Datatype datatype = datatypeList.get(i);
            sqlQuery.addWhere(
                SqlConstraintUtils.constrainLevel2(
                    sqlQuery,
                    expression,
                    datatype,
                    value));
        }
    }

    @Override
	public void addLevelConstraint(
        SqlQuery sqlQuery,
        RolapCube baseCube,
        AggStar aggStar,
        RolapLevel level)
    {
    }

    @Override
	public MemberChildrenConstraint getMemberChildrenConstraint(
        RolapMember parent)
    {
        return null;
    }

    @Override
	public String toString() {
        return "MemberKeyConstraint";
    }


    @Override
	public Object getCacheKey() {
        return cacheKey;
    }

    @Override
	public Evaluator getEvaluator() {
        return null;
    }

    @Override
    public boolean supportsAggTables() {
        return true;
    }
}
