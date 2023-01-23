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
package org.eclipse.daanse.xmla.model.jaxb.msxmla;

import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.eclipse.daanse.xmla.ws.msxmla package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema
   * derived classes for package: org.eclipse.daanse.xmla.ws.msxmla
   * 
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link NormTupleSet }
   * 
   */
  public NormTupleSet createNormTupleSet() {
    return new NormTupleSet();
  }

  /**
   * Create an instance of {@link NormTuplesType }
   * 
   */
  public NormTuplesType createNormTuplesType() {
    return new NormTuplesType();
  }

  /**
   * Create an instance of {@link NormTuplesType.NormTuple }
   * 
   */
  public NormTuplesType.NormTuple createNormTuplesTypeNormTuple() {
    return new NormTuplesType.NormTuple();
  }

  /**
   * Create an instance of {@link NormTupleSet.MembersLookup }
   * 
   */
  public NormTupleSet.MembersLookup createNormTupleSetMembersLookup() {
    return new NormTupleSet.MembersLookup();
  }

  /**
   * Create an instance of {@link NormTuplesType.NormTuple.MemberRef }
   * 
   */
  public NormTuplesType.NormTuple.MemberRef createNormTuplesTypeNormTupleMemberRef() {
    return new NormTuplesType.NormTuple.MemberRef();
  }

}
