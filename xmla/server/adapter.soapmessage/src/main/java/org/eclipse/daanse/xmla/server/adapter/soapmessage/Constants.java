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

            public static final String SCHEMA_NAME_LC = "SchemaName";
            public static final QName QN_SCHEMA_NAME_LC = new QName(ROWSET.NS_URN, SCHEMA_NAME_LC, ROWSET.PREFIX);

			public static final String CUBE_NAME = "CUBE_NAME";
			public static final QName QN_CUBE_NAME = new QName(ROWSET.NS_URN, CUBE_NAME, ROWSET.PREFIX);

            public static final String ACTION_NAME = "ACTION_NAME";
            public static final QName QN_ACTION_NAME = new QName(ROWSET.NS_URN, ACTION_NAME, ROWSET.PREFIX);

            public static final String ACTION_TYPE = "ACTION_TYPE";
            public static final QName QN_ACTION_TYPE = new QName(ROWSET.NS_URN, ACTION_TYPE, ROWSET.PREFIX);

            public static final String COORDINATE = "COORDINATE";
            public static final QName QN_COORDINATE = new QName(ROWSET.NS_URN, COORDINATE, ROWSET.PREFIX);

            public static final String COORDINATE_TYPE = "COORDINATE_TYPE";
            public static final QName QN_COORDINATE_TYPE = new QName(ROWSET.NS_URN, COORDINATE_TYPE, ROWSET.PREFIX);

            public static final String ACTION_CAPTION = "ACTION_CAPTION";
            public static final QName QN_ACTION_CAPTION = new QName(ROWSET.NS_URN, ACTION_CAPTION, ROWSET.PREFIX);

            public static final String DESCRIPTION = "DESCRIPTION";
            public static final QName QN_DESCRIPTION = new QName(ROWSET.NS_URN, DESCRIPTION, ROWSET.PREFIX);

            public static final String DESCRIPTION_LC = "Description";
            public static final QName QN_DESCRIPTION_LC = new QName(ROWSET.NS_URN, DESCRIPTION_LC, ROWSET.PREFIX);

            public static final String CONTENT = "CONTENT";
            public static final QName QN_CONTENT = new QName(ROWSET.NS_URN, CONTENT, ROWSET.PREFIX);

            public static final String APPLICATION = "APPLICATION";
            public static final QName QN_APPLICATION = new QName(ROWSET.NS_URN, APPLICATION, ROWSET.PREFIX);

            public static final String INVOCATION = "INVOCATION";
            public static final QName QN_INVOCATION = new QName(ROWSET.NS_URN, INVOCATION, ROWSET.PREFIX);

            public static final String ENUM_NAME = "EnumName";
            public static final QName QN_ENUM_NAME = new QName(ROWSET.NS_URN, ENUM_NAME, ROWSET.PREFIX);

            public static final String ENUM_DESCRIPTION = "EnumDescription";
            public static final QName QN_ENUM_DESCRIPTION = new QName(ROWSET.NS_URN, ENUM_DESCRIPTION, ROWSET.PREFIX);

            public static final String ENUM_TYPE = "EnumType";
            public static final QName QN_ENUM_TYPE = new QName(ROWSET.NS_URN, ENUM_TYPE, ROWSET.PREFIX);

            public static final String ELEMENT_NAME = "ElementName";
            public static final QName QN_ELEMENT_NAME = new QName(ROWSET.NS_URN, ELEMENT_NAME, ROWSET.PREFIX);

            public static final String ELEMENT_DESCRIPTION = "ElementDescription";
            public static final QName QN_ELEMENT_DESCRIPTION = new QName(ROWSET.NS_URN, ELEMENT_DESCRIPTION, ROWSET.PREFIX);

            public static final String ELEMENT_VALUE = "ElementValue";
            public static final QName QN_ELEMENT_VALUE = new QName(ROWSET.NS_URN, ELEMENT_VALUE, ROWSET.PREFIX);

            public static final String SCHEMA_GUID = "SchemaGuid";
            public static final QName QN_SCHEMA_GUID = new QName(ROWSET.NS_URN, SCHEMA_GUID, ROWSET.PREFIX);

            public static final String RESTRICTIONS_MASK = "RestrictionsMask";
            public static final QName QN_RESTRICTIONS_MASK = new QName(ROWSET.NS_URN, RESTRICTIONS_MASK, ROWSET.PREFIX);

            public static final String ROLES = "ROLES";
            public static final QName QN_ROLES = new QName(ROWSET.NS_URN, ROLES, ROWSET.PREFIX);

            public static final String DATE_MODIFIED = "DATE_MODIFIED";
            public static final QName QN_DATE_MODIFIED = new QName(ROWSET.NS_URN, DATE_MODIFIED, ROWSET.PREFIX);

            public static final String COMPATIBILITY_LEVEL = "COMPATIBILITY_LEVEL";
            public static final QName QN_COMPATIBILITY_LEVEL = new QName(ROWSET.NS_URN, COMPATIBILITY_LEVEL, ROWSET.PREFIX);

            public static final String TYPE = "TYPE";
            public static final QName QN_TYPE = new QName(ROWSET.NS_URN, TYPE, ROWSET.PREFIX);

            public static final String VERSION = "VERSION";
            public static final QName QN_VERSION = new QName(ROWSET.NS_URN, VERSION, ROWSET.PREFIX);

            public static final String DATABASE_ID = "DATABASE_ID";
            public static final QName QN_DATABASE_ID = new QName(ROWSET.NS_URN, DATABASE_ID, ROWSET.PREFIX);

            public static final String DATE_QUERIED = "DATE_QUERIED";
            public static final QName QN_DATE_QUERIED = new QName(ROWSET.NS_URN, DATE_QUERIED, ROWSET.PREFIX);

            public static final String CURRENTLY_USED = "CURRENTLY_USED";
            public static final QName QN_CURRENTLY_USED = new QName(ROWSET.NS_URN, CURRENTLY_USED, ROWSET.PREFIX);

            public static final String POPULARITY = "POPULARITY";
            public static final QName QN_POPULARITY = new QName(ROWSET.NS_URN, POPULARITY, ROWSET.PREFIX);

            public static final String WEIGHTEDPOPULARITY = "WEIGHTEDPOPULARITY";
            public static final QName QN_WEIGHTEDPOPULARITY = new QName(ROWSET.NS_URN, WEIGHTEDPOPULARITY, ROWSET.PREFIX);

            public static final String CLIENTCACHEREFRESHPOLICY = "CLIENTCACHEREFRESHPOLICY";
            public static final QName QN_CLIENTCACHEREFRESHPOLICY = new QName(ROWSET.NS_URN, CLIENTCACHEREFRESHPOLICY, ROWSET.PREFIX);

            public static final String DATA_SOURCE_NAME = "DataSourceName";
            public static final QName QN_DATA_SOURCE_NAME = new QName(ROWSET.NS_URN, DATA_SOURCE_NAME, ROWSET.PREFIX);

            public static final String DATA_SOURCE_DESCRIPTION = "DataSourceDescription";
            public static final QName QN_DATA_SOURCE_DESCRIPTION = new QName(ROWSET.NS_URN, DATA_SOURCE_DESCRIPTION, ROWSET.PREFIX);

            public static final String URL = "URL";
            public static final QName QN_URL = new QName(ROWSET.NS_URN, URL, ROWSET.PREFIX);

            public static final String DATA_SOURCE_INFO = "DataSourceInfo";
            public static final QName QN_DATA_SOURCE_INFO = new QName(ROWSET.NS_URN, DATA_SOURCE_INFO, ROWSET.PREFIX);

            public static final String PROVIDER_NAME = "ProviderName";
            public static final QName QN_PROVIDER_NAME = new QName(ROWSET.NS_URN, PROVIDER_NAME, ROWSET.PREFIX);

            public static final String PROVIDER_TYPE = "ProviderType";
            public static final QName QN_PROVIDER_TYPE = new QName(ROWSET.NS_URN, PROVIDER_TYPE, ROWSET.PREFIX);

            public static final String AUTHENTICATION_MODE = "AuthenticationMode";
            public static final QName QN_AUTHENTICATION_MODE = new QName(ROWSET.NS_URN, AUTHENTICATION_MODE, ROWSET.PREFIX);

            public static final String META_DATA = "MetaData";
            public static final QName QN_META_DATA = new QName(ROWSET.NS_URN, META_DATA, ROWSET.PREFIX);

            public static final String SCHEMA_OWNER = "SCHEMA_OWNER";
            public static final QName QN_SCHEMA_OWNER = new QName(ROWSET.NS_URN, SCHEMA_OWNER, ROWSET.PREFIX);

            public static final String TABLE_CATALOG = "TABLE_CATALOG";
            public static final QName QN_TABLE_CATALOG = new QName(ROWSET.NS_URN, TABLE_CATALOG, ROWSET.PREFIX);

            public static final String TABLE_SCHEMA = "TABLE_SCHEMA";
            public static final QName QN_TABLE_SCHEMA = new QName(ROWSET.NS_URN, TABLE_SCHEMA, ROWSET.PREFIX);

            public static final String TABLE_NAME = "TABLE_NAME";
            public static final QName QN_TABLE_NAME = new QName(ROWSET.NS_URN, TABLE_NAME, ROWSET.PREFIX);

            public static final String TABLE_TYPE = "TABLE_TYPE";
            public static final QName QN_TABLE_TYPE = new QName(ROWSET.NS_URN, TABLE_TYPE, ROWSET.PREFIX);

            public static final String TABLE_GUID = "TABLE_GUID";
            public static final QName QN_TABLE_GUID = new QName(ROWSET.NS_URN, TABLE_GUID, ROWSET.PREFIX);

            public static final String TABLE_PROP_ID = "TABLE_PROP_ID";
            public static final QName QN_TABLE_PROP_ID = new QName(ROWSET.NS_URN, TABLE_PROP_ID, ROWSET.PREFIX);

            public static final String DATE_CREATED = "DATE_CREATED";
            public static final QName QN_DATE_CREATED = new QName(ROWSET.NS_URN, DATE_CREATED, ROWSET.PREFIX);

            public static final String LITERAL_NAME = "LiteralName";
            public static final QName QN_LITERAL_NAME = new QName(ROWSET.NS_URN, LITERAL_NAME, ROWSET.PREFIX);

            public static final String LITERAL_VALUE = "LiteralValue";
            public static final QName QN_LITERAL_VALUE = new QName(ROWSET.NS_URN, LITERAL_VALUE, ROWSET.PREFIX);

            public static final String LITERAL_INVALID_CHARS = "LiteralInvalidChars";
            public static final QName QN_LITERAL_INVALID_CHARS = new QName(ROWSET.NS_URN, LITERAL_INVALID_CHARS, ROWSET.PREFIX);

            public static final String LITERAL_INVALID_STARTING_CHARS = "LiteralInvalidStartingChars";
            public static final QName QN_LITERAL_INVALID_STARTING_CHARS = new QName(ROWSET.NS_URN, LITERAL_INVALID_STARTING_CHARS, ROWSET.PREFIX);

            public static final String LITERAL_MAX_LENGTH = "LiteralMaxLength";
            public static final QName QN_LITERAL_MAX_LENGTH = new QName(ROWSET.NS_URN, LITERAL_MAX_LENGTH, ROWSET.PREFIX);

            public static final String LITERAL_NAME_ENUM_VALUE = "LiteralNameEnumValue";
            public static final QName QN_LITERAL_NAME_ENUM_VALUE = new QName(ROWSET.NS_URN, LITERAL_NAME_ENUM_VALUE, ROWSET.PREFIX);

            public static final String KEYWORD = "Keyword";
            public static final QName QN_KEYWORD = new QName(ROWSET.NS_URN, KEYWORD, ROWSET.PREFIX);

            public static final String COLUMN_NAME = "COLUMN_NAME";
            public static final QName QN_COLUMN_NAME = new QName(ROWSET.NS_URN, COLUMN_NAME, ROWSET.PREFIX);

            public static final String COLUMN_GUID = "COLUMN_GUID";
            public static final QName QN_COLUMN_GUID = new QName(ROWSET.NS_URN, COLUMN_GUID, ROWSET.PREFIX);

            public static final String COLUMN_PROPID = "COLUMN_PROPID";
            public static final QName QN_COLUMN_PROPID = new QName(ROWSET.NS_URN, COLUMN_PROPID, ROWSET.PREFIX);

            public static final String ORDINAL_POSITION = "ORDINAL_POSITION";
            public static final QName QN_ORDINAL_POSITION = new QName(ROWSET.NS_URN, ORDINAL_POSITION, ROWSET.PREFIX);

            public static final String COLUMN_HAS_DEFAULT = "COLUMN_HAS_DEFAULT";
            public static final QName QN_COLUMN_HAS_DEFAULT = new QName(ROWSET.NS_URN, COLUMN_HAS_DEFAULT, ROWSET.PREFIX);

            public static final String COLUMN_DEFAULT = "COLUMN_DEFAULT";
            public static final QName QN_COLUMN_DEFAULT = new QName(ROWSET.NS_URN, COLUMN_DEFAULT, ROWSET.PREFIX);

            public static final String COLUMN_FLAG = "COLUMN_FLAG";
            public static final QName QN_COLUMN_FLAG = new QName(ROWSET.NS_URN, COLUMN_FLAG, ROWSET.PREFIX);

            public static final String IS_NULLABLE = "IS_NULLABLE";
            public static final QName QN_IS_NULLABLE = new QName(ROWSET.NS_URN, IS_NULLABLE, ROWSET.PREFIX);

            public static final String DATA_TYPE = "DATA_TYPE";
            public static final QName QN_DATA_TYPE = new QName(ROWSET.NS_URN, DATA_TYPE, ROWSET.PREFIX);

            public static final String TYPE_GUID = "TYPE_GUID";
            public static final QName QN_TYPE_GUID = new QName(ROWSET.NS_URN, TYPE_GUID, ROWSET.PREFIX);

            public static final String CHARACTER_MAXIMUM_LENGTH = "CHARACTER_MAXIMUM_LENGTH";
            public static final QName QN_CHARACTER_MAXIMUM_LENGTH = new QName(ROWSET.NS_URN, CHARACTER_MAXIMUM_LENGTH, ROWSET.PREFIX);

            public static final String CHARACTER_OCTET_LENGTH = "CHARACTER_OCTET_LENGTH";
            public static final QName QN_CHARACTER_OCTET_LENGTH = new QName(ROWSET.NS_URN, CHARACTER_OCTET_LENGTH, ROWSET.PREFIX);

            public static final String NUMERIC_PRECISION = "NUMERIC_PRECISION";
            public static final QName QN_NUMERIC_PRECISION = new QName(ROWSET.NS_URN, NUMERIC_PRECISION, ROWSET.PREFIX);

            public static final String DATETIME_PRECISION = "DATETIME_PRECISION";
            public static final QName QN_DATETIME_PRECISION = new QName(ROWSET.NS_URN, DATETIME_PRECISION, ROWSET.PREFIX);

            public static final String CHARACTER_SET_CATALOG = "CHARACTER_SET_CATALOG";
            public static final QName QN_CHARACTER_SET_CATALOG = new QName(ROWSET.NS_URN, CHARACTER_SET_CATALOG, ROWSET.PREFIX);

            public static final String CHARACTER_SET_SCHEMA = "CHARACTER_SET_SCHEMA";
            public static final QName QN_CHARACTER_SET_SCHEMA = new QName(ROWSET.NS_URN, CHARACTER_SET_SCHEMA, ROWSET.PREFIX);

            public static final String CHARACTER_SET_NAME = "CHARACTER_SET_NAME";
            public static final QName QN_CHARACTER_SET_NAME = new QName(ROWSET.NS_URN, CHARACTER_SET_NAME, ROWSET.PREFIX);

            public static final String COLLATION_CATALOG = "COLLATION_CATALOG";
            public static final QName QN_COLLATION_CATALOG = new QName(ROWSET.NS_URN, COLLATION_CATALOG, ROWSET.PREFIX);

            public static final String COLLATION_SCHEMA = "COLLATION_SCHEMA";
            public static final QName QN_COLLATION_SCHEMA = new QName(ROWSET.NS_URN, COLLATION_SCHEMA, ROWSET.PREFIX);

            public static final String COLLATION_NAME = "COLLATION_NAME";
            public static final QName QN_COLLATION_NAME = new QName(ROWSET.NS_URN, COLLATION_NAME, ROWSET.PREFIX);

            public static final String DOMAIN_CATALOG = "DOMAIN_CATALOG";
            public static final QName QN_DOMAIN_CATALOG = new QName(ROWSET.NS_URN, DOMAIN_CATALOG, ROWSET.PREFIX);

            public static final String DOMAIN_SCHEMA = "DOMAIN_SCHEMA";
            public static final QName QN_DOMAIN_SCHEMA = new QName(ROWSET.NS_URN, DOMAIN_SCHEMA, ROWSET.PREFIX);

            public static final String DOMAIN_NAME = "DOMAIN_NAME";
            public static final QName QN_DOMAIN_NAME = new QName(ROWSET.NS_URN, DOMAIN_NAME, ROWSET.PREFIX);

            public static final String COLUMN_OLAP_TYPE = "COLUMN_OLAP_TYPE";
            public static final QName QN_COLUMN_OLAP_TYPE = new QName(ROWSET.NS_URN, COLUMN_OLAP_TYPE, ROWSET.PREFIX);

            public static final String BOOKMARKS = "BOOKMARKS";
            public static final QName QN_BOOKMARKS = new QName(ROWSET.NS_URN, BOOKMARKS, ROWSET.PREFIX);

            public static final String BOOKMARK_TYPE = "BOOKMARK_TYPE";
            public static final QName QN_BOOKMARK_TYPE = new QName(ROWSET.NS_URN, BOOKMARK_TYPE, ROWSET.PREFIX);

            public static final String BOOKMARK_DATA_TYPE = "BOOKMARK_DATA_TYPE";
            public static final QName QN_BOOKMARK_DATA_TYPE = new QName(ROWSET.NS_URN, BOOKMARK_DATA_TYPE, ROWSET.PREFIX);

            public static final String BOOKMARK_MAXIMUM_LENGTH = "BOOKMARK_MAXIMUM_LENGTH";
            public static final QName QN_BOOKMARK_MAXIMUM_LENGTH = new QName(ROWSET.NS_URN, BOOKMARK_MAXIMUM_LENGTH, ROWSET.PREFIX);

            public static final String BOOKMARK_INFORMATION = "BOOKMARK_INFORMATION";
            public static final QName QN_BOOKMARK_INFORMATION = new QName(ROWSET.NS_URN, BOOKMARK_INFORMATION, ROWSET.PREFIX);

            public static final String TABLE_VERSION = "TABLE_VERSION";
            public static final QName QN_TABLE_VERSION = new QName(ROWSET.NS_URN, TABLE_VERSION, ROWSET.PREFIX);

            public static final String CARDINALITY = "CARDINALITY";
            public static final QName QN_CARDINALITY = new QName(ROWSET.NS_URN, CARDINALITY, ROWSET.PREFIX);

            public static final String DIMENSION_UNIQUE_NAME = "DIMENSION_UNIQUE_NAME";
            public static final QName QN_DIMENSION_UNIQUE_NAME = new QName(ROWSET.NS_URN, DIMENSION_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String HIERARCHY_NAME = "HIERARCHY_NAME";
            public static final QName QN_HIERARCHY_NAME = new QName(ROWSET.NS_URN, HIERARCHY_NAME, ROWSET.PREFIX);

            public static final String HIERARCHY_UNIQUE_NAME = "HIERARCHY_UNIQUE_NAME";
            public static final QName QN_HIERARCHY_UNIQUE_NAME = new QName(ROWSET.NS_URN, HIERARCHY_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String HIERARCHY_GUID = "HIERARCHY_GUID";
            public static final QName QN_HIERARCHY_GUID = new QName(ROWSET.NS_URN, HIERARCHY_GUID, ROWSET.PREFIX);

            public static final String HIERARCHY_CAPTION = "HIERARCHY_CAPTION";
            public static final QName QN_HIERARCHY_CAPTION = new QName(ROWSET.NS_URN, HIERARCHY_CAPTION, ROWSET.PREFIX);

            public static final String DIMENSION_TYPE = "DIMENSION_TYPE";
            public static final QName QN_DIMENSION_TYPE = new QName(ROWSET.NS_URN, DIMENSION_TYPE, ROWSET.PREFIX);

            public static final String HIERARCHY_CARDINALITY = "HIERARCHY_CARDINALITY";
            public static final QName QN_HIERARCHY_CARDINALITY = new QName(ROWSET.NS_URN, HIERARCHY_CARDINALITY, ROWSET.PREFIX);

            public static final String DEFAULT_MEMBER = "DEFAULT_MEMBER";
            public static final QName QN_DEFAULT_MEMBER = new QName(ROWSET.NS_URN, DEFAULT_MEMBER, ROWSET.PREFIX);

            public static final String ALL_MEMBER = "ALL_MEMBER";
            public static final QName QN_ALL_MEMBER = new QName(ROWSET.NS_URN, ALL_MEMBER, ROWSET.PREFIX);

            public static final String STRUCTURE = "STRUCTURE";
            public static final QName QN_STRUCTURE = new QName(ROWSET.NS_URN, STRUCTURE, ROWSET.PREFIX);

            public static final String IS_VIRTUAL = "IS_VIRTUAL";
            public static final QName QN_IS_VIRTUAL = new QName(ROWSET.NS_URN, IS_VIRTUAL, ROWSET.PREFIX);

            public static final String IS_READWRITE = "IS_READWRITE";
            public static final QName QN_IS_READWRITE = new QName(ROWSET.NS_URN, IS_READWRITE, ROWSET.PREFIX);

            public static final String DIMENSION_UNIQUE_SETTINGS = "DIMENSION_UNIQUE_SETTINGS";
            public static final QName QN_DIMENSION_UNIQUE_SETTINGS = new QName(ROWSET.NS_URN, DIMENSION_UNIQUE_SETTINGS, ROWSET.PREFIX);

            public static final String DIMENSION_MASTER_UNIQUE_NAME = "DIMENSION_MASTER_UNIQUE_NAME";
            public static final QName QN_DIMENSION_MASTER_UNIQUE_NAME = new QName(ROWSET.NS_URN, DIMENSION_MASTER_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String DIMENSION_IS_VISIBLE = "DIMENSION_IS_VISIBLE";
            public static final QName QN_DIMENSION_IS_VISIBLE = new QName(ROWSET.NS_URN, DIMENSION_IS_VISIBLE, ROWSET.PREFIX);

            public static final String HIERARCHY_ORDINAL = "HIERARCHY_ORDINAL";
            public static final QName QN_HIERARCHY_ORDINAL = new QName(ROWSET.NS_URN, HIERARCHY_ORDINAL, ROWSET.PREFIX);

            public static final String DIMENSION_IS_SHARED = "DIMENSION_IS_SHARED";
            public static final QName QN_DIMENSION_IS_SHARED = new QName(ROWSET.NS_URN, DIMENSION_IS_SHARED, ROWSET.PREFIX);

            public static final String HIERARCHY_IS_VISIBLE = "HIERARCHY_IS_VISIBLE";
            public static final QName QN_HIERARCHY_IS_VISIBLE = new QName(ROWSET.NS_URN, HIERARCHY_IS_VISIBLE, ROWSET.PREFIX);

            public static final String HIERARCHY_ORIGIN = "HIERARCHY_ORIGIN";
            public static final QName QN_HIERARCHY_ORIGIN = new QName(ROWSET.NS_URN, HIERARCHY_ORIGIN, ROWSET.PREFIX);

            public static final String HIERARCHY_DISPLAY_FOLDER = "HIERARCHY_DISPLAY_FOLDER";
            public static final QName QN_HIERARCHY_DISPLAY_FOLDER = new QName(ROWSET.NS_URN, HIERARCHY_DISPLAY_FOLDER, ROWSET.PREFIX);

            public static final String INSTANCE_SELECTION = "INSTANCE_SELECTION";
            public static final QName QN_INSTANCE_SELECTION = new QName(ROWSET.NS_URN, INSTANCE_SELECTION, ROWSET.PREFIX);

            public static final String GROUPING_BEHAVIOR = "GROUPING_BEHAVIOR";
            public static final QName QN_GROUPING_BEHAVIOR = new QName(ROWSET.NS_URN, GROUPING_BEHAVIOR, ROWSET.PREFIX);

            public static final String STRUCTURE_TYPE = "STRUCTURE_TYPE";
            public static final QName QN_STRUCTURE_TYPE = new QName(ROWSET.NS_URN, STRUCTURE_TYPE, ROWSET.PREFIX);

            public static final String LEVEL_NAME = "LEVEL_NAME";
            public static final QName QN_LEVEL_NAME = new QName(ROWSET.NS_URN, LEVEL_NAME, ROWSET.PREFIX);

            public static final String LEVEL_UNIQUE_NAME = "LEVEL_UNIQUE_NAME";
            public static final QName QN_LEVEL_UNIQUE_NAME = new QName(ROWSET.NS_URN, LEVEL_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String LEVEL_GUID = "LEVEL_GUID";
            public static final QName QN_LEVEL_GUID = new QName(ROWSET.NS_URN, LEVEL_GUID, ROWSET.PREFIX);

            public static final String LEVEL_CAPTION = "LEVEL_CAPTION";
            public static final QName QN_LEVEL_CAPTION = new QName(ROWSET.NS_URN, LEVEL_CAPTION, ROWSET.PREFIX);

            public static final String LEVEL_NUMBER = "LEVEL_NUMBER";
            public static final QName QN_LEVEL_NUMBER = new QName(ROWSET.NS_URN, LEVEL_NUMBER, ROWSET.PREFIX);

            public static final String LEVEL_CARDINALITY = "LEVEL_CARDINALITY";
            public static final QName QN_LEVEL_CARDINALITY = new QName(ROWSET.NS_URN, LEVEL_CARDINALITY, ROWSET.PREFIX);

            public static final String LEVEL_TYPE = "LEVEL_TYPE";
            public static final QName QN_LEVEL_TYPE = new QName(ROWSET.NS_URN, LEVEL_TYPE, ROWSET.PREFIX);

            public static final String CUSTOM_ROLLUP_SETTINGS = "CUSTOM_ROLLUP_SETTINGS";
            public static final QName QN_CUSTOM_ROLLUP_SETTINGS = new QName(ROWSET.NS_URN, CUSTOM_ROLLUP_SETTINGS, ROWSET.PREFIX);

            public static final String LEVEL_UNIQUE_SETTINGS = "LEVEL_UNIQUE_SETTINGS";
            public static final QName QN_LEVEL_UNIQUE_SETTINGS = new QName(ROWSET.NS_URN, LEVEL_UNIQUE_SETTINGS, ROWSET.PREFIX);

            public static final String LEVEL_IS_VISIBLE = "LEVEL_IS_VISIBLE";
            public static final QName QN_LEVEL_IS_VISIBLE = new QName(ROWSET.NS_URN, LEVEL_IS_VISIBLE, ROWSET.PREFIX);

            public static final String LEVEL_ORIGIN = "LEVEL_ORIGIN";
            public static final QName QN_LEVEL_ORIGIN = new QName(ROWSET.NS_URN, LEVEL_ORIGIN, ROWSET.PREFIX);

            public static final String MEASURE_GROUP_DIMENSION = "MeasureGroupDimension";
            public static final QName QN_MEASURE_GROUP_DIMENSION = new QName(ROWSET.NS_URN, MEASURE_GROUP_DIMENSION, ROWSET.PREFIX);

            public static final String MEASUREGROUP_NAME = "MEASUREGROUP_NAME";
            public static final QName QN_MEASUREGROUP_NAME = new QName(ROWSET.NS_URN, MEASUREGROUP_NAME, ROWSET.PREFIX);

            public static final String MEASUREGROUP_CARDINALITY = "MEASUREGROUP_CARDINALITY";
            public static final QName QN_MEASUREGROUP_CARDINALITY = new QName(ROWSET.NS_URN, MEASUREGROUP_CARDINALITY, ROWSET.PREFIX);

            public static final String DIMENSION_CARDINALITY = "DIMENSION_CARDINALITY";
            public static final QName QN_DIMENSION_CARDINALITY = new QName(ROWSET.NS_URN, DIMENSION_CARDINALITY, ROWSET.PREFIX);

            public static final String DIMENSION_IS_FACT_DIMENSION = "DIMENSION_IS_FACT_DIMENSION";
            public static final QName QN_DIMENSION_IS_FACT_DIMENSION = new QName(ROWSET.NS_URN, DIMENSION_IS_FACT_DIMENSION, ROWSET.PREFIX);

            public static final String DIMENSION_GRANULARITY = "DIMENSION_GRANULARITY";
            public static final QName QN_DIMENSION_GRANULARITY = new QName(ROWSET.NS_URN, DIMENSION_GRANULARITY, ROWSET.PREFIX);

            public static final String IS_WRITE_ENABLED = "IS_WRITE_ENABLED";
            public static final QName QN_IS_WRITE_ENABLED = new QName(ROWSET.NS_URN, IS_WRITE_ENABLED, ROWSET.PREFIX);

            public static final String MEASUREGROUP_CAPTION = "MEASUREGROUP_CAPTION";
            public static final QName QN_MEASUREGROUP_CAPTION = new QName(ROWSET.NS_URN, MEASUREGROUP_CAPTION, ROWSET.PREFIX);

            public static final String MEASURE_NAME = "MEASURE_NAME";
            public static final QName QN_MEASURE_NAME = new QName(ROWSET.NS_URN, MEASURE_NAME, ROWSET.PREFIX);

            public static final String MEASURE_UNIQUE_NAME = "MEASURE_UNIQUE_NAME";
            public static final QName QN_MEASURE_UNIQUE_NAME = new QName(ROWSET.NS_URN, MEASURE_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String MEASURE_CAPTION = "MEASURE_CAPTION";
            public static final QName QN_MEASURE_CAPTION = new QName(ROWSET.NS_URN, MEASURE_CAPTION, ROWSET.PREFIX);

            public static final String MEASURE_GUID = "MEASURE_GUID";
            public static final QName QN_MEASURE_GUID = new QName(ROWSET.NS_URN, MEASURE_GUID, ROWSET.PREFIX);

            public static final String MEASURE_AGGREGATOR = "MEASURE_AGGREGATOR";
            public static final QName QN_MEASURE_AGGREGATOR = new QName(ROWSET.NS_URN, MEASURE_AGGREGATOR, ROWSET.PREFIX);

            public static final String NUMERIC_SCALE = "NUMERIC_SCALE";
            public static final QName QN_NUMERIC_SCALE = new QName(ROWSET.NS_URN, NUMERIC_SCALE, ROWSET.PREFIX);

            public static final String MEASURE_UNITS = "MEASURE_UNITS";
            public static final QName QN_MEASURE_UNITS = new QName(ROWSET.NS_URN, MEASURE_UNITS, ROWSET.PREFIX);

            public static final String MEASURE_IS_VISIBLE = "MEASURE_IS_VISIBLE";
            public static final QName QN_MEASURE_IS_VISIBLE = new QName(ROWSET.NS_URN, MEASURE_IS_VISIBLE, ROWSET.PREFIX);

            public static final String LEVELS_LIST = "LEVELS_LIST";
            public static final QName QN_LEVELS_LIST = new QName(ROWSET.NS_URN, LEVELS_LIST, ROWSET.PREFIX);

            public static final String EXPRESSION = "EXPRESSION";
            public static final QName QN_EXPRESSION = new QName(ROWSET.NS_URN, EXPRESSION, ROWSET.PREFIX);

            public static final String MEASURE_NAME_SQL_COLUMN_NAME = "MEASURE_NAME_SQL_COLUMN_NAME";
            public static final QName QN_MEASURE_NAME_SQL_COLUMN_NAME = new QName(ROWSET.NS_URN, MEASURE_NAME_SQL_COLUMN_NAME, ROWSET.PREFIX);

            public static final String MEASURE_UNQUALIFIED_CAPTION = "MEASURE_UNQUALIFIED_CAPTION";
            public static final QName QN_MEASURE_UNQUALIFIED_CAPTION = new QName(ROWSET.NS_URN, MEASURE_UNQUALIFIED_CAPTION, ROWSET.PREFIX);

            public static final String MEASURE_DISPLAY_FOLDER = "MEASURE_DISPLAY_FOLDER";
            public static final QName QN_MEASURE_DISPLAY_FOLDER = new QName(ROWSET.NS_URN, MEASURE_DISPLAY_FOLDER, ROWSET.PREFIX);

            public static final String DEFAULT_FORMAT_STRING = "DEFAULT_FORMAT_STRING";
            public static final QName QN_DEFAULT_FORMAT_STRING = new QName(ROWSET.NS_URN, DEFAULT_FORMAT_STRING, ROWSET.PREFIX);

            public static final String CUBE_SOURCE = "CUBE_SOURCE";
            public static final QName QN_CUBE_SOURCE = new QName(ROWSET.NS_URN, CUBE_SOURCE, ROWSET.PREFIX);

            public static final String MEASURE_VISIBILITY = "MEASURE_VISIBILITY";
            public static final QName QN_MEASURE_VISIBILITY = new QName(ROWSET.NS_URN, MEASURE_VISIBILITY, ROWSET.PREFIX);

            public static final String MEMBER_ORDINAL = "MEMBER_ORDINAL";
            public static final QName QN_MEMBER_ORDINAL = new QName(ROWSET.NS_URN, MEMBER_ORDINAL, ROWSET.PREFIX);

            public static final String MEMBER_NAME = "MEMBER_NAME";
            public static final QName QN_MEMBER_NAME = new QName(ROWSET.NS_URN, MEMBER_NAME, ROWSET.PREFIX);

            public static final String MEMBER_UNIQUE_NAME = "MEMBER_UNIQUE_NAME";
            public static final QName QN_MEMBER_UNIQUE_NAME = new QName(ROWSET.NS_URN, MEMBER_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String MEMBER_TYPE = "MEMBER_TYPE";
            public static final QName QN_MEMBER_TYPE = new QName(ROWSET.NS_URN, MEMBER_TYPE, ROWSET.PREFIX);

            public static final String MEMBER_GUID = "MEMBER_GUID";
            public static final QName QN_MEMBER_GUID = new QName(ROWSET.NS_URN, MEMBER_GUID, ROWSET.PREFIX);

            public static final String MEMBER_CAPTION = "MEMBER_CAPTION";
            public static final QName QN_MEMBER_CAPTION = new QName(ROWSET.NS_URN, MEMBER_CAPTION, ROWSET.PREFIX);

            public static final String CHILDREN_CARDINALITY = "CHILDREN_CARDINALITY";
            public static final QName QN_CHILDREN_CARDINALITY = new QName(ROWSET.NS_URN, CHILDREN_CARDINALITY, ROWSET.PREFIX);

            public static final String PARENT_LEVEL = "PARENT_LEVEL";
            public static final QName QN_PARENT_LEVEL = new QName(ROWSET.NS_URN, PARENT_LEVEL, ROWSET.PREFIX);

            public static final String PARENT_UNIQUE_NAME = "PARENT_UNIQUE_NAME";
            public static final QName QN_PARENT_UNIQUE_NAME = new QName(ROWSET.NS_URN, PARENT_UNIQUE_NAME, ROWSET.PREFIX);

            public static final String PARENT_COUNT = "PARENT_COUNT";
            public static final QName QN_PARENT_COUNT = new QName(ROWSET.NS_URN, PARENT_COUNT, ROWSET.PREFIX);

            public static final String MEMBER_KEY = "MEMBER_KEY";
            public static final QName QN_MEMBER_KEY = new QName(ROWSET.NS_URN, MEMBER_KEY, ROWSET.PREFIX);

            public static final String IS_PLACEHOLDERMEMBER = "IS_PLACEHOLDERMEMBER";
            public static final QName QN_IS_PLACEHOLDERMEMBER = new QName(ROWSET.NS_URN, IS_PLACEHOLDERMEMBER, ROWSET.PREFIX);

            public static final String IS_DATAMEMBER = "IS_DATAMEMBER";
            public static final QName QN_IS_DATAMEMBER = new QName(ROWSET.NS_URN, IS_DATAMEMBER, ROWSET.PREFIX);

            public static final String SCOPE = "SCOPE";
            public static final QName QN_SCOPE = new QName(ROWSET.NS_URN, SCOPE, ROWSET.PREFIX);

            public static final String PROPERTY_TYPE = "PROPERTY_TYPE";
            public static final QName QN_PROPERTY_TYPE = new QName(ROWSET.NS_URN, PROPERTY_TYPE, ROWSET.PREFIX);

            public static final String PROPERTY_TYPE_LC = "PropertyType";
            public static final QName QN_PROPERTY_TYPE_LC = new QName(ROWSET.NS_URN, PROPERTY_TYPE_LC, ROWSET.PREFIX);

            public static final String PROPERTY_NAME = "PROPERTY_NAME";
            public static final QName QN_PROPERTY_NAME = new QName(ROWSET.NS_URN, PROPERTY_NAME, ROWSET.PREFIX);

            public static final String PROPERTY_NAME_LC = "PropertyName";
            public static final QName QN_PROPERTY_NAME_LC = new QName(ROWSET.NS_URN, PROPERTY_NAME_LC, ROWSET.PREFIX);

            public static final String PROPERTY_CAPTION = "PROPERTY_CAPTION";
            public static final QName QN_PROPERTY_CAPTION = new QName(ROWSET.NS_URN, PROPERTY_CAPTION, ROWSET.PREFIX);

            public static final String PROPERTY_CONTENT_TYPE = "PROPERTY_CONTENT_TYPE";
            public static final QName QN_PROPERTY_CONTENT_TYPE = new QName(ROWSET.NS_URN, PROPERTY_CONTENT_TYPE, ROWSET.PREFIX);

            public static final String SQL_COLUMN_NAME = "SQL_COLUMN_NAME";
            public static final QName QN_SQL_COLUMN_NAME = new QName(ROWSET.NS_URN, SQL_COLUMN_NAME, ROWSET.PREFIX);

            public static final String PROPERTY_ORIGIN = "PROPERTY_ORIGIN";
            public static final QName QN_PROPERTY_ORIGIN = new QName(ROWSET.NS_URN, PROPERTY_ORIGIN, ROWSET.PREFIX);

            public static final String PROPERTY_VISIBILITY = "PROPERTY_VISIBILITY";
            public static final QName QN_PROPERTY_VISIBILITY = new QName(ROWSET.NS_URN, PROPERTY_VISIBILITY, ROWSET.PREFIX);

            public static final String SET_NAME = "SET_NAME";
            public static final QName QN_SET_NAME = new QName(ROWSET.NS_URN, SET_NAME, ROWSET.PREFIX);

            public static final String DIMENSIONS = "DIMENSIONS";
            public static final QName QN_DIMENSIONS = new QName(ROWSET.NS_URN, DIMENSIONS, ROWSET.PREFIX);

            public static final String SET_CAPTION = "SET_CAPTION";
            public static final QName QN_SET_CAPTION = new QName(ROWSET.NS_URN, SET_CAPTION, ROWSET.PREFIX);

            public static final String SET_DISPLAY_FOLDER = "SET_DISPLAY_FOLDER";
            public static final QName QN_SET_DISPLAY_FOLDER = new QName(ROWSET.NS_URN, SET_DISPLAY_FOLDER, ROWSET.PREFIX);

            public static final String MEMBER_DISP_INFO = "MemberDispInfo";
            public static final QName QN_MEMBER_DISP_INFO = new QName(ROWSET.NS_URN, MEMBER_DISP_INFO, ROWSET.PREFIX);

            public static final String PROPERTY_DESCRIPTION = "PropertyDescription";
            public static final QName QN_PROPERTY_DESCRIPTION = new QName(ROWSET.NS_URN, PROPERTY_DESCRIPTION, ROWSET.PREFIX);

            public static final String PROPERTY_ACCESS_TYPE = "PropertyAccessType";
            public static final QName QN_PROPERTY_ACCESS_TYPE = new QName(ROWSET.NS_URN, PROPERTY_ACCESS_TYPE, ROWSET.PREFIX);

            public static final String IS_REQUIRED = "IsRequired";
            public static final QName QN_IS_REQUIRED = new QName(ROWSET.NS_URN, IS_REQUIRED, ROWSET.PREFIX);

            public static final String VALUE = "Value";
            public static final QName QN_VALUE = new QName(ROWSET.NS_URN, VALUE, ROWSET.PREFIX);

            public static final String TYPE_NAME = "TYPE_NAME";
            public static final QName QN_TYPE_NAME = new QName(ROWSET.NS_URN, TYPE_NAME, ROWSET.PREFIX);

            public static final String COLUMN_SIZE = "COLUMN_SIZE";
            public static final QName QN_COLUMN_SIZE = new QName(ROWSET.NS_URN, COLUMN_SIZE, ROWSET.PREFIX);

            public static final String LITERAL_PREFIX = "LITERAL_PREFIX";
            public static final QName QN_LITERAL_PREFIX = new QName(ROWSET.NS_URN, LITERAL_PREFIX, ROWSET.PREFIX);

            public static final String LITERAL_SUFFIX = "LITERAL_SUFFIX";
            public static final QName QN_LITERAL_SUFFIX = new QName(ROWSET.NS_URN, LITERAL_SUFFIX, ROWSET.PREFIX);

            public static final String CREATE_PARAMS = "CREATE_PARAMS";
            public static final QName QN_CREATE_PARAMS = new QName(ROWSET.NS_URN, CREATE_PARAMS, ROWSET.PREFIX);

            public static final String CASE_SENSITIVE = "CASE_SENSITIVE";
            public static final QName QN_CASE_SENSITIVE = new QName(ROWSET.NS_URN, CASE_SENSITIVE, ROWSET.PREFIX);

            public static final String SEARCHABLE = "SEARCHABLE";
            public static final QName QN_SEARCHABLE = new QName(ROWSET.NS_URN, SEARCHABLE, ROWSET.PREFIX);

            public static final String UNSIGNED_ATTRIBUTE = "UNSIGNED_ATTRIBUTE";
            public static final QName QN_UNSIGNED_ATTRIBUTE = new QName(ROWSET.NS_URN, UNSIGNED_ATTRIBUTE, ROWSET.PREFIX);

            public static final String FIXED_PREC_SCALE = "FIXED_PREC_SCALE";
            public static final QName QN_FIXED_PREC_SCALE = new QName(ROWSET.NS_URN, FIXED_PREC_SCALE, ROWSET.PREFIX);

            public static final String AUTO_UNIQUE_VALUE = "AUTO_UNIQUE_VALUE";
            public static final QName QN_AUTO_UNIQUE_VALUE = new QName(ROWSET.NS_URN, AUTO_UNIQUE_VALUE, ROWSET.PREFIX);

            public static final String LOCAL_TYPE_NAME = "LOCAL_TYPE_NAME";
            public static final QName QN_LOCAL_TYPE_NAME = new QName(ROWSET.NS_URN, LOCAL_TYPE_NAME, ROWSET.PREFIX);

            public static final String MINIMUM_SCALE = "MINIMUM_SCALE";
            public static final QName QN_MINIMUM_SCALE = new QName(ROWSET.NS_URN, MINIMUM_SCALE, ROWSET.PREFIX);

            public static final String MAXIMUM_SCALE = "MAXIMUM_SCALE";
            public static final QName QN_MAXIMUM_SCALE = new QName(ROWSET.NS_URN, MAXIMUM_SCALE, ROWSET.PREFIX);

            public static final String GUID = "GUID";
            public static final QName QN_GUID = new QName(ROWSET.NS_URN, GUID, ROWSET.PREFIX);

            public static final String TYPE_LIB = "TYPE_LIB";
            public static final QName QN_TYPE_LIB = new QName(ROWSET.NS_URN, TYPE_LIB, ROWSET.PREFIX);

            public static final String IS_LONG = "IS_LONG";
            public static final QName QN_IS_LONG = new QName(ROWSET.NS_URN, IS_LONG, ROWSET.PREFIX);

            public static final String BEST_MATCH = "BEST_MATCH";
            public static final QName QN_BEST_MATCH = new QName(ROWSET.NS_URN, BEST_MATCH, ROWSET.PREFIX);

            public static final String IS_FIXEDLENGTH = "IS_FIXEDLENGTH";
            public static final QName QN_IS_FIXEDLENGTH = new QName(ROWSET.NS_URN, IS_FIXEDLENGTH, ROWSET.PREFIX);

            public static final String KPI_NAME = "KPI_NAME";
            public static final QName QN_KPI_NAME = new QName(ROWSET.NS_URN, KPI_NAME, ROWSET.PREFIX);

            public static final String KPI_CAPTION = "KPI_CAPTION";
            public static final QName QN_KPI_CAPTION = new QName(ROWSET.NS_URN, KPI_CAPTION, ROWSET.PREFIX);

            public static final String KPI_DESCRIPTION = "KPI_DESCRIPTION";
            public static final QName QN_KPI_DESCRIPTION = new QName(ROWSET.NS_URN, KPI_DESCRIPTION, ROWSET.PREFIX);

            public static final String KPI_DISPLAY_FOLDER = "KPI_DISPLAY_FOLDER";
            public static final QName QN_KPI_DISPLAY_FOLDER = new QName(ROWSET.NS_URN, KPI_DISPLAY_FOLDER, ROWSET.PREFIX);

            public static final String KPI_VALUE = "KPI_VALUE";
            public static final QName QN_KPI_VALUE = new QName(ROWSET.NS_URN, KPI_VALUE, ROWSET.PREFIX);

            public static final String KPI_GOAL = "KPI_GOAL";
            public static final QName QN_KPI_GOAL = new QName(ROWSET.NS_URN, KPI_GOAL, ROWSET.PREFIX);

            public static final String KPI_STATUS = "KPI_STATUS";
            public static final QName QN_KPI_STATUS = new QName(ROWSET.NS_URN, KPI_STATUS, ROWSET.PREFIX);

            public static final String KPI_TREND = "KPI_TREND";
            public static final QName QN_KPI_TREND = new QName(ROWSET.NS_URN, KPI_TREND, ROWSET.PREFIX);

            public static final String KPI_STATUS_GRAPHIC = "KPI_STATUS_GRAPHIC";
            public static final QName QN_KPI_STATUS_GRAPHIC = new QName(ROWSET.NS_URN, KPI_STATUS_GRAPHIC, ROWSET.PREFIX);

            public static final String KPI_TREND_GRAPHIC = "KPI_TREND_GRAPHIC";
            public static final QName QN_KPI_TREND_GRAPHIC = new QName(ROWSET.NS_URN, KPI_TREND_GRAPHIC, ROWSET.PREFIX);

            public static final String KPI_WEIGHT = "KPI_WEIGHT";
            public static final QName QN_KPI_WEIGHT = new QName(ROWSET.NS_URN, KPI_WEIGHT, ROWSET.PREFIX);

            public static final String KPI_CURRENT_TIME_MEMBER = "KPI_CURRENT_TIME_MEMBER";
            public static final QName QN_KPI_CURRENT_TIME_MEMBER = new QName(ROWSET.NS_URN, KPI_CURRENT_TIME_MEMBER, ROWSET.PREFIX);

            public static final String KPI_PARENT_KPI_NAME = "KPI_PARENT_KPI_NAME";
            public static final QName QN_KPI_PARENT_KPI_NAME = new QName(ROWSET.NS_URN, KPI_PARENT_KPI_NAME, ROWSET.PREFIX);

            public static final String ANNOTATIONS = "ANNOTATIONS";
            public static final QName QN_ANNOTATIONS = new QName(ROWSET.NS_URN, ANNOTATIONS, ROWSET.PREFIX);

            public static final String CUBE_TYPE = "CUBE_TYPE";
            public static final QName QN_CUBE_TYPE = new QName(ROWSET.NS_URN, CUBE_TYPE, ROWSET.PREFIX);

            public static final String CUBE_GUID = "CUBE_GUID";
            public static final QName QN_CUBE_GUID = new QName(ROWSET.NS_URN, CUBE_GUID, ROWSET.PREFIX);

            public static final String CREATED_ON = "CREATED_ON";
            public static final QName QN_CREATED_ON = new QName(ROWSET.NS_URN, CREATED_ON, ROWSET.PREFIX);

            public static final String LAST_SCHEMA_UPDATE = "LAST_SCHEMA_UPDATE";
            public static final QName QN_LAST_SCHEMA_UPDATE = new QName(ROWSET.NS_URN, LAST_SCHEMA_UPDATE, ROWSET.PREFIX);

            public static final String SCHEMA_UPDATED_BY = "SCHEMA_UPDATED_BY";
            public static final QName QN_SCHEMA_UPDATED_BY = new QName(ROWSET.NS_URN, SCHEMA_UPDATED_BY, ROWSET.PREFIX);

            public static final String LAST_DATA_UPDATE = "LAST_DATA_UPDATE";
            public static final QName QN_LAST_DATA_UPDATE = new QName(ROWSET.NS_URN, LAST_DATA_UPDATE, ROWSET.PREFIX);

            public static final String DATA_UPDATED_BY = "DATA_UPDATED_BY";
            public static final QName QN_DATA_UPDATED_BY = new QName(ROWSET.NS_URN, DATA_UPDATED_BY, ROWSET.PREFIX);

            public static final String IS_DRILLTHROUGH_ENABLED = "IS_DRILLTHROUGH_ENABLED";
            public static final QName QN_IS_DRILLTHROUGH_ENABLED = new QName(ROWSET.NS_URN, IS_DRILLTHROUGH_ENABLED, ROWSET.PREFIX);

            public static final String IS_LINKABLE = "IS_LINKABLE";
            public static final QName QN_IS_LINKABLE = new QName(ROWSET.NS_URN, IS_LINKABLE, ROWSET.PREFIX);

            public static final String IS_SQL_ENABLED = "IS_SQL_ENABLED";
            public static final QName QN_IS_SQL_ENABLED = new QName(ROWSET.NS_URN, IS_SQL_ENABLED, ROWSET.PREFIX);

            public static final String CUBE_CAPTION = "CUBE_CAPTION";
            public static final QName QN_CUBE_CAPTION = new QName(ROWSET.NS_URN, CUBE_CAPTION, ROWSET.PREFIX);

            public static final String BASE_CUBE_NAME = "BASE_CUBE_NAME";
            public static final QName QN_BASE_CUBE_NAME = new QName(ROWSET.NS_URN, BASE_CUBE_NAME, ROWSET.PREFIX);

            public static final String PREFERRED_QUERY_PATTERNS = "PREFERRED_QUERY_PATTERNS";
            public static final QName QN_PREFERRED_QUERY_PATTERNS = new QName(ROWSET.NS_URN, PREFERRED_QUERY_PATTERNS, ROWSET.PREFIX);

            public static final String DIMENSION_NAME = "DIMENSION_NAME";
            public static final QName QN_DIMENSION_NAME = new QName(ROWSET.NS_URN, DIMENSION_NAME, ROWSET.PREFIX);

            public static final String DIMENSION_GUID = "DIMENSION_GUID";
            public static final QName QN_DIMENSION_GUID = new QName(ROWSET.NS_URN, DIMENSION_GUID, ROWSET.PREFIX);

            public static final String DIMENSION_CAPTION = "DIMENSION_CAPTION";
            public static final QName QN_DIMENSION_CAPTION = new QName(ROWSET.NS_URN, DIMENSION_CAPTION, ROWSET.PREFIX);

            public static final String DIMENSION_ORDINAL = "DIMENSION_ORDINAL";
            public static final QName QN_DIMENSION_ORDINAL = new QName(ROWSET.NS_URN, DIMENSION_ORDINAL, ROWSET.PREFIX);

            public static final String DEFAULT_HIERARCHY = "DEFAULT_HIERARCHY";
            public static final QName QN_DEFAULT_HIERARCHY = new QName(ROWSET.NS_URN, DEFAULT_HIERARCHY, ROWSET.PREFIX);

            public static final String DIMENSION_MASTER_NAME = "DIMENSION_MASTER_NAME";
            public static final QName QN_DIMENSION_MASTER_NAME = new QName(ROWSET.NS_URN, DIMENSION_MASTER_NAME, ROWSET.PREFIX);

            public static final String FUNCTION_NAME = "FUNCTION_NAME";
            public static final QName QN_FUNCTION_NAME = new QName(ROWSET.NS_URN, FUNCTION_NAME, ROWSET.PREFIX);

            public static final String PARAMETER_LIST = "PARAMETER_LIST";
            public static final QName QN_PARAMETER_LIST = new QName(ROWSET.NS_URN, PARAMETER_LIST, ROWSET.PREFIX);

            public static final String RETURN_TYPE = "RETURN_TYPE";
            public static final QName QN_RETURN_TYPE = new QName(ROWSET.NS_URN, RETURN_TYPE, ROWSET.PREFIX);

            public static final String ORIGIN = "ORIGIN";
            public static final QName QN_ORIGIN = new QName(ROWSET.NS_URN, ORIGIN, ROWSET.PREFIX);

            public static final String INTERFACE_NAME = "INTERFACE_NAME";
            public static final QName QN_INTERFACE_NAME = new QName(ROWSET.NS_URN, INTERFACE_NAME, ROWSET.PREFIX);

            public static final String LIBRARY_NAME = "LIBRARY_NAME";
            public static final QName QN_LIBRARY_NAME = new QName(ROWSET.NS_URN, LIBRARY_NAME, ROWSET.PREFIX);

            public static final String DLL_NAME = "DLL_NAME";
            public static final QName QN_DLL_NAME = new QName(ROWSET.NS_URN, DLL_NAME, ROWSET.PREFIX);

            public static final String HELP_FILE = "HELP_FILE";
            public static final QName QN_HELP_FILE = new QName(ROWSET.NS_URN, HELP_FILE, ROWSET.PREFIX);

            public static final String HELP_CONTEXT = "HELP_CONTEXT";
            public static final QName QN_HELP_CONTEXT = new QName(ROWSET.NS_URN, HELP_CONTEXT, ROWSET.PREFIX);

            public static final String OBJECT = "OBJECT";
            public static final QName QN_OBJECT = new QName(ROWSET.NS_URN, OBJECT, ROWSET.PREFIX);

            public static final String CAPTION = "CAPTION";
            public static final QName QN_CAPTION = new QName(ROWSET.NS_URN, CAPTION, ROWSET.PREFIX);

            public static final String DIRECTQUERY_PUSHABLE = "DIRECTQUERY_PUSHABLE";
            public static final QName QN_DIRECTQUERY_PUSHABLE = new QName(ROWSET.NS_URN, DIRECTQUERY_PUSHABLE, ROWSET.PREFIX);

            public static final String NAME = "NAME";
            public static final QName QN_NAME = new QName(ROWSET.NS_URN, NAME, ROWSET.PREFIX);

            public static final String OPTIONAL = "OPTIONAL";
            public static final QName QN_OPTIONAL = new QName(ROWSET.NS_URN, OPTIONAL, ROWSET.PREFIX);

            public static final String REPEATABLE = "REPEATABLE";
            public static final QName QN_REPEATABLE = new QName(ROWSET.NS_URN, REPEATABLE, ROWSET.PREFIX);

            public static final String REPEATGROUP = "REPEATGROUP";
            public static final QName QN_REPEATGROUP = new QName(ROWSET.NS_URN, REPEATGROUP, ROWSET.PREFIX);

        }

	}
	/////

	static class MDDATASET {
		public static final String PREFIX = "mddataset";
		public static final String NS_URN = "urn:schemas-microsoft-com:xml-analysis:mddataset";
		public static final QName QN_CELL_INFO = new QName(MDDATASET.NS_URN, "CellInfo", MDDATASET.PREFIX);
		public static final QName QN_OLAP_INFO = new QName(MDDATASET.NS_URN, "OlapInfo", MDDATASET.PREFIX);
		public static final QName QN_ROOT = new QName(MDDATASET.NS_URN, "root", MDDATASET.PREFIX);
        public static final QName QN_CUBE_NAME = new QName(MDDATASET.NS_URN, "CubeName", MDDATASET.PREFIX);
        public static final QName QN_SIZE = new QName(MDDATASET.NS_URN, "Size", MDDATASET.PREFIX);
	}

	static class ENGINE {
		public static final String PREFIX = "engine";
		public static final String NS_URN = "http://schemas.microsoft.com/analysisservices/2003/engine";
        public static final QName QN_LAST_DATA_UPDATE = new QName(MDDATASET.NS_URN, "LastDataUpdate", MDDATASET.PREFIX);
        public static final QName QN_LAST_SCHEMA_UPDATE = new QName(MDDATASET.NS_URN, "LastSchemaUpdate", MDDATASET.PREFIX);
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

}
