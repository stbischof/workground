
package org.eclipse.daanse.xmla.model.jaxb.engine300;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for RelationshipEndVisualizationProperties complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="RelationshipEndVisualizationProperties"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FolderPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ContextualNameRule" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Context"/&gt;
 *               &lt;enumeration value="Merge"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DefaultDetailsPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DisplayKeyPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="CommonIdentifierPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="IsDefaultMeasure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IsDefaultImage" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SortPropertiesPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipEndVisualizationProperties", propOrder = { "folderPosition", "contextualNameRule",
    "defaultDetailsPosition", "displayKeyPosition", "commonIdentifierPosition", "isDefaultMeasure", "isDefaultImage",
    "sortPropertiesPosition" })
public class RelationshipEndVisualizationProperties implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(name = "FolderPosition", defaultValue = "-1")
  protected BigInteger folderPosition;
  @XmlElement(name = "ContextualNameRule", defaultValue = "None")
  protected String contextualNameRule;
  @XmlElement(name = "DefaultDetailsPosition", defaultValue = "-1")
  protected BigInteger defaultDetailsPosition;
  @XmlElement(name = "DisplayKeyPosition", defaultValue = "-1")
  protected BigInteger displayKeyPosition;
  @XmlElement(name = "CommonIdentifierPosition", defaultValue = "-1")
  protected BigInteger commonIdentifierPosition;
  @XmlElement(name = "IsDefaultMeasure", defaultValue = "false")
  protected Boolean isDefaultMeasure;
  @XmlElement(name = "IsDefaultImage", defaultValue = "false")
  protected Boolean isDefaultImage;
  @XmlElement(name = "SortPropertiesPosition", defaultValue = "-1")
  protected BigInteger sortPropertiesPosition;

  /**
   * Gets the value of the folderPosition property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getFolderPosition() {
    return folderPosition;
  }

  /**
   * Sets the value of the folderPosition property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setFolderPosition(BigInteger value) {
    this.folderPosition = value;
  }

  public boolean isSetFolderPosition() {
    return (this.folderPosition != null);
  }

  /**
   * Gets the value of the contextualNameRule property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getContextualNameRule() {
    return contextualNameRule;
  }

  /**
   * Sets the value of the contextualNameRule property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setContextualNameRule(String value) {
    this.contextualNameRule = value;
  }

  public boolean isSetContextualNameRule() {
    return (this.contextualNameRule != null);
  }

  /**
   * Gets the value of the defaultDetailsPosition property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getDefaultDetailsPosition() {
    return defaultDetailsPosition;
  }

  /**
   * Sets the value of the defaultDetailsPosition property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setDefaultDetailsPosition(BigInteger value) {
    this.defaultDetailsPosition = value;
  }

  public boolean isSetDefaultDetailsPosition() {
    return (this.defaultDetailsPosition != null);
  }

  /**
   * Gets the value of the displayKeyPosition property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getDisplayKeyPosition() {
    return displayKeyPosition;
  }

  /**
   * Sets the value of the displayKeyPosition property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setDisplayKeyPosition(BigInteger value) {
    this.displayKeyPosition = value;
  }

  public boolean isSetDisplayKeyPosition() {
    return (this.displayKeyPosition != null);
  }

  /**
   * Gets the value of the commonIdentifierPosition property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getCommonIdentifierPosition() {
    return commonIdentifierPosition;
  }

  /**
   * Sets the value of the commonIdentifierPosition property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setCommonIdentifierPosition(BigInteger value) {
    this.commonIdentifierPosition = value;
  }

  public boolean isSetCommonIdentifierPosition() {
    return (this.commonIdentifierPosition != null);
  }

  /**
   * Gets the value of the isDefaultMeasure property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isIsDefaultMeasure() {
    return isDefaultMeasure;
  }

  /**
   * Sets the value of the isDefaultMeasure property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setIsDefaultMeasure(Boolean value) {
    this.isDefaultMeasure = value;
  }

  public boolean isSetIsDefaultMeasure() {
    return (this.isDefaultMeasure != null);
  }

  /**
   * Gets the value of the isDefaultImage property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isIsDefaultImage() {
    return isDefaultImage;
  }

  /**
   * Sets the value of the isDefaultImage property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setIsDefaultImage(Boolean value) {
    this.isDefaultImage = value;
  }

  public boolean isSetIsDefaultImage() {
    return (this.isDefaultImage != null);
  }

  /**
   * Gets the value of the sortPropertiesPosition property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getSortPropertiesPosition() {
    return sortPropertiesPosition;
  }

  /**
   * Sets the value of the sortPropertiesPosition property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setSortPropertiesPosition(BigInteger value) {
    this.sortPropertiesPosition = value;
  }

  public boolean isSetSortPropertiesPosition() {
    return (this.sortPropertiesPosition != null);
  }

}
