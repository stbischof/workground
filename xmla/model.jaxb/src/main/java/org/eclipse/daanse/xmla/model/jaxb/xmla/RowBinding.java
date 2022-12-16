
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for RowBinding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="RowBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="TableID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RowBinding", propOrder = { "tableID" })
public class RowBinding extends Binding {

  @XmlElement(name = "TableID", required = true)
  protected String tableID;

  /**
   * Gets the value of the tableID property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTableID() {
    return tableID;
  }

  /**
   * Sets the value of the tableID property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTableID(String value) {
    this.tableID = value;
  }

  public boolean isSetTableID() {
    return (this.tableID != null);
  }

}
