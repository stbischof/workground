
package org.eclipse.daanse.xmla.model.jaxb.engine300;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DimensionAttributeVisualizationProperties complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionAttributeVisualizationProperties"&gt;
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
 *         &lt;element name="Alignment" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="Left"/&gt;
 *               &lt;enumeration value="Right"/&gt;
 *               &lt;enumeration value="Center"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IsFolderDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IsRightToLeft" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SortDirection" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="Ascending"/&gt;
 *               &lt;enumeration value="Descending"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DefaultDetailsPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="CommonIdentifierPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SortPropertiesPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DisplayKeyPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="IsDefaultImage" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DefaultAggregateFunction" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Sum"/&gt;
 *               &lt;enumeration value="Min"/&gt;
 *               &lt;enumeration value="Max"/&gt;
 *               &lt;enumeration value="Count"/&gt;
 *               &lt;enumeration value="Average"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionAttributeVisualizationProperties", propOrder = { "folderPosition", "contextualNameRule",
    "alignment", "isFolderDefault", "isRightToLeft", "sortDirection", "units", "width", "defaultDetailsPosition",
    "commonIdentifierPosition", "sortPropertiesPosition", "displayKeyPosition", "isDefaultImage",
    "defaultAggregateFunction" })
public class DimensionAttributeVisualizationProperties implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(name = "FolderPosition", defaultValue = "-1")
  protected BigInteger folderPosition;
  @XmlElement(name = "ContextualNameRule", defaultValue = "None")
  protected String contextualNameRule;
  @XmlElement(name = "Alignment", defaultValue = "Default")
  protected String alignment;
  @XmlElement(name = "IsFolderDefault", defaultValue = "false")
  protected Boolean isFolderDefault;
  @XmlElement(name = "IsRightToLeft", defaultValue = "false")
  protected Boolean isRightToLeft;
  @XmlElement(name = "SortDirection", defaultValue = "Default")
  protected String sortDirection;
  @XmlElement(name = "Units", defaultValue = "")
  protected String units;
  @XmlElement(name = "Width", defaultValue = "-1")
  protected BigInteger width;
  @XmlElement(name = "DefaultDetailsPosition", defaultValue = "-1")
  protected BigInteger defaultDetailsPosition;
  @XmlElement(name = "CommonIdentifierPosition", defaultValue = "-1")
  protected BigInteger commonIdentifierPosition;
  @XmlElement(name = "SortPropertiesPosition", defaultValue = "-1")
  protected BigInteger sortPropertiesPosition;
  @XmlElement(name = "DisplayKeyPosition", defaultValue = "-1")
  protected BigInteger displayKeyPosition;
  @XmlElement(name = "IsDefaultImage", defaultValue = "false")
  protected Boolean isDefaultImage;
  @XmlElement(name = "DefaultAggregateFunction", defaultValue = "Default")
  protected String defaultAggregateFunction;

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
   * Gets the value of the alignment property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAlignment() {
    return alignment;
  }

  /**
   * Sets the value of the alignment property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAlignment(String value) {
    this.alignment = value;
  }

  public boolean isSetAlignment() {
    return (this.alignment != null);
  }

  /**
   * Gets the value of the isFolderDefault property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isIsFolderDefault() {
    return isFolderDefault;
  }

  /**
   * Sets the value of the isFolderDefault property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setIsFolderDefault(Boolean value) {
    this.isFolderDefault = value;
  }

  public boolean isSetIsFolderDefault() {
    return (this.isFolderDefault != null);
  }

  /**
   * Gets the value of the isRightToLeft property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isIsRightToLeft() {
    return isRightToLeft;
  }

  /**
   * Sets the value of the isRightToLeft property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setIsRightToLeft(Boolean value) {
    this.isRightToLeft = value;
  }

  public boolean isSetIsRightToLeft() {
    return (this.isRightToLeft != null);
  }

  /**
   * Gets the value of the sortDirection property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSortDirection() {
    return sortDirection;
  }

  /**
   * Sets the value of the sortDirection property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSortDirection(String value) {
    this.sortDirection = value;
  }

  public boolean isSetSortDirection() {
    return (this.sortDirection != null);
  }

  /**
   * Gets the value of the units property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUnits() {
    return units;
  }

  /**
   * Sets the value of the units property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUnits(String value) {
    this.units = value;
  }

  public boolean isSetUnits() {
    return (this.units != null);
  }

  /**
   * Gets the value of the width property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getWidth() {
    return width;
  }

  /**
   * Sets the value of the width property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setWidth(BigInteger value) {
    this.width = value;
  }

  public boolean isSetWidth() {
    return (this.width != null);
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
   * Gets the value of the defaultAggregateFunction property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDefaultAggregateFunction() {
    return defaultAggregateFunction;
  }

  /**
   * Sets the value of the defaultAggregateFunction property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDefaultAggregateFunction(String value) {
    this.defaultAggregateFunction = value;
  }

  public boolean isSetDefaultAggregateFunction() {
    return (this.defaultAggregateFunction != null);
  }

}
