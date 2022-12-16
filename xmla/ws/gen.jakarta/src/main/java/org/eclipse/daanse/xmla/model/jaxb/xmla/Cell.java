
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Cell complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cell"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="CellOrdinal" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cell", propOrder = {

})
public class Cell {

    @XmlElement(name = "Value", required = true)
    protected java.lang.Object value;
    @XmlAttribute(name = "CellOrdinal")
    protected Long cellOrdinal;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Object }
     *     
     */
    public java.lang.Object getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Object }
     *     
     */
    public void setValue(java.lang.Object value) {
        this.value = value;
    }

    /**
     * Gets the value of the cellOrdinal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCellOrdinal() {
        return cellOrdinal;
    }

    /**
     * Sets the value of the cellOrdinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCellOrdinal(Long value) {
        this.cellOrdinal = value;
    }

}
