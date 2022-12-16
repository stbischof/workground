
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.daanse.xmla.model.jaxb.engine.ImpersonationInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Database complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Database"&gt;
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
 *         &lt;element name="LastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="PartiallyProcessed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100}ReadWriteMode" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}DbStorageLocation" minOccurs="0"/&gt;
 *         &lt;element name="AggregationPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingPriority" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="EstimatedSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Collation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MasterDataSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataSourceImpersonationInfo" type="{http://schemas.microsoft.com/analysisservices/2003/engine}ImpersonationInfo" minOccurs="0"/&gt;
 *         &lt;element name="Accounts" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Account" type="{urn:schemas-microsoft-com:xml-analysis}Account" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DataSources" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataSource" type="{urn:schemas-microsoft-com:xml-analysis}DataSource" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DataSourceViews" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataSourceView" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceView" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Dimensions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}Dimension" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Cubes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Cube" type="{urn:schemas-microsoft-com:xml-analysis}Cube" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MiningStructures" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MiningStructure" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructure" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Roles" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Role" type="{urn:schemas-microsoft-com:xml-analysis}Role" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Assemblies" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Assembly" type="{urn:schemas-microsoft-com:xml-analysis}Assembly" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DatabasePermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DatabasePermission" type="{urn:schemas-microsoft-com:xml-analysis}DatabasePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
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
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}StorageEngineUsed" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImagePath" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImageUrl" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImageUniqueID" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImageVersion" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}Token" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200}CompatibilityLevel" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}DirectQueryMode" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Database", propOrder = {

})
public class Database {

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
  protected Database.Annotations annotations;
  @XmlElement(name = "LastUpdate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastUpdate;
  @XmlElement(name = "State")
  protected String state;
  @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100")
  protected String readWriteMode;
  @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
  protected String dbStorageLocation;
  @XmlElement(name = "AggregationPrefix")
  protected String aggregationPrefix;
  @XmlElement(name = "ProcessingPriority")
  protected BigInteger processingPriority;
  @XmlElement(name = "EstimatedSize")
  protected Long estimatedSize;
  @XmlElement(name = "LastProcessed")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastProcessed;
  @XmlElement(name = "Language")
  protected BigInteger language;
  @XmlElement(name = "Collation")
  protected String collation;
  @XmlElement(name = "Visible")
  protected Boolean visible;
  @XmlElement(name = "MasterDataSourceID")
  protected String masterDataSourceID;
  @XmlElement(name = "DataSourceImpersonationInfo")
  protected ImpersonationInfo dataSourceImpersonationInfo;
  @XmlElement(name = "Accounts")
  protected Database.Accounts accounts;
  @XmlElement(name = "DataSources")
  protected Database.DataSources dataSources;
  @XmlElement(name = "DataSourceViews")
  protected Database.DataSourceViews dataSourceViews;
  @XmlElement(name = "Dimensions")
  protected Database.Dimensions dimensions;
  @XmlElement(name = "Cubes")
  protected Database.Cubes cubes;
  @XmlElement(name = "MiningStructures")
  protected Database.MiningStructures miningStructures;
  @XmlElement(name = "Roles")
  protected Database.Roles roles;
  @XmlElement(name = "Assemblies")
  protected Database.Assemblies assemblies;
  @XmlElement(name = "DatabasePermissions")
  protected Database.DatabasePermissions databasePermissions;
  @XmlElement(name = "Translations")
  protected Database.Translations translations;
  @XmlElement(name = "StorageEngineUsed", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String storageEngineUsed;
  @XmlElement(name = "ImagePath", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String imagePath;
  @XmlElement(name = "ImageUrl", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String imageUrl;
  @XmlElement(name = "ImageUniqueID", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String imageUniqueID;
  @XmlElement(name = "ImageVersion", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String imageVersion;
  @XmlElement(name = "Token", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
  protected String token;
  @XmlElement(name = "CompatibilityLevel", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
  protected BigInteger compatibilityLevel;
  @XmlElement(name = "DirectQueryMode", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300")
  protected String directQueryMode;

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
   * @return possible object is {@link Database.Annotations }
   * 
   */
  public Database.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link Database.Annotations }
   * 
   */
  public void setAnnotations(Database.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
  }

  /**
   * Gets the value of the lastUpdate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getLastUpdate() {
    return lastUpdate;
  }

  /**
   * Sets the value of the lastUpdate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setLastUpdate(XMLGregorianCalendar value) {
    this.lastUpdate = value;
  }

  public boolean isSetLastUpdate() {
    return (this.lastUpdate != null);
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
   * Gets the value of the readWriteMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getReadWriteMode() {
    return readWriteMode;
  }

  /**
   * Sets the value of the readWriteMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setReadWriteMode(String value) {
    this.readWriteMode = value;
  }

  public boolean isSetReadWriteMode() {
    return (this.readWriteMode != null);
  }

  /**
   * Gets the value of the dbStorageLocation property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDbStorageLocation() {
    return dbStorageLocation;
  }

  /**
   * Sets the value of the dbStorageLocation property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDbStorageLocation(String value) {
    this.dbStorageLocation = value;
  }

  public boolean isSetDbStorageLocation() {
    return (this.dbStorageLocation != null);
  }

  /**
   * Gets the value of the aggregationPrefix property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAggregationPrefix() {
    return aggregationPrefix;
  }

  /**
   * Sets the value of the aggregationPrefix property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAggregationPrefix(String value) {
    this.aggregationPrefix = value;
  }

  public boolean isSetAggregationPrefix() {
    return (this.aggregationPrefix != null);
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
   * Gets the value of the estimatedSize property.
   * 
   * @return possible object is {@link Long }
   * 
   */
  public Long getEstimatedSize() {
    return estimatedSize;
  }

  /**
   * Sets the value of the estimatedSize property.
   * 
   * @param value allowed object is {@link Long }
   * 
   */
  public void setEstimatedSize(Long value) {
    this.estimatedSize = value;
  }

  public boolean isSetEstimatedSize() {
    return (this.estimatedSize != null);
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
   * Gets the value of the visible property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isVisible() {
    return visible;
  }

  /**
   * Sets the value of the visible property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setVisible(Boolean value) {
    this.visible = value;
  }

  public boolean isSetVisible() {
    return (this.visible != null);
  }

  /**
   * Gets the value of the masterDataSourceID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMasterDataSourceID() {
    return masterDataSourceID;
  }

  /**
   * Sets the value of the masterDataSourceID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMasterDataSourceID(String value) {
    this.masterDataSourceID = value;
  }

  public boolean isSetMasterDataSourceID() {
    return (this.masterDataSourceID != null);
  }

  /**
   * Gets the value of the dataSourceImpersonationInfo property.
   * 
   * @return possible object is {@link ImpersonationInfo }
   * 
   */
  public ImpersonationInfo getDataSourceImpersonationInfo() {
    return dataSourceImpersonationInfo;
  }

  /**
   * Sets the value of the dataSourceImpersonationInfo property.
   * 
   * @param value allowed object is {@link ImpersonationInfo }
   * 
   */
  public void setDataSourceImpersonationInfo(ImpersonationInfo value) {
    this.dataSourceImpersonationInfo = value;
  }

  public boolean isSetDataSourceImpersonationInfo() {
    return (this.dataSourceImpersonationInfo != null);
  }

  /**
   * Gets the value of the accounts property.
   * 
   * @return possible object is {@link Database.Accounts }
   * 
   */
  public Database.Accounts getAccounts() {
    return accounts;
  }

  /**
   * Sets the value of the accounts property.
   * 
   * @param value allowed object is {@link Database.Accounts }
   * 
   */
  public void setAccounts(Database.Accounts value) {
    this.accounts = value;
  }

  public boolean isSetAccounts() {
    return (this.accounts != null);
  }

  /**
   * Gets the value of the dataSources property.
   * 
   * @return possible object is {@link Database.DataSources }
   * 
   */
  public Database.DataSources getDataSources() {
    return dataSources;
  }

  /**
   * Sets the value of the dataSources property.
   * 
   * @param value allowed object is {@link Database.DataSources }
   * 
   */
  public void setDataSources(Database.DataSources value) {
    this.dataSources = value;
  }

  public boolean isSetDataSources() {
    return (this.dataSources != null);
  }

  /**
   * Gets the value of the dataSourceViews property.
   * 
   * @return possible object is {@link Database.DataSourceViews }
   * 
   */
  public Database.DataSourceViews getDataSourceViews() {
    return dataSourceViews;
  }

  /**
   * Sets the value of the dataSourceViews property.
   * 
   * @param value allowed object is {@link Database.DataSourceViews }
   * 
   */
  public void setDataSourceViews(Database.DataSourceViews value) {
    this.dataSourceViews = value;
  }

  public boolean isSetDataSourceViews() {
    return (this.dataSourceViews != null);
  }

  /**
   * Gets the value of the dimensions property.
   * 
   * @return possible object is {@link Database.Dimensions }
   * 
   */
  public Database.Dimensions getDimensions() {
    return dimensions;
  }

  /**
   * Sets the value of the dimensions property.
   * 
   * @param value allowed object is {@link Database.Dimensions }
   * 
   */
  public void setDimensions(Database.Dimensions value) {
    this.dimensions = value;
  }

  public boolean isSetDimensions() {
    return (this.dimensions != null);
  }

  /**
   * Gets the value of the cubes property.
   * 
   * @return possible object is {@link Database.Cubes }
   * 
   */
  public Database.Cubes getCubes() {
    return cubes;
  }

  /**
   * Sets the value of the cubes property.
   * 
   * @param value allowed object is {@link Database.Cubes }
   * 
   */
  public void setCubes(Database.Cubes value) {
    this.cubes = value;
  }

  public boolean isSetCubes() {
    return (this.cubes != null);
  }

  /**
   * Gets the value of the miningStructures property.
   * 
   * @return possible object is {@link Database.MiningStructures }
   * 
   */
  public Database.MiningStructures getMiningStructures() {
    return miningStructures;
  }

  /**
   * Sets the value of the miningStructures property.
   * 
   * @param value allowed object is {@link Database.MiningStructures }
   * 
   */
  public void setMiningStructures(Database.MiningStructures value) {
    this.miningStructures = value;
  }

  public boolean isSetMiningStructures() {
    return (this.miningStructures != null);
  }

  /**
   * Gets the value of the roles property.
   * 
   * @return possible object is {@link Database.Roles }
   * 
   */
  public Database.Roles getRoles() {
    return roles;
  }

  /**
   * Sets the value of the roles property.
   * 
   * @param value allowed object is {@link Database.Roles }
   * 
   */
  public void setRoles(Database.Roles value) {
    this.roles = value;
  }

  public boolean isSetRoles() {
    return (this.roles != null);
  }

  /**
   * Gets the value of the assemblies property.
   * 
   * @return possible object is {@link Database.Assemblies }
   * 
   */
  public Database.Assemblies getAssemblies() {
    return assemblies;
  }

  /**
   * Sets the value of the assemblies property.
   * 
   * @param value allowed object is {@link Database.Assemblies }
   * 
   */
  public void setAssemblies(Database.Assemblies value) {
    this.assemblies = value;
  }

  public boolean isSetAssemblies() {
    return (this.assemblies != null);
  }

  /**
   * Gets the value of the databasePermissions property.
   * 
   * @return possible object is {@link Database.DatabasePermissions }
   * 
   */
  public Database.DatabasePermissions getDatabasePermissions() {
    return databasePermissions;
  }

  /**
   * Sets the value of the databasePermissions property.
   * 
   * @param value allowed object is {@link Database.DatabasePermissions }
   * 
   */
  public void setDatabasePermissions(Database.DatabasePermissions value) {
    this.databasePermissions = value;
  }

  public boolean isSetDatabasePermissions() {
    return (this.databasePermissions != null);
  }

  /**
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link Database.Translations }
   * 
   */
  public Database.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is {@link Database.Translations }
   * 
   */
  public void setTranslations(Database.Translations value) {
    this.translations = value;
  }

  public boolean isSetTranslations() {
    return (this.translations != null);
  }

  /**
   * Gets the value of the storageEngineUsed property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getStorageEngineUsed() {
    return storageEngineUsed;
  }

  /**
   * Sets the value of the storageEngineUsed property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setStorageEngineUsed(String value) {
    this.storageEngineUsed = value;
  }

  public boolean isSetStorageEngineUsed() {
    return (this.storageEngineUsed != null);
  }

  /**
   * Gets the value of the imagePath property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getImagePath() {
    return imagePath;
  }

  /**
   * Sets the value of the imagePath property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setImagePath(String value) {
    this.imagePath = value;
  }

  public boolean isSetImagePath() {
    return (this.imagePath != null);
  }

  /**
   * Gets the value of the imageUrl property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Sets the value of the imageUrl property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setImageUrl(String value) {
    this.imageUrl = value;
  }

  public boolean isSetImageUrl() {
    return (this.imageUrl != null);
  }

  /**
   * Gets the value of the imageUniqueID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getImageUniqueID() {
    return imageUniqueID;
  }

  /**
   * Sets the value of the imageUniqueID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setImageUniqueID(String value) {
    this.imageUniqueID = value;
  }

  public boolean isSetImageUniqueID() {
    return (this.imageUniqueID != null);
  }

  /**
   * Gets the value of the imageVersion property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getImageVersion() {
    return imageVersion;
  }

  /**
   * Sets the value of the imageVersion property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setImageVersion(String value) {
    this.imageVersion = value;
  }

  public boolean isSetImageVersion() {
    return (this.imageVersion != null);
  }

  /**
   * Gets the value of the token property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getToken() {
    return token;
  }

  /**
   * Sets the value of the token property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setToken(String value) {
    this.token = value;
  }

  public boolean isSetToken() {
    return (this.token != null);
  }

  /**
   * Gets the value of the compatibilityLevel property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getCompatibilityLevel() {
    return compatibilityLevel;
  }

  /**
   * Sets the value of the compatibilityLevel property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setCompatibilityLevel(BigInteger value) {
    this.compatibilityLevel = value;
  }

  public boolean isSetCompatibilityLevel() {
    return (this.compatibilityLevel != null);
  }

  /**
   * Gets the value of the directQueryMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDirectQueryMode() {
    return directQueryMode;
  }

  /**
   * Sets the value of the directQueryMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDirectQueryMode(String value) {
    this.directQueryMode = value;
  }

  public boolean isSetDirectQueryMode() {
    return (this.directQueryMode != null);
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
   *         &lt;element name="Account" type="{urn:schemas-microsoft-com:xml-analysis}Account" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "account" })
  public static class Accounts {

    @XmlElement(name = "Account")
    protected List<Account> account;

    /**
     * Gets the value of the account property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the account property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAccount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Account }
     * 
     * 
     */
    public List<Account> getAccount() {
      if (account == null) {
        account = new ArrayList<Account>();
      }
      return this.account;
    }

    public boolean isSetAccount() {
      return ((this.account != null) && (!this.account.isEmpty()));
    }

    public void unsetAccount() {
      this.account = null;
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
   *         &lt;element name="Assembly" type="{urn:schemas-microsoft-com:xml-analysis}Assembly" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "assembly" })
  public static class Assemblies {

    @XmlElement(name = "Assembly")
    protected List<Assembly> assembly;

    /**
     * Gets the value of the assembly property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the assembly property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAssembly().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Assembly }
     * 
     * 
     */
    public List<Assembly> getAssembly() {
      if (assembly == null) {
        assembly = new ArrayList<Assembly>();
      }
      return this.assembly;
    }

    public boolean isSetAssembly() {
      return ((this.assembly != null) && (!this.assembly.isEmpty()));
    }

    public void unsetAssembly() {
      this.assembly = null;
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
   *         &lt;element name="Cube" type="{urn:schemas-microsoft-com:xml-analysis}Cube" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "cube" })
  public static class Cubes {

    @XmlElement(name = "Cube")
    protected List<Cube> cube;

    /**
     * Gets the value of the cube property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the cube property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getCube().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Cube }
     * 
     * 
     */
    public List<Cube> getCube() {
      if (cube == null) {
        cube = new ArrayList<Cube>();
      }
      return this.cube;
    }

    public boolean isSetCube() {
      return ((this.cube != null) && (!this.cube.isEmpty()));
    }

    public void unsetCube() {
      this.cube = null;
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
   *         &lt;element name="DatabasePermission" type="{urn:schemas-microsoft-com:xml-analysis}DatabasePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "databasePermission" })
  public static class DatabasePermissions {

    @XmlElement(name = "DatabasePermission")
    protected List<DatabasePermission> databasePermission;

    /**
     * Gets the value of the databasePermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the databasePermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDatabasePermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatabasePermission }
     * 
     * 
     */
    public List<DatabasePermission> getDatabasePermission() {
      if (databasePermission == null) {
        databasePermission = new ArrayList<DatabasePermission>();
      }
      return this.databasePermission;
    }

    public boolean isSetDatabasePermission() {
      return ((this.databasePermission != null) && (!this.databasePermission.isEmpty()));
    }

    public void unsetDatabasePermission() {
      this.databasePermission = null;
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
   *         &lt;element name="DataSource" type="{urn:schemas-microsoft-com:xml-analysis}DataSource" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "dataSource" })
  public static class DataSources {

    @XmlElement(name = "DataSource")
    protected List<DataSource> dataSource;

    /**
     * Gets the value of the dataSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the dataSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDataSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link DataSource }
     * 
     * 
     */
    public List<DataSource> getDataSource() {
      if (dataSource == null) {
        dataSource = new ArrayList<DataSource>();
      }
      return this.dataSource;
    }

    public boolean isSetDataSource() {
      return ((this.dataSource != null) && (!this.dataSource.isEmpty()));
    }

    public void unsetDataSource() {
      this.dataSource = null;
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
   *         &lt;element name="DataSourceView" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceView" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "dataSourceView" })
  public static class DataSourceViews {

    @XmlElement(name = "DataSourceView")
    protected List<DataSourceView> dataSourceView;

    /**
     * Gets the value of the dataSourceView property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the dataSourceView property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDataSourceView().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSourceView }
     * 
     * 
     */
    public List<DataSourceView> getDataSourceView() {
      if (dataSourceView == null) {
        dataSourceView = new ArrayList<DataSourceView>();
      }
      return this.dataSourceView;
    }

    public boolean isSetDataSourceView() {
      return ((this.dataSourceView != null) && (!this.dataSourceView.isEmpty()));
    }

    public void unsetDataSourceView() {
      this.dataSourceView = null;
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
   *         &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}Dimension" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "dimension" })
  public static class Dimensions {

    @XmlElement(name = "Dimension")
    protected List<Dimension> dimension;

    /**
     * Gets the value of the dimension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the dimension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDimension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Dimension }
     * 
     * 
     */
    public List<Dimension> getDimension() {
      if (dimension == null) {
        dimension = new ArrayList<Dimension>();
      }
      return this.dimension;
    }

    public boolean isSetDimension() {
      return ((this.dimension != null) && (!this.dimension.isEmpty()));
    }

    public void unsetDimension() {
      this.dimension = null;
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
   *         &lt;element name="MiningStructure" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructure" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "miningStructure" })
  public static class MiningStructures {

    @XmlElement(name = "MiningStructure")
    protected List<MiningStructure> miningStructure;

    /**
     * Gets the value of the miningStructure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the miningStructure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMiningStructure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MiningStructure }
     * 
     * 
     */
    public List<MiningStructure> getMiningStructure() {
      if (miningStructure == null) {
        miningStructure = new ArrayList<MiningStructure>();
      }
      return this.miningStructure;
    }

    public boolean isSetMiningStructure() {
      return ((this.miningStructure != null) && (!this.miningStructure.isEmpty()));
    }

    public void unsetMiningStructure() {
      this.miningStructure = null;
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
   *         &lt;element name="Role" type="{urn:schemas-microsoft-com:xml-analysis}Role" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "role" })
  public static class Roles {

    @XmlElement(name = "Role")
    protected List<Role> role;

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Role }
     * 
     * 
     */
    public List<Role> getRole() {
      if (role == null) {
        role = new ArrayList<Role>();
      }
      return this.role;
    }

    public boolean isSetRole() {
      return ((this.role != null) && (!this.role.isEmpty()));
    }

    public void unsetRole() {
      this.role = null;
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

}
