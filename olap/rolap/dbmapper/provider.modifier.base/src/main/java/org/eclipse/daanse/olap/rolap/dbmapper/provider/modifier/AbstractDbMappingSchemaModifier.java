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
package org.eclipse.daanse.olap.rolap.dbmapper.provider.modifier;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCellFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingClosure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDocumentation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingElementFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingFormula;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberReaderParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRoleUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRow;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingScript;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTranslation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUnion;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingValue;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.AccessEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.DimensionTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.HideMemberIfEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.InternalTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MeasureDataTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MemberGrantAccessEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.ParameterTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.PropertyTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.api.DatabaseMappingSchemaProvider;

public abstract class AbstractDbMappingSchemaModifier implements DatabaseMappingSchemaProvider {

    protected MappingSchema mappingSchema;

    protected AbstractDbMappingSchemaModifier(MappingSchema mappingSchema) {
        super();
        this.mappingSchema = mappingSchema;
    }

    public MappingSchema get() {
        return modifyMappingSchema(mappingSchema);
    }

    /**
     * @param mappingSchemaOriginal
     * @return
     */
    protected MappingSchema modifyMappingSchema(MappingSchema mappingSchemaOriginal) {

        if (mappingSchemaOriginal != null) {
            String name = schemaName(mappingSchemaOriginal);
            String description = schemaDescription(mappingSchemaOriginal);
            String measuresCaption = schemaMeasuresCaption(mappingSchemaOriginal);
            String defaultRole = schemaDefaultRole(mappingSchemaOriginal);
            List<MappingAnnotation> annotations = schemaAnnotations(mappingSchemaOriginal);
            List<MappingParameter> parameters = schemaParameters(mappingSchemaOriginal);
            List<MappingPrivateDimension> dimensions = schemaDimensions(mappingSchemaOriginal);
            List<MappingCube> cubes = schemaCubes(mappingSchemaOriginal);
            List<MappingVirtualCube> virtualCubes = schemaVirtualCubes(mappingSchemaOriginal);
            List<MappingNamedSet> namedSets = schemaNamedSets(mappingSchemaOriginal);
            List<MappingRole> roles = schemaRoles(mappingSchemaOriginal);
            List<MappingUserDefinedFunction> userDefinedFunctions = schemaUserDefinedFunctions(mappingSchemaOriginal);
            Optional<MappingDocumentation> documentation = schemaDocumentation(mappingSchemaOriginal);
            return new_Schema(name, description, measuresCaption, defaultRole, annotations,
                parameters, dimensions, cubes, virtualCubes, namedSets, roles, userDefinedFunctions, documentation);
        }
        return null;
    }

    protected abstract MappingSchema new_Schema(
        String name, String description, String measuresCaption,
        String defaultRole, List<MappingAnnotation> annotations, List<MappingParameter> parameters,
        List<MappingPrivateDimension> dimensions, List<MappingCube> cubes, List<MappingVirtualCube> virtualCubes,
        List<MappingNamedSet> namedSets, List<MappingRole> roles,
        List<MappingUserDefinedFunction> userDefinedFunctions, Optional<MappingDocumentation> documentation
    );

    protected String schemaDefaultRole(MappingSchema mappingSchemaOriginal) {
        return mappingSchemaOriginal.defaultRole();
    }

    protected String schemaMeasuresCaption(MappingSchema mappingSchemaOriginal) {
        return mappingSchemaOriginal.measuresCaption();
    }

    protected String schemaDescription(MappingSchema mappingSchemaOriginal) {
        return mappingSchemaOriginal.description();
    }

    protected String schemaName(MappingSchema mappingSchemaOriginal) {
        return mappingSchemaOriginal.name();
    }

    protected Optional<MappingDocumentation> schemaDocumentation(MappingSchema mappingSchemaOriginal) {
        if (mappingSchemaOriginal != null) {
            String documentation = documentationDocumentationValue(mappingSchemaOriginal.documentation());
            return new_Documentation(documentation);
        }
        return null;
    }

    protected String documentationDocumentationValue(Optional<MappingDocumentation> documentation) {
        if ( documentation.isPresent() ) {
            return documentation.get().documentation();
        }
        return null;
    };

    protected abstract Optional<MappingDocumentation> new_Documentation(String documentation);


    protected List<MappingAnnotation> schemaAnnotations(MappingSchema mappingSchemaOriginal) {
        return annotations(mappingSchemaOriginal.annotations());
    }

    protected List<MappingParameter> schemaParameters(MappingSchema mappingSchema) {
        return schemaParameters(mappingSchema.parameters());
    }

    protected List<MappingParameter> schemaParameters(List<MappingParameter> parametersOriginal) {
        return parametersOriginal.stream().map(this::parameter).toList();
    }

    protected List<MappingAnnotation> annotations(List<MappingAnnotation> annotations) {
        if (annotations != null) {
            return annotations.stream().map(this::annotation).toList();
        }
        return null;
    }

    protected MappingAnnotation annotation(MappingAnnotation annotation) {
        if (annotation != null) {
            String name = annotation.name();
            String content = annotation.content();
            return new_Annotation(name, content);
        }
        return null;
    }

    protected abstract MappingAnnotation new_Annotation(String name, String content);

    protected MappingParameter parameter(MappingParameter parameterOriginal) {
        if (parameterOriginal != null) {
            String name = parameterOriginal.name();
            String description = parameterOriginal.description();
            ParameterTypeEnum type = parameterOriginal.type();
            boolean modifiable = parameterOriginal.modifiable();
            String defaultValue = parameterOriginal.defaultValue();
            return new_parameter(name, description, type, modifiable, defaultValue);
        }
        return null;
    }

    protected abstract MappingParameter new_parameter(
        String name, String description, ParameterTypeEnum type,
        boolean modifiable, String defaultValue
    );

    protected List<MappingPrivateDimension> schemaDimensions(MappingSchema mappingSchemaOriginal) {
        return dimensions(mappingSchemaOriginal.dimensions());
    }

    protected List<MappingPrivateDimension> dimensions(List<MappingPrivateDimension> dimensions) {
        return dimensions.stream().map(this::dimension).toList();
    }

    protected MappingPrivateDimension dimension(MappingPrivateDimension dimension) {
        if (dimension != null) {
            String name = dimensionName(dimension);
            DimensionTypeEnum type = dimensionType(dimension);
            String caption = dimensionCaption(dimension);
            String description = dimensionDescription(dimension);
            String foreignKey = dimensionForeignKey(dimension);
            List<MappingAnnotation> annotations = dimensionAnnotations(dimension);
            List<MappingHierarchy> hierarchies = dimensionHieraries(dimension);
            boolean visible = dimensionVisible(dimension);
            String usagePrefix = dimensionUsagePrefix(dimension);

            return new_PrivateDimension(name, type, caption, description, foreignKey,  annotations,
                hierarchies, visible, usagePrefix);
        }
        return null;

    }

    protected abstract MappingPrivateDimension new_PrivateDimension(
        String name, DimensionTypeEnum type, String caption,
        String description, String foreignKey, List<MappingAnnotation> annotations,
        List<MappingHierarchy> hierarchies, boolean visible, String usagePrefix
    );

    protected List<MappingHierarchy> dimensionHieraries(MappingPrivateDimension dimension) {
        return hierarchies(dimension.hierarchies());
    }

    protected String dimensionUsagePrefix(MappingPrivateDimension dimension) {
        return dimension.usagePrefix();
    }

    protected boolean dimensionVisible(MappingPrivateDimension dimension) {
        return dimension.visible();
    }

    protected String dimensionForeignKey(MappingPrivateDimension dimension) {
        return dimension.foreignKey();
    }

    protected String dimensionDescription(MappingPrivateDimension dimension) {
        return dimension.description();
    }

    protected String dimensionCaption(MappingPrivateDimension dimension) {
        return dimension.caption();
    }

    protected DimensionTypeEnum dimensionType(MappingPrivateDimension dimension) {
        return dimension.type();
    }

    protected String dimensionName(MappingPrivateDimension dimension) {
        return dimension.name();
    }

    protected List<MappingAnnotation> dimensionAnnotations(MappingPrivateDimension mappingPrivateDimensionOriginal) {
        return annotations(mappingPrivateDimensionOriginal.annotations());
    }

    protected List<MappingHierarchy> hierarchies(List<MappingHierarchy> hierarchies) {
        return hierarchies.stream().map(this::hierarchy).toList();
    }

    protected MappingHierarchy hierarchy(MappingHierarchy hierarchy) {
        if (hierarchy != null) {
            String name = hierarchyName(hierarchy);
            String caption = hierarchyCaption(hierarchy);
            String description = hierarchyDescription(hierarchy);
            List<MappingAnnotation> annotations = hierarchyAnnotations(hierarchy);
            List<MappingLevel> levels = hierarchyLevels(hierarchy);
            List<MappingMemberReaderParameter> memberReaderParameters = hierarchyMemberReaderParameters(hierarchy);
            boolean hasAll = hierarchyHasAll(hierarchy);
            String allMemberName = hierarchyAllMemerName(hierarchy);
            String allMemberCaption = hierarchyAllMemberCaption(hierarchy);
            String allLevelName = hierarchyAllLevelName(hierarchy);
            String primaryKey = hierarchyPrimaryKey(hierarchy);
            String primaryKeyTable = hierarchyPrimaryKeyTable(hierarchy);
            String defaultMember = hierarchyDefaultMember(hierarchy);
            String memberReaderClass = hierarchyMemberReaderClass(hierarchy);
            String uniqueKeyLevelName = hierarchyUniqueKeyLevelName(hierarchy);
            boolean visible = hierarchyVisible(hierarchy);
            String displayFolder = hierarchyDisplayFolder(hierarchy);
            MappingRelationOrJoin relation = hierarchyRelation(hierarchy);
            String origin = hierarchyOrigin(hierarchy);

            return new_Hierarchy(name, caption, description, annotations, levels, memberReaderParameters, hasAll,
                allMemberName, allMemberCaption, allLevelName, primaryKey, primaryKeyTable, defaultMember,
                memberReaderClass, uniqueKeyLevelName, visible, displayFolder, relation, origin);
        }
        return null;
    }

    protected abstract MappingHierarchy new_Hierarchy(
        String name, String caption, String description,
        List<MappingAnnotation> annotations, List<MappingLevel> levels,
        List<MappingMemberReaderParameter> memberReaderParameters, boolean hasAll, String allMemberName,
        String allMemberCaption, String allLevelName, String primaryKey, String primaryKeyTable,
        String defaultMember, String memberReaderClass, String uniqueKeyLevelName, boolean visible,
        String displayFolder, MappingRelationOrJoin relation, String origin
    );

    protected String hierarchyOrigin(MappingHierarchy hierarchy) {
        return hierarchy.origin();
    }

    protected String hierarchyDisplayFolder(MappingHierarchy hierarchy) {
        return hierarchy.displayFolder();
    }

    protected boolean hierarchyVisible(MappingHierarchy hierarchy) {
        return hierarchy.visible();
    }

    protected String hierarchyUniqueKeyLevelName(MappingHierarchy hierarchy) {
        return hierarchy.uniqueKeyLevelName();
    }

    protected String hierarchyMemberReaderClass(MappingHierarchy hierarchy) {
        return hierarchy.memberReaderClass();
    }

    protected String hierarchyDefaultMember(MappingHierarchy hierarchy) {
        return hierarchy.defaultMember();
    }

    protected String hierarchyPrimaryKeyTable(MappingHierarchy hierarchy) {
        return hierarchy.primaryKeyTable();
    }

    protected String hierarchyPrimaryKey(MappingHierarchy hierarchy) {
        return hierarchy.primaryKey();
    }

    protected String hierarchyAllLevelName(MappingHierarchy hierarchy) {
        return hierarchy.allLevelName();
    }

    protected String hierarchyAllMemberCaption(MappingHierarchy hierarchy) {
        return hierarchy.allMemberCaption();
    }

    protected String hierarchyAllMemerName(MappingHierarchy hierarchy) {
        return hierarchy.allMemberName();
    }

    protected boolean hierarchyHasAll(MappingHierarchy hierarchy) {
        return hierarchy.hasAll();
    }

    protected List<MappingMemberReaderParameter> hierarchyMemberReaderParameters(MappingHierarchy hierarchy) {
        return memberReaderParameters(hierarchy.memberReaderParameters());
    }

    protected List<MappingMemberReaderParameter> memberReaderParameters(
        List<MappingMemberReaderParameter> mappingMemberReaderParameters
    ) {
        return mappingMemberReaderParameters.stream().map(this::memberReaderParameter).toList();
    }

    protected MappingMemberReaderParameter memberReaderParameter(
        MappingMemberReaderParameter mappingMemberReaderParameter
    ) {
        if (mappingMemberReaderParameter  != null) {
            String name = mappingMemberReaderParameter.name();
            String value = mappingMemberReaderParameter.value();

            return new_MemberReaderParameter(name, value);
        }
        return null;
    }

    protected abstract MappingMemberReaderParameter new_MemberReaderParameter(String name, String value);

    protected List<MappingLevel> hierarchyLevels(MappingHierarchy hierarchy) {
        return levels(hierarchy.levels());
    }

    protected List<MappingLevel> levels(List<MappingLevel> levels) {
        if ( levels != null ) {
            return levels.stream().map(this::level).toList();
        }
        return null;
    }

    protected List<MappingAnnotation> hierarchyAnnotations(MappingHierarchy hierarchy) {
        return annotations(hierarchy.annotations());
    }

    protected String hierarchyDescription(MappingHierarchy hierarchy) {
        return hierarchy.description();
    }

    protected String hierarchyCaption(MappingHierarchy hierarchy) {
        return hierarchy.caption();
    }

    protected String hierarchyName(MappingHierarchy hierarchy) {
        return hierarchy.name();
    }

    protected List<MappingCube> schemaCubes(MappingSchema mappingSchemaOriginal) {

        return cubes(mappingSchemaOriginal.cubes());
    }

    protected List<MappingCube> cubes(List<MappingCube> cubes) {
        if (cubes != null) {
            return cubes.stream().map(this::cube).toList();
        }
        return null;
    }

    protected MappingCube cube(MappingCube cube) {
        if (cube != null) {
            String name = cubeName(cube);
            String caption = cubeCaption(cube);
            String description = cubeDescription(cube);
            String defaultMeasure = cubeDefaultMeasures(cube);
            List<MappingAnnotation> annotations = cubeAnnotations(cube);
            List<MappingCubeDimension> dimensionUsageOrDimensions = cubeDimensionUsageOrDimensions(cube);
            List<MappingMeasure> measures = cubeMeasures(cube);
            List<MappingCalculatedMember> calculatedMembers = cubeCalculatedMembers(cube);
            List<MappingNamedSet> namedSets = cubeNamedSets(cube);
            List<MappingDrillThroughAction> drillThroughActions = cubeDrillThroughActions(cube);
            Optional<MappingWritebackTable> writebackTable = cubeWritebackTable(cube);
            boolean enabled = cubeEnabled(cube);
            boolean cache = cubeCache(cube);
            boolean visible = cubeVisible(cube);
            MappingRelation fact = cubeFact(cube);
            List<MappingAction> actions = cubeActions(cube);
            List<MappingKpi> kpis = cubeKpis(cube);

            return new_Cube(name, caption, description, defaultMeasure, annotations, dimensionUsageOrDimensions, measures,
                calculatedMembers, namedSets, drillThroughActions, writebackTable, enabled, cache, visible, fact,
                actions, kpis);
        }
        return null;
    }

    protected abstract MappingCube new_Cube(
        String name, String caption, String description, String defaultMeasure,
        List<MappingAnnotation> annotations, List<MappingCubeDimension> dimensionUsageOrDimensions,
        List<MappingMeasure> measures, List<MappingCalculatedMember> calculatedMembers,
        List<MappingNamedSet> namedSets, List<MappingDrillThroughAction> drillThroughActions,
        Optional<MappingWritebackTable> writebackTable, boolean enabled, boolean cache, boolean visible,
        MappingRelation fact, List<MappingAction> actions, List<MappingKpi> kpis
    );

    protected List<MappingAction> cubeActions(MappingCube cube) {
        return actions(cube.actions());
    }

    protected List<MappingKpi> cubeKpis(MappingCube cube) {
        return kpis(cube.kpis());
    }

    protected List<MappingKpi> kpis(List<MappingKpi> kpis) {
        return kpis.stream().map(this::kpi).toList();
    }

    protected List<MappingAction> actions(List<MappingAction> actions) {
        return actions.stream().map(this::action).toList();
    }

    protected MappingKpi kpi(MappingKpi kpi) {
        if (kpi != null) {
            String name = kpiName(kpi);
            String description = kpiDescription(kpi);
            String caption = kpiCaption(kpi);
            List<MappingAnnotation> annotations = kpiAnnotations(kpi);
            String id = kpiId(kpi);
            List<MappingTranslation> translations = kpiTranslations(kpi);
            String displayFolder = kpiDisplayFolder(kpi);
            String associatedMeasureGroupID = kpiAssociatedMeasureGroupID(kpi);
            String value = kpiValue(kpi);
            String goal = kpiGoal(kpi);
            String status = kpiStatus(kpi);
            String trend = kpiTrend(kpi);
            String weight = kpiWeight(kpi);
            String trendGraphic = kpiTrendGraphic(kpi);
            String statusGraphic = kpiStatusGraphic(kpi);
            String currentTimeMember = kpiCurrentTimeMember(kpi);
            String parentKpiID = kpiParentKpiID(kpi);

            return new_MappingKpi(name, description, caption, annotations,
                id, translations, displayFolder, associatedMeasureGroupID,
                value, goal, status, trend, weight, trendGraphic, statusGraphic,
                currentTimeMember, parentKpiID);
        }
        return null;
    }

    protected abstract MappingKpi new_MappingKpi(
        String name,
        String description,
        String caption,
        List<MappingAnnotation> annotations,
        String id,
        List<MappingTranslation> translations,
        String displayFolder,
        String associatedMeasureGroupID,
        String value,
        String goal,
        String status,
        String trend,
        String weight,
        String trendGraphic,
        String statusGraphic,
        String currentTimeMember,
        String parentKpiID
    );

    private String kpiName(MappingKpi kpi) {
        return kpi.name();
    }

    private String kpiDescription(MappingKpi kpi) {
        return kpi.description();
    }

    private String kpiCaption(MappingKpi kpi) {
        return kpi.caption();
    }

    private List<MappingAnnotation> kpiAnnotations(MappingKpi kpi) {
        return annotations(kpi.annotations());
    }

    private String kpiId(MappingKpi kpi) {
        return kpi.id();
    }

    private String kpiAssociatedMeasureGroupID(MappingKpi kpi) {
        return kpi.associatedMeasureGroupID();
    }

    private String kpiValue(MappingKpi kpi) {
        return kpi.value();
    }

    private String kpiGoal(MappingKpi kpi) {
        return kpi.goal();
    }

    private String kpiStatus(MappingKpi kpi) {
        return kpi.status();
    }

    private String kpiTrend(MappingKpi kpi) {
        return kpi.trend();
    }

    private String kpiWeight(MappingKpi kpi) {
        return kpi.weight();
    }

    private String kpiTrendGraphic(MappingKpi kpi) {
        return kpi.trendGraphic();
    }

    private String kpiStatusGraphic(MappingKpi kpi) {
        return kpi.statusGraphic();
    }

    private String kpiCurrentTimeMember(MappingKpi kpi) {
        return kpi.currentTimeMember();
    }

    private String kpiParentKpiID(MappingKpi kpi) {
        return kpi.parentKpiID();
    }

    private String kpiDisplayFolder(MappingKpi kpi) {
        return kpi.displayFolder();
    }

    private List<MappingTranslation> kpiTranslations(MappingKpi kpi) {
        return translations(kpi.translations());
    }

    private List<MappingTranslation> translations(List<MappingTranslation> translations) {
        if (translations != null) {
            return translations.stream().map(this::translation).toList();
        }
        return null;
    }

    private MappingTranslation translation(MappingTranslation translation) {
        if  (translation != null) {
            long language = translationLanguage(translation);
            String caption = translationCaption(translation);
            String description = translationDescription(translation);
            String displayFolder = translationDisplayFolder(translation);
            List<MappingAnnotation> annotations = translationAnnotations(translation);
            return new_MappingTranslation(language,
                caption, description, displayFolder, annotations);
        }
        return null;
    }

    protected abstract MappingTranslation new_MappingTranslation(
        long language, String caption, String description, String displayFolder, List<MappingAnnotation> annotations);

    protected List<MappingAnnotation> translationAnnotations(MappingTranslation translation) {
        return annotations(translation.annotations());
    }

    protected String translationDescription(MappingTranslation translation){
           return translation.description();
    }

    protected String translationCaption(MappingTranslation translation){
        return translation.caption();
    }

    protected String translationDisplayFolder(MappingTranslation translation){
        return translation.displayFolder();
    }

    protected long translationLanguage(MappingTranslation translation){
        return translation.language();
    }


    protected MappingAction action(MappingAction action) {
        if (action != null) {
            String name = actionName(action);
            String caption = actionCaption(action);
            String description = actionDescription(action);
            List<MappingAnnotation> annotations = actionAnnotations(action);
            return new_MappingAction(name, caption, description, annotations);
        }
        return null;
    }

    protected abstract MappingAction new_MappingAction(
        String name, String caption, String description,
        List<MappingAnnotation> annotations
    );

    protected String actionDescription(MappingAction action) {
        return action.description();
    }

    protected String actionCaption(MappingAction action) {
        return action.caption();
    }

    protected String actionName(MappingAction action) {
        return action.name();
    }

    protected List<MappingAnnotation> actionAnnotations(MappingAction action) {
        return annotations(action.annotations());
    }

    protected boolean cubeVisible(MappingCube cube) {
        return cube.visible();
    }

    protected boolean cubeCache(MappingCube cube) {
        return cube.cache();
    }

    protected boolean cubeEnabled(MappingCube cube) {
        return cube.enabled();
    }

    protected List<MappingNamedSet> cubeNamedSets(MappingCube cube) {
        return namedSets(cube.namedSets());
    }

    protected List<MappingAnnotation> cubeAnnotations(MappingCube cube) {
        return annotations(cube.annotations());
    }

    protected String cubeDefaultMeasures(MappingCube cube) {
        return cube.defaultMeasure();
    }

    protected String cubeDescription(MappingCube cube) {
        return cube.description();
    }

    protected String cubeCaption(MappingCube cube) {
        return cube.caption();
    }

    protected String cubeName(MappingCube cube) {
        return cube.name();
    }

    protected List<MappingVirtualCube> schemaVirtualCubes(MappingSchema mappingSchemaOriginal) {

        return virtualCubes(mappingSchemaOriginal.virtualCubes());
    }

    protected List<MappingVirtualCube> virtualCubes(List<MappingVirtualCube> virtualCubes) {
        if (virtualCubes != null) {
            return virtualCubes.stream().map(this::virtualCube).toList();
        }
        return null;
    }

    protected List<MappingNamedSet> schemaNamedSets(MappingSchema mappingSchemaOriginal) {
        return namedSets(mappingSchemaOriginal.namedSets());
    }

    protected List<MappingNamedSet> namedSets(List<MappingNamedSet> mappingNamedSets) {
        if (mappingNamedSets != null) {
            return mappingNamedSets.stream().map(this::namedSets).toList();
        }
        return null;

    }

    protected List<MappingRole> schemaRoles(MappingSchema mappingSchemaOriginal) {
        return roles(mappingSchemaOriginal.roles());
    }

    protected List<MappingRole> roles(List<MappingRole> roles) {
        if (roles != null) {
            return roles.stream().map(this::role).toList();
        }
        return null;

    }

    protected MappingRole role(MappingRole role) {
        if (role != null) {
            List<MappingAnnotation> annotations = roleAnnotations(role);
            List<MappingSchemaGrant> schemaGrants = roleSchemaGrants(role);
            MappingUnion union = roleUnion(role);
            String name = roleName(role);

            return new_MappingRole(annotations, schemaGrants, union, name);
        }
        return null;
    }

    protected abstract MappingRole new_MappingRole(
        List<MappingAnnotation> annotations,
        List<MappingSchemaGrant> schemaGrants, MappingUnion union, String name
    );

    protected String roleName(MappingRole role) {
        return role.name();
    }

    protected MappingUnion roleUnion(MappingRole role) {
        return union(role.union());
    }

    protected List<MappingSchemaGrant> roleSchemaGrants(MappingRole role) {
        return schemaGrants(role.schemaGrants());
    }

    protected List<MappingSchemaGrant> schemaGrants(List<MappingSchemaGrant> grants) {
        if (grants != null) {
            return grants.stream().map(this::grant).toList();
        }
        return null;
    }

    protected List<MappingAnnotation> roleAnnotations(MappingRole role) {
        return annotations(role.annotations());
    }

    protected MappingVirtualCube virtualCube(MappingVirtualCube virtualCube) {
        if (virtualCube != null) {
            List<MappingAnnotation> annotations = virtualCubeAnnotations(virtualCube);
            List<MappingCubeUsage> cubeUsages = virtualCubeCubeUsage(virtualCube);
            List<MappingVirtualCubeDimension> virtualCubeDimensions = virtualCubeVirtualCubeDimension(virtualCube);
            List<MappingVirtualCubeMeasure> virtualCubeMeasures = virtualCubeVirtualCubeMeasure(virtualCube);
            List<MappingCalculatedMember> calculatedMembers = virtualCubeCalculatedMember(virtualCube);
            List<MappingNamedSet> namedSets = virtualCubeNamedSet(virtualCube);
            List<MappingKpi> kpis = virtualCubeKpis(virtualCube);
            boolean enabled = virtualCubeEnabled(virtualCube);
            String name = virtualCubeName(virtualCube);
            String defaultMeasure = virtualCubeDefaultMeasure(virtualCube);
            String caption = virtualCubeCaption(virtualCube);
            String description = virtualCubeDescription(virtualCube);
            boolean visible = virtualCubeVisible(virtualCube);

            return new_VirtualCube(
                name,
                caption,
                description,
                defaultMeasure,
                enabled,
                annotations,
                cubeUsages,
                virtualCubeDimensions,
                virtualCubeMeasures,
                calculatedMembers,
                namedSets,
                kpis,
                visible);
        }
        return null;
    }

    protected boolean virtualCubeVisible(MappingVirtualCube virtualCube) {
        return virtualCube.visible();
    }

    protected String virtualCubeDescription(MappingVirtualCube virtualCube) {
        return virtualCube.description();
    }

    protected String virtualCubeCaption(MappingVirtualCube virtualCube) {
        return virtualCube.caption();
    }

    protected String virtualCubeDefaultMeasure(MappingVirtualCube virtualCube) {
        return virtualCube.defaultMeasure();
    }

    protected String virtualCubeName(MappingVirtualCube virtualCube) {
        return virtualCube.name();
    }

    protected boolean virtualCubeEnabled(MappingVirtualCube virtualCube) {
        return virtualCube.enabled();
    }

    protected List<MappingNamedSet> virtualCubeNamedSet(MappingVirtualCube virtualCube) {
        return namedSets(virtualCube.namedSets());
    }

    protected List<MappingKpi> virtualCubeKpis(MappingVirtualCube virtualCube) {
        return kpis(virtualCube.kpis());
    }

    protected List<MappingCalculatedMember> virtualCubeCalculatedMember(MappingVirtualCube virtualCube) {
        return calculatedMembers(virtualCube.calculatedMembers());
    }

    private List<MappingCalculatedMember> calculatedMembers(List<MappingCalculatedMember> calculatedMembers) {
        if (calculatedMembers != null) {
            return calculatedMembers.stream().map(this::calculatedMember).toList();
        }
        return null;
    }

    private MappingCalculatedMember calculatedMember(MappingCalculatedMember calculatedMember) {
        if (calculatedMember != null) {
            String name = calculatedMemberName(calculatedMember);
            String formatString = calculatedMemberFormatString(calculatedMember);
            String caption = calculatedMemberCaption(calculatedMember);
            String description = calculatedMemberDescription(calculatedMember);
            String dimension = calculatedMemberDimension(calculatedMember);
            boolean visible = calculatedMemberVisible(calculatedMember);
            String displayFolder = calculatedMemberDisplayFolder(calculatedMember);
            List<MappingAnnotation> annotations = calculatedMemberAnnotations(calculatedMember);
            String formula = calculatedMemberFormula(calculatedMember);
            List<MappingCalculatedMemberProperty> calculatedMemberProperties =
                calculatedMemberCalculatedMemberProperties(calculatedMember);
            String hierarchy = calculatedMemberHierarchy(calculatedMember);
            String parent = calculatedMemberParent(calculatedMember);
            MappingCellFormatter cellFormatter = calculatedMemberCellFormatter(calculatedMember);
            MappingFormula formulaElement = calculatedMemberFormulaElement(calculatedMember);

            return new_CalculatedMember(
                name,
                formatString,
                caption,
                description,
                dimension,
                visible,
                displayFolder,
                annotations,
                formula,
                calculatedMemberProperties,
                hierarchy,
                parent,
                cellFormatter,
                formulaElement
            );
        }
        return null;
    }

    protected MappingFormula calculatedMemberFormulaElement(MappingCalculatedMember calculatedMember) {
        return formula(calculatedMember.formulaElement());
    }

    protected abstract MappingFormula new_Formula(String cdata);

    protected String formulaCdata(MappingFormula formula) {
        return formula.cdata();
    }

    protected MappingCellFormatter calculatedMemberCellFormatter(MappingCalculatedMember calculatedMember) {
        return cellFormatter(calculatedMember.cellFormatter());
    }

    protected MappingCellFormatter cellFormatter(MappingCellFormatter cellFormatter) {
    	if (cellFormatter != null) {
    		String className = cellFormatterClassName(cellFormatter);
    		MappingScript script = cellFormatterScript(cellFormatter);
    		return new_CellFormatter(className, script);
    	}
    	return null;
    }

    protected abstract MappingCellFormatter new_CellFormatter(String className, MappingScript script);

    protected MappingScript cellFormatterScript(MappingCellFormatter cellFormatter) {
        return script(cellFormatter.script());
    }

    protected MappingScript script(MappingScript script) {
    	if (script != null) {
    		String language = scriptLanguage(script);
    		String cdata = scriptCdata(script);
    		return new_Script(language, cdata);
    	}
    	return null;
    }

    protected abstract MappingScript new_Script(String language, String cdata);

    protected String scriptCdata(MappingScript script) {
        return script.cdata();
    }

    protected String scriptLanguage(MappingScript script) {
        return script.language();
    }

    protected String cellFormatterClassName(MappingCellFormatter cellFormatter) {
        return cellFormatter.className();
    }

    protected String calculatedMemberParent(MappingCalculatedMember calculatedMember) {
        return calculatedMember.parent();
    }

    protected String calculatedMemberHierarchy(MappingCalculatedMember calculatedMember) {
        return calculatedMember.hierarchy();
    }

    protected List<MappingCalculatedMemberProperty> calculatedMemberCalculatedMemberProperties(
        MappingCalculatedMember calculatedMember
    ) {
        return calculatedMemberProperties(calculatedMember.calculatedMemberProperties());
    }

    protected List<MappingCalculatedMemberProperty> calculatedMemberProperties(List<MappingCalculatedMemberProperty> calculatedMemberProperties) {
        if (calculatedMemberProperties != null) {
            return calculatedMemberProperties.stream().map(this::calculatedMemberProperty).toList();
        }
        return null;
    }

    private MappingCalculatedMemberProperty calculatedMemberProperty(MappingCalculatedMemberProperty calculatedMemberProperty) {
        if (calculatedMemberProperty != null) {
            String name = calculatedMemberPropertyName(calculatedMemberProperty);
            String caption = calculatedMemberPropertyCaption(calculatedMemberProperty);
            String description = calculatedMemberPropertyDescription(calculatedMemberProperty);
            String expression = calculatedMemberPropertyExpression(calculatedMemberProperty);
            String value = calculatedMemberPropertyValue(calculatedMemberProperty);
            return new_CalculatedMemberProperty(
                name,
                caption,
                description,
                expression,
                value
            );
        }
        return null;
    }

    protected String calculatedMemberPropertyValue(MappingCalculatedMemberProperty calculatedMemberProperty) {
        return calculatedMemberProperty.value();
    }

    protected String calculatedMemberPropertyExpression(MappingCalculatedMemberProperty calculatedMemberProperty) {
        return calculatedMemberProperty.expression();
    }

    protected String calculatedMemberPropertyDescription(MappingCalculatedMemberProperty calculatedMemberProperty) {
        return calculatedMemberProperty.description();
    }

    protected String calculatedMemberPropertyCaption(MappingCalculatedMemberProperty calculatedMemberProperty) {
        return calculatedMemberProperty.caption();
    }

    protected String calculatedMemberPropertyName(MappingCalculatedMemberProperty calculatedMemberProperty) {
        return calculatedMemberProperty.name();
    }

    protected abstract MappingCalculatedMemberProperty new_CalculatedMemberProperty(
        String name,
        String caption,
        String description,
        String expression,
        String value
    );

    protected String calculatedMemberFormula(MappingCalculatedMember calculatedMember) {
        return calculatedMember.formula();
    }

    protected List<MappingAnnotation> calculatedMemberAnnotations(MappingCalculatedMember calculatedMember) {
        return annotations(calculatedMember.annotations());
    }

    protected String calculatedMemberDisplayFolder(MappingCalculatedMember calculatedMember) {
        return calculatedMember.displayFolder();
    }

    protected boolean calculatedMemberVisible(MappingCalculatedMember calculatedMember) {
        return calculatedMember.visible();
    }

    protected String calculatedMemberDimension(MappingCalculatedMember calculatedMember) {
        return calculatedMember.dimension();
    }

    protected String calculatedMemberDescription(MappingCalculatedMember calculatedMember) {
        return calculatedMember.description();
    }

    protected String calculatedMemberCaption(MappingCalculatedMember calculatedMember) {
        return calculatedMember.caption();
    }

    protected String calculatedMemberFormatString(MappingCalculatedMember calculatedMember) {
        return calculatedMember.formatString();
    }

    protected String calculatedMemberName(MappingCalculatedMember calculatedMember) {
        return calculatedMember.name();
    }

    protected abstract MappingCalculatedMember new_CalculatedMember(
        String name,
        String formatString,
        String caption,
        String description,
        String dimension,
        boolean visible,
        String displayFolder,
        List<MappingAnnotation> annotations,
        String formula,
        List<MappingCalculatedMemberProperty> calculatedMemberProperties,
        String hierarchy,
        String parent,
        MappingCellFormatter cellFormatter,
        MappingFormula formulaElement
    );

    protected List<MappingVirtualCubeMeasure> virtualCubeVirtualCubeMeasure(MappingVirtualCube virtualCube) {
        return virtualCubeMeasures(virtualCube.virtualCubeMeasures());
    }

    protected List<MappingVirtualCubeMeasure> virtualCubeMeasures(List<MappingVirtualCubeMeasure> virtualCubeMeasures) {
        if (virtualCubeMeasures != null) {
            return virtualCubeMeasures.stream().map(this::virtualCubeMeasure).toList();
        }
        return null;
    }

    private MappingVirtualCubeMeasure virtualCubeMeasure(MappingVirtualCubeMeasure virtualCubeMeasure) {
        if (virtualCubeMeasure != null) {
            String name = virtualCubeMeasureName(virtualCubeMeasure);
            String cubeName = virtualCubeMeasureCubeName(virtualCubeMeasure);
            boolean visible = virtualCubeMeasureVisible(virtualCubeMeasure);
            List<MappingAnnotation> annotations = virtualCubeMeasureAnnotation(virtualCubeMeasure);

            return new_VirtualCubeMeasure(name, cubeName, visible, annotations);
        }
        return null;
    }

    protected List<MappingAnnotation> virtualCubeMeasureAnnotation(MappingVirtualCubeMeasure virtualCubeMeasure) {
        return annotations(virtualCubeMeasure.annotations());
    }

    protected boolean virtualCubeMeasureVisible(MappingVirtualCubeMeasure virtualCubeMeasure) {
        return virtualCubeMeasure.visible();
    }

    protected String virtualCubeMeasureCubeName(MappingVirtualCubeMeasure virtualCubeMeasure) {
        return virtualCubeMeasure.cubeName();
    }

    protected String virtualCubeMeasureName(MappingVirtualCubeMeasure virtualCubeMeasure) {
        return virtualCubeMeasure.name();
    }

    protected abstract MappingVirtualCubeMeasure new_VirtualCubeMeasure(
        String name,
        String cubeName,
        boolean visible,
        List<MappingAnnotation> annotations
    );

    protected List<MappingVirtualCubeDimension> virtualCubeVirtualCubeDimension(MappingVirtualCube virtualCube) {
        return virtualCubeDimensions(virtualCube.virtualCubeDimensions());
    }

    protected List<MappingVirtualCubeDimension> virtualCubeDimensions(List<MappingVirtualCubeDimension> virtualCubeDimensions) {
        if (virtualCubeDimensions != null) {
            return virtualCubeDimensions.stream().map(this::cubeDimension).toList();
        }
        return null;
    }

    private MappingVirtualCubeDimension cubeDimension(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        if (mappingVirtualCubeDimension != null) {
            List<MappingAnnotation> annotations = virtualCubeDimensionAnnotations(mappingVirtualCubeDimension);
            String name = virtualCubeDimensionName(mappingVirtualCubeDimension);
            String foreignKey = virtualCubeDimensionForeignKey(mappingVirtualCubeDimension);
            String caption = virtualCubeDimensionCaption(mappingVirtualCubeDimension);
            boolean visible = virtualCubeDimensionVisible(mappingVirtualCubeDimension);
            String description = virtualCubeDimensionDescription(mappingVirtualCubeDimension);
            String cubeName = virtualCubeDimensionCubeName(mappingVirtualCubeDimension);

            return new_VirtualCubeDimension(
                name,
                cubeName,
                annotations,
                foreignKey,
                caption,
                visible,
                description);
        }
        return null;
    }

    protected String virtualCubeDimensionCubeName(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return mappingVirtualCubeDimension.cubeName();
    }

    protected String virtualCubeDimensionDescription(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return mappingVirtualCubeDimension.description();
    }

    protected boolean virtualCubeDimensionVisible(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return mappingVirtualCubeDimension.visible();
    }

    protected String virtualCubeDimensionCaption(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return mappingVirtualCubeDimension.caption();
    }

    protected String virtualCubeDimensionForeignKey(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return mappingVirtualCubeDimension.foreignKey();
    }

    protected String virtualCubeDimensionName(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return mappingVirtualCubeDimension.name();
    }

    protected List<MappingAnnotation> virtualCubeDimensionAnnotations(MappingVirtualCubeDimension mappingVirtualCubeDimension) {
        return annotations(mappingVirtualCubeDimension.annotations());
    }

    protected abstract MappingVirtualCubeDimension new_VirtualCubeDimension(
        String name,
        String cubeName,
        List<MappingAnnotation> annotations,
        String foreignKey,
        String caption,
        boolean visible,
        String description
    );

    protected List<MappingCubeUsage> virtualCubeCubeUsage(MappingVirtualCube virtualCube) {
        return cubeUsages(virtualCube.cubeUsages());
    }

    protected List<MappingCubeUsage> cubeUsages(List<MappingCubeUsage> cubeUsages) {
        if (cubeUsages != null) {
            return cubeUsages.stream().map(this::cubeUsage).toList();
        }
        return null;
    }

    private MappingCubeUsage cubeUsage(MappingCubeUsage mappingCubeUsage) {
        if (mappingCubeUsage != null) {
            String cubeName = cubeUsageCubeName(mappingCubeUsage);
            boolean ignoreUnrelatedDimensions = cubeUsageIgnoreUnrelatedDimensions(mappingCubeUsage);
            return new_CubeUsage(cubeName, ignoreUnrelatedDimensions);
        }
        return null;
    }

    protected abstract MappingCubeUsage new_CubeUsage(String cubeName, boolean ignoreUnrelatedDimensions);

    protected String cubeUsageCubeName(MappingCubeUsage mappingCubeUsage) {
        return mappingCubeUsage.cubeName();
    }

    protected boolean cubeUsageIgnoreUnrelatedDimensions(MappingCubeUsage mappingCubeUsage) {
        return mappingCubeUsage.ignoreUnrelatedDimensions();
    }

    protected List<MappingAnnotation> virtualCubeAnnotations(MappingVirtualCube virtualCube) {
        return annotations(virtualCube.annotations());
    }

    protected abstract MappingVirtualCube new_VirtualCube(
        String name,
        String caption,
        String description,
        String defaultMeasure,
        boolean enabled,
        List<MappingAnnotation> annotations,
        List<MappingCubeUsage> cubeUsages,
        List<MappingVirtualCubeDimension> virtualCubeDimensions,
        List<MappingVirtualCubeMeasure> virtualCubeMeasures,
        List<MappingCalculatedMember> calculatedMembers,
        List<MappingNamedSet> namedSets,
        List<MappingKpi> kpis,
        boolean visible
    );

    protected MappingNamedSet namedSets(MappingNamedSet namedSet) {
        if (namedSet != null) {
            List<MappingAnnotation> annotations = namedSetAnnotations(namedSet);
            String formula = namedSetFormula(namedSet);
            String name = namedSetName(namedSet);
            String caption = namedSetCaption(namedSet);
            String description = namedSetDescription(namedSet);
            String displayFolder = namedSetDisplayFolder(namedSet);
            MappingFormula formulaElement = namedSetFormulaElement(namedSet);

            return new_NamedSet(
                name,
                caption,
                description,
                formula,
                annotations,
                displayFolder,
                formulaElement
            );
        }
        return null;

    }

    protected List<MappingAnnotation> namedSetAnnotations(MappingNamedSet namedSet) {
        return annotations(namedSet.annotations());
    }

    protected MappingFormula namedSetFormulaElement(MappingNamedSet namedSet) {
        return formula(namedSet.formulaElement());
    }

    protected MappingFormula formula(MappingFormula formula) {
    	if (formula != null) {
    		String cdata = formulaCdata(formula);
    		return new_Formula(cdata);
    	}
    	return null;
    }

    protected String namedSetDisplayFolder(MappingNamedSet namedSet) {
        return namedSet.displayFolder();
    }

    protected String namedSetDescription(MappingNamedSet namedSet) {
        return namedSet.description();
    }

    protected String namedSetCaption(MappingNamedSet namedSet) {
        return namedSet.caption();
    }

    protected String namedSetName(MappingNamedSet namedSet) {
        return namedSet.name();
    }

    protected String namedSetFormula(MappingNamedSet namedSet) {
        return namedSet.formula();
    }

    protected abstract MappingNamedSet new_NamedSet(
        String name,
        String caption,
        String description,
        String formula,
        List<MappingAnnotation> annotations,
        String displayFolder,
        MappingFormula formulaElement
    );

    protected MappingUnion union(MappingUnion union) {
    	if (union != null) {
    		List<MappingRoleUsage> roleUsages = unionRoleUsages(union);
    		return new_Union(roleUsages);
    	}
    	return null;
    }

    protected abstract MappingUnion new_Union(List<MappingRoleUsage> roleUsages);

    protected List<MappingRoleUsage> unionRoleUsages(MappingUnion union) {
        return roleUsages(union.roleUsages());
    }

    protected List<MappingRoleUsage> roleUsages(List<MappingRoleUsage> roleUsages) {
        if (roleUsages != null) {
            return roleUsages.stream().map(this::roleUsage).toList();
        }
        return null;
    }

    private MappingRoleUsage roleUsage(MappingRoleUsage roleUsage) {
        if (roleUsage != null) {
            String roleName = roleUsageRoleName(roleUsage);
            return new_RoleUsage(roleName);
        }
        return null;
    }

    protected String roleUsageRoleName(MappingRoleUsage roleUsage) {
        return roleUsage.roleName();
    }

    protected abstract MappingRoleUsage new_RoleUsage(String roleName);

    protected MappingSchemaGrant grant(MappingSchemaGrant schemaGrant) {
        if (schemaGrant != null) {
            List<MappingCubeGrant> schemaGrantCubeGrants = schemaGrantCubeGrants(schemaGrant);
            AccessEnum access = schemaGrantAccess(schemaGrant);
            return new_SchemaGrant(schemaGrantCubeGrants, access);
        }
        return null;
    }

    protected List<MappingCubeGrant> schemaGrantCubeGrants(MappingSchemaGrant schemaGrant) {
        return cubeGrants(schemaGrant.cubeGrants());
    }

    protected List<MappingCubeGrant> cubeGrants(List<MappingCubeGrant> cubeGrants) {
        if (cubeGrants != null) {
            return cubeGrants.stream().map(this::cubeGrant).toList();
        }
        return null;
    }

    private MappingCubeGrant cubeGrant(MappingCubeGrant cubeGrant) {
        if (cubeGrant != null) {
            List<MappingDimensionGrant> dimensionGrants = cubeGrantDimensionGrants(cubeGrant);
            List<MappingHierarchyGrant> hierarchyGrants = cubeGrantHierarchyGrants(cubeGrant);
            String cube = cubeGrantCube(cubeGrant);
            String access = cubeGrantAccess(cubeGrant);

            return new_CubeGrant(
                cube,
                access,
                dimensionGrants,
                hierarchyGrants
            );
        }
        return null;
    }

    protected String cubeGrantAccess(MappingCubeGrant cubeGrant) {
        return cubeGrant.access();
    }

    protected String cubeGrantCube(MappingCubeGrant cubeGrant) {
        return cubeGrant.cube();
    }

    protected List<MappingHierarchyGrant> cubeGrantHierarchyGrants(MappingCubeGrant cubeGrant) {
        return hierarchyGrants(cubeGrant.hierarchyGrants());
    }

    protected List<MappingHierarchyGrant> hierarchyGrants(List<MappingHierarchyGrant> hierarchyGrants) {
        if (hierarchyGrants != null) {
            return hierarchyGrants.stream().map(this::hierarchyGrant).toList();
        }
        return null;
    }

    private MappingHierarchyGrant hierarchyGrant(MappingHierarchyGrant hierarchyGrant) {
        if (hierarchyGrant != null) {
            List<MappingMemberGrant> memberGrants = hierarchyGrantMemberGrants(hierarchyGrant);
            String hierarchy = hierarchyGrantHierarchy(hierarchyGrant);
            AccessEnum access = hierarchyGrantAccess(hierarchyGrant);
            String topLevel = hierarchyGrantTopLevel(hierarchyGrant);
            String bottomLevel = hierarchyGrantBottomLevel(hierarchyGrant);
            String rollupPolicy = hierarchyGrantRollupPolicy(hierarchyGrant);

            return new_HierarchyGrant(hierarchy,
                access,
                topLevel,
                bottomLevel,
                rollupPolicy,
                memberGrants);
        }
        return null;
    }

    protected String hierarchyGrantRollupPolicy(MappingHierarchyGrant hierarchyGrant) {
        return hierarchyGrant.rollupPolicy() == null ? "full" : hierarchyGrant.rollupPolicy();
    }

    protected String hierarchyGrantBottomLevel(MappingHierarchyGrant hierarchyGrant) {
        return hierarchyGrant.bottomLevel();
    }

    protected String hierarchyGrantTopLevel(MappingHierarchyGrant hierarchyGrant) {
        return hierarchyGrant.topLevel();
    }

    protected AccessEnum hierarchyGrantAccess(MappingHierarchyGrant hierarchyGrant) {
        return hierarchyGrant.access();
    }

    protected String hierarchyGrantHierarchy(MappingHierarchyGrant hierarchyGrant) {
        return hierarchyGrant.hierarchy();
    }

    protected List<MappingMemberGrant> hierarchyGrantMemberGrants(MappingHierarchyGrant hierarchyGrant) {
        return memberGrants(hierarchyGrant.memberGrants());
    }

    protected List<MappingMemberGrant> memberGrants(List<MappingMemberGrant> memberGrants) {
        if (memberGrants != null) {
            return memberGrants.stream().map(this::memberGrant).toList();
        }
        return null;
    }

    private MappingMemberGrant memberGrant(MappingMemberGrant memberGrant) {
        if (memberGrant != null) {
            String member = memberGrantMember(memberGrant);
            MemberGrantAccessEnum access = memberGrantAccess(memberGrant);
            return new_MemberGrant(member, access);
        }
        return null;
    }

    protected MemberGrantAccessEnum memberGrantAccess(MappingMemberGrant memberGrant) {
        return memberGrant.access();
    }

    protected String memberGrantMember(MappingMemberGrant memberGrant) {
        return memberGrant.member();
    }

    protected abstract MappingMemberGrant new_MemberGrant(String member, MemberGrantAccessEnum access);

    protected abstract MappingHierarchyGrant new_HierarchyGrant(
        String hierarchy,
        AccessEnum access,
        String topLevel,
        String bottomLevel,
        String rollupPolicy,
        List<MappingMemberGrant> memberGrants
    );

    protected List<MappingDimensionGrant> cubeGrantDimensionGrants(MappingCubeGrant cubeGrant) {
        return dimensionGrants(cubeGrant.dimensionGrants());
    }

    protected List<MappingDimensionGrant> dimensionGrants(List<MappingDimensionGrant> dimensionGrants) {
        if (dimensionGrants != null) {
            return dimensionGrants.stream().map(this::dimensionGrant).toList();
        }
        return null;
    }

    private MappingDimensionGrant dimensionGrant(MappingDimensionGrant dimensionGrant) {
        if (dimensionGrant != null) {
            AccessEnum access = dimensionGrantAccess(dimensionGrant);
            String dimension = dimensionGrantDimension(dimensionGrant);
            return new_DimensionGrant(access, dimension);
        }
        return null;
    }

    protected String dimensionGrantDimension(MappingDimensionGrant dimensionGrant) {
        return dimensionGrant.dimension();
    }

    protected AccessEnum dimensionGrantAccess(MappingDimensionGrant dimensionGrant) {
        return dimensionGrant.access();
    }

    protected abstract MappingDimensionGrant new_DimensionGrant(AccessEnum access, String dimension);

    protected abstract MappingCubeGrant new_CubeGrant(
        String cube,
        String access,
        List<MappingDimensionGrant> dimensionGrants,
        List<MappingHierarchyGrant> hierarchyGrants
    );

    protected AccessEnum schemaGrantAccess(MappingSchemaGrant schemaGrant) {
        return schemaGrant.access();
    }

    protected abstract MappingSchemaGrant new_SchemaGrant(
        List<MappingCubeGrant> schemaGrantCubeGrants,
        AccessEnum access
    );

    protected MappingRelation cubeFact(MappingCube cube) {
        return relation(cube.fact());
    }

    protected MappingRelation relation(MappingRelation relation) {
        if (relation != null) {
            String alias = relationAlias(relation);
            if (relation instanceof MappingInlineTable inlineTable) {
                List<MappingColumnDef> columnDefs = inlineTableColumnDefs(inlineTable);
                List<MappingRow> rows = inlineTableRows(inlineTable);
                return new_InlineTable(columnDefs, rows, alias);
            }
            if (relation instanceof MappingTable table) {

                MappingSQL sql = tableSql(table);
                List<MappingAggExclude> aggExcludes = tableAggExcludes(table);
                List<MappingAggTable> aggTables = tableAggTables(table);
                List<MappingHint> hints = tableHints(table);
                String name = tableName(table);
                String schema = tableSchema(table);
                return new_Table(schema, name, alias, hints, sql, aggExcludes, aggTables);
            }
            if (relation instanceof MappingView view) {
                List<MappingSQL> sqls = viewSqls(view);
                return new_View(alias, sqls);
            }
        }
        return null;
    }

    protected List<MappingSQL> viewSqls(MappingView view) {
        return sqls(view.sqls());
    }

    protected List<MappingSQL> sqls(List<MappingSQL> sqls) {
        if (sqls != null) {
            return sqls.stream().map(this::sql).toList();
        }
        return null;
    }

    protected String tableSchema(MappingTable table) {
        return table.schema();
    }

    protected String tableName(MappingTable table) {
        return table.name();
    }

    protected List<MappingHint> tableHints(MappingTable table) {
        return hints(table.hints());
    }

    protected List<MappingHint> hints(List<MappingHint> hints) {
        if (hints != null) {
            return hints.stream().map(this::hint).toList();
        }
        return null;
    }

    private MappingHint hint(MappingHint hint) {
        if (hint != null) {
            String content = hintContent(hint);
            String type = hintType(hint);
            return new_Hint(content, type);
        }
        return null;
    }

    protected String hintType(MappingHint hint) {
        return hint.type();
    }

    protected String hintContent(MappingHint hint) {
        return hint.content();
    }

    protected abstract MappingHint new_Hint(String content, String type);

    protected List<MappingAggTable> tableAggTables(MappingTable table) {
        return aggTables(table.aggTables());
    }

    protected List<MappingAggTable> aggTables(List<MappingAggTable> aggTables) {
        if (aggTables != null) {
            return aggTables.stream().map(this::aggTable).toList();
        }
        return null;
    }

    private MappingAggTable aggTable(MappingAggTable aggTable) {
        if (aggTable != null) {
            MappingAggColumnName aggFactCount = aggTableAggFactCount(aggTable);
            List<MappingAggColumnName> aggIgnoreColumns = aggTableAggIgnoreColumns(aggTable);
            List<MappingAggForeignKey> aggForeignKeys = aggTableAggForeignKeys(aggTable);
            List<MappingAggMeasure> aggMeasures = aggTableAggMeasures(aggTable);
            List<MappingAggLevel> aggLevels = aggTableAggLevels(aggTable);
            boolean ignorecase = aggTableIgnorecase(aggTable);
            List<MappingAggMeasureFactCount> measuresFactCounts = aggTableMeasuresFactCounts(aggTable);
            if (aggTable instanceof MappingAggName aggName) {
                String name = aggNameName(aggName);
                String approxRowCount = aggNameApproxRowCount(aggName);
                return new_AggName(
                    name,
                    aggFactCount,
                    aggMeasures,
                    aggIgnoreColumns,
                    aggForeignKeys,
                    aggLevels,
                    ignorecase,
                    measuresFactCounts,
                    approxRowCount
                );
            }
            if (aggTable instanceof MappingAggPattern aggPattern) {
                List<MappingAggExclude> aggExcludes = aggPatternAggExcludes(aggPattern);
                String pattern = aggPatternPattern(aggPattern);
                return new_AggPattern(pattern,
                    aggFactCount,
                    aggIgnoreColumns,
                    aggForeignKeys,
                    aggMeasures,
                    aggLevels,
                    aggExcludes,
                    ignorecase,
                    measuresFactCounts);
            }
        }
        return null;
    }

    protected String aggNameApproxRowCount(MappingAggName aggName) {
        return aggName.approxRowCount();
    }

    protected String aggNameName(MappingAggName aggName) {
        return aggName.name();
    }

    ;

    protected List<MappingAggMeasureFactCount> aggTableMeasuresFactCounts(MappingAggTable aggTable) {
        return measuresFactCounts(aggTable.measuresFactCounts());
    }

    protected List<MappingAggMeasureFactCount> measuresFactCounts(List<MappingAggMeasureFactCount> aggMeasuresFactCounts) {
        if (aggMeasuresFactCounts != null) {
            return aggMeasuresFactCounts.stream().map(this::aggMeasuresFactCount).toList();
        }
        return null;
    }

    private MappingAggMeasureFactCount aggMeasuresFactCount(MappingAggMeasureFactCount mappingAggMeasureFactCount) {
        if (mappingAggMeasureFactCount != null) {
            String factColumn = mappingAggMeasureFactCountFactColumn(mappingAggMeasureFactCount);
            String column = mappingAggMeasureFactCount.column();
            return new_AggMeasureFactCount(factColumn, column);
        }
        return null;
    }

    protected abstract MappingAggMeasureFactCount new_AggMeasureFactCount(String factColumn, String column);

    protected String mappingAggMeasureFactCountFactColumn(MappingAggMeasureFactCount mappingAggMeasureFactCount) {
        return mappingAggMeasureFactCount.factColumn();
    }

    protected boolean aggTableIgnorecase(MappingAggTable aggTable) {
        return aggTable.ignorecase();
    }

    protected List<MappingAggLevel> aggTableAggLevels(MappingAggTable aggTable) {
        return aggLevels(aggTable.aggLevels());
    }

    protected List<MappingAggLevel> aggLevels(List<MappingAggLevel> aggLevels) {
        if (aggLevels != null) {
            return aggLevels.stream().map(this::aggLevel).toList();
        }
        return null;
    }

    private MappingAggLevel aggLevel(MappingAggLevel aggLevel) {
        if (aggLevel != null) {
            String column = aggLevelColumn(aggLevel);
            String name = aggLevelName(aggLevel);
            String ordinalColumn = aggLevelOrdinalColumn(aggLevel);
            String captionColumn = aggLevelCaptionColumn(aggLevel);
            String nameColumn = aggLevelNameColumn(aggLevel);
            Boolean collapsed = aggLevelCollapsed(aggLevel);
            List<MappingAggLevelProperty> properties = aggLevelProperties(aggLevel);
            return new_AggLevel(
                column,
                name,
                ordinalColumn,
                nameColumn,
                captionColumn,
                collapsed,
                properties
            );
        }
        return null;
    }

    protected List<MappingAggLevelProperty> aggLevelProperties(MappingAggLevel aggLevel) {
        return aggLevelProperties(aggLevel.properties());
    }

    protected List<MappingAggLevelProperty> aggLevelProperties(List<MappingAggLevelProperty> properties) {
        if (properties != null) {
            return properties.stream().map(this::aggLevelProperty).toList();
        }
        return null;
    }

    private MappingAggLevelProperty aggLevelProperty(MappingAggLevelProperty aggLevelProperty) {
        if (aggLevelProperty != null) {
            String name = aggLevelPropertyName(aggLevelProperty);
            String column = aggLevelPropertyColumn(aggLevelProperty);
            return new_AggLevelProperty(name, column);
        }
        return null;
    }

    protected String aggLevelPropertyColumn(MappingAggLevelProperty aggLevelProperty) {
        return aggLevelProperty.column();
    }

    protected String aggLevelPropertyName(MappingAggLevelProperty aggLevelProperty) {
        return aggLevelProperty.name();
    }

    protected abstract MappingAggLevelProperty new_AggLevelProperty(String name, String column);

    protected Boolean aggLevelCollapsed(MappingAggLevel aggLevel) {
        return aggLevel.collapsed();
    }

    protected String aggLevelNameColumn(MappingAggLevel aggLevel) {
        return aggLevel.nameColumn();
    }

    protected String aggLevelCaptionColumn(MappingAggLevel aggLevel) {
        return aggLevel.captionColumn();
    }

    protected String aggLevelOrdinalColumn(MappingAggLevel aggLevel) {
        return aggLevel.column();
    }

    protected String aggLevelName(MappingAggLevel aggLevel) {
        return aggLevel.name();
    }

    protected String aggLevelColumn(MappingAggLevel aggLevel) {
        return aggLevel.column();
    }

    protected abstract MappingAggLevel new_AggLevel(
        String column,
        String name,
        String ordinalColumn,
        String nameColumn,
        String captionColumn,
        Boolean collapsed,
        List<MappingAggLevelProperty> properties
    );

    protected List<MappingAggMeasure> aggTableAggMeasures(MappingAggTable aggTable) {
        return aggMeasures(aggTable.aggMeasures());
    }

    protected List<MappingAggMeasure> aggMeasures(List<MappingAggMeasure> aggMeasures) {
        return aggMeasures.stream().map(this::aggMeasure).toList();
    }

    private MappingAggMeasure aggMeasure(MappingAggMeasure aggMeasure) {
        if (aggMeasure != null) {
            String column = aggMeasureColumn(aggMeasure);
            String name = aggMeasureName(aggMeasure);
            String rollupType = aggMeasureRollupType(aggMeasure);
            return new_AggMeasure(column, name, rollupType);
        }
        return null;
    }

    protected abstract MappingAggMeasure new_AggMeasure(
        String column,
        String name,
        String rollupType
    );

    protected String aggMeasureRollupType(MappingAggMeasure aggMeasure) {
        return aggMeasure.rollupType();
    }

    protected String aggMeasureName(MappingAggMeasure aggMeasure) {
        return aggMeasure.name();
    }

    ;

    protected String aggMeasureColumn(MappingAggMeasure aggMeasure) {
        return aggMeasure.column();
    }

    protected List<MappingAggForeignKey> aggTableAggForeignKeys(MappingAggTable aggTable) {
        return aggForeignKeys(aggTable.aggForeignKeys());
    }

    protected List<MappingAggForeignKey> aggForeignKeys(List<MappingAggForeignKey> aggForeignKeys) {
        if (aggForeignKeys != null) {
            return aggForeignKeys.stream().map(this::aggForeignKey).toList();
        }
        return null;
    }

    private MappingAggForeignKey aggForeignKey(MappingAggForeignKey aggForeignKey) {
        if (aggForeignKey != null) {
            String factColumn = aggForeignKeyFactColumn(aggForeignKey);
            String aggColumn = aggForeignKeyAggColumn(aggForeignKey);
            return new_AggForeignKey(factColumn, aggColumn);
        }
        return null;
    }

    protected String aggForeignKeyAggColumn(MappingAggForeignKey aggForeignKey) {
        return aggForeignKey.aggColumn();
    }

    protected String aggForeignKeyFactColumn(MappingAggForeignKey aggForeignKey) {
        return aggForeignKey.factColumn();
    }

    protected abstract MappingAggForeignKey new_AggForeignKey(String factColumn, String aggColumn);

    protected List<MappingAggExclude> aggPatternAggExcludes(MappingAggPattern aggPattern) {
        return aggExcludes(aggPattern.aggExcludes());
    }

    protected String aggPatternPattern(MappingAggPattern aggPattern) {
        return aggPattern.pattern();
    }

    protected List<MappingAggColumnName> aggTableAggIgnoreColumns(MappingAggTable aggTable) {
        return aggColumnNames(aggTable.aggIgnoreColumns());
    }

    protected List<MappingAggColumnName> aggColumnNames(List<MappingAggColumnName> aggColumnNames) {
        if (aggColumnNames != null) {
            return aggColumnNames.stream().map(this::aggColumnName).toList();
        }
        return null;
    }

    private MappingAggColumnName aggColumnName(MappingAggColumnName aggColumnName) {
        if (aggColumnName != null) {
            String column = aggColumnNameColumn(aggColumnName);
            return new_AggColumnName(column);
        }
        return null;
    }

    protected String aggColumnNameColumn(MappingAggColumnName aggColumnName) {
        return aggColumnName.column();
    }

    protected abstract MappingAggColumnName new_AggColumnName(String column);

    protected abstract MappingAggTable new_AggPattern(
        String pattern,
        MappingAggColumnName aggFactCount,
        List<MappingAggColumnName> aggIgnoreColumns,
        List<MappingAggForeignKey> aggForeignKeys,
        List<MappingAggMeasure> aggMeasures,
        List<MappingAggLevel> aggLevels,
        List<MappingAggExclude> aggExcludes,
        boolean ignorecase,
        List<MappingAggMeasureFactCount> measuresFactCounts
    );

    protected abstract MappingAggTable new_AggName(
        String name,
        MappingAggColumnName aggFactCount,
        List<MappingAggMeasure> aggMeasures,
        List<MappingAggColumnName> aggIgnoreColumns,
        List<MappingAggForeignKey> aggForeignKeys,
        List<MappingAggLevel> aggLevels,
        boolean ignorecase,
        List<MappingAggMeasureFactCount> measuresFactCounts,
        String approxRowCount
    );

    protected MappingAggColumnName aggTableAggFactCount(MappingAggTable aggTable) {
        return aggFactCount(aggTable.aggFactCount());
    }

    protected MappingAggColumnName aggFactCount(MappingAggColumnName aggFactCount) {
        if (aggFactCount != null) {
            String column = aggFactCountColumn(aggFactCount);
            return new_AggColumnName(column);
        }
        return null;
    }

    protected String aggFactCountColumn(MappingAggColumnName aggFactCount) {
        return aggFactCount.column();
    }

    protected List<MappingAggExclude> tableAggExcludes(MappingTable table) {
        return aggExcludes(table.aggExcludes());
    }

    protected List<MappingAggExclude> aggExcludes(List<MappingAggExclude> aggExcludes) {
        if (aggExcludes != null) {
            return aggExcludes.stream().map(this::aggExclude).toList();
        }
        return null;
    }

    private MappingAggExclude aggExclude(MappingAggExclude aggExclude) {
        if (aggExclude != null) {
            String pattern = aggExcludePattern(aggExclude);
            String name = aggExcludeName(aggExclude);
            boolean ignorecase = aggExcludeIgnorecase(aggExclude);
            return new_AggExclude(
                pattern,
                name,
                ignorecase
            );
        }
        return null;
    }

    protected boolean aggExcludeIgnorecase(MappingAggExclude aggExclude) {
        return aggExclude.ignorecase();
    }

    protected String aggExcludeName(MappingAggExclude aggExclude) {
        return aggExclude.name();
    }

    protected String aggExcludePattern(MappingAggExclude aggExclude) {
        return aggExclude.pattern();
    }

    protected abstract MappingAggExclude new_AggExclude(
        String pattern,
        String name,
        boolean ignorecase
    );

    protected MappingSQL tableSql(MappingTable table) {
        return sql(table.sql());
    }

    protected MappingSQL sql(MappingSQL sql) {
    	if (sql != null) {
    		String content = sqlContent(sql);
    		String dialect = sqlDialect(sql);
    		return new_SQL(content, dialect);
    	}
    	return null;
    }

    protected String sqlDialect(MappingSQL sql) {
        return sql.dialect();
    }

    protected String sqlContent(MappingSQL sql) {
        return sql.content();
    }

    protected abstract MappingSQL new_SQL(String content, String dialect);

    protected List<MappingRow> inlineTableRows(MappingInlineTable inlineTable) {
        return rows(inlineTable.rows());
    }

    protected List<MappingRow> rows(List<MappingRow> rows) {
        if (rows != null) {
            return rows.stream().map(this::row).toList();
        }
        return null;
    }

    private MappingRow row(MappingRow row) {
        if (row != null) {
            List<MappingValue> values = rowValues(row);
            return new_Row(values);
        }
        return null;
    }

    protected List<MappingValue> rowValues(MappingRow row) {
        return values(row.values());
    }

    protected List<MappingValue> values(List<MappingValue> values) {
        if (values != null) {
            return values.stream().map(this::value).toList();
        }
        return null;
    }

    private MappingValue value(MappingValue value) {
        if (value != null) {
            String content = valueContent(value);
            String column = valueColumn(value);
            return new_Value(column, content);
        }
        return null;
    }

    protected abstract MappingValue new_Value(String column, String content);

    protected String valueColumn(MappingValue value) {
        return value.column();
    }

    protected String valueContent(MappingValue value) {
        return value.content();
    }

    ;

    protected abstract MappingRow new_Row(List<MappingValue> values);

    protected abstract MappingTable new_Table(
        String schema, String name, String alias,
        List<MappingHint> hints, MappingSQL sql,
        List<MappingAggExclude> aggExcludes, List<MappingAggTable> aggTables
    );

    protected List<MappingColumnDef> inlineTableColumnDefs(MappingInlineTable inlineTable) {
        return columnDefs(inlineTable.columnDefs());
    }

    protected List<MappingColumnDef> columnDefs(List<MappingColumnDef> columnDefs) {
        if (columnDefs != null) {
            return columnDefs.stream().map(this::columnDef).toList();
        }
        return null;
    }

    private MappingColumnDef columnDef(MappingColumnDef columnDef) {
        if (columnDef != null) {
            String name = columnDefName(columnDef);
            TypeEnum type = columnDefType(columnDef);
            return new_ColumnDef(name, type);
        }
        return null;
    }

    protected abstract MappingColumnDef new_ColumnDef(String name, TypeEnum type);

    protected TypeEnum columnDefType(MappingColumnDef columnDef) {
        return columnDef.type();
    }

    protected String columnDefName(MappingColumnDef columnDef) {
        return columnDef.name();
    }

    protected abstract MappingRelation new_View(String alias, List<MappingSQL> sqls);

    protected abstract MappingRelation new_InlineTable(
        List<MappingColumnDef> columnDefs,
        List<MappingRow> rows, String alias
    );

    protected String relationAlias(MappingRelation relation) {
        return relation.alias();
    }

    protected Optional<MappingWritebackTable> cubeWritebackTable(MappingCube cube) {
        return writebackTables(cube.writebackTable());
    }

    protected Optional<MappingWritebackTable> writebackTables(Optional<MappingWritebackTable> writebackTables) {
        if (writebackTables != null) {
            return Optional.ofNullable(writebackTable(writebackTables.get()));
        }
        return Optional.empty();
    }

    private MappingWritebackTable writebackTable(MappingWritebackTable writebackTable) {
        if (writebackTable != null) {
            String schema = writebackTableSchema(writebackTable);
            String name = writebackTableName(writebackTable);
            List<MappingWritebackColumn> columns = writebackTableColumns(writebackTable);
            ;

            return new_WritebackTable(schema, name, columns);
        }
        return null;
    }

    protected List<MappingWritebackColumn> writebackTableColumns(MappingWritebackTable writebackTable) {
        return writebackColumns(writebackTable.columns());
    }

    protected List<MappingWritebackColumn> writebackColumns(Iterable<MappingWritebackColumn> columns) {
        if (columns != null) {
            Iterator<MappingWritebackColumn> iterator = columns.iterator();
            List<MappingWritebackColumn> result = new ArrayList<>();
            while (iterator.hasNext()) {
                result.add(writebackColumn(iterator.next()));
            }
            return result;
        }
        return null;
    }

    protected MappingWritebackColumn writebackColumn(MappingWritebackColumn writebackColumn) {
        if (writebackColumn != null) {
            if (writebackColumn instanceof MappingWritebackAttribute mappingWritebackAttribute) {
                return mappingWritebackAttribute(mappingWritebackAttribute);
            }
            if (writebackColumn instanceof MappingWritebackMeasure mappingWritebackMeasure) {
                return mappingWritebackMeasure(mappingWritebackMeasure);
            }
        }
        return null;
    }

    protected MappingWritebackColumn mappingWritebackMeasure(MappingWritebackMeasure writebackMeasure) {
        if (writebackMeasure != null) {
            String name = writebackMeasureName(writebackMeasure);
            String column = writebackMeasureColumn(writebackMeasure);
            return new_WritebackMeasure(name, column);
        }
        return null;
    }

    protected String writebackMeasureColumn(MappingWritebackMeasure writebackMeasure) {
        return writebackMeasure.column();
    }

    protected String writebackMeasureName(MappingWritebackMeasure writebackMeasure) {
        return writebackMeasure.name();
    }

    protected abstract MappingWritebackColumn new_WritebackMeasure(String name, String column);

    protected MappingWritebackColumn mappingWritebackAttribute(MappingWritebackAttribute writebackAttribute) {
        if (writebackAttribute != null) {
            String dimension = writebackAttributeDimension(writebackAttribute);
            String column = writebackAttributeColumn(writebackAttribute);
            return new_WritebackAttribute(dimension, column);
        }
        return null;
    }

    protected String writebackAttributeColumn(MappingWritebackAttribute writebackAttribute) {
        return writebackAttribute.column();
    };

    protected String writebackAttributeDimension(MappingWritebackAttribute writebackAttribute) {
        return writebackAttribute.dimension();
    }

    protected abstract MappingWritebackColumn new_WritebackAttribute(String dimension, String column);

    protected String writebackTableName(MappingWritebackTable writebackTable) {
        return writebackTable.name();
    }

    protected String writebackTableSchema(MappingWritebackTable writebackTable) {
        return writebackTable.schema();
    };

    protected abstract MappingWritebackTable new_WritebackTable(
        String schema, String name, List<MappingWritebackColumn> columns);

    protected List<MappingDrillThroughAction> cubeDrillThroughActions(MappingCube cube) {
        return drillThroughActions(cube.drillThroughActions());
    }

    protected List<MappingDrillThroughAction> drillThroughActions(List<MappingDrillThroughAction> drillThroughActions){
        if (drillThroughActions != null) {
            return drillThroughActions.stream().map(this::drillThroughAction).toList();
        }
        return null;
    }

    private MappingDrillThroughAction drillThroughAction(MappingDrillThroughAction drillThroughAction) {
        if (drillThroughAction != null) {
            List<MappingAnnotation> annotations = drillThroughActionAnnotations(drillThroughAction);
            List<MappingDrillThroughElement> drillThroughElements = drillThroughActionDrillThroughElements(drillThroughAction);
            String name = drillThroughActionName(drillThroughAction);
            Boolean defaultt = drillThroughActionDefault(drillThroughAction);
            String caption = drillThroughActionCaption(drillThroughAction);
            String description = drillThroughActionDescription(drillThroughAction);
            return new_DrillThroughAction(name,
                caption,
                description,
                defaultt,
                annotations,
                drillThroughElements);
        }
        return null;
    }

    protected String drillThroughActionDescription(MappingDrillThroughAction drillThroughAction) {
        return drillThroughAction.description();
    }

    protected String drillThroughActionCaption(MappingDrillThroughAction drillThroughAction) {
        return drillThroughAction.caption();
    }

    protected Boolean drillThroughActionDefault(MappingDrillThroughAction drillThroughAction) {
        return drillThroughAction.defaultt();
    }

    protected String drillThroughActionName(MappingDrillThroughAction drillThroughAction) {
        return drillThroughAction.name();
    }

    protected List<MappingDrillThroughElement> drillThroughActionDrillThroughElements(MappingDrillThroughAction drillThroughAction) {
        return drillThroughElements(drillThroughAction.drillThroughElements());
    }

    protected List<MappingDrillThroughElement> drillThroughElements(List<MappingDrillThroughElement> drillThroughElements) {
        if (drillThroughElements != null) {
            return drillThroughElements.stream().map(this::drillThroughElement).toList();
        }
        return null;
    }

    private MappingDrillThroughElement drillThroughElement(MappingDrillThroughElement mappingDrillThroughElement) {
        if (mappingDrillThroughElement != null) {
            if (mappingDrillThroughElement instanceof MappingDrillThroughMeasure mappingDrillThroughMeasure) {
                return mappingDrillThroughMeasure(mappingDrillThroughMeasure);
            }
            if (mappingDrillThroughElement instanceof MappingDrillThroughAttribute mappingDrillThroughAttribute) {
                return mappingDrillThroughAttribute(mappingDrillThroughAttribute);
            }
        }
        return null;
    }

    protected MappingDrillThroughElement mappingDrillThroughAttribute(
        MappingDrillThroughAttribute drillThroughAttribute) {
        if (drillThroughAttribute != null) {
            String dimension = drillThroughAttributeDimension(drillThroughAttribute);
            String hierarchy = drillThroughAttributeHierarchy(drillThroughAttribute);
            String level = drillThroughAttributeLevel(drillThroughAttribute);
            String property = drillThroughAttributeProperty(drillThroughAttribute);

            return new_DrillThroughAttribute(dimension, level, hierarchy, property);
        }
        return null;
    }

    protected String drillThroughAttributeLevel(MappingDrillThroughAttribute drillThroughAttribute) {
        return drillThroughAttribute.level();
    }

    protected String drillThroughAttributeProperty(MappingDrillThroughAttribute drillThroughAttribute) {
        return drillThroughAttribute.property();
    }

    protected String drillThroughAttributeHierarchy(MappingDrillThroughAttribute drillThroughAttribute) {
        return drillThroughAttribute.hierarchy();
    }

    protected String drillThroughAttributeDimension(MappingDrillThroughAttribute drillThroughAttribute) {
        return drillThroughAttribute.dimension();
    }

    protected abstract MappingDrillThroughElement new_DrillThroughAttribute(
        String dimension, String level, String hierarchy, String property);

    protected MappingDrillThroughElement mappingDrillThroughMeasure(MappingDrillThroughMeasure drillThroughMeasure) {
        if (drillThroughMeasure != null) {
            String name = drillThroughMeasureName(drillThroughMeasure);
            return new_DrillThroughMeasure(name);
        }
        return null;
    }

    protected String drillThroughMeasureName(MappingDrillThroughMeasure drillThroughMeasure) {
        return drillThroughMeasure.name();
    }

    protected abstract MappingDrillThroughElement new_DrillThroughMeasure(String name);

    protected abstract MappingDrillThroughAction new_DrillThroughAction(
        String name,
        String caption,
        String description,
        Boolean defaultt,
        List<MappingAnnotation> annotations,
        List<MappingDrillThroughElement> drillThroughElements
    );

    protected List<MappingAnnotation> drillThroughActionAnnotations(
        MappingDrillThroughAction drillThroughAction) {
        return annotations(drillThroughAction.annotations());
    }

    protected List<MappingCalculatedMember> cubeCalculatedMembers(MappingCube cube) {
        return calculatedMembers(cube.calculatedMembers());
    }

    protected List<MappingMeasure> cubeMeasures(MappingCube cube) {
        return measures(cube.measures());
    }

    protected List<MappingMeasure> measures(List<MappingMeasure> measures) {
        if (measures != null) {
            return measures.stream().map(this::measure).toList();
        }
        return null;
    }

    private MappingMeasure measure(MappingMeasure measure) {
        if (measure != null) {
            List<MappingAnnotation> annotations = measureAnnotations(measure);
            MappingExpressionView measureExpression = measureMeasureExpression(measure);
            List<MappingCalculatedMemberProperty> calculatedMemberProperties = measureCalculatedMemberProperties(measure);
            String name = measureName(measure);
            String column = measureColumn(measure);
            MeasureDataTypeEnum datatype = measureDatatype(measure);
            String formatString = measureFormatString(measure);
            String aggregator = measureAggregator(measure);
            String formatter = measureFormatter(measure);
            String caption = measureCaption(measure);
            String description = measureDescription(measure);
            boolean visible = measureVisible(measure);
            String displayFolder = measureDisplayFolder(measure);
            MappingElementFormatter cellFormatter = measureCellFormatter(measure);
            String backColor = measureBackColor(measure);

            return new_Measure(name,
                column,
                datatype,
                formatString,
                aggregator,
                formatter,
                caption,
                description,
                visible,
                displayFolder,
                annotations,
                measureExpression,
                calculatedMemberProperties,
                cellFormatter,
                backColor);
        }
        return null;
    }

    protected List<MappingCalculatedMemberProperty> measureCalculatedMemberProperties(MappingMeasure measure) {
        return calculatedMemberProperties(measure.calculatedMemberProperties());
    }

    protected String measureBackColor(MappingMeasure measure) {
        return measure.backColor();
    };

    protected MappingElementFormatter measureCellFormatter(MappingMeasure measure) {
        return elementFormatter(measure.cellFormatter());
    }

    protected String measureDisplayFolder(MappingMeasure measure) {
        return measure.displayFolder();
    }

    protected boolean measureVisible(MappingMeasure measure) {
        return measure.visible();
    }

    protected String measureDescription(MappingMeasure measure) {
        return measure.description();
    }

    protected String measureCaption(MappingMeasure measure) {
        return measure.caption();
    }

    protected String measureFormatter(MappingMeasure measure) {
        return measure.formatter();
    }

    protected String measureAggregator(MappingMeasure measure) {
        return measure.aggregator();
    }

    protected String measureFormatString(MappingMeasure measure) {
        return measure.formatString();
    }

    protected MeasureDataTypeEnum measureDatatype(MappingMeasure measure) {
        return measure.datatype();
    }

    protected String measureColumn(MappingMeasure measure) {
        return measure.column();
    }

    protected String measureName(MappingMeasure measure) {
        return measure.name();
    }

    protected MappingExpressionView measureMeasureExpression(MappingMeasure measure){
        return expressionView(measure.measureExpression());
    };

    protected List<MappingAnnotation> measureAnnotations(MappingMeasure measure) {
        return annotations(measure.annotations());
    }

    protected abstract MappingMeasure new_Measure(
        String name,
        String column,
        MeasureDataTypeEnum datatype,
        String formatString,
        String aggregator,
        String formatter,
        String caption,
        String description,
        boolean visible,
        String displayFolder,
        List<MappingAnnotation> annotations,
        MappingExpressionView measureExpression,
        List<MappingCalculatedMemberProperty> calculatedMemberProperties,
        MappingElementFormatter cellFormatter,
        String backColor
    )
    ;

    protected List<MappingCubeDimension> cubeDimensionUsageOrDimensions(MappingCube cube) {
        return cubeDimensions(cube.dimensionUsageOrDimensions());
    }

    protected List<MappingCubeDimension> cubeDimensions(List<MappingCubeDimension> cubeDimensions) {
        if (cubeDimensions != null) {
            return cubeDimensions.stream().map(this::cubeDimension).toList();
        }
        return null;
    }

    private MappingCubeDimension cubeDimension(MappingCubeDimension cubeDimension) {
        if (cubeDimension != null){
            List<MappingAnnotation> annotations = cubeDimensionAnnotations(cubeDimension);
            String name = cubeDimensionName(cubeDimension);
            String foreignKey = cubeDimensionForeignKey(cubeDimension);
            String caption = cubeDimensionCaption(cubeDimension);
            boolean visible = cubeDimensionVisible(cubeDimension);
            String description = cubeDimensionDescription(cubeDimension);
            if (cubeDimension instanceof MappingDimensionUsage dimensionUsage) {
                String source = cubeDimensionSource(dimensionUsage);
                String level = cubeDimensionLevel(dimensionUsage);
                String usagePrefix = cubeDimensionUsagePrefix(dimensionUsage);
                return new_DimensionUsage(
                    name,
                    description,
                    annotations,
                    caption,
                    visible,
                    source,
                    level,
                    usagePrefix,
                    foreignKey
                );
            }
            if (cubeDimension instanceof MappingPrivateDimension privateDimension) {
                DimensionTypeEnum type = dimensionType(privateDimension);
                List<MappingHierarchy> hierarchies = dimensionHieraries(privateDimension);
                String usagePrefix = dimensionUsagePrefix(privateDimension);
                return new_PrivateDimension(
                    name, type, caption,
                    description, foreignKey,  annotations,
                    hierarchies, visible, usagePrefix
                );
            }
        }
        return null;
    }

    protected abstract MappingCubeDimension new_CubeDimension(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String foreignKey);

    protected String cubeDimensionUsagePrefix(MappingDimensionUsage dimensionUsage) {
        return dimensionUsage.usagePrefix();
    }

    protected String cubeDimensionLevel(MappingDimensionUsage dimensionUsage) {
        return dimensionUsage.level();
    }

    protected String cubeDimensionSource(MappingDimensionUsage cubeDimension) {
        return cubeDimension.source();
    }


    protected String cubeDimensionDescription(MappingCubeDimension cubeDimension) {
        return cubeDimension.description();
    }

    protected boolean cubeDimensionVisible(MappingCubeDimension cubeDimension) {
        return cubeDimension.visible();
    }

    protected String cubeDimensionCaption(MappingCubeDimension cubeDimension) {
        return cubeDimension.caption();
    }

    protected String cubeDimensionForeignKey(MappingCubeDimension cubeDimension) {
        return cubeDimension.foreignKey();
    }

    protected String cubeDimensionName(MappingCubeDimension cubeDimension) {
        return cubeDimension.name();
    }

    protected List<MappingAnnotation> cubeDimensionAnnotations(MappingCubeDimension cubeDimension) {
        return annotations(cubeDimension.annotations());
    }

    protected abstract MappingCubeDimension new_DimensionUsage(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String source,
        String level,
        String usagePrefix,
        String foreignKey
    );

    protected MappingLevel level(MappingLevel level) {
        if (level != null) {
            List<MappingAnnotation> annotations = levelAnnotations(level);
            MappingExpressionView keyExpression = levelKeyExpression(level);
            MappingExpressionView nameExpression = levelNameExpression(level);
            MappingExpressionView captionExpression = levelCaptionExpression(level);
            MappingExpressionView ordinalExpression = levelOrdinalExpression(level);
            MappingExpressionView parentExpression = levelParentExpression(level);
            MappingClosure closure = levelClosure(level);
            List<MappingProperty> properties = levelProperties(level);
            String approxRowCount = levelApproxRowCount(level);
            String name = levelName(level);
            String table = levelTable(level);
            String column = levelColumn(level);
            String nameColumn = levelNameColumn(level);
            String ordinalColumn = levelOrdinalColumn(level);
            String parentColumn = levelParentColumn(level);
            String nullParentValue = levelNullParentValue(level);
            TypeEnum type = levelType(level);
            boolean uniqueMembers = levelUniqueMembers(level);
            LevelTypeEnum levelType = levelLevelType(level);
            HideMemberIfEnum hideMemberIf = levelHideMemberIf(level);
            String formatter = levelFormatter(level);
            String caption = levelCaption(level);
            String description = levelDescription(level);
            String captionColumn = levelCaptionColumn(level);
            boolean visible = levelVisible(level);
            InternalTypeEnum internalType = levelInternalType(level);
            MappingElementFormatter memberFormatter = levelMemberFormatter(level);
            return new_Level(
                name,
                table,
                column,
                nameColumn,
                ordinalColumn,
                parentColumn,
                nullParentValue,
                type,
                approxRowCount,
                uniqueMembers,
                levelType,
                hideMemberIf,
                formatter,
                caption,
                description,
                captionColumn,
                annotations,
                keyExpression,
                nameExpression,
                captionExpression,
                ordinalExpression,
                parentExpression,
                closure,
                properties,
                visible,
                internalType,
                memberFormatter);
        }
        return null;
    }

    protected MappingElementFormatter levelMemberFormatter(MappingLevel level) {
        return memberFormatter(level.memberFormatter());
    }

    protected MappingElementFormatter memberFormatter(MappingElementFormatter formatter) {
    	if (formatter != null) {
    		String className = formatterClassName(formatter);
    		MappingScript script = formatterScript(formatter);
    		return new_ElementFormatter(className, script);
    	}
    	return null;
    }

    protected MappingScript formatterScript(MappingElementFormatter formatter) {
        return script(formatter.script());
    }

    protected String formatterClassName(MappingElementFormatter formatter) {
        return formatter.className();
    }

    protected abstract MappingElementFormatter new_ElementFormatter(String className, MappingScript script);

    protected InternalTypeEnum levelInternalType(MappingLevel level) {
        return level.internalType();
    }

    protected boolean levelVisible(MappingLevel level) {
        return level.visible();
    }

    protected String levelCaptionColumn(MappingLevel level) {
        return level.captionColumn();
    }

    protected String levelDescription(MappingLevel level) {
        return level.description();
    }

    protected String levelCaption(MappingLevel level) {
        return level.caption();
    }

    protected String levelFormatter(MappingLevel level) {
        return level.formatter();
    }

    protected HideMemberIfEnum levelHideMemberIf(MappingLevel level) {
        return level.hideMemberIf();
    }

    protected LevelTypeEnum levelLevelType(MappingLevel level) {
        return level.levelType();
    }

    protected boolean levelUniqueMembers(MappingLevel level) {
        return level.uniqueMembers();
    }

    protected TypeEnum levelType(MappingLevel level) {
        return level.type();
    }

    protected String levelNullParentValue(MappingLevel level) {
        return level.nullParentValue();
    }

    protected String levelParentColumn(MappingLevel level) {
        return level.parentColumn();
    }

    protected String levelOrdinalColumn(MappingLevel level) {
        return level.ordinalColumn();
    }

    protected String levelNameColumn(MappingLevel level) {
        return level.nameColumn();
    }

    protected String levelColumn(MappingLevel level) {
        return level.column();
    }

    protected String levelTable(MappingLevel level) {
        return level.table();
    }

    protected String levelName(MappingLevel level) {
        return level.name();
    }

    protected String levelApproxRowCount(MappingLevel level) {
        return level.approxRowCount();
    }

    protected List<MappingProperty> levelProperties(MappingLevel level) {
        return properties(level.properties());
    }

    protected List<MappingProperty> properties(List<MappingProperty> properties) {
        if (properties != null) {
            return properties.stream().map(this::property).toList();
        }
        return null;
    }

    private MappingProperty property(MappingProperty property) {
        if (property != null) {
            String name = propertyName(property);
            String column = propertyColumn(property);
            PropertyTypeEnum type = propertyType(property);
            String formatter = propertyFormatter(property);
            String caption = propertyCaption(property);
            String description = propertyDescription(property);
            boolean dependsOnLevelValue = propertyDependsOnLevelValue(property);
            MappingElementFormatter propertyFormatter = propertyPropertyFormatter(property);

            return new_Property(
                name,
                column,
                type,
                formatter,
                caption,
                description,
                dependsOnLevelValue,
                propertyFormatter
            );
        }
        return null;
    }

    protected MappingElementFormatter propertyPropertyFormatter(MappingProperty property) {
        return elementFormatter(property.propertyFormatter());
    }

    protected MappingElementFormatter elementFormatter(MappingElementFormatter elementFormatter) {
    	if (elementFormatter != null) {
    		String className = elementFormatterClassName(elementFormatter);
    		MappingScript script = elementFormatterScript(elementFormatter);
    		return new_ElementFormatter(className, script);
    	}
    	return null;
    }

    protected MappingScript elementFormatterScript(MappingElementFormatter elementFormatter) {
        return script(elementFormatter.script());
    }

    protected String elementFormatterClassName(MappingElementFormatter elementFormatter) {
        return elementFormatter.className();
    }

    protected boolean propertyDependsOnLevelValue(MappingProperty property) {
        return property.dependsOnLevelValue();
    }

    protected String propertyDescription(MappingProperty property) {
        return property.description();
    }

    protected String propertyCaption(MappingProperty property) {
        return property.caption();
    }

    protected String propertyFormatter(MappingProperty property) {
        return property.formatter();
    }

    protected PropertyTypeEnum propertyType(MappingProperty property) {
        return property.type();
    }

    protected String propertyColumn(MappingProperty property) {
        return property.column();
    }

    protected String propertyName(MappingProperty property) {
        return property.name();
    }

    protected abstract MappingProperty new_Property(
        String name,
        String column,
        PropertyTypeEnum type,
        String formatter,
        String caption,
        String description,
        boolean dependsOnLevelValue,
        MappingElementFormatter propertyFormatter
    );

    protected MappingClosure levelClosure(MappingLevel level) {
        return closure(level.closure());
    }

    protected MappingClosure closure(MappingClosure closure) {
    	if (closure != null) {
    		MappingTable table = closureTable(closure);
    		String parentColumn = closureParentColumn(closure);
    		String childColumn = closureChildColumn(closure);
    		return new_Closure(table, parentColumn, childColumn);
    	}
    	return null;
    }

    protected String closureChildColumn(MappingClosure closure) {
        return closure.childColumn();
    }

    protected String closureParentColumn(MappingClosure closure) {
        return closure.parentColumn();
    }

    private MappingTable closureTable(MappingClosure closure) {
        return table(closure.table());
    }

    protected MappingTable table(MappingTable table) {
        if (table != null) {
            MappingSQL sql = tableSql(table);
            List<MappingAggExclude> aggExcludes = tableAggExcludes(table);
            List<MappingAggTable> aggTables = tableAggTables(table);
            List<MappingHint> hints = tableHints(table);
            String name = tableName(table);
            String schema = tableSchema(table);
            String alias = tableAlias(table);
            return new_Table(schema, name, alias, hints, sql, aggExcludes, aggTables);
        }
        return null;
    }

    protected String tableAlias(MappingTable table) {
        return table.alias();
    }

    protected abstract MappingClosure new_Closure(
        MappingTable table,
        String parentColumn,
        String childColumn
    );

    protected MappingExpressionView levelParentExpression(MappingLevel level) {
        return expressionView(level.parentExpression());
    }

    protected MappingExpressionView expressionView(MappingExpressionView expression) {
    	if (expression != null) {
    		List<MappingSQL> sqls = expressionSqls(expression);
    		String table = expressionTable(expression);
    		String name = expressionName(expression);
    		return new_ExpressionView(sqls, table, name);
    	}
    	return null;
    }

    protected String expressionName(MappingExpressionView expression) {
        return expression.name();
    }

    protected String expressionTable(MappingExpressionView expression) {
        return expression.table();
    }

    protected List<MappingSQL> expressionSqls(MappingExpressionView expression){
        return sqls(expression.sqls());
    }

    protected abstract MappingExpressionView new_ExpressionView(
        List<MappingSQL> sqls, String table, String name);

    protected MappingExpressionView levelOrdinalExpression(MappingLevel level) {
        return expressionView(level.ordinalExpression());
    }

    protected MappingExpressionView levelCaptionExpression(MappingLevel level) {
        return expressionView(level.captionExpression());
    }

    protected MappingExpressionView levelNameExpression(MappingLevel level) {
        return expressionView(level.nameExpression());
    }

    protected MappingExpressionView levelKeyExpression(MappingLevel level) {
        return expressionView(level.keyExpression());
    }

    protected List<MappingAnnotation> levelAnnotations(MappingLevel level) {
        return annotations(level.annotations());
    }

    protected abstract MappingLevel new_Level(
        String name,
        String table,
        String column,
        String nameColumn,
        String ordinalColumn,
        String parentColumn,
        String nullParentValue,
        TypeEnum type,
        String approxRowCount,
        boolean uniqueMembers,
        LevelTypeEnum levelType,
        HideMemberIfEnum hideMemberIf,
        String formatter,
        String caption,
        String description,
        String captionColumn,
        List<MappingAnnotation> annotations,
        MappingExpressionView keyExpression,
        MappingExpressionView nameExpression,
        MappingExpressionView captionExpression,
        MappingExpressionView ordinalExpression,
        MappingExpressionView parentExpression,
        MappingClosure closure,
        List<MappingProperty> properties,
        boolean visible,
        InternalTypeEnum internalType,
        MappingElementFormatter memberFormatter
    );

    protected MappingRelationOrJoin hierarchyRelation(MappingHierarchy hierarchy) {
        return relationOrJoin(hierarchy.relation());
    }

    protected MappingRelationOrJoin relationOrJoin(MappingRelationOrJoin relationOrJoin) {
        if (relationOrJoin != null) {
            if (relationOrJoin instanceof MappingRelation relation) {
                return relation(relation);
            }
            if (relationOrJoin instanceof MappingJoin join) {
                return join(join);
            }
        }
        return null;
    }

    protected MappingJoin join(MappingJoin join) {
        if (join != null) {
            List<MappingRelationOrJoin> relations = joinRelations(join);
            String leftAlias = joinLeftAlias(join);
            String leftKey = joinLeftKey(join);
            String rightAlias = joinRightAlias(join);
            String rightKey = joinRightKey(join);
            return new_Join(relations,
                leftAlias,
                leftKey,
                rightAlias,
                rightKey);
        }
        return null;
    }

    protected String joinRightKey(MappingJoin join) {
        return join.rightKey();
    }

    protected String joinRightAlias(MappingJoin join) {
        return join.rightAlias();
    }

    protected String joinLeftKey(MappingJoin join) {
        return join.leftKey();
    }

    ;

    protected String joinLeftAlias(MappingJoin join) {
        return join.leftAlias();
    }

    protected List<MappingRelationOrJoin> joinRelations(MappingJoin join) {
        return relationOrJoins(join.relations());
    }

    protected List<MappingRelationOrJoin> relationOrJoins(List<MappingRelationOrJoin> relations) {
        return relations.stream().map(this::relationOrJoin).toList();
    }

    protected abstract MappingJoin new_Join(
        List<MappingRelationOrJoin> relations,
        String leftAlias,
        String leftKey,
        String rightAlias,
        String rightKey
    );

    protected List<MappingUserDefinedFunction> schemaUserDefinedFunctions(MappingSchema schema) {
        return userDefinedFunctions(schema.userDefinedFunctions());
    }

    protected List<MappingUserDefinedFunction> userDefinedFunctions(List<MappingUserDefinedFunction> userDefinedFunctions) {
        if (userDefinedFunctions != null) {
            return userDefinedFunctions.stream().map(this::userDefinedFunction).toList();
        }
        return null;
    }

    private MappingUserDefinedFunction userDefinedFunction(MappingUserDefinedFunction userDefinedFunction) {
        if (userDefinedFunction != null) {
            String name = userDefinedFunctionName(userDefinedFunction);
            String className = userDefinedFunctionClassName(userDefinedFunction);
            MappingScript script = userDefinedFunctionScript(userDefinedFunction);
            return new_UserDefinedFunction(name, className, script);
        }
        return null;

    }

    protected MappingScript userDefinedFunctionScript(MappingUserDefinedFunction userDefinedFunction) {
        return script(userDefinedFunction.script());
    }

    protected String userDefinedFunctionClassName(MappingUserDefinedFunction userDefinedFunction) {
        return userDefinedFunction.className();
    }

    protected String userDefinedFunctionName(MappingUserDefinedFunction userDefinedFunction) {
        return userDefinedFunction.name();
    }

    protected abstract MappingUserDefinedFunction new_UserDefinedFunction(
        String name, String className, MappingScript script
    );
}
