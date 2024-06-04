/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ENGINE200.QN_WARNING_COLUMN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ENGINE200.QN_WARNING_MEASURE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_AXES_INFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_AXIS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_AXIS_INFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_CELL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_CELL_DATA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_CROSS_PRODUCT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_CUBE_INFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_HIERARCHY_INFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_MEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_MEMBERS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_NORM_TUPLE_SET;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_TUPLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_TUPLES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDDATASET.QN_UNION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.DESCRIPTION_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ALL_MEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_APPLICATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_AUTHENTICATION_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_AUTO_UNIQUE_VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BASE_CUBE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BEST_MATCH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BOOKMARKS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BOOKMARK_DATA_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BOOKMARK_INFORMATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BOOKMARK_MAXIMUM_LENGTH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_BOOKMARK_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CARDINALITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CASE_SENSITIVE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CATALOG_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CHARACTER_MAXIMUM_LENGTH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CHARACTER_OCTET_LENGTH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CHARACTER_SET_CATALOG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CHARACTER_SET_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CHARACTER_SET_SCHEMA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CHILDREN_CARDINALITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CLIENTCACHEREFRESHPOLICY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLLATION_CATALOG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLLATION_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLLATION_SCHEMA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_DEFAULT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_FLAG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_HAS_DEFAULT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_OLAP_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_PROPID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COLUMN_SIZE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_COMPATIBILITY_LEVEL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CREATED_ON;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CREATE_PARAMS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CUBE_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CUBE_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CUBE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CUBE_SOURCE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CUBE_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CURRENTLY_USED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_CUSTOM_ROLLUP_SETTINGS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATABASE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATA_SOURCE_DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATA_SOURCE_INFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATA_SOURCE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATA_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATA_UPDATED_BY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATETIME_PRECISION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATE_CREATED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATE_MODIFIED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DATE_QUERIED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DEFAULT_FORMAT_STRING;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DEFAULT_HIERARCHY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DEFAULT_MEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DESCRIPTION_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_CARDINALITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_GRANULARITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_IS_FACT_DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_IS_SHARED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_IS_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_MASTER_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_MASTER_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_ORDINAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_PATH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIMENSION_UNIQUE_SETTINGS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DIRECTQUERY_PUSHABLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DLL_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DOMAIN_CATALOG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DOMAIN_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_DOMAIN_SCHEMA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ELEMENT_DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ELEMENT_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ELEMENT_VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ENUM_DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ENUM_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ENUM_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_EXPRESSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_FIXED_PREC_SCALE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_FUNCTION_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_GROUPING_BEHAVIOR;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HELP_CONTEXT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HELP_FILE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_CARDINALITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_IS_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_ORDINAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_ORIGIN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_HIERARCHY_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_INSTANCE_SELECTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_INTERFACE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_INVOCATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_DATAMEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_DRILLTHROUGH_ENABLED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_FIXEDLENGTH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_LINKABLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_LONG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_NULLABLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_PLACEHOLDERMEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_READWRITE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_REQUIRED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_SQL_ENABLED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_VIRTUAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_IS_WRITE_ENABLED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KEYWORD;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_CURRENT_TIME_MEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_GOAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_PARENT_KPI_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_STATUS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_STATUS_GRAPHIC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_TREND;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_TREND_GRAPHIC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_KPI_WEIGHT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVELS_LIST;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_CARDINALITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_IS_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_NUMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_ORIGIN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LEVEL_UNIQUE_SETTINGS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LIBRARY_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_INVALID_CHARS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_INVALID_STARTING_CHARS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_MAX_LENGTH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_NAME_ENUM_VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_PREFIX;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_SUFFIX;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LITERAL_VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_LOCAL_TYPE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MAXIMUM_SCALE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASUREGROUP_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASUREGROUP_CARDINALITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASUREGROUP_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_AGGREGATOR;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_GROUP_DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_IS_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_NAME_SQL_COLUMN_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_UNITS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_UNQUALIFIED_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEASURE_VISIBILITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBERS_LOOKUP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_DISP_INFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_KEY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_ORDINAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_REF;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MEMBER_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_META_DATA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_MINIMUM_SCALE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_NAME_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_NORM_TUPLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_NORM_TUPLES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_NUMERIC_PRECISION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_NUMERIC_SCALE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_OBJECT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_OPTIONAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ORDINAL_POSITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ORIGIN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PARAMETERINFO;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PARAMETER_LIST;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PARENT_COUNT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PARENT_LEVEL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PARENT_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_POPULARITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PREFERRED_QUERY_PATTERNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_ACCESS_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_CONTENT_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_NAME_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_ORIGIN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_TYPE_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROPERTY_VISIBILITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROVIDER_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_PROVIDER_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_REPEATABLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_REPEATGROUP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_RESTRICTIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_RESTRICTIONS_MASK;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_RETURN_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_ROLES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SCHEMA_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SCHEMA_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SCHEMA_NAME_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SCHEMA_OWNER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SCHEMA_UPDATED_BY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SCOPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SEARCHABLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SET_CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SET_DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SET_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_SQL_COLUMN_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_STRUCTURE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_STRUCTURE_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_CATALOG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_PROP_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_SCHEMA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TABLE_VERSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TYPE_GUID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TYPE_LC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TYPE_LIB;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_TYPE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_UNSIGNED_ATTRIBUTE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_URL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_VERSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROWSET.ROW_PROPERTY.QN_WEIGHTEDPOPULARITY;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.api.common.enums.ItemTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.ParameterInfo;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MeasureGroupDimension;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.api.engine200.WarningColumn;
import org.eclipse.daanse.xmla.api.engine200.WarningLocationObject;
import org.eclipse.daanse.xmla.api.engine200.WarningMeasure;
import org.eclipse.daanse.xmla.api.exception.ErrorType;
import org.eclipse.daanse.xmla.api.exception.Exception;
import org.eclipse.daanse.xmla.api.exception.MessageLocation;
import org.eclipse.daanse.xmla.api.exception.Messages;
import org.eclipse.daanse.xmla.api.exception.StartEnd;
import org.eclipse.daanse.xmla.api.exception.WarningType;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.mddataset.Axes;
import org.eclipse.daanse.xmla.api.mddataset.AxesInfo;
import org.eclipse.daanse.xmla.api.mddataset.Axis;
import org.eclipse.daanse.xmla.api.mddataset.AxisInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellData;
import org.eclipse.daanse.xmla.api.mddataset.CellInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellInfoItem;
import org.eclipse.daanse.xmla.api.mddataset.CellType;
import org.eclipse.daanse.xmla.api.mddataset.CellTypeError;
import org.eclipse.daanse.xmla.api.mddataset.CubeInfo;
import org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo;
import org.eclipse.daanse.xmla.api.mddataset.Mddataset;
import org.eclipse.daanse.xmla.api.mddataset.MemberType;
import org.eclipse.daanse.xmla.api.mddataset.MembersType;
import org.eclipse.daanse.xmla.api.mddataset.NormTupleSet;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfo;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube;
import org.eclipse.daanse.xmla.api.mddataset.RowSet;
import org.eclipse.daanse.xmla.api.mddataset.RowSetRow;
import org.eclipse.daanse.xmla.api.mddataset.RowSetRowItem;
import org.eclipse.daanse.xmla.api.mddataset.SetListType;
import org.eclipse.daanse.xmla.api.mddataset.TupleType;
import org.eclipse.daanse.xmla.api.mddataset.TuplesType;
import org.eclipse.daanse.xmla.api.mddataset.Type;
import org.eclipse.daanse.xmla.api.mddataset.Union;
import org.eclipse.daanse.xmla.api.mddataset.Value;
import org.eclipse.daanse.xmla.api.msxmla.MemberRef;
import org.eclipse.daanse.xmla.api.msxmla.MembersLookup;
import org.eclipse.daanse.xmla.api.msxmla.NormTuple;
import org.eclipse.daanse.xmla.api.msxmla.NormTuplesType;
import org.eclipse.daanse.xmla.api.xmla.Restriction;
import org.eclipse.daanse.xmla.api.xmla_empty.Emptyresult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class SoapUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoapUtil.class);

    private SoapUtil() {
        // constructor
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static void toMdSchemaFunctions(List<MdSchemaFunctionsResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addMdSchemaFunctionsRoot(body);

        for (MdSchemaFunctionsResponseRow mdSchemaFunctionsResponseRow : rows) {
            addMdSchemaFunctionsResponseRow(root, mdSchemaFunctionsResponseRow);

        }

    }

    public static void toMdSchemaDimensions(List<MdSchemaDimensionsResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addMdSchemaDimensionsRoot(body);

        for (MdSchemaDimensionsResponseRow mdSchemaDimensionsResponseRow : rows) {
            addMdSchemaDimensionsResponseRow(root, mdSchemaDimensionsResponseRow);

        }

    }

    public static void toDiscoverProperties(List<DiscoverPropertiesResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDiscoverPropertiesRoot(body);

        for (DiscoverPropertiesResponseRow discoverPropertiesResponseRow : rows) {

            addDiscoverPropertiesResponseRow(root, discoverPropertiesResponseRow);
        }

    }

    public static void toMdSchemaCubes(List<MdSchemaCubesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaCubesRoot(body);
        for (MdSchemaCubesResponseRow mdSchemaCubesResponseRow : rows) {
            addMdSchemaCubesResponseRow(root, mdSchemaCubesResponseRow);
        }

    }

    public static void toMdSchemaMeasureGroups(List<MdSchemaMeasureGroupsResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addMdSchemaMeasureGroupsRoot(body);
        for (MdSchemaMeasureGroupsResponseRow mdSchemaMeasureGroupsResponseRow : rows) {
            addMdSchemaMeasureGroupsResponseRow(root, mdSchemaMeasureGroupsResponseRow);
        }

    }

    public static void toMdSchemaKpis(List<MdSchemaKpisResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaKpiRoot(body);

        for (MdSchemaKpisResponseRow mdSchemaKpisResponseRow : rows) {
            addMdSchemaKpisResponseRow(root, mdSchemaKpisResponseRow);
        }

    }

    public static void toMdSchemaSets(List<MdSchemaSetsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaSetsRoot(body);

        for (MdSchemaSetsResponseRow mdSchemaSetsResponseRow : rows) {
            addMdSchemaSetsResponseRow(root, mdSchemaSetsResponseRow);
        }

    }

    public static void toMdSchemaProperties(List<MdSchemaPropertiesResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addMdSchemaPropertiesRoot(body);
        for (MdSchemaPropertiesResponseRow mdSchemaPropertiesResponseRow : rows) {
            addMdSchemaPropertiesResponseRow(root, mdSchemaPropertiesResponseRow);
        }

    }

    public static void toMdSchemaMembers(List<MdSchemaMembersResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaMembersRoot(body);

        for (MdSchemaMembersResponseRow mdSchemaMembersResponseRow : rows) {
            addMdSchemaMembersResponseRow(root, mdSchemaMembersResponseRow);
        }

    }

    public static void toMdSchemaMeasures(List<MdSchemaMeasuresResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaMeasuresRoot(body);

        for (MdSchemaMeasuresResponseRow mdSchemaMeasuresResponseRow : rows) {
            addMdSchemaMeasuresResponseRow(root, mdSchemaMeasuresResponseRow);
        }

    }

    public static void toMdSchemaMeasureGroupDimensions(List<MdSchemaMeasureGroupDimensionsResponseRow> rows,
            SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaMeasureGroupDimensionsRoot(body);

        for (MdSchemaMeasureGroupDimensionsResponseRow mdSchemaMeasureGroupDimensionsResponseRow : rows) {
            addMdSchemaMeasureGroupDimensionsResponseRow(root, mdSchemaMeasureGroupDimensionsResponseRow);
        }

    }

    public static void toMdSchemaLevels(List<MdSchemaLevelsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaLevelsRoot(body);

        for (MdSchemaLevelsResponseRow mdSchemaLevelsResponseRow : rows) {
            addMdSchemaLevelsResponseRow(root, mdSchemaLevelsResponseRow);
        }

    }

    public static void toMdSchemaHierarchies(List<MdSchemaHierarchiesResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addMdSchemaHierarchiesRoot(body);

        for (MdSchemaHierarchiesResponseRow mdSchemaHierarchiesResponseRow : rows) {
            addMdSchemaHierarchiesResponseRow(root, mdSchemaHierarchiesResponseRow);
        }

    }

    public static void toDbSchemaTablesInfo(List<DbSchemaTablesInfoResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDbSchemaTablesInfoRoot(body);

        for (DbSchemaTablesInfoResponseRow dbSchemaTablesInfoResponseRow : rows) {
            addDbSchemaTablesInfoResponseRow(root, dbSchemaTablesInfoResponseRow);
        }

    }

    public static void toDbSchemaSourceTables(List<DbSchemaSourceTablesResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDbSchemaSourceTablesRoot(body);

        for (DbSchemaSourceTablesResponseRow dbSchemaSourceTablesResponseRow : rows) {
            addDbSchemaSourceTablesResponseRow(root, dbSchemaSourceTablesResponseRow);
        }

    }

    public static void toDbSchemaSchemata(List<DbSchemaSchemataResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addDbSchemaSchemataRoot(body);
        for (DbSchemaSchemataResponseRow dbSchemaSchemataResponseRow : rows) {
            addDbSchemaSchemataResponseRow(root, dbSchemaSchemataResponseRow);
        }

    }

    public static void toDbSchemaProviderTypes(List<DbSchemaProviderTypesResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDbSchemaProviderTypesRoot(body);

        for (DbSchemaProviderTypesResponseRow dbSchemaProviderTypesResponseRow : rows) {
            addDbSchemaProviderTypesResponseRow(root, dbSchemaProviderTypesResponseRow);
        }

    }

    public static void toDbSchemaColumns(List<DbSchemaColumnsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addDbSchemaColumnsRoot(body);

        for (DbSchemaColumnsResponseRow dbSchemaColumnsResponseRow : rows) {
            addDbSchemaColumnsResponseRow(root, dbSchemaColumnsResponseRow);
        }

    }

    public static void toDiscoverXmlMetaData(List<DiscoverXmlMetaDataResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDiscoverXmlMetaDataRoot(body);

        for (DiscoverXmlMetaDataResponseRow discoverXmlMetaDataResponseRow : rows) {
            addDiscoverXmlMetaDataResponseRow(root, discoverXmlMetaDataResponseRow);
        }

    }

    public static void toDiscoverDataSources(List<DiscoverDataSourcesResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDiscoverDataSourcesRoot(body);

        for (DiscoverDataSourcesResponseRow discoverDataSourcesResponseRow : rows) {
            addDiscoverDataSourcesResponseRow(root, discoverDataSourcesResponseRow);
        }

    }

    public static void toDbSchemaCatalogs(List<DbSchemaCatalogsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addDbSchemaCatalogsRoot(body);

        for (DbSchemaCatalogsResponseRow dbSchemaCatalogsResponseRow : rows) {
            addDbSchemaCatalogsResponseRow(root, dbSchemaCatalogsResponseRow);
        }

    }

    public static void toDiscoverSchemaRowsets(List<DiscoverSchemaRowsetsResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDiscoverRowSetsRoot(body);

        for (DiscoverSchemaRowsetsResponseRow discoverSchemaRowsetsResponseRow : rows) {
            addDiscoverSchemaRowsetsResponseRow(root, discoverSchemaRowsetsResponseRow);
        }

    }

    public static void toDiscoverEnumerators(List<DiscoverEnumeratorsResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addDiscoverEnumeratorsRoot(body);

        for (DiscoverEnumeratorsResponseRow discoverEnumeratorsResponseRow : rows) {
            addDiscoverEnumeratorsResponseRow(root, discoverEnumeratorsResponseRow);
        }

    }

    public static void toDiscoverKeywords(List<DiscoverKeywordsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addDiscoverKeywordsRoot(body);

        for (DiscoverKeywordsResponseRow discoverKeywordsResponseRow : rows) {
            addDiscoverKeywordsResponseRow(root, discoverKeywordsResponseRow);
        }
    }

    public static void toDiscoverLiterals(List<DiscoverLiteralsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addDiscoverLiteralsRoot(body);

        for (DiscoverLiteralsResponseRow discoverLiteralsResponseRow : rows) {
            addDiscoverLiteralsResponseRow(root, discoverLiteralsResponseRow);
        }
    }

    public static void toDbSchemaTables(List<DbSchemaTablesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addDbSchemaTablesRoot(body);

        for (DbSchemaTablesResponseRow dbSchemaTablesResponseRow : rows) {
            addDbSchemaTablesResponseRow(root, dbSchemaTablesResponseRow);

        }

    }

    public static void toMdSchemaActions(List<MdSchemaActionsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addMdSchemaActionsRoot(body);

        for (MdSchemaActionsResponseRow mdSchemaActionsResponseRow : rows) {
            addMdSchemaActionsResponseRow(root, mdSchemaActionsResponseRow);
        }
    }

    public static void toStatementResponse(StatementResponse statementResponse, SOAPBody body) throws SOAPException {
        if (statementResponse != null && statementResponse.mdDataSet() != null) {
            SOAPElement root = addMddatasetRoot(body);
            addMdDataSet(root, statementResponse.mdDataSet());
        }
        if (statementResponse.rowSet() != null) {
            SOAPElement root = addRowSetRoot(body, statementResponse.rowSet());
            List<RowSetRow> rowSetRows = statementResponse.rowSet().rowSetRows();
            if (rowSetRows != null) {

                for (RowSetRow rowSetRow : rowSetRows) {
                    addRowSetRow(root, rowSetRow);
                }
            }
        }
        if (statementResponse.mdDataSet() == null && statementResponse.rowSet() == null) {
            addEmptyRoot(body);
        }
    }

    private static void addRowSetRow(SOAPElement e, RowSetRow it) throws SOAPException {
        SOAPElement seRow = e.addChildElement(Constants.ROWSET.QN_ROW);
        if (it.rowSetRowItem() != null) {
            it.rowSetRowItem().forEach(i -> addRowSetRowItem(seRow, i));
        }
    }

    private static void addRowSetRowItem(SOAPElement e, RowSetRowItem it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, it.tagName(), Constants.ROWSET.PREFIX);
            el.setTextContent(it.value());
            it.type().ifPresent(v -> setAttribute(el, "type", v.getValue()));
        }
    }

    public static void toAlterResponse(AlterResponse alterResponse, SOAPBody body) throws SOAPException {
        SOAPElement root = addEmptyRoot(body);
        if (alterResponse != null) {
            addEmptyresult(root, alterResponse.emptyresult());
        }
    }

    public static void toClearCacheResponse(ClearCacheResponse clearCacheResponse, SOAPBody body) throws SOAPException {
        SOAPElement root = addEmptyRoot(body);
        if (clearCacheResponse != null) {
            addEmptyresult(root, clearCacheResponse.emptyresult());
        }
    }

    public static void toCancelResponse(CancelResponse cancelResponse, SOAPBody body) throws SOAPException {
        SOAPElement root = addEmptyRoot(body);
        if (cancelResponse != null) {
            addEmptyresult(root, cancelResponse.emptyresult());
        }
    }

    private static void addEmptyresult(SOAPElement root, Emptyresult emptyresult) {
        addException(root, emptyresult.exception());
        addMessages(root, emptyresult.messages());
    }

    private static void addMdSchemaActionsResponseRow(SOAPElement root, MdSchemaActionsResponseRow r)
            throws SOAPException {

        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        addChildElement(row, QN_CUBE_NAME, r.cubeName());
        r.actionName().ifPresent(v -> addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_ACTION_NAME,  v));
        r.actionType().ifPresent(v -> addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_ACTION_TYPE, String.valueOf(v.getValue())));
        addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_COORDINATE, r.coordinate());
        addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_COORDINATE_TYPE, String.valueOf(r.coordinateType().getValue()));
        r.actionCaption().ifPresent(v -> addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_ACTION_CAPTION, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.content().ifPresent(v -> addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_CONTENT, v));
        r.application().ifPresent(v -> addChildElement(row, QN_APPLICATION, v));
        r.invocation().ifPresent(v -> addChildElement(row, QN_INVOCATION, String.valueOf(v.getValue())));
    }

    private static void addDbSchemaTablesResponseRow(SOAPElement root, DbSchemaTablesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.tableCatalog().ifPresent(v -> addChildElement(row, QN_TABLE_CATALOG, v));
        r.tableSchema().ifPresent(v -> addChildElement(row, QN_TABLE_SCHEMA, v));
        r.tableName().ifPresent(v -> addChildElement(row, QN_TABLE_NAME, v));
        r.tableType().ifPresent(v -> addChildElement(row, QN_TABLE_TYPE, v));
        r.tableGuid().ifPresent(v -> addChildElement(row, QN_TABLE_GUID, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.tablePropId().ifPresent(v -> addChildElement(row, QN_TABLE_PROP_ID, String.valueOf(v)));
        r.dateCreated().ifPresent(v -> addChildElement(row, QN_DATE_CREATED, String.valueOf(v)));
        r.dateModified().ifPresent(v -> addChildElement(row,  QN_DATE_MODIFIED, String.valueOf(v)));
    }

    private static void addDiscoverLiteralsResponseRow(SOAPElement root, DiscoverLiteralsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_LITERAL_NAME, r.literalName());
        addChildElement(row, QN_LITERAL_VALUE, r.literalValue());
        addChildElement(row, QN_LITERAL_INVALID_CHARS, r.literalInvalidChars());
        addChildElement(row, QN_LITERAL_INVALID_STARTING_CHARS, r.literalInvalidStartingChars());
        addChildElement(row, QN_LITERAL_MAX_LENGTH, String.valueOf(r.literalMaxLength()));
        addChildElement(row, QN_LITERAL_NAME_ENUM_VALUE, String.valueOf(r.literalNameEnumValue().getValue()));
    }

    private static void addDiscoverKeywordsResponseRow(SOAPElement root, DiscoverKeywordsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_KEYWORD, r.keyword());
    }

    private static void addDiscoverEnumeratorsResponseRow(SOAPElement root, DiscoverEnumeratorsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_ENUM_NAME, r.enumName());
        r.enumDescription().ifPresent(v -> addChildElement(row, QN_ENUM_DESCRIPTION, v));
        addChildElement(row, QN_ENUM_TYPE, r.enumType());
        addChildElement(row, QN_ELEMENT_NAME, r.elementName());
        r.elementDescription().ifPresent(v -> addChildElement(row, QN_ELEMENT_DESCRIPTION, v));
        r.elementValue().ifPresent(v -> addChildElement(row, QN_ELEMENT_VALUE, v));
    }

    private static void addDiscoverSchemaRowsetsResponseRow(SOAPElement root, DiscoverSchemaRowsetsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_SCHEMA_NAME_LC, r.schemaName());
        r.schemaGuid().ifPresent(v -> addChildElement(row, QN_SCHEMA_GUID, v));
        r.restrictions().ifPresent(v -> addRestrictionList(row, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION_LC, v));
        r.restrictionsMask().ifPresent(v -> addChildElement(row, QN_RESTRICTIONS_MASK, String.valueOf(v)));
    }

    private static void addRestrictionList(SOAPElement el, List<Restriction> list) {
        if (list != null) {
            list.forEach(it -> addRestriction(el, it));
        }
    }

    private static void addRestriction(SOAPElement e, Restriction it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_RESTRICTIONS);
            SOAPElement name = addChildElement(el, QN_NAME_LC);
            name.setTextContent(it.name());
            SOAPElement type = addChildElement(el, QN_TYPE_LC);
            type.setTextContent(it.type());
        }
    }

    private static void addDbSchemaCatalogsResponseRow(SOAPElement root, DbSchemaCatalogsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.roles().ifPresent(v -> addChildElement(row, QN_ROLES, v));
        r.dateModified().ifPresent(v -> addChildElement(row, QN_DATE_MODIFIED, v.format(formatter)));
        r.compatibilityLevel().ifPresent(v -> addChildElement(row, QN_COMPATIBILITY_LEVEL, String.valueOf(v)));
        r.type().ifPresent(v -> addChildElement(row, QN_TYPE, String.valueOf(v.getValue())));
        r.version().ifPresent(v -> addChildElement(row, QN_VERSION, String.valueOf(v)));
        r.databaseId().ifPresent(v -> addChildElement(row, QN_DATABASE_ID, v));
        r.dateQueried().ifPresent(v -> addChildElement(row, QN_DATE_QUERIED, String.valueOf(v)));
        r.currentlyUsed().ifPresent(v -> addChildElement(row, QN_CURRENTLY_USED, String.valueOf(v)));
        r.popularity().ifPresent(v -> addChildElement(row, QN_POPULARITY, String.valueOf(v)));
        r.weightedPopularity().ifPresent(v -> addChildElement(row, QN_WEIGHTEDPOPULARITY, String.valueOf(v)));
        r.clientCacheRefreshPolicy()
                .ifPresent(v -> addChildElement(row, QN_CLIENTCACHEREFRESHPOLICY, String.valueOf(v.getValue())));
    }

    private static void addDiscoverDataSourcesResponseRow(SOAPElement root, DiscoverDataSourcesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_DATA_SOURCE_NAME, r.dataSourceName());
        r.dataSourceDescription().ifPresent(v -> addChildElement(row, QN_DATA_SOURCE_DESCRIPTION, v));
        r.url().ifPresent(v -> addChildElement(row, QN_URL, v));
        r.dataSourceInfo().ifPresent(v -> addChildElement(row, QN_DATA_SOURCE_INFO, v));
        addChildElement(row, QN_PROVIDER_NAME, r.providerName());
        r.providerType().ifPresent(v -> addChildElement(row, QN_PROVIDER_TYPE, v.name()));
        r.authenticationMode().ifPresent(v -> addChildElement(row, QN_AUTHENTICATION_MODE, v.getValue()));
    }

    private static void addDiscoverXmlMetaDataResponseRow(SOAPElement root, DiscoverXmlMetaDataResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_META_DATA, r.metaData());
    }

    private static void addDbSchemaColumnsResponseRow(SOAPElement root, DbSchemaColumnsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.tableCatalog().ifPresent(v -> addChildElement(row, QN_TABLE_CATALOG, v));
        r.tableSchema().ifPresent(v -> addChildElement(row, QN_TABLE_SCHEMA, v));
        r.tableName().ifPresent(v -> addChildElement(row, QN_TABLE_NAME, v));
        r.columnName().ifPresent(v -> addChildElement(row, QN_COLUMN_NAME, v));
        r.columnGuid().ifPresent(v -> addChildElement(row, QN_COLUMN_GUID, String.valueOf(v)));
        r.columnPropId().ifPresent(v -> addChildElement(row, QN_COLUMN_PROPID, String.valueOf(v)));
        r.ordinalPosition().ifPresent(v -> addChildElement(row, QN_ORDINAL_POSITION, String.valueOf(v)));
        r.columnHasDefault().ifPresent(v -> addChildElement(row, QN_COLUMN_HAS_DEFAULT, String.valueOf(v)));
        r.columnDefault().ifPresent(v -> addChildElement(row, QN_COLUMN_DEFAULT, v));
        r.columnFlags().ifPresent(v -> addChildElement(row, QN_COLUMN_FLAG, String.valueOf(v.getValue())));
        r.isNullable().ifPresent(v -> addChildElement(row, QN_IS_NULLABLE, String.valueOf(v)));
        r.dataType().ifPresent(v -> addChildElement(row, QN_DATA_TYPE, String.valueOf(v)));
        r.typeGuid().ifPresent(v -> addChildElement(row, QN_TYPE_GUID, String.valueOf(v)));
        r.characterMaximum()
                .ifPresent(v -> addChildElement(row, QN_CHARACTER_MAXIMUM_LENGTH, String.valueOf(v)));
        r.characterOctetLength()
                .ifPresent(v -> addChildElement(row, QN_CHARACTER_OCTET_LENGTH, String.valueOf(v)));
        r.numericPrecision().ifPresent(v -> addChildElement(row, QN_NUMERIC_PRECISION, String.valueOf(v)));
        r.numericScale().ifPresent(v -> addChildElement(row, QN_NUMERIC_SCALE, String.valueOf(v)));
        r.dateTimePrecision().ifPresent(v -> addChildElement(row, QN_DATETIME_PRECISION, String.valueOf(v)));
        r.characterSetCatalog().ifPresent(v -> addChildElement(row, QN_CHARACTER_SET_CATALOG, v));
        r.characterSetSchema().ifPresent(v -> addChildElement(row, QN_CHARACTER_SET_SCHEMA, v));
        r.characterSetName().ifPresent(v -> addChildElement(row, QN_CHARACTER_SET_NAME, v));
        r.collationCatalog().ifPresent(v -> addChildElement(row, QN_COLLATION_CATALOG, v));
        r.collationSchema().ifPresent(v -> addChildElement(row, QN_COLLATION_SCHEMA, v));
        r.collationName().ifPresent(v -> addChildElement(row, QN_COLLATION_NAME, v));
        r.domainCatalog().ifPresent(v -> addChildElement(row, QN_DOMAIN_CATALOG, v));
        r.domainSchema().ifPresent(v -> addChildElement(row, QN_DOMAIN_SCHEMA, v));
        r.domainName().ifPresent(v -> addChildElement(row, QN_DOMAIN_NAME, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.columnOlapType().ifPresent(v -> addChildElement(row, QN_COLUMN_OLAP_TYPE, v.name()));
    }

    private static void addDbSchemaProviderTypesResponseRow(SOAPElement root, DbSchemaProviderTypesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.typeName().ifPresent(v -> addChildElement(row, QN_TYPE_NAME, v));
        r.dataType().ifPresent(v -> addChildElement(row, QN_DATA_TYPE, String.valueOf(v.getValue())));
        r.columnSize().ifPresent(v -> addChildElement(row, QN_COLUMN_SIZE, String.valueOf(v)));
        r.literalPrefix().ifPresent(v -> addChildElement(row, QN_LITERAL_PREFIX, v));
        r.literalSuffix().ifPresent(v -> addChildElement(row, QN_LITERAL_SUFFIX, v));
        r.createParams().ifPresent(v -> addChildElement(row, QN_CREATE_PARAMS, v));
        r.isNullable().ifPresent(v -> addChildElement(row, QN_IS_NULLABLE, String.valueOf(v)));
        r.caseSensitive().ifPresent(v -> addChildElement(row, QN_CASE_SENSITIVE, String.valueOf(v)));
        r.searchable().ifPresent(v -> addChildElement(row, QN_SEARCHABLE, String.valueOf(v.getValue())));
        r.unsignedAttribute().ifPresent(v -> addChildElement(row, QN_UNSIGNED_ATTRIBUTE, String.valueOf(v)));
        r.fixedPrecScale().ifPresent(v -> addChildElement(row, QN_FIXED_PREC_SCALE, String.valueOf(v)));
        r.autoUniqueValue().ifPresent(v -> addChildElement(row, QN_AUTO_UNIQUE_VALUE, String.valueOf(v)));
        r.localTypeName().ifPresent(v -> addChildElement(row, QN_LOCAL_TYPE_NAME, v));
        r.minimumScale().ifPresent(v -> addChildElement(row, QN_MINIMUM_SCALE, String.valueOf(v)));
        r.maximumScale().ifPresent(v -> addChildElement(row, QN_MAXIMUM_SCALE, String.valueOf(v)));
        r.guid().ifPresent(v -> addChildElement(row, QN_GUID, String.valueOf(v)));
        r.typeLib().ifPresent(v -> addChildElement(row, QN_TYPE_LIB, v));
        r.version().ifPresent(v -> addChildElement(row, QN_VERSION, v));
        r.isLong().ifPresent(v -> addChildElement(row, QN_IS_LONG, String.valueOf(v)));
        r.bestMatch().ifPresent(v -> addChildElement(row, QN_BEST_MATCH, String.valueOf(v)));
        r.isFixedLength().ifPresent(v -> addChildElement(row, QN_IS_FIXEDLENGTH, String.valueOf(v)));
    }

    private static void addDbSchemaSchemataResponseRow(SOAPElement root, DbSchemaSchemataResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_CATALOG_NAME, r.catalogName());
        addChildElement(row, QN_SCHEMA_NAME, r.schemaName());
        addChildElement(row, QN_SCHEMA_OWNER, r.schemaOwner());
    }

    private static void addDbSchemaSourceTablesResponseRow(SOAPElement root, DbSchemaSourceTablesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_TABLE_CATALOG, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_TABLE_SCHEMA, v));
        addChildElement(row, QN_TABLE_NAME, r.tableName());
        addChildElement(row, QN_TABLE_TYPE, r.tableType().getValue());
    }

    private static void addDbSchemaTablesInfoResponseRow(SOAPElement root, DbSchemaTablesInfoResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_TABLE_CATALOG, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_TABLE_SCHEMA, v));
        addChildElement(row, QN_TABLE_NAME, r.tableName());
        addChildElement(row, QN_TABLE_TYPE, r.tableType());
        r.tableGuid().ifPresent(v -> addChildElement(row, QN_TABLE_GUID, String.valueOf(v)));
        r.bookmarks().ifPresent(v -> addChildElement(row, QN_BOOKMARKS, String.valueOf(v)));
        r.bookmarkType().ifPresent(v -> addChildElement(row, QN_BOOKMARK_TYPE, String.valueOf(v)));
        r.bookmarkDataType().ifPresent(v -> addChildElement(row, QN_BOOKMARK_DATA_TYPE, String.valueOf(v)));
        r.bookmarkMaximumLength()
                .ifPresent(v -> addChildElement(row, QN_BOOKMARK_MAXIMUM_LENGTH, String.valueOf(v)));
        r.bookmarkInformation().ifPresent(v -> addChildElement(row, QN_BOOKMARK_INFORMATION, String.valueOf(v)));
        r.tableVersion().ifPresent(v -> addChildElement(row, QN_TABLE_VERSION, String.valueOf(v)));
        r.cardinality().ifPresent(v -> addChildElement(row, QN_CARDINALITY, String.valueOf(v)));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.tablePropId().ifPresent(v -> addChildElement(row, QN_TABLE_PROP_ID, String.valueOf(v)));
    }

    private static void addMdSchemaHierarchiesResponseRow(SOAPElement root, MdSchemaHierarchiesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyName().ifPresent(v -> addChildElement(row, QN_HIERARCHY_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, QN_HIERARCHY_UNIQUE_NAME, v));
        r.hierarchyGuid().ifPresent(v -> addChildElement(row, QN_HIERARCHY_GUID, String.valueOf(v)));
        r.hierarchyCaption().ifPresent(v -> addChildElement(row, QN_HIERARCHY_CAPTION, v));
        r.dimensionType().ifPresent(v -> addChildElement(row, QN_DIMENSION_TYPE, String.valueOf(v.getValue())));
        r.hierarchyCardinality()
                .ifPresent(v -> addChildElement(row, QN_HIERARCHY_CARDINALITY, String.valueOf(v)));
        r.defaultMember().ifPresent(v -> addChildElement(row, QN_DEFAULT_MEMBER, v));
        r.allMember().ifPresent(v -> addChildElement(row, QN_ALL_MEMBER, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.structure().ifPresent(v -> addChildElement(row, QN_STRUCTURE, String.valueOf(v.getValue())));
        r.isVirtual().ifPresent(v -> addChildElement(row, QN_IS_VIRTUAL, String.valueOf(v)));
        r.isReadWrite().ifPresent(v -> addChildElement(row, QN_IS_READWRITE, String.valueOf(v)));
        r.dimensionUniqueSettings().ifPresent(
                v -> addChildElement(row, QN_DIMENSION_UNIQUE_SETTINGS, String.valueOf(v.getValue())));
        r.dimensionMasterUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_MASTER_UNIQUE_NAME, v));
        r.dimensionIsVisible().ifPresent(v -> addChildElement(row, QN_DIMENSION_IS_VISIBLE, String.valueOf(v)));
        r.hierarchyOrdinal().ifPresent(v -> addChildElement(row, QN_HIERARCHY_ORDINAL, String.valueOf(v)));
        r.dimensionIsShared().ifPresent(v -> addChildElement(row, QN_DIMENSION_IS_SHARED, String.valueOf(v)));
        r.hierarchyIsVisible().ifPresent(v -> addChildElement(row, QN_HIERARCHY_IS_VISIBLE, String.valueOf(v)));
        r.hierarchyOrigin()
                .ifPresent(v -> addChildElement(row, QN_HIERARCHY_ORIGIN, String.valueOf(v.getValue())));
        r.hierarchyDisplayFolder().ifPresent(v -> addChildElement(row, QN_HIERARCHY_DISPLAY_FOLDER, v));
        //r.hierarchyIsVisible().ifPresent(v -> addChildElement(row, "HIERARCHY_VISIBILITY", prefix, v == true ? "1" : "0"));
        //addChildElement(row, "PARENT_CHILD", prefix, "false");
        r.instanceSelection()
                .ifPresent(v -> addChildElement(row, QN_INSTANCE_SELECTION, String.valueOf(v.getValue())));
        r.groupingBehavior()
                .ifPresent(v -> addChildElement(row, QN_GROUPING_BEHAVIOR, String.valueOf(v.getValue())));
        r.structureType().ifPresent(v -> addChildElement(row, QN_STRUCTURE_TYPE, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaLevelsResponseRow(SOAPElement root, MdSchemaLevelsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, QN_HIERARCHY_UNIQUE_NAME, v));
        r.levelName().ifPresent(v -> addChildElement(row, QN_LEVEL_NAME, v));
        r.levelUniqueName().ifPresent(v -> addChildElement(row, QN_LEVEL_UNIQUE_NAME, v));
        r.levelGuid().ifPresent(v -> addChildElement(row, QN_LEVEL_GUID, String.valueOf(v)));
        r.levelCaption().ifPresent(v -> addChildElement(row, QN_LEVEL_CAPTION, v));
        r.levelNumber().ifPresent(v -> addChildElement(row, QN_LEVEL_NUMBER, String.valueOf(v)));
        r.levelCardinality().ifPresent(v -> addChildElement(row, QN_LEVEL_CARDINALITY, String.valueOf(v)));
        r.levelType().ifPresent(v -> addChildElement(row, QN_LEVEL_TYPE, String.valueOf(v.getValue())));
        r.customRollupSetting()
                .ifPresent(v -> addChildElement(row, QN_CUSTOM_ROLLUP_SETTINGS, String.valueOf(v.getValue())));
        r.levelUniqueSettings()
                .ifPresent(v -> addChildElement(row, QN_LEVEL_UNIQUE_SETTINGS, String.valueOf(v.getValue())));
        r.levelIsVisible().ifPresent(v -> addChildElement(row, QN_LEVEL_IS_VISIBLE, String.valueOf(v)));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        // absent in old mondrian
        // r.levelOrderingProperty().ifPresent(v -> addChildElement(row,
        // "LEVEL_ORDERING_PROPERTY", prefix, v));
        // r.levelDbType().ifPresent(v -> addChildElement(row, "LEVEL_DBTYPE", prefix,
        // String.valueOf(v.getValue())));
        // r.levelMasterUniqueName().ifPresent(v -> addChildElement(row,
        // "LEVEL_MASTER_UNIQUE_NAME", prefix, v));
        // r.levelNameSqlColumnName().ifPresent(v -> addChildElement(row,
        // "LEVEL_NAME_SQL_COLUMN_NAME", prefix, v));
        // r.levelKeySqlColumnName().ifPresent(v -> addChildElement(row,
        // "LEVEL_KEY_SQL_COLUMN_NAME", prefix, v));
        // r.levelUniqueNameSqlColumnName().ifPresent(v -> addChildElement(row,
        // "LEVEL_UNIQUE_NAME_SQL_COLUMN_NAME", prefix, v));
        // r.levelAttributeHierarchyName().ifPresent(v -> addChildElement(row,
        // "LEVEL_ATTRIBUTE_HIERARCHY_NAME", prefix, v));
        // r.levelKeyCardinality().ifPresent(v -> addChildElement(row,
        // "LEVEL_KEY_CARDINALITY", prefix, String.valueOf(v)));

        r.levelOrigin().ifPresent(v -> addChildElement(row, QN_LEVEL_ORIGIN, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaMeasureGroupDimensionsResponseRow(SOAPElement root,
            MdSchemaMeasureGroupDimensionsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));

        r.measureGroupName().ifPresent(v -> addChildElement(row, QN_MEASUREGROUP_NAME, v));
        r.measureGroupCardinality().ifPresent(v -> addChildElement(row, QN_MEASUREGROUP_CARDINALITY, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_UNIQUE_NAME, v));
        r.dimensionCardinality().ifPresent(v -> addChildElement(row, QN_DIMENSION_CARDINALITY, v.name()));
        r.dimensionIsVisible().ifPresent(v -> addChildElement(row, QN_DIMENSION_IS_VISIBLE, String.valueOf(v)));
        r.dimensionIsFactDimension()
                .ifPresent(v -> addChildElement(row, QN_DIMENSION_IS_FACT_DIMENSION, String.valueOf(v)));
        r.dimensionPath().ifPresent(v -> addMeasureGroupDimensionXmlList(row, v));
        r.dimensionGranularity()
                .ifPresent(v -> addChildElement(row, QN_DIMENSION_GRANULARITY, v));
    }

    private static void addMeasureGroupDimensionXmlList(SOAPElement el, List<MeasureGroupDimension> list) {
        if (list != null) {
            SOAPElement e = addChildElement(el, QN_DIMENSION_PATH);
            list.forEach(it -> addMeasureGroupDimensionXml(e, it));
        }
    }

    private static void addMeasureGroupDimensionXml(SOAPElement el, MeasureGroupDimension it) {
        addChildElement(el, QN_MEASURE_GROUP_DIMENSION, it.measureGroupDimension());
    }

    private static void addMdSchemaMeasuresResponseRow(SOAPElement root, MdSchemaMeasuresResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));
        r.measureName().ifPresent(v -> addChildElement(row, QN_MEASURE_NAME, v));
        r.measureUniqueName().ifPresent(v -> addChildElement(row, QN_MEASURE_UNIQUE_NAME, v));
        r.measureCaption().ifPresent(v -> addChildElement(row, QN_MEASURE_CAPTION, v));
        r.measureGuid().ifPresent(v -> addChildElement(row, QN_MEASURE_GUID, String.valueOf(v)));
        r.measureAggregator()
                .ifPresent(v -> addChildElement(row, QN_MEASURE_AGGREGATOR, String.valueOf(v.getValue())));
        r.dataType().ifPresent(v -> addChildElement(row, QN_DATA_TYPE, String.valueOf(v.getValue())));
        r.numericPrecision().ifPresent(v -> addChildElement(row, QN_NUMERIC_PRECISION, String.valueOf(v)));
        r.numericScale().ifPresent(v -> addChildElement(row, QN_NUMERIC_SCALE, String.valueOf(v)));
        r.measureUnits().ifPresent(v -> addChildElement(row, QN_MEASURE_UNITS, v));
        r.expression().ifPresent(v -> addChildElement(row, QN_EXPRESSION, v));
        r.measureIsVisible().ifPresent(v -> addChildElement(row, QN_MEASURE_IS_VISIBLE, String.valueOf(v)));
        r.levelsList().ifPresent(v -> addChildElement(row, QN_LEVELS_LIST, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.measureNameSqlColumnName().ifPresent(v -> addChildElement(row, QN_MEASURE_NAME_SQL_COLUMN_NAME, v));
        r.measureUnqualifiedCaption().ifPresent(v -> addChildElement(row, QN_MEASURE_UNQUALIFIED_CAPTION, v));
        r.measureGroupName().ifPresent(v -> addChildElement(row, QN_MEASUREGROUP_NAME, v));
        r.measureDisplayFolder().ifPresent(v -> addChildElement(row, QN_MEASURE_DISPLAY_FOLDER, v));
        r.defaultFormatString().ifPresent(v -> addChildElement(row, QN_DEFAULT_FORMAT_STRING, v));
        r.cubeSource().ifPresent(v -> addChildElement(row, QN_CUBE_SOURCE, String.valueOf(v.getValue())));
        r.measureVisibility()
                .ifPresent(v -> addChildElement(row, QN_MEASURE_VISIBILITY, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaMembersResponseRow(SOAPElement root, MdSchemaMembersResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, QN_HIERARCHY_UNIQUE_NAME, v));
        r.levelUniqueName().ifPresent(v -> addChildElement(row, QN_LEVEL_UNIQUE_NAME, v));
        r.levelNumber().ifPresent(v -> addChildElement(row, QN_LEVEL_NUMBER, String.valueOf(v)));
        r.memberOrdinal().ifPresent(v -> addChildElement(row, QN_MEMBER_ORDINAL, String.valueOf(v)));
        r.memberName().ifPresent(v -> addChildElement(row, QN_MEMBER_NAME, v));
        r.memberUniqueName().ifPresent(v -> addChildElement(row, QN_MEMBER_UNIQUE_NAME, v));
        r.memberType().ifPresent(v -> addChildElement(row, QN_MEMBER_TYPE, String.valueOf(v.getValue())));
        r.memberGuid().ifPresent(v -> addChildElement(row, QN_MEMBER_GUID, String.valueOf(v)));
        r.memberCaption().ifPresent(v -> addChildElement(row, QN_MEMBER_CAPTION, v));
        r.childrenCardinality().ifPresent(v -> addChildElement(row, QN_CHILDREN_CARDINALITY, String.valueOf(v)));
        r.parentLevel().ifPresent(v -> addChildElement(row, QN_PARENT_LEVEL, String.valueOf(v)));
        r.parentUniqueName().ifPresent(v -> addChildElement(row, QN_PARENT_UNIQUE_NAME, v));
        r.parentCount().ifPresent(v -> addChildElement(row, QN_PARENT_COUNT, String.valueOf(v)));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.expression().ifPresent(v -> addChildElement(row, QN_EXPRESSION, v));
        r.memberKey().ifPresent(v -> addChildElement(row, QN_MEMBER_KEY, v));
        r.isPlaceHolderMember().ifPresent(v -> addChildElement(row, QN_IS_PLACEHOLDERMEMBER, String.valueOf(v)));
        r.isDataMember().ifPresent(v -> addChildElement(row, QN_IS_DATAMEMBER, String.valueOf(v)));
        r.scope().ifPresent(v -> addChildElement(row, QN_SCOPE, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaPropertiesResponseRow(SOAPElement root, MdSchemaPropertiesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));

        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, QN_HIERARCHY_UNIQUE_NAME, v));
        r.levelUniqueName().ifPresent(v -> addChildElement(row, QN_LEVEL_UNIQUE_NAME, v));
        r.memberUniqueName().ifPresent(v -> addChildElement(row, QN_MEMBER_UNIQUE_NAME, v));
        r.propertyType().ifPresent(v -> addChildElement(row, QN_PROPERTY_TYPE, String.valueOf(v.getValue())));
        r.propertyName().ifPresent(v -> addChildElement(row, QN_PROPERTY_NAME, v));
        r.propertyCaption().ifPresent(v -> addChildElement(row, QN_PROPERTY_CAPTION, v));
        r.dataType().ifPresent(v -> addChildElement(row, QN_DATA_TYPE, String.valueOf(v.getValue())));
        // r.characterMaximumLength().ifPresent(v -> addChildElement(row,
        // "CHARACTER_MAXIMUM_LENGTH"
        // , prefix, String.valueOf(v)));
        // r.characterOctetLength().ifPresent(v -> addChildElement(row,
        // "CHARACTER_OCTET_LENGTH",
        // prefix, String.valueOf(v)));
        // r.numericPrecision().ifPresent(v -> addChildElement(row, NUMERIC_PRECISION
        // , prefix, String.valueOf(v)));
        // r.numericScale().ifPresent(v -> addChildElement(row, NUMERIC_SCALE, prefix,
        // String.valueOf(v)));
        r.propertyContentType()
                .ifPresent(v -> addChildElement(row, QN_PROPERTY_CONTENT_TYPE, String.valueOf(v.getValue())));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.sqlColumnName().ifPresent(v -> addChildElement(row, QN_SQL_COLUMN_NAME, v));
        // r.language().ifPresent(v -> addChildElement(row, "LANGUAGE", prefix,
        // String.valueOf(v)));
        r.propertyOrigin()
                .ifPresent(v -> addChildElement(row, QN_PROPERTY_ORIGIN, String.valueOf(v.getValue())));
        // r.propertyAttributeHierarchyName().ifPresent(v -> addChildElement(row
        // , "PROPERTY_ATTRIBUTE_HIERARCHY_NAME", prefix, v));
        // r.propertyCardinality().ifPresent(v -> addChildElement(row,
        // "PROPERTY_CARDINALITY"
        // , prefix, v.name()));
        // r.mimeType().ifPresent(v -> addChildElement(row, "MIME_TYPE"
        // , prefix, v));
        r.cubeSource().ifPresent(v -> addChildElement(row, QN_CUBE_SOURCE, String.valueOf(v.getValue())));
        r.propertyIsVisible()
                .ifPresent(v -> addChildElement(row, QN_PROPERTY_VISIBILITY, String.valueOf(v.getValue())));

    }

    private static void addMdSchemaSetsResponseRow(SOAPElement root, MdSchemaSetsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));
        r.setName().ifPresent(v -> addChildElement(row, QN_SET_NAME, v));
        r.scope().ifPresent(v -> addChildElement(row, QN_SCOPE, String.valueOf(v.getValue())));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.expression().ifPresent(v -> addChildElement(row, QN_EXPRESSION, v));
        r.dimension().ifPresent(v -> addChildElement(row, QN_DIMENSIONS, v));
        r.setCaption().ifPresent(v -> addChildElement(row, QN_SET_CAPTION, v));
        r.setDisplayFolder().ifPresent(v -> addChildElement(row, QN_SET_DISPLAY_FOLDER, v));
    }

    private static void addMdSchemaKpisResponseRow(SOAPElement root, MdSchemaKpisResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));

        r.measureGroupName().ifPresent(v -> addChildElement(row, QN_MEASUREGROUP_NAME, v));
        r.kpiName().ifPresent(v -> addChildElement(row, QN_KPI_NAME, v));
        r.kpiCaption().ifPresent(v -> addChildElement(row, QN_KPI_CAPTION, v));
        r.kpiDescription().ifPresent(v -> addChildElement(row, QN_KPI_DESCRIPTION, v));
        r.kpiDisplayFolder().ifPresent(v -> addChildElement(row, QN_KPI_DISPLAY_FOLDER, v));
        r.kpiValue().ifPresent(v -> addChildElement(row, QN_KPI_VALUE, v));
        r.kpiGoal().ifPresent(v -> addChildElement(row, QN_KPI_GOAL, v));
        r.kpiStatus().ifPresent(v -> addChildElement(row, QN_KPI_STATUS, v));
        r.kpiTrend().ifPresent(v -> addChildElement(row, QN_KPI_TREND, v));
        r.kpiStatusGraphic().ifPresent(v -> addChildElement(row, QN_KPI_STATUS_GRAPHIC, v));
        r.kpiTrendGraphic().ifPresent(v -> addChildElement(row, QN_KPI_TREND_GRAPHIC, v));
        r.kpiWight().ifPresent(v -> addChildElement(row, QN_KPI_WEIGHT, v));
        r.kpiCurrentTimeMember().ifPresent(v -> addChildElement(row, QN_KPI_CURRENT_TIME_MEMBER, v));
        r.kpiParentKpiName().ifPresent(v -> addChildElement(row, QN_KPI_PARENT_KPI_NAME, v));
        r.annotation().ifPresent(v -> addChildElement(row, QN_ANNOTATIONS, v));
        r.scope().ifPresent(v -> addChildElement(row, QN_SCOPE, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaMeasureGroupsResponseRow(SOAPElement root, MdSchemaMeasureGroupsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));

        r.measureGroupName().ifPresent(v -> addChildElement(row, QN_MEASUREGROUP_NAME, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.isWriteEnabled().ifPresent(v -> addChildElement(row, QN_IS_WRITE_ENABLED, String.valueOf(v)));
        r.measureGroupCaption().ifPresent(v -> addChildElement(row, QN_MEASUREGROUP_CAPTION, v));
    }

    private static void addMdSchemaCubesResponseRow(SOAPElement root, MdSchemaCubesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_CATALOG_NAME, r.catalogName());
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));

        r.cubeType().ifPresent(v -> addChildElement(row, QN_CUBE_TYPE, v.name()));
        r.cubeGuid().ifPresent(v -> addChildElement(row, QN_CUBE_GUID, String.valueOf(v)));
        r.createdOn().ifPresent(v -> addChildElement(row, QN_CREATED_ON, String.valueOf(v)));
        r.lastSchemaUpdate().ifPresent(v -> addChildElement(row, QN_LAST_SCHEMA_UPDATE,  v.format(formatter)));
        r.schemaUpdatedBy().ifPresent(v -> addChildElement(row, QN_SCHEMA_UPDATED_BY, v));
        r.lastDataUpdate().ifPresent(v -> addChildElement(row, Constants.ROWSET.ROW_PROPERTY.QN_LAST_DATA_UPDATE, v.format(formatter)));
        r.dataUpdateDBy().ifPresent(v -> addChildElement(row, QN_DATA_UPDATED_BY, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.isDrillThroughEnabled()
                .ifPresent(v -> addChildElement(row, QN_IS_DRILLTHROUGH_ENABLED, String.valueOf(v)));
        r.isLinkable().ifPresent(v -> addChildElement(row, QN_IS_LINKABLE, String.valueOf(v)));
        r.isWriteEnabled().ifPresent(v -> addChildElement(row, QN_IS_WRITE_ENABLED, String.valueOf(v)));
        r.isSqlEnabled().ifPresent(v -> addChildElement(row, QN_IS_SQL_ENABLED, String.valueOf(v)));
        r.cubeCaption().ifPresent(v -> addChildElement(row, QN_CUBE_CAPTION, v));
        r.baseCubeName().ifPresent(v -> addChildElement(row, QN_BASE_CUBE_NAME, v));
        r.cubeSource().ifPresent(v -> addChildElement(row, QN_CUBE_SOURCE, String.valueOf(v.getValue())));
        r.preferredQueryPatterns()
                .ifPresent(v -> addChildElement(row, QN_PREFERRED_QUERY_PATTERNS, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaDimensionsResponseRow(SOAPElement root, MdSchemaDimensionsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> addChildElement(row, QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> addChildElement(row, QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> addChildElement(row, QN_CUBE_NAME, v));

        r.dimensionName().ifPresent(v -> addChildElement(row, QN_DIMENSION_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, QN_DIMENSION_UNIQUE_NAME, v));
        r.dimensionGuid().ifPresent(v -> addChildElement(row, QN_DIMENSION_GUID, String.valueOf(v)));
        r.dimensionCaption().ifPresent(v -> addChildElement(row, QN_DIMENSION_CAPTION, v));
        r.dimensionOptional().ifPresent(v -> addChildElement(row, QN_DIMENSION_ORDINAL, String.valueOf(v)));
        r.dimensionType().ifPresent(v -> addChildElement(row, QN_DIMENSION_TYPE, String.valueOf(v.getValue())));
        r.dimensionCardinality()
                .ifPresent(v -> addChildElement(row, QN_DIMENSION_CARDINALITY, String.valueOf(v)));
        r.defaultHierarchy().ifPresent(v -> addChildElement(row, QN_DEFAULT_HIERARCHY, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        r.isVirtual().ifPresent(v -> addChildElement(row, QN_IS_VIRTUAL, String.valueOf(v)));
        r.isReadWrite().ifPresent(v -> addChildElement(row, QN_IS_READWRITE, String.valueOf(v)));
        r.dimensionUniqueSetting().ifPresent(
                v -> addChildElement(row, QN_DIMENSION_UNIQUE_SETTINGS, String.valueOf(v.getValue())));
        r.dimensionMasterName().ifPresent(v -> addChildElement(row, QN_DIMENSION_MASTER_NAME, v));
        r.dimensionIsVisible().ifPresent(v -> addChildElement(row, QN_DIMENSION_IS_VISIBLE, String.valueOf(v)));
    }

    private static void addMdSchemaFunctionsResponseRow(SOAPElement root, MdSchemaFunctionsResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        r.functionalName().ifPresent(v -> addChildElement(row, QN_FUNCTION_NAME, v));
        r.description().ifPresent(v -> addChildElement(row, QN_DESCRIPTION, v));
        addChildElement(row, QN_PARAMETER_LIST, r.parameterList());
        r.returnType().ifPresent(v -> addChildElement(row, QN_RETURN_TYPE, String.valueOf(v)));
        r.origin().ifPresent(v -> addChildElement(row, QN_ORIGIN, String.valueOf(v.getValue())));
        r.interfaceName().ifPresent(v -> addChildElement(row, QN_INTERFACE_NAME, v.name()));
        r.libraryName().ifPresent(v -> addChildElement(row, QN_LIBRARY_NAME, v));
        r.dllName().ifPresent(v -> addChildElement(row, QN_DLL_NAME, v));
        r.helpFile().ifPresent(v -> addChildElement(row, QN_HELP_FILE, v));
        r.helpContext().ifPresent(v -> addChildElement(row, QN_HELP_CONTEXT, v));
        r.object().ifPresent(v -> addChildElement(row, QN_OBJECT, v));
        r.caption().ifPresent(v -> addChildElement(row, QN_CAPTION, v));
        r.parameterInfo().ifPresent(v -> addParameterInfoXmlList(row, v));
        r.directQueryPushable()
                .ifPresent(v -> addChildElement(row, QN_DIRECTQUERY_PUSHABLE, String.valueOf(v.getValue())));
    }

    private static void addParameterInfoXmlList(SOAPElement root, List<ParameterInfo> list) {
        if (list != null) {
            list.forEach(it -> addParameterInfoXml(root, it));
        }
    }

    private static void addParameterInfoXml(SOAPElement root, ParameterInfo it) {
        SOAPElement el = addChildElement(root, QN_PARAMETERINFO);
        addChildElement(el, QN_NAME, it.name());
        addChildElement(el, QN_DESCRIPTION, it.description());
        addChildElement(el, QN_OPTIONAL, String.valueOf(it.optional()));
        addChildElement(el, QN_REPEATABLE, String.valueOf(it.repeatable()));
        addChildElement(el, QN_REPEATGROUP, String.valueOf(it.repeatGroup()));
    }

    private static void addDiscoverPropertiesResponseRow(SOAPElement root, DiscoverPropertiesResponseRow r)
            throws SOAPException {
        SOAPElement row = root.addChildElement(Constants.ROWSET.QN_ROW);
        addChildElement(row, QN_PROPERTY_NAME_LC, r.propertyName());
        r.propertyDescription().ifPresent(v -> addChildElement(row, QN_PROPERTY_DESCRIPTION, v));
        r.propertyType().ifPresent(v -> addChildElement(row, QN_PROPERTY_TYPE_LC, v));
        addChildElement(row, QN_PROPERTY_ACCESS_TYPE, r.propertyAccessType().getValue());
        r.required().ifPresent(v -> addChildElement(row, QN_IS_REQUIRED, String.valueOf(v)));
        r.value().ifPresent(v -> addChildElement(row, QN_VALUE, v));
    }

    private static void addMdDataSet(SOAPElement el, Mddataset it) throws SOAPException {
        if (it != null) {
            addOlapInfo(el, it.olapInfo());
            addAxes(el, it.axes());
            addCellData(el, it.cellData());
            addException(el, it.exception());
            addMessages(el, it.messages());
        }
    }

    private static void addMessages(SOAPElement e, Messages it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, Constants.MDDATASET.QN_MESSAGES);
            addExceptionTypeList(el, it.warningOrError());

        }
    }

    private static void addExceptionTypeList(SOAPElement e, List<org.eclipse.daanse.xmla.api.exception.Type> list) {
        if (list != null) {
            list.forEach(it -> addExceptionType(e, it));
        }
    }

    private static void addExceptionType(SOAPElement e, org.eclipse.daanse.xmla.api.exception.Type it) {
        if (it != null) {
            if (it instanceof WarningType warningType) {
                addWarningType(e, warningType);
            }
            if (it instanceof ErrorType errorType) {
                addErrorType(e, errorType);
            }
        }
    }

    private static void addErrorType(SOAPElement e, ErrorType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Error", null);

            addChildElement(el, "Callstack", null, it.callstack());
            addChildElement(el, "ErrorCode", null, String.valueOf(it.errorCode()));
            addMessageLocation(el, it.location());
            setAttribute(el, DESCRIPTION, it.description());
            setAttribute(el, "Source", it.source());
            setAttribute(el, "HelpFile", it.helpFile());
        }
    }

    private static void setAttribute(SOAPElement el, String name, String value) {
        if (value != null) {
            el.setAttribute(name, value);
        }
    }

    private static void addWarningType(SOAPElement e, WarningType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Warning", null);
            addChildElement(el, "WarningCode", null, String.valueOf(it.warningCode()));
            addMessageLocation(el, it.location());
            addChildElement(el, DESCRIPTION_LC, null, it.description());
            addChildElement(el, "Source", null, it.source());
            addChildElement(el, "HelpFile", null, it.helpFile());
        }
    }

    private static void addMessageLocation(SOAPElement e, MessageLocation it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Location", null);
            addStartEnd(el, "Start", it.start());
            addStartEnd(el, "End", it.end());
            addChildElement(el, "LineOffset", null, String.valueOf(it.lineOffset()));
            addChildElement(el, "TextLength", null, String.valueOf(it.textLength()));
            addWarningLocationObject(el, "SourceObject", it.sourceObject());
            addWarningLocationObject(el, "DependsOnObject", it.dependsOnObject());
            addChildElement(el, "RowNumber", null, String.valueOf(it.rowNumber()));
        }

    }

    private static void addWarningLocationObject(SOAPElement e, String tagName, WarningLocationObject it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, tagName, null);
            addWarningColumn(el, it.warningColumn());
            addWarningMeasure(el, it.warningMeasure());
        }
    }

    private static void addWarningMeasure(SOAPElement e, WarningMeasure it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_WARNING_MEASURE);
            addChildElement(el, "Cube", null, it.cube());
            addChildElement(el, "MeasureGroup", null, it.measureGroup());
            addChildElement(el, "MeasureName", null, it.measureName());
        }
    }

    private static void addWarningColumn(SOAPElement e, WarningColumn it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_WARNING_COLUMN);
            addChildElement(el, "Dimension", null, it.dimension());
            addChildElement(el, "Attribute", null, it.attribute());
        }
    }

    private static void addStartEnd(SOAPElement e, String tagName, StartEnd it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, tagName, null);
            addChildElement(el, "Line", null, String.valueOf(it.line()));
            addChildElement(el, "Column", null, String.valueOf(it.column()));
        }
    }

    private static void addException(SOAPElement e, Exception it) {
        if (it != null) {
            addChildElement(e, "Exception", null);
        }
    }

    private static void addCellData(SOAPElement e, CellData it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_CELL_DATA);
            addCellTypeList(el, it.cell());
            // addCellSetType(el, it.cellSet());
        }
    }

    private static void addData(SOAPElement e, byte[] it) {
        if (it != null) {
            addChildElement(e, "Data", new String(it, UTF_8));
        }
    }

    private static void addCellTypeList(SOAPElement el, List<CellType> list) {
        if (list != null) {
            list.forEach(it -> addCellType(el, it));
        }
    }

    private static void addCellType(SOAPElement e, CellType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_CELL);
            addCellTypeValue(el, it.value());
            addCellInfoItemList(el, it.any());
            setAttribute(el, "CellOrdinal", String.valueOf(it.cellOrdinal()));
        }
    }

    private static void addCellTypeValue(SOAPElement e, Value it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, Constants.MDDATASET.QN_VALUE);
            el.setAttribute("xsi:type", it.type().getValue());
            el.setTextContent(it.value());
            addCellTypeErrorList(el, it.error());
        }
    }

    private static void addCellTypeErrorList(SOAPElement el, List<CellTypeError> list) {
        if (list != null) {
            list.forEach(it -> addCellTypeError(el, it));
        }
    }

    private static void addCellTypeError(SOAPElement e, CellTypeError it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, Constants.MDDATASET.QN_ERROR);
            setAttribute(el, "ErrorCode", String.valueOf(it.errorCode()));
            setAttribute(el, DESCRIPTION, it.description());
        }
    }

    private static void addAxes(SOAPElement e, Axes it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, Constants.MDDATASET.QN_AXES);
            addAxisList(el, it.axis());
        }
    }

    private static void addAxisList(SOAPElement e, List<Axis> list) {
        if (list != null) {
            list.forEach(it -> addAxis(e, it));
        }
    }

    private static void addAxis(SOAPElement e, Axis it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_AXIS);
            addTypeList(el, it.setType());
            setAttribute(el, "name", it.name());
        }
    }

    private static void addTypeList(SOAPElement e, List<Type> list) {
        if (list != null) {
            list.forEach(it -> addType(e, it));
        }
    }

    private static void addType(SOAPElement soapElement, Type type) {
        if (type != null) {
            if (type instanceof MembersType membersType) {
                addMembersType(soapElement, membersType);
            }
            if (type instanceof TuplesType tuplesType) {
                addTuplesType(soapElement, tuplesType);
            }
            if (type instanceof SetListType setListType) {
                addSetListType(soapElement, setListType);
            }
            if (type instanceof NormTupleSet normTupleSet) {
                addNormTupleSet(soapElement, normTupleSet);
            }
            if (type instanceof Union union) {
                addUnion(soapElement, union);
            }
        }
    }

    private static void addUnion(SOAPElement soapElement, Union union) {
        if (union != null) {
            SOAPElement el = addChildElement(soapElement, QN_UNION);
            addTypeList(el, union.setType());
        }
    }

    private static void addNormTupleSet(SOAPElement soapElement, NormTupleSet normTupleSet) {
        if (normTupleSet != null) {
            SOAPElement el = addChildElement(soapElement, QN_NORM_TUPLE_SET);
            addNormTuplesType(el, normTupleSet.normTuples());
            addTupleTypeList(el, normTupleSet.membersLookup());
        }
    }

    private static void addTupleTypeList(SOAPElement soapElement, MembersLookup membersLookup) {
        if (membersLookup != null) {
            SOAPElement el = addChildElement(soapElement, QN_MEMBERS_LOOKUP);
            if (membersLookup.members() != null) {
                membersLookup.members().forEach(it -> addTupleTypeMembers(el, it));
            }
        }
    }

    private static void addNormTuplesType(SOAPElement e, NormTuplesType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_NORM_TUPLES);
            addNormTupleList(el, it.normTuple());
        }
    }

    private static void addNormTupleList(SOAPElement el, List<NormTuple> list) {
        if (list != null) {
            list.forEach(it -> addNormTuple(el, it));
        }
    }

    private static void addNormTuple(SOAPElement e, NormTuple it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_NORM_TUPLE);
            addMemberRefList(el, it.memberRef());
        }
    }

    private static void addMemberRefList(SOAPElement e, List<MemberRef> list) {
        if (list != null) {
            list.forEach(it -> addMemberRef(e, it));
        }
    }

    private static void addMemberRef(SOAPElement e, MemberRef it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_MEMBER_REF);
            addChildElement(el, QN_MEMBER_ORDINAL, String.valueOf(it.memberOrdinal()));
            addChildElement(el, QN_MEMBER_DISP_INFO, String.valueOf(it.memberDispInfo()));
        }
    }

    private static void addSetListType(SOAPElement e, SetListType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_CROSS_PRODUCT);
            addTypeList(el, it.setType());
            addChildElement(el, Constants.MDDATASET.QN_SIZE, String.valueOf(it.size()));
        }
    }

    private static void addTuplesType(SOAPElement e, TuplesType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_TUPLES);
            addTuplesTypeList(el, it.tuple());
        }

    }

    private static void addTuplesTypeList(SOAPElement e, List<TupleType> list) {
        if (list != null) {
            list.forEach(it -> addTupleTypeTuple(e, it));
        }
    }

    private static void addTupleTypeMembers(SOAPElement e, TupleType it) {
        if (it != null && it.member() != null) {
            SOAPElement el = addChildElement(e, QN_MEMBERS);
            it.member().forEach(item -> addMemberType(el, item));
        }

    }

    private static void addTupleTypeTuple(SOAPElement e, TupleType it) {
        if (it != null && it.member() != null) {
            SOAPElement el = addChildElement(e, QN_TUPLE);
            it.member().forEach(item -> addMemberType(el, item));
        }
    }

    private static void addMemberType(SOAPElement e, MemberType it) {
        if (it != null) {
            SOAPElement seMember = addChildElement(e, QN_MEMBER);
            addCellInfoItemList(seMember, it.any());
            setAttribute(seMember, "Hierarchy", it.hierarchy());
        }
    }

    private static void addMembersType(SOAPElement e, MembersType it) {
        if (it != null && it.member() != null) {
            SOAPElement el = addChildElement(e, QN_MEMBERS);
            it.member().forEach(item -> addMemberType(el, item));
            setAttribute(e, "Hierarchy", it.hierarchy());
        }
    }

    private static void addOlapInfo(SOAPElement e, OlapInfo it) throws SOAPException {
        if (it != null) {
            SOAPElement seOlapInfo = e.addChildElement(Constants.MDDATASET.QN_OLAP_INFO);
            addCubeInfo(seOlapInfo, it.cubeInfo());
            addAxesInfo(seOlapInfo, it.axesInfo());
            addCellInfo(seOlapInfo, it.cellInfo());
        }
    }

    private static void addCellInfo(SOAPElement e, CellInfo it) throws SOAPException {
        if (it != null) {
            SOAPElement seCellInfo = e.addChildElement(Constants.MDDATASET.QN_CELL_INFO);
            addCellInfoItemListName(seCellInfo, it.any());
        }
    }

    private static void addCellInfoItemListName(SOAPElement e, List<CellInfoItem> list) {
        if (list != null) {
            list.forEach(it -> addCellInfoItemName(e, it));
        }
    }

    private static void addCellInfoItemList(SOAPElement e, List<CellInfoItem> list) {
        if (list != null) {
            list.forEach(it -> addCellInfoItem(e, it));
        }
    }

    private static void addCellInfoItemName(SOAPElement e, CellInfoItem it) {
        if (it != null) {
            String prefix = Constants.MDDATASET.PREFIX;
            SOAPElement el = addChildElement(e, it.tagName(), prefix);
            setAttribute(el, "name", it.name());
            it.type().ifPresent(v -> setAttribute(el, "type", v));
        }
    }

    private static void addCellInfoItem(SOAPElement e, CellInfoItem it) {
        if (it != null) {
            String prefix = Constants.MDDATASET.PREFIX;
            SOAPElement el = addChildElement(e, it.tagName(), prefix);
            el.setTextContent(it.name());
            it.type().ifPresent(v -> setAttribute(el, "type", v));
        }
    }

    private static void addAxesInfo(SOAPElement e, AxesInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_AXES_INFO);
            addAxisInfoList(el, it.axisInfo());
        }
    }

    private static void addAxisInfoList(SOAPElement el, List<AxisInfo> list) {
        if (list != null) {
            list.forEach(it -> addAxisInfo(el, it));
        }
    }

    private static void addAxisInfo(SOAPElement e, AxisInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_AXIS_INFO);
            setAttribute(el, "name", it.name());
            addHierarchyInfoList(el, it.hierarchyInfo());
        }
    }

    private static void addHierarchyInfoList(SOAPElement el, List<HierarchyInfo> list) {
        if (list != null) {
            list.forEach(it -> addHierarchyInfo(el, it));
        }
    }

    private static void addHierarchyInfo(SOAPElement e, HierarchyInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_HIERARCHY_INFO);
            addCellInfoItemListName(el, it.any());
            setAttribute(el, "name", it.name());
        }
    }

    private static void addCubeInfo(SOAPElement e, CubeInfo it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, QN_CUBE_INFO);
            addOlapInfoCubeList(el, it.cube());
        }
    }

    private static void addOlapInfoCubeList(SOAPElement e, List<OlapInfoCube> list) {
        if (list != null) {
            list.forEach(it -> addOlapInfoCube(e, it));
        }
    }

    private static void addOlapInfoCube(SOAPElement e, OlapInfoCube it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, Constants.MDDATASET.QN_CUBE);
            addChildElement(el, Constants.MDDATASET.QN_CUBE_NAME, it.cubeName());
            addChildElement(el, Constants.ENGINE.QN_LAST_DATA_UPDATE, instantToString(it.lastDataUpdate()));
            addChildElement(el, Constants.ENGINE.QN_LAST_SCHEMA_UPDATE, instantToString(it.lastSchemaUpdate()));
        }
    }

    private static String instantToString(Instant instant) {
        return instant != null ? formatter.format(instant) : null;
    }

    private static SOAPElement addDiscoverRowSetsRoot(SOAPElement body) throws SOAPException {

        SOAPElement seDiscoverResponse = body.addChildElement(Constants.MSXMLA.QN_DISCOVER_RESPONSE);
        SOAPElement seReturn = seDiscoverResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = addChildElement(seReturn, Constants.ROWSET.QN_ROOT);
        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        SOAPElement schema = addChildElement(seRoot, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("xmlns", Constants.ROWSET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("targetNamespace", Constants.ROWSET.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement el = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s = addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement se = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st = addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        st.setAttribute("name", "uuid");
        SOAPElement r = addChildElement(st, Constants.XSD.QN_RESTRICTION);
        r.setAttribute("base", "xsd:string");
        SOAPElement p = addChildElement(r, Constants.XSD.QN_PATTERN);
        p.setAttribute("value", "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}");

        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "SchemaName");
        s1e1.setAttribute("name", "SchemaName");
        s1e1.setAttribute("type", "xsd:string");
        SOAPElement s1e2 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e2.setAttribute("sql:field", "SchemaGuid");
        s1e2.setAttribute("name", "SchemaGuid");
        s1e2.setAttribute("type", "uuid");
        s1e2.setAttribute("minOccurs", "0");
        SOAPElement s1e3 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e3.setAttribute("sql:field", "Restrictions");
        s1e3.setAttribute("name", "Restrictions");
        s1e3.setAttribute("minOccurs", "0");
        s1e3.setAttribute("maxOccurs", "unbounded");
        SOAPElement s1e3ct = addChildElement(s1e3, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s1e3cts = addChildElement(s1e3ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e3ctse1 = addChildElement(s1e3cts, Constants.XSD.QN_ELEMENT);
        s1e3ctse1.setAttribute("name", "Name");
        s1e3ctse1.setAttribute("type", "xsd:string");
        s1e3ctse1.setAttribute("sql:field", "Name");
        SOAPElement s1e3ctse2 = addChildElement(s1e3cts, Constants.XSD.QN_ELEMENT);
        s1e3ctse2.setAttribute("name", "Type");
        s1e3ctse2.setAttribute("type", "xsd:string");
        s1e3ctse2.setAttribute("sql:field", "Type");

        SOAPElement s1e4 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e4.setAttribute("sql:field", "Description");
        s1e4.setAttribute("name", "Description");
        s1e4.setAttribute("type", "xsd:string");

        SOAPElement s1e5 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e5.setAttribute("sql:field", "RestrictionsMask");
        s1e5.setAttribute("name", "RestrictionsMask");
        s1e5.setAttribute("type", "xsd:unsignedLong");
        s1e5.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private static SOAPElement addDiscoverPropertiesRoot(SOAPElement body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:msxmla", "http://schemas.microsoft.com/analysisservices/2003/xmla");
        SOAPElement schema = addChildElement(seRoot, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("targetNamespace", Constants.ROWSET.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement el = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s = addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement se = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st = addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        st.setAttribute("name", "uuid");
        SOAPElement r = addChildElement(st, Constants.XSD.QN_RESTRICTION);
        r.setAttribute("base", "xsd:string");
        SOAPElement p = addChildElement(r, Constants.XSD.QN_PATTERN);
        p.setAttribute("value", "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}");

        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "xmlDocument");
        SOAPElement s1 = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement a = addChildElement(s1, Constants.XSD.QN_ANY);

        SOAPElement ct2 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s2 = addChildElement(ct2, Constants.XSD.QN_SEQUENCE);
        SOAPElement s2e1 = addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e1.setAttribute("sql:field", "PropertyName");
        s2e1.setAttribute("name", "PropertyName");
        s2e1.setAttribute("type", "xsd:string");

        SOAPElement s2e2 = addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e2.setAttribute("sql:field", "PropertyDescription");
        s2e2.setAttribute("name", "PropertyDescription");
        s2e2.setAttribute("type", "xsd:string");
        s2e2.setAttribute("minOccurs", "0");

        SOAPElement s2e3 = addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e3.setAttribute("sql:field", "PropertyType");
        s2e3.setAttribute("name", "PropertyType");
        s2e3.setAttribute("type", "xsd:string");
        s2e3.setAttribute("minOccurs", "0");

        SOAPElement s2e4 = addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e4.setAttribute("sql:field", "PropertyAccessType");
        s2e4.setAttribute("name", "PropertyAccessType");
        s2e4.setAttribute("type", "xsd:string");

        SOAPElement s2e5 = addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e5.setAttribute("sql:field", "IsRequired");
        s2e5.setAttribute("name", "IsRequired");
        s2e5.setAttribute("type", "xsd:boolean");
        s2e5.setAttribute("minOccurs", "0");

        SOAPElement s2e6 = addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e6.setAttribute("sql:field", "Value");
        s2e6.setAttribute("name", "Value");
        s2e6.setAttribute("type", "xsd:string");
        s2e6.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private static SOAPElement addDbSchemaCatalogsRoot(SOAPElement body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "CATALOG_NAME");
        s1e1.setAttribute("name", "CATALOG_NAME");
        s1e1.setAttribute("type", "xsd:string");

        SOAPElement s1e2 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e2.setAttribute("sql:field", "DESCRIPTION");
        s1e2.setAttribute("name", "DESCRIPTION");
        s1e2.setAttribute("type", "xsd:string");
        s1e2.setAttribute("minOccurs", "0");

        SOAPElement s1e3 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e3.setAttribute("sql:field", "ROLES");
        s1e3.setAttribute("name", "ROLES");
        s1e3.setAttribute("type", "xsd:string");

        SOAPElement s1e4 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e4.setAttribute("sql:field", "DATE_MODIFIED");
        s1e4.setAttribute("name", "DATE_MODIFIED");
        s1e4.setAttribute("type", "xsd:dateTime");
        s1e4.setAttribute("minOccurs", "0");
        return seRoot;
    }

    private static SOAPElement addDiscoverEnumeratorsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "EnumName");
        s1e1.setAttribute("name", "EnumName");
        s1e1.setAttribute("type", "xsd:string");

        SOAPElement s1e2 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e2.setAttribute("sql:field", "EnumDescription");
        s1e2.setAttribute("name", "EnumDescription");
        s1e2.setAttribute("type", "xsd:string");
        s1e2.setAttribute("minOccurs", "0");

        SOAPElement s1e3 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e3.setAttribute("sql:field", "EnumType");
        s1e3.setAttribute("name", "EnumType");
        s1e3.setAttribute("type", "xsd:string");

        SOAPElement s1e4 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e4.setAttribute("sql:field", "ElementName");
        s1e4.setAttribute("name", "ElementName");
        s1e4.setAttribute("type", "xsd:string");

        SOAPElement s1e5 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e5.setAttribute("sql:field", "ElementDescription");
        s1e5.setAttribute("name", "ElementDescription");
        s1e5.setAttribute("type", "xsd:string");
        s1e5.setAttribute("minOccurs", "0");

        SOAPElement s1e6 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e6.setAttribute("sql:field", "ElementValue");
        s1e6.setAttribute("name", "ElementValue");
        s1e6.setAttribute("type", "xsd:string");
        s1e6.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private static SOAPElement addDiscoverKeywordsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "Keyword");
        s1e1.setAttribute("name", "Keyword");
        s1e1.setAttribute("type", "xsd:string");

        return seRoot;
    }

    private static SOAPElement addDiscoverLiteralsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        SOAPElement se1 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se1.setAttribute("sql:field", "LiteralName");
        se1.setAttribute("name", "LiteralName");
        se1.setAttribute("type", "xsd:string");

        SOAPElement se2 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se2.setAttribute("sql:field", "LiteralValue");
        se2.setAttribute("name", "LiteralValue");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se3.setAttribute("sql:field", "LiteralInvalidChars");
        se3.setAttribute("name", "LiteralInvalidChars");
        se3.setAttribute("type", "xsd:string");
        se3.setAttribute("minOccurs", "0");

        SOAPElement se4 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se4.setAttribute("sql:field", "LiteralInvalidStartingChars");
        se4.setAttribute("name", "LiteralInvalidStartingChars");
        se4.setAttribute("type", "xsd:string");
        se4.setAttribute("minOccurs", "0");

        SOAPElement se5 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se5.setAttribute("sql:field", "LiteralMaxLength");
        se5.setAttribute("name", "LiteralMaxLength");
        se5.setAttribute("type", "xsd:int");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se6.setAttribute("sql:field", "LiteralNameEnumValue");
        se6.setAttribute("name", "LiteralNameEnumValue");
        se6.setAttribute("type", "xsd:int");
        se6.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private static SOAPElement addDbSchemaTablesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);

        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        SOAPElement se1 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se1.setAttribute("sql:field", "TABLE_CATALOG");
        se1.setAttribute("name", "TABLE_CATALOG");
        se1.setAttribute("type", "xsd:string");

        SOAPElement se2 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se2.setAttribute("sql:field", "TABLE_SCHEMA");
        se2.setAttribute("name", "TABLE_SCHEMA");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se3.setAttribute("sql:field", "TABLE_NAME");
        se3.setAttribute("name", "TABLE_NAME");
        se3.setAttribute("type", "xsd:string");

        SOAPElement se4 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se4.setAttribute("sql:field", "TABLE_TYPE");
        se4.setAttribute("name", "TABLE_TYPE");
        se4.setAttribute("type", "xsd:string");

        SOAPElement se5 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se5.setAttribute("sql:field", "TABLE_GUID");
        se5.setAttribute("name", "TABLE_GUID");
        se5.setAttribute("type", "uuid");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se6.setAttribute("sql:field", "DESCRIPTION");
        se6.setAttribute("name", "DESCRIPTION");
        se6.setAttribute("type", "xsd:string");
        se6.setAttribute("minOccurs", "0");

        SOAPElement se7 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se7.setAttribute("sql:field", "TABLE_PROPID");
        se7.setAttribute("name", "TABLE_PROPID");
        se7.setAttribute("type", "xsd:unsignedInt");
        se7.setAttribute("minOccurs", "0");

        SOAPElement se8 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se8.setAttribute("sql:field", "DATE_CREATED");
        se8.setAttribute("name", "DATE_CREATED");
        se8.setAttribute("type", "xsd:dateTime");
        se8.setAttribute("minOccurs", "0");

        SOAPElement se9 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se9.setAttribute("sql:field", "DATE_MODIFIED");
        se9.setAttribute("name", "DATE_MODIFIED");
        se9.setAttribute("type", "xsd:dateTime");
        se9.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private static SOAPElement addMdSchemaCubesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);

        SOAPElement se1 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se1.setAttribute("sql:field", "CATALOG_NAME");
        se1.setAttribute("name", "CATALOG_NAME");
        se1.setAttribute("type", "xsd:string");
        se1.setAttribute("minOccurs", "0");

        SOAPElement se2 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se2.setAttribute("sql:field", "SCHEMA_NAME");
        se2.setAttribute("name", "SCHEMA_NAME");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se3.setAttribute("sql:field", "CUBE_NAME");
        se3.setAttribute("name", "CUBE_NAME");
        se3.setAttribute("type", "xsd:string");

        SOAPElement se4 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se4.setAttribute("sql:field", "CUBE_TYPE");
        se4.setAttribute("name", "CUBE_TYPE");
        se4.setAttribute("type", "xsd:string");

        SOAPElement se5 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se5.setAttribute("sql:field", "CUBE_GUID");
        se5.setAttribute("name", "CUBE_GUID");
        se5.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se6 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se6.setAttribute("sql:field", "CREATED_ON");
        se6.setAttribute("name", "CREATED_ON");
        se6.setAttribute("type", "xsd:dateTime");
        se6.setAttribute("minOccurs", "0");

        SOAPElement se7 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se7.setAttribute("sql:field", "LAST_SCHEMA_UPDATE");
        se7.setAttribute("name", "LAST_SCHEMA_UPDATE");
        se7.setAttribute("type", "xsd:dateTime");
        se7.setAttribute("minOccurs", "0");

        SOAPElement se8 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se8.setAttribute("sql:field", "SCHEMA_UPDATED_BY");
        se8.setAttribute("name", "SCHEMA_UPDATED_BY");
        se8.setAttribute("type", "xsd:string");
        se8.setAttribute("minOccurs", "0");

        SOAPElement se9 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se9.setAttribute("sql:field", "LAST_DATA_UPDATE");
        se9.setAttribute("name", "LAST_DATA_UPDATE");
        se9.setAttribute("type", "xsd:dateTime");
        se9.setAttribute("minOccurs", "0");

        SOAPElement se10 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se10.setAttribute("sql:field", "DATA_UPDATED_BY");
        se10.setAttribute("name", "DATA_UPDATED_BY");
        se10.setAttribute("type", "xsd:string");
        se10.setAttribute("minOccurs", "0");

        SOAPElement se11 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se11.setAttribute("sql:field", "DESCRIPTION");
        se11.setAttribute("name", "DESCRIPTION");
        se11.setAttribute("type", "xsd:string");
        se11.setAttribute("minOccurs", "0");

        SOAPElement se12 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se12.setAttribute("sql:field", "IS_DRILLTHROUGH_ENABLED");
        se12.setAttribute("name", "IS_DRILLTHROUGH_ENABLED");
        se12.setAttribute("type", "xsd:boolean");

        SOAPElement se13 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se13.setAttribute("sql:field", "IS_LINKABLE");
        se13.setAttribute("name", "IS_LINKABLE");
        se13.setAttribute("type", "xsd:boolean");

        SOAPElement se14 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se14.setAttribute("sql:field", "IS_WRITE_ENABLED");
        se14.setAttribute("name", "IS_WRITE_ENABLED");
        se14.setAttribute("type", "xsd:boolean");

        SOAPElement se15 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se15.setAttribute("sql:field", "IS_SQL_ENABLED");
        se15.setAttribute("name", "IS_SQL_ENABLED");
        se15.setAttribute("type", "xsd:boolean");

        SOAPElement se16 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se16.setAttribute("sql:field", "CUBE_CAPTION");
        se16.setAttribute("name", "CUBE_CAPTION");
        se16.setAttribute("type", "xsd:string");
        se16.setAttribute("minOccurs", "0");

        SOAPElement se17 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se17.setAttribute("sql:field", "BASE_CUBE_NAME");
        se17.setAttribute("name", "BASE_CUBE_NAME");
        se17.setAttribute("type", "xsd:string");
        se17.setAttribute("minOccurs", "0");

        SOAPElement se18 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se18.setAttribute("sql:field", "DIMENSIONS");
        se18.setAttribute("name", "DIMENSIONS");
        se18.setAttribute("minOccurs", "0");

        SOAPElement se19 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se19.setAttribute("sql:field", "SETS");
        se19.setAttribute("name", "SETS");
        se19.setAttribute("minOccurs", "0");

        SOAPElement se20 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se20.setAttribute("sql:field", "MEASURES");
        se20.setAttribute("name", "MEASURES");
        se20.setAttribute("minOccurs", "0");

        SOAPElement se21 = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se21.setAttribute("sql:field", "CUBE_SOURCE");
        se21.setAttribute("name", "CUBE_SOURCE");
        se21.setAttribute("type", "xsd:int");
        se21.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private static SOAPElement addMdSchemaHierarchiesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);
        SOAPElement s = prepareSequenceElement(schema);

        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_GUID", "uuid", "0");
        addElement(s, "HIERARCHY_CAPTION", "xsd:string", null);
        addElement(s, "DIMENSION_TYPE", "xsd:short", null);
        addElement(s, "HIERARCHY_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "DEFAULT_MEMBER", "xsd:string", "0");
        addElement(s, "ALL_MEMBER", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "STRUCTURE", "xsd:short", null);
        addElement(s, "IS_VIRTUAL", "xsd:boolean", null);
        addElement(s, "IS_READWRITE", "xsd:boolean", null);
        addElement(s, "DIMENSION_UNIQUE_SETTINGS", "xsd:int", null);
        addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "HIERARCHY_ORDINAL", "xsd:unsignedInt", null);
        addElement(s, "DIMENSION_IS_SHARED", "xsd:boolean", null);
        addElement(s, "HIERARCHY_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "HIERARCHY_ORIGIN", "xsd:unsignedShort", "0");
        addElement(s, "HIERARCHY_DISPLAY_FOLDER", "xsd:string", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "HIERARCHY_VISIBILITY", "xsd:unsignedShort", "0");
        addElement(s, "PARENT_CHILD", "xsd:boolean", "0");
        addElement(s, "LEVELS", null, "0");
        return seRoot;
    }

    private static SOAPElement prepareSequenceElement(SOAPElement schema) throws SOAPException {
        SOAPElement ct = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct.setAttribute("name", "row");
        return addChildElement(ct, Constants.XSD.QN_SEQUENCE);
    }

    private static SOAPElement prepareRootElement(SOAPElement body) throws SOAPException {
        SOAPElement seDiscoverResponse = body.addChildElement(Constants.MSXMLA.QN_DISCOVER_RESPONSE);
        SOAPElement seReturn = seDiscoverResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        return seReturn.addChildElement(Constants.ROWSET.QN_ROOT);
    }

    private static SOAPElement addMdSchemaMeasuresRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASURE_NAME", "xsd:string", null);
        addElement(s, "MEASURE_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "MEASURE_CAPTION", "xsd:string", null);
        addElement(s, "MEASURE_GUID", "uuid", "0");
        addElement(s, "MEASURE_AGGREGATOR", "xsd:int", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "MEASURE_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "LEVELS_LIST", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "MEASURE_DISPLAY_FOLDER", "xsd:string", "0");
        addElement(s, "DEFAULT_FORMAT_STRING", "xsd:string", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "MEASURE_VISIBILITY", "xsd:unsignedShort", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaDimensionsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_GUID", "uuid", "0");
        addElement(s, "DIMENSION_CAPTION", "xsd:string", null);
        addElement(s, "DIMENSION_ORDINAL", "xsd:unsignedInt", null);
        addElement(s, "DIMENSION_TYPE", "xsd:short", null);
        addElement(s, "DIMENSION_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "DEFAULT_HIERARCHY", "xsd:string", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "IS_VIRTUAL", "xsd:boolean", "0");
        addElement(s, "IS_READWRITE", "xsd:boolean", "0");
        addElement(s, "DIMENSION_UNIQUE_SETTINGS", "xsd:int", "0");
        addElement(s, "DIMENSION_MASTER_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", "0");
        addElement(s, "HIERARCHIES", null, "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaMeasureGroupsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "IS_WRITE_ENABLED", "xsd:boolean", "0");
        addElement(s, "MEASUREGROUP_CAPTION", "xsd:string", "0");
        return seRoot;
    }


    private static SOAPElement addMdSchemaKpiRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);
        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "KPI_NAME", "xsd:string", "0");
        addElement(s, "KPI_CAPTION", "xsd:string", "0");
        addElement(s, "KPI_DESCRIPTION", "xsd:string", "0");
        addElement(s, "KPI_DISPLAY_FOLDER", "xsd:string", "0");
        addElement(s, "KPI_VALUE", "xsd:string", "0");
        addElement(s, "KPI_GOAL", "xsd:string", "0");
        addElement(s, "KPI_STATUS", "xsd:string", "0");
        addElement(s, "KPI_TREND", "xsd:string", "0");
        addElement(s, "KPI_STATUS_GRAPHIC", "xsd:string", "0");
        addElement(s, "KPI_TREND_GRAPHIC", "xsd:string", "0");
        addElement(s, "KPI_WEIGHT", "xsd:string", "0");
        addElement(s, "KPI_CURRENT_TIME_MEMBER", "xsd:string", "0");
        addElement(s, "KPI_PARENT_KPI_NAME", "xsd:string", "0");
        addElement(s, "ANNOTATIONS", "xsd:string", "0");
        addElement(s, "SCOPE", "xsd:int", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaMeasureGroupDimensionsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "MEASUREGROUP_CARDINALITY", "xsd:string", "0");
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "DIMENSION_CARDINALITY", "xsd:string", "0");
        addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", "0");
        addElement(s, "DIMENSION_IS_FACT_DIMENSION", "xsd:boolean", "0");
        addElement(s, "DIMENSION_PATH", "xsd:string", "0");
        addElement(s, "DIMENSION_GRANULARITY", "xsd:string", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaLevelsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_NAME", "xsd:string", null);
        addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_GUID", "uuid", "0");
        addElement(s, "LEVEL_CAPTION", "xsd:string", null);
        addElement(s, "LEVEL_NUMBER", "xsd:unsignedInt", null);
        addElement(s, "LEVEL_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "LEVEL_TYPE", "xsd:int", null);
        addElement(s, "CUSTOM_ROLLUP_SETTINGS", "xsd:int", null);
        addElement(s, "LEVEL_UNIQUE_SETTINGS", "xsd:int", null);
        addElement(s, "LEVEL_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "LEVEL_ORIGIN", "xsd:unsignedShort", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "LEVEL_VISIBILITY", "xsd:unsignedShort", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaSetsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "SET_NAME", "xsd:string", null);
        addElement(s, "SCOPE", "xsd:int", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "EXPRESSION", "xsd:string", "0");
        addElement(s, "DIMENSIONS", "xsd:string", "0");
        addElement(s, "SET_CAPTION", "xsd:string", "0");
        addElement(s, "SET_DISPLAY_FOLDER", "xsd:string", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaPropertiesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "MEMBER_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "PROPERTY_TYPE", "xsd:short", null);
        addElement(s, "PROPERTY_NAME", "xsd:string", null);
        addElement(s, "PROPERTY_CAPTION", "xsd:string", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "PROPERTY_CONTENT_TYPE", "xsd:short", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "PROPERTY_ORIGIN", "xsd:unsignedShort", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "PROPERTY_VISIBILITY", "xsd:unsignedShort", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaMembersRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_NUMBER", "xsd:unsignedInt", null);
        addElement(s, "MEMBER_ORDINAL", "xsd:unsignedInt", null);
        addElement(s, "MEMBER_NAME", "xsd:string", null);
        addElement(s, "MEMBER_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "MEMBER_TYPE", "xsd:int", null);
        addElement(s, "MEMBER_GUID", "uuid", "0");
        addElement(s, "MEMBER_CAPTION", "xsd:string", null);
        addElement(s, "CHILDREN_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "PARENT_LEVEL", "xsd:unsignedInt", null);
        addElement(s, "PARENT_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "PARENT_COUNT", "xsd:unsignedInt", null);
        addElement(s, "TREE_OP", "xsd:string", "0");
        addElement(s, "DEPTH", "xsd:int", "0");
        return seRoot;
    }

    private static SOAPElement addDbSchemaTablesInfoRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "TABLE_CATALOG", "xsd:string", "0");
        addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        addElement(s, "TABLE_NAME", "xsd:string", null);
        addElement(s, "TABLE_TYPE", "xsd:string", null);
        addElement(s, "TABLE_GUID", "uuid", "0");
        addElement(s, "BOOKMARKS", "xsd:boolean", null);
        addElement(s, "BOOKMARK_TYPE", "xsd:int", "0");
        addElement(s, "BOOKMARK_DATATYPE", "xsd:unsignedShort", "0");
        addElement(s, "BOOKMARK_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        addElement(s, "BOOKMARK_INFORMATION", "xsd:unsignedInt", "0");
        addElement(s, "TABLE_VERSION", "xsd:long", "0");
        addElement(s, "CARDINALITY", "xsd:unsignedLong", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "TABLE_PROPID", "xsd:unsignedInt", "0");
        return seRoot;
    }

    private static SOAPElement addDbSchemaSourceTablesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "TABLE_CATALOG", "xsd:string", "0");
        addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        addElement(s, "TABLE_NAME", "xsd:string", null);
        addElement(s, "TABLE_TYPE", "xsd:string", null);
        return seRoot;
    }

    private static SOAPElement addDbSchemaSchemataRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", null);
        addElement(s, "SCHEMA_NAME", "xsd:string", null);
        addElement(s, "SCHEMA_OWNER", "xsd:string", null);
        return seRoot;
    }

    private static SOAPElement addDbSchemaProviderTypesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "TYPE_NAME", "xsd:string", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "COLUMN_SIZE", "xsd:unsignedInt", null);
        addElement(s, "LITERAL_PREFIX", "xsd:string", "0");
        addElement(s, "LITERAL_SUFFIX", "xsd:string", "0");
        addElement(s, "IS_NULLABLE", "xsd:boolean", "0");
        addElement(s, "CASE_SENSITIVE", "xsd:boolean", "0");
        addElement(s, "SEARCHABLE", "xsd:unsignedInt", "0");
        addElement(s, "UNSIGNED_ATTRIBUTE", "xsd:boolean", "0");
        addElement(s, "FIXED_PREC_SCALE", "xsd:boolean", "0");
        addElement(s, "AUTO_UNIQUE_VALUE", "xsd:boolean", "0");
        addElement(s, "IS_LONG", "xsd:boolean", "0");
        addElement(s, "BEST_MATCH", "xsd:boolean", "0");
        return seRoot;
    }

    private static SOAPElement addDbSchemaColumnsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "TABLE_CATALOG", "xsd:string", null);
        addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        addElement(s, "TABLE_NAME", "xsd:string", null);
        addElement(s, "COLUMN_NAME", "xsd:string", null);
        addElement(s, "ORDINAL_POSITION", "xsd:unsignedInt", null);
        addElement(s, "COLUMN_HAS_DEFAULT", "xsd:boolean", "0");
        addElement(s, "COLUMN_FLAGS", "xsd:unsignedInt", null);
        addElement(s, "IS_NULLABLE", "xsd:boolean", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "CHARACTER_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        addElement(s, "CHARACTER_OCTET_LENGTH", "xsd:unsignedInt", "0");
        addElement(s, "NUMERIC_PRECISION", "xsd:unsignedShort", "0");
        addElement(s, "NUMERIC_SCALE", "xsd:short", "0");
        return seRoot;
    }

    private static SOAPElement addDiscoverXmlMetaDataRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "METADATA", "xsd:string", null);
        addElement(s, "ObjectType", "xsd:string", "0");
        addElement(s, "DatabaseID", "xsd:string", "0");
        return seRoot;
    }

    private static SOAPElement addDiscoverDataSourcesRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "LiteralName", "xsd:string", null);
        addElement(s, "LiteralValue", "xsd:string", "0");
        addElement(s, "LiteralInvalidChars", "xsd:string", "0");
        addElement(s, "LiteralInvalidStartingChars", "xsd:string", "0");
        addElement(s, "LiteralMaxLength", "xsd:int", "0");
        addElement(s, "LiteralNameEnumValue", "xsd:int", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaActionsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", "0");
        addElement(s, "ACTION_NAME", "xsd:string", "0");
        addElement(s, "ACTION_TYPE", "xsd:int", "0");
        addElement(s, "COORDINATE", "xsd:string", "0");
        addElement(s, "COORDINATE_TYPE", "xsd:int", "0");
        addElement(s, "ACTION_CAPTION", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "CONTENT", "xsd:string", "0");
        addElement(s, "APPLICATION", "xsd:string", "0");
        addElement(s, "INVOCATION", "xsd:int", "0");
        return seRoot;
    }

    private static SOAPElement addMdSchemaFunctionsRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = prepareRootElement(body);
        SOAPElement schema = fillRoot(seRoot);

        SOAPElement s = prepareSequenceElement(schema);
        addElement(s, "FUNCTION_NAME", "xsd:string", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "PARAMETER_LIST", "xsd:string", "0");
        addElement(s, "RETURN_TYPE", "xsd:int", null);
        addElement(s, "ORIGIN", "xsd:int", null);
        addElement(s, "INTERFACE_NAME", "xsd:string", null);
        addElement(s, "LIBRARY_NAME", "xsd:string", "0");
        addElement(s, "CAPTION", "xsd:string", "0");
        return seRoot;
    }

    private static void addElement(SOAPElement s, String name, String type, String minOccurs) {
        SOAPElement se = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("sql:field", name);
        se.setAttribute("name", name);
        if (type != null) {
            se.setAttribute("type", type);
        }
        if (minOccurs != null) {
            se.setAttribute("minOccurs", minOccurs);
        }
    }

    private static SOAPElement fillRoot(SOAPElement root) {
        root.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        root.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        root.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        SOAPElement schema = addChildElement(root, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("xmlns", Constants.ROWSET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("targetNamespace", Constants.ROWSET.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");

        SOAPElement el = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s = addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement se = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st = addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        st.setAttribute("name", "uuid");
        SOAPElement r = addChildElement(st, Constants.XSD.QN_RESTRICTION);
        r.setAttribute("base", "xsd:string");
        SOAPElement p = addChildElement(r, Constants.XSD.QN_PATTERN);
        p.setAttribute("value", "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        return schema;
    }

    private static SOAPElement addEmptyRoot(SOAPBody body) throws SOAPException {

        SOAPElement seExecuteResponse = body.addChildElement(Constants.MSXMLA.QN_EXECUTE_RESPONSE);
        SOAPElement seReturn = seExecuteResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = seReturn.addChildElement(Constants.EMPTY.QN_ROOT);

        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        return seRoot;
    }

    private static SOAPElement addMddatasetRoot(SOAPElement body) throws SOAPException {
        SOAPElement seExecuteResponse = body.addChildElement(Constants.MSXMLA.QN_EXECUTE_RESPONSE);
        SOAPElement seReturn = seExecuteResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = seReturn.addChildElement(Constants.MDDATASET.QN_ROOT);
        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        addMddatasetSchema(seRoot);
        return seRoot;
    }

    private static SOAPElement addRowSetRoot(SOAPBody body, RowSet rowSet) throws SOAPException {

        SOAPElement seExecuteResponse = body.addChildElement(Constants.MSXMLA.QN_EXECUTE_RESPONSE);
        SOAPElement seReturn = seExecuteResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = seReturn.addChildElement(Constants.ROWSET.QN_ROOT);

        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        addRowsetSchema(seRoot, rowSet);
        return seRoot;
    }

    private static void addRowsetSchema(SOAPElement root, RowSet rowSet) {
        SOAPElement schema = addChildElement(root, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("targetNamespace", Constants.ROWSET.NS_URN);
        schema.setAttribute("xmlns", Constants.ROWSET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");

        SOAPElement el1 = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el1.setAttribute("name", "root");
        SOAPElement el1complexType = addChildElement(el1, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement el1complexTypeSequence = addChildElement(el1complexType, Constants.XSD.QN_SEQUENCE);
        SOAPElement el1complexTypeSequenceEl = addChildElement(el1complexTypeSequence, Constants.XSD.QN_ELEMENT);
        el1complexTypeSequenceEl.setAttribute("maxOccurs", "unbounded");
        el1complexTypeSequenceEl.setAttribute("minOccurs", "0");
        el1complexTypeSequenceEl.setAttribute("name", "row");
        el1complexTypeSequenceEl.setAttribute("type", "row");

        SOAPElement simpleType = addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        simpleType.setAttribute("name", "uuid");
        SOAPElement restriction = addChildElement(simpleType, Constants.XSD.QN_RESTRICTION);
        restriction.setAttribute("base", "xsd:string");
        SOAPElement pattern = addChildElement(restriction, Constants.XSD.QN_RESTRICTION);
        pattern.setAttribute("value", "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        SOAPElement ct = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct.setAttribute("name", "row");
        SOAPElement ctSequence = addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        if (rowSet.rowSetRows() != null && !rowSet.rowSetRows().isEmpty() && rowSet.rowSetRows().get(0) != null
                && rowSet.rowSetRows().get(0).rowSetRowItem() != null) {
            for (RowSetRowItem item : rowSet.rowSetRows().get(0).rowSetRowItem()) {
                SOAPElement ctSequenceEl1 = addChildElement(ctSequence, Constants.XSD.QN_ELEMENT);
                ItemTypeEnum type = item.type().orElse(ItemTypeEnum.STRING);
                ctSequenceEl1.setAttribute("minOccurs", "0");
                ctSequenceEl1.setAttribute("name", item.tagName());
                ctSequenceEl1.setAttribute("sql:field", item.tagName());
                ctSequenceEl1.setAttribute("type", type.getValue());
            }
        }
    }

    private static void addMddatasetSchema(SOAPElement root) {
        SOAPElement schema = addChildElement(root, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("targetNamespace", Constants.MDDATASET.NS_URN);
        schema.setAttribute("xmlns", Constants.MDDATASET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement ct1 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "MemberType");
        SOAPElement ct1Sequence = addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement e1 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e1.setAttribute("name", "UName");
        e1.setAttribute("type", "xsd:string");
        SOAPElement e2 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e2.setAttribute("name", "Caption");
        e2.setAttribute("type", "xsd:string");
        SOAPElement e3 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e3.setAttribute("name", "LName");
        e3.setAttribute("type", "xsd:string");
        SOAPElement e4 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e4.setAttribute("name", "LNum");
        e4.setAttribute("type", "xsd:unsignedInt");
        SOAPElement e5 = addChildElement(ct1Sequence, Constants.XSD.QN_ELEMENT);
        e5.setAttribute("name", "DisplayInfo");
        e5.setAttribute("type", "xsd:unsignedInt");
        SOAPElement s = addChildElement(ct1Sequence, Constants.XSD.QN_SEQUENCE);
        s.setAttribute("maxOccurs", "unbounded");
        s.setAttribute("minOccurs", "0");
        SOAPElement any = addChildElement(s, Constants.XSD.QN_ANY);
        any.setAttribute("processContents", "lax");
        any.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct1Attribute = addChildElement(ct1, Constants.XSD.QN_ATTRIBUTE);
        ct1Attribute.setAttribute("name", "Hierarchy");
        ct1Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct2 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct2.setAttribute("name", "PropType");
        SOAPElement ct2Attribute = addChildElement(ct2, Constants.XSD.QN_ATTRIBUTE);
        ct2Attribute.setAttribute("name", "name");
        ct2Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct3 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct3.setAttribute("name", "TupleType");
        SOAPElement ct3Sequence = addChildElement(ct3, Constants.XSD.QN_SEQUENCE);
        ct3Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct3SequenceElement = addChildElement(ct3Sequence, Constants.XSD.QN_ELEMENT);
        ct3SequenceElement.setAttribute("name", "Member");
        ct3SequenceElement.setAttribute("type", "MemberType");

        SOAPElement ct4 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct4.setAttribute("name", "MembersType");
        SOAPElement ct4Sequence = addChildElement(ct4, Constants.XSD.QN_SEQUENCE);
        ct4Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct4SequenceElement = addChildElement(ct4Sequence, Constants.XSD.QN_ELEMENT);
        ct4SequenceElement.setAttribute("name", "Member");
        ct4SequenceElement.setAttribute("type", "MemberType");
        SOAPElement ct4Attribute = addChildElement(ct4, Constants.XSD.QN_ATTRIBUTE);
        ct4Attribute.setAttribute("name", "Hierarchy");
        ct4Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct5 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct5.setAttribute("name", "TuplesType");
        SOAPElement ct5Sequence = addChildElement(ct5, Constants.XSD.QN_SEQUENCE);
        ct5Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct5SequenceElement = addChildElement(ct5Sequence, Constants.XSD.QN_ELEMENT);
        ct5SequenceElement.setAttribute("name", "Tuple");
        ct5SequenceElement.setAttribute("type", "TupleType");

        SOAPElement ct6 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct6.setAttribute("name", "CrossProductType");
        SOAPElement ct6Sequence = addChildElement(ct6, Constants.XSD.QN_SEQUENCE);
        //ct6Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct6SequenceChoice = addChildElement(ct6Sequence, Constants.XSD.QN_CHOICE);
        ct6SequenceChoice.setAttribute("minOccurs", "0");
        ct6SequenceChoice.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct6SequenceChoiceE1 = addChildElement(ct6SequenceChoice, Constants.XSD.QN_ELEMENT);
        ct6SequenceChoiceE1.setAttribute("name", "Members");
        ct6SequenceChoiceE1.setAttribute("type", "MembersType");
        SOAPElement ct6SequenceChoiceE2 = addChildElement(ct6SequenceChoice, Constants.XSD.QN_ELEMENT);
        ct6SequenceChoiceE2.setAttribute("name", "Tuples");
        ct6SequenceChoiceE2.setAttribute("type", "TuplesType");
        SOAPElement ct6Attribute = addChildElement(ct6, Constants.XSD.QN_ATTRIBUTE);
        ct6Attribute.setAttribute("name", "Size");
        ct6Attribute.setAttribute("type", "xsd:unsignedInt");

        SOAPElement ct7 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct7.setAttribute("name", "OlapInfo");
        SOAPElement ct7Sequence = addChildElement(ct7, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement1 = addChildElement(ct7Sequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement1.setAttribute("name", "CubeInfo");

        SOAPElement ct7SequenceElement1Ct = addChildElement(ct7SequenceElement1, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement1CtSequence = addChildElement(ct7SequenceElement1Ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement1CtSequenceEl = addChildElement(ct7SequenceElement1CtSequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement1CtSequenceEl.setAttribute("name", "Cube");
        ct7SequenceElement1CtSequenceEl.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement1CtSequenceElCt = addChildElement(ct7SequenceElement1CtSequenceEl,
            Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement1CtSequenceElCtSequence = addChildElement(ct7SequenceElement1CtSequenceElCt,
                Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement1CtSequenceElCtSequenceEl = addChildElement(
                ct7SequenceElement1CtSequenceElCtSequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement1CtSequenceElCtSequenceEl.setAttribute("name", "CubeName");
        ct7SequenceElement1CtSequenceElCtSequenceEl.setAttribute("type", "xsd:string");

        SOAPElement ct7SequenceElement2 = addChildElement(ct7Sequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2.setAttribute("name", "AxesInfo");
        SOAPElement ct7SequenceElement2Ct = addChildElement(ct7SequenceElement2, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement2CtSequence = addChildElement(ct7SequenceElement2Ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement2CtSequenceEl = addChildElement(ct7SequenceElement2CtSequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceEl.setAttribute("name", "AxisInfo");
        ct7SequenceElement2CtSequenceEl.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCt = addChildElement(ct7SequenceElement2CtSequenceEl,
            Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement2CtSequenceElCtSequence = addChildElement(ct7SequenceElement2CtSequenceElCt,
                Constants.XSD.QN_SEQUENCE);

        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElement = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("name", "HierarchyInfo");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCt = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElement, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCt, Constants.XSD.QN_SEQUENCE);

        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence, Constants.XSD.QN_SEQUENCE);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E1 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E1.setAttribute("name", "UName");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E1.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E2 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E2.setAttribute("name", "Caption");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E2.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E3 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E3.setAttribute("name", "LName");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E3.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E4 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E4.setAttribute("name", "LNum");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E4.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E5 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence1, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("name", "DisplayInfo");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("type", "PropType");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence2 = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence2Any = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceSequence2, Constants.XSD.QN_ANY);
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("processContents", "lax");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute = addChildElement(
                ct7SequenceElement2CtSequenceElCtSequenceElementCt, Constants.XSD.QN_ATTRIBUTE);
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("name", "name");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("type", "xsd:string");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("use", "required");
        SOAPElement ct7SequenceElement2CtAttribute = addChildElement(ct7SequenceElement2CtSequenceElCt,
            Constants.XSD.QN_ATTRIBUTE);
        ct7SequenceElement2CtAttribute.setAttribute("name", "name");
        ct7SequenceElement2CtAttribute.setAttribute("type", "xsd:string");

        SOAPElement ct7SequenceElement3 = addChildElement(ct7Sequence, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement3.setAttribute("name", "CellInfo");
        SOAPElement ct7SequenceElement3Ct = addChildElement(ct7SequenceElement3, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct7SequenceElement3CtSequence = addChildElement(ct7SequenceElement3Ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct7SequenceElement2CtSequenceSequence1 = addChildElement(ct7SequenceElement3CtSequence,
            Constants.XSD.QN_SEQUENCE);
        ct7SequenceElement2CtSequenceSequence1.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceSequence1.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceSequence1Ch = addChildElement(ct7SequenceElement2CtSequenceSequence1,
            Constants.XSD.QN_CHOICE);
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE1 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE1.setAttribute("name", "Value");
        ct7SequenceElement2CtSequenceSequence1ChE1.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE2 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE2.setAttribute("name", "FmtValue");
        ct7SequenceElement2CtSequenceSequence1ChE2.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE3 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE3.setAttribute("name", "BackColor");
        ct7SequenceElement2CtSequenceSequence1ChE3.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE4 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE4.setAttribute("name", "ForeColor");
        ct7SequenceElement2CtSequenceSequence1ChE4.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE5 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE5.setAttribute("name", "FontName");
        ct7SequenceElement2CtSequenceSequence1ChE5.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE6 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE6.setAttribute("name", "FontSize");
        ct7SequenceElement2CtSequenceSequence1ChE6.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE7 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE7.setAttribute("name", "FontFlags");
        ct7SequenceElement2CtSequenceSequence1ChE7.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE8 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE8.setAttribute("name", "FormatString");
        ct7SequenceElement2CtSequenceSequence1ChE8.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE9 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE9.setAttribute("name", "NonEmptyBehavior");
        ct7SequenceElement2CtSequenceSequence1ChE9.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE10 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE10.setAttribute("name", "SolveOrder");
        ct7SequenceElement2CtSequenceSequence1ChE10.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE11 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE11.setAttribute("name", "Updateable");
        ct7SequenceElement2CtSequenceSequence1ChE11.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE12 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE12.setAttribute("name", "Visible");
        ct7SequenceElement2CtSequenceSequence1ChE12.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE13 = addChildElement(
                ct7SequenceElement2CtSequenceSequence1Ch, Constants.XSD.QN_ELEMENT);
        ct7SequenceElement2CtSequenceSequence1ChE13.setAttribute("name", "Expression");
        ct7SequenceElement2CtSequenceSequence1ChE13.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence2 = addChildElement(ct7SequenceElement3CtSequence, Constants.XSD.QN_SEQUENCE);
        ct7SequenceElement2CtSequenceSequence2.setAttribute("maxOccurs", "unbounded");
        ct7SequenceElement2CtSequenceSequence2.setAttribute("minOccurs", "0");
        SOAPElement ct7SequenceElement2CtSequenceSequence2Any = addChildElement(ct7SequenceElement2CtSequenceSequence2,
                Constants.XSD.QN_ANY);
        ct7SequenceElement2CtSequenceSequence2Any.setAttribute("processContents", "lax");
        ct7SequenceElement2CtSequenceSequence2Any.setAttribute("maxOccurs", "unbounded");

        SOAPElement ct8 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct8.setAttribute("name", "Axes");
        SOAPElement ct8Sequence = addChildElement(ct8, Constants.XSD.QN_SEQUENCE);
        ct8Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct8SequenceElement = addChildElement(ct8Sequence, Constants.XSD.QN_ELEMENT);
        ct8SequenceElement.setAttribute("name", "Axis");
        SOAPElement ct8SequenceElementComplexType = addChildElement(ct8SequenceElement, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct8SequenceElementComplexTypeChoice = addChildElement(ct8SequenceElementComplexType,
            Constants.XSD.QN_CHOICE);
        ct8SequenceElementComplexTypeChoice.setAttribute("minOccurs", "0");
        ct8SequenceElementComplexTypeChoice.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct8SequenceElementComplexTypeChoiceE1 = addChildElement(ct8SequenceElementComplexTypeChoice,
                Constants.XSD.QN_ELEMENT);
        ct8SequenceElementComplexTypeChoiceE1.setAttribute("name", "CrossProduct");
        ct8SequenceElementComplexTypeChoiceE1.setAttribute("type", "CrossProductType");
        SOAPElement ct8SequenceElementComplexTypeChoiceE2 = addChildElement(ct8SequenceElementComplexTypeChoice,
                Constants.XSD.QN_ELEMENT);
        ct8SequenceElementComplexTypeChoiceE2.setAttribute("name", "Tuples");
        ct8SequenceElementComplexTypeChoiceE2.setAttribute("type", "TuplesType");
        SOAPElement ct8SequenceElementComplexTypeChoiceE3 = addChildElement(ct8SequenceElementComplexTypeChoice,
                Constants.XSD.QN_ELEMENT);
        ct8SequenceElementComplexTypeChoiceE3.setAttribute("name", "Members");
        ct8SequenceElementComplexTypeChoiceE3.setAttribute("type", "MembersType");
        SOAPElement ct8SequenceElementComplexTypeAttribute = addChildElement(ct8SequenceElementComplexType,
            Constants.XSD.QN_ATTRIBUTE);
        ct8SequenceElementComplexTypeAttribute.setAttribute("name", "name");
        ct8SequenceElementComplexTypeAttribute.setAttribute("type", "xsd:string");

        SOAPElement ct9 = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct9.setAttribute("name", "CellData");
        SOAPElement ct9Sequence = addChildElement(ct9, Constants.XSD.QN_SEQUENCE);
        SOAPElement ct9SequenceElement = addChildElement(ct9Sequence, Constants.XSD.QN_ELEMENT);
        ct9SequenceElement.setAttribute("name", "Cell");
        ct9SequenceElement.setAttribute("minOccurs", "0");
        ct9SequenceElement.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct9SequenceElementComplexType = addChildElement(ct9SequenceElement, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement ct9SequenceElementComplexTypeSequence = addChildElement(ct9SequenceElementComplexType,
            Constants.XSD.QN_SEQUENCE);
        ct9SequenceElementComplexTypeSequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoice = addChildElement(ct9SequenceElementComplexTypeSequence,
            Constants.XSD.QN_CHOICE);
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE1 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE1.setAttribute("name", "Value");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE2 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE2.setAttribute("name", "FmtValue");
        ct9SequenceElementComplexTypeSequenceChoiceE2.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE3 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE3.setAttribute("name", "BackColor");
        ct9SequenceElementComplexTypeSequenceChoiceE3.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE4 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE4.setAttribute("name", "ForeColor");
        ct9SequenceElementComplexTypeSequenceChoiceE4.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE5 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE5.setAttribute("name", "FontName");
        ct9SequenceElementComplexTypeSequenceChoiceE5.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE6 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE6.setAttribute("name", "FontSize");
        ct9SequenceElementComplexTypeSequenceChoiceE6.setAttribute("type", "xsd:unsignedShort");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE7 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE7.setAttribute("name", "FontFlags");
        ct9SequenceElementComplexTypeSequenceChoiceE7.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE8 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE8.setAttribute("name", "FormatString");
        ct9SequenceElementComplexTypeSequenceChoiceE8.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE9 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE9.setAttribute("name", "NonEmptyBehavior");
        ct9SequenceElementComplexTypeSequenceChoiceE9.setAttribute("type", "xsd:unsignedShort");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE10 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE10.setAttribute("name", "SolveOrder");
        ct9SequenceElementComplexTypeSequenceChoiceE10.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE11 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE11.setAttribute("name", "Updateable");
        ct9SequenceElementComplexTypeSequenceChoiceE11.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE12 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE12.setAttribute("name", "Visible");
        ct9SequenceElementComplexTypeSequenceChoiceE12.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE13 = addChildElement(
                ct9SequenceElementComplexTypeSequenceChoice, Constants.XSD.QN_ELEMENT);
        ct9SequenceElementComplexTypeSequenceChoiceE13.setAttribute("name", "Expression");
        ct9SequenceElementComplexTypeSequenceChoiceE13.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeAttribute = addChildElement(ct9SequenceElementComplexType,
            Constants.XSD.QN_ATTRIBUTE);
        ct9SequenceElementComplexTypeAttribute.setAttribute("name", "CellOrdinal");
        ct9SequenceElementComplexTypeAttribute.setAttribute("type", "xsd:unsignedInt");
        ct9SequenceElementComplexTypeAttribute.setAttribute("use", "required");

        SOAPElement element = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        element.setAttribute("name", "root");
        SOAPElement elementComplexType = addChildElement(element, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement elementComplexTypeSequence = addChildElement(elementComplexType, Constants.XSD.QN_SEQUENCE);
        elementComplexTypeSequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement elementComplexTypeSequenceE1 = addChildElement(elementComplexTypeSequence, Constants.XSD.QN_ELEMENT);
        elementComplexTypeSequenceE1.setAttribute("name", "OlapInfo");
        elementComplexTypeSequenceE1.setAttribute("type", "OlapInfo");
        SOAPElement elementComplexTypeSequenceE2 = addChildElement(elementComplexTypeSequence, Constants.XSD.QN_ELEMENT);
        elementComplexTypeSequenceE2.setAttribute("name", "Axes");
        elementComplexTypeSequenceE2.setAttribute("type", "Axes");
        SOAPElement elementComplexTypeSequenceE3 = addChildElement(elementComplexTypeSequence, Constants.XSD.QN_ELEMENT);
        elementComplexTypeSequenceE3.setAttribute("name", "CellData");
        elementComplexTypeSequenceE3.setAttribute("type", "CellData");
    }

    private static SOAPElement addChildElement(SOAPElement element, QName qNameOfChild, String valueOfChild) {
        try {
            SOAPElement createdChild = element.addChildElement(qNameOfChild);
            createdChild.setTextContent(valueOfChild);
            return createdChild;

        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new RuntimeException("addChildElement error", e);
        }
    }

    private static SOAPElement addChildElement(SOAPElement element, QName qNameOfChild) {
        try {
            return element.addChildElement(qNameOfChild);
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new RuntimeException("addChildElement error", e);
        }
    }

    private static void addChildElement(SOAPElement element, String childElementName, String prefix, String value) {
        try {
            if (value != null) {
                if (prefix != null) {
                    element.addChildElement(childElementName, prefix).setTextContent(value);
                } else {
                    element.addChildElement(childElementName).setTextContent(value);
                }

            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new RuntimeException("addChildElement error", e);
        }
    }

    private static SOAPElement addChildElement(SOAPElement element, String childElementName, String prefix) {

        try {
            if (prefix == null) {
                return element.addChildElement(childElementName);

            } else {
                return element.addChildElement(childElementName, prefix);
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new RuntimeException("addChildElement error", e);
        }
    }

}
