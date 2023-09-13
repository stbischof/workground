/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2017 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.olap4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.query.component.DimensionExpression;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.Id;
import org.eclipse.daanse.olap.api.query.component.LevelExpression;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.api.query.component.MemberProperty;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.olap4j.Axis;
import org.olap4j.Cell;
import org.olap4j.OlapConnection;
import org.olap4j.OlapDatabaseMetaData;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.PreparedOlapStatement;
import org.olap4j.Scenario;
import org.olap4j.impl.AbstractNamedList;
import org.olap4j.impl.NamedListImpl;
import org.olap4j.impl.Olap4jUtil;
import org.olap4j.impl.UnmodifiableArrayList;
import org.olap4j.mdx.AxisNode;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.CubeNode;
import org.olap4j.mdx.DimensionNode;
import org.olap4j.mdx.HierarchyNode;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.mdx.LevelNode;
import org.olap4j.mdx.LiteralNode;
import org.olap4j.mdx.MemberNode;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.mdx.ParseTreeWriter;
import org.olap4j.mdx.PropertyValueNode;
import org.olap4j.mdx.SelectNode;
import org.olap4j.mdx.WithMemberNode;
import org.olap4j.mdx.parser.MdxParser;
import org.olap4j.mdx.parser.MdxParserFactory;
import org.olap4j.mdx.parser.MdxValidator;
import org.olap4j.mdx.parser.impl.DefaultMdxParserImpl;
import org.olap4j.metadata.Catalog;
import org.olap4j.metadata.Database;
import org.olap4j.metadata.Database.AuthenticationMode;
import org.olap4j.metadata.Database.ProviderType;
import org.olap4j.metadata.NamedList;
import org.olap4j.metadata.Schema;
import org.olap4j.type.BooleanType;
import org.olap4j.type.CubeType;
import org.olap4j.type.DecimalType;
import org.olap4j.type.DimensionType;
import org.olap4j.type.MemberType;
import org.olap4j.type.NullType;
import org.olap4j.type.NumericType;
import org.olap4j.type.SetType;
import org.olap4j.type.StringType;
import org.olap4j.type.SymbolType;
import org.olap4j.type.TupleType;
import org.olap4j.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.mdx.HierarchyExpressionImpl;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.Category;
import mondrian.olap.Exp;
import mondrian.olap.AbstractLiteralImpl;
import mondrian.olap.MondrianException;
import mondrian.olap.MondrianServer;
import mondrian.olap.QueryImpl;
import mondrian.olap.QueryCanceledException;
import mondrian.olap.QueryTimeoutException;
import mondrian.olap.ResourceLimitExceededException;
import mondrian.olap.RoleImpl;
import mondrian.olap.Util;
import mondrian.olap.fun.MondrianEvaluationException;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapMeasure;
import mondrian.rolap.RolapSchema;
import mondrian.rolap.ScenarioImpl;
import mondrian.util.Bug;
import mondrian.xmla.XmlaHandler;

/**
 * Implementation of {@link org.olap4j.OlapConnection}
 * for the Mondrian OLAP engine.
 *
 * <p>This class has sub-classes which implement JDBC 3.0 and JDBC 4.0 APIs;
 * it is instantiated using
 * {@link Factory#newConnection(MondrianOlap4jDriver, String, java.util.Properties)}.</p>
 *
 * <p>This class is public, to allow access to the
 * {@link #setRoleNames(java.util.List)} method before it is added to olap4j
 * version 2.0. <b>This may change without notice</b>. Code should not rely on
 * this class being public.</p>
 *
 * @author jhyde
 * @since May 23, 2007
 */
public abstract class MondrianOlap4jConnection implements OlapConnection {
    static {
        Bug.olap4jUpgrade(
            "Make this class package-protected when we upgrade to olap4j 2.0. "
            + "The setRoleNames method will then be available through the "
            + "olap4j API");
    }

    private static final Logger LOGGER =
        LoggerFactory.getLogger(MondrianOlap4jConnection.class);

    /**
     * Handler for errors.
     */
    final Helper helper = new Helper();

    /**
     * Underlying mondrian connection. Set on creation, cleared on close.
     * Developers, please keep this member private. Access it via
     * {@link #getMondrianConnection()} or {@link #getMondrianConnection2()},
     * and these will throw if the connection has been closed.
     */
    private RolapConnection mondrianConnection;

    private final AtomicBoolean isClosed =
        new AtomicBoolean(false);

    /**
     * Map from mondrian schema objects to olap4j schemas.
     *
     * <p>REVIEW: This assumes that a RolapSchema occurs at most once in a
     * catalog. It is possible for a schema to be mapped more than once, with
     * different names; the same RolapSchema object will be used.
     */
    final Map<org.eclipse.daanse.olap.api.element.Schema, MondrianOlap4jSchema> schemaMap =
        new HashMap<>();

    private final MondrianOlap4jDatabaseMetaData olap4jDatabaseMetaData;

    private static final String CONNECT_STRING_PREFIX = "jdbc:mondrian:";

    private static final String ENGINE_CONNECT_STRING_PREFIX =
        "jdbc:mondrian:engine:";

    final Factory factory;
    final MondrianOlap4jDriver driver;
    private String roleName;

    /** List of role names. Empty if role is the 'all' role. Value must always
     * be an unmodifiable list, because {@link #getRoleNames()} returns the
     * value directly. */
    private List<String> roleNames = Collections.emptyList();
    private boolean autoCommit;
    private boolean readOnly;
    boolean preferList;

    final MondrianServer mondrianServer;
    private final MondrianOlap4jSchema olap4jSchema;
    private final NamedList<MondrianOlap4jDatabase> olap4jDatabases;

    public MondrianOlap4jSchema getMondrianOlap4jSchema(){
        return this.olap4jSchema;
    }

    /**
     * Creates an Olap4j connection to Mondrian.
     *
     * <p>This method is intentionally package-protected. The public API
     * uses the traditional JDBC {@link java.sql.DriverManager}.
     * See {@link mondrian.olap4j.MondrianOlap4jDriver} for more details.
     *
     * @param factory Factory
     * @param driver Driver
     * @param url Connect-string URL
     * @param info Additional properties
     * @throws SQLException if there is an error
     */
    MondrianOlap4jConnection(
        Factory factory,
        MondrianOlap4jDriver driver,
        String url,
        Properties info, Context context)
        throws SQLException
    {
        // Required for the logic below to work.
        assert ENGINE_CONNECT_STRING_PREFIX.startsWith(CONNECT_STRING_PREFIX);

        this.factory = factory;
        this.driver = driver;
        String x;
        if (url.startsWith(ENGINE_CONNECT_STRING_PREFIX)) {
            x = url.substring(ENGINE_CONNECT_STRING_PREFIX.length());
        } else if (url.startsWith(CONNECT_STRING_PREFIX)) {
            x = url.substring(CONNECT_STRING_PREFIX.length());
        } else {
            // This is not a URL we can handle.
            // DriverManager should not have invoked us.
            throw new AssertionError(
                new StringBuilder("does not start with '").append(CONNECT_STRING_PREFIX).append("'").toString());
        }
        Util.PropertyList list = Util.parseConnectString(x);
        final Map<String, String> map = Util.toMap(info);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.put(entry.getKey(), entry.getValue());
        }

        this.mondrianConnection =
            (RolapConnection) mondrian.olap.DriverManager
                .getConnection(list, null, context);

        this.olap4jDatabaseMetaData =
            factory.newDatabaseMetaData(this, mondrianConnection);


        this.mondrianServer =
            MondrianServer.forConnection(mondrianConnection);
        final CatalogFinder catalogFinder =
            (CatalogFinder) mondrianServer;

        NamedList<MondrianOlap4jCatalog> olap4jCatalogs = new
            NamedListImpl<>();
        this.olap4jDatabases =
            new NamedListImpl<>();

        List<Map<String, Object>> dbpropsMaps =
            mondrianServer.getDatabases(mondrianConnection);
        if (dbpropsMaps.size() != 1) {
            throw new AssertionError();
        }
        Map<String, Object> dbpropsMap = dbpropsMaps.get(0);
        StringTokenizer st =
            new StringTokenizer(
                String.valueOf(dbpropsMap.get("ProviderType")),
                ",");
        List<ProviderType> pTypes =
            new ArrayList<>();
        while (st.hasMoreTokens()) {
            pTypes.add(ProviderType.valueOf(st.nextToken()));
        }
        st = new StringTokenizer(
            String.valueOf(dbpropsMap.get("AuthenticationMode")), ",");
        List<AuthenticationMode> aModes =
            new ArrayList<>();
        while (st.hasMoreTokens()) {
            aModes.add(AuthenticationMode.valueOf(st.nextToken()));
        }
        final MondrianOlap4jDatabase database =
            new MondrianOlap4jDatabase(
                this,
                olap4jCatalogs,
                String.valueOf(dbpropsMap.get("DataSourceName")),
                String.valueOf(dbpropsMap.get("DataSourceDescription")),
                String.valueOf(dbpropsMap.get("ProviderName")),
                String.valueOf(dbpropsMap.get("URL")),
                String.valueOf(dbpropsMap.get("DataSourceInfo")),
                pTypes,
                aModes);
        this.olap4jDatabases.add(database);

        for (String catalogName
            : catalogFinder.getCatalogNames(mondrianConnection))
        {
            final Map<String, RolapSchema> schemaMapInner;
            try {
              schemaMapInner =
                  catalogFinder.getRolapSchemas(
                      mondrianConnection,
                      catalogName);
            } catch (Exception e) {
                LOGGER.warn(
                    new StringBuilder("Can't get Rolap Schemas for catalog:")
                    .append(catalogName)
                    .append(". Skipping...").toString(),
                    e);
               continue;
            }
            olap4jCatalogs.add(
                new MondrianOlap4jCatalog(
                    olap4jDatabaseMetaData,
                    catalogName,
                    database,
                    schemaMapInner));
        }

        this.olap4jSchema = toOlap4j(mondrianConnection.getSchema());
    }

    static boolean acceptsURL(String url) {
        return url.startsWith(CONNECT_STRING_PREFIX);
    }

    @Override
	public OlapStatement createStatement() {
        final MondrianOlap4jStatement statement =
            factory.newStatement(this);
        mondrianServer.addStatement(statement);
        return statement;
    }

    @Override
	public ScenarioImpl createScenario() throws OlapException {
        return getMondrianConnection().createScenario();
    }

    @Override
	public void setScenario(Scenario scenario) throws OlapException {
        getMondrianConnection().setScenario(scenario);
    }

    @Override
	public Scenario getScenario() throws OlapException {
        return getMondrianConnection().getScenario();
    }

    @Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public CallableStatement prepareCall(String sql) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public String nativeSQL(String sql) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.autoCommit = autoCommit;
    }

    @Override
	public boolean getAutoCommit() throws SQLException {
        return autoCommit;
    }

    @Override
	public void commit() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void rollback() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void close() throws SQLException {
        if (!isClosed.get()) {
            mondrianConnection.close();
            isClosed.set(true);
        }
    }

    @Override
	public boolean isClosed() throws SQLException {
        return isClosed.get();
    }

    @Override
	public OlapDatabaseMetaData getMetaData() {
        return olap4jDatabaseMetaData;
    }

    @Override
	public void setReadOnly(boolean readOnly) throws SQLException {
        this.readOnly = readOnly;
    }

    @Override
	public boolean isReadOnly() throws SQLException {
        return readOnly;
    }

    @Override
	public void setSchema(String schemaName) throws OlapException {
        // no op.
    }

    @Override
	public String getSchema() throws OlapException {
        return olap4jSchema.getName();
    }

    @Override
	public Schema getOlapSchema() throws OlapException {
        return olap4jSchema;
    }

    @Override
	public NamedList<Schema> getOlapSchemas() throws OlapException {
        return getOlapCatalog().getSchemas();
    }

    @Override
	public void setCatalog(String catalogName) throws OlapException {
        // no op
    }

    @Override
	public String getCatalog() throws OlapException {
        return olap4jSchema.olap4jCatalog.getName();
    }

    @Override
	public Catalog getOlapCatalog() throws OlapException {
        return olap4jSchema.olap4jCatalog;
    }

    @Override
	public NamedList<Catalog> getOlapCatalogs() throws OlapException {
        return getOlapDatabase().getCatalogs();
    }

    @Override
	public void setDatabase(String databaseName) throws OlapException {
        // no op.
    }

    @Override
	public String getDatabase() throws OlapException {
        return getOlapDatabase().getName();
    }

    @Override
	public Database getOlapDatabase() throws OlapException {
        // It is assumed that Mondrian supports only a single
        // database.
        return this.olap4jDatabases.get(0);
    }

    @Override
	public NamedList<Database> getOlapDatabases() throws OlapException {
        return Olap4jUtil.cast(this.olap4jDatabases);
    }

    @Override
	public void setTransactionIsolation(int level) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public int getTransactionIsolation() throws SQLException {
        return TRANSACTION_NONE;
    }

    @Override
	public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void clearWarnings() throws SQLException {
    }

    @Override
	public Statement createStatement(
        int resultSetType, int resultSetConcurrency) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public PreparedStatement prepareStatement(
        String sql,
        int resultSetType,
        int resultSetConcurrency) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public CallableStatement prepareCall(
        String sql,
        int resultSetType,
        int resultSetConcurrency) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void setHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public Savepoint setSavepoint() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public Savepoint setSavepoint(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void rollback(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
	public Statement createStatement(
        int resultSetType,
        int resultSetConcurrency,
        int resultSetHoldability) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public PreparedStatement prepareStatement(
        String sql,
        int resultSetType,
        int resultSetConcurrency,
        int resultSetHoldability) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public CallableStatement prepareCall(
        String sql,
        int resultSetType,
        int resultSetConcurrency,
        int resultSetHoldability) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public PreparedStatement prepareStatement(
        String sql, int autoGeneratedKeys) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public PreparedStatement prepareStatement(
        String sql, int[] columnIndexes) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public PreparedStatement prepareStatement(
        String sql, String[] columnNames) throws SQLException
    {
        throw new UnsupportedOperationException();
    }

    // implement Wrapper

    @Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return iface.cast(this);
        } else if (iface.isInstance(mondrianConnection)) {
            return iface.cast(mondrianConnection);
        }
        if (iface == XmlaHandler.XmlaExtra.class) {
            return iface.cast(MondrianOlap4jExtra.INSTANCE);
        }
        throw helper.createException(new StringBuilder("does not implement '").append(iface).append("'").toString());
    }

    @Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this)
            || iface.isInstance(mondrianConnection);
    }

    // implement OlapConnection

    @Override
	public PreparedOlapStatement prepareOlapStatement(
        String mdx)
        throws OlapException
    {
        final MondrianOlap4jPreparedStatement preparedStatement =
            factory.newPreparedStatement(mdx, this);
        mondrianServer.addStatement(preparedStatement);
        return preparedStatement;
    }

    @Override
	public MdxParserFactory getParserFactory() {
        return new MdxParserFactory() {
            @Override
			public MdxParser createMdxParser(OlapConnection connection) {
                return new DefaultMdxParserImpl();
            }

            @Override
			public MdxValidator createMdxValidator(OlapConnection connection) {
                return new MondrianOlap4jMdxValidator(connection);
            }
        };
    }

    MondrianOlap4jCube toOlap4j(org.eclipse.daanse.olap.api.element.Cube cube) {
        MondrianOlap4jSchema schema = toOlap4j(cube.getSchema());
        return new MondrianOlap4jCube(cube, schema);
    }

    MondrianOlap4jDimension toOlap4j(org.eclipse.daanse.olap.api.element.Dimension dimension) {
        if (dimension == null) {
            return null;
        }
        return new MondrianOlap4jDimension(
            toOlap4j(dimension.getSchema()),
            dimension);
    }

    synchronized MondrianOlap4jSchema toOlap4j(
            org.eclipse.daanse.olap.api.element.Schema schema)
    {
        MondrianOlap4jSchema olap4jSchemaInner = schemaMap.get(schema);
        if (olap4jSchemaInner == null) {
            throw new MondrianOlap4jConnectionException("schema not registered: " + schema);
        }
        return olap4jSchemaInner;
    }

    Type toOlap4j(mondrian.olap.type.Type type) {
        if (type instanceof mondrian.olap.type.BooleanType) {
            return new BooleanType();
        } else if (type instanceof mondrian.olap.type.CubeType cubeType) {
            final org.eclipse.daanse.olap.api.element.Cube mondrianCube =
                cubeType.getCube();
            return new CubeType(toOlap4j(mondrianCube));
        } else if (type instanceof mondrian.olap.type.DecimalType decimalType) {
            return new DecimalType(
                decimalType.getPrecision(),
                decimalType.getScale());
        } else if (type instanceof mondrian.olap.type.DimensionType dimensionType) {
            return new DimensionType(
                toOlap4j(dimensionType.getDimension()));
        } else if (type instanceof mondrian.olap.type.HierarchyType) {
            return new BooleanType();
        } else if (type instanceof mondrian.olap.type.LevelType) {
            return new BooleanType();
        } else if (type instanceof mondrian.olap.type.MemberType memberType) {
            return new MemberType(
                toOlap4j(memberType.getDimension()),
                toOlap4j(memberType.getHierarchy()),
                toOlap4j(memberType.getLevel()),
                toOlap4j(memberType.getMember()));
        } else if (type instanceof mondrian.olap.type.NullType) {
            return new NullType();
        } else if (type instanceof mondrian.olap.type.NumericType) {
            return new NumericType();
        } else if (type instanceof mondrian.olap.type.SetType setType) {
            return new SetType(toOlap4j(setType.getElementType()));
        } else if (type instanceof mondrian.olap.type.StringType) {
            return new StringType();
        } else if (type instanceof mondrian.olap.type.TupleType tupleType) {
            final Type[] types = toOlap4j(tupleType.elementTypes);
            return new TupleType(types);
        } else if (type instanceof mondrian.olap.type.SymbolType) {
            return new SymbolType();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    MondrianOlap4jMember toOlap4j(org.eclipse.daanse.olap.api.element.Member member) {
        if (member == null) {
            return null;
        }
        if (member instanceof RolapMeasure measure) {
            return new MondrianOlap4jMeasure(
                toOlap4j(member.getDimension().getSchema()),
                measure);
        }
        return new MondrianOlap4jMember(
            toOlap4j(member.getDimension().getSchema()),
            member);
    }

    MondrianOlap4jLevel toOlap4j(org.eclipse.daanse.olap.api.element.Level level) {
        if (level == null) {
            return null;
        }
        return new MondrianOlap4jLevel(
            toOlap4j(level.getDimension().getSchema()),
            level);
    }

    MondrianOlap4jHierarchy toOlap4j(org.eclipse.daanse.olap.api.element.Hierarchy hierarchy) {
        if (hierarchy == null) {
            return null;
        }
        return new MondrianOlap4jHierarchy(
            toOlap4j(hierarchy.getDimension().getSchema()),
            hierarchy);
    }

    Type[] toOlap4j(mondrian.olap.type.Type[] mondrianTypes) {
        final Type[] types = new Type[mondrianTypes.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = toOlap4j(mondrianTypes[i]);
        }
        return types;
    }

    NamedList<MondrianOlap4jMember> toOlap4j(
        final List<Member> memberList)
    {
        return new AbstractNamedList<>() {
            @Override
			public String getName(Object olap4jMember) {
                return ((MondrianOlap4jMember)olap4jMember).getName();
            }

            @Override
			public MondrianOlap4jMember get(int index) {
                return toOlap4j(memberList.get(index));
            }

            @Override
			public int size() {
                return memberList.size();
            }
        };
    }

    MondrianOlap4jNamedSet toOlap4j(
        org.eclipse.daanse.olap.api.element.Cube cube,
        org.eclipse.daanse.olap.api.element.NamedSet namedSet)
    {
        if (namedSet == null) {
            return null;
        }
        return new MondrianOlap4jNamedSet(
            toOlap4j(cube),
            namedSet);
    }

    ParseTreeNode toOlap4j(Exp exp) {
        return new MondrianToOlap4jNodeConverter(this).toOlap4j(exp);
    }

    SelectNode toOlap4j(QueryImpl query) {
        return new MondrianToOlap4jNodeConverter(this).toOlap4j(query);
    }

    @Override
	public void setLocale(Locale locale) {
        mondrianConnection.setLocale(locale);
    }

    @Override
	public Locale getLocale() {
        return mondrianConnection.getLocale();
    }

    @Override
	public void setRoleName(String roleName) throws OlapException {
        if (roleName == null) {
            final RolapConnection connection1 = getMondrianConnection();
            final Role role = Util.createRootRole(connection1.getSchema());
            assert role != null;
            this.roleName = roleName;
            this.roleNames = Collections.emptyList();
            connection1.setRole(role);
        } else {
            setRoleNames(Collections.singletonList(roleName));
        }
    }

    /**
     * <p>Set the active role(s) in this connection based on a list of role
     * names.</p>
     *
     * <p>The list may be not be empty. Each role name must be not-null and the
     * name of a valid role for the current user.</p>
     *
     * <p>This method is not part of the olap4j-1.x API. It may be included
     * in olap4j-2.0. If you want to call this method on a
     * {@link OlapConnection}, use {@link #unwrap} to get the underlying
     * Mondrian connection.</p>
     *
     * @param roleNames List of role names
     *
     * @see #getRoleNames()
     */
    public void setRoleNames(List<String> roleNames) throws OlapException {
        final RolapConnection connection1 = getMondrianConnection();
        final List<Role> roleList = new ArrayList<>();
        for (String roleNameInner : roleNames) {
            if (roleNameInner == null) {
                throw new NullPointerException("null role name");
            }
            final Role role = connection1.getSchema().lookupRole(roleNameInner);
            if (role == null) {
                throw helper.createException(new StringBuilder("Unknown role '")
                    .append(roleNameInner).append("'").toString());
            }
            roleList.add(role);
        }

        // Remember the name of the role, because mondrian roles don't know
        // their own name.
        Role role;
        switch (roleList.size()) {
        case 0:
            throw helper.createException("Empty list of role names");
        case 1:
            role = roleList.get(0);
            this.roleName = roleNames.get(0);
            this.roleNames = Collections.singletonList(roleName);
            break;
        default:
            role = RoleImpl.union(roleList);
            this.roleNames =
                Collections.unmodifiableList(new ArrayList<>(roleNames));
            this.roleName = this.roleNames.toString();
            break;
        }
        connection1.setRole(role);
    }

    @Override
	public String getRoleName() {
        return roleName;
    }

    /**
     * Returns a list of the current role names.
     *
     * <p>This method is not part of the olap4j-1.x API. It may be included
     * in olap4j-2.0. If you want to call this method on a
     * {@link OlapConnection}, use {@link #unwrap} to get the underlying
     * Mondrian connection.</p>
     *
     * @return List of the current role names
     */
    public List<String> getRoleNames() {
        return roleNames;
    }

    @Override
	public List<String> getAvailableRoleNames() throws OlapException {
        return UnmodifiableArrayList.of(
            getMondrianConnection().getSchema().roleNames());
    }

    public void setPreferList(boolean preferList) {
        this.preferList = preferList;
    }

    /**
     * Cop-out version of {@link #getMondrianConnection()} that doesn't throw
     * a checked exception. For those situations where the olap4j API doesn't
     * declare 'throws OlapException', but we need an open connection anyway.
     * Use {@link #getMondrianConnection()} where possible.
     *
     * @return Mondrian connection
     * @throws RuntimeException if connection is closed
     */
    RolapConnection getMondrianConnection2() {
        try {
            return getMondrianConnection();
        } catch (OlapException e) {
            // Demote from checked to unchecked exception.
            throw new MondrianOlap4jConnectionException(e);
        }
    }

    public RolapConnection getMondrianConnection() throws OlapException {
        final RolapConnection connection1 = mondrianConnection;
        if (connection1 == null) {
            throw helper.createException("Connection is closed.");
        }
        return connection1;
    }

    // inner classes

    /**
     * Package-private helper class which encapsulates policies which are
     * common throughout the driver. These policies include exception handling
     * and factory methods.
     */
    static class Helper {
        OlapException createException(String msg) {
            return new OlapException(msg);
        }

        /**
         * Creates an exception in the context of a particular Cell.
         *
         * @param context Cell context for exception
         * @param msg Message
         * @return New exception
         */
        OlapException createException(Cell context, String msg) {
            OlapException exception = new OlapException(msg);
            exception.setContext(context);
            return exception;
        }

        /**
         * Creates an exception in the context of a particular Cell and with
         * a given cause.
         *
         * @param context Cell context for exception
         * @param msg Message
         * @param cause Causing exception
         * @return New exception
         */
        OlapException createException(
            Cell context, String msg, Throwable cause)
        {
            OlapException exception = createException(msg, cause);
            exception.setContext(context);
            return exception;
        }

        /**
         * Creates an exception with a given cause.
         *
         * @param msg Message
         * @param cause Causing exception
         * @return New exception
         */
        OlapException createException(
            String msg, Throwable cause)
        {
            String sqlState = deduceSqlState(cause);
            assert !mondrian.util.Bug.olap4jUpgrade(
                "use OlapException(String, String, Throwable) ctor");
            final OlapException e = new OlapException(msg, sqlState);
            e.initCause(cause);
            return e;
        }

        private String deduceSqlState(Throwable cause) {
            if (cause == null) {
                return null;
            }
            if (cause instanceof ResourceLimitExceededException) {
                return "ResourceLimitExceeded";
            }
            if (cause instanceof QueryTimeoutException) {
                return "QueryTimeout";
            }
            if (cause instanceof MondrianEvaluationException) {
                return "EvaluationException";
            }
            if (cause instanceof QueryCanceledException) {
                return "QueryCanceledException";
            }
            return null;
        }

        /**
         * Converts a SQLException to an OlapException. Casts the exception
         * if it is already an OlapException, wraps otherwise.
         *
         * <p>This method is typically used as an adapter for SQLException
         * instances coming from a base class, where derived interface declares
         * that it throws the more specific OlapException.
         *
         * @param e Exception
         * @return Exception as an OlapException
         */
        public OlapException toOlapException(SQLException e) {
            if (e instanceof OlapException olapException) {
                return olapException;
            } else {
                return new OlapException(null, e);
            }
        }
    }

    private static class MondrianOlap4jMdxValidator implements MdxValidator {
        private final MondrianOlap4jConnection connection;

        public MondrianOlap4jMdxValidator(OlapConnection connection) {
            this.connection = (MondrianOlap4jConnection) connection;
        }

        @Override
		public SelectNode validateSelect(SelectNode selectNode)
            throws OlapException
        {
            try {
                // A lot of mondrian's validation happens during parsing.
                // Therefore to do effective validation, we need to go back to
                // the MDX string. Someday we will reshape mondrian's
                // parse/validation process to fit the olap4j model better.
                StringWriter sw = new StringWriter();
                selectNode.unparse(new ParseTreeWriter(new PrintWriter(sw)));
                String mdx = sw.toString();
                QueryImpl query =
                    connection.mondrianConnection
                        .parseQuery(mdx);
                query.resolve();
                return connection.toOlap4j(query);
            } catch (MondrianException e) {
                throw connection.helper.createException("Validation error", e);
            }
        }
    }

    private static class MondrianToOlap4jNodeConverter {
        private final MondrianOlap4jConnection olap4jConnection;

        MondrianToOlap4jNodeConverter(
            MondrianOlap4jConnection olap4jConnection)
        {
            this.olap4jConnection = olap4jConnection;
        }

        public SelectNode toOlap4j(QueryImpl query) {
            List<IdentifierNode> list = Collections.emptyList();
            return new SelectNode(
                null,
                toOlap4j(query.getFormulas()),
                toOlap4j(query.getAxes()),
                new CubeNode(
                    null,
                    olap4jConnection.toOlap4j(query.getCube())),
                query.getSlicerAxis() == null
                    ? null
                    : toOlap4j(query.getSlicerAxis()),
                list);
        }

        private AxisNode toOlap4j(QueryAxis axis) {
            return new AxisNode(
                null,
                axis.isNonEmpty(),
                Axis.Factory.forOrdinal(
                    axis.getAxisOrdinal().logicalOrdinal()),
                toOlap4j(axis.getDimensionProperties()),
                toOlap4j(axis.getSet()));
        }

        private List<IdentifierNode> toOlap4j(Id[] dimensionProperties) {
            final List<IdentifierNode> list = new ArrayList<>();
            for (Id property : dimensionProperties) {
                list.add(toOlap4j(property));
            }
            return list;
        }

        private ParseTreeNode toOlap4j(Exp exp) {
            if (exp instanceof Id id) {
                return toOlap4j(id);
            }
            if (exp instanceof ResolvedFunCallImpl call) {
                return toOlap4j(call);
            }
            if (exp instanceof DimensionExpression dimensionExpr) {
                return new DimensionNode(
                    null,
                    olap4jConnection.toOlap4j(dimensionExpr.getDimension()));
            }
            if (exp instanceof HierarchyExpressionImpl hierarchyExpr) {
                return new HierarchyNode(
                    null,
                    olap4jConnection.toOlap4j(hierarchyExpr.getHierarchy()));
            }
            if (exp instanceof LevelExpression levelExpr) {
                return new LevelNode(
                    null,
                    olap4jConnection.toOlap4j(levelExpr.getLevel()));
            }
            if (exp instanceof MemberExpression memberExpr) {
                return new MemberNode(
                    null,
                    olap4jConnection.toOlap4j(memberExpr.getMember()));
            }
            if (exp instanceof AbstractLiteralImpl literal) {
                final Object value = literal.getValue();
                if (literal.getCategory() == Category.SYMBOL) {
                    return LiteralNode.createSymbol(
                        null, (String) literal.getValue());
                } else if (value instanceof Number number) {
                    BigDecimal bd = bigDecimalFor(number);
                    return LiteralNode.createNumeric(null, bd, false);
                } else if (value instanceof String string) {
                    return LiteralNode.createString(null, string);
                } else if (value == null) {
                    return LiteralNode.createNull(null);
                } else {
                    throw new MondrianOlap4jConnectionException("unknown literal " + literal);
                }
            }
            throw Util.needToImplement(exp.getClass());
        }

        /**
         * Converts a number to big decimal, non-lossy if possible.
         *
         * @param number Number
         * @return BigDecimal
         */
        private static BigDecimal bigDecimalFor(Number number) {
            if (number instanceof BigDecimal bd) {
                return bd;
            } else if (number instanceof BigInteger bi) {
                return new BigDecimal(bi);
            } else if (number instanceof Integer integer) {
                return new BigDecimal(integer);
            } else if (number instanceof Double d) {
                return BigDecimal.valueOf(d);
            } else if (number instanceof Float f) {
                return BigDecimal.valueOf(f);
            } else if (number instanceof Long l) {
                return new BigDecimal(l);
            } else if (number instanceof Short sh) {
                return new BigDecimal(sh);
            } else if (number instanceof Byte b) {
                return new BigDecimal(b);
            } else {
                return BigDecimal.valueOf(number.doubleValue());
            }
        }

        private ParseTreeNode toOlap4j(ResolvedFunCallImpl call) {
            final CallNode callNode = new CallNode(
                null,
                call.getFunName(),
                toOlap4j(call.getSyntax()),
                toOlap4j(Arrays.asList(call.getArgs())));
            if (call.getType() != null) {
                callNode.setType(olap4jConnection.toOlap4j(call.getType()));
            }
            return callNode;
        }

        private List<ParseTreeNode> toOlap4j(List<Exp> exprList) {
            final List<ParseTreeNode> result = new ArrayList<>();
            for (Exp expr : exprList) {
                result.add(toOlap4j(expr));
            }
            return result;
        }

        private org.olap4j.mdx.Syntax toOlap4j(mondrian.olap.Syntax syntax) {
            return org.olap4j.mdx.Syntax.valueOf(syntax.name());
        }

        private List<AxisNode> toOlap4j(QueryAxis[] axes) {
            final ArrayList<AxisNode> axisList = new ArrayList<>();
            for (QueryAxis axis : axes) {
                axisList.add(toOlap4j(axis));
            }
            return axisList;
        }

        private List<ParseTreeNode> toOlap4j(Formula[] formulas) {
            final List<ParseTreeNode> list = new ArrayList<>();
            for (Formula formula : formulas) {
                if (formula.isMember()) {
                    List<PropertyValueNode> memberPropertyList =
                        new ArrayList<>();
                    for (Object child : formula.getChildren()) {
                        if (child instanceof MemberProperty memberProperty) {
                            memberPropertyList.add(
                                new PropertyValueNode(
                                    null,
                                    memberProperty.getName(),
                                    toOlap4j(memberProperty.getExp())));
                        }
                    }
                    list.add(
                        new WithMemberNode(
                            null,
                            toOlap4j(formula.getIdentifier()),
                            toOlap4j(formula.getExpression()),
                            memberPropertyList));
                }
            }
            return list;
        }

        private static IdentifierNode toOlap4j(Id id) {
            List<IdentifierSegment> list = Util.toOlap4j(id.getSegments());
            return new IdentifierNode(
                list.toArray(
                    new IdentifierSegment[list.size()]));
        }
    }
}
