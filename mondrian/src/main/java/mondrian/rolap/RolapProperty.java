/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.rolap;

import mondrian.olap.Property;
import mondrian.spi.PropertyFormatter;
import org.eclipse.daanse.olap.api.MatchType;
import org.eclipse.daanse.olap.api.SchemaReader;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.rolap.mapping.api.model.SQLExpressionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;

import static mondrian.olap.Util.makeFqName;

/**
 * <code>RolapProperty</code> is the definition of a member property.
 *
 * @author jhyde
 */
public class RolapProperty extends Property implements OlapElement {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolapProperty.class);
    private final static String mdxPropertyName = "property ''{0}''";

    /** Array of RolapProperty of length 0. */
    static final RolapProperty[] emptyArray = new RolapProperty[0];

    private final PropertyFormatter formatter;
    private final String caption;
    private final boolean dependsOnLevelValue;
    private RolapLevel level;

    /** The column or expression which yields the property's value. */
    private final SQLExpressionMapping exp;

    private RolapStar.Column column = null;


    /**
     * Creates a RolapProperty.
     *
     * @param name Name of property
     * @param type Datatype
     * @param exp Expression for property's value; often a literal
     * @param formatter A property formatter, or null
     * @param caption Caption
     * @param dependsOnLevelValue Whether the property is functionally dependent
     *     on the level with which it is associated
     * @param internal Whether property is internal
     */
    RolapProperty(
        String name,
        Datatype type,
        SQLExpressionMapping exp,
        PropertyFormatter formatter,
        String caption,
        Boolean dependsOnLevelValue,
        boolean internal,
        String description,
        RolapLevel level)
    {
        super(name, type, -1, internal, false, false, description);
        this.exp = exp;
        this.caption = caption;
        this.formatter = formatter;
        this.dependsOnLevelValue =
            dependsOnLevelValue != null && dependsOnLevelValue;
        this.level = level;
    }

    public SQLExpressionMapping getExp() {
        return exp;
    }

    @Override
	public PropertyFormatter getFormatter() {
        return formatter;
    }

    /**
     * @return Returns the caption.
     */
    @Override
	public String getCaption() {
        if (caption == null) {
            return getName();
        }
        return caption;
    }

    /**
     * @return <p>Returns the dependsOnLevelValue setting (if unset,
     * returns false).  This indicates whether the property is
     * functionally dependent on the level with which it is
     * associated.</p>
     *
     * <p>If true, then the property column can be eliminated from
     * the GROUP BY clause for queries on certain databases such
     * as MySQL.</p>
     */
    public boolean dependsOnLevelValue() {
        return dependsOnLevelValue;
    }

    public RolapStar.Column getColumn() {
        return column;
    }

    public void setColumn(RolapStar.Column column) {
        this.column = column;
    }

    @Override
    public String getUniqueName() {
        if (level != null) {
            return makeFqName(level.getUniqueName(), name);
        }
        return null;
    }

    @Override
    public OlapElement lookupChild(SchemaReader schemaReader, Segment s, MatchType matchType) {
        return null;
    }

    @Override
    public String getQualifiedName() {
        if (getUniqueName() != null) {
            return MessageFormat.format(mdxPropertyName, getUniqueName());
        }
        return MessageFormat.format(mdxPropertyName, getName());
    }

    @Override
    public String getLocalized(LocalizedProperty prop, Locale locale) {
        if (level != null) {
            return level.getLocalized(prop, locale);
        }
        return null;
    }

    @Override
    public Hierarchy getHierarchy() {
        if (level != null) {
            return level.getHierarchy();
        }
        return null;
    }

    @Override
    public Dimension getDimension() {
        if (level != null) {
            return level.getDimension();
        }
        return null;
    }

    @Override
    public boolean isVisible() {
        return level != null && level.isVisible();
    }

    public RolapLevel getLevel() {
        return this.level;
    }

    public void setLevel(RolapLevel level) {
        this.level = level;
    }
}
