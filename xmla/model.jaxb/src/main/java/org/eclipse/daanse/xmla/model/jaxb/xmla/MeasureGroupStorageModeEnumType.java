
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MeasureGroupStorageModeEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="MeasureGroupStorageModeEnumType"&gt;
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
@XmlType(name = "MeasureGroupStorageModeEnumType")
@XmlEnum
public enum MeasureGroupStorageModeEnumType {

  @XmlEnumValue("Molap")
  MOLAP("Molap"), @XmlEnumValue("Rolap")
  ROLAP("Rolap"), @XmlEnumValue("Holap")
  HOLAP("Holap"), @XmlEnumValue("InMemory")
  IN_MEMORY("InMemory");

  private final String value;

  MeasureGroupStorageModeEnumType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static MeasureGroupStorageModeEnumType fromValue(String v) {
    for (MeasureGroupStorageModeEnumType c : MeasureGroupStorageModeEnumType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
