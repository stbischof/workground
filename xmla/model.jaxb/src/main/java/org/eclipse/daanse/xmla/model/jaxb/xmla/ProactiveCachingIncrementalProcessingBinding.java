
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ProactiveCachingIncrementalProcessingBinding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ProactiveCachingIncrementalProcessingBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}ProactiveCachingBinding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="RefreshInterval" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="IncrementalProcessingNotifications"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="IncrementalProcessingNotification" type="{urn:schemas-microsoft-com:xml-analysis}IncrementalProcessingNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProactiveCachingIncrementalProcessingBinding", propOrder = { "refreshInterval",
    "incrementalProcessingNotifications" })
public class ProactiveCachingIncrementalProcessingBinding extends ProactiveCachingBinding {

  @XmlElement(name = "RefreshInterval")
  protected Duration refreshInterval;
  @XmlElement(name = "IncrementalProcessingNotifications", required = true)
  protected ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications incrementalProcessingNotifications;

  /**
   * Gets the value of the refreshInterval property.
   * 
   * @return possible object is {@link Duration }
   * 
   */
  public Duration getRefreshInterval() {
    return refreshInterval;
  }

  /**
   * Sets the value of the refreshInterval property.
   * 
   * @param value allowed object is {@link Duration }
   * 
   */
  public void setRefreshInterval(Duration value) {
    this.refreshInterval = value;
  }

  public boolean isSetRefreshInterval() {
    return (this.refreshInterval != null);
  }

  /**
   * Gets the value of the incrementalProcessingNotifications property.
   * 
   * @return possible object is
   *         {@link ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications }
   * 
   */
  public ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications getIncrementalProcessingNotifications() {
    return incrementalProcessingNotifications;
  }

  /**
   * Sets the value of the incrementalProcessingNotifications property.
   * 
   * @param value allowed object is
   *              {@link ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications }
   * 
   */
  public void setIncrementalProcessingNotifications(
      ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications value) {
    this.incrementalProcessingNotifications = value;
  }

  public boolean isSetIncrementalProcessingNotifications() {
    return (this.incrementalProcessingNotifications != null);
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
   *         &lt;element name="IncrementalProcessingNotification" type="{urn:schemas-microsoft-com:xml-analysis}IncrementalProcessingNotification" maxOccurs="unbounded" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "incrementalProcessingNotification" })
  public static class IncrementalProcessingNotifications {

    @XmlElement(name = "IncrementalProcessingNotification")
    protected List<IncrementalProcessingNotification> incrementalProcessingNotification;

    /**
     * Gets the value of the incrementalProcessingNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present
     * inside the Jakarta XML Binding object. This is why there is not a
     * <CODE>set</CODE> method for the incrementalProcessingNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getIncrementalProcessingNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IncrementalProcessingNotification }
     * 
     * 
     */
    public List<IncrementalProcessingNotification> getIncrementalProcessingNotification() {
      if (incrementalProcessingNotification == null) {
        incrementalProcessingNotification = new ArrayList<IncrementalProcessingNotification>();
      }
      return this.incrementalProcessingNotification;
    }

    public boolean isSetIncrementalProcessingNotification() {
      return ((this.incrementalProcessingNotification != null) && (!this.incrementalProcessingNotification.isEmpty()));
    }

    public void unsetIncrementalProcessingNotification() {
      this.incrementalProcessingNotification = null;
    }

  }

}
