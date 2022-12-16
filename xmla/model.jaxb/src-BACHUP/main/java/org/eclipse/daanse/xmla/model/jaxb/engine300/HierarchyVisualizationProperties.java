
package org.eclipse.daanse.xmla.model.jaxb.engine300;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HierarchyVisualizationProperties complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HierarchyVisualizationProperties"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContextualNameRule" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Context"/&gt;
 *               &lt;enumeration value="Merge"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FolderPosition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HierarchyVisualizationProperties", propOrder = {
    "contextualNameRule",
    "folderPosition"
})
public class HierarchyVisualizationProperties
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ContextualNameRule", defaultValue = "None")
    protected String contextualNameRule;
    @XmlElement(name = "FolderPosition", defaultValue = "-1")
    protected BigInteger folderPosition;

    /**
     * Gets the value of the contextualNameRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextualNameRule() {
        return contextualNameRule;
    }

    /**
     * Sets the value of the contextualNameRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextualNameRule(String value) {
        this.contextualNameRule = value;
    }

    public boolean isSetContextualNameRule() {
        return (this.contextualNameRule!= null);
    }

    /**
     * Gets the value of the folderPosition property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFolderPosition() {
        return folderPosition;
    }

    /**
     * Sets the value of the folderPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFolderPosition(BigInteger value) {
        this.folderPosition = value;
    }

    public boolean isSetFolderPosition() {
        return (this.folderPosition!= null);
    }

}
