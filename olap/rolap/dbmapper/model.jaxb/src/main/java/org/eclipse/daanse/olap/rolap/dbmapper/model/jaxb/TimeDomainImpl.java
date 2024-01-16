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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTimeDomain;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeDomain")
public class TimeDomainImpl implements MappingTimeDomain {

    @XmlAttribute(name = "role")
    private String role;

    @XmlAttribute(name = "epoch")
    private String epoch;

    @Override
    public String role() {
        return null;
    }

    @Override
    public String epoch() {
        return null;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }
}
