package org.eclipse.daanse.xmla.ws.servlet;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.junit5.cm.ConfigurationExtension;

import com.sun.xml.ws.api.server.InstanceResolver;
import com.sun.xml.ws.api.server.WSEndpoint;
import com.sun.xml.ws.binding.BindingImpl;

import jakarta.xml.ws.handler.Handler;

@ExtendWith(ConfigurationExtension.class)
public class MinimalTest {

    private static final String PID = "org.eclipse.daanse.xmla.ws.servlet.WebServiceServletAdapter";
    @InjectBundleContext
    BundleContext bc;

    @Test
    @WithFactoryConfiguration(factoryPid = PID, name = "1", properties = { @Property(key = "name", value = "c1"),
            @Property(key = "path", value = "/calc1"), @Property(key = "endpoint.target", value = "(marker=1)") })
    @WithFactoryConfiguration(factoryPid = PID, name = "2", properties = { @Property(key = "name", value = "c2"),
            @Property(key = "path", value = "/calc2"), @Property(key = "endpoint.target", value = "(marker=-1)") })
    void testName() throws Exception {

        WSEndpoint<WSCalc> endpoint1 = createwsEndpint(1);

        bc.registerService(WSEndpoint.class, endpoint1, Dictionaries.dictionaryOf("marker", "1"));
        WSEndpoint<WSCalc> endpoint2 = createwsEndpint(-1);

        bc.registerService(WSEndpoint.class, endpoint2, Dictionaries.dictionaryOf("marker", "-1"));

        Thread.sleep(1000000);
    }

    private WSEndpoint<WSCalc> createwsEndpint(int i) {
        WSCalc calc = new WSCalc(i);
        com.sun.xml.ws.api.server.Invoker invoker = InstanceResolver.createSingleton(calc)
                .createInvoker();

        WSEndpoint<WSCalc> endpoint = WSEndpoint.create(WSCalc.class, false, invoker, null, null, null,
                BindingImpl.getDefaultBinding(), null, null, null, false);
        List<Handler> handlers = endpoint.getBinding()
                .getHandlerChain();

        endpoint.getBinding()
                .setHandlerChain(handlers);

        return endpoint;
    }

}
