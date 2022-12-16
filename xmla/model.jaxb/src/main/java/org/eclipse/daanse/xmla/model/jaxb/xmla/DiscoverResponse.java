
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import org.eclipse.daanse.xmla.model.jaxb.xmla_rowset.Rowset;

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
 *       &lt;sequence&gt;
 *         &lt;element name="return" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{urn:schemas-microsoft-com:xml-analysis:rowset}root" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
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
@XmlType(name = "", propOrder = { "_return" })
@XmlRootElement(name = "DiscoverResponse")
public class DiscoverResponse {

  @XmlElement(name = "return")
  protected DiscoverResponse.Return _return;

  /**
   * Gets the value of the return property.
   * 
   * @return possible object is {@link DiscoverResponse.Return }
   * 
   */
  public DiscoverResponse.Return getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   * 
   * @param value allowed object is {@link DiscoverResponse.Return }
   * 
   */
  public void setReturn(DiscoverResponse.Return value) {
    this._return = value;
  }

  public boolean isSetReturn() {
    return (this._return != null);
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
   *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis:rowset}root" minOccurs="0"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = { "root" })
  public static class Return {

    @XmlElement(namespace = "urn:schemas-microsoft-com:xml-analysis:rowset")
    protected Rowset root;

    /**
     * Gets the value of the root property.
     * 
     * @return possible object is {@link Rowset }
     * 
     */
    public Rowset getRoot() {
      return root;
    }

    /**
     * Sets the value of the root property.
     * 
     * @param value allowed object is {@link Rowset }
     * 
     */
    public void setRoot(Rowset value) {
      this.root = value;
    }

    public boolean isSetRoot() {
      return (this.root != null);
    }

  }

}
