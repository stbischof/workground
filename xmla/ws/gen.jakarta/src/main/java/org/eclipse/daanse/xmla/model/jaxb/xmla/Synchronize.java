
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Synchronize complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Synchronize"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Source"/&gt;
 *         &lt;element name="SynchronizeSecurity" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="SkipMembership"/&gt;
 *               &lt;enumeration value="CopyAll"/&gt;
 *               &lt;enumeration value="IgnoreSecurity"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ApplyCompression" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}DbStorageLocation" minOccurs="0"/&gt;
 *         &lt;element name="Locations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Location" type="{urn:schemas-microsoft-com:xml-analysis}Location" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "Synchronize", propOrder = {

})
public class Synchronize {

    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlElement(name = "SynchronizeSecurity")
    protected String synchronizeSecurity;
    @XmlElement(name = "ApplyCompression")
    protected Boolean applyCompression;
    @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
    protected String dbStorageLocation;
    @XmlElement(name = "Locations")
    protected Synchronize.Locations locations;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the synchronizeSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynchronizeSecurity() {
        return synchronizeSecurity;
    }

    /**
     * Sets the value of the synchronizeSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynchronizeSecurity(String value) {
        this.synchronizeSecurity = value;
    }

    /**
     * Gets the value of the applyCompression property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyCompression() {
        return applyCompression;
    }

    /**
     * Sets the value of the applyCompression property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyCompression(Boolean value) {
        this.applyCompression = value;
    }

    /**
     * Gets the value of the dbStorageLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbStorageLocation() {
        return dbStorageLocation;
    }

    /**
     * Sets the value of the dbStorageLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbStorageLocation(String value) {
        this.dbStorageLocation = value;
    }

    /**
     * Gets the value of the locations property.
     * 
     * @return
     *     possible object is
     *     {@link Synchronize.Locations }
     *     
     */
    public Synchronize.Locations getLocations() {
        return locations;
    }

    /**
     * Sets the value of the locations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Synchronize.Locations }
     *     
     */
    public void setLocations(Synchronize.Locations value) {
        this.locations = value;
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
     *         &lt;element name="Location" type="{urn:schemas-microsoft-com:xml-analysis}Location" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "location"
    })
    public static class Locations {

        @XmlElement(name = "Location")
        protected List<Location> location;

        /**
         * Gets the value of the location property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the location property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLocation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Location }
         * 
         * 
         */
        public List<Location> getLocation() {
            if (location == null) {
                location = new ArrayList<Location>();
            }
            return this.location;
        }

    }

}
