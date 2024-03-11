package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import javax.xml.namespace.QName;

public class Constants {

	private Constants() {
		// constructor
	}

	// XMLA

	static class MSXMLA {

		public static final String PREFIX = "msxmla";
		public static final String NS_URN = "urn:schemas-microsoft-com:xml-analysis";

		public static final QName QN_COMMAND = new QName(NS_URN, "Command", PREFIX);
		public static final QName QN_DISCOVER = new QName(NS_URN, "Discover", PREFIX);
		public static final QName QN_DISCOVER_RESPONSE = new QName(NS_URN, "DiscoverResponse", PREFIX);
		public static final QName QN_EXECUTE = new QName(NS_URN, "Execute", PREFIX);
		public static final QName QN_EXECUTE_RESPONSE = new QName(NS_URN, "ExecuteResponse", PREFIX);
		public static final QName QN_PROPERTIES = new QName(NS_URN, "Properties", PREFIX);
		public static final QName QN_PROPERTY_LIST = new QName(NS_URN, "PropertyList", PREFIX);
		public static final QName QN_RESTRICTIONS = new QName(NS_URN, "Restrictions", PREFIX);
		public static final QName QN_RESTRICTION_LIST = new QName(NS_URN, "RestrictionList", PREFIX);
		public static final QName QN_RETURN = new QName(NS_URN, "return", PREFIX);
		public static final QName QN_REQUEST_TYPE = new QName(NS_URN, "RequestType", PREFIX);

	}

	/////
	static class EMPTY {

		public static final String PREFIX = "empty";
		public static final String NS_URN = "urn:schemas-microsoft-com:xml-analysis:empty";
		public static final QName QN_ROOT = new QName(NS_URN, "root", EMPTY.PREFIX);
	}

	/////
	static class ROWSET {

		public static final String PREFIX = "rowset";
		public static final String NS_URN = "urn:schemas-microsoft-com:xml-analysis:rowset";
		public static final QName QN_ROOT = new QName(ROWSET.NS_URN, "root", ROWSET.PREFIX);
		public static final QName QN_ROW = new QName(ROWSET.NS_URN, "row", ROWSET.PREFIX);

		static class ROW_PROPERTY {
			public static final String CATALOG_NAME = "CATALOG_NAME";
			public static final QName QN_CATALOG_NAME = new QName(ROWSET.NS_URN, CATALOG_NAME, ROWSET.PREFIX);


			public static final String SCHEMA_NAME = "SCHEMA_NAME";
			public static final QName QN_SCHEMA_NAME = new QName(ROWSET.NS_URN, SCHEMA_NAME, ROWSET.PREFIX);


			public static final String CUBE_NAME = "CUBE_NAME";
			public static final QName QN_CUBE_NAME = new QName(ROWSET.NS_URN, CUBE_NAME, ROWSET.PREFIX);



		}

	}
	/////

	static class MDDATASET {
		public static final String PREFIX = "mddataset";
		public static final String NS_URN = "urn:schemas-microsoft-com:xml-analysis:mddataset";
		public static final QName QN_CELL_INFO = new QName(MDDATASET.NS_URN, "CellInfo", MDDATASET.PREFIX);
		public static final QName QN_OLAP_INFO = new QName(MDDATASET.NS_URN, "OlapInfo", MDDATASET.PREFIX);
		public static final QName QN_ROOT = new QName(MDDATASET.NS_URN, "root", MDDATASET.PREFIX);
	}

	static class ENGINE {
		public static final String PREFIX = "engine";
		public static final String NS_URN = "http://schemas.microsoft.com/analysisservices/2003/engine";
	}

	static class ENGINE200 {
		public static final String PREFIX = "engine200";
		public static final String NS_URN = "http://schemas.microsoft.com/analysisservices/2010/engine/200";
	}

	static class XSI {
		public static final String PREFIX = "xsi";
		public static final String NS_URN = "http://www.w3.org/2001/XMLSchema-instance";
	}
	///////////////////////////

	public static final String DISPLAY_FOLDER = "DisplayFolder";
	public static final String DIMENSIONS = "Dimensions";
	public static final String DIMENSION_ID = "DimensionID";
	public static final String DIMENSION = "Dimension";
	public static final String DESCRIPTION = "Description";
	public static final String DEFAULT_MEMBER = "DefaultMember";
	public static final String DEFAULT_DETAILS_POSITION = "DefaultDetailsPosition";
	public static final String DATA_SOURCE_VIEW_ID = "DataSourceViewID";
	public static final String DATA_SOURCE_ID = "DataSourceID";
	public static final String DIMENSION_VISIBILITY = "DIMENSION_VISIBILITY";
	public static final String DIMENSION_UNIQUE_NAME = "DIMENSION_UNIQUE_NAME";
	public static final String CUBE_ID = "CubeID";
	public static final String CUBE_DIMENSION_ID = "CubeDimensionID";
	public static final String CREATED_TIMESTAMP = "CreatedTimestamp";
	public static final String CONTEXTUAL_NAME_RULE = "ContextualNameRule";
	public static final String CONDITION = "Condition";
	public static final String COLUMNS = "Columns";
	public static final String COLLATION = "Collation";
	public static final String CAPTION_IS_MDX = "CaptionIsMdx";
	public static final String CAPTION = "Caption";
	public static final String CUBE_SOURCE = "CUBE_SOURCE";

	public static final String ATTRIBUTES = "Attributes";
	public static final String ATTRIBUTE_ID = "AttributeID";
	public static final String ATTRIBUTE_HIERARCHY_VISIBLE = "AttributeHierarchyVisible";
	public static final String APPLICATION = "Application";
	public static final String ANNOTATIONS = "Annotations";
	public static final String ALLOW_DRILL_THROUGH = "AllowDrillThrough";
	public static final String AGGREGATION_PREFIX = "AggregationPrefix";
	public static final String AGGREGATION_DESIGN_ID = "AggregationDesignID";
	public static final String MEASURE = "Measure";
	public static final String HIERARCHY = "Hierarchy";
	public static final String ATTRIBUTE = "Attribute";
	public static final String ACTION = "Action";
//    public static final String ROW = "row";
	public static final String ROLE_ID = "RoleID";
	public static final String RESTRICTION_LIST = "RestrictionList";
	public static final String REFRESH_INTERVAL = "RefreshInterval";
	public static final String READ_DEFINITION = "ReadDefinition";
	public static final String PROCESSING_STATE = "ProcessingState";
	public static final String PROCESSING_PRIORITY = "ProcessingPriority";
	public static final String PROCESSING_MODE = "ProcessingMode";
	public static final String PROCESS = "Process";
	public static final String PROACTIVE_CACHING = "ProactiveCaching";
	public static final String ORDINAL = "Ordinal";
	public static final String NOT_LIKE = "NotLike";
	public static final String NOT_EQUAL = "NotEqual";
	public static final String MINING_MODEL_ID = "MiningModelID";
	public static final String MEASURES = "Measures";
	public static final String MEASURE_ID = "MeasureID";
	public static final String MEASURE_GROUP_ID = "MeasureGroupID";
	public static final String MEASURE_GROUP = "MeasureGroup";
	public static final String MEASUREGROUP_NAME = "MEASUREGROUP_NAME";
	public static final String LESS_OR_EQUAL = "LessOrEqual";
	public static final String LAST_SCHEMA_UPDATE = "LastSchemaUpdate";
	public static final String LAST_PROCESSED = "LastProcessed";
	public static final String LANGUAGE = "Language";
	public static final String LEVEL_UNIQUE_NAME = "LEVEL_UNIQUE_NAME";
	public static final String KEY_COLUMNS = "KeyColumns";
	public static final String KEY_COLUMN = "KeyColumn";
	public static final String INVOCATION_LOW = "Invocation";
	public static final String HIERARCHIES = "Hierarchies";
	public static final String HIERARCHY_UNIQUE_NAME = "HIERARCHY_UNIQUE_NAME";
	public static final String GREATER_OR_EQUAL = "GreaterOrEqual";
	public static final String GREATER = "Greater";
	public static final String FOLDER_POSITION = "FolderPosition";
	public static final String FILTER = "Filter";
	public static final String ESTIMATED_SIZE = "EstimatedSize";
	public static final String ESTIMATED_ROWS = "EstimatedRows";
	public static final String ERROR_CONFIGURATION = "ErrorConfiguration";
	public static final String EQUAL = "Equal";
	public static final String EXPRESSION = "Expression";

	public static final String TABLE_SCHEMA = "TABLE_SCHEMA";
	public static final String TRANSLATIONS = "Translations";
	public static final String TABLE_NAME = "TABLE_NAME";
	public static final String TABLE_CATALOG = "TABLE_CATALOG";
	public static final String SOURCE = "Source";
	public static final String XSI_TYPE = "xsi:type";
	public static final String VALUENS = "valuens";
	public static final String WRITE = "Write";
	public static final String VISUALIZATION_PROPERTIES = "VisualizationProperties";
	public static final String VISIBLE = "Visible";
	public static final String VALUE = "Value";
	public static final String TRANSLATION = "Translation";
	public static final String TARGET_TYPE = "TargetType";
	public static final String TARGET = "Target";
	public static final String TABLE_ID = "TableID";
	public static final String TABLE_TYPE = "TABLE_TYPE";
	public static final String STORAGE_MODE = "StorageMode";
	public static final String STORAGE_LOCATION = "StorageLocation";
	public static final String STATE = "State";
	public static final String SORT_PROPERTIES_POSITION = "SortPropertiesPosition";
//	public static final String SCHEMA_NAME = "SCHEMA_NAME";

	public static final String AUTHENTICATION_MODE = "AuthenticationMode";
	public static final String PROVIDER_TYPE = "ProviderType";
	public static final String PROVIDER_NAME = "ProviderName";
	public static final String DATA_SOURCE_INFO = "DataSourceInfo";
	public static final String URL = "URL";
	public static final String DATA_SOURCE_DESCRIPTION = "DataSourceDescription";
	public static final String DATA_SOURCE_NAME = "DataSourceName";
	public static final String OBJECT_EXPANSION = "ObjectExpansion";
	public static final String DATA_SOURCE_PERMISSION_ID = "DataSourcePermissionID";
	public static final String MDX_SCRIPT_ID = "MdxScriptID";
	public static final String ASSEMBLY_ID = "AssemblyID";
	public static final String CUBE_PERMISSION_ID = "CubePermissionID";
	public static final String MINING_STRUCTURE_PERMISSION_ID = "MiningStructurePermissionID";
	public static final String TRACE_ID = "TraceID";
	public static final String MINING_STRUCTURE_ID = "MiningStructureID";
	public static final String MINING_MODEL_PERMISSION_ID = "MiningModelPermissionID";
	public static final String DATABASE_PERMISSION_ID = "DatabasePermissionID";
	public static final String DIMENSION_PERMISSION_ID = "DimensionPermissionID";
	public static final String PERSPECTIVE_ID = "PerspectiveID";
	public static final String PARTITION_ID = "PartitionID";
	public static final String DATABASE_ID = "DatabaseID";
    public static final String OBJECT_TYPE = "ObjectType";
	public static final String COLUMN_OLAP_TYPE = "COLUMN_OLAP_TYPE";
	public static final String COLUMN_NAME = "COLUMN_NAME";
	public static final String BEST_MATCH = "BEST_MATCH";
	public static final String DATA_TYPE = "DATA_TYPE";
	public static final String SCHEMA_OWNER = "SCHEMA_OWNER";
	public static final String HIERARCHY_VISIBILITY = "HIERARCHY_VISIBILITY";
	public static final String HIERARCHY_ORIGIN = "HIERARCHY_ORIGIN";
	public static final String HIERARCHY_NAME = "HIERARCHY_NAME";
	public static final String LEVEL_NAME = "LEVEL_NAME";
	public static final String MEASURE_VISIBILITY = "MEASURE_VISIBILITY";
	public static final String MEASURE_UNIQUE_NAME = "MEASURE_UNIQUE_NAME";
	public static final String MEASURE_NAME = "MEASURE_NAME";
	public static final String TREE_OP = "TREE_OP";
	public static final String MEMBER_CAPTION = "MEMBER_CAPTION";
	public static final String MEMBER_TYPE = "MEMBER_TYPE";
	public static final String MEMBER_NAME = "MEMBER_NAME";
	public static final String LEVEL_NUMBER = "LEVEL_NUMBER";
	public static final String PROPERTY_VISIBILITY = "PROPERTY_VISIBILITY";
	public static final String PROPERTY_ORIGIN = "PROPERTY_ORIGIN";
	public static final String PROPERTY_TYPE = "PROPERTY_TYPE";
    public static final String PROPERTY_CONTENT_TYPE = "PROPERTY_CONTENT_TYPE";
	public static final String PROPERTY_NAME2 = "PROPERTY_NAME";
	public static final String MEMBER_UNIQUE_NAME = "MEMBER_UNIQUE_NAME";
	public static final String SCOPE = "SCOPE";
    public static final String SET_CAPTION = "SET_CAPTION";
    public static final String SET_DISPLAY_FOLDER = "SET_DISPLAY_FOLDER";
	public static final String SET_NAME = "SET_NAME";
	public static final String KPI_NAME = "KPI_NAME";
    public static final String CUBE_TYPE = "CUBE_TYPE";
	public static final String BASE_CUBE_NAME = "BASE_CUBE_NAME";
	public static final String DIMENSION_NAME = "DIMENSION_NAME";
	public static final String LIBRARY_NAME = "LIBRARY_NAME";
	public static final String INTERFACE_NAME = "INTERFACE_NAME";
	public static final String ORIGIN = "ORIGIN";
    public static final String FUNCTION_NAME = "FUNCTION_NAME";
	public static final String PROPERTY_NAME = "PropertyName";
	public static final String NAME = "Name";
	public static final String ID = "ID";

	public static final String EVENT_ID = "EventID";
	public static final String ROLE2 = "Role";
	public static final String ASSEMBLY2 = "Assembly";
	public static final String CUBE2 = "Cube";
	public static final String ALLOW_CREATE = "AllowCreate";
	public static final String SCOPE2 = "Scope";
	public static final String OBJECT_DEFINITION = "ObjectDefinition";
	public static final String SERVER_ID = "ServerID";
	public static final String OBJECT2 = "Object";
	public static final String CANCEL_ASSOCIATED = "CancelAssociated";
	public static final String SPID = "SPID";
	public static final String SESSION_ID = "SessionID";
	public static final String CONNECTION_ID = "ConnectionID";
	public static final String CANCEL = "Cancel";
	public static final String CLEAR_CACHE = "ClearCache";
	public static final String ALTER = "Alter";
	public static final String STATEMENT = "Statement";
	public static final String COMMAND = "Command";
	public static final String COORDINATE_TYPE = "COORDINATE_TYPE";
	public static final String INVOCATION = "INVOCATION";
	public static final String COORDINATE = "COORDINATE";
	public static final String ACTION_TYPE = "ACTION_TYPE";
	public static final String ACTION_NAME = "ACTION_NAME";
	public static final String LITERAL_NAME = "LiteralName";
	public static final String KEYWORD = "Keyword";
	public static final String ENUM_NAME = "EnumName";
	public static final String SCHEMA_NAME_LOW = "SchemaName";
	public static final String NUMERIC_SCALE = "NUMERIC_SCALE";
	public static final String NUMERIC_PRECISION = "NUMERIC_PRECISION";
	public static final String EXPRESSION_UC = "EXPRESSION";
	public static final String DIMENSION_IS_VISIBLE = "DIMENSION_IS_VISIBLE";
	public static final String DESCRIPTION_UC = "DESCRIPTION";

}
