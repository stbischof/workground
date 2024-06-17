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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingFormula;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;

public record NamedSetR(String name,
		String description,
		List<MappingAnnotation> annotations,
        String caption,
        String formula,
        String displayFolder,
        MappingFormula formulaElement)
        implements MappingNamedSet {



	public
 NamedSetR(String name,
			String description,
			List<MappingAnnotation> annotations,
	        String caption,
	        String formula,
	        String displayFolder,
	        MappingFormula formulaElement)
	     {
			this.name = name;
			this.description = description;
			this.annotations = annotations == null ? List.of() : annotations;
			this.caption = caption;
			this.formula = formula;
			this.displayFolder = displayFolder;
			this.formulaElement = formulaElement;

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

    public String getFormula() {
        return formula;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public MappingFormula getFormulaElement() {
        return formulaElement;
    }
}
