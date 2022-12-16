
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Backup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Backup"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *         &lt;element name="File" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Security" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="SkipMembership"/&gt;
 *               &lt;enumeration value="CopyAll"/&gt;
 *               &lt;enumeration value="IgnoreSecurity"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ApplyCompression" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AllowOverwrite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BackupRemotePartitions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Locations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Location" type="{urn:schemas-microsoft-com:xml-analysis}Location_Backup" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "Backup", propOrder = {

})
public class Backup {

    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "File", required = true)
    protected String file;
    @XmlElement(name = "Security")
    protected String security;
    @XmlElement(name = "ApplyCompression")
    protected Boolean applyCompression;
    @XmlElement(name = "AllowOverwrite")
    protected Boolean allowOverwrite;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "BackupRemotePartitions")
    protected Boolean backupRemotePartitions;
    @XmlElement(name = "Locations")
    protected Backup.Locations locations;

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
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFile(String value) {
        this.file = value;
    }

    public boolean isSetFile() {
        return (this.file!= null);
    }

    /**
     * Gets the value of the security property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurity() {
        return security;
    }

    /**
     * Sets the value of the security property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurity(String value) {
        this.security = value;
    }

    public boolean isSetSecurity() {
        return (this.security!= null);
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

    public boolean isSetApplyCompression() {
        return (this.applyCompression!= null);
    }

    /**
     * Gets the value of the allowOverwrite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowOverwrite() {
        return allowOverwrite;
    }

    /**
     * Sets the value of the allowOverwrite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowOverwrite(Boolean value) {
        this.allowOverwrite = value;
    }

    public boolean isSetAllowOverwrite() {
        return (this.allowOverwrite!= null);
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    public boolean isSetPassword() {
        return (this.password!= null);
    }

    /**
     * Gets the value of the backupRemotePartitions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBackupRemotePartitions() {
        return backupRemotePartitions;
    }

    /**
     * Sets the value of the backupRemotePartitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBackupRemotePartitions(Boolean value) {
        this.backupRemotePartitions = value;
    }

    public boolean isSetBackupRemotePartitions() {
        return (this.backupRemotePartitions!= null);
    }

    /**
     * Gets the value of the locations property.
     * 
     * @return
     *     possible object is
     *     {@link Backup.Locations }
     *     
     */
    public Backup.Locations getLocations() {
        return locations;
    }

    /**
     * Sets the value of the locations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Backup.Locations }
     *     
     */
    public void setLocations(Backup.Locations value) {
        this.locations = value;
    }

    public boolean isSetLocations() {
        return (this.locations!= null);
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
     *         &lt;element name="Location" type="{urn:schemas-microsoft-com:xml-analysis}Location_Backup" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<LocationBackup> location;

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
         * {@link LocationBackup }
         * 
         * 
         */
        public List<LocationBackup> getLocation() {
            if (location == null) {
                location = new ArrayList<LocationBackup>();
            }
            return this.location;
        }

        public boolean isSetLocation() {
            return ((this.location!= null)&&(!this.location.isEmpty()));
        }

        public void unsetLocation() {
            this.location = null;
        }

    }

}
