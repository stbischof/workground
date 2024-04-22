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

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserPrincipal;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.eclipse.daanse.xmla.api.execute.alter.AlterRequest;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;

import static org.eclipse.daanse.xmla.client.soapmessage.Constants.SOAP_ACTION_EXECUTE;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToAlterResponse;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToCancelResponse;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToClearCacheResponse;
import static org.eclipse.daanse.xmla.client.soapmessage.Convertor.convertToStatementResponse;

public class ExecuteServiceImpl implements ExecuteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteServiceImpl.class);
    private SoapClient soapClient;

    public ExecuteServiceImpl(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public StatementResponse statement(StatementRequest statementRequest,
                                       RequestMetaData metaData, UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = ExecuteConsumers.createStatementRequestConsumer(statementRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_EXECUTE), msg);
            return convertToStatementResponse(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("ExecuteService statement error", e);
        }
        return null;
    }

    @Override
    public AlterResponse alter(AlterRequest alterRequest,
                               RequestMetaData metaData, UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = ExecuteConsumers.createAlterRequestConsumer(alterRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_EXECUTE), msg);
            return convertToAlterResponse(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("ExecuteService alter error", e);
        }
        return null;
    }

    @Override
    public ClearCacheResponse clearCache(ClearCacheRequest clearCacheRequest,
                                         RequestMetaData metaData, UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = ExecuteConsumers.createClearCacheRequestConsumer(clearCacheRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_EXECUTE), msg);
            return convertToClearCacheResponse(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("ExecuteService clearCache error", e);
        }
        return null;
    }

    @Override
    public CancelResponse cancel(CancelRequest cancelRequest,
                                 RequestMetaData metaData, UserPrincipal userPrincipal) {
        try {
            Consumer<SOAPMessage> msg = ExecuteConsumers.createCancelRequestConsumer(cancelRequest);
            SOAPMessage message = soapClient.callSoapWebService(Optional.of(SOAP_ACTION_EXECUTE), msg);
            return convertToCancelResponse(message.getSOAPBody());
        } catch (SOAPException e) {
            LOGGER.error("ExecuteService cancel error", e);
        }
        return null;
    }
}
