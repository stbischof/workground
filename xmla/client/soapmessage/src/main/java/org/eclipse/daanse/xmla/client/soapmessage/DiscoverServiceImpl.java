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
package org.eclipse.daanse.xmla.client.soapmessage;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserPrincipal;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.eclipse.daanse.xmla.client.soapmessage.Constants.SOAP_ACTION_DISCOVER;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaCatalogsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaColumnsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaProviderTypesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaSchemataResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaSourceTablesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaTablesInfoResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDbSchemaTablesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverDataSourcesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverEnumeratorsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverKeywordsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverLiteralsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverPropertiesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverSchemaRowsetsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToDiscoverXmlMetaDataResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaActionsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaCubesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaDimensionsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaFunctionsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaHierarchiesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaKpisResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaLevelsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaMeasureGroupDimensionsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaMeasureGroupsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaMeasuresResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaMembersResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaPropertiesResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToMdSchemaSetsResponseRow;
import static org.eclipse.daanse.xmla.client.soapmessage.DiscoverConsumers.createDiscoverDataSourcesRequestConsumer;

public class DiscoverServiceImpl implements DiscoverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoverServiceImpl.class);
    private SoapClient soapClient;

    public DiscoverServiceImpl(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public List<DbSchemaCatalogsResponseRow> dbSchemaCatalogs(DbSchemaCatalogsRequest dbSchemaCatalogsRequest,
                                                              RequestMetaData metaData,
                                                              UserPrincipal userPrincipal) {

        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaCatalogsRequestConsumer(dbSchemaCatalogsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaCatalogsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dbSchemaCatalogs error", e);
        }
        return List.of();
    }

    @Override
    public List<DbSchemaTablesResponseRow> dbSchemaTables(DbSchemaTablesRequest dbSchemaTablesRequest,
                                                          RequestMetaData metaData,
                                                          UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaTablesRequestConsumer(dbSchemaTablesRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaTablesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dbSchemaTables error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverEnumeratorsResponseRow> discoverEnumerators(
        DiscoverEnumeratorsRequest discoverEnumeratorsRequest,
        RequestMetaData metaData,
        UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDiscoverEnumeratorsRequestConsumer(discoverEnumeratorsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverEnumeratorsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService discoverEnumerators error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverKeywordsResponseRow> discoverKeywords(DiscoverKeywordsRequest discoverKeywordsRequest,
            RequestMetaData metaData,
            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDiscoverKeywordsRequestConsumer(discoverKeywordsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverKeywordsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService discoverKeywords error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverLiteralsResponseRow> discoverLiterals(DiscoverLiteralsRequest discoverLiteralsRequest,
            RequestMetaData metaData,
            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDiscoverLiteralsRequestConsumer(discoverLiteralsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverLiteralsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService discoverLiterals error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverPropertiesResponseRow> discoverProperties(DiscoverPropertiesRequest discoverPropertiesRequest,
                                                                  RequestMetaData metaData,
                                                                  UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDiscoverPropertiesRequestConsumer(discoverPropertiesRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverPropertiesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService discoverProperties error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverSchemaRowsetsResponseRow> discoverSchemaRowsets(
        DiscoverSchemaRowsetsRequest discoverSchemaRowsetsRequest,
        RequestMetaData metaData,
        UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDiscoverSchemaRowsetsRequestConsumer(discoverSchemaRowsetsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverSchemaRowsetsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService discoverProperties error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaActionsResponseRow> mdSchemaActions(MdSchemaActionsRequest mdSchemaActionsRequest,
                                                            RequestMetaData metaData,
                                                            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaActionsRequestConsumer(mdSchemaActionsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaActionsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaCubes error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaCubesResponseRow> mdSchemaCubes(MdSchemaCubesRequest mdSchemaCubesRequest,
                                                        RequestMetaData metaData,
                                                        UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaCubesRequestConsumer(mdSchemaCubesRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaCubesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaCubes error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaDimensionsResponseRow> mdSchemaDimensions(MdSchemaDimensionsRequest mdSchemaDimensionsRequest,
                                                                  RequestMetaData metaData,
                                                                  UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaDimensionsRequestConsumer(mdSchemaDimensionsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaDimensionsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaFunctions error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaFunctionsResponseRow> mdSchemaFunctions(MdSchemaFunctionsRequest mdSchemaFunctionsRequest,
                                                                RequestMetaData metaData,
                                                                UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaFunctionsRequestConsumer(mdSchemaFunctionsRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaFunctionsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaFunctions error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaHierarchiesResponseRow> mdSchemaHierarchies(MdSchemaHierarchiesRequest requestApi,
                                                                    RequestMetaData metaData,
                                                                    UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaHierarchiesRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaHierarchiesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dataSources error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverDataSourcesResponseRow> dataSources(DiscoverDataSourcesRequest requestApi,
                                                            RequestMetaData metaData,
                                                            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = createDiscoverDataSourcesRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverDataSourcesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dataSources error", e);
        }
        return List.of();
    }

    @Override
    public List<DiscoverXmlMetaDataResponseRow> xmlMetaData(DiscoverXmlMetaDataRequest requestApi,
                                                            RequestMetaData metaData,
                                                            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDiscoverXmlMetaDataRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDiscoverXmlMetaDataResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService xmlMetaData error", e);
        }
        return List.of();
    }

    @Override
    public List<DbSchemaColumnsResponseRow> dbSchemaColumns(DbSchemaColumnsRequest requestApi,
                                                            RequestMetaData metaData,
                                                            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaColumnsRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaColumnsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService xmlMetaData error", e);
        }
        return List.of();
    }

    @Override
    public List<DbSchemaProviderTypesResponseRow> dbSchemaProviderTypes(DbSchemaProviderTypesRequest requestApi,
                                                                        RequestMetaData metaData,
                                                                        UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaProviderTypesRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaProviderTypesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dbSchemaProviderTypes error", e);
        }
        return List.of();
    }

    @Override
    public List<DbSchemaSchemataResponseRow> dbSchemaSchemata(DbSchemaSchemataRequest requestApi,
                                                              RequestMetaData metaData,
                                                              UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaSchemataRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaSchemataResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dbSchemaSchemata error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaLevelsResponseRow> mdSchemaLevels(MdSchemaLevelsRequest requestApi,
                                                          RequestMetaData metaData,
                                                          UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaLevelsRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaLevelsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaLevels error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaMeasureGroupDimensionsResponseRow> mdSchemaMeasureGroupDimensions(
        MdSchemaMeasureGroupDimensionsRequest requestApi,
        RequestMetaData metaData,
        UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaMeasureGroupDimensionsRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaMeasureGroupDimensionsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaMeasureGroupDimensions error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaMeasuresResponseRow> mdSchemaMeasures(MdSchemaMeasuresRequest requestApi,
                                                              RequestMetaData metaData,
                                                              UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaMeasuresRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaMeasuresResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaMeasureGroupDimensions error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaMembersResponseRow> mdSchemaMembers(MdSchemaMembersRequest requestApi,
                                                            RequestMetaData metaData,
                                                            UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaMembersRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaMembersResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaMembers error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaPropertiesResponseRow> mdSchemaProperties(MdSchemaPropertiesRequest requestApi,
                                                                  RequestMetaData metaData,
                                                                  UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaPropertiesRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaPropertiesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaProperties error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaSetsResponseRow> mdSchemaSets(MdSchemaSetsRequest requestApi,
                                                      RequestMetaData metaData,
                                                      UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaSetsRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaSetsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaProperties error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaKpisResponseRow> mdSchemaKpis(MdSchemaKpisRequest requestApi,
                                                      RequestMetaData metaData,
                                                      UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaKpisRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaKpisResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaKpis error", e);
        }
        return List.of();
    }

    @Override
    public List<MdSchemaMeasureGroupsResponseRow> mdSchemaMeasureGroups(MdSchemaMeasureGroupsRequest requestApi,
                                                                        RequestMetaData metaData,
                                                                        UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createMdSchemaMeasureGroupsRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToMdSchemaMeasureGroupsResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService mdSchemaMeasureGroups error", e);
        }
        return List.of();
    }

    @Override
    public List<DbSchemaSourceTablesResponseRow> dbSchemaSourceTables(DbSchemaSourceTablesRequest requestApi,
                                                                      RequestMetaData metaData,
                                                                      UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaSourceTablesRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaSourceTablesResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dbSchemaSourceTables error", e);
        }
        return List.of();

    }

    @Override
    public List<DbSchemaTablesInfoResponseRow> dbSchemaTablesInfo(DbSchemaTablesInfoRequest requestApi,
                                                                  RequestMetaData metaData,
                                                                  UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = DiscoverConsumers.createDbSchemaTablesInfoRequestConsumer(requestApi);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_DISCOVER), msg);
            return convertToDbSchemaTablesInfoResponseRow(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("DiscoverService dbSchemaTablesInfo error", e);
        }
        return List.of();
    }
}
