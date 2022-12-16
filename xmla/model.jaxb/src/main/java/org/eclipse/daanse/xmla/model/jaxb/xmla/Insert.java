
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Insert complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Insert"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}Object"/&gt;
 *         &lt;element name="Attributes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}Attribute_InsertUpdate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
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
@XmlType(name = "Insert", propOrder = {

})
public class Insert {

  @XmlElement(name = "Object", required = true)
  protected Object object;
  @XmlElement(name = "Attributes")
  protected Insert.Attributes attributes;

  /**
   * Gets the value of the object property.
   * 
   * @return possible object is {@link Object }
   * 
   */
  public Object getObject() {
    return object;
  }

  /**
   * Sets the value of the object property.
   * 
   * @param value allowed object is {@link Object }
   * 
   */
  public void setObject(Object value) {
    this.object = value;
  }

  public boolean isSetObject() {
    return (this.object != null);
  }

  /**
   * Gets the value of the attributes property.
   * 
   * @return possible object is {@link Insert.Attributes }
   * 
   */
  public Insert.Attributes getAttributes() {
    return attributes;
  }

  /**
   * Sets the value of the attributes property.
   * 
   * @param value allowed object is {@link Insert.Attributes }
   * 
   */
  public void setAttributes(Insert.Attributes value) {
    this.attributes = value;
  }

  public boolean isSetAttributes() {
    return (this.attributes != null);
  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}Attribute_InsertUpdate" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "attribute" })
  public static class Attributes {

    @XmlElement(name = "Attribute")
    protected List<AttributeInsertUpdate> attribute;

    /**
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeInsertUpdate }
     * 
     * 
     */
    public List<AttributeInsertUpdate> getAttribute() {
      if (attribute == null) {
        attribute = new ArrayList<AttributeInsertUpdate>();
      }
      return this.attribute;
    }

    public boolean isSetAttribute() {
      return ((this.attribute != null) && (!this.attribute.isEmpty()));
    }

    public void unsetAttribute() {
      this.attribute = null;
    }

  }

}
