
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DimensionPermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionPermission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Permission"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AttributePermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AttributePermission" type="{urn:schemas-microsoft-com:xml-analysis}AttributePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Write" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}AllowedRowsExpression" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionPermission", propOrder = {
    "attributePermissions",
    "write",
    "allowedRowsExpression"
})
public class DimensionPermission
    extends Permission
{

    @XmlElement(name = "AttributePermissions")
    protected DimensionPermission.AttributePermissions attributePermissions;
    @XmlElement(name = "Write")
    protected String write;
    @XmlElement(name = "AllowedRowsExpression", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300")
    protected String allowedRowsExpression;

    /**
     * Gets the value of the attributePermissions property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionPermission.AttributePermissions }
     *     
     */
    public DimensionPermission.AttributePermissions getAttributePermissions() {
        return attributePermissions;
    }

    /**
     * Sets the value of the attributePermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionPermission.AttributePermissions }
     *     
     */
    public void setAttributePermissions(DimensionPermission.AttributePermissions value) {
        this.attributePermissions = value;
    }

    public boolean isSetAttributePermissions() {
        return (this.attributePermissions!= null);
    }

    /**
     * Gets the value of the write property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrite() {
        return write;
    }

    /**
     * Sets the value of the write property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrite(String value) {
        this.write = value;
    }

    public boolean isSetWrite() {
        return (this.write!= null);
    }

    /**
     * Gets the value of the allowedRowsExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowedRowsExpression() {
        return allowedRowsExpression;
    }

    /**
     * Sets the value of the allowedRowsExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowedRowsExpression(String value) {
        this.allowedRowsExpression = value;
    }

    public boolean isSetAllowedRowsExpression() {
        return (this.allowedRowsExpression!= null);
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
     *         &lt;element name="AttributePermission" type="{urn:schemas-microsoft-com:xml-analysis}AttributePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "attributePermission"
    })
    public static class AttributePermissions {

        @XmlElement(name = "AttributePermission")
        protected List<AttributePermission> attributePermission;

        /**
         * Gets the value of the attributePermission property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the attributePermission property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAttributePermission().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributePermission }
         * 
         * 
         */
        public List<AttributePermission> getAttributePermission() {
            if (attributePermission == null) {
                attributePermission = new ArrayList<AttributePermission>();
            }
            return this.attributePermission;
        }

        public boolean isSetAttributePermission() {
            return ((this.attributePermission!= null)&&(!this.attributePermission.isEmpty()));
        }

        public void unsetAttributePermission() {
            this.attributePermission = null;
        }

    }

}
