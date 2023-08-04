/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.client.soapmessage;

import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public class SoapClient {

    public SoapClient(String soapEndpointUrl) {
        this.soapEndpointUrl = soapEndpointUrl;
    }

    private String soapEndpointUrl;
    public static final Logger logger = LoggerFactory.getLogger(SoapClient.class);

    public SOAPMessage callSoapWebService(Optional<String> oSoapAction, Consumer<SOAPMessage> consumer)
            throws SOAPException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        consumer.accept(message);

        MimeHeaders headers = message.getMimeHeaders();
        oSoapAction.ifPresent(actionName -> headers.addHeader("SOAPAction", actionName));
        message.saveChanges();

        /* Print the request message, just for debugging purposes */
        logger.debug("Request SOAP Message:");

        // Create SOAP Connection

        SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = connectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        SOAPMessage response = connection.call(message, soapEndpointUrl);

        // Print the SOAP Response

        logger.debug("Response SOAP Message:");

        connection.close();

        return response;

    }

}
