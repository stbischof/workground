
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for action element declaration.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;element name="action"&gt;
 *   &lt;complexType&gt;
 *     &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *         &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
 *       &lt;/restriction&gt;
 *     &lt;/complexContent&gt;
 *   &lt;/complexType&gt;
 * &lt;/element&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "action")
public class Action2 {

  @XmlAttribute(name = "module")
  protected String module;
  @XmlAttribute(name = "package", required = true)
  protected String _package;
  @XmlAttribute(name = "name", required = true)
  protected String name;

  /**
   * Gets the value of the module property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getModule() {
    return module;
  }

  /**
   * Sets the value of the module property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setModule(String value) {
    this.module = value;
  }

  public boolean isSetModule() {
    return (this.module != null);
  }

  /**
   * Gets the value of the package property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPackage() {
    return _package;
  }

  /**
   * Sets the value of the package property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPackage(String value) {
    this._package = value;
  }

  public boolean isSetPackage() {
    return (this._package != null);
  }

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

}
