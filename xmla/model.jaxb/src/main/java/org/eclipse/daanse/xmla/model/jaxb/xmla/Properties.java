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
 * Java class for Properties complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Properties"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PropertyList" type="{urn:schemas-microsoft-com:xml-analysis}PropertyList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Properties", propOrder = { "propertyList" })
public class Properties {

  @XmlElement(name = "PropertyList")
  protected PropertyList propertyList;

  /**
   * Gets the value of the propertyList property.
   * 
   * @return possible object is {@link PropertyList }
   * 
   */
  public PropertyList getPropertyList() {
    return propertyList;
  }

  /**
   * Sets the value of the propertyList property.
   * 
   * @param value allowed object is {@link PropertyList }
   * 
   */
  public void setPropertyList(PropertyList value) {
    this.propertyList = value;
  }

  public boolean isSetPropertyList() {
    return (this.propertyList != null);
  }

}
