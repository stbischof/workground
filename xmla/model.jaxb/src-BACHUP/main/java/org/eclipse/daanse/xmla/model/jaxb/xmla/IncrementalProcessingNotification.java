
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IncrementalProcessingNotification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IncrementalProcessingNotification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="TableID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ProcessingQuery" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncrementalProcessingNotification", propOrder = {

})
public class IncrementalProcessingNotification {

    @XmlElement(name = "TableID", required = true)
    protected String tableID;
    @XmlElement(name = "ProcessingQuery", required = true)
    protected String processingQuery;

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

    public boolean isSetTableID() {
        return (this.tableID!= null);
    }

    /**
     * Gets the value of the processingQuery property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingQuery() {
        return processingQuery;
    }

    /**
     * Sets the value of the processingQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingQuery(String value) {
        this.processingQuery = value;
    }

    public boolean isSetProcessingQuery() {
        return (this.processingQuery!= null);
    }

}
