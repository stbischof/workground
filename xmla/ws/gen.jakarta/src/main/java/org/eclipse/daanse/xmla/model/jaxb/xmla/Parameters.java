
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Parameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parameters"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Parameter" type="{urn:schemas-microsoft-com:xml-analysis}ExecuteParameter" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {
    "parameter"
})
public class Parameters {

    @XmlElement(name = "Parameter")
    protected List<ExecuteParameter> parameter;

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExecuteParameter }
     * 
     * 
     */
    public List<ExecuteParameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<ExecuteParameter>();
        }
        return this.parameter;
    }

}
