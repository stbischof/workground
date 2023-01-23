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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

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
 *       &lt;attribute name="mustUnderstand" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "BeginSession")
public class BeginSession {

  @XmlAttribute(name = "mustUnderstand")
  protected Integer mustUnderstand;

  /**
   * Gets the value of the mustUnderstand property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public int getMustUnderstand() {
    return mustUnderstand;
  }

  /**
   * Sets the value of the mustUnderstand property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setMustUnderstand(int value) {
    this.mustUnderstand = value;
  }

  public boolean isSetMustUnderstand() {
    return (this.mustUnderstand != null);
  }

  public void unsetMustUnderstand() {
    this.mustUnderstand = null;
  }

}
