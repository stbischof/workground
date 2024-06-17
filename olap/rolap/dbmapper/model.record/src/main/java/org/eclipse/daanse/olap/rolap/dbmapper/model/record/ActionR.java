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
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;

public record ActionR(String name,
                      String description,
                      List<MappingAnnotation> annotations,
                      String caption
) implements MappingAction {

    public ActionR(String name, String description, List<MappingAnnotation> annotations, String caption) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.caption = caption;
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
}
