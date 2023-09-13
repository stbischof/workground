/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/
package mondrian.olap.fun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.fun.SetFunDef.SetListCalc;
import mondrian.olap.type.SetType;

class IifFunDefTest {

  private Exp logicalParamMock = mock( Exp.class );
  private Exp trueCaseParamMock = mock( Exp.class );
  private Exp falseCaseParamMock = mock( Exp.class );
  private FunctionDefinition funDefMock = mock( FunctionDefinition.class );
  private ExpressionCompiler compilerMock = mock( ExpressionCompiler.class );
  private Exp[] args = new Exp[] { logicalParamMock, trueCaseParamMock, falseCaseParamMock };
  private SetType setTypeMock = mock( SetType.class );
  private SetListCalc setListCalc;
  ResolvedFunCallImpl call;

  @BeforeEach
  protected void setUp() throws Exception {
    when( trueCaseParamMock.getType() ).thenReturn( setTypeMock );
    setListCalc = new SetListCalc( setTypeMock, new Exp[] { args[1] }, compilerMock, ResultStyle.LIST_MUTABLELIST );
    call = new ResolvedFunCallImpl( funDefMock, args, setTypeMock );
    when( compilerMock.compileAs( any(), any(), any() ) ).thenReturn(  setListCalc );
  }

  @Test
  void testGetResultType() {
    ResultStyle actualResStyle = null;
    ResultStyle expectedResStyle = setListCalc.getResultStyle();
    // Compile calculation for IIf function for (<Logical Expression>, <SetType>, <SetType>) params
    Calc calc = IifFunDef.SET_INSTANCE.compileCall( call, compilerMock );
    try {
      actualResStyle = calc.getResultStyle();
    } catch ( Exception e ) {
      fail( "Should not have thrown any exception." );
    }
    assertNotNull( actualResStyle );
    assertEquals( expectedResStyle, actualResStyle );

  }

}
