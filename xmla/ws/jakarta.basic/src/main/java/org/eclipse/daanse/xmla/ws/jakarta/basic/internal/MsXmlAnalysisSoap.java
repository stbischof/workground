package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import org.eclipse.daanse.xmla.model.jaxb.ext.ReturnValue;
import org.eclipse.daanse.xmla.model.jaxb.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Execute;
import org.eclipse.daanse.xmla.model.jaxb.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Session;
import org.eclipse.daanse.xmla.ws.jakarta.basic.XmlaService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

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
//(@SchemaValidation
public class MsXmlAnalysisSoap {
//  DraconianValidationErrorHandler c = new DraconianValidationErrorHandler();
    private XmlaService xmlaService;

    public MsXmlAnalysisSoap(XmlaService xmlaService) {
        this.xmlaService = xmlaService;
    }

    @WebMethod(operationName = "Authenticate")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "Authenticate", targetNamespace = "http://schemas.microsoft.com/analysisservices/2003/ext", className = "org.eclipse.daanse.xmla.model.jaxb.xmla.ext.Authenticate")
    @ResponseWrapper(localName = "AuthenticateResponse", targetNamespace = "http://schemas.microsoft.com/analysisservices/2003/ext", className = "org.eclipse.daanse.xmla.model.jaxb.xmla.ext.AuthenticateResponse")
    public ReturnValue authenticate(@WebParam(name = "SspiHandshake", targetNamespace = "") byte[] sspiHandshake) {

        ReturnValue rv = xmlaService.authenticate(sspiHandshake);
        return rv;
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
