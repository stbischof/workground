/*
* Copyright (c) 2022 Contributors to the Eclipse Foundation.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   Copyright (C) 2011-2019 Hitachi Vantara
*   Copyright (C) 2020-2021 Sergei Semenkov
*   SmartCity Jena - initial
*   Stefan Bischof (bipolis.org) - initial
*
*/
package org.eclipse.daanse.olap.core;

import java.util.concurrent.TimeUnit;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%ctx.ocd.name", description = "%ctx.ocd.description", localization = "OSGI-INF/l10n/ctx", factoryPid =  BasicContext.PID)
public interface BasicContextConfig {

    @AttributeDefinition(name = "%name.name", description = "%name.description", required = false)
    default String name() {
        return null;
    }

    @AttributeDefinition(name = "%description.name", description = "%description.description", type = AttributeType.STRING)
    default String description() {
    	 return null;
    }

    //<p>Maximum number of simultaneous queries the system will allow.</p> <p>Oracle fails if you try to run more than the 'processes' parameter in init.ora, typically 150. The throughput of Oracle and other databases will probably reduce long before you get to their limit.</p>
    @AttributeDefinition(name = "%queryLimit.name", description = "%queryLimit.description", type = AttributeType.INTEGER)
    default Integer queryLimit() {
        return 40;
    }

    //Integer property that, if set to a value greater than zero, sets a hard limit on the number of cells that are batched together when building segments.
    /**
     * Integer property that, if set to a value greater than zero, sets a hard limit on the
     * number of cells that are batched together when building segments.
     */
    @AttributeDefinition(name = "%cellBatchSize.name", description = "%cellBatchSize.description", type = AttributeType.INTEGER)
    default Integer cellBatchSize() {
        return -1;
    }

    //Integer property that, if set to a value greater than zero, limits the maximum size of a result set.
    @AttributeDefinition(name = "%resultLimit.name", description = "%resultLimit.description", type = AttributeType.INTEGER)
    default Integer resultLimit() {
        return 0;
    }

    //Property that establishes the amount of chunks for querying cells involving high-cardinality dimensions. Should prime with {@link #ResultLimit mondrian.result.limit}.
    @AttributeDefinition(name = "%highCardChunkSize.name", description = "%highCardChunkSize.description", type = AttributeType.INTEGER)
    default Integer highCardChunkSize() {
        return 1;
    }



    /*
    <p>Property containing the JDBC URL of the FoodMart database. The default value is to connect to an ODBC data source called "MondrianFoodMart".</p>

    <p>To run the test suite, first load the FoodMart data set into the database of your choice. Then set the driver.classpath,
    mondrian.foodmart.jdbcURL and mondrian.jdbcDrivers properties, by
    un-commenting and modifying one of the sections below.
    Put the JDBC driver jar into mondrian/testlib.</p>

    <p>Here are example property settings for various databases.</p>

<h3>Derby: needs user and password</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:derby:demo/derby/foodmart<br/>
    mondrian.foodmart.jdbcUser=sa<br/>
    mondrian.foodmart.jdbcPassword=sa<br/>
    mondrian.jdbcDrivers=org.apache.derby.jdbc.EmbeddedDriver<br/>
    driver.classpath=testlib/derby.jar</code></blockquote>

<h3>FireBirdSQL</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:firebirdsql:localhost/3050:/mondrian/foodmart.gdb<br/>
    mondrian.jdbcDrivers=org.firebirdsql.jdbc.FBDriver<br/>
    driver.classpath=/jdbc/fb/firebirdsql-full.jar</code></blockquote>

    <h3>Greenplum (similar to Postgres)</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:postgresql://localhost/foodmart?user=gpadmin&amp;password=xxxx<br/>
    mondrian.foodmart.jdbcUser=foodmart<br/>
    mondrian.foodmart.jdbcPassword=foodmart<br/>
    mondrian.jdbcDrivers=org.postgresql.Driver<br/>
    driver.classpath=lib/postgresql-8.2-504.jdbc3.jar</code></blockquote>

    <h3>LucidDB (see <a href="http://docs.eigenbase.org/LucidDbOlap">instructions</a>)</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:luciddb:http://localhost<br/>
    mondrian.foodmart.jdbcUser=foodmart<br/>
    mondrian.jdbcDrivers=org.luciddb.jdbc.LucidDbClientDriver<br/>
    driver.classpath=/path/to/luciddb/plugin/LucidDbClient.jar</code></blockquote>

    <h3>Oracle (needs user and password)</h3>
<blockquote><code>
    oracle.home=G:/oracle/product/10.1.0/Db_1<br/>
    mondrian.foodmart.jdbcURL.oracle=jdbc:oracle:thin:@//<i>host</i>:<i>port</i>/<i>service_name</i><br/>
        mondrian.foodmart.jdbcURL=jdbc:oracle:thin:foodmart/foodmart@//stilton:1521/orcl<br/>
        mondrian.foodmart.jdbcURL=jdbc:oracle:oci8:foodmart/foodmart@orcl<br/>
    mondrian.foodmart.jdbcUser=FOODMART<br/>
    mondrian.foodmart.jdbcPassword=oracle<br/>
    mondrian.jdbcDrivers=oracle.jdbc.OracleDriver<br/>
    driver.classpath=/home/jhyde/open/mondrian/lib/ojdbc14.jar</code></blockquote>

    <h3>ODBC (Microsoft Access)</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:odbc:MondrianFoodMart<br/>
    mondrian.jdbcDrivers=sun.jdbc.odbc.JdbcOdbcDriver<br/>
    driver.classpath=</code></blockquote>

<h3> Hypersonic</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:hsqldb:demo/hsql/FoodMart<br/>
    mondrian.jdbcDrivers=org.hsqldb.jdbcDriver<br/>
    driver.classpath=xx</code></blockquote>

<h3> MySQL: can have user and password set in JDBC URL</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:mysql://localhost/foodmart?user=foodmart&amp;password=foodmart<br/>
    mondrian.foodmart.jdbcURL=jdbc:mysql://localhost/foodmart<br/>
    mondrian.foodmart.jdbcUser=foodmart<br/>
    mondrian.foodmart.jdbcPassword=foodmart<br/>
    mondrian.jdbcDrivers=com.mysql.jdbc.Driver<br/>
    driver.classpath=D:/mysql-connector-3.1.12</code></blockquote>

<h3> MariaDB: can have user and password set in JDBC URL</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:mariadb://localhost/foodmart?user=foodmart&amp;password=foodmart<br/>
    mondrian.foodmart.jdbcURL=jdbc:mariadb://localhost/foodmart<br/>
    mondrian.foodmart.jdbcUser=foodmart<br/>
    mondrian.foodmart.jdbcPassword=foodmart<br/>
    mondrian.jdbcDrivers=org.mariadb.jdbc.Driver<br/>
    driver.classpath=D:/mariadb-java-client-1.4.6.jar</code></blockquote>

<h3> NuoDB</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:com.nuodb://localhost/foodmart?schema=mondrian<br/>
    mondrian.foodmart.jdbcUser=foodmart<br/>
    mondrian.foodmart.jdbcPassword=foodmart<br/>
    mondrian.jdbcDrivers=com.nuodb.jdbc.Driver<br/>
    mondrian.foodmart.jdbcSchema=mondrian<br/>
    driver.classpath=/opt/nuodb/jar/nuodbjdbc.jar</code></blockquote>

<h3>Infobright</h3>
    <p>As MySQL. (Infobright uses a MySQL driver, version 5.1 and later.)</p>

<h3>Ingres</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:ingres://192.168.200.129:II7/MondrianFoodMart;LOOP=on;AUTO=multi;UID=ingres;PWD=sergni<br/>
    mondrian.jdbcDrivers=com.ingres.jdbc.IngresDriver<br/>
    driver.classpath=c:/ingres2006/ingres/lib/iijdbc.jar</code></blockquote>

<h3>Postgres: needs user and password</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:postgresql://localhost/FM3<br/>
    mondrian.foodmart.jdbcUser=postgres<br/>
    mondrian.foodmart.jdbcPassword=pgAdmin<br/>
    mondrian.jdbcDrivers=org.postgresql.Driver</code></blockquote>

<h3>Neoview</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:hpt4jdbc://localhost:18650/:schema=PENTAHO;serverDataSource=PENTAHO_DataSource<br/>
    mondrian.foodmart.jdbcUser=<i>user</i><br/>
    mondrian.foodmart.jdbcPassword=<i>password</i><br/>
    mondrian.jdbcDrivers=com.hp.t4jdbc.HPT4Driver<br/>
    driver.classpath=/some/path/hpt4jdbc.jar</code></blockquote>

<h3>Netezza: mimics Postgres</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:netezza://127.0.1.10/foodmart<br/>
    mondrian.foodmart.jdbcUser=<i>user</i><br/>
    mondrian.foodmart.jdbcPassword=<i>password</i><br/>
    mondrian.jdbcDrivers=org.netezza.Driver<br/>
    driver.classpath=/some/path/nzjdbc.jar</code></blockquote>

<h3>Sybase</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:jtds:sybase://xxx.xxx.xxx.xxx:<i>port</i>/<i>dbName</i><br/>
    mondrian.foodmart.jdbcUser=<i>user</i><br/>
    mondrian.foodmart.jdbcPassword=<i>password</i><br/>
    mondrian.jdbcDrivers=net.sourceforge.jtds.jdbc.Driver<br/>
    driver.classpath=/some/path/jtds-1.2.jar</code></blockquote>

<h3>Teradata</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:teradata://DatabaseServerName/DATABASE=FoodMart<br/>
    mondrian.foodmart.jdbcUser=<i>user</i><br/>
    mondrian.foodmart.jdbcPassword=<i>password</i><br/>
    mondrian.jdbcDrivers=com.ncr.teradata.TeraDriver<br/>
    driver.classpath=/some/path/terajdbc/classes/terajdbc4.jar</code></blockquote>

<h3>Vertica</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:vertica://xxx.xxx.xxx.xxx:<i>port</i>/<i>dbName</i><br/>
    mondrian.foodmart.jdbcUser=<i>user</i><br/>
    mondrian.foodmart.jdbcPassword=<i>password</i><br/>
    mondrian.jdbcDrivers=com.vertica.Driver<br/>
    driver.classpath=/some/path/vertica.jar</code></blockquote>

<h3>Vectorwise</h3>
<blockquote><code>
    mondrian.foodmart.jdbcURL=jdbc:ingres://xxx.xxx.xxx.xxx<i>port</i>/<i>dbName</i><br/>
    mondrian.foodmart.jdbcUser=<i>user</i><br/>
    mondrian.foodmart.jdbcPassword=<i>password</i><br/>
    mondrian.jdbcDrivers=com.ingres.jdbc.IngresDriver <br/>
    driver.classpath=/some/path/iijdbc.jar</code></blockquote>
     */





    //Property which turns on or off the in-memory rollup of segment data. Defaults to true.
    @AttributeDefinition(name = "%enableInMemoryRollup.name", description = "%enableInMemoryRollup.description", type = AttributeType.BOOLEAN)
    default Boolean enableInMemoryRollup() {
        return true;
    }

    //Property which defines which SegmentCache implementation to use. Specify the value as a fully qualified class name, such as <code>org.example.SegmentCacheImpl</code> where SegmentCacheImpl is an implementation of {@link mondrian.spi.SegmentCache}.
    @AttributeDefinition(name = "%segmentCache.name", description = "%segmentCache.description", type = AttributeType.STRING)
    default String segmentCache() {
        return null;
    }

    /**
     * <p>Property that, with {@link #SparseSegmentDensityThreshold}, determines
     * whether to choose a sparse or dense representation when storing
     * collections of cell values in memory.</p>
     *
     * <p>When storing collections of cell values, Mondrian has to choose
     * between a sparse and a dense representation, based upon the
     * <code>possible</code> and <code>actual</code> number of values.
     * The <code>density</code> is <code>actual / possible</code>.</p>
     *
     * <p>We use a sparse representation if
     * <code>(possible -
     * {@link #SparseSegmentCountThreshold countThreshold}) *
     * {@link #SparseSegmentDensityThreshold densityThreshold} &gt;
     * actual</code></p>
     *
     * <p>For example, at the default values
     * ({@link #SparseSegmentCountThreshold countThreshold} = 1000,
     * {@link #SparseSegmentDensityThreshold} = 0.5),
     * we use a dense representation for</p>
     *
     * <ul>
     * <li>(1000 possible, 0 actual), or</li>
     * <li>(2000 possible, 500 actual), or</li>
     * <li>(3000 possible, 1000 actual).</li>
     * </ul>
     *
     * <p>Any fewer actual values, or any more
     * possible values, and Mondrian will use a sparse representation.</p>
     */    @AttributeDefinition(name = "%sparseSegmentCountThreshold.name", description = "%sparseSegmentCountThreshold.description", type = AttributeType.INTEGER)
    default Integer sparseSegmentCountThreshold() {
        return 1000;
    }

    //Property that, with {@link #SparseSegmentCountThreshold}, determines whether to choose a sparse or dense representation when storing collections of cell values in memory.
    @AttributeDefinition(name = "%sparseSegmentDensityThreshold.name", description = "%sparseSegmentDensityThreshold.description", type = AttributeType.DOUBLE)
    default Double sparseSegmentDensityThreshold() {
        return 0.5;
    }

    /*
        Property that defines
    a pattern for which test XML files to run.  Pattern has to
    match a file name of the form:
    <code>query<i>whatever</i>.xml</code> in the directory.

<p>Example:</p>

<blockquote><code>
    mondrian.test.QueryFilePattern=queryTest_fec[A-Za-z0-9_]*.xml
        </code></blockquote>
     */
    @AttributeDefinition(name = "%queryFilePattern.name", description = "%queryFilePattern.description", type = AttributeType.STRING)
    default String queryFilePattern() {
        return null;
    }

    //Property defining where the test XML files are.
    @AttributeDefinition(name = "%queryFileDirectory.name", description = "%queryFileDirectory.description", type = AttributeType.STRING)
    default String queryFileDirectory() {
        return null;
    }

    //Not documented.
    @AttributeDefinition(name = "%iterations.name", description = "%iterations.description", type = AttributeType.INTEGER)
    default Integer iterations() {
        return 1;
    }

    //Not documented.
    @AttributeDefinition(name = "%vUsers.name", description = "%vUsers.description", type = AttributeType.INTEGER)
    default Integer vUsers() {
        return 1;
    }

    //Property that returns the time limit for the test run in seconds. If the test is running after that time, it is terminated.
    @AttributeDefinition(name = "%timeLimit.name", description = "%timeLimit.description", type = AttributeType.INTEGER)
    default Integer timeLimit() {
        return 0;
    }

    //Property that indicates whether this is a warmup test.
    @AttributeDefinition(name = "%warmup.name", description = "%warmup.description", type = AttributeType.BOOLEAN)
    default Boolean warmup() {
        return false;
    }

    //Property that contains the URL of the catalog to be used by {@link mondrian.tui.CmdRunner} and XML/A Test.
    @AttributeDefinition(name = "%catalogURL.name", description = "%catalogURL.description", type = AttributeType.STRING)
    default String catalogURL() {
        return null;
    }

    //<p>Property that controls whether warning messages should be printed if a SQL comparison test does not contain expected SQL statements for the specified dialect. The tests are skipped if no expected SQL statements are found for the current dialect.</p> <p>Possible values are the following:</p> NONE: no warning (default); ANY: any dialect; ACCESS; DERBY; LUCIDDB; MYSQL ... and any Dialect enum in SqlPattern.Dialect <p>Specific tests can overwrite the default setting. The priority is: Settings besides "ANY" in mondrian.properties file; Any setting in the test; ANY</p>
    @AttributeDefinition(name = "%warnIfNoPatternForDialect.name", description = "%warnIfNoPatternForDialect.description", type = AttributeType.STRING)
    default String warnIfNoPatternForDialect() {
        return "NONE";
    }

    //<p>Boolean property that controls whether Mondrian uses aggregate tables.</p> <p>If true, then Mondrian uses aggregate tables. This property is queried prior to each aggregate query so that changing the value of this property dynamically (not just at startup) is meaningful.</p> <p>Aggregates can be read from the database using the {@link #ReadAggregates} property but will not be used unless this property is set to true.</p>
    @AttributeDefinition(name = "%useAggregates.name", description = "%useAggregates.description", type = AttributeType.BOOLEAN)
    default Boolean useAggregates() {
        return false;
    }

    //<p>Boolean property that determines whether Mondrian should read aggregate tables.</p> <p>If set to true, then Mondrian scans the database for aggregate tables. Unless mondrian.rolap.aggregates.Use is set to true, the aggregates found will not be used.</p>
    @AttributeDefinition(name = "%readAggregates.name", description = "%readAggregates.description", type = AttributeType.BOOLEAN)
    default Boolean readAggregates() {
        return false;
    }

    //<p>Boolean property that controls whether aggregate tables are ordered by their volume or row count.</p> <p>If true, Mondrian uses the aggregate table with the smallest volume (number of rows multiplied by number of columns); if false, Mondrian uses the aggregate table with the fewest rows.</p>
    @AttributeDefinition(name = "%chooseAggregateByVolume.name", description = "%chooseAggregateByVolume.description", type = AttributeType.BOOLEAN)
    default Boolean chooseAggregateByVolume() {
        return false;
    }

    //<p>String property containing the name of the file which defines the rules for recognizing an aggregate table. Can be either a resource in the Mondrian jar or a URL.</p> <p>The default value is "/DefaultRules.xml", which is in the mondrian.rolap.aggmatcher package in Mondrian.jar.</p> <p>Normally, this property is not set by a user.</p>
    @AttributeDefinition(name = "%aggregateRules.name", description = "%aggregateRules.description", type = AttributeType.STRING)
    default String aggregateRules() {
        return "/DefaultRules.xml";
    }

    @AttributeDefinition(name = "%aggregateRuleTag.name", description = "%aggregateRuleTag.description", type = AttributeType.STRING)
    default String aggregateRuleTag() {
        return "default";
    }

    @AttributeDefinition(name = "%generateAggregateSql.name", description = "%generateAggregateSql.description", type = AttributeType.BOOLEAN)
    default Boolean generateAggregateSql() { return false; }

    @AttributeDefinition(name = "%disableCaching.name", description = "%disableCaching.description", type = AttributeType.BOOLEAN)
    default Boolean disableCaching() { return false; }

    @AttributeDefinition(name = "%disableLocalSegmentCache.name", description = "%disableLocalSegmentCache.description", type = AttributeType.BOOLEAN)
    default Boolean disableLocalSegmentCache() { return false; }

    @AttributeDefinition(name = "%enableTriggers.name", description = "%enableTriggers.description", type = AttributeType.BOOLEAN)
    default Boolean enableTriggers() { return true; }

    @AttributeDefinition(name = "%generateFormattedSql.name", description = "%generateFormattedSql.description", type = AttributeType.BOOLEAN)
    default Boolean generateFormattedSql() { return false; }

    @AttributeDefinition(name = "%enableNonEmptyOnAllAxis.name", description = "%enableNonEmptyOnAllAxis.description", type = AttributeType.BOOLEAN)
    default Boolean enableNonEmptyOnAllAxis() { return false; }

    @AttributeDefinition(name = "%expandNonNative.name", description = "%expandNonNative.description", type = AttributeType.BOOLEAN)
    default Boolean expandNonNative() { return false; }

    @AttributeDefinition(name = "%compareSiblingsByOrderKey.name", description = "%compareSiblingsByOrderKey.description", type = AttributeType.BOOLEAN)
    default Boolean compareSiblingsByOrderKey() { return false; }

    @AttributeDefinition(name = "%enableExpCache.name", description = "%enableExpCache.description", type = AttributeType.BOOLEAN)
    default Boolean enableExpCache() { return true; }

    @AttributeDefinition(name = "%testExpDependencies.name", description = "%testExpDependencies.description", type = AttributeType.INTEGER)
    default Integer testExpDependencies() { return 0; }

    @AttributeDefinition(name = "%testSeed.name", description = "%testSeed.description", type = AttributeType.INTEGER)
    default Integer testSeed() { return 1234; }

    @AttributeDefinition(name = "%localePropFile.name", description = "%localePropFile.description", type = AttributeType.INTEGER)
    default String localePropFile() { return null; }

    //If enabled some NON EMPTY CrossJoin will be computed in SQL.
    @AttributeDefinition(name = "%enableNativeCrossJoin.name", description = "%enableNativeCrossJoin.description", type = AttributeType.BOOLEAN)
    default Boolean enableNativeCrossJoin() { return true; }

    //If enabled some TopCount will be computed in SQL.
    @AttributeDefinition(name = "%enableNativeTopCount.name", description = "%enableNativeTopCount.description", type = AttributeType.BOOLEAN)
    default Boolean enableNativeTopCount() { return true; }

    //If enabled some Filter() will be computed in SQL.
    @AttributeDefinition(name = "%enableNativeFilter.name", description = "%enableNativeFilter.description", type = AttributeType.BOOLEAN)
    default Boolean enableNativeFilter() { return true; }

    //If enabled some NON EMPTY set operations like member.children, level.members and member descendants will be computed in SQL.
    @AttributeDefinition(name = "%enableNativeNonEmpty.name", description = "%enableNativeNonEmpty.description", type = AttributeType.BOOLEAN)
    default Boolean enableNativeNonEmpty() { return true; }

    //Alerting action to take in case native evaluation of a function is enabled but not supported for that function's usage in a particular query.  (No alert is ever raised in cases where native evaluation would definitely have been wasted effort.) Recognized actions: OFF: do nothing (default action, also used if unrecognized action is specified) WARN: log a warning to RolapUtil logger ERROR: throw an instance of NativeEvaluationUnsupportedException
    @AttributeDefinition(name = "%alertNativeEvaluationUnsupported.name", description = "%alertNativeEvaluationUnsupported.description", type = AttributeType.STRING)
    default String alertNativeEvaluationUnsupported() { return "OFF"; }

    //If disabled, Mondrian will throw an exception if someone attempts to perform a drillthrough of any kind.
    @AttributeDefinition(name = "%enableDrillThrough.name", description = "%enableDrillThrough.description", type = AttributeType.BOOLEAN)
    default Boolean enableDrillThrough() { return true; }

    //If enabled, first row in the result of an XML/A drill-through request will be filled with the total count of rows in underlying database.
    @AttributeDefinition(name = "%enableTotalCount.name", description = "%enableTotalCount.description", type = AttributeType.BOOLEAN)
    default Boolean enableTotalCount() { return true; }

    //Boolean property that controls whether the MDX parser resolves uses case-sensitive matching when looking up identifiers. The default is false.
    @AttributeDefinition(name = "%caseSensitive.name", description = "%caseSensitive.description", type = AttributeType.BOOLEAN)
    default Boolean caseSensitive() { return false; }

    //Property that defines limit on the number of rows returned by XML/A drill through request.
    @AttributeDefinition(name = "%maxRows.name", description = "%maxRows.description", type = AttributeType.INTEGER)
    default Integer maxRows() { return 1000; }

    //Max number of constraints in a single 'IN' SQL clause. This value may be variant among database products and their runtime settings. Oracle, for example, gives the error ORA-01795: maximum number of expressions in a list is 1000. Recommended values: Oracle: 1,000, DB2: 2,500, Other: 10,000
    @AttributeDefinition(name = "%maxConstraints.name", description = "%maxConstraints.description", type = AttributeType.INTEGER)
    default Integer maxConstraints() { return 1000; }

    //Boolean property that determines whether Mondrian optimizes predicates. If true, Mondrian may retrieve a little more data than specified in MDX query and cache it for future use.  For example, if you ask for data on 48 states of the United States for 3 quarters of 2011, Mondrian will round out to all 50 states and all 4 quarters.  If false, Mondrian still optimizes queries that involve all members of a dimension.
    @AttributeDefinition(name = "%optimizePredicates.name", description = "%optimizePredicates.description", type = AttributeType.BOOLEAN)
    default Boolean optimizePredicates() { return true; }

    //Integer property that defines the maximum number of passes allowable while evaluating an MDX expression. If evaluation exceeds this depth (for example, while evaluating a very complex calculated member), Mondrian will throw an error.
    @AttributeDefinition(name = "%maxEvalDepth.name", description = "%maxEvalDepth.description", type = AttributeType.INTEGER)
    default Integer maxEvalDepth() { return 10; }

    //Property that defines the JdbcSchema factory class which determines the list of tables and columns of a specific datasource. @see mondrian.rolap.aggmatcher.JdbcSchema
    @AttributeDefinition(name = "%jdbcFactoryClass.name", description = "%jdbcFactoryClass.description", type = AttributeType.STRING)
    default String jdbcFactoryClass() { return null; }

    //Property that defines the name of the plugin class that resolves data source names to javax.sql.DataSource objects. The class must implement the mondrian.spi.DataSourceResolver interface. If not specified, the default implementation uses JNDI to perform resolution. Example: mondrian.spi.dataSourceResolverClass=mondrian.spi.impl.JndiDataSourceResolver
    @AttributeDefinition(name = "%dataSourceResolverClass.name", description = "%dataSourceResolverClass.description", type = AttributeType.STRING)
    default String dataSourceResolverClass() { return null; }

    //Property that defines the timeout value (in seconds) for queries. A value of 0 (the default) indicates no timeout.
    @AttributeDefinition(name = "%queryTimeout.name", description = "%queryTimeout.description", type = AttributeType.INTEGER)
    default Integer queryTimeout() { return 20; }

    //This controls query timeouts and cancellation, so a small value (a few milliseconds) is best. Setting this to a value higher than mondrian.rolap.queryTimeout will result the timeout not being enforced as expected. Default value is 1000ms. Default time unit is ms.
    @AttributeDefinition(name = "%rolapConnectionShepherdThreadPollingInterval.name", description = "%rolapConnectionShepherdThreadPollingInterval.description", type = AttributeType.LONG)
    default Long rolapConnectionShepherdThreadPollingInterval() { return 1000L; }

    @AttributeDefinition(name = "%rolapConnectionShepherdThreadPollingIntervalUnit.name", description = "%rolapConnectionShepherdThreadPollingIntervalUnit.description")
    default TimeUnit rolapConnectionShepherdThreadPollingIntervalUnit() { return TimeUnit.MILLISECONDS; }

    //Maximum number of MDX query threads per Mondrian server instance. Defaults to 20.
    @AttributeDefinition(name = "%rolapConnectionShepherdNbThreads.name", description = "%rolapConnectionShepherdNbThreads.description", type = AttributeType.INTEGER)
    default Integer rolapConnectionShepherdNbThreads() { return 20; }

    //Maximum number of threads per Mondrian server instance that are used to run SQL queries when populating segments. Defaults to 100.
    @AttributeDefinition(name = "%segmentCacheManagerNumberSqlThreads.name", description = "%segmentCacheManagerNumberSqlThreads.description", type = AttributeType.INTEGER)
    default Integer segmentCacheManagerNumberSqlThreads() { return 100; }

    //Maximum number of threads per Mondrian server instance that are used to run perform operations on the external caches. Defaults to 100.
    @AttributeDefinition(name = "%segmentCacheManagerNumberCacheThreads.name", description = "%segmentCacheManagerNumberCacheThreads.description", type = AttributeType.INTEGER)
    default Integer segmentCacheManagerNumberCacheThreads() { return 100; }

    //Property that defines whether non-existent member errors should be ignored during schema load. If so, the non-existent member is treated as a null member.
    @AttributeDefinition(name = "%ignoreInvalidMembers.name", description = "%ignoreInvalidMembers.description", type = AttributeType.BOOLEAN)
    default Boolean ignoreInvalidMembers() { return false; }

    //Property that defines whether non-existent member errors should be ignored during query validation. If so, the non-existent member is treated as a null member.
    @AttributeDefinition(name = "%ignoreInvalidMembersDuringQuery.name", description = "%ignoreInvalidMembersDuringQuery.description", type = AttributeType.BOOLEAN)
    default Boolean ignoreInvalidMembersDuringQuery() { return false; }

    //Property that determines how a null member value is represented in the result output. AS 2000 shows this as empty value, AS 2005 shows this as (null) value
    @AttributeDefinition(name = "%nullMemberRepresentation.name", description = "%nullMemberRepresentation.description", type = AttributeType.STRING)
    default String nullMemberRepresentation() { return "#null"; }

    //Integer property indicating the maximum number of iterations allowed when iterating over members to compute aggregates.  A value of 0 (the default) indicates no limit.
    @AttributeDefinition(name = "%iterationLimit.name", description = "%iterationLimit.description", type = AttributeType.STRING)
    default Integer iterationLimit() { return 0; }

    //Positive integer property that determines loop iterations number between checks for whether the current mdx query has been cancelled or timeout was exceeded. Setting the interval too small may result in a performance degradation when reading large result sets; setting it too high can cause a big delay between the query being marked as cancelled or timeout was exceeded and system resources associated to it being released.
    @AttributeDefinition(name = "%checkCancelOrTimeoutInterval.name", description = "%checkCancelOrTimeoutInterval.description", type = AttributeType.INTEGER)
    default Integer checkCancelOrTimeoutInterval() { return 1000; }

    //Property that defines whether the <code>MemoryMonitor</code> should be enabled. By default it is disabled; memory monitor is not available before Java version 1.5.
    @AttributeDefinition(name = "%memoryMonitor.name", description = "%memoryMonitor.description", type = AttributeType.BOOLEAN)
    default Boolean memoryMonitor() { return false; }

    //Property that defines the default MemoryMonitor percentage threshold. If enabled, when Java's memory monitor detects that post-garbage collection is above this value, notifications are generated.
    @AttributeDefinition(name = "%memoryMonitorThreshold.name", description = "%memoryMonitorThreshold.description", type = AttributeType.BOOLEAN)
    default Integer memoryMonitorThreshold() { return 90; }

    //Property that defines the name of the class used to compile scalar expressions. If the value is non-null, it is used by the ExpCompiler.Factory to create the implementation. To test that for all test MDX queries that all functions can handle requests for ITERABLE, LIST and MUTABLE_LIST evaluation results, use the following: mondrian.calc.ExpCompiler.class=mondrian.olap.fun.ResultStyleCompiler
    @AttributeDefinition(name = "%expCompilerClass.name", description = "%expCompilerClass.description", type = AttributeType.STRING)
    default String expCompilerClass() { return null; }

    //Property that defines the name of the factory class used to create maps of member properties to their respective values. If the value is non-null, it is used by the PropertyValueFactory to create the implementation.  If unset, mondrian.rolap.RolapMemberBase.DefaultPropertyValueMapFactory will be used.
    @AttributeDefinition(name = "%propertyValueMapFactoryClass.name", description = "%propertyValueMapFactoryClass.description", type = AttributeType.STRING)
    default String propertyValueMapFactoryClass() { return null; }

    //Property that defines when to apply the crossjoin optimization algorithm. If a crossjoin input list's size is larger than this property's value and the axis has the "NON EMPTY" qualifier, then the crossjoin non-empty optimizer is applied. Setting this value to '0' means that for all crossjoin input lists in non-empty axes will have the optimizer applied. On the other hand, if the value is set larger than any possible list, say <code>Integer.MAX_VALUE</code>, then the optimizer will never be applied.
    @AttributeDefinition(name = "%crossJoinOptimizerSize.name", description = "%crossJoinOptimizerSize.description", type = AttributeType.INTEGER)
    default Integer crossJoinOptimizerSize() { return 0; }

    //Property that defines the behavior of division if the denominator evaluates to zero. If false (the default), if a division has a non-null numerator and a null denominator, it evaluates to "Infinity", which conforms to SSAS behavior. If true, the result is null if the denominator is null. Setting to true enables the old semantics of evaluating this to null; this does not conform to SSAS, but is useful in some applications.
    @AttributeDefinition(name = "%nullDenominatorProducesNull.name", description = "%nullDenominatorProducesNull.description", type = AttributeType.BOOLEAN)
    default Boolean nullDenominatorProducesNull() { return false; }

    //Alerting action to take when a CurrentMember function is applied to a dimension that is also a compound slicer; Recognized actions: OFF: do nothing; WARN: log a warning; ERROR: throw an CurrentMemberWithCompoundSlicerMondrianException
    @AttributeDefinition(name = "%currentMemberWithCompoundSlicerAlert.name", description = "%currentMemberWithCompoundSlicerAlert.description", type = AttributeType.STRING)
    default String currentMemberWithCompoundSlicerAlert() { return "ERROR"; }

    //Property that defines whether to generate SQL queries using the GROUPING SETS construct for rollup. By default it is not enabled. Ignored on databases which do not support the GROUPING SETS construct (see mondrian.spi.Dialect#supportsGroupingSets).
    @AttributeDefinition(name = "%enableGroupingSets.name", description = "%enableGroupingSets.description", type = AttributeType.BOOLEAN)
    default Boolean enableGroupingSets() { return false; }

    //Property that defines whether to ignore measure when non joining dimension is in the tuple during aggregation. If there are unrelated dimensions to a measure in context during aggregation, the measure is ignored in the evaluation context. This behaviour kicks in only if the CubeUsage for this measure has IgnoreUnrelatedDimensions attribute set to false. For example, Gender doesn't join with [Warehouse Sales] measure. With mondrian.olap.agg.IgnoreMeasureForNonJoiningDimension=true Warehouse Sales gets eliminated and is ignored in the aggregate value. SUM({Product.members * Gender.members})    7,913,333.82 With mondrian.olap.agg.IgnoreMeasureForNonJoiningDimension=false Warehouse Sales with Gender All level member contributes to the aggregate value. [Store Sales] + [Warehouse Sales] SUM({Product.members * Gender.members})    9,290,730.03 On a report where Gender M, F and All members exist a user will see a large aggregated value compared to the aggregated value that can be arrived at by summing up values against Gender M and F. This can be confusing to the user. This feature can be used to eliminate such a  situation.
    @AttributeDefinition(name = "%ignoreMeasureForNonJoiningDimension.name", description = "%ignoreMeasureForNonJoiningDimension.description", type = AttributeType.BOOLEAN)
    default Boolean ignoreMeasureForNonJoiningDimension() { return false; }

    //Property determines if elements of dimension (levels, hierarchies, members) need to be prefixed with dimension name in MDX query. For example when the property is true, the following queries will error out. The same queries will work when this property is set to false. select {[M]} on 0 from sales select {[USA]} on 0 from sales select {[USA].[CA].[Santa Monica]}  on 0 from sales When the property is set to true, any query where elements are prefixed with dimension name as below will work select {[Gender].[F]} on 0 from sales select {[Customers].[Santa Monica]} on 0 from sales. Please note that this property does not govern the behaviour wherein [Gender].[M] is resolved into a fully qualified [Gender].[M] In a scenario where the schema is very large and dimensions have large number of members a MDX query that has a invalid member in it will cause mondrian to to go through all the dimensions, levels, hierarchies,  members and properties trying to resolve the element name. This behavior consumes considerable time and resources on the server. Setting this property to true will make it fail fast in a scenario where it is desirable.
    @AttributeDefinition(name = "%needDimensionPrefix.name", description = "%needDimensionPrefix.description", type = AttributeType.BOOLEAN)
    default Boolean needDimensionPrefix() { return false; }

    //Property that determines whether to cache RolapCubeMember objects, each of which associates a member of a shared hierarchy with a particular cube in which it is being used. The default is {@code true}, that is, use a cache. If you wish to use the member cache control aspects of {@link mondrian.olap.CacheControl}, you must set this property to false. RolapCubeMember has recently become more lightweight to construct, and we may obsolete this cache and this property.
    @AttributeDefinition(name = "%enableRolapCubeMemberCache.name", description = "%enableRolapCubeMemberCache.description", type = AttributeType.BOOLEAN)
    default Boolean enableRolapCubeMemberCache() { return true; }

    //Property that controls the behavior of {@link Property#SOLVE_ORDER solve order} of calculated members and sets. Valid values are "scoped" and "absolute" (the default). See  {@link mondrian.olap.SolveOrderMode} for details.
    @AttributeDefinition(name = "%solveOrderMode.name", description = "%solveOrderMode.description", type = AttributeType.STRING)
    default String solveOrderMode() { return "ABSOLUTE"; }

    //Property that sets the compound slicer member solve order.
    @AttributeDefinition(name = "%compoundSlicerMemberSolveOrder.name", description = "%compoundSlicerMemberSolveOrder.description", type = AttributeType.INTEGER)
    default Integer compoundSlicerMemberSolveOrder() { return -99999; }

    //<p>Property that controls minimum expected cardinality required in order for NativizeSet to natively evaluate a query.</p> <p>If the expected cardinality falls below this level the query is executed non-natively.</p> <p>It is possible for the actual cardinality to fall below this threshold even though the expected cardinality falls above this threshold. In this case the query will be natively evaluated.</p>
    @AttributeDefinition(name = "%nativizeMinThreshold.name", description = "%nativizeMinThreshold.description", type = AttributeType.INTEGER)
    default Integer nativizeMinThreshold() { return 100000; }

    //<p>Property that controls the maximum number of results contained in a NativizeSet result set.</p> <p>If the number of tuples contained in the result set exceeds this value Mondrian throws a LimitExceededDuringCrossjoin error.</p>
    @AttributeDefinition(name = "%nativizeMaxResults.name", description = "%nativizeMaxResults.description", type = AttributeType.INTEGER)
    default Integer nativizeMaxResults() { return -150000; }

    //<p>Property that defines whether to enable new naming behavior.</p> <p>If true, hierarchies are named [Dimension].[Hierarchy]; if false, [Dimension.Hierarchy].</p>
    @AttributeDefinition(name = "%ssasCompatibleNaming.name", description = "%ssasCompatibleNaming.description", type = AttributeType.BOOLEAN)
    default Boolean ssasCompatibleNaming() { return false; }

    //<p>Interval at which to refresh the list of XML/A catalogs. (Usually known as the datasources.xml file.)</p> <p>It is not an active process; no threads will be created. It only serves as a rate limiter. The refresh process is triggered by requests to the doPost() servlet method.</p> <p>Values may have time unit suffixes such as s (second) or ms (milliseconds). Default value is 3000 milliseconds (3 seconds). Default time unit is milliseconds.</p> <p>See also {@link mondrian.xmla.impl.DynamicDatasourceXmlaServlet}.</p>
    @AttributeDefinition(name = "%xmlaSchemaRefreshInterval.name", description = "%xmlaSchemaRefreshInterval.description", type = AttributeType.STRING)
    default String xmlaSchemaRefreshInterval() { return "3000ms"; }

    //<p>Property that defines whether to generate joins to filter out members in a snowflake dimension that do not have any children.</p> <p>If true (the default), some queries to query members of high levels snowflake dimensions will be more expensive. If false, and if there are rows in an outer snowflake table that are not referenced by a row in an inner snowflake table, then some queries will return members that have no children.</p> <p>Our recommendation, for best performance, is to remove rows outer snowflake tables are not referenced by any row in an inner snowflake table, during your ETL process, and to set this property to false.</p>
    @AttributeDefinition(name = "%filterChildlessSnowflakeMembers.name", description = "%filterChildlessSnowflakeMembers.description", type = AttributeType.BOOLEAN)
    default Boolean filterChildlessSnowflakeMembers() { return true; }

    //<p>Comma-separated list of classes to be used to get statistics about the number of rows in a table, or the number of distinct values in a column.</p> <p>If there is a value for mondrian.statistics.providers.DATABASE, where DAtABASE is the current database name (e.g. MYSQL or ORACLE), then that property overrides.</p> <p>Example:</p> mondrian.statistics.providers=mondrian.spi.impl.JdbcStatisticsProvider, mondrian.statistics.providers.MYSQL=mondrian.spi.impl.JdbcStatisticsProvider,mondrian.spi.impl.JdbcStatisticsProvider <p>This would use JDBC's statistics (via the java.sql.DatabaseMetaData.getIndexInfo method) for most databases, but for connections to a MySQL database, would use external statistics first, and fall back to JDBC statistics  only if external statistics were not available.</p>
    @AttributeDefinition(name = "%statisticsProviders.name", description = "%statisticsProviders.description", type = AttributeType.STRING)
    default String statisticsProviders() { return null; }

    //<p>Property which governs whether child members or members of a level are precached when child or level members are requested within a query expression.  For example, if an expression references two child members in the store dimension, like <code>{ [Store].[USA].[CA], [Store].[USA].[OR] }</code>, precaching will load *all* children under [USA] rather than just the 2 requested. The threshold value is compared against the cardinality of the level to determine whether or not precaching should be performed.  If cardinality is lower than the threshold value Mondrian will precache.  Setting this property to 0 effectively disables precaching. </p>
    @AttributeDefinition(name = "%levelPreCacheThreshold.name", description = "%levelPreCacheThreshold.description", type = AttributeType.INTEGER)
    default Integer levelPreCacheThreshold() { return 300; }

    //<p>Where mondrian.war will be deployed to. (Used by mondrian's build.xml ant file only.)</p> <p>Example: <code>mondrian.webapp.deploy=C:/jboss-4.0.2/server/default/deploy</code></p>
    @AttributeDefinition(name = "%webappDeploy.name", description = "%webappDeploy.description", type = AttributeType.STRING)
    default String webappDeploy() { return null; }


    //<p>The server will close a session if it is not in use for the specified timeout. Default value is 3600 seconds.</p>
    @AttributeDefinition(name = "%idleOrphanSessionTimeout.name", description = "%idleOrphanSessionTimeout.description", type = AttributeType.INTEGER)
    default Integer idleOrphanSessionTimeout() { return 3600; }

    //<p> If enable, then data cache will be created for every session.</p>
    @AttributeDefinition(name = "%enableSessionCaching.name", description = "%enableSessionCaching.description", type = AttributeType.BOOLEAN)
    default Boolean enableSessionCaching() { return false; }

    //<p>If true, then MDX functions InStr and InStrRev are case sensitive. Default value is false. </p>
    @AttributeDefinition(name = "%caseSensitiveMdxInstr.name", description = "%caseSensitiveMdxInstr.description", type = AttributeType.BOOLEAN)
    default Boolean caseSensitiveMdxInstr() { return false; }
}
