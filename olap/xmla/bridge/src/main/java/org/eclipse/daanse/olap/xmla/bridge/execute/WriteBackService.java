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

import mondrian.olap.QueryImpl;
import mondrian.rolap.BitKey;
import mondrian.rolap.RolapBaseCubeMeasure;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapCubeHierarchy;
import mondrian.rolap.RolapHierarchy;
import mondrian.rolap.RolapMember;
import mondrian.rolap.RolapStar;
import mondrian.rolap.RolapStoredMeasure;
import mondrian.rolap.RolapWritebackAttribute;
import mondrian.rolap.RolapWritebackColumn;
import mondrian.rolap.RolapWritebackMeasure;
import mondrian.rolap.RolapWritebackTable;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.olap.api.result.AllocationPolicy;
import org.eclipse.daanse.olap.api.result.Result;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.impl.ScenarioImpl;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRelationOrJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.SQLR;
import org.eclipse.daanse.olap.rolap.dbmapper.model.record.ViewR;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WriteBackService {

    public void commit(Scenario scenario, Connection con) {
        DataSource dataSource = con.getDataSource();
        try (final java.sql.Connection connection = dataSource.getConnection(); final Statement statement =
            connection.createStatement()) {
            //TODO add cube as method parameter
            statement.execute("delete from write_back_data");
            List<ScenarioImpl.WritebackCell> wbcs = scenario.getWritebackCells();
            for (ScenarioImpl.WritebackCell wbc : wbcs) {
                Member[] members = wbc.getMembersByOrdinal();
                if (members != null && members.length > 0 && members[0] instanceof RolapBaseCubeMeasure rolapBaseCubeMeasure) {
                    Cube cube = rolapBaseCubeMeasure.getCube();
                    String membersString = getMembersString(members);
                    statement.executeUpdate("INSERT INTO write_back_data(currentValue, newValue, allocationPolicy, " +
                        "CUBE_NAME, memberUniqueNames) values("
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

    private List<RolapMember> getMembers(List<String> memberUniqueNames, Cube cube) {
        List<RolapMember> result = new ArrayList<>();
        if (!memberUniqueNames.isEmpty()) {
            Optional<Member> oMeasure =
                cube.getMeasures().stream().filter(m -> memberUniqueNames.get(0).equals(m.getUniqueName())).findFirst();
            if (oMeasure.isPresent()) {
                result.add((RolapMember) oMeasure.get());
            }
            if (memberUniqueNames.size() > 1) {
                List<RolapHierarchy> hierarchies = ((RolapCube) cube).getHierarchies();
                if (hierarchies != null) {
                    for (int i = 1; i < memberUniqueNames.size(); i++) {
                        String memberUniqueName = memberUniqueNames.get(i);
                        String hierarchyName = getHierarchyName(memberUniqueName);
                        List<String> memberNames = getMemberNames(memberUniqueName);
                        Optional<RolapHierarchy> oh =
                            hierarchies.stream().filter(h -> h.getName().equals(hierarchyName)).findFirst();
                        if (oh.isPresent()) {
                            Optional<RolapMember> oRm = getRolapHierarchy(oh.get().getLevels(), memberNames,
                                (RolapCube) cube);
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
        if (levels.length > memberNames.size()) {
            Level level = null;
            for (int i = 0; i < memberNames.size(); i++) {
                int index = i;
                if (i == 0) {
                    for (Level l : levels) {
                        List<Member> members = cube.getLevelMembers(l, false);
                        result = members.stream().filter(m -> m.getName().equals(memberNames.get(index))).findFirst();
                        if (result.isPresent()) {
                            level = l.getChildLevel();
                            break;
                        }
                    }
                } else {
                    if (result.isPresent() && level != null) {
                        Member mem = result.get();
                        List<Member> members = cube.getLevelMembers(level, false);
                        result =
                            members.stream().filter(m -> m.getName().equals(memberNames.get(index)) && m.getUniqueName().startsWith(mem.getUniqueName())).findFirst();
                        level = level.getChildLevel();
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
            for (int i = 1; i < ss.length; i++) {
                res.add(ss[i].replace("[", "").replace("]", ""));
            }
        }
        return res;
    }

    private List<String> getTuples(String memberUniqueName) {
        String[] ss = memberUniqueName.split("].\\[");
        List<String> res = new ArrayList<>();
        if (ss.length > 0) {
            for (int i = 0; i < ss.length; i++) {
                res.add(ss[i].replace("[", "").replace("]", "").replace("(", "").replace(")", ""));
            }
        }
        return res;
    }

    private Optional<String> getMeasure(String measure) {
        String[] ss = measure.split("].\\[");
        if (ss.length > 1) {
            return Optional.of(ss[1].replace("[", "").replace("]", ""));
        }
        return Optional.empty();
    }

    private String getHierarchyName(String memberUniqueName) {
        String[] ss = memberUniqueName.split("].\\[");
        if (ss.length > 0) {
            return ss[0].replace("[", "");
        }
        return null;
    }

    public List<Map<String, Object>> getAllocationValues(
        String tupleString,
        Object value,
        AllocationPolicy equalAllocation,
        String cubeName,
        Connection connection
    ) {
        List<Map<String, Object>> res = new ArrayList<>();
        //[D1.HierarchyWithHasAll].[Level11], [Measures].[Measure1]
        Optional<Cube> oCube = getCube(cubeName, connection);
        if (oCube.isPresent() && oCube.get() instanceof RolapCube rolapCube) {
            Optional<RolapWritebackTable> oWritebackTable = rolapCube.getWritebackTable();
            if (oWritebackTable.isPresent()) {
                RolapWritebackTable writebackTable = oWritebackTable.get();
                String[] str = tupleString.split(",");
                if (str.length > 0) {
                    List<String> tuples = List.of();
                    String measure = null;
                    if (str.length == 1) {
                        //measure only
                        measure = str[0].replace("(", "").replace(")", "").trim();
                    } else {
                        tuples = getTuples(str[0]);
                        measure = str[1].replace("(", "").replace(")", "").trim();
                    }
                    String measureName = measure;
                    Optional<Member> oMember =
                        rolapCube.getMeasures().stream().filter(m -> m.getUniqueName().equals(measureName)).findFirst();
                    if (oMember.isPresent() && oMember.get() instanceof RolapBaseCubeMeasure rolapBaseCubeMeasure) {
                        if (!tuples.isEmpty()) {
                            String hierarchyName = tuples.get(0);
                            Optional<RolapHierarchy> oRolapHierarchy =
                                rolapCube.getHierarchies().stream()
                                    .filter(h -> h.getName().equals(hierarchyName)).findFirst();
                            List<String> ls = new ArrayList<>();
                            if (tuples.size() > 1) {
                                for (int i = 1; i < tuples.size(); i++) {
                                    ls.add(tuples.get(i));
                                }
                            }
                            if (oRolapHierarchy.isPresent()) {
                                System.out.println(oRolapHierarchy.get().getUniqueName());
                                Level[] levels = oRolapHierarchy.get().getLevels();
                                Optional<RolapMember> oRolapMember = getRolapHierarchy(levels, ls, rolapCube);
                                Set<Member> members = getLevelLeafMembers(levels, oRolapMember, rolapCube);
                                Map<Member, Object> data = getData(members, rolapCube);
                                res.addAll(allocateData(data, measureName, (Double) value, equalAllocation, writebackTable));
                            }
                        } else {
                            List<RolapHierarchy> hs = rolapCube.getHierarchies();
                            if (hs != null) {
                                for (RolapHierarchy h : hs) {
                                    if (h instanceof RolapCubeHierarchy rolapCubeHierarchy) {
                                        Level[] levels = rolapCubeHierarchy.getLevels();
                                        if (levels != null && levels.length > 0) {
                                            Set<Member> members = getLevelLeafMembers(levels, Optional.empty(),
                                                rolapCube);
                                            Map<Member, Object> data = getData(members, rolapCube);
                                            res.addAll(allocateData(data, measureName, (Double) value, equalAllocation,
                                                writebackTable));
                                        }
                                    }
                                }
                            } else {
                                // Hierarchies is absent
                                //TODO
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private List<Map<String, Object>> allocateData(
        Map<Member, Object> data,
        String measureName,
        Double value,
        AllocationPolicy allocation,
        RolapWritebackTable writebackTable
    ) {
        Map<Member, Double> d = new HashMap<>();
        switch (allocation) {
            case EQUAL_ALLOCATION:
                int size = data.size();
                double vav = value / size;
                for (Map.Entry<Member, Object> entry : data.entrySet()) {
                    d.put(entry.getKey(), (-1) * (Double) entry.getValue());
                    d.put(entry.getKey(), vav);
                }
                break;
            case WEIGHTED_ALLOCATION:
                break;
            case EQUAL_INCREMENT:
                break;
            case WEIGHTED_INCREMENT:
                break;
            default:

        }
        return allocateData(d, measureName, writebackTable);
    }

    private List<Map<String, Object>> allocateData(
        Map<Member, Double> d,
        String measureName,
        RolapWritebackTable writebackTable
    ) {
        List<Map<String, Object>> res = new ArrayList<>();
        for (Map.Entry<Member, Double> entry : d.entrySet()) {
            Member m = entry.getKey();
            Double value = entry.getValue();
            if (m instanceof RolapMember rolapMember) {
                Map<String, Object> mRes = new HashMap<>();
                Object key = rolapMember.getKey();
                List<RolapWritebackColumn> columns = writebackTable.getColumns();
                for (RolapWritebackColumn column : columns) {
                    if (column instanceof RolapWritebackMeasure rolapWritebackMeasure) {
                        if (rolapWritebackMeasure.getMeasure().getUniqueName().equals(measureName)) {
                            mRes.put(rolapWritebackMeasure.getColumnName(), value);
                        } else {
                            mRes.put(rolapWritebackMeasure.getColumnName(), 0);
                        }
                    }
                    if (column instanceof RolapWritebackAttribute rolapWritebackAttribute) {
                        mRes.put(rolapWritebackAttribute.getColumnName(), key);
                    }
                }
            }
        }
        return res;
    }

    private Map<Member, Object> getData(Set<Member> members, RolapCube cube) {

        //SELECT
        //{
        //    ([D1.HierarchyWithHasAll].[Level11].[Level11]),
        //    ([D1.HierarchyWithHasAll].[Level11].[Level22]),
        //    ([D1.HierarchyWithHasAll].[Level22].[Level11]),
        //    ([D1.HierarchyWithHasAll].[Level22].[Level22]),
        //    ([D1.HierarchyWithHasAll].[Level22].[Level33])
        //} ON 0
        //FROM C

        Map<Member, Object> res = new HashMap<>();
        final StringBuilder buf = new StringBuilder();
        boolean flagFirst = true;
        buf.append("select {");
        for (Member member : members) {
            if (flagFirst) {
                flagFirst = false;
            } else {
                buf.append(",");
            }
            buf.append("(").append(member.getUniqueName()).append(")");
        }
        buf.append("} ON 0 FROM ").append(cube.getName());
        final String mdx = buf.toString();
        final RolapConnection connection =
            cube.getSchema().getInternalConnection();
        final QueryImpl query = connection.parseQuery(mdx);
        final Result result = connection.execute(query);
        int i = 0;
        for (Member m : members) {
            res.put(m, result.getCell(new int[]{i}).getValue());
            i++;
        }
        return res;
    }

    private Set<Member> getLevelLeafMembers(Level[] levels, Optional<RolapMember> oRolapMember, RolapCube rolapCube) {
        Set<Member> result = new HashSet<>();
        if (oRolapMember.isPresent()) {
            Level level = oRolapMember.get().getLevel();
            if (level.getChildLevel() != null) {
                result.addAll(getLevelLeafMembers(level.getChildLevel(), oRolapMember, rolapCube));
            } else {
                List<Member> members = rolapCube.getLevelMembers(level, false);
                if (members != null) {
                    for (Member member : members) {
                        if (member.getUniqueName().startsWith(oRolapMember.get().getUniqueName())) {
                            result.add(member);
                        }
                    }
                }
            }
        } else {
            if (levels != null) {
                for (Level level : levels) {
                    result.addAll(getLevelLeafMembers(level, Optional.empty(), rolapCube));
                }
            }
        }
        return result;
    }

    private Set<Member> getLevelLeafMembers(Level level, Optional<RolapMember> oRolapMember, RolapCube rolapCube) {
        Set<Member> result = new HashSet<>();
        if (level.getChildLevel() != null) {
            result.addAll(getLevelLeafMembers(level.getChildLevel(), oRolapMember, rolapCube));
        } else {
            List<Member> members = rolapCube.getLevelMembers(level, false);
            if (members != null) {
                for (Member member : members) {
                    if (oRolapMember.isPresent()) {
                        if (member.getUniqueName().startsWith(oRolapMember.get().getUniqueName())) {
                            result.add(member);
                        }
                    } else {
                        result.add(member);
                    }
                }
            }
        }
        return result;
    }

    private Optional<Cube> getCube(String cubeName, Connection connection) {
        Schema schema = connection.getSchema();
        Cube[] cubes = schema.getCubes();
        if (cubes != null) {
            for (Cube cube : cubes) {
                if (cube.getName().equals(cubeName)) {
                    return Optional.of(cube);
                }
            }
        }
        return Optional.empty();
    }

    public void addWriteBackFact(Context context) {
        Cube[] cubes = context.getConnection().getSchema().getCubes();
        if (cubes != null) {
            DataSource dataSource = context.getConnection().getDataSource();
            try (final java.sql.Connection connection = dataSource.getConnection(); final Statement statement =
                connection.createStatement()) {
                for (Cube cube : cubes) {
                    if (cube instanceof RolapCube rolapCube) {
                        MappingRelationOrJoin fact = rolapCube.getFact();
                        if (fact instanceof MappingTable table) {
                            StringBuilder sb = new StringBuilder("SELECT * INTO ").append(table.name()).append("WB " +
                                "from ").append(table.name()).append(" Where 1!=1");
                            statement.executeQuery(sb.toString());
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyFact(RolapCube cube) {
        MappingRelation fact = cube.getFact();
        if (fact instanceof MappingTable mappingTable) {
            String alias = mappingTable.alias() != null ? mappingTable.alias() : mappingTable.name();
            cube.getContext().getDialect().getDialectName();
            StringBuilder sql = new StringBuilder("select * from FACT union all select * from FACTWB");
            //TODO add session data
            List<MappingSQL> sqls = List.of(new SQLR(sql.toString(), "generic"), new SQLR(sql.toString(),
                cube.getContext().getDialect().getDialectName()));
            cube.setFact(new ViewR(alias, sqls));
            List<RolapHierarchy> rolapHierarchyList = cube.getHierarchies();
            if (rolapHierarchyList != null) {
                for (RolapHierarchy hierarchy : rolapHierarchyList) {
                    if (fact.equals(hierarchy.getRelation())) {
                        hierarchy.setRelation(cube.getFact());
                    }
                }
            }
            cube.register();
        }
    }
}
