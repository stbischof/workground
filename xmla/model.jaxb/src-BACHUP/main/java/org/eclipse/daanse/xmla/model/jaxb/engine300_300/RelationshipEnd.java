
package org.eclipse.daanse.xmla.model.jaxb.engine300_300;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.engine300.RelationshipEndVisualizationProperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RelationshipEnd complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelationshipEnd"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Role" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Multiplicity"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="One"/&gt;
 *               &lt;enumeration value="Many"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Attributes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}RelationshipEndTranslation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="VisualizationProperties" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300}RelationshipEndVisualizationProperties" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipEnd", propOrder = {
    "role",
    "multiplicity",
    "dimensionID",
    "attributes",
    "translations",
    "visualizationProperties"
})
public class RelationshipEnd
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Role", required = true)
    protected String role;
    @XmlElement(name = "Multiplicity", required = true)
    protected String multiplicity;
    @XmlElement(name = "DimensionID", required = true)
    protected String dimensionID;
    @XmlElement(name = "Attributes")
    protected RelationshipEnd.Attributes attributes;
    @XmlElement(name = "Translations")
    protected RelationshipEnd.Translations translations;
    @XmlElement(name = "VisualizationProperties")
    protected RelationshipEndVisualizationProperties visualizationProperties;

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    public boolean isSetRole() {
        return (this.role!= null);
    }

    /**
     * Gets the value of the multiplicity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultiplicity() {
        return multiplicity;
    }

    /**
     * Sets the value of the multiplicity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultiplicity(String value) {
        this.multiplicity = value;
    }

    public boolean isSetMultiplicity() {
        return (this.multiplicity!= null);
    }

    /**
     * Gets the value of the dimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensionID() {
        return dimensionID;
    }

    /**
     * Sets the value of the dimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public boolean isSetDimensionID() {
        return (this.dimensionID!= null);
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipEnd.Attributes }
     *     
     */
    public RelationshipEnd.Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipEnd.Attributes }
     *     
     */
    public void setAttributes(RelationshipEnd.Attributes value) {
        this.attributes = value;
    }

    public boolean isSetAttributes() {
        return (this.attributes!= null);
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipEnd.Translations }
     *     
     */
    public RelationshipEnd.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipEnd.Translations }
     *     
     */
    public void setTranslations(RelationshipEnd.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
    }

    /**
     * Gets the value of the visualizationProperties property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipEndVisualizationProperties }
     *     
     */
    public RelationshipEndVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    /**
     * Sets the value of the visualizationProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipEndVisualizationProperties }
     *     
     */
    public void setVisualizationProperties(RelationshipEndVisualizationProperties value) {
        this.visualizationProperties = value;
    }

    public boolean isSetVisualizationProperties() {
        return (this.visualizationProperties!= null);
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
     *         &lt;element name="Attribute" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
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
    public static class Attributes
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Attribute")
        protected List<RelationshipEnd.Attributes.Attribute> attribute;

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
         * {@link RelationshipEnd.Attributes.Attribute }
         * 
         * 
         */
        public List<RelationshipEnd.Attributes.Attribute> getAttribute() {
            if (attribute == null) {
                attribute = new ArrayList<RelationshipEnd.Attributes.Attribute>();
            }
            return this.attribute;
        }

        public boolean isSetAttribute() {
            return ((this.attribute!= null)&&(!this.attribute.isEmpty()));
        }

        public void unsetAttribute() {
            this.attribute = null;
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
         *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "attributeID"
        })
        public static class Attribute
            implements Serializable
        {

            private final static long serialVersionUID = 1L;
            @XmlElement(name = "AttributeID", required = true)
            protected String attributeID;

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
     *         &lt;element name="Translation" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}RelationshipEndTranslation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "translation"
    })
    public static class Translations
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Translation")
        protected List<RelationshipEndTranslation> translation;

        /**
         * Gets the value of the translation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the translation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTranslation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RelationshipEndTranslation }
         * 
         * 
         */
        public List<RelationshipEndTranslation> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<RelationshipEndTranslation>();
            }
            return this.translation;
        }

        public boolean isSetTranslation() {
            return ((this.translation!= null)&&(!this.translation.isEmpty()));
        }

        public void unsetTranslation() {
            this.translation = null;
        }

    }

}
