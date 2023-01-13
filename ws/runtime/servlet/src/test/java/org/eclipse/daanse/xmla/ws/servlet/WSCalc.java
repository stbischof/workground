package org.eclipse.daanse.xmla.ws.servlet;

import org.eclipse.daanse.xmla.ws.servlet.XML.IN;
import org.eclipse.daanse.xmla.ws.servlet.XML.OUT;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@WebService(serviceName = "CalculatorService")
@XmlSeeAlso({XML.class})
public class WSCalc {

    private int i;

    public WSCalc(int i) {
        this.i = i;
    }

    @WebMethod(operationName = "calc", action = "urn:calc")

    public OUT calc(@WebParam(name = "num") IN in) {

        return new OUT(in.i + i);
    }

}
