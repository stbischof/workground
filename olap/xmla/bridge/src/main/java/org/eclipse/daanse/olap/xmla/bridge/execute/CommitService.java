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
import mondrian.rolap.RolapCube;
import mondrian.rolap.RolapMember;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Dimension;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Level;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.result.AllocationPolicy;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.impl.ScenarioImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CommitService {

    public void commit(Scenario scenario) {
        //TODO
        List<ScenarioImpl.WritebackCell> wbcs = scenario.getWritebackCells();
        for (ScenarioImpl.WritebackCell wbc : wbcs) {
            System.out.println(wbc.getCurrentValue() + " " + wbc.getNewValue() + " " + wbc.getOffset());
            Member[] members = wbc.getMembersByOrdinal();
            if (members != null) {
                for (Member member : members) {
                    System.out.println(member.getName());
                }
            }

        }
    }

    public List<ScenarioImpl.WritebackCell> getWritebackCellList(Cube cube) {
        List<ScenarioImpl.WritebackCell> result = new ArrayList<>();
        List<RolapMember> members = new LinkedList<>();
        Optional<Member> om = cube.getMeasures().stream().filter(m -> "[Measures].[Measure1]".equals(m.getUniqueName())).findFirst();
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
                                        	Optional<Member> ol = cube.getLevelMembers(l, false).stream().filter(m -> "[D1.HierarchyWithHasAll].[Level11]".equals(m.getUniqueName())).findFirst();
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

        BitKey constrainedColumnsBitKey = null;
        Object[] keyValues = null;
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
}
