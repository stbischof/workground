package org.eclipse.daanse.ws.itests;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.namespace.QName;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public class TestSoapHandler implements SOAPHandler<SOAPMessageContext> {

	AtomicInteger handledMessages = new AtomicInteger();

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		int msg = handledMessages.incrementAndGet();
		System.out.println("TestSoapHandler.handleMessage no. " + msg);
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("TestSoapHandler.handleFault()");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("TestSoapHandler.close()");
	}

	@Override
	public Set<QName> getHeaders() {
		return Set.of();
	}

}
