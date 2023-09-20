/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// Copyright (C) 2021 Sergei Semenkov
// All Rights Reserved.
*/

package mondrian.olap;

import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.OlapElement;

import mondrian.olap.api.NameSegment;
import mondrian.olap.api.Segment;
import mondrian.resource.MondrianResource;

/**
 * Skeleton implementation for {@link Hierarchy}.
 *
 * @author jhyde
 * @since 6 August, 2001
 */
public abstract class HierarchyBase
    extends OlapElementBase
    implements Hierarchy
{

    protected final Dimension dimension;
    /**
     * <code>name</code> and <code>subName</code> are the name of the
     * hierarchy, respectively containing and not containing dimension
     * name. For example:
     * <table>
     * <tr> <th>uniqueName</th>    <th>name</th>        <th>subName</th></tr>
     * <tr> <td>[Time.Weekly]</td> <td>Time.Weekly</td> <td>Weekly</td></tr>
     * <tr> <td>[Customers]</td>   <td>Customers</td>   <td>null</td></tr>
     * </table>
     *
     * <p>If {@link mondrian.olap.MondrianProperties#SsasCompatibleNaming} is
     * true, name and subName have the same value.
     */
    protected final String subName;
    protected final String name;
    protected final String uniqueName;
    protected String description;
    protected Level[] levels;
    protected final boolean hasAll;
    protected String allMemberName;
    protected String allLevelName;

    protected HierarchyBase(
        Dimension dimension,
        String subName,
        String caption,
        boolean visible,
        String description,
        boolean hasAll, boolean caseSensitive)
    {
        this.dimension = dimension;
        this.hasAll = hasAll;
        if (caption != null) {
            this.caption = caption;
        } else if (subName == null) {
            this.caption = dimension.getCaption(caseSensitive);
        } else {
            this.caption = subName;
        }
        this.description = description;
        this.visible = visible;

        String nameInner = dimension.getName(caseSensitive);
        if (MondrianProperties.instance().SsasCompatibleNaming.get()) {
            if(dimension.getDimensionType() == DimensionType.MEASURES_DIMENSION) {
                this.subName = subName;
                this.name = nameInner;
                this.uniqueName = Dimension.MEASURES_UNIQUE_NAME;
            }
            else {
                if (subName == null) {
                    // e.g. "Time"
                    subName = nameInner;
                }
                this.subName = subName;
                this.name = subName;
                this.uniqueName = Util.makeFqName(dimension, this.name);
            }
        } else {
            this.subName = subName;
            if (this.subName != null) {
                // e.g. "Time.Weekly"
                this.name = new StringBuilder(nameInner).append(".").append(subName).toString();
                if (this.subName.equals(nameInner)) {
                    this.uniqueName = dimension.getUniqueName();
                } else {
                    // e.g. "[Time.Weekly]"
                    this.uniqueName = Util.makeFqName(this.name);
                }
            } else {
                // e.g. "Time"
                this.name = nameInner;
                // e.g. "[Time]"
                this.uniqueName = dimension.getUniqueName();
            }
        }
    }

    /**
     * Returns the name of the hierarchy sans dimension name.
     *
     * @return name of hierarchy sans dimension name
     */
    public String getSubName() {
        return subName;
    }

    // implement MdxElement
    @Override
	public String getUniqueName() {
        return uniqueName;
    }

    @Override
	public String getUniqueNameSsas() {
        return Util.makeFqName(dimension, name);
    }

    @Override
	public String getName(boolean caseSensitive) {
        return name;
    }

    @Override
	public String getQualifiedName(boolean caseSensitive) {
        return MondrianResource.instance().MdxHierarchyName.str(
            getUniqueName());
    }

    public abstract boolean isRagged();

    @Override
	public String getDescription(boolean caseSensitive) {
        return description;
    }

    @Override
	public Dimension getDimension(boolean caseSensitive) {
        return dimension;
    }

    @Override
	public Level[] getLevels() {
        return levels;
    }

    @Override
	public Hierarchy getHierarchy(boolean caseSensitive) {
        return this;
    }

    @Override
	public boolean hasAll() {
        return hasAll;
    }

    @Override
	public boolean equalsOlapElement(OlapElement mdxElement) {
        // Use object identity, because a private hierarchy can have the same
        // name as a public hierarchy.
        return (this == mdxElement);
    }

    @Override
	public OlapElement lookupChild(
        SchemaReader schemaReader,
        Segment s,
        MatchType matchType)
    {
        OlapElement oe;
        if (s instanceof NameSegment nameSegment) {
            oe = Util.lookupHierarchyLevel(this, nameSegment.getName(), schemaReader.getContext().getConfig().caseSensitive());
            if (oe == null) {
                oe = Util.lookupHierarchyRootMember(
                    schemaReader, this, nameSegment, matchType);
            }
        } else {
            // Key segment searches bottom level by default. For example,
            // [Products].&[1] is shorthand for [Products].[Product Name].&[1].
            final IdImpl.KeySegment keySegment = (IdImpl.KeySegment) s;
            oe = levels[levels.length - 1]
                .lookupChild(schemaReader, keySegment, matchType);
        }

        if (getLogger().isDebugEnabled()) {
            StringBuilder buf = new StringBuilder(64);
            buf.append("HierarchyBase.lookupChild: ");
            buf.append("name=");
            buf.append(getName(schemaReader.getContext().getConfig().caseSensitive()));
            buf.append(", childname=");
            buf.append(s);
            if (oe == null) {
                buf.append(" returning null");
            } else {
                buf.append(" returning elementname=").append(oe.getName(schemaReader.getContext().getConfig().caseSensitive()));
            }
            getLogger().debug(buf.toString());
        }
        return oe;
    }

    public String getAllMemberName() {
        return allMemberName;
    }

    /**
     * Returns the name of the 'all' level in this hierarchy.
     *
     * @return name of the 'all' level
     */
    public String getAllLevelName() {
        return allLevelName;
    }
}
