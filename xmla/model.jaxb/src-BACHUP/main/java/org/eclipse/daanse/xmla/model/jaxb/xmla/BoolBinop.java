
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bool_binop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bool_binop"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ColumnID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bool_binop", propOrder = {

})
public class BoolBinop {

    @XmlElement(name = "ColumnID", required = true)
    protected String columnID;
    @XmlElement(name = "Value", required = true)
    protected String value;

    /**
     * Gets the value of the columnID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnID() {
        return columnID;
    }

    /**
     * Sets the value of the columnID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnID(String value) {
        this.columnID = value;
    }

    public boolean isSetColumnID() {
        return (this.columnID!= null);
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

}
