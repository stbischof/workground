
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MiningStructure complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MiningStructure"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
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
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Collation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="CacheMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="KeepTrainingCases"/&gt;
 *               &lt;enumeration value="ClearAfterProcessing"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}HoldoutMaxPercent" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}HoldoutMaxCases" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}HoldoutSeed" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}HoldoutActualSize" minOccurs="0"/&gt;
 *         &lt;element name="Columns"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructureColumn" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MiningStructurePermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MiningStructurePermission" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructurePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MiningModels" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MiningModel" type="{urn:schemas-microsoft-com:xml-analysis}MiningModel" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "MiningStructure", propOrder = {

})
public class MiningStructure {

  @XmlElement(name = "Name", required = true)
  protected String name;
  @XmlElement(name = "ID")
  protected String id;
  @XmlElement(name = "CreatedTimestamp")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar createdTimestamp;
  @XmlElement(name = "LastSchemaUpdate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastSchemaUpdate;
  @XmlElement(name = "Description")
  protected String description;
  @XmlElement(name = "Annotations")
  protected MiningStructure.Annotations annotations;
  @XmlElement(name = "Source")
  protected Binding source;
  @XmlElement(name = "LastProcessed")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastProcessed;
  @XmlElement(name = "Translations")
  protected MiningStructure.Translations translations;
  @XmlElement(name = "Language")
  protected BigInteger language;
  @XmlElement(name = "Collation")
  protected String collation;
  @XmlElement(name = "ErrorConfiguration")
  protected ErrorConfiguration errorConfiguration;
  @XmlElement(name = "CacheMode")
  protected String cacheMode;
  @XmlElement(name = "HoldoutMaxPercent", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
  protected Integer holdoutMaxPercent;
  @XmlElement(name = "HoldoutMaxCases", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
  protected Integer holdoutMaxCases;
  @XmlElement(name = "HoldoutSeed", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
  protected Integer holdoutSeed;
  @XmlElement(name = "HoldoutActualSize", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
  protected Integer holdoutActualSize;
  @XmlElement(name = "Columns", required = true)
  protected MiningStructure.Columns columns;
  @XmlElement(name = "State")
  protected String state;
  @XmlElement(name = "MiningStructurePermissions")
  protected MiningStructure.MiningStructurePermissions miningStructurePermissions;
  @XmlElement(name = "MiningModels")
  protected MiningStructure.MiningModels miningModels;

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
   * @return possible object is {@link MiningStructure.Annotations }
   * 
   */
  public MiningStructure.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link MiningStructure.Annotations }
   * 
   */
  public void setAnnotations(MiningStructure.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
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
   * Gets the value of the lastProcessed property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getLastProcessed() {
    return lastProcessed;
  }

  /**
   * Sets the value of the lastProcessed property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setLastProcessed(XMLGregorianCalendar value) {
    this.lastProcessed = value;
  }

  public boolean isSetLastProcessed() {
    return (this.lastProcessed != null);
  }

  /**
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link MiningStructure.Translations }
   * 
   */
  public MiningStructure.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is {@link MiningStructure.Translations }
   * 
   */
  public void setTranslations(MiningStructure.Translations value) {
    this.translations = value;
  }

  public boolean isSetTranslations() {
    return (this.translations != null);
  }

  /**
   * Gets the value of the language property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getLanguage() {
    return language;
  }

  /**
   * Sets the value of the language property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setLanguage(BigInteger value) {
    this.language = value;
  }

  public boolean isSetLanguage() {
    return (this.language != null);
  }

  /**
   * Gets the value of the collation property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCollation() {
    return collation;
  }

  /**
   * Sets the value of the collation property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCollation(String value) {
    this.collation = value;
  }

  public boolean isSetCollation() {
    return (this.collation != null);
  }

  /**
   * Gets the value of the errorConfiguration property.
   * 
   * @return possible object is {@link ErrorConfiguration }
   * 
   */
  public ErrorConfiguration getErrorConfiguration() {
    return errorConfiguration;
  }

  /**
   * Sets the value of the errorConfiguration property.
   * 
   * @param value allowed object is {@link ErrorConfiguration }
   * 
   */
  public void setErrorConfiguration(ErrorConfiguration value) {
    this.errorConfiguration = value;
  }

  public boolean isSetErrorConfiguration() {
    return (this.errorConfiguration != null);
  }

  /**
   * Gets the value of the cacheMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCacheMode() {
    return cacheMode;
  }

  /**
   * Sets the value of the cacheMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCacheMode(String value) {
    this.cacheMode = value;
  }

  public boolean isSetCacheMode() {
    return (this.cacheMode != null);
  }

  /**
   * Gets the value of the holdoutMaxPercent property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getHoldoutMaxPercent() {
    return holdoutMaxPercent;
  }

  /**
   * Sets the value of the holdoutMaxPercent property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setHoldoutMaxPercent(Integer value) {
    this.holdoutMaxPercent = value;
  }

  public boolean isSetHoldoutMaxPercent() {
    return (this.holdoutMaxPercent != null);
  }

  /**
   * Gets the value of the holdoutMaxCases property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getHoldoutMaxCases() {
    return holdoutMaxCases;
  }

  /**
   * Sets the value of the holdoutMaxCases property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setHoldoutMaxCases(Integer value) {
    this.holdoutMaxCases = value;
  }

  public boolean isSetHoldoutMaxCases() {
    return (this.holdoutMaxCases != null);
  }

  /**
   * Gets the value of the holdoutSeed property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getHoldoutSeed() {
    return holdoutSeed;
  }

  /**
   * Sets the value of the holdoutSeed property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setHoldoutSeed(Integer value) {
    this.holdoutSeed = value;
  }

  public boolean isSetHoldoutSeed() {
    return (this.holdoutSeed != null);
  }

  /**
   * Gets the value of the holdoutActualSize property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getHoldoutActualSize() {
    return holdoutActualSize;
  }

  /**
   * Sets the value of the holdoutActualSize property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setHoldoutActualSize(Integer value) {
    this.holdoutActualSize = value;
  }

  public boolean isSetHoldoutActualSize() {
    return (this.holdoutActualSize != null);
  }

  /**
   * Gets the value of the columns property.
   * 
   * @return possible object is {@link MiningStructure.Columns }
   * 
   */
  public MiningStructure.Columns getColumns() {
    return columns;
  }

  /**
   * Sets the value of the columns property.
   * 
   * @param value allowed object is {@link MiningStructure.Columns }
   * 
   */
  public void setColumns(MiningStructure.Columns value) {
    this.columns = value;
  }

  public boolean isSetColumns() {
    return (this.columns != null);
  }

  /**
   * Gets the value of the state property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the value of the state property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setState(String value) {
    this.state = value;
  }

  public boolean isSetState() {
    return (this.state != null);
  }

  /**
   * Gets the value of the miningStructurePermissions property.
   * 
   * @return possible object is
   *         {@link MiningStructure.MiningStructurePermissions }
   * 
   */
  public MiningStructure.MiningStructurePermissions getMiningStructurePermissions() {
    return miningStructurePermissions;
  }

  /**
   * Sets the value of the miningStructurePermissions property.
   * 
   * @param value allowed object is
   *              {@link MiningStructure.MiningStructurePermissions }
   * 
   */
  public void setMiningStructurePermissions(MiningStructure.MiningStructurePermissions value) {
    this.miningStructurePermissions = value;
  }

  public boolean isSetMiningStructurePermissions() {
    return (this.miningStructurePermissions != null);
  }

  /**
   * Gets the value of the miningModels property.
   * 
   * @return possible object is {@link MiningStructure.MiningModels }
   * 
   */
  public MiningStructure.MiningModels getMiningModels() {
    return miningModels;
  }

  /**
   * Sets the value of the miningModels property.
   * 
   * @param value allowed object is {@link MiningStructure.MiningModels }
   * 
   */
  public void setMiningModels(MiningStructure.MiningModels value) {
    this.miningModels = value;
  }

  public boolean isSetMiningModels() {
    return (this.miningModels != null);
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
   *         &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructureColumn" maxOccurs="unbounded"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "column" })
  public static class Columns {

    @XmlElement(name = "Column", required = true)
    protected List<MiningStructureColumn> column;

    /**
     * Gets the value of the column property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the column property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MiningStructureColumn }
     * 
     * 
     */
    public List<MiningStructureColumn> getColumn() {
      if (column == null) {
        column = new ArrayList<MiningStructureColumn>();
      }
      return this.column;
    }

    public boolean isSetColumn() {
      return ((this.column != null) && (!this.column.isEmpty()));
    }

    public void unsetColumn() {
      this.column = null;
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
   *         &lt;element name="MiningModel" type="{urn:schemas-microsoft-com:xml-analysis}MiningModel" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "miningModel" })
  public static class MiningModels {

    @XmlElement(name = "MiningModel")
    protected List<MiningModel> miningModel;

    /**
     * Gets the value of the miningModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the miningModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMiningModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link MiningModel }
     * 
     * 
     */
    public List<MiningModel> getMiningModel() {
      if (miningModel == null) {
        miningModel = new ArrayList<MiningModel>();
      }
      return this.miningModel;
    }

    public boolean isSetMiningModel() {
      return ((this.miningModel != null) && (!this.miningModel.isEmpty()));
    }

    public void unsetMiningModel() {
      this.miningModel = null;
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
   *         &lt;element name="MiningStructurePermission" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructurePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "miningStructurePermission" })
  public static class MiningStructurePermissions {

    @XmlElement(name = "MiningStructurePermission")
    protected List<MiningStructurePermission> miningStructurePermission;

    /**
     * Gets the value of the miningStructurePermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the miningStructurePermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMiningStructurePermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MiningStructurePermission }
     * 
     * 
     */
    public List<MiningStructurePermission> getMiningStructurePermission() {
      if (miningStructurePermission == null) {
        miningStructurePermission = new ArrayList<MiningStructurePermission>();
      }
      return this.miningStructurePermission;
    }

    public boolean isSetMiningStructurePermission() {
      return ((this.miningStructurePermission != null) && (!this.miningStructurePermission.isEmpty()));
    }

    public void unsetMiningStructurePermission() {
      this.miningStructurePermission = null;
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
