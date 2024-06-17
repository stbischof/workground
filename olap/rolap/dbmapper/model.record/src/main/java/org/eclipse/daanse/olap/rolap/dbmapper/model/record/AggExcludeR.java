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
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggExclude;

public record AggExcludeR(String pattern,
                          String name,
                          Boolean ignorecase)
        implements MappingAggExclude {

	public AggExcludeR(String pattern, String name, Boolean ignorecase) {
		this.pattern = pattern;
		this.name = name;
		this.ignorecase = ignorecase == null ? Boolean.TRUE : ignorecase;
	}

	public AggExcludeR(String pattern, String name) {
        this(pattern, name, Boolean.TRUE );
    }

    public String getPattern() {
        return pattern;
    }

    public String getName() {
        return name;
    }

    @Override
    public Boolean ignorecase() {
        return ignorecase;
    }
}
