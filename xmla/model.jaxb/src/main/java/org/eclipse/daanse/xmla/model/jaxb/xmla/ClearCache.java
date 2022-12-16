
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ClearCache complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ClearCache"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClearCache", propOrder = {

})
public class ClearCache {

  @XmlElement(name = "Object", required = true)
  protected ObjectReference object;

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

}
