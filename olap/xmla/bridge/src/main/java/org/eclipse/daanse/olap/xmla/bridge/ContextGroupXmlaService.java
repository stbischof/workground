/*
* Copyright (c) 2022 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.olap.xmla.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.olap.api.ContextGroup;
import org.eclipse.daanse.olap.action.api.UrlAction;
import org.eclipse.daanse.olap.xmla.bridge.discover.DelegatingDiscoverService;
import org.eclipse.daanse.olap.xmla.bridge.execute.OlapExecuteService;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.osgi.namespace.unresolvable.UnresolvableNamespace;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.converter.Converter;
import org.osgi.util.converter.Converters;

@Component(service = XmlaService.class)
@Designate(factory = true, ocd = ContextGroupXmlaServiceConfig.class)
public class ContextGroupXmlaService implements XmlaService {

	private static final Converter CONVERTER = Converters.standardConverter();
    public static final String REF_NAME_URL_ACTIONS = "urlAction";
	private ContextGroupXmlaServiceConfig config;
    private List<UrlAction> urlActions = new ArrayList<>();

	public ContextGroupXmlaService() {

	}

	@Activate
	 void activate(Map<String, Object> props) {
		this.config = CONVERTER.convert(props).to(ContextGroupXmlaServiceConfig.class);
		 ContextListSupplyer contextsListSupplyer = new ContextsSupplyerImpl(contextGroup);
		 executeService = new OlapExecuteService(contextsListSupplyer, urlActions, config);
		 discoverService = new DelegatingDiscoverService(contextsListSupplyer, urlActions, config);

	}



	public static final String PID = "org.eclipse.daanse.olap.xmla.bridge.ContextGroupXmlaService";
	public static final String REF_NAME_CONTEXT_GROUP = "contextGroup";

	private ExecuteService executeService;
	private DiscoverService discoverService;
	private ContextGroup contextGroup;

	/*
	 * target must be configured. no auto fetch of a ContextGroup
	 */
	@Reference(cardinality = ReferenceCardinality.MANDATORY,  name = REF_NAME_CONTEXT_GROUP, target = UnresolvableNamespace.UNRESOLVABLE_FILTER)
	void bindContextGroup(ContextGroup contextGroup) {
		this.contextGroup = contextGroup;
	}

    @Reference(name = REF_NAME_URL_ACTIONS, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindUrlAction(UrlAction action) {
        urlActions.add(action);
    }

    public void unbindUrlAction(UrlAction action) {
        urlActions.remove(action);
    }

	@Override
	public DiscoverService discover() {
		return discoverService;
	}

	@Override
	public ExecuteService execute() {
		return executeService;
	}

}
