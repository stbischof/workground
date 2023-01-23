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
package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import org.eclipse.daanse.ws.api.whiteboard.annotations.RequireSoapWhiteboard;
import org.eclipse.daanse.xmla.model.jaxb.ext.Authenticate;
import org.eclipse.daanse.xmla.model.jaxb.ext.AuthenticateResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Execute;
import org.eclipse.daanse.xmla.model.jaxb.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Session;
import org.eclipse.daanse.xmla.ws.jakarta.basic.XmlaService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.WebServiceContext;

@WebService(name = "MsXmlAnalysisSoapPortType", portName = "MsXmlAnalysisSoapPort", serviceName = "MsXmlAnalysisService") // ,
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ org.eclipse.daanse.xmla.model.jaxb.msxmla.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.ext.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.xmla.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine300_300.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.xmla_exception.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine100.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine2.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine200.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine300.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine400.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine600.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine800.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.xmla_empty.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.xmla_multipleresults.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.xmla_rowset.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine100_100.ObjectFactory.class,
        org.eclipse.daanse.xmla.model.jaxb.engine200_200.ObjectFactory.class })
@Component(service = MsXmlAnalysisSoap.class)
@RequireSoapWhiteboard
@Designate(factory = true, ocd = MsXmlAnalysisSoap.Config.class)
public class MsXmlAnalysisSoap {

    @ObjectClassDefinition()
    @interface Config {

        @AttributeDefinition(name = "XMLA-Service Filter", required = true)
        String xmlaService_target() default "(&(must.be.configured=*)(!(must.not.configured=*)))";

        String osgi_soap_endpoint_implementor() default "true";

        @AttributeDefinition(required = true)
        String osgi_soap_endpoint_contextpath();
    }

    @jakarta.annotation.Resource
    WebServiceContext wsContext;

    @Reference
    private XmlaService xmlaService;

    @Activate
    private Config cfg;

    @WebMethod(operationName = "Authenticate")
    @WebResult(name = "AuthenticateResponse", targetNamespace = "http://schemas.microsoft.com/analysisservices/2003/ext", partName = "parameters")
    public AuthenticateResponse authenticate(
            @WebParam(name = "Authenticate", targetNamespace = "http://schemas.microsoft.com/analysisservices/2003/ext", partName = "parameters") Authenticate authenticate) {
        wsContext.getUserPrincipal();
        AuthenticateResponse ar = xmlaService.authenticate(authenticate);
        return ar;
    }

    @WebMethod(operationName = "Discover", action = "urn:schemas-microsoft-com:xml-analysis:Discover")
    @WebResult(name = "DiscoverResponse", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", partName = "parameters")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)

    public DiscoverResponse discover(
            @WebParam(name = "Discover", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", partName = "parameters") Discover parameters,
            @WebParam(name = "Session", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", header = true, mode = WebParam.Mode.INOUT) Holder<Session> session,
            @WebParam(name = "BeginSession", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", header = true) BeginSession beginSession,
            @WebParam(name = "EndSession", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", header = true) EndSession endSession) {

        System.out.println("---");

        System.out.println(parameters.getRequestType());

        DiscoverResponse discoverResponse = xmlaService.discover(parameters, session, beginSession, endSession);
        return discoverResponse;
    }

    @WebMethod(operationName = "Execute", action = "urn:schemas-microsoft-com:xml-analysis:Execute")
    @WebResult(name = "ExecuteResponse", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", partName = "parameters")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public ExecuteResponse execute(
            @WebParam(name = "Execute", targetNamespace = "urn:schemas-microsoft-com:xml-analysis") Execute parameters,
            @WebParam(name = "Session", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", header = true, mode = WebParam.Mode.INOUT) Holder<Session> session,
            @WebParam(name = "BeginSession", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", header = true) BeginSession beginSession,
            @WebParam(name = "EndSession", targetNamespace = "urn:schemas-microsoft-com:xml-analysis", header = true) EndSession endSession) {

        ExecuteResponse response = xmlaService.execute(parameters, session, beginSession, endSession);
        return response;
    }

}
