
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ManyToManyMeasureGroupDimension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManyToManyMeasureGroupDimension"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}MeasureGroupDimension"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroupDimensionBinding" minOccurs="0"/&gt;
 *         &lt;element name="MeasureGroupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DirectSlice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManyToManyMeasureGroupDimension", propOrder = {
    "cubeDimensionID",
    "annotations",
    "source",
    "measureGroupID",
    "directSlice"
})
public class ManyToManyMeasureGroupDimension
    extends MeasureGroupDimension
{

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Annotations")
    protected ManyToManyMeasureGroupDimension.Annotations annotations;
    @XmlElement(name = "Source")
    protected MeasureGroupDimensionBinding source;
    @XmlElement(name = "MeasureGroupID")
    protected String measureGroupID;
    @XmlElement(name = "DirectSlice")
    protected String directSlice;

    /**
     * Gets the value of the cubeDimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubeDimensionID() {
        return cubeDimensionID;
    }

    /**
     * Sets the value of the cubeDimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubeDimensionID(String value) {
        this.cubeDimensionID = value;
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link ManyToManyMeasureGroupDimension.Annotations }
     *     
     */
    public ManyToManyMeasureGroupDimension.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManyToManyMeasureGroupDimension.Annotations }
     *     
     */
    public void setAnnotations(ManyToManyMeasureGroupDimension.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroupDimensionBinding }
     *     
     */
    public MeasureGroupDimensionBinding getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroupDimensionBinding }
     *     
     */
    public void setSource(MeasureGroupDimensionBinding value) {
        this.source = value;
    }

    /**
     * Gets the value of the measureGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureGroupID() {
        return measureGroupID;
    }

    /**
     * Sets the value of the measureGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureGroupID(String value) {
        this.measureGroupID = value;
    }

    /**
     * Gets the value of the directSlice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectSlice() {
        return directSlice;
    }

    /**
     * Sets the value of the directSlice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectSlice(String value) {
        this.directSlice = value;
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
