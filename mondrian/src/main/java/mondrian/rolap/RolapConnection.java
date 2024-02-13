/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (C) 2001-2005 Julian Hyde
 * Copyright (C) 2005-2020 Hitachi Vantara and others
 * All Rights Reserved.
 *
 * For more information please visit the Project: Hitachi Vantara - Mondrian
 *
 * ---- All changes after Fork in 2023 ------------------------
 *
 * Project: Eclipse daanse
 *
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors after Fork in 2023:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */

package mondrian.rolap;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import mondrian.olap.exceptions.FailedToParseQueryException;
import org.eclipse.daanse.olap.api.CacheControl;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.RolapConnectionProps;
import org.eclipse.daanse.olap.api.SchemaReader;
import org.eclipse.daanse.olap.api.Statement;
import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.eclipse.daanse.olap.api.query.component.QueryComponent;
import org.eclipse.daanse.olap.api.result.Cell;
import org.eclipse.daanse.olap.api.result.Position;
import org.eclipse.daanse.olap.api.result.Result;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.calc.api.todo.TupleCursor;
import org.eclipse.daanse.olap.calc.api.todo.TupleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.calc.impl.TupleCollections;
import mondrian.olap.ConnectionBase;
import mondrian.olap.QueryCanceledException;
import mondrian.olap.QueryImpl;
import mondrian.olap.QueryTimeoutException;
import mondrian.olap.ResourceLimitExceededException;
import mondrian.olap.ResultBase;
import mondrian.olap.ResultLimitExceededException;
import mondrian.olap.RoleImpl;
import mondrian.olap.Util;
import mondrian.parser.MdxParserValidator;
import mondrian.server.Execution;
import mondrian.server.Locus;
import mondrian.util.FauxMemoryMonitor;
import mondrian.util.MemoryMonitor;
import mondrian.util.NotificationMemoryMonitor;

public class RolapConnection extends ConnectionBase {
  private static final Logger LOGGER =
    LoggerFactory.getLogger( RolapConnection.class );
  private static final AtomicInteger ID_GENERATOR = new AtomicInteger();


  private final RolapConnectionProps rolapConnectionProps;

  private Context context = null;
  private final String catalogName;
  private final RolapSchema schema;
  private SchemaReader schemaReader;
  protected Role role;
  private Locale locale = Locale.getDefault();
  private Scenario scenario;
  private boolean closed = false;

  private final int id;
  private final Statement internalStatement;


	public RolapConnection(Context context, RolapConnectionProps rolapConnectionProps) {
		this(context, null, rolapConnectionProps);
	}

  /**
   * Creates a RolapConnection.
   *
   * <p>Only {@link RolapSchemaPool#get} calls this with
   * schema != null (to create a schema's internal connection).
   * Other uses retrieve a schema from the cache based upon
   * the <code>Catalog</code> property.
   *
   * @param server      Server instance this connection belongs to
   * @param connectInfo Connection properties; keywords are described in
   *                    {@link RolapConnectionProperties}.
   * @param schema      Schema for the connection. Must be null unless this is to
   *                    be an internal connection.
   * @param context  If not null an external DataSource to be used
   *                    by Mondrian
   */
	RolapConnection(Context context, RolapSchema schema, RolapConnectionProps rolapConnectionProps) {
    super();

    this.context = context;
    this.id = ID_GENERATOR.getAndIncrement();

    assert rolapConnectionProps != null;

    this.rolapConnectionProps = rolapConnectionProps;
	this.catalogName = context.getName();

    Role roleInner = null;

    // Register this connection before we register its internal statement.
    context.addConnection( this );

    if ( schema == null ) {
      // If RolapSchema.Pool.get were to call this with schema == null,
      // we would loop.
      Statement bootstrapStatement = createInternalStatement( false, this);
      final Locus locus =
        new Locus(
          new Execution( bootstrapStatement, 0 ),
          null,
          "Initializing connection" );
      Locus.push( locus );
      try {

          schema = RolapSchemaPool.instance().get(
            catalogName,
            context,
            rolapConnectionProps );

      } finally {
        Locus.pop( locus );
        bootstrapStatement.close();
      }
      internalStatement =
        schema.getInternalConnection().getInternalStatement();
      List<String> roleNameList =rolapConnectionProps.roles();
      if ( !roleNameList.isEmpty() ) {

        List<Role> roleList = new ArrayList<>();
        for ( String roleName : roleNameList ) {

          Role role1 = schema.lookupRole( roleName );

          if ( role1 == null ) {
            throw Util.newError(
              new StringBuilder("Role '").append(roleName).append("' not found").toString() );
          }
          roleList.add( role1 );
        }
        switch ( roleList.size() ) {
          case 0:
            // If they specify 'Role=;', the list of names will be
            // empty, and the effect will be as if they did specify
            // Role at all.
            roleInner = null;
            break;
          case 1:
            roleInner = roleList.get( 0 );
            break;
          default:
            roleInner = RoleImpl.union( roleList );
            break;
        }
      }
    } else {
      this.internalStatement = createInternalStatement( true,this);
    }

    if ( roleInner == null ) {
      roleInner = schema.getDefaultRole();
    }

    // Set the locale.
    this.locale  =rolapConnectionProps.locale();

    this.schema = schema;
    setRole( roleInner );
  }

   /**
   * Returns the identifier of this connection. Unique within the lifetime of
   * this JVM.
   *
   * @return Identifier of this connection
   */
	@Override
  public int getId() {
    return id;
  }

  @Override
protected Logger getLogger() {
    return LOGGER;
  }


  @Override
public void close() {
    if ( !closed ) {
      closed = true;
      context.removeConnection( this );
    }
    if ( internalStatement != null ) {
      internalStatement.close();
    }
  }

  @Override
public RolapSchema getSchema() {
    return schema;
  }



  @Override
public String getCatalogName() {
    return catalogName;
  }

  @Override
public Locale getLocale() {
    return locale;
  }

  public void setLocale( Locale locale ) {
    if ( locale == null ) {
      throw new IllegalArgumentException( "locale must not be null" );
    }
    this.locale = locale;
  }

  @Override
public SchemaReader getSchemaReader() {
    return schemaReader;
  }

  @Override
public CacheControl getCacheControl( PrintWriter pw ) {
    return context.getAggregationManager().getCacheControl( this, pw );
  }

  /**
   * Executes a Query.
   *
   * @param query Query parse tree
   * @throws ResourceLimitExceededException if some resource limit specified
   *                                        in the property file was exceeded
   * @throws QueryCanceledException         if query was canceled during execution
   * @throws QueryTimeoutException          if query exceeded timeout specified in
   *                                        the property file
   * @deprecated Use {@link #execute(mondrian.server.Execution)}; this method
   * will be removed in mondrian-4.0
   */
  @Deprecated(since = "this method will be removed in mondrian-4.0")
@Override
public Result execute( Query query ) {
    final Statement statement = query.getStatement();
    Execution execution =
      new Execution( statement, statement.getQueryTimeoutMillis() );
    return execute( execution );
  }

  /**
   * Executes a statement.
   *
   * @param execution Execution context (includes statement, query)
   * @throws ResourceLimitExceededException if some resource limit specified
   *                                        in the property file was exceeded
   * @throws QueryCanceledException         if query was canceled during execution
   * @throws QueryTimeoutException          if query exceeded timeout specified in
   *                                        the property file
   */
  @Override
  public Result execute( final Execution execution ) {
    return
      context.getResultShepherd()
        .shepherdExecution(
          execution,
          new Callable<Result>() {
            @Override
			public Result call() throws Exception {
              return executeInternal( execution );
            }
          } );
  }

  private Result executeInternal( final Execution execution ) {
    final Statement statement = execution.getMondrianStatement();
    // Cleanup any previous executions still running
    synchronized ( statement ) {
      final Execution previousExecution =
        statement.getCurrentExecution();
      if ( previousExecution != null ) {
        statement.end( previousExecution );
      }
    }
    final Query query = statement.getQuery();
    final MemoryMonitor.Listener listener = new MemoryMonitor.Listener() {
      @Override
	public void memoryUsageNotification( long used, long max ) {
        execution.setOutOfMemory(
          new StringBuilder("OutOfMemory used=")
              .append(used)
              .append(", max=")
              .append(max)
              .append(" for query: ")
              .append(query.toString())
              .toString()
            // connection string can contain user name and password
            //+ " for connection: "
            //+ getConnectString()
        );
      }
    };

	MemoryMonitor mm = context.getConfig().memoryMonitor() ? new NotificationMemoryMonitor() : new FauxMemoryMonitor();

    final long currId = execution.getId();
    try {
      mm.addListener( listener, context.getConfig().memoryMonitorThreshold() );
      // Check to see if we must punt
      execution.checkCancelOrTimeout();

      if ( LOGGER.isDebugEnabled() ) {
        LOGGER.debug( Util.unparse( query ) );
      }

      if ( RolapUtil.MDX_LOGGER.isDebugEnabled() ) {
        RolapUtil.MDX_LOGGER.debug( new StringBuilder().append(currId)
            .append(": ").append(Util.unparse( query )).toString() );
      }

      final Locus locus = new Locus( execution, null, "Loading cells" );
      Locus.push( locus );
      Result result;
      try {
        statement.start( execution );
        ( (RolapCube) query.getCube() ).clearCachedAggregations( true );
        result = new RolapResult( execution, true );
        int i = 0;
        for ( QueryAxis axis : query.getAxes() ) {
          if ( axis.isNonEmpty() ) {
            result = new NonEmptyResult( result, execution, i );
          }
          ++i;
        }
      } finally {
        Locus.pop( locus );
        ( (RolapCube) query.getCube() ).clearCachedAggregations( true );
      }
      statement.end( execution );
      return result;
    } catch ( ResultLimitExceededException e ) {
      // query has been punted
      throw e;
    } catch ( Exception e ) {
      try {
        if ( !execution.isCancelOrTimeout() ) {
          statement.end( execution );
        }
      } catch ( Exception e1 ) {
        // We can safely ignore that cleanup exception.
        // If an error is encountered here, it means that
        // one was already encountered at statement.start()
        // above and the exception we will throw after the
        // cleanup is the same as the original one.
      }
      String queryString;
      try {
        queryString = Util.unparse( query );
      } catch ( Exception e1 ) {
        queryString = "?";
      }
      throw Util.newError(
        e,
        new StringBuilder("Error while executing query [").append(queryString).append("]").toString() );
    } finally {
      mm.removeListener( listener );
      if ( RolapUtil.MDX_LOGGER.isDebugEnabled() ) {
        final long elapsed = execution.getElapsedMillis();
        RolapUtil.MDX_LOGGER.debug(
          new StringBuilder().append(currId).append(": exec: ").append(elapsed).append(" ms").toString() );
      }
    }
  }

  @Override
public void setRole( Role role ) {
    assert role != null;

    this.role = role;
    this.schemaReader = new RolapSchemaReader( context,role, schema );
  }

  @Override
public Role getRole() {
    Util.assertPostcondition( role != null, "role != null" );

    return role;
  }

  public void setScenario( Scenario scenario ) {
    this.scenario = scenario;
  }

  @Override
  public Scenario getScenario() {
    return scenario;
  }


  @Override
public QueryComponent parseStatement(String query ) {
    Statement statement = createInternalStatement( false ,this);
    final Locus locus =
      new Locus(
        new Execution( statement, 0 ),
        "Parse/validate MDX statement",
        null );
    Locus.push( locus );
    try {
      QueryComponent queryPart =
        parseStatement( statement, query, context.getFunctionService(), false );
      if ( queryPart instanceof QueryImpl q) {
          q.setOwnStatement( true );
        statement = null;
      }
      return queryPart;
    } finally {
      Locus.pop( locus );
      if ( statement != null ) {
        statement.close();
      }
    }
  }

  @Override
public Expression parseExpression( String expr ) {
    boolean debug = false;
    if ( getLogger().isDebugEnabled() ) {
        String msg  = Util.NL + expr;
        getLogger().debug(msg);
    }
    final Statement statement = getInternalStatement();
    try {
      MdxParserValidator parser = createParser();
      return parser.parseExpression( statement, expr, debug, context.getFunctionService() );
    } catch ( Throwable exception ) {
      throw new FailedToParseQueryException( expr, exception );
    }
  }

  @Override
public Statement getInternalStatement() {
    if ( internalStatement == null ) {
      return schema.getInternalConnection().getInternalStatement();
    } else {
      return internalStatement;
    }
  }

  private Statement createInternalStatement( boolean reentrant, Connection connection ) {
    final Statement statement =
      reentrant
        ? new ReentrantInternalStatement(connection)
        : new InternalStatement(connection);
    context.addStatement( statement );
    return statement;
  }


  @Deprecated() //finf a better way for agg manager.
  @Override
public DataSource getDataSource() {
      return getContext().getDataSource();
    }
  @Override
public Context getContext() {
      return context;
    }

  /**
   * Helper method to allow olap4j wrappers to implement
   * {@link org.olap4j.OlapConnection#createScenario()}.
   *
   * @return new Scenario
   */
  public Scenario createScenario() {
    final ScenarioImpl scenarioInner = new ScenarioImpl();
    scenarioInner.register( schema );
    return scenarioInner;
  }

  /**
   * A <code>NonEmptyResult</code> filters a result by removing empty rows
   * on a particular axis.
   */
  static class NonEmptyResult extends ResultBase {

    final Result underlying;
    private final int axis;
    private final Map<Integer, Integer> map;
    /**
     * workspace. Synchronized access only.
     */
    private final int[] pos;

    /**
     * Creates a NonEmptyResult.
     *
     * @param result    Result set
     * @param execution Execution context
     * @param axis      Which axis to make non-empty
     */
    NonEmptyResult( Result result, Execution execution, int axis ) {
      super( execution, result.getAxes().clone() );

      this.underlying = result;
      this.axis = axis;
      this.map = new HashMap<>();
      int axisCount = underlying.getAxes().length;
      this.pos = new int[ axisCount ];
      this.slicerAxis = underlying.getSlicerAxis();
      TupleList tupleList =
        ( (RolapAxis) underlying.getAxes()[ axis ] ).getTupleList();

      final TupleList filteredTupleList;


		filteredTupleList = TupleCollections.createList(tupleList.getArity());
		int i = -1;
		TupleCursor tupleCursor = tupleList.tupleCursor();
		while (tupleCursor.forward()) {
			++i;
			if (!isEmpty(i, axis)) {
				map.put(filteredTupleList.size(), i);
				filteredTupleList.addCurrent(tupleCursor);
			}
		}

      this.axes[ axis ] = new RolapAxis( filteredTupleList );
    }

    @Override
	protected Logger getLogger() {
      return LOGGER;
    }

    /**
     * Returns true if all cells at a given offset on a given axis are
     * empty. For example, in a 2x2x2 dataset, <code>isEmpty(1,0)</code>
     * returns true if cells <code>{(1,0,0), (1,0,1), (1,1,0),
     * (1,1,1)}</code> are all empty. As you can see, we hold the 0th
     * coordinate fixed at 1, and vary all other coordinates over all
     * possible values.
     */
    private boolean isEmpty( int offset, int fixedAxis ) {
      int axisCount = getAxes().length;
      pos[ fixedAxis ] = offset;
      return isEmptyRecurse( fixedAxis, axisCount - 1 );
    }

    private boolean isEmptyRecurse( int fixedAxis, int axis ) {
      if ( axis < 0 ) {
        RolapCell cell = (RolapCell) underlying.getCell( pos );
        return cell.isNull();
      } else if ( axis == fixedAxis ) {
        return isEmptyRecurse( fixedAxis, axis - 1 );
      } else {
        List<Position> positions = getAxes()[ axis ].getPositions();
        final int positionCount = positions.size();
        for ( int i = 0; i < positionCount; i++ ) {
          pos[ axis ] = i;
          if ( !isEmptyRecurse( fixedAxis, axis - 1 ) ) {
            return false;
          }
        }
        return true;
      }
    }

    // synchronized because we use 'pos'
    @Override
	public synchronized Cell getCell( int[] externalPos ) {
      try {
        System.arraycopy(
          externalPos, 0, this.pos, 0, externalPos.length );
        int offset = externalPos[ axis ];
        int mappedOffset = mapOffsetToUnderlying( offset );
        this.pos[ axis ] = mappedOffset;
        return underlying.getCell( this.pos );
      } catch ( NullPointerException npe ) {
        return underlying.getCell( externalPos );
      }
    }

    private int mapOffsetToUnderlying( int offset ) {
      return map.get( offset );
    }

    @Override
	public void close() {
      underlying.close();
    }

      @Override
      public RolapMember[] getCellMembers(int[] coordinates) {
          return underlying.getCellMembers(coordinates);
      }
  }


  /**
   * <p>Implementation of {@link Statement} for use when you don't have an
   * olap4j connection.</p>
   */
  private class InternalStatement extends org.eclipse.daanse.olap.impl.StatementImpl {
    private boolean closed = false;

      /**
       * Creates a StatementImpl.
       *
       * @param queryTimeout
       */
      protected InternalStatement(Connection connection) {
          super(connection);
      }

      @Override
	public void close() {
      if ( !closed ) {
        closed = true;
        context.removeStatement( this );
      }
    }

    @Override
	public RolapConnection getMondrianConnection() {
      return RolapConnection.this;
    }
  }

  /**
   * <p>A statement that can be used for all of the various internal
   * operations, such as resolving MDX identifiers, that require a
   * {@link Statement} and an {@link Execution}.
   *
   * <p>The statement needs to be reentrant because there are many such
   * operations; several of these operations might be active at one time. We
   * don't want to create a new statement for each, but just one internal
   * statement for each connection. The statement shouldn't have a unique
   * execution. For this reason, we don't use the inherited {execution}
   * field.</p>
   *
   * <p>But there is a drawback. If we can't find the unique execution, the
   * statement cannot be canceled or time out. If you want that behavior
   * from an internal statement, use the base class: create a new
   * {@link InternalStatement} for each operation.</p>
   */
  private class ReentrantInternalStatement extends InternalStatement {

      /**
       * Creates a StatementImpl.
       *
       * @param queryTimeout
       */
      protected ReentrantInternalStatement(Connection connection) {
          super(connection);
      }

      @Override
    public synchronized void start( Execution execution ) {
      // Unlike StatementImpl, there is not a unique execution. An
      // internal statement can execute several at the same time. So,
      // we don't set this.execution.
      execution.start();
    }

    @Override
    public synchronized void end( Execution execution ) {
      execution.end();
    }

    @Override
    public void close() {
      // do not close
    }
  }

  //TODO: Extract a statement between connection and Resuolt without the query
  @Override
  public org.eclipse.daanse.olap.api.Statement createStatement() {
    return new org.eclipse.daanse.olap.impl.StatementImpl(this);
  }
}
