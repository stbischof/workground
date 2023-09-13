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
package org.eclipse.daanse.olap.api.query.component;

import mondrian.olap.Exp;
import mondrian.olap.FunctionDefinition;
import mondrian.olap.Parameter;
import mondrian.olap.SchemaReader;
import mondrian.olap.Validator;
import mondrian.rolap.RolapCube;
import mondrian.server.Statement;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.calc.api.Calc;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public non-sealed interface Query extends QueryPart {

    SchemaReader getSchemaReader(boolean b);

    Cube getCube();

    void setResultStyle(ResultStyle list);

    QueryAxis[] getAxes();

    Calc compileExpression(Exp exp, boolean scalar, ResultStyle resultStyle);

    Map<Hierarchy, Calc> getSubcubeHierarchyCalcs();

    void replaceSubcubeMembers();

    void resolve();

    void clearEvalCache();

    QueryAxis getSlicerAxis();

    QueryPart[] getCellProperties();

    Set<Member> getMeasuresMembers();

    Calc getSlicerCalc();

    Calc[] getAxisCalcs();

    void setSubcubeHierarchies(HashMap<Hierarchy, HashMap<Member, Member>> subcubeHierarchies);

    void putEvalCache(String key, Object value);

    Object getEvalCache(String key);

    Formula[] getFormulas();

    Statement getStatement();

    Connection getConnection();

    void addFormulas(Formula[] toArray);

    Formula findFormula(String toString);

    Validator createValidator();

    Collection<RolapCube> getBaseCubes();

    void addMeasuresMembers(OlapElement olapElement);

    void setBaseCubes(List<RolapCube> baseCubeList);

    boolean nativeCrossJoinVirtualCube();

    boolean shouldAlertForNonNative(FunctionDefinition fun);

    ExpressionCompiler createExpressionCompiler();

    boolean hasCellProperty(String name);

    Parameter[] getParameters();

    ResultStyle getResultStyle();

    boolean ignoreInvalidMembers();

    boolean isCellPropertyEmpty();

    void setVirtualCubeNonNativeCrossJoin();
}
