
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.engine300.HierarchyVisualizationProperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Hierarchy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Hierarchy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}ProcessingState" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}StructureType" minOccurs="0"/&gt;
 *         &lt;element name="DisplayFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="AllMemberName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AllMemberTranslations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AllMemberTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MemberNamesUnique" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2003/engine/2}MemberKeysUnique" minOccurs="0"/&gt;
 *         &lt;element name="AllowDuplicateNames" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Levels"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Level" type="{urn:schemas-microsoft-com:xml-analysis}Level" maxOccurs="unbounded"/&gt;
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
 *         &lt;element name="VisualizationProperties" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300}HierarchyVisualizationProperties" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hierarchy", propOrder = {

})
public class Hierarchy {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ProcessingState", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String processingState;
    @XmlElement(name = "StructureType", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String structureType;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "Translations")
    protected Hierarchy.Translations translations;
    @XmlElement(name = "AllMemberName")
    protected String allMemberName;
    @XmlElement(name = "AllMemberTranslations")
    protected Hierarchy.AllMemberTranslations allMemberTranslations;
    @XmlElement(name = "MemberNamesUnique")
    protected Boolean memberNamesUnique;
    @XmlElement(name = "MemberKeysUnique", namespace = "http://schemas.microsoft.com/analysisservices/2003/engine/2")
    protected String memberKeysUnique;
    @XmlElement(name = "AllowDuplicateNames")
    protected Boolean allowDuplicateNames;
    @XmlElement(name = "Levels", required = true)
    protected Hierarchy.Levels levels;
    @XmlElement(name = "Annotations")
    protected Hierarchy.Annotations annotations;
    @XmlElement(name = "VisualizationProperties")
    protected HierarchyVisualizationProperties visualizationProperties;

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
     * Gets the value of the processingState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingState() {
        return processingState;
    }

    /**
     * Sets the value of the processingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingState(String value) {
        this.processingState = value;
    }

    public boolean isSetProcessingState() {
        return (this.processingState!= null);
    }

    /**
     * Gets the value of the structureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStructureType() {
        return structureType;
    }

    /**
     * Sets the value of the structureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStructureType(String value) {
        this.structureType = value;
    }

    public boolean isSetStructureType() {
        return (this.structureType!= null);
    }

    /**
     * Gets the value of the displayFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayFolder() {
        return displayFolder;
    }

    /**
     * Sets the value of the displayFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public boolean isSetDisplayFolder() {
        return (this.displayFolder!= null);
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link Hierarchy.Translations }
     *     
     */
    public Hierarchy.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hierarchy.Translations }
     *     
     */
    public void setTranslations(Hierarchy.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
    }

    /**
     * Gets the value of the allMemberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllMemberName() {
        return allMemberName;
    }

    /**
     * Sets the value of the allMemberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllMemberName(String value) {
        this.allMemberName = value;
    }

    public boolean isSetAllMemberName() {
        return (this.allMemberName!= null);
    }

    /**
     * Gets the value of the allMemberTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link Hierarchy.AllMemberTranslations }
     *     
     */
    public Hierarchy.AllMemberTranslations getAllMemberTranslations() {
        return allMemberTranslations;
    }

    /**
     * Sets the value of the allMemberTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hierarchy.AllMemberTranslations }
     *     
     */
    public void setAllMemberTranslations(Hierarchy.AllMemberTranslations value) {
        this.allMemberTranslations = value;
    }

    public boolean isSetAllMemberTranslations() {
        return (this.allMemberTranslations!= null);
    }

    /**
     * Gets the value of the memberNamesUnique property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMemberNamesUnique() {
        return memberNamesUnique;
    }

    /**
     * Sets the value of the memberNamesUnique property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMemberNamesUnique(Boolean value) {
        this.memberNamesUnique = value;
    }

    public boolean isSetMemberNamesUnique() {
        return (this.memberNamesUnique!= null);
    }

    /**
     * Gets the value of the memberKeysUnique property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberKeysUnique() {
        return memberKeysUnique;
    }

    /**
     * Sets the value of the memberKeysUnique property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberKeysUnique(String value) {
        this.memberKeysUnique = value;
    }

    public boolean isSetMemberKeysUnique() {
        return (this.memberKeysUnique!= null);
    }

    /**
     * Gets the value of the allowDuplicateNames property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowDuplicateNames() {
        return allowDuplicateNames;
    }

    /**
     * Sets the value of the allowDuplicateNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowDuplicateNames(Boolean value) {
        this.allowDuplicateNames = value;
    }

    public boolean isSetAllowDuplicateNames() {
        return (this.allowDuplicateNames!= null);
    }

    /**
     * Gets the value of the levels property.
     * 
     * @return
     *     possible object is
     *     {@link Hierarchy.Levels }
     *     
     */
    public Hierarchy.Levels getLevels() {
        return levels;
    }

    /**
     * Sets the value of the levels property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hierarchy.Levels }
     *     
     */
    public void setLevels(Hierarchy.Levels value) {
        this.levels = value;
    }

    public boolean isSetLevels() {
        return (this.levels!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link Hierarchy.Annotations }
     *     
     */
    public Hierarchy.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hierarchy.Annotations }
     *     
     */
    public void setAnnotations(Hierarchy.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
    }

    /**
     * Gets the value of the visualizationProperties property.
     * 
     * @return
     *     possible object is
     *     {@link HierarchyVisualizationProperties }
     *     
     */
    public HierarchyVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    /**
     * Sets the value of the visualizationProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link HierarchyVisualizationProperties }
     *     
     */
    public void setVisualizationProperties(HierarchyVisualizationProperties value) {
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
     *         &lt;element name="AllMemberTranslation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "allMemberTranslation"
    })
    public static class AllMemberTranslations {

        @XmlElement(name = "AllMemberTranslation")
        protected List<Translation> allMemberTranslation;

        /**
         * Gets the value of the allMemberTranslation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the allMemberTranslation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAllMemberTranslation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Translation }
         * 
         * 
         */
        public List<Translation> getAllMemberTranslation() {
            if (allMemberTranslation == null) {
                allMemberTranslation = new ArrayList<Translation>();
            }
            return this.allMemberTranslation;
        }

        public boolean isSetAllMemberTranslation() {
            return ((this.allMemberTranslation!= null)&&(!this.allMemberTranslation.isEmpty()));
        }

        public void unsetAllMemberTranslation() {
            this.allMemberTranslation = null;
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
     *         &lt;element name="Level" type="{urn:schemas-microsoft-com:xml-analysis}Level" maxOccurs="unbounded"/&gt;
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
        "level"
    })
    public static class Levels {

        @XmlElement(name = "Level", required = true)
        protected List<Level> level;

        /**
         * Gets the value of the level property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the level property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLevel().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Level }
         * 
         * 
         */
        public List<Level> getLevel() {
            if (level == null) {
                level = new ArrayList<Level>();
            }
            return this.level;
        }

        public boolean isSetLevel() {
            return ((this.level!= null)&&(!this.level.isEmpty()));
        }

        public void unsetLevel() {
            this.level = null;
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
