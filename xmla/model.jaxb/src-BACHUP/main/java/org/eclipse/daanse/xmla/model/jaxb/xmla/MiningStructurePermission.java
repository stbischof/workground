
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MiningStructurePermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MiningStructurePermission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Permission"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AllowDrillThrough" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Write" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MiningStructurePermission", propOrder = {
    "allowDrillThrough",
    "write"
})
public class MiningStructurePermission
    extends Permission
{

    @XmlElement(name = "AllowDrillThrough")
    protected Boolean allowDrillThrough;
    @XmlElement(name = "Write")
    protected String write;

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
     * Gets the value of the write property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrite() {
        return write;
    }

    /**
     * Sets the value of the write property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrite(String value) {
        this.write = value;
    }

    public boolean isSetWrite() {
        return (this.write!= null);
    }

}
