
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pred_leaf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pred_leaf"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="comparator"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;choice&gt;
 *           &lt;element name="event"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
 *                   &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="global"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pred_leaf", propOrder = {
    "comparator",
    "event",
    "global",
    "value"
})
public class PredLeaf {

    @XmlElement(required = true)
    protected PredLeaf.Comparator comparator;
    protected PredLeaf.Event event;
    protected PredLeaf.Global global;
    @XmlElement(required = true)
    protected java.lang.Object value;

    /**
     * Gets the value of the comparator property.
     * 
     * @return
     *     possible object is
     *     {@link PredLeaf.Comparator }
     *     
     */
    public PredLeaf.Comparator getComparator() {
        return comparator;
    }

    /**
     * Sets the value of the comparator property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredLeaf.Comparator }
     *     
     */
    public void setComparator(PredLeaf.Comparator value) {
        this.comparator = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link PredLeaf.Event }
     *     
     */
    public PredLeaf.Event getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredLeaf.Event }
     *     
     */
    public void setEvent(PredLeaf.Event value) {
        this.event = value;
    }

    /**
     * Gets the value of the global property.
     * 
     * @return
     *     possible object is
     *     {@link PredLeaf.Global }
     *     
     */
    public PredLeaf.Global getGlobal() {
        return global;
    }

    /**
     * Sets the value of the global property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredLeaf.Global }
     *     
     */
    public void setGlobal(PredLeaf.Global value) {
        this.global = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Object }
     *     
     */
    public java.lang.Object getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Object }
     *     
     */
    public void setValue(java.lang.Object value) {
        this.value = value;
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
     *       &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Comparator {

        @XmlAttribute(name = "module")
        protected String module;
        @XmlAttribute(name = "package", required = true)
        protected String _package;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the module property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModule() {
            return module;
        }

        /**
         * Sets the value of the module property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModule(String value) {
            this.module = value;
        }

        /**
         * Gets the value of the package property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPackage() {
            return _package;
        }

        /**
         * Sets the value of the package property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPackage(String value) {
            this._package = value;
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
     *       &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
     *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Event {

        @XmlAttribute(name = "field")
        protected String field;
        @XmlAttribute(name = "module")
        protected String module;
        @XmlAttribute(name = "package", required = true)
        protected String _package;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the field property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getField() {
            return field;
        }

        /**
         * Sets the value of the field property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setField(String value) {
            this.field = value;
        }

        /**
         * Gets the value of the module property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModule() {
            return module;
        }

        /**
         * Sets the value of the module property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModule(String value) {
            this.module = value;
        }

        /**
         * Gets the value of the package property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPackage() {
            return _package;
        }

        /**
         * Sets the value of the package property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPackage(String value) {
            this._package = value;
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
     *       &lt;attGroup ref="{urn:schemas-microsoft-com:xml-analysis}objectNames"/&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Global {

        @XmlAttribute(name = "module")
        protected String module;
        @XmlAttribute(name = "package", required = true)
        protected String _package;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the module property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModule() {
            return module;
        }

        /**
         * Sets the value of the module property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModule(String value) {
            this.module = value;
        }

        /**
         * Gets the value of the package property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPackage() {
            return _package;
        }

        /**
         * Sets the value of the package property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPackage(String value) {
            this._package = value;
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

    }

}
