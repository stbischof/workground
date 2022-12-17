package org.eclipse.daanse.xmla.ws.jakarta.basic.internal;

import org.eclipse.daanse.xmla.model.jaxb.ext.Authenticate;
import org.eclipse.daanse.xmla.model.jaxb.ext.AuthenticateResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Execute;
import org.eclipse.daanse.xmla.model.jaxb.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Session;
import org.eclipse.daanse.xmla.ws.jakarta.basic.XmlaService;
import org.osgi.service.component.annotations.Component;

import jakarta.xml.ws.Holder;

@Component(service = XmlaService.class)
public class AXmlaService2 implements XmlaService {

    @Override
    public AuthenticateResponse authenticate(Authenticate authenticate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DiscoverResponse discover(Discover parameters, Holder<Session> session, BeginSession beginSession,
            EndSession endSession) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExecuteResponse execute(Execute parameters, Holder<Session> session, BeginSession beginSession,
            EndSession endSession) {
        // TODO Auto-generated method stub
        return null;
    }

}
