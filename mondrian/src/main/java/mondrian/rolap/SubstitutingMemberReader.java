/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mondrian.olap.api.Segment;
import org.eclipse.daanse.olap.api.access.Access;
import org.eclipse.daanse.olap.api.element.Member;

import mondrian.rolap.sql.MemberChildrenConstraint;
import mondrian.rolap.sql.TupleConstraint;

/**
 * Implementation of {@link MemberReader} which replaces given members
 * with a substitute.
 *
 * <p>Derived classes must implement the {@link #substitute(RolapMember)} and
 * {@link #desubstitute(RolapMember)} methods.
 *
 * @author jhyde
 * @since Oct 5, 2007
 */
public abstract class SubstitutingMemberReader extends DelegatingMemberReader {
    private final TupleReader.MemberBuilder memberBuilder =
        new SubstitutingMemberBuilder();

    /**
     * Creates a SubstitutingMemberReader.
     *
     * @param memberReader Parent member reader
     */
    SubstitutingMemberReader(MemberReader memberReader) {
        super(memberReader);
    }

    // Helper methods

    private List<RolapMember> desubstitute(List<RolapMember> members) {
        List<RolapMember> list = new ArrayList<>(members.size());
        for (RolapMember member : members) {
            list.add(desubstitute(member));
        }
        return list;
    }

    private List<RolapMember> substitute(List<RolapMember> members, boolean caseSensitive) {
        List<RolapMember> list = new ArrayList<>(members.size());
        for (RolapMember member : members) {
            list.add(substitute(member, caseSensitive));
        }
        return list;
    }

    // ~ -- Implementations of MemberReader methods ---------------------------

    @Override
    public RolapMember getLeadMember(RolapMember member, int n, boolean caseSensitive) {
        return substitute(
            memberReader.getLeadMember(desubstitute(member), n, caseSensitive), caseSensitive);
    }

    @Override
    public List<RolapMember> getMembersInLevel(
        RolapLevel level, boolean caseSensitive)
    {
        return substitute(memberReader.getMembersInLevel(level, caseSensitive), caseSensitive);
    }

    @Override
    public void getMemberRange(
        RolapLevel level,
        RolapMember startMember,
        RolapMember endMember,
        List<RolapMember> list, boolean caseSensitive)
    {
        memberReader.getMemberRange(
            level,
            desubstitute(startMember),
            desubstitute(endMember),
            new SubstitutingMemberList(list), caseSensitive);
    }

    @Override
    public int compare(
        RolapMember m1,
        RolapMember m2,
        boolean siblingsAreEqual, boolean caseSensitive)
    {
        return memberReader.compare(
            desubstitute(m1),
            desubstitute(m2),
            siblingsAreEqual, caseSensitive);
    }

    @Override
    public RolapHierarchy getHierarchy() {
        return memberReader.getHierarchy();
    }

    @Override
    public boolean setCache(MemberCache cache) {
        // cache semantics don't make sense if members are not comparable
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RolapMember> getMembers(boolean caseSensitive) {
        // might make sense, but I doubt it
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RolapMember> getRootMembers(boolean caseSensitive) {
        return substitute(memberReader.getRootMembers(caseSensitive), caseSensitive);
    }

    @Override
    public void getMemberChildren(
        RolapMember parentMember,
        List<RolapMember> children, boolean caseSensitive)
    {
        memberReader.getMemberChildren(
            desubstitute(parentMember),
            new SubstitutingMemberList(children), caseSensitive);
    }

    @Override
    public void getMemberChildren(
        List<RolapMember> parentMembers,
        List<RolapMember> children, boolean caseSensitive)
    {
        memberReader.getMemberChildren(
            desubstitute(parentMembers),
            new SubstitutingMemberList(children), caseSensitive);
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
        return substitute(
            memberReader.lookupMember(uniqueNameParts, failIfNotFound, caseSensitive), caseSensitive);
    }

    @Override
	public Map<? extends Member, Access> getMemberChildren(
        RolapMember member,
        List<RolapMember> children,
        MemberChildrenConstraint constraint, boolean caseSensitive)
    {
        return memberReader.getMemberChildren(
            desubstitute(member),
            new SubstitutingMemberList(children),
            constraint, caseSensitive);
    }

    @Override
	public Map<? extends Member, Access> getMemberChildren(
        List<RolapMember> parentMembers,
        List<RolapMember> children,
        MemberChildrenConstraint constraint, boolean caseSensitive)
    {
        return memberReader.getMemberChildren(
            desubstitute(parentMembers),
            new SubstitutingMemberList(children),
            constraint, caseSensitive);
    }

    @Override
    public List<RolapMember> getMembersInLevel(
        RolapLevel level, TupleConstraint constraint, boolean caseSensitive)
    {
        return substitute(
            memberReader.getMembersInLevel(
                level, constraint, caseSensitive), caseSensitive);
    }

    @Override
    public RolapMember getDefaultMember(boolean caseSensitive) {
        return substitute(memberReader.getDefaultMember(caseSensitive), caseSensitive);
    }

    @Override
    public RolapMember getMemberParent(RolapMember member, boolean caseSensitive) {
        return substitute(memberReader.getMemberParent(desubstitute(member), caseSensitive), caseSensitive);
    }

    @Override
    public TupleReader.MemberBuilder getMemberBuilder() {
        return memberBuilder;
    }

    /**
     * List which writes through to an underlying list, substituting members
     * as they are written and desubstituting as they are read.
     */
    class SubstitutingMemberList extends AbstractList<RolapMember> {
        private final List<RolapMember> list;

        SubstitutingMemberList(List<RolapMember> list) {
            this.list = list;
        }

        @Override
        public RolapMember get(int index) {
            return desubstitute(list.get(index));
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public RolapMember set(int index, RolapMember element) {
            return desubstitute(list.set(index, substitute(element, true)));
            //TODO UTILS
        }

        @Override
        public void add(int index, RolapMember element) {
            list.add(index, substitute(element, true));
            //TODO UTILS
        }

        @Override
        public RolapMember remove(int index) {
            return list.remove(index);
        }
    }

    private class SubstitutingMemberBuilder
        implements TupleReader.MemberBuilder
    {
        @Override
		public MemberCache getMemberCache() {
            return memberReader.getMemberBuilder().getMemberCache();
        }

        @Override
		public Object getMemberCacheLock() {
            return memberReader.getMemberBuilder().getMemberCacheLock();
        }

        @Override
		public RolapMember makeMember(
            RolapMember parentMember,
            RolapLevel childLevel,
            Object value,
            Object captionValue,
            boolean parentChild,
            SqlStatement stmt,
            Object key,
            int column, boolean caseSensitive) throws SQLException
        {
            return substitute(
                memberReader.getMemberBuilder().makeMember(
                    desubstitute(parentMember),
                    childLevel,
                    value,
                    captionValue,
                    parentChild,
                    stmt,
                    key,
                    column, caseSensitive), caseSensitive);
        }

        @Override
		public RolapMember allMember(boolean caseSensitive) {
            return substitute(memberReader.getHierarchy().getAllMember(), caseSensitive);
        }
    }
}
