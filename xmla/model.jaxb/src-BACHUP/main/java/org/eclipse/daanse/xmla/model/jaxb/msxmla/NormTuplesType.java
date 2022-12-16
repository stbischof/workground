
package org.eclipse.daanse.xmla.model.jaxb.msxmla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NormTuplesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NormTuplesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NormTuple" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MemberRef" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="MemberOrdinal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="MemberDispInfo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "NormTuplesType", propOrder = {
    "normTuple"
})
public class NormTuplesType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "NormTuple")
    protected List<NormTuplesType.NormTuple> normTuple;

    /**
     * Gets the value of the normTuple property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the normTuple property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNormTuple().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NormTuplesType.NormTuple }
     * 
     * 
     */
    public List<NormTuplesType.NormTuple> getNormTuple() {
        if (normTuple == null) {
            normTuple = new ArrayList<NormTuplesType.NormTuple>();
        }
        return this.normTuple;
    }

    public boolean isSetNormTuple() {
        return ((this.normTuple!= null)&&(!this.normTuple.isEmpty()));
    }

    public void unsetNormTuple() {
        this.normTuple = null;
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
     *         &lt;element name="MemberRef" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="MemberOrdinal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="MemberDispInfo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
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
        "memberRef"
    })
    public static class NormTuple
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "MemberRef")
        protected List<NormTuplesType.NormTuple.MemberRef> memberRef;

        /**
         * Gets the value of the memberRef property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the memberRef property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMemberRef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NormTuplesType.NormTuple.MemberRef }
         * 
         * 
         */
        public List<NormTuplesType.NormTuple.MemberRef> getMemberRef() {
            if (memberRef == null) {
                memberRef = new ArrayList<NormTuplesType.NormTuple.MemberRef>();
            }
            return this.memberRef;
        }

        public boolean isSetMemberRef() {
            return ((this.memberRef!= null)&&(!this.memberRef.isEmpty()));
        }

        public void unsetMemberRef() {
            this.memberRef = null;
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
         *         &lt;element name="MemberOrdinal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="MemberDispInfo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
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
            "memberOrdinal",
            "memberDispInfo"
        })
        public static class MemberRef
            implements Serializable
        {

            private final static long serialVersionUID = 1L;
            @XmlElement(name = "MemberOrdinal")
            protected int memberOrdinal;
            @XmlElement(name = "MemberDispInfo")
            protected Integer memberDispInfo;

            /**
             * Gets the value of the memberOrdinal property.
             * 
             */
            public int getMemberOrdinal() {
                return memberOrdinal;
            }

            /**
             * Sets the value of the memberOrdinal property.
             * 
             */
            public void setMemberOrdinal(int value) {
                this.memberOrdinal = value;
            }

            public boolean isSetMemberOrdinal() {
                return true;
            }

            /**
             * Gets the value of the memberDispInfo property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getMemberDispInfo() {
                return memberDispInfo;
            }

            /**
             * Sets the value of the memberDispInfo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setMemberDispInfo(Integer value) {
                this.memberDispInfo = value;
            }

            public boolean isSetMemberDispInfo() {
                return (this.memberDispInfo!= null);
            }

        }

    }

}
