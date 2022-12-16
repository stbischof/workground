
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MiningModelingFlag complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MiningModelingFlag"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ModelingFlag" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiningModelingFlag", propOrder = {

})
public class MiningModelingFlag {

  @XmlElement(name = "ModelingFlag", required = true)
  protected String modelingFlag;

  /**
   * Gets the value of the modelingFlag property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getModelingFlag() {
    return modelingFlag;
  }

  /**
   * Sets the value of the modelingFlag property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setModelingFlag(String value) {
    this.modelingFlag = value;
  }

  public boolean isSetModelingFlag() {
    return (this.modelingFlag != null);
  }

}
