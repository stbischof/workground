
package org.eclipse.daanse.xmla.model.jaxb.engine300;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttributeHierarchyProcessingState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="AttributeHierarchyProcessingState"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Processed"/&gt;
 *     &lt;enumeration value="Unprocessed"/&gt;
 *     &lt;enumeration value="DependencyError"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AttributeHierarchyProcessingState")
@XmlEnum
public enum AttributeHierarchyProcessingState {

    @XmlEnumValue("Processed")
    PROCESSED("Processed"),
    @XmlEnumValue("Unprocessed")
    UNPROCESSED("Unprocessed"),
    @XmlEnumValue("DependencyError")
    DEPENDENCY_ERROR("DependencyError");
    private final String value;

    AttributeHierarchyProcessingState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AttributeHierarchyProcessingState fromValue(String v) {
        for (AttributeHierarchyProcessingState c: AttributeHierarchyProcessingState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
