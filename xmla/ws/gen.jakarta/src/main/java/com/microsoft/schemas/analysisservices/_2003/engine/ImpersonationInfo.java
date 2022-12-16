
package com.microsoft.schemas.analysisservices._2003.engine;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImpersonationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImpersonationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ImpersonationMode"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="ImpersonateServiceAccount"/&gt;
 *               &lt;enumeration value="ImpersonateAnonymous"/&gt;
 *               &lt;enumeration value="ImpersonateCurrentUser"/&gt;
 *               &lt;enumeration value="ImpersonateAccount"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Account" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ImpersonationInfoSecurity" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="PasswordRemoved"/&gt;
 *               &lt;enumeration value="Unchanged"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
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
@XmlType(name = "ImpersonationInfo", propOrder = {

})
public class ImpersonationInfo {

    @XmlElement(name = "ImpersonationMode", required = true)
    protected String impersonationMode;
    @XmlElement(name = "Account")
    protected String account;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "ImpersonationInfoSecurity")
    protected String impersonationInfoSecurity;

    /**
     * Gets the value of the impersonationMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpersonationMode() {
        return impersonationMode;
    }

    /**
     * Sets the value of the impersonationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpersonationMode(String value) {
        this.impersonationMode = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
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
     * Gets the value of the impersonationInfoSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpersonationInfoSecurity() {
        return impersonationInfoSecurity;
    }

    /**
     * Sets the value of the impersonationInfoSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpersonationInfoSecurity(String value) {
        this.impersonationInfoSecurity = value;
    }

}
