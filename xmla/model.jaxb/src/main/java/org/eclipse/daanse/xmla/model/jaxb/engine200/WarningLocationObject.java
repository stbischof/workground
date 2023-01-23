/*
* Copyright (c) 2023 Contributors to the Eclipse Foundation.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   SmartCity Jena - initial
*   Stefan Bischof (bipolis.org) - initial
*/
package org.eclipse.daanse.xmla.model.jaxb.engine200;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for WarningLocationObject complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="WarningLocationObject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200}WarningColumn"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200}WarningMeasure"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WarningLocationObject", propOrder = { "warningColumn", "warningMeasure" })
public class WarningLocationObject implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(name = "WarningColumn", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
  protected WarningColumn warningColumn;
  @XmlElement(name = "WarningMeasure", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200")
  protected WarningMeasure warningMeasure;

  /**
   * Gets the value of the warningColumn property.
   * 
   * @return possible object is {@link WarningColumn }
   * 
   */
  public WarningColumn getWarningColumn() {
    return warningColumn;
  }

  /**
   * Sets the value of the warningColumn property.
   * 
   * @param value allowed object is {@link WarningColumn }
   * 
   */
  public void setWarningColumn(WarningColumn value) {
    this.warningColumn = value;
  }

  public boolean isSetWarningColumn() {
    return (this.warningColumn != null);
  }

  /**
   * Gets the value of the warningMeasure property.
   * 
   * @return possible object is {@link WarningMeasure }
   * 
   */
  public WarningMeasure getWarningMeasure() {
    return warningMeasure;
  }

  /**
   * Sets the value of the warningMeasure property.
   * 
   * @param value allowed object is {@link WarningMeasure }
   * 
   */
  public void setWarningMeasure(WarningMeasure value) {
    this.warningMeasure = value;
  }

  public boolean isSetWarningMeasure() {
    return (this.warningMeasure != null);
  }

}
