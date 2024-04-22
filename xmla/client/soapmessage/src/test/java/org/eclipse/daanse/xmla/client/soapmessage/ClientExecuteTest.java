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
import org.eclipse.daanse.xmla.api.UserPrincipal;
import org.eclipse.daanse.xmla.api.common.properties.AxisFormat;
import org.eclipse.daanse.xmla.api.common.properties.PropertyListElementDefinition;
import org.eclipse.daanse.xmla.api.engine200.WarningColumn;
import org.eclipse.daanse.xmla.api.engine200.WarningLocationObject;
import org.eclipse.daanse.xmla.api.engine200.WarningMeasure;
import org.eclipse.daanse.xmla.api.exception.ErrorType;
import org.eclipse.daanse.xmla.api.exception.MessageLocation;
import org.eclipse.daanse.xmla.api.exception.Messages;
import org.eclipse.daanse.xmla.api.exception.StartEnd;
import org.eclipse.daanse.xmla.api.exception.Type;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.alter.AlterRequest;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.mddataset.AxesInfo;
import org.eclipse.daanse.xmla.api.mddataset.AxisInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellInfoItem;
import org.eclipse.daanse.xmla.api.mddataset.CubeInfo;
import org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo;
import org.eclipse.daanse.xmla.api.mddataset.Mddataset;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfo;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube;
import org.eclipse.daanse.xmla.api.xmla.Cancel;
import org.eclipse.daanse.xmla.api.xmla.ClearCache;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttribute;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.MajorObject;
import org.eclipse.daanse.xmla.api.xmla.ObjectExpansion;
import org.eclipse.daanse.xmla.api.xmla.ObjectReference;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.Scope;
import org.eclipse.daanse.xmla.api.xmla_empty.Emptyresult;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.execute.alter.AlterRequestR;
import org.eclipse.daanse.xmla.model.record.execute.cancel.CancelRequestR;
import org.eclipse.daanse.xmla.model.record.execute.clearcache.ClearCacheRequestR;
import org.eclipse.daanse.xmla.model.record.execute.statement.StatementRequestR;
import org.eclipse.daanse.xmla.model.record.xmla.AlterR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.ColumnBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DataItemR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceViewBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.ErrorConfigurationR;
import org.eclipse.daanse.xmla.model.record.xmla.MajorObjectR;
import org.eclipse.daanse.xmla.model.record.xmla.ObjectReferenceR;
import org.eclipse.daanse.xmla.model.record.xmla.ProactiveCachingR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
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

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.ALTER;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.CANCEL;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.CLEAR_CACHE;
import static org.eclipse.daanse.xmla.client.soapmessage.Responses.STATEMENT;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RequireSoapWhiteboard
@ExtendWith(ConfigurationExtension.class)
@WithFactoryConfiguration(factoryPid = "org.eclipse.daanse.ws.handler.SOAPLoggingHandler", name = "test-ms-config",
    location = "?", properties = {
    @Property(key = "osgi.soap.endpoint.selector", value = "(service.pid=*)")})
@RequireServiceComponentRuntime
class ClientExecuteTest {

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
    @SuppressWarnings("java:S5961")
    void testCancel() throws Exception {
        Provider<SOAPMessage> provider = registerService(CANCEL);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.FORMAT, "Tabular");
        properties.setAxisFormat(Optional.of(AxisFormat.TUPLE_FORMAT));
        properties.setCatalog(Optional.of("FoodMart"));
        List<ExecuteParameter> parameters = List.of();
        Cancel command = new CancelR(
            BigInteger.valueOf(100),
            "SessionID",
            BigInteger.valueOf(101),
            true
        );

        RequestMetaData requestMetaData = null;
        UserPrincipal userPrincipal = null;
        CancelRequest cancelRequest = new CancelRequestR(properties, parameters, command);

        CancelResponse response = client.execute()
            .cancel(cancelRequest, requestMetaData, userPrincipal);
        assertThat(response).isNotNull();
        assertThat(response.emptyresult()).isNotNull();
        Emptyresult emptyresult = response.emptyresult();
        assertThat(emptyresult.messages()).isNotNull();
        assertThat(emptyresult.exception()).isNotNull();
        checkMessages(emptyresult.messages());

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Cancel")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Cancel/ConnectionID")
            .isEqualTo("100");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Cancel/SessionID")
            .isEqualTo("SessionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Cancel/SPID")
            .isEqualTo("101");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Cancel/CancelAssociated")
            .isEqualTo("true");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Catalog")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Format")
            .isEqualTo("Tabular");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/AxisFormat")
            .isEqualTo("TupleFormat");

    }

    @Test
    @SuppressWarnings("java:S5961")
    void testClearCache() throws Exception {
        Provider<SOAPMessage> provider = registerService(CLEAR_CACHE);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.FORMAT, "Tabular");
        properties.setAxisFormat(Optional.of(AxisFormat.TUPLE_FORMAT));
        properties.setCatalog(Optional.of("FoodMart"));
        List<ExecuteParameter> parameters = List.of();
        ObjectReference objectReference = new ObjectReferenceR(
            "serverID",
            "databaseID",
            "roleID",
            "traceID",
            "assemblyID",
            "dimensionID",
            "dimensionPermissionID",
            "dataSourceID",
            "dataSourcePermissionID",
            "databasePermissionID",
            "dataSourceViewID",
            "cubeID",
            "miningStructureID",
            "measureGroupID",
            "perspectiveID",
            "cubePermissionID",
            "mdxScriptID",
            "partitionID",
            "aggregationDesignID",
            "miningModelID",
            "miningModelPermissionID",
            "miningStructurePermissionID");
        ClearCache command = new ClearCacheR(objectReference);

        RequestMetaData requestMetaData = null;
        UserPrincipal userPrincipal = null;
        ClearCacheRequest clearCacheRequest = new ClearCacheRequestR(properties, parameters, command);

        ClearCacheResponse response = client.execute()
            .clearCache(clearCacheRequest, requestMetaData, userPrincipal);
        assertThat(response).isNotNull();
        assertThat(response.emptyresult()).isNotNull();
        Emptyresult emptyresult = response.emptyresult();
        assertThat(emptyresult.messages()).isNotNull();
        assertThat(emptyresult.exception()).isNotNull();
        checkMessages(emptyresult.messages());

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/ServerID")
            .isEqualTo("serverID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DatabaseID")
            .isEqualTo("databaseID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/RoleID")
            .isEqualTo("roleID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/TraceID")
            .isEqualTo("traceID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/AssemblyID")
            .isEqualTo("assemblyID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DimensionID")
            .isEqualTo("dimensionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DimensionPermissionID")
            .isEqualTo("dimensionPermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DataSourceID")
            .isEqualTo("dataSourceID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DataSourcePermissionID")
            .isEqualTo("dataSourcePermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DatabasePermissionID")
            .isEqualTo("databasePermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/DataSourceViewID")
            .isEqualTo("dataSourceViewID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/CubeID")
            .isEqualTo("cubeID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/MiningStructureID")
            .isEqualTo("miningStructureID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/MeasureGroupID")
            .isEqualTo("measureGroupID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/PerspectiveID")
            .isEqualTo("perspectiveID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/CubePermissionID")
            .isEqualTo("cubePermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/MdxScriptID")
            .isEqualTo("mdxScriptID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/PartitionID")
            .isEqualTo("partitionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/AggregationDesignID")
            .isEqualTo("aggregationDesignID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/MiningModelID")
            .isEqualTo("miningModelID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/MiningModelPermissionID")
            .isEqualTo("miningModelPermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/ClearCache/Object/MiningStructurePermissionID")
            .isEqualTo("miningStructurePermissionID");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Catalog")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Format")
            .isEqualTo("Tabular");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/AxisFormat")
            .isEqualTo("TupleFormat");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testStatement() throws Exception {
        Provider<SOAPMessage> provider = registerService(STATEMENT);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.FORMAT, "Tabular");
        properties.setAxisFormat(Optional.of(AxisFormat.TUPLE_FORMAT));
        properties.setCatalog(Optional.of("FoodMart"));
        List<ExecuteParameter> parameters = List.of();
        StatementR command = new StatementR("select [Measures].[Sales Count] on 0, non empty [Store].[Store State]" +
            ".members on 1 from [Sales]");
        RequestMetaData requestMetaData = null;
        UserPrincipal userPrincipal = null;

        StatementRequest statementRequest = new StatementRequestR(properties, parameters, command, null);

        StatementResponse response = client.execute()
            .statement(statementRequest, requestMetaData, userPrincipal);
        assertThat(response).isNotNull();
        assertThat(response.mdDataSet()).isNotNull();
        Mddataset mddataset = response.mdDataSet();
        assertThat(mddataset.olapInfo()).isNotNull();
        assertThat(mddataset.messages()).isNotNull();
        checkOlapInfo(mddataset.olapInfo());
        Messages messages = mddataset.messages();

        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Statement")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Statement")
            .isEqualTo("select [Measures].[Sales Count] on 0, non empty [Store].[Store State].members on 1 from " +
                "[Sales]");
        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Catalog")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Format")
            .isEqualTo("Tabular");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/AxisFormat")
            .isEqualTo("TupleFormat");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void testAlter() throws Exception {
        Provider<SOAPMessage> provider = registerService(ALTER);
        PropertiesR properties = new PropertiesR();
        properties.addProperty(PropertyListElementDefinition.DATA_SOURCE_INFO, "FoodMart");
        properties.addProperty(PropertyListElementDefinition.FORMAT, "Tabular");
        properties.setAxisFormat(Optional.of(AxisFormat.TUPLE_FORMAT));
        properties.setCatalog(Optional.of("FoodMart"));
        List<ExecuteParameter> parameters = List.of();
        ObjectReference object = new ObjectReferenceR(
            "serverID",
            "databaseID",
            "roleID",
            "traceID",
            "assemblyID",
            "dimensionID",
            "dimensionPermissionID",
            "dataSourceID",
            "dataSourcePermissionID",
            "databasePermissionID",
            "dataSourceViewID",
            "cubeID",
            "miningStructureID",
            "measureGroupID",
            "perspectiveID",
            "cubePermissionID",
            "mdxScriptID",
            "partitionID",
            "aggregationDesignID",
            "miningModelID",
            "miningModelPermissionID",
            "miningStructurePermissionID");

        ErrorConfiguration errorConfiguration = new ErrorConfigurationR(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of("ReportAndStop"),
            Optional.of("ReportAndStop"),
            Optional.empty(),
            Optional.of("ReportAndStop"),
            Optional.empty()
        );
        List<DataItem> keyColumns = List.of(new DataItemR("Integer",
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(new ColumnBindingR("dbo_DimCustomer", "CustomerKey")),
            Optional.empty()));
        List<DimensionAttribute> attributes = List.of(new DimensionAttributeR(
            "Customer Key",
            "Customer Key",
            null,
            null,
            "Key",
            null,
            18484L,
            keyColumns,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ));

        ProactiveCaching proactiveCaching = new ProactiveCachingR(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(Duration.parse("-PT1S")),
            Optional.of(Duration.parse("-PT1S")),
            Optional.of(Duration.parse("-PT1S")),
            Optional.of(Duration.parse("-PT1S")),
            Optional.empty()
        );

        Dimension dimension = new DimensionR(
            "Customer",
            "Dim Customer",
            null,
            null,
            null,
            null,
            new DataSourceViewBindingR("dsvAdventureWorksDW2008"),
            null,
            null,
            null,
            null,
            errorConfiguration,
            null,
            null,
            null,
            null,
            null,
            null,
            BigInteger.valueOf(1033l),
            "Latin1_General_CI_AS",
            "Unknown",
            null,
            null,
            proactiveCaching,
            null,
            null,
            null,
            null,
            attributes,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );

        MajorObject objectDefinition = new MajorObjectR(
            null,
            null,
            null,
            null,
            null,
            null,
            dimension,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
        AlterR command = new AlterR(
            object,
            objectDefinition,
            Scope.SESSION,
            true,
            ObjectExpansion.EXPAND_FULL);

        RequestMetaData requestMetaData = null;
        UserPrincipal userPrincipal = null;
        AlterRequest alterRequest = new AlterRequestR(properties, parameters, command);

        AlterResponse response = client.execute()
            .alter(alterRequest, requestMetaData, userPrincipal);
        assertThat(response).isNotNull();
        assertThat(response.emptyresult()).isNotNull();
        assertThat(response.emptyresult().exception()).isNotNull();
        assertThat(response.emptyresult().messages()).isNotNull();


        verify(provider, (times(1))).invoke(requestMessageCaptor.capture());

        SOAPMessage request = requestMessageCaptor.getValue();

        request.writeTo(System.out);
        XmlAssert xmlAssert = XMLUtil.createAssert(request);
        xmlAssert.hasXPath("/SOAP:Envelope");
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object")
            .exist();
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition")
            .exist();

        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/ServerID")
            .isEqualTo("serverID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DatabaseID")
            .isEqualTo("databaseID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/RoleID")
            .isEqualTo("roleID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/TraceID")
            .isEqualTo("traceID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/AssemblyID")
            .isEqualTo("assemblyID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DimensionID")
            .isEqualTo("dimensionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DimensionPermissionID")
            .isEqualTo("dimensionPermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DataSourceID")
            .isEqualTo("dataSourceID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DataSourcePermissionID")
            .isEqualTo("dataSourcePermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DatabasePermissionID")
            .isEqualTo("databasePermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/DataSourceViewID")
            .isEqualTo("dataSourceViewID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/CubeID")
            .isEqualTo("cubeID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/MiningStructureID")
            .isEqualTo("miningStructureID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/MeasureGroupID")
            .isEqualTo("measureGroupID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/PerspectiveID")
            .isEqualTo("perspectiveID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/CubePermissionID")
            .isEqualTo("cubePermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/MdxScriptID")
            .isEqualTo("mdxScriptID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/PartitionID")
            .isEqualTo("partitionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/AggregationDesignID")
            .isEqualTo("aggregationDesignID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/MiningModelID")
            .isEqualTo("miningModelID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/MiningModelPermissionID")
            .isEqualTo("miningModelPermissionID");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/Object/MiningStructurePermissionID")
            .isEqualTo("miningStructurePermissionID");

        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ID")
            .isEqualTo("Dim Customer");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/Name")
            .isEqualTo("Customer");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/Language")
            .isEqualTo("1033");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/Collation")
            .isEqualTo("Latin1_General_CI_AS");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/UnknownMemberName")
            .isEqualTo("Unknown");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/Source/DataSourceViewID")
            .isEqualTo("dsvAdventureWorksDW2008");

        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ErrorConfiguration/NullKeyNotAllowed")
            .isEqualTo("ReportAndStop");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ErrorConfiguration/KeyNotFound")
            .isEqualTo("ReportAndStop");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ErrorConfiguration/KeyDuplicate")
            .isEqualTo("ReportAndStop");

        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ProactiveCaching/SilenceInterval")
            .isEqualTo("PT-1S");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ProactiveCaching/Latency")
            .isEqualTo("PT-1S");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ProactiveCaching/SilenceOverrideInterval")
            .isEqualTo("PT-1S");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Command/Alter/ObjectDefinition/Dimension/ProactiveCaching/ForceRebuildInterval")
            .isEqualTo("PT-1S");

        // Properties
        xmlAssert.nodesByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList")
            .exist();
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/DataSourceInfo")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Catalog")
            .isEqualTo("FoodMart");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/Format")
            .isEqualTo("Tabular");
        xmlAssert.valueByXPath("/SOAP:Envelope/SOAP:Body/Execute/Properties/PropertyList/AxisFormat")
            .isEqualTo("TupleFormat");
    }

    private void checkOlapInfo(OlapInfo olapInfo) {
        assertThat(olapInfo.cubeInfo()).isNotNull();
        assertThat(olapInfo.axesInfo()).isNotNull();
        assertThat(olapInfo.cellInfo()).isNotNull();
        ;
        checkCubeInfo(olapInfo.cubeInfo());
        checkAxesInfo(olapInfo.axesInfo());
        checkCellInfo(olapInfo.cellInfo());
    }

    private void checkCellInfo(CellInfo cellInfo) {
        assertThat(cellInfo.any()).isNotNull().hasSize(1);
        assertThat(cellInfo.any().get(0)).isNotNull();
        checkCellInfoItem(cellInfo.any().get(0));
    }

    private void checkAxesInfo(AxesInfo axesInfo) {
        assertThat(axesInfo.axisInfo()).isNotNull().hasSize(1);
        assertThat(axesInfo.axisInfo().get(0)).isNotNull();
        AxisInfo axisInfo = axesInfo.axisInfo().get(0);
        assertThat(axisInfo.name()).isEqualTo("name");
        assertThat(axisInfo.hierarchyInfo()).hasSize(1);
        HierarchyInfo hierarchyInfo = axisInfo.hierarchyInfo().get(0);
        assertThat(hierarchyInfo.name()).isEqualTo("name");
        assertThat(hierarchyInfo.any()).hasSize(1);
        assertThat(hierarchyInfo.any().get(0)).isNotNull();
        checkCellInfoItem(hierarchyInfo.any().get(0));
    }

    private void checkCellInfoItem(CellInfoItem cellInfoItem) {
        assertThat(cellInfoItem.name()).isEqualTo("name");
        assertThat(cellInfoItem.type()).isPresent().contains("value");
    }

    private void checkCubeInfo(CubeInfo cubeInfo) {
        assertThat(cubeInfo.cube()).hasSize(1);
        checkOlapInfoCube(cubeInfo.cube().get(0));
    }

    private void checkOlapInfoCube(OlapInfoCube olapInfoCube) {
        assertThat(olapInfoCube.lastSchemaUpdate())
            .isEqualTo(Instant.parse("2024-01-10T10:45:00.00Z"));
        assertThat(olapInfoCube.lastDataUpdate())
            .isEqualTo(Instant.parse("2024-01-10T10:45:00.00Z"));
        assertThat(olapInfoCube.cubeName()).isEqualTo("cubeName");
    }

    private void checkMessages(Messages messages) {
        assertThat(messages.warningOrError()).hasSize(1);
        Type t = messages.warningOrError().get(0);
        assertThat(t).isInstanceOf(ErrorType.class);
        ErrorType errorType = (ErrorType) t;
        assertThat(errorType.location()).isNotNull();
        MessageLocation messageLocation = errorType.location();
        assertThat(messageLocation.start()).isNotNull();
        assertThat(messageLocation.end()).isNotNull();
        StartEnd start = messageLocation.start();
        StartEnd end = messageLocation.end();
        assertThat(start.line()).isEqualTo(1);
        assertThat(start.column()).isEqualTo(2);
        assertThat(end.line()).isEqualTo(3);
        assertThat(end.column()).isEqualTo(4);
        assertThat(messageLocation.lineOffset()).isEqualTo(1);
        assertThat(messageLocation.textLength()).isEqualTo(2);
        assertThat(messageLocation.rowNumber()).isEqualTo(3);
        assertThat(messageLocation.dependsOnObject()).isNotNull();
        assertThat(messageLocation.sourceObject()).isNotNull();
        WarningLocationObject dependsOnObject = messageLocation.dependsOnObject();
        WarningLocationObject sourceObject = messageLocation.sourceObject();
        assertThat(dependsOnObject.warningMeasure()).isNotNull();
        assertThat(dependsOnObject.warningColumn()).isNotNull();
        assertThat(sourceObject.warningMeasure()).isNotNull();
        assertThat(sourceObject.warningColumn()).isNotNull();
        WarningMeasure dependsWarningMeasure = dependsOnObject.warningMeasure();
        WarningColumn dependsWarningColumn = dependsOnObject.warningColumn();
        WarningMeasure sourceWarningMeasure = sourceObject.warningMeasure();
        WarningColumn sourceWarningColumn = sourceObject.warningColumn();
        assertThat(dependsWarningMeasure.measureGroup()).isEqualTo("MeasureGroup");
        assertThat(dependsWarningMeasure.cube()).isEqualTo("Cube");
        assertThat(dependsWarningMeasure.measureName()).isEqualTo("MeasureName");
        assertThat(dependsWarningColumn.attribute()).isEqualTo("Attribute");
        assertThat(dependsWarningColumn.dimension()).isEqualTo("Dimension");
        assertThat(sourceWarningMeasure.measureGroup()).isEqualTo("MeasureGroup");
        assertThat(sourceWarningMeasure.cube()).isEqualTo("Cube");
        assertThat(sourceWarningMeasure.measureName()).isEqualTo("MeasureName");
        assertThat(sourceWarningColumn.attribute()).isEqualTo("Attribute");
        assertThat(sourceWarningColumn.dimension()).isEqualTo("Dimension");
    }

    private Provider<SOAPMessage> registerService(String xml) throws InterruptedException {
        Provider<SOAPMessage> provider = spy(new MockProvider(xml));
        bc.registerService(Provider.class, provider, Dictionaries.dictionaryOf("osgi.soap.endpoint.contextpath",
            "/xmla", "osgi.soap.endpoint.implementor", "true"));
        TimeUnit.SECONDS.sleep(2);
        return provider;
    }

}
