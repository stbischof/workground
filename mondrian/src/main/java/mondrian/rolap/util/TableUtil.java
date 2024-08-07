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
package mondrian.rolap.util;

import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQueryOptimisationHint;
import org.eclipse.daanse.rolap.mapping.api.model.TableQueryMapping;
import org.eclipse.daanse.rolap.mapping.api.model.TableQueryOptimizationHintMapping;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTableQuery;

public class TableUtil {
	public static Map<String, String> getHintMap(TableQueryMapping table) {

		if (table.getOptimizationHints() == null) {
			return Map.of();
		}
		return table.getOptimizationHints().stream().collect(Collectors.toMap(TableQueryOptimizationHintMapping::getType, TableQueryOptimizationHintMapping::getValue));
	}

    public static String getFilter(MappingTableQuery table) {
        return (table.getSql() == null) ? null : table.getSql().statement();
    }
}
