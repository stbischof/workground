
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregationDesignDimension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregationDesignDimension"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Attributes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}AggregationDesignAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "AggregationDesignDimension", propOrder = {

})
public class AggregationDesignDimension {

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Attributes")
    protected AggregationDesignDimension.Attributes attributes;
    @XmlElement(name = "Annotations")
    protected AggregationDesignDimension.Annotations annotations;

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

    public boolean isSetCubeDimensionID() {
        return (this.cubeDimensionID!= null);
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link AggregationDesignDimension.Attributes }
     *     
     */
    public AggregationDesignDimension.Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationDesignDimension.Attributes }
     *     
     */
    public void setAttributes(AggregationDesignDimension.Attributes value) {
        this.attributes = value;
    }

    public boolean isSetAttributes() {
        return (this.attributes!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link AggregationDesignDimension.Annotations }
     *     
     */
    public AggregationDesignDimension.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationDesignDimension.Annotations }
     *     
     */
    public void setAnnotations(AggregationDesignDimension.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
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

        public boolean isSetAnnotation() {
            return ((this.annotation!= null)&&(!this.annotation.isEmpty()));
        }

        public void unsetAnnotation() {
            this.annotation = null;
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
     *         &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}AggregationDesignAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<AggregationDesignAttribute> attribute;

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
         * {@link AggregationDesignAttribute }
         * 
         * 
         */
        public List<AggregationDesignAttribute> getAttribute() {
            if (attribute == null) {
                attribute = new ArrayList<AggregationDesignAttribute>();
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
