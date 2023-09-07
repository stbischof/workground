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
package org.eclipse.daanse.function.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.eclipse.daanse.function.api.FunctionResolver;
import org.eclipse.daanse.function.api.FunctionResolverProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.converter.Converter;
import org.osgi.util.converter.Converters;

import mondrian.olap.Syntax;

@Designate(ocd = Config.class, factory = true)
@Component(service = FunctionResolverProvider.class)
public class DefaultFunctionResolverProvider implements FunctionResolverProvider {
	private static final Converter CONVERTER = Converters.standardConverter();
	private Config config;

	AtomicBoolean needsRecalculation = new AtomicBoolean();

	private final List<FunctionResolver> functionResolvers = new ArrayList<>();

	@Activate
	public DefaultFunctionResolverProvider(Map<String, Object> configuration) {
		this.config = CONVERTER.convert(configuration).to(Config.class);
	}

	@Deactivate
	public void deactivate() {
		config = null;
	}

	@Reference(service = FunctionResolver.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindFunctionResolver(FunctionResolver resolver) {

		functionResolvers.add(resolver);

	}

	public void unbindFunctionResolver(FunctionResolver resolver) {
		functionResolvers.remove(resolver);
	}

	@Override
	public boolean isReservedWord(String word) {
		return functionResolvers.stream()//
				.flatMap(functionResolver -> Stream.of(functionResolver.getReservedWords()))//
				.anyMatch(reservedWord -> reservedWord.equalsIgnoreCase(word));//
	}

	@Override
	public boolean isPropertyWord(String word) {
		return functionResolvers.stream()//
				.filter(functionResolver -> functionResolver.getSyntax().equals(Syntax.Property))//
				.anyMatch(functionResolver -> functionResolver.getName().equalsIgnoreCase(word));//
	}

	@Override
	public List<FunctionResolver> getFunctionResolvers() {
		return List.copyOf(functionResolvers);
	}

	@Override
	public List<FunctionResolver> getFunctionResolver(String name, Syntax syntax) {
		return functionResolvers.stream()//
				.filter(functionResolver -> functionResolver.getName().equalsIgnoreCase(name))//
				.filter(functionResolver -> functionResolver.getSyntax().equals(syntax))//
				.toList();
	}

}
