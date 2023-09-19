/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2004-2005 TONBELLER AG
// Copyright (C) 2006-2017 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.olap.api.access.Access;
import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.olap.api.access.RollupPolicy;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.NamedSet;
import org.eclipse.daanse.olap.api.query.component.LevelExpression;
import org.eclipse.daanse.olap.api.query.component.Literal;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.api.query.component.NamedSetExpression;
import org.eclipse.daanse.olap.api.query.component.NumericLiteral;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.calc.ExpCompiler;
import mondrian.calc.TupleListCalc;
import mondrian.calc.TupleList;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.MondrianProperties;
import mondrian.olap.Util;
import mondrian.olap.fun.ParenthesesFunDef;
import mondrian.olap.fun.SetFunDef;
import mondrian.olap.fun.TupleFunDef;
import mondrian.olap.type.HierarchyType;
import mondrian.olap.type.Type;
import mondrian.rolap.RolapCalculatedMember;
import mondrian.rolap.RolapEvaluator;
import mondrian.rolap.RolapLevel;
import mondrian.rolap.RolapMember;

/**
 * Creates CrossJoinArgs for use in constraining SQL queries.
 *
 * @author kwalker
 * @since Dec 15, 2009
 */
public class CrossJoinArgFactory {
    protected static final Logger LOGGER =
        LoggerFactory.getLogger(CrossJoinArgFactory.class);
    private boolean restrictMemberTypes;

    public CrossJoinArgFactory(boolean restrictMemberTypes) {
        this.restrictMemberTypes = restrictMemberTypes;
    }

    public Set<CrossJoinArg> buildConstraintFromAllAxes(
        final RolapEvaluator evaluator, boolean caseSensitive)
    {
        Set<CrossJoinArg> joinArgs =
            new LinkedHashSet<>();
        for (QueryAxis ax : evaluator.getQuery().getAxes()) {
            List<CrossJoinArg[]> axesArgs =
                checkCrossJoinArg(evaluator, ax.getSet(), true, caseSensitive);
            if (axesArgs != null) {
                for (CrossJoinArg[] axesArg : axesArgs) {
                    joinArgs.addAll(Arrays.asList(axesArg));
                }
            }
        }
        return stripConflictingArgs(joinArgs);
    }

    /**
     * Construct a set of CJ args that exclude any duplicates.  This avoids
     * construction of conflicting constraints when the same hierarchy is
     * nested on more than one axis (e.g. when appearing in a both a
     * calculation and explicitly referenced.)
     * It's not safe to include the CJ constraint in such a case because
     * we can't guess which set of members can be used in the sql constraint.
     */
    private Set<CrossJoinArg> stripConflictingArgs(Set<CrossJoinArg> joinArgs) {
        Set<Hierarchy> skip = new HashSet<>();
        Set<Hierarchy> encountered = new HashSet<>();
        // linked to keep arg order for sql
        Set<CrossJoinArg> result = new LinkedHashSet<>();
        for (CrossJoinArg arg : joinArgs) {
            if (arg.getLevel() == null) {
                continue;
            }
            Hierarchy level =  arg.getLevel().getHierarchy();
            if (encountered.contains(level) && arg.getMembers() != null) {
                skip.add(level);
            }
            encountered.add(level);
        }
        for (CrossJoinArg arg : joinArgs) {
            if (arg.getLevel() != null
                && !skip.contains(arg.getLevel().getHierarchy()))
            {
                result.add(arg);
            }
        }
        return result;
    }

    /**
     * Scans for memberChildren, levelMembers, memberDescendants, crossJoin.
     */
    public List<CrossJoinArg[]> checkCrossJoinArg(
        RolapEvaluator evaluator,
        Exp exp, boolean caseSensitive)
    {
        return checkCrossJoinArg(evaluator, exp, false, caseSensitive);
    }

    /**
     * Checks whether an expression can be natively evaluated. The following
     * expressions can be natively evaluated:
     * <p/>
     * <ul>
     * <li>member.Children
     * <li>level.members
     * <li>descendents of a member
     * <li>member list
     * <li>filter on a dimension
     * </ul>
     *
     * @param evaluator Evaluator
     * @param exp       Expresssion
     * @return List of CrossJoinArg arrays. The first array represent the
     *         CJ CrossJoinArg and the second array represent the additional
     *         constraints.
     */
    @SuppressWarnings("java:S125")
    List<CrossJoinArg[]> checkCrossJoinArg(
        RolapEvaluator evaluator,
        Exp exp,
        final boolean returnAny, boolean caseSensitive)
    {
        if (exp instanceof NamedSetExpression namedSetExpr) {
            NamedSet namedSet = namedSetExpr.getNamedSet();
            exp = namedSet.getExp();
        }
        if (!(exp instanceof ResolvedFunCallImpl funCall)) {
            return null;
        }
        FunctionDefinition fun = funCall.getFunDef();
        Exp[] args = funCall.getArgs();

        final Role role = evaluator.getSchemaReader().getRole();
        CrossJoinArg[] cjArgs;

        cjArgs = checkMemberChildren(role, fun, args);
        if (cjArgs != null) {
            return Collections.singletonList(cjArgs);
        }
        cjArgs = checkLevelMembers(role, fun, args);
        if (cjArgs != null) {
            return Collections.singletonList(cjArgs);
        }
        cjArgs = checkDescendants(role, fun, args);
        if (cjArgs != null) {
            return Collections.singletonList(cjArgs);
        }
        final boolean exclude = false;
        cjArgs = checkEnumeration(evaluator, fun, args, exclude);
        if (cjArgs != null) {
            return Collections.singletonList(cjArgs);
        }

        if (returnAny) {
            cjArgs = checkConstrainedMeasures(evaluator, fun, args);
            if (cjArgs != null) {
                return Collections.singletonList(cjArgs);
            }
        }

        List<CrossJoinArg[]> allArgs =
            checkDimensionFilter(evaluator, fun, args, caseSensitive);
        if (allArgs != null) {
            return allArgs;
        }
        // strip off redundant set braces, for example
        // { Gender.Gender.members }, or {{{ Gender.M }}}
        if ("{}".equalsIgnoreCase(fun.getName()) && args.length == 1) {
            return checkCrossJoinArg(evaluator, args[0], returnAny, caseSensitive);
        }
        if ("NativizeSet".equalsIgnoreCase(fun.getName()) && args.length == 1) {
            return checkCrossJoinArg(evaluator, args[0], returnAny, caseSensitive);
        }
        return checkCrossJoin(evaluator, fun, args, returnAny, caseSensitive);
    }

    private CrossJoinArg[] checkConstrainedMeasures(
        RolapEvaluator evaluator, FunctionDefinition fun, Exp[] args)
    {
            if (isSetOfConstrainedMeasures(fun, args)) {
            HashMap<Dimension, List<RolapMember>> memberLists =
                new LinkedHashMap<>();
            for (Exp arg : args) {
                addConstrainingMembersToMap(arg, memberLists);
            }
            return memberListCrossJoinArgArray(memberLists, args, evaluator);
        }
        return null;
    }

    private boolean isSetOfConstrainedMeasures(FunctionDefinition fun, Exp[] args) {
        return fun.getName().equals("{}") && allArgsConstrainedMeasure(args);
    }

    private boolean allArgsConstrainedMeasure(Exp[] args) {
        for (Exp arg : args) {
            if (!isConstrainedMeasure(arg)) {
                return false;
            }
        }
        return true;
    }

    private boolean isConstrainedMeasure(Exp arg) {
        if (!(arg instanceof MemberExpression memberExpr
            && memberExpr.getMember().isMeasure()))
        {
            if (arg instanceof ResolvedFunCallImpl call && (call.getFunDef() instanceof SetFunDef
                || call.getFunDef() instanceof ParenthesesFunDef))
            {
                return allArgsConstrainedMeasure(call.getArgs());
            }
            return false;
        }
        Member member = ((MemberExpression) arg).getMember();
        if (member instanceof RolapCalculatedMember rolapCalculatedMember) {
            Exp calcExp =
                rolapCalculatedMember.getFormula().getExpression();
            return (calcExp instanceof ResolvedFunCallImpl resolvedFunCall
                && resolvedFunCall.getFunDef()
                instanceof TupleFunDef)
                || calcExp instanceof Literal;
        }
        return false;
    }

    private void addConstrainingMembersToMap(
        Exp arg, Map<Dimension, List<RolapMember>> memberLists)
    {
        if (arg instanceof ResolvedFunCallImpl call) {
            for (Exp callArg : call.getArgs()) {
                addConstrainingMembersToMap(callArg, memberLists);
            }
        }
        Exp[] tupleArgs = getCalculatedTupleArgs(arg);
        for (Exp tupleArg : tupleArgs) {
            Dimension dimension = tupleArg.getType().getDimension();

            if (tupleArg instanceof ResolvedFunCallImpl) {
                addConstrainingMembersToMap(tupleArg, memberLists);
            } else if (dimension != null && !dimension.isMeasures()) {
                List<RolapMember> members;
                if (memberLists.containsKey(dimension)) {
                    members = memberLists.get(dimension);
                } else {
                    members = new ArrayList<>();
                }
                members.add((RolapMember) ((MemberExpression) tupleArg).getMember());
                memberLists.put(dimension, members);
            } else if (isConstrainedMeasure(tupleArg)) {
                addConstrainingMembersToMap(tupleArg, memberLists);
            }
        }
    }

    private Exp[] getCalculatedTupleArgs(Exp arg) {
        if (arg instanceof MemberExpression memberExpr) {
            Member member = memberExpr.getMember();
            if (member instanceof RolapCalculatedMember rolapCalculatedMember) {
                Exp formulaExp =
                    rolapCalculatedMember
                        .getFormula().getExpression();
                if (formulaExp instanceof ResolvedFunCallImpl resolvedFunCall) {
                    return resolvedFunCall.getArgs();
                }
            }
        }
        return new Exp[0];
    }

    private CrossJoinArg[] memberListCrossJoinArgArray(
        Map<Dimension, List<RolapMember>> memberLists,
        Exp[] args,
        RolapEvaluator evaluator)
    {
        List<CrossJoinArg> argList = new ArrayList<>();
        for (List<RolapMember> memberList : memberLists.values()) {
            if (memberList.size() == countNonLiteralMeasures(args)) {
                // when the memberList and args list have the same length
                // it means there must have been a constraint on each measure
                // for this dimension.
                final CrossJoinArg cjArg =
                    MemberListCrossJoinArg.create(
                        evaluator,
                        removeDuplicates(memberList),
                        restrictMemberTypes(), false);
                if (cjArg != null) {
                    argList.add(cjArg);
                }
            }
        }
        if (!argList.isEmpty()) {
            return argList.toArray(new CrossJoinArg[argList.size()]);
        }
        return null;
    }

    private List<RolapMember> removeDuplicates(List<RolapMember> list)
    {
        Set<RolapMember> set = new HashSet<>();
        List<RolapMember> uniqueList = new ArrayList<>();
        for (RolapMember element : list) {
            if (set.add(element)) {
                uniqueList.add(element);
            }
        }
        return uniqueList;
    }

    private int countNonLiteralMeasures(Exp[] length) {
        int count = 0;
        for (Exp exp : length) {
            if (exp instanceof MemberExpression memberExpr) {
                Exp calcExp = memberExpr.getMember().getExpression();
                if (!(calcExp instanceof Literal)) {
                    count++;
                }
            } else if (exp instanceof ResolvedFunCallImpl resolvedFunCall) {
                count +=
                    countNonLiteralMeasures(resolvedFunCall.getArgs());
            }
        }
        return count;
    }

    /**
     * Checks for <code>CrossJoin(&lt;set1&gt;, &lt;set2&gt;)</code>, where
     * set1 and set2 are one of
     * <code>member.children</code>, <code>level.members</code> or
     * <code>member.descendants</code>.
     *
     * @param evaluator Evaluator to use if inputs are to be evaluated
     * @param fun       The function, either "CrossJoin" or "NonEmptyCrossJoin"
     * @param args      Inputs to the CrossJoin
     * @param returnAny indicates we should return any valid crossjoin args
     * @return array of CrossJoinArg representing the inputs
     */
    public List<CrossJoinArg[]> checkCrossJoin(
        RolapEvaluator evaluator,
        FunctionDefinition fun,
        Exp[] args,
        final boolean returnAny, boolean caseSensitive)
    {
        // is this "CrossJoin([A].children, [B].children)"
        if (!"Crossjoin".equalsIgnoreCase(fun.getName())
            && !"NonEmptyCrossJoin".equalsIgnoreCase(fun.getName()))
        {
            return null;
        }
        if (args.length != 2) {
            return null;
        }
        // Check if the arguments can be natively evaluated.
        // If not, try evaluating this argument and turning the result into
        // MemberListCrossJoinArg.
        List<CrossJoinArg[]> allArgsOneInput;
        // An array(size 2) of arrays(size arbitary). Each outer array represent
        // native inputs fro one input.
        CrossJoinArg[][] cjArgsBothInputs =
            new CrossJoinArg[2][];
        CrossJoinArg[][] predicateArgsBothInputs =
            new CrossJoinArg[2][];

        for (int i = 0; i < 2; i++) {
            allArgsOneInput = checkCrossJoinArg(evaluator, args[i], returnAny);

            if (allArgsOneInput == null
                || allArgsOneInput.isEmpty()
                || allArgsOneInput.get(0) == null)
            {
                cjArgsBothInputs[i] = expandNonNative(evaluator, args[i], caseSensitive);
            } else {
                // Collect CJ CrossJoinArg
                cjArgsBothInputs[i] = allArgsOneInput.get(0);
            }
            if (returnAny) {
                continue;
            }
            if (cjArgsBothInputs[i] == null) {
                return null;
            }

            // Collect Predicate CrossJoinArg if it exists.
            predicateArgsBothInputs[i] = null;
            if (allArgsOneInput != null && allArgsOneInput.size() == 2) {
                predicateArgsBothInputs[i] = allArgsOneInput.get(1);
            }
        }

        List<CrossJoinArg[]> allArgsBothInputs =
            new ArrayList<>();
        // Now combine the cjArgs from both sides
        CrossJoinArg[] combinedCJArgs =
            Util.appendArrays(
                cjArgsBothInputs[0] == null
                    ? CrossJoinArg.EMPTY_ARRAY
                    : cjArgsBothInputs[0],
                cjArgsBothInputs[1] == null
                    ? CrossJoinArg.EMPTY_ARRAY
                    : cjArgsBothInputs[1]);
        allArgsBothInputs.add(combinedCJArgs);

        CrossJoinArg[] combinedPredicateArgs =
            Util.appendArrays(
                predicateArgsBothInputs[0] == null
                    ? CrossJoinArg.EMPTY_ARRAY
                    : predicateArgsBothInputs[0],
                predicateArgsBothInputs[1] == null
                    ? CrossJoinArg.EMPTY_ARRAY
                    : predicateArgsBothInputs[1]);
        if (combinedPredicateArgs.length > 0) {
            allArgsBothInputs.add(combinedPredicateArgs);
        }

        return allArgsBothInputs;
    }

    /**
     * Checks for a set constructor, <code>{member1, member2,
     * &#46;&#46;&#46;}</code> that does not contain calculated members.
     *
     * @return an {@link mondrian.rolap.sql.CrossJoinArg} instance describing the enumeration,
     *         or null if <code>fun</code> represents something else.
     */
    private CrossJoinArg[] checkEnumeration(
        RolapEvaluator evaluator,
        FunctionDefinition fun,
        Exp[] args,
        boolean exclude)
    {
        // Return null if not the expected function name or input size.
        if (fun == null) {
            if (args.length != 1) {
                return null;
            }
        } else {
            if (!"{}".equalsIgnoreCase(fun.getName())
                || !isArgSizeSupported(args.length))
            {
                return null;
            }
        }

        List<RolapMember> memberList = new ArrayList<>();
        for (Exp arg : args) {
            if (!(arg instanceof MemberExpression)) {
                return null;
            }
            final Member member = ((MemberExpression) arg).getMember();
            if (member.isCalculated()
                && !member.isParentChildLeaf())
            {
                // also returns null if any member is calculated
                return null;
            }
            memberList.add((RolapMember) member);
        }

        final CrossJoinArg cjArg =
            MemberListCrossJoinArg.create(
                evaluator, memberList, restrictMemberTypes(), exclude);
        if (cjArg == null) {
            return null;
        }
        return new CrossJoinArg[]{cjArg};
    }

    private boolean restrictMemberTypes() {
        return restrictMemberTypes;
    }

    /**
     * Checks for <code>&lt;Member&gt;.Children</code>.
     *
     * @return an {@link mondrian.rolap.sql.CrossJoinArg} instance describing the member.children
     *         function, or null if <code>fun</code> represents something else.
     */
    private CrossJoinArg[] checkMemberChildren(
        Role role,
        FunctionDefinition fun,
        Exp[] args)
    {
        if (!"Children".equalsIgnoreCase(fun.getName())) {
            return null;
        }
        if (args.length != 1) {
            return null;
        }

        // Note: <Dimension>.Children is not recognized as a native expression.
        if (!(args[0] instanceof MemberExpression)) {
            return null;
        }
        RolapMember member = (RolapMember) ((MemberExpression) args[0]).getMember();
        if (member.isCalculated()) {
            return null;
        }
        RolapLevel level = member.getLevel();
        level = (RolapLevel) level.getChildLevel();
        if (level == null || !level.isSimple()) {
            // no child level
            return null;
        }
        // Children of a member in an access-controlled hierarchy cannot be
        // converted to SQL when RollupPolicy=FULL. (We could be smarter; we
        // don't currently notice when we don't look below the rolled up level
        // therefore no access-control is needed.
        final Access access = role.getAccess(level.getHierarchy());
        switch (access) {
        case ALL:
            break;
        case CUSTOM:
            final RollupPolicy rollupPolicy =
                role.getAccessDetails(level.getHierarchy()).getRollupPolicy();
            if (rollupPolicy == RollupPolicy.FULL) {
                return null;
            }
        break;
        default:
            return null;
        }
        return new CrossJoinArg[]{
            new DescendantsCrossJoinArg(level, member)
        };
    }

    /**
     * Checks for <code>&lt;Level&gt;.Members</code>.
     *
     * @return an {@link mondrian.rolap.sql.CrossJoinArg} instance describing the Level.members
     *         function, or null if <code>fun</code> represents something else.
     */
    private CrossJoinArg[] checkLevelMembers(
        Role role,
        FunctionDefinition fun,
        Exp[] args)
    {
        if (!"Members".equalsIgnoreCase(fun.getName())) {
            return null;
        }
        if (args.length != 1) {
            return null;
        }
        if (!(args[0] instanceof LevelExpression)) {
            return null;
        }
        RolapLevel level = (RolapLevel) ((LevelExpression) args[0]).getLevel();
        if (!level.isSimple()) {
            return null;
        }
        // Members of a level in an access-controlled hierarchy cannot be
        // converted to SQL when RollupPolicy=FULL. (We could be smarter; we
        // don't currently notice when we don't look below the rolled up level
        // therefore no access-control is needed.
        final Access access = role.getAccess(level.getHierarchy());
        switch (access) {
        case ALL:
            break;
        case CUSTOM:
            final RollupPolicy rollupPolicy =
                role.getAccessDetails(level.getHierarchy()).getRollupPolicy();
            if (rollupPolicy == RollupPolicy.FULL) {
                return null;
            }
        break;
        default:
            return null;
        }
        return new CrossJoinArg[]{
            new DescendantsCrossJoinArg(level, null)
        };
    }


    private static boolean isArgSizeSupported(
        int argSize)
    {
        boolean argSizeNotSupported = false;

        // Note: arg size 0 is accepted as valid CJ argument
        // This is used to push down the "1 = 0" predicate
        // into the emerging CJ so that the entire CJ can
        // be natively evaluated.

        // First check that the member list will not result in a predicate
        // longer than the underlying DB could support.
        if (argSize > MondrianProperties.instance().MaxConstraints.get()) {
            argSizeNotSupported = true;
        }

        return !argSizeNotSupported;
    }

    /**
     * Checks for Descendants(&lt;member&gt;, &lt;Level&gt;)
     *
     * @return an {@link mondrian.rolap.sql.CrossJoinArg} instance describing the Descendants
     *         function, or null if <code>fun</code> represents something else.
     */
    private CrossJoinArg[] checkDescendants(
        Role role,
        FunctionDefinition fun,
        Exp[] args)
    {
        if (!"Descendants".equalsIgnoreCase(fun.getName())) {
            return null;
        }
        if (args.length != 2) {
            return null;
        }
        if (!(args[0] instanceof MemberExpression)) {
            return null;
        }
        RolapMember member = (RolapMember) ((MemberExpression) args[0]).getMember();
        if (member.isCalculated()) {
            return null;
        }
        RolapLevel level = null;
        if ((args[1] instanceof LevelExpression)) {
            level = (RolapLevel) ((LevelExpression) args[1]).getLevel();
        } else if (args[1] instanceof NumericLiteral descendantsDepth) {
            RolapLevel[] levels = (RolapLevel[])
                member.getHierarchy().getLevels();
            int currentDepth = member.getDepth();
            int newDepth = currentDepth + descendantsDepth.getIntValue();
            if (newDepth < levels.length) {
                level = levels[newDepth];
            }
        } else {
            return null;
        }

        if (level == null || !level.isSimple()) {
            return null;
        }
        // Descendants of a member in an access-controlled hierarchy cannot be
        // converted to SQL. (We could be smarter; we don't currently notice
        // when the member is in a part of the hierarchy that is not
        // access-controlled.)
        final Access access = role.getAccess(level.getHierarchy());
        if (!Access.ALL.equals(access)) {
            return null;
        }
        return new CrossJoinArg[]{
            new DescendantsCrossJoinArg(level, member)
        };
    }

    /**
     * Check if a dimension filter can be natively evaluated.
     * Currently, these types of filters can be natively evaluated:
     * Filter(Set, Qualified Predicate)
     * where Qualified Predicate is either
     * CurrentMember reference IN {m1, m2},
     * CurrentMember reference Is m1,
     * negation(NOT) of qualified predicate
     * conjuction(AND) of qualified predicates
     * and where
     * currentmember reference is either a member or
     * ancester of a member from the context,
     *
     * @param evaluator  Evaluator
     * @param fun        Filter function
     * @param filterArgs inputs to the Filter function
     * @return a list of CrossJoinArg arrays. The first array is the CrossJoin
     *         dimensions. The second array, if any, contains additional
     *         constraints on the dimensions. If either the list or the first
     *         array is null, then native cross join is not feasible.
     */
    private List<CrossJoinArg[]> checkDimensionFilter(
        RolapEvaluator evaluator,
        FunctionDefinition fun,
        Exp[] filterArgs, boolean caseSensitive)
    {
        if (!MondrianProperties.instance().EnableNativeFilter.get()) {
            return null;
        }

        // Return null if not the expected funciton name or input size.
        if (!"Filter".equalsIgnoreCase(fun.getName())
            || filterArgs.length != 2)
        {
            return null;
        }

        // Now check filterArg[0] can be natively evaluated.
        // checkCrossJoin returns a list of CrossJoinArg arrays.
        // The first array is the CrossJoin dimensions
        // The second array, if any, contains additional constraints on the
        // dimensions. If either the list or the first array is null, then
        // native cross join is not feasible.
        List<CrossJoinArg[]> allArgs =
            checkCrossJoinArg(evaluator, filterArgs[0], caseSensitive);

        if (allArgs == null || allArgs.isEmpty() || allArgs.get(0) == null) {
            return null;
        }

        final CrossJoinArg[] cjArgs = allArgs.get(0);
        if (cjArgs == null) {
            return null;
        }

        final CrossJoinArg[] previousPredicateArgs;
        if (allArgs.size() == 2) {
            previousPredicateArgs = allArgs.get(1);
        } else {
            previousPredicateArgs = null;
        }

        // True if the Filter wants to exclude member(s)
        final boolean exclude = false;

        // Check that filterArgs[1] is a qualified predicate
        // Composites such as AND/OR are not supported at this time
        CrossJoinArg[] currentPredicateArgs;
        if (filterArgs[1] instanceof ResolvedFunCallImpl predicateCall) {
            currentPredicateArgs =
                checkFilterPredicate(evaluator, predicateCall, exclude);
        } else {
            currentPredicateArgs = null;
        }

        if (currentPredicateArgs == null) {
            return null;
        }

        // cjArgs remain the same but now there is more predicateArgs
        // Combine the previous predicate args with the current predicate args.
        LOGGER.debug("using native dimension filter");
        CrossJoinArg[] combinedPredicateArgs =
            currentPredicateArgs;

        if (previousPredicateArgs != null) {
            combinedPredicateArgs =
                Util.appendArrays(previousPredicateArgs, currentPredicateArgs);
        }

        // CJ args do not change.
        // Predicate args will grow if filter is native.
        return Arrays.asList(cjArgs, combinedPredicateArgs);
    }

    /**
     * Checks whether the filter predicate can be turned into native SQL.
     * See comment for checkDimensionFilter for the types of predicates
     * suported.
     *
     * @param evaluator     Evaluator
     * @param predicateCall Call to predicate function (ANd, NOT or parentheses)
     * @param exclude       Whether to exclude tuples that match the predicate
     * @return if filter predicate can be natively evaluated, the CrossJoinArg
     *         array representing the predicate; otherwise, null.
     */
    private CrossJoinArg[] checkFilterPredicate(
        RolapEvaluator evaluator,
        ResolvedFunCallImpl predicateCall,
        boolean exclude)
    {
        CrossJoinArg[] predicateCJArgs = null;
        if (predicateCall.getFunName().equals("()")) {
            Exp actualPredicateCall = predicateCall.getArg(0);
            if (actualPredicateCall instanceof ResolvedFunCallImpl resolvedFunCall) {
                return checkFilterPredicate(
                    evaluator, resolvedFunCall, exclude);
            } else {
                return null;
            }
        }

        if (predicateCall.getFunName().equals("NOT")
            && predicateCall.getArg(0) instanceof ResolvedFunCallImpl resolvedFunCall)
        {
            predicateCall = resolvedFunCall;
            // Flip the exclude flag
            exclude = !exclude;
            return checkFilterPredicate(evaluator, predicateCall, exclude);
        }

        if (predicateCall.getFunName().equals("AND")) {
            Exp andArg0 = predicateCall.getArg(0);
            Exp andArg1 = predicateCall.getArg(1);

            if (andArg0 instanceof ResolvedFunCallImpl andArg0ResolvedFunCall
                && andArg1 instanceof ResolvedFunCallImpl andArg1ResolvedFunCall)
            {
                CrossJoinArg[] andCJArgs0;
                CrossJoinArg[] andCJArgs1;
                andCJArgs0 =
                    checkFilterPredicate(
                        evaluator, andArg0ResolvedFunCall, exclude);
                if (andCJArgs0 != null) {
                    andCJArgs1 =
                        checkFilterPredicate(
                            evaluator, andArg1ResolvedFunCall, exclude);
                    if (andCJArgs1 != null) {
                        predicateCJArgs =
                            Util.appendArrays(andCJArgs0, andCJArgs1);
                    }
                }
            }
            // predicateCJArgs is either initialized or null
            return predicateCJArgs;
        }

        // Now check the broken down predicate clause.
        predicateCJArgs =
            checkFilterPredicateInIs(evaluator, predicateCall, exclude);
        return predicateCJArgs;
    }

    /**
     * Check whether the predicate is an IN or IS predicate and can be
     * natively evaluated.
     *
     * @return the array of CrossJoinArg containing the predicate.
     */
    private CrossJoinArg[] checkFilterPredicateInIs(
        RolapEvaluator evaluator,
        ResolvedFunCallImpl predicateCall,
        boolean exclude)
    {
        final boolean useIs;
        if (predicateCall.getFunName().equals("IS")) {
            useIs = true;
        } else if (predicateCall.getFunName().equals("IN")) {
            useIs = false;
        } else {
            // Neither IN nor IS
            // This predicate can not be natively evaluated.
            return null;
        }

        Exp[] predArgs = predicateCall.getArgs();
        if (predArgs.length != 2) {
            return null;
        }

        // Check that predArgs[0] is a ResolvedFuncCall while FunDef is:
        //   DimensionCurrentMemberFunDef
        //   HierarchyCurrentMemberFunDef
        //   or Ancestor of those functions.
        if (!(predArgs[0] instanceof ResolvedFunCallImpl predFirstArgCall)) {
            return null;
        }

        if (predFirstArgCall.getFunDef().getName().equals("Ancestor")) {
            Exp[] ancestorArgs = predFirstArgCall.getArgs();

            if (!(ancestorArgs[0] instanceof ResolvedFunCallImpl)) {
                return null;
            }

            predFirstArgCall = (ResolvedFunCallImpl) ancestorArgs[0];
        }

        // Now check that predFirstArgCall is a CurrentMember function that
        // refers to the dimension being filtered
        FunctionDefinition predFirstArgFun = predFirstArgCall.getFunDef();
        if (!predFirstArgFun.getName().equals("CurrentMember")) {
            return null;
        }

        Exp currentMemberArg = predFirstArgCall.getArg(0);
        Type currentMemberArgType = currentMemberArg.getType();

        // Input to CurremntMember should be either Dimension or Hierarchy type.
        if (!(currentMemberArgType
            instanceof mondrian.olap.type.DimensionType
            || currentMemberArgType instanceof HierarchyType))
        {
            return null;
        }

        // It is not necessary to check currentMemberArg comes from the same
        // dimension as one of the filterCJArgs, because query parser makes sure
        // that currentMember always references dimensions in context.

        // Check that predArgs[1] can be expressed as an MemberListCrossJoinArg.
        Exp predSecondArg = predArgs[1];
        Exp[] predSecondArgList;
        FunctionDefinition predSecondArgFun;
        CrossJoinArg[] predCJArgs;

        if (useIs) {
            // IS operator
            if (!(predSecondArg instanceof MemberExpression)) {
                return null;
            }

            // IS predicate only contains one member
            // Make it into a list to be uniform with IN predicate.
            predSecondArgFun = null;
            predSecondArgList = new Exp[]{predSecondArg};
        } else {
            // IN operator
            if (predSecondArg instanceof NamedSetExpression namedSetExpr) {
                NamedSet namedSet =
                    namedSetExpr.getNamedSet();
                predSecondArg = namedSet.getExp();
            }

            if (!(predSecondArg instanceof ResolvedFunCallImpl predSecondArgCall)) {
                return null;
            }

            predSecondArgFun = predSecondArgCall.getFunDef();
            predSecondArgList = predSecondArgCall.getArgs();
        }

        predCJArgs =
            checkEnumeration(
                evaluator, predSecondArgFun, predSecondArgList, exclude);
        return predCJArgs;
    }

    private CrossJoinArg[] expandNonNative(
        RolapEvaluator evaluator,
        Exp exp, boolean caseSensitive)
    {
        ExpCompiler compiler = evaluator.getQuery().createCompiler();
        CrossJoinArg[] arg0 = null;
        if (shouldExpandNonEmpty(exp)
            && evaluator.getActiveNativeExpansions().add(exp))
        {
            TupleListCalc listCalc0 = compiler.compileList(exp, caseSensitive);
            final TupleList tupleList = listCalc0.evaluateList(evaluator, caseSensitive);

            // Prevent the case when the second argument size is too large
            Util.checkCJResultLimit(tupleList.size());

            if (tupleList.getArity() == 1) {
                List<RolapMember> list0 =
                    Util.cast(tupleList.slice(0));
                CrossJoinArg arg =
                    MemberListCrossJoinArg.create(
                        evaluator, list0, restrictMemberTypes(), false);
                if (arg != null) {
                    arg0 = new CrossJoinArg[]{arg};
                }
            }
            evaluator.getActiveNativeExpansions().remove(exp);
        }
        return arg0;
    }

    private boolean shouldExpandNonEmpty(Exp exp) {
        return MondrianProperties.instance().ExpandNonNative.get()
//               && !MondrianProperties.instance().EnableNativeCrossJoin.get()
            || isCheapSet(exp);
    }

    private boolean isCheapSet(Exp exp) {
        return isSet(exp) && allArgsCheapToExpand(exp);
    }

    private static final List<String> cheapFuns =
        Arrays.asList("LastChild", "FirstChild", "Lag");

    private boolean allArgsCheapToExpand(Exp exp) {
        while (exp instanceof NamedSetExpression namedSetExpr) {
            exp = namedSetExpr.getNamedSet().getExp();
        }
        for (Exp arg : ((ResolvedFunCallImpl) exp).getArgs()) {
            if (arg instanceof ResolvedFunCallImpl resolvedFunCall) {
                if (!cheapFuns.contains(resolvedFunCall.getFunName())) {
                    return false;
                }
            } else if (!(arg instanceof MemberExpression)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSet(Exp exp) {
        return ((exp instanceof ResolvedFunCallImpl resolvedFunCall)
            && resolvedFunCall.getFunName().equals("{}"))
            || (exp instanceof NamedSetExpression);
    }
}
