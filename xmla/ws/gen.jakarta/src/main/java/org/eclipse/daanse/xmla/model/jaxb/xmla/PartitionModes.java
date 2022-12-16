
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partitionModes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="partitionModes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="none"/&gt;
 *     &lt;enumeration value="perNode"/&gt;
 *     &lt;enumeration value="perCpu"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "partitionModes")
@XmlEnum
public enum PartitionModes {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("perNode")
    PER_NODE("perNode"),
    @XmlEnumValue("perCpu")
    PER_CPU("perCpu");
    private final String value;

    PartitionModes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PartitionModes fromValue(String v) {
        for (PartitionModes c: PartitionModes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
