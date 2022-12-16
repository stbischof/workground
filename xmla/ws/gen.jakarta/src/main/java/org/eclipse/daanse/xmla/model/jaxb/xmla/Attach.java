
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Attach complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Attach"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Folder" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AllowOverwrite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100}ReadWriteMode" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attach", propOrder = {

})
public class Attach {

    @XmlElement(name = "Folder", required = true)
    protected String folder;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "AllowOverwrite")
    protected Boolean allowOverwrite;
    @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100")
    protected String readWriteMode;

    /**
     * Gets the value of the folder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Sets the value of the folder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolder(String value) {
        this.folder = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the allowOverwrite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowOverwrite() {
        return allowOverwrite;
    }

    /**
     * Sets the value of the allowOverwrite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowOverwrite(Boolean value) {
        this.allowOverwrite = value;
    }

    /**
     * Gets the value of the readWriteMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadWriteMode() {
        return readWriteMode;
    }

    /**
     * Sets the value of the readWriteMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadWriteMode(String value) {
        this.readWriteMode = value;
    }

}
