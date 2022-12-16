
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TuplesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TuplesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tuple" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}TupleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TuplesType", propOrder = {
    "tuple"
})
public class TuplesType {

    @XmlElement(name = "Tuple")
    protected List<TupleType> tuple;

    /**
     * Gets the value of the tuple property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the tuple property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTuple().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TupleType }
     * 
     * 
     */
    public List<TupleType> getTuple() {
        if (tuple == null) {
            tuple = new ArrayList<TupleType>();
        }
        return this.tuple;
    }

    public boolean isSetTuple() {
        return ((this.tuple!= null)&&(!this.tuple.isEmpty()));
    }

    public void unsetTuple() {
        this.tuple = null;
    }

}
