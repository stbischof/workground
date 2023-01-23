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
 * Java class for PartitionCurrentStorageModeEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="PartitionCurrentStorageModeEnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Molap"/&gt;
 *     &lt;enumeration value="Rolap"/&gt;
 *     &lt;enumeration value="Holap"/&gt;
 *     &lt;enumeration value="InMemory"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PartitionCurrentStorageModeEnumType")
@XmlEnum
public enum PartitionCurrentStorageModeEnumType {

  @XmlEnumValue("Molap")
  MOLAP("Molap"), @XmlEnumValue("Rolap")
  ROLAP("Rolap"), @XmlEnumValue("Holap")
  HOLAP("Holap"), @XmlEnumValue("InMemory")
  IN_MEMORY("InMemory");

  private final String value;

  PartitionCurrentStorageModeEnumType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static PartitionCurrentStorageModeEnumType fromValue(String v) {
    for (PartitionCurrentStorageModeEnumType c : PartitionCurrentStorageModeEnumType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
