
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DSVTableBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DSVTableBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}TabularBinding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceViewID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TableID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}DataEmbeddingStyle" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DSVTableBinding", propOrder = {
    "dataSourceViewID",
    "tableID",
    "dataEmbeddingStyle"
})
public class DSVTableBinding
    extends TabularBinding
{

    @XmlElement(name = "DataSourceViewID")
    protected String dataSourceViewID;
    @XmlElement(name = "TableID", required = true)
    protected String tableID;
    @XmlElement(name = "DataEmbeddingStyle", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String dataEmbeddingStyle;

    /**
     * Gets the value of the dataSourceViewID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceViewID() {
        return dataSourceViewID;
    }

    /**
     * Sets the value of the dataSourceViewID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceViewID(String value) {
        this.dataSourceViewID = value;
    }

    /**
     * Gets the value of the tableID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableID() {
        return tableID;
    }

    /**
     * Sets the value of the tableID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableID(String value) {
        this.tableID = value;
    }

    /**
     * Gets the value of the dataEmbeddingStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataEmbeddingStyle() {
        return dataEmbeddingStyle;
    }

    /**
     * Sets the value of the dataEmbeddingStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEmbeddingStyle(String value) {
        this.dataEmbeddingStyle = value;
    }

}
