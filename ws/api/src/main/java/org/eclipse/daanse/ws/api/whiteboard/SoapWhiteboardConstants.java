package org.eclipse.daanse.ws.api.whiteboard;

public class SoapWhiteboardConstants {

    private SoapWhiteboardConstants() {
        // non-instantiable
    }

    public static final String SOAP = "osgi.soap";
    public static final String HTTP_WHITEBOARD_IMPLEMENTATION = SOAP;

    private static final String SOAP_ENDPOINT = SOAP + ".endpoint";
    public static final String SOAP_ENDPOINT_IMPLEMENTOR = SOAP_ENDPOINT + ".implementor";
    public static final String SOAP_ENDPOINT_PATH = SOAP_ENDPOINT + ".path";

    public static final String SOAP_SPECIFICATION_VERSION = "0.0";
    public static final Object SOAP_ENDPOINT_SELECT = SOAP_ENDPOINT + ".select";
}
