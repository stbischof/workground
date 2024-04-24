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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTranslation;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Translation", propOrder = { "language", "caption", "description", "displayFolder", "annotations"  })
@XmlRootElement(name = "Translation")
public class TranslationImpl  implements MappingTranslation {
    @XmlElement(name = "Language")
    @XmlSchemaType(name = "unsignedInt")
    protected long language;
    @XmlElement(name = "Caption")
    protected String caption;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = AnnotationImpl.class)
    protected List<MappingAnnotation> annotations;

    public long language() {
        return language;
    }

    public void setLanguage(long value) {
        this.language = value;
    }

    public String caption() {
        return caption;
    }

    public void setCaption(String value) {
        this.caption = value;
    }

    public String description() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String displayFolder() {
        return displayFolder;
    }

    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public List<MappingAnnotation> annotations() {
        return annotations;
    }

    public void setAnnotations(List<MappingAnnotation> value) {
        this.annotations = value;
    }

}
