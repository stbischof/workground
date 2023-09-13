/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * History:
 *  This files came from the mondrian project. Some of the Flies
 *  (mostly the Tests) did not have License Header.
 *  But the Project is EPL Header. 2002-2022 Hitachi Vantara.
 *
 * Contributors:
 *   Hitachi Vantara.
 *   SmartCity Jena - initial  Java 8, Junit5
 */
package org.opencube.junit5;

import java.io.InputStream;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;

import org.eclipse.daanse.olap.api.Context;
import org.glassfish.jaxb.runtime.v2.JAXBContextFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.opencube.junit5.context.BaseTestContext;
import org.opencube.junit5.context.TestingContext;
import org.opencube.junit5.dataloader.DataLoader;
import org.opencube.junit5.dbprovider.DatabaseProvider;
import org.opencube.junit5.xmltests.ResourceTestCase;
import org.opencube.junit5.xmltests.XmlResourceRoot;
import org.opencube.junit5.xmltests.XmlResourceTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aQute.bnd.annotation.Cardinality;
import aQute.bnd.annotation.spi.ServiceConsumer;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import mondrian.olap.Util.PropertyList;
import mondrian.rolap.RolapConnectionProperties;

@ServiceConsumer(cardinality = Cardinality.MULTIPLE, value = DatabaseProvider.class)
public class ContextArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<ContextSource> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextArgumentsProvider.class);
	private ContextSource contextSource;

	private static Map<Class<? extends DatabaseProvider>, Map<Class<? extends DataLoader>, Entry<PropertyList, Context>>> store = new HashMap<>();
	public static boolean dockerWasChanged = true;

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

		// TODO: parallel
		List<TestingContext> contexts = prepareContexts(extensionContext);
		List<XmlResourceTestCase> xmlTestCases = readTestcases(extensionContext);

		List<Arguments> argumentss = new ArrayList<>();

		if (contexts == null || contexts.isEmpty()) {

			if (xmlTestCases != null && !xmlTestCases.isEmpty()) {

				for (XmlResourceTestCase xmlTestCase : xmlTestCases) {
					argumentss.add(Arguments.of(xmlTestCase));
				}
			}
		} else {
			for (TestingContext context : contexts) {

				if (xmlTestCases == null || xmlTestCases.isEmpty()) {
					argumentss.add(Arguments.of(context));

				} else {
					for (XmlResourceTestCase xmlTestCase : xmlTestCases) {
						argumentss.add(Arguments.of(context, xmlTestCase));
					}

				}

			}
		}
		return argumentss.stream();

	}

	private List<XmlResourceTestCase> readTestcases(ExtensionContext extensionContext) {

		Optional<AnnotatedElement> oElement = extensionContext.getElement();
		if (oElement.isPresent()) {
			if (oElement.get() instanceof Method) {
				Method method = (Method) oElement.get();
				for (Parameter param : method.getParameters()) {
					if (ResourceTestCase.class.equals(param.getType())) {
						Optional<Class<?>> oTestclass = extensionContext.getTestClass();
						if (oTestclass.isPresent()) {
							Class<?> testclass = oTestclass.get();

							InputStream is = testclass.getResourceAsStream(testclass.getSimpleName() + ".ref.xml");
							JAXBContext jaxbContext = null;
							try {

//								Map.of(JAXBContext.JAXB_CONTEXT_FACTORY, JaxBConFa));

								jaxbContext = new JAXBContextFactory()
										.createContext(new Class[] { XmlResourceRoot.class }, Map.<String, Object>of());

								Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

//								try {
//									System.out.println(new String(is.readAllBytes()));
//								} catch (IOException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
								XmlResourceRoot o = (XmlResourceRoot) jaxbUnmarshaller.unmarshal(is);

								return o.testCase;
							} catch (JAXBException e) {
							    LOGGER.error("readTestcases error", e);
							}

						}
					}
				}
			}
		}

		return null;
	}

	private List<TestingContext> prepareContexts(ExtensionContext extensionContext) {
		Stream<DatabaseProvider> providers;
		Thread.currentThread().setContextClassLoader(getClass().getClassLoader()); //for withSchemaProcessor(context, MyFoodmart.class);
		Class<? extends DatabaseProvider>[] dbHandlerClasses = contextSource.database();
		if (dbHandlerClasses == null || dbHandlerClasses.length == 0) {
			providers = ServiceLoader.load(DatabaseProvider.class, this.getClass().getClassLoader()).stream()
					.map(Provider::get);
		} else {
			providers = Stream.of(dbHandlerClasses).map(c -> {
				try {
					return c.getConstructor().newInstance();
				} catch (Exception e) {
					LOGGER.error("prepareContexts error", e);
					return null;
				}
			});
		}
		List<TestingContext> args = providers.parallel().map(dbp -> {

			Entry<PropertyList, Context> dataSource = null;
			Class<? extends DatabaseProvider> clazzProvider = dbp.getClass();

			if (!store.containsKey(clazzProvider)) {
				store.put(clazzProvider, new HashMap<>());
			} else {

			}

			List<TestingContext> aaa = new ArrayList<>();

			Optional<AnnotatedElement> oElement = extensionContext.getElement();
			if (oElement.isPresent()) {
				if (oElement.get() instanceof Method) {
					Method method = (Method) oElement.get();
					for (Parameter param : method.getParameters()) {
						if (TestingContext.class.isAssignableFrom(param.getType())) {
							ContextSource contextSource=method.getAnnotation(ContextSource.class);

							try {

								Class<? extends DataLoader> dataLoaderClass = contextSource.dataloader();

								Map<Class<? extends DataLoader>, Entry<PropertyList, Context>> storedLoaders = store
										.get(clazzProvider);
								if (storedLoaders.containsKey(dataLoaderClass) && !dockerWasChanged) {
									dataSource = storedLoaders.get(dataLoaderClass);
									dataSource.getKey().put(RolapConnectionProperties.Jdbc.name(), dbp.getJdbcUrl());
								} else {
									dataSource = dbp.activate();
									DataLoader dataLoader = dataLoaderClass.getConstructor().newInstance();
									dataLoader.loadData(dataSource.getValue());
									storedLoaders.clear();
									storedLoaders.put(dataLoaderClass, dataSource);
									dockerWasChanged = false;
								}

							} catch (Exception e) {

                                LOGGER.error("prepareContexts error", e);
								throw new RuntimeException(e);
							}

							BaseTestContext context=new BaseTestContext();
							context.init(dataSource);

							Stream.of(contextSource.propertyUpdater()).map(c -> {
								try {
									return c.getConstructor().newInstance();
								} catch (Exception e) {
                                    LOGGER.error("prepareContexts error", e);
									throw new RuntimeException(e);
								}
							}).forEachOrdered(u->context.update(u));

							aaa.add(context);
						}
					}
				}
			}
			return aaa;
		}).flatMap(Collection::stream).toList();
		return args;
	}

	@Override
	public void accept(ContextSource annotation) {
		this.contextSource = annotation;

	}

	class NamedArguments implements Arguments {

		public NamedArguments(TestingContext context, ResourceTestCase res) {
			super();
			this.context = context;
			this.res = res;
		}

		TestingContext context;
		ResourceTestCase res;

		@Override
		public Object[] get() {

			if (context == null) {
				if (res != null) {
					return new Object[] { context, res };
				}
			}

			if (res == null) {
				if (context != null) {
					return new Object[] { context };
				}
			}
			return new Object[] { context, res };
		}

	}

}
