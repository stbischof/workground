package mondrian.rolap.physicalschema;

import org.eigenbase.xom.Location;
import org.eigenbase.xom.NodeDef;

/**
 * Implementation of XmlLocation based on {@link Location}.
 */
class XmlLocationImpl implements XmlLocation {

    private final NodeDef node;
    private final Location location;
    private final String attributeName;

    /**
     * Creates an XmlLocationImpl.
     *
     * @param node          XML node
     * @param location      Location
     * @param attributeName Attribute name (may be null)
     */
    XmlLocationImpl(
        NodeDef node, Location location, String attributeName
    ) {
        this.node = node;
        this.location = location;
        this.attributeName = attributeName;
    }

    public String toString() {
        return location == null ? "null" : location.toString();
    }

    public String getRange() {
        final int start = location.getStartPos();
        final int end = start + location.getText(true).length();
        return start + "-" + end;
    }

    public XmlLocation at(String attributeName) {
        if (Util.equals(attributeName, this.attributeName)) {
            return this;
        }
        return new XmlLocationImpl(node, location, attributeName);
    }
}
