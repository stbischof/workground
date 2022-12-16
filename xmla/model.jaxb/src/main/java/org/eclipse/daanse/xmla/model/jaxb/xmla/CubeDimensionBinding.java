
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CubeDimensionBinding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CubeDimensionBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CubeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Filter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubeDimensionBinding", propOrder = { "dataSourceID", "cubeID", "cubeDimensionID", "filter" })
public class CubeDimensionBinding extends Binding {

  @XmlElement(name = "DataSourceID", required = true)
  protected String dataSourceID;
  @XmlElement(name = "CubeID", required = true)
  protected String cubeID;
  @XmlElement(name = "CubeDimensionID", required = true)
  protected String cubeDimensionID;
  @XmlElement(name = "Filter")
  protected String filter;

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
   * Gets the value of the cubeID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCubeID() {
    return cubeID;
  }

  /**
   * Sets the value of the cubeID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCubeID(String value) {
    this.cubeID = value;
  }

  public boolean isSetCubeID() {
    return (this.cubeID != null);
  }

  /**
   * Gets the value of the cubeDimensionID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCubeDimensionID() {
    return cubeDimensionID;
  }

  /**
   * Sets the value of the cubeDimensionID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCubeDimensionID(String value) {
    this.cubeDimensionID = value;
  }

  public boolean isSetCubeDimensionID() {
    return (this.cubeDimensionID != null);
  }

  /**
   * Gets the value of the filter property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFilter() {
    return filter;
  }

  /**
   * Sets the value of the filter property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFilter(String value) {
    this.filter = value;
  }

  public boolean isSetFilter() {
    return (this.filter != null);
  }

}
