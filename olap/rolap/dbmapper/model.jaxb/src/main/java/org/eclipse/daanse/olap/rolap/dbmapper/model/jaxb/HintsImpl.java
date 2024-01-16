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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHints;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hints", propOrder = { "hints" })
public class HintsImpl implements MappingHints {

    @XmlElement(name = "Hint", type = HintImpl.class)
    private List<MappingHint> hints;

    @Override
    public List<MappingHint> hints() {
        return hints;
    }

    public void setHints(List<MappingHint> hints) {
        this.hints = hints;
    }
}
