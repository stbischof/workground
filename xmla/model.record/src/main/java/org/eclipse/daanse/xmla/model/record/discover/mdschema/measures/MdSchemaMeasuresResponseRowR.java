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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.measures;

import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.MeasureAggregatorEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;

import java.util.Optional;

public record MdSchemaMeasuresResponseRowR(Optional<String> catalogName,
                                           Optional<String> schemaName,
                                           Optional<String> cubeName,
                                           Optional<String> measureName,
                                           Optional<String> measureUniqueName,
                                           Optional<String> measureCaption,
                                           Optional<Integer> measureGuid,
                                           Optional<MeasureAggregatorEnum> measureAggregator,
                                           Optional<LevelDbTypeEnum> dataType,
                                           Optional<Integer> numericPrecision,
                                           Optional<Integer> numericScale,
                                           Optional<String> measureUnits,
                                           Optional<String> description,
                                           Optional<String> expression,
                                           Optional<Boolean> measureIsVisible,
                                           Optional<String> levelsList,
                                           Optional<String> measureNameSqlColumnName,
                                           Optional<String> measureUnqualifiedCaption,
                                           Optional<String> measureGroupName,
                                           Optional<String> measureDisplayFolder,
                                           Optional<String> defaultFormatString)
    implements MdSchemaMeasuresResponseRow {

}
