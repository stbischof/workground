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
 * Java class for Source complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Source"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *         &lt;element name="ConnectionString" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Source", propOrder = {

})
public class Source {

  @XmlElement(name = "Object", required = true)
  protected ObjectReference object;
  @XmlElement(name = "ConnectionString", required = true)
  protected String connectionString;

  /**
   * Gets the value of the object property.
   * 
   * @return possible object is {@link ObjectReference }
   * 
   */
  public ObjectReference getObject() {
    return object;
  }

  /**
   * Sets the value of the object property.
   * 
   * @param value allowed object is {@link ObjectReference }
   * 
   */
  public void setObject(ObjectReference value) {
    this.object = value;
  }

  public boolean isSetObject() {
    return (this.object != null);
  }

  /**
   * Gets the value of the connectionString property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getConnectionString() {
    return connectionString;
  }

  /**
   * Sets the value of the connectionString property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setConnectionString(String value) {
    this.connectionString = value;
  }

  public boolean isSetConnectionString() {
    return (this.connectionString != null);
  }

}
