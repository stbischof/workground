package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public class MyMessageNamespaceMapper implements SOAPHandler<SOAPMessageContext> {

  @Override
  public Set<QName> getHeaders() {
    return null;
  }

  @Override
  public boolean handleMessage(SOAPMessageContext context) {
    final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    // only process outbound messages
    if (outbound) {
      try {
        final SOAPMessage soapMessage = context.getMessage();
        final SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
        final SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        final SOAPBody soapBody = soapMessage.getSOAPBody();

        // STEP 1: add new prefix/namespace entries
        soapEnvelope.addNamespaceDeclaration("S1", "http://schemas.xmlsoap.org/soap/envelope/");
        soapEnvelope.addNamespaceDeclaration("FOO-1", "http://foo1.bar.com/ns");

        // STEP 2: set desired namespace prefixes
        // set desired namespace prefix for the envelope, header and body
        soapEnvelope.setPrefix("S1");
        soapHeader.setPrefix("S1");
        soapBody.setPrefix("S1");
        addDesiredBodyNamespaceEntries(soapBody.getChildElements());

        // STEP 3: remove prefix/namespace entries entries added by JAX-WS
        soapEnvelope.removeNamespaceDeclaration("S");
        soapEnvelope.removeNamespaceDeclaration("SOAP-ENV");

        // IMPORTANT! "Save" the changes
        soapMessage.saveChanges();
      } catch (SOAPException e) {
        // handle the error
      }
    }

    return true;
  }

  private void addDesiredBodyNamespaceEntries(Iterator childElements) {
    while (childElements.hasNext()) {
      final Object childElementNode = childElements.next();
      if (childElementNode instanceof SOAPElement) {
        SOAPElement soapElement = (SOAPElement) childElementNode;

        // set desired namespace body element prefix
        soapElement.setPrefix("FOO-1");

        // recursively set desired namespace prefix entries in child elements
        addDesiredBodyNamespaceEntries(soapElement.getChildElements());
      }
    }
  }

  private Set<String> getNamespacePrefixList(Iterator namespacePrefixIter) {
    Set<String> namespacePrefixesSet = new HashSet<>();
    while (namespacePrefixIter.hasNext()) {
      namespacePrefixesSet.add((String) namespacePrefixIter.next());
    }
    return namespacePrefixesSet;
  }

  @Override
  public boolean handleFault(SOAPMessageContext context) {
    return true;
  }

  @Override
  public void close(MessageContext context) {
  }
}