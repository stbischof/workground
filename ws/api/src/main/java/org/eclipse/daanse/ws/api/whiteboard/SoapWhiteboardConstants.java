package org.eclipse.daanse.ws.api.whiteboard;

public class SoapWhiteboardConstants {

    private SoapWhiteboardConstants() {
        // non-instantiable
    }

    public static final String SOAP = "osgi.soap";
    public static final String SOAP_ENDPOINT_PREFIX = SOAP + ".endpoint.";
    public static final String SOAP_ENDPOINT_IMPLEMENTOR = SOAP_ENDPOINT_PREFIX + "implementor";
    public static final String SOAP_ENDPOINT_PATH = SOAP_ENDPOINT_PREFIX + "contextpath";
    public static final String SOAP_ENDPOINT_SELECT = SOAP_ENDPOINT_PREFIX + "selector";

    public static final String SOAP_SPECIFICATION_VERSION = "0.0";
    
}
