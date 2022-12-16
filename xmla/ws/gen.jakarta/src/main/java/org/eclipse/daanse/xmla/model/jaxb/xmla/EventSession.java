
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


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
 *         &lt;element name="templateCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="templateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="templateDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="event" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="maxMemory" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" default="4" /&gt;
 *       &lt;attribute name="eventRetentionMode" type="{urn:schemas-microsoft-com:xml-analysis}retentionModes" default="allowSingleEventLoss" /&gt;
 *       &lt;attribute name="dispatchLatency" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" default="30" /&gt;
 *       &lt;attribute name="maxEventSize" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" default="0" /&gt;
 *       &lt;attribute name="memoryPartitionMode" type="{urn:schemas-microsoft-com:xml-analysis}partitionModes" default="none" /&gt;
 *       &lt;attribute name="trackCausality" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "templateCategory",
    "templateName",
    "templateDescription",
    "event",
    "target"
})
@XmlRootElement(name = "event_session")
public class EventSession {

    protected String templateCategory;
    protected String templateName;
    protected String templateDescription;
    protected List<java.lang.Object> event;
    protected List<java.lang.Object> target;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "maxMemory")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger maxMemory;
    @XmlAttribute(name = "eventRetentionMode")
    protected RetentionModes eventRetentionMode;
    @XmlAttribute(name = "dispatchLatency")
    @XmlSchemaType(name = "unsignedInt")
    protected Long dispatchLatency;
    @XmlAttribute(name = "maxEventSize")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxEventSize;
    @XmlAttribute(name = "memoryPartitionMode")
    protected PartitionModes memoryPartitionMode;
    @XmlAttribute(name = "trackCausality")
    protected Boolean trackCausality;

    /**
     * Gets the value of the templateCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateCategory() {
        return templateCategory;
    }

    /**
     * Sets the value of the templateCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateCategory(String value) {
        this.templateCategory = value;
    }

    /**
     * Gets the value of the templateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Sets the value of the templateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateName(String value) {
        this.templateName = value;
    }

    /**
     * Gets the value of the templateDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateDescription() {
        return templateDescription;
    }

    /**
     * Sets the value of the templateDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateDescription(String value) {
        this.templateDescription = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the event property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link java.lang.Object }
     * 
     * 
     */
    public List<java.lang.Object> getEvent() {
        if (event == null) {
            event = new ArrayList<java.lang.Object>();
        }
        return this.event;
    }

    /**
     * Gets the value of the target property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the target property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link java.lang.Object }
     * 
     * 
     */
    public List<java.lang.Object> getTarget() {
        if (target == null) {
            target = new ArrayList<java.lang.Object>();
        }
        return this.target;
    }

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
     * Gets the value of the maxMemory property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxMemory() {
        if (maxMemory == null) {
            return new BigInteger("4");
        } else {
            return maxMemory;
        }
    }

    /**
     * Sets the value of the maxMemory property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxMemory(BigInteger value) {
        this.maxMemory = value;
    }

    /**
     * Gets the value of the eventRetentionMode property.
     * 
     * @return
     *     possible object is
     *     {@link RetentionModes }
     *     
     */
    public RetentionModes getEventRetentionMode() {
        if (eventRetentionMode == null) {
            return RetentionModes.ALLOW_SINGLE_EVENT_LOSS;
        } else {
            return eventRetentionMode;
        }
    }

    /**
     * Sets the value of the eventRetentionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetentionModes }
     *     
     */
    public void setEventRetentionMode(RetentionModes value) {
        this.eventRetentionMode = value;
    }

    /**
     * Gets the value of the dispatchLatency property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getDispatchLatency() {
        if (dispatchLatency == null) {
            return  30L;
        } else {
            return dispatchLatency;
        }
    }

    /**
     * Sets the value of the dispatchLatency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDispatchLatency(Long value) {
        this.dispatchLatency = value;
    }

    /**
     * Gets the value of the maxEventSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getMaxEventSize() {
        if (maxEventSize == null) {
            return  0L;
        } else {
            return maxEventSize;
        }
    }

    /**
     * Sets the value of the maxEventSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxEventSize(Long value) {
        this.maxEventSize = value;
    }

    /**
     * Gets the value of the memoryPartitionMode property.
     * 
     * @return
     *     possible object is
     *     {@link PartitionModes }
     *     
     */
    public PartitionModes getMemoryPartitionMode() {
        if (memoryPartitionMode == null) {
            return PartitionModes.NONE;
        } else {
            return memoryPartitionMode;
        }
    }

    /**
     * Sets the value of the memoryPartitionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartitionModes }
     *     
     */
    public void setMemoryPartitionMode(PartitionModes value) {
        this.memoryPartitionMode = value;
    }

    /**
     * Gets the value of the trackCausality property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isTrackCausality() {
        if (trackCausality == null) {
            return false;
        } else {
            return trackCausality;
        }
    }

    /**
     * Sets the value of the trackCausality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTrackCausality(Boolean value) {
        this.trackCausality = value;
    }

}
