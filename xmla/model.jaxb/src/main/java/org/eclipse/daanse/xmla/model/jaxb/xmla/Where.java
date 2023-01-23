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
 * Java class for Where complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Where"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}Where_Attribute"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Where", propOrder = {

})
public class Where {

  @XmlElement(name = "Attribute", required = true)
  protected WhereAttribute attribute;

  /**
   * Gets the value of the attribute property.
   * 
   * @return possible object is {@link WhereAttribute }
   * 
   */
  public WhereAttribute getAttribute() {
    return attribute;
  }

  /**
   * Sets the value of the attribute property.
   * 
   * @param value allowed object is {@link WhereAttribute }
   * 
   */
  public void setAttribute(WhereAttribute value) {
    this.attribute = value;
  }

  public boolean isSetAttribute() {
    return (this.attribute != null);
  }

}
