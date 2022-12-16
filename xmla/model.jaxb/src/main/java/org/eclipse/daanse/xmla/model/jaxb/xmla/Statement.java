
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Statement complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Statement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Statement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Statement", propOrder = {

})
public class Statement {

  @XmlElement(name = "Statement")
  protected String statement;

  /**
   * Gets the value of the statement property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getStatement() {
    return statement;
  }

  /**
   * Sets the value of the statement property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setStatement(String value) {
    this.statement = value;
  }

  public boolean isSetStatement() {
    return (this.statement != null);
  }

}
