
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CubeAttribute complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CubeAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AggregationUsage" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Full"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Unrestricted"/&gt;
 *               &lt;enumeration value="Default"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributeHierarchyOptimizedState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="FullyOptimized"/&gt;
 *               &lt;enumeration value="NotOptimized"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributeHierarchyEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AttributeHierarchyVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
@XmlType(name = "CubeAttribute", propOrder = {

})
public class CubeAttribute {

  @XmlElement(name = "AttributeID", required = true)
  protected String attributeID;
  @XmlElement(name = "AggregationUsage")
  protected String aggregationUsage;
  @XmlElement(name = "AttributeHierarchyOptimizedState")
  protected String attributeHierarchyOptimizedState;
  @XmlElement(name = "AttributeHierarchyEnabled")
  protected Boolean attributeHierarchyEnabled;
  @XmlElement(name = "AttributeHierarchyVisible")
  protected Boolean attributeHierarchyVisible;
  @XmlElement(name = "Annotations")
  protected CubeAttribute.Annotations annotations;

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
   * Gets the value of the aggregationUsage property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAggregationUsage() {
    return aggregationUsage;
  }

  /**
   * Sets the value of the aggregationUsage property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAggregationUsage(String value) {
    this.aggregationUsage = value;
  }

  public boolean isSetAggregationUsage() {
    return (this.aggregationUsage != null);
  }

  /**
   * Gets the value of the attributeHierarchyOptimizedState property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAttributeHierarchyOptimizedState() {
    return attributeHierarchyOptimizedState;
  }

  /**
   * Sets the value of the attributeHierarchyOptimizedState property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAttributeHierarchyOptimizedState(String value) {
    this.attributeHierarchyOptimizedState = value;
  }

  public boolean isSetAttributeHierarchyOptimizedState() {
    return (this.attributeHierarchyOptimizedState != null);
  }

  /**
   * Gets the value of the attributeHierarchyEnabled property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isAttributeHierarchyEnabled() {
    return attributeHierarchyEnabled;
  }

  /**
   * Sets the value of the attributeHierarchyEnabled property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setAttributeHierarchyEnabled(Boolean value) {
    this.attributeHierarchyEnabled = value;
  }

  public boolean isSetAttributeHierarchyEnabled() {
    return (this.attributeHierarchyEnabled != null);
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
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link CubeAttribute.Annotations }
   * 
   */
  public CubeAttribute.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link CubeAttribute.Annotations }
   * 
   */
  public void setAnnotations(CubeAttribute.Annotations value) {
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
