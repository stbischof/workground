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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.access.Access;
import org.eclipse.daanse.olap.api.element.Member;

import mondrian.olap.Util;
import mondrian.rolap.TupleReader.MemberBuilder;
import mondrian.rolap.sql.MemberChildrenConstraint;
import mondrian.rolap.sql.TupleConstraint;

import static org.eclipse.daanse.olap.api.result.Olap4jUtil.discard;

/**
 * <code>CacheMemberReader</code> implements {@link MemberReader} by reading
 * from a pre-populated array of {@link org.eclipse.daanse.olap.api.element.Member}s.
 * <p>Note: CacheMemberReader can not handle ragged hierarchies. (HR
 * Tests fail if {@link SmartMemberReader} is replaced with
 * CacheMemberReader).
 *
 * @author jhyde
 * @since 21 December, 2001
 */
class CacheMemberReader implements MemberReader, MemberCache {
    private final MemberSource source;
    private final List<RolapMember> members;
    /** Maps a {@link MemberKey} to a {@link RolapMember}. */
    private final Map<Object, RolapMember> mapKeyToMember;

    CacheMemberReader(MemberSource source) {
        this.source = source;
        if (false) {
            // we don't want the reader to write back to our cache
            discard(source.setCache(this));
        }
        this.mapKeyToMember = new HashMap<>();
        this.members = source.getMembers();
        for (int i = 0; i < members.size(); i++) {
            RolapMember member = RolapUtil.strip(members.get(i));
            ((RolapMemberBase) member).setOrdinal(i);
        }
    }

    // implement MemberReader
    @Override
	public RolapHierarchy getHierarchy() {
        return source.getHierarchy();
    }

    @Override
	public boolean setCache(MemberCache cache) {
        // we do not support cache writeback -- we must be masters of our
        // own cache
        return false;
    }

    @Override
	public RolapMember substitute(RolapMember member) {
        return member;
    }

    @Override
	public RolapMember desubstitute(RolapMember member) {
        return member;
    }

    @Override
	public RolapMember getMemberByKey(
        RolapLevel level, List<Comparable> keyValues)
    {
        assert keyValues.size() == 1;
        return mapKeyToMember.get(keyValues.get(0));
    }

    // implement MemberReader
    @Override
	public List<RolapMember> getMembers() {
        return members;
    }

    // implement MemberCache
    @Override
	public Object makeKey(RolapMember parent, Object key) {
        return new MemberKey(parent, key);
    }

    // implement MemberCache
    @Override
	public RolapMember getMember(Object key) {
        return mapKeyToMember.get(key);
    }
    @Override
	public RolapMember getMember(Object key, boolean mustCheckCacheStatus) {
        return mapKeyToMember.get(key);
    }

    // implement MemberCache
    @Override
	public Object putMember(Object key, RolapMember value) {
        return mapKeyToMember.put(key, value);
    }

    // don't need to implement this MemberCache method because we're never
    // used in a context where it is needed
    @Override
	public void putChildren(
        RolapMember member,
        MemberChildrenConstraint constraint,
        List<RolapMember> children)
    {
        throw new UnsupportedOperationException();
    }

    // don't need to implement this MemberCache method because we're never
    // used in a context where it is needed
    @Override
	public void putChildren(
        RolapLevel level,
        TupleConstraint constraint,
        List<RolapMember> children)
    {
        throw new UnsupportedOperationException();
    }

    // this cache is immutable
    @Override
	public boolean isMutable()
    {
        return false;
    }

    @Override
	public RolapMember removeMember(Object key)
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public RolapMember removeMemberAndDescendants(Object key)
    {
        throw new UnsupportedOperationException();
    }

    // don't need to implement this MemberCache method because we're never
    // used in a context where it is needed
    @Override
	public List<RolapMember> getChildrenFromCache(
        RolapMember member,
        MemberChildrenConstraint constraint)
    {
        return null;
    }

    // don't need to implement this MemberCache method because we're never
    // used in a context where it is needed
    @Override
	public List<RolapMember> getLevelMembersFromCache(
        RolapLevel level,
        TupleConstraint constraint)
    {
        return null;
    }

    @Override
	public RolapMember lookupMember(
        List<Segment> uniqueNameParts,
        boolean failIfNotFound)
    {
        return RolapUtil.lookupMember(this, uniqueNameParts, failIfNotFound);
    }

    @Override
	public List<RolapMember> getRootMembers() {
        List<RolapMember> list = new ArrayList<>();
        for (RolapMember member : members) {
            if (member.getParentMember() == null) {
                list.add(member);
            }
        }
        return list;
    }

    @Override
	public List<RolapMember> getMembersInLevel(
        RolapLevel level)
    {
        List<RolapMember> list = new ArrayList<>();
        int levelDepth = level.getDepth();
        for (RolapMember member : members) {
            if (member.getLevel().getDepth() == levelDepth) {
                list.add(member);
            }
        }
        return list;
    }

    @Override
	public List<RolapMember> getMembersInLevel(
        RolapLevel level,
        TupleConstraint constraint)
    {
        return getMembersInLevel(level);
    }

    @Override
	public int getLevelMemberCount(RolapLevel level) {
        int count = 0;
        int levelDepth = level.getDepth();
        for (Member member : members) {
            if (member.getLevel().getDepth() == levelDepth) {
                ++count;
            }
        }
        return count;
    }

    @Override
	public void getMemberChildren(
        RolapMember parentMember,
        List<RolapMember> children)
    {
        for (Member member : members) {
            if (member.getParentMember() == parentMember) {
                ((List)children).add(member);
            }
        }
    }

    @Override
	public Map<? extends Member, Access> getMemberChildren(
        RolapMember member,
        List<RolapMember> children,
        MemberChildrenConstraint constraint)
    {
        getMemberChildren(member, children);
        return Util.toNullValuesMap(children);
    }

    @Override
	public void getMemberChildren(
        List<RolapMember> parentMembers,
        List<RolapMember> children)
    {
        for (Member member : members) {
            if (parentMembers.contains(member.getParentMember())) {
                ((List)children).add(member);
            }
        }
    }

    @Override
	public Map<? extends Member, Access> getMemberChildren(
        List<RolapMember> parentMembers,
        List<RolapMember> children,
        MemberChildrenConstraint constraint)
    {
        getMemberChildren(parentMembers, children);
        return Util.toNullValuesMap(children);
    }

    @Override
	public RolapMember getLeadMember(RolapMember member, int n) {
        if (n >= 0) {
            for (int ordinal = member.getOrdinal(); ordinal < members.size();
                 ordinal++)
            {
                if ((members.get(ordinal).getLevel() == member.getLevel())
                    && (n-- == 0))
                {
                    return members.get(ordinal);
                }
            }
            return (RolapMember) member.getHierarchy().getNullMember();

        } else {
            for (int ordinal = member.getOrdinal(); ordinal >= 0; ordinal--) {
                if ((members.get(ordinal).getLevel() == member.getLevel())
                    && (n++ == 0))
                {
                    return members.get(ordinal);
                }
            }
            return (RolapMember) member.getHierarchy().getNullMember();
        }
    }

    @Override
	public void getMemberRange(
        RolapLevel level,
        RolapMember startMember,
        RolapMember endMember,
        List<RolapMember> list)
    {
        assert startMember != null;
        assert endMember != null;
        assert startMember.getLevel() == endMember.getLevel();
        final int endOrdinal = endMember.getOrdinal();
        for (int i = startMember.getOrdinal(); i <= endOrdinal; i++) {
            if (members.get(i).getLevel() == endMember.getLevel()) {
                list.add(members.get(i));
            }
        }
    }

    @Override
	public int getMemberCount() {
        return members.size();
    }

    @Override
	public int compare(
        RolapMember m1,
        RolapMember m2,
        boolean siblingsAreEqual)
    {
        if (m1 == m2) {
            return 0;
        }
        if (siblingsAreEqual
            && (m1.getParentMember() == m2.getParentMember()))
        {
            return 0;
        }
        Util.assertTrue(members.get(m1.getOrdinal()) == m1);
        Util.assertTrue(members.get(m2.getOrdinal()) == m2);

        return (m1.getOrdinal() < m2.getOrdinal()) ? -1 : 1;
    }

    @Override
	public MemberBuilder getMemberBuilder() {
        return null;
    }

    @Override
	public RolapMember getDefaultMember() {
        RolapMember defaultMember =
            (RolapMember) getHierarchy().getDefaultMember();
        if (defaultMember != null) {
            return defaultMember;
        }
        return getRootMembers().get(0);
    }

    @Override
	public RolapMember getMemberParent(RolapMember member) {
        return member.getParentMember();
    }
}
