package org.eclipse.daanse.olap.impl;

import mondrian.olap4j.Olap4jRuntimeException;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.result.Datatype;
import org.eclipse.daanse.olap.api.result.IMondrianOlap4jProperty;
import org.eclipse.daanse.olap.api.result.Property;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PropertyImpl implements IMondrianOlap4jProperty {

    /**
     * Map of member properties that are built into Mondrian but are not in the
     * olap4j standard.
     */
    static final Map<String, Property> MEMBER_EXTENSIONS =
        new LinkedHashMap<>();

    /**
     * Map of cell properties that are built into Mondrian but are not in the
     * olap4j standard.
     */
    static final Map<String, Property> CELL_EXTENSIONS =
        new LinkedHashMap<>();

    static {
        // Build set of names of olap4j standard member properties.
        final Set<String> memberNames = new HashSet<>();
        for (org.olap4j.metadata.Property property : org.olap4j.metadata.Property.StandardMemberProperty.values()) {
            memberNames.add(property.getName());
        }

        final Set<String> cellNames = new HashSet<>();
        for (org.olap4j.metadata.Property property : org.olap4j.metadata.Property.StandardCellProperty.values()) {
            cellNames.add(property.getName());
        }

        for (mondrian.olap.Property o
            : mondrian.olap.Property.enumeration.getValuesSortedByName())
        {
            if (o.isMemberProperty()
                && !memberNames.contains(o.getName()))
            {
                MEMBER_EXTENSIONS.put(
                    o.getName(),
                    new PropertyImpl(o));
            }
            if (o.isCellProperty()
                && !cellNames.contains(o.getName()))
            {
                CELL_EXTENSIONS.put(
                    o.getName(),
                    new PropertyImpl(o));
            }
        }
    }

    final mondrian.olap.Property property;

    org.eclipse.daanse.olap.api.element.Level level;

    PropertyImpl(mondrian.olap.Property property) {
        this.property = property;
    }

    PropertyImpl(mondrian.olap.Property property, org.eclipse.daanse.olap.api.element.Level level) {
        this(property);
        this.level = level;
    }


    @Override
    public String getName() {
        return property.name;
    }

    @Override
    public Datatype getDatatype() {
        switch (property.getType()) {
            case TYPE_BOOLEAN:
                return Datatype.BOOLEAN;
            case TYPE_NUMERIC:
                return Datatype.DOUBLE;
            case TYPE_INTEGER:
                return Datatype.INTEGER;
            case TYPE_LONG:
                return Datatype.LARGE_INTEGER;
            case TYPE_STRING:
                return Datatype.STRING;
            case TYPE_OTHER:
                return Datatype.VARIANT;
            default:
                throw new Olap4jRuntimeException("unexpected: " + property.getType());
        }
    }

    @Override
    public Set<TypeFlag> getType() {
        return property.isCellProperty()
            ?Property.TypeFlag.CELL_TYPE_FLAG
            :Property.TypeFlag.MEMBER_TYPE_FLAG;
    }

    @Override
    public String getCaption() {
        // todo: i18n
        return property.getCaption();
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
