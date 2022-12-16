
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Detach complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Detach"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Detach", propOrder = {

})
public class Detach {

  @XmlElement(name = "Object", required = true)
  protected ObjectReference object;
  @XmlElement(name = "Password")
  protected String password;

  /**
   * Gets the value of the object property.
   * 
   * @return possible object is {@link ObjectReference }
   * 
   */
  public ObjectReference getObject() {
    return object;
  }

  /**
   * Sets the value of the object property.
   * 
   * @param value allowed object is {@link ObjectReference }
   * 
   */
  public void setObject(ObjectReference value) {
    this.object = value;
  }

  public boolean isSetObject() {
    return (this.object != null);
  }

  /**
   * Gets the value of the password property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the value of the password property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPassword(String value) {
    this.password = value;
  }

  public boolean isSetPassword() {
    return (this.password != null);
  }

}
