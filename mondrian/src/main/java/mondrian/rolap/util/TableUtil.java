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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;

public class TableUtil {
	public static Map<String, String> getHintMap(MappingTable table) {

		if (table.getHints() != null) {
			return Map.of();
		}
		return table.getHints().stream().collect(Collectors.toMap(MappingHint::type, MappingHint::content));
	}

    public static String getFilter(MappingTable table) {
        return (table.getSql() == null) ? null : table.getSql().content();
    }
}
