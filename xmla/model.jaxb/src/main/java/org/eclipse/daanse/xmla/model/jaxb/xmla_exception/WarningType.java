
package org.eclipse.daanse.xmla.model.jaxb.xmla_exception;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for WarningType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="WarningType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Location" type="{urn:schemas-microsoft-com:xml-analysis:exception}MessageLocation" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="WarningCode" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Source" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="HelpFile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WarningType", propOrder = {

})
public class WarningType implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(name = "Location")
  protected MessageLocation location;
  @XmlAttribute(name = "WarningCode")
  protected Integer warningCode;
  @XmlAttribute(name = "Description")
  protected String description;
  @XmlAttribute(name = "Source")
  protected String source;
  @XmlAttribute(name = "HelpFile")
  protected String helpFile;

  /**
   * Gets the value of the location property.
   * 
   * @return possible object is {@link MessageLocation }
   * 
   */
  public MessageLocation getLocation() {
    return location;
  }

  /**
   * Sets the value of the location property.
   * 
   * @param value allowed object is {@link MessageLocation }
   * 
   */
  public void setLocation(MessageLocation value) {
    this.location = value;
  }

  public boolean isSetLocation() {
    return (this.location != null);
  }

  /**
   * Gets the value of the warningCode property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public int getWarningCode() {
    return warningCode;
  }

  /**
   * Sets the value of the warningCode property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setWarningCode(int value) {
    this.warningCode = value;
  }

  public boolean isSetWarningCode() {
    return (this.warningCode != null);
  }

  public void unsetWarningCode() {
    this.warningCode = null;
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
   * Gets the value of the source property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSource(String value) {
    this.source = value;
  }

  public boolean isSetSource() {
    return (this.source != null);
  }

  /**
   * Gets the value of the helpFile property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getHelpFile() {
    return helpFile;
  }

  /**
   * Sets the value of the helpFile property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setHelpFile(String value) {
    this.helpFile = value;
  }

  public boolean isSetHelpFile() {
    return (this.helpFile != null);
  }

}
