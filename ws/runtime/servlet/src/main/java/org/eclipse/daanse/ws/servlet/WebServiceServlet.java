package org.eclipse.daanse.ws.servlet;

import org.eclipse.daanse.ws.servlet.adapter.CustomWSServletDelegate;

import com.sun.xml.ws.transport.http.servlet.WSServletDelegate;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CustomWSServletDelegate delegate = null;

    public WebServiceServlet(CustomWSServletDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }

    /**
     * Gets the {@link WSServletDelegate} that we will be forwarding the requests
     * to.
     *
     * @param servletConfig the ServletConfig object
     * @return null if the deployment have failed and we don't have the delegate.
     */
    protected WSServletDelegate getDelegate(ServletConfig servletConfig) {
        return (WSServletDelegate) servletConfig.getServletContext()
                .getAttribute(JAXWS_RI_RUNTIME_INFO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (delegate != null) {
            delegate.doPost(request, response, getServletContext());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (delegate != null) {
            delegate.doGet(request, response, getServletContext());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (delegate != null) {
            delegate.doPut(request, response, getServletContext());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (delegate != null) {
            delegate.doDelete(request, response, getServletContext());
        }
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (delegate != null) {
            delegate.doHead(request, response, getServletContext());
        }
    }

    public static final String JAXWS_RI_RUNTIME_INFO = "com.sun.xml.ws.server.http.servletDelegate";
    public static final String JAXWS_RI_PROPERTY_PUBLISH_WSDL = "com.sun.xml.ws.server.http.publishWSDL";
    public static final String JAXWS_RI_PROPERTY_PUBLISH_STATUS_PAGE = "com.sun.xml.ws.server.http.publishStatusPage";

}