
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for MemberType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MemberType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;any processContents='skip' namespace='urn:schemas-microsoft-com:xml-analysis:mddataset' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Hierarchy" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MemberType", propOrder = {
    "any"
})
public class MemberType {

    @XmlAnyElement
    protected List<Element> any;
    @XmlAttribute(name = "Hierarchy")
    protected String hierarchy;

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

    public boolean isSetAny() {
        return ((this.any!= null)&&(!this.any.isEmpty()));
    }

    public void unsetAny() {
        this.any = null;
    }

    /**
     * Gets the value of the hierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarchy() {
        return hierarchy;
    }

    /**
     * Sets the value of the hierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarchy(String value) {
        this.hierarchy = value;
    }

    public boolean isSetHierarchy() {
        return (this.hierarchy!= null);
    }

}
