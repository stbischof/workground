
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.xmla.model.jaxb.xmla_exception.Exception;
import org.eclipse.daanse.xmla.model.jaxb.xmla_exception.Messages;

/**
 * <p>
 * Java class for mddataset complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="mddataset"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OlapInfo" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}OlapInfo" minOccurs="0"/&gt;
 *         &lt;element name="Axes" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}Axes" minOccurs="0"/&gt;
 *         &lt;element name="CellData" type="{urn:schemas-microsoft-com:xml-analysis:mddataset}CellData" minOccurs="0"/&gt;
 *         &lt;element name="Exception" type="{urn:schemas-microsoft-com:xml-analysis:exception}Exception" minOccurs="0"/&gt;
 *         &lt;element name="Messages" type="{urn:schemas-microsoft-com:xml-analysis:exception}Messages" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mddataset", propOrder = { "olapInfo", "axes", "cellData", "exception", "messages" })
public class Mddataset {

  @XmlElement(name = "OlapInfo")
  protected OlapInfo olapInfo;
  @XmlElement(name = "Axes")
  protected Axes axes;
  @XmlElement(name = "CellData")
  protected CellData cellData;
  @XmlElement(name = "Exception")
  protected Exception exception;
  @XmlElement(name = "Messages")
  protected Messages messages;

  /**
   * Gets the value of the olapInfo property.
   * 
   * @return possible object is {@link OlapInfo }
   * 
   */
  public OlapInfo getOlapInfo() {
    return olapInfo;
  }

  /**
   * Sets the value of the olapInfo property.
   * 
   * @param value allowed object is {@link OlapInfo }
   * 
   */
  public void setOlapInfo(OlapInfo value) {
    this.olapInfo = value;
  }

  public boolean isSetOlapInfo() {
    return (this.olapInfo != null);
  }

  /**
   * Gets the value of the axes property.
   * 
   * @return possible object is {@link Axes }
   * 
   */
  public Axes getAxes() {
    return axes;
  }

  /**
   * Sets the value of the axes property.
   * 
   * @param value allowed object is {@link Axes }
   * 
   */
  public void setAxes(Axes value) {
    this.axes = value;
  }

  public boolean isSetAxes() {
    return (this.axes != null);
  }

  /**
   * Gets the value of the cellData property.
   * 
   * @return possible object is {@link CellData }
   * 
   */
  public CellData getCellData() {
    return cellData;
  }

  /**
   * Sets the value of the cellData property.
   * 
   * @param value allowed object is {@link CellData }
   * 
   */
  public void setCellData(CellData value) {
    this.cellData = value;
  }

  public boolean isSetCellData() {
    return (this.cellData != null);
  }

  /**
   * Gets the value of the exception property.
   * 
   * @return possible object is {@link Exception }
   * 
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Sets the value of the exception property.
   * 
   * @param value allowed object is {@link Exception }
   * 
   */
  public void setException(Exception value) {
    this.exception = value;
  }

  public boolean isSetException() {
    return (this.exception != null);
  }

  /**
   * Gets the value of the messages property.
   * 
   * @return possible object is {@link Messages }
   * 
   */
  public Messages getMessages() {
    return messages;
  }

  /**
   * Sets the value of the messages property.
   * 
   * @param value allowed object is {@link Messages }
   * 
   */
  public void setMessages(Messages value) {
    this.messages = value;
  }

  public boolean isSetMessages() {
    return (this.messages != null);
  }

}
