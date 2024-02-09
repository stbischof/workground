/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2015-2017 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap;

import static mondrian.resource.MondrianResource.QueryCanceled;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import mondrian.olap.MondrianException;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mondrian.olap.QueryCanceledException;
import mondrian.server.Execution;
import mondrian.server.Locus;
import mondrian.server.StatementImpl;
import mondrian.server.monitor.Monitor;

/**
 * @author Andrey Khayrutdinov
 */
class SqlStatementTest {

  private Monitor monitor;
  private Context context;
  private RolapConnection rolapConnection;
  private StatementImpl statMock;
  private Execution execution;
  private Locus locus;
  private SqlStatement statement;

  @BeforeEach
  public void beforeEach() {
    monitor = mock(Monitor.class);

    context = mock(Context.class);
    when(context.getMonitor()).thenReturn(monitor);

    rolapConnection = mock(RolapConnection.class);
    when(rolapConnection.getContext()).thenReturn(context);

    statMock = mock(StatementImpl.class);
    when(statMock.getMondrianConnection()).thenReturn(rolapConnection);

    execution = new Execution(statMock, 0);
    execution = spy(execution);
    doThrow(new QueryCanceledException(QueryCanceled))
            .when(execution).checkCancelOrTimeout();

    locus = new Locus(execution, "component", "message");

    statement = new SqlStatement(null, "sql", null, 0, 0, locus, 0, 0, null);
    statement = spy(statement);
  }

  @Test
  void testPrintingNilDurationIfCancelledBeforeStart() throws Exception {
    try {
      statement.execute();
    } catch (Exception e) {
      Throwable cause = e.getCause();
      if (!(cause instanceof QueryCanceledException)) {
        String message = "Expected QueryCanceledException but caught "
          + ((cause == null) ? null : cause.getClass().getSimpleName());
        fail(message);
      }
    }

    verify(statement).formatTimingStatus(eq(0L), anyInt());
  }

  @Test
  void testGetDialectSchemaAndConnectionNull() {
    try {
      this.statement.getDialect(null);
      fail("Should throw exception");
    } catch (Exception e) {
      //TODO Commented by reason context implementation
      //verify(statement).createDialect();
    }
  }

  @Test
  void testGetDialectDialectNull() {
    RolapSchema schema = mock(RolapSchema.class);
    when(schema.getDialect()).thenReturn(null);
    try {
      statement.getDialect(schema);
      fail("Should throw exception");
    } catch (Exception e) {
      //TODO Commented by reason context implementation
      //verify(statement).createDialect();
    }
  }

  @Test
  void testGetDialect() {
    RolapSchema schema = mock(RolapSchema.class);
    Dialect dialect = mock(Dialect.class);
    when(schema.getDialect()).thenReturn(dialect);
    Dialect dialectReturn = statement.getDialect(schema);
    assertNotNull(dialectReturn);
    assertEquals(dialect, dialectReturn);
  }

  @Test
  void testCreateDialect() {
    statement = mock(SqlStatement.class);
    Dialect dialect = mock(Dialect.class);
    Context context = mock(Context.class);
    when(statement.getContext()).thenReturn(context);
    when(statement.getDialect(any())).thenCallRealMethod();
    when(context.getDialect()).thenReturn(dialect);
    Dialect dialectReturn = statement.getDialect(null);
    assertNotNull(dialectReturn);
    assertEquals(dialect, dialectReturn);
  }
}
