package org.eclipse.daanse.xmla.ws.jakarta.basic.internal.servlet;

import com.sun.xml.ws.api.server.WSEndpoint;
import com.sun.xml.ws.transport.http.servlet.ServletAdapter;
import com.sun.xml.ws.transport.http.servlet.ServletAdapterList;
public class CustomServletAdapter extends ServletAdapter{

    protected CustomServletAdapter(String name, String urlPattern, WSEndpoint<?> endpoint, ServletAdapterList owner) {
        super(name, urlPattern, endpoint, owner);
    }

}
