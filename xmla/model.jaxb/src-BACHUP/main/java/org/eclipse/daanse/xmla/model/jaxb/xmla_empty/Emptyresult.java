
package org.eclipse.daanse.xmla.model.jaxb.xmla_empty;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jaxb.xmla_exception.Exception;
import org.eclipse.daanse.xmla.model.jaxb.xmla_exception.Messages;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for emptyresult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="emptyresult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
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
@XmlType(name = "emptyresult", propOrder = {
    "exception",
    "messages"
})
public class Emptyresult
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Exception")
    protected Exception exception;
    @XmlElement(name = "Messages")
    protected Messages messages;

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
