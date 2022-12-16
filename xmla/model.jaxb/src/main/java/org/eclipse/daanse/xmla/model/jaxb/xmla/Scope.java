
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Scope.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="Scope"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Session"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Scope")
@XmlEnum
public enum Scope {

  @XmlEnumValue("Session")
  SESSION("Session");

  private final String value;

  Scope(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static Scope fromValue(String v) {
    for (Scope c : Scope.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
