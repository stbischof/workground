
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OutOfLineBinding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OutOfLineBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DimensionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CubeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MeasureGroupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PartitionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningModelID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningStructureID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MeasureID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParentColumnID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ColumnID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *         &lt;element name="NameColumn" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SkippedLevelsColumn" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustomRollupColumn" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustomRollupPropertiesColumn" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ValueColumn" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="UnaryOperatorColumn" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="KeyColumns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="KeyColumn" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                           &lt;/all&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ForeignKeyColumns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ForeignKeyColumn" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                           &lt;/all&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *                           &lt;/all&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "OutOfLineBinding", propOrder = {

})
public class OutOfLineBinding {

  @XmlElement(name = "DatabaseID")
  protected String databaseID;
  @XmlElement(name = "DimensionID")
  protected String dimensionID;
  @XmlElement(name = "CubeID")
  protected String cubeID;
  @XmlElement(name = "MeasureGroupID")
  protected String measureGroupID;
  @XmlElement(name = "PartitionID")
  protected String partitionID;
  @XmlElement(name = "MiningModelID")
  protected String miningModelID;
  @XmlElement(name = "MiningStructureID")
  protected String miningStructureID;
  @XmlElement(name = "AttributeID")
  protected String attributeID;
  @XmlElement(name = "CubeDimensionID")
  protected String cubeDimensionID;
  @XmlElement(name = "MeasureID")
  protected String measureID;
  @XmlElement(name = "ParentColumnID")
  protected String parentColumnID;
  @XmlElement(name = "ColumnID")
  protected String columnID;
  @XmlElement(name = "Source")
  protected Binding source;
  @XmlElement(name = "NameColumn")
  protected OutOfLineBinding.NameColumn nameColumn;
  @XmlElement(name = "SkippedLevelsColumn")
  protected OutOfLineBinding.SkippedLevelsColumn skippedLevelsColumn;
  @XmlElement(name = "CustomRollupColumn")
  protected OutOfLineBinding.CustomRollupColumn customRollupColumn;
  @XmlElement(name = "CustomRollupPropertiesColumn")
  protected OutOfLineBinding.CustomRollupPropertiesColumn customRollupPropertiesColumn;
  @XmlElement(name = "ValueColumn")
  protected OutOfLineBinding.ValueColumn valueColumn;
  @XmlElement(name = "UnaryOperatorColumn")
  protected OutOfLineBinding.UnaryOperatorColumn unaryOperatorColumn;
  @XmlElement(name = "KeyColumns")
  protected OutOfLineBinding.KeyColumns keyColumns;
  @XmlElement(name = "ForeignKeyColumns")
  protected OutOfLineBinding.ForeignKeyColumns foreignKeyColumns;
  @XmlElement(name = "Translations")
  protected OutOfLineBinding.Translations translations;

  /**
   * Gets the value of the databaseID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDatabaseID() {
    return databaseID;
  }

  /**
   * Sets the value of the databaseID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDatabaseID(String value) {
    this.databaseID = value;
  }

  public boolean isSetDatabaseID() {
    return (this.databaseID != null);
  }

  /**
   * Gets the value of the dimensionID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDimensionID() {
    return dimensionID;
  }

  /**
   * Sets the value of the dimensionID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDimensionID(String value) {
    this.dimensionID = value;
  }

  public boolean isSetDimensionID() {
    return (this.dimensionID != null);
  }

  /**
   * Gets the value of the cubeID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCubeID() {
    return cubeID;
  }

  /**
   * Sets the value of the cubeID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCubeID(String value) {
    this.cubeID = value;
  }

  public boolean isSetCubeID() {
    return (this.cubeID != null);
  }

  /**
   * Gets the value of the measureGroupID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMeasureGroupID() {
    return measureGroupID;
  }

  /**
   * Sets the value of the measureGroupID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMeasureGroupID(String value) {
    this.measureGroupID = value;
  }

  public boolean isSetMeasureGroupID() {
    return (this.measureGroupID != null);
  }

  /**
   * Gets the value of the partitionID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPartitionID() {
    return partitionID;
  }

  /**
   * Sets the value of the partitionID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPartitionID(String value) {
    this.partitionID = value;
  }

  public boolean isSetPartitionID() {
    return (this.partitionID != null);
  }

  /**
   * Gets the value of the miningModelID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMiningModelID() {
    return miningModelID;
  }

  /**
   * Sets the value of the miningModelID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMiningModelID(String value) {
    this.miningModelID = value;
  }

  public boolean isSetMiningModelID() {
    return (this.miningModelID != null);
  }

  /**
   * Gets the value of the miningStructureID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMiningStructureID() {
    return miningStructureID;
  }

  /**
   * Sets the value of the miningStructureID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMiningStructureID(String value) {
    this.miningStructureID = value;
  }

  public boolean isSetMiningStructureID() {
    return (this.miningStructureID != null);
  }

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
   * Gets the value of the cubeDimensionID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCubeDimensionID() {
    return cubeDimensionID;
  }

  /**
   * Sets the value of the cubeDimensionID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCubeDimensionID(String value) {
    this.cubeDimensionID = value;
  }

  public boolean isSetCubeDimensionID() {
    return (this.cubeDimensionID != null);
  }

  /**
   * Gets the value of the measureID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMeasureID() {
    return measureID;
  }

  /**
   * Sets the value of the measureID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMeasureID(String value) {
    this.measureID = value;
  }

  public boolean isSetMeasureID() {
    return (this.measureID != null);
  }

  /**
   * Gets the value of the parentColumnID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getParentColumnID() {
    return parentColumnID;
  }

  /**
   * Sets the value of the parentColumnID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setParentColumnID(String value) {
    this.parentColumnID = value;
  }

  public boolean isSetParentColumnID() {
    return (this.parentColumnID != null);
  }

  /**
   * Gets the value of the columnID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getColumnID() {
    return columnID;
  }

  /**
   * Sets the value of the columnID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setColumnID(String value) {
    this.columnID = value;
  }

  public boolean isSetColumnID() {
    return (this.columnID != null);
  }

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link Binding }
   * 
   */
  public Binding getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link Binding }
   * 
   */
  public void setSource(Binding value) {
    this.source = value;
  }

  public boolean isSetSource() {
    return (this.source != null);
  }

  /**
   * Gets the value of the nameColumn property.
   * 
   * @return possible object is {@link OutOfLineBinding.NameColumn }
   * 
   */
  public OutOfLineBinding.NameColumn getNameColumn() {
    return nameColumn;
  }

  /**
   * Sets the value of the nameColumn property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.NameColumn }
   * 
   */
  public void setNameColumn(OutOfLineBinding.NameColumn value) {
    this.nameColumn = value;
  }

  public boolean isSetNameColumn() {
    return (this.nameColumn != null);
  }

  /**
   * Gets the value of the skippedLevelsColumn property.
   * 
   * @return possible object is {@link OutOfLineBinding.SkippedLevelsColumn }
   * 
   */
  public OutOfLineBinding.SkippedLevelsColumn getSkippedLevelsColumn() {
    return skippedLevelsColumn;
  }

  /**
   * Sets the value of the skippedLevelsColumn property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.SkippedLevelsColumn }
   * 
   */
  public void setSkippedLevelsColumn(OutOfLineBinding.SkippedLevelsColumn value) {
    this.skippedLevelsColumn = value;
  }

  public boolean isSetSkippedLevelsColumn() {
    return (this.skippedLevelsColumn != null);
  }

  /**
   * Gets the value of the customRollupColumn property.
   * 
   * @return possible object is {@link OutOfLineBinding.CustomRollupColumn }
   * 
   */
  public OutOfLineBinding.CustomRollupColumn getCustomRollupColumn() {
    return customRollupColumn;
  }

  /**
   * Sets the value of the customRollupColumn property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.CustomRollupColumn }
   * 
   */
  public void setCustomRollupColumn(OutOfLineBinding.CustomRollupColumn value) {
    this.customRollupColumn = value;
  }

  public boolean isSetCustomRollupColumn() {
    return (this.customRollupColumn != null);
  }

  /**
   * Gets the value of the customRollupPropertiesColumn property.
   * 
   * @return possible object is
   *         {@link OutOfLineBinding.CustomRollupPropertiesColumn }
   * 
   */
  public OutOfLineBinding.CustomRollupPropertiesColumn getCustomRollupPropertiesColumn() {
    return customRollupPropertiesColumn;
  }

  /**
   * Sets the value of the customRollupPropertiesColumn property.
   * 
   * @param value allowed object is
   *              {@link OutOfLineBinding.CustomRollupPropertiesColumn }
   * 
   */
  public void setCustomRollupPropertiesColumn(OutOfLineBinding.CustomRollupPropertiesColumn value) {
    this.customRollupPropertiesColumn = value;
  }

  public boolean isSetCustomRollupPropertiesColumn() {
    return (this.customRollupPropertiesColumn != null);
  }

  /**
   * Gets the value of the valueColumn property.
   * 
   * @return possible object is {@link OutOfLineBinding.ValueColumn }
   * 
   */
  public OutOfLineBinding.ValueColumn getValueColumn() {
    return valueColumn;
  }

  /**
   * Sets the value of the valueColumn property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.ValueColumn }
   * 
   */
  public void setValueColumn(OutOfLineBinding.ValueColumn value) {
    this.valueColumn = value;
  }

  public boolean isSetValueColumn() {
    return (this.valueColumn != null);
  }

  /**
   * Gets the value of the unaryOperatorColumn property.
   * 
   * @return possible object is {@link OutOfLineBinding.UnaryOperatorColumn }
   * 
   */
  public OutOfLineBinding.UnaryOperatorColumn getUnaryOperatorColumn() {
    return unaryOperatorColumn;
  }

  /**
   * Sets the value of the unaryOperatorColumn property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.UnaryOperatorColumn }
   * 
   */
  public void setUnaryOperatorColumn(OutOfLineBinding.UnaryOperatorColumn value) {
    this.unaryOperatorColumn = value;
  }

  public boolean isSetUnaryOperatorColumn() {
    return (this.unaryOperatorColumn != null);
  }

  /**
   * Gets the value of the keyColumns property.
   * 
   * @return possible object is {@link OutOfLineBinding.KeyColumns }
   * 
   */
  public OutOfLineBinding.KeyColumns getKeyColumns() {
    return keyColumns;
  }

  /**
   * Sets the value of the keyColumns property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.KeyColumns }
   * 
   */
  public void setKeyColumns(OutOfLineBinding.KeyColumns value) {
    this.keyColumns = value;
  }

  public boolean isSetKeyColumns() {
    return (this.keyColumns != null);
  }

  /**
   * Gets the value of the foreignKeyColumns property.
   * 
   * @return possible object is {@link OutOfLineBinding.ForeignKeyColumns }
   * 
   */
  public OutOfLineBinding.ForeignKeyColumns getForeignKeyColumns() {
    return foreignKeyColumns;
  }

  /**
   * Sets the value of the foreignKeyColumns property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.ForeignKeyColumns }
   * 
   */
  public void setForeignKeyColumns(OutOfLineBinding.ForeignKeyColumns value) {
    this.foreignKeyColumns = value;
  }

  public boolean isSetForeignKeyColumns() {
    return (this.foreignKeyColumns != null);
  }

  /**
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link OutOfLineBinding.Translations }
   * 
   */
  public OutOfLineBinding.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is {@link OutOfLineBinding.Translations }
   * 
   */
  public void setTranslations(OutOfLineBinding.Translations value) {
    this.translations = value;
  }

  public boolean isSetTranslations() {
    return (this.translations != null);
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
   *       &lt;all&gt;
   *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *       &lt;/all&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {

  })
  public static class CustomRollupColumn {

    @XmlElement(name = "Source")
    protected Binding source;

    /**
     * Gets the value of the source property.
     * 
     * @return possible object is {@link Binding }
     * 
     */
    public Binding getSource() {
      return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value allowed object is {@link Binding }
     * 
     */
    public void setSource(Binding value) {
      this.source = value;
    }

    public boolean isSetSource() {
      return (this.source != null);
    }

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
   *       &lt;all&gt;
   *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *       &lt;/all&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {

  })
  public static class CustomRollupPropertiesColumn {

    @XmlElement(name = "Source")
    protected Binding source;

    /**
     * Gets the value of the source property.
     * 
     * @return possible object is {@link Binding }
     * 
     */
    public Binding getSource() {
      return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value allowed object is {@link Binding }
     * 
     */
    public void setSource(Binding value) {
      this.source = value;
    }

    public boolean isSetSource() {
      return (this.source != null);
    }

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
   *         &lt;element name="ForeignKeyColumn" maxOccurs="unbounded" minOccurs="0"&gt;
   *           &lt;complexType&gt;
   *             &lt;complexContent&gt;
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *                 &lt;all&gt;
   *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *                 &lt;/all&gt;
   *               &lt;/restriction&gt;
   *             &lt;/complexContent&gt;
   *           &lt;/complexType&gt;
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
  @XmlType(name = "", propOrder = { "foreignKeyColumn" })
  public static class ForeignKeyColumns {

    @XmlElement(name = "ForeignKeyColumn")
    protected List<OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn> foreignKeyColumn;

    /**
     * Gets the value of the foreignKeyColumn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the foreignKeyColumn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getForeignKeyColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn }
     * 
     * 
     */
    public List<OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn> getForeignKeyColumn() {
      if (foreignKeyColumn == null) {
        foreignKeyColumn = new ArrayList<OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn>();
      }
      return this.foreignKeyColumn;
    }

    public boolean isSetForeignKeyColumn() {
      return ((this.foreignKeyColumn != null) && (!this.foreignKeyColumn.isEmpty()));
    }

    public void unsetForeignKeyColumn() {
      this.foreignKeyColumn = null;
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
     *       &lt;all&gt;
     *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class ForeignKeyColumn {

      @XmlElement(name = "Source")
      protected Binding source;

      /**
       * Gets the value of the source property.
       * 
       * @return possible object is {@link Binding }
       * 
       */
      public Binding getSource() {
        return source;
      }

      /**
       * Sets the value of the source property.
       * 
       * @param value allowed object is {@link Binding }
       * 
       */
      public void setSource(Binding value) {
        this.source = value;
      }

      public boolean isSetSource() {
        return (this.source != null);
      }

    }

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
   *         &lt;element name="KeyColumn" maxOccurs="unbounded" minOccurs="0"&gt;
   *           &lt;complexType&gt;
   *             &lt;complexContent&gt;
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *                 &lt;all&gt;
   *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *                 &lt;/all&gt;
   *               &lt;/restriction&gt;
   *             &lt;/complexContent&gt;
   *           &lt;/complexType&gt;
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
  @XmlType(name = "", propOrder = { "keyColumn" })
  public static class KeyColumns {

    @XmlElement(name = "KeyColumn")
    protected List<OutOfLineBinding.KeyColumns.KeyColumn> keyColumn;

    /**
     * Gets the value of the keyColumn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the keyColumn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getKeyColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OutOfLineBinding.KeyColumns.KeyColumn }
     * 
     * 
     */
    public List<OutOfLineBinding.KeyColumns.KeyColumn> getKeyColumn() {
      if (keyColumn == null) {
        keyColumn = new ArrayList<OutOfLineBinding.KeyColumns.KeyColumn>();
      }
      return this.keyColumn;
    }

    public boolean isSetKeyColumn() {
      return ((this.keyColumn != null) && (!this.keyColumn.isEmpty()));
    }

    public void unsetKeyColumn() {
      this.keyColumn = null;
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
     *       &lt;all&gt;
     *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class KeyColumn {

      @XmlElement(name = "Source")
      protected Binding source;

      /**
       * Gets the value of the source property.
       * 
       * @return possible object is {@link Binding }
       * 
       */
      public Binding getSource() {
        return source;
      }

      /**
       * Sets the value of the source property.
       * 
       * @param value allowed object is {@link Binding }
       * 
       */
      public void setSource(Binding value) {
        this.source = value;
      }

      public boolean isSetSource() {
        return (this.source != null);
      }

    }

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
   *       &lt;all&gt;
   *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *       &lt;/all&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {

  })
  public static class NameColumn {

    @XmlElement(name = "Source")
    protected Binding source;

    /**
     * Gets the value of the source property.
     * 
     * @return possible object is {@link Binding }
     * 
     */
    public Binding getSource() {
      return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value allowed object is {@link Binding }
     * 
     */
    public void setSource(Binding value) {
      this.source = value;
    }

    public boolean isSetSource() {
      return (this.source != null);
    }

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
   *       &lt;all&gt;
   *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *       &lt;/all&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {

  })
  public static class SkippedLevelsColumn {

    @XmlElement(name = "Source")
    protected Binding source;

    /**
     * Gets the value of the source property.
     * 
     * @return possible object is {@link Binding }
     * 
     */
    public Binding getSource() {
      return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value allowed object is {@link Binding }
     * 
     */
    public void setSource(Binding value) {
      this.source = value;
    }

    public boolean isSetSource() {
      return (this.source != null);
    }

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
   *         &lt;element name="Translation" maxOccurs="unbounded" minOccurs="0"&gt;
   *           &lt;complexType&gt;
   *             &lt;complexContent&gt;
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *                 &lt;all&gt;
   *                   &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
   *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *                 &lt;/all&gt;
   *               &lt;/restriction&gt;
   *             &lt;/complexContent&gt;
   *           &lt;/complexType&gt;
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
  @XmlType(name = "", propOrder = { "translation" })
  public static class Translations {

    @XmlElement(name = "Translation")
    protected List<OutOfLineBinding.Translations.Translation> translation;

    /**
     * Gets the value of the translation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the translation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getTranslation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OutOfLineBinding.Translations.Translation }
     * 
     * 
     */
    public List<OutOfLineBinding.Translations.Translation> getTranslation() {
      if (translation == null) {
        translation = new ArrayList<OutOfLineBinding.Translations.Translation>();
      }
      return this.translation;
    }

    public boolean isSetTranslation() {
      return ((this.translation != null) && (!this.translation.isEmpty()));
    }

    public void unsetTranslation() {
      this.translation = null;
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
     *       &lt;all&gt;
     *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Translation {

      @XmlElement(name = "Language")
      protected int language;
      @XmlElement(name = "Source")
      protected Binding source;

      /**
       * Gets the value of the language property.
       * 
       */
      public int getLanguage() {
        return language;
      }

      /**
       * Sets the value of the language property.
       * 
       */
      public void setLanguage(int value) {
        this.language = value;
      }

      public boolean isSetLanguage() {
        return true;
      }

      /**
       * Gets the value of the source property.
       * 
       * @return possible object is {@link Binding }
       * 
       */
      public Binding getSource() {
        return source;
      }

      /**
       * Sets the value of the source property.
       * 
       * @param value allowed object is {@link Binding }
       * 
       */
      public void setSource(Binding value) {
        this.source = value;
      }

      public boolean isSetSource() {
        return (this.source != null);
      }

    }

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
   *       &lt;all&gt;
   *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *       &lt;/all&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {

  })
  public static class UnaryOperatorColumn {

    @XmlElement(name = "Source")
    protected Binding source;

    /**
     * Gets the value of the source property.
     * 
     * @return possible object is {@link Binding }
     * 
     */
    public Binding getSource() {
      return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value allowed object is {@link Binding }
     * 
     */
    public void setSource(Binding value) {
      this.source = value;
    }

    public boolean isSetSource() {
      return (this.source != null);
    }

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
   *       &lt;all&gt;
   *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
   *       &lt;/all&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {

  })
  public static class ValueColumn {

    @XmlElement(name = "Source")
    protected Binding source;

    /**
     * Gets the value of the source property.
     * 
     * @return possible object is {@link Binding }
     * 
     */
    public Binding getSource() {
      return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value allowed object is {@link Binding }
     * 
     */
    public void setSource(Binding value) {
      this.source = value;
    }

    public boolean isSetSource() {
      return (this.source != null);
    }

  }

}
