
package org.eclipse.daanse.xmla.model.jaxb.xmla_exception;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.daanse.xmla.ws.xmla_x package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.daanse.xmla.ws.xmla_x
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MessageLocation }
     * 
     */
    public MessageLocation createMessageLocation() {
        return new MessageLocation();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Messages }
     * 
     */
    public Messages createMessages() {
        return new Messages();
    }

    /**
     * Create an instance of {@link WarningType }
     * 
     */
    public WarningType createWarningType() {
        return new WarningType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link MessageLocation.Start }
     * 
     */
    public MessageLocation.Start createMessageLocationStart() {
        return new MessageLocation.Start();
    }

    /**
     * Create an instance of {@link MessageLocation.End }
     * 
     */
    public MessageLocation.End createMessageLocationEnd() {
        return new MessageLocation.End();
    }

}
