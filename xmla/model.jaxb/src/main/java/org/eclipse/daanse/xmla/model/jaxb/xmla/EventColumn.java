
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
 * Java class for EventColumn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="EventColumn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="EventColumnSubclassList" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="EventColumnSubclass" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/all&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "EventColumn", propOrder = {

})
public class EventColumn {

  @XmlElement(name = "ID", required = true)
  protected BigInteger id;
  @XmlElement(name = "EventColumnSubclassList")
  protected EventColumn.EventColumnSubclassList eventColumnSubclassList;

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
   * Gets the value of the eventColumnSubclassList property.
   * 
   * @return possible object is {@link EventColumn.EventColumnSubclassList }
   * 
   */
  public EventColumn.EventColumnSubclassList getEventColumnSubclassList() {
    return eventColumnSubclassList;
  }

  /**
   * Sets the value of the eventColumnSubclassList property.
   * 
   * @param value allowed object is {@link EventColumn.EventColumnSubclassList }
   * 
   */
  public void setEventColumnSubclassList(EventColumn.EventColumnSubclassList value) {
    this.eventColumnSubclassList = value;
  }

  public boolean isSetEventColumnSubclassList() {
    return (this.eventColumnSubclassList != null);
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
   *         &lt;element name="EventColumnSubclass" maxOccurs="unbounded" minOccurs="0"&gt;
   *           &lt;complexType&gt;
   *             &lt;complexContent&gt;
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *                 &lt;all&gt;
   *                   &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
   *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *                 &lt;/all&gt;
   *               &lt;/restriction&gt;
   *             &lt;/complexContent&gt;
   *           &lt;/complexType&gt;
   *         &lt;/element&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "eventColumnSubclass" })
  public static class EventColumnSubclassList {

    @XmlElement(name = "EventColumnSubclass")
    protected List<EventColumn.EventColumnSubclassList.EventColumnSubclass> eventColumnSubclass;

    /**
     * Gets the value of the eventColumnSubclass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the eventColumnSubclass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getEventColumnSubclass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventColumn.EventColumnSubclassList.EventColumnSubclass }
     * 
     * 
     */
    public List<EventColumn.EventColumnSubclassList.EventColumnSubclass> getEventColumnSubclass() {
      if (eventColumnSubclass == null) {
        eventColumnSubclass = new ArrayList<EventColumn.EventColumnSubclassList.EventColumnSubclass>();
      }
      return this.eventColumnSubclass;
    }

    public boolean isSetEventColumnSubclass() {
      return ((this.eventColumnSubclass != null) && (!this.eventColumnSubclass.isEmpty()));
    }

    public void unsetEventColumnSubclass() {
      this.eventColumnSubclass = null;
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
     *       &lt;all&gt;
     *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class EventColumnSubclass {

      @XmlElement(name = "ID", required = true)
      protected BigInteger id;
      @XmlElement(required = true)
      protected String name;

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

    }

  }

}
