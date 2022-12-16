
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for TraceEvent complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="TraceEvent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EventColumnList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="EventColumn" type="{urn:schemas-microsoft-com:xml-analysis}EventColumn" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "TraceEvent", propOrder = {

})
public class TraceEvent {

  @XmlElement(name = "ID", required = true)
  protected BigInteger id;
  @XmlElement(name = "Name")
  protected String name;
  @XmlElement(name = "Description")
  protected String description;
  @XmlElement(name = "EventColumnList", required = true)
  protected TraceEvent.EventColumnList eventColumnList;

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getID() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link BigInteger }
   * 
   */
  public void setID(BigInteger value) {
    this.id = value;
  }

  public boolean isSetID() {
    return (this.id != null);
  }

  /**
   * Gets the value of the name property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setName(String value) {
    this.name = value;
  }

  public boolean isSetName() {
    return (this.name != null);
  }

  /**
   * Gets the value of the description property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescription(String value) {
    this.description = value;
  }

  public boolean isSetDescription() {
    return (this.description != null);
  }

  /**
   * Gets the value of the eventColumnList property.
   * 
   * @return possible object is {@link TraceEvent.EventColumnList }
   * 
   */
  public TraceEvent.EventColumnList getEventColumnList() {
    return eventColumnList;
  }

  /**
   * Sets the value of the eventColumnList property.
   * 
   * @param value allowed object is {@link TraceEvent.EventColumnList }
   * 
   */
  public void setEventColumnList(TraceEvent.EventColumnList value) {
    this.eventColumnList = value;
  }

  public boolean isSetEventColumnList() {
    return (this.eventColumnList != null);
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
   *         &lt;element name="EventColumn" type="{urn:schemas-microsoft-com:xml-analysis}EventColumn" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "eventColumn" })
  public static class EventColumnList {

    @XmlElement(name = "EventColumn")
    protected List<EventColumn> eventColumn;

    /**
     * Gets the value of the eventColumn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the eventColumn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getEventColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link EventColumn }
     * 
     * 
     */
    public List<EventColumn> getEventColumn() {
      if (eventColumn == null) {
        eventColumn = new ArrayList<EventColumn>();
      }
      return this.eventColumn;
    }

    public boolean isSetEventColumn() {
      return ((this.eventColumn != null) && (!this.eventColumn.isEmpty()));
    }

    public void unsetEventColumn() {
      this.eventColumn = null;
    }

  }

}
