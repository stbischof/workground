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
package org.eclipse.daanse.xmla.api.discover.mdschema.properties;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyCardinalityEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;

/**
 * This schema rowset describes the properties of members and cell properties.
 */
public interface MdSchemaPropertiesResponseRow {

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
     * The unique name of the dimension.
     */
    Optional<String> dimensionUniqueName();

    /**
     * The unique name of the hierarchy.
     */
    Optional<String> hierarchyUniqueName();

    /**
     * The unique name of the level.
     */
    Optional<String> levelUniqueName();

    /**
     * @return The unique name of the member.
     */
    Optional<String> memberUniqueName();

    /**
     * @return A bitmask that specifies the
     * type of the property, as
     * follows:
     * 1 - Identifies a property of
     * a member.
     * 2 - Identifies a property of
     * a cell.
     * 4 - Identifies an internal
     * property.
     * 8 - Identifies a property
     * which contains a binary
     * large object (BLOB).
     */
    Optional<PropertyTypeEnum> propertyType();

    /**
     * @return The name of the property.
     */
    Optional<String> propertyName();

    /**
     * @return A label or caption associated
     * with the property.
     */
    Optional<String> propertyCaption();

    /**
     * @return This enumeration is the same
     * as LEVEL_DBTYPE for
     * MDSCHEMA_LEVELS.
     */
    Optional<LevelDbTypeEnum> dataType();

    /**
     * @return The maximum possible length
     * of the property, if it is a
     * Character or Binary type.
     * Zero indicates there is no
     * defined maximum length.
     * Returns NULL for all other data
     * types
     */
    Optional<Integer> characterMaximumLength();

    /**
     * @return The maximum possible length
     * (in bytes) of the property, if it
     * is a Character or Binary type.
     * Zero indicates there is no
     * defined maximum length.
     * Returns NULL for all other data
     * types.
     */
    Optional<Integer> characterOctetLength();

    /**
     * @return The maximum precision of the
     * property if the measure object's
     * data type is Numeric,
     * Decimal or DateTime. NULL
     * for all other property types.
     */
    Optional<Integer> numericPrecision();

    /**
     * @return The number of digits to the
     * right of the decimal point if the
     * measure object's type indicator
     * is Numeric, Decimal or
     * DateTime. Otherwise, this
     * value is NULL.
     */
    Optional<Integer> numericScale();

    /**
     * @return A description of the property.
     */
    Optional<String> description();

    /**
     * @return The content type of the
     * property.
     * Built-in values are values listed
     * as follows. This enumeration is
     * extensible and additional values
     * can be added by users.
     * 0x00 - Regular
     * 0x01 - Id
     * 0x02 - Relation to parent
     * 0x03 - Rollup operator
     * 0x11 - Organization title
     * 0x21 - Caption
     * 0x22 - Caption short
     * 0x23 - Caption description
     * 0x24 - Caption
     * abbreviation
     * 0x31 - Web URL
     * 0x32 - Web HTML
     * 0x33 - Web XML or XSL
     * 0x34 - Web mail alias
     * 0x41 - Address
     * 0x42 - Address street
     * 0x43 - Address house
     * 0x44 - Address city
     * 0x45 - Address state or
     * province
     * 0x46 - Address zip
     * 0x47 - Address quarter
     * 0x48 - Address country
     * 0x49 - Address building
     * 0x4A - Address room
     * 0x4B - Address floor
     * 0x4C - Address fax
     * 0x4D - Address phone
     * 0x61 - Geography centroid
     * x
     * 0x62 - Geography centroid
     * y
     * 0x63 - Geography centroid
     * z
     * 0x64 - Geography
     * boundary top
     * 0x65 - Geography
     * boundary left
     * 0x66 - Geography
     * boundary bottom
     * 0x67 - Geography
     * boundary right
     * 0x68 - Geography
     * boundary front
     * 0x69 - Geography
     * boundary rear
     * 0x6A - Geography
     * boundary polygon
     * 0x71 - Physical size
     * 0x72 - Physical color
     * 0x73 - Physical weight
     * 0x74 - Physical height
     * 0x75 - Physical width
     * 0x76 - Physical depth
     * 0x77 - Physical volume
     * 0x78 - Physical density
     * 0x82 - Person full name
     * 0x83 - Person first name
     * 0x84 - Person last name
     * 0x85 - Person middle name
     * 0x86 - Person demographic
     * 0x87 - Person contact
     * 0x91 - Quantity range low
     * 0x92 - Quantity range high
     * 0xA1 - Formatting color
     * 0xA2 - Formatting order
     * 0xA3 - Formatting font
     * 0xA4 - Formatting font
     * effects
     * 0xA5 - Formatting font size
     * 0xA6 - Formatting sub
     * total
     * 0xB1 - Date
     * 0xB2 - Date start
     * 0xB3 - Date ended
     * 0xB4 - Date canceled
     * 0xB5 - Date modified
     * 0xB6 - Date duration
     * 0xC1 - Version
     */
    Optional<PropertyContentTypeEnum> propertyContentType();

    /**
     * @return The column name of the
     * property used in SQL queries.
     */
    Optional<String> sqlColumnName();

    /**
     * @return The language expressed as an
     * LCID. Valid only for property
     * translations.
     */
    Optional<Integer> language();

    /**
     * @return A bitmask that specifies the
     * type of hierarchy to which the
     * property applies, as follows:
     * 1 - Indicates the property
     * is on a user defined
     * hierarchy.
     * 2 - Indicates the property
     * is on an attribute
     * hierarchy.
     * 4 - Indicates the property
     * is on a key attribute
     * hierarchy.
     * 8 - Indicates the property
     * is on an attribute hierarchy
     * that is not enabled.
     */
    Optional<PropertyOriginEnum> propertyOrigin();

    /**
     * @return The name of the attribute
     * hierarchy that is sourcing this
     * property.
     */
    Optional<String> propertyAttributeHierarchyName();

    /**
     * @return The cardinality of the property.
     * Possible values include the
     * following strings:
     * "ONE"
     * "MANY"
     */
    Optional<PropertyCardinalityEnum> propertyCardinality();

    /**
     * @return The MIME type (if this
     * property is of type Binary).
     */
    Optional<String> mimeType();

    Optional<CubeSourceEnum> cubeSource();
    /**
     * @return When true, indicates that the
     * property is visible; otherwise false.
     */
    Optional<VisibilityEnum> propertyIsVisible();
}
