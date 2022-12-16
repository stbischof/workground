
package org.eclipse.daanse.xmla.model.jaxb.xmla_exception;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Location" type="{urn:schemas-microsoft-com:xml-analysis:exception}MessageLocation" minOccurs="0"/&gt;
 *         &lt;element name="Callstack" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Source" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="HelpFile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorType", propOrder = {

})
public class ErrorType {

    @XmlElement(name = "Location")
    protected MessageLocation location;
    @XmlElement(name = "Callstack")
    protected String callstack;
    @XmlAttribute(name = "ErrorCode")
    protected Long errorCode;
    @XmlAttribute(name = "Description")
    protected String description;
    @XmlAttribute(name = "Source")
    protected String source;
    @XmlAttribute(name = "HelpFile")
    protected String helpFile;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link MessageLocation }
     *     
     */
    public MessageLocation getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageLocation }
     *     
     */
    public void setLocation(MessageLocation value) {
        this.location = value;
    }

    /**
     * Gets the value of the callstack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallstack() {
        return callstack;
    }

    /**
     * Sets the value of the callstack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallstack(String value) {
        this.callstack = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setErrorCode(Long value) {
        this.errorCode = value;
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
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the helpFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHelpFile() {
        return helpFile;
    }

    /**
     * Sets the value of the helpFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHelpFile(String value) {
        this.helpFile = value;
    }

}
