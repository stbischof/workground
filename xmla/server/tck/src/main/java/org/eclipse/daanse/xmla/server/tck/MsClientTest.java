/*
* Copyright (c) 2023 Contributors to the Eclipse Foundation.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   SmartCity Jena - initial
*   Stefan Bischof (bipolis.org) - initial
*/
package org.eclipse.daanse.xmla.server.tck;

import org.assertj.core.api.Assertions;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.common.enums.AccessEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesResponseRowR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(ConfigurationExtension.class)
@WithFactoryConfiguration(factoryPid = Constants.PID_MS_SOAP, name = "test-ms-config", location = "?", properties = {
        @Property(key = "xmlaService.target", value = "(" + Constants.XMLASERVICE_FILTER_KEY + "="
                + Constants.XMLASERVICE_FILTER_VALUE + ")"),
        @Property(key = "osgi.soap.endpoint.contextpath", value = Constants.WS_PATH) })
@WithFactoryConfiguration(factoryPid = "org.eclipse.daanse.ws.handler.SOAPLoggingHandler", name = "test-ms-config", location = "?", properties = {
        @Property(key = "osgi.soap.endpoint.selector", value = "(service.pid=*)") })
@RequireServiceComponentRuntime
class MsClientTest {
    private Logger logger = LoggerFactory.getLogger(MsClientTest.class);

    @InjectBundleContext
    BundleContext bc;

    @BeforeEach
    void beforaEach() throws InterruptedException {
        XmlaService xmlaService = mock(XmlaService.class);
        DiscoverService discoverService = mock(DiscoverService.class);

        when(xmlaService.discover()).thenReturn(discoverService);

        bc.registerService(XmlaService.class, xmlaService, FrameworkUtil
                .asDictionary(Map.of(Constants.XMLASERVICE_FILTER_KEY, Constants.XMLASERVICE_FILTER_VALUE)));
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    void testRequest1(@InjectService XmlaService xmlaService) throws IOException, InterruptedException {

        List<DiscoverPropertiesResponseRow> result = new ArrayList<>();
        result.add(new DiscoverPropertiesResponseRowR("MyPopertyName1", Optional.of("MyPopertyDescription"),
                Optional.of("string"), AccessEnum.READ, Optional.of(false), Optional.of("v")));
        result.add(new DiscoverPropertiesResponseRowR("MyPopertyName2", Optional.of("MyPopertyDescription1"),
                Optional.of("string"), AccessEnum.READ, Optional.of(false), Optional.of("v")));

        DiscoverService discoverService = xmlaService.discover();

        when(discoverService.discoverProperties(any(), any(), any())).thenReturn(result);

        // call test

        Process process = callByMsClient("schema", "DISCOVER_PROPERTIES");

        byte[] errors = process.getErrorStream()
                .readAllBytes();
        byte[] info = process.getInputStream()
                .readAllBytes();
        process.waitFor(100000, TimeUnit.SECONDS);
        String infoStr = new String(info);
        logger.debug(infoStr);
        String errorsStr = new String(errors);
        logger.error(errorsStr);

        Assertions.assertThat(errors)
                .isEmpty();
        Assertions.assertThat(process.exitValue())
                .isEqualTo(0);

//		verify client

    }

    private Process callByMsClient(String... values) throws IOException {
        String dotNet = System.getProperty("user.home") + "/.dotnet/dotnet";

        final var cmds = new ArrayList<String>();
        cmds.add(dotNet);
        cmds.add("run");
        cmds.add("Data source=http://localhost:8090/xmla;UID=Domain\\User;PWD=UserDomainPassword");

        Stream.of(values)
                .forEach(cmds::add);

        cmds.forEach(logger::debug);

        ProcessBuilder processBuilder = new ProcessBuilder(cmds);
        processBuilder.inheritIO();
        processBuilder.directory(Paths.get("../../../../../../MsAdomdClientTester/")
                .toAbsolutePath()
                .toFile());

        return processBuilder.start();
    }

}
