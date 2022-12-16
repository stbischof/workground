
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="RequestType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="DBSCHEMA_CATALOGS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_CUBES"/&gt;
 *               &lt;enumeration value="MDSCHEMA_DIMENSIONS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_HIERARCHIES"/&gt;
 *               &lt;enumeration value="MDSCHEMA_LEVELS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_MEASURES"/&gt;
 *               &lt;enumeration value="MDSCHEMA_PROPERTIES"/&gt;
 *               &lt;enumeration value="MDSCHEMA_MEMBERS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_ACTIONS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_SETS"/&gt;
 *               &lt;enumeration value="DISCOVER_INSTANCES"/&gt;
 *               &lt;enumeration value="MDSCHEMA_KPIS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_MEASUREGROUPS"/&gt;
 *               &lt;enumeration value="MDSCHEMA_MEASUREGROUP_DIMENSIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_PROPERTIES"/&gt;
 *               &lt;enumeration value="DISCOVER_LITERALS"/&gt;
 *               &lt;enumeration value="DISCOVER_SCHEMA_ROWSETS"/&gt;
 *               &lt;enumeration value="DISCOVER_KEYWORDS"/&gt;
 *               &lt;enumeration value="DBSCHEMA_TABLES"/&gt;
 *               &lt;enumeration value="DBSCHEMA_COLUMNS"/&gt;
 *               &lt;enumeration value="DBSCHEMA_PROVIDER_TYPES"/&gt;
 *               &lt;enumeration value="MDSCHEMA_INPUT_DATASOURCES"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_SERVICES"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_SERVICE_PARAMETERS"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_FUNCTIONS"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_MODEL_CONTENT"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_MODEL_XML"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_MODEL_CONTENT_PMML"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_MODELS"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_COLUMNS"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_STRUCTURES"/&gt;
 *               &lt;enumeration value="DMSCHEMA_MINING_STRUCTURE_COLUMNS"/&gt;
 *               &lt;enumeration value="DISCOVER_DATASOURCES"/&gt;
 *               &lt;enumeration value="DISCOVER_ENUMERATORS"/&gt;
 *               &lt;enumeration value="DISCOVER_XML_METADATA"/&gt;
 *               &lt;enumeration value="DISCOVER_TRACES"/&gt;
 *               &lt;enumeration value="DISCOVER_TRACE_DEFINITION_PROVIDERINFO"/&gt;
 *               &lt;enumeration value="DISCOVER_TRACE_COLUMNS"/&gt;
 *               &lt;enumeration value="DISCOVER_TRACE_EVENT_CATEGORIES"/&gt;
 *               &lt;enumeration value="DISCOVER_MEMORYUSAGE"/&gt;
 *               &lt;enumeration value="DISCOVER_MEMORYGRANT"/&gt;
 *               &lt;enumeration value="DISCOVER_LOCKS"/&gt;
 *               &lt;enumeration value="DISCOVER_CONNECTIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_SESSIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_JOBS"/&gt;
 *               &lt;enumeration value="DISCOVER_TRANSACTIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_DB_CONNECTIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_MASTER_KEY"/&gt;
 *               &lt;enumeration value="DISCOVER_PERFORMANCE_COUNTERS"/&gt;
 *               &lt;enumeration value="DISCOVER_LOCATIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_PARTITION_DIMENSION_STAT"/&gt;
 *               &lt;enumeration value="DISCOVER_PARTITION_STAT"/&gt;
 *               &lt;enumeration value="DISCOVER_DIMENSION_STAT"/&gt;
 *               &lt;enumeration value="DISCOVER_COMMANDS"/&gt;
 *               &lt;enumeration value="DISCOVER_COMMAND_OBJECTS"/&gt;
 *               &lt;enumeration value="DISCOVER_OBJECT_ACTIVITY"/&gt;
 *               &lt;enumeration value="DISCOVER_OBJECT_MEMORY_USAGE"/&gt;
 *               &lt;enumeration value="DISCOVER_STORAGE_TABLES"/&gt;
 *               &lt;enumeration value="DISCOVER_STORAGE_TABLE_COLUMNS"/&gt;
 *               &lt;enumeration value="DISCOVER_STORAGE_TABLE_COLUMN_SEGMENTS"/&gt;
 *               &lt;enumeration value="DISCOVER_CSDL_METADATA"/&gt;
 *               &lt;enumeration value="DISCOVER_CALC_DEPENDENCY"/&gt;
 *               &lt;enumeration value="MDSCHEMA_FUNCTIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_RING_BUFFERS"/&gt;
 *               &lt;enumeration value="DISCOVER_XEVENT_TRACE_DEFINITION"/&gt;
 *               &lt;enumeration value="DISCOVER_XEVENT_PACKAGES"/&gt;
 *               &lt;enumeration value="DISCOVER_XEVENT_OBJECTS"/&gt;
 *               &lt;enumeration value="DISCOVER_XEVENT_OBJECT_COLUMNS"/&gt;
 *               &lt;enumeration value="DISCOVER_XEVENT_SESSIONS"/&gt;
 *               &lt;enumeration value="DISCOVER_XEVENT_SESSION_TARGETS"/&gt;
 *               &lt;enumeration value="DISCOVER_MEM_STATS"/&gt;
 *               &lt;enumeration value="DISCOVER_DB_MEM_STATS"/&gt;
 *               &lt;enumeration value="DISCOVER_OBJECT_COUNTERS"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Restrictions"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="RestrictionList" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;any maxOccurs="unbounded" minOccurs="0"/&gt;
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
 *         &lt;element name="Properties" type="{urn:schemas-microsoft-com:xml-analysis}Properties"/&gt;
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
    "requestType",
    "restrictions",
    "properties"
})
@XmlRootElement(name = "Discover")
public class Discover {

    @XmlElement(name = "RequestType", required = true)
    protected String requestType;
    @XmlElement(name = "Restrictions", required = true)
    protected Discover.Restrictions restrictions;
    @XmlElement(name = "Properties", required = true)
    protected Properties properties;

    /**
     * Gets the value of the requestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the value of the requestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestType(String value) {
        this.requestType = value;
    }

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link Discover.Restrictions }
     *     
     */
    public Discover.Restrictions getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Discover.Restrictions }
     *     
     */
    public void setRestrictions(Discover.Restrictions value) {
        this.restrictions = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setProperties(Properties value) {
        this.properties = value;
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
     *         &lt;element name="RestrictionList" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;any maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "restrictionList"
    })
    public static class Restrictions {

        @XmlElementRef(name = "RestrictionList", type = JAXBElement.class, required = false)
        protected JAXBElement<Discover.Restrictions.RestrictionList> restrictionList;

        /**
         * Gets the value of the restrictionList property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Discover.Restrictions.RestrictionList }{@code >}
         *     
         */
        public JAXBElement<Discover.Restrictions.RestrictionList> getRestrictionList() {
            return restrictionList;
        }

        /**
         * Sets the value of the restrictionList property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Discover.Restrictions.RestrictionList }{@code >}
         *     
         */
        public void setRestrictionList(JAXBElement<Discover.Restrictions.RestrictionList> value) {
            this.restrictionList = value;
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
         *         &lt;any maxOccurs="unbounded" minOccurs="0"/&gt;
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
            "any"
        })
        public static class RestrictionList {

            @XmlAnyElement(lax = true)
            protected List<java.lang.Object> any;

            /**
             * Gets the value of the any property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the any property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAny().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link java.lang.Object }
             * 
             * 
             */
            public List<java.lang.Object> getAny() {
                if (any == null) {
                    any = new ArrayList<java.lang.Object>();
                }
                return this.any;
            }

        }

    }

}
