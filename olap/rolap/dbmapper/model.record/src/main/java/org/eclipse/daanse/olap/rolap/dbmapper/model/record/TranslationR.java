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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTranslation;

import java.util.List;

public record TranslationR(
    long language,
    String caption,
    String description,
    String displayFolder,
    List<MappingAnnotation> annotations
) implements MappingTranslation {

    public long getLanguage() {
        return language;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public List<MappingAnnotation> getAnnotations() {
        return annotations;
    }
}
