
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServerProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServerProperty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RequiresRestart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="PendingValue" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/&gt;
 *         &lt;element name="DefaultValue" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/&gt;
 *         &lt;element name="DisplayFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerProperty", propOrder = {

})
public class ServerProperty {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "RequiresRestart")
    protected Boolean requiresRestart;
    @XmlElement(name = "PendingValue")
    protected java.lang.Object pendingValue;
    @XmlElement(name = "DefaultValue")
    protected java.lang.Object defaultValue;
    @XmlElement(name = "DisplayFlag")
    protected Boolean displayFlag;
    @XmlElement(name = "Type")
    protected String type;

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
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Gets the value of the requiresRestart property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiresRestart() {
        return requiresRestart;
    }

    /**
     * Sets the value of the requiresRestart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiresRestart(Boolean value) {
        this.requiresRestart = value;
    }

    public boolean isSetRequiresRestart() {
        return (this.requiresRestart!= null);
    }

    /**
     * Gets the value of the pendingValue property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Object }
     *     
     */
    public java.lang.Object getPendingValue() {
        return pendingValue;
    }

    /**
     * Sets the value of the pendingValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Object }
     *     
     */
    public void setPendingValue(java.lang.Object value) {
        this.pendingValue = value;
    }

    public boolean isSetPendingValue() {
        return (this.pendingValue!= null);
    }

    /**
     * Gets the value of the defaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Object }
     *     
     */
    public java.lang.Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Object }
     *     
     */
    public void setDefaultValue(java.lang.Object value) {
        this.defaultValue = value;
    }

    public boolean isSetDefaultValue() {
        return (this.defaultValue!= null);
    }

    /**
     * Gets the value of the displayFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisplayFlag() {
        return displayFlag;
    }

    /**
     * Sets the value of the displayFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisplayFlag(Boolean value) {
        this.displayFlag = value;
    }

    public boolean isSetDisplayFlag() {
        return (this.displayFlag!= null);
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

    public boolean isSetType() {
        return (this.type!= null);
    }

}
