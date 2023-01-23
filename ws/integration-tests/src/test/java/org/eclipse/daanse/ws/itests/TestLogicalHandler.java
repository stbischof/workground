package org.eclipse.daanse.ws.itests;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.xml.ws.handler.LogicalHandler;
import jakarta.xml.ws.handler.LogicalMessageContext;
import jakarta.xml.ws.handler.MessageContext;

public class TestLogicalHandler implements LogicalHandler<LogicalMessageContext> {

	AtomicInteger handledMessages = new AtomicInteger();

	@Override
	public boolean handleMessage(LogicalMessageContext context) {
		int msg = handledMessages.incrementAndGet();
		System.out.println("TestLogicalHandler.handleMessage no. " + msg);
		return true;
	}

	@Override
	public boolean handleFault(LogicalMessageContext context) {
		System.out.println("TestLogicalHandler.handleFault()");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("TestLogicalHandler.close()");
	}

}
