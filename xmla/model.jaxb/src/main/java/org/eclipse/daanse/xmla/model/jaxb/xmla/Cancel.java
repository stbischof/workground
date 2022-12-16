
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Cancel complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Cancel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ConnectionID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SPID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="CancelAssociated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cancel", propOrder = {

})
public class Cancel {

  @XmlElement(name = "ConnectionID")
  protected BigInteger connectionID;
  @XmlElement(name = "SessionID")
  protected String sessionID;
  @XmlElement(name = "SPID")
  protected BigInteger spid;
  @XmlElement(name = "CancelAssociated")
  protected Boolean cancelAssociated;

  /**
   * Gets the value of the connectionID property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getConnectionID() {
    return connectionID;
  }

  /**
   * Sets the value of the connectionID property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setConnectionID(BigInteger value) {
    this.connectionID = value;
  }

  public boolean isSetConnectionID() {
    return (this.connectionID != null);
  }

  /**
   * Gets the value of the sessionID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSessionID() {
    return sessionID;
  }

  /**
   * Sets the value of the sessionID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSessionID(String value) {
    this.sessionID = value;
  }

  public boolean isSetSessionID() {
    return (this.sessionID != null);
  }

  /**
   * Gets the value of the spid property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getSPID() {
    return spid;
  }

  /**
   * Sets the value of the spid property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setSPID(BigInteger value) {
    this.spid = value;
  }

  public boolean isSetSPID() {
    return (this.spid != null);
  }

  /**
   * Gets the value of the cancelAssociated property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isCancelAssociated() {
    return cancelAssociated;
  }

  /**
   * Sets the value of the cancelAssociated property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setCancelAssociated(Boolean value) {
    this.cancelAssociated = value;
  }

  public boolean isSetCancelAssociated() {
    return (this.cancelAssociated != null);
  }

}
