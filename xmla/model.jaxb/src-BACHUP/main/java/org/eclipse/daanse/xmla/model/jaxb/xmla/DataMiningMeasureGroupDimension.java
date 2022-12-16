
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataMiningMeasureGroupDimension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataMiningMeasureGroupDimension"&gt;
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
 *         &lt;element name="CaseCubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataMiningMeasureGroupDimension", propOrder = {
    "cubeDimensionID",
    "annotations",
    "source",
    "caseCubeDimensionID"
})
public class DataMiningMeasureGroupDimension
    extends MeasureGroupDimension
{

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Annotations")
    protected DataMiningMeasureGroupDimension.Annotations annotations;
    @XmlElement(name = "Source")
    protected MeasureGroupDimensionBinding source;
    @XmlElement(name = "CaseCubeDimensionID", required = true)
    protected String caseCubeDimensionID;

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

    public boolean isSetCubeDimensionID() {
        return (this.cubeDimensionID!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link DataMiningMeasureGroupDimension.Annotations }
     *     
     */
    public DataMiningMeasureGroupDimension.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataMiningMeasureGroupDimension.Annotations }
     *     
     */
    public void setAnnotations(DataMiningMeasureGroupDimension.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
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

    public boolean isSetSource() {
        return (this.source!= null);
    }

    /**
     * Gets the value of the caseCubeDimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseCubeDimensionID() {
        return caseCubeDimensionID;
    }

    /**
     * Sets the value of the caseCubeDimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseCubeDimensionID(String value) {
        this.caseCubeDimensionID = value;
    }

    public boolean isSetCaseCubeDimensionID() {
        return (this.caseCubeDimensionID!= null);
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

        public boolean isSetAnnotation() {
            return ((this.annotation!= null)&&(!this.annotation.isEmpty()));
        }

        public void unsetAnnotation() {
            this.annotation = null;
        }

    }

}
