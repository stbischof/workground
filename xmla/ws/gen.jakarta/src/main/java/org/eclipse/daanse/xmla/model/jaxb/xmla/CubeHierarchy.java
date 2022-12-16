
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CubeHierarchy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CubeHierarchy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="HierarchyID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="OptimizedState" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="FullyOptimized"/&gt;
 *               &lt;enumeration value="NotOptimized"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Annotations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubeHierarchy", propOrder = {

})
public class CubeHierarchy {

    @XmlElement(name = "HierarchyID", required = true)
    protected String hierarchyID;
    @XmlElement(name = "OptimizedState")
    protected String optimizedState;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "Enabled")
    protected Boolean enabled;
    @XmlElement(name = "Annotations")
    protected CubeHierarchy.Annotations annotations;

    /**
     * Gets the value of the hierarchyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarchyID() {
        return hierarchyID;
    }

    /**
     * Sets the value of the hierarchyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarchyID(String value) {
        this.hierarchyID = value;
    }

    /**
     * Gets the value of the optimizedState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptimizedState() {
        return optimizedState;
    }

    /**
     * Sets the value of the optimizedState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptimizedState(String value) {
        this.optimizedState = value;
    }

    /**
     * Gets the value of the visible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisible(Boolean value) {
        this.visible = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnabled(Boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link CubeHierarchy.Annotations }
     *     
     */
    public CubeHierarchy.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubeHierarchy.Annotations }
     *     
     */
    public void setAnnotations(CubeHierarchy.Annotations value) {
        this.annotations = value;
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
     *         &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "annotation"
    })
    public static class Annotations {

        @XmlElement(name = "Annotation")
        protected List<Annotation> annotation;

        /**
         * Gets the value of the annotation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the annotation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAnnotation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Annotation }
         * 
         * 
         */
        public List<Annotation> getAnnotation() {
            if (annotation == null) {
                annotation = new ArrayList<Annotation>();
            }
            return this.annotation;
        }

    }

}
