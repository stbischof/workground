
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Restore complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Restore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DatabaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *         &lt;element name="AllowOverwrite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}DbStorageLocation" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100}ReadWriteMode" minOccurs="0"/&gt;
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
@XmlType(name = "Restore", propOrder = {

})
public class Restore {

  @XmlElement(name = "DatabaseName")
  protected String databaseName;
  @XmlElement(name = "DatabaseID")
  protected String databaseID;
  @XmlElement(name = "File", required = true)
  protected String file;
  @XmlElement(name = "Security")
  protected String security;
  @XmlElement(name = "AllowOverwrite")
  protected Boolean allowOverwrite;
  @XmlElement(name = "Password")
  protected String password;
  @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
  protected String dbStorageLocation;
  @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100")
  protected String readWriteMode;
  @XmlElement(name = "Locations")
  protected Restore.Locations locations;

  /**
   * Gets the value of the databaseName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDatabaseName() {
    return databaseName;
  }

  /**
   * Sets the value of the databaseName property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDatabaseName(String value) {
    this.databaseName = value;
  }

  public boolean isSetDatabaseName() {
    return (this.databaseName != null);
  }

  /**
   * Gets the value of the databaseID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDatabaseID() {
    return databaseID;
  }

  /**
   * Sets the value of the databaseID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDatabaseID(String value) {
    this.databaseID = value;
  }

  public boolean isSetDatabaseID() {
    return (this.databaseID != null);
  }

  /**
   * Gets the value of the file property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getFile() {
    return file;
  }

  /**
   * Sets the value of the file property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setFile(String value) {
    this.file = value;
  }

  public boolean isSetFile() {
    return (this.file != null);
  }

  /**
   * Gets the value of the security property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSecurity() {
    return security;
  }

  /**
   * Sets the value of the security property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSecurity(String value) {
    this.security = value;
  }

  public boolean isSetSecurity() {
    return (this.security != null);
  }

  /**
   * Gets the value of the allowOverwrite property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isAllowOverwrite() {
    return allowOverwrite;
  }

  /**
   * Sets the value of the allowOverwrite property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setAllowOverwrite(Boolean value) {
    this.allowOverwrite = value;
  }

  public boolean isSetAllowOverwrite() {
    return (this.allowOverwrite != null);
  }

  /**
   * Gets the value of the password property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the value of the password property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPassword(String value) {
    this.password = value;
  }

  public boolean isSetPassword() {
    return (this.password != null);
  }

  /**
   * Gets the value of the dbStorageLocation property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDbStorageLocation() {
    return dbStorageLocation;
  }

  /**
   * Sets the value of the dbStorageLocation property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDbStorageLocation(String value) {
    this.dbStorageLocation = value;
  }

  public boolean isSetDbStorageLocation() {
    return (this.dbStorageLocation != null);
  }

  /**
   * Gets the value of the readWriteMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getReadWriteMode() {
    return readWriteMode;
  }

  /**
   * Sets the value of the readWriteMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setReadWriteMode(String value) {
    this.readWriteMode = value;
  }

  public boolean isSetReadWriteMode() {
    return (this.readWriteMode != null);
  }

  /**
   * Gets the value of the locations property.
   * 
   * @return possible object is {@link Restore.Locations }
   * 
   */
  public Restore.Locations getLocations() {
    return locations;
  }

  /**
   * Sets the value of the locations property.
   * 
   * @param value allowed object is {@link Restore.Locations }
   * 
   */
  public void setLocations(Restore.Locations value) {
    this.locations = value;
  }

  public boolean isSetLocations() {
    return (this.locations != null);
  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within
   * this class.
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
  @XmlType(name = "", propOrder = { "location" })
  public static class Locations {

    @XmlElement(name = "Location")
    protected List<Location> location;

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Location }
     * 
     * 
     */
    public List<Location> getLocation() {
      if (location == null) {
        location = new ArrayList<Location>();
      }
      return this.location;
    }

    public boolean isSetLocation() {
      return ((this.location != null) && (!this.location.isEmpty()));
    }

    public void unsetLocation() {
      this.location = null;
    }

  }

}
