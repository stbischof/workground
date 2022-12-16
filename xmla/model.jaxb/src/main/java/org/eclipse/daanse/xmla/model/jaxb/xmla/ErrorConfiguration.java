
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ErrorConfiguration complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorConfiguration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="KeyErrorLimit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="KeyErrorLogFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="KeyErrorAction" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ConvertToUnknown"/&gt;
 *               &lt;enumeration value="DiscardRecord"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="KeyErrorLimitAction" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="StopProcessing"/&gt;
 *               &lt;enumeration value="StopLogging"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="KeyNotFound" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="IgnoreError"/&gt;
 *               &lt;enumeration value="ReportAndContinue"/&gt;
 *               &lt;enumeration value="ReportAndStop"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="KeyDuplicate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="IgnoreError"/&gt;
 *               &lt;enumeration value="ReportAndContinue"/&gt;
 *               &lt;enumeration value="ReportAndStop"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NullKeyConvertedToUnknown" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="IgnoreError"/&gt;
 *               &lt;enumeration value="ReportAndContinue"/&gt;
 *               &lt;enumeration value="ReportAndStop"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NullKeyNotAllowed" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="IgnoreError"/&gt;
 *               &lt;enumeration value="ReportAndContinue"/&gt;
 *               &lt;enumeration value="ReportAndStop"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200}CalculationError" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorConfiguration", propOrder = {

})
public class ErrorConfiguration {

  @XmlElement(name = "KeyErrorLimit")
  protected Long keyErrorLimit;
  @XmlElement(name = "KeyErrorLogFile")
  protected String keyErrorLogFile;
  @XmlElement(name = "KeyErrorAction")
  protected String keyErrorAction;
  @XmlElement(name = "KeyErrorLimitAction")
  protected String keyErrorLimitAction;
  @XmlElement(name = "KeyNotFound")
  protected String keyNotFound;
  @XmlElement(name = "KeyDuplicate")
  protected String keyDuplicate;
  @XmlElement(name = "NullKeyConvertedToUnknown")
  protected String nullKeyConvertedToUnknown;
  @XmlElement(name = "NullKeyNotAllowed")
  protected String nullKeyNotAllowed;
  @XmlElement(name = "CalculationError", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
  protected String calculationError;

  /**
   * Gets the value of the keyErrorLimit property.
   * 
   * @return possible object is {@link Long }
   * 
   */
  public Long getKeyErrorLimit() {
    return keyErrorLimit;
  }

  /**
   * Sets the value of the keyErrorLimit property.
   * 
   * @param value allowed object is {@link Long }
   * 
   */
  public void setKeyErrorLimit(Long value) {
    this.keyErrorLimit = value;
  }

  public boolean isSetKeyErrorLimit() {
    return (this.keyErrorLimit != null);
  }

  /**
   * Gets the value of the keyErrorLogFile property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getKeyErrorLogFile() {
    return keyErrorLogFile;
  }

  /**
   * Sets the value of the keyErrorLogFile property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setKeyErrorLogFile(String value) {
    this.keyErrorLogFile = value;
  }

  public boolean isSetKeyErrorLogFile() {
    return (this.keyErrorLogFile != null);
  }

  /**
   * Gets the value of the keyErrorAction property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getKeyErrorAction() {
    return keyErrorAction;
  }

  /**
   * Sets the value of the keyErrorAction property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setKeyErrorAction(String value) {
    this.keyErrorAction = value;
  }

  public boolean isSetKeyErrorAction() {
    return (this.keyErrorAction != null);
  }

  /**
   * Gets the value of the keyErrorLimitAction property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getKeyErrorLimitAction() {
    return keyErrorLimitAction;
  }

  /**
   * Sets the value of the keyErrorLimitAction property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setKeyErrorLimitAction(String value) {
    this.keyErrorLimitAction = value;
  }

  public boolean isSetKeyErrorLimitAction() {
    return (this.keyErrorLimitAction != null);
  }

  /**
   * Gets the value of the keyNotFound property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getKeyNotFound() {
    return keyNotFound;
  }

  /**
   * Sets the value of the keyNotFound property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setKeyNotFound(String value) {
    this.keyNotFound = value;
  }

  public boolean isSetKeyNotFound() {
    return (this.keyNotFound != null);
  }

  /**
   * Gets the value of the keyDuplicate property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getKeyDuplicate() {
    return keyDuplicate;
  }

  /**
   * Sets the value of the keyDuplicate property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setKeyDuplicate(String value) {
    this.keyDuplicate = value;
  }

  public boolean isSetKeyDuplicate() {
    return (this.keyDuplicate != null);
  }

  /**
   * Gets the value of the nullKeyConvertedToUnknown property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNullKeyConvertedToUnknown() {
    return nullKeyConvertedToUnknown;
  }

  /**
   * Sets the value of the nullKeyConvertedToUnknown property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNullKeyConvertedToUnknown(String value) {
    this.nullKeyConvertedToUnknown = value;
  }

  public boolean isSetNullKeyConvertedToUnknown() {
    return (this.nullKeyConvertedToUnknown != null);
  }

  /**
   * Gets the value of the nullKeyNotAllowed property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNullKeyNotAllowed() {
    return nullKeyNotAllowed;
  }

  /**
   * Sets the value of the nullKeyNotAllowed property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNullKeyNotAllowed(String value) {
    this.nullKeyNotAllowed = value;
  }

  public boolean isSetNullKeyNotAllowed() {
    return (this.nullKeyNotAllowed != null);
  }

  /**
   * Gets the value of the calculationError property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCalculationError() {
    return calculationError;
  }

  /**
   * Sets the value of the calculationError property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCalculationError(String value) {
    this.calculationError = value;
  }

  public boolean isSetCalculationError() {
    return (this.calculationError != null);
  }

}
