
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregationDesignAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregationDesignAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="EstimatedCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregationDesignAttribute", propOrder = {

})
public class AggregationDesignAttribute {

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "EstimatedCount")
    protected Long estimatedCount;

    /**
     * Gets the value of the attributeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeID() {
        return attributeID;
    }

    /**
     * Sets the value of the attributeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeID(String value) {
        this.attributeID = value;
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

}
