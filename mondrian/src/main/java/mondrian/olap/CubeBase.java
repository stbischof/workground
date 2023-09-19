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

import java.util.List;

import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.LevelType;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;

import mondrian.olap.api.NameSegment;
import mondrian.olap.api.Segment;
import mondrian.resource.MondrianResource;

/**
 * <code>CubeBase</code> is an abstract implementation of {@link Cube}.
 *
 * @author jhyde
 * @since 6 August, 2001
 */
public abstract class CubeBase extends OlapElementBase implements Cube {

    /** constraints indexes for adSchemaMembers
     *
     * http://msdn.microsoft.com/library/psdk/dasdk/mdx8h4k.htm
     * check "Restrictions in the MEMBER Rowset" under MEMBER Rowset section
     */
    public static final int CATALOG_NAME = 0;
    public static final int SCHEMA_NAME = 1;
    public static final int CUBE_NAME = 2;
    public static final int DIMENSION_UNIQUE_NAME = 3;
    public static final int HIERARCHY_UNIQUE_NAME = 4;
    public static final int LEVEL_UNIQUE_NAME = 5;
    public static final int LEVEL_NUMBER = 6;
    public static final int MEMBER_NAME = 7;
    public static final int MEMBER_UNIQUE_NAME = 8;
    public static final int MEMBER_CAPTION = 9;
    public static final int MEMBER_TYPE = 10;
    public static final int TREE_OPERATOR = 11;
    public static final int MAX_NOF_CONSTRAINTS_FOR_AD_SCHEMA_MEMBER = 12;
    public static final int MDTREEOP_SELF = 0;
    public static final int MDTREEOP_CHILDREN = 1;
    public static final int MDPROP_USERDEFINED0 = 19;

    protected final String name;
    private final String uniqueName;
    private final String description;
    protected Dimension[] dimensions;

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
	public String getName(boolean caseSensitive) {
        return name;
    }

    @Override
	public String getUniqueName() {
        // return e.g. '[Sales Ragged]'
        return uniqueName;
    }

    @Override
	public String getQualifiedName(boolean caseSensitive) {
        return MondrianResource.instance().MdxCubeName.str(getName(caseSensitive));
    }

    @Override
	public Dimension getDimension(boolean caseSensitive) {
        return null;
    }

    @Override
	public Hierarchy getHierarchy(boolean caseSensitive) {
        return null;
    }

    @Override
	public String getDescription(boolean caseSensitive) {
        return description;
    }

    @Override
	public Dimension[] getDimensions() {
        return dimensions;
    }

    @Override
	public Hierarchy lookupHierarchy(NameSegment s, boolean unique, boolean caseSensitive) {
        for (Dimension dimension : dimensions) {
            Hierarchy[] hierarchies = dimension.getHierarchies();
            for (Hierarchy hierarchy : hierarchies) {
                String nameInner = unique
                    ? hierarchy.getUniqueName() : hierarchy.getName(caseSensitive);
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
        boolean caseSensitive = schemaReader.getContext().getConfig().caseSensitive();
        Dimension mdxDimension = lookupDimension(s, caseSensitive);
        if (mdxDimension != null) {
            return mdxDimension;
        }

        final List<Dimension> dimensionsInner = schemaReader.getCubeDimensions(this);

        // Look for hierarchies named '[dimension.hierarchy]'.
        if (s instanceof NameSegment nameSegment) {
            Hierarchy hierarchy = lookupHierarchy(nameSegment, false, caseSensitive);
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
                    && MondrianProperties.instance().NeedDimensionPrefix.get())
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
    public Dimension lookupDimension(Segment s, boolean caseSensitive) {
        if (!(s instanceof NameSegment nameSegment)) {
            return null;
        }
        for (Dimension dimension : dimensions) {
            if (Util.equalName(dimension.getName(caseSensitive), nameSegment.getName(), caseSensitive)) {
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
