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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberReaderParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;

public record HierarchyR(String name,
                         String description,
                         List<MappingAnnotation> annotations,
                         String caption,
                         Boolean visible,
                         List<MappingLevel> levels,
                         List<MappingMemberReaderParameter> memberReaderParameters,
                         Boolean hasAll,
                         String allMemberName,
                         String allMemberCaption,
                         String allLevelName,
                         String primaryKey,
                         String primaryKeyTable,
                         String defaultMember,
                         String memberReaderClass,
                         String uniqueKeyLevelName,
                         String displayFolder,
                         MappingQuery relation,
                         String origin
)
    implements MappingHierarchy {

    public HierarchyR(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        List<MappingLevel> levels,
        List<MappingMemberReaderParameter> memberReaderParameters,
        Boolean hasAll,
        String allMemberName,
        String allMemberCaption,
        String allLevelName,
        String primaryKey,
        String primaryKeyTable,
        String defaultMember,
        String memberReaderClass,
        String uniqueKeyLevelName,
        String displayFolder,
        MappingQuery relation,
        String origin
    ) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.caption = caption;
        this.visible = visible == null ? Boolean.TRUE : visible;
        this.levels = levels == null ? List.of() : levels;
        this.memberReaderParameters = memberReaderParameters == null ? List.of() : memberReaderParameters;
        this.hasAll = hasAll == null ? Boolean.FALSE : hasAll;//TODO: docs sais none default -null
        this.allMemberName = allMemberName;
        this.allMemberCaption = allMemberCaption;
        this.allLevelName = allLevelName;
        this.primaryKey = primaryKey;
        this.primaryKeyTable = primaryKeyTable;
        this.defaultMember = defaultMember;
        this.memberReaderClass = memberReaderClass;
        this.uniqueKeyLevelName = uniqueKeyLevelName;
        this.displayFolder = displayFolder;
        this.relation = relation;
        this.origin = origin;

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

    public Boolean getVisible() {
        return visible;
    }

    public List<MappingLevel> getLevels() {
        return levels;
    }

    public List<MappingMemberReaderParameter> getMemberReaderParameters() {
        return memberReaderParameters;
    }

    public Boolean getHasAll() {
        return hasAll;
    }

    public String getAllMemberName() {
        return allMemberName;
    }

    public String getAllMemberCaption() {
        return allMemberCaption;
    }

    public String getAllLevelName() {
        return allLevelName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getPrimaryKeyTable() {
        return primaryKeyTable;
    }

    public String getDefaultMember() {
        return defaultMember;
    }

    public String getMemberReaderClass() {
        return memberReaderClass;
    }

    public String getUniqueKeyLevelName() {
        return uniqueKeyLevelName;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public MappingQuery getRelation() {
        return relation;
    }

    public String getOrigin() {
        return origin;
    }
}
