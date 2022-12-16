
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
 * <p>Java class for Partition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Partition"&gt;
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
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}TabularBinding" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingPriority" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="AggregationPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StorageMode" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;PartitionStorageModeEnumType"&gt;
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
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="StorageLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RemoteDatasourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Slice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProactiveCaching" type="{urn:schemas-microsoft-com:xml-analysis}ProactiveCaching" minOccurs="0"/&gt;
 *         &lt;element name="Type" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Data"/&gt;
 *               &lt;enumeration value="Writeback"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EstimatedSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="EstimatedRows" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="CurrentStorageMode" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;PartitionCurrentStorageModeEnumType"&gt;
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
 *         &lt;element name="AggregationDesignID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AggregationInstances" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AggregationInstance" type="{urn:schemas-microsoft-com:xml-analysis}AggregationInstance" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AggregationInstanceSource" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceViewBinding" minOccurs="0"/&gt;
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}StringStoresCompatibilityLevel" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}CurrentStringStoresCompatibilityLevel" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}DirectQueryUsage" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Partition", propOrder = {

})
public class Partition {

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
    protected Partition.Annotations annotations;
    @XmlElement(name = "Source")
    protected TabularBinding source;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "AggregationPrefix")
    protected String aggregationPrefix;
    @XmlElement(name = "StorageMode")
    protected Partition.StorageMode storageMode;
    @XmlElement(name = "ProcessingMode")
    protected String processingMode;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "StorageLocation")
    protected String storageLocation;
    @XmlElement(name = "RemoteDatasourceID")
    protected String remoteDatasourceID;
    @XmlElement(name = "Slice")
    protected String slice;
    @XmlElement(name = "ProactiveCaching")
    protected ProactiveCaching proactiveCaching;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "EstimatedSize")
    protected Long estimatedSize;
    @XmlElement(name = "EstimatedRows")
    protected Long estimatedRows;
    @XmlElement(name = "CurrentStorageMode")
    protected Partition.CurrentStorageMode currentStorageMode;
    @XmlElement(name = "AggregationDesignID")
    protected String aggregationDesignID;
    @XmlElement(name = "AggregationInstances")
    protected Partition.AggregationInstances aggregationInstances;
    @XmlElement(name = "AggregationInstanceSource")
    protected DataSourceViewBinding aggregationInstanceSource;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "StringStoresCompatibilityLevel", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected Integer stringStoresCompatibilityLevel;
    @XmlElement(name = "CurrentStringStoresCompatibilityLevel", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected Integer currentStringStoresCompatibilityLevel;
    @XmlElement(name = "DirectQueryUsage", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300")
    protected String directQueryUsage;

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
     *     {@link Partition.Annotations }
     *     
     */
    public Partition.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Partition.Annotations }
     *     
     */
    public void setAnnotations(Partition.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link TabularBinding }
     *     
     */
    public TabularBinding getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link TabularBinding }
     *     
     */
    public void setSource(TabularBinding value) {
        this.source = value;
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
     * Gets the value of the storageMode property.
     * 
     * @return
     *     possible object is
     *     {@link Partition.StorageMode }
     *     
     */
    public Partition.StorageMode getStorageMode() {
        return storageMode;
    }

    /**
     * Sets the value of the storageMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Partition.StorageMode }
     *     
     */
    public void setStorageMode(Partition.StorageMode value) {
        this.storageMode = value;
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
     * Gets the value of the remoteDatasourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteDatasourceID() {
        return remoteDatasourceID;
    }

    /**
     * Sets the value of the remoteDatasourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteDatasourceID(String value) {
        this.remoteDatasourceID = value;
    }

    /**
     * Gets the value of the slice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlice() {
        return slice;
    }

    /**
     * Sets the value of the slice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlice(String value) {
        this.slice = value;
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
     * Gets the value of the currentStorageMode property.
     * 
     * @return
     *     possible object is
     *     {@link Partition.CurrentStorageMode }
     *     
     */
    public Partition.CurrentStorageMode getCurrentStorageMode() {
        return currentStorageMode;
    }

    /**
     * Sets the value of the currentStorageMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Partition.CurrentStorageMode }
     *     
     */
    public void setCurrentStorageMode(Partition.CurrentStorageMode value) {
        this.currentStorageMode = value;
    }

    /**
     * Gets the value of the aggregationDesignID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggregationDesignID() {
        return aggregationDesignID;
    }

    /**
     * Sets the value of the aggregationDesignID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggregationDesignID(String value) {
        this.aggregationDesignID = value;
    }

    /**
     * Gets the value of the aggregationInstances property.
     * 
     * @return
     *     possible object is
     *     {@link Partition.AggregationInstances }
     *     
     */
    public Partition.AggregationInstances getAggregationInstances() {
        return aggregationInstances;
    }

    /**
     * Sets the value of the aggregationInstances property.
     * 
     * @param value
     *     allowed object is
     *     {@link Partition.AggregationInstances }
     *     
     */
    public void setAggregationInstances(Partition.AggregationInstances value) {
        this.aggregationInstances = value;
    }

    /**
     * Gets the value of the aggregationInstanceSource property.
     * 
     * @return
     *     possible object is
     *     {@link DataSourceViewBinding }
     *     
     */
    public DataSourceViewBinding getAggregationInstanceSource() {
        return aggregationInstanceSource;
    }

    /**
     * Sets the value of the aggregationInstanceSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSourceViewBinding }
     *     
     */
    public void setAggregationInstanceSource(DataSourceViewBinding value) {
        this.aggregationInstanceSource = value;
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
     * Gets the value of the stringStoresCompatibilityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStringStoresCompatibilityLevel() {
        return stringStoresCompatibilityLevel;
    }

    /**
     * Sets the value of the stringStoresCompatibilityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStringStoresCompatibilityLevel(Integer value) {
        this.stringStoresCompatibilityLevel = value;
    }

    /**
     * Gets the value of the currentStringStoresCompatibilityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCurrentStringStoresCompatibilityLevel() {
        return currentStringStoresCompatibilityLevel;
    }

    /**
     * Sets the value of the currentStringStoresCompatibilityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCurrentStringStoresCompatibilityLevel(Integer value) {
        this.currentStringStoresCompatibilityLevel = value;
    }

    /**
     * Gets the value of the directQueryUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectQueryUsage() {
        return directQueryUsage;
    }

    /**
     * Sets the value of the directQueryUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectQueryUsage(String value) {
        this.directQueryUsage = value;
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
     *         &lt;element name="AggregationInstance" type="{urn:schemas-microsoft-com:xml-analysis}AggregationInstance" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "aggregationInstance"
    })
    public static class AggregationInstances {

        @XmlElement(name = "AggregationInstance")
        protected List<AggregationInstance> aggregationInstance;

        /**
         * Gets the value of the aggregationInstance property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the aggregationInstance property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAggregationInstance().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AggregationInstance }
         * 
         * 
         */
        public List<AggregationInstance> getAggregationInstance() {
            if (aggregationInstance == null) {
                aggregationInstance = new ArrayList<AggregationInstance>();
            }
            return this.aggregationInstance;
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
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;PartitionCurrentStorageModeEnumType"&gt;
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
    public static class CurrentStorageMode {

        @XmlValue
        protected PartitionCurrentStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link PartitionCurrentStorageModeEnumType }
         *     
         */
        public PartitionCurrentStorageModeEnumType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link PartitionCurrentStorageModeEnumType }
         *     
         */
        public void setValue(PartitionCurrentStorageModeEnumType value) {
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
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:schemas-microsoft-com:xml-analysis&gt;PartitionStorageModeEnumType"&gt;
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
        protected PartitionStorageModeEnumType value;
        @XmlAttribute(name = "valuens")
        protected String valuens;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link PartitionStorageModeEnumType }
         *     
         */
        public PartitionStorageModeEnumType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link PartitionStorageModeEnumType }
         *     
         */
        public void setValue(PartitionStorageModeEnumType value) {
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

}
