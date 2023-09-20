/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2018 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap;

import static mondrian.rolap.util.ExpressionUtil.genericExpression;
import static mondrian.rolap.util.LevelUtil.getPropertyExp;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mondrian.olap.api.NameSegment;
import mondrian.olap.api.Segment;
import org.eclipse.daanse.db.dialect.api.BestFitColumnType;
import org.eclipse.daanse.db.dialect.api.Datatype;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.LevelType;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Closure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Column;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.CubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Expression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Relation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.InternalTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.PropertyTypeEnum;
import org.olap4j.impl.UnmodifiableArrayMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.olap.DimensionType;
import mondrian.olap.IdImpl;
import mondrian.olap.LevelBase;
import mondrian.olap.MatchType;
import mondrian.olap.Property;
import mondrian.olap.SchemaReader;
import mondrian.olap.Util;
import mondrian.resource.MondrianResource;
import mondrian.rolap.format.FormatterCreateContext;
import mondrian.rolap.format.FormatterFactory;
import mondrian.rolap.util.ExpressionUtil;
import mondrian.rolap.util.LevelUtil;
import mondrian.rolap.util.RelationUtil;
import mondrian.spi.PropertyFormatter;

/**
 * <code>RolapLevel</code> implements {@link Level} for a ROLAP database.
 *
 * @author jhyde
 * @since 10 August, 2001
 */
public class RolapLevel extends LevelBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolapLevel.class);

    /**
     * The column or expression which yields the level's key.
     */
    protected Expression keyExp;

    /**
     * The column or expression which yields the level's ordinal.
     */
    protected Expression ordinalExp;

    /**
     * The column or expression which yields the level members' caption.
     */
    protected Expression captionExp;

    private final Datatype datatype;

    private final int flags;

    private boolean caseSensitive;

    static final int FLAG_ALL = 0x02;

    /**
     * For SQL generator. Whether values of "column" are unique globally
     * unique (as opposed to unique only within the context of the parent
     * member).
     */
    static final int FLAG_UNIQUE = 0x04;

    private RolapLevel closedPeerLevel;

    protected RolapProperty[] properties;
    private final RolapProperty[] inheritedProperties;

    /**
     * Ths expression which gives the name of members of this level. If null,
     * members are named using the key expression.
     */
    protected Expression nameExp;
    /** The expression which joins to the parent member in a parent-child
     * hierarchy, or null if this is a regular hierarchy. */
    protected Expression parentExp;
    /** Value which indicates a null parent in a parent-child hierarchy. */
    private final String nullParentValue;

    /** Condition under which members are hidden. */
    private final HideMemberCondition hideMemberCondition;
    protected final Closure xmlClosure;
    private final Map<String, Object> metadata;
    private final BestFitColumnType internalType; // may be null

    /**
     * Creates a level.
     *
     * @pre parentExp != null || nullParentValue == null
     * @pre properties != null
     * @pre levelType != null
     * @pre hideMemberCondition != null
     */
    RolapLevel(
        RolapHierarchy hierarchy,
        String name,
        String caption,
        boolean visible,
        String description,
        int depth,
        Expression keyExp,
        Expression nameExp,
        Expression captionExp,
        Expression ordinalExp,
        Expression parentExp,
        String nullParentValue,
        Closure xmlClosure,
        RolapProperty[] properties,
        int flags,
        Datatype datatype,
        BestFitColumnType internalType,
        HideMemberCondition hideMemberCondition,
        LevelType levelType,
        String approxRowCount,
        Map<String, Object> metadata, boolean caseSensitive)
    {
        super(
            hierarchy, name, caption, visible, description, depth, levelType);
        assert metadata != null;
        Util.assertPrecondition(properties != null, "properties != null");
        Util.assertPrecondition(
            hideMemberCondition != null,
            "hideMemberCondition != null");
        Util.assertPrecondition(levelType != null, "levelType != null");

        if (keyExp instanceof Column) {
            checkColumn((Column) keyExp);
        }
        this.metadata = metadata;
        this.approxRowCount = loadApproxRowCount(approxRowCount);
        this.flags = flags;
        this.datatype = datatype;
        this.keyExp = keyExp;
        this.caseSensitive = caseSensitive;
        if (nameExp != null) {
            if (nameExp instanceof Column) {
                checkColumn((Column) nameExp);
            }
        }
        this.nameExp = nameExp;
        if (captionExp != null) {
            if (captionExp instanceof Column) {
                checkColumn((Column) captionExp);
            }
        }
        this.captionExp = captionExp;
        if (ordinalExp != null) {
            if (ordinalExp instanceof Column) {
                checkColumn((Column) ordinalExp);
            }
            this.ordinalExp = ordinalExp;
        } else {
            this.ordinalExp = this.keyExp;
        }
        if (parentExp instanceof Column) {
            checkColumn((Column) parentExp);
        }
        this.parentExp = parentExp;
        if (parentExp != null) {
            Util.assertTrue(
                !isAll(),
                new StringBuilder("'All' level '").append(this).append("' must not be parent-child").toString());
            Util.assertTrue(
                isUnique(),
                new StringBuilder("Parent-child level '").append(this)
                .append("' must have uniqueMembers=\"true\"").toString());
        }
        this.nullParentValue = nullParentValue;
        Util.assertPrecondition(
            parentExp != null || nullParentValue == null,
            "parentExp != null || nullParentValue == null");
        this.xmlClosure = xmlClosure;
        for (RolapProperty property : properties) {
            if (property.getExp() instanceof Column) {
                checkColumn((Column) property.getExp());
            }
        }
        this.properties = properties;
        List<Property> list = new ArrayList<>();
        for (Level level = this; level != null;
             level = level.getParentLevel())
        {
            final Property[] levelProperties = level.getProperties();
            for (final Property levelProperty : levelProperties) {
                Property existingProperty = lookupProperty(
                    list, levelProperty.getName());
                if (existingProperty == null) {
                    list.add(levelProperty);
                } else if (existingProperty.getType()
                    != levelProperty.getType())
                {
                    throw Util.newError(
                        new StringBuilder("Property ").append(this.getName(caseSensitive)).append(".")
                        .append(levelProperty.getName()).append(" overrides a ")
                        .append("property with the same name but different type").toString());
                }
            }
        }
        this.inheritedProperties = list.toArray(new RolapProperty[list.size()]);

        Dimension dim = hierarchy.getDimension(caseSensitive);
        if (dim.getDimensionType() == DimensionType.TIME_DIMENSION) {
            if (!levelType.isTime() && !isAll()) {
                throw MondrianResource.instance()
                    .NonTimeLevelInTimeHierarchy.ex(getUniqueName());
            }
        } else if (dim.getDimensionType() == null) {
            // there was no dimension type assigned to the dimension
            // - check later
        } else {
            if (levelType.isTime()) {
                throw MondrianResource.instance()
                    .TimeLevelInNonTimeHierarchy.ex(getUniqueName());
            }
        }
        this.internalType = internalType;
        this.hideMemberCondition = hideMemberCondition;
    }

    @Override
	public RolapHierarchy getHierarchy(boolean caseSensitive) {
        return (RolapHierarchy) hierarchy;
    }

    @Override
	public Map<String, Object> getMetadata() {
        return metadata;
    }

    private int loadApproxRowCount(String approxRowCount) {
        boolean notNullAndNumeric =
            approxRowCount != null
                && approxRowCount.matches("^\\d+$");
        if (notNullAndNumeric) {
            return Integer.parseInt(approxRowCount);
        } else {
            // if approxRowCount is not set, return MIN_VALUE to indicate
            return Integer.MIN_VALUE;
        }
    }

    @Override
	protected Logger getLogger() {
        return LOGGER;
    }

    String getTableName() {
        String tableName = null;

        Expression expr = getKeyExp();
        if (expr instanceof Column mc) {
            tableName = ExpressionUtil.getTableAlias(mc);
        }
        return tableName;
    }

    public Expression getKeyExp() {
        return keyExp;
    }

    public Expression getOrdinalExp() {
        return ordinalExp;
    }

    public Expression getCaptionExp() {
        return captionExp;
    }

    public boolean hasCaptionColumn() {
        return captionExp != null;
    }

    public boolean hasOrdinalExp() {
      return !getOrdinalExp().equals(getKeyExp());
    }

    final int getFlags() {
        return flags;
    }

    HideMemberCondition getHideMemberCondition() {
        return hideMemberCondition;
    }

    public final boolean isUnique() {
        return (flags & FLAG_UNIQUE) != 0;
    }

    public final Datatype getDatatype() {
        return datatype;
    }

    final String getNullParentValue() {
        return nullParentValue;
    }

    /**
     * Returns whether this level is parent-child.
     */
    public boolean isParentChild() {
        return parentExp != null;
    }

    public Expression getParentExp() {
        return parentExp;
    }

    // RME: this has to be public for two of the DrillThroughTest test.
    public
    Expression getNameExp() {
        return nameExp;
    }

    private Property lookupProperty(List<Property> list, String propertyName) {
        for (Property property : list) {
            if (property.getName().equals(propertyName)) {
                return property;
            }
        }
        return null;
    }

    RolapLevel(
        RolapHierarchy hierarchy,
        int depth,
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.Level xmlLevel, boolean caseSensitive)
    {

        this(
            hierarchy,
            xmlLevel.name(),
            xmlLevel.caption(),
            xmlLevel.visible(),
            xmlLevel.description(),
            depth,
            LevelUtil.getKeyExp(xmlLevel),
            LevelUtil.getNameExp(xmlLevel),
            LevelUtil.getCaptionExp(xmlLevel),
            LevelUtil.getOrdinalExp(xmlLevel),
            LevelUtil.getParentExp(xmlLevel),
            xmlLevel.nullParentValue(),
            xmlLevel.closure(),
            createProperties(xmlLevel),
            (xmlLevel.uniqueMembers() ? FLAG_UNIQUE : 0),
            org.eclipse.daanse.db.dialect.api.Datatype.fromValue(xmlLevel.type().getValue()),
            toInternalType(xmlLevel.internalType()),
            HideMemberCondition.valueOf(xmlLevel.hideMemberIf().getValue()),
            LevelType.fromValue(
                xmlLevel.levelType().getValue().equals("TimeHalfYear")
                    ? "TimeHalfYears"
                    : xmlLevel.levelType().getValue()),
            xmlLevel.approxRowCount(),
            RolapHierarchy.createMetadataMap(xmlLevel.annotations()), caseSensitive);

        if (!Util.isEmpty(xmlLevel.caption())) {
            setCaption(xmlLevel.caption());
        }

        FormatterCreateContext memberFormatterContext =
            new FormatterCreateContext.Builder(getUniqueName())
                .formatterDef(xmlLevel.memberFormatter())
                .formatterAttr(xmlLevel.formatter())
                .build();
        memberFormatter =
            FormatterFactory.instance()
                .createRolapMemberFormatter(memberFormatterContext);
    }

    // helper for constructor
    private static RolapProperty[] createProperties(org.eclipse.daanse.olap.rolap.dbmapper.model.api.Level xmlLevel)
    {
        List<RolapProperty> list = new ArrayList<>();
        final Expression nameExp = LevelUtil.getNameExp(xmlLevel);

        if (nameExp != null) {
            list.add(
                new RolapProperty(
                    Property.NAME_PROPERTY.name, Property.Datatype.TYPE_STRING,
                    nameExp, null, null, null, true,
                    Property.NAME_PROPERTY.description));
        }
        for (int i = 0; i < xmlLevel.properties().size(); i++) {
            org.eclipse.daanse.olap.rolap.dbmapper.model.api.Property xmlProperty = xmlLevel.properties().get(i);

            FormatterCreateContext formatterContext =
                    new FormatterCreateContext.Builder(xmlProperty.name())
                        .formatterDef(xmlProperty.propertyFormatter())
                        .formatterAttr(xmlProperty.formatter())
                        .build();
            PropertyFormatter formatter =
                FormatterFactory.instance()
                    .createPropertyFormatter(formatterContext);

            list.add(
                new RolapProperty(
                    xmlProperty.name(),
                    convertPropertyTypeNameToCode(xmlProperty.type()),
                    getPropertyExp(xmlLevel, i),
                    formatter,
                    xmlProperty.caption(),
                    xmlLevel.properties().get(i).dependsOnLevelValue(),
                    false,
                    xmlProperty.description()));
        }
        return list.toArray(new RolapProperty[list.size()]);
    }

    private static Property.Datatype convertPropertyTypeNameToCode(
        PropertyTypeEnum type)
    {
        if (type.equals(PropertyTypeEnum.STRING)) {
            return Property.Datatype.TYPE_STRING;
        } else if (type.equals(PropertyTypeEnum.NUMERIC)) {
            return Property.Datatype.TYPE_NUMERIC;
        } else if (type.equals(PropertyTypeEnum.INTEGER)) {
            return Property.Datatype.TYPE_INTEGER;
        } else if (type.equals(PropertyTypeEnum.LONG)) {
            return Property.Datatype.TYPE_LONG;
        } else if (type.equals(PropertyTypeEnum.BOOLEAN)) {
            return Property.Datatype.TYPE_BOOLEAN;
        } else if (type.equals(PropertyTypeEnum.TIMESTAMP)) {
            return Property.Datatype.TYPE_TIMESTAMP;
        } else if (type.equals(PropertyTypeEnum.TIME)) {
            return Property.Datatype.TYPE_TIME;
        } else if (type.equals(PropertyTypeEnum.DATE)) {
            return Property.Datatype.TYPE_DATE;
        } else {
            throw Util.newError(new StringBuilder("Unknown property type '")
                .append(type).append("'").toString());
        }
    }

    private void checkColumn(Column nameColumn) {
        final RolapHierarchy rolapHierarchy = (RolapHierarchy) hierarchy;
        if (nameColumn.table() == null) {
            final Relation table = rolapHierarchy.getUniqueTable();
            if (table == null) {
                throw Util.newError(
                    new StringBuilder("must specify a table for level ").append(getUniqueName())
                    .append(" because hierarchy has more than one table").toString());
            }
            nameColumn.setTable(RelationUtil.getAlias(table));
        } else {
            if (!rolapHierarchy.tableExists(nameColumn.table())) {
                throw Util.newError(
                    new StringBuilder("Table '").append(nameColumn.table())
                        .append("' not found").toString());
            }
        }
    }

    void init(CubeDimension xmlDimension) {
        if (xmlClosure != null) {
            final RolapDimension dimension = ((RolapHierarchy) hierarchy)
                .createClosedPeerDimension(this, xmlClosure, caseSensitive);
            closedPeerLevel =
                    (RolapLevel) dimension.getHierarchies()[0].getLevels()[1];
        }
    }

    @Override
	public final boolean isAll() {
        return (flags & FLAG_ALL) != 0;
    }

    @Override
	public boolean areMembersUnique() {
        return (depth == 0) || (depth == 1) && hierarchy.hasAll();
    }

    public String getTableAlias() {
        return ExpressionUtil.getTableAlias(keyExp);
    }

    @Override
	public RolapProperty[] getProperties() {
        return properties;
    }

    @Override
	public Property[] getInheritedProperties() {
        return inheritedProperties;
    }

    @Override
	public int getApproxRowCount() {
        return approxRowCount;
    }

    private static final Map<String, BestFitColumnType> VALUES =
        UnmodifiableArrayMap.of(
            "int", BestFitColumnType.INT,
            "double", BestFitColumnType.DOUBLE,
            "Object", BestFitColumnType.OBJECT,
            "String", BestFitColumnType.STRING,
            "long", BestFitColumnType.LONG);

    private static BestFitColumnType toInternalType(InternalTypeEnum internalType) {
        BestFitColumnType type = VALUES.get(internalType == null ? null : internalType.getValue());
        if (type == null && internalType != null) {
            throw Util.newError(
                new StringBuilder("Invalid value '").append(internalType.getValue())
                    .append("' for attribute 'internalType' of element 'Level'. ")
                    .append("Valid values are: ")
                    .append(VALUES.keySet()).toString());
        }
        return type;
    }

    public BestFitColumnType getInternalType() {
        return internalType;
    }

    /**
     * Conditions under which a level's members may be hidden (thereby creating
     * a <dfn>ragged hierarchy</dfn>).
     */
    public enum HideMemberCondition {
        /** A member always appears. */
        Never,

        /** A member doesn't appear if its name is null or empty. */
        IfBlankName,

        /** A member appears unless its name matches its parent's. */
        IfParentsName
    }

    public OlapElement lookupChild(SchemaReader schemaReader, Segment name) {
        return lookupChild(schemaReader, name, MatchType.EXACT);
    }

    @Override
	public OlapElement lookupChild(
            SchemaReader schemaReader, Segment name, MatchType matchType)
    {
        if (name instanceof IdImpl.KeySegment keySegment) {
            List<Comparable> keyValues = new ArrayList<>();
            for (NameSegment nameSegment : keySegment.getKeyParts()) {
                final String keyValue = nameSegment.getName();
                if (RolapUtil.mdxNullLiteral().equalsIgnoreCase(keyValue)) {
                    keyValues.add(RolapUtil.sqlNullValue);
                } else {
                    keyValues.add(keyValue);
                }
            }
            final List<Expression> keyExps = getInheritedKeyExps();
            if (keyExps.size() != keyValues.size()) {
                throw Util.newError(
                    new StringBuilder("Wrong number of values in member key; ")
                        .append(keySegment).append(" has ").append(keyValues.size())
                        .append(" values, whereas level's key has ").append(keyExps.size())
                        .append(" columns ")
                        .append(new AbstractList<String>() {
                            @Override
							public String get(int index) {
                                return genericExpression(keyExps.get(index));
                            }
                            @Override
							public int size() {
                            return keyExps.size();
                        }})
                        .append(".").toString());
            }
            return getHierarchy(caseSensitive).getMemberReader().getMemberByKey(
                this, keyValues);
        }
        List<Member> levelMembers = schemaReader.getLevelMembers(this, true);
        if (levelMembers.size() > 0) {
            Member parent = levelMembers.get(0).getParentMember();
            return
                RolapUtil.findBestMemberMatch(
                    levelMembers,
                    (RolapMember) parent,
                    this,
                    name,
                    matchType, caseSensitive);
        }
        return null;
    }

    private List<Expression> getInheritedKeyExps() {
        final List<Expression> list =
            new ArrayList<>();
        for (RolapLevel x = this;; x = (RolapLevel) x.getParentLevel()) {
            final Expression keyExp1 = x.getKeyExp();
            if (keyExp1 != null) {
                list.add(keyExp1);
            }
            if (x.isUnique()) {
                break;
            }
        }
        return list;
    }

    /**
     * Indicates that level is not ragged and not a parent/child level.
     */
    public boolean isSimple() {
        // most ragged hierarchies are not simple -- see isTooRagged.
        if (isTooRagged()) {
            return false;
        }
        if (isParentChild()) {
            return false;
        }
        // does not work for measures
        if (isMeasure(caseSensitive)) {
            return false;
        }
        return true;
    }

    /**
     * Determines whether the specified level is too ragged for native
     * evaluation, which is able to handle one special case of a ragged
     * hierarchy: when the level specified in the query is the leaf level of
     * the hierarchy and HideMemberCondition for the level is IfBlankName.
     * This is true even if higher levels of the hierarchy can be hidden
     * because even in that case the only column that needs to be read is the
     * column that holds the leaf. IfParentsName can't be handled even at the
     * leaf level because in the general case we aren't reading the column
     * that holds the parent. Also, IfBlankName can't be handled for non-leaf
     * levels because we would have to read the column for the next level
     * down for members with blank names.
     *
     * @return true if the specified level is too ragged for native
     *         evaluation.
     */
    private boolean isTooRagged() {
        // Is this the special case of raggedness that native evaluation
        // is able to handle?
        if (getDepth() == getHierarchy(caseSensitive).getLevels().length - 1) {
            switch (getHideMemberCondition()) {
            case Never:
            case IfBlankName:
                return false;
            default:
                return true;
            }
        }
        // Handle the general case in the traditional way.
        return getHierarchy(caseSensitive).isRagged();
    }


    /**
     * Returns true when the level is part of a parent/child hierarchy and has
     * an equivalent closed level.
     */
    boolean hasClosedPeer() {
        return closedPeerLevel != null;
    }

    public RolapLevel getClosedPeer() {
        return closedPeerLevel;
    }

    public static RolapLevel lookupLevel(
        RolapLevel[] levels,
        String levelName, boolean caseSensitive)
    {
        for (RolapLevel level : levels) {
            if (level.getName(caseSensitive).equals(levelName)) {
                return level;
            }
        }
        return null;
    }

}
