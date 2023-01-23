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
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for QueryBinding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="QueryBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}TabularBinding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QueryDefinition" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryBinding", propOrder = { "dataSourceID", "queryDefinition" })
public class QueryBinding extends TabularBinding {

  @XmlElement(name = "DataSourceID")
  protected String dataSourceID;
  @XmlElement(name = "QueryDefinition", required = true)
  protected String queryDefinition;

  /**
   * Gets the value of the dataSourceID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDataSourceID() {
    return dataSourceID;
  }

  /**
   * Sets the value of the dataSourceID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDataSourceID(String value) {
    this.dataSourceID = value;
  }

  public boolean isSetDataSourceID() {
    return (this.dataSourceID != null);
  }

  /**
   * Gets the value of the queryDefinition property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getQueryDefinition() {
    return queryDefinition;
  }

  /**
   * Sets the value of the queryDefinition property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setQueryDefinition(String value) {
    this.queryDefinition = value;
  }

  public boolean isSetQueryDefinition() {
    return (this.queryDefinition != null);
  }

}
