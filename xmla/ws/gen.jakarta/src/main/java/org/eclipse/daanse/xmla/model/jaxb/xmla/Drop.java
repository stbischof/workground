
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Drop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Drop"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}Object"/&gt;
 *         &lt;element name="DeleteWithDescendants" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Where" type="{urn:schemas-microsoft-com:xml-analysis}Where"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Drop", propOrder = {

})
public class Drop {

    @XmlElement(name = "Object", required = true)
    protected Object object;
    @XmlElement(name = "DeleteWithDescendants")
    protected Boolean deleteWithDescendants;
    @XmlElement(name = "Where", required = true)
    protected Where where;

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setObject(Object value) {
        this.object = value;
    }

    /**
     * Gets the value of the deleteWithDescendants property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeleteWithDescendants() {
        return deleteWithDescendants;
    }

    /**
     * Sets the value of the deleteWithDescendants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeleteWithDescendants(Boolean value) {
        this.deleteWithDescendants = value;
    }

    /**
     * Gets the value of the where property.
     * 
     * @return
     *     possible object is
     *     {@link Where }
     *     
     */
    public Where getWhere() {
        return where;
    }

    /**
     * Sets the value of the where property.
     * 
     * @param value
     *     allowed object is
     *     {@link Where }
     *     
     */
    public void setWhere(Where value) {
        this.where = value;
    }

}
