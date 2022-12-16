
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Where_Attribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Where_Attribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AttributeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Keys" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "Where_Attribute", propOrder = {

})
public class WhereAttribute {

    @XmlElement(name = "AttributeName", required = true)
    protected String attributeName;
    @XmlElement(name = "Keys")
    protected WhereAttribute.Keys keys;

    /**
     * Gets the value of the attributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    public boolean isSetAttributeName() {
        return (this.attributeName!= null);
    }

    /**
     * Gets the value of the keys property.
     * 
     * @return
     *     possible object is
     *     {@link WhereAttribute.Keys }
     *     
     */
    public WhereAttribute.Keys getKeys() {
        return keys;
    }

    /**
     * Sets the value of the keys property.
     * 
     * @param value
     *     allowed object is
     *     {@link WhereAttribute.Keys }
     *     
     */
    public void setKeys(WhereAttribute.Keys value) {
        this.keys = value;
    }

    public boolean isSetKeys() {
        return (this.keys!= null);
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
     *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "key"
    })
    public static class Keys {

        @XmlElement(name = "Key")
        protected List<java.lang.Object> key;

        /**
         * Gets the value of the key property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the key property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKey().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link java.lang.Object }
         * 
         * 
         */
        public List<java.lang.Object> getKey() {
            if (key == null) {
                key = new ArrayList<java.lang.Object>();
            }
            return this.key;
        }

        public boolean isSetKey() {
            return ((this.key!= null)&&(!this.key.isEmpty()));
        }

        public void unsetKey() {
            this.key = null;
        }

    }

}
