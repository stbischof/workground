
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.xmla.model.jaxb.engine300.CalculationPropertiesVisualizationProperties;


/**
 * <p>Java class for CalculationProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalculationProperty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CalculationReference" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CalculationType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Member"/&gt;
 *               &lt;enumeration value="Set"/&gt;
 *               &lt;enumeration value="Cells"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
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
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SolveOrder" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="FormatString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ForeColor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BackColor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FontSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FontFlags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NonEmptyBehavior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AssociatedMeasureGroupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DisplayFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="VisualizationProperties" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300}CalculationPropertiesVisualizationProperties" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationProperty", propOrder = {

})
public class CalculationProperty {

    @XmlElement(name = "CalculationReference", required = true)
    protected String calculationReference;
    @XmlElement(name = "CalculationType", required = true)
    protected String calculationType;
    @XmlElement(name = "Translations")
    protected CalculationProperty.Translations translations;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "SolveOrder")
    protected BigInteger solveOrder;
    @XmlElement(name = "FormatString")
    protected String formatString;
    @XmlElement(name = "ForeColor")
    protected String foreColor;
    @XmlElement(name = "BackColor")
    protected String backColor;
    @XmlElement(name = "FontName")
    protected String fontName;
    @XmlElement(name = "FontSize")
    protected String fontSize;
    @XmlElement(name = "FontFlags")
    protected String fontFlags;
    @XmlElement(name = "NonEmptyBehavior")
    protected String nonEmptyBehavior;
    @XmlElement(name = "AssociatedMeasureGroupID")
    protected String associatedMeasureGroupID;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "Language")
    protected BigInteger language;
    @XmlElement(name = "VisualizationProperties")
    protected CalculationPropertiesVisualizationProperties visualizationProperties;

    /**
     * Gets the value of the calculationReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculationReference() {
        return calculationReference;
    }

    /**
     * Sets the value of the calculationReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculationReference(String value) {
        this.calculationReference = value;
    }

    /**
     * Gets the value of the calculationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculationType() {
        return calculationType;
    }

    /**
     * Sets the value of the calculationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculationType(String value) {
        this.calculationType = value;
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationProperty.Translations }
     *     
     */
    public CalculationProperty.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationProperty.Translations }
     *     
     */
    public void setTranslations(CalculationProperty.Translations value) {
        this.translations = value;
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

    /**
     * Gets the value of the solveOrder property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSolveOrder() {
        return solveOrder;
    }

    /**
     * Sets the value of the solveOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSolveOrder(BigInteger value) {
        this.solveOrder = value;
    }

    /**
     * Gets the value of the formatString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatString() {
        return formatString;
    }

    /**
     * Sets the value of the formatString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatString(String value) {
        this.formatString = value;
    }

    /**
     * Gets the value of the foreColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeColor() {
        return foreColor;
    }

    /**
     * Sets the value of the foreColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeColor(String value) {
        this.foreColor = value;
    }

    /**
     * Gets the value of the backColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackColor() {
        return backColor;
    }

    /**
     * Sets the value of the backColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackColor(String value) {
        this.backColor = value;
    }

    /**
     * Gets the value of the fontName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * Sets the value of the fontName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontName(String value) {
        this.fontName = value;
    }

    /**
     * Gets the value of the fontSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontSize() {
        return fontSize;
    }

    /**
     * Sets the value of the fontSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontSize(String value) {
        this.fontSize = value;
    }

    /**
     * Gets the value of the fontFlags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontFlags() {
        return fontFlags;
    }

    /**
     * Sets the value of the fontFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontFlags(String value) {
        this.fontFlags = value;
    }

    /**
     * Gets the value of the nonEmptyBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonEmptyBehavior() {
        return nonEmptyBehavior;
    }

    /**
     * Sets the value of the nonEmptyBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonEmptyBehavior(String value) {
        this.nonEmptyBehavior = value;
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

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLanguage(BigInteger value) {
        this.language = value;
    }

    /**
     * Gets the value of the visualizationProperties property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationPropertiesVisualizationProperties }
     *     
     */
    public CalculationPropertiesVisualizationProperties getVisualizationProperties() {
        return visualizationProperties;
    }

    /**
     * Sets the value of the visualizationProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationPropertiesVisualizationProperties }
     *     
     */
    public void setVisualizationProperties(CalculationPropertiesVisualizationProperties value) {
        this.visualizationProperties = value;
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
