
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.msxmla.NormTupleSet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SetListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;group ref="{urn:schemas-microsoft-com:xml-analysis:mddataset}SetType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;attribute name="Size" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetListType", propOrder = {
    "setType"
})
public class SetListType {

    @XmlElements({
        @XmlElement(name = "Members", type = MembersType.class),
        @XmlElement(name = "Tuples", type = TuplesType.class),
        @XmlElement(name = "CrossProduct", type = SetListType.class),
        @XmlElement(name = "NormTupleSet", namespace = "http://schemas.microsoft.com/analysisservices/2003/xmla", type = NormTupleSet.class),
        @XmlElement(name = "Union", type = Union.class)
    })
    protected List<Object> setType;
    @XmlAttribute(name = "Size")
    @XmlSchemaType(name = "unsignedInt")
    protected Long size;

    /**
     * Gets the value of the setType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the setType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NormTupleSet }
     * {@link MembersType }
     * {@link SetListType }
     * {@link SetListType.Union }
     * {@link TuplesType }
     * 
     * 
     */
    public List<Object> getSetType() {
        if (setType == null) {
            setType = new ArrayList<Object>();
        }
        return this.setType;
    }

    public boolean isSetSetType() {
        return ((this.setType!= null)&&(!this.setType.isEmpty()));
    }

    public void unsetSetType() {
        this.setType = null;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSize(long value) {
        this.size = value;
    }

    public boolean isSetSize() {
        return (this.size!= null);
    }

    public void unsetSize() {
        this.size = null;
    }


}
