
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrillThroughAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrillThroughAction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Action"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Caption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CaptionIsMdx" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
 *         &lt;element name="TargetType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Cube"/&gt;
 *               &lt;enumeration value="Cells"/&gt;
 *               &lt;enumeration value="Set"/&gt;
 *               &lt;enumeration value="Hierarchy"/&gt;
 *               &lt;enumeration value="Level"/&gt;
 *               &lt;enumeration value="DimensionMembers"/&gt;
 *               &lt;enumeration value="HierarchyMembers"/&gt;
 *               &lt;enumeration value="LevelMembers"/&gt;
 *               &lt;enumeration value="AttributeMembers"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Target" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Condition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Type"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Url"/&gt;
 *               &lt;enumeration value="Html"/&gt;
 *               &lt;enumeration value="Statement"/&gt;
 *               &lt;enumeration value="DrillThrough"/&gt;
 *               &lt;enumeration value="Dataset"/&gt;
 *               &lt;enumeration value="Rowset"/&gt;
 *               &lt;enumeration value="CommandLine"/&gt;
 *               &lt;enumeration value="Proprietary"/&gt;
 *               &lt;enumeration value="Report"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Invocation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Interactive"/&gt;
 *               &lt;enumeration value="OnOpen"/&gt;
 *               &lt;enumeration value="Batch"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="Default" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Columns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}Binding" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MaximumRows" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillThroughAction", propOrder = {
    "name",
    "id",
    "caption",
    "captionIsMdx",
    "translations",
    "targetType",
    "target",
    "condition",
    "type",
    "invocation",
    "application",
    "description",
    "annotations",
    "_default",
    "columns",
    "maximumRows"
})
public class DrillThroughAction
    extends Action
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "Caption")
    protected String caption;
    @XmlElement(name = "CaptionIsMdx")
    protected Boolean captionIsMdx;
    @XmlElement(name = "Translations")
    protected DrillThroughAction.Translations translations;
    @XmlElement(name = "TargetType", required = true)
    protected String targetType;
    @XmlElement(name = "Target")
    protected String target;
    @XmlElement(name = "Condition")
    protected String condition;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Invocation")
    protected String invocation;
    @XmlElement(name = "Application")
    protected String application;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Annotations")
    protected DrillThroughAction.Annotations annotations;
    @XmlElement(name = "Default")
    protected Boolean _default;
    @XmlElement(name = "Columns")
    protected DrillThroughAction.Columns columns;
    @XmlElement(name = "MaximumRows")
    protected BigInteger maximumRows;

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

    /**
     * Gets the value of the caption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the value of the caption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaption(String value) {
        this.caption = value;
    }

    /**
     * Gets the value of the captionIsMdx property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCaptionIsMdx() {
        return captionIsMdx;
    }

    /**
     * Sets the value of the captionIsMdx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCaptionIsMdx(Boolean value) {
        this.captionIsMdx = value;
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link DrillThroughAction.Translations }
     *     
     */
    public DrillThroughAction.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrillThroughAction.Translations }
     *     
     */
    public void setTranslations(DrillThroughAction.Translations value) {
        this.translations = value;
    }

    /**
     * Gets the value of the targetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * Sets the value of the targetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetType(String value) {
        this.targetType = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondition(String value) {
        this.condition = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the invocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvocation() {
        return invocation;
    }

    /**
     * Sets the value of the invocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvocation(String value) {
        this.invocation = value;
    }

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
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
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link DrillThroughAction.Annotations }
     *     
     */
    public DrillThroughAction.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrillThroughAction.Annotations }
     *     
     */
    public void setAnnotations(DrillThroughAction.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefault(Boolean value) {
        this._default = value;
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link DrillThroughAction.Columns }
     *     
     */
    public DrillThroughAction.Columns getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrillThroughAction.Columns }
     *     
     */
    public void setColumns(DrillThroughAction.Columns value) {
        this.columns = value;
    }

    /**
     * Gets the value of the maximumRows property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumRows() {
        return maximumRows;
    }

    /**
     * Sets the value of the maximumRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumRows(BigInteger value) {
        this.maximumRows = value;
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
     *         &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}Binding" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<Binding> column;

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
         * {@link Binding }
         * 
         * 
         */
        public List<Binding> getColumn() {
            if (column == null) {
                column = new ArrayList<Binding>();
            }
            return this.column;
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

    }

}
