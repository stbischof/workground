package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public class AuthenticationHandler implements SOAPHandler<SOAPMessageContext> {

  public Set<QName> getHeaders() {
    return null;
  }

  public boolean handleMessage(SOAPMessageContext context) {
    Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    if (outbound) {
      // Get headers, create if null
      @SuppressWarnings("unchecked")
      Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
      if (headers == null) {
        headers = new HashMap<>();
      }

      // Add authentication header
      headers.put("Authentication-Token", Arrays.asList("somesecret"));
      context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
    }
    return true;
  }

  public boolean handleFault(SOAPMessageContext context) {
    return true;
  }

  public void close(MessageContext context) {
  }
}