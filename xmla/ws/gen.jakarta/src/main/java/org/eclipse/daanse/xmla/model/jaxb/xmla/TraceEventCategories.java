
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trace_Event_Categories complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trace_Event_Categories"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="EventCategory"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="EventList"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="Event" type="{urn:schemas-microsoft-com:xml-analysis}TraceEvent" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/all&gt;
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
@XmlType(name = "Trace_Event_Categories", propOrder = {
    "data"
})
public class TraceEventCategories {

    @XmlElement(name = "Data", required = true)
    protected TraceEventCategories.Data data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link TraceEventCategories.Data }
     *     
     */
    public TraceEventCategories.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link TraceEventCategories.Data }
     *     
     */
    public void setData(TraceEventCategories.Data value) {
        this.data = value;
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
     *         &lt;element name="EventCategory"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;all&gt;
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="EventList"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="Event" type="{urn:schemas-microsoft-com:xml-analysis}TraceEvent" maxOccurs="unbounded" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/all&gt;
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
        "eventCategory"
    })
    public static class Data {

        @XmlElement(name = "EventCategory", required = true)
        protected TraceEventCategories.Data.EventCategory eventCategory;

        /**
         * Gets the value of the eventCategory property.
         * 
         * @return
         *     possible object is
         *     {@link TraceEventCategories.Data.EventCategory }
         *     
         */
        public TraceEventCategories.Data.EventCategory getEventCategory() {
            return eventCategory;
        }

        /**
         * Sets the value of the eventCategory property.
         * 
         * @param value
         *     allowed object is
         *     {@link TraceEventCategories.Data.EventCategory }
         *     
         */
        public void setEventCategory(TraceEventCategories.Data.EventCategory value) {
            this.eventCategory = value;
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
         *       &lt;all&gt;
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="EventList"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="Event" type="{urn:schemas-microsoft-com:xml-analysis}TraceEvent" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        @XmlType(name = "", propOrder = {

        })
        public static class EventCategory {

            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Type")
            protected String type;
            @XmlElement(name = "Description")
            protected String description;
            @XmlElement(name = "EventList", required = true)
            protected TraceEventCategories.Data.EventCategory.EventList eventList;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the eventList property.
             * 
             * @return
             *     possible object is
             *     {@link TraceEventCategories.Data.EventCategory.EventList }
             *     
             */
            public TraceEventCategories.Data.EventCategory.EventList getEventList() {
                return eventList;
            }

            /**
             * Sets the value of the eventList property.
             * 
             * @param value
             *     allowed object is
             *     {@link TraceEventCategories.Data.EventCategory.EventList }
             *     
             */
            public void setEventList(TraceEventCategories.Data.EventCategory.EventList value) {
                this.eventList = value;
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
             *         &lt;element name="Event" type="{urn:schemas-microsoft-com:xml-analysis}TraceEvent" maxOccurs="unbounded" minOccurs="0"/&gt;
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
                "event"
            })
            public static class EventList {

                @XmlElement(name = "Event")
                protected List<TraceEvent> event;

                /**
                 * Gets the value of the event property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the Jakarta XML Binding object.
                 * This is why there is not a <CODE>set</CODE> method for the event property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getEvent().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TraceEvent }
                 * 
                 * 
                 */
                public List<TraceEvent> getEvent() {
                    if (event == null) {
                        event = new ArrayList<TraceEvent>();
                    }
                    return this.event;
                }

            }

        }

    }

}
