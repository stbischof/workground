/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2021 Hitachi Vantara..  All rights reserved.
*/

package mondrian.olap.fun;

import org.eclipse.daanse.olap.api.model.Hierarchy;

import mondrian.calc.Calc;
import mondrian.calc.ExpCompiler;
import mondrian.calc.ListCalc;
import mondrian.calc.TupleList;
import mondrian.calc.impl.AbstractCalc;
import mondrian.calc.impl.AbstractDoubleCalc;
import mondrian.calc.impl.ValueCalc;
import mondrian.mdx.ResolvedFunCall;
import mondrian.olap.Evaluator;
import mondrian.olap.FunDef;

/**
 * Definition of the <code>Avg</code> MDX function.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class AvgFunDef extends AbstractAggregateFunDef {
  static final ReflectiveMultiResolver Resolver =
      new ReflectiveMultiResolver( "Avg", "Avg(<Set>[, <Numeric Expression>])",
          "Returns the average value of a numeric expression evaluated over a set.", new String[] { "fnx", "fnxn" },
          AvgFunDef.class );

  private static final String TIMING_NAME = AvgFunDef.class.getSimpleName();

  public AvgFunDef( FunDef dummyFunDef ) {
    super( dummyFunDef );
  }

  public Calc compileCall( ResolvedFunCall call, ExpCompiler compiler ) {
    final ListCalc listCalc = compiler.compileList( call.getArg( 0 ) );
    final Calc calc =
        call.getArgCount() > 1 ? compiler.compileScalar( call.getArg( 1 ), true ) : new ValueCalc( call.getFunName(),call.getType() );
    return new AbstractDoubleCalc( call.getFunName(),call.getType(), new Calc[] { listCalc, calc } ) {
      public double evaluateDouble( Evaluator evaluator ) {
        evaluator.getTiming().markStart( AvgFunDef.TIMING_NAME );
        final int savepoint = evaluator.savepoint();
        try {
          TupleList memberList = AbstractAggregateFunDef.evaluateCurrentList( listCalc, evaluator );
          evaluator.setNonEmpty( false );
          return (Double) FunUtil.avg( evaluator, memberList, calc );
        } finally {
          evaluator.restore( savepoint );
          evaluator.getTiming().markEnd( AvgFunDef.TIMING_NAME );
        }
      }

      public boolean dependsOn( Hierarchy hierarchy ) {
        return AbstractCalc.anyDependsButFirst( getCalcs(), hierarchy );
      }
    };
  }
}
