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
package org.eclipse.daanse.xmla.api.discover;

import java.util.List;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserPrincipal;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequest;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;

/**
 * The {@link DiscoverService} gives access to all discover methods of the
 * {@link XmlaService}. The execute methods could be found in the
 * {@link ExecuteService}.
 *
 * @see {@link ExecuteService}
 */
public interface DiscoverService {

	/**
	 * returns a list of the data sources that are available on the server.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DiscoverDataSourcesResponseRow> dataSources(DiscoverDataSourcesRequest request,
                                                     RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns the catalogs that are accessible on the server.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaCatalogsResponseRow> dbSchemaCatalogs(DbSchemaCatalogsRequest request,
                                                       RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the structure of cubes within a database. Perspectives are also
	 * returned in this schema.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaColumnsResponseRow> dbSchemaColumns(DbSchemaColumnsRequest request,
                                                     RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the properties of members and cell properties.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaProviderTypesResponseRow> dbSchemaProviderTypes(DbSchemaProviderTypesRequest request,
                                                                 RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * identifies the (base) data types supported by the server.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaSchemataResponseRow> dbSchemaSchemata(DbSchemaSchemataRequest request,
                                                       RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 *
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaSourceTablesResponseRow> dbSchemaSourceTables(DbSchemaSourceTablesRequest request,
                                                               RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns dimensions, measure groups, or schema rowsets exposed as tables.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaTablesResponseRow> dbSchemaTables(DbSchemaTablesRequest request,
                                                   RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 *
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DbSchemaTablesInfoResponseRow> dbSchemaTablesInfo(DbSchemaTablesInfoRequest request,
                                                           RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns a row for each measure, each cube dimension attribute, and each
	 * schema rowset column, exposed as a column
	 *
	 * @param request the request
	 * @return the list
	 */
	// discover
	List<DiscoverEnumeratorsResponseRow> discoverEnumerators(DiscoverEnumeratorsRequest request,
                                                             RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns information about keywords that are reserved by the XMLA server.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DiscoverKeywordsResponseRow> discoverKeywords(DiscoverKeywordsRequest request,
                                                       RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns information about literals supported by the server.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DiscoverLiteralsResponseRow> discoverLiterals(DiscoverLiteralsRequest request,
                                                       RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns a list of information and values about the properties that are
	 * supported by the server for the specified data source.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DiscoverPropertiesResponseRow> discoverProperties(DiscoverPropertiesRequest request,
                                                           RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns the names, restrictions, description, and other information for all
	 * Discover requests.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DiscoverSchemaRowsetsResponseRow> discoverSchemaRowsets(DiscoverSchemaRowsetsRequest request,
                                                                 RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the actions that can be available to the client application.
	 *
	 * @param request the request
	 * @return the list
	 */
	// md
	List<MdSchemaActionsResponseRow> mdSchemaActions(MdSchemaActionsRequest request,
                                                     RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the structure of cubes within a database. Perspectives are also
	 * returned in this schema.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaCubesResponseRow> mdSchemaCubes(MdSchemaCubesRequest request,
                                                 RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the dimensions within a database.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaDimensionsResponseRow> mdSchemaDimensions(MdSchemaDimensionsRequest request,
                                                           RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns information about the functions that are currently available for use
	 * in the DAX and MDX languages.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaFunctionsResponseRow> mdSchemaFunctions(MdSchemaFunctionsRequest request,
                                                         RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes each hierarchy within a particular dimension.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaHierarchiesResponseRow> mdSchemaHierarchies(MdSchemaHierarchiesRequest request,
                                                             RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the KPIs within a database
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaKpisResponseRow> mdSchemaKpis(MdSchemaKpisRequest request,
                                               RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes each level within a particular hierarchy.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaLevelsResponseRow> mdSchemaLevels(MdSchemaLevelsRequest request,
                                                   RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * enumerates the dimensions of measure groups.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaMeasureGroupDimensionsResponseRow> mdSchemaMeasureGroupDimensions(
			MdSchemaMeasureGroupDimensionsRequest request,
            RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the MeasureGroups within a database
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaMeasureGroupsResponseRow> mdSchemaMeasureGroups(MdSchemaMeasureGroupsRequest request,
                                                                 RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the members within a database
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaMeasuresResponseRow> mdSchemaMeasures(MdSchemaMeasuresRequest request,
                                                       RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the members within a database
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaMembersResponseRow> mdSchemaMembers(MdSchemaMembersRequest request,
                                                     RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes the properties of members and cell properties.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaPropertiesResponseRow> mdSchemaProperties(MdSchemaPropertiesRequest request,
                                                           RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * describes any sets that are currently defined in a database, including
	 * session- scoped sets.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<MdSchemaSetsResponseRow> mdSchemaSets(MdSchemaSetsRequest request,
                                               RequestMetaData metaData, UserPrincipal userPrincipal);

	/**
	 * returns a rowset with one row and one column. The single cell in the rowset
	 * contains an XML document that contains the requested XML metadata.
	 *
	 * @param request the request
	 * @return the list
	 */
	List<DiscoverXmlMetaDataResponseRow> xmlMetaData(DiscoverXmlMetaDataRequest request,
                                                     RequestMetaData metaData, UserPrincipal userPrincipal);
}
