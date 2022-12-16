
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trace"&gt;
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
 *         &lt;element name="LogFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LogFileAppend" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="LogFileSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Audit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="LogFileRollover" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AutoRestart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="StopTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Filter" type="{urn:schemas-microsoft-com:xml-analysis}TraceFilter" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis}EventType"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trace", propOrder = {

})
public class Trace {

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
    protected Trace.Annotations annotations;
    @XmlElement(name = "LogFileName")
    protected String logFileName;
    @XmlElement(name = "LogFileAppend")
    protected Boolean logFileAppend;
    @XmlElement(name = "LogFileSize")
    protected Long logFileSize;
    @XmlElement(name = "Audit")
    protected Boolean audit;
    @XmlElement(name = "LogFileRollover")
    protected Boolean logFileRollover;
    @XmlElement(name = "AutoRestart")
    protected Boolean autoRestart;
    @XmlElement(name = "StopTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar stopTime;
    @XmlElement(name = "Filter")
    protected TraceFilter filter;
    @XmlElement(name = "EventType", namespace = "urn:schemas-microsoft-com:xml-analysis", required = true)
    protected EventType eventType;

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
     *     {@link Trace.Annotations }
     *     
     */
    public Trace.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Trace.Annotations }
     *     
     */
    public void setAnnotations(Trace.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the logFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogFileName() {
        return logFileName;
    }

    /**
     * Sets the value of the logFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogFileName(String value) {
        this.logFileName = value;
    }

    /**
     * Gets the value of the logFileAppend property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLogFileAppend() {
        return logFileAppend;
    }

    /**
     * Sets the value of the logFileAppend property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLogFileAppend(Boolean value) {
        this.logFileAppend = value;
    }

    /**
     * Gets the value of the logFileSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLogFileSize() {
        return logFileSize;
    }

    /**
     * Sets the value of the logFileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLogFileSize(Long value) {
        this.logFileSize = value;
    }

    /**
     * Gets the value of the audit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAudit() {
        return audit;
    }

    /**
     * Sets the value of the audit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAudit(Boolean value) {
        this.audit = value;
    }

    /**
     * Gets the value of the logFileRollover property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLogFileRollover() {
        return logFileRollover;
    }

    /**
     * Sets the value of the logFileRollover property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLogFileRollover(Boolean value) {
        this.logFileRollover = value;
    }

    /**
     * Gets the value of the autoRestart property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoRestart() {
        return autoRestart;
    }

    /**
     * Sets the value of the autoRestart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoRestart(Boolean value) {
        this.autoRestart = value;
    }

    /**
     * Gets the value of the stopTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStopTime() {
        return stopTime;
    }

    /**
     * Sets the value of the stopTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStopTime(XMLGregorianCalendar value) {
        this.stopTime = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link TraceFilter }
     *     
     */
    public TraceFilter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link TraceFilter }
     *     
     */
    public void setFilter(TraceFilter value) {
        this.filter = value;
    }

    /**
     * Gets the value of the eventType property.
     * 
     * @return
     *     possible object is
     *     {@link EventType }
     *     
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventType }
     *     
     */
    public void setEventType(EventType value) {
        this.eventType = value;
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

}
