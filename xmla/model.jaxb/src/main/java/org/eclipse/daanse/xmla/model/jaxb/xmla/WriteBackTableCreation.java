
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for WriteBackTableCreation.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="WriteBackTableCreation"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Create"/&gt;
 *     &lt;enumeration value="CreateAlways"/&gt;
 *     &lt;enumeration value="UseExisting"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "WriteBackTableCreation")
@XmlEnum
public enum WriteBackTableCreation {

  @XmlEnumValue("Create")
  CREATE("Create"), @XmlEnumValue("CreateAlways")
  CREATE_ALWAYS("CreateAlways"), @XmlEnumValue("UseExisting")
  USE_EXISTING("UseExisting");

  private final String value;

  WriteBackTableCreation(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static WriteBackTableCreation fromValue(String v) {
    for (WriteBackTableCreation c : WriteBackTableCreation.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
