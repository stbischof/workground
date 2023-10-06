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
package org.eclipse.daanse.xmla.api.discover.mdschema.measures;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.MeasureAggregatorEnum;

/**
 * This schema rowset describes each measure.
 */
public interface MdSchemaMeasuresResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> catalogName();


    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    Optional<String> cubeName();

    /**
     * @return The name of the measure.
     */
    Optional<String> measureName();

    /**
     * The unique name of the measure
     */
    Optional<String> measureUniqueName();

    /**
     * @return A caption associated with the measure.
     */
    Optional<String> measureCaption();

    /**
     * @return The GUID of the measure.
     */
    Optional<Integer> measureGuid();

    /**
     * @return An enumeration that identifies how a measure
     * was derived. This enumeration can be one of
     * the following values:
     * 1 – (MDMEASURE_AGGR_SUM) MEASURE
     * aggregates from SUM.
     * 2 - (MDMEASURE_AGGR_COUNT)
     * MEASURE aggregates from COUNT.
     * 3 - (MDMEASURE_AGGR_MIN) MEASURE
     * aggregates from MIN.
     * 4 - (MDMEASURE_AGGR_MAX) MEASURE
     * aggregates from MAX.
     * 5 - (MDMEASURE_AGGR_AVG) MEASURE
     * aggregates from AVG.
     * 6 - (MDMEASURE_AGGR_VAR) MEASURE
     * aggregates from VAR.
     * 7 - (MDMEASURE_AGGR_STD) Identifies
     * that the measure aggregates from STDEV.
     * 8 - (MDMEASURE_AGGR_DST) Distinct
     * Count: The aggregation is a count of
     * unique members.
     * 9 - (MDMEASURE_AGGR_NONE) None: No
     * aggregation is applied.
     * 10 - (MDMEASURE_AGGR_AVGCHILDREN)
     * Average of Children: The aggregation of a
     * member is the average of its children.
     * 11 - (MDMEASURE_AGGR_FIRSTCHILD)
     * First Child: The member value is evaluated
     * as the value of its first child along the time
     * dimension.
     * 12 - (MDMEASURE_AGGR_LASTCHILD)
     * Last Child: The member value is evaluated
     * as the value of its last child along the time
     * dimension.
     * 13 -
     * (MDMEASURE_AGGR_FIRSTNONEMPTY)
     * First Non-Empty: The member value is
     * evaluated as the value of its first child
     * along the time dimension that contains
     * data.
     * 14 -
     * (MDMEASURE_AGGR_LASTNONEMPTY)
     * Last Non-Empty: The member value is
     * evaluated as the value of its last child
     * along the time dimension that contains
     * data.
     * 15 - (MDMEASURE_AGGR_BYACCOUNT)
     * ByAccount: The system uses the
     * semiadditive behavior specified for the
     * account type.
     * 127 - (MDMEASURE_AGGR_CALCULATED)
     * Identifies that the measure was derived
     * from a formula that was not any of the
     * single functions listed in any of the
     * preceding single functions.
     * 0 - (MDMEASURE_AGGR_UNKNOWN)
     * Identifies that the measure was derived
     * from an unknown aggregation function or
     * formula.
     */
    Optional<MeasureAggregatorEnum> measureAggregator();

    Optional<LevelDbTypeEnum> dataType();

    /**
     * @return The maximum precision of the measure if the
     * measure object's data type is Numeric,
     * Decimal, or DateTime. NULL for all other
     * property types.
     */
    Optional<Integer> numericPrecision();

    /**
     * @return The number of digits to the right of the decimal
     * point if the measure object's type indicator is
     * Numeric, Decimal or DateTime. Otherwise, this
     * value is NULL.
     */
    Optional<Integer> numericScale();

    /**
     * @return The units for the measure.
     */
    Optional<String> measureUnits();

    /**
     * @return A description of the measure.
     */
    Optional<String> description();

    /**
     * @return An expression for the member.
     */
    Optional<String> expression();

    /**
     * @return When true, indicates that the measure is
     * visible. Always returns a value of true. If the
     * measure is not visible, it will not be included in
     * the schema rowset.
     */
    Optional<Boolean> measureIsVisible();

    /**
     * @deprecated
     * @return Not currently in use.
     */
    @Deprecated(since = "deprecated in specification")
    Optional<String> levelsList();

    /**
     * @return The name of the column in the SQL query that
     * corresponds to the measure's name.
     */
    Optional<String> measureNameSqlColumnName();

    /**
     * @return The caption of the measure, not qualified with
     * the measure group caption.
     */
    Optional<String> measureUnqualifiedCaption();

    /**
     * The name of the measure group to which the
     * measure belongs.
     */
    Optional<String> measureGroupName();

    /**
     * @return The display folder of the measure.
     */
    Optional<String> measureDisplayFolder();

    /**
     * @return The default format string for the measure.
     */
    Optional<String> defaultFormatString();

}
