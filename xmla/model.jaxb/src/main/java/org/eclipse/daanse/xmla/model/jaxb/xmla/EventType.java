
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.engine300_300.XEvent;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

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
 *       &lt;choice&gt;
 *         &lt;element name="Events"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Event" type="{urn:schemas-microsoft-com:xml-analysis}Event" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}XEvent" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "events", "xEvent" })
@XmlRootElement(name = "EventType")
public class EventType {

  @XmlElement(name = "Events")
  protected EventType.Events events;
  @XmlElement(name = "XEvent", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300")
  protected XEvent xEvent;

  /**
   * Gets the value of the events property.
   * 
   * @return possible object is {@link EventType.Events }
   * 
   */
  public EventType.Events getEvents() {
    return events;
  }

  /**
   * Sets the value of the events property.
   * 
   * @param value allowed object is {@link EventType.Events }
   * 
   */
  public void setEvents(EventType.Events value) {
    this.events = value;
  }

  public boolean isSetEvents() {
    return (this.events != null);
  }

  /**
   * Gets the value of the xEvent property.
   * 
   * @return possible object is {@link XEvent }
   * 
   */
  public XEvent getXEvent() {
    return xEvent;
  }

  /**
   * Sets the value of the xEvent property.
   * 
   * @param value allowed object is {@link XEvent }
   * 
   */
  public void setXEvent(XEvent value) {
    this.xEvent = value;
  }

  public boolean isSetXEvent() {
    return (this.xEvent != null);
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
   *         &lt;element name="Event" type="{urn:schemas-microsoft-com:xml-analysis}Event" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "event" })
  public static class Events {

    @XmlElement(name = "Event")
    protected List<Event> event;

    /**
     * Gets the value of the event property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the event property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Event }
     * 
     * 
     */
    public List<Event> getEvent() {
      if (event == null) {
        event = new ArrayList<Event>();
      }
      return this.event;
    }

    public boolean isSetEvent() {
      return ((this.event != null) && (!this.event.isEmpty()));
    }

    public void unsetEvent() {
      this.event = null;
    }

  }

}
