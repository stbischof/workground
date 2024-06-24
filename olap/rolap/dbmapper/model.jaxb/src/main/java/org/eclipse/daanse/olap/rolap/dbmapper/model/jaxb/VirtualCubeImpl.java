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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {  "cubeUsages", "virtualCubeDimensions", "virtualCubeMeasures",
		"calculatedMembers", "namedSets", "kpis"})
public class VirtualCubeImpl  extends AbstractMainElement  implements MappingVirtualCube {

	@XmlElementWrapper(name = "CubeUsages", required = true)
	@XmlElement(name = "CubeUsage", required = true, type = CubeUsageImpl.class)
	protected List<MappingCubeUsage> cubeUsages;
	@XmlElement(name = "VirtualCubeDimension", required = true, type = VirtualCubeDimensionImpl.class)
	protected List<MappingVirtualCubeDimension> virtualCubeDimensions;
	@XmlElement(name = "VirtualCubeMeasure", required = true, type = VirtualCubeMeasureImpl.class)
	protected List<MappingVirtualCubeMeasure> virtualCubeMeasures;
	@XmlElement(name = "CalculatedMember", type = CalculatedMemberImpl.class)
	protected List<MappingCalculatedMember> calculatedMembers;
	@XmlElement(name = "NamedSet", type = NamedSetImpl.class)
	protected List<MappingNamedSet> namedSets;
	@XmlAttribute(name = "enabled")
	protected Boolean enabled;

    @XmlElement(name = "Kpi", type = KpiImpl.class)
    @XmlElementWrapper(name = "Kpis")
    protected List<MappingKpi> kpis;


    @XmlAttribute(name = "defaultMeasure")
	protected String defaultMeasure;

	@XmlAttribute(name = "visible")
	protected Boolean visible = true;

	@Override
	public List<MappingCubeUsage> cubeUsages() {
		return cubeUsages;
	}

	public void setCubeUsages(List<MappingCubeUsage> value) {
		this.cubeUsages = value;
	}

	@Override
	public List<MappingVirtualCubeDimension> virtualCubeDimensions() {
		if (virtualCubeDimensions == null) {
			virtualCubeDimensions = new ArrayList<>();
		}
		return this.virtualCubeDimensions;
	}

	@Override
	public List<MappingVirtualCubeMeasure> virtualCubeMeasures() {
		if (virtualCubeMeasures == null) {
			virtualCubeMeasures = new ArrayList<>();
		}
		return this.virtualCubeMeasures;
	}

	@Override
	public List<MappingCalculatedMember> calculatedMembers() {
		if (calculatedMembers == null) {
			calculatedMembers = new ArrayList<>();
		}
		return this.calculatedMembers;
	}

	@Override
	public List<MappingNamedSet> namedSets() {
		if (namedSets == null) {
			namedSets = new ArrayList<>();
		}
		return this.namedSets;
	}

	@Override
	public Boolean enabled() {
		if (enabled == null) {
			return true;
		} else {
			return enabled;
		}
	}

	public void setEnabled(Boolean value) {
		this.enabled = value;
	}

	@Override
	public String defaultMeasure() {
		return defaultMeasure;
	}

	public void setDefaultMeasure(String value) {
		this.defaultMeasure = value;
	}

	@Override
	public Boolean visible() {
		return visible;
	}

    @Override
    public List<MappingKpi> kpis() {
        return kpis != null ? kpis : List.of();
    }

    public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setVirtualCubeDimensions(List<MappingVirtualCubeDimension> virtualCubeDimensions) {
		this.virtualCubeDimensions = virtualCubeDimensions;
	}

	public void setVirtualCubeMeasures(List<MappingVirtualCubeMeasure> virtualCubeMeasures) {
		this.virtualCubeMeasures = virtualCubeMeasures;
	}

	public void setCalculatedMembers(List<MappingCalculatedMember> calculatedMembers) {
		this.calculatedMembers = calculatedMembers;
	}

	public void setNamedSets(List<MappingNamedSet> namedSets) {
		this.namedSets = namedSets;
	}

    public void setKpis(List<MappingKpi> kpis) {
        this.kpis = kpis;
    }
}
