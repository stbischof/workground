
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.engine300.AttributeHierarchyProcessingState;
import org.eclipse.daanse.xmla.model.jaxb.engine300.DimensionAttributeVisualizationProperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for DimensionAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Type" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;DimensionAttributeTypeEnumType"&gt;
 *                 &lt;attribute name="valuens"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;enumeration value="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Usage" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="Key"/&gt;
 *               &lt;enumeration value="Parent"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *         &lt;element name="EstimatedCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="KeyColumns"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="KeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NameColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
 *         &lt;element name="ValueColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}AttributeTranslation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributeRelationships" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AttributeRelationship" type="{urn:schemas-microsoft-com:xml-analysis}AttributeRelationship" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DiscretizationMethod" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Automatic"/&gt;
 *               &lt;enumeration value="EqualAreas"/&gt;
 *               &lt;enumeration value="Clusters"/&gt;
 *               &lt;enumeration value="Thresholds"/&gt;
 *               &lt;enumeration value="UserDefined"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DiscretizationBucketCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="RootMemberIf" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ParentIsBlankSelfOrMissing"/&gt;
 *               &lt;enumeration value="ParentIsBlank"/&gt;
 *               &lt;enumeration value="ParentIsSelf"/&gt;
 *               &lt;enumeration value="ParentIsMissing"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OrderBy" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Key"/&gt;
 *               &lt;enumeration value="Name"/&gt;
 *               &lt;enumeration value="AttributeKey"/&gt;
 *               &lt;enumeration value="AttributeName"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DefaultMember" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrderByAttributeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SkippedLevelsColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
 *         &lt;element name="NamingTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MembersWithData" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="NonLeafDataHidden"/&gt;
 *               &lt;enumeration value="NonLeafDataVisible"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MembersWithDataCaption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NamingTemplateTranslations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="NamingTemplateTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustomRollupColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
 *         &lt;element name="CustomRollupPropertiesColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
 *         &lt;element name="UnaryOperatorColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
 *         &lt;element name="AttributeHierarchyOrdered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MemberNamesUnique" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IsAggregatable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AttributeHierarchyEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AttributeHierarchyOptimizedState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="FullyOptimized"/&gt;
 *               &lt;enumeration value="NotOptimized"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributeHierarchyVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AttributeHierarchyDisplayFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="KeyUniquenessGuarantee" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="GroupingBehavior" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="EncourageGrouping"/&gt;
 *               &lt;enumeration value="DiscourageGrouping"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="InstanceSelection" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="DropDown"/&gt;
 *               &lt;enumeration value="List"/&gt;
 *               &lt;enumeration value="FilteredList"/&gt;
 *               &lt;enumeration value="MandatoryFilter"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Annotations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProcessingState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *               &lt;enumeration value="InvalidExpression"/&gt;
 *               &lt;enumeration value="CalculationError"/&gt;
 *               &lt;enumeration value="DependencyError"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributeHierarchyProcessingState" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300}AttributeHierarchyProcessingState" minOccurs="0"/&gt;
 *         &lt;element name="VisualizationProperties" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300}DimensionAttributeVisualizationProperties" minOccurs="0"/&gt;
 *         &lt;element name="ExtendedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionAttribute", propOrder = {

})
public class DimensionAttribute {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Type")
    protected DimensionAttribute.Type type;
    @XmlElement(name = "Usage")
    protected String usage;
    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "EstimatedCount")
    protected Long estimatedCount;
    @XmlElement(name = "KeyColumns", required = true)
    protected DimensionAttribute.KeyColumns keyColumns;
    @XmlElement(name = "NameColumn")
    protected DataItem nameColumn;
    @XmlElement(name = "ValueColumn")
    protected DataItem valueColumn;
    @XmlElement(name = "Translations")
    protected DimensionAttribute.Translations translations;
    @XmlElement(name = "AttributeRelationships")
    protected DimensionAttribute.AttributeRelationships attributeRelationships;
    @XmlElement(name = "DiscretizationMethod")
    protected String discretizationMethod;
    @XmlElement(name = "DiscretizationBucketCount")
    protected BigInteger discretizationBucketCount;
    @XmlElement(name = "RootMemberIf")
    protected String rootMemberIf;
    @XmlElement(name = "OrderBy")
    protected String orderBy;
    @XmlElement(name = "DefaultMember")
    protected String defaultMember;
    @XmlElement(name = "OrderByAttributeID")
    protected String orderByAttributeID;
    @XmlElement(name = "SkippedLevelsColumn")
    protected DataItem skippedLevelsColumn;
    @XmlElement(name = "NamingTemplate")
    protected String namingTemplate;
    @XmlElement(name = "MembersWithData")
    protected String membersWithData;
    @XmlElement(name = "MembersWithDataCaption")
    protected String membersWithDataCaption;
    @XmlElement(name = "NamingTemplateTranslations")
    protected DimensionAttribute.NamingTemplateTranslations namingTemplateTranslations;
    @XmlElement(name = "CustomRollupColumn")
    protected DataItem customRollupColumn;
    @XmlElement(name = "CustomRollupPropertiesColumn")
    protected DataItem customRollupPropertiesColumn;
    @XmlElement(name = "UnaryOperatorColumn")
    protected DataItem unaryOperatorColumn;
    @XmlElement(name = "AttributeHierarchyOrdered")
    protected Boolean attributeHierarchyOrdered;
    @XmlElement(name = "MemberNamesUnique")
    protected Boolean memberNamesUnique;
    @XmlElement(name = "IsAggregatable")
    protected Boolean isAggregatable;
    @XmlElement(name = "AttributeHierarchyEnabled")
    protected Boolean attributeHierarchyEnabled;
    @XmlElement(name = "AttributeHierarchyOptimizedState")
    protected String attributeHierarchyOptimizedState;
    @XmlElement(name = "AttributeHierarchyVisible")
    protected Boolean attributeHierarchyVisible;
    @XmlElement(name = "AttributeHierarchyDisplayFolder")
    protected String attributeHierarchyDisplayFolder;
    @XmlElement(name = "KeyUniquenessGuarantee")
    protected Boolean keyUniquenessGuarantee;
    @XmlElement(name = "GroupingBehavior")
    protected String groupingBehavior;
    @XmlElement(name = "InstanceSelection")
    protected String instanceSelection;
    @XmlElement(name = "Annotations")
    protected DimensionAttribute.Annotations annotations;
    @XmlElement(name = "ProcessingState")
    protected String processingState;
    @XmlElement(name = "AttributeHierarchyProcessingState")
    @XmlSchemaType(name = "string")
    protected AttributeHierarchyProcessingState attributeHierarchyProcessingState;
    @XmlElement(name = "VisualizationProperties")
    protected DimensionAttributeVisualizationProperties visualizationProperties;
    @XmlElement(name = "ExtendedType")
    protected String extendedType;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    public boolean isSetID() {
        return (this.id!= null);
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttribute.Type }
     *     
     */
    public DimensionAttribute.Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttribute.Type }
     *     
     */
    public void setType(DimensionAttribute.Type value) {
        this.type = value;
    }

    public boolean isSetType() {
        return (this.type!= null);
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsage(String value) {
        this.usage = value;
    }

    public boolean isSetUsage() {
        return (this.usage!= null);
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Binding }
     *     
     */
    public Binding getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Binding }
     *     
     */
    public void setSource(Binding value) {
        this.source = value;
    }

    public boolean isSetSource() {
        return (this.source!= null);
    }

    /**
     * Gets the value of the estimatedCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstimatedCount() {
        return estimatedCount;
    }

    /**
     * Sets the value of the estimatedCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstimatedCount(Long value) {
        this.estimatedCount = value;
    }

    public boolean isSetEstimatedCount() {
        return (this.estimatedCount!= null);
    }

    /**
     * Gets the value of the keyColumns property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttribute.KeyColumns }
     *     
     */
    public DimensionAttribute.KeyColumns getKeyColumns() {
        return keyColumns;
    }

    /**
     * Sets the value of the keyColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttribute.KeyColumns }
     *     
     */
    public void setKeyColumns(DimensionAttribute.KeyColumns value) {
        this.keyColumns = value;
    }

    public boolean isSetKeyColumns() {
        return (this.keyColumns!= null);
    }

    /**
     * Gets the value of the nameColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem }
     *     
     */
    public DataItem getNameColumn() {
        return nameColumn;
    }

    /**
     * Sets the value of the nameColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem }
     *     
     */
    public void setNameColumn(DataItem value) {
        this.nameColumn = value;
    }

    public boolean isSetNameColumn() {
        return (this.nameColumn!= null);
    }

    /**
     * Gets the value of the valueColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem }
     *     
     */
    public DataItem getValueColumn() {
        return valueColumn;
    }

    /**
     * Sets the value of the valueColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem }
     *     
     */
    public void setValueColumn(DataItem value) {
        this.valueColumn = value;
    }

    public boolean isSetValueColumn() {
        return (this.valueColumn!= null);
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttribute.Translations }
     *     
     */
    public DimensionAttribute.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttribute.Translations }
     *     
     */
    public void setTranslations(DimensionAttribute.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
    }

    /**
     * Gets the value of the attributeRelationships property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttribute.AttributeRelationships }
     *     
     */
    public DimensionAttribute.AttributeRelationships getAttributeRelationships() {
        return attributeRelationships;
    }

    /**
     * Sets the value of the attributeRelationships property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttribute.AttributeRelationships }
     *     
     */
    public void setAttributeRelationships(DimensionAttribute.AttributeRelationships value) {
        this.attributeRelationships = value;
    }

    public boolean isSetAttributeRelationships() {
        return (this.attributeRelationships!= null);
    }

    /**
     * Gets the value of the discretizationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscretizationMethod() {
        return discretizationMethod;
    }

    /**
     * Sets the value of the discretizationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscretizationMethod(String value) {
        this.discretizationMethod = value;
    }

    public boolean isSetDiscretizationMethod() {
        return (this.discretizationMethod!= null);
    }

    /**
     * Gets the value of the discretizationBucketCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDiscretizationBucketCount() {
        return discretizationBucketCount;
    }

    /**
     * Sets the value of the discretizationBucketCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDiscretizationBucketCount(BigInteger value) {
        this.discretizationBucketCount = value;
    }

    public boolean isSetDiscretizationBucketCount() {
        return (this.discretizationBucketCount!= null);
    }

    /**
     * Gets the value of the rootMemberIf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootMemberIf() {
        return rootMemberIf;
    }

    /**
     * Sets the value of the rootMemberIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootMemberIf(String value) {
        this.rootMemberIf = value;
    }

    public boolean isSetRootMemberIf() {
        return (this.rootMemberIf!= null);
    }

    /**
     * Gets the value of the orderBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    public boolean isSetOrderBy() {
        return (this.orderBy!= null);
    }

    /**
     * Gets the value of the defaultMember property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultMember() {
        return defaultMember;
    }

    /**
     * Sets the value of the defaultMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultMember(String value) {
        this.defaultMember = value;
    }

    public boolean isSetDefaultMember() {
        return (this.defaultMember!= null);
    }

    /**
     * Gets the value of the orderByAttributeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderByAttributeID() {
        return orderByAttributeID;
    }

    /**
     * Sets the value of the orderByAttributeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderByAttributeID(String value) {
        this.orderByAttributeID = value;
    }

    public boolean isSetOrderByAttributeID() {
        return (this.orderByAttributeID!= null);
    }

    /**
     * Gets the value of the skippedLevelsColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem }
     *     
     */
    public DataItem getSkippedLevelsColumn() {
        return skippedLevelsColumn;
    }

    /**
     * Sets the value of the skippedLevelsColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem }
     *     
     */
    public void setSkippedLevelsColumn(DataItem value) {
        this.skippedLevelsColumn = value;
    }

    public boolean isSetSkippedLevelsColumn() {
        return (this.skippedLevelsColumn!= null);
    }

    /**
     * Gets the value of the namingTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamingTemplate() {
        return namingTemplate;
    }

    /**
     * Sets the value of the namingTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamingTemplate(String value) {
        this.namingTemplate = value;
    }

    public boolean isSetNamingTemplate() {
        return (this.namingTemplate!= null);
    }

    /**
     * Gets the value of the membersWithData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembersWithData() {
        return membersWithData;
    }

    /**
     * Sets the value of the membersWithData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembersWithData(String value) {
        this.membersWithData = value;
    }

    public boolean isSetMembersWithData() {
        return (this.membersWithData!= null);
    }

    /**
     * Gets the value of the membersWithDataCaption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembersWithDataCaption() {
        return membersWithDataCaption;
    }

    /**
     * Sets the value of the membersWithDataCaption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembersWithDataCaption(String value) {
        this.membersWithDataCaption = value;
    }

    public boolean isSetMembersWithDataCaption() {
        return (this.membersWithDataCaption!= null);
    }

    /**
     * Gets the value of the namingTemplateTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttribute.NamingTemplateTranslations }
     *     
     */
    public DimensionAttribute.NamingTemplateTranslations getNamingTemplateTranslations() {
        return namingTemplateTranslations;
    }

    /**
     * Sets the value of the namingTemplateTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttribute.NamingTemplateTranslations }
     *     
     */
    public void setNamingTemplateTranslations(DimensionAttribute.NamingTemplateTranslations value) {
        this.namingTemplateTranslations = value;
    }

    public boolean isSetNamingTemplateTranslations() {
        return (this.namingTemplateTranslations!= null);
    }

    /**
     * Gets the value of the customRollupColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem }
     *     
     */
    public DataItem getCustomRollupColumn() {
        return customRollupColumn;
    }

    /**
     * Sets the value of the customRollupColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem }
     *     
     */
    public void setCustomRollupColumn(DataItem value) {
        this.customRollupColumn = value;
    }

    public boolean isSetCustomRollupColumn() {
        return (this.customRollupColumn!= null);
    }

    /**
     * Gets the value of the customRollupPropertiesColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem }
     *     
     */
    public DataItem getCustomRollupPropertiesColumn() {
        return customRollupPropertiesColumn;
    }

    /**
     * Sets the value of the customRollupPropertiesColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem }
     *     
     */
    public void setCustomRollupPropertiesColumn(DataItem value) {
        this.customRollupPropertiesColumn = value;
    }

    public boolean isSetCustomRollupPropertiesColumn() {
        return (this.customRollupPropertiesColumn!= null);
    }

    /**
     * Gets the value of the unaryOperatorColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem }
     *     
     */
    public DataItem getUnaryOperatorColumn() {
        return unaryOperatorColumn;
    }

    /**
     * Sets the value of the unaryOperatorColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem }
     *     
     */
    public void setUnaryOperatorColumn(DataItem value) {
        this.unaryOperatorColumn = value;
    }

    public boolean isSetUnaryOperatorColumn() {
        return (this.unaryOperatorColumn!= null);
    }

    /**
     * Gets the value of the attributeHierarchyOrdered property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAttributeHierarchyOrdered() {
        return attributeHierarchyOrdered;
    }

    /**
     * Sets the value of the attributeHierarchyOrdered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAttributeHierarchyOrdered(Boolean value) {
        this.attributeHierarchyOrdered = value;
    }

    public boolean isSetAttributeHierarchyOrdered() {
        return (this.attributeHierarchyOrdered!= null);
    }

    /**
     * Gets the value of the memberNamesUnique property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMemberNamesUnique() {
        return memberNamesUnique;
    }

    /**
     * Sets the value of the memberNamesUnique property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMemberNamesUnique(Boolean value) {
        this.memberNamesUnique = value;
    }

    public boolean isSetMemberNamesUnique() {
        return (this.memberNamesUnique!= null);
    }

    /**
     * Gets the value of the isAggregatable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAggregatable() {
        return isAggregatable;
    }

    /**
     * Sets the value of the isAggregatable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAggregatable(Boolean value) {
        this.isAggregatable = value;
    }

    public boolean isSetIsAggregatable() {
        return (this.isAggregatable!= null);
    }

    /**
     * Gets the value of the attributeHierarchyEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAttributeHierarchyEnabled() {
        return attributeHierarchyEnabled;
    }

    /**
     * Sets the value of the attributeHierarchyEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAttributeHierarchyEnabled(Boolean value) {
        this.attributeHierarchyEnabled = value;
    }

    public boolean isSetAttributeHierarchyEnabled() {
        return (this.attributeHierarchyEnabled!= null);
    }

    /**
     * Gets the value of the attributeHierarchyOptimizedState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeHierarchyOptimizedState() {
        return attributeHierarchyOptimizedState;
    }

    /**
     * Sets the value of the attributeHierarchyOptimizedState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeHierarchyOptimizedState(String value) {
        this.attributeHierarchyOptimizedState = value;
    }

    public boolean isSetAttributeHierarchyOptimizedState() {
        return (this.attributeHierarchyOptimizedState!= null);
    }

    /**
     * Gets the value of the attributeHierarchyVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAttributeHierarchyVisible() {
        return attributeHierarchyVisible;
    }

    /**
     * Sets the value of the attributeHierarchyVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAttributeHierarchyVisible(Boolean value) {
        this.attributeHierarchyVisible = value;
    }

    public boolean isSetAttributeHierarchyVisible() {
        return (this.attributeHierarchyVisible!= null);
    }

    /**
     * Gets the value of the attributeHierarchyDisplayFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeHierarchyDisplayFolder() {
        return attributeHierarchyDisplayFolder;
    }

    /**
     * Sets the value of the attributeHierarchyDisplayFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeHierarchyDisplayFolder(String value) {
        this.attributeHierarchyDisplayFolder = value;
    }

    public boolean isSetAttributeHierarchyDisplayFolder() {
        return (this.attributeHierarchyDisplayFolder!= null);
    }

    /**
     * Gets the value of the keyUniquenessGuarantee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKeyUniquenessGuarantee() {
        return keyUniquenessGuarantee;
    }

    /**
     * Sets the value of the keyUniquenessGuarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKeyUniquenessGuarantee(Boolean value) {
        this.keyUniquenessGuarantee = value;
    }

    public boolean isSetKeyUniquenessGuarantee() {
        return (this.keyUniquenessGuarantee!= null);
    }

    /**
     * Gets the value of the groupingBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupingBehavior() {
        return groupingBehavior;
    }

    /**
     * Sets the value of the groupingBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupingBehavior(String value) {
        this.groupingBehavior = value;
    }

    public boolean isSetGroupingBehavior() {
        return (this.groupingBehavior!= null);
    }

    /**
     * Gets the value of the instanceSelection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceSelection() {
        return instanceSelection;
    }

    /**
     * Sets the value of the instanceSelection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceSelection(String value) {
        this.instanceSelection = value;
    }

    public boolean isSetInstanceSelection() {
        return (this.instanceSelection!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttribute.Annotations }
     *     
     */
    public DimensionAttribute.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttribute.Annotations }
     *     
     */
    public void setAnnotations(DimensionAttribute.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
    }

    /**
     * Gets the value of the processingState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingState() {
        return processingState;
    }

    /**
     * Sets the value of the processingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingState(String value) {
        this.processingState = value;
    }

    public boolean isSetProcessingState() {
        return (this.processingState!= null);
    }

    /**
     * Gets the value of the attributeHierarchyProcessingState property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeHierarchyProcessingState }
     *     
     */
    public AttributeHierarchyProcessingState getAttributeHierarchyProcessingState() {
        return attributeHierarchyProcessingState;
    }

    /**
     * Sets the value of the attributeHierarchyProcessingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeHierarchyProcessingState }
     *     
     */
    public void setAttributeHierarchyProcessingState(AttributeHierarchyProcessingState value) {
        this.attributeHierarchyProcessingState = value;
    }

    public boolean isSetAttributeHierarchyProcessingState() {
        return (this.attributeHierarchyProcessingState!= null);
    }

    /**
     * Gets the value of the visualizationProperties property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionAttributeVisualizationProperties }
     *     
     */
    public DimensionAttributeVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    /**
     * Sets the value of the visualizationProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionAttributeVisualizationProperties }
     *     
     */
    public void setVisualizationProperties(DimensionAttributeVisualizationProperties value) {
        this.visualizationProperties = value;
    }

    public boolean isSetVisualizationProperties() {
        return (this.visualizationProperties!= null);
    }

    /**
     * Gets the value of the extendedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendedType() {
        return extendedType;
    }

    /**
     * Sets the value of the extendedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendedType(String value) {
        this.extendedType = value;
    }

    public boolean isSetExtendedType() {
        return (this.extendedType!= null);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "annotation"
    })
    public static class Annotations {

        @XmlElement(name = "Annotation")
        protected List<Annotation> annotation;

        /**
         * Gets the value of the annotation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the annotation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAnnotation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Annotation }
         * 
         * 
         */
        public List<Annotation> getAnnotation() {
            if (annotation == null) {
                annotation = new ArrayList<Annotation>();
            }
            return this.annotation;
        }

        public boolean isSetAnnotation() {
            return ((this.annotation!= null)&&(!this.annotation.isEmpty()));
        }

        public void unsetAnnotation() {
            this.annotation = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="AttributeRelationship" type="{urn:schemas-microsoft-com:xml-analysis}AttributeRelationship" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "attributeRelationship"
    })
    public static class AttributeRelationships {

        @XmlElement(name = "AttributeRelationship")
        protected List<AttributeRelationship> attributeRelationship;

        /**
         * Gets the value of the attributeRelationship property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the attributeRelationship property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAttributeRelationship().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributeRelationship }
         * 
         * 
         */
        public List<AttributeRelationship> getAttributeRelationship() {
            if (attributeRelationship == null) {
                attributeRelationship = new ArrayList<AttributeRelationship>();
            }
            return this.attributeRelationship;
        }

        public boolean isSetAttributeRelationship() {
            return ((this.attributeRelationship!= null)&&(!this.attributeRelationship.isEmpty()));
        }

        public void unsetAttributeRelationship() {
            this.attributeRelationship = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="KeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "keyColumn"
    })
    public static class KeyColumns {

        @XmlElement(name = "KeyColumn")
        protected List<DataItem> keyColumn;

        /**
         * Gets the value of the keyColumn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the keyColumn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKeyColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataItem }
         * 
         * 
         */
        public List<DataItem> getKeyColumn() {
            if (keyColumn == null) {
                keyColumn = new ArrayList<DataItem>();
            }
            return this.keyColumn;
        }

        public boolean isSetKeyColumn() {
            return ((this.keyColumn!= null)&&(!this.keyColumn.isEmpty()));
        }

        public void unsetKeyColumn() {
            this.keyColumn = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="NamingTemplateTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "namingTemplateTranslation"
    })
    public static class NamingTemplateTranslations {

        @XmlElement(name = "NamingTemplateTranslation")
        protected List<Translation> namingTemplateTranslation;

        /**
         * Gets the value of the namingTemplateTranslation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the namingTemplateTranslation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNamingTemplateTranslation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Translation }
         * 
         * 
         */
        public List<Translation> getNamingTemplateTranslation() {
            if (namingTemplateTranslation == null) {
                namingTemplateTranslation = new ArrayList<Translation>();
            }
            return this.namingTemplateTranslation;
        }

        public boolean isSetNamingTemplateTranslation() {
            return ((this.namingTemplateTranslation!= null)&&(!this.namingTemplateTranslation.isEmpty()));
        }

        public void unsetNamingTemplateTranslation() {
            this.namingTemplateTranslation = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}AttributeTranslation" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "translation"
    })
    public static class Translations {

        @XmlElement(name = "Translation")
        protected List<AttributeTranslation> translation;

        /**
         * Gets the value of the translation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the translation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTranslation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributeTranslation }
         * 
         * 
         */
        public List<AttributeTranslation> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<AttributeTranslation>();
            }
            return this.translation;
        }

        public boolean isSetTranslation() {
            return ((this.translation!= null)&&(!this.translation.isEmpty()));
        }

        public void unsetTranslation() {
            this.translation = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;DimensionAttributeTypeEnumType"&gt;
     *       &lt;attribute name="valuens"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;enumeration value="http://schemas.microsoft.com/analysisservices/2010/engine/200/200"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Type {

        @XmlValue
        protected DimensionAttributeTypeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link DimensionAttributeTypeEnumType }
         *     
         */
        public DimensionAttributeTypeEnumType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link DimensionAttributeTypeEnumType }
         *     
         */
        public void setValue(DimensionAttributeTypeEnumType value) {
            this.value = value;
        }

        public boolean isSetValue() {
            return (this.value!= null);
        }

        /**
         * Gets the value of the valuens property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValuens() {
            return valuens;
        }

        /**
         * Sets the value of the valuens property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValuens(String value) {
            this.valuens = value;
        }

        public boolean isSetValuens() {
            return (this.valuens!= null);
        }

    }

}
