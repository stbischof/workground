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

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingClosure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingElementFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.HideMemberIfEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.InternalTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;

public record LevelR(String name,
                     String description,
                     List<MappingAnnotation> annotations,
                     String caption,
                     Boolean visible,
                     String table,
                     String column,
                     String nameColumn,
                     String ordinalColumn,
                     String parentColumn,
                     String nullParentValue,
                     TypeEnum type,
                     String approxRowCount,
                     Boolean uniqueMembers,
                     LevelTypeEnum levelType,
                     HideMemberIfEnum hideMemberIf,
                     String formatter,
                     String captionColumn,
                     MappingExpressionView keyExpression,
                     MappingExpressionView nameExpression,
                     MappingExpressionView captionExpression,
                     MappingExpressionView ordinalExpression,
                     MappingExpressionView parentExpression,
                     MappingClosure closure,
                     List<MappingProperty> properties,
                     InternalTypeEnum internalType,
                     MappingElementFormatter memberFormatter
)
    implements MappingLevel {

    public LevelR(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String table,
        String column,
        String nameColumn,
        String ordinalColumn,
        String parentColumn,
        String nullParentValue,
        TypeEnum type,
        String approxRowCount,
        Boolean uniqueMembers,
        LevelTypeEnum levelType,
        HideMemberIfEnum hideMemberIf,
        String formatter,
        String captionColumn,
        MappingExpressionView keyExpression,
        MappingExpressionView nameExpression,
        MappingExpressionView captionExpression,
        MappingExpressionView ordinalExpression,
        MappingExpressionView parentExpression,
        MappingClosure closure,
        List<MappingProperty> properties,
        InternalTypeEnum internalType,
        MappingElementFormatter memberFormatter
    ) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.caption = caption;
        this.visible = visible == null ? Boolean.TRUE : visible;
        this.table = table;
        this.column = column;
        this.nameColumn = nameColumn;
        this.ordinalColumn = ordinalColumn;
        this.parentColumn = parentColumn;
        this.nullParentValue = nullParentValue;
        this.type = type == null ? TypeEnum.STRING : type;
        this.approxRowCount = approxRowCount;
        this.uniqueMembers = uniqueMembers == null ? Boolean.FALSE : uniqueMembers;
        this.levelType = levelType == null ? LevelTypeEnum.REGULAR : levelType;
        this.hideMemberIf = hideMemberIf == null ? HideMemberIfEnum.NEVER : hideMemberIf;
        this.formatter = formatter;
        this.captionColumn = captionColumn;
        this.keyExpression = keyExpression;
        this.nameExpression = nameExpression;
        this.captionExpression = captionExpression;
        this.ordinalExpression = ordinalExpression;
        this.parentExpression = parentExpression;
        this.closure = closure;
        this.properties = properties == null ? List.of() : properties;
        this.internalType = internalType;// == null ? InternalTypeEnum.STRING : internalType;
        this.memberFormatter = memberFormatter;
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

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public String getOrdinalColumn() {
        return ordinalColumn;
    }

    public String getParentColumn() {
        return parentColumn;
    }

    public String getNullParentValue() {
        return nullParentValue;
    }

    public TypeEnum getType() {
        return type;
    }

    public String getApproxRowCount() {
        return approxRowCount;
    }

    public Boolean getUniqueMembers() {
        return uniqueMembers;
    }

    public LevelTypeEnum getLevelType() {
        return levelType;
    }

    public HideMemberIfEnum getHideMemberIf() {
        return hideMemberIf;
    }

    public String getFormatter() {
        return formatter;
    }

    public String getCaptionColumn() {
        return captionColumn;
    }

    public MappingExpressionView getKeyExpression() {
        return keyExpression;
    }

    public MappingExpressionView getNameExpression() {
        return nameExpression;
    }

    public MappingExpressionView getCaptionExpression() {
        return captionExpression;
    }

    public MappingExpressionView getOrdinalExpression() {
        return ordinalExpression;
    }

    public MappingExpressionView getParentExpression() {
        return parentExpression;
    }

    public MappingClosure getClosure() {
        return closure;
    }

    public List<MappingProperty> getProperties() {
        return properties;
    }

    public InternalTypeEnum getInternalType() {
        return internalType;
    }

    public MappingElementFormatter getMemberFormatter() {
        return memberFormatter;
    }
}
