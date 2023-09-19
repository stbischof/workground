/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2005-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// All Rights Reserved.
*/

package mondrian.olap.type;

import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;

import mondrian.olap.Util;

/**
 * The type of an expression which represents a member.
 *
 * @author jhyde
 * @since Feb 17, 2005
 */
public class MemberType implements Type {
    private final Hierarchy hierarchy;
    private final Dimension dimension;
    private final Level level;
    private final Member member;
    private final String digest;
    private final boolean caseSensitive;

    public static final MemberType Unknown =
        new MemberType(null, null, null, null, true);

    /**
     * Creates a type representing a member.
     *
     * @param dimension Dimension the member belongs to, or null if not known
     * @param hierarchy Hierarchy the member belongs to, or null if not known
     * @param level Level the member belongs to, or null if not known
     * @param member The precise member, or null if not known
     */
    public MemberType(
        Dimension dimension,
        Hierarchy hierarchy,
        Level level,
        Member member, boolean caseSensitive)
    {
        this.dimension = dimension;
        this.hierarchy = hierarchy;
        this.level = level;
        this.member = member;
        this.caseSensitive = caseSensitive;
        if (member != null) {
            Util.assertPrecondition(level != null);
            Util.assertPrecondition(member.getLevel() == level);
        }
        if (level != null) {
            Util.assertPrecondition(hierarchy != null);
            Util.assertPrecondition(level.getHierarchy(caseSensitive) == hierarchy);
        }
        if (hierarchy != null) {
            Util.assertPrecondition(dimension != null);
            Util.assertPrecondition(hierarchy.getDimension(caseSensitive) == dimension);
        }
        StringBuilder buf = new StringBuilder("MemberType<");
        if (member != null) {
            buf.append("member=").append(member.getUniqueName());
        } else if (level != null) {
            buf.append("level=").append(level.getUniqueName());
        } else if (hierarchy != null) {
            buf.append("hierarchy=").append(hierarchy.getUniqueName());
        } else if (dimension != null) {
            buf.append("dimension=").append(dimension.getUniqueName());
        }
        buf.append(">");
        this.digest = buf.toString();
    }

    public static MemberType forDimension(Dimension dimension, boolean caseSensitive) {
        return new MemberType(dimension, null, null, null, caseSensitive);
    }

    public static MemberType forHierarchy(Hierarchy hierarchy, boolean caseSensitive) {
        final Dimension dimension;
        if (hierarchy == null) {
            dimension = null;
        } else {
            dimension = hierarchy.getDimension(caseSensitive);
        }
        return new MemberType(dimension, hierarchy, null, null, caseSensitive);
    }

    public static MemberType forLevel(Level level, boolean caseSensitive) {
        final Dimension dimension;
        final Hierarchy hierarchy;
        if (level == null) {
            dimension = null;
            hierarchy = null;
        } else {
            dimension = level.getDimension(caseSensitive);
            hierarchy = level.getHierarchy(caseSensitive);
        }
        return new MemberType(dimension, hierarchy, level, null, caseSensitive);
    }

    public static MemberType forMember(Member member, boolean caseSensitive) {
        final Dimension dimension;
        final Hierarchy hierarchy;
        final Level level;
        if (member == null) {
            dimension = null;
            hierarchy = null;
            level = null;
        } else {
            dimension = member.getDimension(caseSensitive);
            hierarchy = member.getHierarchy(caseSensitive);
            level = member.getLevel();
        }
        return new MemberType(dimension, hierarchy, level, member, caseSensitive);
    }

    @Override
	public String toString() {
        return digest;
    }

    @Override
	public Hierarchy getHierarchy(boolean caseSensitive) {
        return hierarchy;
    }

    @Override
	public Level getLevel() {
        return level;
    }

    public Member getMember() {
        return member;
    }

    @Override
	public boolean usesDimension(Dimension dimension, boolean definitely) {
        return this.dimension == dimension
            || (!definitely && this.dimension == null);
    }

    @Override
	public boolean usesHierarchy(Hierarchy hierarchy, boolean definitely) {
        return this.hierarchy == hierarchy
            || (!definitely
                && this.hierarchy == null
                && (this.dimension == null
                    || this.dimension == hierarchy.getDimension(caseSensitive)));
    }

    public Type getValueType() {
        // todo: when members have more type information (double vs. integer
        // vs. string), return better type if member != null.
        return new ScalarType();
    }

    @Override
	public Dimension getDimension() {
        return dimension;
    }

    public static MemberType forType(Type type, boolean caseSensitive) {
        if (type instanceof MemberType) {
            return (MemberType) type;
        } else {
            return new MemberType(
                type.getDimension(),
                type.getHierarchy(caseSensitive),
                type.getLevel(),
                null, caseSensitive);
        }
    }

    @Override
	public Type computeCommonType(Type type, int[] conversionCount, boolean caseSensitive) {
        if (type instanceof ScalarType) {
            return getValueType().computeCommonType(type, conversionCount, caseSensitive);
        }
        if (type instanceof TupleType) {
            return type.computeCommonType(this, conversionCount, caseSensitive);
        }
        if (!(type instanceof MemberType that)) {
            return null;
        }
        if (this.getMember() != null
            && this.getMember().equals(that.getMember()))
        {
            return this;
        }
        if (this.getLevel() != null
            && this.getLevel().equals(that.getLevel()))
        {
            return new MemberType(
                this.getDimension(),
                this.getHierarchy(caseSensitive),
                this.getLevel(),
                null, caseSensitive);
        }
        if (this.getHierarchy(caseSensitive) != null
            && this.getHierarchy(caseSensitive).equals(that.getHierarchy(caseSensitive)))
        {
            return new MemberType(
                this.getDimension(),
                this.getHierarchy(caseSensitive),
                null,
                null, caseSensitive);
        }
        if (this.getDimension() != null
            && this.getDimension().equals(that.getDimension()))
        {
            return new MemberType(
                this.getDimension(),
                null,
                null,
                null, caseSensitive);
        }
        return MemberType.Unknown;
    }

    @Override
	public boolean isInstance(Object value, boolean caseSensitive) {
        return value instanceof Member
            && (level == null
            || ((Member) value).getLevel().equals(level))
            && (hierarchy == null
            || ((Member) value).getHierarchy(caseSensitive).equals(hierarchy))
            && (dimension == null
            || ((Member) value).getDimension(caseSensitive).equals(dimension));
    }

    @Override
	public int getArity() {
        return 1;
    }
}
