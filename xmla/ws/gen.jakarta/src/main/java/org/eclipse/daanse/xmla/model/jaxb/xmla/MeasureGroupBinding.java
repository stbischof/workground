
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import javax.xml.datatype.Duration;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MeasureGroupBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MeasureGroupBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CubeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MeasureGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Persistence" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="NotPersisted"/&gt;
 *               &lt;enumeration value="Metadata"/&gt;
 *               &lt;enumeration value="All"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RefreshPolicy" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ByQuery"/&gt;
 *               &lt;enumeration value="ByInterval"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RefreshInterval" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
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
@XmlType(name = "MeasureGroupBinding", propOrder = {
    "dataSourceID",
    "cubeID",
    "measureGroupID",
    "persistence",
    "refreshPolicy",
    "refreshInterval",
    "filter"
})
public class MeasureGroupBinding
    extends Binding
{

    @XmlElement(name = "DataSourceID", required = true)
    protected String dataSourceID;
    @XmlElement(name = "CubeID", required = true)
    protected String cubeID;
    @XmlElement(name = "MeasureGroupID", required = true)
    protected String measureGroupID;
    @XmlElement(name = "Persistence")
    protected String persistence;
    @XmlElement(name = "RefreshPolicy")
    protected String refreshPolicy;
    @XmlElement(name = "RefreshInterval")
    protected Duration refreshInterval;
    @XmlElement(name = "Filter")
    protected String filter;

    /**
     * Gets the value of the dataSourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceID() {
        return dataSourceID;
    }

    /**
     * Sets the value of the dataSourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceID(String value) {
        this.dataSourceID = value;
    }

    /**
     * Gets the value of the cubeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubeID() {
        return cubeID;
    }

    /**
     * Sets the value of the cubeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubeID(String value) {
        this.cubeID = value;
    }

    /**
     * Gets the value of the measureGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureGroupID() {
        return measureGroupID;
    }

    /**
     * Sets the value of the measureGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureGroupID(String value) {
        this.measureGroupID = value;
    }

    /**
     * Gets the value of the persistence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistence() {
        return persistence;
    }

    /**
     * Sets the value of the persistence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersistence(String value) {
        this.persistence = value;
    }

    /**
     * Gets the value of the refreshPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefreshPolicy() {
        return refreshPolicy;
    }

    /**
     * Sets the value of the refreshPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefreshPolicy(String value) {
        this.refreshPolicy = value;
    }

    /**
     * Gets the value of the refreshInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getRefreshInterval() {
        return refreshInterval;
    }

    /**
     * Sets the value of the refreshInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setRefreshInterval(Duration value) {
        this.refreshInterval = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilter(String value) {
        this.filter = value;
    }

}
