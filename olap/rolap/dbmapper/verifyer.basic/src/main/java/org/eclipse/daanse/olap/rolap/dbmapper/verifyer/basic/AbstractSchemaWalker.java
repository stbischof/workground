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
package org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic;

import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.NOT_SET;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggColumnName;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggExclude;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggForeignKey;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevelProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasureFactCount;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggName;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggPattern;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMemberProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingClosure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingElementFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingFormula;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoinedQueryElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberReaderParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRoleUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRow;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSharedDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTranslation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUnion;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingValue;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.VerificationResult;

public abstract class AbstractSchemaWalker {

    protected List<VerificationResult> results = new ArrayList<>();

    public List<VerificationResult> checkSchema(MappingSchema schema) {

        if (schema != null) {
            checkAnnotationList(schema.annotations());
            checkParameterList(schema.parameters());
            checkCubeDimensionList(schema.dimensions(), null, schema);
            checkCubeList(schema.cubes(), schema);
            checkVirtualCubeList(schema.virtualCubes(), schema);
            checkNamedSetList(schema.namedSets());
            checkRoleList(schema.roles(), schema);
            checkUserDefinedFunctionList(schema.userDefinedFunctions());
        }

        return results;
    }

    protected void checkCube(MappingCube cube, MappingSchema schema) {
        if (cube != null) {
            checkAnnotationList(cube.annotations());
            checkCubeDimensionList(cube.dimensionUsageOrDimensions(), cube, schema);
            checkMeasureList(cube.measures(), cube);
            checkCalculatedMemberList(cube.calculatedMembers());
            checkNamedSetList(cube.namedSets());
            checkDrillThroughActionList(cube.drillThroughActions());
            checkWritebackTableOption(cube.writebackTable(), cube);
            checkKpiList(cube.kpis(), cube);
            if (cube.actions() != null) {
                cube.actions().forEach(this::checkAction);
            }
        }
    }

    protected void checkAction(MappingAction action) {
        if (action != null) {
            checkAnnotationList(action.annotations());
        }
    }

    protected void checkDrillThroughAction(MappingDrillThroughAction drillThroughAction) {
        if (drillThroughAction != null) {
            checkAnnotationList(drillThroughAction.annotations());
            checkDrillThroughElementList(drillThroughAction.drillThroughElements());
        }
    }

    protected void checkDrillThroughElement(MappingDrillThroughElement drillThroughElement) {
        if (drillThroughElement != null) {
            if (drillThroughElement instanceof MappingDrillThroughMeasure drillThroughMeasure) {
                checkDrillThroughMeasure(drillThroughMeasure);
            }
            if (drillThroughElement instanceof MappingDrillThroughAttribute drillThroughAttribute) {
                checkDrillThroughAttribute(drillThroughAttribute);
            }
        }
    }

    protected void checkDrillThroughAttribute(MappingDrillThroughAttribute drillThroughElement) {
        //empty
    }

    protected void checkDrillThroughMeasure(MappingDrillThroughMeasure drillThroughElement) {
        //empty
    }

    @SuppressWarnings("java:S1172")
    protected void checkMeasure(MappingMeasure measure, MappingCube cube) {
        if (measure != null) {
            checkMeasureColumn(measure, cube);
            checkMeasureAggregation(measure, cube);
            checkAnnotationList(measure.annotations());
            checkCalculatedMemberPropertyList(measure.calculatedMemberProperties());
            checkExpressionView(measure.measureExpression());
            checkElementFormatter(measure.cellFormatter());
        }
    }

    protected void checkKpi(MappingKpi kpi, MappingCube cube) {
        if (kpi != null) {
            checkAnnotationList(kpi.annotations());
            checkTranslationList(kpi.translations());
        }
    }

    protected void checkKpi(MappingKpi kpi, MappingVirtualCube cube) {
        if (kpi != null) {
            checkAnnotationList(kpi.annotations());
            checkTranslationList(kpi.translations());
        }
    }

    protected void checkMeasureAggregation(MappingMeasure measure, MappingCube cube) {
        //empty
    }

    protected void checkMeasureColumn(MappingMeasure measure, MappingCube cube) {
        //empty
    }

    protected void checkCalculatedMemberProperty(MappingCalculatedMemberProperty calculatedMemberProperty) {
        //empty
    }

    protected void checkExpressionView(MappingExpressionView measureExpression) {
        if (measureExpression != null) {
            checkSQLList(measureExpression.sqls());
        }
    }

    protected void checkSQL(MappingSQL sql) {
        //empty
    }

    protected void checkElementFormatter(MappingElementFormatter elementFormatter) {
        //empty
    }

    protected void checkElementFormatterClass(String className) {
        //empty
    }

    protected void checkCubeDimension(MappingCubeDimension cubeDimension, MappingCube cube, MappingSchema schema) {
        if (cubeDimension != null) {
            checkAnnotationList(cubeDimension.annotations());
            if (cubeDimension instanceof MappingPrivateDimension privateDimension && privateDimension.hierarchies() != null) {
                privateDimension.hierarchies()
                    .forEach(h -> checkHierarchy(h, (MappingPrivateDimension) cubeDimension, cube));
            }
        }
    }

    protected void checkHierarchy(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension, MappingCube cube) {
        if (hierarchy != null) {
            checkAnnotationList(hierarchy.annotations());
            checkMemberReaderParameterList(hierarchy.memberReaderParameters());
            checkRelationOrJoin(hierarchy.relation());
            //Level
            if (hierarchy.levels() != null) {
                hierarchy.levels().forEach(l -> checkLevel(l, hierarchy, cubeDimension, cube));
            }
        }
    }

    protected void checkMemberReaderParameter(MappingMemberReaderParameter memberReaderParameter) {
        //empty
    }

    protected void checkJoin(MappingJoinQuery join) {
        if (join != null) {
            checkJoinedQueryElement(join.left());
            checkJoinedQueryElement(join.right());
        }
    }

    protected void checkJoinedQueryElement(MappingJoinedQueryElement element) {
        if (element != null) {
            checkRelationOrJoin(element.getQuery());
        }
    }

    protected void checkRelationOrJoin(MappingQuery relationOrJoin) {
        if (relationOrJoin != null) {
            if (relationOrJoin instanceof MappingInlineTable inlineTable) {
                checkInlineTable(inlineTable);
            }
            if (relationOrJoin instanceof MappingJoinQuery join) {
                checkJoin(join);
            }
            if (relationOrJoin instanceof MappingTable table) {
                checkTable(table);
            }
            if (relationOrJoin instanceof MappingView view) {
                checkView(view);
            }
        }
    }

    protected void checkView(MappingView relationOrJoin) {
        if (relationOrJoin != null) {
            checkSQLList(relationOrJoin.sqls());
        }
    }

    protected void checkInlineTable(MappingInlineTable relationOrJoin) {
        if (relationOrJoin != null) {
            checkColumnDefList(relationOrJoin.columnDefs());
            checkRowList(relationOrJoin.rows());
        }
    }

    protected void checkRow(MappingRow row) {
        if (row != null) {
            checkValueList(row.values());
        }
    }

    protected void checkValue(MappingValue value) {
        //empty
    }

    protected void checkColumnDef(MappingColumnDef columnDef) {
        //empty
    }

    protected void checkTable(MappingTable table) {
        if (table != null) {
            checkSQL(table.getSql());

            checkAggExcludeList(table.getAggExcludes(), table.getSchema());

            checkAggTableList(table.getAggTables(), table.getSchema());

            checkHintList(table.getHints());
        }
    }

    protected void checkHint(MappingHint hint) {
        //empty
    }

    protected void checkAggTable(MappingAggTable aggTable, String schema) {
        if (aggTable != null) {
            checkAggColumnName(aggTable.aggFactCount());
            checkAggColumnNameList(aggTable.aggIgnoreColumns());
            checkAggForeignKeyList(aggTable.aggForeignKeys());
            checkAggMeasureList(aggTable.aggMeasures());
            checkAggLevelList(aggTable.aggLevels());
            checkAggMeasureFactCountList(aggTable.measuresFactCounts());
            if (aggTable instanceof MappingAggName aggName) {
                checkAggName(aggName);
            }
            if (aggTable instanceof MappingAggPattern aggPattern) {
                checkAggPattern(aggPattern, schema);
            }
        }
    }

    protected void checkAggPattern(MappingAggPattern aggTable, String schema) {
        if (aggTable != null) {
            checkAggExcludeList(aggTable.aggExcludes(), schema);
        }
    }

    protected void checkAggName(MappingAggName aggTable) {
        //empty
    }

    protected void checkAggMeasureFactCount(MappingAggMeasureFactCount aggMeasureFactCount) {
        //empty
    }

    protected void checkAggLevel(MappingAggLevel aggLevel) {
        if (aggLevel != null) {
            checkAggLevelPropertyList(aggLevel.properties());
        }
    }

    protected void checkAggLevelProperty(MappingAggLevelProperty aggLevelProperty) {
        //empty
    }

    protected void checkAggMeasure(MappingAggMeasure aggMeasure) {
        //empty
    }

    protected void checkAggForeignKey(MappingAggForeignKey aggForeignKey) {
        //empty
    }

    protected void checkAggColumnName(MappingAggColumnName aggFactCount) {
        if (aggFactCount != null && aggFactCount instanceof MappingAggMeasureFactCount aggMeasureFactCount) {
            checkAggMeasureFactCount(aggMeasureFactCount);
        }
    }

    protected void checkAggExclude(MappingAggExclude aggExclude, String schemaName) {
        //empty
    }

    @SuppressWarnings("java:S1172")
    protected void checkLevel(
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level, MappingHierarchy hierarchy,
        MappingPrivateDimension parentDimension, MappingCube cube
    ) {
        if (level != null) {
            checkAnnotationList(level.annotations());

            checkExpression(level.keyExpression());

            checkExpression(level.nameExpression());

            checkExpression(level.captionExpression());

            checkExpression(level.ordinalExpression());

            checkExpression(level.parentExpression());

            checkClosure(level.closure());

            checkPropertyList(level.properties(), level, hierarchy,
                cube);

            checkElementFormatter(level.memberFormatter());
        }
    }

    protected void checkClosure(MappingClosure closure) {
        if (closure != null) {
            checkTable(closure.table());
        }
    }

    protected void checkExpression(MappingExpression keyExpression) {
        if (keyExpression != null) {
            if (keyExpression instanceof MappingColumn column) {
                checkColumn(column);
            }
            if (keyExpression instanceof MappingExpressionView expressionView) {
                checkExpressionView(expressionView);
            }
        }
    }

    protected void checkColumn(MappingColumn keyExpression) {
        //empty
    }

    @SuppressWarnings("java:S1172")
    protected void checkProperty(
        MappingProperty property, org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingHierarchy hierarchy, MappingCube cube
    ) {
        if (property != null) {
            //ElementFormatter
            checkElementFormatter(property.propertyFormatter());
        }
    }

    protected void checkVirtualCube(MappingVirtualCube virtCube, MappingSchema schema) {
        if (virtCube != null) {
            checkAnnotationList(virtCube.annotations());

            checkCubeUsageList(virtCube.cubeUsages());

            checkCubeDimensionList(virtCube.virtualCubeDimensions(), null, schema);

            checkVirtualCubeMeasureList(virtCube.virtualCubeMeasures(), virtCube, schema);

            checkNamedSetList(virtCube.namedSets());

            checkKpiList(virtCube.kpis(), virtCube);

            //CalculatedMember
            if (virtCube.calculatedMembers() != null) {
                virtCube.calculatedMembers()
                    .forEach(this::checkCalculatedMember);
            }
        }
    }

    protected void checkCubeUsage(MappingCubeUsage cubeUsage) {
        //empty
    }

    protected void checkVirtualCubeMeasure(MappingVirtualCubeMeasure virtualCubeMeasure, MappingVirtualCube vCube, MappingSchema schema) {
        if (virtualCubeMeasure != null) {
            checkAnnotationList(virtualCubeMeasure.annotations());
        }
    }

    protected void checkCalculatedMember(MappingCalculatedMember calculatedMember) {
        if (calculatedMember != null) {
            checkAnnotationList(calculatedMember.annotations());
            checkCalculatedMemberPropertyList(calculatedMember.calculatedMemberProperties());

            if (calculatedMember.formulaElement() != null) {
                checkFormula(calculatedMember.formulaElement());
            }

            if (calculatedMember.cellFormatter() != null) {
                checkElementFormatter(calculatedMember.cellFormatter());
            }
        }
    }

    protected void checkFormula(MappingFormula formula) {
        //empty
    }

    /**
     * Validates a column, and returns an error message if it is invalid.
     *
     * @param column          Column
     * @param fieldName       Field name
     * @param level           Level
     * @param cube            Cube
     * @param parentHierarchy Hierarchy
     */
    protected void checkColumn(
        String column, String fieldName, org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingCube cube, MappingHierarchy parentHierarchy
    ) {
        //empty
    }

    protected void checkNamedSet(MappingNamedSet namedSet) {
        if (namedSet != null) {
            checkAnnotationList(namedSet.annotations());

            if (namedSet.formulaElement() != null) {
                checkFormula(namedSet.formulaElement());
            }
        }
    }

    /**
     * @return @deprecated
     */
    @Deprecated(since="new version", forRemoval=true)
    protected void checkUserDefinedFunction(MappingUserDefinedFunction udf) {
        //empty
    }

    protected void checkParameter(MappingParameter parameter) {
        //empty
    }

    protected void checkAnnotation(MappingAnnotation annotation) {
        //empty
    }

    protected void checkTranslation(MappingTranslation annotation) {
        //empty
    }

    protected void checkRole(MappingRole role, MappingSchema schema) {
        if (role != null) {
            checkAnnotationList(role.annotations());
            checkSchemaGrantList(role.schemaGrants(), schema);
            checkUnion(role.union());
        }
    }

    protected void checkUnion(MappingUnion union){
        if (union != null) {
            checkRoleUsageList(union.roleUsages());
        }
    }

    private void checkRoleUsageList(List<? extends MappingRoleUsage> list) {
        if (list != null) {
            list.forEach(this::checkRoleUsage);
        }
    }

    protected void checkRoleUsage(MappingRoleUsage roleUsage) {
        //empty
    }

    protected void checkSchemaGrant(MappingSchemaGrant schemaGrant, MappingSchema schema) {
        if (schemaGrant != null) {
            checkCubeGrantList(schemaGrant.cubeGrants(), schema);
        }
    }

    protected void checkCubeGrant(MappingCubeGrant cubeGrant, MappingSchema schema) {
        if (cubeGrant != null && cubeGrant.cube() != null) {
            checkDimensionGrantList(cubeGrant.dimensionGrants());
            checkHierarchyGrantList(cubeGrant.hierarchyGrants(), cubeGrant.cube(), schema);
        }
    }

    protected void checkHierarchyGrant(MappingHierarchyGrant hierarchyGrant, String cubeName,  MappingSchema schema) {
        if (hierarchyGrant != null) {
            checkMemberGrantList(hierarchyGrant.memberGrants(), cubeName, schema);
        }
    }

    protected void checkMemberGrant(MappingMemberGrant memberGrant, String cubeName, MappingSchema schema) {
        //empty
    }

    protected void checkDimensionGrant(MappingDimensionGrant dimensionGrant) {
        //empty
    }

    protected static boolean isEmpty(String v) {
        return (v == null) || v.equals("");
    }

    protected void checkNamedSetList(List<? extends MappingNamedSet> namedSet) {
        if (namedSet != null) {
            namedSet.forEach(this::checkNamedSet);

        }
    }

    protected void checkWritebackTable(MappingWritebackTable writebackTable, MappingCube cube) {
        if (writebackTable != null && writebackTable.columns() != null) {
            writebackTable.columns().forEach(c -> checkWritebackColumn(c, cube));
        }
    }

    protected void checkWritebackColumn(MappingWritebackColumn writebackColumn, MappingCube cube) {
        if (writebackColumn != null) {
            if (writebackColumn instanceof MappingWritebackAttribute writebackAttribute) {
                checkWritebackAttribute(writebackAttribute, cube);
            }
            if (writebackColumn instanceof MappingWritebackMeasure writebackMeasure) {
                checkWritebackMeasure(writebackMeasure, cube);
            }
        }
    }

    protected void checkWritebackMeasure(MappingWritebackMeasure writebackColumn, MappingCube cube) {
        //empty
    }

    protected void checkWritebackAttribute(MappingWritebackAttribute writebackColumn, MappingCube cube) {
        //empty
    }

    protected void checkSharedDimension(final MappingSharedDimension sharedDimension) {
        //empty
    }

    protected boolean isSchemaRequired() {
        return true;
    }

    protected String orNotSet(String value) {
        return value == null ? NOT_SET : value;
    }

    protected void checkFact(MappingCube cube, MappingSchema schema) {
        //empty
    }

    private void checkHintList(List<? extends MappingHint> list) {
        if (list != null) {
            list.forEach(this::checkHint);
        }
    }

    private void checkCalculatedMemberPropertyList(List<? extends MappingCalculatedMemberProperty> list) {
        if (list != null) {
            list.forEach(this::checkCalculatedMemberProperty);
        }
    }

    private void checkAggTableList(List<? extends MappingAggTable> list, String schema) {
        if (list != null) {
            list.forEach(at -> checkAggTable(at, schema));
        }
    }

    private void checkDrillThroughElementList(List<? extends MappingDrillThroughElement> list) {
        if (list != null) {
            list.forEach(this::checkDrillThroughElement);
        }
    }

    protected void checkSQLList(List<? extends MappingSQL> list) {
        if (list != null) {
            list.forEach(this::checkSQL);
        }
    }

    private void checkMemberReaderParameterList(List<? extends MappingMemberReaderParameter> list) {
        if (list != null) {
            list.forEach(this::checkMemberReaderParameter);
        }
    }

    private void checkRowList(List<? extends MappingRow> list) {
        if (list != null) {
            list.forEach(this::checkRow);
        }
    }

    private void checkValueList(List<? extends MappingValue> list) {
        if (list != null) {
            list.forEach(this::checkValue);
        }
    }

    private void checkColumnDefList(List<? extends MappingColumnDef> list) {
        if (list != null) {
            list.forEach(this::checkColumnDef);
        }
    }

    private void checkAggMeasureFactCountList(List<? extends MappingAggMeasureFactCount> list) {
        if (list != null) {
            list.forEach(this::checkAggMeasureFactCount);
        }
    }

    private void checkAggLevelList(List<? extends MappingAggLevel> list) {
        if (list != null) {
            list.forEach(this::checkAggLevel);
        }
    }

    private void checkAggLevelPropertyList(List<MappingAggLevelProperty> list) {
        if (list != null) {
            list.forEach(this::checkAggLevelProperty);
        }
    }

    private void checkAggMeasureList(List<? extends MappingAggMeasure> list) {
        if (list != null) {
            list.forEach(this::checkAggMeasure);
        }
    }

    private void checkAggForeignKeyList(List<? extends MappingAggForeignKey> list) {
        if (list != null) {
            list.forEach(this::checkAggForeignKey);
        }
    }

    private void checkAggColumnNameList(List<? extends MappingAggColumnName> list) {
        if (list != null) {
            list.forEach(this::checkAggColumnName);
        }
    }

    private void checkAggExcludeList(List<? extends MappingAggExclude> list, String schema) {
        if (list != null) {
            list.forEach(ae -> checkAggExclude(ae, schema));
        }
    }

    private void checkPropertyList(
        List<? extends MappingProperty> list,
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingHierarchy hierarchy, MappingCube cube
    ) {
        if (list != null) {
            list.forEach(it -> checkProperty(it, level,
                hierarchy, cube));
        }
    }

    private void checkCubeUsageList(List<? extends MappingCubeUsage> list) {
        if (list != null) {
            list.forEach(this::checkCubeUsage);
        }
    }

    private void checkVirtualCubeMeasureList(List<? extends MappingVirtualCubeMeasure> list, MappingVirtualCube virtCube, MappingSchema schema) {
        if (list != null) {
            list.forEach(m -> checkVirtualCubeMeasure(m, virtCube, schema));
        }
    }

    private void checkCubeGrantList(List<? extends MappingCubeGrant> list, MappingSchema schema) {
        if (list != null) {
            list.forEach(cg -> checkCubeGrant(cg, schema));
        }
    }

    private void checkHierarchyGrantList(List<? extends MappingHierarchyGrant> list, String cubeName, MappingSchema schema) {
        if (list != null) {
            list.forEach(hg -> checkHierarchyGrant(hg, cubeName, schema));
        }
    }

    private void checkMemberGrantList(List<? extends MappingMemberGrant> list, String cubeName, MappingSchema schema) {
        if (list != null) {
            list.forEach(mg -> checkMemberGrant(mg, cubeName, schema));
        }
    }

    private void checkDimensionGrantList(List<? extends MappingDimensionGrant> list) {
        if (list != null) {
            list.forEach(this::checkDimensionGrant);
        }
    }

    private void checkSchemaGrantList(List<? extends MappingSchemaGrant> list, MappingSchema schema) {
        if (list != null) {
            list.forEach(sg -> checkSchemaGrant(sg, schema));
        }
    }

    private void checkCubeDimensionList(List<? extends MappingCubeDimension> list, MappingCube cube, MappingSchema schema) {
        if (list != null) {
            list.forEach(it -> checkCubeDimension(it, cube, schema));
        }
    }

    private void checkVirtualCubeList(List<? extends MappingVirtualCube> list, MappingSchema schema) {
        if (list != null) {
            list.forEach(vc -> checkVirtualCube(vc, schema));
        }
    }

    /**
     * @return @deprecated
     */
    @Deprecated(since="new version", forRemoval=true)
    private void checkUserDefinedFunctionList(List<? extends MappingUserDefinedFunction> list) {
        if (list != null) {
            list.forEach(this::checkUserDefinedFunction);
        }
    }

    private void checkCubeList(List<? extends MappingCube> list, MappingSchema schema) {
        if (list != null) {
            list.forEach(cube -> checkCube(cube, schema));
        }
    }

    private void checkAnnotationList(List<? extends MappingAnnotation> list) {
        if (list != null) {
            list.forEach(this::checkAnnotation);
        }
    }

    private void checkTranslationList(List<? extends MappingTranslation> list) {
        if (list != null) {
            list.forEach(this::checkTranslation);
        }
    }

    private void checkParameterList(List<? extends MappingParameter> list) {
        if (list != null) {
            list.forEach(this::checkParameter);
        }
    }

    private void checkCalculatedMemberList(List<? extends MappingCalculatedMember> list) {
        if (list != null) {
            list.forEach(this::checkCalculatedMember);
        }
    }

    private void checkMeasureList(List<? extends MappingMeasure> list, MappingCube cube) {
        if (list != null) {
            list.forEach(m -> checkMeasure(m, cube));
        }
    }

    private void checkKpiList(List<? extends MappingKpi> list, MappingCube cube) {
        if (list != null) {
            list.forEach(k -> checkKpi(k, cube));
        }
    }

    private void checkKpiList(List<? extends MappingKpi> list, MappingVirtualCube cube) {
        if (list != null) {
            list.forEach(k -> checkKpi(k, cube));
        }
    }

    private void checkRoleList(List<? extends MappingRole> list, MappingSchema schema) {
        if (list != null) {
            list.forEach(r -> checkRole(r, schema));
        }
    }

    private void checkWritebackTableOption(Optional<? extends MappingWritebackTable> writebackTable, MappingCube cube) {
        if (writebackTable != null && writebackTable.isPresent()) {
            checkWritebackTable(writebackTable.get(), cube);
        }
    }

    private void checkDrillThroughActionList(List<? extends MappingDrillThroughAction> list) {
        if (list != null) {
            list.forEach(this::checkDrillThroughAction);
        }
    }
}
