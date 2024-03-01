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
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package mondrian.test;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.result.Result;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

import static mondrian.olap.Util.assertTrue;
import static org.opencube.junit5.TestUtil.executeQuery;

public class TestExcel {

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class )
    void test(Context context) {
        String query = """
  SELECT NON EMPTY CrossJoin(Hierarchize(AddCalculatedMembers({DrilldownLevel({[Store].[All Stores]})})),Hierarchize(AddCalculatedMembers({DrilldownLevel({[Position].[All Position]})}))) DIMENSION PROPERTIES PARENT_UNIQUE_NAME ON COLUMNS  FROM [HR] WHERE ([Measures].[Count]) CELL PROPERTIES VALUE, FORMAT_STRING, LANGUAGE, BACK_COLOR, FORE_COLOR, FONT_FLAGS
  """;

        Result result =
            executeQuery(context.getConnection(), query );

        assertTrue ( result != null );
    }
}
