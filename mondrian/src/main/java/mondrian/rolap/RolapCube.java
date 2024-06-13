/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2021 Hitachi Vantara and others
// Copyright (C) 2021-2022 Sergei Semenkov
// All Rights Reserved.
*/
package mondrian.rolap;

import static mondrian.rolap.util.CalculatedMemberUtil.getFormatString;
import static mondrian.rolap.util.CalculatedMemberUtil.getFormula;
import static mondrian.rolap.util.JoinUtil.changeLeftRight;
import static mondrian.rolap.util.JoinUtil.getLeftAlias;
import static mondrian.rolap.util.JoinUtil.getRightAlias;
import static mondrian.rolap.util.JoinUtil.left;
import static mondrian.rolap.util.JoinUtil.right;
import static mondrian.rolap.util.RelationUtil.getAlias;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import mondrian.olap.exceptions.BadMeasureSourceException;
import mondrian.olap.exceptions.CalcMemberNotUniqueException;
import org.eclipse.daanse.olap.api.CacheControl;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.DataType;
import org.eclipse.daanse.olap.api.DrillThroughAction;
import org.eclipse.daanse.olap.api.DrillThroughColumn;
import org.eclipse.daanse.olap.api.Evaluator;
import org.eclipse.daanse.olap.api.MatchType;
import org.eclipse.daanse.olap.api.NameSegment;
import org.eclipse.daanse.olap.api.OlapAction;
import org.eclipse.daanse.olap.api.Parameter;
import org.eclipse.daanse.olap.api.Quoting;
import org.eclipse.daanse.olap.api.SchemaReader;
import org.eclipse.daanse.olap.api.Segment;
import org.eclipse.daanse.olap.api.Statement;
import org.eclipse.daanse.olap.api.access.Access;
import org.eclipse.daanse.olap.api.access.Role;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.NamedSet;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.api.query.component.CellProperty;
import org.eclipse.daanse.olap.api.query.component.Expression;
import org.eclipse.daanse.olap.api.query.component.Formula;
import org.eclipse.daanse.olap.api.query.component.MemberExpression;
import org.eclipse.daanse.olap.api.query.component.MemberProperty;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.query.component.ResolvedFunCall;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.function.AbstractFunctionDefinition;
import org.eclipse.daanse.olap.impl.IdentifierNode;
import org.eclipse.daanse.olap.impl.IdentifierSegment;
import org.eclipse.daanse.olap.impl.ScenarioImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMemberProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingExpression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCubeMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.AnnotationImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb.MeasureImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.ColumnR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.InlineTableR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.JoinR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.TableR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.mdx.MdxVisitorImpl;
import mondrian.mdx.ResolvedFunCallImpl;
import mondrian.olap.CubeBase;
import mondrian.olap.DimensionType;
import mondrian.olap.FormulaImpl;
import mondrian.olap.IdImpl;
import mondrian.olap.MondrianException;
import mondrian.olap.NameResolverImpl;
import mondrian.olap.Property;
import mondrian.olap.QueryAxisImpl;
import mondrian.olap.QueryImpl;
import mondrian.olap.RoleImpl;
import mondrian.olap.SetBase;
import mondrian.olap.SystemWideProperties;
import mondrian.olap.Util;
import mondrian.rolap.aggmatcher.ExplicitRules;
import mondrian.rolap.cache.SoftSmartCache;
import mondrian.rolap.format.FormatterCreateContext;
import mondrian.rolap.format.FormatterFactory;
import mondrian.rolap.util.DimensionUtil;
import mondrian.rolap.util.NamedSetUtil;
import mondrian.server.LocusImpl;
import mondrian.spi.CellFormatter;

/**
 * <code>RolapCube</code> implements {@link Cube} for a ROLAP database.
 *
 * @author jhyde
 * @since 10 August, 2001
 */
public class RolapCube extends CubeBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolapCube.class);
    public static final String BAD_RELATION_TYPE = "bad relation type ";

    private final RolapSchema schema;
    private final Map<String, Object> metadata;
    private final RolapHierarchy measuresHierarchy;

    /** For SQL generator. Fact table. */
    private MappingRelation fact;

    /** Schema reader which can see this cube and nothing else. */
    private SchemaReader schemaReader;

    /**
     * List of calculated members.
     */
    private final List<Formula> calculatedMemberList = new ArrayList<>();

    /**
     * Role-based cache of calculated members
     */
    private final SoftSmartCache<Role, List<Member>>
        roleToAccessibleCalculatedMembers =
        new SoftSmartCache<>();

    /**
     * List of named sets.
     */
    private final List<Formula> namedSetList = new ArrayList<>();

    /** Contains {@link HierarchyUsage}s for this cube */
    private final List<HierarchyUsage> hierarchyUsages;

    private RolapStar star;
    private ExplicitRules.Group aggGroup;

    private final Map<Hierarchy, HierarchyUsage> firstUsageMap =
        new HashMap<>();

    /**
     * Refers {@link RolapCubeUsages} if this is a virtual cube
     */
    private RolapCubeUsages cubeUsages;

    public RolapBaseCubeMeasure factCountMeasure;

    final List<RolapHierarchy> hierarchyList =
        new ArrayList<>();

    /**
     * Set to true when a cube is being modified after creation.
     *
     * @see #isLoadInProgress()
     */
    private boolean loadInProgress = false;

    private Map<RolapLevel, RolapCubeLevel> virtualToBaseMap =
        new HashMap<>();

    final BitKey closureColumnBitKey;

    final List<AbstractRolapAction> actionList =
            new ArrayList<>();

    private Optional<RolapWritebackTable> writebackTable = Optional.empty();

    /**
     * Used for virtual cubes.
     * Contains a list of all base cubes related to a virtual cube
     */
    private List<RolapCube> baseCubes;
    private Context context;
    private final static String noTimeDimensionInCube =
        "Cannot use the function ''{0}'', no time dimension is available for this cube.";
    private final static String calcMemberHasBadDimension =
        "Unknown dimension ''{0}'' for calculated member ''{1}'' in cube ''{2}''";
    private final static String calcMemberHasBothDimensionAndHierarchy =
        "Cannot specify both a dimension and hierarchy for calculated member ''{0}'' in cube ''{1}''";
    private final static String calcMemberHasDifferentParentAndHierarchy =
        "The calculated member ''{0}'' in cube ''{1}'' is defined for hierarchy ''{2}'' but its parent member is not part of that hierarchy";
    private final static String calcMemberHasUnknownParent =
        "Cannot find a parent with name ''{0}'' for calculated member ''{1}'' in cube ''{2}''";
    private final static String exprAndValueForMemberProperty =
        "Member property must not have both a value and an expression. (Property ''{0}'' of member ''{1}'' of cube ''{2}''.)";
    private final static String hierarchyInvalidForeignKey =
        "Foreign key ''{0}'' of hierarchy ''{1}'' in cube ''{2}'' is not a column in the fact table.";
    private final static String hierarchyMustHaveForeignKey =
        "Hierarchy ''{0}'' in cube ''{1}'' must have a foreign key, since it is not based on the cube''s fact table.";
    private final static String measureOrdinalsNotUnique =
        "Cube ''{0}'': Ordinal {1} is not unique: ''{2}'' and ''{3}''";
    private final static String namedSetNotUnique = "Named set ''{0}'' already exists in cube ''{1}''";
    private final static String neitherExprNorValueForCalcMemberProperty =
        "Member property must have a value or an expression. (Property ''{0}'' of member ''{1}'' of cube ''{2}''.)";
    private final static String unknownNamedSetHasBadFormula = "Named set in cube ''{0}'' has bad formula";

    /**
     * Private constructor used by both normal cubes and virtual cubes.
     *
     * @param schema Schema cube belongs to
     * @param name Name of cube
     * @param caption Caption
     * @param description Description
     * @param fact Definition of fact table*
     * @param metadata Annotations
     */
    private RolapCube(
        RolapSchema schema,
        MappingSchema mappingSchema,
        String name,
        boolean visible,
        String caption,
        String description,
        boolean isCache,
        MappingRelation fact,
        List<? extends MappingCubeDimension> dimensions,
        Map<String, Object> metadata,
        Context context)
    {
        super(
            name,
            caption,
            visible,
            description,
            new RolapDimension[dimensions.size() + 1]);

        assert metadata != null;
        this.schema = schema;
        this.metadata = metadata;
        this.caption = caption;
        this.fact = fact;
        this.hierarchyUsages = new ArrayList<>();
        this.context = context;

        if (! isVirtual()) {
            this.star = schema.getRolapStarRegistry().getOrCreateStar(getFact());
            // only set if different from default (so that if two cubes share
            // the same fact table, either can turn off caching and both are
            // effected).
            if (! isCache) {
                star.setCacheAggregations(isCache);
            }
        }

        if (getLogger().isDebugEnabled()) {
            if (isVirtual()) {
                String msg = new StringBuilder("RolapCube<init>: virtual cube=").append(this.name).toString();
                getLogger().debug(msg);
            } else {
                String msg = new StringBuilder("RolapCube<init>: cube=").append(this.name).toString();
                getLogger().debug(msg);
            }
        }

        RolapDimension measuresDimension =
            new RolapDimension(
                schema,
                Dimension.MEASURES_NAME,
                null,
                true,
                null,
                DimensionType.MEASURES_DIMENSION,
                Map.of());

        this.dimensions[0] = measuresDimension;

        this.measuresHierarchy =
            measuresDimension.newHierarchy(null, false, null);
        hierarchyList.add(measuresHierarchy);

        if (!Util.isEmpty(mappingSchema.measuresCaption())) {
            measuresDimension.setCaption(mappingSchema.measuresCaption());
            this.measuresHierarchy.setCaption(mappingSchema.measuresCaption());
        }

        for (int i = 0; i < dimensions.size(); i++) {
            MappingCubeDimension mappingCubeDimension = dimensions.get(i);

            // Look up usages of shared dimensions in the schema before
            // consulting the XML schema (which may be null).
            RolapCubeDimension dimension =
                getOrCreateDimension(
                    mappingCubeDimension, schema, mappingSchema, i + 1, hierarchyList);
            if (getLogger().isDebugEnabled()) {
                String msg = new StringBuilder("RolapCube<init>: dimension=").append(dimension.getName()).toString();
                getLogger().debug(msg);
            }
            this.dimensions[i + 1] = dimension;

            if (! isVirtual()) {
                createUsages(dimension, mappingCubeDimension);
            }

            // the register Dimension call was moved here
            // to keep the RolapStar in sync with the realiasing
            // within the RolapCubeHierarchy objects.
            registerDimension(dimension);
        }

        // Initialize closure bit key only when we know how many columns are in
        // the star.
        if (! isVirtual()) {
            closureColumnBitKey =
                BitKey.Factory.makeBitKey(star.getColumnCount());
        } else {
            closureColumnBitKey = null;
        }

        schema.addCube(this);
    }

    /**
     * Creates a <code>RolapCube</code> from a regular cube.
     */
    RolapCube(
        RolapSchema schema,
        MappingSchema mappingSchema,
        MappingCube mappingCube,
        Context context)
    {
        this(
            schema,
            mappingSchema,
            mappingCube.name(),
            mappingCube.visible(),
            mappingCube.caption(),
            mappingCube.description(),
            mappingCube.cache(),
            mappingCube.fact(),
            mappingCube.dimensionUsageOrDimensions(),
            RolapHierarchy.createMetadataMap(mappingCube.annotations()), context);

        if (getFact() == null) {
            throw Util.newError(
                new StringBuilder("Must specify fact table of cube '").append(getName()).append("'").toString());
        }

        if (getAlias(getFact()) == null) {
            throw Util.newError(
                new StringBuilder("Must specify alias for fact table of cube '").append(getName())
                .append("'").toString());
        }

        // since Measure and VirtualCubeMeasure
        // can not be treated as the same, measure creation can not be
        // done in a common constructor.
        RolapLevel measuresLevel = this.measuresHierarchy.newMeasuresLevel();

        List<RolapMember> measureList =
            new ArrayList<>(mappingCube.measures().size());
        Member defaultMeasure = null;
        for (int i = 0; i < mappingCube.measures().size(); i++) {
            RolapBaseCubeMeasure measure =
                createMeasure(mappingCube, measuresLevel, i, mappingCube.measures().get(i));
            measureList.add(measure);

            // Is this the default measure?
            if (Util.equalName(measure.getName(), mappingCube.defaultMeasure())) {
                defaultMeasure = measure;
            }

            if (measure.getAggregator() == RolapAggregator.Count) {
                factCountMeasure = measure;
            }
        }

        boolean writebackEnabled = false;
        for (RolapHierarchy hierarchy : hierarchyList) {
            if (ScenarioImpl.isScenario(hierarchy)) {
                writebackEnabled = true;
            }
        }

        // Ensure that cube has an atomic cell count
        // measure even if the schema does not contain one.
        if (factCountMeasure == null) {
            AnnotationImpl internalUsage = new AnnotationImpl();
            internalUsage.setName("Internal Use");
            internalUsage.setContent("For internal use");
            List<MappingAnnotation> annotations = new ArrayList<>();
            annotations.add(internalUsage);
            final MeasureImpl mappingMeasure = new MeasureImpl();
            mappingMeasure.setName("Fact Count");
            mappingMeasure.setAggregator("count");
            mappingMeasure.setVisible(false);
            mappingMeasure.setAnnotations(annotations);
            factCountMeasure =
                createMeasure(
                    mappingCube, measuresLevel, measureList.size(), mappingMeasure);
            measureList.add(factCountMeasure);
        }

        setMeasuresHierarchyMemberReader(
            new CacheMemberReader(
                new MeasureMemberSource(this.measuresHierarchy, measureList)));

        this.measuresHierarchy.setDefaultMember(defaultMeasure);
        init(mappingCube.dimensionUsageOrDimensions());
        init(mappingCube, measureList);

        setMeasuresHierarchyMemberReader(
            new CacheMemberReader(
                new MeasureMemberSource(this.measuresHierarchy, measureList)));

        checkOrdinals(mappingCube.name(), measureList);
        loadAggGroup(mappingCube);

        for(MappingAction mappingAction: mappingCube.drillThroughActions()) {
            if(mappingAction instanceof MappingDrillThroughAction mappingDrillThroughAction) {
                List<DrillThroughColumn> columns = new ArrayList<>();

                for(MappingDrillThroughElement mappinmgDrillThroughColumn: mappingDrillThroughAction.drillThroughElements()) {
                    if(mappinmgDrillThroughColumn instanceof MappingDrillThroughAttribute mappingDrillThroughAttribute) {
                        Dimension dimension = null;
                        Hierarchy hierarchy = null;
                        Level level = null;
                        RolapProperty property = null;
                        for(Dimension currntDimension: this.getDimensions()) {
                            if(currntDimension.getName().equals(mappingDrillThroughAttribute.dimension())) {
                                dimension = currntDimension;
                                break;
                            }
                        }
                        if(dimension == null) {
                            throw Util.newError(
                                    new StringBuilder("Error while creating DrillThrough  action. Dimension '")
                                        .append(mappingDrillThroughAttribute.dimension()).append("' not found").toString());
                        }
                        else {
                            if(mappingDrillThroughAttribute.hierarchy() != null && !mappingDrillThroughAttribute.hierarchy().equals("")) {
                                for(Hierarchy currentHierarchy: dimension.getHierarchies()) {
                                    if(currentHierarchy instanceof RolapCubeHierarchy rolapCubeHierarchy
                                        && rolapCubeHierarchy.getSubName().equals(mappingDrillThroughAttribute.hierarchy())) {
                                        hierarchy = currentHierarchy;
                                        break;
                                    }
                                }
                                if(hierarchy == null) {
                                    throw Util.newError(
                                            new StringBuilder("Error while creating DrillThrough  action. Hierarchy '")
                                                .append(mappingDrillThroughAttribute.hierarchy())
                                                .append("' not found").toString());
                                }
                                else {
                                    if(mappingDrillThroughAttribute.level() != null && !mappingDrillThroughAttribute.level().equals("")) {
                                        for(Level currentLevel: hierarchy.getLevels()) {
                                            if(currentLevel.getName().equals(mappingDrillThroughAttribute.level())) {
                                                level = currentLevel;
                                                break;
                                            }
                                        }
                                        if(level == null) {
                                            throw Util.newError(
                                                    new StringBuilder("Error while creating DrillThrough  action. Level '")
                                                        .append(mappingDrillThroughAttribute.level())
                                                        .append("' not found").toString());
                                        } else {
                                            if(mappingDrillThroughAttribute.property() != null && !mappingDrillThroughAttribute.property().equals("")) {
                                                for(Property currentProperty: level.getProperties()) {
                                                    if(currentProperty instanceof RolapProperty rolapProperty
                                                        &&  currentProperty.getName().equals(mappingDrillThroughAttribute.property())) {
                                                        property = rolapProperty;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }

                        columns.add(
                                new RolapDrillThroughAttribute(
                                        dimension,
                                        hierarchy,
                                        level, property
                                )
                        );

                    }
                    else if(mappinmgDrillThroughColumn instanceof MappingDrillThroughMeasure drillThroughMeasure) {
                        Member measure = null;
                        for(Member currntMeasure: this.getMeasures()) {
                            if(currntMeasure.getName().equals(drillThroughMeasure.name())) {
                                measure = currntMeasure;
                                break;
                            }
                        }
                        if(measure == null) {
                            throw Util.newError(
                                    new StringBuilder("Error while creating DrillThrough  action. Measure '")
                                            .append(drillThroughMeasure.name()).append("' not found").toString());
                        }
                        columns.add(
                                new RolapDrillThroughMeasure(measure)
                        );
                    }
                }

                RolapDrillThroughAction rolapDrillThroughAction = new RolapDrillThroughAction(
                        mappingDrillThroughAction.name(),
                        mappingDrillThroughAction.caption(),
                        mappingDrillThroughAction.description(),
                        mappingDrillThroughAction.defaultt() != null && mappingDrillThroughAction.defaultt(),
                        columns
                );
                this.actionList.add(rolapDrillThroughAction);
            }
        }

        if (mappingCube.writebackTable() != null && mappingCube.writebackTable().isPresent()) {
            MappingWritebackTable writebackTable = mappingCube.writebackTable().get();
            List<RolapWritebackColumn> columns = new ArrayList<>();

            for(MappingWritebackColumn writebackColumn: writebackTable.columns()) {
                if(writebackColumn instanceof MappingWritebackAttribute writebackAttribute) {
                    Dimension dimension = null;
                    for(Dimension currentDimension: this.getDimensions()) {
                        if(currentDimension.getName().equals(writebackAttribute.dimension())) {
                            dimension = currentDimension;
                            break;
                        }
                    }
                    if(dimension == null) {
                        throw Util.newError(
                            new StringBuilder("Error while creating `WritebackTable`. Dimension '")
                                        .append(writebackAttribute.dimension()).append("' not found").toString());
                    }

                    columns.add(
                            new RolapWritebackAttribute(
                                    dimension,
                                    writebackAttribute.column()
                            )
                    );

                }
                else if(writebackColumn instanceof MappingWritebackMeasure writebackMeasure) {
                    Member measure = null;
                    for(Member currentMeasure: this.getMeasures()) {
                        if(currentMeasure.getName().equals(writebackMeasure.name())) {
                            measure = currentMeasure;
                            break;
                        }
                    }
                    if(measure == null) {
                        throw Util.newError(
                            new StringBuilder("Error while creating DrillThrough  action. Measure '")
                                        .append(writebackMeasure.name()).append("' not found").toString());
                    }
                    columns.add(
                            new RolapWritebackMeasure(
                                    measure,
                                    writebackMeasure.column())
                    );
                }
            }

            RolapWritebackTable rolapWritebackTable = new RolapWritebackTable(
                    writebackTable.name(),
                    writebackTable.schema(),
                    columns
            );
            this.writebackTable = Optional.of(rolapWritebackTable);
        }
    }

    /**
     * Creates a measure.
     *
     * @param mappingCube XML cube
     * @param measuresLevel Member that all measures belong to
     * @param ordinal Ordinal of measure
     * @param mappingMeasure XML measure
     * @return Measure
     */
    private RolapBaseCubeMeasure createMeasure(
        MappingCube mappingCube,
        RolapLevel measuresLevel,
        int ordinal,
        final MappingMeasure mappingMeasure)
    {
        MappingExpression measureExp;
        if (mappingMeasure.column() != null) {
            if (mappingMeasure.measureExpression() != null) {
                throw new BadMeasureSourceException(
                    mappingCube.name(), mappingMeasure.name());
            }
            measureExp = new ColumnR(
                getAlias(getFact()), mappingMeasure.column());
        } else if (mappingMeasure.measureExpression() != null) {
            measureExp = mappingMeasure.measureExpression();
        } else if (mappingMeasure.aggregator().equals("count")) {
            // it's ok if count has no expression; it means 'count(*)'
            measureExp = null;
        } else {
            throw new BadMeasureSourceException(
                mappingCube.name(), mappingMeasure.name());
        }

        // Validate aggregator name. Substitute deprecated "distinct count"
        // with modern "distinct-count".
        String aggregator = mappingMeasure.aggregator();
        if (aggregator.equals("distinct count")) {
            aggregator = RolapAggregator.DistinctCount.getName();
        }
        final RolapBaseCubeMeasure measure =
            new RolapBaseCubeMeasure(
                this, null, measuresLevel, mappingMeasure.name(),
                mappingMeasure.caption(), mappingMeasure.description(),
                mappingMeasure.formatString(), measureExp,
                aggregator, mappingMeasure.datatype(),
                RolapHierarchy.createMetadataMap(mappingMeasure.annotations()));

        FormatterCreateContext formatterContext =
                new FormatterCreateContext.Builder(measure.getUniqueName())
                    .formatterDef(mappingMeasure.cellFormatter())
                    .formatterAttr(mappingMeasure.formatter())
                    .build();
        CellFormatter cellFormatter =
            FormatterFactory.instance()
                .createCellFormatter(formatterContext);
        if (cellFormatter != null) {
            measure.setFormatter(cellFormatter);
        }

        // Set member's caption, if present.
        if (!Util.isEmpty(mappingMeasure.caption())) {
            // there is a special caption string
            measure.setProperty(
                Property.CAPTION.name,
                mappingMeasure.caption());
        }

        // Set member's visibility, default true.
        Boolean visible = mappingMeasure.visible();
        if (visible == null) {
            visible = Boolean.TRUE;
        }
        measure.setProperty(Property.VISIBLE.name, visible);

        measure.setProperty(Property.DISPLAY_FOLDER.name, mappingMeasure.displayFolder());

        measure.setProperty(Property.BACK_COLOR.name, mappingMeasure.backColor());

        List<String> propNames = new ArrayList<>();
        List<String> propExprs = new ArrayList<>();
        validateMemberProps(
            mappingMeasure.calculatedMemberProperties(), propNames, propExprs, mappingMeasure.name());
        for (int j = 0; j < propNames.size(); j++) {
            String propName = propNames.get(j);
            final Object propExpr = propExprs.get(j);
            measure.setProperty(propName, propExpr);
            if (propName.equals(Property.MEMBER_ORDINAL.name)
                && propExpr instanceof String expr && expr.startsWith("\"")
                && expr.endsWith("\""))
            {
                try {
                    ordinal =
                        Integer.valueOf(
                            expr.substring(1, expr.length() - 1));
                } catch (NumberFormatException e) {
                	e.printStackTrace();
//                    discard(e);
                }
            }
        }
        measure.setOrdinal(ordinal);
        return measure;
    }

    /**
     * Makes sure that the schemaReader cache is invalidated.
     * Problems can occur if the measure hierarchy member reader is out
     * of sync with the cache.
     *
     * @param memberReader new member reader for measures hierarchy
     */
    private void setMeasuresHierarchyMemberReader(MemberReader memberReader) {
        this.measuresHierarchy.setMemberReader(memberReader);
        // this invalidates any cached schema reader
        this.schemaReader = null;
    }

    /**
     * Creates a <code>RolapCube</code> from a virtual cube.
     */
    RolapCube(
        RolapSchema schema,
        MappingSchema mappingSchema,
        MappingVirtualCube mappingVirtualCube,
        Context context)
    {
        this(
            schema,
            mappingSchema,
            mappingVirtualCube.name(),
            mappingVirtualCube.visible(),
            mappingVirtualCube.caption(),
            mappingVirtualCube.description(),
            true,
            null,
            mappingVirtualCube.virtualCubeDimensions(),
            RolapHierarchy.createMetadataMap(mappingVirtualCube.annotations()),
            context);

        // Since Measure and VirtualCubeMeasure cannot
        // be treated as the same, measure creation cannot be done in a common
        // constructor.
        RolapLevel measuresLevel = this.measuresHierarchy.newMeasuresLevel();

        // Recreate CalculatedMembers, as the original members point to
        // incorrect dimensional ordinals for the virtual cube.
        List<RolapVirtualCubeMeasure> origMeasureList =
            new ArrayList<>();
        List<MappingCalculatedMember> origCalcMeasureList =
            new ArrayList<>();
        CubeComparator cubeComparator = new CubeComparator();
        Map<RolapCube, List<MappingCalculatedMember>>
            calculatedMembersMap =
            new TreeMap<>(
                cubeComparator);
        Member defaultMeasure = null;

        this.cubeUsages = new RolapCubeUsages(mappingVirtualCube.cubeUsages());

        HashMap<String, MappingVirtualCubeMeasure> measureHash = new HashMap<>();

        for (MappingVirtualCubeMeasure mappingMeasure
            : mappingVirtualCube.virtualCubeMeasures())
        {
        	measureHash.put(mappingMeasure.name(), mappingMeasure);

            // Lookup a measure in an existing cube.
            RolapCube cube = schema.lookupCube(mappingMeasure.cubeName());
            if (cube == null) {
                throw Util.newError(
                    new StringBuilder("Cube '").append(mappingMeasure.cubeName()).append("' not found").toString());
            }
            List<Member> cubeMeasures = cube.getMeasures();
            boolean found = false;
            boolean isDefaultMeasureFound = false;
            for (Member cubeMeasure : cubeMeasures) {
                if (cubeMeasure.getUniqueName().equals(mappingMeasure.name())) {
                    if (cubeMeasure.getName().equalsIgnoreCase(
                            mappingVirtualCube.defaultMeasure()))
                    {
                      defaultMeasure = cubeMeasure;
                      isDefaultMeasureFound = true;
                    }
                    found = true;
                    if (cubeMeasure instanceof RolapCalculatedMember) {
                        // We have a calculated member!  Keep track of which
                        // base cube each calculated member is associated
                        // with, so we can resolve the calculated member
                        // relative to its base cube.  We're using a treeMap
                        // to store the mapping to ensure a deterministic
                        // order for the members.
                        MappingCalculatedMember calcMember =
                            schema.lookupXmlCalculatedMember(
                                mappingMeasure.name(), mappingMeasure.cubeName());
                        if (calcMember == null) {
                            throw Util.newInternal(
                                new StringBuilder("Could not find XML Calculated Member '")
                                .append(mappingMeasure.name()).append("' in XML cube '")
                                .append(mappingMeasure.cubeName()).append("'").toString());
                        }
                        List<MappingCalculatedMember> memberList =
                            calculatedMembersMap.get(cube);
                        if (memberList == null) {
                            memberList =
                                new ArrayList<>();
                        }
                        memberList.add(calcMember);
                        origCalcMeasureList.add(calcMember);
                        calculatedMembersMap.put(cube, memberList);
                    } else {
                        // This is the a standard measure. (Don't know
                        // whether it will confuse things that this
                        // measure still points to its 'real' cube.)
                        RolapVirtualCubeMeasure virtualCubeMeasure =
                            new RolapVirtualCubeMeasure(
                                null,
                                measuresLevel,
                                (RolapStoredMeasure) cubeMeasure,
                                RolapHierarchy.createMetadataMap(
                                    mappingMeasure.annotations()));

                        // Set member's visibility, default true.
                        Boolean visible = mappingMeasure.visible();
                        if (visible == null) {
                            visible = Boolean.TRUE;
                        }
                        virtualCubeMeasure.setProperty(
                            Property.VISIBLE.name,
                            visible);
                        // Inherit caption from the "real" measure
                        virtualCubeMeasure.setProperty(
                            Property.CAPTION.name,
                            cubeMeasure.getCaption());
                        origMeasureList.add(virtualCubeMeasure);
                        //Set the actual virtual cube measure
                        //to the default measure
                        if (isDefaultMeasureFound) {
                          defaultMeasure = virtualCubeMeasure;
                        }
                    }
                    break;
                }
            }
            if (!found) {
                throw Util.newInternal(
                    new StringBuilder("could not find measure '").append(mappingMeasure.name())
                    .append("' in cube '").append(mappingMeasure.cubeName()).append("'").toString());
            }
        }

        // Must init the dimensions before dealing with calculated members
        init(mappingVirtualCube.virtualCubeDimensions());

        // Loop through the base cubes containing calculated members
        // referenced by this virtual cube.  Resolve those members relative
        // to their base cubes first, then resolve them relative to this
        // cube so the correct dimension ordinals are used
        List<RolapVirtualCubeMeasure> modifiedMeasureList =
            new ArrayList<>(origMeasureList);
        for (Map.Entry<RolapCube, List<MappingCalculatedMember>> entry : calculatedMembersMap.entrySet()) {
            RolapCube baseCube = entry.getKey();
            List<MappingCalculatedMember> mappingCalculatedMemberList =
                calculatedMembersMap.get(baseCube);
            Query queryExp =
                resolveCalcMembers(
                    mappingCalculatedMemberList,
                    Collections.<MappingNamedSet>emptyList(),
                    baseCube,
                    false);
            MeasureFinder measureFinder =
                new MeasureFinder(this, baseCube, measuresLevel);
            queryExp.accept(measureFinder);
            modifiedMeasureList.addAll(measureFinder.getMeasuresFound());
        }

        // Add the original calculated members from the base cubes to our
        // list of calculated members
        List<MappingCalculatedMember> mappingCalculatedMemberList =
            new ArrayList<>();
        for (Map.Entry<RolapCube, List<MappingCalculatedMember>> entry : calculatedMembersMap.entrySet()) {
            RolapCube baseCube = entry.getKey();
            mappingCalculatedMemberList.addAll(
                calculatedMembersMap.get(baseCube));
        }
        mappingCalculatedMemberList.addAll(
            mappingVirtualCube.calculatedMembers());


        // Resolve all calculated members relative to this virtual cube,
        // whose measureHierarchy member reader now contains all base
        // measures referenced in those calculated members
        setMeasuresHierarchyMemberReader(
            new CacheMemberReader(
                new MeasureMemberSource(
                    this.measuresHierarchy,
                    Util.<RolapMember>cast(modifiedMeasureList))));

        createCalcMembersAndNamedSets(
            mappingCalculatedMemberList,
            mappingVirtualCube.namedSets(),
            new ArrayList<>(),
            new ArrayList<>(),
            this,
            false);

        // iterate through a calculated member definitions in a virtual cube
        // retrieve calculated member source cube
        // set it appropriate rolap calculated measure
        Map<String, RolapHierarchy.RolapCalculatedMeasure> calcMeasuresWithBaseCube =
                new HashMap<>();
        for (Map.Entry<RolapCube, List<MappingCalculatedMember>> entry : calculatedMembersMap.entrySet()) {
            RolapCube rolapCube = entry.getKey();
            List<MappingCalculatedMember> calculatedMembers =
                    calculatedMembersMap.get(rolapCube);
            for (MappingCalculatedMember calculatedMember
                    : calculatedMembers)
            {
                List<Member> measures = rolapCube.getMeasures();
                for (Member measure : measures) {
                    if (measure instanceof RolapHierarchy.RolapCalculatedMeasure calculatedMeasure &&
                        calculatedMember
                            .name().equals(calculatedMeasure.getKey()))
                    {
                        calculatedMeasure.setBaseCube(rolapCube);
                        calcMeasuresWithBaseCube.put(calculatedMeasure.getUniqueName(),
                            calculatedMeasure);
                    }
                }
            }
        }

        // reset the measureHierarchy member reader back to the list of
        // measures that are only defined on this virtual cube
        setMeasuresHierarchyMemberReader(
            new CacheMemberReader(
                new MeasureMemberSource(
                    this.measuresHierarchy,
                    Util.<RolapMember>cast(origMeasureList))));

        this.measuresHierarchy.setDefaultMember(defaultMeasure);

        List<? extends MappingCalculatedMember> mappingVirtualCubeCalculatedMemberList =
                mappingVirtualCube.calculatedMembers();
        if (!vcHasAllCalcMembers(
                origCalcMeasureList, mappingVirtualCubeCalculatedMemberList))
        {
            // Remove from the calculated members array
            // those members that weren't originally defined
            // on this virtual cube.
            List<Formula> calculatedMemberListCopy =
                new ArrayList<>(calculatedMemberList);
            calculatedMemberList.clear();
            for (Formula calculatedMember : calculatedMemberListCopy) {
                if (findOriginalMembers(
                        calculatedMember,
                        origCalcMeasureList,
                        calculatedMemberList))
                {
                    continue;
                }
                findOriginalMembers(
                    calculatedMember,
                    mappingVirtualCubeCalculatedMemberList,
                    calculatedMemberList);
            }
        }

        for (Formula calcMember : calculatedMemberList) {
            if (calcMember.getName().equalsIgnoreCase(
                    mappingVirtualCube.defaultMeasure()))
            {
                this.measuresHierarchy.setDefaultMember(
                    calcMember.getMdxMember());
                break;
            }
        }

        // We modify the measures schema reader one last time with a version
        // which includes all calculated members as well.
        final List<RolapMember> finalMeasureMembers =
            new ArrayList<>();
        for (RolapVirtualCubeMeasure measure : origMeasureList) {
            finalMeasureMembers.add(measure);
        }
        for (Formula formula : calculatedMemberList) {
            final RolapMember calcMeasure = (RolapMember) formula.getMdxMember();
            if (calcMeasure instanceof RolapHierarchy.RolapCalculatedMeasure rolapCalculatedMeasure
                    && calcMeasuresWithBaseCube.containsKey(calcMeasure.getUniqueName())) {
                rolapCalculatedMeasure
                        .setBaseCube(calcMeasuresWithBaseCube.get(calcMeasure.getUniqueName()).getBaseCube());
            }

            MappingVirtualCubeMeasure mappingMeasure = measureHash.get(calcMeasure.getUniqueName());
        	if(mappingMeasure != null) {
	            Boolean visible = mappingMeasure.visible();
	            if(visible != null) {
	            	calcMeasure.setProperty(
	                        Property.VISIBLE.name,
	                        visible);
	            }
        	}

            finalMeasureMembers.add(calcMeasure);
        }
        setMeasuresHierarchyMemberReader(
            new CacheMemberReader(
                new MeasureMemberSource(
                    this.measuresHierarchy,
                    Util.<RolapMember>cast(finalMeasureMembers))));
        // Note: virtual cubes do not get aggregate
    }

    private boolean vcHasAllCalcMembers(
        List<? extends MappingCalculatedMember> origCalcMeasureList,
        List<? extends MappingCalculatedMember> mappingVirtualCubeCalculatedMemberList)
    {
        return calculatedMemberList.size()
            == (origCalcMeasureList.size()
            + mappingVirtualCubeCalculatedMemberList.size());
    }

    private boolean findOriginalMembers(
        Formula formula,
        List<? extends MappingCalculatedMember> mappingCalcMembers,
        List<Formula> calcMembers)
    {
        for (MappingCalculatedMember mappingCalcMember : mappingCalcMembers) {
            Hierarchy hierarchy = null;
            if (mappingCalcMember.dimension() != null) {
                Dimension dimension =
                    lookupDimension(
                        new IdImpl.NameSegmentImpl(
                            mappingCalcMember.dimension(),
                            Quoting.UNQUOTED));
                if (dimension != null
                    && dimension.getHierarchy() != null)
                {
                    hierarchy = dimension.getHierarchy();
                }
            } else if (mappingCalcMember.hierarchy() != null) {
                hierarchy =
                    lookupHierarchy(
                        new IdImpl.NameSegmentImpl(
                            mappingCalcMember.hierarchy(),
                            Quoting.UNQUOTED),
                        true);
            }
            if (formula.getName().equals(mappingCalcMember.name())
                && formula.getMdxMember().getHierarchy().equals(
                    hierarchy))
            {
                calcMembers.add(formula);
                return true;
            }
        }
        return false;
    }

    @Override
	protected Logger getLogger() {
        return LOGGER;
    }

    @Override
	public Map<String, Object> getMetadata() {
        return metadata;
    }

    public boolean hasAggGroup() {
        return aggGroup != null;
    }

    public ExplicitRules.Group getAggGroup() {
        return aggGroup;
    }

    void loadAggGroup(MappingCube mappingCube) {
        aggGroup = ExplicitRules.Group.make(this, mappingCube);
    }

    /**
     * Creates a dimension from its XML definition. If the XML definition is
     * a &lt;DimensionUsage&gt;, and the shared dimension is cached in the
     * schema, returns that.
     *
     * @param mappingCubeDimension XML Dimension or DimensionUsage
     * @param schema Schema
     * @param mappingSchema XML Schema
     * @param dimensionOrdinal Ordinal of dimension
     * @param cubeHierarchyList List of hierarchies in cube
     * @return A dimension
     */
    private RolapCubeDimension getOrCreateDimension(
        MappingCubeDimension mappingCubeDimension,
        RolapSchema schema,
        MappingSchema mappingSchema,
        int dimensionOrdinal,
        List<RolapHierarchy> cubeHierarchyList)
    {
        RolapDimension dimension = null;
        if (mappingCubeDimension instanceof MappingDimensionUsage usage) {
            final RolapHierarchy sharedHierarchy =
                schema.getSharedHierarchy(usage.source());
            if (sharedHierarchy != null) {
                dimension =
                    (RolapDimension) sharedHierarchy.getDimension();
            }
        }

        if (dimension == null) {
            MappingPrivateDimension mappingDimension =
                DimensionUtil.getDimension(mappingSchema, mappingCubeDimension);
            dimension =
                new RolapDimension(
                    schema, this, mappingDimension, mappingCubeDimension);
        }

        // wrap the shared or regular dimension with a
        // rolap cube dimension object
        return new RolapCubeDimension(
            this, dimension, mappingCubeDimension,
            mappingCubeDimension.name(), dimensionOrdinal,
            cubeHierarchyList);
    }

    /**
     * Post-initialization, doing things which cannot be done in the
     * constructor.
     */
    private void init(
        MappingCube mappingCube,
        final List<RolapMember> memberList)
    {
        // Load calculated members and named sets.
        // (We cannot do this in the constructor, because
        // cannot parse the generated query, because the schema has not been
        // set in the cube at this point.)
        List<Formula> formulaList = new ArrayList<>();
        createCalcMembersAndNamedSets(
            mappingCube.calculatedMembers(),
            mappingCube.namedSets(),
            memberList,
            formulaList,
            this,
            true);
    }

    /**
     * Checks that the ordinals of measures (including calculated measures)
     * are unique.
     *
     * @param cubeName        name of the cube (required for error messages)
     * @param measures        measure list
     */
    private void checkOrdinals(
        String cubeName,
        List<RolapMember> measures)
    {
        Map<Integer, String> ordinals = new HashMap<>();
        for (RolapMember measure : measures) {
            Integer ordinal = measure.getOrdinal();
            if (!ordinals.containsKey(ordinal)) {
                ordinals.put(ordinal, measure.getUniqueName());
            } else {
                throw new MondrianException(MessageFormat.format(measureOrdinalsNotUnique,
                    cubeName,
                    ordinal.toString(),
                    ordinals.get(ordinal),
                    measure.getUniqueName()));
            }
        }
    }

    /**
     * Adds a collection of calculated members and named sets to this cube.
     * The members and sets can refer to each other.
     *
     * @param mappingCalcMembers XML objects representing members
     * @param mappingNamedSets Array of XML definition of named set
     * @param memberList Output list of {@link org.eclipse.daanse.olap.api.element.Member} objects
     * @param formulaList Output list of {@link mondrian.olap.FormulaImpl} objects
     * @param cube the cube that the calculated members originate from
     * @param errOnDups throws an error if a duplicate member is found
     */
    private void createCalcMembersAndNamedSets(
        List<? extends MappingCalculatedMember> mappingCalcMembers,
        List<? extends MappingNamedSet> mappingNamedSets,
        List<RolapMember> memberList,
        List<Formula> formulaList,
        RolapCube cube,
        boolean errOnDups)
    {
        final Query queryExp =
            resolveCalcMembers(
                mappingCalcMembers,
                mappingNamedSets,
                cube,
                errOnDups);
        if (queryExp == null) {
            return;
        }

        // Now pick through the formulas.
        Util.assertTrue(
            queryExp.getFormulas().length
            == mappingCalcMembers.size() + mappingNamedSets.size());
        for (int i = 0; i < mappingCalcMembers.size(); i++) {
            postCalcMember(mappingCalcMembers, i, queryExp, memberList);
        }
        for (int i = 0; i < mappingNamedSets.size(); i++) {
            postNamedSet(
                mappingNamedSets, mappingCalcMembers.size(), i, queryExp, formulaList);
        }
    }

    private Query resolveCalcMembers(
        List<? extends MappingCalculatedMember> mappingCalcMembers,
        List<? extends MappingNamedSet> mappingNamedSets,
        RolapCube cube,
        boolean errOnDups)
    {
        // If there are no objects to create, our generated SQL will be so
        // silly, the parser will laugh.
        if (mappingCalcMembers.isEmpty() && mappingNamedSets.isEmpty()) {
            return null;
        }

        StringBuilder buf = new StringBuilder(256);
        buf.append("WITH").append(Util.NL);

        // Check the members individually, and generate SQL.
        final Set<String> fqNames = new LinkedHashSet<>();
        for (int i = 0; i < mappingCalcMembers.size(); i++) {
            preCalcMember(mappingCalcMembers, i, buf, cube, errOnDups, fqNames);
        }

        // Check the named sets individually (for uniqueness) and generate SQL.
        Set<String> nameSet = new HashSet<>();
        for (Formula namedSet : namedSetList) {
            nameSet.add(namedSet.getName());
        }
        for (MappingNamedSet mappingNamedSet : mappingNamedSets) {
            preNamedSet(mappingNamedSet, nameSet, buf);
        }

        buf.append("SELECT FROM ").append(cube.getUniqueName());

        // Parse and validate this huge MDX query we've created.
        final String queryString = buf.toString();
        try {
            final RolapConnection conn = schema.getInternalConnection();
            return LocusImpl.execute(
                conn,
                "RolapCube.resolveCalcMembers",
                new LocusImpl.Action<Query>() {
                    @Override
					public QueryImpl execute() {
                        final QueryImpl queryExp =
                            conn.parseQuery(queryString);
                        queryExp.resolve();
                        return queryExp;
                    }
                });
        } catch (Exception e) {
            throw new MondrianException(MessageFormat.format(unknownNamedSetHasBadFormula, getName()), e);
        }
    }

    private void postNamedSet(
        List<? extends MappingNamedSet> mappingNamedSets,
        final int offset,
        int i,
        final Query queryExp,
        List<Formula> formulaList)
    {
        MappingNamedSet mappingNamedSet = mappingNamedSets.get(i);
//        discard(xmlNamedSet);
        Formula formula = queryExp.getFormulas()[offset + i];
        final SetBase namedSet = (SetBase) formula.getNamedSet();
        if (mappingNamedSet.caption() != null
            && mappingNamedSet.caption().length() > 0)
        {
            namedSet.setCaption(mappingNamedSet.caption());
        }

        if (mappingNamedSet.description() != null
            && mappingNamedSet.description().length() > 0)
        {
            namedSet.setDescription(mappingNamedSet.description());
        }

        if (mappingNamedSet.displayFolder() != null
                && mappingNamedSet.displayFolder().length() > 0)
        {
            namedSet.setDisplayFolder(mappingNamedSet.displayFolder());
        }

        namedSet.setMetadata(
            RolapHierarchy.createMetadataMap(mappingNamedSet.annotations()));

        namedSetList.add(formula);
        formulaList.add(formula);
    }

    private void preNamedSet(
        MappingNamedSet mappingNamedSet,
        Set<String> nameSet,
        StringBuilder buf)
    {
        if (!nameSet.add(mappingNamedSet.name())) {
            throw new MondrianException(MessageFormat.format(namedSetNotUnique,
                mappingNamedSet.name(), getName()));
        }

        buf.append("SET ")
            .append(Util.makeFqName(mappingNamedSet.name()))
            .append(Util.NL)
            .append(" AS ");
        Util.singleQuoteString(NamedSetUtil.getFormula(mappingNamedSet), buf);
        buf.append(Util.NL);
    }

    private void postCalcMember(
        List<? extends MappingCalculatedMember> mappingCalcMembers,
        int i,
        final Query queryExp,
        List<RolapMember> memberList)
    {
        MappingCalculatedMember mappingCalcMember = mappingCalcMembers.get(i);

        final Formula formula = queryExp.getFormulas()[i];

        calculatedMemberList.add(formula);

        final RolapMember member = (RolapMember) formula.getMdxMember();

        Boolean visible = mappingCalcMember.visible();
        if (visible == null) {
            visible = Boolean.TRUE;
        }
        member.setProperty(Property.VISIBLE.name, visible);

        member.setProperty(Property.DISPLAY_FOLDER.name, mappingCalcMember.displayFolder());

        if (mappingCalcMember.caption() != null
            && mappingCalcMember.caption().length() > 0)
        {
            member.setProperty(
                Property.CAPTION.name, mappingCalcMember.caption());
        }

        if (mappingCalcMember.description() != null
            && mappingCalcMember.description().length() > 0)
        {
            member.setProperty(
                Property.DESCRIPTION_PROPERTY.name, mappingCalcMember.description());
        }

        if (getFormatString(mappingCalcMember) != null
            && getFormatString(mappingCalcMember).length() > 0)
        {
            member.setProperty(
                Property.FORMAT_STRING.name, getFormatString(mappingCalcMember));
        }

        final RolapMember member1 = RolapUtil.strip(member);
        ((RolapCalculatedMember) member1).setMetadata(
            RolapHierarchy.createMetadataMap(mappingCalcMember.annotations()));

        memberList.add(member);
    }

    private void preCalcMember(
        List<? extends MappingCalculatedMember> mappingCalcMembers,
        int j,
        StringBuilder buf,
        RolapCube cube,
        boolean errOnDup,
        Set<String> fqNames)
    {
        MappingCalculatedMember mappingCalcMember = mappingCalcMembers.get(j);

        if (mappingCalcMember.hierarchy() != null
            && mappingCalcMember.dimension() != null)
        {
            throw  new MondrianException(MessageFormat.format(
                calcMemberHasBothDimensionAndHierarchy,
                    mappingCalcMember.name(), getName()));
        }

        // Lookup dimension
        Hierarchy hierarchy = null;
        String dimName = null;
        if (mappingCalcMember.dimension() != null) {
            dimName = mappingCalcMember.dimension();
            final Dimension dimension =
                lookupDimension(
                    new IdImpl.NameSegmentImpl(
                        mappingCalcMember.dimension(),
                        Quoting.UNQUOTED));
            if (dimension != null) {
                hierarchy = dimension.getHierarchy();
            }
        } else if (mappingCalcMember.hierarchy() != null) {
            dimName = mappingCalcMember.hierarchy();
            hierarchy = (Hierarchy)
                getSchemaReader().withLocus().lookupCompound(
                    this,
                    Util.parseIdentifier(dimName),
                    false,
                    DataType.HIERARCHY);
        }
        if (hierarchy == null) {
            throw new MondrianException(MessageFormat.format(calcMemberHasBadDimension,
                dimName, mappingCalcMember.name(), getName()));
        }

        // Root of fully-qualified name.
        String parentFqName;
        if (mappingCalcMember.parent() != null) {
            parentFqName = mappingCalcMember.parent();
        } else {
            parentFqName = hierarchy.getUniqueNameSsas();
        }

        if (!hierarchy.getDimension().isMeasures()) {
            // Check if the parent exists.
            final OlapElement parent =
                Util.lookupCompound(
                    getSchemaReader().withLocus(),
                    this,
                    Util.parseIdentifier(parentFqName),
                    false,
                    DataType.UNKNOWN);

            if (parent == null) {
                throw new MondrianException(MessageFormat.format(
                    calcMemberHasUnknownParent,
                        parentFqName, mappingCalcMember.name(), getName()));
            }

            if (parent.getHierarchy() != hierarchy) {
                throw  new MondrianException(MessageFormat.format(
                calcMemberHasDifferentParentAndHierarchy,
                    mappingCalcMember.name(), getName(), hierarchy.getUniqueName()));
            }
        }

        // If we're processing a virtual cube, it's possible that we've
        // already processed this calculated member because it's
        // referenced in another measure; in that case, remove it from the
        // list, since we'll add it back in later; otherwise, in the
        // non-virtual cube case, throw an exception
        final String fqName = Util.makeFqName(parentFqName, mappingCalcMember.name());
        for (int i = 0; i < calculatedMemberList.size(); i++) {
            Formula formula = calculatedMemberList.get(i);
            if (formula.getName().equals(mappingCalcMember.name())
                && formula.getMdxMember().getHierarchy().equals(
                    hierarchy))
            {
                if (errOnDup) {
                    throw new CalcMemberNotUniqueException(
                        fqName,
                        getName());
                } else {
                    calculatedMemberList.remove(i);
                    --i;
                }
            }
        }

        // Check this calc member doesn't clash with one earlier in this
        // batch.
        if (!fqNames.add(fqName)) {
            throw new CalcMemberNotUniqueException(fqName, getName());
        }

        final List<? extends MappingCalculatedMemberProperty> mappingProperties =
                mappingCalcMember.calculatedMemberProperties();
        List<String> propNames = new ArrayList<>();
        List<String> propExprs = new ArrayList<>();
        validateMemberProps(
            mappingProperties, propNames, propExprs, mappingCalcMember.name());

        final int measureCount =
            cube.measuresHierarchy.getMemberReader().getMemberCount();

        // Generate SQL.
        assert fqName.startsWith("[");
        buf.append("MEMBER ")
            .append(fqName)
            .append(Util.NL)
            .append("  AS ");
        Util.singleQuoteString(getFormula(mappingCalcMember), buf);

        if (mappingCalcMember.cellFormatter() != null) {
            if (mappingCalcMember.cellFormatter().className() != null) {
                propNames.add(Property.CELL_FORMATTER.name);
                propExprs.add(
                    Util.quoteForMdx(mappingCalcMember.cellFormatter().className()));
            }
            if (mappingCalcMember.cellFormatter().script() != null) {
                if (mappingCalcMember.cellFormatter().script().language() != null) {
                    propNames.add(Property.CELL_FORMATTER_SCRIPT_LANGUAGE.name);
                    propExprs.add(
                        Util.quoteForMdx(
                            mappingCalcMember.cellFormatter().script().language()));
                }
                propNames.add(Property.CELL_FORMATTER_SCRIPT.name);
                propExprs.add(
                    Util.quoteForMdx(mappingCalcMember.cellFormatter().script().cdata()));
            }
        }

        assert propNames.size() == propExprs.size();
        processFormatStringAttribute(mappingCalcMember, buf);

        for (int i = 0; i < propNames.size(); i++) {
            String name = propNames.get(i);
            String expr = propExprs.get(i);
            buf.append(",").append(Util.NL);
            expr = removeSurroundingQuotesIfNumericProperty(name, expr);
            buf.append(name).append(" = ").append(expr);
        }
        // Flag that the calc members are defined against a cube; will
        // determine the value of Member.isCalculatedInQuery
        buf.append(",")
            .append(Util.NL);
        Util.quoteMdxIdentifier(Property.MEMBER_SCOPE.name, buf);
        buf.append(" = 'CUBE'");

        // Assign the member an ordinal higher than all of the stored measures.
        if (!propNames.contains(Property.MEMBER_ORDINAL.getName())) {
            buf.append(",")
                .append(Util.NL)
                .append(Property.MEMBER_ORDINAL)
                .append(" = ")
                .append(measureCount + j);
        }
        buf.append(Util.NL);
    }

    private String removeSurroundingQuotesIfNumericProperty(
        String name,
        String expr)
    {
        Property prop = Property.lookup(name, false);
        if (prop != null
            && prop.getType().isNumeric()
            && isSurroundedWithQuotes(expr)
            && expr.length() > 2)
        {
            return expr.substring(1, expr.length() - 1);
        }
        return expr;
    }

    private boolean isSurroundedWithQuotes(String expr) {
        return expr.startsWith("\"") && expr.endsWith("\"");
    }

    void processFormatStringAttribute(
        MappingCalculatedMember mappingCalcMember,
        StringBuilder buf)
    {
        if (getFormatString(mappingCalcMember) != null) {
            buf.append(",")
                .append(Util.NL)
                .append(Property.FORMAT_STRING.name)
                .append(" = ")
                .append(Util.quoteForMdx(getFormatString(mappingCalcMember)));
        }
    }

    /**
     * Validates an array of member properties, and populates a list of names
     * and expressions, one for each property.
     *
     * @param mappingProperties Array of property definitions.
     * @param propNames Output array of property names.
     * @param propExprs Output array of property expressions.
     * @param memberName Name of member which the properties belong to.
     */
    private void validateMemberProps(
        final List<? extends MappingCalculatedMemberProperty> mappingProperties,
        List<String> propNames,
        List<String> propExprs,
        String memberName)
    {
        if (mappingProperties == null) {
            return;
        }
        for (MappingCalculatedMemberProperty mappingProperty : mappingProperties) {
            if (mappingProperty.expression() == null && mappingProperty.value() == null) {
                throw  new MondrianException(MessageFormat.format(
                    neitherExprNorValueForCalcMemberProperty,
                        mappingProperty.name(), memberName, getName()));
            }
            if (mappingProperty.expression() != null && mappingProperty.value() != null) {
                throw new MondrianException(
                    MessageFormat.format(exprAndValueForMemberProperty, mappingProperty.name(), memberName, getName()));
            }
            propNames.add(mappingProperty.name());
            if (mappingProperty.expression() != null) {
                propExprs.add(mappingProperty.expression());
            } else {
                propExprs.add(Util.quoteForMdx(mappingProperty.value()));
            }
        }
    }

    @Override
	public RolapSchema getSchema() {
        return schema;
    }

    /**
     * Returns the named sets of this cube.
     */
    @Override
	public NamedSet[] getNamedSets() {
        NamedSet[] namedSetsArray = new NamedSet[namedSetList.size()];
        for (int i = 0; i < namedSetList.size(); i++) {
            namedSetsArray[i] = namedSetList.get(i).getNamedSet();
        }
        return namedSetsArray;
    }

    /**
     * Returns the schema reader which enforces the appropriate access-control
     * context. schemaReader is cached, and needs to stay in sync with
     * any changes to the cube.
     *
     * @post return != null
     * @see #getSchemaReader(Role)
     */
    public synchronized SchemaReader getSchemaReader() {
        if (schemaReader == null) {
            schemaReader =
                new RolapCubeSchemaReader(context, RoleImpl.createRootRole(schema));
        }
        return schemaReader;
    }

    @Override
	public SchemaReader getSchemaReader(Role role) {
        if (role == null) {
            return getSchemaReader();
        } else {
            return new RolapCubeSchemaReader(context, role);
        }
    }

    MappingCubeDimension lookup(
        List<? extends MappingCubeDimension> mappingDimensions,
        String name)
    {
        for (MappingCubeDimension cd : mappingDimensions) {
            if (name.equals(cd.name())) {
                return cd;
            }
        }
        // TODO: this ought to be a fatal error.
        return null;
    }

    private void init(List<? extends MappingCubeDimension> mappingDimensions) {
        for (Dimension dimension1 : dimensions) {
            final RolapDimension dimension = (RolapDimension) dimension1;
            dimension.init(lookup(mappingDimensions, dimension.getName()));
        }
        register();
    }

    public void register() {
        if (isVirtual()) {
            return;
        }
        List<RolapBaseCubeMeasure> storedMeasures =
            new ArrayList<>();
        for (Member measure : getMeasures()) {
            if (measure instanceof RolapBaseCubeMeasure rolapBaseCubeMeasure) {
                storedMeasures.add(rolapBaseCubeMeasure);
            }
        }

        RolapStar starInner = getStar();
        RolapStar.Table table = starInner.getFactTable();

        // create measures (and stars for them, if necessary)
        for (RolapBaseCubeMeasure storedMeasure : storedMeasures) {
            table.makeMeasure(storedMeasure);
        }
    }

    /**
     * Returns true if this Cube is either virtual or if the Cube's
     * RolapStar is caching aggregates.
     *
     * @return Whether this Cube's RolapStar should cache aggregations
     */
    public boolean isCacheAggregations() {
        return isVirtual() || star.isCacheAggregations();
    }

    /**
     * Set if this (non-virtual) Cube's RolapStar should cache
     * aggregations.
     *
     * @param cache Whether this Cube's RolapStar should cache aggregations
     */
    public void setCacheAggregations(boolean cache) {
        if (! isVirtual()) {
            star.setCacheAggregations(cache);
        }
    }

    /**
     * Clear the in memory aggregate cache associated with this Cube, but
     * only if Disabling Caching has been enabled.
     */
    public void clearCachedAggregations() {
        clearCachedAggregations(false);
    }

    /**
     * Clear the in memory aggregate cache associated with this Cube.
     */
    public void clearCachedAggregations(boolean forced) {
        if (isVirtual()) {
            // TODO:
            // Currently a virtual cube does not keep a list of all of its
            // base cubes, so we need to iterate through each and flush
            // the ones that should be flushed. Could use a CacheControl
            // method here.
            for (RolapStar star1 : schema.getStars()) {
                // this will only flush the star's aggregate cache if
                // 1) DisableCaching is true or 2) the star's cube has
                // cacheAggregations set to false in the schema.
                star1.clearCachedAggregations(forced);
            }
        } else {
            star.clearCachedAggregations(forced);
        }
    }

    /**
     * Returns this cube's underlying star schema.
     */
    public RolapStar getStar() {
        if (star.getFactTable().getRelation().equals(getFact())) {
            return star;
        }
        star = schema.makeRolapStar(getFact());
        return star;
    }

    private void createUsages(
        RolapCubeDimension dimension,
        MappingCubeDimension mappingCubeDimension)
    {
        // RME level may not be in all hierarchies
        // If one uses the DimensionUsage attribute "level", which level
        // in a hierarchy to join on, and there is more than one hierarchy,
        // then a HierarchyUsage can not be created for the hierarchies
        // that do not have the level defined.
        RolapCubeHierarchy[] hierarchies =
            (RolapCubeHierarchy[]) dimension.getHierarchies();

        if (hierarchies.length == 1) {
            // Only one, so let lower level error checking handle problems
            createUsage(hierarchies[0], mappingCubeDimension);

        } else if ((mappingCubeDimension instanceof MappingDimensionUsage du)
            && (((MappingDimensionUsage) mappingCubeDimension).level()
                != null))
        {
            int cnt = 0;

            for (RolapCubeHierarchy hierarchy : hierarchies) {
                if (getLogger().isDebugEnabled()) {
                    String msg = new StringBuilder("RolapCube<init>: hierarchy=")
                        .append(hierarchy.getName()).toString();
                    getLogger().debug(msg);
                }
                RolapLevel joinLevel = (RolapLevel)
                    Util.lookupHierarchyLevel(hierarchy, du.level());
                if (joinLevel == null) {
                    continue;
                }
                createUsage(hierarchy, mappingCubeDimension);
                cnt++;
            }

            if (cnt == 0) {
                // None of the hierarchies had the level, let lower level
                // detect and throw error
                createUsage(hierarchies[0], mappingCubeDimension);
            }

        } else {
            // just do it
            for (RolapCubeHierarchy hierarchy : hierarchies) {
                if (getLogger().isDebugEnabled()) {
                    String msg = new StringBuilder("RolapCube<init>: hierarchy=")
                        .append(hierarchy.getName()).toString();
                    getLogger().debug(msg);
                }
                createUsage(hierarchy, mappingCubeDimension);
            }
        }
    }

    synchronized void createUsage(
        RolapCubeHierarchy hierarchy,
        MappingCubeDimension cubeDim)
    {
        HierarchyUsage usage = new HierarchyUsage(this, hierarchy, cubeDim);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                "RolapCube.createUsage: cube={}, hierarchy={}, usage={}",
                getName(), hierarchy.getName(), usage);
        }
        for (HierarchyUsage hierUsage : hierarchyUsages) {
            if (hierUsage.equals(usage)) {
                String msg = new StringBuilder("RolapCube.createUsage: duplicate ").append(hierUsage).toString();
                getLogger().warn(msg);
                return;
            }
        }
        if (getLogger().isDebugEnabled()) {
            String msg = new StringBuilder("RolapCube.createUsage: register ").append(usage).toString();
            getLogger().debug(msg);
        }
        this.hierarchyUsages.add(usage);
    }

    private synchronized HierarchyUsage getUsageByName(String name) {
        for (HierarchyUsage hierUsage : hierarchyUsages) {
            if (hierUsage.getFullName().equals(name)) {
                return hierUsage;
            }
        }
        return null;
    }

    /**
     * A Hierarchy may have one or more HierarchyUsages. This method returns
     * an array holding the one or more usages associated with a Hierarchy.
     * The HierarchyUsages hierarchyName attribute always equals the name
     * attribute of the Hierarchy.
     *
     * @param hierarchy Hierarchy
     * @return an HierarchyUsages array with 0 or more members.
     */
    public synchronized HierarchyUsage[] getUsages(Hierarchy hierarchy) {
        String name = hierarchy.getName();
        if (!name.equals(hierarchy.getDimension().getName())
            && SystemWideProperties.instance().SsasCompatibleNaming)
        {
            name = new StringBuilder(hierarchy.getDimension().getName()).append(".").append(name).toString();
        }
        if (getLogger().isDebugEnabled()) {
            String msg = new StringBuilder("RolapCube.getUsages: name=").append(name).toString();
            getLogger().debug(msg);
        }

        HierarchyUsage hierUsage = null;
        List<HierarchyUsage> list = null;

        for (HierarchyUsage hu : hierarchyUsages) {
            if (hu.getHierarchyName().equals(name)) {
                if (list != null) {
                    if (getLogger().isDebugEnabled()) {
                        getLogger().debug(
                            new StringBuilder("RolapCube.getUsages: ")
                            .append("add list HierarchyUsage.name=").append(hu.getName()).toString());
                    }
                    list.add(hu);
                } else if (hierUsage == null) {
                    hierUsage = hu;
                } else {
                    list = new ArrayList<>();
                    if (getLogger().isDebugEnabled()) {
                        getLogger().debug(
                            new StringBuilder("RolapCube.getUsages: ")
                            .append("add list hierUsage.name=")
                                .append(hierUsage.getName())
                                .append(", hu.name=")
                                .append(hu.getName()).toString());
                    }
                    list.add(hierUsage);
                    list.add(hu);
                    hierUsage = null;
                }
            }
        }
        if (hierUsage != null) {
            return new HierarchyUsage[] { hierUsage };
        } else if (list != null) {
            if (getLogger().isDebugEnabled()) {
                getLogger().debug("RolapCube.getUsages: return list");
            }
            return list.toArray(new HierarchyUsage[list.size()]);
        } else {
            return new HierarchyUsage[0];
        }
    }

    synchronized HierarchyUsage getFirstUsage(Hierarchy hier) {
        HierarchyUsage hierarchyUsage = firstUsageMap.get(hier);
        if (hierarchyUsage == null) {
            HierarchyUsage[] hierarchyUsagesInner = getUsages(hier);
            if (hierarchyUsagesInner.length != 0) {
                hierarchyUsage = hierarchyUsagesInner[0];
                firstUsageMap.put(hier, hierarchyUsage);
            }
        }
        return hierarchyUsage;
    }

    /**
     * Looks up all of the HierarchyUsages with the same "source" returning
     * an array of HierarchyUsage of length 0 or more.
     *
     * This method is currently only called if an error occurs in lookupChild(),
     * so that more information can be displayed in the error log.
     *
     * @param source Name of shared dimension
     * @return array of HierarchyUsage (HierarchyUsage[]) - never null.
     */
    private synchronized HierarchyUsage[] getUsagesBySource(String source) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug("RolapCube.getUsagesBySource: source={}", source);
        }

        HierarchyUsage hierUsage = null;
        List<HierarchyUsage> list = null;

        for (HierarchyUsage hu : hierarchyUsages) {
            String s = hu.getSource();
            if ((s != null) && s.equals(source)) {
                if (list != null) {
                    if (getLogger().isDebugEnabled()) {
                        getLogger().debug(
                            new StringBuilder("RolapCube.getUsagesBySource: ")
                                .append("add list HierarchyUsage.name=")
                                .append(hu.getName()).toString());
                    }
                    list.add(hu);
                } else if (hierUsage == null) {
                    hierUsage = hu;
                } else {
                    list = new ArrayList<>();
                    if (getLogger().isDebugEnabled()) {
                        getLogger().debug(
                            new StringBuilder("RolapCube.getUsagesBySource: ")
                                .append("add list hierUsage.name=")
                                .append(hierUsage.getName())
                                .append(", hu.name=")
                                .append(hu.getName()).toString());
                    }
                    list.add(hierUsage);
                    list.add(hu);
                    hierUsage = null;
                }
            }
        }
        if (hierUsage != null) {
            return new HierarchyUsage[] { hierUsage };
        } else if (list != null) {
            if (getLogger().isDebugEnabled()) {
                getLogger().debug("RolapCube.getUsagesBySource: return list");
            }
            return list.toArray(new HierarchyUsage[list.size()]);
        } else {
            return new HierarchyUsage[0];
        }
    }


    /**
     * Understand this and you are no longer a novice.
     *
     * @param dimension Dimension
     */
    void registerDimension(RolapCubeDimension dimension) {
        RolapStar starInner = getStar();

        Hierarchy[] hierarchies = dimension.getHierarchies();

        for (Hierarchy hierarchy1 : hierarchies) {
            RolapHierarchy hierarchy = (RolapHierarchy) hierarchy1;

            MappingRelationOrJoin relation = hierarchy.getRelation();
            if (relation == null) {
                continue; // e.g. [Measures] hierarchy
            }
            RolapCubeLevel[] levels = (RolapCubeLevel[]) hierarchy.getLevels();

            HierarchyUsage[] hierarchyUsagesInner = getUsages(hierarchy);
            if (hierarchyUsagesInner.length == 0) {
                if (getLogger().isDebugEnabled()) {
                    StringBuilder buf = new StringBuilder(64);
                    buf.append("RolapCube.registerDimension: ");
                    buf.append("hierarchyUsages == null for cube=\"");
                    buf.append(this.name);
                    buf.append("\", hierarchy=\"");
                    buf.append(hierarchy.getName());
                    buf.append("\"");
                    getLogger().debug(buf.toString());
                }
                continue;
            }

            for (HierarchyUsage hierarchyUsage : hierarchyUsagesInner) {
                String usagePrefix = hierarchyUsage.getUsagePrefix();
                RolapStar.Table table = starInner.getFactTable();

                String levelName = hierarchyUsage.getLevelName();

                // RME
                // If a DimensionUsage has its level attribute set, then
                // one wants joins to occur at that level and not below (not
                // at a finer level), i.e., if you have levels: Year, Quarter,
                // Month, and Day, and the level attribute is set to Month, the
                // you do not want aggregate joins to include the Day level.
                // By default, it is the lowest level that the fact table
                // joins to, the Day level.
                // To accomplish this, we reorganize the relation and then
                // copy it (so that elsewhere the original relation can
                // still be used), and finally, clip off those levels below
                // the DimensionUsage level attribute.
                // Note also, if the relation (Relation) is not
                // a Join, i.e., the dimension is not a snowflake,
                // there is a single dimension table, then this is currently
                // an unsupported configuation and all bets are off.
                if (relation instanceof MappingJoin) {
                    // RME
                    // take out after things seem to be working
                    MappingRelationOrJoin relationTmp1 = relation;

                    relation = reorder(relation, levels);

                    if (relation == null && getLogger().isDebugEnabled()) {
                        getLogger().debug(
                            "RolapCube.registerDimension: after reorder relation==null");
                        String msg = new StringBuilder("RolapCube.registerDimension: reorder relationTmp1=")
                            .append(format(relationTmp1)).toString();
                        getLogger().debug(msg);
                    }
                }

                MappingRelationOrJoin relationTmp2 = relation;

                if (levelName != null) {
                    // When relation is a table, this does nothing. Otherwise
                    // it tries to arrange the joins so that the fact table
                    // in the RolapStar will be joining at the lowest level.
                    //

                    // Make sure the level exists
                    RolapLevel level =
                        RolapLevel.lookupLevel(levels, levelName);
                    if (level == null) {
                        StringBuilder buf = new StringBuilder(64);
                        buf.append("For cube \"");
                        buf.append(getName());
                        buf.append("\" and HierarchyUsage [");
                        buf.append(hierarchyUsage);
                        buf.append("], there is no level with given");
                        buf.append(" level name \"");
                        buf.append(levelName);
                        buf.append("\"");
                        throw Util.newInternal(buf.toString());
                    }

                    // If level has child, not the lowest level, then snip
                    // relation between level and its child so that
                    // joins do not include the lower levels.
                    // If the child level is null, then the DimensionUsage
                    // level attribute was simply set to the default, lowest
                    // level and we do nothing.
                    if (relation instanceof MappingJoin) {
                        RolapLevel childLevel =
                            (RolapLevel) level.getChildLevel();
                        if (childLevel != null) {
                            String tableName = childLevel.getTableName();
                            if (tableName != null) {
                                relation = snip(relation, tableName);

                                if (relation == null
                                    && getLogger().isDebugEnabled())
                                {
                                    getLogger().debug(
                                        "RolapCube.registerDimension: after snip relation==null");
                                    String msg = new StringBuilder("RolapCube.registerDimension: snip relationTmp2=")
                                        .append(format(relationTmp2)).toString();
                                    getLogger().debug(msg);
                                }
                            }
                        }
                    }
                }

                // cube and dimension usage are in different tables
                if (relation != null && !relation.equals(table.getRelation())) {
                    // HierarchyUsage should have checked this.
                    if (hierarchyUsage.getForeignKey() == null) {
                        throw new MondrianException(MessageFormat.format(
                            hierarchyMustHaveForeignKey,
                                hierarchy.getName(), getName()));
                    }
                    // jhyde: check is disabled until we handle <View> correctly
                    if (false
                        && !starInner.getFactTable().containsColumn(
                            hierarchyUsage.getForeignKey()))
                    {
                        throw  new MondrianException(MessageFormat.format(
                            hierarchyInvalidForeignKey,
                                hierarchyUsage.getForeignKey(),
                                hierarchy.getName(),
                                getName()));
                    }
                    // parameters:
                    //   fact table,
                    //   fact table foreign key,
                    MappingColumn column =
                        new ColumnR(
                            table.getAlias(),
                            hierarchyUsage.getForeignKey());
                    // parameters:
                    //   left column
                    //   right column
                    RolapStar.Condition joinCondition =
                        new RolapStar.Condition(
                            column,
                            hierarchyUsage.getJoinExp());

                    if (hierarchy.getXmlHierarchy() != null
                            && hierarchy.getXmlHierarchy()
                            .primaryKeyTable() != null
                            && relation instanceof MappingJoin join
                            && right(join) instanceof MappingTable
                            && getAlias(((MappingTable)
                            right(((MappingJoin) relation)))) != null
                            && getAlias(((MappingTable)
                            right(((MappingJoin) relation))))
                            .equals(
                                hierarchy.getXmlHierarchy()
                              .primaryKeyTable()))
                    {
                        MappingJoin newRelation = new JoinR(
                        List.of(right(((MappingJoin) relation)), left(((MappingJoin) relation))),
                        getRightAlias((MappingJoin) relation),
                        ((MappingJoin) relation).rightKey(),
                        getLeftAlias(((MappingJoin) relation)),
                        ((MappingJoin) relation).leftKey());
                        relation = newRelation;
                    }

                    table = table.addJoin(this, relation, joinCondition);
                }

                // The parent Column is used so that non-shared dimensions
                // which use the fact table (not a separate dimension table)
                // can keep a record of what other columns are in the
                // same set of levels.
                RolapStar.Column parentColumn = null;

                // RME
                // If the level name is not null, then we need only register
                // those columns for that level and above.
                if (levelName != null) {
                    for (RolapCubeLevel level : levels) {
                        if (level.getKeyExp() != null) {
                            parentColumn = makeColumns(
                                table, level, parentColumn, usagePrefix);
                        }
                        if (levelName.equals(level.getName())) {
                            break;
                        }
                    }
                } else {
                    // This is the normal case, no level attribute so register
                    // all columns.
                    for (RolapCubeLevel level : levels) {
                        if (level.getKeyExp() != null) {
                            parentColumn = makeColumns(
                                table, level, parentColumn, usagePrefix);
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds a column to the appropriate table in the {@link RolapStar}.
     * Note that if the RolapLevel has a table attribute, then the associated
     * column needs to be associated with that table.
     */
    protected RolapStar.Column makeColumns(
        RolapStar.Table table,
        RolapCubeLevel level,
        RolapStar.Column parentColumn,
        String usagePrefix)
    {
        // If there is a table name, then first see if the table name is the
        // table parameter's name or alias and, if so, simply add the column
        // to that table. On the other hand, find the ancestor of the table
        // parameter and if found, then associate the new column with
        // that table.
        //
        // Lastly, if the ancestor can not be found, i.e., there is no table
        // with the level's table name, what to do.  Here we simply punt and
        // associated the new column with the table parameter which might
        // be an error. We do issue a warning in any case.
        String tableName = level.getTableName();
        if (tableName != null) {
            if (table.getAlias().equals(tableName)) {
                parentColumn = table.makeColumns(
                    this, level, parentColumn, usagePrefix);
            } else if (table.equalsTableName(tableName)) {
                parentColumn = table.makeColumns(
                    this, level, parentColumn, usagePrefix);
            } else {
                RolapStar.Table t = table.findAncestor(tableName);
                if (t != null) {
                    parentColumn = t.makeColumns(
                        this, level, parentColumn, usagePrefix);
                } else {
                    // Issue warning and keep going.
                    String msg = new StringBuilder("RolapCube.makeColumns: for cube \"")
                        .append(getName())
                        .append("\" the Level \"")
                        .append(level.getName())
                        .append("\" has a table name attribute \"")
                        .append(tableName)
                        .append("\" but the associated RolapStar does not")
                        .append(" have a table with that name.").toString();
                    getLogger().warn(msg);

                    parentColumn = table.makeColumns(
                        this, level, parentColumn, usagePrefix);
                }
            }
        } else {
            // level's expr is not a Column (this is used by tests)
            // or there is no table name defined
            parentColumn = table.makeColumns(
                this, level, parentColumn, usagePrefix);
        }

        return parentColumn;
    }

    // The following code deals with handling the DimensionUsage level attribute
    // and snowflake dimensions only.

    /**
     * Formats a {@link MappingRelationOrJoin}, indenting
     * joins for readability.
     *
     * @param relation A table or a join
     */
    private static String format(MappingRelationOrJoin relation) {
        StringBuilder buf = new StringBuilder();
        format(relation, buf, "");
        return buf.toString();
    }

    private static void format(
        MappingRelationOrJoin relation,
        StringBuilder buf,
        String indent)
    {
        if (relation instanceof MappingTable table) {
            buf.append(indent);
            buf.append(table.name());
            if (table.alias() != null) {
                buf.append('(');
                buf.append(table.alias());
                buf.append(')');
            }
            buf.append(Util.NL);
        } else {
            MappingJoin join = (MappingJoin) relation;
            String subindent = new StringBuilder(indent).append("  ").toString();

            buf.append(indent);
            buf.append(getLeftAlias(join));
            buf.append('.');
            buf.append(join.leftKey());
            buf.append('=');
            buf.append(getRightAlias(join));
            buf.append('.');
            buf.append(join.rightKey());
            buf.append(Util.NL);
            format(left(join), buf, subindent);
            format(right(join), buf, indent);
        }
    }

    /**
     * This method tells us if unrelated dimensions to measures from
     * the input base cube should be pushed to default member or not
     * during aggregation.
     * @param baseCubeName name of the base cube for which we want
     * to check this property
     * @return boolean
     */
    public boolean shouldIgnoreUnrelatedDimensions(String baseCubeName) {
        return cubeUsages != null
            && cubeUsages.shouldIgnoreUnrelatedDimensions(baseCubeName);
    }

    /**
     * Returns a list of all hierarchies in this cube, in order of dimension.
     *
     * <p>TODO: Make this method return RolapCubeHierarchy, when the measures
     * hierarchy is a RolapCubeHierarchy.
     *
     * @return List of hierarchies
     */
    public List<RolapHierarchy> getHierarchies() {
        return hierarchyList;
    }

    public boolean isLoadInProgress() {
        return loadInProgress
            || getSchema().getSchemaLoadDate() == null;
    }

    /**
     * Association between a Table with its associated
     * level's depth. This is used to rank tables in a snowflake so that
     * the table with the lowest rank, level depth, is furthest from
     * the base fact table in the RolapStar.
     */
    private static class RelNode {

        /**
         * Finds a RelNode by table name or, if that fails, by table alias
         * from a map of RelNodes.
         *
         * @param table Is supposed a {@link MappingTable}
         * @param map Names of tables and {@link RelNode} pairs
         */
        private static RelNode lookup(
            MappingRelation table,
            Map<String, RelNode> map)
        {
            RelNode relNode;
            if (table instanceof MappingTable t) {
                relNode = map.get(t.name());
                if (relNode != null) {
                    return relNode;
                }
            }
            return map.get(getAlias(table));
        }

        private int depth;
        private String alias;
        private MappingRelation table;

        RelNode(String alias, int depth) {
            this.alias = alias;
            this.depth = depth;
        }
    }

    /**
     * Attempts to transform a {@link MappingRelationOrJoin}
     * into the "canonical" form.
     *
     * <p>What is the canonical form? It is only relevant
     * when the relation is a snowflake (nested joins), not simply a table.
     * The canonical form has lower levels to the left of higher levels (Day
     * before Month before Quarter before Year) and the nested joins are always
     * on the right side of the parent join.
     *
     * <p>The canonical form is (using a Time dimension example):
     * <pre>
     *            |
     *    ----------------
     *    |             |
     *   Day      --------------
     *            |            |
     *          Month      ---------
     *                     |       |
     *                   Quarter  Year
     * </pre>
     * <p>
     * When the relation looks like the above, then the fact table joins to the
     * lowest level table (the Day table) which joins to the next level (the
     * Month table) which joins to the next (the Quarter table) which joins to
     * the top level table (the Year table).
     * <p>
     * This method supports the transformation of a subset of all possible
     * join/table relation trees (and anyone who whats to generalize it is
     * welcome to). It will take any of the following and convert them to
     * the canonical.
     * <pre>
     *            |
     *    ----------------
     *    |             |
     *   Year     --------------
     *            |            |
     *         Quarter     ---------
     *                     |       |
     *                   Month    Day
     *
     *                  |
     *           ----------------
     *           |              |
     *        --------------   Year
     *        |            |
     *    ---------     Quarter
     *    |       |
     *   Day     Month
     *
     *                  |
     *           ----------------
     *           |              |
     *        --------------   Day
     *        |            |
     *    ---------      Month
     *    |       |
     *   Year   Quarter
     *
     *            |
     *    ----------------
     *    |             |
     *   Day      --------------
     *            |            |
     *          Month      ---------
     *                     |       |
     *                   Quarter  Year
     *
     * </pre>
     * <p>
     * In addition, at any join node, it can exchange the left and right
     * child relations so that the lower level depth is to the left.
     * For example, it can also transform the following:
     * <pre>
     *                |
     *         ----------------
     *         |              |
     *      --------------   Day
     *      |            |
     *    Month     ---------
     *              |       |
     *             Year   Quarter
     * </pre>
     * <p>
     * What it can not handle are cases where on both the left and right side of
     * a join there are child joins:
     * <pre>
     *                |
     *         ----------------
     *         |              |
     *      ---------     ----------
     *      |       |     |        |
     *    Month    Day   Year    Quarter
     *
     *                |
     *         ----------------
     *         |              |
     *      ---------     ----------
     *      |       |     |        |
     *    Year     Day   Month   Quarter
     * </pre>
     * <p>
     * When does this method do nothing? 1) when there are less than 2 levels,
     * 2) when any level does not have a table name, and 3) when for every table
     * in the relation there is not a level. In these cases, this method simply
     * return the original relation.
     *
     * @param relation A table or a join
     * @param levels Levels in hierarchy
     */
    private static MappingRelationOrJoin reorder(
        MappingRelationOrJoin relation,
        RolapLevel[] levels)
    {
        // Need at least two levels, with only one level theres nothing to do.
        if (levels.length < 2) {
            return relation;
        }

        Map<String, RelNode> nodeMap = new HashMap<>();

        // Create RelNode in top down order (year -> day)
        for (int i = 0; i < levels.length; i++) {
            RolapLevel level = levels[i];

            if (level.isAll()) {
                continue;
            }

            // this is the table alias
            String tableName = level.getTableName();
            if (tableName == null) {
                // punt, no table name
                return relation;
            }
            RelNode rnode = new RelNode(tableName, i);
            nodeMap.put(tableName, rnode);
        }
        if (! validateNodes(relation, nodeMap)) {
            return relation;
        }
        relation = copy(relation);

        // Put lower levels to the left of upper levels
        leftToRight(relation, nodeMap);

        // Move joins to the right side
        topToBottom(relation);

        return relation;
    }

    /**
     * The map has to be validated against the relation because there are
     * certain cases where we do not want to (read: can not) do reordering, for
     * instance, when closures are involved.
     *
     * @param relation A table or a join
     * @param map Names of tables and {@link RelNode} pairs
     */
    private static boolean validateNodes(
        MappingRelationOrJoin relation,
        Map<String, RelNode> map)
    {
        if (relation instanceof MappingRelation table) {
            RelNode relNode = RelNode.lookup(table, map);
            return (relNode != null);

        } else if (relation instanceof MappingJoin join) {
            return validateNodes(left(join), map)
                && validateNodes(right(join), map);

        } else {
            throw Util.newInternal(BAD_RELATION_TYPE + relation);
        }
    }

    /**
     * Transforms the Relation moving the tables associated with
     * lower levels (greater level depth, i.e., Day is lower than Month) to the
     * left of tables with high levels.
     *
     * @param relation is a table or a join
     * @param map Names of tables and {@link RelNode} pairs
     */
    private static int leftToRight(
        MappingRelationOrJoin relation,
        Map<String, RelNode> map)
    {
        if (relation instanceof MappingRelation table) {
            RelNode relNode = RelNode.lookup(table, map);
            // Associate the table with its RelNode!!!! This is where this
            // happens.
            relNode.table = table;

            return relNode.depth;

        } else if (relation instanceof MappingJoin join) {
            int leftDepth = leftToRight(left(join), map);
            int rightDepth = leftToRight(right(join), map);

            // we want the right side to be less than the left
            if (rightDepth > leftDepth) {
                // switch
                String leftAlias = getLeftAlias(join);
                String leftKey = join.leftKey();
                MappingRelationOrJoin left = left(join);
                join.setLeftAlias(getRightAlias(join));
                join.setLeftKey(join.rightKey());
                changeLeftRight(join, right(join), left);
                join.setRightAlias(leftAlias);
                join.setRightKey(leftKey);
            }
            // Does not currently matter which is returned because currently we
            // only support structures where the left and right depth values
            // form an inclusive subset of depth values, that is, any
            // node with a depth value between the left or right values is
            // a child of this current join.
            return leftDepth;

        } else {
            throw Util.newInternal(BAD_RELATION_TYPE + relation);
        }
    }

    /**
     * Transforms so that all joins have a table as their left child and either
     * a table of child join on the right.
     *
     * @param relation A table or a join
     */
    private static void topToBottom(MappingRelationOrJoin relation) {
        if (relation instanceof MappingTable) {
            // nothing

        } else if (relation instanceof MappingJoin join) {
            while (left(join) instanceof MappingJoin leftJoin) {
                MappingJoin jleft = leftJoin;
                changeLeftRight(join, left(jleft), new JoinR(
                    List.of(right(jleft), right(join)),
                    getLeftAlias(join),
                    join.leftKey(),
                    getRightAlias(join),
                    join.rightKey()
                    ));
                join.setRightAlias(getRightAlias(jleft));
                join.setRightKey(jleft.rightKey());
                join.setLeftAlias(getLeftAlias(jleft));
                join.setLeftKey(jleft.leftKey());
            }
        }
    }

    /**
     * Copies a {@link MappingRelationOrJoin}.
     *
     * @param relation A table or a join
     */
    private static MappingRelationOrJoin copy(
        MappingRelationOrJoin relation)
    {
        if (relation instanceof MappingTable table) {
            return new TableR(table);

        } else if (relation instanceof MappingInlineTable table) {
            return new InlineTableR(table);

        } else if (relation instanceof MappingJoin join) {
            MappingRelationOrJoin left = copy(left(join));
            MappingRelationOrJoin right = copy(right(join));

            return new JoinR(
                List.of(left, right),
                getLeftAlias(join), join.leftKey(),
                getRightAlias(join), join.rightKey());

        } else {
            throw Util.newInternal(BAD_RELATION_TYPE + relation);
        }
    }

    /**
     * Takes a relation in canonical form and snips off the
     * the tables with the given tableName (or table alias). The matching table
     * only appears once in the relation.
     *
     * @param relation A table or a join
     * @param tableName Table name in relation
     */
    private static MappingRelationOrJoin snip(
        MappingRelationOrJoin relation,
        String tableName)
    {
        if (relation instanceof MappingTable table) {
            // Return null if the table's name or alias matches tableName
            if ((table.alias() != null) && table.alias().equals(tableName)) {
                return null;
            } else {
                return table.name().equals(tableName) ? null : table;
            }

        } else if (relation instanceof MappingJoin join) {
            // snip left
            MappingRelationOrJoin left = snip(left(join), tableName);
            if (left == null) {
                // left got snipped so return the right
                // (the join is no longer a join).
                return right(join);

            } else {
                // whatever happened on the left, save it
                changeLeftRight(join, left, right(join));

                // snip right
                MappingRelationOrJoin right = snip(right(join), tableName);
                if (right == null) {
                    // right got snipped so return the left.
                    return left(join);

                } else {
                    // save the right, join still has right and left children
                    // so return it.
                    changeLeftRight(join, left(join), right);
                    return join;
                }
            }


        } else {
            throw Util.newInternal(BAD_RELATION_TYPE + relation);
        }
    }

    @Override
	public Member[] getMembersForQuery(String query, List<Member> calcMembers) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the time hierarchy for this cube. If there is no time hierarchy,
     * throws.
     */
    public RolapHierarchy getTimeHierarchy(String funName) {
        for (RolapHierarchy hierarchy : hierarchyList) {
            if (hierarchy.getDimension().getDimensionType()
                == DimensionType.TIME_DIMENSION)
            {
                return hierarchy;
            }
        }

        throw new MondrianException(MessageFormat.format(noTimeDimensionInCube, funName));
    }

    /**
     * Finds out non joining dimensions for this cube.
     * Useful for finding out non joining dimensions for a stored measure from
     * a base cube.
     *
     * @param tuple array of members
     * @return Set of dimensions that do not exist (non joining) in this cube
     */
    @Override
	public Set<Dimension> nonJoiningDimensions(Member[] tuple) {
        Set<Dimension> otherDims = new HashSet<>();
        for (Member member : tuple) {
            if (!member.isCalculated()) {
                otherDims.add(member.getDimension());
            }
        }
        return nonJoiningDimensions(otherDims);
    }

    /**
     * Finds out non joining dimensions for this cube.  Equality test for
     * dimensions is done based on the unique name. Object equality can't be
     * used.
     *
     * @param otherDims Set of dimensions to be tested for existence in this
     * cube
     * @return Set of dimensions that do not exist (non joining) in this cube
     */
    @Override
	public Set<Dimension> nonJoiningDimensions(Set<Dimension> otherDims) {
        Dimension[] baseCubeDimensions = getDimensions();
        Set<String>  baseCubeDimNames = new HashSet<>();
        for (Dimension baseCubeDimension : baseCubeDimensions) {
            baseCubeDimNames.add(baseCubeDimension.getUniqueName());
        }
        Set<Dimension> nonJoiningDimensions = new HashSet<>();
        for (Dimension otherDim : otherDims) {
            if (!baseCubeDimNames.contains(otherDim.getUniqueName())) {
                nonJoiningDimensions.add(otherDim);
            }
        }
        return nonJoiningDimensions;
    }

    @Override
	public List<Member> getMeasures() {
        Level measuresLevel = dimensions[0].getHierarchies()[0].getLevels()[0];
        return getSchemaReader().getLevelMembers(measuresLevel, true);
    }

    /**
     * Returns this cube's fact table, null if the cube is virtual.
     */
    public MappingRelation getFact() {
        return fact;
    }

    public void setFact(MappingRelation fact) {
        this.fact = fact;
    }


    /**
     * Returns whether this cube is virtual. We use the fact that virtual cubes
     * do not have fact tables.
     */
    public boolean isVirtual() {
        return fact == null;
    }

    /**
     * Returns the system measure that counts the number of fact table rows in
     * a given cell.
     *
     * <p>Never null, because if there is no count measure explicitly defined,
     * the system creates one.
     */
    RolapMeasure getFactCountMeasure() {
        return factCountMeasure;
    }

    /**
     * Returns the system measure that counts the number of atomic cells in
     * a given cell.
     *
     * <p>A cell is atomic if all dimensions are at their lowest level.
     * If the fact table has a primary key, this measure is equivalent to the
     * {@link #getFactCountMeasure() fact count measure}.
     */
    public RolapMeasure getAtomicCellCountMeasure() {
        // TODO: separate measure
        return factCountMeasure;
    }

    /**
     * Locates the base cube hierarchy for a particular virtual hierarchy.
     * If not found, return null. This may be converted to a map lookup
     * or cached in some way in the future to increase performance
     * with cubes that have large numbers of hierarchies
     *
     * @param hierarchy virtual hierarchy
     * @return base cube hierarchy if found
     */
    RolapHierarchy findBaseCubeHierarchy(RolapHierarchy hierarchy) {
        for (int i = 0; i < getDimensions().length; i++) {
            Dimension dimension = getDimensions()[i];
            if (dimension.getName().equals(
                    hierarchy.getDimension().getName()))
            {
                for (int j = 0; j <  dimension.getHierarchies().length; j++) {
                    Hierarchy hier = dimension.getHierarchies()[j];
                    if (hier.getName().equals(hierarchy.getName())) {
                        return (RolapHierarchy)hier;
                    }
                }
            }
        }
        return null;
    }


    /**
     * Locates the base cube level for a particular virtual level.
     * If not found, return null. This may be converted to a map lookup
     * or cached in some way in the future to increase performance
     * with cubes that have large numbers of hierarchies and levels
     *
     * @param level virtual level
     * @return base cube level if found
     */
    public RolapCubeLevel findBaseCubeLevel(RolapLevel level) {
        if (virtualToBaseMap.containsKey(level)) {
            return virtualToBaseMap.get(level);
        }
        String levelDimName = level.getDimension().getName();
        String levelHierName = level.getHierarchy().getName();

        // Closures are not in the dimension list so we need special logic for
        // locating the level.
        //
        // REVIEW: jhyde, 2009/7/21: This may no longer be the case, and we may
        // be able to improve performance. RolapCube.hierarchyList now contains
        // all hierarchies, including closure hierarchies; and
        // RolapHierarchy.closureFor indicates the base hierarchy for a closure
        // hierarchy.

        boolean isClosure = false;
        String closDimName = null;
        String closHierName = null;
        if (levelDimName.endsWith("$Closure")) {
            isClosure = true;
            closDimName = levelDimName.substring(0, levelDimName.length() - 8);
            closHierName =
                levelHierName.substring(0, levelHierName.length() - 8);
        }

        for (Dimension dimension : getDimensions()) {
            final String dimensionName = dimension.getName();
            if (dimensionName.equals(levelDimName)
                || (isClosure && dimensionName.equals(closDimName)))
            {
                for (Hierarchy hier : dimension.getHierarchies()) {
                    final String hierarchyName = hier.getName();
                    if (hierarchyName.equals(levelHierName)
                        || (isClosure && hierarchyName.equals(closHierName)))
                    {
                        if (isClosure) {
                            final RolapCubeLevel baseLevel =
                                ((RolapCubeLevel)
                                    hier.getLevels()[1]).getClosedPeer();
                            virtualToBaseMap.put(level, baseLevel);
                            return baseLevel;
                        }
                        for (Level lvl : hier.getLevels()) {
                            if (lvl.getName().equals(level.getName())) {
                                final RolapCubeLevel baseLevel =
                                    (RolapCubeLevel) lvl;
                                virtualToBaseMap.put(level, baseLevel);
                                return baseLevel;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    RolapCubeDimension createDimension(
        MappingCubeDimension mappingCubeDimension,
        MappingSchema mappingSchema)
    {
        RolapCubeDimension dimension =
            getOrCreateDimension(
                mappingCubeDimension, schema, mappingSchema,
                dimensions.length, hierarchyList);

        if (! isVirtual()) {
            createUsages(dimension, mappingCubeDimension);
        }
        registerDimension(dimension);

        dimension.init(mappingCubeDimension);

        // add to dimensions array
        this.dimensions = Util.append(dimensions, dimension);

        return dimension;
    }

    public OlapElement lookupChild(SchemaReader schemaReader, Segment s) {
        return lookupChild(schemaReader, s, MatchType.EXACT);
    }

    @Override
	public OlapElement lookupChild(
        SchemaReader schemaReader, Segment s, MatchType matchType)
    {
        if (!(s instanceof NameSegment nameSegment)) {
            return null;
        }
        // Note that non-exact matches aren't supported at this level,
        // so the matchType is ignored
        String status = null;
        OlapElement oe = null;
        if (matchType == MatchType.EXACT_SCHEMA) {
            oe = super.lookupChild(
                schemaReader, nameSegment, MatchType.EXACT_SCHEMA);
        } else {
            oe = super.lookupChild(
                schemaReader, nameSegment, MatchType.EXACT);
        }

        if (oe == null) {
            HierarchyUsage[] usages = getUsagesBySource(nameSegment.getName());
            if (usages.length > 0) {
                StringBuilder buf = new StringBuilder(64);
                buf.append("RolapCube.lookupChild: ");
                buf.append("In cube \"");
                buf.append(getName());
                buf.append("\" use of unaliased Dimension name \"");
                buf.append(nameSegment);
                if (usages.length == 1) {
                    // ERROR: this will work but is bad coding
                    buf.append("\" rather than the alias name ");
                    buf.append("\"");
                    buf.append(usages[0].getName());
                    buf.append("\" ");
                    String msg = buf.toString();
                    getLogger().error(msg);
                    throw new MondrianException(buf.toString());
                } else {
                    // ERROR: this is not allowed
                    buf.append("\" rather than one of the alias names ");
                    for (HierarchyUsage usage : usages) {
                        buf.append("\"");
                        buf.append(usage.getName());
                        buf.append("\" ");
                    }
                    String msg = buf.toString();
                    getLogger().error(msg);
                    throw new MondrianException(buf.toString());
                }
            }
        }

        if (getLogger().isDebugEnabled()) {
            if (!nameSegment.matches("Measures")) {
                HierarchyUsage hierUsage = getUsageByName(nameSegment.getName());
                if (hierUsage == null) {
                    status = "hierUsage == null";
                } else {
                    status =
                        "hierUsage == "
                        + (hierUsage.isShared() ? "shared" : "not shared");
                }
            }
            StringBuilder buf = new StringBuilder(64);
            buf.append("RolapCube.lookupChild: ");
            buf.append("name=");
            buf.append(getName());
            buf.append(", childname=");
            buf.append(nameSegment);
            if (status != null) {
                buf.append(", status=");
                buf.append(status);
            }
            if (oe == null) {
                buf.append(" returning null");
            } else {
                buf.append(" returning elementname=").append(oe.getName());
            }
            getLogger().debug(buf.toString());
        }

        return oe;
    }

    /**
     * Returns the the measures hierarchy.
     */
    public Hierarchy getMeasuresHierarchy() {
        return measuresHierarchy;
    }

    public List<RolapMember> getMeasuresMembers() {
        return measuresHierarchy.getMemberReader().getMembers();
    }



    /**
     * Creates a calculated member.
     *
     * <p>The member will be called [{dimension name}].[{name}].
     *
     * <p>Not for public use.
     *
     * @param hierarchy Hierarchy the calculated member belongs to
     * @param name Name of member
     * @param calc Compiled expression
     */
    public RolapMember createCalculatedMember(
        RolapHierarchy hierarchy,
        String name,
        Calc calc
    )
    {
        final List<Segment> segmentList = new ArrayList<>(Util.parseIdentifier(hierarchy.getUniqueName()));
        segmentList.add(new IdImpl.NameSegmentImpl(name));
        final Formula formula = new FormulaImpl(
            new IdImpl(segmentList),
            createDummyExp(calc),
            new MemberProperty[0]);
        final Statement statement =
            schema.getInternalConnection().getInternalStatement();
        try {
            final QueryImpl query =
                new QueryImpl(
                    statement,
                    this,
                    new Formula[] {formula},
                    new QueryAxisImpl[0],
                    null,
                    new CellProperty[0],
                    new Parameter[0],
                    false);
            query.createValidator().validate(formula);
            calculatedMemberList.add(formula);
            return (RolapMember) formula.getMdxMember();
        } finally {
            statement.close();
        }
    }

    @Override
    public void createNamedSet(
            Formula formula)
    {
        final Statement statement =
                schema.getInternalConnection().getInternalStatement();
        try {
            final QueryImpl query =
                    new QueryImpl(
                            statement,
                            this,
                            new Formula[] {formula},
                            new QueryAxisImpl[0],
                            null,
                            new CellProperty[0],
                            new Parameter[0],
                            false);
            query.createValidator().validate(formula);
            namedSetList.add(formula);
        } finally {
            statement.close();
        }
    }

    public RolapMember createCalculatedMember(
            Formula formula)
    {
        final Statement statement =
                schema.getInternalConnection().getInternalStatement();
        try {
            final QueryImpl query =
                    new QueryImpl(
                            statement,
                            this,
                            new Formula[] {formula},
                            new QueryAxisImpl[0],
                            null,
                            new CellProperty[0],
                            new Parameter[0],
                            false);
            query.createValidator().validate(formula);
            calculatedMemberList.add(formula);
            return (RolapMember) formula.getMdxMember();
        } finally {
            statement.close();
        }
    }

    /**
     * Schema reader which works from the perspective of a particular cube
     * (and hence includes calculated members defined in that cube) and also
     * applies the access-rights of a given role.
     */
    private class RolapCubeSchemaReader
        extends RolapSchemaReader
        implements NameResolverImpl.Namespace
    {
        public RolapCubeSchemaReader(Context context,Role role) {
            super(context,role, RolapCube.this.schema);
            assert role != null : "precondition: role != null";
        }

        @Override
		public List<Member> getLevelMembers(
                Level level,
                boolean includeCalculated)
        {
            return getLevelMembers(level, includeCalculated, null);
        }

        @Override
		public List<Member> getLevelMembers(
            Level level,
            boolean includeCalculated,
            Evaluator context)
        {
            List<Member> members = super.getLevelMembers(level, false, context);
            if (includeCalculated) {
                members = Util.addLevelCalculatedMembers(this, level, members);
            }
            return members;
        }

        @Override
		public Member getCalculatedMember(List<Segment> nameParts) {
            final String uniqueName = Util.implode(nameParts);
            for (Formula formula : calculatedMemberList) {
                final String formulaUniqueName =
                    formula.getMdxMember().getUniqueName();
                if (formulaUniqueName.equals(uniqueName)
                    && getRole().canAccess(formula.getMdxMember()))
                {
                    return formula.getMdxMember();
                }
            }
            return null;
        }

        @Override
		public NamedSet getNamedSet(List<Segment> segments) {
            if (segments.size() == 1) {
                Segment segment = segments.get(0);
                for (Formula namedSet : namedSetList) {
                    if (segment.matches(namedSet.getName())) {
                        return namedSet.getNamedSet();
                    }
                }
            }
            return super.getNamedSet(segments);
        }

        @Override
		public List<Member> getCalculatedMembers(Hierarchy hierarchy) {
            ArrayList<Member> list = new ArrayList<>();

            if (getRole().getAccess(hierarchy) == Access.NONE) {
                return list;
            }

            for (Member member : getCalculatedMembers()) {
                if (member.getHierarchy().equals(hierarchy)) {
                    list.add(member);
                }
            }
            return list;
        }

        @Override
		public List<Member> getCalculatedMembers(Level level) {
            List<Member> list = new ArrayList<>();

            if (getRole().getAccess(level) == Access.NONE) {
                return list;
            }

            for (Member member : getCalculatedMembers()) {
                if (member.getLevel().equals(level)) {
                    list.add(member);
                }
            }
            return list;
        }

        @Override
		public List<Member> getCalculatedMembers() {
//            List<Member> list =
//                roleToAccessibleCalculatedMembers.get(getRole());
//            if (list == null) {
//                list = new ArrayList<Member>();
//
//                for (Formula formula : calculatedMemberList) {
//                    Member member = formula.getMdxMember();
//                    if (getRole().canAccess(member)) {
//                        list.add(member);
//                    }
//                }
//                //  calculatedMembers array may not have been initialized
//                if (list.size() > 0) {
//                    roleToAccessibleCalculatedMembers.put(getRole(), list);
//                }
//            }

            //Without roleToAccessibleCalculatedMembers
            //Issues with session objects
            List<Member> list = new ArrayList<>();

            for (Formula formula : calculatedMemberList) {
                Member member = formula.getMdxMember();
                if (getRole().canAccess(member)) {
                    list.add(member);
                }
            }
            return list;
        }

        @Override
		public SchemaReader withoutAccessControl() {
            assert getClass() == RolapCubeSchemaReader.class
                : new StringBuilder("Derived class ").append(getClass()).append(" must override method").toString();
            return RolapCube.this.getSchemaReader();
        }

        @Override
		public Member getMemberByUniqueName(
            List<Segment> uniqueNameParts,
            boolean failIfNotFound,
            MatchType matchType)
        {
            Member member =
                (Member) lookupCompound(
                    RolapCube.this,
                    uniqueNameParts,
                    failIfNotFound,
                    DataType.MEMBER,
                    matchType);
            if (member == null) {
                assert !failIfNotFound;
                return null;
            }
            if (getRole().canAccess(member)) {
                return member;
            } else {
                if (failIfNotFound) {
                    throw Util.newElementNotFoundException(
                        DataType.MEMBER,
                        new IdentifierNode(
                            Util.toOlap4j(uniqueNameParts)));
                }
                return null;
            }
        }

        @Override
		public Cube getCube() {
            return RolapCube.this;
        }

        @Override
		public List<NameResolverImpl.Namespace> getNamespaces() {
            final List<NameResolverImpl.Namespace> list =
                new ArrayList<>();
            list.add(this);
            list.addAll(schema.getSchemaReader().getNamespaces());
            return list;
        }

        @Override
		public OlapElement lookupChild(
            OlapElement parent,
            IdentifierSegment segment,
            MatchType matchType)
        {
            // ignore matchType
            return lookupChild(parent, segment);
        }

        @Override
		public OlapElement lookupChild(
            OlapElement parent,
            IdentifierSegment segment)
        {
            // Don't look for stored members, or look for dimensions,
            // hierarchies, levels at all. Only look for calculated members
            // and named sets defined against this cube.

            // Look up calc member.
            for (Formula formula : calculatedMemberList) {
                if (NameResolverImpl.matches(formula, parent, segment)) {
                    return formula.getMdxMember();
                }
            }

            // Look up named set.
            if (parent == RolapCube.this) {
                for (Formula formula : namedSetList) {
                    if (Util.matches(segment, formula.getName())) {
                        return formula.getNamedSet();
                    }
                }
            }

            return null;
        }
    }

    /**
     * Visitor that walks an MDX parse tree containing formulas
     * associated with calculated members defined in a base cube but
     * referenced from a virtual cube.  When walking the tree, look
     * for other calculated members as well as stored measures.  Keep
     * track of all stored measures found, and for the calculated members,
     * once the formula of that calculated member has been visited, resolve
     * the calculated member relative to the virtual cube.
     */
    private class MeasureFinder extends MdxVisitorImpl
    {
        /**
         * The virtual cube where the original calculated member was
         * referenced from
         */
        private RolapCube virtualCube;

        /**
         * The base cube where the original calculated member is defined
         */
        private RolapCube baseCube;

        /**
         * The measures level corresponding to the virtual cube
         */
        private RolapLevel measuresLevel;

        /**
         * List of measures found
         */
        private List<RolapVirtualCubeMeasure> measuresFound;

        /**
         * List of calculated members found
         */
        private List<RolapCalculatedMember> calcMembersSeen;

        public MeasureFinder(
            RolapCube virtualCube,
            RolapCube baseCube,
            RolapLevel measuresLevel)
        {
            this.virtualCube = virtualCube;
            this.baseCube = baseCube;
            this.measuresLevel = measuresLevel;
            this.measuresFound = new ArrayList<>();
            this.calcMembersSeen = new ArrayList<>();
        }

        @Override
		public Object visitMemberExpression(MemberExpression memberExpr)
        {
            Member member = memberExpr.getMember();
            if (member instanceof RolapCalculatedMember calcMember) {
                // ignore the calculated member if we've already processed
                // it in another reference
                if (calcMembersSeen.contains(member)) {
                    return null;
                }
                Formula formula = calcMember.getFormula();
                if (!calcMembersSeen.contains(calcMember)) {
                  calcMembersSeen.add(calcMember);
                }
                formula.accept(this);

                // now that we've located all measures referenced in the
                // calculated member's formula, resolve the calculated
                // member relative to the virtual cube
                virtualCube.setMeasuresHierarchyMemberReader(
                    new CacheMemberReader(
                        new MeasureMemberSource(
                            virtualCube.measuresHierarchy,
                            Util.<RolapMember>cast(measuresFound))));

                MappingCalculatedMember mappingCalcMember =
                    schema.lookupXmlCalculatedMember(
                        calcMember.getUniqueName(),
                        baseCube.name);
                createCalcMembersAndNamedSets(
                    Collections.singletonList(mappingCalcMember),
                    Collections.<MappingNamedSet>emptyList(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    virtualCube,
                    false);

            } else if (member instanceof RolapBaseCubeMeasure baseMeasure) {
                RolapVirtualCubeMeasure virtualCubeMeasure =
                    new RolapVirtualCubeMeasure(
                        null,
                        measuresLevel,
                        baseMeasure,
                        Map.of());
                if (!measuresFound.contains(virtualCubeMeasure)) {
                    measuresFound.add(virtualCubeMeasure);
                }
            }

            return null;
        }

        public List<RolapVirtualCubeMeasure> getMeasuresFound()
        {
            return measuresFound;
        }
    }

    public static class CubeComparator implements Comparator<RolapCube>
    {
        @Override
		public int compare(RolapCube c1, RolapCube c2)
        {
            return c1.getName().compareTo(c2.getName());
        }
    }

    /**
     * Creates an expression that compiles to a given compiled expression.
     *
     * <p>Use this for synthetic expressions that do not correspond to anything
     * in an MDX parse tree, and just need to compile to a particular compiled
     * expression. The expression has minimal amounts of metadata, for example
     * type information, but the function has no name or description.
     *
     * @see mondrian.olap.type.TypeWrapperExp
     */
    static Expression createDummyExp(final Calc calc) {
        return new ResolvedFunCallImpl(
            new AbstractFunctionDefinition("dummy", null, "fn") {
                @Override
				public Calc compileCall(
                    ResolvedFunCall call, ExpressionCompiler compiler)
                {
                    return calc;
                }
            },
            new Expression[0],
            calc.getType());
    }



    /**Returns the list of base cubes associated with this cube
     * if this one is a virtual cube,
     * otherwise return just this cube
     *
     * @return the list of base cubes
     */
    public List<RolapCube> getBaseCubes() {
      if (baseCubes == null) {
        baseCubes = findBaseCubes(this);
      }
      return baseCubes;
    }

    /**
     * Locates all base cubes associated with the virtual cube.
     */
    private static List<RolapCube> findBaseCubes(RolapCube cube) {
      if (!cube.isVirtual()) {
        return Collections.singletonList(cube);
      }
      List<RolapCube> cubesList = new ArrayList<>();
      Set<RolapCube> cubes = new TreeSet<>(new RolapCube.CubeComparator());
      for (Member member : cube.getMeasures()) {
        if (member instanceof RolapStoredMeasure rolapStoredMeasure) {
          cubes.add(rolapStoredMeasure.getCube());
        } else if (member instanceof RolapHierarchy.RolapCalculatedMeasure rolapCalculatedMeasure) {
          RolapCube baseCube =
              rolapCalculatedMeasure.getBaseCube();
          if (baseCube != null) {
            cubes.add(baseCube);
          }
        }
      }
      cubesList.addAll(cubes);
      return cubesList;
    }

    public void flushCache(RolapConnection rolapConnection) {
        //Data cache exists in connection context
        final CacheControl cacheControl = rolapConnection.getCacheControl(null);
        cacheControl.flush(cacheControl.createMeasuresRegion(this));


        for(RolapHierarchy rolapHierarchy: this.hierarchyList){
            if (rolapHierarchy instanceof RolapCubeHierarchy rolapCubeHierarchy) {
                MemberReader memberReader = rolapCubeHierarchy.getMemberReader();
                if(memberReader instanceof RolapCubeHierarchy.CacheRolapCubeHierarchyMemberReader crhmr) {
                    ((MemberCacheHelper)crhmr.getMemberCache()).flushCache();
                    crhmr.getRolapCubeMemberCacheHelper().flushCache();
                }

                RolapHierarchy sharedRolapHierarchy = rolapCubeHierarchy.getRolapHierarchy();
                memberReader = sharedRolapHierarchy.getMemberReader();
                if (memberReader instanceof SmartMemberReader smartMemberReader) {
                    final MemberCacheHelper memberCacheHelper = (MemberCacheHelper) smartMemberReader.getMemberCache();
                    memberCacheHelper.flushCache();
                }
            }
        }
    }

    @Override
    public RolapDrillThroughAction getDefaultDrillThroughAction() {
        for(OlapAction action: this.actionList) {
            if(action instanceof RolapDrillThroughAction rolapDrillThroughAction
                && rolapDrillThroughAction.getIsDefault()) {
                return rolapDrillThroughAction;
            }
        }
        return null;
    }

    @Override
    public List<DrillThroughAction> getDrillThroughActions() {
        List<DrillThroughAction> res = new ArrayList<>();
        for (AbstractRolapAction action : this.actionList) {
            if (action instanceof DrillThroughAction drillThroughAction) {
                res.add(drillThroughAction);
            }
        }
        return res;
    }

    @Override
    public List<Member> getLevelMembers(Level level, boolean includeCalculated) {
        return getSchemaReader().withLocus().getLevelMembers(level, true);
    }

    @Override
    public int getLevelCardinality(
        Level level, boolean approximate, boolean materialize){
        return getSchemaReader().withLocus().getLevelCardinality(level, approximate, materialize);
    }

    public Context getContext() {
        return context;
    }

    public Optional<RolapWritebackTable> getWritebackTable() {
        return writebackTable;
    }
}
