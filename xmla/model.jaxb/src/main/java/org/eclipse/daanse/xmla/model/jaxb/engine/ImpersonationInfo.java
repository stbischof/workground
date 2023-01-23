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
*//*
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
package org.eclipse.daanse.xmla.model.jaxb.engine;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpersonationInfo", propOrder = {

})
public class ImpersonationInfo {

  @XmlElement(name = "ImpersonationMode", required = true)
  protected String impersonationMode;
  @XmlElement(name = "Account")
  protected String account;
  @XmlElement(name = "Password")
  protected String password;
  @XmlElement(name = "ImpersonationInfoSecurity")
  protected String impersonationInfoSecurity;

  public String getImpersonationMode() {
    return impersonationMode;
  }

  public void setImpersonationMode(String value) {
    this.impersonationMode = value;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String value) {
    this.account = value;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String value) {
    this.password = value;
  }

  public String getImpersonationInfoSecurity() {
    return impersonationInfoSecurity;
  }

  public void setImpersonationInfoSecurity(String value) {
    this.impersonationInfoSecurity = value;
  }

}
