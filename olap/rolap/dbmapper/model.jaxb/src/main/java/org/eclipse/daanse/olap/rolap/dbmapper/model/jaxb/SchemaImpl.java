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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDocumentation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * A schema is a collection of cubes and virtual cubes. It can also contain
 * shared dimensions (for use by those cubes), named sets, roles, and
 * declarations of user-defined functions.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "parameters", "dimensions", "cubes", "virtualCubes", "namedSets", "roles",
    "userDefinedFunctions", "documentation"})
@XmlRootElement(name = "Schema")
public class SchemaImpl extends AbstractMainElement implements MappingSchema {


    @XmlElement(name = "Parameter", type = ParameterImpl.class)
    protected List<MappingParameter> parameters;
    @XmlElement(name = "Dimension", type = PrivateDimensionImpl.class)
    protected List<MappingPrivateDimension> dimensions;
    @XmlElement(name = "Cube", required = true, type = CubeImpl.class)
    protected List<MappingCube> cubes;
    @XmlElement(name = "VirtualCube", type = VirtualCubeImpl.class)
    protected List<MappingVirtualCube> virtualCubes;
    @XmlElement(name = "NamedSet", type = NamedSetImpl.class)
    protected List<MappingNamedSet> namedSets;
    @XmlElement(name = "Role", type = RoleImpl.class)
    protected List<MappingRole> roles;
    @XmlElement(name = "Documentation", type = DocumentationImpl.class)
    protected MappingDocumentation documentation;


    /**
     * @deprecated
     */
    @Deprecated(since = "new version")
    @XmlElement(name = "UserDefinedFunction", type = UserDefinedFunctionImpl.class)
    protected List<MappingUserDefinedFunction> userDefinedFunctions;
    @XmlAttribute(name = "measuresCaption")
    protected String measuresCaption;
    @XmlAttribute(name = "defaultRole")
    protected String defaultRole;

    @Override
    public List<MappingParameter> parameters() {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        return this.parameters;
    }

    @Override
    public List<MappingPrivateDimension> dimensions() {
        if (dimensions == null) {
            dimensions = new ArrayList<>();
        }
        return this.dimensions;
    }

    @Override
    public List<MappingCube> cubes() {
        if (cubes == null) {
            cubes = new ArrayList<>();
        }
        return this.cubes;
    }

    @Override
    public List<MappingVirtualCube> virtualCubes() {
        if (virtualCubes == null) {
            virtualCubes = new ArrayList<>();
        }
        return this.virtualCubes;
    }

    @Override
    public List<MappingNamedSet> namedSets() {
        if (namedSets == null) {
            namedSets = new ArrayList<>();
        }
        return this.namedSets;
    }

    @Override
    public List<MappingRole> roles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return this.roles;
    }

    /**
     * @return @deprecated
     */
    @Deprecated(since="new version")
    @Override
    public List<MappingUserDefinedFunction> userDefinedFunctions() {
        if (userDefinedFunctions == null) {
            userDefinedFunctions = new ArrayList<>();
        }
        return this.userDefinedFunctions;
    }

    @Override
    public String measuresCaption() {
        return measuresCaption;
    }

    public void setMeasuresCaption(String value) {
        this.measuresCaption = value;
    }

    @Override
    public String defaultRole() {
        return defaultRole;
    }


    public void setNamedSets(List<MappingNamedSet> namedSets) {
        this.namedSets = namedSets;

    }

    public void setParameters(List<MappingParameter> parameters) {
        this.parameters = parameters;

    }

    public void setRoles(List<MappingRole> roles) {
        this.roles = roles;

    }


    /**
     * @return @deprecated
     */
    @Deprecated(since="new version")
    public void setUserDefinedFunctions(List<MappingUserDefinedFunction> userDefinedFunctions) {
        this.userDefinedFunctions = userDefinedFunctions;
    }

    public void setVirtualCubes(List<MappingVirtualCube> virtualCubs) {
        this.virtualCubes = virtualCubs;
    }

    public void setDimensions(List<MappingPrivateDimension> dimensions) {
        this.dimensions = dimensions;
    }

    public void setCubes(List<MappingCube> cubes) {
        this.cubes = cubes;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Override
    public Optional<MappingDocumentation> documentation() {
        return Optional.ofNullable(documentation);
    }

    public void setDocumentation(Optional<MappingDocumentation> documentation) {
        this.documentation = documentation.orElse(null);
    }

}
