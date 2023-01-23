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
package org.eclipse.daanse.xmla.model.jaxb.ext;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema
   * derived classes for package: org.eclipse.daanse.xmla.ws.ext
   * 
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link AuthenticateResponse }
   * 
   */
  public AuthenticateResponse createAuthenticateResponse() {
    return new AuthenticateResponse();
  }

  /**
   * Create an instance of {@link Authenticate }
   * 
   */
  public Authenticate createAuthenticate() {
    return new Authenticate();
  }

  /**
   * Create an instance of {@link AuthenticateResponse.ReturnValue }
   * 
   */
  public ReturnValue createAuthenticateResponseReturn() {
    return new ReturnValue();
  }

}
