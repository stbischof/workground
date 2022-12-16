
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushedDataSource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushedDataSource"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="root"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Parameter"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;enumeration value="InputRowset"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EndOfData"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Parameter"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;enumeration value="EndOfInputRowset"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
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
@XmlType(name = "PushedDataSource", propOrder = {

})
public class PushedDataSource {

    @XmlElement(required = true)
    protected PushedDataSource.Root root;
    @XmlElement(name = "EndOfData", required = true)
    protected PushedDataSource.EndOfData endOfData;

    /**
     * Gets the value of the root property.
     * 
     * @return
     *     possible object is
     *     {@link PushedDataSource.Root }
     *     
     */
    public PushedDataSource.Root getRoot() {
        return root;
    }

    /**
     * Sets the value of the root property.
     * 
     * @param value
     *     allowed object is
     *     {@link PushedDataSource.Root }
     *     
     */
    public void setRoot(PushedDataSource.Root value) {
        this.root = value;
    }

    /**
     * Gets the value of the endOfData property.
     * 
     * @return
     *     possible object is
     *     {@link PushedDataSource.EndOfData }
     *     
     */
    public PushedDataSource.EndOfData getEndOfData() {
        return endOfData;
    }

    /**
     * Sets the value of the endOfData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PushedDataSource.EndOfData }
     *     
     */
    public void setEndOfData(PushedDataSource.EndOfData value) {
        this.endOfData = value;
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
     *       &lt;attribute name="Parameter"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;enumeration value="EndOfInputRowset"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class EndOfData {

        @XmlAttribute(name = "Parameter")
        protected String parameter;

        /**
         * Gets the value of the parameter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameter() {
            return parameter;
        }

        /**
         * Sets the value of the parameter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameter(String value) {
            this.parameter = value;
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
     *       &lt;attribute name="Parameter"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;enumeration value="InputRowset"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Root {

        @XmlAttribute(name = "Parameter")
        protected String parameter;

        /**
         * Gets the value of the parameter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameter() {
            return parameter;
        }

        /**
         * Sets the value of the parameter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameter(String value) {
            this.parameter = value;
        }

    }

}
