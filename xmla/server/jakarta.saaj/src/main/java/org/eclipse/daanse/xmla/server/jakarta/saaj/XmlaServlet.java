
package org.eclipse.daanse.xmla.server.jakarta.saaj;

import jakarta.servlet.Servlet;
import jakarta.xml.soap.MimeHeader;
import jakarta.xml.soap.SOAPMessage;
import org.eclipse.daanse.jakarta.servlet.soap.AbstractSoapServlet;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.servlet.whiteboard.propertytypes.HttpWhiteboardServletPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@HttpWhiteboardServletPattern("/xmla")
@Component(service = Servlet.class, scope = ServiceScope.PROTOTYPE)
public class XmlaServlet extends AbstractSoapServlet {

	private static Logger LOGGER = LoggerFactory.getLogger(XmlaServlet.class);
	private XmlaApiAdapter wsAdapter;

	@Reference
	private XmlaService xmlaService;

	@Activate
	public void activate() {
		wsAdapter = new XmlaApiAdapter(xmlaService);
	}

	@Override
	public SOAPMessage onMessage(SOAPMessage soapMessage) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SoapMessage in:", prettyPrint(soapMessage).toString());
			}
            Iterable<MimeHeader> iterable = () -> soapMessage.getMimeHeaders().getAllHeaders();
            Map<String, Object> map = StreamSupport.stream(iterable.spliterator(), true).collect(Collectors.toMap(MimeHeader::getName, MimeHeader::getValue, (oldValue, newValue) -> oldValue));

			SOAPMessage returnMessage = wsAdapter.handleRequest(soapMessage, map);

			LOGGER.debug("SoapMessage out:", prettyPrint(returnMessage).toString());

			return returnMessage;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static ByteArrayOutputStream prettyPrint(SOAPMessage msg) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(msg.getSOAPPart().getContent(), new StreamResult(baos));
		} catch (Exception e) {
			LOGGER.error("Exception while generate prettyPrint of SoapMessage.", e);
			try {

				msg.writeTo(baos);
			} catch (Exception e1) {
				LOGGER.error("Exception while generate prettyPrintfallback of SoapMessage.", e1);
				baos.writeBytes(msg.toString().getBytes());
			}
		}
		return baos;
	}
}
