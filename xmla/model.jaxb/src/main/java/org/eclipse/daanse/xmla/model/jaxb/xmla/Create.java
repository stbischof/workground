
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Create complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Create"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ParentObject" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference" minOccurs="0"/&gt;
 *         &lt;element name="ObjectDefinition" type="{urn:schemas-microsoft-com:xml-analysis}MajorObject"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="Scope" type="{urn:schemas-microsoft-com:xml-analysis}Scope" /&gt;
 *       &lt;attribute name="AllowOverwrite" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Create", propOrder = {

})
public class Create {

  @XmlElement(name = "ParentObject")
  protected ObjectReference parentObject;
  @XmlElement(name = "ObjectDefinition", required = true)
  protected MajorObject objectDefinition;
  @XmlAttribute(name = "Scope")
  protected Scope scope;
  @XmlAttribute(name = "AllowOverwrite")
  protected Boolean allowOverwrite;

  /**
   * Gets the value of the parentObject property.
   * 
   * @return possible object is {@link ObjectReference }
   * 
   */
  public ObjectReference getParentObject() {
    return parentObject;
  }

  /**
   * Sets the value of the parentObject property.
   * 
   * @param value allowed object is {@link ObjectReference }
   * 
   */
  public void setParentObject(ObjectReference value) {
    this.parentObject = value;
  }

  public boolean isSetParentObject() {
    return (this.parentObject != null);
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
   * Gets the value of the allowOverwrite property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public boolean isAllowOverwrite() {
    return allowOverwrite;
  }

  /**
   * Sets the value of the allowOverwrite property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setAllowOverwrite(boolean value) {
    this.allowOverwrite = value;
  }

  public boolean isSetAllowOverwrite() {
    return (this.allowOverwrite != null);
  }

  public void unsetAllowOverwrite() {
    this.allowOverwrite = null;
  }

}
