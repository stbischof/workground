package org.eclipse.daanse.xmla.ws.servlet;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class WSEcho {

    @WebMethod(operationName = "echo", action = "urn:echo")
    public String echo(@WebParam(name = "textIn") String text) {

        return text;
    }

}
