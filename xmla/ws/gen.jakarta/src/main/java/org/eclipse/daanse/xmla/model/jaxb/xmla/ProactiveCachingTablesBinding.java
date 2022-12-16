
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProactiveCachingTablesBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProactiveCachingTablesBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}ProactiveCachingObjectNotificationBinding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="NotificationTechnique" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Client"/&gt;
 *               &lt;enumeration value="Server"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TableNotifications"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TableNotification" type="{urn:schemas-microsoft-com:xml-analysis}TableNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "ProactiveCachingTablesBinding", propOrder = {
    "notificationTechnique",
    "tableNotifications"
})
public class ProactiveCachingTablesBinding
    extends ProactiveCachingObjectNotificationBinding
{

    @XmlElement(name = "NotificationTechnique")
    protected String notificationTechnique;
    @XmlElement(name = "TableNotifications", required = true)
    protected ProactiveCachingTablesBinding.TableNotifications tableNotifications;

    /**
     * Gets the value of the notificationTechnique property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationTechnique() {
        return notificationTechnique;
    }

    /**
     * Sets the value of the notificationTechnique property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationTechnique(String value) {
        this.notificationTechnique = value;
    }

    /**
     * Gets the value of the tableNotifications property.
     * 
     * @return
     *     possible object is
     *     {@link ProactiveCachingTablesBinding.TableNotifications }
     *     
     */
    public ProactiveCachingTablesBinding.TableNotifications getTableNotifications() {
        return tableNotifications;
    }

    /**
     * Sets the value of the tableNotifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProactiveCachingTablesBinding.TableNotifications }
     *     
     */
    public void setTableNotifications(ProactiveCachingTablesBinding.TableNotifications value) {
        this.tableNotifications = value;
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
     *         &lt;element name="TableNotification" type="{urn:schemas-microsoft-com:xml-analysis}TableNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "tableNotification"
    })
    public static class TableNotifications {

        @XmlElement(name = "TableNotification")
        protected List<TableNotification> tableNotification;

        /**
         * Gets the value of the tableNotification property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the tableNotification property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTableNotification().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TableNotification }
         * 
         * 
         */
        public List<TableNotification> getTableNotification() {
            if (tableNotification == null) {
                tableNotification = new ArrayList<TableNotification>();
            }
            return this.tableNotification;
        }

    }

}
