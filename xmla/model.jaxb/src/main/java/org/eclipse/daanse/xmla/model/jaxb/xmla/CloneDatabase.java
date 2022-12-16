
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CloneDatabase complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CloneDatabase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Object"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DatabaseID" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Target"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}DbStorageLocation" minOccurs="0"/&gt;
 *                   &lt;element name="DatabaseName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "CloneDatabase", propOrder = {

})
public class CloneDatabase {

  @XmlElement(name = "Object", required = true)
  protected CloneDatabase.Object object;
  @XmlElement(name = "Target", required = true)
  protected CloneDatabase.Target target;

  /**
   * Gets the value of the object property.
   * 
   * @return possible object is {@link CloneDatabase.Object }
   * 
   */
  public CloneDatabase.Object getObject() {
    return object;
  }

  /**
   * Sets the value of the object property.
   * 
   * @param value allowed object is {@link CloneDatabase.Object }
   * 
   */
  public void setObject(CloneDatabase.Object value) {
    this.object = value;
  }

  public boolean isSetObject() {
    return (this.object != null);
  }

  /**
   * Gets the value of the target property.
   * 
   * @return possible object is {@link CloneDatabase.Target }
   * 
   */
  public CloneDatabase.Target getTarget() {
    return target;
  }

  /**
   * Sets the value of the target property.
   * 
   * @param value allowed object is {@link CloneDatabase.Target }
   * 
   */
  public void setTarget(CloneDatabase.Target value) {
    this.target = value;
  }

  public boolean isSetTarget() {
    return (this.target != null);
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
   *         &lt;element name="DatabaseID" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "databaseID" })
  public static class Object {

    @XmlElement(name = "DatabaseID", required = true)
    protected ObjectReference databaseID;

    /**
     * Gets the value of the databaseID property.
     * 
     * @return possible object is {@link ObjectReference }
     * 
     */
    public ObjectReference getDatabaseID() {
      return databaseID;
    }

    /**
     * Sets the value of the databaseID property.
     * 
     * @param value allowed object is {@link ObjectReference }
     * 
     */
    public void setDatabaseID(ObjectReference value) {
      this.databaseID = value;
    }

    public boolean isSetDatabaseID() {
      return (this.databaseID != null);
    }

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
   *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2008/engine/100/100}DbStorageLocation" minOccurs="0"/&gt;
   *         &lt;element name="DatabaseName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *         &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "dbStorageLocation", "databaseName", "databaseID" })
  public static class Target {

    @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
    protected String dbStorageLocation;
    @XmlElement(name = "DatabaseName", required = true)
    protected String databaseName;
    @XmlElement(name = "DatabaseID", required = true)
    protected String databaseID;

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

  }

}
