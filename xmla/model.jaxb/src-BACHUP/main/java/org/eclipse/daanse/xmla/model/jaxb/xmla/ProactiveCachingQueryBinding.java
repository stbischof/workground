
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProactiveCachingQueryBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProactiveCachingQueryBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}ProactiveCachingBinding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="RefreshInterval" type="{http://www.w3.org/2001/XMLSchema}duration"/&gt;
 *         &lt;element name="QueryNotifications"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="QueryNotification" type="{urn:schemas-microsoft-com:xml-analysis}QueryNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProactiveCachingQueryBinding", propOrder = {
    "refreshInterval",
    "queryNotifications"
})
public class ProactiveCachingQueryBinding
    extends ProactiveCachingBinding
{

    @XmlElement(name = "RefreshInterval", required = true)
    protected Duration refreshInterval;
    @XmlElement(name = "QueryNotifications", required = true)
    protected ProactiveCachingQueryBinding.QueryNotifications queryNotifications;

    /**
     * Gets the value of the refreshInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getRefreshInterval() {
        return refreshInterval;
    }

    /**
     * Sets the value of the refreshInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setRefreshInterval(Duration value) {
        this.refreshInterval = value;
    }

    public boolean isSetRefreshInterval() {
        return (this.refreshInterval!= null);
    }

    /**
     * Gets the value of the queryNotifications property.
     * 
     * @return
     *     possible object is
     *     {@link ProactiveCachingQueryBinding.QueryNotifications }
     *     
     */
    public ProactiveCachingQueryBinding.QueryNotifications getQueryNotifications() {
        return queryNotifications;
    }

    /**
     * Sets the value of the queryNotifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProactiveCachingQueryBinding.QueryNotifications }
     *     
     */
    public void setQueryNotifications(ProactiveCachingQueryBinding.QueryNotifications value) {
        this.queryNotifications = value;
    }

    public boolean isSetQueryNotifications() {
        return (this.queryNotifications!= null);
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
     *         &lt;element name="QueryNotification" type="{urn:schemas-microsoft-com:xml-analysis}QueryNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "queryNotification"
    })
    public static class QueryNotifications {

        @XmlElement(name = "QueryNotification")
        protected List<QueryNotification> queryNotification;

        /**
         * Gets the value of the queryNotification property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the queryNotification property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getQueryNotification().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link QueryNotification }
         * 
         * 
         */
        public List<QueryNotification> getQueryNotification() {
            if (queryNotification == null) {
                queryNotification = new ArrayList<QueryNotification>();
            }
            return this.queryNotification;
        }

        public boolean isSetQueryNotification() {
            return ((this.queryNotification!= null)&&(!this.queryNotification.isEmpty()));
        }

        public void unsetQueryNotification() {
            this.queryNotification = null;
        }

    }

}
