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
package org.eclipse.daanse.olap.impl;

import mondrian.calc.impl.GenericCalc;
import mondrian.olap.Util;
import mondrian.olap.type.ScalarType;
import mondrian.rolap.RolapEvaluator;
import org.eclipse.daanse.olap.api.Evaluator;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.eclipse.daanse.olap.api.element.Member;

import java.util.List;

public class ScenarioCalc extends GenericCalc {
    private final Scenario scenario;
    private final Object o;
    List<Member> ms;

    /**
     * Creates a ScenarioCalc.
     *
     * @param scenario Scenario whose writeback values should be substituted
     * for the values stored in the database.
     */
    public ScenarioCalc(Scenario scenario, Object o, List<Member> ms) {
        super(ScalarType.INSTANCE);
        this.scenario = scenario;
        this.o = o;
        this.ms = ms;
    }

    /**
     * Returns the Scenario this writeback cell belongs to.
     *
     * @return Scenario, never null
     */
    private Scenario getScenario() {
        return scenario;
    }

    @Override
    public Object evaluate(Evaluator evaluator) {
        // Evaluate current member in the given scenario by expanding in
        // terms of the writeback cells.

        // First, evaluate in the null scenario.
        final int savepoint = evaluator.savepoint();
        try {
            //final Object o = evaluator.evaluateCurrent();
            evaluator.setContext(ms.toArray(Member[]::new));
            double d =
                o instanceof Number
                    ? ((Number) o).doubleValue()
                    : 0d;

            // Look for writeback cells which are equal to, ancestors of,
            // or descendants of, the current cell. Modify the value
            // accordingly.
            //
            // It is possible that the value is modified by several
            // writebacks. If so, order is important.
            int changeCount = 0;
            for (ScenarioImpl.WritebackCell writebackCell
                : scenario.getWritebackCellMap().values())
            {
                ScenarioImpl.CellRelation relation =
                    writebackCell.getRelationTo(evaluator.getMembers());
                switch (relation) {
                    case ABOVE:
                        // This cell is below the writeback cell. Value is
                        // determined by allocation policy.
                        double atomicCellCount =
                            evaluateAtomicCellCount((RolapEvaluator) evaluator);
                        if (atomicCellCount == 0d) {
                            // Sometimes the value comes back zero if the cache
                            // is not ready. Switch to 1, which at least does
                            // not give divide-by-zero. We will be invoked again
                            // for the correct answer when the cache has been
                            // populated.
                            atomicCellCount = 1d;
                        }
                        switch (writebackCell.getAllocationPolicy()) {
                            case EQUAL_ALLOCATION:
                                d = writebackCell.getNewValue()
                                    * atomicCellCount
                                    / writebackCell.getAtomicCellCount();
                                break;
                            case EQUAL_INCREMENT:
                                d += writebackCell.getOffset()
                                    * atomicCellCount
                                    / writebackCell.getAtomicCellCount();
                                break;
                            default:
                                throw Util.unexpected(
                                    writebackCell.getAllocationPolicy());
                        }
                        ++changeCount;
                        break;
                    case EQUAL:
                        // This cell is the writeback cell. Value is the value
                        // written back.
                        d = writebackCell.getNewValue();
                        ++changeCount;
                        break;
                    case BELOW:
                        // This cell is above the writeback cell. Value is the
                        // current value plus the change in the writeback cell.
                        d += writebackCell.getOffset();
                        ++changeCount;
                        break;
                    case NONE:
                        // Writeback cell is unrelated. It has no effect on
                        // cell's value.
                        break;
                    default:
                        throw Util.unexpected(relation);
                }
            }
            // Don't create a new object if value has not changed.
            if (changeCount == 0) {
                return o;
            } else {
                return d;
            }
        } finally {
            evaluator.restore(savepoint);
        }
    }

    private static double evaluateAtomicCellCount(RolapEvaluator evaluator) {
        final int savepoint = evaluator.savepoint();
        try {
            evaluator.setContext(
                evaluator.getCube().getAtomicCellCountMeasure());
            final Object o = evaluator.evaluateCurrent();
            return ((Number) o).doubleValue();
        } finally {
            evaluator.restore(savepoint);
        }
    }

}
