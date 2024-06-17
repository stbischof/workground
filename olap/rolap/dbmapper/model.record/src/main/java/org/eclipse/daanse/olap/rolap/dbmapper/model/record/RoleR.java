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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUnion;

public record RoleR(
		String name,
		String description,
		List<MappingAnnotation> annotations,
		List<MappingSchemaGrant> schemaGrants,
        MappingUnion union
                    )
        implements MappingRole {


	public  RoleR(
			String name,
			String description,
			List<MappingAnnotation> annotations,
			List<MappingSchemaGrant> schemaGrants,
	        MappingUnion union
	                    )
	          {
				this.name = name;
				this.description = description;
				this.annotations = annotations == null ? List.of() : annotations;
				this.schemaGrants = schemaGrants == null ? List.of() : schemaGrants;
				this.union = union;
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

    public List<MappingSchemaGrant> getSchemaGrants() {
        return schemaGrants;
    }

    public MappingUnion getUnion() {
        return union;
    }
}
