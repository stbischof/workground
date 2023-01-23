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

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for retentionModes.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="retentionModes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="allowSingleEventLoss"/&gt;
 *     &lt;enumeration value="allowMultipleEventLoss"/&gt;
 *     &lt;enumeration value="noEventLoss"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "retentionModes")
@XmlEnum
public enum RetentionModes {

  @XmlEnumValue("allowSingleEventLoss")
  ALLOW_SINGLE_EVENT_LOSS("allowSingleEventLoss"), @XmlEnumValue("allowMultipleEventLoss")
  ALLOW_MULTIPLE_EVENT_LOSS("allowMultipleEventLoss"), @XmlEnumValue("noEventLoss")
  NO_EVENT_LOSS("noEventLoss");

  private final String value;

  RetentionModes(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static RetentionModes fromValue(String v) {
    for (RetentionModes c : RetentionModes.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
