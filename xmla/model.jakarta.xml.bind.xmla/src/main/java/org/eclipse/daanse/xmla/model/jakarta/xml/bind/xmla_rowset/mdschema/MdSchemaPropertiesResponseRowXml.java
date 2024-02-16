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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.mdschema;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.PropertyCardinalityEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.PropertyOriginEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.PropertyTypeEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.VisibilityEnum;

/**
 * This schema rowset describes the properties of members and cell properties.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaPropertiesResponseRowXml")
public class MdSchemaPropertiesResponseRowXml extends AbstractMdSchemaResponseRowXml implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 8022966927923698289L;

    /**
     * The unique name of the dimension.
     */
    @XmlElement(name = "DIMENSION_UNIQUE_NAME", required = false)
    private String dimensionUniqueName;

    /**
     * The unique name of the hierarchy.
     */
    @XmlElement(name = "HIERARCHY_UNIQUE_NAME", required = false)
    private String hierarchyUniqueName;

    /**
     * The unique name of the level.
     */
    @XmlElement(name = "LEVEL_UNIQUE_NAME", required = false)
    private String levelUniqueName;

    /**
     * The unique name of the member.
     */
    @XmlElement(name = "MEMBER_UNIQUE_NAME", required = false)
    private String memberUniqueName;

    /**
     * A bitmask that specifies the
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
    @XmlElement(name = "PROPERTY_TYPE", required = false)
    private PropertyTypeEnum propertyType;

    /**
     * The name of the property.
     */
    @XmlElement(name = "PROPERTY_NAME", required = false)
    private String propertyName;

    /**
     * A label or caption associated
     * with the property.
     */
    @XmlElement(name = "PROPERTY_CAPTION", required = false)
    private String propertyCaption;

    /**
     * This enumeration is the same
     * as LEVEL_DBTYPE for
     * MDSCHEMA_LEVELS.
     */
    @XmlElement(name = "DATA_TYPE", required = false)
    private LevelDbTypeEnum dataType;

    /**
     * The maximum possible length
     * of the property, if it is a
     * Character or Binary type.
     * Zero indicates there is no
     * defined maximum length.
     * Returns NULL for all other data
     * types
     */
    @XmlElement(name = "CHARACTER_MAXIMUM_LENGTH", required = false)
    private Integer characterMaximumLength;

    /**
     * The maximum possible length
     * (in bytes) of the property, if it
     * is a Character or Binary type.
     * Zero indicates there is no
     * defined maximum length.
     * Returns NULL for all other data
     * types.
     */
    @XmlElement(name = "CHARACTER_OCTET_LENGTH", required = false)
    private Integer characterOctetLength;

    /**
     * The maximum precision of the
     * property if the measure object's
     * data type is Numeric,
     * Decimal or DateTime. NULL
     * for all other property types.
     */
    @XmlElement(name = "NUMERIC_PRECISION", required = false)
    private Integer numericPrecision;

    /**
     * The number of digits to the
     * right of the decimal point if the
     * measure object's type indicator
     * is Numeric, Decimal or
     * DateTime. Otherwise, this
     * value is NULL.
     */
    @XmlElement(name = "NUMERIC_SCALE", required = false)
    private Integer numericScale;

    /**
     * A description of the property.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * The content type of the
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
    @XmlElement(name = "PROPERTY_CONTENT_TYPE", required = false)
    private PropertyContentTypeEnum propertyContentType;

    /**
     * The column name of the
     * property used in SQL queries.
     */
    @XmlElement(name = "SQL_COLUMN_NAME", required = false)
    private String sqlColumnName;

    /**
     * The language expressed as an
     * LCID. Valid only for property
     * translations.
     */
    @XmlElement(name = "LANGUAGE", required = false)
    private Integer language;

    /**
     * A bitmask that specifies the
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
    @XmlElement(name = "PROPERTY_ORIGIN", required = false)
    private PropertyOriginEnum propertyOrigin;

    /**
     * The name of the attribute
     * hierarchy that is sourcing this
     * property.
     */
    @XmlElement(name = "PROPERTY_ATTRIBUTE_HIERARCHY_NAME", required = false)
    private String propertyAttributeHierarchyName;

    /**
     * The cardinality of the property.
     * Possible values include the
     * following strings:
     * "ONE"
     * "MANY"
     */
    @XmlElement(name = "PROPERTY_CARDINALITY", required = false)
    private PropertyCardinalityEnum propertyCardinality;

    /**
     * The MIME type (if this
     * property is of type Binary).
     */
    @XmlElement(name = "MIME_TYPE", required = false)
    private String mimeType;

    /**
     * When true, indicates that the
     * property is visible; otherwise false.
     */
    @XmlElement(name = "PROPERTY_VISIBILITY", required = false)
    private VisibilityEnum propertyIsVisible;

    public String getDimensionUniqueName() {
        return dimensionUniqueName;
    }

    public void setDimensionUniqueName(String dimensionUniqueName) {
        this.dimensionUniqueName = dimensionUniqueName;
    }

    public String getHierarchyUniqueName() {
        return hierarchyUniqueName;
    }

    public void setHierarchyUniqueName(String hierarchyUniqueName) {
        this.hierarchyUniqueName = hierarchyUniqueName;
    }

    public String getLevelUniqueName() {
        return levelUniqueName;
    }

    public void setLevelUniqueName(String levelUniqueName) {
        this.levelUniqueName = levelUniqueName;
    }

    public String getMemberUniqueName() {
        return memberUniqueName;
    }

    public void setMemberUniqueName(String memberUniqueName) {
        this.memberUniqueName = memberUniqueName;
    }

    public PropertyTypeEnum getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyTypeEnum propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCaption() {
        return propertyCaption;
    }

    public void setPropertyCaption(String propertyCaption) {
        this.propertyCaption = propertyCaption;
    }

    public LevelDbTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(LevelDbTypeEnum dataType) {
        this.dataType = dataType;
    }

    public Integer getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Integer characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Integer getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Integer characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Integer getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Integer numericScale) {
        this.numericScale = numericScale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyContentTypeEnum getPropertyContentType() {
        return propertyContentType;
    }

    public void setPropertyContentType(PropertyContentTypeEnum propertyContentType) {
        this.propertyContentType = propertyContentType;
    }

    public String getSqlColumnName() {
        return sqlColumnName;
    }

    public void setSqlColumnName(String sqlColumnName) {
        this.sqlColumnName = sqlColumnName;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public PropertyOriginEnum getPropertyOrigin() {
        return propertyOrigin;
    }

    public void setPropertyOrigin(PropertyOriginEnum propertyOrigin) {
        this.propertyOrigin = propertyOrigin;
    }

    public String getPropertyAttributeHierarchyName() {
        return propertyAttributeHierarchyName;
    }

    public void setPropertyAttributeHierarchyName(String propertyAttributeHierarchyName) {
        this.propertyAttributeHierarchyName = propertyAttributeHierarchyName;
    }

    public PropertyCardinalityEnum getPropertyCardinality() {
        return propertyCardinality;
    }

    public void setPropertyCardinality(PropertyCardinalityEnum propertyCardinality) {
        this.propertyCardinality = propertyCardinality;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public VisibilityEnum getPropertyIsVisible() {
        return propertyIsVisible;
    }

    public void setPropertyIsVisible(VisibilityEnum propertyIsVisible) {
        this.propertyIsVisible = propertyIsVisible;
    }
}
