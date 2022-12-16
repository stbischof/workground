
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComAssembly complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComAssembly"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Assembly"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComAssembly", propOrder = {
    "source"
})
public class ComAssembly
    extends Assembly
{

    @XmlElement(name = "Source")
    protected String source;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    public boolean isSetSource() {
        return (this.source!= null);
    }

}
