
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TableBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TableBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}TabularBinding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DbTableName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DbSchemaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableBinding", propOrder = {
    "dataSourceID",
    "dbTableName",
    "dbSchemaName"
})
public class TableBinding
    extends TabularBinding
{

    @XmlElement(name = "DataSourceID")
    protected String dataSourceID;
    @XmlElement(name = "DbTableName", required = true)
    protected String dbTableName;
    @XmlElement(name = "DbSchemaName")
    protected String dbSchemaName;

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
     * Gets the value of the dbTableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbTableName() {
        return dbTableName;
    }

    /**
     * Sets the value of the dbTableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbTableName(String value) {
        this.dbTableName = value;
    }

    /**
     * Gets the value of the dbSchemaName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbSchemaName() {
        return dbSchemaName;
    }

    /**
     * Sets the value of the dbSchemaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbSchemaName(String value) {
        this.dbSchemaName = value;
    }

}
