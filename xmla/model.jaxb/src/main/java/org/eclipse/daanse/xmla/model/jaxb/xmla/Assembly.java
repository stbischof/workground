
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.daanse.xmla.model.jaxb.engine.ImpersonationInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Assembly complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Assembly"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CreatedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="LastSchemaUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="ImpersonationInfo" type="{http://schemas.microsoft.com/analysisservices/2003/engine}ImpersonationInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Assembly", propOrder = { "id", "name", "createdTimestamp", "lastSchemaUpdate", "description",
    "annotations", "impersonationInfo" })
@XmlSeeAlso({ ComAssembly.class, ClrAssembly.class })
public abstract class Assembly {

  @XmlElement(name = "ID")
  protected String id;
  @XmlElement(name = "Name", required = true)
  protected String name;
  @XmlElement(name = "CreatedTimestamp")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar createdTimestamp;
  @XmlElement(name = "LastSchemaUpdate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastSchemaUpdate;
  @XmlElement(name = "Description")
  protected String description;
  @XmlElement(name = "Annotations")
  protected Assembly.Annotations annotations;
  @XmlElement(name = "ImpersonationInfo")
  protected ImpersonationInfo impersonationInfo;

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getID() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setID(String value) {
    this.id = value;
  }

  public boolean isSetID() {
    return (this.id != null);
  }

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
   * Gets the value of the createdTimestamp property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getCreatedTimestamp() {
    return createdTimestamp;
  }

  /**
   * Sets the value of the createdTimestamp property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setCreatedTimestamp(XMLGregorianCalendar value) {
    this.createdTimestamp = value;
  }

  public boolean isSetCreatedTimestamp() {
    return (this.createdTimestamp != null);
  }

  /**
   * Gets the value of the lastSchemaUpdate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getLastSchemaUpdate() {
    return lastSchemaUpdate;
  }

  /**
   * Sets the value of the lastSchemaUpdate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setLastSchemaUpdate(XMLGregorianCalendar value) {
    this.lastSchemaUpdate = value;
  }

  public boolean isSetLastSchemaUpdate() {
    return (this.lastSchemaUpdate != null);
  }

  /**
   * Gets the value of the description property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescription(String value) {
    this.description = value;
  }

  public boolean isSetDescription() {
    return (this.description != null);
  }

  /**
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link Assembly.Annotations }
   * 
   */
  public Assembly.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link Assembly.Annotations }
   * 
   */
  public void setAnnotations(Assembly.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
  }

  /**
   * Gets the value of the impersonationInfo property.
   * 
   * @return possible object is {@link ImpersonationInfo }
   * 
   */
  public ImpersonationInfo getImpersonationInfo() {
    return impersonationInfo;
  }

  /**
   * Sets the value of the impersonationInfo property.
   * 
   * @param value allowed object is {@link ImpersonationInfo }
   * 
   */
  public void setImpersonationInfo(ImpersonationInfo value) {
    this.impersonationInfo = value;
  }

  public boolean isSetImpersonationInfo() {
    return (this.impersonationInfo != null);
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
