/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.xmla;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.daanse.olap.api.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestContextWrapper;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.test.DiffRepository;
import mondrian.tui.MockHttpServletRequest;
import mondrian.tui.MockHttpServletResponse;
import mondrian.tui.XmlaSupport;
import mondrian.util.Base64;

/**
 * Test of the XMLA Fault generation - errors occur/are-detected in
 * in Mondrian XMLA and a SOAP Fault is returned.
 *
 * <p>There is a set of tests dealing with Authorization and HTTP Header
 * Expect and Continue dialog. These are normally done at the webserver
 * level and can be removed here if desired. (I wrote them before I
 * realized that Mondrian XMLA would not handle any Authorization issues
 * if it were in a webserver.)
 *
 * @author Richard M. Emberson
 */
@SuppressWarnings({"ThrowableInstanceNeverThrown"})
class XmlaErrorTest extends XmlaBaseTestCase
    implements XmlaConstants
{
    static boolean doAuthorization = false;
    static String user = null;
    static String password = null;

    static class Callback implements XmlaRequestCallback {
        static String MY_SESSION_ID = "my_session_id";

        Callback() {
        }

        @Override
		public void init(ServletConfig servletConfig) throws ServletException {
        }

        @Override
		public boolean processHttpHeader(
            HttpServletRequest request,
            HttpServletResponse response,
            Map<String, Object> context) throws Exception
        {
            // look for authorization
            // Authorization: Basic ZWRnZTphYmNkMTIzNC4=
            // tjones:abcd1234$$.

if (DEBUG) {
System.out.println("doAuthorization=" + doAuthorization);
System.out.println("AUTH_TYPE=" + request.getAuthType());
}
            if (doAuthorization) {
                Enumeration values = request.getHeaders(AUTHORIZATION);
                if ((values == null) || (! values.hasMoreElements())) {
                    throw Helper.authorizationException(
                        new Exception("Authorization: no header value"));
                }
                String authScheme = (String) values.nextElement();
if (DEBUG) {
System.out.println("authScheme=" + authScheme);
}
                if (! values.hasMoreElements()) {
                    throw Helper.authorizationException(
                        new Exception("Authorization: too few header value"));
                }

                String encoded = (String) values.nextElement();
if (DEBUG) {
System.out.println("encoded=" + encoded);
}
                byte[] bytes = Base64.decode(encoded);
                String userPass = new String(bytes);
if (DEBUG) {
System.out.println("userPass=" + userPass);
}
                if (! authScheme.equals(HttpServletRequest.BASIC_AUTH)) {
                    throw Helper.authorizationException(
                        new Exception(
                            "Authorization: bad schema: " + authScheme));
                }
                int index = userPass.indexOf(':');
                if (index == -1) {
                    throw Helper.authorizationException(
                        new Exception(
                            "Authorization: badly formed userPass in encoding: "
                            + encoded));
                }
                String userid = userPass.substring(0, index);
                String password =
                    userPass.substring(index + 1, userPass.length());
if (DEBUG) {
System.out.println("userid=" + userid);
System.out.println("password=" + password);
}
                if (!Objects.equals(userid, XmlaErrorTest.user)) {
                    throw Helper.authorizationException(
                        new Exception(
                            "Authorization: bad userid: "
                            + userid
                            + " should be: "
                            + XmlaErrorTest.user));
                }
                if (!Objects.equals(password, XmlaErrorTest.password)) {
                    throw Helper.authorizationException(
                        new Exception(
                            "Authorization: bad password: "
                            + password
                            + " should be: "
                            + XmlaErrorTest.password));
                }
            }

            String expect = request.getHeader(EXPECT);
            if ((expect != null)
                && expect.equalsIgnoreCase(EXPECT_100_CONTINUE))
            {
                Helper.generatedExpectResponse(
                    request, response, context);
                return false;
            } else {
                return true;
            }
         }


        @Override
		public void preAction(
            HttpServletRequest request,
            Element[] requestSoapParts,
            Map<String, Object> context) throws Exception
        {
            context.put(
                MY_SESSION_ID,
                getSessionId("XmlaExcelXPTest", Action.CREATE));
        }

        @Override
		public String generateSessionId(Map<String, Object> context) {
            return (String) context.get(MY_SESSION_ID);
        }

        @Override
		public void postAction(
            HttpServletRequest request,
            HttpServletResponse response,
            byte[][] responseSoapParts,
            Map<String, Object> context) throws Exception
        {
        }
    }

    static Element[] getChildElements(Node node) {
        List<Element> list = new ArrayList<>();

        NodeList nlist = node.getChildNodes();
        int len = nlist.getLength();
        for (int i = 0; i < len; i++) {
            Node child = nlist.item(i);
            if (child instanceof Element) {
                list.add((Element) child);
            }
        }

        return list.toArray(new Element[list.size()]);
    }

    static CharacterData getCharacterData(Node node) {
        NodeList nlist = node.getChildNodes();
        int len = nlist.getLength();
        for (int i = 0; i < len; i++) {
            Node child = nlist.item(i);
            if (child instanceof CharacterData) {
                return (CharacterData) child;
            }
        }
        return null;
    }

    static String getNodeContent(Node n) {
        CharacterData cd = getCharacterData(n);
        return (cd != null)
            ? cd.getData()
            : null;
    }

    private static class Fault {
        final String faultCode;
        final String faultString;
        final String faultActor;
        final String errorNS;
        final String errorCode;
        final String errorDesc;

        Fault(
            String faultCode,
            String faultString,
            String faultActor,
            String errorNS,
            String errorCode,
            String errorDesc)
        {
            this.faultCode = faultCode;
            this.faultString = faultString + " " + errorDesc;
            this.faultActor = faultActor;
            this.errorNS = errorNS;
            this.errorCode = errorCode;
            this.errorDesc = errorDesc;
        }

        Fault(Node[] faultNodes) throws Exception {
            if (faultNodes.length < 3 || faultNodes.length > 4) {
                throw new Exception(
                    "SOAP Fault node has " + faultNodes.length + " children");
            }
            // fault code element
            Node node = faultNodes[0];
            faultCode = getNodeContent(node);

            // fault string element
            node = faultNodes[1];
            faultString = getNodeContent(node);

            // actor element
            node = faultNodes[2];
            faultActor = getNodeContent(node);

            if (faultNodes.length > 3) {
                // detail element
                node = faultNodes[3];
                faultNodes = getChildElements(node);
                if (faultNodes.length != 1) {
                    throw new Exception(
                        "SOAP Fault detail node has "
                        + faultNodes.length
                        + " children");
                }
                // error element
                node = faultNodes[0];
                errorNS = node.getNamespaceURI();
                faultNodes = getChildElements(node);
//                if (faultNodes.length != 2) {
//                    throw new Exception(
//                        "SOAP Fault detail error node has "
//                        + faultNodes.length
//                        + " children");
//                }
                // error code element
                errorCode = node.getAttributes().getNamedItem("ErrorCode").getNodeValue();

                // error desc element
                errorDesc = node.getAttributes().getNamedItem("Description").getNodeValue();
            } else {
                errorNS = errorCode = errorDesc = null;
            }
        }

        String getFaultCode() {
            return faultCode;
        }

        String getFaultString() {
            return faultString;
        }

        String getFaultActor() {
            return faultActor;
        }

        boolean hasDetailError() {
            return (errorCode != null);
        }

        String getDetailErrorCode() {
            return errorCode;
        }

        String getDetailErrorDesc() {
            return errorDesc;
        }

        @Override
		public String toString() {
            return
            "faultCode=" + faultCode + ", faultString=" + faultString
            + ", faultActor=" + faultActor + ", errorNS=" + errorNS
            + ", errorCode=" + errorCode + ", errorDesc=" + errorDesc;
        }

        void checkSame(Fault expectedFault) throws Exception {
            if (!Objects.equals(this.faultCode, expectedFault.faultCode)) {
                notSame("faultcode", expectedFault.faultCode, this.faultCode);
            }
            if (!Objects.equals(this.faultString, expectedFault.faultString)) {
                notSame(
                    "faultstring", expectedFault.faultString, this.faultString);
            }
            if (!Objects.equals(this.faultActor, expectedFault.faultActor)) {
                notSame(
                    "faultactor",
                    expectedFault.faultActor,
                    this.faultActor);
            }
            if (!Objects.equals(this.errorNS, expectedFault.errorNS)) {
                throw new Exception(
                    "For error element namespace "
                    + " Expected "
                    + expectedFault.errorNS
                    + " but Got "
                    + this.errorNS);
            }
            if (!Objects.equals(this.errorCode, expectedFault.errorCode)) {
                notSame("error.code", expectedFault.errorCode, this.errorCode);
            }
        }

        private void notSame(String elementName, String expected, String got)
            throws Exception
        {
            throw new Exception(
                "For element " + elementName
                + " expected [" + expected
                + "] but got [" + got + "]");
        }
    }

    private static PrintStream systemErr;

    @BeforeEach
    public void beforeEach() throws ClassNotFoundException {
        // NOTE jvs 27-Feb-2007:  Since this test produces errors
        // intentionally, squelch the ones that SAX produces on stderr
        systemErr = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream()));
        Class.forName(MondrianOlap4jDriver.class.getName());

    }

    @AfterEach
    public void afterEach() {
        System.setErr(systemErr);
        tearDown();
    }

    @Override
	protected DiffRepository getDiffRepos() {
        return DiffRepository.lookup(XmlaErrorTest.class);
    }

    @Override
	protected Class<? extends XmlaRequestCallback> getServletCallbackClass() {
        return Callback.class;
    }




    /////////////////////////////////////////////////////////////////////////
    // tests
    /////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////
    // bad XML
    /////////////////////////////////////////////////////////////////////////

    // junk rather than xml
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testJunk(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    USM_DOM_PARSE_CODE),
                    USM_DOM_PARSE_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Premature end of file.");

        doTest(expectedFault, context.createConnection());
    }

    // bad soap envolope element tag
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadXml01(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    USM_DOM_PARSE_CODE),
                    USM_DOM_PARSE_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "The element type \"soapenv:FOOEnvelope\" must be terminated by the matching end-tag \"</soapenv:FOOEnvelope>\".");

        doTest(expectedFault, context.createConnection());
    }

    // bad soap namespace
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadXml02(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    USM_DOM_PARSE_CODE),
                    USM_DOM_PARSE_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid SOAP message: Envelope element not in SOAP namespace");

        doTest(expectedFault, context.createConnection());
    }

    /////////////////////////////////////////////////////////////////////////
    // bad action
    /////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadAction01(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_SOAP_BODY_CODE),
                    HSB_BAD_SOAP_BODY_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Body has 0 Discover Requests and 0 Execute Requests");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadAction02(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_SOAP_BODY_CODE),
                    HSB_BAD_SOAP_BODY_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Body has 2 Discover Requests and 0 Execute Requests");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadAction03(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_SOAP_BODY_CODE),
                    HSB_BAD_SOAP_BODY_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Body has 1 Discover Requests and 1 Execute Requests");

        doTest(expectedFault, context.createConnection());
    }

    /////////////////////////////////////////////////////////////////////////
    // bad soap structure
    /////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadSoap01(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    USM_DOM_PARSE_CODE),
                    USM_DOM_PARSE_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid SOAP message: More than one Header elements");

        doTest(expectedFault, context.createConnection());
    }

    void testBadSoap02(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    USM_DOM_PARSE_CODE),
                    USM_DOM_PARSE_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    USM_DOM_PARSE_CODE, null);

        doTest(expectedFault, context.createConnection());
    }

    /////////////////////////////////////////////////////////////////////////
    // authorization
    /////////////////////////////////////////////////////////////////////////

    // no authorization field in header
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testAuth01(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    CHH_AUTHORIZATION_CODE),
                    CHH_AUTHORIZATION_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Authorization: no header value");
        doAuthorization = true;
        try {
            doTest(expectedFault, context.createConnection());
        } finally {
            doAuthorization = false;
        }
    }

    // the user/password is not base64 encode and no ':' character
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testAuth02(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    CHH_AUTHORIZATION_CODE),
                    CHH_AUTHORIZATION_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Authorization: badly formed userPass in encoding: FOOBAR");

        doAuthorization = true;

        String requestText = fileToString("request");
        byte[] reqBytes = requestText.getBytes();

        MockHttpServletRequest req = new MockHttpServletRequest(reqBytes);
        req.setMethod("POST");
        req.setContentType("text/xml");

        req.setAuthType(HttpServletRequest.BASIC_AUTH);
        req.setHeader(
            XmlaRequestCallback.AUTHORIZATION, HttpServletRequest.BASIC_AUTH);
        req.setHeader(XmlaRequestCallback.AUTHORIZATION, "FOOBAR");

        try {
            doTest(req, expectedFault, context.createConnection());
        } finally {
            doAuthorization = false;
        }
    }

    // this should work
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testAuth03(TestContextWrapper context) throws Exception {
        Fault expectedFault = null;

        doAuthorization = true;

        String requestText = fileToString("request");
        byte[] reqBytes = requestText.getBytes();

        MockHttpServletRequest req = new MockHttpServletRequest(reqBytes);
        req.setMethod("POST");
        req.setContentType("text/xml");

        req.setAuthType(HttpServletRequest.BASIC_AUTH);
        req.setHeader(
            XmlaRequestCallback.AUTHORIZATION, HttpServletRequest.BASIC_AUTH);

        String user = "MY_USER";
        String password = "MY_PASSWORD";
        XmlaErrorTest.user = user;
        XmlaErrorTest.password = password;
        String credential = user + ':' + password;
        String encoded = Base64.encodeBytes(credential.getBytes());

        req.setHeader(XmlaRequestCallback.AUTHORIZATION, encoded);

        try {
            doTest(req, expectedFault, context.createConnection());
            req.setHeader(
                XmlaRequestCallback.EXPECT,
                XmlaRequestCallback.EXPECT_100_CONTINUE);
if (DEBUG) {
System.out.println("DO IT AGAIN");
}
            doTest(req, expectedFault, context.createConnection());
        } finally {
            XmlaErrorTest.doAuthorization = false;
            XmlaErrorTest.user = null;
            XmlaErrorTest.password = null;
        }
    }

    // fail: bad user name
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testAuth04(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    CHH_AUTHORIZATION_CODE),
                    CHH_AUTHORIZATION_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Authorization: bad userid: MY_USER should be: MY_USERFOO");

        doAuthorization = true;

        String requestText = fileToString("request");
        byte[] reqBytes = requestText.getBytes();

        MockHttpServletRequest req = new MockHttpServletRequest(reqBytes);
        req.setMethod("POST");
        req.setContentType("text/xml");

        req.setAuthType(HttpServletRequest.BASIC_AUTH);
        req.setHeader(
            XmlaRequestCallback.AUTHORIZATION, HttpServletRequest.BASIC_AUTH);

        String user = "MY_USER";
        String password = "MY_PASSWORD";
        XmlaErrorTest.user = user + "FOO";
        XmlaErrorTest.password = password;
        String credential = user + ':' + password;
        String encoded = Base64.encodeBytes(credential.getBytes());

        req.setHeader(XmlaRequestCallback.AUTHORIZATION, encoded);

        try {
            doTest(req, expectedFault, context.createConnection());
        } finally {
            XmlaErrorTest.doAuthorization = false;
            XmlaErrorTest.user = null;
            XmlaErrorTest.password = null;
        }
    }

    // fail: bad password
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testAuth05(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    CHH_AUTHORIZATION_CODE),
                    CHH_AUTHORIZATION_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Authorization: bad password: MY_PASSWORD should be: MY_PASSWORDFOO");

        doAuthorization = true;

        String requestText = fileToString("request");
        byte[] reqBytes = requestText.getBytes();

        MockHttpServletRequest req = new MockHttpServletRequest(reqBytes);
        req.setMethod("POST");
        req.setContentType("text/xml");

        req.setAuthType(HttpServletRequest.BASIC_AUTH);
        req.setHeader(
            XmlaRequestCallback.AUTHORIZATION, HttpServletRequest.BASIC_AUTH);

        String user = "MY_USER";
        String password = "MY_PASSWORD";
        XmlaErrorTest.user = user;
        XmlaErrorTest.password = password + "FOO";
        String credential = user + ':' + password;
        String encoded = Base64.encodeBytes(credential.getBytes());

        req.setHeader(XmlaRequestCallback.AUTHORIZATION, encoded);

        try {
            doTest(req, expectedFault, context.createConnection());
        } finally {
            XmlaErrorTest.doAuthorization = false;
            XmlaErrorTest.user = null;
            XmlaErrorTest.password = null;
        }
    }

    // bad header
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadHeader01(TestContextWrapper context) throws Exception {
        // remember, errors in headers do not have detail sections
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    MUST_UNDERSTAND_FAULT_FC,
                    HSH_MUST_UNDERSTAND_CODE),
                    HSH_MUST_UNDERSTAND_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Unknown \"mustUnderstand\" XMLA Header element \"Foo\"");

        doTest(expectedFault, context.createConnection());
    }

    // bad body
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody01(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_SOAP_BODY_CODE),
                    HSB_BAD_SOAP_BODY_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Body has 1 Discover Requests and 1 Execute Requests");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody02(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_SOAP_BODY_CODE),
                    HSB_BAD_SOAP_BODY_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Body has 0 Discover Requests and 0 Execute Requests");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody03(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_SOAP_BODY_CODE),
                    HSB_BAD_SOAP_BODY_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Invalid XML/A message: Body has 0 Discover Requests and 0 Execute Requests");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody04(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_REQUEST_TYPE_CODE),
                    HSB_BAD_REQUEST_TYPE_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of RequestType elements: 0");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody05(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_RESTRICTIONS_CODE),
                    HSB_BAD_RESTRICTIONS_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of Restrictions elements: 0");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody06(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_PROPERTIES_CODE),
                    HSB_BAD_PROPERTIES_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of Properties elements: 0");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody07(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_COMMAND_CODE),
                    HSB_BAD_COMMAND_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of Command elements: 0");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody08(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_PROPERTIES_CODE),
                    HSB_BAD_PROPERTIES_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of Properties elements: 0");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody09(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_RESTRICTION_LIST_CODE),
                    HSB_BAD_RESTRICTION_LIST_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of RestrictionList elements: 2");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody10(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_PROPERTIES_LIST_CODE),
                    HSB_BAD_PROPERTIES_LIST_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of PropertyList elements: 2");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody11(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_PROPERTIES_LIST_CODE),
                    HSB_BAD_PROPERTIES_LIST_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of PropertyList elements: 2");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody12(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    CLIENT_FAULT_FC,
                    HSB_BAD_COMMAND_CODE),
                    HSB_BAD_COMMAND_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Internal error: Invalid XML/A message: Wrong number of Command children elements: 2");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody13(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                	SERVER_FAULT_FC,
                    HSB_PROCESS_CODE),
                    HSB_PROCESS_FAULT_FS,
                    FAULT_ACTOR,
                    null,
                    "3238658121",
                    "Mondrian Error:Syntax error at line 2, column 22, token '-'");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody14(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    	SERVER_FAULT_FC,
                        HSB_PROCESS_CODE),
                        HSB_PROCESS_FAULT_FS,
                        FAULT_ACTOR,
                        null,
                        "3238658121",
                        "Mondrian Error:Syntax error at line 2, column 39, token '-'");

        doTest(expectedFault, context.createConnection());
    }

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testBadBody15(TestContextWrapper context) throws Exception {
        Fault expectedFault =
            new Fault(
                XmlaException.formatFaultCode(
                    	SERVER_FAULT_FC,
                        HSB_PROCESS_CODE),
                        HSB_PROCESS_FAULT_FS,
                        FAULT_ACTOR,
                        null,
                        "3238658121",
                        "Mondrian Error:Syntax error at line 5, column 1, token 'DRILLTHROUGH'");

        doTest(expectedFault, context.createConnection());
    }


    /////////////////////////////////////////////////////////////////////////
    // helper
    /////////////////////////////////////////////////////////////////////////

    protected void doTest(
        MockHttpServletRequest req,
        Fault expectedFault, Connection connection) throws Exception
    {
        MockHttpServletResponse res = new MockHttpServletResponse();
        res.setCharacterEncoding("UTF-8");

        Servlet servlet = getServlet(connection);
        servlet.service(req, res);

        int statusCode = res.getStatusCode();
        if (statusCode == HttpServletResponse.SC_OK) {
            byte[] bytes = res.toByteArray();
            processResults(bytes, expectedFault);

        } else if (statusCode == HttpServletResponse.SC_UNAUTHORIZED) {
            byte[] bytes = res.toByteArray();
            processResults(bytes, expectedFault);

        } else if (statusCode == HttpServletResponse.SC_CONTINUE) {
            // remove the Expect header from request and try again
if (DEBUG) {
System.out.println("Got CONTINUE");
}

            req.clearHeader(XmlaRequestCallback.EXPECT);
            req.clearHeader(XmlaRequestCallback.AUTHORIZATION);
            doAuthorization = false;

            servlet.service(req, res);

            statusCode = res.getStatusCode();
            if (statusCode == HttpServletResponse.SC_OK) {
                byte[] bytes = res.toByteArray();
                processResults(bytes, expectedFault);
            } else {
                fail("Bad status code: " + statusCode);
            }
        } else {
            fail("Bad status code: " + statusCode);
        }
    }

    protected void doTest(
            Fault expectedFault, Connection connection
            ) throws Exception
    {
    	java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(connection.getContext()));
        String requestText = fileToString("request");
        Servlet servlet = getServlet(connection);
        // do SOAP-XMLA
        byte[] bytes = XmlaSupport.processSoapXmla(requestText, servlet);
        processResults(bytes, expectedFault);
    }

    protected void processResults(byte[] results, Fault expectedFault)
        throws Exception
    {
if (DEBUG) {
String response = new String(results);
System.out.println("response=" + response);
}
        Node[] fnodes = XmlaSupport.extractFaultNodesFromSoap(results);
        if ((fnodes == null) || (fnodes.length == 0)) {
            if (expectedFault != null) {
                 // error
                fail("Failed to get SOAP Fault element in SOAP Body node");
            }
        }
        if (expectedFault != null) {
            Fault fault = new Fault(fnodes);

if (DEBUG) {
System.out.println("fault=" + fault);
System.out.println("expectedFault=" + expectedFault);
}
            fault.checkSame(expectedFault);
        }
    }

    @Override
	protected String getSessionId(Action action) {
        return getSessionId("XmlaExcelXPTest", action);
    }
}
