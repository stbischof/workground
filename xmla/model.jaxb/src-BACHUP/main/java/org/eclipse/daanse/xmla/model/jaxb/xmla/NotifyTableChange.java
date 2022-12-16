
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NotifyTableChange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotifyTableChange"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
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
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotifyTableChange", propOrder = {

})
public class NotifyTableChange {

    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "TableNotifications", required = true)
    protected NotifyTableChange.TableNotifications tableNotifications;

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

    public boolean isSetObject() {
        return (this.object!= null);
    }

    /**
     * Gets the value of the tableNotifications property.
     * 
     * @return
     *     possible object is
     *     {@link NotifyTableChange.TableNotifications }
     *     
     */
    public NotifyTableChange.TableNotifications getTableNotifications() {
        return tableNotifications;
    }

    /**
     * Sets the value of the tableNotifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotifyTableChange.TableNotifications }
     *     
     */
    public void setTableNotifications(NotifyTableChange.TableNotifications value) {
        this.tableNotifications = value;
    }

    public boolean isSetTableNotifications() {
        return (this.tableNotifications!= null);
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

        public boolean isSetTableNotification() {
            return ((this.tableNotification!= null)&&(!this.tableNotification.isEmpty()));
        }

        public void unsetTableNotification() {
            this.tableNotification = null;
        }

    }

}
