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

package mondrian.olap;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.daanse.olap.api.MatchType;
import org.eclipse.daanse.olap.api.NameSegment;
import org.eclipse.daanse.olap.api.SchemaReader;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.LevelType;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
/**
 * <code>CubeBase</code> is an abstract implementation of {@link Cube}.
 *
 * @author jhyde
 * @since 6 August, 2001
 */
public abstract class CubeBase extends OlapElementBase implements Cube {

    protected final String name;
    private final String uniqueName;
    private final String description;
    protected Dimension[] dimensions;
    private final static String mdxCubeName = "cube ''{0}''";

    /**
     * Creates a CubeBase.
     *
     * @param name Name
     * @param caption Caption
     * @param description Description
     * @param dimensions List of dimensions
     */
    protected CubeBase(
        String name,
        String caption,
        boolean visible,
        String description,
        Dimension[] dimensions)
    {
        this.name = name;
        this.caption = caption;
        this.visible = visible;
        this.description = description;
        this.dimensions = dimensions;
        this.uniqueName = Util.quoteMdxIdentifier(name);
    }

    // implement OlapElement
    @Override
	public String getName() {
        return name;
    }

    @Override
	public String getUniqueName() {
        // return e.g. '[Sales Ragged]'
        return uniqueName;
    }

    @Override
	public String getQualifiedName() {
        return MessageFormat.format(mdxCubeName, getName());
    }

    @Override
	public Dimension getDimension() {
        return null;
    }

    @Override
	public Hierarchy getHierarchy() {
        return null;
    }

    @Override
	public String getDescription() {
        return description;
    }

    @Override
	public Dimension[] getDimensions() {
        return dimensions;
    }

    @Override
	public Hierarchy lookupHierarchy(NameSegment s, boolean unique) {
        for (Dimension dimension : dimensions) {
            Hierarchy[] hierarchies = dimension.getHierarchies();
            for (Hierarchy hierarchy : hierarchies) {
                String nameInner = unique
                    ? hierarchy.getUniqueName() : hierarchy.getName();
                if (nameInner.equals(s.getName())) {
                    return hierarchy;
                }
            }
        }
        return null;
    }

    @Override
	public OlapElement lookupChild(
        SchemaReader schemaReader,
        Segment s,
        MatchType matchType)
    {
        Dimension mdxDimension = lookupDimension(s);
        if (mdxDimension != null) {
            return mdxDimension;
        }

        final List<Dimension> dimensionsInner = schemaReader.getCubeDimensions(this);

        // Look for hierarchies named '[dimension.hierarchy]'.
        if (s instanceof NameSegment nameSegment) {
            Hierarchy hierarchy = lookupHierarchy(nameSegment, false);
            if (hierarchy != null) {
                return hierarchy;
            }
        }

        // Try hierarchies, levels and members.
        for (Dimension dimension : dimensionsInner) {
            OlapElement mdxElement = dimension.lookupChild(
                schemaReader, s, matchType);
            if (mdxElement != null) {
                if (mdxElement instanceof Member
                    && schemaReader.getContext().getConfig().needDimensionPrefix())
                {
                    // With this property setting, don't allow members to be
                    // referenced without at least a dimension prefix. We
                    // allow [Store].[USA].[CA] or even [Store].[CA] but not
                    // [USA].[CA].
                    continue;
                }
                return mdxElement;
            }
        }
        return null;
    }

    /**
     * Looks up a dimension in this cube based on a component of its name.
     *
     * @param s Name segment
     * @return Dimension, or null if not found
     */
    public Dimension lookupDimension(Segment s) {
        if (!(s instanceof NameSegment nameSegment)) {
            return null;
        }
        for (Dimension dimension : dimensions) {
            if (Util.equalName(dimension.getName(), nameSegment.getName())) {
                return dimension;
            }
        }
        return null;
    }

    // ------------------------------------------------------------------------

    /**
     * Returns the first level of a given type in this cube.
     *
     * @param levelType Level type
     * @return First level of given type, or null
     */
    private Level getTimeLevel(LevelType levelType) {
        for (Dimension dimension : dimensions) {
            if (dimension.getDimensionType() == DimensionType.TIME_DIMENSION) {
                Hierarchy[] hierarchies = dimension.getHierarchies();
                for (Hierarchy hierarchy : hierarchies) {
                    Level[] levels = hierarchy.getLevels();
                    for (Level level : levels) {
                        if (level.getLevelType() == levelType) {
                            return level;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
	public Level getYearLevel() {
        return getTimeLevel(LevelType.TIME_YEARS);
    }

    @Override
	public Level getQuarterLevel() {
        return getTimeLevel(LevelType.TIME_QUARTERS);
    }

    @Override
	public Level getMonthLevel() {
        return getTimeLevel(LevelType.TIME_MONTHS);
    }

    @Override
	public Level getWeekLevel() {
        return getTimeLevel(LevelType.TIME_WEEKS);
    }
}
