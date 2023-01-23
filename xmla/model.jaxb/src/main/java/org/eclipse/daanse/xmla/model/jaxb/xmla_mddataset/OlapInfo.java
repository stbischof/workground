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
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OlapInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OlapInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CubeInfo" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}CubeInfo"/&gt;
 *         &lt;element name="AxesInfo" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}AxesInfo"/&gt;
 *         &lt;element name="CellInfo" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}CellInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OlapInfo", propOrder = { "cubeInfo", "axesInfo", "cellInfo" })
public class OlapInfo {

  @XmlElement(name = "CubeInfo", required = true)
  protected CubeInfo cubeInfo;
  @XmlElement(name = "AxesInfo", required = true)
  protected AxesInfo axesInfo;
  @XmlElement(name = "CellInfo", required = true)
  protected CellInfo cellInfo;

  /**
   * Gets the value of the cubeInfo property.
   * 
   * @return possible object is {@link CubeInfo }
   * 
   */
  public CubeInfo getCubeInfo() {
    return cubeInfo;
  }

  /**
   * Sets the value of the cubeInfo property.
   * 
   * @param value allowed object is {@link CubeInfo }
   * 
   */
  public void setCubeInfo(CubeInfo value) {
    this.cubeInfo = value;
  }

  public boolean isSetCubeInfo() {
    return (this.cubeInfo != null);
  }

  /**
   * Gets the value of the axesInfo property.
   * 
   * @return possible object is {@link AxesInfo }
   * 
   */
  public AxesInfo getAxesInfo() {
    return axesInfo;
  }

  /**
   * Sets the value of the axesInfo property.
   * 
   * @param value allowed object is {@link AxesInfo }
   * 
   */
  public void setAxesInfo(AxesInfo value) {
    this.axesInfo = value;
  }

  public boolean isSetAxesInfo() {
    return (this.axesInfo != null);
  }

  /**
   * Gets the value of the cellInfo property.
   * 
   * @return possible object is {@link CellInfo }
   * 
   */
  public CellInfo getCellInfo() {
    return cellInfo;
  }

  /**
   * Sets the value of the cellInfo property.
   * 
   * @param value allowed object is {@link CellInfo }
   * 
   */
  public void setCellInfo(CellInfo value) {
    this.cellInfo = value;
  }

  public boolean isSetCellInfo() {
    return (this.cellInfo != null);
  }

}
