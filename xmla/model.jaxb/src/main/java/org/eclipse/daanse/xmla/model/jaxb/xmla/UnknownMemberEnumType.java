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
 * Java class for UnknownMemberEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="UnknownMemberEnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Visible"/&gt;
 *     &lt;enumeration value="Hidden"/&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="AutomaticNull"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UnknownMemberEnumType")
@XmlEnum
public enum UnknownMemberEnumType {

  @XmlEnumValue("Visible")
  VISIBLE("Visible"), @XmlEnumValue("Hidden")
  HIDDEN("Hidden"), @XmlEnumValue("None")
  NONE("None"), @XmlEnumValue("AutomaticNull")
  AUTOMATIC_NULL("AutomaticNull");

  private final String value;

  UnknownMemberEnumType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static UnknownMemberEnumType fromValue(String v) {
    for (UnknownMemberEnumType c : UnknownMemberEnumType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
