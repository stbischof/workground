
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PerspectiveAttribute complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PerspectiveAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AttributeHierarchyVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DefaultMember" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Annotations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "PerspectiveAttribute", propOrder = {

})
public class PerspectiveAttribute {

  @XmlElement(name = "AttributeID", required = true)
  protected String attributeID;
  @XmlElement(name = "AttributeHierarchyVisible")
  protected Boolean attributeHierarchyVisible;
  @XmlElement(name = "DefaultMember")
  protected String defaultMember;
  @XmlElement(name = "Annotations")
  protected PerspectiveAttribute.Annotations annotations;

  /**
   * Gets the value of the attributeID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAttributeID() {
    return attributeID;
  }

  /**
   * Sets the value of the attributeID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAttributeID(String value) {
    this.attributeID = value;
  }

  public boolean isSetAttributeID() {
    return (this.attributeID != null);
  }

  /**
   * Gets the value of the attributeHierarchyVisible property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isAttributeHierarchyVisible() {
    return attributeHierarchyVisible;
  }

  /**
   * Sets the value of the attributeHierarchyVisible property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setAttributeHierarchyVisible(Boolean value) {
    this.attributeHierarchyVisible = value;
  }

  public boolean isSetAttributeHierarchyVisible() {
    return (this.attributeHierarchyVisible != null);
  }

  /**
   * Gets the value of the defaultMember property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDefaultMember() {
    return defaultMember;
  }

  /**
   * Sets the value of the defaultMember property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDefaultMember(String value) {
    this.defaultMember = value;
  }

  public boolean isSetDefaultMember() {
    return (this.defaultMember != null);
  }

  /**
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link PerspectiveAttribute.Annotations }
   * 
   */
  public PerspectiveAttribute.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link PerspectiveAttribute.Annotations }
   * 
   */
  public void setAnnotations(PerspectiveAttribute.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
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
   *         &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "annotation" })
  public static class Annotations {

    @XmlElement(name = "Annotation")
    protected List<Annotation> annotation;

    /**
     * Gets the value of the annotation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the annotation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAnnotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Annotation }
     * 
     * 
     */
    public List<Annotation> getAnnotation() {
      if (annotation == null) {
        annotation = new ArrayList<Annotation>();
      }
      return this.annotation;
    }

    public boolean isSetAnnotation() {
      return ((this.annotation != null) && (!this.annotation.isEmpty()));
    }

    public void unsetAnnotation() {
      this.annotation = null;
    }

  }

}
