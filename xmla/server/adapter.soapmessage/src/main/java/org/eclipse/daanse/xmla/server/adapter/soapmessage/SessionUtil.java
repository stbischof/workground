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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import jakarta.xml.soap.SOAPHeader;
import org.eclipse.daanse.xmla.api.xmla.BeginSession;
import org.eclipse.daanse.xmla.api.xmla.EndSession;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.eclipse.daanse.xmla.model.record.xmla.SessionR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SessionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionUtil.class);

    public static Optional<Session> getSession(SOAPHeader soapHeader, Set<String> sessions) {
        Optional<Session> oSession = Convert.getSession(soapHeader);
        Optional<EndSession> oEndSession = Convert.getEndSession(soapHeader);
        Optional<BeginSession> beginSession = Convert.getBeginSession(soapHeader);
        if (beginSession.isPresent()) {
            String ses = UUID.randomUUID().toString();
            sessions.add(ses);
            return Optional.of(new SessionR(ses, null));

        }
        if (oSession.isPresent()) {
            checkSession(oSession.get().sessionId(), sessions);
            return oSession;


        }
        if (oEndSession.isPresent()) {
            removeSession(oEndSession.get().sessionId(), sessions);
        }
        return Optional.empty();
    }

    private static void removeSession(String s, Set<String> sessions) {
        sessions.remove(s);
    }

    private static void checkSession(String session, Set<String> sessions) {
        if (!sessions.contains(session)) {
            LOGGER.error("Session expired " + session);
            throw new RuntimeException("Session expired");
        }
    }

}
