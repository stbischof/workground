
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceMeasureGroupDimension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceMeasureGroupDimension"&gt;
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
 *         &lt;element name="IntermediateCubeDimensionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IntermediateGranularityAttributeID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Materialization" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="Indirect"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ProcessingState" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceMeasureGroupDimension", propOrder = {
    "cubeDimensionID",
    "annotations",
    "source",
    "intermediateCubeDimensionID",
    "intermediateGranularityAttributeID",
    "materialization",
    "processingState"
})
public class ReferenceMeasureGroupDimension
    extends MeasureGroupDimension
{

    @XmlElement(name = "CubeDimensionID", required = true)
    protected String cubeDimensionID;
    @XmlElement(name = "Annotations")
    protected ReferenceMeasureGroupDimension.Annotations annotations;
    @XmlElement(name = "Source")
    protected MeasureGroupDimensionBinding source;
    @XmlElement(name = "IntermediateCubeDimensionID", required = true)
    protected String intermediateCubeDimensionID;
    @XmlElement(name = "IntermediateGranularityAttributeID", required = true)
    protected String intermediateGranularityAttributeID;
    @XmlElement(name = "Materialization")
    protected String materialization;
    @XmlElement(name = "ProcessingState", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String processingState;

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
     *     {@link ReferenceMeasureGroupDimension.Annotations }
     *     
     */
    public ReferenceMeasureGroupDimension.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceMeasureGroupDimension.Annotations }
     *     
     */
    public void setAnnotations(ReferenceMeasureGroupDimension.Annotations value) {
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
     * Gets the value of the intermediateCubeDimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntermediateCubeDimensionID() {
        return intermediateCubeDimensionID;
    }

    /**
     * Sets the value of the intermediateCubeDimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntermediateCubeDimensionID(String value) {
        this.intermediateCubeDimensionID = value;
    }

    /**
     * Gets the value of the intermediateGranularityAttributeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntermediateGranularityAttributeID() {
        return intermediateGranularityAttributeID;
    }

    /**
     * Sets the value of the intermediateGranularityAttributeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntermediateGranularityAttributeID(String value) {
        this.intermediateGranularityAttributeID = value;
    }

    /**
     * Gets the value of the materialization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialization() {
        return materialization;
    }

    /**
     * Sets the value of the materialization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialization(String value) {
        this.materialization = value;
    }

    /**
     * Gets the value of the processingState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingState() {
        return processingState;
    }

    /**
     * Sets the value of the processingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingState(String value) {
        this.processingState = value;
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
