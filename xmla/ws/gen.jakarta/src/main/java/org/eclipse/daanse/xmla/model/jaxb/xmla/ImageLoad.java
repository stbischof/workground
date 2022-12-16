
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImageLoad complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImageLoad"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImagePath" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImageUrl" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImageUniqueID" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2010/engine/200/200}ImageVersion" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100}ReadWriteMode"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}DbStorageLocation" minOccurs="0"/&gt;
 *         &lt;element name="DatabaseName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Data" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataBlock" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "ImageLoad", propOrder = {

})
public class ImageLoad {

    @XmlElement(name = "ImagePath", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imagePath;
    @XmlElement(name = "ImageUrl", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageUrl;
    @XmlElement(name = "ImageUniqueID", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageUniqueID;
    @XmlElement(name = "ImageVersion", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageVersion;
    @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100", required = true)
    protected String readWriteMode;
    @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
    protected String dbStorageLocation;
    @XmlElement(name = "DatabaseName", required = true)
    protected String databaseName;
    @XmlElement(name = "DatabaseID", required = true)
    protected String databaseID;
    @XmlElement(name = "Data")
    protected ImageLoad.Data data;

    /**
     * Gets the value of the imagePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the value of the imagePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagePath(String value) {
        this.imagePath = value;
    }

    /**
     * Gets the value of the imageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the value of the imageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    /**
     * Gets the value of the imageUniqueID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageUniqueID() {
        return imageUniqueID;
    }

    /**
     * Sets the value of the imageUniqueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageUniqueID(String value) {
        this.imageUniqueID = value;
    }

    /**
     * Gets the value of the imageVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageVersion() {
        return imageVersion;
    }

    /**
     * Sets the value of the imageVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageVersion(String value) {
        this.imageVersion = value;
    }

    /**
     * Gets the value of the readWriteMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadWriteMode() {
        return readWriteMode;
    }

    /**
     * Sets the value of the readWriteMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadWriteMode(String value) {
        this.readWriteMode = value;
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
     * Gets the value of the databaseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Sets the value of the databaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseName(String value) {
        this.databaseName = value;
    }

    /**
     * Gets the value of the databaseID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseID() {
        return databaseID;
    }

    /**
     * Sets the value of the databaseID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseID(String value) {
        this.databaseID = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link ImageLoad.Data }
     *     
     */
    public ImageLoad.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageLoad.Data }
     *     
     */
    public void setData(ImageLoad.Data value) {
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
     *         &lt;element name="DataBlock" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "dataBlock"
    })
    public static class Data {

        @XmlElement(name = "DataBlock")
        protected List<String> dataBlock;

        /**
         * Gets the value of the dataBlock property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the dataBlock property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDataBlock().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getDataBlock() {
            if (dataBlock == null) {
                dataBlock = new ArrayList<String>();
            }
            return this.dataBlock;
        }

    }

}
