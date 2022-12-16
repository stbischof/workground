
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for and_or_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="and_or_type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="2" minOccurs="2"&gt;
 *         &lt;element name="Not" type="{urn:schemas-microsoft-com:xml-analysis}not_type" minOccurs="0"/&gt;
 *         &lt;element name="Or" type="{urn:schemas-microsoft-com:xml-analysis}and_or_type" minOccurs="0"/&gt;
 *         &lt;element name="And" type="{urn:schemas-microsoft-com:xml-analysis}and_or_type" minOccurs="0"/&gt;
 *         &lt;element name="Equal" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="NotEqual" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="Less" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="LessOrEqual" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="Greater" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="GreaterOrEqual" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="Like" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="NotLike" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "and_or_type", propOrder = {
    "notOrOrOrAnd"
})
public class AndOrType {

    @XmlElementRefs({
        @XmlElementRef(name = "Not", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Or", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "And", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Equal", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "NotEqual", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Less", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "LessOrEqual", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Greater", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "GreaterOrEqual", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Like", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "NotLike", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> notOrOrOrAnd;

    /**
     * Gets the value of the notOrOrOrAnd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the notOrOrOrAnd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotOrOrOrAnd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     * {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * {@link JAXBElement }{@code <}{@link NotType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getNotOrOrOrAnd() {
        if (notOrOrOrAnd == null) {
            notOrOrOrAnd = new ArrayList<JAXBElement<?>>();
        }
        return this.notOrOrOrAnd;
    }

    public boolean isSetNotOrOrOrAnd() {
        return ((this.notOrOrOrAnd!= null)&&(!this.notOrOrOrAnd.isEmpty()));
    }

    public void unsetNotOrOrOrAnd() {
        this.notOrOrOrAnd = null;
    }

}
