
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Axes complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Axes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Axis" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}Axis" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Axes", propOrder = { "axis" })
public class Axes {

  @XmlElement(name = "Axis", required = true)
  protected List<Axis> axis;

  /**
   * Gets the value of the axis property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the axis property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getAxis().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Axis }
   * 
   * 
   */
  public List<Axis> getAxis() {
    if (axis == null) {
      axis = new ArrayList<Axis>();
    }
    return this.axis;
  }

  public boolean isSetAxis() {
    return ((this.axis != null) && (!this.axis.isEmpty()));
  }

  public void unsetAxis() {
    this.axis = null;
  }

}
