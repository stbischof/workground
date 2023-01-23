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
 * Java class for Unsubscribe complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Unsubscribe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2013/engine/800}SubscriptionId"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Unsubscribe", propOrder = {

})
public class Unsubscribe {

  @XmlElement(name = "SubscriptionId", namespace = "http://schemas.microsoft.com/analysisservices/2013/engine/800", required = true)
  protected String subscriptionId;

  /**
   * Gets the value of the subscriptionId property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSubscriptionId() {
    return subscriptionId;
  }

  /**
   * Sets the value of the subscriptionId property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSubscriptionId(String value) {
    this.subscriptionId = value;
  }

  public boolean isSetSubscriptionId() {
    return (this.subscriptionId != null);
  }

}
