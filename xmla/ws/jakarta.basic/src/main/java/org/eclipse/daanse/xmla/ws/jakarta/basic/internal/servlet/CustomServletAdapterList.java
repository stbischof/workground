package org.eclipse.daanse.xmla.ws.jakarta.basic.internal.servlet;

import com.sun.xml.ws.api.server.WSEndpoint;
import com.sun.xml.ws.transport.http.servlet.ServletAdapterList;

public class CustomServletAdapterList extends ServletAdapterList {

    public CustomServletAdapterList(CustomServletContext c) {
        super(c);
    }

    @Override
    public CustomServletAdapter createHttpAdapter(String name, String urlPattern, WSEndpoint<?> endpoint) {
        return new CustomServletAdapter(name, urlPattern, endpoint, this);
    }
}
