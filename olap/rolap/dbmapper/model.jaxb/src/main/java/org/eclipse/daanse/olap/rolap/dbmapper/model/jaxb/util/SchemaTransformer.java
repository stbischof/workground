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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.util;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.CubeImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.DimensionUsageImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.HierarchyImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.JoinImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.LevelImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.MeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.PrivateDimensionImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.SchemaImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.TableImpl;

import java.util.List;

public class SchemaTransformer {

    private SchemaTransformer() {
        // constructor
    }

    public static SchemaImpl transformSchema(MappingSchema s) {
        SchemaImpl sch = new SchemaImpl();
        sch.setName(s.name());
        sch.setDescription(s.description());
        sch.setMeasuresCaption(s.measuresCaption());
//        sch.setDefaultRole(s.defaultRole());
        //TODO
        sch.setAnnotations(null);
        sch.setParameters(List.of());
        sch.setDimensions(s.dimensions() == null ? null :
            s.dimensions().stream().map(SchemaTransformer::transformPrivateDimension).toList());
        sch.setCubes(s.cubes() == null ? null : s.cubes().stream().map(SchemaTransformer::transformCube).toList());
        sch.setNamedSets(List.of());
        sch.setRoles(List.of());
        sch.setUserDefinedFunctions(List.of());
        return sch;
    }

    private static MappingCube transformCube(MappingCube c) {
        CubeImpl cube = new CubeImpl();
        cube.setName(c.name());
        cube.setCaption(c.caption());
        cube.setDescription(c.description());
        cube.setDefaultMeasure(c.defaultMeasure());
        cube.setAnnotations(null);
        cube.setDimensionUsageOrDimensions(c.dimensionUsageOrDimensions() == null ? null :
            c.dimensionUsageOrDimensions().stream()
                .map(SchemaTransformer::transformDimensionUsageOrDimensions).toList());
        cube.setMeasures(c.measures() == null ? null :
            c.measures().stream().map(SchemaTransformer::transformMeasure).toList());
        cube.setCalculatedMembers(List.of());
        cube.setNamedSets(List.of());
        cube.setDrillThroughActions(List.of());
        cube.setWritebackTable(null);
        cube.setEnabled(c.enabled());
        cube.setCache(c.cache());
        cube.setVisible(c.visible());
        cube.setFact(transformRelation(c.fact()));
        cube.setActions(List.of());
        return cube;
    }

    private static MappingMeasure transformMeasure(MappingMeasure m) {
        MeasureImpl measure = new MeasureImpl();
        measure.setName(m.name());
        measure.setColumn(m.column());
        measure.setDatatype(m.datatype());
        measure.setFormatString(m.formatString());
        measure.setAggregator(m.aggregator());
        measure.setFormatter(m.formatter());
        measure.setCaption(m.caption());
        measure.setDescription(m.description());
        measure.setVisible(m.visible());
        measure.setDisplayFolder(m.displayFolder());
        measure.setAnnotations(null);
        //TODO
        measure.setMeasureExpression(null);
        measure.setCalculatedMemberProperties(List.of());
        measure.setCellFormatter(null);
        measure.setBackColor(m.backColor());
        return measure;
    }

    private static MappingCubeDimension transformDimensionUsageOrDimensions(MappingCubeDimension d) {
        MappingCubeDimension cd = null;
        if (d instanceof MappingDimensionUsage du) {
            DimensionUsageImpl dimensionUsage = new DimensionUsageImpl();
            dimensionUsage.setName(du.name());
            dimensionUsage.setSource(du.source());
            dimensionUsage.setLevel(du.level());
            dimensionUsage.setUsagePrefix(du.usagePrefix());
            dimensionUsage.setForeignKey(du.foreignKey());
            //TODO
            dimensionUsage.setAnnotations(null);
            dimensionUsage.setCaption(du.caption());
            dimensionUsage.setVisible(du.visible());
            dimensionUsage.setDescription(du.description());
            cd = dimensionUsage;
        }
        if (d instanceof MappingPrivateDimension pd) {
            cd = transformPrivateDimension(pd);
        }
        return cd;
    }

    public static MappingPrivateDimension transformPrivateDimension(MappingPrivateDimension d) {
        PrivateDimensionImpl privateDimension = new PrivateDimensionImpl();
        privateDimension.setCaption(d.caption());
        privateDimension.setDescription(d.description());
        privateDimension.setName(d.name());
        privateDimension.setForeignKey(d.foreignKey());
        privateDimension.setType(d.type());
        privateDimension.setVisible(d.visible());
        privateDimension.setHierarchies(d.hierarchies() == null ? null :
            d.hierarchies().stream().map(SchemaTransformer::transformHierarchy).toList());
        return privateDimension;
    }

    public static MappingHierarchy transformHierarchy(MappingHierarchy h) {
        HierarchyImpl hierarchyImpl = new HierarchyImpl();
        hierarchyImpl.setName(h.name());
        hierarchyImpl.setCaption(h.caption());
        hierarchyImpl.setDescription(h.description());
        hierarchyImpl.setAnnotations(null);
        hierarchyImpl.setLevels(h.levels() == null ? null :
            h.levels().stream().map(SchemaTransformer::transformLevel).toList());
        hierarchyImpl.setHasAll(h.hasAll());
        hierarchyImpl.setAllMemberName(h.allMemberName());
        hierarchyImpl.setAllMemberCaption(h.allMemberCaption());
        hierarchyImpl.setAllLevelName(h.allLevelName());
        hierarchyImpl.setPrimaryKey(h.primaryKey());
        hierarchyImpl.setPrimaryKeyTable(h.primaryKeyTable());
        hierarchyImpl.setDefaultMember(h.defaultMember());
        hierarchyImpl.setMemberReaderClass(h.memberReaderClass());
        hierarchyImpl.setUniqueKeyLevelName(h.uniqueKeyLevelName());
        hierarchyImpl.setVisible(h.visible());
        hierarchyImpl.setDisplayFolder(h.displayFolder());
        hierarchyImpl.setRelation(transformRelationOrJoin(h.relation()));
        hierarchyImpl.setOrigin(h.origin());
        return hierarchyImpl;
    }

    private static MappingRelation transformRelation(MappingRelation relation) {
        MappingRelation r = null;
        if (relation instanceof MappingTable t) {
            r = transformTable(t);
        }
        return r;
    }

    private static MappingRelationOrJoin transformRelationOrJoin(MappingRelationOrJoin relation) {
        MappingRelationOrJoin r = null;
        if (relation instanceof MappingJoin j) {
            r = transformJoin(j);
        }
        if (relation instanceof MappingTable t) {
            r = transformTable(t);
        }
        return r;
    }

    private static TableImpl transformTable(MappingTable t) {
        TableImpl table = new TableImpl();
        table.setAlias(t.alias());
        table.setSchema(t.schema());
        table.setName(t.name());
        //TODO use transformer for others fields
        return table;
    }

    private static JoinImpl transformJoin(MappingJoin j) {
        JoinImpl join = new JoinImpl();
        join.setLeftAlias(j.leftAlias());
        join.setLeftKey(j.leftKey());
        join.setRightAlias(j.rightAlias());
        join.setRightKey(j.rightKey());
        join.setRelations(
            j.relations() == null ? null : j.relations().stream()
                .map(SchemaTransformer::transformRelationOrJoin).toList());
        return join;
    }

    private static MappingLevel transformLevel(MappingLevel l) {
        LevelImpl level = new LevelImpl();
        level.setName(l.name());
        level.setTable(l.table());
        level.setColumn(l.column());
        level.setNameColumn(l.nameColumn());
        level.setOrdinalColumn(l.ordinalColumn());
        level.setParentColumn(l.parentColumn());
        level.setNullParentValue(l.nullParentValue());
        level.setType(l.type());
        level.setApproxRowCount(l.approxRowCount());
        level.setUniqueMembers(l.uniqueMembers());
        level.setLevelType(l.levelType());
        level.setHideMemberIf(l.hideMemberIf());
        level.setFormatter(l.formatter());
        level.setCaption(l.caption());
        level.setDescription(l.description());
        level.setCaptionColumn(l.caption());
        level.setAnnotations(null);
        level.setKeyExpression(null);
        level.setNameExpression(null);
        level.setCaptionExpression(null);
        level.setOrdinalExpression(null);
        level.setParentExpression(null);
        level.setClosure(null);
        level.setProperties(null);
        level.setVisible(l.visible());
        level.setInternalType(l.internalType());
        level.setMemberFormatter(null);
        return level;
    }
}
