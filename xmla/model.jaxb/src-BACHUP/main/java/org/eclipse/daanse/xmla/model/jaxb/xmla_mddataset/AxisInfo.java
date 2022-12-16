
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AxisInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AxisInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HierarchyInfo" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}HierarchyInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AxisInfo", propOrder = {
    "hierarchyInfo"
})
public class AxisInfo {

    @XmlElement(name = "HierarchyInfo")
    protected List<HierarchyInfo> hierarchyInfo;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the hierarchyInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the hierarchyInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHierarchyInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HierarchyInfo }
     * 
     * 
     */
    public List<HierarchyInfo> getHierarchyInfo() {
        if (hierarchyInfo == null) {
            hierarchyInfo = new ArrayList<HierarchyInfo>();
        }
        return this.hierarchyInfo;
    }

    public boolean isSetHierarchyInfo() {
        return ((this.hierarchyInfo!= null)&&(!this.hierarchyInfo.isEmpty()));
    }

    public void unsetHierarchyInfo() {
        this.hierarchyInfo = null;
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

}
