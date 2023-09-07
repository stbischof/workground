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
package org.eclipse.daanse.query.api;

import mondrian.olap.api.DmvQuery;
import mondrian.olap.api.DrillThrough;
import mondrian.olap.api.Explain;
import mondrian.olap.api.Query;
import mondrian.olap.api.QueryPart;
import mondrian.olap.api.Refresh;
import mondrian.olap.api.Update;
import org.eclipse.daanse.mdx.model.api.DMVStatement;
import org.eclipse.daanse.mdx.model.api.DrillthroughStatement;
import org.eclipse.daanse.mdx.model.api.ExplainStatement;
import org.eclipse.daanse.mdx.model.api.MdxStatement;
import org.eclipse.daanse.mdx.model.api.RefreshStatement;
import org.eclipse.daanse.mdx.model.api.SelectStatement;
import org.eclipse.daanse.mdx.model.api.UpdateStatement;

public interface QueryProvider {

    QueryPart createQuery(MdxStatement selectStatement);

    Query createQuery(SelectStatement selectStatement);

    DrillThrough createDrillThrough(DrillthroughStatement drillThroughStatement);

    Explain createExplain(ExplainStatement explainStatement);

    DmvQuery createDMV(DMVStatement dmvStatement);

    Refresh createRefresh(RefreshStatement refreshStatement);

    Update createUpdate(UpdateStatement updateStatement);
}
