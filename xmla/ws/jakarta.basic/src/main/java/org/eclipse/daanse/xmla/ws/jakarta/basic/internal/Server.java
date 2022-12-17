package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import java.util.List;

import org.eclipse.daanse.xmla.ws.jakarta.basic.XmlaService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.spi.http.HttpContext;

@Component(immediate = true)
public class Server {

    private Endpoint endpoint;
    private XmlaService xmlaService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    public void bindXmlaService(XmlaService xmlaService) {
        this.xmlaService = xmlaService;
    }

    public void unbindXmlaService(XmlaService xmlaService) {
        this.xmlaService = null;
    }

    @Activate
    public void activate() {

        if(true) {
            return;
        }
        int port = 8081;

        String address = "http://localhost:" + port + "/xmla";
        System.out.println(address);
        MsXmlAnalysisSoap s = new MsXmlAnalysisSoap(xmlaService);
        endpoint = Endpoint.create(s);
        List<Handler> handlerChain = endpoint.getBinding()
                .getHandlerChain();
        handlerChain.add(new SOAPLoggingHandler());
//    handlerChain.add(new nsHandler());
        endpoint.getBinding()
                .setHandlerChain(handlerChain);

        try {
            endpoint.publish(address);
        } catch (Exception e) {
            System.out.println(e);

            e.printStackTrace();
        }
        System.out.println(address);

        // TODO: may register with as a servlet
    }

    @Deactivate
    public void deactivate() {
        endpoint.stop();
        endpoint = null;

    }
}
