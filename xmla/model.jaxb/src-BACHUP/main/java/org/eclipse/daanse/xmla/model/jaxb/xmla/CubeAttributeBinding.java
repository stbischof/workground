
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CubeAttributeBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CubeAttributeBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CubeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Type"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="All"/&gt;
 *               &lt;enumeration value="Key"/&gt;
 *               &lt;enumeration value="Name"/&gt;
 *               &lt;enumeration value="Value"/&gt;
 *               &lt;enumeration value="Translation"/&gt;
 *               &lt;enumeration value="UnaryOperator"/&gt;
 *               &lt;enumeration value="SkippedLevels"/&gt;
 *               &lt;enumeration value="CustomRollup"/&gt;
 *               &lt;enumeration value="CustomRollupProperties"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Ordinal" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Ordinal" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubeAttributeBinding", propOrder = {
    "cubeID",
    "cubeDimensionID",
    "attributeID",
    "type",
    "ordinal"
})
public class CubeAttributeBinding
    extends Binding
{

    @XmlElement(name = "CubeID", required = true)
    protected String cubeID;
    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Ordinal")
    protected CubeAttributeBinding.Ordinal ordinal;

    /**
     * Gets the value of the cubeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubeID() {
        return cubeID;
    }

    /**
     * Sets the value of the cubeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubeID(String value) {
        this.cubeID = value;
    }

    public boolean isSetCubeID() {
        return (this.cubeID!= null);
    }

    /**
     * Gets the value of the cubeDimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    /**
     * Sets the value of the cubeDimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    public boolean isSetCubeDimensionID() {
        return (this.cubeDimensionID!= null);
    }

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

    public boolean isSetType() {
        return (this.type!= null);
    }

    /**
     * Gets the value of the ordinal property.
     * 
     * @return
     *     possible object is
     *     {@link CubeAttributeBinding.Ordinal }
     *     
     */
    public CubeAttributeBinding.Ordinal getOrdinal() {
        return ordinal;
    }

    /**
     * Sets the value of the ordinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeAttributeBinding.Ordinal }
     *     
     */
    public void setOrdinal(CubeAttributeBinding.Ordinal value) {
        this.ordinal = value;
    }

    public boolean isSetOrdinal() {
        return (this.ordinal!= null);
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
     *         &lt;element name="Ordinal" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "ordinal"
    })
    public static class Ordinal {

        @XmlElement(name = "Ordinal")
        protected List<BigInteger> ordinal;

        /**
         * Gets the value of the ordinal property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the ordinal property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrdinal().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BigInteger }
         * 
         * 
         */
        public List<BigInteger> getOrdinal() {
            if (ordinal == null) {
                ordinal = new ArrayList<BigInteger>();
            }
            return this.ordinal;
        }

        public boolean isSetOrdinal() {
            return ((this.ordinal!= null)&&(!this.ordinal.isEmpty()));
        }

        public void unsetOrdinal() {
            this.ordinal = null;
        }

    }

}
