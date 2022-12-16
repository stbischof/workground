
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CubeDimension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CubeDimension"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AllMemberAggregationUsage" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Full"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Unrestricted"/&gt;
 *               &lt;enumeration value="Default"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="HierarchyUniqueNameStyle" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="IncludeDimensionName"/&gt;
 *               &lt;enumeration value="ExcludeDimensionName"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MemberUniqueNameStyle" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Native"/&gt;
 *               &lt;enumeration value="NamePath"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Attributes" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}CubeAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Hierarchies" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Hierarchy" type="{urn:schemas-microsoft-com:xml-analysis}CubeHierarchy" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "CubeDimension", propOrder = {

})
public class CubeDimension {

    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Translations")
    protected CubeDimension.Translations translations;
    @XmlElement(name = "DimensionID", required = true)
    protected String dimensionID;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "AllMemberAggregationUsage")
    protected String allMemberAggregationUsage;
    @XmlElement(name = "HierarchyUniqueNameStyle")
    protected String hierarchyUniqueNameStyle;
    @XmlElement(name = "MemberUniqueNameStyle")
    protected String memberUniqueNameStyle;
    @XmlElement(name = "Attributes")
    protected CubeDimension.Attributes attributes;
    @XmlElement(name = "Hierarchies")
    protected CubeDimension.Hierarchies hierarchies;
    @XmlElement(name = "Annotations")
    protected CubeDimension.Annotations annotations;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    public boolean isSetID() {
        return (this.id!= null);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
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

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link CubeDimension.Translations }
     *     
     */
    public CubeDimension.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeDimension.Translations }
     *     
     */
    public void setTranslations(CubeDimension.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
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
     * Gets the value of the visible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public boolean isSetVisible() {
        return (this.visible!= null);
    }

    /**
     * Gets the value of the allMemberAggregationUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllMemberAggregationUsage() {
        return allMemberAggregationUsage;
    }

    /**
     * Sets the value of the allMemberAggregationUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllMemberAggregationUsage(String value) {
        this.allMemberAggregationUsage = value;
    }

    public boolean isSetAllMemberAggregationUsage() {
        return (this.allMemberAggregationUsage!= null);
    }

    /**
     * Gets the value of the hierarchyUniqueNameStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarchyUniqueNameStyle() {
        return hierarchyUniqueNameStyle;
    }

    /**
     * Sets the value of the hierarchyUniqueNameStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarchyUniqueNameStyle(String value) {
        this.hierarchyUniqueNameStyle = value;
    }

    public boolean isSetHierarchyUniqueNameStyle() {
        return (this.hierarchyUniqueNameStyle!= null);
    }

    /**
     * Gets the value of the memberUniqueNameStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberUniqueNameStyle() {
        return memberUniqueNameStyle;
    }

    /**
     * Sets the value of the memberUniqueNameStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberUniqueNameStyle(String value) {
        this.memberUniqueNameStyle = value;
    }

    public boolean isSetMemberUniqueNameStyle() {
        return (this.memberUniqueNameStyle!= null);
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link CubeDimension.Attributes }
     *     
     */
    public CubeDimension.Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeDimension.Attributes }
     *     
     */
    public void setAttributes(CubeDimension.Attributes value) {
        this.attributes = value;
    }

    public boolean isSetAttributes() {
        return (this.attributes!= null);
    }

    /**
     * Gets the value of the hierarchies property.
     * 
     * @return
     *     possible object is
     *     {@link CubeDimension.Hierarchies }
     *     
     */
    public CubeDimension.Hierarchies getHierarchies() {
        return hierarchies;
    }

    /**
     * Sets the value of the hierarchies property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeDimension.Hierarchies }
     *     
     */
    public void setHierarchies(CubeDimension.Hierarchies value) {
        this.hierarchies = value;
    }

    public boolean isSetHierarchies() {
        return (this.hierarchies!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link CubeDimension.Annotations }
     *     
     */
    public CubeDimension.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeDimension.Annotations }
     *     
     */
    public void setAnnotations(CubeDimension.Annotations value) {
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
     *         &lt;element name="Attribute" type="{urn:schemas-microsoft-com:xml-analysis}CubeAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<CubeAttribute> attribute;

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
         * {@link CubeAttribute }
         * 
         * 
         */
        public List<CubeAttribute> getAttribute() {
            if (attribute == null) {
                attribute = new ArrayList<CubeAttribute>();
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
     *         &lt;element name="Hierarchy" type="{urn:schemas-microsoft-com:xml-analysis}CubeHierarchy" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "hierarchy"
    })
    public static class Hierarchies {

        @XmlElement(name = "Hierarchy")
        protected List<CubeHierarchy> hierarchy;

        /**
         * Gets the value of the hierarchy property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the hierarchy property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHierarchy().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CubeHierarchy }
         * 
         * 
         */
        public List<CubeHierarchy> getHierarchy() {
            if (hierarchy == null) {
                hierarchy = new ArrayList<CubeHierarchy>();
            }
            return this.hierarchy;
        }

        public boolean isSetHierarchy() {
            return ((this.hierarchy!= null)&&(!this.hierarchy.isEmpty()));
        }

        public void unsetHierarchy() {
            this.hierarchy = null;
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
     *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    public static class Translations {

        @XmlElement(name = "Translation")
        protected List<Translation> translation;

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
         * {@link Translation }
         * 
         * 
         */
        public List<Translation> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<Translation>();
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
