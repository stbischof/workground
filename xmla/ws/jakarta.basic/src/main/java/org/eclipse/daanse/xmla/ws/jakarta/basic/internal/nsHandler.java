package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import jakarta.xml.bind.Element;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class nsHandler implements SOAPHandler<SOAPMessageContext> {

    private Logger logger = LoggerFactory.getLogger(nsHandler.class);

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage message = context.getMessage();

        try {
            if (moveNamespaceToDocument(message, "http://www.myserviceABC.com/application/ws/service")) {

                context.setMessage(message);
            }
        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
        // Deliberately empty
    }

    private static boolean moveNamespaceToDocument(SOAPMessage message, String namespaceURI) throws SOAPException {
        SOAPEnvelope envelope = message.getSOAPPart()
                .getEnvelope();
        SOAPBody body = envelope.getBody();
        if (body.hasFault()) {
            return false;
        }

        Attr namespaceAttr = null;

        NamedNodeMap attributes = body.getAttributes();
        int count = attributes.getLength();
        for (int i = 0; i < count; i++) {
            Attr attr = (Attr) attributes.item(i);
            String name = attr.getName();
            if (name.startsWith("xmlns:") && namespaceURI.equals(attr.getValue())) {

                namespaceAttr = attr;
                break;
            }
        }

        if (namespaceAttr == null) {
            return false;
        }

        NodeList children = body.getElementsByTagName("*");
        if (children.getLength() < 1) {
            return false;
        }
        Element root = (Element) children.item(0);

        body.removeAttributeNode(namespaceAttr);
//        root.setAttributeNode(namespaceAttr);

        return true;
    }
}