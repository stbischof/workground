
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MiningModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MiningModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreatedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="LastSchemaUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="Algorithm"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Microsoft_Naive_Bayes"/&gt;
 *               &lt;enumeration value="Microsoft_Decision_Trees"/&gt;
 *               &lt;enumeration value="Microsoft_Clustering"/&gt;
 *               &lt;enumeration value="Microsoft_Neural_Network"/&gt;
 *               &lt;enumeration value="Microsoft_Logistic_Regression"/&gt;
 *               &lt;enumeration value="Microsoft_Linear_Regression"/&gt;
 *               &lt;enumeration value="Microsoft_Association_Rules"/&gt;
 *               &lt;enumeration value="Microsoft_Time_Series"/&gt;
 *               &lt;enumeration value="Microsoft_Sequence_Clustering"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LastProcessed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="AlgorithmParameters" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AlgorithmParameter" type="{urn:schemas-microsoft-com:xml-analysis}AlgorithmParameter" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AllowDrillThrough" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}AttributeTranslation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Columns"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}MiningModelColumn" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="State" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Processed"/&gt;
 *               &lt;enumeration value="Unprocessed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FoldingParameters" type="{urn:schemas-microsoft-com:xml-analysis}FoldingParameters" minOccurs="0"/&gt;
 *         &lt;element name="Filter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningModelPermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MiningModelPermission" type="{urn:schemas-microsoft-com:xml-analysis}MiningModelPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Collation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiningModel", propOrder = {

})
public class MiningModel {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "CreatedTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTimestamp;
    @XmlElement(name = "LastSchemaUpdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSchemaUpdate;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Annotations")
    protected MiningModel.Annotations annotations;
    @XmlElement(name = "Algorithm", required = true)
    protected String algorithm;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "AlgorithmParameters")
    protected MiningModel.AlgorithmParameters algorithmParameters;
    @XmlElement(name = "AllowDrillThrough")
    protected Boolean allowDrillThrough;
    @XmlElement(name = "Translations")
    protected MiningModel.Translations translations;
    @XmlElement(name = "Columns", required = true)
    protected MiningModel.Columns columns;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "FoldingParameters")
    protected FoldingParameters foldingParameters;
    @XmlElement(name = "Filter")
    protected String filter;
    @XmlElement(name = "MiningModelPermissions")
    protected MiningModel.MiningModelPermissions miningModelPermissions;
    @XmlElement(name = "Language")
    protected String language;
    @XmlElement(name = "Collation")
    protected String collation;

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
     * Gets the value of the createdTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the value of the createdTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTimestamp(XMLGregorianCalendar value) {
        this.createdTimestamp = value;
    }

    public boolean isSetCreatedTimestamp() {
        return (this.createdTimestamp!= null);
    }

    /**
     * Gets the value of the lastSchemaUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    /**
     * Sets the value of the lastSchemaUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSchemaUpdate(XMLGregorianCalendar value) {
        this.lastSchemaUpdate = value;
    }

    public boolean isSetLastSchemaUpdate() {
        return (this.lastSchemaUpdate!= null);
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
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link MiningModel.Annotations }
     *     
     */
    public MiningModel.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningModel.Annotations }
     *     
     */
    public void setAnnotations(MiningModel.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
    }

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    public boolean isSetAlgorithm() {
        return (this.algorithm!= null);
    }

    /**
     * Gets the value of the lastProcessed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    /**
     * Sets the value of the lastProcessed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    public boolean isSetLastProcessed() {
        return (this.lastProcessed!= null);
    }

    /**
     * Gets the value of the algorithmParameters property.
     * 
     * @return
     *     possible object is
     *     {@link MiningModel.AlgorithmParameters }
     *     
     */
    public MiningModel.AlgorithmParameters getAlgorithmParameters() {
        return algorithmParameters;
    }

    /**
     * Sets the value of the algorithmParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningModel.AlgorithmParameters }
     *     
     */
    public void setAlgorithmParameters(MiningModel.AlgorithmParameters value) {
        this.algorithmParameters = value;
    }

    public boolean isSetAlgorithmParameters() {
        return (this.algorithmParameters!= null);
    }

    /**
     * Gets the value of the allowDrillThrough property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowDrillThrough() {
        return allowDrillThrough;
    }

    /**
     * Sets the value of the allowDrillThrough property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowDrillThrough(Boolean value) {
        this.allowDrillThrough = value;
    }

    public boolean isSetAllowDrillThrough() {
        return (this.allowDrillThrough!= null);
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link MiningModel.Translations }
     *     
     */
    public MiningModel.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningModel.Translations }
     *     
     */
    public void setTranslations(MiningModel.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link MiningModel.Columns }
     *     
     */
    public MiningModel.Columns getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningModel.Columns }
     *     
     */
    public void setColumns(MiningModel.Columns value) {
        this.columns = value;
    }

    public boolean isSetColumns() {
        return (this.columns!= null);
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    public boolean isSetState() {
        return (this.state!= null);
    }

    /**
     * Gets the value of the foldingParameters property.
     * 
     * @return
     *     possible object is
     *     {@link FoldingParameters }
     *     
     */
    public FoldingParameters getFoldingParameters() {
        return foldingParameters;
    }

    /**
     * Sets the value of the foldingParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link FoldingParameters }
     *     
     */
    public void setFoldingParameters(FoldingParameters value) {
        this.foldingParameters = value;
    }

    public boolean isSetFoldingParameters() {
        return (this.foldingParameters!= null);
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilter(String value) {
        this.filter = value;
    }

    public boolean isSetFilter() {
        return (this.filter!= null);
    }

    /**
     * Gets the value of the miningModelPermissions property.
     * 
     * @return
     *     possible object is
     *     {@link MiningModel.MiningModelPermissions }
     *     
     */
    public MiningModel.MiningModelPermissions getMiningModelPermissions() {
        return miningModelPermissions;
    }

    /**
     * Sets the value of the miningModelPermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningModel.MiningModelPermissions }
     *     
     */
    public void setMiningModelPermissions(MiningModel.MiningModelPermissions value) {
        this.miningModelPermissions = value;
    }

    public boolean isSetMiningModelPermissions() {
        return (this.miningModelPermissions!= null);
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    public boolean isSetLanguage() {
        return (this.language!= null);
    }

    /**
     * Gets the value of the collation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollation() {
        return collation;
    }

    /**
     * Sets the value of the collation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollation(String value) {
        this.collation = value;
    }

    public boolean isSetCollation() {
        return (this.collation!= null);
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
     *         &lt;element name="AlgorithmParameter" type="{urn:schemas-microsoft-com:xml-analysis}AlgorithmParameter" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "algorithmParameter"
    })
    public static class AlgorithmParameters {

        @XmlElement(name = "AlgorithmParameter")
        protected List<AlgorithmParameter> algorithmParameter;

        /**
         * Gets the value of the algorithmParameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the algorithmParameter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlgorithmParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AlgorithmParameter }
         * 
         * 
         */
        public List<AlgorithmParameter> getAlgorithmParameter() {
            if (algorithmParameter == null) {
                algorithmParameter = new ArrayList<AlgorithmParameter>();
            }
            return this.algorithmParameter;
        }

        public boolean isSetAlgorithmParameter() {
            return ((this.algorithmParameter!= null)&&(!this.algorithmParameter.isEmpty()));
        }

        public void unsetAlgorithmParameter() {
            this.algorithmParameter = null;
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
     *         &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}MiningModelColumn" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "column"
    })
    public static class Columns {

        @XmlElement(name = "Column")
        protected List<MiningModelColumn> column;

        /**
         * Gets the value of the column property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the column property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MiningModelColumn }
         * 
         * 
         */
        public List<MiningModelColumn> getColumn() {
            if (column == null) {
                column = new ArrayList<MiningModelColumn>();
            }
            return this.column;
        }

        public boolean isSetColumn() {
            return ((this.column!= null)&&(!this.column.isEmpty()));
        }

        public void unsetColumn() {
            this.column = null;
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
     *         &lt;element name="MiningModelPermission" type="{urn:schemas-microsoft-com:xml-analysis}MiningModelPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "miningModelPermission"
    })
    public static class MiningModelPermissions {

        @XmlElement(name = "MiningModelPermission")
        protected List<MiningModelPermission> miningModelPermission;

        /**
         * Gets the value of the miningModelPermission property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the miningModelPermission property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMiningModelPermission().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MiningModelPermission }
         * 
         * 
         */
        public List<MiningModelPermission> getMiningModelPermission() {
            if (miningModelPermission == null) {
                miningModelPermission = new ArrayList<MiningModelPermission>();
            }
            return this.miningModelPermission;
        }

        public boolean isSetMiningModelPermission() {
            return ((this.miningModelPermission!= null)&&(!this.miningModelPermission.isEmpty()));
        }

        public void unsetMiningModelPermission() {
            this.miningModelPermission = null;
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
     *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}AttributeTranslation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<AttributeTranslation> translation;

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
         * {@link AttributeTranslation }
         * 
         * 
         */
        public List<AttributeTranslation> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<AttributeTranslation>();
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
