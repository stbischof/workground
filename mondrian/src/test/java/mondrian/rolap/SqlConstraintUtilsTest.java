/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2003-2005 Julian Hyde
// Copyright (C) 2005-2020 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.DataType;
import org.eclipse.daanse.olap.api.Evaluator;
import org.eclipse.daanse.olap.api.Evaluator.SetEvaluator;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.function.FunctionDefinition;
import org.eclipse.daanse.olap.api.function.FunctionMetaData;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.eclipse.daanse.olap.api.type.Type;
import org.eclipse.daanse.olap.calc.api.todo.TupleIterable;
import org.eclipse.daanse.olap.calc.api.todo.TupleList;
import org.eclipse.daanse.olap.operation.api.FunctionOperationAtom;
import org.eclipse.daanse.olap.operation.api.OperationAtom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalog;

import mondrian.calc.impl.AbstractTupleCursor;
import mondrian.calc.impl.UnaryTupleList;
import mondrian.mdx.MemberExpressionImpl;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.fun.AggregateFunDef;
import mondrian.olap.fun.CrossJoinTest.NullFunDef;
import mondrian.olap.fun.ParenthesesFunDef;
import mondrian.olap.fun.TestMember;
import mondrian.olap.type.DecimalType;
import mondrian.olap.type.NullType;
import mondrian.olap.type.TupleType;
import mondrian.rolap.aggmatcher.AggStar;
import mondrian.rolap.sql.SqlQuery;
import mondrian.server.Execution;

/**
 * <code>SqlConstraintUtilsTest</code> tests the functions defined in
 * {@link SqlConstraintUtils}.
 *
 */
class SqlConstraintUtilsTest {

    private void assertSameContent(
        String msg, Collection<Member> expected, Collection<Member> actual)
    {
        if (expected == null) {
            assertEquals(expected, actual, msg);
        }
        assertEquals(expected.size(), actual.size(), msg + " size");
        Iterator<Member> itExpected = expected.iterator();
        Iterator<Member> itActual = actual.iterator();
        for (int i = 0; itExpected.hasNext(); i++) {
            assertEquals(
                itActual.next(), itExpected.next(), msg + " [" + i + "]");
        }
    }

    private void assertSameContent(
        String msg, List<Member> expected, Member[] actual)
    {
      assertSameContent(msg, expected, Arrays.asList(actual));
    }

    /**
    * Used to suppress a series of asserts on
    * {@code SqlConstraintUtils.expandSupportedCalculatedMembers}
    * when they are supposed to result identically.
    * @param msg message for asserts
    * @param expectedMembersArray expected result
    * @param argMembersArray passed to the tested method
    * @param evaluator passed to the tested method
    */

    private void assertEveryExpandSupportedCalculatedMembers(
        String msg, Member[] expectedMembersArray, Member[] argMembersArray,
        Evaluator evaluator)
    {
      final List<Member> expectedMembersList =
          Collections.unmodifiableList(Arrays.asList(expectedMembersArray));
      final List<Member> argMembersList =
          Collections.unmodifiableList(Arrays.asList(argMembersArray));
      assertSameContent(
          msg + " - (list, eval)",
          expectedMembersList,
          SqlConstraintUtils.expandSupportedCalculatedMembers(
              argMembersList, evaluator).getMembers());
      assertSameContent(
          msg + " - (list, eval, false)",
          expectedMembersList,
          SqlConstraintUtils.expandSupportedCalculatedMembers(
              argMembersList, evaluator, false).getMembers());
      assertSameContent(
          msg + " - (list, eval, true)",
          expectedMembersList,
          SqlConstraintUtils.expandSupportedCalculatedMembers(
              argMembersList, evaluator, true).getMembers());
    }

    private void assertApartExpandSupportedCalculatedMembers(
        String msg,
        Member[] expectedByDefault,
        Member[] expectedOnDisjoint,
        Member[] argMembersArray,
        Evaluator evaluator)
    {
      final List<Member> expectedListOnDisjoin =
          Collections.unmodifiableList(Arrays.asList(expectedOnDisjoint));
      final List<Member> expectedListByDefault =
          Collections.unmodifiableList(Arrays.asList(expectedByDefault));
      final List<Member> argMembersList =
          Collections.unmodifiableList(Arrays.asList(argMembersArray));
      assertSameContent(
          msg + " - (list, eval)",
          expectedListByDefault,
          SqlConstraintUtils.expandSupportedCalculatedMembers(
              argMembersList, evaluator).getMembers());
      assertSameContent(
          msg + " - (list, eval, false)",
          expectedListByDefault,
          SqlConstraintUtils.expandSupportedCalculatedMembers(
              argMembersList, evaluator, false).getMembers());
      assertSameContent(
          msg + " - (list, eval, true)",
          expectedListOnDisjoin,
          SqlConstraintUtils.expandSupportedCalculatedMembers(
              argMembersList, evaluator, true).getMembers());
    }

    private Member makeNoncalculatedMember(String toString) {
        Member member = Mockito.mock(Member.class);
        assertEquals(false, member.isCalculated());
        Mockito.doReturn("mock[" + toString + "]").when(member).toString();
        return member;
    }

    private Expression makeSupportedExpressionForCalculatedMember() {
        Expression memberExpr = new MemberExpressionImpl(Mockito.mock(Member.class));
        assertEquals(
            true,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                memberExpr));
        return memberExpr;
    }

    private Expression makeUnsupportedExpressionForCalculatedMember() {
        Expression nullFunDefExpr = new ResolvedFunCallImpl(
            new NullFunDef(), new Expression[]{}, NullType.INSTANCE);
        assertEquals(
            false,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                nullFunDefExpr));
        return nullFunDefExpr;
    }

    private Member makeUnsupportedCalculatedMember(String toString) {
        Expression memberExp = makeUnsupportedExpressionForCalculatedMember();
        Member member = Mockito.mock(Member.class);
        Mockito.doReturn("mock[" + toString + "]").when(member).toString();
        Mockito.doReturn(true).when(member).isCalculated();
        Mockito.doReturn(memberExp).when(member).getExpression();

        assertEquals(true, member.isCalculated());
        assertEquals(
            false, SqlConstraintUtils.isSupportedCalculatedMember(member));

        return member;
    }

    private Member makeMemberExprMember(Member resultMember) {
        Expression memberExp = new MemberExpressionImpl(resultMember);
        Member member = Mockito.mock(Member.class);
        Mockito.doReturn(true).when(member).isCalculated();
        Mockito.doReturn(memberExp).when(member).getExpression();
        return member;
    }

    private Member makeAggregateExprMember(
        Evaluator mockEvaluator, List<Member> endMembers)
    {
        Member member = Mockito.mock(Member.class);
        Mockito.doReturn(true).when(member).isCalculated();

        Member aggregatedMember0 = Mockito.mock(Member.class);
        Expression aggregateArg0 = new MemberExpressionImpl(aggregatedMember0);

        FunctionMetaData functionInformation = Mockito.mock(FunctionMetaData.class);
        OperationAtom functionAtom = new FunctionOperationAtom("dummy");


        Mockito.doReturn(functionAtom).when(functionInformation).operationAtom();


        FunctionDefinition funDef = new AggregateFunDef(functionInformation);
        Expression[] args = new Expression[]{aggregateArg0};
        Type returnType = new DecimalType(1, 1);
        Expression memberExp = new ResolvedFunCallImpl(funDef, args, returnType);

        Mockito.doReturn(memberExp).when(member).getExpression();

        SetEvaluator setEvaluator = Mockito.mock(SetEvaluator.class);
        Mockito.doReturn(setEvaluator)
            .when(mockEvaluator).getSetEvaluator(aggregateArg0, true);
        Mockito.doReturn(
            new UnaryTupleList(endMembers))
            .when(setEvaluator).evaluateTupleIterable();

        assertEquals(true, member.isCalculated());
        assertEquals(
            true, SqlConstraintUtils.isSupportedCalculatedMember(member));

        return member;
    }

    private Member makeParenthesesExprMember(
        Evaluator evaluator, Member parenthesesInnerMember, String toString)
    {
        Member member = Mockito.mock(Member.class);
        Mockito.doReturn("mock[" + toString + "]").when(member).toString();
        Mockito.doReturn(true).when(member).isCalculated();

        Expression parenthesesArg = new MemberExpressionImpl(parenthesesInnerMember);

        FunctionDefinition funDef = new ParenthesesFunDef(DataType.MEMBER);
        Expression[] args = new Expression[]{parenthesesArg};
        Type returnType = new DecimalType(1, 1);
        Expression memberExp = new ResolvedFunCallImpl(funDef, args, returnType);

        Mockito.doReturn(memberExp).when(member).getExpression();

        assertEquals(true, member.isCalculated());
        assertEquals(
            true, SqlConstraintUtils.isSupportedCalculatedMember(member));

        return member;
    }

    // ~ Test methods ----------------------------------------------------------
    @Test
    void testIsSupportedExpressionForCalculatedMember() {
        assertEquals(
            false,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(null),
            "null expression");

        Expression memberExpr = new MemberExpressionImpl(Mockito.mock(Member.class));
        assertEquals(
            true,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                memberExpr), "MemberExpr");

        Expression nullFunDefExpr = new ResolvedFunCallImpl(
            new NullFunDef(), new Expression[]{}, NullType.INSTANCE);
        assertEquals(
            false,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                nullFunDefExpr), "ResolvedFunCall-NullFunDef");

        // ResolvedFunCall arguments
        final Expression argUnsupported = new ResolvedFunCallImpl(
            new NullFunDef(), new Expression[]{}, NullType.INSTANCE);
        final Expression argSupported = new MemberExpressionImpl(Mockito.mock(Member.class));
        assertEquals(
            false,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                argUnsupported));
        assertEquals(
            true,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                argSupported));
        final Expression[] noArgs = new Expression[]{};
        final Expression[] args1Unsupported = new Expression[]{argUnsupported};
        final Expression[] args1Supported = new Expression[]{argSupported};
        final Expression[] args2Different = new Expression[]{argUnsupported, argSupported};

        final ParenthesesFunDef parenthesesFunDef =
            new ParenthesesFunDef(DataType.MEMBER);
        Type parenthesesReturnType = new DecimalType(1, 1);
        Expression parenthesesExpr = new ResolvedFunCallImpl(
            parenthesesFunDef, noArgs, parenthesesReturnType);
        assertEquals(
            true,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                parenthesesExpr),
                "ResolvedFunCall-Parentheses()");

        parenthesesExpr = new ResolvedFunCallImpl(
            parenthesesFunDef, args1Unsupported, parenthesesReturnType);
        assertEquals(
            false, SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                parenthesesExpr),
                "ResolvedFunCall-Parentheses(N)");

        parenthesesExpr = new ResolvedFunCallImpl(
            parenthesesFunDef, args1Supported, parenthesesReturnType);
        assertEquals(
            true, SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                parenthesesExpr),  "ResolvedFunCall-Parentheses(Y)");

        parenthesesExpr = new ResolvedFunCallImpl(
            parenthesesFunDef, args2Different, parenthesesReturnType);
        assertEquals(
            true, SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                parenthesesExpr), "ResolvedFunCall-Parentheses(N,Y)");

        FunctionMetaData functionInformation = Mockito.mock(FunctionMetaData.class);
        OperationAtom functionAtom = new FunctionOperationAtom("dummy");

        Mockito.doReturn(functionAtom).when(functionInformation).operationAtom();
        FunctionDefinition aggregateFunDef = new AggregateFunDef(functionInformation);
        Type aggregateReturnType = new DecimalType(1, 1);

        Expression aggregateExpr = new ResolvedFunCallImpl(
            aggregateFunDef, noArgs, aggregateReturnType);
        assertEquals(
            true, SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                aggregateExpr), "ResolvedFunCall-Aggregate()");

        aggregateExpr = new ResolvedFunCallImpl(
            aggregateFunDef, args1Unsupported, aggregateReturnType);
        assertEquals(
            true, SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                aggregateExpr), "ResolvedFunCall-Aggregate(N)");

        aggregateExpr = new ResolvedFunCallImpl(
            aggregateFunDef, args1Supported, aggregateReturnType);
        assertEquals(
            true, SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                aggregateExpr), "ResolvedFunCall-Aggregate(Y)");

        aggregateExpr = new ResolvedFunCallImpl(
            aggregateFunDef, args2Different, aggregateReturnType);
        assertEquals(
            true,
            SqlConstraintUtils.isSupportedExpressionForCalculatedMember(
                aggregateExpr), "ResolvedFunCall-Aggregate(N,Y)");
    }

    @Test
    void testIsSupportedCalculatedMember() {
        Member member = Mockito.mock(Member.class);
        assertEquals(false, member.isCalculated());
        assertEquals(
            false, SqlConstraintUtils.isSupportedCalculatedMember(member));

        Mockito.doReturn(true).when(member).isCalculated();

        assertEquals(null, member.getExpression());
        assertEquals(
            false, SqlConstraintUtils.isSupportedCalculatedMember(member));

        Mockito.doReturn(makeUnsupportedExpressionForCalculatedMember())
        .when(member).getExpression();
        assertEquals(
            false, SqlConstraintUtils.isSupportedCalculatedMember(member));

        Mockito.doReturn(makeSupportedExpressionForCalculatedMember())
        .when(member).getExpression();
        assertEquals(
            true, SqlConstraintUtils.isSupportedCalculatedMember(member));
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class)
    void testReplaceCompoundSlicerPlaceholder(Context context) {
        final Connection connection = context.getConnection();

        final String queryText =
            "SELECT {[Measures].[Customer Count]} ON 0 "
            + "FROM [Sales] "
            + "WHERE [Time].[1997]";

        final Query query = connection.parseQuery(queryText);
        final QueryAxis querySlicerAxis = query.getSlicerAxis();
        final Member slicerMember =
            ((MemberExpression)querySlicerAxis.getSet()).getMember();
        final RolapHierarchy slicerHierarchy =
            ((RolapCube)query.getCube()).getTimeHierarchy(null);

        final Execution execution = new Execution(query.getStatement(), 0L);
        final RolapEvaluatorRoot rolapEvaluatorRoot =
            new RolapEvaluatorRoot(execution);
        final RolapEvaluator rolapEvaluator =
            new RolapEvaluator(rolapEvaluatorRoot);
        final Member expectedMember = slicerMember;
        setSlicerContext(rolapEvaluator, expectedMember);

        RolapResult.CompoundSlicerRolapMember placeHolderMember =
            Mockito.mock(RolapResult.CompoundSlicerRolapMember.class);
        Mockito.doReturn(slicerHierarchy)
        .when(placeHolderMember).getHierarchy();
        // tested call
        Member r = SqlConstraintUtils.replaceCompoundSlicerPlaceholder(
            placeHolderMember, rolapEvaluator);
        // test
        assertSame(expectedMember, r);
    }

    @Test
    void testExpandSupportedCalculatedMember_notCalculated() {
        // init
        Evaluator evaluator = Mockito.mock(Evaluator.class);

        Member member = makeNoncalculatedMember("0");

        // tested call
        TupleConstraintStruct constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        List<Member> r = constraint.getMembers();
        // test
        assertNotNull(r);
        assertEquals(1, r.size());
        assertSame(member, r.get(0));
    }


    @Test
    void testExpandSupportedCalculatedMember_calculated_unsupported() {
        Evaluator evaluator = Mockito.mock(Evaluator.class);

        Member member = makeUnsupportedCalculatedMember("0");

        // tested call
        TupleConstraintStruct constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        List<Member> r = constraint.getMembers();
        // test
        assertNotNull(r);
        assertEquals(1, r.size());
        assertSame(member, r.get(0));
    }

    @Test
    void testExpandSupportedCalculatedMember_calculated_memberExpr() {
        Evaluator evaluator = Mockito.mock(Evaluator.class);

        Member resultMember = makeNoncalculatedMember("0");
        Member member = makeMemberExprMember(resultMember);

        // tested call
        TupleConstraintStruct constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        List<Member> r = constraint.getMembers();
        // test
        assertNotNull(r);
        assertEquals(1, r.size());
        assertSame(resultMember, r.get(0));
    }

    @Test
    void testExpandSupportedCalculatedMember_calculated_aggregate() {
        Evaluator evaluator = Mockito.mock(Evaluator.class);

        Member endMember0 = Mockito.mock(Member.class);
        Member endMember1 = Mockito.mock(Member.class);
        Member endMember2 = Mockito.mock(Member.class);

        Member member = null;
        List<Member> r = null;
        List<Member> aggregatedMembers = null;

        // 0
        aggregatedMembers = Collections.emptyList();
        member = makeAggregateExprMember(evaluator, aggregatedMembers);
        // tested call
        TupleConstraintStruct constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, true, constraint);
        r = constraint.getMembers();
        // test
        assertSameContent("",  aggregatedMembers, r);

        // 1
        aggregatedMembers = Collections.singletonList(endMember0);
        member = makeAggregateExprMember(evaluator, aggregatedMembers);
        // tested call
        constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        r = constraint.getMembers();
        // test
        assertSameContent("",  aggregatedMembers, r);

        // 2
        aggregatedMembers = Arrays.asList(
            new Member[] {endMember0, endMember1});
        member = makeAggregateExprMember(evaluator, aggregatedMembers);
        // tested call
        constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        r = constraint.getMembers();
        // test
        assertSameContent("",  aggregatedMembers, r);

        // 3
        aggregatedMembers = Arrays.asList(
            new Member[] {endMember0, endMember1, endMember2});
        member = makeAggregateExprMember(evaluator, aggregatedMembers);
        // tested call
        constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        r = constraint.getMembers();
        // test
        assertSameContent("",  aggregatedMembers, r);
    }

    @Test
    void testExpandSupportedCalculatedMember_calculated_parentheses() {
        Evaluator evaluator = Mockito.mock(Evaluator.class);

        Member resultMember = Mockito.mock(Member.class);
        Member member = this.makeParenthesesExprMember(
            evaluator, resultMember, "0");

        // tested call
        TupleConstraintStruct constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSupportedCalculatedMember(
            member, evaluator, constraint);
        List<Member> r = constraint.getMembers();
        // test
        assertNotNull(r);
        assertEquals(1, r.size());
        assertSame(resultMember, r.get(0));
    }

    @Test
    void testExpandSupportedCalculatedMembers() {
        Evaluator evaluator = Mockito.mock(Evaluator.class);

        Member endMember0 = Mockito.mock(Member.class);
        Member endMember1 = Mockito.mock(Member.class);
        Member endMember2 = Mockito.mock(Member.class);
        Member endMember3 = Mockito.mock(Member.class);

        Member argMember0 = null;
        Member argMember1 = null;

        Member[] argMembers = null;
        Member[] expectedMembers = null;

        // ()
        argMembers = new Member[] {};
        expectedMembers = new Member[] {};
        assertEveryExpandSupportedCalculatedMembers(
            "()", expectedMembers, argMembers, evaluator);

        // (0, 2)
        argMember0 = endMember0;
        argMember1 = endMember2;
        argMembers = new Member[] {argMember0, argMember1};
        expectedMembers = new Member[] {endMember0, endMember2};
        assertEveryExpandSupportedCalculatedMembers(
            "(0, 2)", expectedMembers, argMembers, evaluator);

        // (Aggr(0, 1), 2)
        argMember0 = makeAggregateExprMember(
            evaluator,
            Arrays.asList(new Member[] {endMember0, endMember1}));
        argMember1 = endMember2;
        argMembers = new Member[] {argMember0, argMember1};
        expectedMembers = new Member[] {endMember0, endMember1, endMember2};
        assertEveryExpandSupportedCalculatedMembers(
            "(Aggr(0, 1), 2)", expectedMembers, argMembers, evaluator);

        // (Aggr(0, 1), Aggr(3, 2))
        argMember0 = makeAggregateExprMember(
            evaluator,
            Arrays.asList(new Member[] {endMember0, endMember1}));
        argMember1 = makeAggregateExprMember(
            evaluator,
            Arrays.asList(new Member[] {endMember3, endMember2}));
        argMember1 = endMember2;
        argMembers = new Member[] {argMember0, argMember1};
        expectedMembers = new Member[] {endMember0, endMember1, endMember2};
        assertEveryExpandSupportedCalculatedMembers(
            "(Aggr(0, 1), Aggr(3, 2))", expectedMembers, argMembers, evaluator);
    }

    // test with a placeholder member
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class)
    void testExpandSupportedCalculatedMembers2(Context context) {
      final Connection connection = context.getConnection();

      final String queryText =
          "SELECT {[Measures].[Customer Count]} ON 0 "
          + "FROM [Sales] "
          + "WHERE [Time].[1997]";

      final Query query = connection.parseQuery(queryText);
      final QueryAxis querySlicerAxis = query.getSlicerAxis();
      final Member slicerMember =
          ((MemberExpression)querySlicerAxis.getSet()).getMember();
      final RolapHierarchy slicerHierarchy =
          ((RolapCube)query.getCube()).getTimeHierarchy(null);

      final Execution execution = new Execution(query.getStatement(), 0L);
      final RolapEvaluatorRoot rolapEvaluatorRoot =
          new RolapEvaluatorRoot(execution);
      final RolapEvaluator rolapEvaluator =
          new RolapEvaluator(rolapEvaluatorRoot);
      final Member expectedMember = slicerMember;
      setSlicerContext(rolapEvaluator, expectedMember);

      RolapResult.CompoundSlicerRolapMember placeHolderMember =
          Mockito.mock(RolapResult.CompoundSlicerRolapMember.class);
      Mockito.doReturn(slicerHierarchy)
      .when(placeHolderMember).getHierarchy();

      Member endMember0 = makeNoncalculatedMember("0");

      // (0, placeholder)
      Member[] argMembers = new Member[] {endMember0, placeHolderMember};
      Member[] expectedMembers = new Member[] {endMember0, slicerMember};
      Member[] expectedMembersOnDisjoin = new Member[] {endMember0};
      assertApartExpandSupportedCalculatedMembers(
          "(0, placeholder)",
          expectedMembers, expectedMembersOnDisjoin, argMembers,
          rolapEvaluator);
    }

    /**
     * calculation test for disjoint tuples
     */
    @Test
    void testGetSetFromCalculatedMember() {
        List<Member> listColumn1 = new ArrayList<>();
        List<Member> listColumn2 = new ArrayList<>();

        listColumn1.add(new TestMember("elem1_col1"));
        listColumn1.add(new TestMember("elem2_col1"));
        listColumn2.add(new TestMember("elem1_col2"));
        listColumn2.add(new TestMember("elem2_col2"));

        final List<List<Member>> table = new ArrayList<>();
        table.add(listColumn1);
        table.add(listColumn2);

        List<Member> arrayRes = getCalculatedMember(table, 1).getMembers();

        assertEquals(arrayRes.size(), 4);

        assertEquals(listColumn1.get(0), arrayRes.get(0));
        assertEquals(listColumn1.get(1), arrayRes.get(1));
        assertEquals(listColumn2.get(0), arrayRes.get(2));
        assertEquals(listColumn2.get(1), arrayRes.get(3));
    }

    /**
     * calculation test for disjoint tuples
     */
    @Test
    void testGetSetFromCalculatedMember_disjoint() {
        final int ARITY = 2;

        List<Member> listColumn1 = new ArrayList<>();
        List<Member> listColumn2 = new ArrayList<>();

        listColumn1.add(new TestMember("elem1_col1"));
        listColumn1.add(new TestMember("elem2_col1"));
        listColumn2.add(new TestMember("elem1_col2"));
        listColumn2.add(new TestMember("elem2_col2"));

        final List<List<Member>> table = new ArrayList<>();
        table.add(listColumn1);
        table.add(listColumn2);

        TupleConstraintStruct res = getCalculatedMember(table, ARITY);

        TupleList tuple = res.getDisjoinedTupleLists().get(0);

        assertTrue(res.getMembers().isEmpty()); // should be empty
        assertEquals(tuple.getArity(), ARITY);
        assertEquals(tuple.get(0).get(0), listColumn1.get(0));
        assertEquals(tuple.get(0).get(1), listColumn1.get(1));
        assertEquals(tuple.get(1).get(0), listColumn2.get(0));
        assertEquals(tuple.get(1).get(1), listColumn2.get(1));
    }

    public TupleConstraintStruct getCalculatedMember(
        final List<List<Member>> table,
        int arity)
    {
        Member memberMock = mock(Member.class);

        Expression[] funCallArgExps = new Expression[0];
        ResolvedFunCallImpl funCallArgMock = new ResolvedFunCallImpl(
            mock(FunctionDefinition.class),
            funCallArgExps, mock(TupleType.class));

        Expression[] funCallExps = {funCallArgMock};
        ResolvedFunCallImpl funCallMock = new ResolvedFunCallImpl(
            mock(FunctionDefinition.class), funCallExps, mock(TupleType.class));

        when(memberMock.getExpression()).thenReturn(funCallMock);

        Evaluator evaluatorMock = mock(Evaluator.class);

        SetEvaluator setEvaluatorMock = mock(
            SetEvaluator.class);

        TupleIterable tupleIterableMock = mock(TupleIterable.class);

        when(tupleIterableMock.iterator()).thenReturn(table.iterator());
        when(tupleIterableMock.getArity()).thenReturn(arity);

        AbstractTupleCursor cursor = new AbstractTupleCursor(arity) {
            Iterator<List<Member>> iterator = table.iterator();
            List<Member> curList;

            @Override
            public boolean forward() {
                boolean hasNext = iterator.hasNext();
                if (hasNext) {
                    curList = iterator.next();
                } else {
                    curList = null;
                }
                return hasNext;
            }

            @Override
            public List<Member> current() {
                return curList;
            }
        };

        when(tupleIterableMock.tupleCursor()).thenReturn(cursor);

        when(setEvaluatorMock.evaluateTupleIterable())
            .thenReturn(tupleIterableMock);

        when(evaluatorMock.getSetEvaluator(eq(funCallArgMock), anyBoolean()))
            .thenReturn(setEvaluatorMock);

        TupleConstraintStruct constraint = new TupleConstraintStruct();
        SqlConstraintUtils.expandSetFromCalculatedMember(
            evaluatorMock, memberMock, constraint);
        return constraint;
    }

    @Test
    void testRemoveCalculatedAndDefaultMembers() {
        Hierarchy hierarchy = mock(Hierarchy.class);

        // create members
        List<Member> members = new ArrayList<>();
        members.add(createMemberMock(false, false, hierarchy)); // 0:passed
        members.add(createMemberMock(true, false, hierarchy)); // 1:not passed
        members.add(createMemberMock(true, true, hierarchy)); // 2:passed
        members.add(createMemberMock(false, true, hierarchy)); // 3:passed
        members.add(createMemberMock(false, true, hierarchy)); // 4:default,
                                                               //   not passed
        members.add(createMemberMock(false, false, hierarchy)); // 5:passed
        members.add(createMemberMock(true, false, hierarchy)); // 6:not passed

        when(hierarchy.getDefaultMember()).thenReturn(members.get(4));

        List<Member> newMembers = SqlConstraintUtils
            .removeCalculatedAndDefaultMembers(members);

        assertEquals(4, newMembers.size());
        assertTrue(newMembers.contains(members.get(0)));
        assertTrue(newMembers.contains(members.get(2)));
        assertTrue(newMembers.contains(members.get(3)));
        assertTrue(newMembers.contains(members.get(5)));
    }

    private Member createMemberMock(
        boolean isCalculated,
        boolean isParentChildLeaf,
        Hierarchy hierarchy)
    {
        Member mock = mock(Member.class);
        when(mock.isCalculated()).thenReturn(isCalculated);
        when(mock.isParentChildLeaf()).thenReturn(isParentChildLeaf);
        when(mock.getHierarchy()).thenReturn(hierarchy);
        return mock;
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalog.class, dataloader = FastFoodmardDataLoader.class)
    void testConstrainLevel(Context context){

        final RolapCubeLevel level = mock( RolapCubeLevel.class);
        final RolapCube baseCube = mock(RolapCube.class);
        final RolapStar.Column column = mock(RolapStar.Column.class);

        final AggStar aggStar = null;
        final Dialect dialect =  context.getDialect();
        final SqlQuery query = new SqlQuery(dialect, context.getConfig().generateFormattedSql());

        when(level.getBaseStarKeyColumn(baseCube)).thenReturn(column);
        when(column.getNameColumn()).thenReturn(column);
        when(column.generateExprString(query)).thenReturn("dummyName");

        String[] columnValue = new String[1];
        columnValue[0] = "dummyValue";

        StringBuilder levelStrBuilder = SqlConstraintUtils.constrainLevel(level, query, baseCube, aggStar, columnValue, false);
        assertEquals("dummyName = 'dummyValue'",  levelStrBuilder.toString());
    }

    private void setSlicerContext(RolapEvaluator e, Member m) {
      List<Member> members = new ArrayList<>();
      members.add( m );
      Map<Hierarchy, Set<Member>> membersByHierarchy = new HashMap<>();
      membersByHierarchy.put( m.getHierarchy(), new HashSet<>(members) );
      e.setSlicerContext( members, membersByHierarchy );
    }
}
