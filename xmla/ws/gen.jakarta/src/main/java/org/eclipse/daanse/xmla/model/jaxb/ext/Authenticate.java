
package org.eclipse.daanse.xmla.model.jaxb.ext;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


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
 *         &lt;element name="SspiHandshake" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
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
    "sspiHandshake"
})
@XmlRootElement(name = "Authenticate")
public class Authenticate {

    @XmlElement(name = "SspiHandshake", required = true)
    protected byte[] sspiHandshake;

    /**
     * Gets the value of the sspiHandshake property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSspiHandshake() {
        return sspiHandshake;
    }

    /**
     * Sets the value of the sspiHandshake property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSspiHandshake(byte[] value) {
        this.sspiHandshake = value;
    }

}
