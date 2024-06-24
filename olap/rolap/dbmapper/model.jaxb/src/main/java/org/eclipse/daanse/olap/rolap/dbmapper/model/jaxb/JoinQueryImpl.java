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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinedQueryElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "left", "right" })
public class JoinQueryImpl implements MappingJoinQuery {

    @XmlElement(name = "Left", type = JoinedQueryElementImpl.class)
    MappingJoinedQueryElement left;

    @XmlElement(name = "Right", type = JoinedQueryElementImpl.class)
    MappingJoinedQueryElement right;

    @Override
    public MappingJoinedQueryElement left() {
        return left;
    }

    @Override
    public MappingJoinedQueryElement right() {
        return right;
    }

    public void setLeft(MappingJoinedQueryElement left) {
        this.left = left;
    }

    public void setRight(MappingJoinedQueryElement right) {
        this.right = right;
    }
}
