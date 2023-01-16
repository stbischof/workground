package org.eclipse.daanse.ws.server;

import java.util.Comparator;
import java.util.Map.Entry;

import org.osgi.framework.Filter;

import jakarta.xml.ws.handler.Handler;

final record HandlerInfo(Filter filter, int priority) {
	
	static final Comparator<Entry<Handler<?>, HandlerInfo>> SORT_BY_PRIORITY = Comparator
			.comparing(Entry::getValue, Comparator.comparingInt(HandlerInfo::priority).reversed());

}