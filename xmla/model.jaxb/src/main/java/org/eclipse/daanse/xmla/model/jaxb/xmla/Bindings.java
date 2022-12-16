
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Bindings complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Bindings"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Binding" type="{urn:schemas-microsoft-com:xml-analysis}OutOfLineBinding" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bindings", propOrder = { "binding" })
public class Bindings {

  @XmlElement(name = "Binding")
  protected List<OutOfLineBinding> binding;

  /**
   * Gets the value of the binding property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the binding property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getBinding().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link OutOfLineBinding }
   * 
   * 
   */
  public List<OutOfLineBinding> getBinding() {
    if (binding == null) {
      binding = new ArrayList<OutOfLineBinding>();
    }
    return this.binding;
  }

  public boolean isSetBinding() {
    return ((this.binding != null) && (!this.binding.isEmpty()));
  }

  public void unsetBinding() {
    this.binding = null;
  }

}
