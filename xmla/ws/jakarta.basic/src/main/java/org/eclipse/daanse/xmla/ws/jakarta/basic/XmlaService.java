package org.eclipse.daanse.xmla.ws.jakarta.basic;

import org.eclipse.daanse.xmla.model.jaxb.ext.ReturnValue;
import org.eclipse.daanse.xmla.model.jaxb.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Discover;
import org.eclipse.daanse.xmla.model.jaxb.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Execute;
import org.eclipse.daanse.xmla.model.jaxb.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jaxb.xmla.Session;

import jakarta.xml.ws.Holder;

public interface XmlaService {

  public ReturnValue authenticate(byte[] sspiHandshake);

  public DiscoverResponse discover(Discover parameters, Holder<Session> session, BeginSession beginSession,
      EndSession endSession);

  public ExecuteResponse execute(Execute parameters, Holder<Session> session, BeginSession beginSession,
      EndSession endSession);

}
