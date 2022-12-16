
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregationInstanceAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregationInstanceAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="KeyColumns"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="KeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregationInstanceAttribute", propOrder = {

})
public class AggregationInstanceAttribute {

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "KeyColumns", required = true)
    protected AggregationInstanceAttribute.KeyColumns keyColumns;

    /**
     * Gets the value of the attributeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeID() {
        return attributeID;
    }

    /**
     * Sets the value of the attributeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    public boolean isSetAttributeID() {
        return (this.attributeID!= null);
    }

    /**
     * Gets the value of the keyColumns property.
     * 
     * @return
     *     possible object is
     *     {@link AggregationInstanceAttribute.KeyColumns }
     *     
     */
    public AggregationInstanceAttribute.KeyColumns getKeyColumns() {
        return keyColumns;
    }

    /**
     * Sets the value of the keyColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationInstanceAttribute.KeyColumns }
     *     
     */
    public void setKeyColumns(AggregationInstanceAttribute.KeyColumns value) {
        this.keyColumns = value;
    }

    public boolean isSetKeyColumns() {
        return (this.keyColumns!= null);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="KeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "keyColumn"
    })
    public static class KeyColumns {

        @XmlElement(name = "KeyColumn")
        protected List<DataItem> keyColumn;

        /**
         * Gets the value of the keyColumn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the keyColumn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKeyColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataItem }
         * 
         * 
         */
        public List<DataItem> getKeyColumn() {
            if (keyColumn == null) {
                keyColumn = new ArrayList<DataItem>();
            }
            return this.keyColumn;
        }

        public boolean isSetKeyColumn() {
            return ((this.keyColumn!= null)&&(!this.keyColumn.isEmpty()));
        }

        public void unsetKeyColumn() {
            this.keyColumn = null;
        }

    }

}
