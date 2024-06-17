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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMemberProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingElementFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MeasureDataTypeEnum;

public record MeasureR(String name,
                       String description,
                       List<MappingAnnotation> annotations,
                       String caption,
                       Boolean visible,
                       String column,
                       MeasureDataTypeEnum datatype,
                       String formatString,
                       String aggregator,
                       String formatter,
                       String displayFolder,
                       MappingExpressionView measureExpression,
                       List<MappingCalculatedMemberProperty> calculatedMemberProperties,
                       MappingElementFormatter cellFormatter,
                       String backColor
)
    implements MappingMeasure {

    public MeasureR(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String column,
        MeasureDataTypeEnum datatype,
        String formatString,
        String aggregator,
        String formatter,
        String displayFolder,
        MappingExpressionView measureExpression,
        List<MappingCalculatedMemberProperty> calculatedMemberProperties,
        MappingElementFormatter cellFormatter,
        String backColor
    ) {
        this.name = name;
        this.description = description;
        this.annotations = annotations == null ? List.of() : annotations;
        this.caption = caption;
        this.visible = visible == null ? Boolean.TRUE : visible;
        this.column = column;
        this.datatype = datatype;
        this.formatString = formatString;
        this.aggregator = aggregator;
        this.formatter = formatter;
        this.displayFolder = displayFolder;
        this.measureExpression = measureExpression;
        this.calculatedMemberProperties = calculatedMemberProperties == null ? List.of() : calculatedMemberProperties;
        this.cellFormatter = cellFormatter;
        this.backColor = backColor;

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

    public String getColumn() {
        return column;
    }

    public MeasureDataTypeEnum getDatatype() {
        return datatype;
    }

    public String getFormatString() {
        return formatString;
    }

    public String getAggregator() {
        return aggregator;
    }

    public String getFormatter() {
        return formatter;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public MappingExpressionView getMeasureExpression() {
        return measureExpression;
    }

    public List<MappingCalculatedMemberProperty> getCalculatedMemberProperties() {
        return calculatedMemberProperties;
    }

    public MappingElementFormatter getCellFormatter() {
        return cellFormatter;
    }

    public String getBackColor() {
        return backColor;
    }
}
