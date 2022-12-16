
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TupleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TupleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Member" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}MemberType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TupleType", propOrder = {
    "member"
})
public class TupleType {

    @XmlElement(name = "Member", required = true)
    protected List<MemberType> member;

    /**
     * Gets the value of the member property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the member property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MemberType }
     * 
     * 
     */
    public List<MemberType> getMember() {
        if (member == null) {
            member = new ArrayList<MemberType>();
        }
        return this.member;
    }

}
