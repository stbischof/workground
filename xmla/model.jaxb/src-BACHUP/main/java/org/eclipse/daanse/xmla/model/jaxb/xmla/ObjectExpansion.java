
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectExpansion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="ObjectExpansion"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ObjectProperties"/&gt;
 *     &lt;enumeration value="ExpandFull"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ObjectExpansion")
@XmlEnum
public enum ObjectExpansion {

    @XmlEnumValue("ObjectProperties")
    OBJECT_PROPERTIES("ObjectProperties"),
    @XmlEnumValue("ExpandFull")
    EXPAND_FULL("ExpandFull");
    private final String value;

    ObjectExpansion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectExpansion fromValue(String v) {
        for (ObjectExpansion c: ObjectExpansion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
