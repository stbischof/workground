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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughElement;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillThroughAction", propOrder = { "drillThroughElements" })
public class DrillThroughActionImpl extends AbstractMainElement implements MappingDrillThroughAction {

	@XmlElements({ @XmlElement(name = "DrillThroughAttribute", type = DrillThroughAttributeImpl.class),
			@XmlElement(name = "DrillThroughMeasure", type = DrillThroughMeasureImpl.class) })
	protected List<MappingDrillThroughElement> drillThroughElements;

	@XmlAttribute(name = "default")
	protected Boolean defaultt;

	@Override
	public List<MappingDrillThroughElement> drillThroughElements() {
		if (drillThroughElements == null) {
			drillThroughElements = new ArrayList<>();
		}
		return this.drillThroughElements;
	}

	@Override
	public Boolean defaultt() {
		return defaultt;
	}

	public void setDefaultt(Boolean value) {
		this.defaultt = value;
	}

	public void setDrillThroughElements(List<MappingDrillThroughElement> drillThroughElements) {
		this.drillThroughElements = drillThroughElements;
	}
}
