
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OlapInfoCube complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OlapInfoCube"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CubeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2003/engine}LastDataUpdate" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2003/engine}LastSchemaUpdate" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OlapInfoCube", propOrder = { "cubeName", "lastDataUpdate", "lastSchemaUpdate" })
public class OlapInfoCube {

  @XmlElement(name = "CubeName", required = true)
  protected String cubeName;
  @XmlElement(name = "LastDataUpdate", namespace = "http://schemas.microsoft.com/analysisservices/2003/engine")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastDataUpdate;
  @XmlElement(name = "LastSchemaUpdate", namespace = "http://schemas.microsoft.com/analysisservices/2003/engine")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastSchemaUpdate;

  /**
   * Gets the value of the cubeName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCubeName() {
    return cubeName;
  }

  /**
   * Sets the value of the cubeName property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCubeName(String value) {
    this.cubeName = value;
  }

  public boolean isSetCubeName() {
    return (this.cubeName != null);
  }

  /**
   * Gets the value of the lastDataUpdate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getLastDataUpdate() {
    return lastDataUpdate;
  }

  /**
   * Sets the value of the lastDataUpdate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setLastDataUpdate(XMLGregorianCalendar value) {
    this.lastDataUpdate = value;
  }

  public boolean isSetLastDataUpdate() {
    return (this.lastDataUpdate != null);
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

}
