
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Event complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Event"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="EventID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Columns" type="{urn:schemas-microsoft-com:xml-analysis}EventColumnID"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {

})
public class Event {

  @XmlElement(name = "EventID", required = true)
  protected String eventID;
  @XmlElement(name = "Columns", required = true)
  protected EventColumnID columns;

  /**
   * Gets the value of the eventID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getEventID() {
    return eventID;
  }

  /**
   * Sets the value of the eventID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setEventID(String value) {
    this.eventID = value;
  }

  public boolean isSetEventID() {
    return (this.eventID != null);
  }

  /**
   * Gets the value of the columns property.
   * 
   * @return possible object is {@link EventColumnID }
   * 
   */
  public EventColumnID getColumns() {
    return columns;
  }

  /**
   * Sets the value of the columns property.
   * 
   * @param value allowed object is {@link EventColumnID }
   * 
   */
  public void setColumns(EventColumnID value) {
    this.columns = value;
  }

  public boolean isSetColumns() {
    return (this.columns != null);
  }

}
