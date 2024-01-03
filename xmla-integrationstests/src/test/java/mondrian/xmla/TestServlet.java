package mondrian.xmla;

import jakarta.servlet.ServletException;


import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeader;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.model.record.RequestMetaDataR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;
import java.util.StringTokenizer;

public class TestServlet extends HttpServlet {
    private final XmlaApiAdapter wsAdapter;
    private final MessageFactory messageFactory;

    public TestServlet(XmlaService xmlaService) {
        super();
        try {
            messageFactory = MessageFactory.newInstance();
            wsAdapter = new XmlaApiAdapter(xmlaService);

        } catch (SOAPException ex) {
            throw new RuntimeException("Unable to create message factory" + ex.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try {
            MimeHeaders headers = getRequestHeaders(request);

            InputStream requestInptStream = request.getInputStream();

            SOAPMessage requestMessage = messageFactory.createMessage(headers, requestInptStream);

            SOAPMessage responseMessage = null;

            responseMessage = onMessage(requestMessage);

            if (responseMessage != null) {

                // Need to saveChanges 'cos we're going to use the
                // MimeHeaders to set HTTP response information. These
                // MimeHeaders are generated as part of the save.

                if (responseMessage.saveRequired()) {
                    responseMessage.saveChanges();
                }

                response.setStatus(HttpServletResponse.SC_OK);

                putHeaders(responseMessage.getMimeHeaders(), response);

                OutputStream responseOutputStream = response.getOutputStream();

                responseMessage.writeTo(responseOutputStream);

                responseOutputStream.flush();

            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (Exception ex) {
            throw new RuntimeException("SAAJ POST failed " + ex.getMessage());
        }
    }

    protected static MimeHeaders getRequestHeaders(HttpServletRequest req) {
        Enumeration enumeration = req.getHeaderNames();
        MimeHeaders headers = new MimeHeaders();

        while (enumeration.hasMoreElements()) {
            String headerName = (String) enumeration.nextElement();
            String headerValue = req.getHeader(headerName);

            StringTokenizer values = new StringTokenizer(headerValue, ",");

            while (values.hasMoreTokens()) {
                headers.addHeader(headerName, values.nextToken().trim());
            }
        }

        return headers;
    }

    protected static void putHeaders(MimeHeaders headers, HttpServletResponse res) {
        Iterator it = headers.getAllHeaders();

        while (it.hasNext()) {
            MimeHeader header = (MimeHeader) it.next();

            String[] values = headers.getHeader(header.getName());

            if (values.length == 1) {
                res.setHeader(header.getName(), header.getValue());
            } else {
                StringBuffer concat = new StringBuffer();
                int i = 0;

                while (i < values.length) {
                    if (i != 0) {
                        concat.append(',');
                    }
                    concat.append(values[i++]);
                }
                res.setHeader(header.getName(), concat.toString());
            }
        }
    }

    public SOAPMessage onMessage(SOAPMessage message) {
        System.out.println("On message call");
        try {

            message.writeTo(System.out);

            SOAPHeader header = message.getSOAPHeader();
            SOAPBody body = message.getSOAPBody();

            MimeHeaders m = message.getMimeHeaders();
            String[] s = m.getHeader("User-agent");

            Optional<String> oUserAgent = Optional.empty();
            if (s != null && s.length > 0) {
                oUserAgent = Optional.of(s[0]);
            }

            RequestMetaData metaData = new RequestMetaDataR(oUserAgent);

            return wsAdapter.handleRequest(message, metaData);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
