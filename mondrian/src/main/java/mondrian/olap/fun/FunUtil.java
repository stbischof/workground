/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// Copyright (C) 2021 Sergei Semenkov
// All Rights Reserved.
*/
package mondrian.olap.fun;



import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.olap.api.DataType;
import org.eclipse.daanse.olap.api.Evaluator;
import org.eclipse.daanse.olap.api.Execution;
import org.eclipse.daanse.olap.api.MatchType;
import org.eclipse.daanse.olap.api.SchemaReader;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.Validator;
import org.eclipse.daanse.olap.api.access.Access;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.api.function.FunctionDefinition;
import org.eclipse.daanse.olap.api.function.FunctionMetaData;
import org.eclipse.daanse.olap.api.query.component.DimensionExpression;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.LevelExpression;
import org.eclipse.daanse.olap.api.query.component.Literal;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.api.type.Type;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.DoubleCalc;
import org.eclipse.daanse.olap.calc.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.todo.TupleCursor;
import org.eclipse.daanse.olap.calc.api.todo.TupleIterable;
import org.eclipse.daanse.olap.calc.api.todo.TupleList;
import org.eclipse.daanse.olap.function.AbstractFunctionDefinition;
import org.eclipse.daanse.olap.impl.IdentifierParser.Builder;
import org.eclipse.daanse.olap.operation.api.FunctionOperationAtom;
import org.eclipse.daanse.olap.operation.api.InfixOperationAtom;
import org.eclipse.daanse.olap.operation.api.InternalOperationAtom;
import org.eclipse.daanse.olap.operation.api.MethodOperationAtom;
import org.eclipse.daanse.olap.operation.api.OperationAtom;
import org.eclipse.daanse.olap.operation.api.PlainPropertyOperationAtom;
import org.eclipse.daanse.olap.operation.api.PostfixOperationAtom;
import org.eclipse.daanse.olap.operation.api.PrefixOperationAtom;

import mondrian.calc.impl.UnaryTupleList;
import mondrian.mdx.HierarchyExpressionImpl;
import mondrian.olap.MondrianException;
import mondrian.olap.Property;
import mondrian.olap.ResultStyleException;
import mondrian.olap.Util;
import mondrian.olap.exceptions.CousinHierarchyMismatchException;
import mondrian.olap.exceptions.MdxChildObjectNotFoundException;
import mondrian.olap.fun.sort.OrderKey;
import mondrian.olap.fun.sort.Sorter;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.ScalarType;
import mondrian.olap.type.TupleType;
import mondrian.olap.type.TypeUtil;
import mondrian.rolap.RolapUtil;
import mondrian.util.CancellationChecker;
import mondrian.util.ConcatenableList;
import mondrian.util.IdentifierParser;

/**
 * {@code FunUtil} contains a set of methods useful within the {@code mondrian.olap.fun} package.
 *
 * @author jhyde
 * @since 1.0
 */
public class FunUtil extends Util {

  public static final NullMember NullMember = new NullMember();


  /**
   * Special value which indicates that a {@code double} computation has returned the MDX EMPTY value. See {@link
   * DoubleCalc}.
   */
  public static final double DOUBLE_EMPTY = -0.000000012345;

//  /**
//   * Special value which indicates that an {@code int} computation has returned the MDX null value. See {@link
//   * org.eclipse.daanse.olap.calc.api.IntegerCalc}.
//   */
//  public static final int INTEGER_NULL = Integer.MIN_VALUE + 1;

  /**
   * Null value in three-valued boolean logic. Actually, a placeholder until we actually implement 3VL.
   */
  public static final boolean BOOLEAN_NULL = false;
    private final static String memberNotInLevelHierarchy = "The member ''{0}'' is not in the same hierarchy as the level ''{1}''.";

    /**
   * Creates an exception which indicates that an error has occurred while executing a given function.
   *
   * @param funDef  Function being executed
   * @param message Explanatory message
   * @return Exception that can be used as a cell result
   */
  public static RuntimeException newEvalException(
		  FunctionMetaData functionMetaData ,
    String message ) {
    return new MondrianEvaluationException( message );
  }

  /**
   * Creates an exception which indicates that an error has occurred while executing a given function.
   *
   * @param throwable Exception
   * @return Exception that can be used as a cell result
   */
  public static RuntimeException newEvalException( Throwable throwable ) {
    return new MondrianEvaluationException(
      new StringBuilder(throwable.getClass().getName()).append(": ").append(throwable.getMessage()).toString() );
  }

  /**
   * Creates an exception which indicates that an error has occurred while executing a given function.
   *
   * @param message   Explanatory message
   * @param throwable Exception
   * @return Exception that can be used as a cell result
   */
  public static RuntimeException newEvalException(
    String message,
    Throwable throwable ) {
    return new MondrianEvaluationException(
      new StringBuilder(message)
        .append(": ").append(Util.getErrorMessage( throwable )).toString() );
  }

  public static void checkIterListResultStyles( Calc calc ) {
    switch ( calc.getResultStyle() ) {
      case ITERABLE, LIST, MUTABLE_LIST:
        break;
      default:
        throw ResultStyleException.generateBadType(
          ResultStyle.ITERABLE_LIST_MUTABLELIST,
          calc.getResultStyle() );
    }
  }

  public static void checkListResultStyles( Calc calc ) {
    switch ( calc.getResultStyle() ) {
      case LIST, MUTABLE_LIST:
        break;
      default:
        throw ResultStyleException.generateBadType(
          ResultStyle.LIST_MUTABLELIST,
          calc.getResultStyle() );
    }
  }

  /**
   * Returns an argument whose value is a literal.
   */

  static String getLiteralArg(
    ResolvedFunCall call,
    int i,
    String defaultValue,
    List<String> allowedValues ) {
    if ( i >= call.getArgCount() ) {
      if ( defaultValue == null ) {
        throw FunUtil.newEvalException(
          call.getFunDef().getFunctionMetaData(),
          "Required argument is missing" );
      } else {
        return defaultValue;
      }
    }
    Expression arg = call.getArg( i );
    if ( !( arg instanceof Literal)
      || arg.getCategory() != DataType.SYMBOL) {
      throw FunUtil.newEvalException(
        call.getFunDef().getFunctionMetaData(),
        new StringBuilder("Expected a symbol, found '").append(arg).append("'").toString() );
    }
    String s = (String) ( (Literal) arg ).getValue();
    StringBuilder sb = new StringBuilder( 64 );
    int j=0;
    for ( String allowedValue: allowedValues ) {
      if ( allowedValue.equalsIgnoreCase( s ) ) {
        return allowedValue;
      }
      if ( j > 0 ) {
        sb.append( ", " );
      }
      j++;
      sb.append( allowedValue );
    }
    throw FunUtil.newEvalException(
      call.getFunDef().getFunctionMetaData(),
      new StringBuilder("Allowed values are: {").append(sb).append("}").toString() );
  }

  /**
   * Returns the ordinal of a literal argument. If the argument does not belong to the supplied enumeration, returns
   * -1.
   */
  static <E extends Enum<E>> E getLiteralArg(
    ResolvedFunCall call,
    int i,
    E defaultValue,
    Class<E> allowedValues ) {
    if ( i >= call.getArgCount() ) {
      if ( defaultValue == null ) {
        throw FunUtil.newEvalException(
          call.getFunDef().getFunctionMetaData(),
          "Required argument is missing" );
      } else {
        return defaultValue;
      }
    }
    Expression arg = call.getArg( i );
    if ( !( arg instanceof Literal )
      || arg.getCategory() != DataType.SYMBOL) {
      throw FunUtil.newEvalException(
        call.getFunDef().getFunctionMetaData(),
        new StringBuilder("Expected a symbol, found '").append(arg).append("'").toString() );
    }
    String s = (String) ( (Literal) arg ).getValue();
    for ( E e : allowedValues.getEnumConstants() ) {
      if ( e.name().equalsIgnoreCase( s ) ) {
        return e;
      }
    }
    StringBuilder buf = new StringBuilder( 64 );
    int k = 0;
    for ( E e : allowedValues.getEnumConstants() ) {
      if ( k++ > 0 ) {
        buf.append( ", " );
      }
      buf.append( e.name() );
    }
    throw FunUtil.newEvalException(
      call.getFunDef().getFunctionMetaData(),
      new StringBuilder("Allowed values are: {").append(buf).append("}").toString() );
  }

  /**
   * Throws an error if the expressions don't have the same hierarchy.
   *
   * @throws MondrianEvaluationException if expressions don't have the same hierarchy
   */
  static void checkCompatible( Expression left, Expression right, FunctionDefinition funDef ) {
    final Type leftType = TypeUtil.stripSetType( left.getType() );
    final Type rightType = TypeUtil.stripSetType( right.getType() );
    if ( !TypeUtil.isUnionCompatible( leftType, rightType ) ) {
      throw FunUtil.newEvalException(
        funDef.getFunctionMetaData(), "Expressions must have the same hierarchy" );
    }
  }

  /**
   * Adds every element of {@code right} which is not in {@code set} to both {@code set} and {@code left}.
   */
  static void addUnique(
    TupleList left,
    TupleList right,
    Set<List<Member>> set ) {
    assert left != null;
    assert right != null;
    if ( right.isEmpty() ) {
      return;
    }
    for ( int i = 0, n = right.size(); i < n; i++ ) {
      List<Member> o = right.get( i );
      if ( set.add( o ) ) {
        left.add( o );
      }
    }
  }





  static List<Member> addMembers(
    final SchemaReader schemaReader,
    final List<Member> members,
    final Hierarchy hierarchy) {
    // only add accessible levels
    for ( Level level : schemaReader.getHierarchyLevels( hierarchy ) ) {
      FunUtil.addMembers( schemaReader, members, level );
    }
    return members;
  }

  static List<Member> addMembers(
    SchemaReader schemaReader,
    List<Member> members,
    Level level) {
    List<Member> levelMembers = schemaReader.getLevelMembers( level, true );
    members.addAll( levelMembers );
    return members;
  }

  /**
   * Removes every member from a list which is calculated. The list must not be null, and must consist only of members.
   *
   * @param memberList Member list
   * @return List of non-calculated members
   */
  static List<Member> removeCalculatedMembers( List<Member> memberList ) {
    List<Member> clone = new ArrayList<>();
    for ( Member member : memberList ) {
      if ( member.isCalculated()
        && !member.isParentChildPhysicalMember() ) {
        continue;
      }
      clone.add( member );
    }
    return clone;
  }

  /**
   * Removes every tuple from a list which is calculated. The list must not be null, and must consist only of members.
   *
   * @param memberList Member list
   * @return List of non-calculated members
   */
  static TupleList removeCalculatedMembers( TupleList memberList ) {
    if ( memberList.getArity() == 1 ) {
      return new UnaryTupleList(
        FunUtil.removeCalculatedMembers(
          memberList.slice( 0 ) ) );
    } else {
      final TupleList clone = memberList.copyList( memberList.size() );
      outer:
      for ( List<Member> members : memberList ) {
        for ( Member member : members ) {
          if ( member.isCalculated()
            && !member.isParentChildPhysicalMember() ) {
            continue outer;
          }
        }
        clone.add( members );
      }
      return clone;
    }
  }

  /**
   * Returns whether {@code m0} is an ancestor of {@code m1}.
   *
   * @param strict if true, a member is not an ancestor of itself
   */
  public static boolean isAncestorOf( Member m0, Member m1, boolean strict ) {
    if ( strict ) {
      if ( m1 == null ) {
        return false;
      }
      m1 = m1.getParentMember();
    }
    while ( m1 != null ) {
      if ( m1.equals( m0 ) ) {
        return true;
      }
      m1 = m1.getParentMember();
    }
    return false;
  }

  /**
   * Compares double-precision values according to MDX semantics.
   *
   * <p>MDX requires a total order:
   * <blockquote>
   * -inf &lt; NULL &lt; ... &lt; -1 &lt; ... &lt; 0 &lt; ... &lt; NaN &lt; +inf
   * </blockquote>
   * but this is different than Java semantics, specifically with regard to {@link Double#NaN}.
   */
  public static int compareValues( double d1, double d2 ) {
    if ( Double.isNaN( d1 ) ) {
      if ( d2 == Double.POSITIVE_INFINITY ) {
        return -1;
      } else if ( Double.isNaN( d2 ) ) {
        return 0;
      } else {
        return 1;
      }
    } else if ( Double.isNaN( d2 ) ) {
      if ( d1 == Double.POSITIVE_INFINITY ) {
        return 1;
      } else {
        return -1;
      }
    } else if ( d1 == d2 ) {
      return 0;
    } else if ( d1 == FunUtil.DOUBLE_NULL) {
      if ( d2 == Double.NEGATIVE_INFINITY ) {
        return 1;
      } else {
        return -1;
      }
    } else if ( d2 == FunUtil.DOUBLE_NULL) {
      if ( d1 == Double.NEGATIVE_INFINITY ) {
        return -1;
      } else {
        return 1;
      }
    } else if ( d1 < d2 ) {
      return -1;
    } else {
      return 1;
    }
  }

  /**
   * Compares two cell values.
   *
   * <p>Nulls compare last, exceptions (including the
   * object which indicates the the cell is not in the cache yet) next, then numbers and strings are compared by value.
   *
   * @param value0 First cell value
   * @param value1 Second cell value
   * @return -1, 0, or 1, depending upon whether first cell value is less than, equal to, or greater than the second
   */
  public static int compareValues( Object value0, Object value1 ) {
    if ( value0 == value1 ) {
      return 0;
    }
    // null is less than anything else
    if ( value0 == null ) {
      return -1;
    }
    if ( value1 == null ) {
      return 1;
    }

    if ( value0 == RolapUtil.valueNotReadyException ) {
      // the left value is not in cache; continue as best as we can
      return -1;
    } else if ( value1 == RolapUtil.valueNotReadyException ) {
      // the right value is not in cache; continue as best as we can
      return 1;
    } else if ( value0 == Util.nullValue ) {
      return -1; // null == -infinity
    } else if ( value1 == Util.nullValue ) {
      return 1; // null == -infinity
    } else if ( value0 instanceof String str) {
      return str.compareToIgnoreCase( (String) value1 );
    } else if ( value0 instanceof Number numberValue0) {
      return FunUtil.compareValues(
          numberValue0.doubleValue(),
        ( (Number) value1 ).doubleValue() );
    } else if ( value0 instanceof Date date) {
      return date.compareTo( (Date) value1 );
    } else if ( value0 instanceof OrderKey orderKey) {
      return orderKey.compareTo( value1 );
    } else {
      throw Util.newInternal( "cannot compare " + value0 );
    }
  }

  /**
   * Turns the mapped values into relative values (percentages) for easy use by the general topOrBottom function. This
   * might also be a useful function in itself.
   */
  static void toPercent(
    TupleList members,
    Map<List<Member>, Object> mapMemberToValue ) {
    double total = 0;
    int memberCount = members.size();
    for ( int i = 0; i < memberCount; i++ ) {
      final List<Member> key = members.get( i );
      final Object o = mapMemberToValue.get( key );
      if ( o instanceof Number number) {
        total += number.doubleValue();
      }
    }
    for ( int i = 0; i < memberCount; i++ ) {
      final List<Member> key = members.get( i );
      final Object o = mapMemberToValue.get( key );
      if ( o instanceof Number number) {
        double d = number.doubleValue();
        mapMemberToValue.put(
          key,
            total == 0 ? 0 :d / total * 100 );
      }
    }
  }

  public static OperationAtom decodeSyntacticTypeToOp( String flags,String name ) {
	    char c = flags.charAt( 0 );
	    switch ( c ) {
	      case 'p':
	        return new PlainPropertyOperationAtom(name);
	      case 'f':
	        return new FunctionOperationAtom(name);
	      case 'm':
	        return new MethodOperationAtom(name);
	      case 'i':
	        return new InfixOperationAtom(name);
	      case 'P':
	        return new PrefixOperationAtom(name);
	      case 'Q':
	        return new PostfixOperationAtom(name);
	      case 'I':
	        return new InternalOperationAtom(name);
	      default:
	        throw Util.newInternal(
	          new StringBuilder("unknown syntax code '").append(c).append("' in string '")
	              .append(flags).append("'").toString() );
	    }
	  }

  /**
   * Decodes the signature of a function into a category code which describes the return type of the operator.
   *
   * <p>For example, <code>decodeReturnType("fnx")</code> returns
   * <code>{@link DataType#NUMERIC}</code>, indicating this function has a
   * numeric return value.
   *
   * @param flags The signature of an operator, as used by the {@code flags} parameter used to construct a {@link
   *              AbstractFunctionDefinition}.
   * @return An array {@link DataType} codes.
   */
  public static DataType decodeReturnCategory( String flags ) {
    final DataType returnCategory = FunUtil.decodeCategory( flags, 1 );
    return returnCategory;
  }

  /**
   * Decodes the {@code offset}th character of an encoded method signature into a type category.
   *
   * <p>The codes are:
   * <table border="1">
   *
   * <tr><td>a</td><td>{@link DataType#ARRAY}</td></tr>
   *
   * <tr><td>d</td><td>{@link DataType#DIMENSION}</td></tr>
   *
   * <tr><td>h</td><td>{@link DataType#HIERARCHY}</td></tr>
   *
   * <tr><td>l</td><td>{@link DataType#LEVEL}</td></tr>
   *
   * <tr><td>b</td><td>{@link DataType#LOGICAL}</td></tr>
   *
   * <tr><td>m</td><td>{@link DataType#MEMBER}</td></tr>
   *
   * <tr><td>N</td><td>Constant {@link DataType#NUMERIC}</td></tr>
   *
   * <tr><td>n</td><td>{@link DataType#NUMERIC}</td></tr>
   *
   * <tr><td>x</td><td>{@link DataType#SET}</td></tr>
   *
   * <tr><td>#</td><td>Constant {@link DataType#STRING}</td></tr>
   *
   * <tr><td>S</td><td>{@link DataType#STRING}</td></tr>
   *
   * <tr><td>t</td><td>{@link DataType#TUPLE}</td></tr>
   *
   * <tr><td>v</td><td>{@link DataType#VALUE}</td></tr>
   *
   * <tr><td>y</td><td>{@link DataType#SYMBOL}</td></tr>
   *
   * </table>
   *
   * @param flags  Encoded signature string
   * @param offset 0-based offset of character within string
   * @return A {@link DataType}
   */
  public static DataType decodeCategory( String flags, int offset ) {
    char c = flags.charAt( offset );
    switch ( c ) {
      case 'a':
        return DataType.ARRAY;
      case 'd':
        return DataType.DIMENSION;
      case 'h':
        return DataType.HIERARCHY;
      case 'l':
        return DataType.LEVEL;
      case 'b':
        return DataType.LOGICAL;
      case 'm':
        return DataType.MEMBER;
      case 'N':
        return DataType.NUMERIC; //Was Constant
      case 'n':
        return DataType.NUMERIC;
      case 'I':
        return DataType.INTEGER ;//Was Constant
      case 'i':
        return  DataType.INTEGER;
      case 'x':
        return DataType.SET;
      case '#':
        return DataType.STRING;
      case 'S':
        return DataType.STRING;//Was Constant
      case 't':
        return DataType.TUPLE;
      case 'v':
        return DataType.VALUE;
      case 'y':
        return DataType.SYMBOL;
      case 'U':
        return DataType.NULL;
      case 'e':
        return DataType.EMPTY;
      case 'D':
        return DataType.DATE_TIME;
      default:
        throw Util.newInternal(
          new StringBuilder("unknown type code '").append(c)
              .append("' in string '").append(flags).append("'").toString() );
    }
  }

  /**
   * Decodes a string of parameter types into an array of type codes.
   *
   * <p>Each character is decoded using {@link #decodeCategory(String, int)}.
   * For example, <code>decodeParameterTypes("nx")</code> returns
   * <code>{{@link DataType#NUMERIC}, {@link DataType#SET}}</code>.
   *
   * @param flags The signature of an operator, as used by the {@code flags} parameter used to construct a {@link
   *              AbstractFunctionDefinition}.
   * @return An array {@link DataType} codes.
   */
  public static DataType[] decodeParameterCategories( String flags ) {
	  DataType[] parameterCategories = new DataType[ flags.length() - 2 ];
    for ( int i = 0; i < parameterCategories.length; i++ ) {
      parameterCategories[ i ] = FunUtil.decodeCategory( flags, i + 2 );
    }
    return parameterCategories;
  }

  static double percentile(
    Evaluator evaluator,
    TupleList members,
    Calc exp,
    double p ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, exp );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else if ( sw.v.isEmpty() ) {
      return FunUtil.DOUBLE_NULL;
    }
    double[] asArray = new double[ sw.v.size() ];
    for ( int i = 0; i < asArray.length; i++ ) {
      asArray[ i ] = (Double) sw.v.get( i );
    }
    Arrays.sort( asArray );

    // The median is defined as the value that has exactly the same
    // number of entries before it in the sorted list as after.
    // So, if the number of entries in the list is odd, the
    // median is the entry at (length-1)/2 (using zero-based indexes).
    // If the number of entries is even, the median is defined as the
    // arithmetic mean of the two numbers in the middle of the list, or
    // (entries[length/2 - 1] + entries[length/2]) / 2.
    int length = asArray.length;
    if ( length == 1 ) {
      // if array contains a single element return it
      return asArray[ 0 ];
    }
    if ( p <= 0.0 ) {
      return asArray[ 0 ];
    } else if ( p >= 1.0 ) {
      return asArray[ length - 1 ];
    } else if ( length == 1 ) {
      return asArray[ 0 ];
    } else if ( p == 0.5 ) {
      // Special case for median.
      if ( ( length & 1 ) == 1 ) {
        // The length is odd. Note that length/2 is an integer
        // expression, and it's positive so we save ourselves a divide.
        return asArray[ length >> 1 ];
      } else {
        return ( asArray[ ( length >> 1 ) - 1 ] + asArray[ length >> 1 ] )
          / 2.0;
      }
    } else {
      final double rank = ( ( length - 1 ) * p ) + 1;
      final int integerPart = (int) Math.floor( rank );
      assert integerPart >= 1;
      final double decimalPart = rank - integerPart;
      assert decimalPart >= 0;
      assert decimalPart <= 1;
      int indexForFormula = integerPart - 1;
      return asArray[ indexForFormula ]
        + ( ( asArray[ indexForFormula + 1 ] - asArray[ indexForFormula ] )
        * decimalPart );
    }
  }

  /**
   * Returns the member which lies upon a particular quartile according to a given expression.
   *
   * @param evaluator Evaluator
   * @param members   List of members
   * @param exp       Expression to rank members
   * @param range     Quartile (1, 2 or 3)
   * @pre range >= 1 && range <= 3
   */
  public static double quartile(
    Evaluator evaluator,
    TupleList members,
    Calc exp,
    int range ) {
    assert range >= 1 && range <= 3;

    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, exp );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else if ( sw.v.isEmpty() ) {
      return FunUtil.DOUBLE_NULL;
    }

    double[] asArray = new double[ sw.v.size() ];
    for ( int i = 0; i < asArray.length; i++ ) {
      asArray[ i ] = ( (Double) sw.v.get( i ) ).doubleValue();
    }

    Arrays.sort( asArray );
    // get a quartile, median is a second q
    double dm = 0.25 * asArray.length * range;
    int median = (int) Math.floor( dm );
    return dm == median && median < asArray.length - 1
      ? ( asArray[ median ] + asArray[ median + 1 ] ) / 2
      : asArray[ median ];
  }

  public static Object min(
    Evaluator evaluator,
    TupleList members,
    Calc calc ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, calc );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else {
      final int size = sw.v.size();
      if ( size == 0 ) {
        return Util.nullValue;
      } else {
        Double min = ( (Number) sw.v.get( 0 ) ).doubleValue();
        for ( int i = 1; i < size; i++ ) {
          Double iValue = ( (Number) sw.v.get( i ) ).doubleValue();
          if ( iValue < min ) {
            min = iValue;
          }
        }
        return min;
      }
    }
  }

  public static Object max(
    Evaluator evaluator,
    TupleList members,
    Calc exp ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, exp );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else {
      final int size = sw.v.size();
      if ( size == 0 ) {
        return Util.nullValue;
      } else {
        Double max = ( (Number) sw.v.get( 0 ) ).doubleValue();
        for ( int i = 1; i < size; i++ ) {
          Double iValue = ( (Number) sw.v.get( i ) ).doubleValue();
          if ( iValue > max ) {
            max = iValue;
          }
        }
        return max;
      }
    }
  }

  static Object var(
    Evaluator evaluator,
    TupleList members,
    Calc exp,
    boolean biased ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, exp );
    return FunUtil.var( sw, biased );
  }

  private static Object var(SetWrapper sw, boolean biased ) {
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else if ( sw.v.isEmpty() ) {
      return Util.nullValue;
    } else {
      double stdev = 0.0;
      double avg = FunUtil.avg( sw );
      for ( int i = 0; i < sw.v.size(); i++ ) {
        stdev +=
          Math.pow( ( ( (Number) sw.v.get( i ) ).doubleValue() - avg ), 2 );
      }
      int n = sw.v.size();
      if ( !biased ) {
        n--;
      }
      return Double.valueOf( stdev / n );
    }
  }

  static double correlation(
    Evaluator evaluator,
    TupleList memberList,
    Calc exp1,
    Calc exp2 ) {
    SetWrapper sw1 = FunUtil.evaluateSet( evaluator, memberList, exp1 );
    SetWrapper sw2 = FunUtil.evaluateSet( evaluator, memberList, exp2 );
    Object covar = FunUtil.covariance( sw1, sw2, false );
    Object var1 = FunUtil.var( sw1, false ); // this should be false, yes?
    Object var2 = FunUtil.var( sw2, false );

    return ( (Number) covar ).doubleValue()
      / Math.sqrt(
      ( (Number) var1 ).doubleValue()
        * ( (Number) var2 ).doubleValue() );
  }

  static Object covariance(
    Evaluator evaluator,
    TupleList members,
    Calc exp1,
    Calc exp2,
    boolean biased ) {
    final int savepoint = evaluator.savepoint();
    SetWrapper sw1;
    try {
      sw1 = FunUtil.evaluateSet( evaluator, members, exp1 );
    } finally {
      evaluator.restore( savepoint );
    }
    SetWrapper sw2;
    try {
      sw2 = FunUtil.evaluateSet( evaluator, members, exp2 );
    } finally {
      evaluator.restore( savepoint );
    }
    // todo: because evaluateSet does not add nulls to the SetWrapper, this
    // solution may lead to mismatched lists and is therefore not robust
    return FunUtil.covariance( sw1, sw2, biased );
  }


  private static Object covariance(
    SetWrapper sw1,
    SetWrapper sw2,
    boolean biased ) {
    if ( sw1.v.size() != sw2.v.size() ) {
      return Util.nullValue;
    }
    double avg1 = FunUtil.avg( sw1 );
    double avg2 = FunUtil.avg( sw2 );
    double covar = 0.0;
    for ( int i = 0; i < sw1.v.size(); i++ ) {
      // all of this casting seems inefficient - can we make SetWrapper
      // contain an array of double instead?
      double diff1 = ( ( (Number) sw1.v.get( i ) ).doubleValue() - avg1 );
      double diff2 = ( ( (Number) sw2.v.get( i ) ).doubleValue() - avg2 );
      covar += ( diff1 * diff2 );
    }
    int n = sw1.v.size();
    if ( !biased ) {
      n--;
    }
    return Double.valueOf( covar / n );
  }

  static Object stdev(
    Evaluator evaluator,
    TupleList members,
    Calc exp,
    boolean biased ) {
    Object o = FunUtil.var( evaluator, members, exp, biased );
    return ( o instanceof Double )
      ? Double.valueOf( Math.sqrt( ( (Number) o ).doubleValue() ) )
      : o;
  }

  public static Object avg(
    Evaluator evaluator,
    TupleList members,
    Calc calc ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, calc );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else {
        return (sw.v.isEmpty())
            ? Util.nullValue
            : Double.valueOf(FunUtil.avg(sw));
    }
  }

  // TODO: parameterize inclusion of nulls; also, maybe make _avg a method of
  // setwrapper, so we can cache the result (i.e. for correl)
  private static double avg(SetWrapper sw ) {
    double sum = 0.0;
    for ( int i = 0; i < sw.v.size(); i++ ) {
      sum += ( (Number) sw.v.get( i ) ).doubleValue();
    }
    // TODO: should look at context and optionally include nulls
    return sum / sw.v.size();
  }

  public static Object sum(
    Evaluator evaluator,
    TupleList members,
    Calc exp ) {
    double d = FunUtil.sumDouble( evaluator, members, exp );
    return d == FunUtil.DOUBLE_NULL ? Util.nullValue : Double.valueOf( d );
  }

  public static double sumDouble(
    Evaluator evaluator,
    TupleList members,
    Calc exp ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, members, exp );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else if ( sw.v.isEmpty() ) {
      return FunUtil.DOUBLE_NULL;
    } else {
      double sum = 0.0;
      for ( int i = 0; i < sw.v.size(); i++ ) {
        sum += ( (Number) sw.v.get( i ) ).doubleValue();
      }
      return sum;
    }
  }

  public static double sumDouble(
    Evaluator evaluator,
    TupleIterable iterable,
    Calc exp ) {
    SetWrapper sw = FunUtil.evaluateSet( evaluator, iterable, exp );
    if ( sw.errorCount > 0 ) {
      return Double.NaN;
    } else if ( sw.v.isEmpty() ) {
      return FunUtil.DOUBLE_NULL;
    } else {
      double sum = 0.0;
      for ( int i = 0; i < sw.v.size(); i++ ) {
        sum += ( (Number) sw.v.get( i ) ).doubleValue();
      }
      return sum;
    }
  }

  public static int count(
    Evaluator evaluator,
    TupleIterable iterable,
    boolean includeEmpty ) {
    if ( iterable == null ) {
      return 0;
    }
    if ( includeEmpty ) {
      if ( iterable instanceof TupleList tupleList) {
        return tupleList.size();
      } else {
        int retval = 0;
        TupleCursor cursor = iterable.tupleCursor();
        while ( cursor.forward() ) {
          retval++;
        }
        return retval;
      }
    } else {
      int retval = 0;
      TupleCursor cursor = iterable.tupleCursor();
      while ( cursor.forward() ) {
        cursor.setContext( evaluator );
        if ( !evaluator.currentIsEmpty() ) {
          retval++;
        }
      }
      return retval;
    }
  }

  /**
   * Evaluates {@code exp} (if defined) over {@code members} to generate a {@link List} of {@link SetWrapper} objects,
   * which contains a {@link Double} value and meta information, unlike {@link #evaluateMembers}, which only produces
   * values.
   *
   * @pre exp != null
   */
  static SetWrapper evaluateSet(
    Evaluator evaluator,
    TupleIterable members,
    Calc calc ) {
    assert members != null;
    assert calc != null;
    assert calc.getType() instanceof ScalarType;

    // todo: treat constant exps as evaluateMembers() does
    SetWrapper retval = new SetWrapper();
    final TupleCursor cursor = members.tupleCursor();
    int currentIteration = 0;
    Execution execution =
      evaluator.getQuery().getStatement().getCurrentExecution();
    while ( cursor.forward() ) {
      CancellationChecker.checkCancelOrTimeout(
        currentIteration++, execution );
      cursor.setContext( evaluator );
      Object o = calc.evaluate( evaluator );
      if ( o == null || o == Util.nullValue ) {
        retval.nullCount++;
      } else if ( o == RolapUtil.valueNotReadyException ) {
        // Carry on summing, so that if we are running in a
        // BatchingCellReader, we find out all the dependent cells we
        // need
        retval.errorCount++;
      } else if ( o instanceof Number number) {
        retval.v.add( number.doubleValue() );
      } else {
        retval.v.add( o );
      }
    }
    return retval;
  }

  /**
   * Evaluates one or more expressions against the member list returning a SetWrapper array. Where this differs very
   * significantly from the above evaluateSet methods is how it count null values and Throwables; this method adds nulls
   * to the SetWrapper Vector rather than not adding anything - as the above method does. The impact of this is that if,
   * for example, one was creating a list of x,y values then each list will have the same number of values (though some
   * might be null) - this allows higher level code to determine how to handle the lack of data rather than having a
   * non-equal number (if one is plotting x,y values it helps to have the same number and know where a potential gap is
   * the data is.
   */
  static SetWrapper[] evaluateSet(
    Evaluator evaluator,
    TupleList list,
    DoubleCalc[] calcs ) {
    Util.assertPrecondition( calcs != null, "calcs != null" );

    // todo: treat constant exps as evaluateMembers() does
    SetWrapper[] retvals = new SetWrapper[ calcs.length ];
    for ( int i = 0; i < calcs.length; i++ ) {
      retvals[ i ] = new SetWrapper();
    }
    final TupleCursor cursor = list.tupleCursor();
    int currentIteration = 0;
    Execution execution =
      evaluator.getQuery().getStatement().getCurrentExecution();
    while ( cursor.forward() ) {
      CancellationChecker.checkCancelOrTimeout(
        currentIteration++, execution );
      cursor.setContext( evaluator );
      for ( int i = 0; i < calcs.length; i++ ) {
        DoubleCalc calc = calcs[ i ];
        SetWrapper retval = retvals[ i ];
        Double o = calc.evaluate( evaluator );
        if(o==null) {
        	System.out.println(calc);
        }
        if ( o == FunUtil.DOUBLE_NULL) {
          retval.nullCount++;
          retval.v.add( null );
        } else {
          retval.v.add( o );
        }
        // TODO: If the expression yielded an error, carry on
        // summing, so that if we are running in a
        // BatchingCellReader, we find out all the dependent cells
        // we need
      }
    }
    return retvals;
  }

  static List<Member> periodsToDate(
    Evaluator evaluator,
    Level level,
    Member member ) {
    if ( member == null ) {
      member = evaluator.getContext( level.getHierarchy() );
    }
    Member m = member;
    while ( m != null ) {
      if ( m.getLevel() == level ) {
        break;
      }
      m = m.getParentMember();
    }
    // If m == null, then "level" was lower than member's level.
    // periodsToDate([Time].[Quarter], [Time].[1997] is valid,
    //  but will return an empty List
    List<Member> members = new ArrayList<>();
    if ( m != null ) {
      // e.g. m is [Time].[1997] and member is [Time].[1997].[Q1].[3]
      // we now have to make m to be the first member of the range,
      // so m becomes [Time].[1997].[Q1].[1]
      SchemaReader reader = evaluator.getSchemaReader();
      m = Util.getFirstDescendantOnLevel( reader, m, member.getLevel() );
      reader.getMemberRange( level, m, member, members );
    }
    return members;
  }

  static List<Member> memberRange(
    Evaluator evaluator,
    Member startMember,
    Member endMember ) {
    final Level level = startMember.getLevel();
    Util.assertTrue( level == endMember.getLevel() );
    List<Member> members = new ArrayList<>();
    evaluator.getSchemaReader().getMemberRange(
      level, startMember, endMember, members );

    if ( members.isEmpty() ) {
      // The result is empty, so maybe the members are reversed. This is
      // cheaper than comparing the members before we call getMemberRange.
      evaluator.getSchemaReader().getMemberRange(
        level, endMember, startMember, members );
    }
    return members;
  }

  /**
   * Returns the member under ancestorMember having the same relative position under member's parent.
   * <p>For exmaple, cousin([Feb 2001], [Q3 2001]) is [August 2001].
   *
   * @param schemaReader   The reader to use
   * @param member         The member for which we'll find the cousin.
   * @param ancestorMember The cousin's ancestor.
   * @return The child of {@code ancestorMember} in the same position under {@code ancestorMember} as {@code member} is
   * under its parent.
   */
  static Member cousin(
    SchemaReader schemaReader,
    Member member,
    Member ancestorMember ) {
    if ( ancestorMember.isNull() ) {
      return ancestorMember;
    }
    if ( member.getHierarchy() != ancestorMember.getHierarchy() ) {
      throw new CousinHierarchyMismatchException(
        member.getUniqueName(), ancestorMember.getUniqueName() );
    }
    if ( member.getLevel().getDepth()
      < ancestorMember.getLevel().getDepth() ) {
      return member.getHierarchy().getNullMember();
    }

    Member cousin = FunUtil.cousin2( schemaReader, member, ancestorMember );
    if ( cousin == null ) {
      cousin = member.getHierarchy().getNullMember();
    }

    return cousin;
  }

  private static Member cousin2(
    SchemaReader schemaReader,
    Member member1,
    Member member2 ) {
    if ( member1.getLevel() == member2.getLevel() ) {
      return member2;
    }
    Member uncle =
      FunUtil.cousin2( schemaReader, member1.getParentMember(), member2 );
    if ( uncle == null ) {
      return null;
    }
    int ordinal = Util.getMemberOrdinalInParent( schemaReader, member1 );
    List<Member> cousins = schemaReader.getMemberChildren( uncle );
    if ( cousins.size() <= ordinal ) {
      return null;
    }
    return cousins.get( ordinal );
  }

  /**
   * Returns the ancestor of {@code member} at the given level or distance. It is assumed that any error checking
   * required has been done prior to calling this function.
   *
   * <p>This method takes into consideration the fact that there
   * may be intervening hidden members between {@code member} and the ancestor. If {@code targetLevel} is not null, then
   * the method will only return a member if the level at {@code distance} from the member is actually the {@code
   * targetLevel} specified.
   *
   * @param evaluator   The evaluation context
   * @param member      The member for which the ancestor is to be found
   * @param distance    The distance up the chain the ancestor is to be found.
   * @param targetLevel The desired targetLevel of the ancestor. If {@code null}, then the distance completely
   *                    determines the desired ancestor.
   * @return The ancestor member, or {@code null} if no such ancestor exists.
   */
  static Member ancestor(
    Evaluator evaluator,
    Member member,
    int distance,
    Level targetLevel ) {
    if ( ( targetLevel != null )
      && ( member.getHierarchy() != targetLevel.getHierarchy() ) ) {
      throw new MondrianException(MessageFormat.format(memberNotInLevelHierarchy,
        member.getUniqueName(), targetLevel.getUniqueName() ));
    }

    if ( distance == 0 ) {
      // Shortcut if there's nowhere to go.
      return member;
    } else if ( distance < 0 ) {
      // Can't go backwards.
      return member.getHierarchy().getNullMember();
    }

    final List<Member> ancestors = new ArrayList<>();
    final SchemaReader schemaReader = evaluator.getSchemaReader();
    schemaReader.getMemberAncestors( member, ancestors );

    Member result = member.getHierarchy().getNullMember();

    searchLoop:
    for ( int i = 0; i < ancestors.size(); i++ ) {
      final Member ancestorMember = ancestors.get( i );

      if ( targetLevel != null ) {
        if ( ancestorMember.getLevel() == targetLevel ) {
          if ( schemaReader.isVisible( ancestorMember ) ) {
            result = ancestorMember;
            break;
          } else {
            result = member.getHierarchy().getNullMember();
            break;
          }
        }
      } else {
        if ( schemaReader.isVisible( ancestorMember ) ) {
          distance--;

          // Make sure that this ancestor is really on the right
          // targetLevel. If a targetLevel was specified and at least
          // one of the ancestors was hidden, this this algorithm goes
          // too far up the ancestor list. It's not a problem, except
          // that we need to check if it's happened and return the
          // hierarchy's null member instead.
          //
          // For example, consider what happens with
          // Ancestor([Store].[Israel].[Haifa], [Store].[Store
          // State]).  The distance from [Haifa] to [Store State] is
          // 1, but that lands us at the country targetLevel, which is
          // clearly wrong.
          if ( distance == 0 ) {
            result = ancestorMember;
            break;
          }
        }
      }
    }

    return result;
  }



  /**
   * Compares two members which are known to have the same parent.
   * <p>
   * First, compare by ordinal. This is only valid now we know they're siblings, because ordinals are only unique within
   * a parent. If the dimension does not use ordinals, both ordinals will be -1.
   *
   * <p>If the ordinals do not differ, compare using regular member
   * comparison.
   *
   * @param m1 First member
   * @param m2 Second member
   * @return -1 if m1 collates less than m2, 1 if m1 collates after m2, 0 if m1 == m2.
   */
  public static int compareSiblingMembers( Member m1, Member m2 ) {
    // calculated members collate after non-calculated
    final boolean calculated1 = m1.isCalculatedInQuery();
    final boolean calculated2 = m2.isCalculatedInQuery();
    if ( calculated1 ) {
      if ( !calculated2 ) {
        return 1;
      }
    } else {
      if ( calculated2 ) {
        return -1;
      }
    }
    final Comparable k1 = m1.getOrderKey();
    final Comparable k2 = m2.getOrderKey();
    if ( ( k1 != null ) && ( k2 != null ) ) {
      return k1.compareTo( k2 );
    } else {
      final int ordinal1 = m1.getOrdinal();
      final int ordinal2 = m2.getOrdinal();
      if ( ordinal1 == ordinal2 ) {
        return m1.compareTo(m2);
      } else {
        return (ordinal1 < ordinal2) ? -1 : 1;
      }
    }
  }

  /**
   * Returns whether one of the members in a tuple is null.
   */
  public static boolean tupleContainsNullMember( Member[] tuple ) {
    for ( Member member : tuple ) {
      if ( member.isNull() ) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether one of the members in a tuple is null.
   */
  public static boolean tupleContainsNullMember( List<Member> tuple ) {
    for ( Member member : tuple ) {
      if ( member.isNull() ) {
        return true;
      }
    }
    return false;
  }

  public static Member[] makeNullTuple( final TupleType tupleType ) {
    final Type[] elementTypes = tupleType.elementTypes;
    Member[] members = new Member[ elementTypes.length ];
    for ( int i = 0; i < elementTypes.length; i++ ) {
      MemberType type = (MemberType) elementTypes[ i ];
      members[ i ] = FunUtil.makeNullMember( type );
    }
    return members;
  }

  static Member makeNullMember( MemberType memberType ) {
    Hierarchy hierarchy = memberType.getHierarchy();
    if ( hierarchy == null ) {
      return FunUtil.NullMember;
    }
    return hierarchy.getNullMember();
  }

  /**
   * Validates the arguments to a function and resolves the function.
   *
   * @param validator Validator used to validate function arguments and resolve the function
   * @param funDef    Function definition, or null to deduce definition from name, syntax and argument types
   * @param args      Arguments to the function
   * @param newArgs   Output parameter for the resolved arguments
   * @param name      Function name
   * @param syntax    Syntax style used to invoke function
   * @return resolved function definition
   */
  public static FunctionDefinition resolveFunArgs(
    Validator validator,
    FunctionDefinition funDef,
    Expression[] args,
    Expression[] newArgs,
    OperationAtom operationAtom ) {
    for ( int i = 0; i < args.length; i++ ) {
      newArgs[ i ] = validator.validate( args[ i ], false );
    }
    if ( funDef == null || validator.alwaysResolveFunDef() ) {
      funDef = validator.getDef( newArgs, operationAtom );
    }
    FunUtil.checkNativeCompatible( validator, funDef, newArgs );
    return funDef;
  }

  /**
   * Functions that dynamically return one or more members of the measures dimension prevent us from using native
   * evaluation.
   *
   * @param validator Validator used to validate function arguments and resolve the function
   * @param funDef    Function definition, or null to deduce definition from name, syntax and argument types
   * @param args      Arguments to the function
   */
  private static void checkNativeCompatible(
    Validator validator,
    FunctionDefinition funDef,
    Expression[] args ) {
    // If the first argument to a function is either:
    // 1) the measures dimension or
    // 2) a measures member where the function returns another member or
    //    a set,
    // then these are functions that dynamically return one or more
    // members of the measures dimension.  In that case, we cannot use
    // native cross joins because the functions need to be executed to
    // determine the resultant measures.
    //
    // As a result, we disallow functions like AllMembers applied on the
    // Measures dimension as well as functions like the range operator,
    // siblings, and lag, when the argument is a measure member.
    // However, we do allow functions like isEmpty, rank, and topPercent.
    //
    // Also, the Set and Parentheses functions are ok since they're
    // essentially just containers.
    Query query = validator.getQuery();
    if ( !( funDef instanceof SetFunDef )
      && !( funDef instanceof ParenthesesFunDef )
      && query != null
      && query.nativeCrossJoinVirtualCube() ) {
    	DataType[] paramCategories = funDef.getFunctionMetaData().parameterDataTypes();
      if ( paramCategories.length > 0 ) {
        final DataType cat0 = paramCategories[ 0 ];
        final Expression arg0 = args[ 0 ];
        switch ( cat0 ) {
          case DIMENSION, HIERARCHY:
            if ( arg0 instanceof DimensionExpression dimensionExpr
              && dimensionExpr.getDimension().isMeasures()
              && !( funDef instanceof HierarchyCurrentMemberFunDef ) ) {
              query.setVirtualCubeNonNativeCrossJoin();
            }
            break;
          case MEMBER:
            if ( arg0 instanceof MemberExpression memberExpr
              && memberExpr.getMember().isMeasure()
              && FunUtil.isMemberOrSet( funDef.getFunctionMetaData().returnCategory() ) ) {
              query.setVirtualCubeNonNativeCrossJoin();
            }
            break;
        }
      }
    }
  }

  private static boolean isMemberOrSet( DataType category ) {
    return category == DataType.MEMBER || category == DataType.SET;
  }

  static void appendTuple( StringBuilder buf, Member[] members ) {
    buf.append( "(" );
    for ( int j = 0; j < members.length; j++ ) {
      if ( j > 0 ) {
        buf.append( ", " );
      }
      Member member = members[ j ];
      buf.append( member.getUniqueName() );
    }
    buf.append( ")" );
  }

  /**
   * Returns whether two tuples are equal.
   *
   * <p>The members are allowed to be in different positions. For example,
   * <code>([Gender].[M], [Store].[USA]) IS ([Store].[USA],
   * [Gender].[M])</code> returns {@code true}.
   */
  static boolean equalTuple( Member[] members0, Member[] members1 ) {
    final int count = members0.length;
    if ( count != members1.length ) {
      return false;
    }
    outer:
    for ( int i = 0; i < count; i++ ) {
      // First check the member at the corresponding ordinal. It is more
      // likely to be there.
      final Member member0 = members0[ i ];
      if ( member0.equals( members1[ i ] ) ) {
        continue;
      }
      // Look for this member in other positions.
      // We can assume that the members in members0 are distinct (because
      // they belong to different dimensions), so this test is valid.
      for ( int j = 0; j < count; j++ ) {
        if ( i != j && member0.equals( members1[ j ] ) ) {
          continue outer;
        }
      }
      // This member of members0 does not occur in any position of
      // members1. The tuples are not equal.
      return false;
    }
    return true;
  }

  public static List<Member> getNonEmptyMemberChildren(
    Evaluator evaluator,
    Member member ) {
    SchemaReader sr = evaluator.getSchemaReader();
    if ( evaluator.isNonEmpty() ) {
      return sr.getMemberChildren( member, evaluator );
    } else {
      return sr.getMemberChildren( member );
    }
  }

  public static Map<Member, Access> getNonEmptyMemberChildrenWithDetails(
    Evaluator evaluator, Member member ) {
    SchemaReader sr = evaluator.getSchemaReader();
    if ( evaluator.isNonEmpty() ) {
      return (Map<Member, Access>)
        sr.getMemberChildrenWithDetails( member, evaluator );
    } else {
      return (Map<Member, Access>)
        sr.getMemberChildrenWithDetails( member, null );
    }
  }

  /**
   * Returns members of a level which are not empty (according to the criteria expressed by the evaluator).
   *
   * @param evaluator          Evaluator, determines non-empty criteria
   * @param level              Level
   * @param includeCalcMembers Whether to include calculated members
   */
  static List<Member> getNonEmptyLevelMembers(
    final Evaluator evaluator,
    final Level level,
    final boolean includeCalcMembers ) {
    SchemaReader sr = evaluator.getSchemaReader();
    if ( evaluator.isNonEmpty() ) {
      List<Member> members = sr.getLevelMembers( level, evaluator );
      if ( includeCalcMembers ) {
        return Util.addLevelCalculatedMembers( sr, level, members );
      }
      return members;
    }
    return sr.getLevelMembers( level, includeCalcMembers );
  }

  static TupleList levelMembers(
    final Level level,
    final Evaluator evaluator,
    final boolean includeCalcMembers ) {
    List<Member> memberList =
      FunUtil.getNonEmptyLevelMembers( evaluator, level, includeCalcMembers );
    TupleList tupleList;
    if ( !includeCalcMembers ) {
      memberList = FunUtil.removeCalculatedMembers( memberList );
    }
    final List<Member> memberListClone = new ArrayList<>( memberList );
    tupleList = new UnaryTupleList( memberListClone );
    return Sorter.hierarchizeTupleList( tupleList, false );
  }

  static TupleList hierarchyMembers(
    Hierarchy hierarchy,
    Evaluator evaluator,
    final boolean includeCalcMembers ) {
    TupleList tupleList = new UnaryTupleList();
    final List<Member> memberList = tupleList.slice( 0 );
    if ( evaluator.isNonEmpty() ) {
      // Allow the SQL generator to generate optimized SQL since we know
      // we're only interested in non-empty members of this level.
      for ( Level level : hierarchy.getLevels() ) {
        List<Member> members =
          FunUtil.getNonEmptyLevelMembers(
            evaluator, level, includeCalcMembers );
        memberList.addAll( members );
      }
    } else {
      final List<Member> memberList1 = FunUtil.addMembers(
        evaluator.getSchemaReader(),
        new ConcatenableList<>(),
        hierarchy);
      if ( includeCalcMembers ) {
        memberList.addAll( memberList1 );
      } else {
        // Same effect as calling removeCalculatedMembers(tupleList)
        // but one fewer copy of the list.
        for ( Member member1 : memberList1 ) {
          if ( !member1.isCalculated()
            || member1.isParentChildPhysicalMember() ) {
            memberList.add( member1 );
          }
        }
      }
    }
    return Sorter.hierarchizeTupleList( tupleList, false );
  }


  static TupleList parseTupleList(
    Evaluator evaluator,
    String string,
    List<Hierarchy> hierarchies ) {
    final IdentifierParser.TupleListBuilder builder =
      new IdentifierParser.TupleListBuilder(
        evaluator.getSchemaReader(),
        evaluator.getCube(),
        hierarchies );
    IdentifierParser.parseTupleList( builder, string );
    return builder.tupleList;
  }

  /**
   * Parses a tuple, of the form '(member, member, ...)'. There must be precisely one member for each hierarchy.
   *
   * @param evaluator   Evaluator, provides a {@link org.eclipse.daanse.olap.api.SchemaReader} and {@link org.eclipse.daanse.olap.api.element.Cube}
   * @param string      String to parse
   * @param i           Position to start parsing in string
   * @param members     Output array of members
   * @param hierarchies Hierarchies of the members
   * @return Position where parsing ended in string
   */
  private static int parseTuple(
    final Evaluator evaluator,
    String string,
    int i,
    final Member[] members,
    List<Hierarchy> hierarchies ) {
    final Builder builder =
      new IdentifierParser.TupleBuilder(
        evaluator.getSchemaReader(),
        evaluator.getCube(),
        hierarchies ) {
        @Override
		public void tupleComplete() {
          super.tupleComplete();
          memberList.toArray( members );
        }
      };
    return IdentifierParser.parseTuple( builder, string, i );
  }

  /**
   * Parses a tuple, such as "([Gender].[M], [Marital Status].[S])".
   *
   * @param evaluator   Evaluator, provides a {@link org.eclipse.daanse.olap.api.SchemaReader} and {@link org.eclipse.daanse.olap.api.element.Cube}
   * @param string      String to parse
   * @param hierarchies Hierarchies of the members
   * @return Tuple represented as array of members
   */
  static Member[] parseTuple(
    Evaluator evaluator,
    String string,
    List<Hierarchy> hierarchies ) {
    final Member[] members = new Member[ hierarchies.size() ];
    FunUtil.parseTuple( evaluator, string, 0, members, hierarchies );
    // todo: check for garbage at end of string
    if ( FunUtil.tupleContainsNullMember( members ) ) {
      return null;
    }
    return members;
  }

  static List<Member> parseMemberList(
    Evaluator evaluator,
    String string,
    Hierarchy hierarchy ) {
    IdentifierParser.MemberListBuilder builder =
      new IdentifierParser.MemberListBuilder(
        evaluator.getSchemaReader(),
        evaluator.getCube(),
        hierarchy );
    IdentifierParser.parseMemberList( builder, string );
    return builder.memberList;
  }

  private static int parseMember(
    Evaluator evaluator,
    String string,
    int i,
    final Member[] members,
    Hierarchy hierarchy ) {
    IdentifierParser.MemberListBuilder builder =
      new IdentifierParser.MemberListBuilder(
        evaluator.getSchemaReader(), evaluator.getCube(), hierarchy ) {
        @Override
        public void memberComplete() {
          members[ 0 ] = resolveMember( hierarchyList.get( 0 ) );
          segmentList.clear();
        }
      };
    return IdentifierParser.parseMember( builder, string, i );
  }

  static Member parseMember(
    Evaluator evaluator, String string, Hierarchy hierarchy ) {
    Member[] members = { null };
    FunUtil.parseMember( evaluator, string, 0, members, hierarchy );
    // todo: check for garbage at end of string
    final Member member = members[ 0 ];
    if ( member == null ) {
      throw new MdxChildObjectNotFoundException(
        string, evaluator.getCube().getQualifiedName() );
    }
    return member;
  }

  /**
   * Returns whether an expression is worth wrapping in "Cache( ... )".
   *
   * @param exp Expression
   * @return Whether worth caching
   */
  public static boolean worthCaching( Expression exp ) {
    // Literal is not worth caching.
    if ( exp instanceof Literal ) {
      return false;
    }
    // Member, hierarchy, level, or dimension expression is not worth
    // caching.
    if ( exp instanceof MemberExpression
      || exp instanceof LevelExpression
      || exp instanceof HierarchyExpressionImpl
      || exp instanceof DimensionExpression) {
      return false;
    }
    if ( exp instanceof ResolvedFunCall call && call.getFunDef() instanceof SetFunDef) {
      // A set of literals is not worth caching.
      for ( Expression setArg : call.getArgs() ) {
          if ( FunUtil.worthCaching( setArg ) ) {
            return true;
          }
      }
      return false;
    }
    return true;
  }

  /**
   * Returns true if leftTuple Exists w/in rightTuple
   *
   * @param leftTuple        tuple from arg one of EXISTS()
   * @param rightTuple       tuple from arg two of EXISTS()
   * @param leftHierarchies  list of hierarchies from leftTuple, in the same order
   * @param rightHierarchies list of the hiearchies from rightTuple, in the same order
   * @return true if each member from leftTuple is somewhere in the hierarchy chain of the corresponding member from
   * rightTuple, false otherwise. If there is no explicit corresponding member from either right or left, then the
   * default member is used.
   */
  static boolean existsInTuple(
    final List<Member> leftTuple, final List<Member> rightTuple,
    final List<Hierarchy> leftHierarchies,
    final List<Hierarchy> rightHierarchies,
    final Evaluator eval ) {
    List<Member> checkedMembers = new ArrayList<>();

    for ( Member leftMember : leftTuple ) {
      Member rightMember = FunUtil.getCorrespondingMember(
        leftMember, rightTuple, rightHierarchies, eval );
      checkedMembers.add( rightMember );
      if ( !leftMember.isOnSameHierarchyChain( rightMember ) ) {
        return false;
      }
    }
    // this loop handles members in the right tuple not present in left
    // Such a member could only impact the resulting tuple list if the
    // default member of the hierarchy is not the all member.
    for ( Member rightMember : rightTuple ) {
      if ( checkedMembers.contains( rightMember ) ) {
        // already checked in the previous loop
        continue;
      }
      Member leftMember = FunUtil.getCorrespondingMember(
        rightMember, leftTuple, leftHierarchies, eval );
      if ( !leftMember.isOnSameHierarchyChain( rightMember ) ) {
        return false;
      }
    }
    return true;
  }

  private static boolean isOnSameHierarchyChain( Member mA, Member mB ) {
    return ( FunUtil.isAncestorOf( mA, mB, false ) )
      || ( FunUtil.isAncestorOf( mB, mA, false ) );
  }

  /**
   * Returns the corresponding member from tuple, or the default member for the hierarchy if member is not explicitly
   * contained in the tuple.
   *
   * @param member           source member
   * @param tuple            tuple containing the target member
   * @param tupleHierarchies list of the hierarchies explicitly contained in the tuple, in the same order.
   * @return target member
   */
  private static Member getCorrespondingMember(
    final Member member, final List<Member> tuple,
    final List<Hierarchy> tupleHierarchies,
    final Evaluator eval ) {
    assert tuple.size() == tupleHierarchies.size();
    int dimPos = tupleHierarchies.indexOf( member.getHierarchy() );
    if ( dimPos >= 0 ) {
      return tuple.get( dimPos );
    } else if ( eval != null ) {
      return eval.getContext( member.getHierarchy() );
    } else {
      return member.getHierarchy().getDefaultMember();
    }
  }

  // ~ Inner classes ---------------------------------------------------------


  static class SetWrapper {
    List v = new ArrayList();
    public int errorCount = 0;
    public int nullCount = 0;

    // private double avg = Double.NaN;
    // TODO: parameterize inclusion of nulls
    // by making this a method of the SetWrapper, we can cache the result
    // this allows its reuse in Correlation
    // public double getAverage() {
    //     if (avg == Double.NaN) {
    //         double sum = 0.0;
    //         for (int i = 0; i < resolvers.size(); i++) {
    //             sum += ((Number) resolvers.elementAt(i)).doubleValue();
    //         }
    //         // TODO: should look at context and optionally include nulls
    //         avg = sum / (double) resolvers.size();
    //     }
    //     return avg;
    // }
  }

  /**
   * Compares cell values, so that larger values compare first.
   *
   * <p>Nulls compare last, exceptions (including the
   * object which indicates the the cell is not in the cache yet) next, then numbers and strings are compared by value.
   */
  public static class DescendingValueComparator implements Comparator {
    /**
     * The singleton.
     */
    static final DescendingValueComparator instance =
      new DescendingValueComparator();

    @Override
	public int compare( Object o1, Object o2 ) {
      return -FunUtil.compareValues( o1, o2 );
    }
  }

  /**
   * Null member of unknown hierarchy.
   */
  private static class NullMember implements Member {
    @Override
	public Member getParentMember() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Level getLevel() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Hierarchy getHierarchy() {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getParentUniqueName() {
      throw new UnsupportedOperationException();
    }

    @Override
	public MemberType getMemberType() {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isParentChildLeaf() {
      return false;
    }

    @Override
	public boolean isParentChildPhysicalMember() {
      return false;
    }

    @Override
	public void setName( String name ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isAll() {
      return false;
    }

    @Override
	public boolean isMeasure() {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isNull() {
      return true;
    }

    @Override
	public boolean isChildOrEqualTo( Member member ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isCalculated() {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isEvaluated() {
      throw new UnsupportedOperationException();
    }

    @Override
	public int getSolveOrder() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Expression getExpression() {
      throw new UnsupportedOperationException();
    }

    @Override
	public List<Member> getAncestorMembers() {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isCalculatedInQuery() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Object getPropertyValue( String propertyName ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public Object getPropertyValue( String propertyName, boolean matchCase ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getPropertyFormattedValue( String propertyName ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public void setProperty( String name, Object value ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public Property[] getProperties() {
      throw new UnsupportedOperationException();
    }

    @Override
	public int getOrdinal() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Comparable getOrderKey() {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isHidden() {
      throw new UnsupportedOperationException();
    }

    @Override
	public int getDepth() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Member getDataMember() {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getUniqueName() {
      throw new UnsupportedOperationException();
    }

    @Override public boolean isOnSameHierarchyChain( Member otherMember ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getName() {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getDescription() {
      throw new UnsupportedOperationException();
    }

    @Override
	public OlapElement lookupChild(
        SchemaReader schemaReader, Segment s, MatchType matchType ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getQualifiedName() {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getCaption() {
      throw new UnsupportedOperationException();
    }

    @Override
	public String getLocalized( LocalizedProperty prop, Locale locale ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean isVisible() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Dimension getDimension() {
      throw new UnsupportedOperationException();
    }

    @Override
	public Map<String, Object> getMetadata() {
      throw new UnsupportedOperationException();
    }

    @Override
	public int compareTo( Object o ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public boolean equals( Object obj ) {
      throw new UnsupportedOperationException();
    }

    @Override
	public int hashCode() {
      throw new UnsupportedOperationException();
    }


  }


}
