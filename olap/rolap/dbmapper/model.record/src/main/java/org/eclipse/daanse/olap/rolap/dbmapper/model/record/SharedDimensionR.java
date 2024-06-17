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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSharedDimension;

public record SharedDimensionR(String name,
		String description,
		List<MappingAnnotation> annotations,
        String caption,
        String type,
        List<MappingHierarchy> hierarchies,
        String foreignKey)
        implements MappingSharedDimension {

	public  SharedDimensionR(String name,
			String description,
			List<MappingAnnotation> annotations,
	        String caption,
	        String type,
	        List<MappingHierarchy> hierarchies,
	        String foreignKey)
	{
		this.name = name;
		this.description = description;
		this.annotations = annotations == null ? List.of() : annotations;
		this.caption = caption;
		this.type = type;
		this.hierarchies = hierarchies == null ? List.of() : hierarchies;
		this.foreignKey = foreignKey;

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

    public String getType() {
        return type;
    }

    public List<MappingHierarchy> getHierarchies() {
        return hierarchies;
    }

    public String getForeignKey() {
        return foreignKey;
    }
}
