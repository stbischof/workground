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
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.olap.xmla.bridge.execute;

import mondrian.rolap.BitKey;
import mondrian.rolap.RolapBaseCubeMeasure;
import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapCubeMember;
import mondrian.rolap.RolapHierarchy;
import mondrian.rolap.RolapMember;
import mondrian.rolap.RolapStar;
import mondrian.rolap.RolapStoredMeasure;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.result.AllocationPolicy;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.impl.ScenarioImpl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WriteBackService {

    public void commit(Scenario scenario, Connection con) {
        DataSource dataSource = con.getDataSource();
        try (final java.sql.Connection connection = dataSource.getConnection(); final Statement statement = connection.createStatement()) {
            //TODO add cube as method parameter
            statement.execute("delete from write_back_data");
            List<ScenarioImpl.WritebackCell> wbcs = scenario.getWritebackCells();
            for (ScenarioImpl.WritebackCell wbc : wbcs) {
                Member[] members = wbc.getMembersByOrdinal();
                if (members != null && members.length > 0 &&  members[0] instanceof RolapBaseCubeMeasure rolapBaseCubeMeasure) {
                    Cube cube = rolapBaseCubeMeasure.getCube();
                    String membersString = getMembersString(members);
                    statement.executeUpdate("INSERT INTO write_back_data(currentValue, newValue, allocationPolicy, CUBE_NAME, memberUniqueNames) values("
                        + wbc.getCurrentValue() + ", "
                        + wbc.getNewValue() + ", '"
                        + wbc.getAllocationPolicy().name() + "', '"
                        + cube.getName() + "', '"
                        + membersString + "')");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String getMembersString(Member[] members) {
        return Arrays.stream(members).map(String::valueOf).collect(Collectors.joining(","));
    }

    public List<ScenarioImpl.WritebackCell> getWritebackCellList(Cube cube) {
        List<ScenarioImpl.WritebackCell> result = new ArrayList<>();
        List<WriteBackData> writeBackDataList = getWriteBackData(cube);
        for (WriteBackData writeBackData : writeBackDataList) {
            List<RolapMember> members = getMembers(writeBackData.getMemberUniqueNames(), cube);
            final RolapStoredMeasure measure = (RolapStoredMeasure) members.get(0);
            final RolapStar.Measure starMeasure =
                (RolapStar.Measure) measure.getStarMeasure();
            assert starMeasure != null;
            int starColumnCount = starMeasure.getStar().getColumnCount();
            final BitKey constrainedColumnsBitKey =
                BitKey.Factory.makeBitKey(starColumnCount);
            Object[] keyValues = new Object[starColumnCount];

            ScenarioImpl.WritebackCell writeBackCell = new ScenarioImpl.WritebackCell(
                (RolapCube) cube,
                members,
                constrainedColumnsBitKey,
                keyValues,
                writeBackData.getNewValue(),
                writeBackData.getCurrentValue(),
                writeBackData.getAllocationPolicy()
            );
            result.add(writeBackCell);
        }
        return result;
    }

    private List<RolapMember> getMembers(List<String> memberUniqueNames, Cube cube) {
        List<RolapMember> result = new ArrayList<>();
        if (!memberUniqueNames.isEmpty()) {
            Optional<Member> oMeasure = cube.getMeasures().stream().filter(m -> memberUniqueNames.get(0).equals(m.getUniqueName())).findFirst();
            if (oMeasure.isPresent()) {
                result.add((RolapMember) oMeasure.get());
            }
            if (memberUniqueNames.size() > 1) {
                List<RolapHierarchy> hierarchies = ((RolapCube)cube).getHierarchies();
                if (hierarchies != null) {
                    for (int i = 1; i < memberUniqueNames.size(); i++) {
                        String memberUniqueName = memberUniqueNames.get(i);
                        String hierarchyName = getHierarchyName(memberUniqueName);
                        List<String> memberNames = getMemberNames(memberUniqueName);
                        Optional<RolapHierarchy> oh = hierarchies.stream().filter(h -> h.getName().equals(hierarchyName)).findFirst();
                        if (oh.isPresent()) {
                            Optional<RolapMember> oRm = getRolapHierarchy(oh.get().getLevels(), memberNames, (RolapCube)cube);
                            if (oRm.isPresent()) {
                                result.add(oRm.get());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private Optional<RolapMember> getRolapHierarchy(Level[] levels, List<String> memberNames, RolapCube cube) {
        Optional<Member> result = Optional.empty();
        if (levels.length >= memberNames.size()) {
            for (int i = 0; i < memberNames.size(); i++) {
                int index = i;
                for (Level l : levels) {
                	result = cube.getLevelMembers(l, false).stream().filter(m -> m.getName().equals(memberNames.get(index))).findFirst();
                	if (result.isPresent()) {
                		break;
                	}
                }
            }
        }
        if (result.isPresent()) {
            return Optional.of((RolapMember) result.get());
        }
        return Optional.empty();
    }

    private List<String> getMemberNames(String memberUniqueName) {
        String[] ss = memberUniqueName.split("].\\[");
        List<String> res = new ArrayList<>();
        if (ss.length > 1) {
            for (int i = 1; i < ss.length; i++ ) {
                res.add(ss[i].replace("[", "").replace("]", ""));
            }
        }
        return res;
    }

    private String getHierarchyName(String memberUniqueName) {
        String[] ss = memberUniqueName.split("].\\[");
        if (ss.length > 0) {
            return ss[0].replace("[", "");
        }
        return null;
    }

    public List<ScenarioImpl.WritebackCell> getWritebackCellList1(Cube cube) {
        List<ScenarioImpl.WritebackCell> result = new ArrayList<>();
        List<RolapMember> members = new LinkedList<>();
        Optional<Member> om =
            cube.getMeasures().stream().filter(m -> "[Measures].[Measure1]".equals(m.getUniqueName())).findFirst();
        if (om.isPresent()) {
            members.add(((RolapMember) om.get()));
            Dimension[] dimensions = cube.getDimensions();
            if (dimensions != null) {
                for (Dimension dimension : dimensions) {
                    if ("D1".equals(dimension.getName())) {
                        Hierarchy[] hierarchies = dimension.getHierarchies();
                        if (hierarchies != null) {
                            for (Hierarchy h : hierarchies) {
                                if ("[D1.HierarchyWithHasAll]".equals(h.getUniqueName())) {
                                    members.add((RolapMember) h.getAllMember());
                                    Level[] ls = h.getLevels();
                                    if (ls != null) {
                                        for (Level l : ls) {
                                            Optional<Member> ol =
                                                cube.getLevelMembers(l, false).stream().filter(m -> ("[D1" +
                                                    ".HierarchyWithHasAll].[Level11]").equals(m.getUniqueName())).findFirst();
                                            if (ol.isPresent()) {
                                                members.add((RolapMember) ol.get());
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }

        final RolapStoredMeasure measure = (RolapStoredMeasure) members.get(0);
        final RolapStar.Measure starMeasure =
            (RolapStar.Measure) measure.getStarMeasure();
        assert starMeasure != null;
        int starColumnCount = starMeasure.getStar().getColumnCount();
        final BitKey constrainedColumnsBitKey =
            BitKey.Factory.makeBitKey(starColumnCount);
        Object[] keyValues = new Object[starColumnCount];
        double newValue = 208;
        double currentValue = 104;
        AllocationPolicy allocationPolicy = AllocationPolicy.EQUAL_ALLOCATION;

        ScenarioImpl.WritebackCell writeBackCell = new ScenarioImpl.WritebackCell(
            (RolapCube) cube,
            members,
            constrainedColumnsBitKey,
            keyValues,
            newValue,
            currentValue,
            allocationPolicy
        );
        result.add(writeBackCell);
        return result;
    }

    private List<WriteBackData> getWriteBackData(Cube cube) {
        List<WriteBackData> result = new ArrayList<>();
        Connection con = ((RolapCube) cube).getContext().getConnection();
        DataSource dataSource = con.getDataSource();
        try (final java.sql.Connection connection = dataSource.getConnection(); final Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from write_back_data where cube_name='" + cube.getName() + "'");
            while(resultSet.next()){
                double currentValue = resultSet.getDouble("currentValue");
                double newValue = resultSet.getDouble("newValue");
                String memberUniqueNamesString =  resultSet.getString("memberUniqueNames");
                String allocationPolicy =  resultSet.getString("allocationPolicy");
                List<String> memberUniqueNames = Arrays.stream(memberUniqueNamesString.split(",")).collect(Collectors.toList());
                WriteBackData writeBackData = new WriteBackData();
                writeBackData.setCurrentValue(currentValue);
                writeBackData.setNewValue(newValue);
                writeBackData.setAllocationPolicy(AllocationPolicy.valueOf(allocationPolicy));
                writeBackData.setMemberUniqueNames(memberUniqueNames);
                result.add(writeBackData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        List<String> memberUniqueNames = List.of(
            "[Measures].[Count]",
            "[Time.Time1].[1997]",
            "[Store].[USA].[CA].[Alameda].[HQ]",
            "[Pay Type].[All Pay Types]",
            "[Store Type].[All Store Types]",
            "[Position].[All Position]",
            "[Department].[All Departments]",
            "[Employees].[All Employees]",
            "[Employees$Closure].[All Employees$Closure]"
        );
        WriteBackData writeBackData = new WriteBackData();
        writeBackData.setCurrentValue(7392);
        writeBackData.setNewValue(15000);
        writeBackData.setAllocationPolicy(AllocationPolicy.EQUAL_ALLOCATION);
        writeBackData.setMemberUniqueNames(memberUniqueNames);
        result.add(writeBackData);
         */
        return result;
    }
}
