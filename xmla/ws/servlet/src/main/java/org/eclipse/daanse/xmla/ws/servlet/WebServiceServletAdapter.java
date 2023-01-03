package org.eclipse.daanse.xmla.ws.servlet;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.daanse.xmla.ws.servlet.adapter.CustomServletAdapter;
import org.eclipse.daanse.xmla.ws.servlet.adapter.CustomServletAdapterList;
import org.eclipse.daanse.xmla.ws.servlet.adapter.CustomServletContext;
import org.eclipse.daanse.xmla.ws.servlet.adapter.CustomWSServletDelegate;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.servlet.whiteboard.HttpWhiteboardConstants;

import com.sun.xml.ws.api.server.WSEndpoint;

import jakarta.servlet.Servlet;

@Component(scope = ServiceScope.PROTOTYPE)
@Designate(factory = true, ocd = WebServiceServletAdapterConfig.class)
public class WebServiceServletAdapter {

    @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC, target = "(&(must.not.resolve=*)(!(must.not.resolve=*)))")
    private WSEndpoint<?> endpoint;
    private CustomWSServletDelegate delegate = null;
    private ServiceRegistration<Servlet> sr;

    public WebServiceServletAdapter() {

    }

    @Activate
    public void activate(BundleContext bc, WebServiceServletAdapterConfig config) {
        CustomServletContext c = new CustomServletContext();
        CustomServletAdapterList sal = new CustomServletAdapterList(c);
        try {

            CustomServletAdapter csa = sal.createHttpAdapter(config.name(), "/*", endpoint);
            this.delegate = new CustomWSServletDelegate(csa);

            WebServiceServlet servlet = new WebServiceServlet(delegate);

            Dictionary<String, Object> ht = new Hashtable<>();

            ht.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME, config.name());
            ht.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN, config.path());
            if (!config.contextSelect()
                    .isEmpty()) {
                ht.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT, config.contextSelect());
            }

            sr = bc.registerService(Servlet.class, servlet, ht);

        } catch (Throwable e) {
            throw e;
        }

    }

    @Deactivate
    public void deActivate() {
        sr.unregister();
    }

}