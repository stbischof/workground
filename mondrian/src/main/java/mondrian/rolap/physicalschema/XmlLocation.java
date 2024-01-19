package mondrian.rolap.physicalschema;

/**
 * Location of a node in an XML document.
 */
public interface XmlLocation {
    String getRange();

    /**
     * Returns a similar location, but specifying an attribute.
     *
     * @param attributeName Attribute name (may be null)
     * @return Location of attribute
     */
    XmlLocation at(String attributeName);
}
