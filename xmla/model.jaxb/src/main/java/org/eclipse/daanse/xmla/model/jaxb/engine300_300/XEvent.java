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
package org.eclipse.daanse.xmla.model.jaxb.engine300_300;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jaxb.xmla.EventSession;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis}event_session"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "eventSession" })
@XmlRootElement(name = "XEvent")
public class XEvent implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(name = "event_session", namespace = "urn:schemas-microsoft-com:xml-analysis", required = true)
  protected EventSession eventSession;

  /**
   * Gets the value of the eventSession property.
   * 
   * @return possible object is {@link EventSession }
   * 
   */
  public EventSession getEventSession() {
    return eventSession;
  }

  /**
   * Sets the value of the eventSession property.
   * 
   * @param value allowed object is {@link EventSession }
   * 
   */
  public void setEventSession(EventSession value) {
    this.eventSession = value;
  }

  public boolean isSetEventSession() {
    return (this.eventSession != null);
  }

}
