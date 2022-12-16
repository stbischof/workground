package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.msxmla.NormTupleSet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
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
 *       &lt;group ref="{urn:schemas-microsoft-com:xml-analysis:mddataset}SetType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "setType" })
public class Union {

  @XmlElements({ @XmlElement(name = "Members", type = MembersType.class),
      @XmlElement(name = "Tuples", type = TuplesType.class),
      @XmlElement(name = "CrossProduct", type = SetListType.class),
      @XmlElement(name = "NormTupleSet", namespace = "http://schemas.microsoft.com/analysisservices/2003/xmla", type = NormTupleSet.class),
      @XmlElement(name = "Union", type = Union.class) })
  protected List<Object> setType;

  /**
   * Gets the value of the setType property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the setType property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getSetType().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link NormTupleSet
   * } {@link MembersType } {@link SetListType } {@link Union } {@link TuplesType
   * }
   * 
   * 
   */
  public List<Object> getSetType() {
    if (setType == null) {
      setType = new ArrayList<Object>();
    }
    return this.setType;
  }

  public boolean isSetSetType() {
    return ((this.setType != null) && (!this.setType.isEmpty()));
  }

  public void unsetSetType() {
    this.setType = null;
  }

}
