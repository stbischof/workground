
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ScalarMiningStructureColumn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ScalarMiningStructureColumn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Type"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Long"/&gt;
 *               &lt;enumeration value="Boolean"/&gt;
 *               &lt;enumeration value="Text"/&gt;
 *               &lt;enumeration value="Double"/&gt;
 *               &lt;enumeration value="Date"/&gt;
 *               &lt;enumeration value="Table"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
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
 *         &lt;element name="IsKey" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
 *         &lt;element name="Distribution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ModelingFlags" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ModelingFlag" type="{urn:schemas-microsoft-com:xml-analysis}MiningModelingFlag" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Content"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Discrete"/&gt;
 *               &lt;enumeration value="Continuous"/&gt;
 *               &lt;enumeration value="Discretized"/&gt;
 *               &lt;enumeration value="Ordered"/&gt;
 *               &lt;enumeration value="Cyclical"/&gt;
 *               &lt;enumeration value="Probability"/&gt;
 *               &lt;enumeration value="Variance"/&gt;
 *               &lt;enumeration value="StdDev"/&gt;
 *               &lt;enumeration value="ProbabilityVariance"/&gt;
 *               &lt;enumeration value="ProbabilityStdDev"/&gt;
 *               &lt;enumeration value="Support"/&gt;
 *               &lt;enumeration value="Key"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ClassifiedColumns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ClassifiedColumnID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DiscretizationMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DiscretizationBucketCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="KeyColumns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="KeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NameColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" minOccurs="0"/&gt;
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
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScalarMiningStructureColumn", propOrder = {

})
public class ScalarMiningStructureColumn {

  @XmlElement(name = "Name", required = true)
  protected String name;
  @XmlElement(name = "ID")
  protected String id;
  @XmlElement(name = "Description")
  protected String description;
  @XmlElement(name = "Type", required = true)
  protected String type;
  @XmlElement(name = "Annotations")
  protected ScalarMiningStructureColumn.Annotations annotations;
  @XmlElement(name = "IsKey")
  protected Boolean isKey;
  @XmlElement(name = "Source")
  protected Binding source;
  @XmlElement(name = "Distribution")
  protected String distribution;
  @XmlElement(name = "ModelingFlags")
  protected ScalarMiningStructureColumn.ModelingFlags modelingFlags;
  @XmlElement(name = "Content", required = true)
  protected String content;
  @XmlElement(name = "ClassifiedColumns")
  protected ScalarMiningStructureColumn.ClassifiedColumns classifiedColumns;
  @XmlElement(name = "DiscretizationMethod")
  protected String discretizationMethod;
  @XmlElement(name = "DiscretizationBucketCount")
  protected BigInteger discretizationBucketCount;
  @XmlElement(name = "KeyColumns")
  protected ScalarMiningStructureColumn.KeyColumns keyColumns;
  @XmlElement(name = "NameColumn")
  protected DataItem nameColumn;
  @XmlElement(name = "Translations")
  protected ScalarMiningStructureColumn.Translations translations;

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
   * Gets the value of the type property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setType(String value) {
    this.type = value;
  }

  public boolean isSetType() {
    return (this.type != null);
  }

  /**
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link ScalarMiningStructureColumn.Annotations }
   * 
   */
  public ScalarMiningStructureColumn.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is
   *              {@link ScalarMiningStructureColumn.Annotations }
   * 
   */
  public void setAnnotations(ScalarMiningStructureColumn.Annotations value) {
    this.annotations = value;
  }

  public boolean isSetAnnotations() {
    return (this.annotations != null);
  }

  /**
   * Gets the value of the isKey property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isIsKey() {
    return isKey;
  }

  /**
   * Sets the value of the isKey property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setIsKey(Boolean value) {
    this.isKey = value;
  }

  public boolean isSetIsKey() {
    return (this.isKey != null);
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
   * Gets the value of the distribution property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDistribution() {
    return distribution;
  }

  /**
   * Sets the value of the distribution property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDistribution(String value) {
    this.distribution = value;
  }

  public boolean isSetDistribution() {
    return (this.distribution != null);
  }

  /**
   * Gets the value of the modelingFlags property.
   * 
   * @return possible object is {@link ScalarMiningStructureColumn.ModelingFlags }
   * 
   */
  public ScalarMiningStructureColumn.ModelingFlags getModelingFlags() {
    return modelingFlags;
  }

  /**
   * Sets the value of the modelingFlags property.
   * 
   * @param value allowed object is
   *              {@link ScalarMiningStructureColumn.ModelingFlags }
   * 
   */
  public void setModelingFlags(ScalarMiningStructureColumn.ModelingFlags value) {
    this.modelingFlags = value;
  }

  public boolean isSetModelingFlags() {
    return (this.modelingFlags != null);
  }

  /**
   * Gets the value of the content property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the value of the content property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setContent(String value) {
    this.content = value;
  }

  public boolean isSetContent() {
    return (this.content != null);
  }

  /**
   * Gets the value of the classifiedColumns property.
   * 
   * @return possible object is
   *         {@link ScalarMiningStructureColumn.ClassifiedColumns }
   * 
   */
  public ScalarMiningStructureColumn.ClassifiedColumns getClassifiedColumns() {
    return classifiedColumns;
  }

  /**
   * Sets the value of the classifiedColumns property.
   * 
   * @param value allowed object is
   *              {@link ScalarMiningStructureColumn.ClassifiedColumns }
   * 
   */
  public void setClassifiedColumns(ScalarMiningStructureColumn.ClassifiedColumns value) {
    this.classifiedColumns = value;
  }

  public boolean isSetClassifiedColumns() {
    return (this.classifiedColumns != null);
  }

  /**
   * Gets the value of the discretizationMethod property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDiscretizationMethod() {
    return discretizationMethod;
  }

  /**
   * Sets the value of the discretizationMethod property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDiscretizationMethod(String value) {
    this.discretizationMethod = value;
  }

  public boolean isSetDiscretizationMethod() {
    return (this.discretizationMethod != null);
  }

  /**
   * Gets the value of the discretizationBucketCount property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getDiscretizationBucketCount() {
    return discretizationBucketCount;
  }

  /**
   * Sets the value of the discretizationBucketCount property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setDiscretizationBucketCount(BigInteger value) {
    this.discretizationBucketCount = value;
  }

  public boolean isSetDiscretizationBucketCount() {
    return (this.discretizationBucketCount != null);
  }

  /**
   * Gets the value of the keyColumns property.
   * 
   * @return possible object is {@link ScalarMiningStructureColumn.KeyColumns }
   * 
   */
  public ScalarMiningStructureColumn.KeyColumns getKeyColumns() {
    return keyColumns;
  }

  /**
   * Sets the value of the keyColumns property.
   * 
   * @param value allowed object is
   *              {@link ScalarMiningStructureColumn.KeyColumns }
   * 
   */
  public void setKeyColumns(ScalarMiningStructureColumn.KeyColumns value) {
    this.keyColumns = value;
  }

  public boolean isSetKeyColumns() {
    return (this.keyColumns != null);
  }

  /**
   * Gets the value of the nameColumn property.
   * 
   * @return possible object is {@link DataItem }
   * 
   */
  public DataItem getNameColumn() {
    return nameColumn;
  }

  /**
   * Sets the value of the nameColumn property.
   * 
   * @param value allowed object is {@link DataItem }
   * 
   */
  public void setNameColumn(DataItem value) {
    this.nameColumn = value;
  }

  public boolean isSetNameColumn() {
    return (this.nameColumn != null);
  }

  /**
   * Gets the value of the translations property.
   * 
   * @return possible object is {@link ScalarMiningStructureColumn.Translations }
   * 
   */
  public ScalarMiningStructureColumn.Translations getTranslations() {
    return translations;
  }

  /**
   * Sets the value of the translations property.
   * 
   * @param value allowed object is
   *              {@link ScalarMiningStructureColumn.Translations }
   * 
   */
  public void setTranslations(ScalarMiningStructureColumn.Translations value) {
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
   *         &lt;element name="ClassifiedColumnID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "classifiedColumnID" })
  public static class ClassifiedColumns {

    @XmlElement(name = "ClassifiedColumnID")
    protected List<String> classifiedColumnID;

    /**
     * Gets the value of the classifiedColumnID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the classifiedColumnID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getClassifiedColumnID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     * 
     * 
     */
    public List<String> getClassifiedColumnID() {
      if (classifiedColumnID == null) {
        classifiedColumnID = new ArrayList<String>();
      }
      return this.classifiedColumnID;
    }

    public boolean isSetClassifiedColumnID() {
      return ((this.classifiedColumnID != null) && (!this.classifiedColumnID.isEmpty()));
    }

    public void unsetClassifiedColumnID() {
      this.classifiedColumnID = null;
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
   *         &lt;element name="KeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    protected List<DataItem> keyColumn;

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
     * Objects of the following type(s) are allowed in the list {@link DataItem }
     * 
     * 
     */
    public List<DataItem> getKeyColumn() {
      if (keyColumn == null) {
        keyColumn = new ArrayList<DataItem>();
      }
      return this.keyColumn;
    }

    public boolean isSetKeyColumn() {
      return ((this.keyColumn != null) && (!this.keyColumn.isEmpty()));
    }

    public void unsetKeyColumn() {
      this.keyColumn = null;
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
   *         &lt;element name="ModelingFlag" type="{urn:schemas-microsoft-com:xml-analysis}MiningModelingFlag" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "modelingFlag" })
  public static class ModelingFlags {

    @XmlElement(name = "ModelingFlag")
    protected List<MiningModelingFlag> modelingFlag;

    /**
     * Gets the value of the modelingFlag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the modelingFlag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getModelingFlag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MiningModelingFlag }
     * 
     * 
     */
    public List<MiningModelingFlag> getModelingFlag() {
      if (modelingFlag == null) {
        modelingFlag = new ArrayList<MiningModelingFlag>();
      }
      return this.modelingFlag;
    }

    public boolean isSetModelingFlag() {
      return ((this.modelingFlag != null) && (!this.modelingFlag.isEmpty()));
    }

    public void unsetModelingFlag() {
      this.modelingFlag = null;
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
