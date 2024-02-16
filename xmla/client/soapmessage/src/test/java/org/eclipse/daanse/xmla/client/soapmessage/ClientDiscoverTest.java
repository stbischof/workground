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

import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.Provider;
import org.eclipse.daanse.ws.api.whiteboard.annotations.RequireSoapWhiteboard;
import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.common.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.AuthenticationModeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ClientCacheRefreshPolicyEnum;
import org.eclipse.daanse.xmla.api.common.enums.ColumnFlagsEnum;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CustomRollupSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionCardinalityEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionUniqueSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.DirectQueryPushableEnum;
import org.eclipse.daanse.xmla.api.common.enums.GroupingBehaviorEnum;
import org.eclipse.daanse.xmla.api.common.enums.HierarchyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.InstanceSelectionEnum;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.InvocationEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelUniqueSettingsEnum;
import org.eclipse.daanse.xmla.api.common.enums.LiteralNameEnumValueEnum;
import org.eclipse.daanse.xmla.api.common.enums.MeasureAggregatorEnum;
import org.eclipse.daanse.xmla.api.common.enums.MemberTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ObjectExpansionEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PreferredQueryPatternsEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyCardinalityEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ProviderTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.common.enums.SearchableEnum;
import org.eclipse.daanse.xmla.api.common.enums.SetEvaluationContextEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TreeOpEnum;
import org.eclipse.daanse.xmla.api.common.enums.TypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.common.properties.PropertyListElementDefinition;
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
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.ParameterInfo;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MeasureGroupDimension;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.xmlunit.assertj3.XmlAssert;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.ACTIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.CATALOGS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.CUBES;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.DATA_SOURCES;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.DIMENSIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.ENUMERARORS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.FUNCTIONS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.KEYWORDS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.LITERALS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.PROPERTIES;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.PROVIDER_TYPES;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.SCHEMAROWSETS;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.SCHEMATA;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.SOURCE_TABLES;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.TABLES;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.TABLES_INFO;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.XML_META_DATA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RequireSoapWhiteboard
@ExtendWith(ConfigurationExtension.class)
@WithFactoryConfiguration(factoryPid = "org.eclipse.daanse.ws.handler.SOAPLoggingHandler", name = "test-ms-config", location = "?", properties = {
        @Property(key = "osgi.soap.endpoint.selector", value = "(service.pid=*)") })
@RequireServiceComponentRuntime
class ClientDiscoverTest {
    XmlaServiceClientImpl client = new XmlaServiceClientImpl("http://localhost:8090/xmla");
    // Register a Provider using whiteboardpattern and xmlassert to check xml
    @InjectBundleContext
    BundleContext bc;

    private ArgumentCaptor<SOAPMessage> requestMessageCaptor;

    @BeforeEach
    void beforeEach() {
        requestMessageCaptor = ArgumentCaptor.forClass(SOAPMessage.class);
    }

    @Test
    void testDataSources() throws Exception {
        Provider<SOAPMessage> provider = registerService(DATA_SOURCES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverDataSourcesRestrictionsR restrictions = new DiscoverDataSourcesRestrictionsR(
            "dataSourceName",
            Optional.of("dataSourceDescription"),
            Optional.of("url"), Optional.of("dataSourceInfo"),
            "providerName",
            Optional.of(ProviderTypeEnum.DMP),
            Optional.of(AuthenticationModeEnum.AUTHENTICATED)
        );

        DiscoverDataSourcesRequest dataSourcesRequest = new DiscoverDataSourcesRequestR(properties, restrictions);

        List<DiscoverDataSourcesResponseRow> rows = client.discover()
                .dataSources(dataSourcesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverDataSourcesResponseRow r = rows.get(0);
        assertThat(r.dataSourceName()).isEqualTo("FoodMart");
        assertThat(r.dataSourceDescription()).isPresent().contains("Foodmart 2000 on local machine");
        assertThat(r.url()).isPresent().contains("http://localhost/xmla/msxisapi.dll");
        assertThat(r.dataSourceInfo()).isPresent().contains("Foodmart 2000");
        assertThat(r.providerType()).isPresent().contains(ProviderTypeEnum.TDP);
        assertThat(r.authenticationMode()).isPresent().contains(AuthenticationModeEnum.UNAUTHENTICATED);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_DATASOURCES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DataSourceName")
            .isEqualTo("dataSourceName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DataSourceDescription")
            .isEqualTo("dataSourceDescription");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:URL")
            .isEqualTo("url");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DataSourceInfo")
            .isEqualTo("dataSourceInfo");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:ProviderName")
            .isEqualTo("providerName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:ProviderType")
            .isEqualTo("DMP");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:AuthenticationMode")
            .isEqualTo("Authenticated");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }


    @Test
    void testDiscoverEnumerators() throws Exception {
        Provider<SOAPMessage> provider = registerService(ENUMERARORS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverEnumeratorsRestrictionsR restrictions = new DiscoverEnumeratorsRestrictionsR(Optional.of("FoodMart"));

        DiscoverEnumeratorsRequest discoverEnumeratorsRequest = new DiscoverEnumeratorsRequestR(properties, restrictions);

        List<DiscoverEnumeratorsResponseRow> rows = client.discover()
            .discoverEnumerators(discoverEnumeratorsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverEnumeratorsResponseRow r = rows.get(0);
        assertThat(r.enumName()).isEqualTo("EnumName");
        assertThat(r.enumDescription()).isPresent().contains("EnumDescription");
        assertThat(r.enumType()).isEqualTo("EnumType");
        assertThat(r.elementName()).isEqualTo("ElementName");
        assertThat(r.elementDescription()).isPresent().contains("ElementDescription");
        assertThat(r.elementValue()).isPresent().contains("ElementValue");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_ENUMERATORS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:EnumName")
            .isEqualTo("FoodMart");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testDiscoverKeywords() throws Exception {
        Provider<SOAPMessage> provider = registerService(KEYWORDS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverKeywordsRestrictionsR restrictions = new DiscoverKeywordsRestrictionsR(Optional.of("Keyword"));

        DiscoverKeywordsRequest discoverKeywordsRequest = new DiscoverKeywordsRequestR(properties, restrictions);

        List<DiscoverKeywordsResponseRow> rows = client.discover()
            .discoverKeywords(discoverKeywordsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverKeywordsResponseRow r = rows.get(0);
        assertThat(r.keyword()).isEqualTo("Keyword");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_KEYWORDS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:Keyword")
            .isEqualTo("Keyword");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testDiscoverLiterals() throws Exception {
        Provider<SOAPMessage> provider = registerService(LITERALS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverLiteralsRestrictionsR restrictions = new DiscoverLiteralsRestrictionsR(Optional.of("LiteralName"));

        DiscoverLiteralsRequest discoverLiteralsRequest = new DiscoverLiteralsRequestR(properties, restrictions);

        List<DiscoverLiteralsResponseRow> rows = client.discover()
            .discoverLiterals(discoverLiteralsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverLiteralsResponseRow r = rows.get(0);
        assertThat(r.literalName()).isEqualTo("LiteralName");
        assertThat(r.literalValue()).isEqualTo("LiteralValue");
        assertThat(r.literalInvalidChars()).isEqualTo("LiteralInvalidChars");
        assertThat(r.literalInvalidStartingChars()).isEqualTo("LiteralInvalidStartingChars");
        assertThat(r.literalMaxLength()).isEqualTo(100);
        assertThat(r.literalNameEnumValue()).isEqualTo(LiteralNameEnumValueEnum.DBLITERAL_BINARY_LITERAL);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_LITERALS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LiteralName")
            .isEqualTo("LiteralName");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testDiscoverProperties() throws Exception {
        Provider<SOAPMessage> provider = registerService(PROPERTIES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverPropertiesRestrictionsR restrictions = new DiscoverPropertiesRestrictionsR(Optional.of("PropertyName"));

        DiscoverPropertiesRequest discoverPropertiesRequest = new DiscoverPropertiesRequestR(properties, restrictions);

        List<DiscoverPropertiesResponseRow> rows = client.discover()
            .discoverProperties(discoverPropertiesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverPropertiesResponseRow r = rows.get(0);

        assertThat(r.propertyName()).isEqualTo("PropertyName");
        assertThat(r.propertyDescription()).isPresent().contains("PropertyDescription");
        assertThat(r.propertyType()).isPresent().contains("PropertyType");
        assertThat(r.propertyAccessType()).isEqualTo("PropertyAccessType");
        assertThat(r.required()).isPresent().contains(true);
        assertThat(r.value()).isPresent().contains("Value");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_PROPERTIES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PropertyName")
            .isEqualTo("PropertyName");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testSchemaRowSets() throws Exception {
        Provider<SOAPMessage> provider = registerService(SCHEMAROWSETS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverSchemaRowsetsRestrictionsR restrictions = new DiscoverSchemaRowsetsRestrictionsR(Optional.of("SchemaName"));

        DiscoverSchemaRowsetsRequest discoverSchemaRowsetsRequest = new DiscoverSchemaRowsetsRequestR(properties, restrictions);

        List<DiscoverSchemaRowsetsResponseRow> rows = client.discover()
            .discoverSchemaRowsets(discoverSchemaRowsetsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverSchemaRowsetsResponseRow r = rows.get(0);

        assertThat(r.schemaName()).isEqualTo("SchemaName");
        assertThat(r.schemaGuid()).isPresent().contains("SchemaGuid");
        assertThat(r.restrictions()).isPresent();
        assertThat(r.restrictions().get()).hasSize(1);
        assertThat(r.restrictions().get().get(0)).isNotNull();
        assertThat(r.restrictions().get().get(0).name()).isEqualTo("TABLE_SCHEMA");
        assertThat(r.restrictions().get().get(0).type()).isEqualTo("xsd:string");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.restrictionsMask()).isPresent().contains(100l);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_SCHEMA_ROWSETS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SchemaName")
            .isEqualTo("SchemaName");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testXmlMetaData() throws Exception {
        Provider<SOAPMessage> provider = registerService(XML_META_DATA);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DiscoverXmlMetaDataRestrictionsR restrictions = new DiscoverXmlMetaDataRestrictionsR(
            Optional.of("DatabaseId"),
            Optional.of("DimensionId"),
            Optional.of("CubeId"),
            Optional.of("MeasureGroupId"),
            Optional.of("PartitionId"),
            Optional.of("PerspectiveId"),
            Optional.of("DimensionPermissionId"),
            Optional.of("RoleId"),
            Optional.of("DatabasePermissionId"),
            Optional.of("MiningModelId"),
            Optional.of("MiningModelPermissionId"),
            Optional.of("DataSourceId"),
            Optional.of("MiningStructureId"),
            Optional.of("AggregationDesignId"),
            Optional.of("TraceId"),
            Optional.of("MiningStructurePermissionId"),
            Optional.of("CubePermissionId"),
            Optional.of("AssemblyId"),
            Optional.of("MdxScriptId"),
            Optional.of("DataSourceViewId"),
            Optional.of("DataSourcePermissionId"),
            Optional.of(ObjectExpansionEnum.EXPAND_OBJECT)
        );

        DiscoverXmlMetaDataRequest discoverXmlMetaDataRequest = new DiscoverXmlMetaDataRequestR(properties, restrictions);

        List<DiscoverXmlMetaDataResponseRow> rows = client.discover()
            .xmlMetaData(discoverXmlMetaDataRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DiscoverXmlMetaDataResponseRow r = rows.get(0);

        assertThat(r.metaData()).isEqualTo("MetaData");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DISCOVER_XML_METADATA");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DatabaseID")
            .isEqualTo("DatabaseId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DimensionID").isEqualTo("DimensionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CubeID").isEqualTo("CubeId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MeasureGroupID").isEqualTo("MeasureGroupId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PartitionID").isEqualTo("PartitionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PerspectiveID").isEqualTo("PerspectiveId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DimensionPermissionID").isEqualTo("DimensionPermissionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:RoleID").isEqualTo("RoleId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DatabasePermissionID").isEqualTo("DatabasePermissionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MiningModelID").isEqualTo("MiningModelId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MiningModelPermissionID").isEqualTo("MiningModelPermissionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DataSourceID").isEqualTo("DataSourceId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MiningStructureID").isEqualTo("MiningStructureId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:AggregationDesignID").isEqualTo("AggregationDesignId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TraceID").isEqualTo("TraceId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MiningStructurePermissionID").isEqualTo("MiningStructurePermissionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CubePermissionID").isEqualTo("CubePermissionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:AssemblyID").isEqualTo("AssemblyId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MdxScriptID").isEqualTo("MdxScriptId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DataSourceViewID").isEqualTo("DataSourceViewId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DataSourcePermissionID").isEqualTo("DataSourcePermissionId");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:ObjectExpansion").isEqualTo("ExpandObject");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testCatalogs() throws Exception {


		RequestMetaData metaData= mock(RequestMetaData.class);
		when(metaData.userAgent()).thenReturn(Optional.empty());

        Provider<SOAPMessage> provider = registerService(CATALOGS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaCatalogsRestrictionsR restrictions = new DbSchemaCatalogsRestrictionsR(Optional.of("CatalogName"));

        DbSchemaCatalogsRequest dbSchemaCatalogsRequest = new DbSchemaCatalogsRequestR(properties, restrictions);

        List<DbSchemaCatalogsResponseRow> rows = client.discover()
            .dbSchemaCatalogs(dbSchemaCatalogsRequest,metaData);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaCatalogsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.roles()).isPresent().contains("Roles");
        assertThat(r.dateModified()).isPresent().contains(LocalDateTime.of(2023, Month.JANUARY, 10, 10, 45));
        assertThat(r.compatibilityLevel()).isPresent().contains(10);
        assertThat(r.type()).isPresent().contains(TypeEnum.MULTIDIMENSIONAL);
        assertThat(r.version()).isPresent().contains(10);
        assertThat(r.databaseId()).isPresent().contains("DatabaseId");
        assertThat(r.dateQueried()).isPresent().contains(LocalDateTime.of(2024, Month.JANUARY, 10, 10, 45));
        assertThat(r.currentlyUsed()).isPresent().contains(true);
        assertThat(r.popularity()).isPresent().contains(2.25);
        assertThat(r.weightedPopularity()).isPresent().contains(2.26);
        assertThat(r.clientCacheRefreshPolicy()).isPresent().contains(ClientCacheRefreshPolicyEnum.REFRESH_NEWER_DATA);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_CATALOGS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testColumns() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.COLUMNS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaColumnsRestrictionsR restrictions = new DbSchemaColumnsRestrictionsR(
            Optional.of("TableCatalog"),
            Optional.of("TableSchema"),
            Optional.of("TableName"),
            Optional.of("ColumnName"),
            Optional.of(ColumnOlapTypeEnum.ATTRIBUTE)
        );

        DbSchemaColumnsRequest dbSchemaColumnsRequest = new DbSchemaColumnsRequestR(properties, restrictions);

        List<DbSchemaColumnsResponseRow> rows = client.discover()
            .dbSchemaColumns(dbSchemaColumnsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaColumnsResponseRow r = rows.get(0);

        assertThat(r.tableCatalog()).isPresent().contains("TableCatalog");
        assertThat(r.tableSchema()).isPresent().contains("TableSchema");
        assertThat(r.tableName()).isPresent().contains("TableName");
        assertThat(r.columnName()).isPresent().contains("ColumnName");
        assertThat(r.columnGuid()).isPresent().contains(10);
        assertThat(r.columnPropId()).isPresent().contains(11);
        assertThat(r.ordinalPosition()).isPresent().contains(12);
        assertThat(r.columnHasDefault()).isPresent().contains(true);
        assertThat(r.columnDefault()).isPresent().contains("ColumnDefault");
        assertThat(r.columnFlags()).isPresent().contains(ColumnFlagsEnum.DBCOLUMNFLAGS_ISBOOKMARK);
        assertThat(r.dataType()).isPresent().contains(10);
        assertThat(r.typeGuid()).isPresent().contains(11);
        assertThat(r.characterMaximum()).isPresent().contains(12);
        assertThat(r.characterOctetLength()).isPresent().contains(14);
        assertThat(r.numericPrecision()).isPresent().contains(15);
        assertThat(r.numericScale()).isPresent().contains(16);
        assertThat(r.dateTimePrecision()).isPresent().contains(17);
        assertThat(r.characterSetCatalog()).isPresent().contains("CharacterSetCatalog");
        assertThat(r.characterSetSchema()).isPresent().contains("CharacterSetSchema");
        assertThat(r.characterSetName()).isPresent().contains("CharacterSetName");
        assertThat(r.collationCatalog()).isPresent().contains("CollationCatalog");
        assertThat(r.collationSchema()).isPresent().contains("CollationSchema");
        assertThat(r.collationName()).isPresent().contains("CollationName");
        assertThat(r.domainCatalog()).isPresent().contains("DomainCatalog");
        assertThat(r.domainSchema()).isPresent().contains("DomainSchema");
        assertThat(r.domainName()).isPresent().contains("DomainName");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.columnOlapType()).isPresent().contains(ColumnOlapTypeEnum.ATTRIBUTE);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_COLUMNS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_CATALOG")
            .isEqualTo("TableCatalog");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_SCHEMA")
            .isEqualTo("TableSchema");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_NAME")
            .isEqualTo("TableName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:COLUMN_NAME")
            .isEqualTo("ColumnName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:COLUMN_OLAP_TYPE")
            .isEqualTo("ATTRIBUTE");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testProviderTypes() throws Exception {
        Provider<SOAPMessage> provider = registerService(PROVIDER_TYPES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaProviderTypesRestrictionsR restrictions = new DbSchemaProviderTypesRestrictionsR(
            Optional.of(LevelDbTypeEnum.DBTYPE_EMPTY),
            Optional.of(true)
        );

        DbSchemaProviderTypesRequest dbSchemaProviderTypesRequest = new DbSchemaProviderTypesRequestR(properties, restrictions);

        List<DbSchemaProviderTypesResponseRow> rows = client.discover()
            .dbSchemaProviderTypes(dbSchemaProviderTypesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaProviderTypesResponseRow r = rows.get(0);

        assertThat(r.typeName()).isPresent().contains("TypeName");
        assertThat(r.dataType()).isPresent().contains(LevelDbTypeEnum.DBTYPE_EMPTY);
        assertThat(r.columnSize()).isPresent().contains(10);
        assertThat(r.literalPrefix()).isPresent().contains("LiteralPrefix");
        assertThat(r.literalSuffix()).isPresent().contains("LiteralSuffix");
        assertThat(r.createParams()).isPresent().contains("CreateParams");
        assertThat(r.isNullable()).isPresent().contains(true);
        assertThat(r.caseSensitive()).isPresent().contains(true);
        assertThat(r.searchable()).isPresent().contains(SearchableEnum.DB_UNSEARCHABLE);
        assertThat(r.unsignedAttribute()).isPresent().contains(true);
        assertThat(r.fixedPrecScale()).isPresent().contains(true);
        assertThat(r.autoUniqueValue()).isPresent().contains(true);
        assertThat(r.localTypeName()).isPresent().contains("LocalTypeName");
        assertThat(r.minimumScale()).isPresent().contains(10);
        assertThat(r.maximumScale()).isPresent().contains(11);
        assertThat(r.guid()).isPresent().contains(12);
        assertThat(r.typeLib()).isPresent().contains("TypeLib");
        assertThat(r.version()).isPresent().contains("Version");
        assertThat(r.isLong()).isPresent().contains(true);
        assertThat(r.bestMatch()).isPresent().contains(true);
        assertThat(r.isFixedLength()).isPresent().contains(true);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_PROVIDER_TYPES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DATA_TYPE")
            .isEqualTo("0");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:BEST_MATCH")
            .isEqualTo("true");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testSchemata() throws Exception {
        Provider<SOAPMessage> provider = registerService(SCHEMATA);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaSchemataRestrictionsR restrictions = new DbSchemaSchemataRestrictionsR(
            "CatalogName",
            "SchemaName",
            "SchemaOwner"
        );

        DbSchemaSchemataRequest dbSchemaSchemataRequest = new DbSchemaSchemataRequestR(properties, restrictions);

        List<DbSchemaSchemataResponseRow> rows = client.discover()
            .dbSchemaSchemata(dbSchemaSchemataRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaSchemataResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isEqualTo("CatalogName");
        assertThat(r.schemaName()).isEqualTo("SchemaName");
        assertThat(r.schemaOwner()).isEqualTo("SchemaOwner");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_SCHEMATA");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_OWNER")
            .isEqualTo("SchemaOwner");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testSourceTables() throws Exception {
        Provider<SOAPMessage> provider = registerService(SOURCE_TABLES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaSourceTablesRestrictionsR restrictions = new DbSchemaSourceTablesRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            "TableName",
            TableTypeEnum.TABLE
        );

        DbSchemaSourceTablesRequest dbSchemaSourceTablesRequest = new DbSchemaSourceTablesRequestR(properties, restrictions);

        List<DbSchemaSourceTablesResponseRow> rows = client.discover()
            .dbSchemaSourceTables(dbSchemaSourceTablesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaSourceTablesResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.tableName()).isEqualTo("TableName");
        assertThat(r.tableType()).isEqualTo(TableTypeEnum.TABLE);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_SOURCE_TABLES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_CATALOG")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_SCHEMA")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_NAME")
            .isEqualTo("TableName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_TYPE")
            .isEqualTo("TABLE");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    void testTables() throws Exception {
        Provider<SOAPMessage> provider = registerService(TABLES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaTablesRestrictionsR restrictions = new DbSchemaTablesRestrictionsR(
            Optional.of("TableCatalog"),
            Optional.of("TableSchema"),
            Optional.of("TableName"),
            Optional.of("TableType")
        );

        DbSchemaTablesRequest dbSchemaTablesRequest = new DbSchemaTablesRequestR(properties, restrictions);

        List<DbSchemaTablesResponseRow> rows = client.discover()
            .dbSchemaTables(dbSchemaTablesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaTablesResponseRow r = rows.get(0);

        assertThat(r.tableCatalog()).isPresent().contains("TableCatalog");
        assertThat(r.tableSchema()).isPresent().contains("TableSchema");
        assertThat(r.tableName()).isPresent().contains("TableName");
        assertThat(r.tableType()).isPresent().contains("TableType");
        assertThat(r.tableGuid()).isPresent().contains("TableGuid");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.tablePropId()).isPresent().contains(10);
        assertThat(r.dateCreated()).isPresent().contains(LocalDateTime.of(2023, Month.JANUARY, 10, 10, 45));
        assertThat(r.dateModified()).isPresent().contains(LocalDateTime.of(2024, Month.JANUARY, 10, 10, 45));

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_TABLES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_CATALOG")
            .isEqualTo("TableCatalog");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_SCHEMA")
            .isEqualTo("TableSchema");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_NAME")
            .isEqualTo("TableName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_TYPE")
            .isEqualTo("TableType");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testTablesInfo() throws Exception {
        Provider<SOAPMessage> provider = registerService(TABLES_INFO);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        DbSchemaTablesInfoRestrictionsR restrictions = new DbSchemaTablesInfoRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("TableSchema"),
            "TableName",
            TableTypeEnum.TABLE
        );

        DbSchemaTablesInfoRequest dbSchemaTablesInfoRequest = new DbSchemaTablesInfoRequestR(properties, restrictions);

        List<DbSchemaTablesInfoResponseRow> rows = client.discover()
            .dbSchemaTablesInfo(dbSchemaTablesInfoRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        DbSchemaTablesInfoResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.tableName()).isEqualTo("TableName");
        assertThat(r.tableType()).isEqualTo("TableType");
        assertThat(r.tableGuid()).isPresent().contains(10);
        assertThat(r.bookmarks()).isPresent().contains(true);
        assertThat(r.bookmarkType()).isPresent().contains(10);
        assertThat(r.bookmarkDataType()).isPresent().contains(11);
        assertThat(r.bookmarkMaximumLength()).isPresent().contains(12);
        assertThat(r.bookmarkInformation()).isPresent().contains(14);
        assertThat(r.tableVersion()).isPresent().contains(15l);
        assertThat(r.cardinality()).isPresent().contains(16l);
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.tablePropId()).isPresent().contains(17);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("DBSCHEMA_TABLES_INFO");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_CATALOG")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_SCHEMA")
            .isEqualTo("TableSchema");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_NAME")
            .isEqualTo("TableName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TABLE_TYPE")
            .isEqualTo("TABLE");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testActions() throws Exception {
        Provider<SOAPMessage> provider = registerService(ACTIONS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaActionsRestrictionsR restrictions = new MdSchemaActionsRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            "CubeName",
            Optional.of("ActionName"),
            Optional.of(ActionTypeEnum.URL),
            Optional.of("Coordinate"),
            CoordinateTypeEnum.CUBE,
            InvocationEnum.NORMAL_OPERATION,
            Optional.of(CubeSourceEnum.CUBE)
        );

        MdSchemaActionsRequest mdSchemaActionsRequest = new MdSchemaActionsRequestR(properties, restrictions);

        List<MdSchemaActionsResponseRow> rows = client.discover()
            .mdSchemaActions(mdSchemaActionsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaActionsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isEqualTo("CubeName");
        assertThat(r.actionType()).isPresent().contains(ActionTypeEnum.URL);
        assertThat(r.coordinate()).isEqualTo("Coordinate");
        assertThat(r.coordinateType()).isEqualTo(CoordinateTypeEnum.CUBE);
        assertThat(r.actionCaption()).isPresent().contains("ActionCaption");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.content()).isPresent().contains("Content");
        assertThat(r.application()).isPresent().contains("Application");
        assertThat(r.invocation()).isPresent().contains(InvocationEnum.NORMAL_OPERATION);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_ACTIONS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:ACTION_NAME")
            .isEqualTo("ActionName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:ACTION_TYPE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:COORDINATE")
            .isEqualTo("Coordinate");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:COORDINATE_TYPE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:INVOCATION")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");


        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testCubes() throws Exception {
        Provider<SOAPMessage> provider = registerService(CUBES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaCubesRestrictionsR restrictions = new MdSchemaCubesRestrictionsR(
            "CatalogName",
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("BaseCubeName"),
            Optional.of(CubeSourceEnum.CUBE)
        );

        MdSchemaCubesRequest mdSchemaCubesRequest = new MdSchemaCubesRequestR(properties, restrictions);

        List<MdSchemaCubesResponseRow> rows = client.discover()
            .mdSchemaCubes(mdSchemaCubesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaCubesResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isEqualTo("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.cubeType()).isPresent().contains(CubeTypeEnum.CUBE);
        assertThat(r.cubeGuid()).isPresent().contains(10);
        assertThat(r.createdOn()).isPresent().contains(LocalDateTime.of(2023, Month.JANUARY, 10, 10, 45));
        assertThat(r.lastSchemaUpdate()).isPresent().contains(LocalDateTime.of(2024, Month.JANUARY, 10, 10, 45));
        assertThat(r.schemaUpdatedBy()).isPresent().contains("SchemaUpdatedBy");
        assertThat(r.lastDataUpdate()).isPresent().contains(LocalDateTime.of(2025, Month.JANUARY, 10, 10, 45));
        assertThat(r.dataUpdateDBy()).isPresent().contains("DataUpdatedBy");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.isDrillThroughEnabled()).isPresent().contains(true);
        assertThat(r.isLinkable()).isPresent().contains(true);
        assertThat(r.isWriteEnabled()).isPresent().contains(true);
        assertThat(r.isSqlEnabled()).isPresent().contains(true);
        assertThat(r.cubeCaption()).isPresent().contains("CubeCaption");
        assertThat(r.baseCubeName()).isPresent().contains("BaseCubeName");
        assertThat(r.cubeSource()).isPresent().contains(CubeSourceEnum.CUBE);
        assertThat(r.preferredQueryPatterns()).isPresent().contains(PreferredQueryPatternsEnum.CROSS_JOIN);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_CUBES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();

        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:BASE_CUBE_NAME")
            .isEqualTo("BaseCubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testDimensions() throws Exception {
        Provider<SOAPMessage> provider = registerService(DIMENSIONS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaDimensionsRestrictionsR restrictions = new MdSchemaDimensionsRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("DimensionName"),
            Optional.of("DimensionUniqueName"),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of(VisibilityEnum.VISIBLE)
        );

        MdSchemaDimensionsRequest mdSchemaDimensionsRequest = new MdSchemaDimensionsRequestR(properties, restrictions);

        List<MdSchemaDimensionsResponseRow> rows = client.discover()
            .mdSchemaDimensions(mdSchemaDimensionsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaDimensionsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.dimensionName()).isPresent().contains("DimensionName");
        assertThat(r.dimensionUniqueName()).isPresent().contains("DimensionUniqueName");
        assertThat(r.dimensionGuid()).isPresent().contains(10);
        assertThat(r.dimensionCaption()).isPresent().contains("DimensionCaption");
        assertThat(r.dimensionOptional()).isPresent().contains(11);
        assertThat(r.dimensionType()).isPresent().contains(DimensionTypeEnum.UNKNOWN);
        assertThat(r.dimensionCardinality()).isPresent().contains(12);
        assertThat(r.defaultHierarchy()).isPresent().contains("DefaultHierarchy");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.isVirtual()).isPresent().contains(true);
        assertThat(r.isReadWrite()).isPresent().contains(true);
        assertThat(r.dimensionUniqueSetting()).isPresent().contains(DimensionUniqueSettingEnum.MEMBER_KEY);
        assertThat(r.dimensionMasterName()).isPresent().contains("DimensionMasterName");
        assertThat(r.dimensionIsVisible()).isPresent().contains(true);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_DIMENSIONS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();

        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_NAME")
            .isEqualTo("DimensionName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_UNIQUE_NAME")
            .isEqualTo("DimensionUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_VISIBILITY")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testFunctions() throws Exception {
        Provider<SOAPMessage> provider = registerService(FUNCTIONS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaFunctionsRestrictionsR restrictions = new MdSchemaFunctionsRestrictionsR(
            Optional.of(OriginEnum.MSOLAP),
            Optional.of(InterfaceNameEnum.FILTER),
            Optional.of("LibraryName")
        );

        MdSchemaFunctionsRequest mdSchemaFunctionsRequest = new MdSchemaFunctionsRequestR(properties, restrictions);

        List<MdSchemaFunctionsResponseRow> rows = client.discover()
            .mdSchemaFunctions(mdSchemaFunctionsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaFunctionsResponseRow r = rows.get(0);

        assertThat(r.functionalName()).isPresent().contains("FunctionalName");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.parameterList()).isEqualTo("ParameterList");
        assertThat(r.returnType()).isPresent().contains(10);
        assertThat(r.origin()).isPresent().contains(OriginEnum.MSOLAP);
        assertThat(r.interfaceName()).isPresent().contains(InterfaceNameEnum.FILTER);
        assertThat(r.libraryName()).isPresent().contains("LibraryName");
        assertThat(r.dllName()).isPresent().contains("DllName");
        assertThat(r.helpFile()).isPresent().contains("HelpFile");
        assertThat(r.helpContext()).isPresent().contains("HelpContext");
        assertThat(r.object()).isPresent().contains("Object");
        assertThat(r.caption()).isPresent().contains("Caption");

        assertThat(r.parameterInfo()).isPresent();
        assertThat(r.parameterInfo().get()).hasSize(2);
        ParameterInfo pi = r.parameterInfo().get().get(0);
        assertThat(pi.name()).isEqualTo("name");
        assertThat(pi.description()).isEqualTo("description");
        assertThat(pi.optional()).isTrue();
        assertThat(pi.repeatable()).isTrue();
        assertThat(pi.repeatGroup()).isEqualTo(1);

        assertThat(r.directQueryPushable()).isPresent().contains(DirectQueryPushableEnum.MEASURE);
        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());
        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_FUNCTIONS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();

        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:ORIGIN")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:INTERFACE_NAME")
            .isEqualTo("FILTER");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LIBRARY_NAME")
            .isEqualTo("LibraryName");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testHierarchies() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.HIERARCHIES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaHierarchiesRestrictionsR restrictions = new MdSchemaHierarchiesRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("DimensionUniqueName"),
            Optional.of("HierarchyName"),
            Optional.of("HierarchyUniqueName"),
            Optional.of(10),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of(VisibilityEnum.VISIBLE)
        );

        MdSchemaHierarchiesRequest mdSchemaHierarchiesRequest = new MdSchemaHierarchiesRequestR(properties, restrictions);

        List<MdSchemaHierarchiesResponseRow> rows = client.discover()
            .mdSchemaHierarchies(mdSchemaHierarchiesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaHierarchiesResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.dimensionUniqueName()).isPresent().contains("DimensionUniqueName");
        assertThat(r.hierarchyName()).isPresent().contains("HierarchyName");
        assertThat(r.hierarchyUniqueName()).isPresent().contains("HierarchyUniqueName");
        assertThat(r.hierarchyGuid()).isPresent().contains(10);
        assertThat(r.hierarchyCaption()).isPresent().contains("HierarchyCaption");
        assertThat(r.dimensionType()).isPresent().contains(DimensionTypeEnum.UNKNOWN);
        assertThat(r.hierarchyCardinality()).isPresent().contains(11);
        assertThat(r.defaultMember()).isPresent().contains("DefaultMember");
        assertThat(r.allMember()).isPresent().contains("AllMember");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.structure()).isPresent().contains(StructureEnum.HIERARCHY_FULLY_BALANCED);
        assertThat(r.isVirtual()).isPresent().contains(true);
        assertThat(r.isReadWrite()).isPresent().contains(true);
        assertThat(r.dimensionUniqueSettings()).isPresent().contains(DimensionUniqueSettingEnum.MEMBER_KEY);
        assertThat(r.dimensionMasterUniqueName()).isPresent().contains("dimensionMasterUniqueName");
        assertThat(r.dimensionIsVisible()).isPresent().contains(true);
        assertThat(r.hierarchyOrdinal()).isPresent().contains(12);
        assertThat(r.dimensionIsShared()).isPresent().contains(true);
        assertThat(r.hierarchyIsVisible()).isPresent().contains(true);
        assertThat(r.hierarchyOrigin()).isPresent().contains(HierarchyOriginEnum.USER_DEFINED);
        assertThat(r.hierarchyDisplayFolder()).isPresent().contains("HierarchyDisplayFolder");
        assertThat(r.instanceSelection()).isPresent().contains(InstanceSelectionEnum.DROPDOWN);
        assertThat(r.groupingBehavior()).isPresent().contains(GroupingBehaviorEnum.ENCOURAGED);
        assertThat(r.structureType()).isPresent().contains(StructureTypeEnum.NATURAL);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_HIERARCHIES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_UNIQUE_NAME")
            .isEqualTo("DimensionUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_NAME")
            .isEqualTo("HierarchyName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_UNIQUE_NAME")
            .isEqualTo("HierarchyUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_ORIGIN")
            .isEqualTo(10);
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_VISIBILITY")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testKpis() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.KPIS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaKpisRestrictionsR restrictions = new MdSchemaKpisRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("KpiName"),
            Optional.of(CubeSourceEnum.CUBE)
        );

        MdSchemaKpisRequest mdSchemaKpisRequest = new MdSchemaKpisRequestR(properties, restrictions);

        List<MdSchemaKpisResponseRow> rows = client.discover()
            .mdSchemaKpis(mdSchemaKpisRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaKpisResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.measureGroupName()).isPresent().contains("MeasureGroupName");
        assertThat(r.kpiName()).isPresent().contains("KpiName");
        assertThat(r.kpiCaption()).isPresent().contains("KpiCaption");
        assertThat(r.kpiDescription()).isPresent().contains("KpiDescription");
        assertThat(r.kpiDisplayFolder()).isPresent().contains("KpiDisplayFolder");
        assertThat(r.kpiValue()).isPresent().contains("KpiValue");
        assertThat(r.kpiGoal()).isPresent().contains("KpiGoal");
        assertThat(r.kpiStatus()).isPresent().contains("KpiStatus");
        assertThat(r.kpiTrend()).isPresent().contains("KpiTrend");
        assertThat(r.kpiStatusGraphic()).isPresent().contains("KpiStatusGraphic");
        assertThat(r.kpiTrendGraphic()).isPresent().contains("KpiTrendGraphic");
        assertThat(r.kpiWight()).isPresent().contains("KpiWight");
        assertThat(r.kpiCurrentTimeMember()).isPresent().contains("KpiCurrentTimeMember");
        assertThat(r.kpiParentKpiName()).isPresent().contains("KpiParentKpiName");
        assertThat(r.annotation()).isPresent().contains("Annotation");
        assertThat(r.scope()).isPresent().contains(ScopeEnum.GLOBAL);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_KPIS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:KPI_NAME")
            .isEqualTo("KpiName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testLevels() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.LEVELS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaLevelsRestrictionsR restrictions = new MdSchemaLevelsRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("DimensionUniqueName"),
            Optional.of("HierarchyUniqueName"),
            Optional.of("LevelName"),
            Optional.of("LevelUniqueName"),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of(VisibilityEnum.VISIBLE)
        );

        MdSchemaLevelsRequest mdSchemaLevelsRequest = new MdSchemaLevelsRequestR(properties, restrictions);

        List<MdSchemaLevelsResponseRow> rows = client.discover()
            .mdSchemaLevels(mdSchemaLevelsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaLevelsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.dimensionUniqueName()).isPresent().contains("DimensionUniqueName");
        assertThat(r.hierarchyUniqueName()).isPresent().contains("HierarchyUniqueName");
        assertThat(r.levelName()).isPresent().contains("LevelName");
        assertThat(r.levelUniqueName()).isPresent().contains("LevelUniqueName");
        assertThat(r.levelGuid()).isPresent().contains(10);
        assertThat(r.levelCaption()).isPresent().contains("LevelCaption");
        assertThat(r.levelNumber()).isPresent().contains(11);
        assertThat(r.levelCardinality()).isPresent().contains(12);
        assertThat(r.levelType()).isPresent().contains(LevelTypeEnum.ALL);
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.customRollupSetting()).isPresent().contains(CustomRollupSettingEnum.CUSTOM_ROLLUP_EXPRESSION_EXIST);
        assertThat(r.levelUniqueSettings()).isPresent().contains(LevelUniqueSettingsEnum.KEY_COLUMNS);
        assertThat(r.levelIsVisible()).isPresent().contains(true);
        assertThat(r.levelOrderingProperty()).isPresent().contains("LevelOrderingProperty");
        assertThat(r.levelDbType()).isPresent().contains(LevelDbTypeEnum.DBTYPE_EMPTY);
        assertThat(r.levelMasterUniqueName()).isPresent().contains("LevelMasterUniqueName");
        assertThat(r.levelNameSqlColumnName()).isPresent().contains("LevelNameSqlColumnName");
        assertThat(r.levelKeySqlColumnName()).isPresent().contains("LevelKeySqlColumnName");
        assertThat(r.levelUniqueNameSqlColumnName()).isPresent().contains("LevelUniqueNameSqlColumnName");
        assertThat(r.levelAttributeHierarchyName()).isPresent().contains("LevelAttributeHierarchyName");
        assertThat(r.levelKeyCardinality()).isPresent().contains(14);
        assertThat(r.levelOrigin()).isPresent().contains(LevelOriginEnum.USER_DEFINED);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_LEVELS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_UNIQUE_NAME")
            .isEqualTo("DimensionUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_UNIQUE_NAME")
            .isEqualTo("HierarchyUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LEVEL_NAME")
            .isEqualTo("LevelName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LEVEL_UNIQUE_NAME")
            .isEqualTo("LevelUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LEVEL_VISIBILITY")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testMeasureGroupDimensions() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.MEASURE_GROUP_DIMENSIONS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaMeasureGroupDimensionsRestrictionsR restrictions = new MdSchemaMeasureGroupDimensionsRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("DimensionUniqueName"),
            Optional.of("MeasureGroupName"),
            Optional.of(VisibilityEnum.VISIBLE)
        );

        MdSchemaMeasureGroupDimensionsRequest mdSchemaMeasureGroupDimensionsRequest = new MdSchemaMeasureGroupDimensionsRequestR(properties, restrictions);

        List<MdSchemaMeasureGroupDimensionsResponseRow> rows = client.discover()
            .mdSchemaMeasureGroupDimensions(mdSchemaMeasureGroupDimensionsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaMeasureGroupDimensionsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.measureGroupName()).isPresent().contains("MeasureGroupName");
        assertThat(r.measureGroupCardinality()).isPresent().contains("MeasureGroupCardinality");
        assertThat(r.dimensionUniqueName()).isPresent().contains("DimensionUniqueName");
        assertThat(r.dimensionCardinality()).isPresent().contains(DimensionCardinalityEnum.ONE);
        assertThat(r.dimensionIsVisible()).isPresent().contains(true);
        assertThat(r.dimensionIsFactDimension()).isPresent().contains(true);
        assertThat(r.dimensionPath()).isPresent();
        assertThat(r.dimensionPath().get()).hasSize(2);
        MeasureGroupDimension mgd = r.dimensionPath().get().get(0);
        assertThat(mgd.measureGroupDimension()).isEqualTo("MeasureGroupDimension");
        assertThat(r.dimensionGranularity()).isPresent().contains("DimensionGranularity");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_MEASUREGROUP_DIMENSIONS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEASUREGROUP_NAME")
            .isEqualTo("MeasureGroupName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_UNIQUE_NAME")
            .isEqualTo("DimensionUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_VISIBILITY")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testMeasureGroups() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.MEASURE_GROUPS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaMeasureGroupsRestrictionsR restrictions = new MdSchemaMeasureGroupsRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("MeasureGroupName")
        );

        MdSchemaMeasureGroupsRequest mdSchemaMeasureGroupsRequest = new MdSchemaMeasureGroupsRequestR(properties, restrictions);

        List<MdSchemaMeasureGroupsResponseRow> rows = client.discover()
            .mdSchemaMeasureGroups(mdSchemaMeasureGroupsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaMeasureGroupsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.measureGroupName()).isPresent().contains("MeasureGroupName");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.isWriteEnabled()).isPresent().contains(true);
        assertThat(r.measureGroupCaption()).isPresent().contains("MeasureGroupCaption");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_MEASUREGROUPS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEASUREGROUP_NAME")
            .isEqualTo("MeasureGroupName");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testMeasures() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.MEASURES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaMeasuresRestrictionsR restrictions = new MdSchemaMeasuresRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("MeasureName"),
            Optional.of("MeasureUniqueName"),
            Optional.of("MeasureGroupName"),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of(VisibilityEnum.VISIBLE)
        );

        MdSchemaMeasuresRequest mdSchemaMeasuresRequest = new MdSchemaMeasuresRequestR(properties, restrictions);

        List<MdSchemaMeasuresResponseRow> rows = client.discover()
            .mdSchemaMeasures(mdSchemaMeasuresRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaMeasuresResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.measureName()).isPresent().contains("MeasureName");
        assertThat(r.measureUniqueName()).isPresent().contains("MeasureUniqueName");
        assertThat(r.measureCaption()).isPresent().contains("MeasureCaption");
        assertThat(r.measureGuid()).isPresent().contains(10);
        assertThat(r.measureAggregator()).isPresent().contains(MeasureAggregatorEnum.MDMEASURE_AGGR_SUM);
        assertThat(r.dataType()).isPresent().contains(LevelDbTypeEnum.DBTYPE_EMPTY);
        assertThat(r.numericPrecision()).isPresent().contains(11);
        assertThat(r.numericScale()).isPresent().contains(12);
        assertThat(r.measureUnits()).isPresent().contains("MeasureUnits");
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.expression()).isPresent().contains("Expression");
        assertThat(r.measureIsVisible()).isPresent().contains(true);
        assertThat(r.levelsList()).isPresent().contains("LevelsList");
        assertThat(r.measureNameSqlColumnName()).isPresent().contains("MeasureNameSqlColumnName");
        assertThat(r.measureUnqualifiedCaption()).isPresent().contains("MeasureUnqualifiedCaption");
        assertThat(r.measureGroupName()).isPresent().contains("MeasureGroupName");
        assertThat(r.defaultFormatString()).isPresent().contains("DefaultFormatString");

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_MEASURES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEASURE_NAME")
            .isEqualTo("MeasureName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEASURE_UNIQUE_NAME")
            .isEqualTo("MeasureUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEASUREGROUP_NAME")
            .isEqualTo("MeasureGroupName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEASURE_VISIBILITY")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testMembers() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.MEMBERS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaMembersRestrictionsR restrictions = new MdSchemaMembersRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("DimensionUniqueName"),
            Optional.of("HierarchyUniqueName"),
            Optional.of("LevelUniqueName"),
            Optional.of(10),
            Optional.of("MemberName"),
            Optional.of("MemberUniqueName"),
            Optional.of(MemberTypeEnum.REGULAR_MEMBER),
            Optional.of("MemberCaption"),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of(TreeOpEnum.CHILDREN)
        );

        MdSchemaMembersRequest mdSchemaMembersRequest = new MdSchemaMembersRequestR(properties, restrictions);

        List<MdSchemaMembersResponseRow> rows = client.discover()
            .mdSchemaMembers(mdSchemaMembersRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaMembersResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.dimensionUniqueName()).isPresent().contains("DimensionUniqueName");
        assertThat(r.hierarchyUniqueName()).isPresent().contains("HierarchyUniqueName");
        assertThat(r.levelUniqueName()).isPresent().contains("LevelUniqueName");
        assertThat(r.levelNumber()).isPresent().contains(10);
        assertThat(r.memberOrdinal()).isPresent().contains(11);
        assertThat(r.memberName()).isPresent().contains("MemberName");
        assertThat(r.memberUniqueName()).isPresent().contains("MemberUniqueName");
        assertThat(r.memberType()).isPresent().contains(MemberTypeEnum.REGULAR_MEMBER);
        assertThat(r.memberGuid()).isPresent().contains(12);
        assertThat(r.memberCaption()).isPresent().contains("MemberCaption");
        assertThat(r.childrenCardinality()).isPresent().contains(14);
        assertThat(r.parentLevel()).isPresent().contains(15);
        assertThat(r.parentUniqueName()).isPresent().contains("ParentUniqueName");
        assertThat(r.parentCount()).isPresent().contains(16);
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.expression()).isPresent().contains("Expression");
        assertThat(r.memberKey()).isPresent().contains("MemberKey");
        assertThat(r.isPlaceHolderMember()).isPresent().contains(true);
        assertThat(r.isDataMember()).isPresent().contains(true);
        assertThat(r.scope()).isPresent().contains(ScopeEnum.GLOBAL);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_MEMBERS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_UNIQUE_NAME")
            .isEqualTo("DimensionUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_UNIQUE_NAME")
            .isEqualTo("HierarchyUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LEVEL_UNIQUE_NAME")
            .isEqualTo("LevelUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LEVEL_NUMBER")
            .isEqualTo("10");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEMBER_NAME")
            .isEqualTo("MemberName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEMBER_UNIQUE_NAME")
            .isEqualTo("MemberUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEMBER_TYPE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEMBER_CAPTION")
            .isEqualTo("MemberCaption");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:TREE_OP")
            .isEqualTo("1");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testMdProperties() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.MD_PROPERTIES);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaPropertiesRestrictionsR restrictions = new MdSchemaPropertiesRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("DimensionUniqueName"),
            Optional.of("HierarchyUniqueName"),
            Optional.of("LevelUniqueName"),
            Optional.of("MemberUniqueName"),
            Optional.of(PropertyTypeEnum.PROPERTY_MEMBER),
            Optional.of("PropertyName"),
            Optional.of(PropertyOriginEnum.USER_DEFINED),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of(VisibilityEnum.VISIBLE)
        );

        MdSchemaPropertiesRequest mdSchemaPropertiesRequest = new MdSchemaPropertiesRequestR(properties, restrictions);

        List<MdSchemaPropertiesResponseRow> rows = client.discover()
            .mdSchemaProperties(mdSchemaPropertiesRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaPropertiesResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.dimensionUniqueName()).isPresent().contains("DimensionUniqueName");
        assertThat(r.hierarchyUniqueName()).isPresent().contains("HierarchyUniqueName");
        assertThat(r.levelUniqueName()).isPresent().contains("LevelUniqueName");
        assertThat(r.memberUniqueName()).isPresent().contains("MemberUniqueName");
        assertThat(r.propertyType()).isPresent().contains(PropertyTypeEnum.PROPERTY_MEMBER);
        assertThat(r.propertyName()).isPresent().contains("PropertyName");
        assertThat(r.propertyCaption()).isPresent().contains("PropertyCaption");
        assertThat(r.dataType()).isPresent().contains(LevelDbTypeEnum.DBTYPE_EMPTY);
        assertThat(r.characterMaximumLength()).isPresent().contains(10);
        assertThat(r.characterOctetLength()).isPresent().contains(11);
        assertThat(r.numericPrecision()).isPresent().contains(12);
        assertThat(r.numericScale()).isPresent().contains(14);
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.propertyContentType()).isPresent().contains(PropertyContentTypeEnum.DATE);
        assertThat(r.sqlColumnName()).isPresent().contains("SqlColumnName");
        assertThat(r.language()).isPresent().contains(15);
        assertThat(r.propertyOrigin()).isPresent().contains(PropertyOriginEnum.USER_DEFINED);
        assertThat(r.propertyAttributeHierarchyName()).isPresent().contains("PropertyAttributeHierarchyName");
        assertThat(r.propertyCardinality()).isPresent().contains(PropertyCardinalityEnum.ONE);
        assertThat(r.mimeType()).isPresent().contains("MimeType");
        assertThat(r.propertyIsVisible()).isPresent().contains(VisibilityEnum.VISIBLE);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_PROPERTIES");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:DIMENSION_UNIQUE_NAME")
            .isEqualTo("DimensionUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_UNIQUE_NAME")
            .isEqualTo("HierarchyUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:LEVEL_UNIQUE_NAME")
            .isEqualTo("LevelUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:MEMBER_UNIQUE_NAME")
            .isEqualTo("MemberUniqueName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PROPERTY_TYPE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PROPERTY_NAME")
            .isEqualTo("PropertyName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PROPERTY_ORIGIN")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:PROPERTY_VISIBILITY")
            .isEqualTo("1");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testSets() throws Exception {
        Provider<SOAPMessage> provider = registerService(Responses.SETS);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.CONTENT, "SchemaData");
        MdSchemaSetsRestrictionsR restrictions = new MdSchemaSetsRestrictionsR(
            Optional.of("CatalogName"),
            Optional.of("SchemaName"),
            Optional.of("CubeName"),
            Optional.of("SetName"),
            Optional.of(ScopeEnum.GLOBAL),
            Optional.of(CubeSourceEnum.CUBE),
            Optional.of("HierarchyUniqueName")
        );

        MdSchemaSetsRequest mdSchemaSetsRequest = new MdSchemaSetsRequestR(properties, restrictions);

        List<MdSchemaSetsResponseRow> rows = client.discover()
            .mdSchemaSets(mdSchemaSetsRequest);

        assertThat(rows).isNotNull().hasSize(1);
        assertThat(rows.get(0)).isNotNull();
        MdSchemaSetsResponseRow r = rows.get(0);

        assertThat(r.catalogName()).isPresent().contains("CatalogName");
        assertThat(r.schemaName()).isPresent().contains("SchemaName");
        assertThat(r.cubeName()).isPresent().contains("CubeName");
        assertThat(r.setName()).isPresent().contains("SetName");
        assertThat(r.scope()).isPresent().contains(ScopeEnum.GLOBAL);
        assertThat(r.description()).isPresent().contains("Description");
        assertThat(r.expression()).isPresent().contains("Expression");
        assertThat(r.dimension()).isPresent().contains("Dimension");
        assertThat(r.setCaption()).isPresent().contains("SetCaption");
        assertThat(r.setDisplayFolder()).isPresent().contains("SetDisplayFolder");
        assertThat(r.setEvaluationContext()).isPresent().contains(SetEvaluationContextEnum.STATIC);

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:RequestType").isEqualTo("MDSCHEMA_SETS");
        // Restrictions
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CATALOG_NAME")
            .isEqualTo("CatalogName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCHEMA_NAME")
            .isEqualTo("SchemaName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_NAME")
            .isEqualTo("CubeName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SET_NAME")
            .isEqualTo("SetName");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:SCOPE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:CUBE_SOURCE")
            .isEqualTo("1");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Restrictions/msxmla:RestrictionList/msxmla:HIERARCHY_UNIQUE_NAME")
            .isEqualTo("HierarchyUniqueName");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/msxmla:Discover/msxmla:Properties/msxmla:PropertyList/msxmla:Content")
            .isEqualTo("SchemaData");
    }

    private Provider<SOAPMessage> registerService(String xml) throws InterruptedException {
        Provider<SOAPMessage> provider = spy(new MockProvider(xml));
        bc.registerService(Provider.class, provider, Dictionaries.dictionaryOf("osgi.soap.endpoint.contextpath",
            "/xmla", "osgi.soap.endpoint.implementor", "true"));
        TimeUnit.SECONDS.sleep(2);
        return provider;
    }

}
