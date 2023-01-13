package org.eclipse.daanse.ws.handler;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.xml.namespace.QName;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
@Component(service = Handler.class)
@Designate(ocd = SOAPLoggingHandler.Config.class, factory = true)
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    @ObjectClassDefinition
    @interface Config {

    }

    private Config config;

    @Activate
    public void activate(Config config) {
        modified(config);
    }

    @Modified
    public void modified(Config config) {
        this.config = config;
    }

    @Deactivate
    public void deactivate() {

    }

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