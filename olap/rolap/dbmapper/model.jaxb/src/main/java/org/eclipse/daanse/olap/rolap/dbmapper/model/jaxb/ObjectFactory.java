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

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
        //constructor
    }

    public WritebackTableImpl createWritebackTable() {
        return new WritebackTableImpl();
    }

    public SchemaImpl createSchema() {
        return new SchemaImpl();
    }

    public TableImpl createTable() {
        return new TableImpl();
    }

    public AggPatternImpl createTableAggPattern() {
        return new AggPatternImpl();
    }

    public AggNameImpl createTableAggName() {
        return new AggNameImpl();
    }

    public HierarchyImpl createHierarchy() {
        return new HierarchyImpl();
    }

    public LevelImpl createHierarchyLevel() {
        return new LevelImpl();
    }

    public InlineTableImpl createHierarchyInlineTable() {
        return new InlineTableImpl();
    }

    public RowImpl createHierarchyInlineTableRowsRow() {
        return new RowImpl();
    }

    public RoleImpl createSchemaRole() {
        return new RoleImpl();
    }

    public UnionImpl createSchemaRoleUnion() {
        return new UnionImpl();
    }

    public SchemaGrantImpl createSchemaRoleSchemaGrant() {
        return new SchemaGrantImpl();
    }

    public CubeGrantImpl createSchemaRoleSchemaGrantCubeGrant() {
        return new CubeGrantImpl();
    }

    public HierarchyGrantImpl createSchemaRoleSchemaGrantCubeGrantHierarchyGrant() {
        return new HierarchyGrantImpl();
    }

    public VirtualCubeImpl createSchemaVirtualCube() {
        return new VirtualCubeImpl();
    }

    public CubeImpl createSchemaCube() {
        return new CubeImpl();
    }

    public ParameterImpl createSchemaParameter() {
        return new ParameterImpl();
    }

    public SharedDimensionImpl createSharedDimension() {
        return new SharedDimensionImpl();
    }

    public NamedSetImpl createNamedSet() {
        return new NamedSetImpl();
    }

    /**
     * @return @deprecated
     */
    @Deprecated(since="new version", forRemoval=true)
    public UserDefinedFunctionImpl createSchemaUserDefinedFunction() {
        return new UserDefinedFunctionImpl();
    }

    public PrivateDimensionImpl createPrivateDimension() {
        return new PrivateDimensionImpl();
    }

    public DimensionUsageImpl createDimensionUsage() {
        return new DimensionUsageImpl();
    }

    public AggColumnNameImpl createAggColumnName() {
        return new AggColumnNameImpl();
    }

    public SqlSelectQueryImpl createSQL() {
        return new SqlSelectQueryImpl();
    }

    public ExpressionViewImpl createExpressionView() {
        return new ExpressionViewImpl();
    }

    public ViewImpl createView() {
        return new ViewImpl();
    }

    public CalculatedMemberImpl createCalculatedMember() {
        return new CalculatedMemberImpl();
    }

    public CalculatedMemberPropertyImpl createCalculatedMemberProperty() {
        return new CalculatedMemberPropertyImpl();
    }

    public DrillThroughActionImpl createDrillThroughAction() {
        return new DrillThroughActionImpl();
    }

    public DrillThroughAttributeImpl createDrillThroughAttribute() {
        return new DrillThroughAttributeImpl();
    }

    public DrillThroughMeasureImpl createDrillThroughMeasure() {
        return new DrillThroughMeasureImpl();
    }

    public AggExcludeImpl createTableAggExclude() {
        return new AggExcludeImpl();
    }

    /**
     * Impl create an instance of {@link HintImpl }
     *
     */
    public HintImpl createTableHint() {
        return new HintImpl();
    }

    public AggForeignKeyImpl createTableAggPatternAggForeignKey() {
        return new AggForeignKeyImpl();
    }

    public AggMeasureImpl createTableAggPatternAggMeasure() {
        return new AggMeasureImpl();
    }

    public AggLevelImpl createTableAggPatternAggLevel() {
        return new AggLevelImpl();
    }

    public AggExcludeImpl createTableAggPatternAggExclude() {
        return new AggExcludeImpl();
    }

    public AggForeignKeyImpl createTableAggNameAggForeignKey() {
        return new AggForeignKeyImpl();
    }

    public AggMeasureImpl createTableAggNameAggMeasure() {
        return new AggMeasureImpl();
    }

    public AggLevelImpl createTableAggNameAggLevel() {
        return new AggLevelImpl();
    }

    public JoinQueryImpl createHierarchyJoin() {
        return new JoinQueryImpl();
    }

    public MemberReaderParameterImpl createHierarchyMemberReaderParameter() {
        return new MemberReaderParameterImpl();
    }

    public ClosureImpl createHierarchyLevelClosure() {
        return new ClosureImpl();
    }

    public PropertyImpl createHierarchyLevelProperty() {
        return new PropertyImpl();
    }

    public ValueImpl createHierarchyInlineTableRowsRowValue() {
        return new ValueImpl();
    }

    public ColumnDefImpl createHierarchyInlineTableColumnDefsColumnDef() {
        return new ColumnDefImpl();
    }

    public RoleUsageImpl createSchemaRoleUnionRoleUsage() {
        return new RoleUsageImpl();
    }

    public DimensionGrantImpl createSchemaRoleSchemaGrantCubeGrantDimensionGrant() {
        return new DimensionGrantImpl();
    }

    public MemberGrantImpl createSchemaRoleSchemaGrantCubeGrantHierarchyGrantMemberGrant() {
        return new MemberGrantImpl();
    }

    public VirtualCubeDimensionImpl createSchemaVirtualCubeVirtualCubeDimension() {
        return new VirtualCubeDimensionImpl();
    }

    public VirtualCubeMeasureImpl createSchemaVirtualCubeVirtualCubeMeasure() {
        return new VirtualCubeMeasureImpl();
    }

    public CubeUsageImpl createSchemaVirtualCubeCubeUsagesCubeUsage() {
        return new CubeUsageImpl();
    }

    public MeasureImpl createSchemaCubeMeasure() {
        return new MeasureImpl();
    }

    public AnnotationImpl createAnnotationsAnnotation() {
        return new AnnotationImpl();
    }

}
