
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import javax.xml.datatype.Duration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ProactiveCaching complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ProactiveCaching"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="OnlineMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Immediate"/&gt;
 *               &lt;enumeration value="OnCacheComplete"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AggregationStorage" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Regular"/&gt;
 *               &lt;enumeration value="MolapOnly"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}ProactiveCachingBinding" minOccurs="0"/&gt;
 *         &lt;element name="SilenceInterval" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="Latency" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="SilenceOverrideInterval" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="ForceRebuildInterval" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProactiveCaching", propOrder = {

})
public class ProactiveCaching {

  @XmlElement(name = "OnlineMode")
  protected String onlineMode;
  @XmlElement(name = "AggregationStorage")
  protected String aggregationStorage;
  @XmlElement(name = "Source")
  protected ProactiveCachingBinding source;
  @XmlElement(name = "SilenceInterval")
  protected Duration silenceInterval;
  @XmlElement(name = "Latency")
  protected Duration latency;
  @XmlElement(name = "SilenceOverrideInterval")
  protected Duration silenceOverrideInterval;
  @XmlElement(name = "ForceRebuildInterval")
  protected Duration forceRebuildInterval;
  @XmlElement(name = "Enabled")
  protected Boolean enabled;

  /**
   * Gets the value of the onlineMode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOnlineMode() {
    return onlineMode;
  }

  /**
   * Sets the value of the onlineMode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setOnlineMode(String value) {
    this.onlineMode = value;
  }

  public boolean isSetOnlineMode() {
    return (this.onlineMode != null);
  }

  /**
   * Gets the value of the aggregationStorage property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAggregationStorage() {
    return aggregationStorage;
  }

  /**
   * Sets the value of the aggregationStorage property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setAggregationStorage(String value) {
    this.aggregationStorage = value;
  }

  public boolean isSetAggregationStorage() {
    return (this.aggregationStorage != null);
  }

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link ProactiveCachingBinding }
   * 
   */
  public ProactiveCachingBinding getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link ProactiveCachingBinding }
   * 
   */
  public void setSource(ProactiveCachingBinding value) {
    this.source = value;
  }

  public boolean isSetSource() {
    return (this.source != null);
  }

  /**
   * Gets the value of the silenceInterval property.
   * 
   * @return possible object is {@link Duration }
   * 
   */
  public Duration getSilenceInterval() {
    return silenceInterval;
  }

  /**
   * Sets the value of the silenceInterval property.
   * 
   * @param value allowed object is {@link Duration }
   * 
   */
  public void setSilenceInterval(Duration value) {
    this.silenceInterval = value;
  }

  public boolean isSetSilenceInterval() {
    return (this.silenceInterval != null);
  }

  /**
   * Gets the value of the latency property.
   * 
   * @return possible object is {@link Duration }
   * 
   */
  public Duration getLatency() {
    return latency;
  }

  /**
   * Sets the value of the latency property.
   * 
   * @param value allowed object is {@link Duration }
   * 
   */
  public void setLatency(Duration value) {
    this.latency = value;
  }

  public boolean isSetLatency() {
    return (this.latency != null);
  }

  /**
   * Gets the value of the silenceOverrideInterval property.
   * 
   * @return possible object is {@link Duration }
   * 
   */
  public Duration getSilenceOverrideInterval() {
    return silenceOverrideInterval;
  }

  /**
   * Sets the value of the silenceOverrideInterval property.
   * 
   * @param value allowed object is {@link Duration }
   * 
   */
  public void setSilenceOverrideInterval(Duration value) {
    this.silenceOverrideInterval = value;
  }

  public boolean isSetSilenceOverrideInterval() {
    return (this.silenceOverrideInterval != null);
  }

  /**
   * Gets the value of the forceRebuildInterval property.
   * 
   * @return possible object is {@link Duration }
   * 
   */
  public Duration getForceRebuildInterval() {
    return forceRebuildInterval;
  }

  /**
   * Sets the value of the forceRebuildInterval property.
   * 
   * @param value allowed object is {@link Duration }
   * 
   */
  public void setForceRebuildInterval(Duration value) {
    this.forceRebuildInterval = value;
  }

  public boolean isSetForceRebuildInterval() {
    return (this.forceRebuildInterval != null);
  }

  /**
   * Gets the value of the enabled property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets the value of the enabled property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setEnabled(Boolean value) {
    this.enabled = value;
  }

  public boolean isSetEnabled() {
    return (this.enabled != null);
  }

}
