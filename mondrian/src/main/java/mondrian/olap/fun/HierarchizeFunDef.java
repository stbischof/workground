/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2020 Hitachi Vantara..  All rights reserved.
 */

package mondrian.olap.fun;

import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import mondrian.calc.TupleList;
import mondrian.calc.TupleListCalc;
import mondrian.calc.impl.AbstractListCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.fun.sort.Sorter;

/**
 * Definition of the <code>Hierarchize</code> MDX function.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class HierarchizeFunDef extends FunDefBase {
  static final String[] prePost = { "PRE", "POST" };
  static final ReflectiveMultiResolver Resolver =
    new ReflectiveMultiResolver(
      "Hierarchize",
      "Hierarchize(<Set>[, POST])",
      "Orders the members of a set in a hierarchy.",
      new String[] { "fxx", "fxxy" },
      HierarchizeFunDef.class,
      HierarchizeFunDef.prePost );

  public HierarchizeFunDef( FunctionDefinition dummyFunDef ) {
    super( dummyFunDef );
  }

  @Override
public Calc compileCall( ResolvedFunCall call, ExpressionCompiler compiler ) {
    final TupleListCalc tupleListCalc =
      compiler.compileList( call.getArg( 0 ), true );
    String order = FunUtil.getLiteralArg( call, 1, "PRE", HierarchizeFunDef.prePost );
    final boolean post = order.equals( "POST" );
    return new AbstractListCalc( call.getType(), new Calc[] { tupleListCalc } ) {
      @Override
	public TupleList evaluateList( Evaluator evaluator ) {
        TupleList list = tupleListCalc.evaluateList( evaluator );
        return Sorter.hierarchizeTupleList( list, post );
      }
    };
  }
}
