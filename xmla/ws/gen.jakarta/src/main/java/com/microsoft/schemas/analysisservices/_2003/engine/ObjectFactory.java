
package com.microsoft.schemas.analysisservices._2003.engine;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.schemas.analysisservices._2003.engine package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LastSchemaUpdate_QNAME = new QName("http://schemas.microsoft.com/analysisservices/2003/engine", "LastSchemaUpdate");
    private final static QName _LastDataUpdate_QNAME = new QName("http://schemas.microsoft.com/analysisservices/2003/engine", "LastDataUpdate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.schemas.analysisservices._2003.engine
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImpersonationInfo }
     * 
     */
    public ImpersonationInfo createImpersonationInfo() {
        return new ImpersonationInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2003/engine", name = "LastSchemaUpdate")
    public JAXBElement<XMLGregorianCalendar> createLastSchemaUpdate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastSchemaUpdate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2003/engine", name = "LastDataUpdate")
    public JAXBElement<XMLGregorianCalendar> createLastDataUpdate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastDataUpdate_QNAME, XMLGregorianCalendar.class, null, value);
    }

}
