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
 * Java class for Folder complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Folder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Original" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="New" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Folder", propOrder = {

})
public class Folder {

  @XmlElement(name = "Original", required = true)
  protected String original;
  @XmlElement(name = "New", required = true)
  protected String _new;

  /**
   * Gets the value of the original property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOriginal() {
    return original;
  }

  /**
   * Sets the value of the original property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOriginal(String value) {
    this.original = value;
  }

  public boolean isSetOriginal() {
    return (this.original != null);
  }

  /**
   * Gets the value of the new property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNew() {
    return _new;
  }

  /**
   * Sets the value of the new property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setNew(String value) {
    this._new = value;
  }

  public boolean isSetNew() {
    return (this._new != null);
  }

}
