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
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MeasureGroupDimensionBinding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MeasureGroupDimensionBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasureGroupDimensionBinding", propOrder = { "cubeDimensionID" })
public class MeasureGroupDimensionBinding extends Binding {

  @XmlElement(name = "CubeDimensionID", required = true)
  protected String cubeDimensionID;

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

}
