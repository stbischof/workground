
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Delete complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Delete"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="IgnoreFailures" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Delete", propOrder = {

})
public class Delete {

  @XmlElement(name = "Object", required = true)
  protected ObjectReference object;
  @XmlAttribute(name = "IgnoreFailures")
  protected Boolean ignoreFailures;

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
   * Gets the value of the ignoreFailures property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public boolean isIgnoreFailures() {
    return ignoreFailures;
  }

  /**
   * Sets the value of the ignoreFailures property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setIgnoreFailures(boolean value) {
    this.ignoreFailures = value;
  }

  public boolean isSetIgnoreFailures() {
    return (this.ignoreFailures != null);
  }

  public void unsetIgnoreFailures() {
    this.ignoreFailures = null;
  }

}
