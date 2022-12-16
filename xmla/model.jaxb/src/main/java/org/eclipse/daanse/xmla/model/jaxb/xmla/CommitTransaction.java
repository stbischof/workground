
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CommitTransaction complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CommitTransaction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DurabilityGuarantee" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="LocalDisk"/&gt;
 *               &lt;enumeration value="Full"/&gt;
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
@XmlType(name = "CommitTransaction", propOrder = {

})
public class CommitTransaction {

  @XmlElement(name = "DurabilityGuarantee")
  protected String durabilityGuarantee;

  /**
   * Gets the value of the durabilityGuarantee property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDurabilityGuarantee() {
    return durabilityGuarantee;
  }

  /**
   * Sets the value of the durabilityGuarantee property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDurabilityGuarantee(String value) {
    this.durabilityGuarantee = value;
  }

  public boolean isSetDurabilityGuarantee() {
    return (this.durabilityGuarantee != null);
  }

}
