
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Attribute_InsertUpdate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Attribute_InsertUpdate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="AttributeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Keys" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" maxOccurs="unbounded" minOccurs="0"/&gt;
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
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation_InsertUpdate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CUSTOM_ROLLUP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CUSTOM_ROLLUP_PROPERTIES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UNARY_OPERATOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SKIPPED_LEVELS" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attribute_InsertUpdate", propOrder = {

})
public class AttributeInsertUpdate {

    @XmlElement(name = "AttributeName", required = true)
    protected String attributeName;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Keys")
    protected AttributeInsertUpdate.Keys keys;
    @XmlElement(name = "Translations")
    protected AttributeInsertUpdate.Translations translations;
    @XmlElement(name = "Value")
    protected String value;
    @XmlElement(name = "CUSTOM_ROLLUP")
    protected String customrollup;
    @XmlElement(name = "CUSTOM_ROLLUP_PROPERTIES")
    protected String customrollupproperties;
    @XmlElement(name = "UNARY_OPERATOR")
    protected String unaryoperator;
    @XmlElement(name = "SKIPPED_LEVELS")
    protected BigInteger skippedlevels;

    /**
     * Gets the value of the attributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    public boolean isSetAttributeName() {
        return (this.attributeName!= null);
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
     * Gets the value of the keys property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeInsertUpdate.Keys }
     *     
     */
    public AttributeInsertUpdate.Keys getKeys() {
        return keys;
    }

    /**
     * Sets the value of the keys property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeInsertUpdate.Keys }
     *     
     */
    public void setKeys(AttributeInsertUpdate.Keys value) {
        this.keys = value;
    }

    public boolean isSetKeys() {
        return (this.keys!= null);
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeInsertUpdate.Translations }
     *     
     */
    public AttributeInsertUpdate.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeInsertUpdate.Translations }
     *     
     */
    public void setTranslations(AttributeInsertUpdate.Translations value) {
        this.translations = value;
    }

    public boolean isSetTranslations() {
        return (this.translations!= null);
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
     * Gets the value of the customrollup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTOMROLLUP() {
        return customrollup;
    }

    /**
     * Sets the value of the customrollup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTOMROLLUP(String value) {
        this.customrollup = value;
    }

    public boolean isSetCUSTOMROLLUP() {
        return (this.customrollup!= null);
    }

    /**
     * Gets the value of the customrollupproperties property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTOMROLLUPPROPERTIES() {
        return customrollupproperties;
    }

    /**
     * Sets the value of the customrollupproperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTOMROLLUPPROPERTIES(String value) {
        this.customrollupproperties = value;
    }

    public boolean isSetCUSTOMROLLUPPROPERTIES() {
        return (this.customrollupproperties!= null);
    }

    /**
     * Gets the value of the unaryoperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNARYOPERATOR() {
        return unaryoperator;
    }

    /**
     * Sets the value of the unaryoperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNARYOPERATOR(String value) {
        this.unaryoperator = value;
    }

    public boolean isSetUNARYOPERATOR() {
        return (this.unaryoperator!= null);
    }

    /**
     * Gets the value of the skippedlevels property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSKIPPEDLEVELS() {
        return skippedlevels;
    }

    /**
     * Sets the value of the skippedlevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSKIPPEDLEVELS(BigInteger value) {
        this.skippedlevels = value;
    }

    public boolean isSetSKIPPEDLEVELS() {
        return (this.skippedlevels!= null);
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
     *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "key"
    })
    public static class Keys {

        @XmlElement(name = "Key")
        protected List<java.lang.Object> key;

        /**
         * Gets the value of the key property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the key property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKey().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link java.lang.Object }
         * 
         * 
         */
        public List<java.lang.Object> getKey() {
            if (key == null) {
                key = new ArrayList<java.lang.Object>();
            }
            return this.key;
        }

        public boolean isSetKey() {
            return ((this.key!= null)&&(!this.key.isEmpty()));
        }

        public void unsetKey() {
            this.key = null;
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
     *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation_InsertUpdate" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<TranslationInsertUpdate> translation;

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
         * {@link TranslationInsertUpdate }
         * 
         * 
         */
        public List<TranslationInsertUpdate> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<TranslationInsertUpdate>();
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
