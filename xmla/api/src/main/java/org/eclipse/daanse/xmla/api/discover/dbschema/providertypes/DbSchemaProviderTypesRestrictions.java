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
package org.eclipse.daanse.xmla.api.discover.dbschema.providertypes;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.properties.XsdType;

public interface DbSchemaProviderTypesRestrictions {

    String RESTRICTIONS_DATA_TYPE = "DATA_TYPE";
    String RESTRICTIONS_BEST_MATCH = "BEST_MATCH";

    /**
     * @return This enumeration is the same as LEVEL_DBTYPE
     * for MDSCHEMA_LEVELS.
     * The type of the member key
     * column that is used for the
     * level attribute. It MUST be
     * null if concatenated keys are
     * used as the member key
     * column.
     * Type values are described in
     * the following list:
     * 0 – (DBTYPE_EMPTY)
     * Indicates that no value
     * was specified.
     * 2 – (DBTYPE_I2)
     * Indicates a two-byte
     * signed integer.
     * 3 – (DBTYPE_I4)
     * Indicates a four-byte
     * signed integer.
     * 4 – (DBTYPE_R4)
     * Indicates a single-
     * precision floating-point
     * value.
     * 5 – (DBTYPE_R8)
     * Indicates a double-
     * precision floating-point
     * value.
     * 6 – (DBTYPE_CY)
     * Indicates a currency
     * value. Currency is a
     * fixed-point number with
     * four digits to the right of
     * the decimal point and is
     * stored in an eight-byte
     * signed integer scaled by
     * 10,000.
     * 7 – (DBTYPE_DATE)
     * Indicates a date value.
     * Date values are stored as
     * Double, the whole part
     * of which is the number of
     * days since December 30,
     * 1899, and the fractional
     * part of which is the
     * fraction of a day.
     * 8 – (DBTYPE_BSTR) A
     * pointer to a BSTR, which
     * is a null-terminated
     * character string in which
     * the string length is stored
     * with the string.
     * 9 – (DBTYPE_IDISPATCH)
     * Indicates a pointer to an
     * IDispatch interface on an
     * OLE object.
     * 10 – (DBTYPE_ERROR)
     * Indicates a 32-bit error
     * code.
     * 11 – (DBTYPE_BOOL)
     * Indicates a Boolean
     * value.
     * 12 – (DBTYPE_VARIANT)
     * Indicates an Automation
     * variant.
     * 13 –
     * (DBTYPE_IUNKNOWN)
     * Indicates a pointer to an
     * IUnknown interface on an
     * OLE object.
     * 14 – (DBTYPE_DECIMAL)
     * Indicates an exact
     * numeric value with a
     * fixed precision and scale.
     * The scale is between 0
     * and 28.
     * 16 – (DBTYPE_I1)
     * Indicates a one-byte
     * signed integer.
     * 17 – (DBTYPE_UI1)
     * Indicates a one-byte
     * unsigned integer.
     * 18 – (DBTYPE_UI2)
     * Indicates a two-byte
     * unsigned integer.
     * 19 – (DBTYPE_UI4)
     * Indicates a four-byte
     * unsigned integer.
     * 20 – (DBTYPE_I8)
     * Indicates an eight-byte
     * signed integer.
     * 21 – (DBTYPE_UI8)
     * Indicates an eight-byte
     * unsigned integer.
     * 72 – (DBTYPE_GUID)
     * Indicates a GUID.
     * 128 – (DBTYPE_BYTES)
     * Indicates a binary value.
     * 129 – (DBTYPE_STR)
     * Indicates a string value.
     * 130 – (DBTYPE_WSTR)
     * Indicates a null-
     * terminated Unicode
     * character string.
     * 131 –
     * (DBTYPE_NUMERIC)
     * Indicates an exact
     * numeric value with a
     * fixed precision and scale.
     * The scale is between 0
     * and 38.
     * 132 – (DBTYPE_UDT)
     * Indicates a user-defined
     * variable.
     * 133 – (DBTYPE_DBDATE)
     * Indicates a date value
     * (yyyymmdd).
     * 134 – (DBTYPE_DBTIME)
     * Indicates a time value
     * (hhmmss).
     * 135 –
     * (DBTYPE_DBTIMESTAMP)
     * Indicates a date-time
     * stamp
     * (yyyymmddhhmmss plus
     * a fraction in billionths).
     * 136 -
     * (DBTYPE_HCHAPTER)
     * Indicates a four-byte
     * chapter value used to
     * identify rows in a child
     * rowset.
     */
    @Restriction(name = RESTRICTIONS_DATA_TYPE, type = XsdType.XSD_UNSIGNED_SHORT, order = 0)
    Optional<LevelDbTypeEnum> dataType();

    /**
     * @return A Boolean that indicates whether the data type is
     * the best match.
     * A value of true indicates that the data type is the
     * best match between all data types in the data
     * store and the OLE DB data type that is indicated by
     * the value in the DATA_TYPE column. For more
     * information, see [MSDN-OLEDB].
     * A value of false indicates that the data type is not
     * the best match.
     * For each set of rows in which the value of the
     * DATA_TYPE column is the same, the BEST_MATCH
     * column is set to true in only one row.
     */
    @Restriction(name = RESTRICTIONS_BEST_MATCH, type = XsdType.XSD_BOOLEAN, order = 1)
    Optional<Boolean> bestMatch();

}
