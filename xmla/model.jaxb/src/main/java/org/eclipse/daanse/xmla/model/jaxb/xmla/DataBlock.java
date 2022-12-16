
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DataBlock complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DataBlock"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Block" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataBlock", propOrder = { "block" })
public class DataBlock {

  @XmlElement(name = "Block")
  protected List<byte[]> block;

  /**
   * Gets the value of the block property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the block property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getBlock().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list byte[]
   * 
   */
  public List<byte[]> getBlock() {
    if (block == null) {
      block = new ArrayList<byte[]>();
    }
    return this.block;
  }

  public boolean isSetBlock() {
    return ((this.block != null) && (!this.block.isEmpty()));
  }

  public void unsetBlock() {
    this.block = null;
  }

}
