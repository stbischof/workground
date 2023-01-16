package org.eclipse.daanse.ws.server;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.osgi.framework.Bundle;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.EndpointContext;

public class BundleEndpointContext extends EndpointContext {

	private Set<Endpoint> endpoints = ConcurrentHashMap.newKeySet();
	private Set<Endpoint> endpointsView = Collections.unmodifiableSet(endpoints);
	private Bundle bundle;

	public BundleEndpointContext(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public Set<Endpoint> getEndpoints() {
		return endpointsView;
	}

	@Override
	public String toString() {
		Set<Endpoint> ep = getEndpoints();
		return "Endpoints for bundle " + bundle.getSymbolicName() + " " + bundle.getVersion() + ": "
				+ ep.stream().map(Endpoint::getImplementor).map(String::valueOf).collect(
						Collectors.joining(System.lineSeparator(), System.lineSeparator(), "Total: " + ep.size()));
	}

	public void addEndpoint(Endpoint endpoint) {
		endpoints.add(endpoint);
	}

	public void removeEndpoint(Endpoint endpoint) {
		endpoints.remove(endpoint);
	}
	
}
