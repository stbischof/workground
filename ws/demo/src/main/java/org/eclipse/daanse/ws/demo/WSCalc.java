package org.eclipse.daanse.ws.demo;

import org.eclipse.daanse.ws.api.whiteboard.prototypes.SOAPWhiteboardEndpoint;
import org.eclipse.daanse.ws.demo.XML.IN;
import org.eclipse.daanse.ws.demo.XML.OUT;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@WebService(serviceName = "CalculatorService")
@XmlSeeAlso({XML.class})
@SOAPWhiteboardEndpoint(contextpath = "/calc")
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE, name="org.eclipse.daanse.ws.demo.calculator", service = WSCalc.class, immediate = true)
public class WSCalc {

    private int i;

    @Activate
    public WSCalc(WSCalcConfig config) {
    	i = config.number();
    }
    
    @Activate
    @Modified
    void configure(WSCalcConfig config) {
    	i = config.number();
    }

    @WebMethod(operationName = "calc", action = "urn:calc")
    public OUT calc(@WebParam(name = "num") IN in) {
        return new OUT(in.i + i);
    }

}
