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

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
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
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.alter.AlterRequest;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.eclipse.daanse.xmla.model.record.UserPrincipalR;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.execute.alter.AlterRequestR;
import org.eclipse.daanse.xmla.model.record.execute.cancel.CancelRequestR;
import org.eclipse.daanse.xmla.model.record.execute.clearcache.ClearCacheRequestR;
import org.eclipse.daanse.xmla.model.record.execute.statement.StatementRequestR;
import org.eclipse.daanse.xmla.model.record.xmla.AlterR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class XmlaApiAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlaApiAdapter.class);

    private XmlaService xmlaService;

    public XmlaApiAdapter(XmlaService xmlaService) {
        this.xmlaService = xmlaService;
    }

    private static final String MDSCHEMA_FUNCTIONS = "MDSCHEMA_FUNCTIONS";
    private static final String MDSCHEMA_DIMENSIONS = "MDSCHEMA_DIMENSIONS";
    private static final String MDSCHEMA_CUBES = "MDSCHEMA_CUBES";
    private static final String MDSCHEMA_ACTIONS = "MDSCHEMA_ACTIONS";
    private static final String DBSCHEMA_TABLES = "DBSCHEMA_TABLES";
    private static final String DISCOVER_LITERALS = "DISCOVER_LITERALS";
    private static final String DISCOVER_KEYWORDS = "DISCOVER_KEYWORDS";
    private static final String DISCOVER_ENUMERATORS = "DISCOVER_ENUMERATORS";
    private static final String DISCOVER_SCHEMA_ROWSETS = "DISCOVER_SCHEMA_ROWSETS";
    private static final String DISCOVER_PROPERTIES = "DISCOVER_PROPERTIES";
    private static final String DBSCHEMA_CATALOGS = "DBSCHEMA_CATALOGS";
    private static final String DISCOVER_DATASOURCES = "DISCOVER_DATASOURCES";
    private static final String DISCOVER_XML_METADATA = "DISCOVER_XML_METADATA";
    private static final String DBSCHEMA_COLUMNS = "DBSCHEMA_COLUMNS";
    private static final String DBSCHEMA_PROVIDER_TYPES = "DBSCHEMA_PROVIDER_TYPES";
    private static final String DBSCHEMA_SCHEMATA = "DBSCHEMA_SCHEMATA";
    private static final String DBSCHEMA_SOURCE_TABLES = "DBSCHEMA_SOURCE_TABLES";
    private static final String DBSCHEMA_TABLES_INFO = "DBSCHEMA_TABLES_INFO";
    private static final String MDSCHEMA_HIERARCHIES = "MDSCHEMA_HIERARCHIES";
    private static final String MDSCHEMA_LEVELS = "MDSCHEMA_LEVELS";
    private static final String MDSCHEMA_MEASUREGROUP_DIMENSIONS = "MDSCHEMA_MEASUREGROUP_DIMENSIONS";
    private static final String MDSCHEMA_MEASURES = "MDSCHEMA_MEASURES";
    private static final String MDSCHEMA_MEMBERS = "MDSCHEMA_MEMBERS";
    private static final String MDSCHEMA_PROPERTIES = "MDSCHEMA_PROPERTIES";
    private static final String MDSCHEMA_SETS = "MDSCHEMA_SETS";
    private static final String MDSCHEMA_KPIS = "MDSCHEMA_KPIS";
    private static final String MDSCHEMA_MEASUREGROUPS = "MDSCHEMA_MEASUREGROUPS";
    private Set<String> sessions = new HashSet<>();

    public SOAPMessage handleRequest(SOAPMessage messageRequest, Map<String, Object> headers) {
        try {
            SOAPMessage messageResponse = MessageFactory.newInstance().createMessage();
            SOAPPart soapPartResponse = messageResponse.getSOAPPart();
            SOAPEnvelope envelopeResponse = soapPartResponse.getEnvelope();

            envelopeResponse.addNamespaceDeclaration(Constants.MSXMLA.PREFIX, Constants.MSXMLA.NS_URN);
            envelopeResponse.addNamespaceDeclaration(Constants.ROWSET.PREFIX, Constants.ROWSET.NS_URN);
            envelopeResponse.addNamespaceDeclaration(Constants.MDDATASET.PREFIX, Constants.MDDATASET.NS_URN);
            envelopeResponse.addNamespaceDeclaration(Constants.ENGINE.PREFIX, Constants.ENGINE.NS_URN);
            envelopeResponse.addNamespaceDeclaration(Constants.ENGINE200.PREFIX, Constants.ENGINE200.NS_URN);
            envelopeResponse.addNamespaceDeclaration(Constants.EMPTY.PREFIX, Constants.EMPTY.NS_URN);
            envelopeResponse.addNamespaceDeclaration(Constants.XSI.PREFIX, Constants.XSI.NS_URN);

            SOAPBody bodyResponse = envelopeResponse.getBody();
            Object role = headers.get("ROLE");
            Object user = headers.get("USER");
            UserPrincipal userPrincipal = new UserPrincipalR(getStringOrNull(user), getRiles(role));
            Optional<Session> ses = SessionUtil.getSession(messageRequest.getSOAPHeader(), sessions);
            if (ses.isPresent()) {
                SOAPHeader header = envelopeResponse.getHeader();
                QName session = new QName("urn:schemas-microsoft-com:xml-analysis", "Session");
                SOAPHeaderElement sessionElement = header.addHeaderElement(session);
                sessionElement.addAttribute(new QName("SessionId"), ses.get().sessionId());
            }
            RequestMetaData metaData = RequestMetaDataUtils.getRequestMetaData(headers, ses);
            handleBody(messageRequest.getSOAPBody(), bodyResponse, metaData, userPrincipal);
            return messageResponse;
        } catch (SOAPException e) {
            LOGGER.error("handleRequest error", e);
        }
        return null;
    }

    private List<String> getRiles(Object ob) {
        if (ob != null && ob instanceof String str) {
            return Arrays.asList(str.split(","));
        }
        return List.of();
    }

    private String getStringOrNull(Object ob) {
        if (ob != null && ob instanceof String str) {
            return str;
        }
        return null;
    }

    private void handleBody(SOAPBody body, SOAPBody responseBody,RequestMetaData metaData, UserPrincipal userPrincipal) throws SOAPException {
        SOAPElement node = null;

        Iterator<Node> nodeIterator = body.getChildElements();
        while (nodeIterator.hasNext()) {
            Node nodeN = nodeIterator.next();
            if (nodeN instanceof SOAPElement soapElement) {
                node = soapElement;
                break;
            }
        }
        if (node != null) {
            printNode(node);
        }

        if (node != null && Constants.MSXMLA.QN_DISCOVER.equals(node.getElementQName())) {

            discover(node, responseBody, metaData, userPrincipal);

        }
        if (node != null && Constants.MSXMLA.QN_EXECUTE.equals(node.getElementQName())) {
            execute(node, responseBody, metaData, userPrincipal);
        }

    }

    private void discover(SOAPElement discover, SOAPBody responseBody,RequestMetaData metaData, UserPrincipal userPrincipal) throws SOAPException {

        String requestType = null;
        PropertiesR properties = null;
        SOAPElement restictions = null;

        Iterator<Node> nodeIterator = discover.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement element) {
                if (requestType == null && Constants.MSXMLA.QN_REQUEST_TYPE.equals(element.getElementQName())) {
                    requestType = element.getTextContent();
                    continue;
                }
                if (restictions == null && Constants.MSXMLA.QN_RESTRICTIONS.equals(element.getElementQName())) {
                    restictions = element;
                    continue;
                }
                if (properties == null && Constants.MSXMLA.QN_PROPERTIES.equals(element.getElementQName())) {
                    properties = Convert.propertiestoProperties(element);
                }
            }
        }

        discover(requestType, metaData, userPrincipal, properties, restictions, responseBody);
    }

    private void execute(SOAPElement discover, SOAPBody responseBody,
                         RequestMetaData metaData, UserPrincipal userPrincipal) throws SOAPException {

        Command command = null;
        PropertiesR properties = null;
        List<ExecuteParameter> parameters = null;

        Iterator<Node> nodeIterator = discover.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement element) {
                if (properties == null && Constants.MSXMLA.QN_PROPERTIES.equals(element.getElementQName())) {
                    properties = Convert.propertiestoProperties(element);
                }
                if (command == null && Constants.MSXMLA.QN_COMMAND.equals(element.getElementQName())) {
                    command = Convert.commandtoCommand(element);
                }
            }
        }

        execute(command, properties, parameters, responseBody, metaData, userPrincipal);
    }

    private void printNode(SOAPElement node) {
        LOGGER.debug(node.getNamespaceURI());
        LOGGER.debug(node.getBaseURI());
        LOGGER.debug(node.getPrefix());
        LOGGER.debug(node.getNodeName());
        LOGGER.debug(node.getLocalName());
        LOGGER.debug(node.getNodeValue());
        LOGGER.debug(node.getTextContent());
        LOGGER.debug(node.getValue());
        String elementQNameStr = node.getElementQName().toString();
        LOGGER.debug(elementQNameStr);
    }

    private void execute(Command command,
                             PropertiesR properties,
                             List<ExecuteParameter> parameters,
                             SOAPBody responseBody,
                             RequestMetaData metaData,
                             UserPrincipal userPrincipal) throws SOAPException {

        if (command instanceof StatementR statement) {
            handleStatement(metaData, userPrincipal, statement, properties, parameters, responseBody);
        }
        if (command instanceof AlterR alter) {
            handleAlter(metaData, userPrincipal, alter, properties, parameters, responseBody);
        }
        if (command instanceof ClearCacheR clearCache) {
            handleClearCache(metaData, userPrincipal, clearCache, properties, parameters, responseBody);
        }
        if (command instanceof CancelR cancel) {
            handleCancel(metaData, userPrincipal, cancel, properties, parameters, responseBody);
        }
    }

    private void discover(String requestType,
    						  RequestMetaData metaData,
                              UserPrincipal userPrincipal,
                              PropertiesR properties,
                              SOAPElement restrictionElement,
                              SOAPBody responseBody) throws SOAPException {

        switch (requestType) {
            case MDSCHEMA_FUNCTIONS -> handleMdSchemaFunctions(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_DIMENSIONS -> handleMdSchemaDimensions(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_CUBES -> handleMdSchemaCubes(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_ACTIONS ->  handleMdSchemaActions(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_TABLES ->  handleDbSchemaTables(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_LITERALS ->  handleDiscoverLiterals(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_KEYWORDS ->  handleDiscoverKeywords(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_ENUMERATORS ->  handleDiscoverEnumerators(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_SCHEMA_ROWSETS ->  handleDiscoverSchemaRowsets(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_PROPERTIES ->  handleDiscoverProperties(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_CATALOGS ->  handleDbSchemaCatalogs(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_DATASOURCES -> handleDiscoverDataSources(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DISCOVER_XML_METADATA -> handleDiscoverXmlMetaData(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_COLUMNS -> handleDbSchemaColumns(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_PROVIDER_TYPES -> handleDbSchemaProviderTypes(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_SCHEMATA -> handleDbSchemaSchemata(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_SOURCE_TABLES -> handleDbSchemaSourceTables(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case DBSCHEMA_TABLES_INFO -> handleDbSchemaTablesInfo(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_HIERARCHIES -> handleMdSchemaHierarchies(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_LEVELS -> handleMdSchemaLevels(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_MEASUREGROUP_DIMENSIONS -> handleMdSchemaMeasureGroupDimensions(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_MEASURES -> handleMdSchemaMeasures(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_MEMBERS -> handleMdSchemaMembers(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_PROPERTIES -> handleMdSchemaProperties(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_SETS -> handleMdSchemaSets(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_KPIS -> handleMdSchemaKpis(metaData, userPrincipal, properties, restrictionElement, responseBody);
            case MDSCHEMA_MEASUREGROUPS -> handleMdSchemaMeasureGroups(metaData, userPrincipal, properties, restrictionElement, responseBody);
            default -> throw new IllegalArgumentException("Unexpected value: " + requestType);

        }
    }

    private void handleMdSchemaMeasureGroups(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaMeasureGroupsRestrictionsR restrictionsR = Convert.discoverMdSchemaMeasureGroups(restrictionElement);
        MdSchemaMeasureGroupsRequest request = new MdSchemaMeasureGroupsRequestR(propertiesR, restrictionsR);
        List<MdSchemaMeasureGroupsResponseRow> rows = xmlaService.discover()
            .mdSchemaMeasureGroups(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaMeasureGroups(rows, body);
    }

    private void handleMdSchemaKpis(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaKpisRestrictionsR restrictionsR = Convert.discoverMdSchemaKpisRestrictions(restrictionElement);
        MdSchemaKpisRequest request = new MdSchemaKpisRequestR(propertiesR, restrictionsR);
        List<MdSchemaKpisResponseRow> rows = xmlaService.discover()
            .mdSchemaKpis(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaKpis(rows, body);
    }

    private void handleMdSchemaSets(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaSetsRestrictionsR restrictionsR = Convert.discoverMdSchemaSetsRestrictions(restrictionElement);
        MdSchemaSetsRequest request = new MdSchemaSetsRequestR(propertiesR, restrictionsR);
        List<MdSchemaSetsResponseRow> rows = xmlaService.discover()
            .mdSchemaSets(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaSets(rows, body);
    }

    private void handleMdSchemaProperties(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaPropertiesRestrictionsR restrictionsR = Convert.discoverMdSchemaPropertiesRestrictions(restrictionElement);
        MdSchemaPropertiesRequest request = new MdSchemaPropertiesRequestR(propertiesR, restrictionsR);
        List<MdSchemaPropertiesResponseRow> rows = xmlaService.discover()
            .mdSchemaProperties(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaProperties(rows, body);

    }

    private void handleMdSchemaMembers(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaMembersRestrictionsR restrictionsR = Convert.discoverMdSchemaMembersRestrictions(restrictionElement);
        MdSchemaMembersRequest request = new MdSchemaMembersRequestR(propertiesR, restrictionsR);
        List<MdSchemaMembersResponseRow> rows = xmlaService.discover()
            .mdSchemaMembers(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaMembers(rows, body);

    }

    private void handleMdSchemaMeasures(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaMeasuresRestrictionsR restrictionsR = Convert.discoverMdSchemaMeasuresRestrictions(restrictionElement);
        MdSchemaMeasuresRequest request = new MdSchemaMeasuresRequestR(propertiesR, restrictionsR);
        List<MdSchemaMeasuresResponseRow> rows = xmlaService.discover()
            .mdSchemaMeasures(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaMeasures(rows, body);

    }

    private void handleMdSchemaMeasureGroupDimensions(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaMeasureGroupDimensionsRestrictionsR restrictionsR = Convert.discoverMdSchemaMeasureGroupDimensionsRestrictions(restrictionElement);
        MdSchemaMeasureGroupDimensionsRequest request = new MdSchemaMeasureGroupDimensionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaMeasureGroupDimensionsResponseRow> rows = xmlaService.discover()
            .mdSchemaMeasureGroupDimensions(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaMeasureGroupDimensions(rows, body);

    }

    private void handleMdSchemaLevels(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaLevelsRestrictionsR restrictionsR = Convert.discoverMdSchemaLevelsRestrictions(restrictionElement);
        MdSchemaLevelsRequest request = new MdSchemaLevelsRequestR(propertiesR, restrictionsR);
        List<MdSchemaLevelsResponseRow> rows = xmlaService.discover()
            .mdSchemaLevels(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaLevels(rows, body);
    }

    private void handleMdSchemaHierarchies(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaHierarchiesRestrictionsR restrictionsR = Convert.discoverMdSchemaHierarchiesRestrictions(restrictionElement);
        MdSchemaHierarchiesRequest request = new MdSchemaHierarchiesRequestR(propertiesR, restrictionsR);
        List<MdSchemaHierarchiesResponseRow> rows = xmlaService.discover()
            .mdSchemaHierarchies(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaHierarchies(rows, body);
    }

    private void handleDbSchemaTablesInfo(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaTablesInfoRestrictionsR restrictionsR = Convert.discoverDbSchemaTablesInfo(restrictionElement);
        DbSchemaTablesInfoRequest request = new DbSchemaTablesInfoRequestR(propertiesR, restrictionsR);
        List<DbSchemaTablesInfoResponseRow> rows = xmlaService.discover()
            .dbSchemaTablesInfo(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaTablesInfo(rows, body);

    }

    private void handleDbSchemaSourceTables(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaSourceTablesRestrictionsR restrictionsR = Convert.discoverDbSchemaSourceTablesRestrictions(restrictionElement);
        DbSchemaSourceTablesRequest request = new DbSchemaSourceTablesRequestR(propertiesR, restrictionsR);
        List<DbSchemaSourceTablesResponseRow> rows = xmlaService.discover()
            .dbSchemaSourceTables(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaSourceTables(rows, body);

    }

    private void handleDbSchemaSchemata(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaSchemataRestrictionsR restrictionsR = Convert.discoverDbSchemaSchemataRestrictions(restrictionElement);
        DbSchemaSchemataRequest request = new DbSchemaSchemataRequestR(propertiesR, restrictionsR);
        List<DbSchemaSchemataResponseRow> rows = xmlaService.discover()
            .dbSchemaSchemata(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaSchemata(rows, body);

    }

    private void handleDbSchemaProviderTypes(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaProviderTypesRestrictionsR restrictionsR = Convert.discoverDbSchemaProviderTypesRestrictions(restrictionElement);
        DbSchemaProviderTypesRequest request = new DbSchemaProviderTypesRequestR(propertiesR, restrictionsR);
        List<DbSchemaProviderTypesResponseRow> rows = xmlaService.discover()
            .dbSchemaProviderTypes(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaProviderTypes(rows, body);

    }

    private void handleDbSchemaColumns(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaColumnsRestrictionsR restrictionsR = Convert.discoverDbSchemaColumnsRestrictions(restrictionElement);
        DbSchemaColumnsRequest request = new DbSchemaColumnsRequestR(propertiesR, restrictionsR);
        List<DbSchemaColumnsResponseRow> rows = xmlaService.discover()
            .dbSchemaColumns(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaColumns(rows, body);

    }

    private void handleDiscoverXmlMetaData(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DiscoverXmlMetaDataRestrictionsR restrictionsR = Convert.discoverDiscoverXmlMetaDataRestrictions(restrictionElement);
        DiscoverXmlMetaDataRequest request = new DiscoverXmlMetaDataRequestR(propertiesR, restrictionsR);
        List<DiscoverXmlMetaDataResponseRow> rows = xmlaService.discover()
            .xmlMetaData(request, metaData, userPrincipal);

        SoapUtil.toDiscoverXmlMetaData(rows, body);

    }

    private void handleDiscoverDataSources(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DiscoverDataSourcesRestrictionsR restrictionsR = Convert.discoverDiscoverDataSourcesRestrictions(restrictionElement);
        DiscoverDataSourcesRequest request = new DiscoverDataSourcesRequestR(propertiesR, restrictionsR);
        List<DiscoverDataSourcesResponseRow> rows = xmlaService.discover()
            .dataSources(request, metaData, userPrincipal);

        SoapUtil.toDiscoverDataSources(rows, body);

    }

    private void handleDbSchemaCatalogs(RequestMetaData metaData, UserPrincipal userPrincipal,  PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaCatalogsRestrictionsR restrictionsR = Convert.discoverDbSchemaCatalogsRestrictions(restrictionElement);
        DbSchemaCatalogsRequest request = new DbSchemaCatalogsRequestR(propertiesR, restrictionsR);
        List<DbSchemaCatalogsResponseRow> rows = xmlaService.discover()
            .dbSchemaCatalogs(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaCatalogs(rows, body);

    }

    private void handleDiscoverSchemaRowsets(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DiscoverSchemaRowsetsRestrictionsR restrictionsR = Convert.discoverSchemaRowsetsRestrictions(restrictionElement);
        DiscoverSchemaRowsetsRequest request = new DiscoverSchemaRowsetsRequestR(propertiesR, restrictionsR);
        List<DiscoverSchemaRowsetsResponseRow> rows = xmlaService.discover()
            .discoverSchemaRowsets(request, metaData, userPrincipal);

        SoapUtil.toDiscoverSchemaRowsets(rows, body);

    }

    private void handleDiscoverEnumerators(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DiscoverEnumeratorsRestrictionsR restrictionsR = Convert.discoverDiscoverEnumerators(restrictionElement);
        DiscoverEnumeratorsRequest request = new DiscoverEnumeratorsRequestR(propertiesR, restrictionsR);
        List<DiscoverEnumeratorsResponseRow> rows = xmlaService.discover()
            .discoverEnumerators(request, metaData, userPrincipal);

        SoapUtil.toDiscoverEnumerators(rows, body);

    }

    private void handleDiscoverKeywords(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DiscoverKeywordsRestrictionsR restrictionsR = Convert.discoverKeywordsRestrictions(restrictionElement);
        DiscoverKeywordsRequest request = new DiscoverKeywordsRequestR(propertiesR, restrictionsR);
        List<DiscoverKeywordsResponseRow> rows = xmlaService.discover()
            .discoverKeywords(request, metaData, userPrincipal);

        SoapUtil.toDiscoverKeywords(rows, body);

    }

    private void handleDiscoverLiterals(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DiscoverLiteralsRestrictionsR restrictionsR = Convert.discoverLiteralsRestrictions(restrictionElement);
        DiscoverLiteralsRequest request = new DiscoverLiteralsRequestR(propertiesR, restrictionsR);
        List<DiscoverLiteralsResponseRow> rows = xmlaService.discover()
            .discoverLiterals(request, metaData, userPrincipal);

        SoapUtil.toDiscoverLiterals(rows, body);

    }

    private void handleDbSchemaTables(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        DbSchemaTablesRestrictionsR restrictionsR = Convert.discoverDbSchemaTablesRestrictions(restrictionElement);
        DbSchemaTablesRequest request = new DbSchemaTablesRequestR(propertiesR, restrictionsR);
        List<DbSchemaTablesResponseRow> rows = xmlaService.discover()
            .dbSchemaTables(request, metaData, userPrincipal);

        SoapUtil.toDbSchemaTables(rows, body);

    }

    private void handleMdSchemaActions(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaActionsRestrictionsR restrictionsR = Convert.discoverMdSchemaActionsRestrictions(restrictionElement);
        MdSchemaActionsRequest request = new MdSchemaActionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaActionsResponseRow> rows = xmlaService.discover()
            .mdSchemaActions(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaActions(rows, body);
    }

    private void handleMdSchemaCubes(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaCubesRestrictionsR restrictionsR = Convert.discoverMdSchemaCubesRestrictions(restrictionElement);
        MdSchemaCubesRequest request = new MdSchemaCubesRequestR(propertiesR, restrictionsR);
        List<MdSchemaCubesResponseRow> rows = xmlaService.discover()
            .mdSchemaCubes(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaCubes(rows, body);
    }

    private  void handleMdSchemaDimensions(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {
        MdSchemaDimensionsRestrictionsR restrictionsR = Convert.discoverMdSchemaDimensionsRestrictions(restrictionElement);
        MdSchemaDimensionsRequest request = new MdSchemaDimensionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaDimensionsResponseRow> rows = xmlaService.discover()
            .mdSchemaDimensions(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaDimensions(rows, body);
    }

    private void handleDiscoverProperties(RequestMetaData metaData, UserPrincipal userPrincipal,PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {

        DiscoverPropertiesRestrictionsR restrictionsR = Convert.discoverPropertiesRestrictions(restrictionElement);
        DiscoverPropertiesRequest request = new DiscoverPropertiesRequestR(propertiesR, restrictionsR);
        List<DiscoverPropertiesResponseRow> rows = xmlaService.discover()
                .discoverProperties(request, metaData, userPrincipal);

        SoapUtil.toDiscoverProperties(rows, body);
    }

    private void handleMdSchemaFunctions(RequestMetaData metaData, UserPrincipal userPrincipal, PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body)  throws SOAPException {

        MdSchemaFunctionsRestrictionsR restrictionsR = Convert.discoverMdSchemaFunctionsRestrictions(restrictionElement);
        MdSchemaFunctionsRequest request = new MdSchemaFunctionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaFunctionsResponseRow> rows = xmlaService.discover()
            .mdSchemaFunctions(request, metaData, userPrincipal);

        SoapUtil.toMdSchemaFunctions(rows, body);
    }

    private void handleStatement(RequestMetaData metaData, UserPrincipal userPrincipal, StatementR statement,
                                     PropertiesR properties,
                                     List<ExecuteParameter> parameters,
                                     SOAPBody responseBody)  throws SOAPException {
        String sessionId = metaData != null && metaData.sessionId() != null && metaData.sessionId().isPresent() ? metaData.sessionId().get() : null;
        StatementRequest statementRequest = new StatementRequestR(properties,
            parameters,
            statement, sessionId);
        StatementResponse statementResponse = xmlaService.execute().statement(statementRequest, metaData, userPrincipal);
        SoapUtil.toStatementResponse(statementResponse, responseBody);
    }

    private void handleAlter(RequestMetaData metaData, UserPrincipal userPrincipal, AlterR alter,
                                 PropertiesR properties,
                                 List<ExecuteParameter> parameters,
                                 SOAPBody responseBody)  throws SOAPException {
        AlterRequest alterRequest = new AlterRequestR(properties,
            parameters,
            alter);
        AlterResponse alterResponse = xmlaService.execute().alter(alterRequest, metaData, userPrincipal);
        SoapUtil.toAlterResponse(alterResponse, responseBody);
    }

    private void handleClearCache(RequestMetaData metaData, UserPrincipal userPrincipal, ClearCacheR clearCache, PropertiesR properties, List<ExecuteParameter> parameters, SOAPBody responseBody)  throws SOAPException {
        ClearCacheRequest clearCacheRequest = new ClearCacheRequestR(properties,
            parameters,
            clearCache);
        ClearCacheResponse clearCacheResponse = xmlaService.execute().clearCache(clearCacheRequest, metaData, userPrincipal);
        SoapUtil.toClearCacheResponse(clearCacheResponse, responseBody);
    }

    private void handleCancel(RequestMetaData metaData, UserPrincipal userPrincipal, CancelR cancel, PropertiesR properties, List<ExecuteParameter> parameters, SOAPBody responseBody) throws SOAPException {
        CancelRequest cancelRequest = new CancelRequestR(properties,
            parameters,
            cancel);
        CancelResponse cancelResponse = xmlaService.execute().cancel(cancelRequest, metaData, userPrincipal);
        SoapUtil.toCancelResponse(cancelResponse, responseBody);
    }

}
