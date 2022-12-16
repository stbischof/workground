
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CellData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CellData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Cell" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}CellType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CellSet" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}CellSetType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CellData", propOrder = { "cell", "cellSet" })
public class CellData {

  @XmlElement(name = "Cell")
  protected List<CellType> cell;
  @XmlElement(name = "CellSet")
  protected CellSetType cellSet;

  /**
   * Gets the value of the cell property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the cell property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getCell().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link CellType }
   * 
   * 
   */
  public List<CellType> getCell() {
    if (cell == null) {
      cell = new ArrayList<CellType>();
    }
    return this.cell;
  }

  public boolean isSetCell() {
    return ((this.cell != null) && (!this.cell.isEmpty()));
  }

  public void unsetCell() {
    this.cell = null;
  }

  /**
   * Gets the value of the cellSet property.
   * 
   * @return possible object is {@link CellSetType }
   * 
   */
  public CellSetType getCellSet() {
    return cellSet;
  }

  /**
   * Sets the value of the cellSet property.
   * 
   * @param value allowed object is {@link CellSetType }
   * 
   */
  public void setCellSet(CellSetType value) {
    this.cellSet = value;
  }

  public boolean isSetCellSet() {
    return (this.cellSet != null);
  }

}
