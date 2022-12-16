
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataSourceViewBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSourceViewBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceViewID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceViewBinding", propOrder = {
    "dataSourceViewID"
})
public class DataSourceViewBinding
    extends Binding
{

    @XmlElement(name = "DataSourceViewID", required = true)
    protected String dataSourceViewID;

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

    public boolean isSetDataSourceViewID() {
        return (this.dataSourceViewID!= null);
    }

}
