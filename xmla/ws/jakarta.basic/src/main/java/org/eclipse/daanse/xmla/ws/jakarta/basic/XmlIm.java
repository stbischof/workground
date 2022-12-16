package org.eclipse.daanse.xmla.ws.jakarta.basic;

import org.eclipse.daanse.xmla.model.jaxb.ext.ReturnValue;
import org.eclipse.daanse.xmla.model.jaxb.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Execute;
import org.eclipse.daanse.xmla.model.jaxb.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Session;
import org.osgi.service.component.annotations.Component;

import jakarta.xml.ws.Holder;

@Component(immediate = true, service = XmlaService.class)
public class XmlIm implements XmlaService {

    @Override
    public ReturnValue authenticate(byte[] sspiHandshake) {
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
