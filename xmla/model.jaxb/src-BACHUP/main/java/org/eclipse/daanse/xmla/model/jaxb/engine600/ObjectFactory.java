
package org.eclipse.daanse.xmla.model.jaxb.engine600;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.daanse.xmla.ws.eng600 package. 
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

    private final static QName _SupportedCompatibilityLevels_QNAME = new QName("http://schemas.microsoft.com/analysisservices/2013/engine/600", "SupportedCompatibilityLevels");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.daanse.xmla.ws.eng600
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2013/engine/600", name = "SupportedCompatibilityLevels")
    public JAXBElement<String> createSupportedCompatibilityLevels(String value) {
        return new JAXBElement<String>(_SupportedCompatibilityLevels_QNAME, String.class, null, value);
    }

}
