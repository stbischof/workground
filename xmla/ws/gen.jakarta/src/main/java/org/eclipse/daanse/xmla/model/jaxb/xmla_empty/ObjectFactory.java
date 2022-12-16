
package org.eclipse.daanse.xmla.model.jaxb.xmla_empty;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.daanse.xmla.model.jaxb.xmla_empty package. 
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

    private final static QName _Root_QNAME = new QName("urn:schemas-microsoft-com:xml-analysis:empty", "root");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.daanse.xmla.model.jaxb.xmla_empty
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Emptyresult }
     * 
     */
    public Emptyresult createEmptyresult() {
        return new Emptyresult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Emptyresult }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Emptyresult }{@code >}
     */
    @XmlElementDecl(namespace = "urn:schemas-microsoft-com:xml-analysis:empty", name = "root")
    public JAXBElement<Emptyresult> createRoot(Emptyresult value) {
        return new JAXBElement<Emptyresult>(_Root_QNAME, Emptyresult.class, null, value);
    }

}
