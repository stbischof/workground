/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2020 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.CacheControl;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.eclipse.daanse.olap.api.query.component.QueryPart;
import org.eclipse.daanse.olap.api.result.Cell;
import org.eclipse.daanse.olap.api.result.Position;
import org.eclipse.daanse.olap.api.result.Result;
import org.eigenbase.util.property.StringProperty;
import org.olap4j.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.calc.TupleCollections;
import mondrian.calc.TupleCursor;
import mondrian.calc.TupleList;
import mondrian.calc.impl.DelegatingTupleList;
import mondrian.olap.ConnectionBase;
import mondrian.olap.DriverManager;
import mondrian.olap.Exp;
import mondrian.olap.FunctionTable;
import mondrian.olap.MondrianProperties;
import mondrian.olap.MondrianServer;
import mondrian.olap.QueryImpl;
import mondrian.olap.QueryCanceledException;
import mondrian.olap.QueryTimeoutException;
import mondrian.olap.ResourceLimitExceededException;
import mondrian.olap.ResultBase;
import mondrian.olap.ResultLimitExceededException;
import mondrian.olap.RoleImpl;
import mondrian.olap.SchemaReader;
import mondrian.olap.Util;
import mondrian.parser.MdxParserValidator;
import mondrian.resource.MondrianResource;
import mondrian.server.Execution;
import mondrian.server.Locus;
import mondrian.server.Statement;
import mondrian.server.StatementImpl;
import mondrian.spi.DataSourceResolver;
import mondrian.spi.impl.JndiDataSourceResolver;
import mondrian.util.ClassResolver;
import mondrian.util.FilteredIterableList;
import mondrian.util.LockBox;
import mondrian.util.MemoryMonitor;
import mondrian.util.MemoryMonitorFactory;
import mondrian.util.Pair;

/**
 * A <code>RolapConnection</code> is a connection to a Mondrian OLAP Server.
 *
 * <p>Typically, you create a connection via
 * {@link DriverManager#getConnection(String, mondrian.spi.CatalogLocator)}.
 * {@link RolapConnectionProperties} describes allowable keywords.</p>
 *
 * @author jhyde
 * @see RolapSchema
 * @see DriverManager
 * @since 2 October, 2002
 */
public class RolapConnection extends ConnectionBase {
  private static final Logger LOGGER =
    LoggerFactory.getLogger( RolapConnection.class );
  private static final AtomicInteger ID_GENERATOR = new AtomicInteger();

  private final MondrianServer server;

  private final Util.PropertyList connectInfo;

  private Context context = null;
  private final String catalogUrl;
  private final RolapSchema schema;
  private SchemaReader schemaReader;
  protected Role role;
  private Locale locale = Locale.getDefault();
  private Scenario scenario;
  private boolean closed = false;

  private static DataSourceResolver dataSourceResolver;
  private final int id;
  private final Statement internalStatement;

  /**
   * Creates a connection.
   *
   * @param server      Server instance this connection belongs to
   * @param connectInfo Connection properties; keywords are described in
   *                    {@link RolapConnectionProperties}.
   * @param context  Context
   */
  public RolapConnection(
    MondrianServer server,
    Util.PropertyList connectInfo,
    Context context ) {
    this( server, connectInfo, null, context );
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
  RolapConnection(
    MondrianServer server,
    Util.PropertyList connectInfo,
    RolapSchema schema,
    Context context ) {
    super();
    assert server != null;
    this.server = server;
    this.context = context;
    this.id = ID_GENERATOR.getAndIncrement();

    assert connectInfo != null;
    String provider = connectInfo.get(
      RolapConnectionProperties.Provider.name(), "mondrian" );
    Util.assertTrue( provider.equalsIgnoreCase( "mondrian" ) );
    this.connectInfo = connectInfo;
    this.catalogUrl =
      connectInfo.get( RolapConnectionProperties.Catalog.name() );
    final String jdbcUser =
      connectInfo.get( RolapConnectionProperties.JdbcUser.name() );
    final String jdbcConnectString = getJdbcConnectionString( connectInfo );
    final String strDataSource =
      connectInfo.get( RolapConnectionProperties.DataSource.name() );
    Role roleInner = null;

    // Register this connection before we register its internal statement.
    server.addConnection( this );

    if ( schema == null ) {
      // If RolapSchema.Pool.get were to call this with schema == null,
      // we would loop.
      Statement bootstrapStatement = createInternalStatement( false );
      final Locus locus =
        new Locus(
          new Execution( bootstrapStatement, 0 ),
          null,
          "Initializing connection" );
      Locus.push( locus );
      try {
        if ( context == null ) {
          // If there is no external data source is passed in, we
          // expect the properties Jdbc, JdbcUser, DataSource to be
          // set, as they are used to generate the schema cache key.
          final String connectionKey =
            jdbcConnectString
              + getJdbcProperties( connectInfo ).toString();

          schema = RolapSchemaPool.instance().get(
            catalogUrl,
            connectionKey,
            jdbcUser,
            strDataSource,
            connectInfo );
        } else {
          schema = RolapSchemaPool.instance().get(
            catalogUrl,
            context,
            connectInfo );
        }
      } finally {
        Locus.pop( locus );
        bootstrapStatement.close();
      }
      internalStatement =
        schema.getInternalConnection().getInternalStatement();
      String roleNameList =
        connectInfo.get( RolapConnectionProperties.Role.name() );
      if ( roleNameList != null ) {
        List<String> roleNames = Util.parseCommaList( roleNameList );
        List<Role> roleList = new ArrayList<>();
        for ( String roleName : roleNames ) {
          final LockBox.Entry entry =
            server.getLockBox().get( roleName );
          Role role1;
          if ( entry != null ) {
            try {
              role1 = (Role) entry.getValue();
            } catch ( ClassCastException e ) {
              role1 = null;
            }
          } else {
            role1 = schema.lookupRole( roleName );
          }
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
      this.internalStatement = createInternalStatement( true );

      // We are creating an internal connection. Now is a great time to
      // make sure that the JDBC credentials are valid, for this
      // connection and for external connections built on top of this.
      java.sql.Statement statement = null;
      try(Connection conn = this.context.getDataSource().getConnection()) {
        Dialect dialect =context.getDialect();
          if ( dialect.getDialectName().equals("derby")) {
          // TODO replace that crutch
          // Derby requires a little extra prodding to do the
          // validation to detect an error.
          statement = conn.createStatement();
          statement.executeQuery( "select * from bogustable" );
        }
      } catch ( SQLException e ) {
        if ( e.getMessage().equals(
          "Table/View 'BOGUSTABLE' does not exist." ) ) {
          // Ignore. This exception comes from Derby when the
          // connection is valid. If the connection were invalid, we
          // would receive an error such as "Schema 'BOGUSUSER' does
          // not exist"
        } else {
          throw Util.newError(
            e,
            "Error while creating SQL connection: "  );
        }
      } finally {
        try {
          if ( statement != null ) {
            statement.close();
          }
        } catch ( SQLException e ) {
          // ignore
        }
      }
    }

    if ( roleInner == null ) {
      roleInner = schema.getDefaultRole();
    }

    // Set the locale.
    String localeString =
      connectInfo.get( RolapConnectionProperties.Locale.name() );
    if ( localeString != null ) {
      this.locale = Util.parseLocale( localeString );
      assert locale != null;
    }

    this.schema = schema;
    setRole( roleInner );
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      super.finalize();
      close();
    } catch ( Throwable t ) {
      LOGGER.info(
        MondrianResource.instance()
          .FinalizerErrorRolapConnection.baseMessage,
        t );
    }
  }

  /**
   * Returns the identifier of this connection. Unique within the lifetime of
   * this JVM.
   *
   * @return Identifier of this connection
   */
  public int getId() {
    return id;
  }

  @Override
protected Logger getLogger() {
    return LOGGER;
  }

  /**
   * Returns the instance of the {@link mondrian.spi.DataSourceResolver}
   * plugin.
   *
   * @return data source resolver
   */
  private static synchronized DataSourceResolver getDataSourceResolver() {
    if ( dataSourceResolver == null ) {
      final StringProperty property =
        MondrianProperties.instance().DataSourceResolverClass;
      final String className =
        property.get(
          JndiDataSourceResolver.class.getName() );
      try {
        dataSourceResolver =
          ClassResolver.INSTANCE.instantiateSafe( className );
      } catch ( ClassCastException e ) {
        throw Util.newInternal(
          e,
          new StringBuilder("Plugin class specified by property ")
              .append(property.getPath())
              .append(" must implement ")
              .append(DataSourceResolver.class.getName()).toString() );
      }
    }
    return dataSourceResolver;
  }

  /**
   * Appends "key=value" to a buffer, if value is not null.
   *
   * @param buf   Buffer
   * @param key   Key
   * @param value Value
   */
  private static void appendKeyValue(
    StringBuilder buf,
    String key,
    Object value ) {
    if ( value != null ) {
      if ( buf.length() > 0 ) {
        buf.append( "; " );
      }
      buf.append( key ).append( '=' ).append( value );
    }
  }

  /**
   * Creates a {@link Properties} object containing all of the JDBC
   * connection properties present in the
   * {@link mondrian.olap.Util.PropertyList connectInfo}.
   *
   * @param connectInfo Connection properties
   * @return The JDBC connection properties.
   */
  private static Properties getJdbcProperties( Util.PropertyList connectInfo ) {
    Properties jdbcProperties = new Properties();
    for ( Pair<String, String> entry : connectInfo ) {
      if ( entry.left.startsWith(
        RolapConnectionProperties.JdbcPropertyPrefix ) ) {
        jdbcProperties.put(
          entry.left.substring(
            RolapConnectionProperties.JdbcPropertyPrefix.length() ),
          entry.right );
      }
    }
    return jdbcProperties;
  }

  public Util.PropertyList getConnectInfo() {
    return connectInfo;
  }

  @Override
public void close() {
    if ( !closed ) {
      closed = true;
      server.removeConnection( this );
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
public String getConnectString() {
    final Util.PropertyList connectInfoClone = Util.PropertyList.newInstance(connectInfo);
    connectInfoClone.remove( RolapConnectionProperties.JdbcPassword.name() );
    return connectInfoClone.toString();
  }

  @Override
public String getCatalogName() {
    return catalogUrl;
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
public Object getProperty( String name ) {
    // Mask out the values of certain properties.
    if ( name.equals( RolapConnectionProperties.JdbcPassword.name() )
      || name.equals( RolapConnectionProperties.CatalogContent.name() ) ) {
      return "";
    }
    return connectInfo.get( name );
  }

  @Override
public CacheControl getCacheControl( PrintWriter pw ) {
    return getServer().getAggregationManager().getCacheControl( this, pw );
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
public Result execute( QueryImpl query ) {
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
  public Result execute( final Execution execution ) {
    return
      server.getResultShepherd()
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
    execution.setContextMap();
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
    MemoryMonitor mm = MemoryMonitorFactory.getMemoryMonitor();
    final long currId = execution.getId();
    try {
      mm.addListener( listener );
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

  public Scenario getScenario() {
    return scenario;
  }

  /**
   * Returns the server (mondrian instance) that this connection belongs to.
   * Usually there is only one server instance in a given JVM.
   *
   * @return Server instance; never null
   */
  public MondrianServer getServer() {
    return server;
  }

  @Override
public QueryPart parseStatement(String query ) {
    Statement statement = createInternalStatement( false );
    final Locus locus =
      new Locus(
        new Execution( statement, 0 ),
        "Parse/validate MDX statement",
        null );
    Locus.push( locus );
    try {
      QueryPart queryPart =
        parseStatement( statement, query, null, false );
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
public Exp parseExpression( String expr ) {
    boolean debug = false;
    if ( getLogger().isDebugEnabled() ) {
        String msg  = Util.NL + expr;
        getLogger().debug(msg);
    }
    final Statement statement = getInternalStatement();
    try {
      MdxParserValidator parser = createParser();
      final FunctionTable funTable = getSchema().getFunTable();
      return parser.parseExpression( statement, expr, debug, funTable );
    } catch ( Throwable exception ) {
      throw MondrianResource.instance().FailedToParseQuery.ex(
        expr,
        exception );
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

  private Statement createInternalStatement( boolean reentrant ) {
    final Statement statement =
      reentrant
        ? new ReentrantInternalStatement()
        : new InternalStatement();
    server.addStatement( statement );
    return statement;
  }

  /**
   * Implementation of {@link DataSource} which calls the good ol'
   * {@link java.sql.DriverManager}.
   *
   * <p>Overrides {@link #hashCode()} and {@link #equals(Object)} so that
   * {@link Dialect} objects can be cached more effectively.
   */
  private static class DriverManagerDataSource implements DataSource {
    private final String jdbcConnectString;
    private PrintWriter logWriter;
    private int loginTimeout;
    private Properties jdbcProperties;

    public DriverManagerDataSource(
      String jdbcConnectString,
      Properties properties ) {
      this.jdbcConnectString = jdbcConnectString;
      this.jdbcProperties = properties;
    }

    @Override
    public int hashCode() {
      int h = loginTimeout;
      h = Util.hash( h, jdbcConnectString );
      h = Util.hash( h, jdbcProperties );
      return h;
    }

    @Override
    public boolean equals( Object obj ) {
      if ( obj instanceof DriverManagerDataSource that ) {
        return this.loginTimeout == that.loginTimeout
          && this.jdbcConnectString.equals( that.jdbcConnectString )
          && this.jdbcProperties.equals( that.jdbcProperties );
      }
      return false;
    }

    @Override
	public Connection getConnection() throws SQLException {
      return new org.apache.commons.dbcp.DelegatingConnection(
        java.sql.DriverManager.getConnection(
          jdbcConnectString, jdbcProperties ) );
    }

    @Override
	public Connection getConnection( String username, String password )
      throws SQLException {
      if ( jdbcProperties == null ) {
        return java.sql.DriverManager.getConnection(
          jdbcConnectString, username, password );
      } else {
        Properties temp = (Properties) jdbcProperties.clone();
        temp.put( "user", username );
        temp.put( "password", password );
        return java.sql.DriverManager.getConnection(
          jdbcConnectString, temp );
      }
    }

    @Override
	public PrintWriter getLogWriter() throws SQLException {
      return logWriter;
    }

    @Override
	public void setLogWriter( PrintWriter out ) throws SQLException {
      logWriter = out;
    }

    @Override
	public void setLoginTimeout( int seconds ) throws SQLException {
      loginTimeout = seconds;
    }

    @Override
	public int getLoginTimeout() throws SQLException {
      return loginTimeout;
    }

    @Override
	public java.util.logging.Logger getParentLogger() {
      return java.util.logging.Logger.getLogger( "" );
    }

    @Override
	public <T> T unwrap( Class<T> iface ) throws SQLException {
      throw new SQLException( "not a wrapper" );
    }

    @Override
	public boolean isWrapperFor( Class<?> iface ) throws SQLException {
      return false;
    }
  }

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
  public ScenarioImpl createScenario() {
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
      if ( !tupleList.isEmpty()
        && tupleList.get( 0 ).get( 0 ).getDimension()
        .isHighCardinality() ) {
          String msg = MondrianResource.instance()
              .HighCardinalityInDimension.str(
                  tupleList.get( 0 ).get( 0 ).getDimension()
                      .getUniqueName() );
          LOGGER.warn(msg);
          filteredTupleList =
              new DelegatingTupleList(
                  tupleList.getArity(),
                  new FilteredIterableList<>(
                      tupleList,
                      new FilteredIterableList.Filter<List<Member>>() {
                          @Override
                          public boolean accept( final List<Member> p ) {
                  return p.get( 0 ) != null;
                }
                      }
                      )
              );
      } else {
          filteredTupleList =
              TupleCollections.createList( tupleList.getArity() );
          int i = -1;
          TupleCursor tupleCursor = tupleList.tupleCursor();
          while ( tupleCursor.forward() ) {
              ++i;
              if ( !isEmpty( i, axis ) ) {
                  map.put( filteredTupleList.size(), i );
                  filteredTupleList.addCurrent( tupleCursor );
              }
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
  }

  /**
   * Data source that delegates all methods to an underlying data source.
   */
  private abstract static class DelegatingDataSource implements DataSource {
    protected final DataSource dataSource;

    public DelegatingDataSource( DataSource dataSource ) {
      this.dataSource = dataSource;
    }

    @Override
	public Connection getConnection() throws SQLException {
      return dataSource.getConnection();
    }

    @Override
	public Connection getConnection(
      String username,
      String password )
      throws SQLException {
      return dataSource.getConnection( username, password );
    }

    @Override
	public PrintWriter getLogWriter() throws SQLException {
      return dataSource.getLogWriter();
    }

    @Override
	public void setLogWriter( PrintWriter out ) throws SQLException {
      dataSource.setLogWriter( out );
    }

    @Override
	public void setLoginTimeout( int seconds ) throws SQLException {
      dataSource.setLoginTimeout( seconds );
    }

    @Override
	public int getLoginTimeout() throws SQLException {
      return dataSource.getLoginTimeout();
    }

    // JDBC 4.0 support (JDK 1.6 and higher)
    @Override
	public <T> T unwrap( Class<T> iface ) throws SQLException {
      if ( Util.JDBC_VERSION >= 0x0400 ) {
        // Do
        //              return dataSource.unwrap(iface);
        // via reflection.
        try {
          Method method =
            DataSource.class.getMethod( "unwrap", Class.class );
          return iface.cast( method.invoke( dataSource, iface ) );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
          throw Util.newInternal( e, "While invoking unwrap" );
        }
      } else {
        if ( iface.isInstance( dataSource ) ) {
          return iface.cast( dataSource );
        } else {
          return null;
        }
      }
    }

    // JDBC 4.0 support (JDK 1.6 and higher)
    @Override
	public boolean isWrapperFor( Class<?> iface ) throws SQLException {
      if ( Util.JDBC_VERSION >= 0x0400 ) {
        // Do
        //              return dataSource.isWrapperFor(iface);
        // via reflection.
        try {
          Method method =
            DataSource.class.getMethod(
              "isWrapperFor", boolean.class );
          return (Boolean) method.invoke( dataSource, iface );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
          throw Util.newInternal( e, "While invoking isWrapperFor" );
        }
      } else {
        return iface.isInstance( dataSource );
      }
    }

    // JDBC 4.1 support (JDK 1.7 and higher)
    @Override
	public java.util.logging.Logger getParentLogger() {
      if ( Util.JDBC_VERSION >= 0x0401 ) {
        // Do
        // via reflection.
        try {
          Method method =
            DataSource.class.getMethod( "getParentLogger" );
          return (java.util.logging.Logger) method.invoke( dataSource );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
          throw Util.newInternal( e, "While invoking getParentLogger" );
        }
      } else {
        // Can't throw SQLFeatureNotSupportedException... it doesn't
        // exist before JDBC 4.1.
        throw new UnsupportedOperationException();
      }
    }
  }

  private static String getJdbcConnectionString( Util.PropertyList connectInfo ) {

    String jdbc = connectInfo.get( RolapConnectionProperties.Jdbc.name() );

    if (  jdbc==null||jdbc.length()==0 ) {
      return null;
    }
    String s=connectInfo.get( "databaseName" ) ;
    String database = (  s==null||s.length()==0 )
      ? "" : ";databaseName=" + connectInfo.get( "databaseName" );
    String integratedSecurity = Boolean.parseBoolean( connectInfo.get( "integratedSecurity" ) )
      ? ";integratedSecurity=true" : "";

    return jdbc + database + integratedSecurity;
  }


  /**
   * <p>Implementation of {@link Statement} for use when you don't have an
   * olap4j connection.</p>
   */
  private class InternalStatement extends StatementImpl {
    private boolean closed = false;

    @Override
	public void close() {
      if ( !closed ) {
        closed = true;
        server.removeStatement( this );
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
    throw new UnsupportedOperationException("TODO");
  }
}
