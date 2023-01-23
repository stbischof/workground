package org.eclipse.daanse.ws.demo;

import org.eclipse.daanse.ws.api.whiteboard.prototypes.SOAPWhiteboardEndpoint;
import org.osgi.service.component.annotations.Component;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
@SOAPWhiteboardEndpoint(contextpath = "/echo")
@Component(immediate = true, service = WSEcho.class, property = "wstype=echo")
public class WSEcho {

	public WSEcho() {
		System.out.println("I will echo everything I receive!");
	}

	@WebMethod(operationName = "echo", action = "echo")
	public String echo(@WebParam(name = "textIn") String text) {
		System.out.println("Echo '" + text + "' to caller!");
		return text;
	}

}
