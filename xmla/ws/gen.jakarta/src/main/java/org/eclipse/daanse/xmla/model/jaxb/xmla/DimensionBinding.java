
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import javax.xml.datatype.Duration;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DimensionBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Persistence" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="NotPersisted"/&gt;
 *               &lt;enumeration value="Metadata"/&gt;
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
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionBinding", propOrder = {
    "dataSourceID",
    "dimensionID",
    "persistence",
    "refreshPolicy",
    "refreshInterval"
})
public class DimensionBinding
    extends Binding
{

    @XmlElement(name = "DataSourceID", required = true)
    protected String dataSourceID;
    @XmlElement(name = "DimensionID", required = true)
    protected String dimensionID;
    @XmlElement(name = "Persistence")
    protected String persistence;
    @XmlElement(name = "RefreshPolicy")
    protected String refreshPolicy;
    @XmlElement(name = "RefreshInterval")
    protected Duration refreshInterval;

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
     * Gets the value of the dimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensionID() {
        return dimensionID;
    }

    /**
     * Sets the value of the dimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensionID(String value) {
        this.dimensionID = value;
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

}
