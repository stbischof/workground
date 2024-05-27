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
package org.eclipse.daanse.olap.rolap.dbmapper.provider.modifier.jaxb;

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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDocumentation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingElementFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpressionView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingFormula;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackColumn;
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
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ActionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggColumnNameImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggExcludeImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggForeignKeyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggLevelImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggLevelPropertyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggMeasureFactCountImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggMeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggNameImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AggPatternImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AnnotationImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CalculatedMemberImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CalculatedMemberPropertyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CellFormatterImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ClosureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ColumnDefImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CubeGrantImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CubeImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CubeUsageImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DimensionGrantImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DimensionUsageImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DocumentationImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DrillThroughActionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DrillThroughAttributeImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DrillThroughMeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ElementFormatterImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ExpressionViewImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.FormulaImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.HierarchyGrantImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.HierarchyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.HintImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.InlineTableImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.JoinImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.KpiImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.LevelImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.MeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.MemberGrantImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.MemberReaderParameterImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.NamedSetImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ParameterImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.PrivateDimensionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.PropertyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.RoleImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.RoleUsageImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.RowImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SQLImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SchemaGrantImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SchemaImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ScriptImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.TableImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.TranslationImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.UnionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.UserDefinedFunctionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ValueImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.ViewImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.VirtualCubeDimensionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.VirtualCubeImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.VirtualCubeMeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.WritebackAttributeImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.WritebackMeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.WritebackTableImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.provider.modifier.AbstractDbMappingSchemaModifier;

public class JaxbDbMappingSchemaModifier extends AbstractDbMappingSchemaModifier {

    public JaxbDbMappingSchemaModifier(MappingSchema mappingSchema) {
        super(mappingSchema);
    }

    @Override
    protected MappingSchema new_Schema(
        String name, String description, String measuresCaption, String defaultRole,
        List<MappingAnnotation> annotations, List<MappingParameter> parameters,
        List<MappingPrivateDimension> dimensions, List<MappingCube> cubes, List<MappingVirtualCube> virtualCubes,
        List<MappingNamedSet> namedSets, List<MappingRole> roles,
        List<MappingUserDefinedFunction> userDefinedFunctions, Optional<MappingDocumentation> documentation
    ) {
        SchemaImpl schema = new SchemaImpl();
        schema.setName(name);
        schema.setDescription(description);
        schema.setMeasuresCaption(measuresCaption);
        schema.setDefaultRole(defaultRole);
        schema.setAnnotations(annotations);
        schema.setParameters(parameters);
        schema.setDimensions(dimensions);
        schema.setCubes(cubes);
        schema.setVirtualCubes(virtualCubes);
        schema.setNamedSets(namedSets);
        schema.setRoles(roles);
        schema.setUserDefinedFunctions(userDefinedFunctions);
        schema.setDocumentation(documentation);
        return schema;
    }

    @Override
    protected Optional<MappingDocumentation> new_Documentation(String doc) {
        DocumentationImpl documentation = new DocumentationImpl();
        documentation.setDocumentation(doc);
        return Optional.of(documentation);
    }

    @Override
    protected MappingAnnotation new_Annotation(String name, String content) {
        AnnotationImpl annotation = new AnnotationImpl();
        annotation.setName(name);
        annotation.setContent(content);
        return annotation;
    }

    @Override
    protected MappingParameter new_parameter(
        String name, String description, ParameterTypeEnum type,
        boolean modifiable, String defaultValue
    ) {
        ParameterImpl parameter = new ParameterImpl();
        parameter.setName(name);
        parameter.setDescription(description);
        parameter.setType(type);
        return parameter;
    }

    @Override
    protected MappingPrivateDimension new_PrivateDimension(
        String name, DimensionTypeEnum type, String caption,
        String description, String foreignKey, List<MappingAnnotation> annotations,
        List<MappingHierarchy> hierarchies, boolean visible, String usagePrefix
    ) {
        PrivateDimensionImpl privateDimension = new PrivateDimensionImpl();
        privateDimension.setName(name);
        privateDimension.setDescription(description);
        privateDimension.setAnnotations(annotations);
        privateDimension.setCaption(caption);
        privateDimension.setVisible(visible);
        privateDimension.setType(type);
        privateDimension.setForeignKey(foreignKey);
        privateDimension.setHierarchies(hierarchies);
        privateDimension.setUsagePrefix(usagePrefix);
        return privateDimension;
    }

    @Override
    protected MappingHierarchy new_Hierarchy(
        String name, String caption, String description,
        List<MappingAnnotation> annotations, List<MappingLevel> levels,
        List<MappingMemberReaderParameter> memberReaderParameters, boolean hasAll, String allMemberName,
        String allMemberCaption, String allLevelName, String primaryKey, String primaryKeyTable,
        String defaultMember, String memberReaderClass, String uniqueKeyLevelName, boolean visible,
        String displayFolder, MappingRelationOrJoin relation, String origin
    ) {
        HierarchyImpl hierarchy = new HierarchyImpl();
        hierarchy.setName(name);
        hierarchy.setDescription(description);
        hierarchy.setAnnotations(annotations);
        hierarchy.setCaption(caption);
        hierarchy.setVisible(visible);
        hierarchy.setLevels(levels);
        hierarchy.setMemberReaderParameters(memberReaderParameters);
        hierarchy.setHasAll(hasAll);
        hierarchy.setAllMemberName(allMemberName);
        hierarchy.setAllMemberCaption(allMemberCaption);
        hierarchy.setAllLevelName(allLevelName);
        hierarchy.setPrimaryKey(primaryKey);
        hierarchy.setPrimaryKeyTable(primaryKeyTable);
        hierarchy.setDefaultMember(defaultMember);
        hierarchy.setMemberReaderClass(memberReaderClass);
        hierarchy.setUniqueKeyLevelName(uniqueKeyLevelName);
        hierarchy.setDisplayFolder(displayFolder);
        hierarchy.setRelation(relation);
        hierarchy.setOrigin(origin);
        return hierarchy;
    }

    @Override
    protected MappingMemberReaderParameter new_MemberReaderParameter(String name, String value) {
        MemberReaderParameterImpl memberReaderParameter = new MemberReaderParameterImpl();
        memberReaderParameter.setName(name);
        memberReaderParameter.setValue(value);
        return memberReaderParameter;
    }

    @Override
    protected MappingCube new_Cube(
        String name, String caption, String description, String defaultMeasure,
        List<MappingAnnotation> annotations, List<MappingCubeDimension> dimensionUsageOrDimensions,
        List<MappingMeasure> measures, List<MappingCalculatedMember> calculatedMembers,
        List<MappingNamedSet> namedSets, List<MappingDrillThroughAction> drillThroughActions,
        Optional<MappingWritebackTable> writebackTable, boolean enabled, boolean cache, boolean visible,
        MappingRelation fact, List<MappingAction> actions, List<MappingKpi> kpis
    ) {
        CubeImpl cube = new CubeImpl();
        cube.setName(name);
        cube.setDescription(description);
        cube.setAnnotations(annotations);
        cube.setCaption(caption);
        cube.setVisible(visible);
        cube.setDefaultMeasure(defaultMeasure);
        cube.setDimensionUsageOrDimensions(dimensionUsageOrDimensions);
        cube.setMeasures(measures);
        cube.setCalculatedMembers(calculatedMembers);
        cube.setNamedSets(namedSets);
        cube.setDrillThroughActions(drillThroughActions);
        cube.setWritebackTable(writebackTable.orElse(null));
        cube.setEnabled(enabled);
        cube.setCache(cache);
        cube.setFact(fact);
        cube.setActions(actions);
        cube.setKpis(kpis);
        return cube;
    }

    @Override
    protected MappingKpi new_MappingKpi(
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
    ) {
        KpiImpl kpi = new KpiImpl();
        kpi.setName(name);
        kpi.setDescription(description);
        kpi.setCaption(caption);
        kpi.setAnnotations(annotations);
        kpi.setId(id);
        kpi.setTranslations(translations);
        kpi.setDisplayFolder(displayFolder);
        kpi.setAssociatedMeasureGroupID(associatedMeasureGroupID);
        kpi.setValue(value);
        kpi.setGoal(goal);
        kpi.setStatus(status);
        kpi.setTrend(trend);
        kpi.setWeight(weight);
        kpi.setTrendGraphic(trendGraphic);
        kpi.setStatusGraphic(statusGraphic);
        kpi.setCurrentTimeMember(currentTimeMember);
        kpi.setParentKpiID(parentKpiID);
        return kpi;
    }

    @Override
    protected MappingTranslation new_MappingTranslation(
        long language,
        String caption,
        String description,
        String displayFolder,
        List<MappingAnnotation> annotations
    ) {
        TranslationImpl translation = new TranslationImpl();
        translation.setLanguage(language);
        translation.setCaption(caption);
        translation.setDescription(description);
        translation.setDisplayFolder(displayFolder);
        translation.setAnnotations(annotations);
        return translation;
    }

    @Override
    protected MappingAction new_MappingAction(
        String name, String caption, String description,
        List<MappingAnnotation> annotations
    ) {
        ActionImpl action = new ActionImpl();
        action.setName(name);
        action.setDescription(description);
        action.setAnnotations(annotations);
        action.setCaption(caption);
        return action;
    }

    @Override
    protected MappingRole new_MappingRole(
        List<MappingAnnotation> annotations, List<MappingSchemaGrant> schemaGrants,
        MappingUnion union, String name
    ) {
        RoleImpl role = new RoleImpl();
        role.setAnnotations(annotations);
        role.setSchemaGrants(schemaGrants);
        role.setUnion(union);
        role.setName(name);
        return role;
    }

    @Override
    protected MappingFormula new_Formula(String cdata) {
        FormulaImpl formula = new FormulaImpl();
        formula.setCdata(cdata);
        return formula;
    }

    @Override
    protected MappingCellFormatter new_CellFormatter(String className, MappingScript script) {
        CellFormatterImpl cellFormatter = new CellFormatterImpl();
        cellFormatter.setClassName(className);
        cellFormatter.setScript(script);
        return cellFormatter;
    }

    @Override
    protected MappingScript new_Script(String language, String cdata) {
        ScriptImpl script = new ScriptImpl();
        script.setLanguage(language);
        script.setCdata(cdata);
        return script;
    }

    @Override
    protected MappingCalculatedMemberProperty new_CalculatedMemberProperty(
        String name, String caption,
        String description, String expression, String value
    ) {
        CalculatedMemberPropertyImpl calculatedMemberProperty = new CalculatedMemberPropertyImpl();
        calculatedMemberProperty.setName(name);
        calculatedMemberProperty.setCaption(caption);
        calculatedMemberProperty.setDescription(description);
        calculatedMemberProperty.setExpression(expression);
        calculatedMemberProperty.setValue(value);
        return calculatedMemberProperty;
    }

    @Override
    protected MappingCalculatedMember new_CalculatedMember(
        String name, String formatString, String caption,
        String description, String dimension, boolean visible, String displayFolder,
        List<MappingAnnotation> annotations, String formula,
        List<MappingCalculatedMemberProperty> calculatedMemberProperties, String hierarchy, String parent,
        MappingCellFormatter cellFormatter, MappingFormula formulaElement
    ) {
        CalculatedMemberImpl calculatedMember = new CalculatedMemberImpl();
        calculatedMember.setName(name);
        calculatedMember.setDescription(description);
        calculatedMember.setAnnotations(annotations);
        calculatedMember.setCaption(caption);
        calculatedMember.setVisible(visible);
        calculatedMember.setFormatString(formatString);
        calculatedMember.setDimension(dimension);
        calculatedMember.setDisplayFolder(displayFolder);
        calculatedMember.setFormula(formula);
        calculatedMember.setCalculatedMemberProperties(calculatedMemberProperties);
        calculatedMember.setHierarchy(hierarchy);
        calculatedMember.setParent(parent);
        calculatedMember.setCellFormatter(cellFormatter);
        calculatedMember.setFormulaElement(formulaElement);
        return calculatedMember;
    }

    @Override
    protected MappingVirtualCubeMeasure new_VirtualCubeMeasure(
        String name, String cubeName, boolean visible,
        List<MappingAnnotation> annotations
    ) {
        VirtualCubeMeasureImpl virtualCubeMeasure = new VirtualCubeMeasureImpl();
        virtualCubeMeasure.setName(name);
        virtualCubeMeasure.setCubeName(cubeName);
        virtualCubeMeasure.setVisible(visible);
        virtualCubeMeasure.setAnnotations(annotations);
        return virtualCubeMeasure;
    }

    @Override
    protected MappingVirtualCubeDimension new_VirtualCubeDimension(
        String name, String cubeName,
        List<MappingAnnotation> annotations, String foreignKey, String caption,
        boolean visible, String description
    ) {
        VirtualCubeDimensionImpl virtualCubeDimension = new VirtualCubeDimensionImpl();
        virtualCubeDimension.setName(name);
        virtualCubeDimension.setCubeName(cubeName);
        virtualCubeDimension.setAnnotations(annotations);
        virtualCubeDimension.setForeignKey(foreignKey);
        virtualCubeDimension.setCaption(caption);
        virtualCubeDimension.setVisible(visible);
        virtualCubeDimension.setDescription(description);
        return virtualCubeDimension;
    }

    @Override
    protected MappingCubeUsage new_CubeUsage(String cubeName, boolean ignoreUnrelatedDimensions) {
        CubeUsageImpl cubeUsage = new CubeUsageImpl();
        cubeUsage.setCubeName(cubeName);
        cubeUsage.setIgnoreUnrelatedDimensions(ignoreUnrelatedDimensions);
        return cubeUsage;
    }

    @Override
    protected MappingVirtualCube new_VirtualCube(
        String name, String caption, String description, String defaultMeasure,
        boolean enabled, List<MappingAnnotation> annotations, List<MappingCubeUsage> cubeUsages,
        List<MappingVirtualCubeDimension> virtualCubeDimensions,
        List<MappingVirtualCubeMeasure> virtualCubeMeasures, List<MappingCalculatedMember> calculatedMembers,
        List<MappingNamedSet> namedSets, List<MappingKpi> kpis, boolean visible
    ) {
        VirtualCubeImpl virtualCube = new VirtualCubeImpl();
        virtualCube.setName(name);

        virtualCube.setDescription(description);
        virtualCube.setAnnotations(annotations);
        virtualCube.setCaption(caption);
        virtualCube.setVisible(visible);
        virtualCube.setDefaultMeasure(defaultMeasure);
        virtualCube.setEnabled(enabled);
        virtualCube.setCubeUsages(cubeUsages);
        virtualCube.setVirtualCubeDimensions(virtualCubeDimensions);
        virtualCube.setVirtualCubeMeasures(virtualCubeMeasures);
        virtualCube.setCalculatedMembers(calculatedMembers);
        virtualCube.setNamedSets(namedSets);
        virtualCube.setKpis(kpis);
        return virtualCube;
    }

    @Override
    protected MappingNamedSet new_NamedSet(
        String name, String caption, String description, String formula,
        List<MappingAnnotation> annotations, String displayFolder, MappingFormula formulaElement
    ) {
        NamedSetImpl namedSet = new NamedSetImpl();
        namedSet.setName(name);
        namedSet.setDescription(description);
        namedSet.setAnnotations(annotations);
        namedSet.setCaption(caption);
        namedSet.setFormula(formula);
        namedSet.setDisplayFolder(displayFolder);
        namedSet.setFormulaElement(formulaElement);
        return namedSet;
    }

    @Override
    protected MappingUnion new_Union(List<MappingRoleUsage> roleUsages) {
        UnionImpl union = new UnionImpl();
        union.setRoleUsages(roleUsages);
        return union;
    }

    @Override
    protected MappingRoleUsage new_RoleUsage(String roleName) {
        RoleUsageImpl roleUsage = new RoleUsageImpl();
        roleUsage.setRoleName(roleName);
        return roleUsage;
    }

    @Override
    protected MappingMemberGrant new_MemberGrant(String member, MemberGrantAccessEnum access) {
        MemberGrantImpl memberGrant = new MemberGrantImpl();
        memberGrant.setMember(member);
        memberGrant.setAccess(access);
        return memberGrant;
    }

    @Override
    protected MappingHierarchyGrant new_HierarchyGrant(
        String hierarchy, AccessEnum access, String topLevel,
        String bottomLevel, String rollupPolicy, List<MappingMemberGrant> memberGrants
    ) {
        HierarchyGrantImpl hierarchyGrant = new HierarchyGrantImpl();
        hierarchyGrant.setHierarchy(hierarchy);
        hierarchyGrant.setAccess(access);
        hierarchyGrant.setTopLevel(topLevel);
        hierarchyGrant.setBottomLevel(bottomLevel);
        hierarchyGrant.setRollupPolicy(rollupPolicy);
        hierarchyGrant.setMemberGrants(memberGrants);
        return hierarchyGrant;
    }

    @Override
    protected MappingDimensionGrant new_DimensionGrant(AccessEnum access, String dimension) {
        DimensionGrantImpl dimensionGrant = new DimensionGrantImpl();
        dimensionGrant.setAccess(access);
        dimensionGrant.setDimension(dimension);
        return dimensionGrant;
    }

    @Override
    protected MappingCubeGrant new_CubeGrant(
        String cube, String access, List<MappingDimensionGrant> dimensionGrants,
        List<MappingHierarchyGrant> hierarchyGrants
    ) {
        CubeGrantImpl cubeGrant = new CubeGrantImpl();
        cubeGrant.setCube(cube);
        cubeGrant.setAccess(access);
        cubeGrant.setDimensionGrant(dimensionGrants);
        cubeGrant.setHierarchyGrant(hierarchyGrants);
        return cubeGrant;
    }

    @Override
    protected MappingSchemaGrant new_SchemaGrant(List<MappingCubeGrant> schemaGrantCubeGrants, AccessEnum access) {
        SchemaGrantImpl schemaGrant = new SchemaGrantImpl();
        schemaGrant.setCubeGrants(schemaGrantCubeGrants);
        schemaGrant.setAccess(access);
        return schemaGrant;
    }

    @Override
    protected MappingHint new_Hint(String content, String type) {
        HintImpl hint = new HintImpl();
        hint.setContent(content);
        hint.setType(type);
        return hint;
    }

    @Override
    protected MappingAggMeasureFactCount new_AggMeasureFactCount(String factColumn, String column) {
        AggMeasureFactCountImpl aggMeasureFactCount = new AggMeasureFactCountImpl();
        aggMeasureFactCount.setFactColumn(factColumn);
        aggMeasureFactCount.setColumn(column);
        return aggMeasureFactCount;
    }

    @Override
    protected MappingAggLevelProperty new_AggLevelProperty(String name, String column) {
        AggLevelPropertyImpl aggLevelProperty = new AggLevelPropertyImpl();
        aggLevelProperty.setName(name);
        aggLevelProperty.setColumn(column);
        return aggLevelProperty;
    }

    @Override
    protected MappingAggLevel new_AggLevel(
        String column, String name, String ordinalColumn, String nameColumn,
        String captionColumn, Boolean collapsed, List<MappingAggLevelProperty> properties
    ) {
        AggLevelImpl aggLevel = new AggLevelImpl();
        aggLevel.setColumn(column);
        aggLevel.setName(name);
        aggLevel.setOrdinalColumn(ordinalColumn);
        aggLevel.setNameColumn(nameColumn);
        aggLevel.setCaptionColumn(captionColumn);
        aggLevel.setCollapsed(collapsed);
        aggLevel.setProperties(properties);
        return aggLevel;
    }

    @Override
    protected MappingAggMeasure new_AggMeasure(String column, String name, String rollupType) {
        AggMeasureImpl aggMeasure = new AggMeasureImpl();
        aggMeasure.setColumn(column);
        aggMeasure.setName(name);
        aggMeasure.setRollupType(rollupType);
        return aggMeasure;
    }

    @Override
    protected MappingAggForeignKey new_AggForeignKey(String factColumn, String aggColumn) {
        AggForeignKeyImpl aggForeignKey = new AggForeignKeyImpl();
        aggForeignKey.setFactColumn(factColumn);
        aggForeignKey.setAggColumn(aggColumn);
        return aggForeignKey;
    }

    @Override
    protected MappingAggColumnName new_AggColumnName(String column) {
        AggColumnNameImpl aggColumnName = new AggColumnNameImpl();
        aggColumnName.setColumn(column);
        return aggColumnName;
    }

    @Override
    protected MappingAggTable new_AggPattern(
        String pattern, MappingAggColumnName aggFactCount,
        List<MappingAggColumnName> aggIgnoreColumns, List<MappingAggForeignKey> aggForeignKeys,
        List<MappingAggMeasure> aggMeasures, List<MappingAggLevel> aggLevels, List<MappingAggExclude> aggExcludes,
        boolean ignorecase, List<MappingAggMeasureFactCount> measuresFactCounts
    ) {
        AggPatternImpl aggPattern = new AggPatternImpl();
        aggPattern.setPattern(pattern);
        aggPattern.setAggFactCount(aggFactCount);
        aggPattern.setAggIgnoreColumns(aggIgnoreColumns);
        aggPattern.setAggForeignKeys(aggForeignKeys);
        aggPattern.setAggMeasures(aggMeasures);
        aggPattern.setAggLevels(aggLevels);
        aggPattern.setAggExcludes(aggExcludes);
        aggPattern.setIgnorecase(ignorecase);
        aggPattern.setMeasuresFactCounts(measuresFactCounts);
        return aggPattern;
    }

    @Override
    protected MappingAggTable new_AggName(
        String name, MappingAggColumnName aggFactCount,
        List<MappingAggMeasure> aggMeasures, List<MappingAggColumnName> aggIgnoreColumns,
        List<MappingAggForeignKey> aggForeignKeys, List<MappingAggLevel> aggLevels, boolean ignorecase,
        List<MappingAggMeasureFactCount> measuresFactCounts, String approxRowCount
    ) {
        AggNameImpl aggName = new AggNameImpl();
        aggName.setName(name);
        aggName.setAggFactCount(aggFactCount);
        aggName.setAggMeasures(aggMeasures);
        aggName.setAggIgnoreColumns(aggIgnoreColumns);
        aggName.setAggForeignKeys(aggForeignKeys);
        aggName.setAggLevels(aggLevels);
        aggName.setIgnorecase(ignorecase);
        aggName.setMeasuresFactCounts(measuresFactCounts);
        aggName.setApproxRowCount(approxRowCount);
        return aggName;
    }

    @Override
    protected MappingAggExclude new_AggExclude(String pattern, String name, boolean ignorecase) {
        AggExcludeImpl aggExclude = new AggExcludeImpl();
        aggExclude.setPattern(pattern);
        aggExclude.setName(name);
        aggExclude.setIgnorecase(ignorecase);
        return aggExclude;
    }

    @Override
    protected MappingSQL new_SQL(String content, String dialect) {
        SQLImpl sql = new SQLImpl();
        sql.setContent(content);
        sql.setDialect(dialect);
        return sql;
    }

    @Override
    protected MappingValue new_Value(String column, String content) {
        ValueImpl value = new ValueImpl();
        value.setColumn(column);
        value.setContent(content);
        return value;
    }

    @Override
    protected MappingRow new_Row(List<MappingValue> values) {
        RowImpl row = new RowImpl();
        row.setValues(values);
        return row;
    }

    @Override
    protected MappingTable new_Table(
        String schema, String name, String alias, List<MappingHint> hints, MappingSQL sql,
        List<MappingAggExclude> aggExcludes, List<MappingAggTable> aggTables
    ) {
        TableImpl table = new TableImpl();
        table.setSchema(schema);
        table.setName(name);
        table.setAlias(alias);
        table.setHints(hints);
        table.setSql(sql);
        table.setAggExcludes(aggExcludes);
        table.setAggTables(aggTables);
        return table;
    }

    @Override
    protected MappingColumnDef new_ColumnDef(String name, TypeEnum type) {
        ColumnDefImpl columnDef = new ColumnDefImpl();
        columnDef.setName(name);
        columnDef.setType(type);
        return columnDef;
    }

    @Override
    protected MappingRelation new_View(String alias, List<MappingSQL> sqls) {
        ViewImpl view = new ViewImpl();
        view.setAlias(alias);
        view.setSqls(sqls);
        return view;
    }

    @Override
    protected MappingRelation new_InlineTable(List<MappingColumnDef> columnDefs, List<MappingRow> rows, String alias) {
        InlineTableImpl inlineTable = new InlineTableImpl();
        inlineTable.setColumnDefs(columnDefs);
        inlineTable.setRows(rows);
        inlineTable.setAlias(alias);
        return inlineTable;
    }

    @Override
    protected MappingWritebackColumn new_WritebackMeasure(String name, String column) {
        WritebackMeasureImpl writebackMeasure = new WritebackMeasureImpl();
        writebackMeasure.setName(name);
        writebackMeasure.setColumn(column);
        return writebackMeasure;
    }

    @Override
    protected MappingWritebackColumn new_WritebackAttribute(String dimension, String column) {
        WritebackAttributeImpl writebackAttribute = new WritebackAttributeImpl();
        writebackAttribute.setDimension(dimension);
        writebackAttribute.setColumn(column);
        return writebackAttribute;
    }

    @Override
    protected MappingWritebackTable new_WritebackTable(
        String schema, String name,
        List<MappingWritebackColumn> columns
    ) {
        WritebackTableImpl writebackTable = new WritebackTableImpl();
        writebackTable.setSchema(schema);
        writebackTable.setName(name);
        writebackTable.setColumns(columns);
        return writebackTable;
    }

    @Override
    protected MappingDrillThroughElement new_DrillThroughAttribute(
        String dimension, String level,
        String hierarchy
    ) {
        DrillThroughAttributeImpl drillThroughAttribute = new DrillThroughAttributeImpl();
        drillThroughAttribute.setDimension(dimension);
        drillThroughAttribute.setLevel(level);
        drillThroughAttribute.setHierarchy(hierarchy);
        return drillThroughAttribute;
    }

    @Override
    protected MappingDrillThroughElement new_DrillThroughMeasure(String name) {
        DrillThroughMeasureImpl drillThroughMeasure = new DrillThroughMeasureImpl();
        drillThroughMeasure.setName(name);
        return drillThroughMeasure;
    }

    @Override
    protected MappingDrillThroughAction new_DrillThroughAction(
        String name, String caption, String description,
        Boolean defaultt, List<MappingAnnotation> annotations,
        List<MappingDrillThroughElement> drillThroughElements
    ) {
        DrillThroughActionImpl drillThroughAction = new DrillThroughActionImpl();
        drillThroughAction.setName(name);
        drillThroughAction.setCaption(caption);
        drillThroughAction.setDescription(description);
        drillThroughAction.setDefaultt(defaultt);
        drillThroughAction.setAnnotations(annotations);
        drillThroughAction.setDrillThroughElements(drillThroughElements);
        return drillThroughAction;
    }

    @Override
    protected MappingMeasure new_Measure(
        String name, String column, MeasureDataTypeEnum datatype, String formatString,
        String aggregator, String formatter, String caption, String description, boolean visible,
        String displayFolder, List<MappingAnnotation> annotations, MappingExpressionView measureExpression,
        List<MappingCalculatedMemberProperty> calculatedMemberProperties, MappingElementFormatter cellFormatter,
        String backColor
    ) {
        MeasureImpl measure = new MeasureImpl();
        measure.setName(name);
        measure.setDescription(description);
        measure.setAnnotations(annotations);
        measure.setCaption(caption);
        measure.setVisible(visible);
        measure.setColumn(column);
        measure.setDatatype(datatype);
        measure.setFormatString(formatString);
        measure.setAggregator(aggregator);
        measure.setFormatter(formatter);
        measure.setDisplayFolder(displayFolder);
        measure.setMeasureExpression(measureExpression);
        measure.setCalculatedMemberProperties(calculatedMemberProperties);
        measure.setCellFormatter(cellFormatter);
        measure.setBackColor(backColor);
        return measure;
    }

    @Override
    protected MappingCubeDimension new_CubeDimension(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String foreignKey
    ) {
        DimensionUsageImpl dimensionUsage = new DimensionUsageImpl();
        dimensionUsage.setName(name);
        dimensionUsage.setDescription(description);
        dimensionUsage.setAnnotations(annotations);
        dimensionUsage.setCaption(caption);
        dimensionUsage.setVisible(visible);
        dimensionUsage.setForeignKey(foreignKey);
        return dimensionUsage;
    }

    protected MappingCubeDimension new_DimensionUsage(
        String name,
        String description,
        List<MappingAnnotation> annotations,
        String caption,
        Boolean visible,
        String source,
        String level,
        String usagePrefix,
        String foreignKey
    ) {
        DimensionUsageImpl privateDimension = new DimensionUsageImpl();
        privateDimension.setName(name);
        privateDimension.setDescription(description);
        privateDimension.setAnnotations(annotations);
        privateDimension.setCaption(caption);
        privateDimension.setVisible(visible);
        privateDimension.setSource(source);
        privateDimension.setLevel(level);
        privateDimension.setUsagePrefix(usagePrefix);
        privateDimension.setForeignKey(foreignKey);
        return privateDimension;
    }

    @Override
    protected MappingElementFormatter new_ElementFormatter(String className, MappingScript script) {
        ElementFormatterImpl elementFormatter = new ElementFormatterImpl();
        elementFormatter.setClassName(className);
        elementFormatter.setScript(script);
        return elementFormatter;
    }

    @Override
    protected MappingProperty new_Property(
        String name, String column, PropertyTypeEnum type, String formatter,
        String caption, String description, boolean dependsOnLevelValue,
        MappingElementFormatter propertyFormatter
    ) {
        PropertyImpl property = new PropertyImpl();
        property.setName(name);
        property.setColumn(column);
        property.setType(type);
        property.setFormatter(formatter);
        property.setCaption(caption);
        property.setDescription(description);
        property.setDependsOnLevelValue(dependsOnLevelValue);
        property.setPropertyFormatter(propertyFormatter);
        return property;
    }

    @Override
    protected MappingClosure new_Closure(MappingTable table, String parentColumn, String childColumn) {
        ClosureImpl closure = new ClosureImpl();
        closure.setTable(table);
        closure.setParentColumn(parentColumn);
        closure.setChildColumn(childColumn);
        return closure;
    }

    @Override
    protected MappingExpressionView new_ExpressionView(
        List<MappingSQL> sqls,
        String table,
        String name
    ) {
        ExpressionViewImpl expressionView = new ExpressionViewImpl();
        expressionView.setSqls(sqls);
        expressionView.setTable(table);
        expressionView.setName(name);
        return expressionView;
    }

    @Override
    protected MappingLevel new_Level(
        String name, String table, String column, String nameColumn, String ordinalColumn,
        String parentColumn, String nullParentValue, TypeEnum type, String approxRowCount, boolean uniqueMembers,
        LevelTypeEnum levelType, HideMemberIfEnum hideMemberIf, String formatter, String caption,
        String description, String captionColumn, List<MappingAnnotation> annotations,
        MappingExpressionView keyExpression, MappingExpressionView nameExpression,
        MappingExpressionView captionExpression, MappingExpressionView ordinalExpression,
        MappingExpressionView parentExpression, MappingClosure closure, List<MappingProperty> properties,
        boolean visible, InternalTypeEnum internalType, MappingElementFormatter memberFormatter
    ) {
        LevelImpl level = new LevelImpl();
        level.setName(name);
        level.setDescription(description);
        level.setAnnotations(annotations);
        level.setCaption(caption);
        level.setVisible(visible);
        level.setTable(table);
        level.setColumn(column);
        level.setNameColumn(nameColumn);
        level.setOrdinalColumn(ordinalColumn);
        level.setParentColumn(parentColumn);
        level.setNullParentValue(nullParentValue);
        level.setType(type);
        level.setApproxRowCount(approxRowCount);
        level.setUniqueMembers(uniqueMembers);
        level.setLevelType(levelType);
        level.setHideMemberIf(hideMemberIf);
        level.setFormatter(formatter);
        level.setCaptionColumn(captionColumn);
        level.setKeyExpression(keyExpression);
        level.setNameExpression(nameExpression);
        level.setCaptionExpression(captionExpression);
        level.setOrdinalExpression(ordinalExpression);
        level.setParentExpression(parentExpression);
        level.setClosure(closure);
        level.setProperties(properties);
        level.setInternalType(internalType);
        level.setMemberFormatter(memberFormatter);
        return level;
    }

    @Override
    protected MappingJoin new_Join(
        List<MappingRelationOrJoin> relations, String leftAlias, String leftKey,
        String rightAlias, String rightKey
    ) {
        JoinImpl join = new JoinImpl();
        join.setRelations(relations);
        join.setLeftAlias(leftAlias);
        join.setLeftKey(leftKey);
        join.setRightAlias(rightAlias);
        join.setRightKey(rightKey);
        return join;
    }

    @Override
    protected MappingUserDefinedFunction new_UserDefinedFunction(String name, String className, MappingScript script) {
        UserDefinedFunctionImpl userDefinedFunction = new UserDefinedFunctionImpl();
        userDefinedFunction.setName(name);
        userDefinedFunction.setClassName(className);
        userDefinedFunction.setScript(script);
        return userDefinedFunction;
    }



}
