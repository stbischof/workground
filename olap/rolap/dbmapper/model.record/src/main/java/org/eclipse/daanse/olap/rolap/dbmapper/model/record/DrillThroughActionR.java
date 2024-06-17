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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughElement;

public record DrillThroughActionR(String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean defaultt,
        List<MappingDrillThroughElement> drillThroughElements)
implements MappingDrillThroughAction {



	public  DrillThroughActionR(String name,
            String description,
            List<MappingAnnotation> annotations,
            String caption,
            Boolean defaultt,
            List<MappingDrillThroughElement> drillThroughElements)
 {
	this.name = name;
	this.description = description;
	this.annotations = annotations == null ? List.of() : annotations;
	this.caption = caption;
	this.defaultt = defaultt == null ? Boolean.FALSE : defaultt;
	this.drillThroughElements = drillThroughElements == null ? List.of() : drillThroughElements;

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

    public Boolean getDefaultt() {
        return defaultt;
    }

    public List<MappingDrillThroughElement> getDrillThroughElements() {
        return drillThroughElements;
    }
}
