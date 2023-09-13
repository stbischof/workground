/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2002-2020 Hitachi Vantara..  All rights reserved.
*/
package mondrian.olap.fun;

import java.util.Map;
import java.util.Set;

import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.HierarchyCalc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.base.nested.AbstractProfilingNestedMemberCalc;
import org.eigenbase.util.property.StringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.olap.Evaluator;
import mondrian.olap.MondrianException;
import mondrian.olap.MondrianProperties;
import mondrian.olap.type.Type;
import mondrian.resource.MondrianResource;
import mondrian.rolap.RolapEvaluator;
import mondrian.rolap.RolapHierarchy;

/**
 * Definition of the <code>&lt;Hierarchy&gt;.CurrentMember</code> MDX builtin function.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
public class HierarchyCurrentMemberFunDef extends FunDefBase {
  private static final Logger LOGGER = LoggerFactory.getLogger( HierarchyCurrentMemberFunDef.class );

  static final HierarchyCurrentMemberFunDef instance = new HierarchyCurrentMemberFunDef();

  private HierarchyCurrentMemberFunDef() {
    super( "CurrentMember", "Returns the current member along a hierarchy during an iteration.", "pmh" );
  }

  @Override
public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler ) {
    final HierarchyCalc hierarchyCalc = compiler.compileHierarchy( call.getArg( 0 ) );
    final Hierarchy hierarchy = hierarchyCalc.getType().getHierarchy();
    if ( hierarchy != null ) {
      return new CurrentMemberFixedCalc( call.getType(), hierarchy );
    } else {
      return new CurrentMemberCalc( call.getType(), hierarchyCalc );
    }
  }

  /**
   * Compiled implementation of the Hierarchy.CurrentMember function that evaluates the hierarchy expression first.
   */
  public static class CurrentMemberCalc extends AbstractProfilingNestedMemberCalc {
    private final HierarchyCalc hierarchyCalc;

    public CurrentMemberCalc( Type type, HierarchyCalc hierarchyCalc ) {
      super( type, new Calc[] { hierarchyCalc } );
      this.hierarchyCalc = hierarchyCalc;
    }

 

    @Override
	public Member evaluate( Evaluator evaluator ) {
      Hierarchy hierarchy = hierarchyCalc.evaluate( evaluator );
      HierarchyCurrentMemberFunDef.validateSlicerMembers( hierarchy, evaluator );
      return evaluator.getContext( hierarchy );
    }

    @Override
	public boolean dependsOn( Hierarchy hierarchy ) {
      return hierarchyCalc.getType().usesHierarchy( hierarchy, false );
    }
  }

  /**
   * Compiled implementation of the Hierarchy.CurrentMember function that uses a fixed hierarchy.
   */
  public static class CurrentMemberFixedCalc extends AbstractProfilingNestedMemberCalc {
    // getContext works faster if we give RolapHierarchy rather than
    // Hierarchy
    private final RolapHierarchy hierarchy;

    public CurrentMemberFixedCalc( Type type, Hierarchy hierarchy ) {
      super( type, new Calc[] {} );
      assert hierarchy != null;
      this.hierarchy = (RolapHierarchy) hierarchy;
    }



    @Override
	public Member evaluate( Evaluator evaluator ) {
      HierarchyCurrentMemberFunDef.validateSlicerMembers( hierarchy, evaluator );
      return evaluator.getContext( hierarchy );
    }

    @Override
	public boolean dependsOn( Hierarchy hierarchy ) {
      return this.hierarchy == hierarchy;
    }

  }

  private static void validateSlicerMembers( Hierarchy hierarchy, Evaluator evaluator ) {
    if ( evaluator instanceof RolapEvaluator rev ) {
      StringProperty alertProperty = MondrianProperties.instance().CurrentMemberWithCompoundSlicerAlert;
      String alertValue = alertProperty.get();

      if ( alertValue.equalsIgnoreCase( org.apache.logging.log4j.Level.OFF.toString() ) ) {
        return; // No validation
      }

      Map<Hierarchy, Set<Member>> map = rev.getSlicerMembersByHierarchy();
      Set<Member> members = map.get( hierarchy );

      if ( members != null && members.size() > 1 ) {
        MondrianException exception =
            MondrianResource.instance().CurrentMemberWithCompoundSlicer.ex( hierarchy.getUniqueName() );

        if ( alertValue.equalsIgnoreCase( org.apache.logging.log4j.Level.WARN.toString() ) ) {
          HierarchyCurrentMemberFunDef.LOGGER.warn( exception.getMessage() );
        } else if ( alertValue.equalsIgnoreCase( org.apache.logging.log4j.Level.ERROR.toString() ) ) {
          throw MondrianResource.instance().CurrentMemberWithCompoundSlicer.ex( hierarchy.getUniqueName() );
        }
      }
    }
  }
}
