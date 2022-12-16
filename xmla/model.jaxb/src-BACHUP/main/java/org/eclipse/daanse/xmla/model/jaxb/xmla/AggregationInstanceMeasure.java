
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregationInstanceMeasure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregationInstanceMeasure"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="MeasureID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}ColumnBinding"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregationInstanceMeasure", propOrder = {

})
public class AggregationInstanceMeasure {

    @XmlElement(name = "MeasureID", required = true)
    protected String measureID;
    @XmlElement(name = "Source", required = true)
    protected ColumnBinding source;

    /**
     * Gets the value of the measureID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureID() {
        return measureID;
    }

    /**
     * Sets the value of the measureID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureID(String value) {
        this.measureID = value;
    }

    public boolean isSetMeasureID() {
        return (this.measureID!= null);
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link ColumnBinding }
     *     
     */
    public ColumnBinding getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColumnBinding }
     *     
     */
    public void setSource(ColumnBinding value) {
        this.source = value;
    }

    public boolean isSetSource() {
        return (this.source!= null);
    }

}
