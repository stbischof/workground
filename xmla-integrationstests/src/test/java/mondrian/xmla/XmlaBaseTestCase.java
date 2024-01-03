/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2017 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.xmla;

import static org.junit.jupiter.api.Assertions.fail;
import static org.opencube.junit5.TestUtil.upgradeActual;
import static org.opencube.junit5.TestUtil.upgradeQuery;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.olap4j.metadata.XmlaConstants;
import org.opencube.junit5.context.TestContextWrapper;
import org.opentest4j.AssertionFailedError;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlunit.assertj3.XmlAssert;

import mondrian.olap.MondrianServer;
import mondrian.olap.Util;
import mondrian.olap.Util.PropertyList;
import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapConnectionProperties;
import mondrian.server.Session;
import mondrian.test.DiffRepository;
import mondrian.tui.MockHttpServletRequest;
import mondrian.tui.MockHttpServletResponse;
import mondrian.tui.XmlUtil;
import mondrian.tui.XmlaSupport;
import mondrian.util.LockBox;
import mondrian.xmla.test.XmlaTestContext;

/**
 * Extends FoodMartTestCase, adding support for testing XMLA specific
 * functionality, for example LAST_SCHEMA_UPDATE
 *
 * @author mkambol
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public abstract class XmlaBaseTestCase {
    protected static final String SCHEMA_TEST_DATE =
        "xxxx-xx-xxTxx:xx:xx";
    private static final String LAST_SCHEMA_UPDATE_NODE_NAME =
        "LAST_SCHEMA_UPDATE";
    private static final String LastSchemaUpdate_NODE_NAME =
        "LastSchemaUpdate";
    private static final String LAST_DATA_UPDATE_NODE_NAME =
        "LAST_DATA_UPDATE";
    private static final String LastDataUpdate_NODE_NAME =
        "LastDataUpdate";
    protected SortedMap<String, String> catalogNameUrls = null;
    private static final String DATE_MODIFIED_NODE_NAME =
            "DATE_MODIFIED";

    private static int sessionIdCounter = 1000;
    private static Map<String, String> sessionIdMap =
        new HashMap<>();
    // session id property
    public static final String SESSION_ID_PROP     = "session.id";
    // request.type
    public static final String REQUEST_TYPE_PROP =
        "request.type";// data.source.info
    public static final String DATA_SOURCE_INFO_PROP = "data.source.info";

    private static final String DATA_SOURCE_INFO_RESPONSE_PROP =
        "data.source.info.response";

    public static final String DATA_SOURCE_INFO = "FoodMart";// catalog
    public static final String CATALOG_PROP     = "catalog";
    public static final String CATALOG_NAME_PROP = "catalog.name";
    public static final String CATALOG          = "FoodMart";// cube
    public static final String CUBE_NAME_PROP   = "cube.name";
    public static final String SALES_CUBE       = "Sales";// format
    public static final String FORMAT_PROP     = "format";
    public static final String FORMAT_MULTI_DIMENSIONAL = "Multidimensional";
    public static final String ROLE_PROP = "Role";
    public static final String LOCALE_PROP = "locale";
    protected static final boolean DEBUG = false;

    /**
     * Cache servlet instances between test invocations. Prevents creation
     * of many spurious MondrianServer instances.
     */
    private final HashMap<List<String>, Servlet>
        SERVLET_CACHE = new HashMap<>();

    /**
     * Cache servlet instances between test invocations. Prevents creation
     * of many spurious MondrianServer instances.
     */
    private final HashMap<List<String>, MondrianServer>
        SERVER_CACHE = new HashMap<>();
    static final String PID_XMLA_SERVICE = "org.eclipse.daanse.olap.xmla.bridge.ContextGroupXmlaService";

    @InjectService(timeout = 5000, filter = "(component.name=" + PID_XMLA_SERVICE + ")")
    XmlaService xmlaService;

    protected void tearDown() {
        for (MondrianServer server : SERVER_CACHE.values()) {
            server.shutdown();
        }
        SERVER_CACHE.clear();
        for (Servlet servlet : SERVLET_CACHE.values()) {
            servlet.destroy();
        }
        SERVLET_CACHE.clear();
    }

    protected String generateExpectedString(Properties props)
        throws Exception
    {
        String expectedStr = fileToString("response");
        if (props != null) {
            // YES, duplicate the above
            String sessionId = getSessionId(Action.QUERY);
            if (sessionId != null) {
                props.put(SESSION_ID_PROP, sessionId);
            }
            expectedStr = Util.replaceProperties(
                expectedStr, Util.toMap(props));
        }
        return expectedStr;
    }

    protected String generateRequestString(Properties props)
        throws Exception
    {
        String requestText = fileToString("request");

        if (props != null) {
            String sessionId = getSessionId(Action.QUERY);
            if (sessionId != null) {
                props.put(SESSION_ID_PROP, sessionId);
            }
            requestText = Util.replaceProperties(
                requestText, Util.toMap(props));
        }
if (DEBUG) {
System.out.println("requestText=" + requestText);
}
        return requestText;
    }

    protected void validate(
        byte[] bytes,
        Document expectedDoc,
        boolean replace,
        boolean validate)
        throws Exception
    {
        if (validate && XmlUtil.supportsValidation()) {
            if (XmlaSupport.validateSoapXmlaUsingXpath(bytes)) {
                if (DEBUG) {
                    System.out.println("XML Data is Valid");
                }
            }
        }

        Document gotDoc = XmlUtil.parse(bytes);
        gotDoc = replaceSchemaDates(gotDoc);
        String gotStr = XmlUtil.toString(gotDoc, true);
        gotStr = maskVersion(gotStr);
        gotStr = upgradeActual(gotStr);
        if (expectedDoc == null) {
            if (replace) {
                getDiffRepos().amend("${response}", gotStr);
            }
            return;
        }
        expectedDoc = replaceSchemaDates(expectedDoc);
        String expectedStr = XmlUtil.toString(expectedDoc, true);
        expectedStr = maskVersion(expectedStr);
        try {
            XmlAssert.assertThat(expectedStr).and(gotStr).areSimilar();
        } catch (AssertionFailedError e) {
            // Let DiffRepository do the comparison. It will output
            // a textual difference, and will update the logfile,
            // XmlaBasicTest.log.xml. If you agree with the change,
            // copy this file to XmlaBasicTest.ref.xml.
            if (replace) {
                gotStr =
                    gotStr.replaceAll(
                        " SessionId=\"[^\"]*\" ",
                        " SessionId=\"\\${session.id}\" ");
                getDiffRepos().assertEquals(
                    "response",
                    "${response}",
                    gotStr);
            } else {
                throw e;
            }
        }
    }

    public void doTest(Connection connection, Properties props) throws Exception {
        String requestText = generateRequestString(props);
        Document reqDoc = XmlUtil.parseString(requestText);

        //Servlet servlet = getServlet(connection);
        TestServlet servlet = new TestServlet(xmlaService);
        byte[] bytes = XmlaSupport.processSoapXmla(reqDoc, servlet);

        String expectedStr = generateExpectedString(props);
        Document expectedDoc = XmlUtil.parseString(expectedStr);
        validate(bytes, expectedDoc, true, true);
    }

    protected void doTest(Connection connection,
        MockHttpServletRequest req,
        Properties props) throws Exception
    {
        String requestText = generateRequestString(props);

        MockHttpServletResponse res = new MockHttpServletResponse();
        res.setCharacterEncoding("UTF-8");

        //Servlet servlet = getServlet(connection);

        Servlet servlet = new TestServlet(xmlaService);
        servlet.service(req, res);

        int statusCode = res.getStatusCode();
        if (statusCode == HttpServletResponse.SC_OK) {
            byte[] bytes = res.toByteArray();
            String expectedStr = generateExpectedString(props);
            Document expectedDoc = XmlUtil.parseString(expectedStr);
            validate(bytes, expectedDoc, true, true);

        } else if (statusCode == HttpServletResponse.SC_CONTINUE) {
            // remove the Expect header from request and try again
if (DEBUG) {
System.out.println("Got CONTINUE");
}

            req.clearHeader(XmlaRequestCallback.EXPECT);
            req.setBodyContent(requestText);

            servlet.service(req, res);

            statusCode = res.getStatusCode();
            if (statusCode == HttpServletResponse.SC_OK) {
                byte[] bytes = res.toByteArray();
                String expectedStr = generateExpectedString(props);
                Document expectedDoc = XmlUtil.parseString(expectedStr);
                validate(
                    bytes, expectedDoc, true, true);

            } else {
                fail("Bad status code: "  + statusCode);
            }
        } else {
            fail("Bad status code: "  + statusCode);
        }
    }

    /*
    private XmlaService getXmlaService(Context context) {
        ContextGroup contextGroup = new ContextGroup() {
            @Override
            public List<Context> getValidContexts() {
                return List.of(context);
            }
        };
        ContextListSupplyer contextsListSupplyer = new ContextsSupplyerImpl(contextGroup);
        ContextGroupXmlaServiceConfig config = mock(ContextGroupXmlaServiceConfig.class);
        DiscoverService discoverService = new DelegatingDiscoverService(contextsListSupplyer, config);
        ExecuteService executeService = new OlapExecuteService(contextsListSupplyer, config);
      return new XmlaService() {
          @Override
          public DiscoverService discover() {
              return discoverService;
          }

          @Override
          public ExecuteService execute() {
              return executeService;
          }
      };
    }
     */

    protected void helperTestExpect(TestContextWrapper context, boolean doSessionId)
    {
        try {
            //java.sql.DriverManager.registerDriver(new XmlaOlap4jDriver());// finy out why this dies not happend automatically
            java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(context.getContext()));// finy out why this dies not happend automatically
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (doSessionId) {
        	String sessionId = getSessionId(Action.CREATE);
            Util.discard(sessionId);
            try {
            	Session session = Session.getWithoutCheck(sessionId);
            	if(session == null) {
            		Session.create(sessionId);
            	}
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        req.setContentType("text/xml");
        req.setHeader(
            XmlaRequestCallback.EXPECT,
            XmlaRequestCallback.EXPECT_100_CONTINUE);

        Properties props = new Properties();
        addDatasourceInfoResponseKey(context, props);
        try {
            doTest(context.getContext().getConnection(), req, props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void helperTest(TestContextWrapper context, boolean doSessionId)
    {
        try {
            //java.sql.DriverManager.registerDriver(new XmlaOlap4jDriver());// finy out why this dies not happend automatically
            java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver());// finy out why this dies not happend automatically
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (doSessionId) {
            String sessionId = getSessionId(Action.CREATE);
            try {
            	Session session = Session.getWithoutCheck(sessionId);
            	if(session == null) {
            		Session.create(sessionId);
            	}
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Properties props = new Properties();
        addDatasourceInfoResponseKey(context, props);
        try {
            doTest(context.getContext().getConnection(), props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void addDatasourceInfoResponseKey(TestContextWrapper context, Properties props) {
        XmlaTestContext s = new XmlaTestContext();
        String con = s.getConnectString(context).replaceAll("&amp;","&");
        PropertyList pl = Util.parseConnectString(con);

        props.setProperty(DATA_SOURCE_INFO_RESPONSE_PROP, pl.toString());
    }

    static class CallBack implements XmlaRequestCallback {
        public CallBack() {
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
            return true;
        }

        @Override
		public void preAction(
            HttpServletRequest request,
            Element[] requestSoapParts,
            Map<String, Object> context) throws Exception
        {
        }

        @Override
		public String generateSessionId(Map<String, Object> context) {
            return null;
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


    protected abstract DiffRepository getDiffRepos();

    protected String fileToString(String filename) throws Exception {
        String var = "${" + filename + "}";
        String s = getDiffRepos().expand(null, var);
        if (s.startsWith("$")) {
            getDiffRepos().amend(var, "\n\n");
        }
        // Give derived class a chance to change the content.
        s = filter(getDiffRepos().getCurrentTestCaseName(true), filename, s);
        return s;
    }

    protected Document replaceSchemaDates(Document doc) {
        NodeList elements =
            doc.getElementsByTagName(LAST_SCHEMA_UPDATE_NODE_NAME);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            node.getFirstChild().setNodeValue(SCHEMA_TEST_DATE);
        }
        elements =
                doc.getElementsByTagName(LastSchemaUpdate_NODE_NAME);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            node.getFirstChild().setNodeValue(SCHEMA_TEST_DATE);
        }
        elements =
                doc.getElementsByTagName(LAST_DATA_UPDATE_NODE_NAME);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            node.getFirstChild().setNodeValue(SCHEMA_TEST_DATE);
        }
        elements =
                doc.getElementsByTagName(LastDataUpdate_NODE_NAME);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            node.getFirstChild().setNodeValue(SCHEMA_TEST_DATE);
        }
        elements =
                doc.getElementsByTagName(DATE_MODIFIED_NODE_NAME);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            node.getFirstChild().setNodeValue(SCHEMA_TEST_DATE);
        }
        return doc;
    }

    private String ignoreLastUpdateDate(String document) {
        document = document.replaceAll(
            "\"LAST_SCHEMA_UPDATE\": \"....-..-..T..:..:..\"",
            "\"LAST_SCHEMA_UPDATE\": \"" + SCHEMA_TEST_DATE + "\"");
        document = document.replaceAll(
            "\"LAST_DATA_UPDATE\": \"....-..-..T..:..:..\"",
            "\"LAST_DATA_UPDATE\": \"" + SCHEMA_TEST_DATE + "\"");
        document = document.replaceAll(
            "\"LastDataUpdate\": \\{\"....-..-..T..:..:..\"",
            "\"LastDataUpdate\": \\{\"" + SCHEMA_TEST_DATE + "\"");
        document = document.replaceAll(
            "\"LastSchemaUpdate\": \\{\"....-..-..T..:..:..\"",
            "\"LastSchemaUpdate\": \\{\"" + SCHEMA_TEST_DATE + "\"");
        return document;
    }

    protected Map<String, String> getCatalogNameUrls(Connection connection) {
        if (catalogNameUrls == null) {
            catalogNameUrls = new TreeMap<>();
            String connectString = ((RolapConnection)connection).getConnectInfo().toString();
            PropertyList connectProperties =
                        Util.parseConnectString(connectString);
            String catalog = connectProperties.get(
                RolapConnectionProperties.Catalog.name());
            catalogNameUrls.put("FoodMart", catalog);
        }
        return catalogNameUrls;
    }

    protected Servlet getServlet(Connection connection)
        throws IOException, ServletException, SAXException
    {
        getSessionId(Action.CLEAR);

        String connectString = ((RolapConnection)connection).getConnectInfo().toString();
        Map<String, String> catalogNameUrls =
            getCatalogNameUrls(connection);
        connectString = filterConnectString(connectString);
        return
            XmlaSupport.makeServlet(
                connectString,
                catalogNameUrls,
                getServletCallbackClass().getName(),
                SERVLET_CACHE);
    }

    protected String filterConnectString(String original) {
        return original;
    }

    protected abstract Class<? extends XmlaRequestCallback>
    getServletCallbackClass();

    protected Properties getDefaultRequestProperties(String requestType) {
        Properties props = new Properties();
        props.setProperty(REQUEST_TYPE_PROP, requestType);
        props.setProperty(CATALOG_PROP, CATALOG);
        props.setProperty(CATALOG_NAME_PROP, CATALOG);
        props.setProperty(CUBE_NAME_PROP, SALES_CUBE);
        props.setProperty(FORMAT_PROP, FORMAT_MULTI_DIMENSIONAL);
        props.setProperty(DATA_SOURCE_INFO_PROP, DATA_SOURCE_INFO);
        return props;
    }

    protected Document fileToDocument(String filename, Properties props)
        throws IOException, SAXException
    {
        final String var = "${" + filename + "}";
        String s = getDiffRepos().expand(null, var);
        s = Util.replaceProperties(
            s, Util.toMap(props));
        if (s.equals(filename)) {
            s = "<?xml version='1.0'?><Empty/>";
            getDiffRepos().amend(var, s);
        }
        // Give derived class a chance to change the content.
        s = filter(getDiffRepos().getCurrentTestCaseName(true), filename, s);

        return XmlUtil.parse(new ByteArrayInputStream(s.getBytes()));
    }

    /**
     * Filters the content of a test resource. The default implementation
     * returns the content unchanged, but a derived class might override this
     * method to change the content.
     *
     * @param testCaseName Name of current test case, e.g. "testFoo"
     * @param filename Name of requested content, e.g.  "${request}"
     * @param content Content
     * @return Modified content
     */
    protected String filter(
        String testCaseName,
        String filename,
        String content)
    {
        Util.discard(testCaseName); // might be used by derived class
        Util.discard(filename); // might be used by derived class
        return content;
    }

    /**
     * Executes an XMLA request, reading the text of the request and the
     * response from attributes in {@link #getDiffRepos()}.
     *
     * @param requestType Request type: "DISCOVER_DATASOURCES", "EXECUTE", etc.
     * @param props Properties for request
     * @param connection Connection
     * @throws Exception on error
     */
    public void doTest(
        String requestType,
        Properties props,
        Connection connection) throws Exception
    {
        doTest(requestType, props, connection, null);
    }

    public void doTest(
        String requestType,
        Properties props,
        Connection connection,
        Role role) throws Exception
    {
        String requestText = fileToString("request");
        requestText = upgradeQuery(requestText);
        doTestInline(
            requestType, requestText, "response",
            props, connection, role);
    }

    public void doTestInline(
        String requestType,
        String requestText,
        String respFileName,
        Properties props,
        Connection connection)
        throws Exception
    {
        doTestInline(
            requestType, requestText, respFileName,
            props, connection , null);
    }

    public void doTestInline(
        String requestType,
        String requestText,
        String respFileName,
        Properties props,
        Connection connection,
        Role role)
        throws Exception
    {
        String connectString = ((RolapConnection)connection).getConnectInfo().toString();
        Map<String, String> catalogNameUrls = getCatalogNameUrls(connection);

        boolean xml = !requestText.contains("application/json");
        if (!xml) {
            String responseStr = (respFileName != null)
                ? fileToString(respFileName)
                : null;
            doTestsJson(
                requestText, props, connection, connectString, catalogNameUrls,
                responseStr, XmlaConstants.Content.Data, role);
            return;
        }

        final Document responseDoc = (respFileName != null)
            ? fileToDocument(respFileName, props)
            : null;
        Document expectedDoc;

        // Test 'schemadata' first, so that if it fails, we will be able to
        // amend the ref file with the fullest XML response.
        final String ns = "cxmla";
        expectedDoc = (responseDoc != null)
            ? XmlaSupport.transformSoapXmla(
                responseDoc, new String[][] {{"content", "schemadata"}}, ns)
            : null;
        doTests(
            requestText, props,
                connection, connectString, catalogNameUrls,
            expectedDoc, XmlaConstants.Content.SchemaData, role, true);

        if (requestType.equals("EXECUTE")) {
            return;
        }

        expectedDoc = (responseDoc != null)
            ? XmlaSupport.transformSoapXmla(
                responseDoc, new String[][] {{"content", "none"}}, ns)
            : null;
        doTests(
            requestText, props,
                connection, connectString, catalogNameUrls,
            expectedDoc, XmlaConstants.Content.None, role, false);

        expectedDoc = (responseDoc != null)
            ? XmlaSupport.transformSoapXmla(
                responseDoc, new String[][] {{"content", "data"}}, ns)
            : null;
        doTests(
            requestText, props,
                connection, connectString, catalogNameUrls,
            expectedDoc, XmlaConstants.Content.Data, role, false);

        expectedDoc = (responseDoc != null)
            ? XmlaSupport.transformSoapXmla(
                responseDoc, new String[][] {{"content", "schema"}}, ns)
            : null;
        doTests(
            requestText, props,
            connection, connectString, catalogNameUrls,
            expectedDoc, XmlaConstants.Content.Schema, role, false);
    }

    /**
     * Executes a SOAP request.
     *
     * @param soapRequestText SOAP request
     * @param props Name/value pairs to substitute in the request
     * @param connection Connection
     * @param connectString Connect string
     * @param catalogNameUrls Map from catalog names to URL
     * @param expectedDoc Expected SOAP output
     * @param content Content type
     * @param role Role in which to execute query, or null
     * @param replace Whether to generate a replacement reference log into
     *    TestName.log.xml if there is an exception. If you are running the same
     *    request with different content types and the same reference log, you
     *    should pass {@code true} for the content type that has the most
     *    information (generally
     *    {@link XmlaConstants.Content#SchemaData})
     * @throws Exception on error
     */
    protected void doTests(
        String soapRequestText,
        Properties props,
        Connection connection,
        String connectString,
        Map<String, String> catalogNameUrls,
        Document expectedDoc,
        XmlaConstants.Content content,
        Role role,
        boolean replace) throws Exception
    {
        if (content != null) {
            props.setProperty(XmlaBasicTest.CONTENT_PROP, content.name());
        }

        // Even though it is not used, it is important that entry is in scope
        // until after request has returned. Prevents role's lock box entry from
        // being garbage collected.
        LockBox.Entry entry = null;
        if (role != null) {
            final MondrianServer mondrianServer =
                MondrianServer.forConnection(connection);
            entry = mondrianServer.getLockBox().register(role);
            connectString += "; Role='" + entry.getMoniker() + "'";
            props.setProperty(XmlaBaseTestCase.ROLE_PROP, entry.getMoniker());
        }
        soapRequestText = Util.replaceProperties(
            soapRequestText, Util.toMap(props));

        Document soapReqDoc = XmlUtil.parseString(soapRequestText);
        Document xmlaReqDoc = XmlaSupport.extractBodyFromSoap(soapReqDoc);

        // do XMLA
        byte[] bytes =
            XmlaSupport.processXmla(
                xmlaReqDoc, filterConnectString(connectString),
                catalogNameUrls, role, SERVER_CACHE);
        if (XmlUtil.supportsValidation()
            // We can't validate against the schema when the content type
            // is Data because it doesn't respect the XDS.
            && !content.equals(XmlaConstants.Content.Data))
        {
            // Validating requires a <?xml header.
            String response = new String(bytes);
            if (!response.startsWith("<?xml version=\"1.0\"?>")) {
                response =
                    "<?xml version=\"1.0\"?>"
                    + Util.NL
                    + response;
            }
            if (XmlaSupport.validateXmlaUsingXpath(response.getBytes())) {
                if (DEBUG) {
                    System.out.println(
                        "XmlaBaseTestCase.doTests: XML Data is Valid");
                }
            }
        }

        // do SOAP-XMLA
        String callBackClassName = CallBack.class.getName();
        bytes = XmlaSupport.processSoapXmla(
            soapReqDoc,
            filterConnectString(connectString),
            catalogNameUrls,
            callBackClassName,
            role,
            SERVLET_CACHE);

        if (DEBUG) {
            System.out.println(
                "XmlaBaseTestCase.doTests: soap response="
                + new String(bytes));
        }

        validate(
            bytes, expectedDoc, replace,
            content.equals(XmlaConstants.Content.Data) ? false : true);
        Util.discard(entry);
    }

    protected void doTestsJson(
        String soapRequestText,
        Properties props,
        Connection connection,
        String connectString,
        Map<String, String> catalogNameUrls,
        String expectedStr,
        XmlaConstants.Content content,
        Role role) throws Exception
    {
        if (content != null) {
            props.setProperty(XmlaBasicTest.CONTENT_PROP, content.name());
        }
        if (role != null) {
            final MondrianServer mondrianServer =
                MondrianServer.forConnection(connection);
            final LockBox.Entry entry =
                mondrianServer.getLockBox().register(role);
            props.setProperty(XmlaBaseTestCase.ROLE_PROP, entry.getMoniker());
        }
        soapRequestText = Util.replaceProperties(
            soapRequestText, Util.toMap(props));

        Document soapReqDoc = XmlUtil.parseString(soapRequestText);
        Document xmlaReqDoc = XmlaSupport.extractBodyFromSoap(soapReqDoc);

        // do XMLA
        byte[] bytes =
            XmlaSupport.processXmla(
                xmlaReqDoc, connectString, catalogNameUrls, role, SERVER_CACHE);

        // do SOAP-XMLA
        String callBackClassName = CallBack.class.getName();
        bytes = XmlaSupport.processSoapXmla(
            soapReqDoc,
            connectString,
            catalogNameUrls,
            callBackClassName,
            role,
            SERVLET_CACHE);
        if (DEBUG) {
            System.out.println(
                "XmlaBaseTestCase.doTests: soap response=" + new String(bytes));
        }

        String gotStr = new String(bytes);
        gotStr = ignoreLastUpdateDate(gotStr);
        gotStr = maskVersion(gotStr);
        gotStr = upgradeActual(gotStr);
        if (expectedStr != null) {
            // Let DiffRepository do the comparison. It will output
            // a textual difference, and will update the logfile,
            // XmlaBasicTest.log.xml. If you agree with the change,
            // copy this file to XmlaBasicTest.ref.xml.
            getDiffRepos().assertEquals(
                "response",
                "${response}",
                gotStr);
        }
    }

    enum Action {
        CREATE,
        QUERY,
        CLEAR
    }

    /**
     * Creates, retrieves or clears the session id for this test.
     *
     * @param action Action to perform
     * @return Session id for create, query; null for clear
     */
    protected abstract String getSessionId(Action action);

    protected static String getSessionId(String name, Action action) {
        switch (action) {
        case CLEAR:
            sessionIdMap.put(name, null);
            return null;

        case QUERY:
            return sessionIdMap.get(name);

        case CREATE:
            String sessionId = sessionIdMap.get(name);
            if (sessionId == null) {
                int id = sessionIdCounter++;
                StringBuilder buf = new StringBuilder();
                buf.append(name);
                buf.append("-");
                buf.append(id);
                buf.append("-foo");
                sessionId = buf.toString();
                sessionIdMap.put(name, sessionId);
            }
            return sessionId;

        default:
            throw new UnsupportedOperationException();
        }
    }

    protected static abstract class XmlaRequestCallbackImpl
        implements XmlaRequestCallback
    {
        private static final String MY_SESSION_ID = "my_session_id";
        private final String name;

        protected XmlaRequestCallbackImpl(String name) {
            this.name = name;
        }

        @Override
		public void init(ServletConfig servletConfig) throws ServletException {
        }

        @Override
		public boolean processHttpHeader(
            HttpServletRequest request,
            HttpServletResponse response,
            Map<String, Object> context)
            throws Exception
        {
            String expect = request.getHeader(XmlaRequestCallback.EXPECT);
            if ((expect != null)
                && expect.equalsIgnoreCase(
                    XmlaRequestCallback.EXPECT_100_CONTINUE))
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
            Map<String, Object> context)
            throws Exception
        {
        }

        private void setSessionId(Map<String, Object> context) {
            context.put(
                MY_SESSION_ID,
                getSessionId(name, Action.CREATE));
        }

        @Override
		public String generateSessionId(Map<String, Object> context) {
            setSessionId(context);
            return (String) context.get(MY_SESSION_ID);
        }

        @Override
		public void postAction(
            HttpServletRequest request,
            HttpServletResponse response,
            byte[][] responseSoapParts,
            Map<String, Object> context)
            throws Exception
        {
        }
    }

    /**
     * Masks Mondrian's version number from a string.
     * Note that this method does a mostly blind replacement
     * of the version string and may replace strings that
     * just happen to have the same sequence.
     *
     * @param str String
     * @return String with each occurrence of mondrian's version number
     *    (e.g. "2.3.0.0") replaced with "${mondrianVersion}"
     */
    public static String maskVersion(String str) {

        String versionString = "";
        // regex characters that wouldn't be expected before or after the
        // version string.  This avoids a false match when the version
        // string digits appear in other contexts (e.g. $3.56)
        versionString = "10.50.1600.1";
        String charsOutOfContext = "([^,\\$\\d])";
        String matchString = charsOutOfContext + Pattern.quote(versionString)
            + charsOutOfContext;
        return str.replaceAll(matchString, "$1\\${mondrianVersion}$2");
    }
}
