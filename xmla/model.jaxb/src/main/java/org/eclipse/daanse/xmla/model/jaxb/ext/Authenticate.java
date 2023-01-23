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

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sspiHandshake" })
@XmlRootElement(name = "Authenticate")
public class Authenticate implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(name = "SspiHandshake", required = true)
  protected byte[] sspiHandshake;

  public byte[] getSspiHandshake() {
    return sspiHandshake;
  }

  public void setSspiHandshake(byte[] value) {
    this.sspiHandshake = value;
  }

  public boolean isSetSspiHandshake() {
    return (this.sspiHandshake != null);
  }

}
