
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DimensionCurrentStorageModeEnumType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="DimensionCurrentStorageModeEnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Molap"/&gt;
 *     &lt;enumeration value="InMemory"/&gt;
 *     &lt;enumeration value="Rolap"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DimensionCurrentStorageModeEnumType")
@XmlEnum
public enum DimensionCurrentStorageModeEnumType {

  @XmlEnumValue("Molap")
  MOLAP("Molap"), @XmlEnumValue("InMemory")
  IN_MEMORY("InMemory"), @XmlEnumValue("Rolap")
  ROLAP("Rolap");

  private final String value;

  DimensionCurrentStorageModeEnumType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static DimensionCurrentStorageModeEnumType fromValue(String v) {
    for (DimensionCurrentStorageModeEnumType c : DimensionCurrentStorageModeEnumType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
