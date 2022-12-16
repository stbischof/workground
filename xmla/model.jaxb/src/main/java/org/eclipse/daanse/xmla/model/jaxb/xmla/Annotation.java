
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Annotation complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Annotation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Visibility" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="SchemaRowset"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Annotation", propOrder = {

})
public class Annotation {

  @XmlElement(name = "Name", required = true)
  protected String name;
  @XmlElement(name = "Visibility")
  protected String visibility;
  @XmlElement(name = "Value")
  protected java.lang.Object value;

  /**
   * Gets the value of the name property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setName(String value) {
    this.name = value;
  }

  public boolean isSetName() {
    return (this.name != null);
  }

  /**
   * Gets the value of the visibility property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getVisibility() {
    return visibility;
  }

  /**
   * Sets the value of the visibility property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setVisibility(String value) {
    this.visibility = value;
  }

  public boolean isSetVisibility() {
    return (this.visibility != null);
  }

  /**
   * Gets the value of the value property.
   * 
   * @return possible object is {@link java.lang.Object }
   * 
   */
  public java.lang.Object getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   * 
   * @param value allowed object is {@link java.lang.Object }
   * 
   */
  public void setValue(java.lang.Object value) {
    this.value = value;
  }

  public boolean isSetValue() {
    return (this.value != null);
  }

}
