/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2002-2018 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.olap4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import mondrian.olap.Syntax;

import org.eclipse.daanse.olap.api.element.MetaElement;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.olap4j.Cell;
import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.OlapWrapper;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Member;
import org.olap4j.metadata.MetadataElement;
import org.olap4j.metadata.Schema;

import mondrian.olap.Category;
import mondrian.olap.FunctionTable;
import mondrian.olap.HierarchyBase;
import mondrian.olap.MondrianServer;
import mondrian.olap.Property;
import mondrian.olap.Util;
import mondrian.olap.Util.PropertyList;
import mondrian.olap.fun.FunctionInfo;
import mondrian.rolap.RolapAggregator;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapConnectionProperties;
import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapLevel;
import mondrian.rolap.RolapMemberBase;
import mondrian.rolap.RolapSchema;
import mondrian.xmla.RowsetDefinition;
import mondrian.xmla.XmlaHandler;

/**
 * Provides access to internals of mondrian's olap4j driver that are not part
 * of the olap4j API.
 *
 * @author jhyde
 * @since Nov 12, 2010
 */
class MondrianOlap4jExtra implements XmlaHandler.XmlaExtra {
    static final MondrianOlap4jExtra INSTANCE = new MondrianOlap4jExtra();

    @Override
	public ResultSet executeDrillthrough(
        OlapStatement olapStatement,
        String mdx,
        boolean advanced,
        String tabFields,
        int[] rowCountSlot) throws SQLException
    {
        return ((MondrianOlap4jStatement) olapStatement).executeQuery2(
            mdx,
            advanced,
            rowCountSlot);
    }

    @Override
	public void setPreferList(OlapConnection connection) {
        ((MondrianOlap4jConnection) connection).setPreferList(true);
    }

    @Override
	public Date getSchemaLoadDate(Schema schema) {
        return ((MondrianOlap4jSchema) schema).schema.getSchemaLoadDate();
    }

    @Override
	public int getLevelCardinality(Level level) throws OlapException {
        if (level instanceof MondrianOlap4jLevel olap4jLevel) {
            final mondrian.olap.SchemaReader schemaReader =
                olap4jLevel.olap4jSchema.olap4jCatalog.olap4jDatabaseMetaData
                    .olap4jConnection.getMondrianConnection().getSchemaReader()
                    .withLocus();
            return schemaReader.getLevelCardinality(
                olap4jLevel.level, true, true);
        } else {
            return level.getCardinality();
        }
    }

    @Override
	public void getSchemaFunctionList(
        List<FunctionDefinition> funDefs,
        Schema schema,
        Predicate<String> functionFilter)
    {
        final FunctionTable funTable =
            ((MondrianOlap4jSchema) schema).schema.getFunTable();
        StringBuilder buf = new StringBuilder(50);
        for (FunctionInfo fi : funTable.getFunctionInfos()) {
            if (Syntax.Empty.equals(fi.getSyntax())
                || Syntax.Internal.equals(fi.getSyntax())
                || Syntax.Parentheses.equals(fi.getSyntax())) {
                continue;
            }
            final Boolean passes = functionFilter.test(fi.getName());
            if (passes == null || !passes) {
                continue;
            }

            int[][] paramCategories = fi.getParameterCategories();
            int[] returnCategories = fi.getReturnCategories();

            // Convert Windows newlines in 'description' to UNIX format.
            String description = fi.getDescription();
            if (description != null) {
                description = fi.getDescription().replace(
                    "\r",
                    "");
            }
            if ((paramCategories == null)
                || (paramCategories.length == 0))
            {
                funDefs.add(
                    new FunctionDefinition(
                        fi.getName(),
                        description,
                        "(none)",
                        1,
                        1,
                        // TODO WHAT VALUE should this have
                        "",
                        fi.getName()));
            } else {
                for (int i = 0; i < paramCategories.length; i++) {
                    int[] pc = paramCategories[i];
                    int returnCategory = returnCategories[i];

                    buf.setLength(0);
                    for (int j = 0; j < pc.length; j++) {
                        int v = pc[j];
                        if (j > 0) {
                            buf.append(", ");
                        }
                        buf.append(
                            Category.instance.getDescription(
                                v & Category.MASK));
                    }

                    RowsetDefinition.MdschemaFunctionsRowset.VarType varType =
                        RowsetDefinition.MdschemaFunctionsRowset.VarType
                            .forCategory(returnCategory);
                    funDefs.add(
                        new FunctionDefinition(
                            fi.getName(),
                            description,
                            buf.toString(),
                            // TODO: currently FunInfo can not tell us which
                            // functions are MDX and which are UDFs.
                            varType.ordinal(),
                            1,
                            // TODO: Name of the interface for UDF and Group
                            // name for the MDX functions.
                            // TODO WHAT VALUE should this have
                            "",
                            fi.getName()));
                }
            }
        }
    }

    @Override
	public int getHierarchyCardinality(Hierarchy hierarchy)
        throws OlapException
    {
        final MondrianOlap4jHierarchy olap4jHierarchy =
            (MondrianOlap4jHierarchy) hierarchy;
        final mondrian.olap.SchemaReader schemaReader =
            olap4jHierarchy.olap4jSchema.olap4jCatalog.olap4jDatabaseMetaData
                .olap4jConnection.getMondrianConnection().getSchemaReader()
                .withLocus();
        return RolapMemberBase.getHierarchyCardinality(
            schemaReader, olap4jHierarchy.hierarchy);
    }

    @Override
	public int getHierarchyStructure(Hierarchy hierarchy) {
        final MondrianOlap4jHierarchy olap4jHierarchy =
            (MondrianOlap4jHierarchy) hierarchy;
        return ((HierarchyBase) olap4jHierarchy.hierarchy).isRagged() ? 1 : 0;
    }

    @Override
	public boolean isHierarchyParentChild(Hierarchy hierarchy) {
        Level nonAllFirstLevel = hierarchy.getLevels().get(0);
        if (nonAllFirstLevel.getLevelType() == Level.Type.ALL) {
            nonAllFirstLevel = hierarchy.getLevels().get(1);
        }
        MondrianOlap4jLevel olap4jLevel =
            (MondrianOlap4jLevel) nonAllFirstLevel;
        return ((RolapLevel) olap4jLevel.level).isParentChild();
    }

    @Override
	public String getMeasureDisplayFolder(Member member) {
        MondrianOlap4jMeasure olap4jMeasure =
                (MondrianOlap4jMeasure) member;
        return olap4jMeasure.getDisplayFolder();
    }

    @Override
	public int getMeasureAggregator(Member member) {
        MondrianOlap4jMeasure olap4jMeasure =
            (MondrianOlap4jMeasure) member;
        Object aggProp =
            olap4jMeasure.member.getPropertyValue(
                Property.AGGREGATION_TYPE.name);
        if (aggProp == null) {
            return
                RowsetDefinition.MdschemaMeasuresRowset
                    .MDMEASURE_AGGR_CALCULATED;
        }
        RolapAggregator agg = (RolapAggregator) aggProp;
        if (agg == RolapAggregator.Sum) {
            return RowsetDefinition.MdschemaMeasuresRowset.MDMEASURE_AGGR_SUM;
        } else if (agg == RolapAggregator.Count) {
            return RowsetDefinition.MdschemaMeasuresRowset.MDMEASURE_AGGR_COUNT;
        } else if (agg == RolapAggregator.Min) {
            return RowsetDefinition.MdschemaMeasuresRowset.MDMEASURE_AGGR_MIN;
        } else if (agg == RolapAggregator.Max) {
            return RowsetDefinition.MdschemaMeasuresRowset.MDMEASURE_AGGR_MAX;
        } else if (agg == RolapAggregator.Avg) {
            return RowsetDefinition.MdschemaMeasuresRowset.MDMEASURE_AGGR_AVG;
        } else {
            // TODO: what are VAR and STD
            return RowsetDefinition.MdschemaMeasuresRowset
                .MDMEASURE_AGGR_UNKNOWN;
        }
    }

    @Override
	public void checkMemberOrdinal(Member member, boolean caseSensitive) throws OlapException {
        if (member.getOrdinal() == -1) {
            MondrianOlap4jMember olap4jMember =
                (MondrianOlap4jMember) member;
            final mondrian.olap.SchemaReader schemaReader =
                olap4jMember.olap4jSchema.olap4jCatalog.olap4jDatabaseMetaData
                    .olap4jConnection.getMondrianConnection().getSchemaReader()
                    .withLocus();
            RolapMemberBase.setOrdinals(schemaReader, olap4jMember.member, caseSensitive);
        }
    }

    @Override
	public boolean shouldReturnCellProperty(
        CellSet cellSet,
        org.olap4j.metadata.Property cellProperty,
        boolean evenEmpty)
    {
        MondrianOlap4jCellSet olap4jCellSet = (MondrianOlap4jCellSet) cellSet;
        Query query = olap4jCellSet.query;
        return
            (evenEmpty
             && query.isCellPropertyEmpty())
            || query.hasCellProperty(cellProperty.getName());
    }

    @Override
	public List<String> getSchemaRoleNames(Schema schema) {
        MondrianOlap4jSchema olap4jSchema = (MondrianOlap4jSchema) schema;
        // TODO: this returns ALL roles, no the current user's roles
        return new ArrayList<>(
            ((RolapSchema) olap4jSchema.schema).roleNames());
    }

    @Override
	public String getSchemaId(Schema schema) {
        return ((MondrianOlap4jSchema)schema).schema.getId();
    }

    @Override
	public String getCubeType(Cube cube) {
        return
            (cube instanceof MondrianOlap4jCube)
            && ((RolapCube) ((MondrianOlap4jCube) cube).cube).isVirtual()
                ? RowsetDefinition.MdschemaCubesRowset.MD_CUBTYPE_VIRTUAL_CUBE
                : RowsetDefinition.MdschemaCubesRowset.MD_CUBTYPE_CUBE;
    }

    @Override
	public boolean isLevelUnique(Level level) {
        MondrianOlap4jLevel olap4jLevel = (MondrianOlap4jLevel) level;
        return (olap4jLevel.level instanceof RolapLevel rolapLevel)
            && rolapLevel.isUnique();
    }

    @Override
	public List<org.olap4j.metadata.Property> getLevelProperties(Level level) {
        MondrianOlap4jLevel olap4jLevel = (MondrianOlap4jLevel) level;
        return olap4jLevel.getProperties(false);
    }

    @Override
	public boolean isPropertyInternal(org.olap4j.metadata.Property property) {
        MondrianOlap4jProperty olap4jProperty =
            (MondrianOlap4jProperty) property;
        return olap4jProperty.property.isInternal();
    }

    @Override
	public List<Map<String, Object>> getDataSources(OlapConnection connection)
        throws OlapException
    {
        MondrianOlap4jConnection olap4jConnection =
            (MondrianOlap4jConnection) connection;
        MondrianServer server =
            MondrianServer.forConnection(
                olap4jConnection.getMondrianConnection());
        final List<Map<String, Object>> databases =
            server.getDatabases(olap4jConnection.getMondrianConnection());

        // We can't let JdbcPassword leak out of the public API, so we remove
        // it here. This is only called by the XMLA servlets.
        for (Map<String, Object> db : databases) {
            String dsi = (String) db.get("DataSourceInfo");

            if (dsi == null) {
                break;
            }

            PropertyList pl = Util.parseConnectString(dsi);

            boolean removed =
                pl.remove(RolapConnectionProperties.Jdbc.name());
            removed |= pl.remove(RolapConnectionProperties.JdbcUser.name());
            removed |= pl.remove(RolapConnectionProperties.JdbcPassword.name());

            if (removed) {
                db.put("DataSourceInfo", pl.toString());
            }
        }
        return databases;
    }

    @Override
	public Map<String, Object> getAnnotationMap(MetadataElement element)
        throws SQLException
    {
        if (element instanceof OlapWrapper wrapper && wrapper.isWrapperFor(MetaElement.class)) {
            final MetaElement annotated = wrapper.unwrap(MetaElement.class);
            final Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Object> entry
                : annotated.getMetadata().entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return map;
        }
        return Collections.emptyMap();
    }

    @Override
	public boolean canDrillThrough(Cell cell) {
        return ((MondrianOlap4jCell)cell).cell.canDrillThrough();
    }

    @Override
	public int getDrillThroughCount(Cell cell) {
        return ((MondrianOlap4jCell)cell).cell.getDrillThroughCount();
    }

    @Override
	public void flushSchemaCache(OlapConnection conn) throws OlapException {
        try {
            RolapConnection rConn = conn.unwrap(RolapConnection.class);
            rConn.getCacheControl(null).flushSchema(
                rConn.getSchema());
        } catch (SQLException e) {
            throw new OlapException(e);
        }
    }

    @Override
	public Object getMemberKey(Member m) throws OlapException {
        try {
            return ((MondrianOlap4jMember)m)
                .unwrap(RolapMemberBase.class).getKey();
        } catch (SQLException e) {
            throw new OlapException(e);
        }
    }

    @Override
	public Object getOrderKey(Member m) throws OlapException {
        try {
            return ((MondrianOlap4jMember)m)
                .unwrap(RolapMemberBase.class).getOrderKey();
        } catch (SQLException e) {
            throw new OlapException(e);
        }
    }

    @Override
	public String getLevelDataType( Level level ) {
        MondrianOlap4jLevel olap4jLevel = (MondrianOlap4jLevel) level;
        if ( olap4jLevel.level instanceof RolapLevel rolapLevel) {
            return rolapLevel.getDatatype().getValue();

        }
        return null;
    }
}
