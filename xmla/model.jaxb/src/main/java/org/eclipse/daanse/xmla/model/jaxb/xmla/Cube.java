
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for Cube complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Cube"&gt;
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
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Collation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="Dimensions"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}CubeDimension" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CubePermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CubePermission" type="{urn:schemas-microsoft-com:xml-analysis}CubePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdxScripts" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MdxScript" type="{urn:schemas-microsoft-com:xml-analysis}MdxScript" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Perspectives" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Perspective" type="{urn:schemas-microsoft-com:xml-analysis}Perspective" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="PartiallyProcessed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DefaultMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MeasureGroups"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MeasureGroup" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceViewBinding" minOccurs="0"/&gt;
 *         &lt;element name="AggregationPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingPriority" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="StorageMode" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;CubeStorageModeEnumType"&gt;
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
 *         &lt;element name="ProcessingMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="LazyAggregations"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ScriptCacheProcessingMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="Lazy"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ScriptErrorHandlingMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="IgnoreNone"/&gt;
 *               &lt;enumeration value="IgnoreAll"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2013/engine/800}DaxOptimizationMode" minOccurs="0"/&gt;
 *         &lt;element name="ProactiveCaching" type="{urn:schemas-microsoft-com:xml-analysis}ProactiveCaching" minOccurs="0"/&gt;
 *         &lt;element name="Kpis" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Kpi" type="{urn:schemas-microsoft-com:xml-analysis}Kpi" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="Actions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Action" type="{urn:schemas-microsoft-com:xml-analysis}Action" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="StorageLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EstimatedRows" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cube", propOrder = {

})
public class Cube {

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
  protected Cube.Annotations annotations;
  @XmlElement(name = "Language")
  protected BigInteger language;
  @XmlElement(name = "Collation")
  protected String collation;
  @XmlElement(name = "Translations")
  protected Cube.Translations translations;
  @XmlElement(name = "Dimensions", required = true)
  protected Cube.Dimensions dimensions;
  @XmlElement(name = "CubePermissions")
  protected Cube.CubePermissions cubePermissions;
  @XmlElement(name = "MdxScripts")
  protected Cube.MdxScripts mdxScripts;
  @XmlElement(name = "Perspectives")
  protected Cube.Perspectives perspectives;
  @XmlElement(name = "State")
  protected String state;
  @XmlElement(name = "DefaultMeasure")
  protected String defaultMeasure;
  @XmlElement(name = "Visible")
  protected Boolean visible;
  @XmlElement(name = "MeasureGroups", required = true)
  protected Cube.MeasureGroups measureGroups;
  @XmlElement(name = "Source")
  protected DataSourceViewBinding source;
  @XmlElement(name = "AggregationPrefix")
  protected String aggregationPrefix;
  @XmlElement(name = "ProcessingPriority")
  protected BigInteger processingPriority;
  @XmlElement(name = "StorageMode")
  protected Cube.StorageMode storageMode;
  @XmlElement(name = "ProcessingMode")
  protected String processingMode;
  @XmlElement(name = "ScriptCacheProcessingMode")
  protected String scriptCacheProcessingMode;
  @XmlElement(name = "ScriptErrorHandlingMode")
  protected String scriptErrorHandlingMode;
  @XmlElement(name = "DaxOptimizationMode", namespace = "http://schemas.microsoft.com/analysisservices/2013/engine/800")
  protected String daxOptimizationMode;
  @XmlElement(name = "ProactiveCaching")
  protected ProactiveCaching proactiveCaching;
  @XmlElement(name = "Kpis")
  protected Cube.Kpis kpis;
  @XmlElement(name = "ErrorConfiguration")
  protected ErrorConfiguration errorConfiguration;
  @XmlElement(name = "Actions")
  protected Cube.Actions actions;
  @XmlElement(name = "StorageLocation")
  protected String storageLocation;
  @XmlElement(name = "EstimatedRows")
  protected Long estimatedRows;
  @XmlElement(name = "LastProcessed")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastProcessed;

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
   * @return possible object is {@link Cube.Annotations }
   * 
   */
  public Cube.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link Cube.Annotations }
   * 
   */
  public void setAnnotations(Cube.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
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
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link Cube.Translations }
   * 
   */
  public Cube.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is {@link Cube.Translations }
   * 
   */
  public void setTranslations(Cube.Translations value) {
    this.translations = value;
  }

  public boolean isSetTranslations() {
    return (this.translations != null);
  }

  /**
   * Gets the value of the dimensions property.
   * 
   * @return possible object is {@link Cube.Dimensions }
   * 
   */
  public Cube.Dimensions getDimensions() {
    return dimensions;
  }

  /**
   * Sets the value of the dimensions property.
   * 
   * @param value allowed object is {@link Cube.Dimensions }
   * 
   */
  public void setDimensions(Cube.Dimensions value) {
    this.dimensions = value;
  }

  public boolean isSetDimensions() {
    return (this.dimensions != null);
  }

  /**
   * Gets the value of the cubePermissions property.
   * 
   * @return possible object is {@link Cube.CubePermissions }
   * 
   */
  public Cube.CubePermissions getCubePermissions() {
    return cubePermissions;
  }

  /**
   * Sets the value of the cubePermissions property.
   * 
   * @param value allowed object is {@link Cube.CubePermissions }
   * 
   */
  public void setCubePermissions(Cube.CubePermissions value) {
    this.cubePermissions = value;
  }

  public boolean isSetCubePermissions() {
    return (this.cubePermissions != null);
  }

  /**
   * Gets the value of the mdxScripts property.
   * 
   * @return possible object is {@link Cube.MdxScripts }
   * 
   */
  public Cube.MdxScripts getMdxScripts() {
    return mdxScripts;
  }

  /**
   * Sets the value of the mdxScripts property.
   * 
   * @param value allowed object is {@link Cube.MdxScripts }
   * 
   */
  public void setMdxScripts(Cube.MdxScripts value) {
    this.mdxScripts = value;
  }

  public boolean isSetMdxScripts() {
    return (this.mdxScripts != null);
  }

  /**
   * Gets the value of the perspectives property.
   * 
   * @return possible object is {@link Cube.Perspectives }
   * 
   */
  public Cube.Perspectives getPerspectives() {
    return perspectives;
  }

  /**
   * Sets the value of the perspectives property.
   * 
   * @param value allowed object is {@link Cube.Perspectives }
   * 
   */
  public void setPerspectives(Cube.Perspectives value) {
    this.perspectives = value;
  }

  public boolean isSetPerspectives() {
    return (this.perspectives != null);
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
   * Gets the value of the defaultMeasure property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDefaultMeasure() {
    return defaultMeasure;
  }

  /**
   * Sets the value of the defaultMeasure property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDefaultMeasure(String value) {
    this.defaultMeasure = value;
  }

  public boolean isSetDefaultMeasure() {
    return (this.defaultMeasure != null);
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
   * Gets the value of the measureGroups property.
   * 
   * @return possible object is {@link Cube.MeasureGroups }
   * 
   */
  public Cube.MeasureGroups getMeasureGroups() {
    return measureGroups;
  }

  /**
   * Sets the value of the measureGroups property.
   * 
   * @param value allowed object is {@link Cube.MeasureGroups }
   * 
   */
  public void setMeasureGroups(Cube.MeasureGroups value) {
    this.measureGroups = value;
  }

  public boolean isSetMeasureGroups() {
    return (this.measureGroups != null);
  }

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link DataSourceViewBinding }
   * 
   */
  public DataSourceViewBinding getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link DataSourceViewBinding }
   * 
   */
  public void setSource(DataSourceViewBinding value) {
    this.source = value;
  }

  public boolean isSetSource() {
    return (this.source != null);
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
   * Gets the value of the storageMode property.
   * 
   * @return possible object is {@link Cube.StorageMode }
   * 
   */
  public Cube.StorageMode getStorageMode() {
    return storageMode;
  }

  /**
   * Sets the value of the storageMode property.
   * 
   * @param value allowed object is {@link Cube.StorageMode }
   * 
   */
  public void setStorageMode(Cube.StorageMode value) {
    this.storageMode = value;
  }

  public boolean isSetStorageMode() {
    return (this.storageMode != null);
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
   * Gets the value of the scriptCacheProcessingMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getScriptCacheProcessingMode() {
    return scriptCacheProcessingMode;
  }

  /**
   * Sets the value of the scriptCacheProcessingMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setScriptCacheProcessingMode(String value) {
    this.scriptCacheProcessingMode = value;
  }

  public boolean isSetScriptCacheProcessingMode() {
    return (this.scriptCacheProcessingMode != null);
  }

  /**
   * Gets the value of the scriptErrorHandlingMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getScriptErrorHandlingMode() {
    return scriptErrorHandlingMode;
  }

  /**
   * Sets the value of the scriptErrorHandlingMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setScriptErrorHandlingMode(String value) {
    this.scriptErrorHandlingMode = value;
  }

  public boolean isSetScriptErrorHandlingMode() {
    return (this.scriptErrorHandlingMode != null);
  }

  /**
   * Gets the value of the daxOptimizationMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDaxOptimizationMode() {
    return daxOptimizationMode;
  }

  /**
   * Sets the value of the daxOptimizationMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDaxOptimizationMode(String value) {
    this.daxOptimizationMode = value;
  }

  public boolean isSetDaxOptimizationMode() {
    return (this.daxOptimizationMode != null);
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
   * Gets the value of the kpis property.
   * 
   * @return possible object is {@link Cube.Kpis }
   * 
   */
  public Cube.Kpis getKpis() {
    return kpis;
  }

  /**
   * Sets the value of the kpis property.
   * 
   * @param value allowed object is {@link Cube.Kpis }
   * 
   */
  public void setKpis(Cube.Kpis value) {
    this.kpis = value;
  }

  public boolean isSetKpis() {
    return (this.kpis != null);
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
   * Gets the value of the actions property.
   * 
   * @return possible object is {@link Cube.Actions }
   * 
   */
  public Cube.Actions getActions() {
    return actions;
  }

  /**
   * Sets the value of the actions property.
   * 
   * @param value allowed object is {@link Cube.Actions }
   * 
   */
  public void setActions(Cube.Actions value) {
    this.actions = value;
  }

  public boolean isSetActions() {
    return (this.actions != null);
  }

  /**
   * Gets the value of the storageLocation property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getStorageLocation() {
    return storageLocation;
  }

  /**
   * Sets the value of the storageLocation property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setStorageLocation(String value) {
    this.storageLocation = value;
  }

  public boolean isSetStorageLocation() {
    return (this.storageLocation != null);
  }

  /**
   * Gets the value of the estimatedRows property.
   * 
   * @return possible object is {@link Long }
   * 
   */
  public Long getEstimatedRows() {
    return estimatedRows;
  }

  /**
   * Sets the value of the estimatedRows property.
   * 
   * @param value allowed object is {@link Long }
   * 
   */
  public void setEstimatedRows(Long value) {
    this.estimatedRows = value;
  }

  public boolean isSetEstimatedRows() {
    return (this.estimatedRows != null);
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
   *         &lt;element name="Action" type="{urn:schemas-microsoft-com:xml-analysis}Action" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "action" })
  public static class Actions {

    @XmlElement(name = "Action")
    protected List<Action> action;

    /**
     * Gets the value of the action property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the action property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Action }
     * 
     * 
     */
    public List<Action> getAction() {
      if (action == null) {
        action = new ArrayList<Action>();
      }
      return this.action;
    }

    public boolean isSetAction() {
      return ((this.action != null) && (!this.action.isEmpty()));
    }

    public void unsetAction() {
      this.action = null;
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
   *         &lt;element name="CubePermission" type="{urn:schemas-microsoft-com:xml-analysis}CubePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "cubePermission" })
  public static class CubePermissions {

    @XmlElement(name = "CubePermission")
    protected List<CubePermission> cubePermission;

    /**
     * Gets the value of the cubePermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the cubePermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getCubePermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CubePermission }
     * 
     * 
     */
    public List<CubePermission> getCubePermission() {
      if (cubePermission == null) {
        cubePermission = new ArrayList<CubePermission>();
      }
      return this.cubePermission;
    }

    public boolean isSetCubePermission() {
      return ((this.cubePermission != null) && (!this.cubePermission.isEmpty()));
    }

    public void unsetCubePermission() {
      this.cubePermission = null;
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
   *         &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}CubeDimension" maxOccurs="unbounded"/&gt;
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

    @XmlElement(name = "Dimension", required = true)
    protected List<CubeDimension> dimension;

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
     * Objects of the following type(s) are allowed in the list {@link CubeDimension
     * }
     * 
     * 
     */
    public List<CubeDimension> getDimension() {
      if (dimension == null) {
        dimension = new ArrayList<CubeDimension>();
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
   *         &lt;element name="Kpi" type="{urn:schemas-microsoft-com:xml-analysis}Kpi" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "kpi" })
  public static class Kpis {

    @XmlElement(name = "Kpi")
    protected List<Kpi> kpi;

    /**
     * Gets the value of the kpi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the kpi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getKpi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Kpi }
     * 
     * 
     */
    public List<Kpi> getKpi() {
      if (kpi == null) {
        kpi = new ArrayList<Kpi>();
      }
      return this.kpi;
    }

    public boolean isSetKpi() {
      return ((this.kpi != null) && (!this.kpi.isEmpty()));
    }

    public void unsetKpi() {
      this.kpi = null;
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
   *         &lt;element name="MdxScript" type="{urn:schemas-microsoft-com:xml-analysis}MdxScript" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "mdxScript" })
  public static class MdxScripts {

    @XmlElement(name = "MdxScript")
    protected List<MdxScript> mdxScript;

    /**
     * Gets the value of the mdxScript property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the mdxScript property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMdxScript().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link MdxScript }
     * 
     * 
     */
    public List<MdxScript> getMdxScript() {
      if (mdxScript == null) {
        mdxScript = new ArrayList<MdxScript>();
      }
      return this.mdxScript;
    }

    public boolean isSetMdxScript() {
      return ((this.mdxScript != null) && (!this.mdxScript.isEmpty()));
    }

    public void unsetMdxScript() {
      this.mdxScript = null;
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
   *         &lt;element name="MeasureGroup" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "measureGroup" })
  public static class MeasureGroups {

    @XmlElement(name = "MeasureGroup")
    protected List<MeasureGroup> measureGroup;

    /**
     * Gets the value of the measureGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the measureGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMeasureGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link MeasureGroup
     * }
     * 
     * 
     */
    public List<MeasureGroup> getMeasureGroup() {
      if (measureGroup == null) {
        measureGroup = new ArrayList<MeasureGroup>();
      }
      return this.measureGroup;
    }

    public boolean isSetMeasureGroup() {
      return ((this.measureGroup != null) && (!this.measureGroup.isEmpty()));
    }

    public void unsetMeasureGroup() {
      this.measureGroup = null;
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
   *         &lt;element name="Perspective" type="{urn:schemas-microsoft-com:xml-analysis}Perspective" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "perspective" })
  public static class Perspectives {

    @XmlElement(name = "Perspective")
    protected List<Perspective> perspective;

    /**
     * Gets the value of the perspective property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the perspective property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getPerspective().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Perspective }
     * 
     * 
     */
    public List<Perspective> getPerspective() {
      if (perspective == null) {
        perspective = new ArrayList<Perspective>();
      }
      return this.perspective;
    }

    public boolean isSetPerspective() {
      return ((this.perspective != null) && (!this.perspective.isEmpty()));
    }

    public void unsetPerspective() {
      this.perspective = null;
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
   *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;CubeStorageModeEnumType"&gt;
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
  public static class StorageMode {

    @XmlValue
    protected CubeStorageModeEnumType value;
    @XmlAttribute(name = "valuens")
    protected String valuens;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link CubeStorageModeEnumType }
     * 
     */
    public CubeStorageModeEnumType getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link CubeStorageModeEnumType }
     * 
     */
    public void setValue(CubeStorageModeEnumType value) {
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
