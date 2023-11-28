package org.eclipse.daanse.olap.xmla.bridge.execute;

import mondrian.xmla.XmlaUtil;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.result.CellSet;
import org.eclipse.daanse.olap.api.result.CellSetAxis;
import org.eclipse.daanse.olap.api.result.CellSetAxisMetaData;
import org.eclipse.daanse.olap.api.result.IMondrianOlap4jProperty;
import org.eclipse.daanse.olap.api.result.Position;
import org.eclipse.daanse.olap.api.result.Property;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.mddataset.Axis;
import org.eclipse.daanse.xmla.api.mddataset.AxisInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellInfoItem;
import org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube;
import org.eclipse.daanse.xmla.api.mddataset.TupleType;
import org.eclipse.daanse.xmla.api.mddataset.Type;
import org.eclipse.daanse.xmla.model.record.exception.ExceptionR;
import org.eclipse.daanse.xmla.model.record.exception.MessagesR;
import org.eclipse.daanse.xmla.model.record.execute.statement.StatementResponseR;
import org.eclipse.daanse.xmla.model.record.mddataset.AxesInfoR;
import org.eclipse.daanse.xmla.model.record.mddataset.AxesR;
import org.eclipse.daanse.xmla.model.record.mddataset.AxisInfoR;
import org.eclipse.daanse.xmla.model.record.mddataset.AxisR;
import org.eclipse.daanse.xmla.model.record.mddataset.CellDataR;
import org.eclipse.daanse.xmla.model.record.mddataset.CellInfoItemR;
import org.eclipse.daanse.xmla.model.record.mddataset.CellInfoR;
import org.eclipse.daanse.xmla.model.record.mddataset.CubeInfoR;
import org.eclipse.daanse.xmla.model.record.mddataset.HierarchyInfoR;
import org.eclipse.daanse.xmla.model.record.mddataset.MddatasetR;
import org.eclipse.daanse.xmla.model.record.mddataset.MemberTypeR;
import org.eclipse.daanse.xmla.model.record.mddataset.OlapInfoCubeR;
import org.eclipse.daanse.xmla.model.record.mddataset.OlapInfoR;
import org.eclipse.daanse.xmla.model.record.mddataset.TupleTypeR;
import org.eclipse.daanse.xmla.model.record.mddataset.TuplesTypeR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Convertor {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(Convertor.class);
    private static XmlaUtil.ElementNameEncoder encoder =
        XmlaUtil.ElementNameEncoder.INSTANCE;

    protected static final Map<String, Property.StandardMemberProperty> longProps =
        new HashMap();

    static {
        longProps.put("UName", Property.StandardMemberProperty.MEMBER_UNIQUE_NAME);
        longProps.put("Caption", Property.StandardMemberProperty.MEMBER_CAPTION);
        longProps.put("LName", Property.StandardMemberProperty.LEVEL_UNIQUE_NAME);
        longProps.put("LNum", Property.StandardMemberProperty.LEVEL_NUMBER);
        longProps.put("DisplayInfo", Property.StandardMemberProperty.DISPLAY_INFO);
    }

    public static StatementResponse toStatementResponse(CellSet cellSet, boolean omitDefaultSlicerInfo) {

        OlapInfoR olapInfo = getOlapInfo(cellSet, omitDefaultSlicerInfo);

        AxesR axes = getAxes(cellSet, omitDefaultSlicerInfo, List.of()); //TODO
        CellDataR cellData = getCellData(cellSet);
        ExceptionR exception = null;
        MessagesR messages = null;

        MddatasetR mdDataSet = new MddatasetR(
            olapInfo,
            axes,
            cellData,
            exception,
            messages
        );

        return new StatementResponseR(mdDataSet, null);
    }

    private static CellDataR getCellData(CellSet cellSet) {
        //TODO
        return null;
    }

    private static AxesR getAxes(CellSet cellSet, boolean omitDefaultSlicerInfo, List<Hierarchy> slicerAxisHierarchies) {
        List<Axis> axisList = new ArrayList<>();
        final List<CellSetAxis> axes = cellSet.getAxes();
        for (int i = 0; i < axes.size(); i++) {
            final CellSetAxis axis = axes.get(i);
            final List<Property> props = getProps(axis.getAxisMetaData());
            axisList.add(getAxis(axis, props, "Axis" + i));
        }

        ////////////////////////////////////////////
        // now generate SlicerAxis information
        //

        CellSetAxis slicerAxis = cellSet.getFilterAxis();
        if (omitDefaultSlicerInfo) {
            // We always write a slicer axis. There are two 'empty' cases:
            // zero positions (which happens when the WHERE clause evalutes
            // to an empty set) or one position containing a tuple of zero
            // members (which happens when there is no WHERE clause) and we
            // need to be able to distinguish between the two.

            if(!slicerAxisHierarchies.isEmpty()) {
                axisList.add(getAxis(
                    slicerAxis,
                    getProps(slicerAxis.getAxisMetaData()),
                    "SlicerAxis"));
            }
        } else {
            List<Hierarchy> hierarchies = slicerAxisHierarchies;
            List<Type> setTypes = new ArrayList<>();
            List<TupleType> tuples = new ArrayList<>();
            List<MemberTypeR> mem = new ArrayList<>();
            tuples.add(new TupleTypeR(mem));
            setTypes.add(new TuplesTypeR(tuples));
            new AxisR(setTypes, "SlicerAxis");


            Map<String, Integer> memberMap = new HashMap<>();
            Member positionMember;
            final List<Position> slicerPositions =
                slicerAxis.getPositions();
            if (slicerPositions != null
                && !slicerPositions.isEmpty())
            {
                final Position pos0 = slicerPositions.get(0);
                int i = 0;
                for (Member member : pos0.getMembers()) {
                    memberMap.put(member.getHierarchy().getName(), i++);
                }
            }

            final List<Member> slicerMembers =
                slicerPositions.isEmpty()
                    ? List.of()
                    : slicerPositions.get(0).getMembers();
            for (Hierarchy hierarchy : hierarchies) {
                // Find which member is on the slicer.
                // If it's not explicitly there, use the default member.
                Member member = hierarchy.getDefaultMember();
                final Integer indexPosition =
                    memberMap.get(hierarchy.getName());
                if (indexPosition != null) {
                    positionMember = slicerMembers.get(indexPosition);
                } else {
                    positionMember = null;
                }
                for (Member slicerMember : slicerMembers) {
                    if (slicerMember.getHierarchy().equals(hierarchy)) {
                        member = slicerMember;
                        break;
                    }
                }

                if (member != null) {
                    if (positionMember != null) {
                        mem.add(
                        getMember(
                            positionMember, null,
                            slicerPositions.get(0), indexPosition,
                            getProps(slicerAxis.getAxisMetaData())));
                    } else {
                        slicerAxis(
                            member,
                            getProps(slicerAxis.getAxisMetaData()));
                    }
                } else {
                    LOGGER.warn(
                        "Can not create SlicerAxis: null default member for Hierarchy {}",
                        hierarchy.getUniqueName());
                }
            }
        }

        //
        ////////////////////////////////////////////


        return new AxesR(axisList);
    }

    private static MemberTypeR slicerAxis(Member member, List<Property> props) {
        List<CellInfoItem> any = new ArrayList<>();
        for (Property prop : props) {
            Object value;
            Property longProp = longProps.get(prop.getName());
            if (longProp == null) {
                longProp = prop;
            }
            if (longProp == Property.StandardMemberProperty.DISPLAY_INFO) {
                Integer childrenCard =
                    (Integer) member.getPropertyValue(
                        Property.StandardMemberProperty.CHILDREN_CARDINALITY.getName());
                // NOTE: don't know if this is correct for
                // SlicerAxis
                int displayInfo = 0xffff & childrenCard;
/*
                    int displayInfo =
                        calculateDisplayInfo((j == 0 ? null : positions[j - 1]),
                          (j + 1 == positions.length ? null : positions[j + 1]),
                          member, k, childrenCard.intValue());
*/
                value = displayInfo;
            } else if (longProp == Property.StandardMemberProperty.DEPTH) {
                value = member.getDepth();
            } else {
                value = member.getPropertyValue(longProp.getName());
            }
            if(value == null) {
                value = getDefaultValue(prop);
            }
            if (value != null) {
                any.add(new CellInfoItemR(encoder.encode(prop.getName()), value.toString(), Optional.empty()));
            }
        }
        return new MemberTypeR(any,
            member.getHierarchy().getUniqueName());
    }

    private static MemberTypeR getMember(
        Member member, Position prevPosition,
        Position nextPosition, int k, List<Property> props) {
        List<CellInfoItem> any = new ArrayList<>();
        for (final Property prop : props) {
            Object value = null;
            Property longProp = (longProps.get(prop.getName()) != null)
                ? longProps.get(prop.getName()) : prop;
            if (longProp == Property.StandardMemberProperty.DISPLAY_INFO) {
                Integer childrenCard = (Integer) member
                    .getPropertyValue(
                        Property.StandardMemberProperty.CHILDREN_CARDINALITY.getName());
                value = calculateDisplayInfo(
                    prevPosition, nextPosition, member, k, childrenCard);
            } else if (longProp == Property.StandardMemberProperty.DEPTH) {
                value = member.getDepth();
            } else {
                if(longProp instanceof IMondrianOlap4jProperty currentProperty) {
                    String thisHierarchyName = member.getHierarchy().getName();
                    String thatHierarchyName = currentProperty.getLevel()
                        .getHierarchy().getName();
                    if (thisHierarchyName.equals(thatHierarchyName)) {
                        value = getHierarchyProperty(member, longProp);
                    }
                    else {
                        // Property belongs to other hierarchy
                        continue;
                    }
                }
                else {
                    value = member.getPropertyValue(longProp.getName());
                }
            }
            if(longProp != prop && value == null) {
                value = getDefaultValue(prop);
            }
            if (value != null) {
                if(longProp instanceof IMondrianOlap4jProperty) {
                    any.add(new CellInfoItemR(encoder.encode(prop.getName()), value.toString(), getXsdType(prop)));
                }
                else {
                    any.add(new CellInfoItemR(encoder.encode(prop.getName()), value.toString(), Optional.empty()));
                }
            }
        }
        return new MemberTypeR(any,
            member.getHierarchy().getUniqueName());


    }

    private static Object getHierarchyProperty(Member member, Property longProp) {
        return null;
        //TODO
    }

    private static Optional<String> getXsdType(Property prop) {
        return Optional.empty();
        //TODO
    }

    private static Object calculateDisplayInfo(Position prevPosition, Position nextPosition, Member member, int k, Integer childrenCard) {
        return null;
        //TODO
    }

    private static Object getDefaultValue(Property prop) {
        //TODO
        return null;
    }

    private static Axis getAxis(CellSetAxis axis, List<Property> props, String name) {
        //TODO
        return null;
    }

    private static OlapInfoR getOlapInfo(CellSet cellSet, boolean omitDefaultSlicerInfo) {
        Cube cube = cellSet.getMetaData().getCube();
        String cubeName = cube.getName();
        Instant lastDataUpdate = Instant.now(); //TODO get from getMondrianOlap4jSchema()
        Instant lastSchemaUpdate = Instant.now(); //TODO
        OlapInfoCubeR olapInfoCube = new OlapInfoCubeR(cubeName, lastDataUpdate, lastSchemaUpdate);
        List<OlapInfoCube> cubes = List.of(olapInfoCube);
        CubeInfoR cubeInfo = new CubeInfoR(cubes);

        AxesInfoR axesInfo = getAxesInfo(cellSet, cube, omitDefaultSlicerInfo);

        CellInfoR cellInfo = getCellInfo(cellSet);

        return new OlapInfoR(cubeInfo, axesInfo, cellInfo);
    }

    private static CellInfoR getCellInfo(CellSet cellSet) {
        return null;
        //TODO

    }

    private static AxesInfoR getAxesInfo(CellSet cellSet, Cube cube, boolean omitDefaultSlicerInfo)
    {
        List<AxisInfo> axisInfo = new ArrayList<>();
        final List<CellSetAxis> axes = cellSet.getAxes();
        List<Hierarchy> axisHierarchyList = new ArrayList<>();
        for (int i = 0; i < axes.size(); i++) {
            axisInfo.add(getAxisInfo(axes.get(i), "Axis" + i));
            axisHierarchyList.addAll(getHierarchies(axes.get(i)));
        }
        ///////////////////////////////////////////////
        // create AxesInfo for slicer axes
        //
        List<Hierarchy> hierarchies = new ArrayList<>();
        CellSetAxis slicerAxis = cellSet.getFilterAxis();
        if (omitDefaultSlicerInfo) {
            axisInfo.add(getAxisInfo(slicerAxis, "SlicerAxis"));
            hierarchies.addAll(getHierarchies(slicerAxis));
        } else {
            // The slicer axes contains the default hierarchy
            // of each dimension not seen on another axis.
            List<Dimension> unseenDimensionList =
                new ArrayList(cube.getDimensions() != null ? Arrays.asList(cube.getDimensions()) : List.of());
            for (Hierarchy hier1 : axisHierarchyList) {
                unseenDimensionList.remove(hier1.getDimension());
            }
            hierarchies = new ArrayList<>();
            for (Dimension dimension : unseenDimensionList) {
                for (Hierarchy hierarchy : dimension.getHierarchies()) {
                    hierarchies.add(hierarchy);
                }
            }
            axisInfo.add(new AxisInfoR(getHierarchyInfoList(hierarchies, getProps(slicerAxis.getAxisMetaData())), "SlicerAxis" ));
        }
        //slicerAxisHierarchies = hierarchies;
        return new AxesInfoR(axisInfo);
    }

    private static List<Hierarchy> getHierarchies(CellSetAxis axis) {
        List<Hierarchy> hierarchies = new ArrayList();
        Iterator<Position> it = axis.getPositions().iterator();
        if (it.hasNext()) {
            final Position position = it.next();
            hierarchies = new ArrayList<>();
            for (Member member : position) {
                hierarchies.add(member.getHierarchy());
            }
        } else {
            hierarchies = axis.getAxisMetaData().getHierarchies();
        }
        return hierarchies;

    }

    private static AxisInfo getAxisInfo(CellSetAxis axis, String axisName) {
        List<Hierarchy> hierarchies;
        List<Property> props = new ArrayList<>(getProps(axis.getAxisMetaData()));
        Iterator<Position> it = axis.getPositions().iterator();
        if (it.hasNext()) {
            final Position position = it.next();
            hierarchies = new ArrayList<>();
            for (Member member : position) {
                hierarchies.add(member.getHierarchy());
            }
        } else {
            hierarchies = axis.getAxisMetaData().getHierarchies();
        }

        // remove a property without a valid associated hierarchy
        props.removeIf(prop -> !isValidProp(axis.getPositions(), prop));

        return new AxisInfoR(getHierarchyInfoList(hierarchies, props), axisName );
    }

    private static List<HierarchyInfo> getHierarchyInfoList(List<Hierarchy> hierarchies, List<Property> props) {
        List<HierarchyInfo> result = new ArrayList<>();
        for (Hierarchy hierarchy : hierarchies) {
            List<CellInfoItem> ciiList = new ArrayList<>();
            for (final Property prop : props) {
                if (prop instanceof IMondrianOlap4jProperty iMondrianProp) {
                    Optional<CellInfoItem> op = getCellInfoItemProperty(hierarchy, iMondrianProp);
                    if (op.isPresent()) {
                        ciiList.add(op.get());
                    }
                } else {
                    ciiList.add(getCellInfoItem(hierarchy, prop));
                }
            }
            result.add(new HierarchyInfoR(ciiList, hierarchy.getUniqueName()));
        }
        return result;

    }


    private static Optional<CellInfoItem> getCellInfoItemProperty(
        Hierarchy hierarchy,
        final IMondrianOlap4jProperty prop
    )
    {
        String thisHierarchyName = hierarchy.getName();
        String thatHierarchiName = prop.getLevel()
            .getHierarchy().getName();
        if (thisHierarchyName.equals(thatHierarchiName)) {
            return Optional.of(getCellInfoItem(hierarchy, prop));
        }
        return Optional.empty();
    }

    private static CellInfoItem getCellInfoItem(
        Hierarchy hierarchy,
        final Property prop
    )
    {
        final String encodedProp = encoder
            .encode(prop.getName());
        final Object[] attributes = getAttributes(
            prop, hierarchy);
        return new CellInfoItemR(encodedProp, attributes != null && attributes.length > 0 ?  attributes[0].toString() : null,
            Optional.empty());
    }

    private static Object[] getAttributes(Property prop, Hierarchy hierarchy) {
        return null;
        //TODO
    }

    private static List<Property> getProps(CellSetAxisMetaData axisMetaData) {
        //TODO
        return List.of();
    }

    private static boolean isValidProp(List<Position> positions, Property prop) {
        return false;
        //TODO
    }

}
