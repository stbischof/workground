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
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDocumentation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;

public record SchemaR(String name,
                      String description,
                      List<MappingAnnotation> annotations,
                      String measuresCaption,
                      String defaultRole,
                      List<MappingParameter> parameters,
                      List<MappingPrivateDimension> dimensions,
                      List<MappingCube> cubes,
                      List<MappingVirtualCube> virtualCubes,
                      List<MappingNamedSet> namedSets,
                      List<MappingRole> roles,
                      List<MappingUserDefinedFunction> userDefinedFunctions,
                      Optional<MappingDocumentation> documentation)
        implements MappingSchema {



	public  SchemaR(String name,
            String description,
            List<MappingAnnotation> annotations,
            String measuresCaption,
            String defaultRole,
            List<MappingParameter> parameters,
            List<MappingPrivateDimension> dimensions,
            List<MappingCube> cubes,
            List<MappingVirtualCube> virtualCubes,
            List<MappingNamedSet> namedSets,
            List<MappingRole> roles,
            List<MappingUserDefinedFunction> userDefinedFunctions,
            Optional<MappingDocumentation> documentation)
{
	this.name = name;
	this.description = description;
	this.annotations = annotations == null ? List.of() : annotations;
	this.measuresCaption = measuresCaption;
	this.defaultRole = defaultRole ;
	this.parameters = parameters == null ? List.of() : parameters;
	this.dimensions = dimensions == null ? List.of() : dimensions;
	this.cubes = cubes == null ? List.of() : cubes;
	this.virtualCubes = virtualCubes == null ? List.of() : virtualCubes;
	this.namedSets = namedSets == null ? List.of() : namedSets;
	this.roles = roles == null ? List.of() : roles;
	this.userDefinedFunctions = userDefinedFunctions == null ? List.of() : userDefinedFunctions;
	this.documentation = documentation;
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

    public String getMeasuresCaption() {
        return measuresCaption;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public List<MappingParameter> getParameters() {
        return parameters;
    }

    public List<MappingPrivateDimension> getDimensions() {
        return dimensions;
    }

    public List<MappingCube> getCubes() {
        return cubes;
    }

    public List<MappingVirtualCube> getVirtualCubes() {
        return virtualCubes;
    }

    public List<MappingNamedSet> getNamedSets() {
        return namedSets;
    }

    public List<MappingRole> getRoles() {
        return roles;
    }

    public List<MappingUserDefinedFunction> getUserDefinedFunctions() {
        return userDefinedFunctions;
    }

    public Optional<MappingDocumentation> getDocumentation() {
        return documentation;
    }
}
