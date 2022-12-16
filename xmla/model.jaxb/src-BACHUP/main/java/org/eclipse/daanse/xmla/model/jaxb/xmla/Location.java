
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Location"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Location_Backup"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DataSourceType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Remote"/&gt;
 *               &lt;enumeration value="Local"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ConnectionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Folders" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Folder" type="{urn:schemas-microsoft-com:xml-analysis}Folder" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
    "dataSourceType",
    "connectionString",
    "folders"
})
public class Location
    extends LocationBackup
{

    @XmlElement(name = "DataSourceType")
    protected String dataSourceType;
    @XmlElement(name = "ConnectionString")
    protected String connectionString;
    @XmlElement(name = "Folders")
    protected Location.Folders folders;

    /**
     * Gets the value of the dataSourceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceType() {
        return dataSourceType;
    }

    /**
     * Sets the value of the dataSourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceType(String value) {
        this.dataSourceType = value;
    }

    public boolean isSetDataSourceType() {
        return (this.dataSourceType!= null);
    }

    /**
     * Gets the value of the connectionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Sets the value of the connectionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionString(String value) {
        this.connectionString = value;
    }

    public boolean isSetConnectionString() {
        return (this.connectionString!= null);
    }

    /**
     * Gets the value of the folders property.
     * 
     * @return
     *     possible object is
     *     {@link Location.Folders }
     *     
     */
    public Location.Folders getFolders() {
        return folders;
    }

    /**
     * Sets the value of the folders property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location.Folders }
     *     
     */
    public void setFolders(Location.Folders value) {
        this.folders = value;
    }

    public boolean isSetFolders() {
        return (this.folders!= null);
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
     *         &lt;element name="Folder" type="{urn:schemas-microsoft-com:xml-analysis}Folder" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "folder"
    })
    public static class Folders {

        @XmlElement(name = "Folder")
        protected List<Folder> folder;

        /**
         * Gets the value of the folder property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the folder property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFolder().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Folder }
         * 
         * 
         */
        public List<Folder> getFolder() {
            if (folder == null) {
                folder = new ArrayList<Folder>();
            }
            return this.folder;
        }

        public boolean isSetFolder() {
            return ((this.folder!= null)&&(!this.folder.isEmpty()));
        }

        public void unsetFolder() {
            this.folder = null;
        }

    }

}
