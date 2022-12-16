
package org.eclipse.daanse.xmla.model.jaxb.engine200;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.daanse.xmla.model.jaxb.engine200 package. 
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

    private final static QName _CalculationError_QNAME = new QName("http://schemas.microsoft.com/analysisservices/2010/engine/200", "CalculationError");
    private final static QName _CompatibilityLevel_QNAME = new QName("http://schemas.microsoft.com/analysisservices/2010/engine/200", "CompatibilityLevel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.daanse.xmla.model.jaxb.engine200
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WarningColumn }
     * 
     */
    public WarningColumn createWarningColumn() {
        return new WarningColumn();
    }

    /**
     * Create an instance of {@link WarningMeasure }
     * 
     */
    public WarningMeasure createWarningMeasure() {
        return new WarningMeasure();
    }

    /**
     * Create an instance of {@link WarningLocationObject }
     * 
     */
    public WarningLocationObject createWarningLocationObject() {
        return new WarningLocationObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200", name = "CalculationError")
    public JAXBElement<String> createCalculationError(String value) {
        return new JAXBElement<String>(_CalculationError_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200", name = "CompatibilityLevel")
    public JAXBElement<BigInteger> createCompatibilityLevel(BigInteger value) {
        return new JAXBElement<BigInteger>(_CompatibilityLevel_QNAME, BigInteger.class, null, value);
    }

}
