package org.eclipse.daanse.ws.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.daanse.ws.api.whiteboard.SoapWhiteboardConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.AnyService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.MessageContext;

@Component
@Designate(ocd = ServerRuntime.Config.class)
public class ServerRuntime {

    private Map<Object, Endpoint> endpointMap = new ConcurrentHashMap<>();
    private Map<Object, Object> endpointImplementorMap = new ConcurrentHashMap<>();

    private Map<Object, Map<String, Object>> propsMap = new ConcurrentHashMap<>();

    private Map<Object, List<Handler>> endpointHandlers = new ConcurrentHashMap<>();
    private Map<Handler<? extends MessageContext>, Map<String, Object>> handlers = new ConcurrentHashMap<Handler<? extends MessageContext>, Map<String, Object>>();

    @ObjectClassDefinition
    @interface Config {
        @AttributeDefinition(required = true)
        int port();

        @AttributeDefinition()
        String basepath() default "";
    }

    private Config config;
    private BundleContext bc;

    @Activate
    public void activate(BundleContext bc, Config config) {
        this.bc = bc;
        modified(config);
    }

    @Modified
    public void modified(Config config) {
        this.config = config;
    }

    @Deactivate
    public void deactivate() {

    }

    private void updateHandlers() {

        endpointMap.keySet()
                .stream()
                .forEach(this::updateHandlers);

    }

    private void updateHandlers(Object servicePid) {

        List<Handler> endpHandlers = new ArrayList<>();

        for (Entry<Handler<? extends MessageContext>, Map<String, Object>> handlerEntry : handlers.entrySet()) {

            Object sfilter = handlerEntry.getValue()
                    .get(SoapWhiteboardConstants.SOAP_ENDPOINT_SELECT);
            if (sfilter == null) {
                continue;
            }

            Filter filter;
            try {
                filter = bc.createFilter(sfilter.toString());
                boolean matches = filter.matches(propsMap.get(servicePid));

                endpHandlers.add(handlerEntry.getKey());

            } catch (InvalidSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        List<Handler> oldHandlers = endpointHandlers.get(servicePid);

        Endpoint endpoint = endpointMap.get(servicePid);
        endpoint.getBinding()
                .setHandlerChain(endpHandlers);

        endpointHandlers.put(servicePid, endpHandlers);

    }

    private String calcAdress(int port, String basePath, String path) {
        StringBuilder sb = new StringBuilder("http://localhost:");
        sb = sb.append(port);

        basePath = cleanPath(basePath);
        if (!basePath.isEmpty()) {
            sb = sb.append("/")
                    .append(basePath);
        }

        path = cleanPath(path);
        if (!path.isEmpty()) {
            sb = sb.append("/")
                    .append(path);
        }

        String address = sb.toString();
        return address;
    }

    private String cleanPath(String basepath) {
        if (basepath.startsWith("/")) {
            basepath = basepath.substring(1);
        }
        return basepath;
    }

    @Reference(service = AnyService.class, target = "(" + SoapWhiteboardConstants.SOAP_ENDPOINT_IMPLEMENTOR
            + "=true)", cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)

    public void bindEndpointImplementor(Object endpintImplementor, Map<String, Object> properties) {

        Object servicePid = servicePid(properties);
        Object pathO = properties.get(SoapWhiteboardConstants.SOAP_ENDPOINT_PATH);
        String path = pathO == null ? "" : pathO.toString();

        String address = calcAdress(config.port(), config.basepath(), path);

        Endpoint endpoint = Endpoint.create(endpintImplementor);
        endpointImplementorMap.put(servicePid, endpintImplementor);
        updateHandlers(servicePid);
        endpointMap.put(servicePid, endpoint);

        endpoint.publish(address);

    }

    public void unbindEndpointImplementor(Object endpintImplementor, Map<String, Object> properties) {
        Object servicePid = servicePid(properties);

        endpointImplementorMap.remove(servicePid);
        Endpoint endpoint = endpointMap.remove(servicePid);
        endpoint.stop();

    }

    private Object servicePid(Map<String, Object> properties) {
        return properties.get("service.pid");
    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindHandler(Handler<? extends MessageContext> handler, Map<String, Object> properties) {

        handlers.put(handler, properties);
        updateHandlers();

    }

    public void unbindHandler(Handler<? extends MessageContext> handler, Map<String, Object> properties) {
        handlers.remove(handler);
        updateHandlers();

    }
}
