
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.daanse.xmla.model.jaxb.engine300_300.Relationships;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for Dimension complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Dimension"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreatedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="LastSchemaUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *         &lt;element name="MiningModelID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Type" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="Time"/&gt;
 *               &lt;enumeration value="Geography"/&gt;
 *               &lt;enumeration value="Organization"/&gt;
 *               &lt;enumeration value="BillOfMaterials"/&gt;
 *               &lt;enumeration value="Accounts"/&gt;
 *               &lt;enumeration value="Customers"/&gt;
 *               &lt;enumeration value="Products"/&gt;
 *               &lt;enumeration value="Scenario"/&gt;
 *               &lt;enumeration value="Quantitative"/&gt;
 *               &lt;enumeration value="Utility"/&gt;
 *               &lt;enumeration value="Currency"/&gt;
 *               &lt;enumeration value="Rates"/&gt;
 *               &lt;enumeration value="Channel"/&gt;
 *               &lt;enumeration value="Promotion"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="UnknownMember" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;UnknownMemberEnumType"&gt;
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
 *         &lt;element name="MdxMissingMemberMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="Ignore"/&gt;
 *               &lt;enumeration value="Error"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="StorageMode"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Molap"/&gt;
 *               &lt;enumeration value="Rolap"/&gt;
 *               &lt;enumeration value="InMemory"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="WriteEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingPriority" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="DimensionPermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DimensionPermission" type="{urn:schemas-microsoft-com:xml-analysis}DimensionPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DependsOnDimensionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Collation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnknownMemberName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnknownMemberTranslations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="UnknownMemberTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *               &lt;enumeration value="PartiallyProcessed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProactiveCaching" type="{urn:schemas-microsoft-com:xml-analysis}ProactiveCaching" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="LazyAggregations"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProcessingGroup" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ByAttribute"/&gt;
 *               &lt;enumeration value="ByTable"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CurrentStorageMode" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;DimensionCurrentStorageModeEnumType"&gt;
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
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Attributes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}DimensionAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributeAllMemberName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AttributeAllMemberTranslations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MemberAllMemberTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Hierarchies" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Hierarchy" type="{urn:schemas-microsoft-com:xml-analysis}Hierarchy" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ProcessingRecommendation" minOccurs="0"/&gt;
 *         &lt;element name="Relationships" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}Relationships" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}StringStoresCompatibilityLevel" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}CurrentStringStoresCompatibilityLevel" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimension", propOrder = {

})
public class Dimension {

  @XmlElement(name = "Name", required = true)
  protected String name;
  @XmlElement(name = "ID")
  protected String id;
  @XmlElement(name = "CreatedTimestamp")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar createdTimestamp;
  @XmlElement(name = "LastSchemaUpdate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastSchemaUpdate;
  @XmlElement(name = "Description")
  protected String description;
  @XmlElement(name = "Annotations")
  protected Dimension.Annotations annotations;
  @XmlElement(name = "Source")
  protected Binding source;
  @XmlElement(name = "MiningModelID")
  protected String miningModelID;
  @XmlElement(name = "Type")
  protected String type;
  @XmlElement(name = "UnknownMember")
  protected Dimension.UnknownMember unknownMember;
  @XmlElement(name = "MdxMissingMemberMode")
  protected String mdxMissingMemberMode;
  @XmlElement(name = "ErrorConfiguration")
  protected ErrorConfiguration errorConfiguration;
  @XmlElement(name = "StorageMode", required = true)
  protected String storageMode;
  @XmlElement(name = "WriteEnabled")
  protected Boolean writeEnabled;
  @XmlElement(name = "ProcessingPriority")
  protected BigInteger processingPriority;
  @XmlElement(name = "LastProcessed")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastProcessed;
  @XmlElement(name = "DimensionPermissions")
  protected Dimension.DimensionPermissions dimensionPermissions;
  @XmlElement(name = "DependsOnDimensionID")
  protected String dependsOnDimensionID;
  @XmlElement(name = "Language")
  protected BigInteger language;
  @XmlElement(name = "Collation")
  protected String collation;
  @XmlElement(name = "UnknownMemberName")
  protected String unknownMemberName;
  @XmlElement(name = "UnknownMemberTranslations")
  protected Dimension.UnknownMemberTranslations unknownMemberTranslations;
  @XmlElement(name = "State")
  protected String state;
  @XmlElement(name = "ProactiveCaching")
  protected ProactiveCaching proactiveCaching;
  @XmlElement(name = "ProcessingMode")
  protected String processingMode;
  @XmlElement(name = "ProcessingGroup")
  protected String processingGroup;
  @XmlElement(name = "CurrentStorageMode")
  protected Dimension.CurrentStorageMode currentStorageMode;
  @XmlElement(name = "Translations")
  protected Dimension.Translations translations;
  @XmlElement(name = "Attributes")
  protected Dimension.Attributes attributes;
  @XmlElement(name = "AttributeAllMemberName")
  protected String attributeAllMemberName;
  @XmlElement(name = "AttributeAllMemberTranslations")
  protected Dimension.AttributeAllMemberTranslations attributeAllMemberTranslations;
  @XmlElement(name = "Hierarchies")
  protected Dimension.Hierarchies hierarchies;
  @XmlElement(name = "ProcessingRecommendation", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String processingRecommendation;
  @XmlElement(name = "Relationships")
  protected Relationships relationships;
  @XmlElement(name = "StringStoresCompatibilityLevel", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
  protected Integer stringStoresCompatibilityLevel;
  @XmlElement(name = "CurrentStringStoresCompatibilityLevel", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
  protected Integer currentStringStoresCompatibilityLevel;

  /**
   * Gets the value of the name property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setName(String value) {
    this.name = value;
  }

  public boolean isSetName() {
    return (this.name != null);
  }

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getID() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setID(String value) {
    this.id = value;
  }

  public boolean isSetID() {
    return (this.id != null);
  }

  /**
   * Gets the value of the createdTimestamp property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getCreatedTimestamp() {
    return createdTimestamp;
  }

  /**
   * Sets the value of the createdTimestamp property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setCreatedTimestamp(XMLGregorianCalendar value) {
    this.createdTimestamp = value;
  }

  public boolean isSetCreatedTimestamp() {
    return (this.createdTimestamp != null);
  }

  /**
   * Gets the value of the lastSchemaUpdate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getLastSchemaUpdate() {
    return lastSchemaUpdate;
  }

  /**
   * Sets the value of the lastSchemaUpdate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setLastSchemaUpdate(XMLGregorianCalendar value) {
    this.lastSchemaUpdate = value;
  }

  public boolean isSetLastSchemaUpdate() {
    return (this.lastSchemaUpdate != null);
  }

  /**
   * Gets the value of the description property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescription(String value) {
    this.description = value;
  }

  public boolean isSetDescription() {
    return (this.description != null);
  }

  /**
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link Dimension.Annotations }
   * 
   */
  public Dimension.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link Dimension.Annotations }
   * 
   */
  public void setAnnotations(Dimension.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
  }

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link Binding }
   * 
   */
  public Binding getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link Binding }
   * 
   */
  public void setSource(Binding value) {
    this.source = value;
  }

  public boolean isSetSource() {
    return (this.source != null);
  }

  /**
   * Gets the value of the miningModelID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMiningModelID() {
    return miningModelID;
  }

  /**
   * Sets the value of the miningModelID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMiningModelID(String value) {
    this.miningModelID = value;
  }

  public boolean isSetMiningModelID() {
    return (this.miningModelID != null);
  }

  /**
   * Gets the value of the type property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setType(String value) {
    this.type = value;
  }

  public boolean isSetType() {
    return (this.type != null);
  }

  /**
   * Gets the value of the unknownMember property.
   * 
   * @return possible object is {@link Dimension.UnknownMember }
   * 
   */
  public Dimension.UnknownMember getUnknownMember() {
    return unknownMember;
  }

  /**
   * Sets the value of the unknownMember property.
   * 
   * @param value allowed object is {@link Dimension.UnknownMember }
   * 
   */
  public void setUnknownMember(Dimension.UnknownMember value) {
    this.unknownMember = value;
  }

  public boolean isSetUnknownMember() {
    return (this.unknownMember != null);
  }

  /**
   * Gets the value of the mdxMissingMemberMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMdxMissingMemberMode() {
    return mdxMissingMemberMode;
  }

  /**
   * Sets the value of the mdxMissingMemberMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMdxMissingMemberMode(String value) {
    this.mdxMissingMemberMode = value;
  }

  public boolean isSetMdxMissingMemberMode() {
    return (this.mdxMissingMemberMode != null);
  }

  /**
   * Gets the value of the errorConfiguration property.
   * 
   * @return possible object is {@link ErrorConfiguration }
   * 
   */
  public ErrorConfiguration getErrorConfiguration() {
    return errorConfiguration;
  }

  /**
   * Sets the value of the errorConfiguration property.
   * 
   * @param value allowed object is {@link ErrorConfiguration }
   * 
   */
  public void setErrorConfiguration(ErrorConfiguration value) {
    this.errorConfiguration = value;
  }

  public boolean isSetErrorConfiguration() {
    return (this.errorConfiguration != null);
  }

  /**
   * Gets the value of the storageMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getStorageMode() {
    return storageMode;
  }

  /**
   * Sets the value of the storageMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setStorageMode(String value) {
    this.storageMode = value;
  }

  public boolean isSetStorageMode() {
    return (this.storageMode != null);
  }

  /**
   * Gets the value of the writeEnabled property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isWriteEnabled() {
    return writeEnabled;
  }

  /**
   * Sets the value of the writeEnabled property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setWriteEnabled(Boolean value) {
    this.writeEnabled = value;
  }

  public boolean isSetWriteEnabled() {
    return (this.writeEnabled != null);
  }

  /**
   * Gets the value of the processingPriority property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getProcessingPriority() {
    return processingPriority;
  }

  /**
   * Sets the value of the processingPriority property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setProcessingPriority(BigInteger value) {
    this.processingPriority = value;
  }

  public boolean isSetProcessingPriority() {
    return (this.processingPriority != null);
  }

  /**
   * Gets the value of the lastProcessed property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getLastProcessed() {
    return lastProcessed;
  }

  /**
   * Sets the value of the lastProcessed property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setLastProcessed(XMLGregorianCalendar value) {
    this.lastProcessed = value;
  }

  public boolean isSetLastProcessed() {
    return (this.lastProcessed != null);
  }

  /**
   * Gets the value of the dimensionPermissions property.
   * 
   * @return possible object is {@link Dimension.DimensionPermissions }
   * 
   */
  public Dimension.DimensionPermissions getDimensionPermissions() {
    return dimensionPermissions;
  }

  /**
   * Sets the value of the dimensionPermissions property.
   * 
   * @param value allowed object is {@link Dimension.DimensionPermissions }
   * 
   */
  public void setDimensionPermissions(Dimension.DimensionPermissions value) {
    this.dimensionPermissions = value;
  }

  public boolean isSetDimensionPermissions() {
    return (this.dimensionPermissions != null);
  }

  /**
   * Gets the value of the dependsOnDimensionID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDependsOnDimensionID() {
    return dependsOnDimensionID;
  }

  /**
   * Sets the value of the dependsOnDimensionID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDependsOnDimensionID(String value) {
    this.dependsOnDimensionID = value;
  }

  public boolean isSetDependsOnDimensionID() {
    return (this.dependsOnDimensionID != null);
  }

  /**
   * Gets the value of the language property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getLanguage() {
    return language;
  }

  /**
   * Sets the value of the language property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setLanguage(BigInteger value) {
    this.language = value;
  }

  public boolean isSetLanguage() {
    return (this.language != null);
  }

  /**
   * Gets the value of the collation property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCollation() {
    return collation;
  }

  /**
   * Sets the value of the collation property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCollation(String value) {
    this.collation = value;
  }

  public boolean isSetCollation() {
    return (this.collation != null);
  }

  /**
   * Gets the value of the unknownMemberName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUnknownMemberName() {
    return unknownMemberName;
  }

  /**
   * Sets the value of the unknownMemberName property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUnknownMemberName(String value) {
    this.unknownMemberName = value;
  }

  public boolean isSetUnknownMemberName() {
    return (this.unknownMemberName != null);
  }

  /**
   * Gets the value of the unknownMemberTranslations property.
   * 
   * @return possible object is {@link Dimension.UnknownMemberTranslations }
   * 
   */
  public Dimension.UnknownMemberTranslations getUnknownMemberTranslations() {
    return unknownMemberTranslations;
  }

  /**
   * Sets the value of the unknownMemberTranslations property.
   * 
   * @param value allowed object is {@link Dimension.UnknownMemberTranslations }
   * 
   */
  public void setUnknownMemberTranslations(Dimension.UnknownMemberTranslations value) {
    this.unknownMemberTranslations = value;
  }

  public boolean isSetUnknownMemberTranslations() {
    return (this.unknownMemberTranslations != null);
  }

  /**
   * Gets the value of the state property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the value of the state property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setState(String value) {
    this.state = value;
  }

  public boolean isSetState() {
    return (this.state != null);
  }

  /**
   * Gets the value of the proactiveCaching property.
   * 
   * @return possible object is {@link ProactiveCaching }
   * 
   */
  public ProactiveCaching getProactiveCaching() {
    return proactiveCaching;
  }

  /**
   * Sets the value of the proactiveCaching property.
   * 
   * @param value allowed object is {@link ProactiveCaching }
   * 
   */
  public void setProactiveCaching(ProactiveCaching value) {
    this.proactiveCaching = value;
  }

  public boolean isSetProactiveCaching() {
    return (this.proactiveCaching != null);
  }

  /**
   * Gets the value of the processingMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getProcessingMode() {
    return processingMode;
  }

  /**
   * Sets the value of the processingMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setProcessingMode(String value) {
    this.processingMode = value;
  }

  public boolean isSetProcessingMode() {
    return (this.processingMode != null);
  }

  /**
   * Gets the value of the processingGroup property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getProcessingGroup() {
    return processingGroup;
  }

  /**
   * Sets the value of the processingGroup property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setProcessingGroup(String value) {
    this.processingGroup = value;
  }

  public boolean isSetProcessingGroup() {
    return (this.processingGroup != null);
  }

  /**
   * Gets the value of the currentStorageMode property.
   * 
   * @return possible object is {@link Dimension.CurrentStorageMode }
   * 
   */
  public Dimension.CurrentStorageMode getCurrentStorageMode() {
    return currentStorageMode;
  }

  /**
   * Sets the value of the currentStorageMode property.
   * 
   * @param value allowed object is {@link Dimension.CurrentStorageMode }
   * 
   */
  public void setCurrentStorageMode(Dimension.CurrentStorageMode value) {
    this.currentStorageMode = value;
  }

  public boolean isSetCurrentStorageMode() {
    return (this.currentStorageMode != null);
  }

  /**
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link Dimension.Translations }
   * 
   */
  public Dimension.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is {@link Dimension.Translations }
   * 
   */
  public void setTranslations(Dimension.Translations value) {
    this.translations = value;
  }

  public boolean isSetTranslations() {
    return (this.translations != null);
  }

  /**
   * Gets the value of the attributes property.
   * 
   * @return possible object is {@link Dimension.Attributes }
   * 
   */
  public Dimension.Attributes getAttributes() {
    return attributes;
  }

  /**
   * Sets the value of the attributes property.
   * 
   * @param value allowed object is {@link Dimension.Attributes }
   * 
   */
  public void setAttributes(Dimension.Attributes value) {
    this.attributes = value;
  }

  public boolean isSetAttributes() {
    return (this.attributes != null);
  }

  /**
   * Gets the value of the attributeAllMemberName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAttributeAllMemberName() {
    return attributeAllMemberName;
  }

  /**
   * Sets the value of the attributeAllMemberName property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAttributeAllMemberName(String value) {
    this.attributeAllMemberName = value;
  }

  public boolean isSetAttributeAllMemberName() {
    return (this.attributeAllMemberName != null);
  }

  /**
   * Gets the value of the attributeAllMemberTranslations property.
   * 
   * @return possible object is {@link Dimension.AttributeAllMemberTranslations }
   * 
   */
  public Dimension.AttributeAllMemberTranslations getAttributeAllMemberTranslations() {
    return attributeAllMemberTranslations;
  }

  /**
   * Sets the value of the attributeAllMemberTranslations property.
   * 
   * @param value allowed object is
   *              {@link Dimension.AttributeAllMemberTranslations }
   * 
   */
  public void setAttributeAllMemberTranslations(Dimension.AttributeAllMemberTranslations value) {
    this.attributeAllMemberTranslations = value;
  }

  public boolean isSetAttributeAllMemberTranslations() {
    return (this.attributeAllMemberTranslations != null);
  }

  /**
   * Gets the value of the hierarchies property.
   * 
   * @return possible object is {@link Dimension.Hierarchies }
   * 
   */
  public Dimension.Hierarchies getHierarchies() {
    return hierarchies;
  }

  /**
   * Sets the value of the hierarchies property.
   * 
   * @param value allowed object is {@link Dimension.Hierarchies }
   * 
   */
  public void setHierarchies(Dimension.Hierarchies value) {
    this.hierarchies = value;
  }

  public boolean isSetHierarchies() {
    return (this.hierarchies != null);
  }

  /**
   * Gets the value of the processingRecommendation property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getProcessingRecommendation() {
    return processingRecommendation;
  }

  /**
   * Sets the value of the processingRecommendation property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setProcessingRecommendation(String value) {
    this.processingRecommendation = value;
  }

  public boolean isSetProcessingRecommendation() {
    return (this.processingRecommendation != null);
  }

  /**
   * Gets the value of the relationships property.
   * 
   * @return possible object is {@link Relationships }
   * 
   */
  public Relationships getRelationships() {
    return relationships;
  }

  /**
   * Sets the value of the relationships property.
   * 
   * @param value allowed object is {@link Relationships }
   * 
   */
  public void setRelationships(Relationships value) {
    this.relationships = value;
  }

  public boolean isSetRelationships() {
    return (this.relationships != null);
  }

  /**
   * Gets the value of the stringStoresCompatibilityLevel property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getStringStoresCompatibilityLevel() {
    return stringStoresCompatibilityLevel;
  }

  /**
   * Sets the value of the stringStoresCompatibilityLevel property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setStringStoresCompatibilityLevel(Integer value) {
    this.stringStoresCompatibilityLevel = value;
  }

  public boolean isSetStringStoresCompatibilityLevel() {
    return (this.stringStoresCompatibilityLevel != null);
  }

  /**
   * Gets the value of the currentStringStoresCompatibilityLevel property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getCurrentStringStoresCompatibilityLevel() {
    return currentStringStoresCompatibilityLevel;
  }

  /**
   * Sets the value of the currentStringStoresCompatibilityLevel property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setCurrentStringStoresCompatibilityLevel(Integer value) {
    this.currentStringStoresCompatibilityLevel = value;
  }

  public boolean isSetCurrentStringStoresCompatibilityLevel() {
    return (this.currentStringStoresCompatibilityLevel != null);
  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
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
  @XmlType(name = "", propOrder = { "annotation" })
  public static class Annotations {

    @XmlElement(name = "Annotation")
    protected List<Annotation> annotation;

    /**
     * Gets the value of the annotation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the annotation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAnnotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Annotation }
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
      return ((this.annotation != null) && (!this.annotation.isEmpty()));
    }

    public void unsetAnnotation() {
      this.annotation = null;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="MemberAllMemberTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "memberAllMemberTranslation" })
  public static class AttributeAllMemberTranslations {

    @XmlElement(name = "MemberAllMemberTranslation")
    protected List<Translation> memberAllMemberTranslation;

    /**
     * Gets the value of the memberAllMemberTranslation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the memberAllMemberTranslation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMemberAllMemberTranslation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Translation }
     * 
     * 
     */
    public List<Translation> getMemberAllMemberTranslation() {
      if (memberAllMemberTranslation == null) {
        memberAllMemberTranslation = new ArrayList<Translation>();
      }
      return this.memberAllMemberTranslation;
    }

    public boolean isSetMemberAllMemberTranslation() {
      return ((this.memberAllMemberTranslation != null) && (!this.memberAllMemberTranslation.isEmpty()));
    }

    public void unsetMemberAllMemberTranslation() {
      this.memberAllMemberTranslation = null;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}DimensionAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "attribute" })
  public static class Attributes {

    @XmlElement(name = "Attribute")
    protected List<DimensionAttribute> attribute;

    /**
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DimensionAttribute }
     * 
     * 
     */
    public List<DimensionAttribute> getAttribute() {
      if (attribute == null) {
        attribute = new ArrayList<DimensionAttribute>();
      }
      return this.attribute;
    }

    public boolean isSetAttribute() {
      return ((this.attribute != null) && (!this.attribute.isEmpty()));
    }

    public void unsetAttribute() {
      this.attribute = null;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;simpleContent&gt;
   *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;DimensionCurrentStorageModeEnumType"&gt;
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
  @XmlType(name = "", propOrder = { "value" })
  public static class CurrentStorageMode {

    @XmlValue
    protected DimensionCurrentStorageModeEnumType value;
    @XmlAttribute(name = "valuens")
    protected String valuens;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link DimensionCurrentStorageModeEnumType }
     * 
     */
    public DimensionCurrentStorageModeEnumType getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link DimensionCurrentStorageModeEnumType }
     * 
     */
    public void setValue(DimensionCurrentStorageModeEnumType value) {
      this.value = value;
    }

    public boolean isSetValue() {
      return (this.value != null);
    }

    /**
     * Gets the value of the valuens property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getValuens() {
      return valuens;
    }

    /**
     * Sets the value of the valuens property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setValuens(String value) {
      this.valuens = value;
    }

    public boolean isSetValuens() {
      return (this.valuens != null);
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="DimensionPermission" type="{urn:schemas-microsoft-com:xml-analysis}DimensionPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "dimensionPermission" })
  public static class DimensionPermissions {

    @XmlElement(name = "DimensionPermission")
    protected List<DimensionPermission> dimensionPermission;

    /**
     * Gets the value of the dimensionPermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the dimensionPermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDimensionPermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DimensionPermission }
     * 
     * 
     */
    public List<DimensionPermission> getDimensionPermission() {
      if (dimensionPermission == null) {
        dimensionPermission = new ArrayList<DimensionPermission>();
      }
      return this.dimensionPermission;
    }

    public boolean isSetDimensionPermission() {
      return ((this.dimensionPermission != null) && (!this.dimensionPermission.isEmpty()));
    }

    public void unsetDimensionPermission() {
      this.dimensionPermission = null;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="Hierarchy" type="{urn:schemas-microsoft-com:xml-analysis}Hierarchy" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "hierarchy" })
  public static class Hierarchies {

    @XmlElement(name = "Hierarchy")
    protected List<Hierarchy> hierarchy;

    /**
     * Gets the value of the hierarchy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the hierarchy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getHierarchy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Hierarchy }
     * 
     * 
     */
    public List<Hierarchy> getHierarchy() {
      if (hierarchy == null) {
        hierarchy = new ArrayList<Hierarchy>();
      }
      return this.hierarchy;
    }

    public boolean isSetHierarchy() {
      return ((this.hierarchy != null) && (!this.hierarchy.isEmpty()));
    }

    public void unsetHierarchy() {
      this.hierarchy = null;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "translation" })
  public static class Translations {

    @XmlElement(name = "Translation")
    protected List<Translation> translation;

    /**
     * Gets the value of the translation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the translation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getTranslation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Translation }
     * 
     * 
     */
    public List<Translation> getTranslation() {
      if (translation == null) {
        translation = new ArrayList<Translation>();
      }
      return this.translation;
    }

    public boolean isSetTranslation() {
      return ((this.translation != null) && (!this.translation.isEmpty()));
    }

    public void unsetTranslation() {
      this.translation = null;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;simpleContent&gt;
   *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;UnknownMemberEnumType"&gt;
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
  @XmlType(name = "", propOrder = { "value" })
  public static class UnknownMember {

    @XmlValue
    protected UnknownMemberEnumType value;
    @XmlAttribute(name = "valuens")
    protected String valuens;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link UnknownMemberEnumType }
     * 
     */
    public UnknownMemberEnumType getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link UnknownMemberEnumType }
     * 
     */
    public void setValue(UnknownMemberEnumType value) {
      this.value = value;
    }

    public boolean isSetValue() {
      return (this.value != null);
    }

    /**
     * Gets the value of the valuens property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getValuens() {
      return valuens;
    }

    /**
     * Sets the value of the valuens property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setValuens(String value) {
      this.valuens = value;
    }

    public boolean isSetValuens() {
      return (this.valuens != null);
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="UnknownMemberTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "unknownMemberTranslation" })
  public static class UnknownMemberTranslations {

    @XmlElement(name = "UnknownMemberTranslation")
    protected List<Translation> unknownMemberTranslation;

    /**
     * Gets the value of the unknownMemberTranslation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the unknownMemberTranslation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getUnknownMemberTranslation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Translation }
     * 
     * 
     */
    public List<Translation> getUnknownMemberTranslation() {
      if (unknownMemberTranslation == null) {
        unknownMemberTranslation = new ArrayList<Translation>();
      }
      return this.unknownMemberTranslation;
    }

    public boolean isSetUnknownMemberTranslation() {
      return ((this.unknownMemberTranslation != null) && (!this.unknownMemberTranslation.isEmpty()));
    }

    public void unsetUnknownMemberTranslation() {
      this.unknownMemberTranslation = null;
    }

  }

}
