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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingScript;

public record  ScriptR(String language, String cdata) implements MappingScript {

    public ScriptR(String language, String cdata) {
        this.language = language == null ? "JavaScript" : language;
        this.cdata = cdata;
    }

    public String getLanguage() {
        return language;
    }

    public String getCdata() {
        return cdata;
    }
}
