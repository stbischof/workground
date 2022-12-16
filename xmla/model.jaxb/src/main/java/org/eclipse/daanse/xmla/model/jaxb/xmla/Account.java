
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Account complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Account"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AggregationFunction" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Sum"/&gt;
 *               &lt;enumeration value="Count"/&gt;
 *               &lt;enumeration value="Min"/&gt;
 *               &lt;enumeration value="Max"/&gt;
 *               &lt;enumeration value="DistinctCount"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="AverageOfChildren"/&gt;
 *               &lt;enumeration value="FirstChild"/&gt;
 *               &lt;enumeration value="LastChild"/&gt;
 *               &lt;enumeration value="FirstNonEmpty"/&gt;
 *               &lt;enumeration value="LastNonEmpty"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Aliases" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Alias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "Account", propOrder = {

})
public class Account {

  @XmlElement(name = "AccountType", required = true)
  protected String accountType;
  @XmlElement(name = "AggregationFunction")
  protected String aggregationFunction;
  @XmlElement(name = "Aliases")
  protected Account.Aliases aliases;
  @XmlElement(name = "Annotations")
  protected Account.Annotations annotations;

  /**
   * Gets the value of the accountType property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAccountType() {
    return accountType;
  }

  /**
   * Sets the value of the accountType property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAccountType(String value) {
    this.accountType = value;
  }

  public boolean isSetAccountType() {
    return (this.accountType != null);
  }

  /**
   * Gets the value of the aggregationFunction property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAggregationFunction() {
    return aggregationFunction;
  }

  /**
   * Sets the value of the aggregationFunction property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAggregationFunction(String value) {
    this.aggregationFunction = value;
  }

  public boolean isSetAggregationFunction() {
    return (this.aggregationFunction != null);
  }

  /**
   * Gets the value of the aliases property.
   * 
   * @return possible object is {@link Account.Aliases }
   * 
   */
  public Account.Aliases getAliases() {
    return aliases;
  }

  /**
   * Sets the value of the aliases property.
   * 
   * @param value allowed object is {@link Account.Aliases }
   * 
   */
  public void setAliases(Account.Aliases value) {
    this.aliases = value;
  }

  public boolean isSetAliases() {
    return (this.aliases != null);
  }

  /**
   * Gets the value of the annotations property.
   * 
   * @return possible object is {@link Account.Annotations }
   * 
   */
  public Account.Annotations getAnnotations() {
    return annotations;
  }

  /**
   * Sets the value of the annotations property.
   * 
   * @param value allowed object is {@link Account.Annotations }
   * 
   */
  public void setAnnotations(Account.Annotations value) {
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
   *         &lt;element name="Alias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "alias" })
  public static class Aliases {

    @XmlElement(name = "Alias")
    protected List<String> alias;

    /**
     * Gets the value of the alias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the alias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAlias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     * 
     * 
     */
    public List<String> getAlias() {
      if (alias == null) {
        alias = new ArrayList<String>();
      }
      return this.alias;
    }

    public boolean isSetAlias() {
      return ((this.alias != null) && (!this.alias.isEmpty()));
    }

    public void unsetAlias() {
      this.alias = null;
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
