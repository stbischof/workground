/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2003-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// All Rights Reserved.
*/

package mondrian.rolap;

import java.util.List;
import java.util.Map;

import mondrian.olap.api.Segment;
import org.eclipse.daanse.olap.api.access.Access;
import org.eclipse.daanse.olap.api.element.Member;

import mondrian.rolap.TupleReader.MemberBuilder;
import mondrian.rolap.sql.MemberChildrenConstraint;
import mondrian.rolap.sql.TupleConstraint;

/**
 * A <code>DelegatingMemberReader</code> is a {@link MemberReader} which
 * redirects all method calls to an underlying {@link MemberReader}.
 *
 * @author jhyde
 * @since Feb 26, 2003
 */
class DelegatingMemberReader implements MemberReader {
    protected final MemberReader memberReader;

    DelegatingMemberReader(MemberReader memberReader) {
        this.memberReader = memberReader;
    }

    @Override
	public RolapMember substitute(RolapMember member, boolean caseSensitive) {
        return memberReader.substitute(member, caseSensitive);
    }

    @Override
	public RolapMember desubstitute(RolapMember member) {
        return memberReader.desubstitute(member);
    }

    @Override
	public RolapMember getMemberByKey(
        RolapLevel level, List<Comparable> keyValues)
    {
        return memberReader.getMemberByKey(level, keyValues);
    }

    @Override
	public RolapMember getLeadMember(RolapMember member, int n, boolean caseSensitive) {
        return memberReader.getLeadMember(member, n, caseSensitive);
    }

    @Override
	public List<RolapMember> getMembersInLevel(
        RolapLevel level, boolean caseSensitive)
    {
        return memberReader.getMembersInLevel(level, caseSensitive);
    }

    @Override
	public void getMemberRange(
        RolapLevel level,
        RolapMember startMember,
        RolapMember endMember,
        List<RolapMember> list, boolean caseSensitive )
    {
        memberReader.getMemberRange(level, startMember, endMember, list, caseSensitive);
    }

    @Override
	public int compare(
        RolapMember m1,
        RolapMember m2,
        boolean siblingsAreEqual, boolean caseSensitive)
    {
        return memberReader.compare(m1, m2, siblingsAreEqual, caseSensitive);
    }

    @Override
	public RolapHierarchy getHierarchy() {
        return memberReader.getHierarchy();
    }

    @Override
	public boolean setCache(MemberCache cache) {
        return memberReader.setCache(cache);
    }

    @Override
	public List<RolapMember> getMembers(boolean caseSensitive) {
        return memberReader.getMembers(caseSensitive);
    }

    @Override
	public List<RolapMember> getRootMembers(boolean caseSensitive) {
        return memberReader.getRootMembers(caseSensitive);
    }

    @Override
	public void getMemberChildren(
        RolapMember parentMember,
        List<RolapMember> children, boolean caseSensitive)
    {
        getMemberChildren(parentMember, children, null, caseSensitive);
    }

    @Override
	public Map<? extends Member, Access> getMemberChildren(
        RolapMember parentMember,
        List<RolapMember> children,
        MemberChildrenConstraint constraint, boolean caseSensitive)
    {
        return memberReader.getMemberChildren(
            parentMember, children, constraint, caseSensitive);
    }

    @Override
	public void getMemberChildren(
        List<RolapMember> parentMembers,
        List<RolapMember> children, boolean caseSensitive)
    {
        memberReader.getMemberChildren(
            parentMembers, children, caseSensitive);
    }

    @Override
	public Map<? extends Member, Access> getMemberChildren(
        List<RolapMember> parentMembers,
        List<RolapMember> children,
        MemberChildrenConstraint constraint,
        boolean caseSensitive)
    {
        return memberReader.getMemberChildren(
            parentMembers, children, constraint, caseSensitive);
    }

    @Override
	public int getMemberCount() {
        return memberReader.getMemberCount();
    }

    @Override
	public RolapMember lookupMember(
        List<Segment> uniqueNameParts,
        boolean failIfNotFound, boolean caseSensitive)
    {
        return memberReader.lookupMember(uniqueNameParts, failIfNotFound, caseSensitive);
    }

    @Override
	public List<RolapMember> getMembersInLevel(
        RolapLevel level, TupleConstraint constraint, boolean caseSensitive)
    {
        return memberReader.getMembersInLevel(
            level, constraint, caseSensitive);
    }

    @Override
	public int getLevelMemberCount(RolapLevel level) {
        return memberReader.getLevelMemberCount(level);
    }

    @Override
	public MemberBuilder getMemberBuilder() {
        return memberReader.getMemberBuilder();
    }

    @Override
	public RolapMember getDefaultMember(boolean caseSensitive) {
        return memberReader.getDefaultMember(caseSensitive);
    }

    @Override
	public RolapMember getMemberParent(RolapMember member, boolean caseSensitive) {
        return memberReader.getMemberParent(member, caseSensitive);
    }
}
