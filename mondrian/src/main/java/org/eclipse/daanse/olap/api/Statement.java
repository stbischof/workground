/*
* Copyright (c) 2022 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.olap.api;

import org.eclipse.daanse.olap.api.result.CellSet;
import org.eclipse.daanse.olap.api.result.Result;

import java.sql.ResultSet;
import java.util.Optional;

public interface Statement  {


   Connection getConnection() ;

    /**
     * Executes an mdx SelectStatement.
     *
     * @param mdx MDX <statement
     *
     * @return {@link Result}
     *
     */
    Result executeSelect(String mdx) throws Exception;

    CellSet executeQuery(String statement);

    ResultSet executeQuery(String statement, Optional<Boolean> advanced, Optional<String> tabFields, int[] rowCountSlot);
}
