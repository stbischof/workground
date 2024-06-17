/*
 * Copyright (c) 0 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License .0
 * which is available at https://www.eclipse.org/legal/epl-.0/
 *
 * SPDX-License-Identifier: EPL-.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevelProperty;

public record AggLevelR(String column,
                        String name,
                        String ordinalColumn,
                        String nameColumn,
                        String captionColumn,
                        Boolean collapsed,
                        List<MappingAggLevelProperty> properties)
        implements MappingAggLevel {


	public  AggLevelR(String column,
            String name,
            String ordinalColumn,
            String nameColumn,
            String captionColumn,
            Boolean collapsed,
            List<MappingAggLevelProperty> properties)
  {
		this.column = column;
		this.name = name;
		this.ordinalColumn = ordinalColumn;
		this.nameColumn = nameColumn;
		this.captionColumn = captionColumn;
		this.collapsed = collapsed == null ? Boolean.TRUE : collapsed;
		this.properties = properties == null ? List.of() : properties;
	}

    public String getColumn() {
        return column;
    }

    public String getName() {
        return name;
    }

    public String getOrdinalColumn() {
        return ordinalColumn;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public String getCaptionColumn() {
        return captionColumn;
    }

    public Boolean getCollapsed() {
        return collapsed;
    }

    public List<MappingAggLevelProperty> getProperties() {
        return properties;
    }
}
