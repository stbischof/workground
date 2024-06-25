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

import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Cube", propOrder = { "fact", "dimensionUsageOrDimensions", "measures", "calculatedMembers",
		"namedSets", "drillThroughActions", "writebackTable", "kpis", "actions" })
public class CubeImpl extends AbstractMainElement implements MappingCube {

	@XmlElements({ @XmlElement(name = "DimensionUsage", type = DimensionUsageImpl.class),
			@XmlElement(name = "Dimension", type = PrivateDimensionImpl.class) })
	protected List<MappingCubeDimension> dimensionUsageOrDimensions;
	@XmlElement(name = "Measure", required = true, type = MeasureImpl.class)
	protected List<MappingMeasure> measures;
	@XmlElement(name = "CalculatedMember", type = CalculatedMemberImpl.class)
	protected List<MappingCalculatedMember> calculatedMembers;
	@XmlElement(name = "NamedSet", type = NamedSetImpl.class)
	protected List<MappingNamedSet> namedSets;
	@XmlElement(name = "DrillThroughAction", type = DrillThroughActionImpl.class)
	protected List<MappingDrillThroughAction> drillThroughActions;
	@XmlElement(name = "WritebackTable", type = WritebackTableImpl.class)
	protected MappingWritebackTable writebackTable;
    @XmlElement(name = "Kpi", type = KpiImpl.class)
    @XmlElementWrapper(name = "Kpis")
    protected List<MappingKpi> kpis;
	@XmlAttribute(name = "defaultMeasure")
	protected String defaultMeasure;
	@XmlAttribute(name = "cache")
	protected Boolean cache;
	@XmlAttribute(name = "enabled")
	protected Boolean enabled;
	@XmlAttribute(name = "visible")
	protected Boolean visible = true;
	@XmlElements({ @XmlElement(name = "InlineTable", type = InlineTableImpl.class),
			@XmlElement(name = "Table", type = TableImpl.class), @XmlElement(name = "View", type = ViewImpl.class) })
	protected MappingRelationQuery fact;
	@XmlElement(name = "Action", type = ActionImpl.class)
	protected List<MappingAction> actions;

	@Override
	public List<MappingCubeDimension> dimensionUsageOrDimensions() {
		if (dimensionUsageOrDimensions == null) {
			dimensionUsageOrDimensions = new ArrayList<>();
		}
		return this.dimensionUsageOrDimensions;
	}

	@Override
	public List<MappingMeasure> measures() {
		if (measures == null) {
			measures = new ArrayList<>();
		}
		return this.measures;
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
	public List<MappingDrillThroughAction> drillThroughActions() {
		if (drillThroughActions == null) {
			drillThroughActions = new ArrayList<>();
		}
		return this.drillThroughActions;
	}

	@Override
	public String defaultMeasure() {
		return defaultMeasure;
	}

	public void setDefaultMeasure(String value) {
		this.defaultMeasure = value;
	}

	@Override
	public Boolean cache() {
		if (cache == null) {
			return true;
		} else {
			return cache;
		}
	}

	public void setCache(Boolean value) {
		this.cache = value;
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
	public Optional<MappingWritebackTable> writebackTable() {
			return Optional.ofNullable(writebackTable);
	}

	@Override
	public Boolean visible() {
		return visible;
	}

	@Override
	public MappingRelationQuery fact() {
		return fact;
	}

	public void setFact(MappingRelationQuery fact) {
		this.fact = fact;
	}

	@Override
	public List<MappingAction> actions() {
		if (actions == null) {
			actions = new ArrayList<>();
		}
		return actions;
	}

    @Override
    public List<MappingKpi> kpis() {
        if (kpis == null) {
            kpis = new ArrayList<>();
        }
        return kpis;
    }

	public void setDimensionUsageOrDimensions(List<MappingCubeDimension> dimensionUsageOrDimensions) {
		this.dimensionUsageOrDimensions = dimensionUsageOrDimensions;
	}

	public void setMeasures(List<MappingMeasure> measures) {
		this.measures = measures;
	}

	public void setCalculatedMembers(List<MappingCalculatedMember> calculatedMembers) {
		this.calculatedMembers = calculatedMembers;
	}

	public void setNamedSets(List<MappingNamedSet> namedSets) {
		this.namedSets = namedSets;
	}

	public void setDrillThroughActions(List<MappingDrillThroughAction> drillThroughActions) {
		this.drillThroughActions = drillThroughActions;
	}

	public void setWritebackTable(MappingWritebackTable writebackTable) {
		this.writebackTable = writebackTable;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setActions(List<MappingAction> actions) {
		this.actions = actions;
	}

    public void setKpis(List<MappingKpi> kpis) {
        this.kpis = kpis;
    }

}
