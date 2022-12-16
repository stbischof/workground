
package org.eclipse.daanse.xmla.model.jaxb.xmla_multipleresults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.xmla_empty.Emptyresult;

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
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis:empty}root" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlRootElement(name = "results")
public class Results implements Serializable {

  private final static long serialVersionUID = 1L;
  @XmlElement(namespace = "urn:schemas-microsoft-com:xml-analysis:empty")
  protected List<Emptyresult> root;

  /**
   * Gets the value of the root property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the root property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getRoot().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Emptyresult }
   * 
   * 
   */
  public List<Emptyresult> getRoot() {
    if (root == null) {
      root = new ArrayList<Emptyresult>();
    }
    return this.root;
  }

  public boolean isSetRoot() {
    return ((this.root != null) && (!this.root.isEmpty()));
  }

  public void unsetRoot() {
    this.root = null;
  }

}
