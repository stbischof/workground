
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.Duration;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DesignAggregations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DesignAggregations"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="Steps" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Optimization" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="Storage" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Materialize" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Queries" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Query" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "DesignAggregations", propOrder = {

})
public class DesignAggregations {

    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "Time")
    protected Duration time;
    @XmlElement(name = "Steps")
    protected BigInteger steps;
    @XmlElement(name = "Optimization")
    protected Double optimization;
    @XmlElement(name = "Storage")
    protected Long storage;
    @XmlElement(name = "Materialize")
    protected Boolean materialize;
    @XmlElement(name = "Queries")
    protected DesignAggregations.Queries queries;

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectReference }
     *     
     */
    public ObjectReference getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectReference }
     *     
     */
    public void setObject(ObjectReference value) {
        this.object = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setTime(Duration value) {
        this.time = value;
    }

    /**
     * Gets the value of the steps property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSteps() {
        return steps;
    }

    /**
     * Sets the value of the steps property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSteps(BigInteger value) {
        this.steps = value;
    }

    /**
     * Gets the value of the optimization property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOptimization() {
        return optimization;
    }

    /**
     * Sets the value of the optimization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOptimization(Double value) {
        this.optimization = value;
    }

    /**
     * Gets the value of the storage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStorage() {
        return storage;
    }

    /**
     * Sets the value of the storage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStorage(Long value) {
        this.storage = value;
    }

    /**
     * Gets the value of the materialize property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMaterialize() {
        return materialize;
    }

    /**
     * Sets the value of the materialize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMaterialize(Boolean value) {
        this.materialize = value;
    }

    /**
     * Gets the value of the queries property.
     * 
     * @return
     *     possible object is
     *     {@link DesignAggregations.Queries }
     *     
     */
    public DesignAggregations.Queries getQueries() {
        return queries;
    }

    /**
     * Sets the value of the queries property.
     * 
     * @param value
     *     allowed object is
     *     {@link DesignAggregations.Queries }
     *     
     */
    public void setQueries(DesignAggregations.Queries value) {
        this.queries = value;
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
     *         &lt;element name="Query" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "query"
    })
    public static class Queries {

        @XmlElement(name = "Query")
        protected List<String> query;

        /**
         * Gets the value of the query property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the query property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getQuery().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getQuery() {
            if (query == null) {
                query = new ArrayList<String>();
            }
            return this.query;
        }

    }

}
