/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.xmla.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import mondrian.olap.Util;
import mondrian.xmla.XmlaServlet;

/**
 * Dummy request for testing XmlaServlet. Provides a 'text/xml' content stream
 * from a post from xmlaTest.jsp. Assumes that the SOAPRequest parameter
 * contains XML/A SOAP request body.
 *
 * @author Sherman Wood
 */
public class XmlaTestServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest originalRequest;
    private ServletInputStream servletInStream;

    public XmlaTestServletRequestWrapper(HttpServletRequest req) {
        super(req);
        originalRequest = req;
        init();
    }

    /**
     * Extract the data from the HTTP request and create an XML/A request
     */
    private void init() {
        String soapRequest = originalRequest.getParameter("SOAPRequest");

        if (soapRequest == null || soapRequest.length() == 0) {
            // Parameter not set. Look for the request in the body of the http
            // request.

            try {
                final ServletInputStream inputStream =
                        originalRequest.getInputStream();
                soapRequest = Util.readFully(
                    new InputStreamReader(inputStream), 2048);
            } catch (IOException e) {
                throw Util.newInternal(e, "error reading body of soap request");
            }

            if (soapRequest.length() == 0) {
                throw new RuntimeException("SOAPRequest not set");
            }
        }

        /*
         * Strip the XML premable if it is there
         */
        if (soapRequest.indexOf("<?") == 0) {
            soapRequest = soapRequest.substring(soapRequest.indexOf("?>") + 2);
        }

        /*
         * Make a SOAP message
         */
        String request =
            "<?xml version=\"1.0\"?>\r\n"
            + "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\""
            + XmlaServlet.NS_SOAP_ENV_1_1
            + "\" SOAP-ENV:encodingStyle=\""
            + XmlaServlet.NS_SOAP_ENC_1_1
            + "\">\r\n"
            + "<SOAP-ENV:Header/>\r\n"
            + "<SOAP-ENV:Body>\r\n"
            + soapRequest
            + "</SOAP-ENV:Body>\r\n</SOAP-ENV:Envelope>\r\n";

        servletInStream = new XmlaTestServletInputStream(request);
    }

    @Override
	public String getContentType() {
        return "text/xml";
    }

    @Override
	public ServletInputStream getInputStream() {
        return servletInStream;
    }

    private static class XmlaTestServletInputStream extends ServletInputStream {

        private ByteArrayInputStream bais;

        XmlaTestServletInputStream(String source) {
            bais = new ByteArrayInputStream(source.getBytes());
        }

        @Override
		public int readLine(byte[] arg0, int arg1, int arg2)
            throws IOException
        {
            return bais.read(arg0, arg1, arg2);
        }

        @Override
		public int available() throws IOException {
            return bais.available();
        }

        @Override
		public void close() throws IOException {
            bais.close();
        }

        @Override
		public synchronized void mark(int readlimit) {
            bais.mark(readlimit);
        }

        @Override
		public boolean markSupported() {
            return bais.markSupported();
        }

        @Override
		public int read() throws IOException {
            return bais.read();
        }

        @Override
		public int read(byte[] b, int off, int len) throws IOException {
            return bais.read(b, off, len);
        }

        @Override
		public int read(byte[] b) throws IOException {
            return bais.read(b);
        }

        @Override
		public synchronized void reset() throws IOException {
             bais.reset();
        }

        @Override
		public long skip(long n) throws IOException {
            return bais.skip(n);
        }
    }
}
