
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SetAuthContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetAuthContext"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}Token"/&gt;
 *         &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetAuthContext", propOrder = {

})
public class SetAuthContext {

    @XmlElement(name = "Token", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", required = true)
    protected String token;
    @XmlElement(name = "DatabaseID", required = true)
    protected String databaseID;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    public boolean isSetToken() {
        return (this.token!= null);
    }

    /**
     * Gets the value of the databaseID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseID() {
        return databaseID;
    }

    /**
     * Sets the value of the databaseID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseID(String value) {
        this.databaseID = value;
    }

    public boolean isSetDatabaseID() {
        return (this.databaseID!= null);
    }

}
