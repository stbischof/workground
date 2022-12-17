package org.eclipse.daanse.xmla.ws.jakarta.basic;

import static org.eclipse.daanse.xmla.ws.jakarta.basic.XMLUtil.pretty;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public class SOAPUtil {
  public static Logger logger = LoggerFactory.getLogger(SOAPUtil.class);

  public static SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction,
      Consumer<SOAPMessage> consumer) {
    try {

      MessageFactory messageFactory = MessageFactory.newInstance();
      SOAPMessage message = messageFactory.createMessage();

      consumer.accept(message);

      MimeHeaders headers = message.getMimeHeaders();
      headers.addHeader("SOAPAction", soapAction);
      message.saveChanges();

      /* Print the request message, just for debugging purposes */
      logger.debug("Request SOAP Message:");

      logger.debug("\n" + pretty(string(message)));

      // Create SOAP Connection

      SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
      SOAPConnection connection = connectionFactory.createConnection();

      // Send SOAP Message to SOAP Server
      SOAPMessage response = connection.call(message, soapEndpointUrl);

      // Print the SOAP Response

      logger.debug("Response SOAP Message:");
      logger.debug("\n" + pretty(string(response)));

      connection.close();

      return response;
    } catch (Exception e) {
      fail(
          "\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n",
          e);
      return null;
    }
  }

  public static String string(SOAPMessage soapMessage) throws SOAPException, IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    soapMessage.writeTo(baos);
    return new String(baos.toByteArray());

  }
}
