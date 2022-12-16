
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Measure complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Measure"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AggregateFunction" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Sum"/&gt;
 *               &lt;enumeration value="Count"/&gt;
 *               &lt;enumeration value="Min"/&gt;
 *               &lt;enumeration value="Max"/&gt;
 *               &lt;enumeration value="DistinctCount"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="ByAccount"/&gt;
 *               &lt;enumeration value="AverageOfChildren"/&gt;
 *               &lt;enumeration value="FirstChild"/&gt;
 *               &lt;enumeration value="LastChild"/&gt;
 *               &lt;enumeration value="FirstNonEmpty"/&gt;
 *               &lt;enumeration value="LastNonEmpty"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DataType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="WChar"/&gt;
 *               &lt;enumeration value="Integer"/&gt;
 *               &lt;enumeration value="BigInt"/&gt;
 *               &lt;enumeration value="Single"/&gt;
 *               &lt;enumeration value="Double"/&gt;
 *               &lt;enumeration value="Date"/&gt;
 *               &lt;enumeration value="Currency"/&gt;
 *               &lt;enumeration value="UnsignedTinyInt"/&gt;
 *               &lt;enumeration value="UnsignedSmallInt"/&gt;
 *               &lt;enumeration value="UnsignedInt"/&gt;
 *               &lt;enumeration value="UnsignedBigInt"/&gt;
 *               &lt;enumeration value="Bool"/&gt;
 *               &lt;enumeration value="Smallint"/&gt;
 *               &lt;enumeration value="Tinyint"/&gt;
 *               &lt;enumeration value="Variant"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}DataItem"/&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MeasureExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DisplayFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FormatString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BackColor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ForeColor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FontSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FontFlags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
@XmlType(name = "Measure", propOrder = {

})
public class Measure {

  @XmlElement(name = "Name", required = true)
  protected String name;
  @XmlElement(name = "ID")
  protected String id;
  @XmlElement(name = "Description")
  protected String description;
  @XmlElement(name = "AggregateFunction")
  protected String aggregateFunction;
  @XmlElement(name = "DataType")
  protected String dataType;
  @XmlElement(name = "Source", required = true)
  protected DataItem source;
  @XmlElement(name = "Visible")
  protected Boolean visible;
  @XmlElement(name = "MeasureExpression")
  protected String measureExpression;
  @XmlElement(name = "DisplayFolder")
  protected String displayFolder;
  @XmlElement(name = "FormatString")
  protected String formatString;
  @XmlElement(name = "BackColor")
  protected String backColor;
  @XmlElement(name = "ForeColor")
  protected String foreColor;
  @XmlElement(name = "FontName")
  protected String fontName;
  @XmlElement(name = "FontSize")
  protected String fontSize;
  @XmlElement(name = "FontFlags")
  protected String fontFlags;
  @XmlElement(name = "Translations")
  protected Measure.Translations translations;
  @XmlElement(name = "Annotations")
  protected Measure.Annotations annotations;

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
   * Gets the value of the aggregateFunction property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAggregateFunction() {
    return aggregateFunction;
  }

  /**
   * Sets the value of the aggregateFunction property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAggregateFunction(String value) {
    this.aggregateFunction = value;
  }

  public boolean isSetAggregateFunction() {
    return (this.aggregateFunction != null);
  }

  /**
   * Gets the value of the dataType property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDataType() {
    return dataType;
  }

  /**
   * Sets the value of the dataType property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDataType(String value) {
    this.dataType = value;
  }

  public boolean isSetDataType() {
    return (this.dataType != null);
  }

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link DataItem }
   * 
   */
  public DataItem getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link DataItem }
   * 
   */
  public void setSource(DataItem value) {
    this.source = value;
  }

  public boolean isSetSource() {
    return (this.source != null);
  }

  /**
   * Gets the value of the visible property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isVisible() {
    return visible;
  }

  /**
   * Sets the value of the visible property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setVisible(Boolean value) {
    this.visible = value;
  }

  public boolean isSetVisible() {
    return (this.visible != null);
  }

  /**
   * Gets the value of the measureExpression property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getMeasureExpression() {
    return measureExpression;
  }

  /**
   * Sets the value of the measureExpression property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setMeasureExpression(String value) {
    this.measureExpression = value;
  }

  public boolean isSetMeasureExpression() {
    return (this.measureExpression != null);
  }

  /**
   * Gets the value of the displayFolder property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDisplayFolder() {
    return displayFolder;
  }

  /**
   * Sets the value of the displayFolder property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDisplayFolder(String value) {
    this.displayFolder = value;
  }

  public boolean isSetDisplayFolder() {
    return (this.displayFolder != null);
  }

  /**
   * Gets the value of the formatString property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFormatString() {
    return formatString;
  }

  /**
   * Sets the value of the formatString property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFormatString(String value) {
    this.formatString = value;
  }

  public boolean isSetFormatString() {
    return (this.formatString != null);
  }

  /**
   * Gets the value of the backColor property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getBackColor() {
    return backColor;
  }

  /**
   * Sets the value of the backColor property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setBackColor(String value) {
    this.backColor = value;
  }

  public boolean isSetBackColor() {
    return (this.backColor != null);
  }

  /**
   * Gets the value of the foreColor property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getForeColor() {
    return foreColor;
  }

  /**
   * Sets the value of the foreColor property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setForeColor(String value) {
    this.foreColor = value;
  }

  public boolean isSetForeColor() {
    return (this.foreColor != null);
  }

  /**
   * Gets the value of the fontName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFontName() {
    return fontName;
  }

  /**
   * Sets the value of the fontName property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFontName(String value) {
    this.fontName = value;
  }

  public boolean isSetFontName() {
    return (this.fontName != null);
  }

  /**
   * Gets the value of the fontSize property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFontSize() {
    return fontSize;
  }

  /**
   * Sets the value of the fontSize property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFontSize(String value) {
    this.fontSize = value;
  }

  public boolean isSetFontSize() {
    return (this.fontSize != null);
  }

  /**
   * Gets the value of the fontFlags property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFontFlags() {
    return fontFlags;
  }

  /**
   * Sets the value of the fontFlags property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFontFlags(String value) {
    this.fontFlags = value;
  }

  public boolean isSetFontFlags() {
    return (this.fontFlags != null);
  }

  /**
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link Measure.Translations }
   * 
   */
  public Measure.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is {@link Measure.Translations }
   * 
   */
  public void setTranslations(Measure.Translations value) {
    this.translations = value;
  }

  public boolean isSetTranslations() {
    return (this.translations != null);
  }

  /**
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link Measure.Annotations }
   * 
   */
  public Measure.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link Measure.Annotations }
   * 
   */
  public void setAnnotations(Measure.Annotations value) {
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
   *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    protected List<Translation> translation;

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
     * Objects of the following type(s) are allowed in the list {@link Translation }
     * 
     * 
     */
    public List<Translation> getTranslation() {
      if (translation == null) {
        translation = new ArrayList<Translation>();
      }
      return this.translation;
    }

    public boolean isSetTranslation() {
      return ((this.translation != null) && (!this.translation.isEmpty()));
    }

    public void unsetTranslation() {
      this.translation = null;
    }

  }

}
