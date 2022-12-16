
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
