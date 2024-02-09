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
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package mondrian.resource;

import java.text.MessageFormat;

public class MondrianResource {

    private MondrianResource() {
    }

    public final static String MdxChildObjectNotFound = "MDX object ''{0}'' not found in {1}";

    public final static String MemberNotFound = "Member ''{0}'' not found";

    public final static String MdxCubeName = "cube ''{0}''";

    public final static String MdxHierarchyName = "hierarchy ''{0}''";


    public final static String MdxDimensionName = "dimension ''{0}''";

    public final static String MdxLevelName = "level ''{0}''";

    public final static String MdxMemberName = "member ''{0}''";

    public final static String FailedToParseQuery = "Failed to parse query ''{0}''";

    public final static String MdxCubeSlicerMemberError = "Failed to add Cube Slicer with member ''{0}'' for hierarchy ''{1}'' on cube ''{2}''";

    public final static String MdxCubeSlicerHierarchyError = "Failed to add Cube Slicer for hierarchy ''{0}'' on cube ''{1}''";

    public final static String MdxCalculatedHierarchyError = "Hierarchy for calculated member ''{0}'' not found";

    public final static String MdxAxisIsNotSet = "Axis ''{0}'' expression is not a set";

    public final static String MdxMemberExpIsSet = "Member expression ''{0}'' must not be a set";

    public final static String MdxSetExpNotSet = "Set expression ''{0}'' must be a set";

    public final static String MdxFuncArgumentsNum = "Function ''{0}'' must have at least 2 arguments";

    public final static String MdxFuncNotHier = "Argument ''{0,number}'' of function ''{1}'' must be a hierarchy";

    public final static String UnknownParameter = "Unknown parameter ''{0}''";

    public final static String MdxFormulaNotFound = "Calculated {0} ''{1}'' has not been found in query ''{2}''";

    public final static String MdxCantFindMember = "Cannot find MDX member ''{0}''. Make sure it is indeed a member and not a level or a hierarchy.";

    public final static String CalculatedMember = "calculated member";

    public final static String CalculatedSet = "calculated set";

    public final static String MdxCalculatedFormulaUsedOnAxis = "Cannot delete {0} ''{1}''. It is used on {2} axis.";

    public final static String MdxCalculatedFormulaUsedOnSlicer = "Cannot delete {0} ''{1}''. It is used on slicer.";

    public final static String MdxCalculatedFormulaUsedInFormula = "Cannot delete {0} ''{1}''. It is used in definition of {2} ''{3}''.";

    public final static String MdxCalculatedFormulaUsedInQuery = "Cannot delete {0} ''{1}''. It is used in query ''{2}''.";

    public final static String MdxAxisShowSubtotalsNotSupported = "Show/hide subtotals operation on axis ''{0,number}'' is not supported.";

    public final static String NoFunctionMatchesSignature = "No function matches signature ''{0}''";

    public final static String MoreThanOneFunctionMatchesSignature = "More than one function matches signature ''{0}''; they are: {1}";

    public final static String MemberNotInLevelHierarchy = "The member ''{0}'' is not in the same hierarchy as the level ''{1}''.";

    public final static String ToggleDrillStateRecursiveNotSupported = "'RECURSIVE' is not supported in ToggleDrillState.";

    public final static String FunctionMbrAndLevelHierarchyMismatch = "The <level> and <member> arguments to {0} must be from the same hierarchy. The level was from ''{1}'' but the member was from ''{2}''.";

    public final static String CousinHierarchyMismatch = "The member arguments to the Cousin function must be from the same hierarchy. The members are ''{0}'' and ''{1}''.";

    public final static String HierarchyInIndependentAxes = "Hierarchy ''{0}'' appears in more than one independent axis.";

    public final static String ArgsMustHaveSameHierarchy = "All arguments to function ''{0}'' must have same hierarchy.";

    public final static String TimeArgNeeded = "Argument to function ''{0}'' must belong to Time hierarchy.";

    //need for parser
    public final static String InvalidAxis = "Invalid axis specification. The axis number must be a non-negative integer, but it was {0,number}.";

    public final static String DuplicateAxis = "Duplicate axis name ''{0}''.";

    public final static String NonContiguousAxis = "Axis numbers specified in a query must be sequentially specified, and cannot contain gaps. Axis {0,number} ({1}) is missing.";

    public final static String DupHierarchiesInTuple = "Tuple contains more than one member of hierarchy ''{0}''.";

    public final static String VisualTotalsAppliedToTuples = "Argument to 'VisualTotals' function must be a set of members; got set of tuples.";

    public final static String ParameterIsNotModifiable = "Parameter ''{0}'' (defined at ''{1}'' scope) is not modifiable";

    public final static String ParameterDefinedMoreThanOnce = "Parameter ''{0}'' is defined more than once in this statement";

    public final static String CycleDuringParameterEvaluation = "Cycle occurred while evaluating parameter ''{0}''";

    public final static String CastInvalidType = "Unknown type ''{0}''; values are NUMERIC, STRING, BOOLEAN";

    public final static String NullNotSupported = "Function does not support NULL member parameter";

    public final static String TwoNullsNotSupported = "Function does not support two NULL member parameters";

    public final static String NoTimeDimensionInCube = "Cannot use the function ''{0}'', no time dimension is available for this cube.";

    public final static String HierarchyHasNoAccessibleMembers = "Hierarchy ''{0}'' has no accessible members.";

    public final static String NullValue = "An MDX expression was expected. An empty expression was specified.";

    public final static String DrillthroughDisabled = "Can''t perform drillthrough operations because ''{0}'' is set to false.";

    public final static String DrillthroughCalculatedMember = "Can''t perform drillthrough operations because ''{0}'' is a calculated member.";

    public final static String ValidMeasureUsingCalculatedMember = "The function ValidMeasure cannot be used with the measure ''{0}'' because it is a calculated member. The function should be used to wrap the base measure in the source cube.";

    public final static String UnsupportedCalculatedMember = "Calculated member ''{0}'' is not supported within a compound predicate";

    public final static String CurrentMemberWithCompoundSlicer = "The MDX function CURRENTMEMBER failed because the coordinate for the ''{0}'' hierarchy contains a set";

    public final static String NonTimeLevelInTimeHierarchy = "Level ''{0}'' belongs to a time hierarchy, so its level-type must be  ''Regular'', ''TimeYears'', ''TimeHalfYears'', ''TimeHalfYear'', ''TimeQuarters'', ''TimeMonths'', ''TimeWeeks'', ''TimeDays'', ''TimeHours'', ''TimeMinutes'', ''TimeSeconds'', ''TimeUndefined''.";

    public final static String TimeLevelInNonTimeHierarchy = "Level ''{0}'' does not belong to a time hierarchy, so its level-type must be ''Standard''.";

    public final static String MustSpecifyPrimaryKeyForHierarchy = "In usage of hierarchy ''{0}'' in cube ''{1}'', you must specify a primary key.";

    public final static String MustSpecifyPrimaryKeyTableForHierarchy = "Must specify a primary key table for hierarchy ''{0}'', because it has more than one table.";

    public final static String MustSpecifyForeignKeyForHierarchy = "In usage of hierarchy ''{0}'' in cube ''{1}'', you must specify a foreign key, because the hierarchy table is different from the fact table.";

    public final static String LevelMustHaveNameExpression = "Level ''{0}'' must have a name expression (a ''column'' attribute or an <Expression> child";

    public final static String PublicDimensionMustNotHaveForeignKey = "Dimension ''{0}'' has a foreign key. This attribute is only valid in private dimensions and dimension usages.";

    public final static String HierarchyMustNotHaveMoreThanOneSource = "Hierarchy ''{0}'' has more than one source (memberReaderClass, <Table>, <Join> or <View>)";

    public final static String DimensionUsageHasUnknownLevel = "In usage of dimension ''{0}'' in cube ''{1}'', the level ''{2}'' is unknown";

    public final static String CalcMemberHasBadDimension = "Unknown dimension ''{0}'' for calculated member ''{1}'' in cube ''{2}''";

    public final static String CalcMemberHasBothDimensionAndHierarchy = "Cannot specify both a dimension and hierarchy for calculated member ''{0}'' in cube ''{1}''";

    public final static String CalcMemberHasUnknownParent = "Cannot find a parent with name ''{0}'' for calculated member ''{1}'' in cube ''{2}''";

    public final static String CalcMemberHasDifferentParentAndHierarchy = "The calculated member ''{0}'' in cube ''{1}'' is defined for hierarchy ''{2}'' but its parent member is not part of that hierarchy";

    public final static String CalcMemberNotUnique = "Calculated member ''{0}'' already exists in cube ''{1}''";

    public final static String NeitherExprNorValueForCalcMemberProperty = "Member property must have a value or an expression. (Property ''{0}'' of member ''{1}'' of cube ''{2}''.)";

    public final static String ExprAndValueForMemberProperty = "Member property must not have both a value and an expression. (Property ''{0}'' of member ''{1}'' of cube ''{2}''.)";

    public final static String MemberFormatterLoadFailed = "Failed to load formatter class ''{0}'' for level ''{1}''.";

    public final static String CellFormatterLoadFailed = "Failed to load formatter class ''{0}'' for member ''{1}''.";

    public final static String PropertyFormatterLoadFailed = "Failed to load formatter class ''{0}'' for property ''{1}''.";

    public final static String HierarchyMustHaveForeignKey = "Hierarchy ''{0}'' in cube ''{1}'' must have a foreign key, since it is not based on the cube''s fact table.";

    public final static String HierarchyInvalidForeignKey = "Foreign key ''{0}'' of hierarchy ''{1}'' in cube ''{2}'' is not a column in the fact table.";

    public final static String UdfClassNotFound = "Failed to load user-defined function ''{0}'': class ''{1}'' not found";

    public final static String UdfClassMustBePublicAndStatic = "Failed to load user-defined function ''{0}'': class ''{1}'' must be public and static";

    public final static String UdfClassWrongIface = "Failed to load user-defined function ''{0}'': class ''{1}'' does not implement the required interface ''{2}''";

    public final static String UdfDuplicateName = "Duplicate user-defined function ''{0}''";

    public final static String NamedSetNotUnique = "Named set ''{0}'' already exists in cube ''{1}''";

    public final static String UnknownNamedSetHasBadFormula = "Named set in cube ''{0}'' has bad formula";

    public final static String NamedSetHasBadFormula = "Named set ''{0}'' has bad formula";

    public final static String MeasureOrdinalsNotUnique = "Cube ''{0}'': Ordinal {1} is not unique: ''{2}'' and ''{3}''";

    public final static String BadMeasureSource = "Cube ''{0}'': Measure ''{1}'' must contain either a source column or a source expression, but not both";

    public final static String DuplicateSchemaParameter = "Duplicate parameter ''{0}'' in schema";

    public final static String UnknownAggregator = "Unknown aggregator ''{0}''; valid aggregators are: {1}";

    public final static String RoleUnionGrants = "Union role must not contain grants";

    public final static String UnknownRole = "Unknown role ''{0}''";

    public final static String DescendantsAppliedToSetOfTuples = "Argument to Descendants function must be a member or set of members, not a set of tuples";

    public final static String CannotDeduceTypeOfSet = "Cannot deduce type of set";

    public final static String NotANamedSet = "Not a named set";

    public final static String HierarchyHasNoLevels = "Hierarchy ''{0}'' must have at least one level.";

    public final static String HierarchyLevelNamesNotUnique = "Level names within hierarchy ''{0}'' are not unique; there is more than one level with name ''{1}''.";

    public final static String IllegalLeftDeepJoin = "Left side of join must not be a join; mondrian only supports right-deep joins.";

    public final static String LevelTableParentNotFound = "The level {0} makes use of the ''parentColumn'' attribute, but a parent member for key {1} is missing. This can be due to the usage of the NativizeSet MDX function with a list of members form a parent-child hierarchy that doesn''t include all parent members in its definition. Using NativizeSet with a parent-child hierarchy requires the parent members to be included in the set, or the hierarchy cannot be properly built natively.";

    public final static String CreateTableFailed = "Mondrian loader could not create table ''{0}''.";

    public final static String CreateIndexFailed = "Mondrian loader could not create index ''{0}'' on table ''{1}''.";

    public final static String MissingArg = "Argument ''{0}'' must be specified.";

    public final static String InvalidInsertLine = "Input line is not a valid INSERT statement; line {0,number}: {1}.";

    public final static String LimitExceededDuringCrossjoin = "Size of CrossJoin result ({0,number}) exceeded limit ({1,number})";

    public final static String TotalMembersLimitExceeded = "Total number of Members in result ({0,number}) exceeded limit ({1,number})";

    public final static String MemberFetchLimitExceeded = "Number of members to be read exceeded limit ({0,number})";

    public final static String SegmentFetchLimitExceeded = "Number of cell results to be read exceeded limit of ({0,number})";

    public final static String QueryCanceled = "Query canceled";

    public final static String QueryTimeout = "Query timeout of {0,number} seconds reached";

    public final static String IterationLimitExceeded = "Number of iterations exceeded limit of {0,number}";

    public final static String JavaDoubleOverflow = "Big decimal value in ''{0}'' exceeds double size.";

    public final static String InvalidHierarchyCondition = "Hierarchy ''{0}'' is invalid (has no members)";

    public final static String TooManyMessageRecorderErrors = "Context ''{0}'': Exceeded number of allowed errors ''{1,number}''";

    public final static String ForceMessageRecorderError = "Context ''{0}'': Client forcing return with errors ''{1,number}''";

    public final static String UnknownLevelName = "Context ''{0}'': The Hierarchy Level ''{1}'' does not have a Level named ''{2}''";

    public final static String DuplicateLevelNames = "Context ''{0}'': Two levels share the same name ''{1}''";

    public final static String DuplicateLevelColumnNames = "Context ''{0}'': Two levels, ''{1}'' and ''{2}'',  share the same foreign column name ''{3}''";

    public final static String DuplicateMeasureColumnNames = "Context ''{0}'': Two measures, ''{1}'' and ''{2}'',  share the same column name ''{3}''";

    public final static String DuplicateLevelMeasureColumnNames = "Context ''{0}'': The level ''{1}'' and the measuer ''{2}'',  share the same column name ''{3}''";

    public final static String DuplicateMeasureNames = "Context ''{0}'': Two measures share the same name ''{1}''";

    public final static String DuplicateFactForeignKey = "Context ''{0}'': Duplicate fact foreign keys ''{1}'' for key ''{2}''.";

    public final static String UnknownLeftJoinCondition = "Context ''{0}'': Failed to find left join condition in fact table ''{1}'' for foreign key ''{2}''.";

    public final static String UnknownHierarchyName = "Context ''{0}'': The Hierarchy ''{1}'' does not exist\"";

    public final static String BadLevelNameFormat = "Context ''{0}'': The Level name ''{1}'' should be [usage hierarchy name].[level name].";

    public final static String BadMeasureNameFormat = "Context ''{0}'': The Measures name ''{1}'' should be [Measures].[measure name].";

    public final static String BadMeasures = "Context ''{0}'': This name ''{1}'' must be the string \"Measures\".";

    public final static String UnknownMeasureName = "Context ''{0}'': Measures does not have a measure named ''{1}''";

    public final static String NullAttributeString = "Context ''{0}'': The value for the attribute ''{1}'' is null.";

    public final static String EmptyAttributeString = "Context ''{0}'': The value for the attribute ''{1}'' is empty (length is zero).";

    public final static String MissingDefaultAggRule = "There is no default aggregate recognition rule with tag ''{0}''.";

    public final static String AggRuleParse = "Error while parsing default aggregate recognition ''{0}''.";

    public final static String BadMeasureName = "Context ''{0}'': Failed to find Measure name ''{1}'' for cube ''{2}''.";

    public final static String BadRolapStarLeftJoinCondition = "Context ''{0}'': Bad RolapStar left join condition type: ''{1}'' ''{2}''.";

    public final static String SqlQueryFailed = "Context ''{0}'': Sql query failed to run ''{1}''.";

    public final static String AggLoadingError = "Error while loading/reloading aggregates.";

    public final static String AggLoadingExceededErrorCount = "Too many errors, ''{0,number}'', while loading/reloading aggregates.";

    public final static String UnknownFactTableColumn = "Context ''{0}'': For Fact table ''{1}'', the column ''{2}'' is neither a measure or foreign key\".";

    public final static String AggMultipleMatchingMeasure = "Context ''{0}'': Candidate aggregate table ''{1}'' for fact table ''{2}'' has ''{3,number}'' columns matching measure ''{4}'', ''{5}'', ''{6}''\".";

    public final static String CouldNotLoadDefaultAggregateRules = "Could not load default aggregate rules ''{0}''.";

    public final static String CubeRelationNotTable = "The Cube ''{0}'' relation is not a MondrianDef.Table but rather ''{1}''.";

    public final static String AttemptToChangeTableUsage = "JdbcSchema.Table ''{0}'' already set to usage ''{1}'' and can not be reset to usage ''{2}''.";

    public final static String NonNumericFactCountColumn = "Candidate aggregate table ''{0}'' for fact table ''{1}'' has candidate fact count column ''{2}'' has type ''{3}'' that is not numeric.";

    public final static String TooManyFactCountColumns = "Candidate aggregate table ''{0}'' for fact table ''{1}'' has ''{2,number}'' fact count columns.";

    public final static String NoFactCountColumns = "Candidate aggregate table ''{0}'' for fact table ''{1}'' has no fact count columns.";

    public final static String NoMeasureColumns = "Candidate aggregate table ''{0}'' for fact table ''{1}'' has no measure columns.";

    public final static String TooManyMatchingForeignKeyColumns = "Candidate aggregate table ''{0}'' for fact table ''{1}'' had ''{2,number}'' columns matching foreign key ''{3}''";

    public final static String DoubleMatchForLevel = "Double Match for candidate aggregate table ''{0}'' for fact table ''{1}'' and column ''{2}'' matched two hierarchies: 1) table=''{3}'', column=''{4}'' and 2) table=''{5}'', column=''{6}''";

    public final static String AggUnknownColumn = "Candidate aggregate table ''{0}'' for fact table ''{1}'' has a column ''{2}'' with unknown usage.";

    public final static String NoAggregatorFound = "No aggregator found while converting fact table aggregator: for usage\n        ''{0}'', fact aggregator ''{1}'' and sibling aggregator ''{2}''";

    public final static String NoColumnNameFromExpression = "Could not get a column name from a level key expression: ''{0}''.";

    public final static String AggTableZeroSize = "Zero size Aggregate table ''{0}'' for Fact Table ''{1}''.";

    public final static String AggTableNoConstraintGenerated = "Aggregate star fact table ''{0}'':  A constraint will not be generated because name column is not the same as key column.";

    public final static String CacheFlushRegionMustContainMembers = "Region of cells to be flushed must contain measures.";

    public final static String CacheFlushUnionDimensionalityMismatch = "Cannot union cell regions of different dimensionalities. (Dimensionalities are ''{0}'', ''{1}''.)";

    public final static String CacheFlushCrossjoinDimensionsInCommon = "Cannot crossjoin cell regions which have dimensions in common. (Dimensionalities are {0}.)";

    public final static String SegmentCacheIsNotImplementingInterface = "The mondrian.rolap.SegmentCache property points to a class name which is not an\n        implementation of mondrian.spi.SegmentCache.";

    public final static String SegmentCacheFailedToInstanciate = "An exception was encountered while creating the SegmentCache.";

    public final static String SegmentCacheFailedToLoadSegment = "An exception was encountered while loading a segment from the SegmentCache.";

    public final static String SegmentCacheFailedToSaveSegment = "An exception was encountered while loading a segment from the SegmentCache.";

    public final static String SegmentCacheFailedToLookupSegment = "An exception was encountered while performing a segment lookup in the SegmentCache.";

    public final static String SegmentCacheFailedToScanSegments = "An exception was encountered while getting a list of segment headers in the SegmentCache.";

    public final static String SegmentCacheFailedToDeleteSegment = "An exception was encountered while deleting a segment from the SegmentCache.";

    public final static String SegmentCacheReadTimeout = "Timeout reached while reading segment from SegmentCache.";

    public final static String SegmentCacheWriteTimeout = "Timeout reached while writing segment to SegmentCache.";

    public final static String SegmentCacheLookupTimeout = "Timeout reached while performing a segment lookup in SegmentCache.";

    public final static String NativeEvaluationUnsupported = "Native evaluation not supported for this usage of function ''{0}''";

    public final static String NativeSqlInClauseTooLarge = "Cannot use native aggregation constraints for level ''{0}'' because the number of members is larger than the value of ''mondrian.rolap.maxConstraints'' ({1})";

    public final static String ExecutionStatementCleanupException = "An exception was encountered while trying to cleanup an execution context. A statement failed to cancel gracefully. Locus was : \"{0}\".";

    public final static String QueryLimitReached = "The number of concurrent MDX statements that can be processed simultaneously by this Mondrian server instance ({0,number}) has been reached. To change the limit, set the ''{1}'' property.";

    public final static String SqlQueryLimitReached = "The number of concurrent SQL statements which can be used simultaneously by this Mondrian server instance has been reached. Set ''mondrian.rolap.maxSqlThreads'' to change the current limit.";

    public final static String SegmentCacheLimitReached = "The number of concurrent segment cache operations which can be run simultaneously by this Mondrian server instance has been reached. Set ''mondrian.rolap.maxCacheThreads'' to change the current limit.";

    public final static String FinalizerErrorRolapSchema = "An exception was encountered while finalizing a RolapSchema object instance.";

    public final static String FinalizerErrorMondrianServerImpl = "An exception was encountered while finalizing a RolapSchema object instance.";

    public static String message(String s, Object... message) {
        return MessageFormat.format(s, message);
    }
}
