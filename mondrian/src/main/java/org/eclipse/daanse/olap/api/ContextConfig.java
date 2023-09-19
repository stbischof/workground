package org.eclipse.daanse.olap.api;

import java.util.Optional;

public interface ContextConfig {
    default Optional<String> nameOverride() {
        return Optional.empty();
    }

    default Optional<String> descriptionOverride() {
        return Optional.empty();
    }

    default Integer queryLimit() {
        return 40;
    }

    default String jdbcDrivers() {
        return "sun.jdbc.odbc.JdbcOdbcDriver,org.hsqldb.jdbcDriver,oracle.jdbc.OracleDriver,com.mysql.jdbc.Driver";
    }

    default Integer cellBatchSize() {
        return -1;
    }


    default Integer resultLimit() {
        return 0;
    }

    default Integer highCardChunkSize() {
        return 1;
    }

    default String testName() {
        return null;
    }

    default String testClass() {
        return null;
    }

    default String testConnectString() {
        return null;
    }

    default String testHighCardinalityDimensionList() {
        return null;
    }

    default String foodmartJdbcURL() {
        return "jdbc:odbc:MondrianFoodMart";
    }

    default String testJdbcUser() {
        return null;
    }

    default String testJdbcPassword() {
        return null;
    }

    default Boolean enableInMemoryRollup() {
        return true;
    }

    default String segmentCache() {
        return null;
    }

    default Integer sparseSegmentCountThreshold() {
        return 1000;
    }

    default Double sparseSegmentDensityThreshold() {
        return 0.5;
    }

    default String queryFilePattern() {
        return null;
    }

    default String queryFileDirectory() {
        return null;
    }

    default Integer iterations() {
        return 1;
    }

    default Integer vUsers() {
        return 1;
    }

    default Integer timeLimit() {
        return 0;
    }

    default Boolean warmup() {
        return false;
    }

    default String catalogURL() {
        return null;
    }

    default String warnIfNoPatternForDialect() {
        return "NONE";
    }

    default Boolean useAggregates() {
        return false;
    }

    default Boolean readAggregates() {
        return false;
    }

    default Boolean chooseAggregateByVolume() {
        return false;
    }

    default String aggregateRules() {
        return "/DefaultRules.xml";
    }

    default String aggregateRuleTag() {
        return "default";
    }

    default Boolean generateAggregateSql() { return false; }

    default Boolean disableCaching() { return false; }

    default Boolean disableLocalSegmentCache() { return false; }

    default Boolean enableTriggers() { return true; }

    default Boolean generateFormattedSql() { return false; }

    default Boolean enableNonEmptyOnAllAxis() { return false; }

    default Boolean expandNonNative() { return false; }

    default Boolean compareSiblingsByOrderKey() { return false; }

    default Boolean enableExpCache() { return true; }

    default Integer testExpDependencies() { return 0; }

    default Integer testSeed() { return 1234; }

    default String localePropFile() { return null; }

    default Boolean enableNativeCrossJoin() { return true; }

    default Boolean enableNativeTopCount() { return true; }

    default Boolean enableNativeFilter() { return true; }

    default Boolean enableNativeNonEmpty() { return true; }

    default String alertNativeEvaluationUnsupported() { return "OFF"; }

    default Boolean enableDrillThrough() { return true; }

    default Boolean enableTotalCount() { return true; }

    default Boolean caseSensitive() { return false; }

    default Integer maxRows() { return 1000; }

    default Integer maxConstraints() { return 1000; }

    default Boolean optimizePredicates() { return true; }

    default Integer maxEvalDepth() { return 10; }

    default String jdbcFactoryClass() { return null; }

    default String dataSourceResolverClass() { return null; }

    default Integer queryTimeout() { return 0; }

    default String rolapConnectionShepherdThreadPollingInterval() { return "1000ms"; }

    default Integer rolapConnectionShepherdNbThreads() { return 20; }

    default Integer segmentCacheManagerNumberSqlThreads() { return 100; }

    default Integer segmentCacheManagerNumberCacheThreads() { return 100; }

    default Boolean ignoreInvalidMembers() { return false; }

    default Boolean ignoreInvalidMembersDuringQuery() { return false; }

    default String nullMemberRepresentation() { return "#null"; }

    default String iterationLimit() { return "#null"; }

    default Integer checkCancelOrTimeoutInterval() { return 1000; }

    default Integer executionHistorySize() { return 1000; }

    default Boolean memoryMonitor() { return false; }

    default Integer memoryMonitorThreshold() { return 90; }

    default String memoryMonitorClass() { return null; }

    default String expCompilerClass() { return null; }

    default String propertyValueMapFactoryClass() { return null; }

    default String sqlMemberSourceValuePoolFactoryClass() { return null; }

    default Integer crossJoinOptimizerSize() { return 0; }

    default Boolean nullDenominatorProducesNull() { return false; }

    default String currentMemberWithCompoundSlicerAlert() { return "ERROR"; }

    default Boolean enableGroupingSets() { return false; }

    default Boolean ignoreMeasureForNonJoiningDimension() { return false; }

    default Boolean needDimensionPrefix() { return false; }

    default Boolean enableRolapCubeMemberCache() { return true; }

    default String solveOrderMode() { return "ABSOLUTE"; }

    default Integer compoundSlicerMemberSolveOrder() { return -99999; }

    default Integer nativizeMinThreshold() { return -100000; }

    default Integer nativizeMaxResults() { return -150000; }

    default Boolean ssasCompatibleNaming() { return false; }

    default String xmlaSchemaRefreshInterval() { return "3000ms"; }

    default Boolean filterChildlessSnowflakeMembers() { return true; }

    default String statisticsProviders() { return null; }

    default Integer levelPreCacheThreshold() { return 300; }

    default String webappDeploy() { return null; }

    default String webappConnectStrinexecutionHistorySizeg() { return "Provider=mondrian;Jdbc=jdbc:odbc:MondrianFoodMart;Catalog=/WEB-INF/queries/FoodMart.xml;JdbcDrivers=sun.jdbc.odbc.JdbcOdbcDriver"; }

    default String log4jConfiguration() { return null; }

    default Integer idleOrphanSessionTimeout() { return 3600; }

    default Boolean enableSessionCaching() { return false; }

    default Boolean caseSensitiveMdxInstr() { return false; }

}
