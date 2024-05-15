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

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.impl.ScenarioImpl;

import java.util.List;

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
}
