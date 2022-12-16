
package org.eclipse.daanse.xmla.model.jaxb.xmla_rowset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.xmla_exception.Exception;
import org.eclipse.daanse.xmla.model.jaxb.xmla_exception.Messages;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rowset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rowset"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="row" type="{urn:schemas-microsoft-com:xml-analysis:rowset}row" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "rowset", propOrder = {
    "row",
    "exception",
    "messages"
})
public class Rowset
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected List<Row> row;
    @XmlElement(name = "Exception")
    protected Exception exception;
    @XmlElement(name = "Messages")
    protected Messages messages;

    /**
     * Gets the value of the row property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the row property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Row }
     * 
     * 
     */
    public List<Row> getRow() {
        if (row == null) {
            row = new ArrayList<Row>();
        }
        return this.row;
    }
    


    public boolean isSetRow() {
        return ((this.row!= null)&&(!this.row.isEmpty()));
    }

    public void unsetRow() {
        this.row = null;
    }

    /**
     * Gets the value of the exception property.
     * 
     * @return
     *     possible object is
     *     {@link Exception }
     *     
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Sets the value of the exception property.
     * 
     * @param value
     *     allowed object is
     *     {@link Exception }
     *     
     */
    public void setException(Exception value) {
        this.exception = value;
    }

    public boolean isSetException() {
        return (this.exception!= null);
    }

    /**
     * Gets the value of the messages property.
     * 
     * @return
     *     possible object is
     *     {@link Messages }
     *     
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * Sets the value of the messages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Messages }
     *     
     */
    public void setMessages(Messages value) {
        this.messages = value;
    }

    public boolean isSetMessages() {
        return (this.messages!= null);
    }

}
