
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
 * <p>Java class for MeasureGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MeasureGroup"&gt;
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
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
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
 *         &lt;element name="Type" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="ExchangeRate"/&gt;
 *               &lt;enumeration value="Sales"/&gt;
 *               &lt;enumeration value="Budget"/&gt;
 *               &lt;enumeration value="FinancialReporting"/&gt;
 *               &lt;enumeration value="Marketing"/&gt;
 *               &lt;enumeration value="Inventory"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
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
 *         &lt;element name="Measures"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Measure" type="{urn:schemas-microsoft-com:xml-analysis}Measure" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DataAggregation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="DataAggregatable"/&gt;
 *               &lt;enumeration value="CacheAggregatable"/&gt;
 *               &lt;enumeration value="DataAndCacheAggregatable"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroupBinding" minOccurs="0"/&gt;
 *         &lt;element name="StorageMode" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;MeasureGroupStorageModeEnumType"&gt;
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
 *         &lt;element name="StorageLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IgnoreUnrelatedDimensions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ProactiveCaching" type="{urn:schemas-microsoft-com:xml-analysis}ProactiveCaching" minOccurs="0"/&gt;
 *         &lt;element name="EstimatedRows" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="EstimatedSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="LazyAggregations"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Dimensions"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroupDimension" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Partitions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Partition" type="{urn:schemas-microsoft-com:xml-analysis}Partition" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AggregationPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingPriority" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="AggregationDesigns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AggregationDesign" type="{urn:schemas-microsoft-com:xml-analysis}AggregationDesign" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasureGroup", propOrder = {

})
public class MeasureGroup {

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
    protected MeasureGroup.Annotations annotations;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "Translations")
    protected MeasureGroup.Translations translations;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "Measures", required = true)
    protected MeasureGroup.Measures measures;
    @XmlElement(name = "DataAggregation")
    protected String dataAggregation;
    @XmlElement(name = "Source")
    protected MeasureGroupBinding source;
    @XmlElement(name = "StorageMode")
    protected MeasureGroup.StorageMode storageMode;
    @XmlElement(name = "StorageLocation")
    protected String storageLocation;
    @XmlElement(name = "IgnoreUnrelatedDimensions")
    protected Boolean ignoreUnrelatedDimensions;
    @XmlElement(name = "ProactiveCaching")
    protected ProactiveCaching proactiveCaching;
    @XmlElement(name = "EstimatedRows")
    protected Long estimatedRows;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "EstimatedSize")
    protected Long estimatedSize;
    @XmlElement(name = "ProcessingMode")
    protected String processingMode;
    @XmlElement(name = "Dimensions", required = true)
    protected MeasureGroup.Dimensions dimensions;
    @XmlElement(name = "Partitions")
    protected MeasureGroup.Partitions partitions;
    @XmlElement(name = "AggregationPrefix")
    protected String aggregationPrefix;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "AggregationDesigns")
    protected MeasureGroup.AggregationDesigns aggregationDesigns;

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

    /**
     * Gets the value of the createdTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the value of the createdTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTimestamp(XMLGregorianCalendar value) {
        this.createdTimestamp = value;
    }

    /**
     * Gets the value of the lastSchemaUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    /**
     * Sets the value of the lastSchemaUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSchemaUpdate(XMLGregorianCalendar value) {
        this.lastSchemaUpdate = value;
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

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.Annotations }
     *     
     */
    public MeasureGroup.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.Annotations }
     *     
     */
    public void setAnnotations(MeasureGroup.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the lastProcessed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    /**
     * Sets the value of the lastProcessed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.Translations }
     *     
     */
    public MeasureGroup.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.Translations }
     *     
     */
    public void setTranslations(MeasureGroup.Translations value) {
        this.translations = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the measures property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.Measures }
     *     
     */
    public MeasureGroup.Measures getMeasures() {
        return measures;
    }

    /**
     * Sets the value of the measures property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.Measures }
     *     
     */
    public void setMeasures(MeasureGroup.Measures value) {
        this.measures = value;
    }

    /**
     * Gets the value of the dataAggregation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAggregation() {
        return dataAggregation;
    }

    /**
     * Sets the value of the dataAggregation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAggregation(String value) {
        this.dataAggregation = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroupBinding }
     *     
     */
    public MeasureGroupBinding getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroupBinding }
     *     
     */
    public void setSource(MeasureGroupBinding value) {
        this.source = value;
    }

    /**
     * Gets the value of the storageMode property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.StorageMode }
     *     
     */
    public MeasureGroup.StorageMode getStorageMode() {
        return storageMode;
    }

    /**
     * Sets the value of the storageMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.StorageMode }
     *     
     */
    public void setStorageMode(MeasureGroup.StorageMode value) {
        this.storageMode = value;
    }

    /**
     * Gets the value of the storageLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStorageLocation() {
        return storageLocation;
    }

    /**
     * Sets the value of the storageLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStorageLocation(String value) {
        this.storageLocation = value;
    }

    /**
     * Gets the value of the ignoreUnrelatedDimensions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIgnoreUnrelatedDimensions() {
        return ignoreUnrelatedDimensions;
    }

    /**
     * Sets the value of the ignoreUnrelatedDimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreUnrelatedDimensions(Boolean value) {
        this.ignoreUnrelatedDimensions = value;
    }

    /**
     * Gets the value of the proactiveCaching property.
     * 
     * @return
     *     possible object is
     *     {@link ProactiveCaching }
     *     
     */
    public ProactiveCaching getProactiveCaching() {
        return proactiveCaching;
    }

    /**
     * Sets the value of the proactiveCaching property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProactiveCaching }
     *     
     */
    public void setProactiveCaching(ProactiveCaching value) {
        this.proactiveCaching = value;
    }

    /**
     * Gets the value of the estimatedRows property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstimatedRows() {
        return estimatedRows;
    }

    /**
     * Sets the value of the estimatedRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstimatedRows(Long value) {
        this.estimatedRows = value;
    }

    /**
     * Gets the value of the errorConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorConfiguration }
     *     
     */
    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    /**
     * Sets the value of the errorConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorConfiguration }
     *     
     */
    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    /**
     * Gets the value of the estimatedSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstimatedSize() {
        return estimatedSize;
    }

    /**
     * Sets the value of the estimatedSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstimatedSize(Long value) {
        this.estimatedSize = value;
    }

    /**
     * Gets the value of the processingMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingMode() {
        return processingMode;
    }

    /**
     * Sets the value of the processingMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingMode(String value) {
        this.processingMode = value;
    }

    /**
     * Gets the value of the dimensions property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.Dimensions }
     *     
     */
    public MeasureGroup.Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Sets the value of the dimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.Dimensions }
     *     
     */
    public void setDimensions(MeasureGroup.Dimensions value) {
        this.dimensions = value;
    }

    /**
     * Gets the value of the partitions property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.Partitions }
     *     
     */
    public MeasureGroup.Partitions getPartitions() {
        return partitions;
    }

    /**
     * Sets the value of the partitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.Partitions }
     *     
     */
    public void setPartitions(MeasureGroup.Partitions value) {
        this.partitions = value;
    }

    /**
     * Gets the value of the aggregationPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggregationPrefix() {
        return aggregationPrefix;
    }

    /**
     * Sets the value of the aggregationPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggregationPrefix(String value) {
        this.aggregationPrefix = value;
    }

    /**
     * Gets the value of the processingPriority property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getProcessingPriority() {
        return processingPriority;
    }

    /**
     * Sets the value of the processingPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setProcessingPriority(BigInteger value) {
        this.processingPriority = value;
    }

    /**
     * Gets the value of the aggregationDesigns property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup.AggregationDesigns }
     *     
     */
    public MeasureGroup.AggregationDesigns getAggregationDesigns() {
        return aggregationDesigns;
    }

    /**
     * Sets the value of the aggregationDesigns property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup.AggregationDesigns }
     *     
     */
    public void setAggregationDesigns(MeasureGroup.AggregationDesigns value) {
        this.aggregationDesigns = value;
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
     *         &lt;element name="AggregationDesign" type="{urn:schemas-microsoft-com:xml-analysis}AggregationDesign" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "aggregationDesign"
    })
    public static class AggregationDesigns {

        @XmlElement(name = "AggregationDesign")
        protected List<AggregationDesign> aggregationDesign;

        /**
         * Gets the value of the aggregationDesign property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the aggregationDesign property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAggregationDesign().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AggregationDesign }
         * 
         * 
         */
        public List<AggregationDesign> getAggregationDesign() {
            if (aggregationDesign == null) {
                aggregationDesign = new ArrayList<AggregationDesign>();
            }
            return this.aggregationDesign;
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
     *         &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroupDimension" maxOccurs="unbounded"/&gt;
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
        "dimension"
    })
    public static class Dimensions {

        @XmlElement(name = "Dimension", required = true)
        protected List<MeasureGroupDimension> dimension;

        /**
         * Gets the value of the dimension property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the dimension property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDimension().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MeasureGroupDimension }
         * 
         * 
         */
        public List<MeasureGroupDimension> getDimension() {
            if (dimension == null) {
                dimension = new ArrayList<MeasureGroupDimension>();
            }
            return this.dimension;
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
     *         &lt;element name="Measure" type="{urn:schemas-microsoft-com:xml-analysis}Measure" maxOccurs="unbounded"/&gt;
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
        "measure"
    })
    public static class Measures {

        @XmlElement(name = "Measure", required = true)
        protected List<Measure> measure;

        /**
         * Gets the value of the measure property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the measure property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMeasure().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Measure }
         * 
         * 
         */
        public List<Measure> getMeasure() {
            if (measure == null) {
                measure = new ArrayList<Measure>();
            }
            return this.measure;
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
     *         &lt;element name="Partition" type="{urn:schemas-microsoft-com:xml-analysis}Partition" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "partition"
    })
    public static class Partitions {

        @XmlElement(name = "Partition")
        protected List<Partition> partition;

        /**
         * Gets the value of the partition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the partition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPartition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Partition }
         * 
         * 
         */
        public List<Partition> getPartition() {
            if (partition == null) {
                partition = new ArrayList<Partition>();
            }
            return this.partition;
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
     *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;MeasureGroupStorageModeEnumType"&gt;
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
    public static class StorageMode {

        @XmlValue
        protected MeasureGroupStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link MeasureGroupStorageModeEnumType }
         *     
         */
        public MeasureGroupStorageModeEnumType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link MeasureGroupStorageModeEnumType }
         *     
         */
        public void setValue(MeasureGroupStorageModeEnumType value) {
            this.value = value;
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
    @XmlType(name = "", propOrder = {
        "translation"
    })
    public static class Translations {

        @XmlElement(name = "Translation")
        protected List<Translation> translation;

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
         * {@link Translation }
         * 
         * 
         */
        public List<Translation> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<Translation>();
            }
            return this.translation;
        }

    }

}
