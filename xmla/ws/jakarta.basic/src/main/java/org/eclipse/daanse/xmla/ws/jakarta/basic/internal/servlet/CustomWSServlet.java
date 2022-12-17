package org.eclipse.daanse.xmla.ws.jakarta.basic.internal.servlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.daanse.xmla.ws.jakarta.basic.XmlaService;
import org.eclipse.daanse.xmla.ws.jakarta.basic.internal.MsXmlAnalysisSoap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.servlet.whiteboard.propertytypes.HttpWhiteboardServletName;
import org.osgi.service.servlet.whiteboard.propertytypes.HttpWhiteboardServletPattern;

import com.sun.xml.ws.api.server.InstanceResolver;
import com.sun.xml.ws.api.server.WSEndpoint;
import com.sun.xml.ws.binding.BindingImpl;
import com.sun.xml.ws.transport.http.servlet.WSServletDelegate;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(service = Servlet.class, scope = ServiceScope.PROTOTYPE)
@HttpWhiteboardServletName("xmla")
@HttpWhiteboardServletPattern("/*")
public class CustomWSServlet extends HttpServlet {

    private CustomWSServletDelegate delegate = null;

    private CustomServletContext c = new CustomServletContext();
    private CustomServletAdapterList sal = new CustomServletAdapterList(c);

    private Map<XmlaService, CustomServletAdapter> map = new ConcurrentHashMap<>();
    private AtomicInteger atomicInteger = new AtomicInteger();

    public CustomWSServlet() {
        this.delegate = new CustomWSServletDelegate();

    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindXmlaService(XmlaService xmlaService) {

        int i = atomicInteger.incrementAndGet();

        MsXmlAnalysisSoap msxmla = new MsXmlAnalysisSoap(xmlaService);
        com.sun.xml.ws.api.server.Invoker invoker = InstanceResolver.createSingleton(msxmla)
                .createInvoker();

        WSEndpoint<MsXmlAnalysisSoap> endpoint = WSEndpoint.create(MsXmlAnalysisSoap.class, false, invoker, null, null,
                null, BindingImpl.getDefaultBinding(), null, null, null, false);

        try {

            CustomServletAdapter csa = sal.createHttpAdapter(i + "", "/" + i, endpoint);
            delegate.registerEndpointUrlPattern(csa);
        } catch (Throwable e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public void unbindXmlaService(XmlaService xmlaService) {
        CustomServletAdapter csa = map.get(xmlaService);
        if (csa != null) {

            delegate.unregisterEndpointUrlPattern(csa);
        }

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
    protected  WSServletDelegate getDelegate(ServletConfig servletConfig) {
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