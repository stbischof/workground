/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2004-2005 TONBELLER AG
// Copyright (C) 2005-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.rolap;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.element.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.olap.MondrianProperties;
import mondrian.olap.Util;
import mondrian.rolap.sql.TupleConstraint;

/**
 * Helper class for {@link mondrian.rolap.HighCardSqlTupleReader} that
 * keeps track of target levels and constraints for adding to SQL query.
 *
 * @deprecated Deprecated for Mondrian 4.0.
 * @author luis f. canals, Kurtis Walker
 * @since July 23, 2009
 */
@Deprecated
public class Target extends TargetBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Target.class);
    private final HighCardSqlTupleReader sqlTupleReader;
    private final MemberCache cache;
    private final TupleConstraint constraint;

    boolean parentChild;
    private RolapLevel[] levels;
    private int levelDepth;

    public Target(
        final RolapLevel level,
        final TupleReader.MemberBuilder memberBuilder,
        final List<RolapMember> srcMembers,
        final TupleConstraint constraint,
        final HighCardSqlTupleReader sqlTupleReader)
    {
        super(srcMembers, level, memberBuilder);
        this.sqlTupleReader = sqlTupleReader;
        this.constraint = constraint;
        this.cache = memberBuilder.getMemberCache();
    }

    @Override
	public void open() {
        levels = (RolapLevel[]) level.getHierarchy().getLevels();
        setList(new ArrayList<>());
        levelDepth = level.getDepth();
        parentChild = level.isParentChild();
    }

    @Override
	int internalAddRow(SqlStatement stmt, int column)
        throws SQLException
    {
        RolapMember member = null;
        if (getCurrMember() != null) {
            member = getCurrMember();
        } else {
            boolean checkCacheStatus = true;
            final List<SqlStatement.Accessor> accessors = stmt.getAccessors();
            for (int i = 0; i <= levelDepth; i++) {
                RolapLevel childLevel = levels[i];
                if (childLevel.isAll()) {
                    member = memberBuilder.allMember();
                    continue;
                }

                if (childLevel.isParentChild()) {
                    column++;
                }

                Object value = accessors.get(column++).get();
                if (value == null) {
                    value = RolapUtil.sqlNullValue;
                }
                Object captionValue;
                if (childLevel.hasCaptionColumn()) {
                    captionValue = accessors.get(column++).get();
                } else {
                    captionValue = null;
                }
                RolapMember parentMember = member;
                Object key = cache.makeKey(parentMember, value);
                member = cache.getMember(key, checkCacheStatus);
                checkCacheStatus = false; /* Only check the first time */
                if (member == null) {
                    if (constraint instanceof
                        RolapNativeCrossJoin.NonEmptyCrossJoinConstraint
                        && childLevel.isParentChild())
                    {
                        member = castToNonEmptyCJConstraint(constraint)
                            .findMember(value);
                    }
                    if (member == null) {
                        member = memberBuilder.makeMember(
                            parentMember, childLevel, value, captionValue,
                            parentChild, stmt, key, column);
                    }
                }

                // Skip over the columns consumed by makeMember
                if (!childLevel.getOrdinalExp().equals(
                        childLevel.getKeyExp()))
                {
                    ++column;
                }
                column += childLevel.getProperties().length;
            }
            setCurrMember(member);
        }
        getList().add(member);
        return column;
    }

    @Override
	public List<Member> close() {
        final boolean asList = this.constraint.getEvaluator() != null
            && this.constraint.getEvaluator().getQuery().getResultStyle()
            == ResultStyle.LIST;
        final int limit = MondrianProperties.instance().ResultLimit.get();

        final List<RolapMember> l = new AbstractList<>() {
            private boolean moreRows = true;
            private int offset = 0;
            private RolapMember first = null;
            private boolean firstMemberAssigned = false;

            /**
             * Performs a load of the whole result set.
             */
            @Override
			public int size() {
                while (this.moreRows) {
                    this.moreRows = sqlTupleReader.readNextTuple();
                    if (limit > 0 && !asList && getList().size() > limit) {

                        LOGGER.debug(
                            "Target: 199, Ouch! Toooo big array...{}",
                            hashCode());
                    }
                }

                return getList().size();
            }

            @Override
			public RolapMember get(final int idx) {
                if (asList) {
                    return getList().get(idx);
                }

                if (idx == 0 && this.firstMemberAssigned) {
                    return this.first;
                }
                int index = idx - offset;

                if (0 < limit && index < 0) {
                    // Cannot send NoSuchElementException since its intercepted
                    // by AbstractSequentialList to identify out of bounds.
                    throw new RolapRuntimeException(
                        new StringBuilder("Element ").append(idx)
                        .append(" has been forgotten").toString());
                }

                while (index >= getList().size() && this.moreRows) {
                    this.moreRows = sqlTupleReader.readNextTuple();
                    if (limit > 0 && getList().size() >= limit) {
                        // We have to offset the list, but we don't want to use
                        // a 0(n) operation. What we do is we calculate an
                        // offset value corresponding to 10% of the current
                        // size of the list and use subList(), which will
                        // create a view of the sub array with an offset.
                        //
                        // We use a ~10% offset increment because we don't want
                        // to perform this operation too often and impair
                        // performance by spawning too many sublists.
                        //
                        // REVIEW: It doesn't matter right now if this code
                        // is sub-optimal. The highCardinality feature is
                        // marked for deprecation in 4.0. In practice,
                        // this code should never get executed because if
                        // you create a tuple list bigger than the value
                        // of 'limit', the code will throw an exception
                        // upstream before it ever reaches this point.
                        // For now it is sufficient. We get the speed of
                        // random access along with quick offsets.
                        int deltaOffset = Math.round((limit / 10f) + 0.5f);
                        index -= deltaOffset;
                        offset += deltaOffset;
                        setList(
                            getList().subList(
                                deltaOffset,
                                getList().size()));
                    }
                }

                if (idx == 0) {
                    this.first = getList().get(index);

                    // Above might run into exception which is caught in
                    // isEmpty(). So can change the state of the object after
                    // that.
                    this.firstMemberAssigned = true;
                    return this.first;
                } else {
                    return getList().get(index);
                }
            }

            @Override
			public RolapMember set(final int i, final RolapMember e) {
                if (asList) {
                    return getList().set(i, e);
                } else {
                    throw new UnsupportedOperationException();
                }
            }

            @Override
			public boolean isEmpty() {
                try {
                    get(0);
                    return false;
                } catch (IndexOutOfBoundsException e) {
                    return true;
                }
            }

            @Override
			public int hashCode() {
                return Target.this.hashCode();
            }

            @Override
			public Iterator<RolapMember> iterator() {
                return new Iterator<>() {
                    private int cursor = 0;

                    @Override
					public boolean hasNext() {
                        try {
                            get(cursor);
                            return true;
                        } catch (IndexOutOfBoundsException ioobe) {
                            return false;
                        }
                    }

                    @Override
					public RolapMember next() {
                        if(!hasNext()){
                            throw new NoSuchElementException();
                        }
                        return get(cursor++);
                    }

                    @Override
					public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };

        if (asList) {
            int s = l.size();
            LOGGER.debug("size {}", s);
        }

        return Util.cast(l);
    }
}
