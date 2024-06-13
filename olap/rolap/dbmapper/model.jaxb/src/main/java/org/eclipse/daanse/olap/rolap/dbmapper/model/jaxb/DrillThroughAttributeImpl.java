
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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAttribute;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillThroughAttribute")
public class DrillThroughAttributeImpl implements MappingDrillThroughAttribute {

    @XmlAttribute(name = "dimension", required = true)
    protected String dimension;
    @XmlAttribute(name = "hierarchy")
    protected String hierarchy;
    @XmlAttribute(name = "level")
    protected String level;
    @XmlAttribute(name = "property")
    protected String property;

    @Override
    public String dimension() {
        return dimension;
    }

    public void setDimension(String value) {
        this.dimension = value;
    }

    @Override
    public String hierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String value) {
        this.hierarchy = value;
    }

    @Override
    public String level() {
        return level;
    }

    public void setLevel(String value) {
        this.level = value;
    }

    @Override
    public String property() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

}
