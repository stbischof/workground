
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Update complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Update"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}Object"/&gt;
 *         &lt;element name="Attributes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}Attribute_InsertUpdate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MoveWithDescendants" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MoveToRoot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
@XmlType(name = "Update", propOrder = {

})
public class Update {

    @XmlElement(name = "Object", required = true)
    protected Object object;
    @XmlElement(name = "Attributes")
    protected Update.Attributes attributes;
    @XmlElement(name = "MoveWithDescendants")
    protected Boolean moveWithDescendants;
    @XmlElement(name = "MoveToRoot")
    protected Boolean moveToRoot;
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

    public boolean isSetObject() {
        return (this.object!= null);
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link Update.Attributes }
     *     
     */
    public Update.Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Update.Attributes }
     *     
     */
    public void setAttributes(Update.Attributes value) {
        this.attributes = value;
    }

    public boolean isSetAttributes() {
        return (this.attributes!= null);
    }

    /**
     * Gets the value of the moveWithDescendants property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMoveWithDescendants() {
        return moveWithDescendants;
    }

    /**
     * Sets the value of the moveWithDescendants property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMoveWithDescendants(Boolean value) {
        this.moveWithDescendants = value;
    }

    public boolean isSetMoveWithDescendants() {
        return (this.moveWithDescendants!= null);
    }

    /**
     * Gets the value of the moveToRoot property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMoveToRoot() {
        return moveToRoot;
    }

    /**
     * Sets the value of the moveToRoot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMoveToRoot(Boolean value) {
        this.moveToRoot = value;
    }

    public boolean isSetMoveToRoot() {
        return (this.moveToRoot!= null);
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

    public boolean isSetWhere() {
        return (this.where!= null);
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
     *         &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}Attribute_InsertUpdate" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "attribute"
    })
    public static class Attributes {

        @XmlElement(name = "Attribute")
        protected List<AttributeInsertUpdate> attribute;

        /**
         * Gets the value of the attribute property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the attribute property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAttribute().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributeInsertUpdate }
         * 
         * 
         */
        public List<AttributeInsertUpdate> getAttribute() {
            if (attribute == null) {
                attribute = new ArrayList<AttributeInsertUpdate>();
            }
            return this.attribute;
        }

        public boolean isSetAttribute() {
            return ((this.attribute!= null)&&(!this.attribute.isEmpty()));
        }

        public void unsetAttribute() {
            this.attribute = null;
        }

    }

}
