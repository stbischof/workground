
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AxesInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AxesInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AxisInfo" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}AxisInfo" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AxesInfo", propOrder = {
    "axisInfo"
})
public class AxesInfo {

    @XmlElement(name = "AxisInfo", required = true)
    protected List<AxisInfo> axisInfo;

    /**
     * Gets the value of the axisInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the axisInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAxisInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AxisInfo }
     * 
     * 
     */
    public List<AxisInfo> getAxisInfo() {
        if (axisInfo == null) {
            axisInfo = new ArrayList<AxisInfo>();
        }
        return this.axisInfo;
    }

}
