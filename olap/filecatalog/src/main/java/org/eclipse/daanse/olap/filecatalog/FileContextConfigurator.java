package org.eclipse.daanse.olap.filecatalog;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.daanse.common.io.fs.watcher.api.FileSystemWatcherListener;
import org.eclipse.daanse.common.io.fs.watcher.api.propertytypes.FileSystemWatcherListenerProperties;
import org.eclipse.daanse.olap.core.BasicContext;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Designate(factory = true, ocd = FileContextConfigurator.Config.class)
@Component()
@RequireConfigurationAdmin
@RequireServiceComponentRuntime
@FileSystemWatcherListenerProperties(pattern = ".*.xml")
public class FileContextConfigurator implements FileSystemWatcherListener {

	@ObjectClassDefinition
	@interface Config {
		@AttributeDefinition
		String io_fs_watcher_path();
		@AttributeDefinition
		String name();

	}

	private static final String TARGET_EXT = ".target";

	public static final String PID_CONTEXT = "org.eclipse.daanse.olap.core.BasicContext";

	public static final String PID_SATATISTICS = "org.eclipse.daanse.db.statistics.metadata.JdbcStatisticsProvider";

    public static final String PID_PARSER = "org.eclipse.daanse.mdx.parser.ccc.MdxParserProviderImpl";

	public static final String PID_EXP_COMP_FAC = "org.eclipse.daanse.olap.calc.base.compiler.BaseExpressionCompilerFactory";


	static final String PID_XML_SCHEMA = "org.eclipse.daanse.olap.rolap.dbmapper.provider.xml.XmlDbMappingSchemaProvider";

	private Map<Path, Configuration> schemaConfigs = new ConcurrentHashMap<>();

	@Reference
	ConfigurationAdmin configurationAdmin;

	private Configuration cContext;

	private Config config;

	private Path basePath;

	private static final String MATCHER_KEY = "file.context.matcher";
	private String matcher_value = null;
	private String contextSpecificFilter;

	@Activate
	public void activate(Config config) throws IOException {
		this.config = config;
		matcher_value = config.io_fs_watcher_path().replace("\\","-.-");
		contextSpecificFilter = "(" + MATCHER_KEY + "=" + matcher_value + ")";
		initContext();
	}

	private void initContext() throws IOException {

		cContext = configurationAdmin.getFactoryConfiguration(PID_CONTEXT, UUID.randomUUID().toString(), "?");

		Dictionary<String, Object> props = new Hashtable<>();
		props.put(BasicContext.REF_NAME_DATA_SOURCE + TARGET_EXT, contextSpecificFilter);
		props.put(BasicContext.REF_NAME_STATISTICS_PROVIDER + TARGET_EXT, "(component.name=" + PID_SATATISTICS + ")");
		props.put(BasicContext.REF_NAME_EXPRESSION_COMPILER_FACTORY + TARGET_EXT,
				"(component.name=" + PID_EXP_COMP_FAC + ")");
		props.put(BasicContext.REF_NAME_DB_MAPPING_SCHEMA_PROVIDER + TARGET_EXT, contextSpecificFilter);
        props.put(BasicContext.REF_NAME_MDX_PARSER_PROVIDER + TARGET_EXT, "(component.name=" + PID_PARSER + ")");
		// props.put(BasicContext.REF_NAME_QUERY_PROVIDER+ TARGET_EXT, "(qp=1)");

		String catalog_path = config.io_fs_watcher_path();
		String theDescription = "theDescription for " + config.io_fs_watcher_path();

		String name=config.name();
		if(name==null || name.isEmpty()) {
			name="not_set"+UUID.randomUUID().toString();
		}
		props.put("name", name);
		props.put("description", theDescription);
		props.put("catalog.path", catalog_path);
        //props.put("useAggregates", true);
		cContext.update(props);
	}

	@Deactivate
	public void deactivate() throws IOException {

		schemaConfigs.forEach((pathAsKey, configurationAsValue) -> {
			try {
				schemaConfigs.remove(pathAsKey);
				configurationAsValue.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		cContext.delete();
	}

	@Override
	public void handleBasePath(Path basePath) {
		this.basePath = basePath;

	}

	@Override
	public void handleInitialPaths(List<Path> paths) {
		paths.forEach(this::addPath);
	}

	@Override
	public void handlePathEvent(Path path, Kind<Path> kind) {
		if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
			removePath(path);
			addPath(path);
		} else if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
			addPath(path);
		} else if (StandardWatchEventKinds.ENTRY_DELETE.equals(kind)) {
			removePath(path);
		}

	}

	private void addPath(Path path) {
		if (Files.isDirectory(path)) {
			return;
		}
		System.out.println(path.getFileName());
		if (!path.toString().endsWith(".xml")) {
			return;
		}

		addSchema(path);
	}

	private void removePath(Path path) {
		if (Files.isDirectory(path)) {
			return;
		}
		if (!path.toString().endsWith(".xml")) {
			return;
		}

		removeSchema(path);
	}

	private void addSchema(Path path) {
		String uuid = UUID.randomUUID().toString();

		try {
			Configuration c = configurationAdmin.getFactoryConfiguration(PID_XML_SCHEMA, uuid, "?");
			URL url = path.toUri().toURL();
			String sUrl = url.toString();
			Dictionary<String, Object> ht = new Hashtable<>();
			ht.put("url", sUrl);
			ht.put("sample.name", sUrl);
			ht.put("sample.type", "xml");
			ht.put(MATCHER_KEY, matcher_value);
			c.update(ht);

			schemaConfigs.put(path, c);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void removeSchema(Path path) {
		try {
			Configuration c = schemaConfigs.remove(path);
			if (c != null) {
				c.delete();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
