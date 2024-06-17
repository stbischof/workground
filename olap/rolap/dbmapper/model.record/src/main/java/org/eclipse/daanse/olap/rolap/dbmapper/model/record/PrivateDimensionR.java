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

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.DimensionTypeEnum;

public record PrivateDimensionR(String name,
		String description,
		List<MappingAnnotation> annotations,
		String caption,
		Boolean visible,
        DimensionTypeEnum type,
        String foreignKey,
        List<MappingHierarchy> hierarchies,
        String usagePrefix
        )
        implements MappingPrivateDimension {



	public  PrivateDimensionR(String name,
			String description,
			List<MappingAnnotation> annotations,
			String caption,
			Boolean visible,
	        DimensionTypeEnum type,
	        String foreignKey,
	        List<MappingHierarchy> hierarchies,
	        String usagePrefix
	        ) {
			this.name = name;
			this.description = description;
			this.annotations = annotations == null ? List.of() : annotations;
			this.caption = caption;
			this.visible = visible == null ? Boolean.TRUE : visible;
			this.type = type;
			this.foreignKey = foreignKey;
			this.hierarchies = hierarchies == null ? List.of() : hierarchies;
			this.usagePrefix = usagePrefix;
	}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<MappingAnnotation> getAnnotations() {
        return annotations;
    }

    public String getCaption() {
        return caption;
    }

    public Boolean getVisible() {
        return visible;
    }

    public DimensionTypeEnum getType() {
        return type;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public List<MappingHierarchy> getHierarchies() {
        return hierarchies;
    }

    public String getUsagePrefix() {
        return usagePrefix;
    }
}
