
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CubeDimensionPermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CubeDimensionPermission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Read" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Write" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
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
 *         &lt;element name="Annotations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "CubeDimensionPermission", propOrder = {

})
public class CubeDimensionPermission {

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Read")
    protected String read;
    @XmlElement(name = "Write")
    protected String write;
    @XmlElement(name = "AttributePermissions")
    protected CubeDimensionPermission.AttributePermissions attributePermissions;
    @XmlElement(name = "Annotations")
    protected CubeDimensionPermission.Annotations annotations;

    /**
     * Gets the value of the cubeDimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    /**
     * Sets the value of the cubeDimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the read property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRead() {
        return read;
    }

    /**
     * Sets the value of the read property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRead(String value) {
        this.read = value;
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

    /**
     * Gets the value of the attributePermissions property.
     * 
     * @return
     *     possible object is
     *     {@link CubeDimensionPermission.AttributePermissions }
     *     
     */
    public CubeDimensionPermission.AttributePermissions getAttributePermissions() {
        return attributePermissions;
    }

    /**
     * Sets the value of the attributePermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeDimensionPermission.AttributePermissions }
     *     
     */
    public void setAttributePermissions(CubeDimensionPermission.AttributePermissions value) {
        this.attributePermissions = value;
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link CubeDimensionPermission.Annotations }
     *     
     */
    public CubeDimensionPermission.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeDimensionPermission.Annotations }
     *     
     */
    public void setAnnotations(CubeDimensionPermission.Annotations value) {
        this.annotations = value;
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
     *         &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "annotation"
    })
    public static class Annotations {

        @XmlElement(name = "Annotation")
        protected List<Annotation> annotation;

        /**
         * Gets the value of the annotation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the annotation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAnnotation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Annotation }
         * 
         * 
         */
        public List<Annotation> getAnnotation() {
            if (annotation == null) {
                annotation = new ArrayList<Annotation>();
            }
            return this.annotation;
        }

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

    }

}
