package org.eclipse.daanse.demo.server.play;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.daanse.common.io.fs.watcher.api.FileSystemWatcherWhiteboardConstants;
import org.eclipse.daanse.olap.core.BasicContextGroup;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;

@Component(immediate = true)
@RequireConfigurationAdmin
@RequireServiceComponentRuntime
public class DemoSetup {
	private static final String TARGET_EXT = ".target";

	public static final String PID_FILE_CAT_CONTEXT = "org.eclipse.daanse.olap.filecatalog.FileContextRepositoryConfigurator";
	public static final String PID_FILE_CAT_DS = "org.eclipse.daanse.db.jdbc.dataloader.csvtoh2.FileContextRepositoryConfigurator";

    public static final String PID_DATABASE_VER = "org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.jdbc.DatabaseVerifyer";
    public static final String PID_DESCRIPTION_VER = "org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.description.DescriptionVerifyer";
    public static final String PID_MANDATORIES_VER = "org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.mandantory.MandantoriesVerifyer";

	public static final String PID_MS_SOAP = "org.eclipse.daanse.xmla.server.jakarta.jws.MsXmlAnalysisSoap";

	public static final String PID_MS_SOAP_MSG_WS = "org.eclipse.daanse.xmla.server.jakarta.xml.ws.provider.soapmessage.XmlaWebserviceProvider";
	public static final String PID_MS_SOAP_MSG_SAAJ = "org.eclipse.daanse.xmla.server.jakarta.saaj.XmlaServlet";

	public static final String PID_XMLA_SERVICE = "org.eclipse.daanse.olap.xmla.bridge.ContextGroupXmlaService";

	public static final String PID_SATATISTICS = "org.eclipse.daanse.db.statistics.metadata.JdbcStatisticsProvider";

	public static final String PID_EXP_COMP_FAC = "org.eclipse.daanse.olap.calc.base.compiler.BaseExpressionCompilerFactory";

	public static final String PID_CONTEXT_GROUP = "org.eclipse.daanse.olap.core.BasicContextGroup";

	@Reference
	ConfigurationAdmin configurationAdmin;
	private Configuration cXmlaEndpoint;
	private Configuration cLoggingHandler;

	private Configuration contextGroupXmlaService;

	private Configuration cDs;

	private Configuration cCG;

    private Configuration dVC;

    private Configuration descVC;

    private Configuration mVC;

	private Configuration cXmlaEndpoint2;

	private Configuration cXmlaEndpoint3;

	private Configuration cCtxs;


	@Activate
	public void activate() throws IOException {

		Dictionary<String, Object> dict;
		initXmlaEndPoint();
		//
		initXmlaService();

		initContext();

        initVerifiers();
	}

	private void initContext() throws IOException {


//		String PATH_TO_OBSERVE = "./catalogs";

		String PATH_TO_OBSERVE = "./../../../../../catalogs";

		String path = Paths.get(PATH_TO_OBSERVE).toAbsolutePath().normalize().toString();

		System.out.println(path);
		System.out.println(path);
		System.out.println(path);

		Dictionary<String, Object> propsDS = new Hashtable<>();
		propsDS.put(FileSystemWatcherWhiteboardConstants.FILESYSTEM_WATCHER_PATH, path);
		cDs = configurationAdmin.getFactoryConfiguration(PID_FILE_CAT_DS, "1", "?");
		cDs.update(propsDS);



		Dictionary<String, Object> propsCtxs = new Hashtable<>();
		propsCtxs.put(FileSystemWatcherWhiteboardConstants.FILESYSTEM_WATCHER_PATH, path);
		cCtxs = configurationAdmin.getFactoryConfiguration(PID_FILE_CAT_CONTEXT, "1", "?");
		cCtxs.update(propsDS);


		Dictionary<String, Object> propsCG = new Hashtable<>();
		propsCG.put(BasicContextGroup.REF_NAME_CONTEXTS + TARGET_EXT, "(service.pid=*)");
		cCG = configurationAdmin.getFactoryConfiguration(PID_CONTEXT_GROUP, "1", "?");
		cCG.update(propsCG);

    }

    private void initVerifiers() throws IOException {
        Dictionary<String, Object> propsDVC = new Hashtable<>();
        dVC = configurationAdmin.getFactoryConfiguration(PID_DATABASE_VER, "1", "?");
        dVC.update(propsDVC);

        Dictionary<String, Object> propsDescVC = new Hashtable<>();
        descVC = configurationAdmin.getFactoryConfiguration(PID_DESCRIPTION_VER, "1", "?");
        descVC.update(propsDescVC);

        Dictionary<String, Object> propsMVC = new Hashtable<>();
        mVC = configurationAdmin.getFactoryConfiguration(PID_MANDATORIES_VER, "1", "?");
        mVC.update(propsMVC);

    }

	private void initXmlaService() throws IOException {
		Dictionary<String, Object> dict;
		contextGroupXmlaService = configurationAdmin.getFactoryConfiguration(PID_XMLA_SERVICE, "1", "?");
		dict = new Hashtable<>();
		dict.put("contextGroup" + TARGET_EXT, "(service.pid=*)");
		contextGroupXmlaService.update(dict);
	}

	private void initXmlaEndPoint() throws IOException {
		cXmlaEndpoint = configurationAdmin.getFactoryConfiguration(PID_MS_SOAP, "1", "?");

		Dictionary<String, Object> dict = new Hashtable<>();
		dict.put("xmlaService.target", "(service.pid=*)");
		dict.put("osgi.soap.endpoint.contextpath", "/xmla1");

		cXmlaEndpoint.update(dict);

		cXmlaEndpoint2 = configurationAdmin.getFactoryConfiguration(PID_MS_SOAP_MSG_WS, "2", "?");

		dict = new Hashtable<>();
		dict.put("xmlaService.target", "(service.pid=*)");
		dict.put("osgi.soap.endpoint.contextpath", "/xmla2");

		cXmlaEndpoint2.update(dict);

		cXmlaEndpoint3 = configurationAdmin.getFactoryConfiguration(PID_MS_SOAP_MSG_SAAJ, "3", "?");

		dict = new Hashtable<>();
		dict.put("xmlaService.target", "(service.pid=*)");
		dict.put("osgi.http.whiteboard.servlet.pattern", "/xmla3");

		cXmlaEndpoint3.update(dict);

		cLoggingHandler = configurationAdmin.getFactoryConfiguration("org.eclipse.daanse.ws.handler.SOAPLoggingHandler",
				"1", "?");

		dict = new Hashtable<>();
		dict.put("osgi.soap.endpoint.selector", "(service.pid=*)");
		cLoggingHandler.update(dict);

	}

	@Deactivate
	public void deactivate() throws IOException {

		if (cXmlaEndpoint != null) {
			cXmlaEndpoint.delete();
		}
		if (cLoggingHandler != null) {
			cLoggingHandler.delete();
		}
	}

}
