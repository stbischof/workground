package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private Logger logger = LoggerFactory.getLogger(SOAPLoggingHandler.class);

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        logOut(smc);
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        logOut(smc);
        return true;
    }

    // nothing to clean up
    public void close(MessageContext messageContext) {
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context to see if this is an
     * outgoing or incoming message. Write a brief message to the print stream and
     * output the message. The writeTo() method can throw SOAPException or
     * IOException
     */
  private void logOut(SOAPMessageContext smc) {

    Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    if (outboundProperty.booleanValue()) {
      logger.error("\nOutbound message:");
    } else {
      logger.error("Inbound message:");
    }


    SOAPMessage message = smc.getMessage();
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
       message.writeTo(baos);
      logger.error(new String(baos.toByteArray()));
    } catch (Exception e) {
      logger.error("Exception in handler: " + e);
    }
  }
}