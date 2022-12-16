
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MergePartitions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MergePartitions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Sources"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Target" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MergePartitions", propOrder = {

})
public class MergePartitions {

    @XmlElement(name = "Sources", required = true)
    protected MergePartitions.Sources sources;
    @XmlElement(name = "Target", required = true)
    protected ObjectReference target;

    /**
     * Gets the value of the sources property.
     * 
     * @return
     *     possible object is
     *     {@link MergePartitions.Sources }
     *     
     */
    public MergePartitions.Sources getSources() {
        return sources;
    }

    /**
     * Sets the value of the sources property.
     * 
     * @param value
     *     allowed object is
     *     {@link MergePartitions.Sources }
     *     
     */
    public void setSources(MergePartitions.Sources value) {
        this.sources = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectReference }
     *     
     */
    public ObjectReference getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectReference }
     *     
     */
    public void setTarget(ObjectReference value) {
        this.target = value;
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
     *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "source"
    })
    public static class Sources {

        @XmlElement(name = "Source")
        protected List<ObjectReference> source;

        /**
         * Gets the value of the source property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the source property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ObjectReference }
         * 
         * 
         */
        public List<ObjectReference> getSource() {
            if (source == null) {
                source = new ArrayList<ObjectReference>();
            }
            return this.source;
        }

    }

}
