
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Kpi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Kpi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="DisplayFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AssociatedMeasureGroupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Goal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Trend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrendGraphic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StatusGraphic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CurrentTimeMember" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParentKpiID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "Kpi", propOrder = {

})
public class Kpi {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Translations")
    protected Kpi.Translations translations;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "AssociatedMeasureGroupID")
    protected String associatedMeasureGroupID;
    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "Goal")
    protected String goal;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Trend")
    protected String trend;
    @XmlElement(name = "Weight")
    protected String weight;
    @XmlElement(name = "TrendGraphic")
    protected String trendGraphic;
    @XmlElement(name = "StatusGraphic")
    protected String statusGraphic;
    @XmlElement(name = "CurrentTimeMember")
    protected String currentTimeMember;
    @XmlElement(name = "ParentKpiID")
    protected String parentKpiID;
    @XmlElement(name = "Annotations")
    protected Kpi.Annotations annotations;

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
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link Kpi.Translations }
     *     
     */
    public Kpi.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Kpi.Translations }
     *     
     */
    public void setTranslations(Kpi.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
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
     * Gets the value of the associatedMeasureGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedMeasureGroupID() {
        return associatedMeasureGroupID;
    }

    /**
     * Sets the value of the associatedMeasureGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedMeasureGroupID(String value) {
        this.associatedMeasureGroupID = value;
    }

    public boolean isSetAssociatedMeasureGroupID() {
        return (this.associatedMeasureGroupID!= null);
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Gets the value of the goal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoal() {
        return goal;
    }

    /**
     * Sets the value of the goal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoal(String value) {
        this.goal = value;
    }

    public boolean isSetGoal() {
        return (this.goal!= null);
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    public boolean isSetStatus() {
        return (this.status!= null);
    }

    /**
     * Gets the value of the trend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrend() {
        return trend;
    }

    /**
     * Sets the value of the trend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrend(String value) {
        this.trend = value;
    }

    public boolean isSetTrend() {
        return (this.trend!= null);
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeight(String value) {
        this.weight = value;
    }

    public boolean isSetWeight() {
        return (this.weight!= null);
    }

    /**
     * Gets the value of the trendGraphic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrendGraphic() {
        return trendGraphic;
    }

    /**
     * Sets the value of the trendGraphic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrendGraphic(String value) {
        this.trendGraphic = value;
    }

    public boolean isSetTrendGraphic() {
        return (this.trendGraphic!= null);
    }

    /**
     * Gets the value of the statusGraphic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusGraphic() {
        return statusGraphic;
    }

    /**
     * Sets the value of the statusGraphic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusGraphic(String value) {
        this.statusGraphic = value;
    }

    public boolean isSetStatusGraphic() {
        return (this.statusGraphic!= null);
    }

    /**
     * Gets the value of the currentTimeMember property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentTimeMember() {
        return currentTimeMember;
    }

    /**
     * Sets the value of the currentTimeMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentTimeMember(String value) {
        this.currentTimeMember = value;
    }

    public boolean isSetCurrentTimeMember() {
        return (this.currentTimeMember!= null);
    }

    /**
     * Gets the value of the parentKpiID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentKpiID() {
        return parentKpiID;
    }

    /**
     * Sets the value of the parentKpiID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentKpiID(String value) {
        this.parentKpiID = value;
    }

    public boolean isSetParentKpiID() {
        return (this.parentKpiID!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link Kpi.Annotations }
     *     
     */
    public Kpi.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Kpi.Annotations }
     *     
     */
    public void setAnnotations(Kpi.Annotations value) {
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
