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
package org.eclipse.daanse.xmla.ws.jakarta.basic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.model.jaxb.ext.AuthenticateResponse;
import org.eclipse.daanse.xmla.model.jaxb.ext.ReturnValue;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla_rowset.Row;
import org.eclipse.daanse.xmla.model.jaxb.xmla_rowset.Rowset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentConfigurationDTO;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;
import org.osgi.service.component.runtime.dto.SatisfiedReferenceDTO;
import org.osgi.service.component.runtime.dto.UnsatisfiedReferenceDTO;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.Property.Scalar;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aQute.bnd.annotation.service.ServiceCapability;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

@ServiceCapability(XmlaService.class)
@ExtendWith(ConfigurationExtension.class)
public class MinTest {
    private Logger logger = LoggerFactory.getLogger(MinTest.class);

	private static final String SERVER_PORT_WHITEBOARD = "8090";
    private static final String URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS = "urn:schemas-microsoft-com:xml-analysis";

    public static final QName QNAME_Discover = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "Discover");
    public static final QName QNAME_RequestType = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "RequestType");
    public static final QName QNAME_Restrictions = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "Restrictions");
    public static final QName QNAME_RestrictionList = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS,
            "RestrictionList");
    public static final QName QNAME_Properties = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "Properties");
    public static final QName QNAME_PropertyList = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "PropertyList");
    public static final QName QNAME_DataSourceInfo = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS,
            "DataSourceInfo");
    public static final QName QNAME_Catalog = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "Catalog");
    public static final QName QNAME_Format = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "Format");
    public static final QName QNAME_Content = new QName(URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS, "Content");

    public static final String soapEndpointUrl = "http://localhost:8081/xmla";
    public static final String SOAP_ACTION_DISCOVER = URN_SCHEMAS_MICROSOFT_COM_XML_ANALYSIS + ":Discover";

    public static final String REQUEST_AUTHENTICATE_1 = """
            <Authenticate xmlns="http://schemas.microsoft.com/analysisservices/2003/ext">
            <SspiHandshake>TlRMTVNTUAABAAAAB7IIogcABwAxAAAACQAJACgAAAAFAs4OAAAAD1ZBTEVSSUswM1JFRE1PTkQ=</SspiHandshake>
            </Authenticate>
            """;

    public static final String REQUEST_DISCOVER_MDSCHEMACUBES_CONTENT = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">
            <RequestType>MDSCHEMA_CUBES</RequestType>
            <Restrictions></Restrictions>
            <Properties>
            <PropertyList>
            <Content>Data</Content>
            </PropertyList>
            </Properties>
            </Discover>
            """;

    public static final String REQUEST_DISCOVER_PROPERTIES_LocaleIdentifier = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">
               <RequestType>DISCOVER_PROPERTIES</RequestType>
                <Restrictions/>
                <Properties>
                  <PropertyList>
                    <LocaleIdentifier>1033</LocaleIdentifier>
                  </PropertyList>
                </Properties>
            </Discover>
            """;

	private static final String PID_MS_SOAP = "org.eclipse.daanse.msxmlanalysisservice";

    @InjectBundleContext
    BundleContext bc;
    
    @InjectService
	ServiceComponentRuntime componentRuntime;

    private ArgumentCaptor<Discover> dicoverCaptor;

    @BeforeEach
    void beforaEach() {
        XmlaService xmlaService = mock(XmlaService.class);
        dicoverCaptor = ArgumentCaptor.forClass(Discover.class);
        bc.registerService(XmlaService.class, xmlaService, FrameworkUtil.asDictionary(Map.of("type", "mock")));
        
        //TODO: register matching MsXmlAnalysisSoap
    }
    
    @Test
	@WithFactoryConfiguration(factoryPid = PID_MS_SOAP, name = "test-ms-config", location = "?", properties = {
			@Property(key = "xmlaService.target", value = "(type=mock)") ,
	@Property(key = "osgi_soap_endpoint_contextpath", value = "/ms") })
    void testRequestwsdl(@InjectService XmlaService xmlaService) throws Exception {
    	printScrInfo();
		try (InputStream stream = new URL(String.format("http://localhost:%s/echo?wsdl", SERVER_PORT_WHITEBOARD)).openStream()) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			stream.transferTo(out);
			String wsdl = new String(out.toByteArray(), StandardCharsets.UTF_8).replaceAll("\\s", " ")
					.replaceAll("\\s+", " ");
			System.out.println("We got the WSDL: "+wsdl);
			assertThat(wsdl).matches(".*<definitions.*name=\"MsXmlAnalysisService\".*>.*")
					.contains("<service name=\"MsXmlAnalysisService\">");
		}
		
    }
    

    @Test()
    void testRequest_AUTH(@InjectService XmlaService xmlaService) throws Exception {

        AuthenticateResponse ar = new AuthenticateResponse();
        ReturnValue rv = new ReturnValue();
        rv.setSspiHandshake("22".getBytes());
        ar.setReturn(rv);
        when(xmlaService.authenticate(Mockito.any())).thenReturn(ar);

        SOAPUtil.callSoapWebService(soapEndpointUrl, Optional.empty(), envelop(REQUEST_AUTHENTICATE_1));

//        verify(xmlaService, (times(1))).authenticate(Mockito.any());
    }

    @Test
    void testRequest_DISCOVER_PROPERTIES_LocaleIdentifier(@InjectService XmlaService xmlaService) throws Exception {

        SOAPUtil.callSoapWebService(soapEndpointUrl, Optional.of(SOAP_ACTION_DISCOVER),
                envelop(REQUEST_DISCOVER_PROPERTIES_LocaleIdentifier));

        verify(xmlaService, (times(1))).discover(dicoverCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.any());

        assertThat(dicoverCaptor.getValue()).isNotNull()
                .satisfies(d -> {
                    // getRequestType
                    assertThat(d.getRequestType()).isNotNull()
                            .isEqualTo("DISCOVER_PROPERTIES");
                    // getRestrictions
                    assertThat(d.getRestrictions()).isNotNull()
                            .satisfies(r -> {
                                assertThat(r.getRestrictionList()).isNull();
                            });
                    // getProperties
                    assertThat(d.getProperties()).isNotNull()
                            .satisfies(r -> {
                                assertThat(r.getPropertyList()).isNotNull()
                                        .satisfies(pl -> pl.getLocaleIdentifier()
                                                .equals(new BigInteger("1033")));
                            });

                });

    }

    @Test
    void testRequest_MDSCHEMA_CUBES_Content_Data(@InjectService XmlaService xmlaService) throws Exception {

        SOAPUtil.callSoapWebService(soapEndpointUrl, Optional.of(SOAP_ACTION_DISCOVER),
                envelop(REQUEST_DISCOVER_MDSCHEMACUBES_CONTENT));

        verify(xmlaService, (times(1))).discover(dicoverCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.any());

        assertThat(dicoverCaptor.getValue()).isNotNull()
                .satisfies(d -> {
                    // getRequestType
                    assertThat(d.getRequestType()).isNotNull()
                            .isEqualTo("MDSCHEMA_CUBES");
                    // getRestrictions
                    assertThat(d.getRestrictions()).isNotNull()
                            .satisfies(r -> {
                                assertThat(r.getRestrictionList()).isNull();
                            });
                    // getProperties
                    assertThat(d.getProperties()).isNotNull()
                            .satisfies(r -> {
                                assertThat(r.getPropertyList()).isNotNull()
                                        .satisfies(pl -> pl.getContent()
                                                .equals("Data"));
                            });

                });

    }

    @Test
    void testResponse(@InjectService XmlaService xmlaService) throws Exception {

        DiscoverResponse discoverResponse = new DiscoverResponse();

        org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse.Return r = new DiscoverResponse.Return();
        Rowset rs = new Rowset();
        Row row = new Row();
        rs.getRow()
                .add(row);

        r.setRoot(rs);
        discoverResponse.setReturn(r);

        when(xmlaService.discover(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(discoverResponse);

        SOAPMessage response = SOAPUtil.callSoapWebService(soapEndpointUrl, Optional.of(SOAP_ACTION_DISCOVER),
                envelop(""));
        System.out.println(response);

    }

    private static Consumer<SOAPMessage> envelop(String xmlString) throws SOAPException {

        return plainSoapMessage -> {

            try {
                SOAPPart soapPart = plainSoapMessage.getSOAPPart();
                plainSoapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "false");
                SOAPEnvelope envelope = soapPart.getEnvelope();
                // SOAP Body
                SOAPBody soapBody = envelope.getBody();
                soapBody.addDocument(XMLUtil.stringToDocument(xmlString));

            } catch (SOAPException e) {
                fail(e);
            }
        };
    }

    void printScrInfo() {

		System.out.println("============ Framework Components ==================");
		Collection<ComponentDescriptionDTO> descriptionDTOs = componentRuntime.getComponentDescriptionDTOs();
		Comparator<ComponentConfigurationDTO> byComponentName = Comparator.comparing(dto -> dto.description.name,
				String.CASE_INSENSITIVE_ORDER);
		Comparator<ComponentConfigurationDTO> byComponentState = Comparator.comparingInt(dto -> dto.state);
		descriptionDTOs.stream().flatMap(dto -> componentRuntime.getComponentConfigurationDTOs(dto).stream())
				.sorted(byComponentState.thenComparing(byComponentName)).forEachOrdered(dto -> {
					if (dto.state == ComponentConfigurationDTO.FAILED_ACTIVATION) {
						System.out.println(
								toComponentState(dto.state) + " | " + dto.description.name + " | " + dto.failure);
					} else {
						System.out.println(toComponentState(dto.state) + " | " + dto.description.name);
					}
					for (int i = 0; i < dto.unsatisfiedReferences.length; i++) {
						UnsatisfiedReferenceDTO ref = dto.unsatisfiedReferences[i];
						System.out.println("\t" + ref.name + " is missing");
					}
					for (int i = 0; i < dto.satisfiedReferences.length; i++) {
						SatisfiedReferenceDTO sat = dto.satisfiedReferences[i];
						System.out.println("\t" + sat.name + " (bound " + sat.boundServices.length + ")");
					}
					System.out.println("\tProperties:");
					for (Entry<String, Object> entry : dto.properties.entrySet()) {
						System.out.println("\t\t" + entry.getKey() + ": " + entry.getValue());
					}
				});
	}

	private static String toComponentState(int state) {
		return switch (state) {
		case ComponentConfigurationDTO.ACTIVE -> "ACTIVE     ";
		case ComponentConfigurationDTO.FAILED_ACTIVATION -> "FAILED     ";
		case ComponentConfigurationDTO.SATISFIED -> "SATISFIED  ";
		case ComponentConfigurationDTO.UNSATISFIED_CONFIGURATION, ComponentConfigurationDTO.UNSATISFIED_REFERENCE ->
			"UNSATISFIED";
		default -> String.valueOf(state);
		};
	}
}
