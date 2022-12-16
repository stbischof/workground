
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CubeStorageModeEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="CubeStorageModeEnumType"&gt;
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
@XmlType(name = "CubeStorageModeEnumType")
@XmlEnum
public enum CubeStorageModeEnumType {

    @XmlEnumValue("Molap")
    MOLAP("Molap"),
    @XmlEnumValue("Rolap")
    ROLAP("Rolap"),
    @XmlEnumValue("Holap")
    HOLAP("Holap"),
    @XmlEnumValue("InMemory")
    IN_MEMORY("InMemory");
    private final String value;

    CubeStorageModeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CubeStorageModeEnumType fromValue(String v) {
        for (CubeStorageModeEnumType c: CubeStorageModeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
