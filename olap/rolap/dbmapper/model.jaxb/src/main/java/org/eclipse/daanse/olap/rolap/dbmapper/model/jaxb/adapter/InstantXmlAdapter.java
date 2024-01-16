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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.adapter;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.Instant;

public class InstantXmlAdapter  extends XmlAdapter<String,  Instant> {

    @Override
    public Instant unmarshal(String v) {
        return Instant.parse(v);
    }

    @Override
    public String marshal(Instant e) throws Exception {
        if (e != null) {
            return e.toString();
        }
        return null;
    }

}
