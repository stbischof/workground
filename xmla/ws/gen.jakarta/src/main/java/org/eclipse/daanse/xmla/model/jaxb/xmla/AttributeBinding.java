
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttributeBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttributeBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
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
 *         &lt;element name="Ordinal" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeBinding", propOrder = {
    "attributeID",
    "type",
    "ordinal"
})
public class AttributeBinding
    extends Binding
{

    @XmlElement(name = "AttributeID", required = true)
    protected String attributeID;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Ordinal")
    protected BigInteger ordinal;

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
     * Gets the value of the ordinal property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrdinal() {
        return ordinal;
    }

    /**
     * Sets the value of the ordinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrdinal(BigInteger value) {
        this.ordinal = value;
    }

}
