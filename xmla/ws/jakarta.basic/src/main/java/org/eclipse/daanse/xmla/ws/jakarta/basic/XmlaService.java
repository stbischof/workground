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
package org.eclipse.daanse.xmla.ws.jakarta.basic;

import org.eclipse.daanse.xmla.model.jaxb.ext.Authenticate;
import org.eclipse.daanse.xmla.model.jaxb.ext.AuthenticateResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Execute;
import org.eclipse.daanse.xmla.model.jaxb.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Session;

import jakarta.xml.ws.Holder;

public interface XmlaService {

  public AuthenticateResponse authenticate(Authenticate authenticate);

  public DiscoverResponse discover(Discover parameters, Holder<Session> session, BeginSession beginSession,
      EndSession endSession);

  public ExecuteResponse execute(Execute parameters, Holder<Session> session, BeginSession beginSession,
      EndSession endSession);

}
