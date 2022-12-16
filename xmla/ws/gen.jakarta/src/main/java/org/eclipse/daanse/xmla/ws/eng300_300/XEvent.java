
package org.eclipse.daanse.xmla.ws.eng300_300;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EventSession;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis}event_session"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "eventSession"
})
@XmlRootElement(name = "XEvent")
public class XEvent {

    @XmlElement(name = "event_session", namespace = "urn:schemas-microsoft-com:xml-analysis", required = true)
    protected EventSession eventSession;

    /**
     * Gets the value of the eventSession property.
     * 
     * @return
     *     possible object is
     *     {@link EventSession }
     *     
     */
    public EventSession getEventSession() {
        return eventSession;
    }

    /**
     * Sets the value of the eventSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventSession }
     *     
     */
    public void setEventSession(EventSession value) {
        this.eventSession = value;
    }

}
