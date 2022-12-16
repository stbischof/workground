
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Alter complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Alter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference" minOccurs="0"/&gt;
 *         &lt;element name="ObjectDefinition" type="{urn:schemas-microsoft-com:xml-analysis}MajorObject"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="Scope" type="{urn:schemas-microsoft-com:xml-analysis}Scope" /&gt;
 *       &lt;attribute name="AllowCreate" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="ObjectExpansion" type="{urn:schemas-microsoft-com:xml-analysis}ObjectExpansion" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Alter", propOrder = {

})
public class Alter {

  @XmlElement(name = "Object")
  protected ObjectReference object;
  @XmlElement(name = "ObjectDefinition", required = true)
  protected MajorObject objectDefinition;
  @XmlAttribute(name = "Scope")
  protected Scope scope;
  @XmlAttribute(name = "AllowCreate")
  protected Boolean allowCreate;
  @XmlAttribute(name = "ObjectExpansion")
  protected ObjectExpansion objectExpansion;

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
   * Gets the value of the objectDefinition property.
   * 
   * @return possible object is {@link MajorObject }
   * 
   */
  public MajorObject getObjectDefinition() {
    return objectDefinition;
  }

  /**
   * Sets the value of the objectDefinition property.
   * 
   * @param value allowed object is {@link MajorObject }
   * 
   */
  public void setObjectDefinition(MajorObject value) {
    this.objectDefinition = value;
  }

  public boolean isSetObjectDefinition() {
    return (this.objectDefinition != null);
  }

  /**
   * Gets the value of the scope property.
   * 
   * @return possible object is {@link Scope }
   * 
   */
  public Scope getScope() {
    return scope;
  }

  /**
   * Sets the value of the scope property.
   * 
   * @param value allowed object is {@link Scope }
   * 
   */
  public void setScope(Scope value) {
    this.scope = value;
  }

  public boolean isSetScope() {
    return (this.scope != null);
  }

  /**
   * Gets the value of the allowCreate property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public boolean isAllowCreate() {
    return allowCreate;
  }

  /**
   * Sets the value of the allowCreate property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setAllowCreate(boolean value) {
    this.allowCreate = value;
  }

  public boolean isSetAllowCreate() {
    return (this.allowCreate != null);
  }

  public void unsetAllowCreate() {
    this.allowCreate = null;
  }

  /**
   * Gets the value of the objectExpansion property.
   * 
   * @return possible object is {@link ObjectExpansion }
   * 
   */
  public ObjectExpansion getObjectExpansion() {
    return objectExpansion;
  }

  /**
   * Sets the value of the objectExpansion property.
   * 
   * @param value allowed object is {@link ObjectExpansion }
   * 
   */
  public void setObjectExpansion(ObjectExpansion value) {
    this.objectExpansion = value;
  }

  public boolean isSetObjectExpansion() {
    return (this.objectExpansion != null);
  }

}
