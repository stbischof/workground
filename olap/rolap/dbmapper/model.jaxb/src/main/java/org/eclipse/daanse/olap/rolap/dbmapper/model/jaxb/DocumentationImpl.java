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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDocumentation;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documentation")
public class DocumentationImpl implements MappingDocumentation {

    @XmlValue
    protected String documentation;

    @Override
    public String documentation() {
        return documentation.replaceAll( "<!\\[CDATA\\[", "" ).replaceAll( "]]>", "" );
            //.trim().removePrefix("&lt![CDATA[").removeSuffix("]]&gt");
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

}
