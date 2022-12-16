
package org.eclipse.daanse.xmla.model.jaxb.msxmla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset.TupleType;

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
 *         &lt;element name="NormTuples" type="{http://schemas.microsoft.com/analysisservices/2003/xmla}NormTuplesType"/&gt;
 *         &lt;element name="MembersLookup"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Members" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}TupleType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "normTuples",
    "membersLookup"
})
@XmlRootElement(name = "NormTupleSet")
public class NormTupleSet implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "NormTuples", required = true)
    protected NormTuplesType normTuples;
    @XmlElement(name = "MembersLookup", required = true)
    protected NormTupleSet.MembersLookup membersLookup;

    /**
     * Gets the value of the normTuples property.
     * 
     * @return
     *     possible object is
     *     {@link NormTuplesType }
     *     
     */
    public NormTuplesType getNormTuples() {
        return normTuples;
    }

    /**
     * Sets the value of the normTuples property.
     * 
     * @param value
     *     allowed object is
     *     {@link NormTuplesType }
     *     
     */
    public void setNormTuples(NormTuplesType value) {
        this.normTuples = value;
    }

    public boolean isSetNormTuples() {
        return (this.normTuples!= null);
    }

    /**
     * Gets the value of the membersLookup property.
     * 
     * @return
     *     possible object is
     *     {@link NormTupleSet.MembersLookup }
     *     
     */
    public NormTupleSet.MembersLookup getMembersLookup() {
        return membersLookup;
    }

    /**
     * Sets the value of the membersLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link NormTupleSet.MembersLookup }
     *     
     */
    public void setMembersLookup(NormTupleSet.MembersLookup value) {
        this.membersLookup = value;
    }

    public boolean isSetMembersLookup() {
        return (this.membersLookup!= null);
    }


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
     *         &lt;element name="Members" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}TupleType" maxOccurs="unbounded"/&gt;
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
        "members"
    })
    public static class MembersLookup
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Members", required = true)
        protected List<TupleType> members;

        /**
         * Gets the value of the members property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the members property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMembers().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TupleType }
         * 
         * 
         */
        public List<TupleType> getMembers() {
            if (members == null) {
                members = new ArrayList<TupleType>();
            }
            return this.members;
        }

        public boolean isSetMembers() {
            return ((this.members!= null)&&(!this.members.isEmpty()));
        }

        public void unsetMembers() {
            this.members = null;
        }

    }

}
