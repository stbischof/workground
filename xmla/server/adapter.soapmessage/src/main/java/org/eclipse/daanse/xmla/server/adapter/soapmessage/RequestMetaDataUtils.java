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

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.eclipse.daanse.xmla.model.record.RequestMetaDataR;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RequestMetaDataUtils {

    public static final String USER_AGENT = "User-agent";

    public static RequestMetaData getRequestMetaData(
        Map<String, Object> headers,
        Optional<Session> ses) {
        Optional<String> oUserAgent = get(headers.get(USER_AGENT));
        return new RequestMetaDataR(oUserAgent, ses.isPresent() ? Optional.of(ses.get().sessionId()) : Optional.empty());
    }

    private static Optional<String> get(Object o) {
        if (o instanceof List list) {
            if (list.isEmpty()) {
                return Optional.of((String) list.get(0));
            }
        }
        if (o instanceof String s) {
            return Optional.of(s);
        }
        return Optional.empty();
    }
}
