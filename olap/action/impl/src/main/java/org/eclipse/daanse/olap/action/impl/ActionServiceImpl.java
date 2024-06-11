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
package org.eclipse.daanse.olap.action.impl;

import org.eclipse.daanse.olap.action.api.ActionService;
import org.eclipse.daanse.olap.action.api.UrlAction;
import org.eclipse.daanse.olap.action.api.XmlaAcriton;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.api.DrillThroughAction;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.xmla.api.common.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.InvocationEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsResponseRowR;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.eclipse.daanse.olap.action.impl.DrillThroughUtils.getCoordinateElements;
import static org.eclipse.daanse.olap.action.impl.DrillThroughUtils.getDrillThroughQuery;

@Component(service = ActionService.class, scope = ServiceScope.SINGLETON, name="actionService", immediate = true)
public class ActionServiceImpl implements ActionService {

    public static final String REF_NAME_URL_ACTIONS = "urlAction";
    public static final String REF_NAME_DRILL_THROUGH_ACTIONS = "drillThroughAction";

    private List<XmlaAcriton> xmlaActions = new ArrayList<>();

    @Reference(name = REF_NAME_URL_ACTIONS, cardinality = ReferenceCardinality.MULTIPLE, policy =
        ReferencePolicy.DYNAMIC)
    public void bindUrlAction(UrlAction action) {
        xmlaActions.add(action);
    }

    public void unbindUrlAction(UrlAction action) {
        xmlaActions.remove(action);
    }

    @Reference(name = REF_NAME_DRILL_THROUGH_ACTIONS, cardinality = ReferenceCardinality.MULTIPLE, policy =
        ReferencePolicy.DYNAMIC)
    public void bindDrillThroughAction(org.eclipse.daanse.olap.action.api.DrillThroughAction action) {
        xmlaActions.add(action);
    }

    public void unbindDrillThroughAction(org.eclipse.daanse.olap.action.api.DrillThroughAction action) {
        xmlaActions.remove(action);
    }

    @Override
    public List<MdSchemaActionsResponseRow> getResponses(
        List<Context> contexts,
        Optional<String> schemaName,
        String cubeName,
        Optional<String> actionName,
        Optional<ActionTypeEnum> actionType,
        Optional<String> coordinate,
        CoordinateTypeEnum coordinateType,
        InvocationEnum invocation,
        Optional<CubeSourceEnum> cubeSource
    ) {
        List<MdSchemaActionsResponseRow> result = new ArrayList<>();
        result.addAll(contexts.stream().map(c ->
            getMdSchemaActionsResponseRow(c, schemaName, cubeName, actionName, actionType, coordinate, coordinateType
                , invocation, cubeSource)
        ).flatMap(Collection::stream).toList());

        if (CoordinateTypeEnum.CELL.equals(coordinateType)) {
            result.addAll(getMdSchemaUrlActionsResponseRow(coordinate, xmlaActions));
        }
        return result;
    }

    private static List<MdSchemaActionsResponseRow> getMdSchemaUrlActionsResponseRow(
        Optional<String> coordinate,
        List<XmlaAcriton> urlActions
    ) {
        //TODO add filter for schema, catalogs,
        List<MdSchemaActionsResponseRow> result = new ArrayList<>();
        for (XmlaAcriton xmlaAcriton : urlActions) {
            result.add(new MdSchemaActionsResponseRowR(
                xmlaAcriton.catalogName(),
                xmlaAcriton.schemaName(),
                xmlaAcriton.cubeName(),
                xmlaAcriton.actionName(),
                Optional.of(xmlaAcriton.actionType()),
                coordinate.orElse(null),
                xmlaAcriton.coordinateType(),
                xmlaAcriton.actionCaption(),
                xmlaAcriton.description(),
                Optional.ofNullable((String) xmlaAcriton.content(coordinate.orElse(null), xmlaAcriton.cubeName())),
                Optional.empty(),
                Optional.ofNullable(InvocationEnum.NORMAL_OPERATION)
            ));
        }
        return result;
    }

    private List<MdSchemaActionsResponseRow> getMdSchemaActionsResponseRow(
        Context context,
        Optional<String> oSchemaName,
        String cubeName,
        Optional<String> oActionName,
        Optional<ActionTypeEnum> oActionType,
        Optional<String> oCoordinate,
        CoordinateTypeEnum coordinateType,
        InvocationEnum invocation,
        Optional<CubeSourceEnum> oCubeSource
    ) {
        List<Schema> schemas = context.getConnection().getSchemas();
        if (schemas != null) {
            return getSchemasWithFilter(schemas, oSchemaName).stream()
                .map(schema -> getMdSchemaActionsResponseRow(context.getName(), schema, cubeName, oActionName,
                    oActionType, oCoordinate, coordinateType, invocation, oCubeSource)
                ).flatMap(Collection::stream).toList();
        }
        return List.of();
    }

    private List<MdSchemaActionsResponseRow> getMdSchemaActionsResponseRow(
        String catalogName,
        Schema schema,
        String cubeName,
        Optional<String> oActionName,
        Optional<ActionTypeEnum> oActionType,
        Optional<String> oCoordinate,
        CoordinateTypeEnum coordinateType,
        InvocationEnum invocation,
        Optional<CubeSourceEnum> oCubeSource
    ) {
        List<MdSchemaActionsResponseRow> result = new ArrayList<>();
        List<Cube> cubes = schema.getCubes() == null ? List.of() : Arrays.asList(schema.getCubes());
        result.addAll(getCubesWithFilter(cubes, cubeName).stream()
            .map(c -> getMdSchemaActionsResponseRow(catalogName, schema.getName(), c, oActionName, oActionType,
                oCoordinate, coordinateType, invocation, oCubeSource))
            .flatMap(Collection::stream)
            .toList());

        return result;
    }

    private List<MdSchemaActionsResponseRow> getMdSchemaActionsResponseRow(
        String catalogName,
        String schemaName,
        Cube cube,
        Optional<String> oActionName,
        Optional<ActionTypeEnum> oActionType,
        Optional<String> oCoordinate,
        CoordinateTypeEnum coordinateType,
        InvocationEnum invocation,
        Optional<CubeSourceEnum> oCubeSource
    ) {
        List<MdSchemaActionsResponseRow> result = new ArrayList<>();
        if (cube.getDrillThroughActions() != null && coordinateType.equals(CoordinateTypeEnum.CELL)) {
            result.addAll(getMappingDrillThroughActionWithFilter(cube.getDrillThroughActions(), oActionName).stream()
                .map(da -> getMdSchemaDrillThroughActionsResponseRow(catalogName, schemaName, cube, da, oCoordinate))
                .flatMap(Collection::stream)
                .toList());

        }
        return result;
    }

    private static List<MdSchemaActionsResponseRow> getMdSchemaDrillThroughActionsResponseRow(
        String catalogName, String schemaName,
        Cube cube, DrillThroughAction da, Optional<String> oCoordinate
    ) {
        List<MdSchemaActionsResponseRow> result = new ArrayList<>();
        if (oCoordinate != null && oCoordinate.isPresent()) {
            List<String> coordinateElements = getCoordinateElements(oCoordinate.get());
            //if (DrillThroughUtils.isDrillThroughElementsExist(da.getOlapElements(), coordinateElements, cube)) {
            String query = getDrillThroughQuery(coordinateElements, da.getOlapElements(), cube);
            String coordinate = oCoordinate.get();

            result.add(new MdSchemaActionsResponseRowR(
                Optional.ofNullable(catalogName),
                Optional.ofNullable(schemaName),
                cube.getName(),
                Optional.ofNullable(da.getName()),
                Optional.of(ActionTypeEnum.DRILL_THROUGH),
                coordinate,
                CoordinateTypeEnum.CELL,
                Optional.ofNullable(da.getCaption()),
                Optional.ofNullable(da.getDescription()),
                Optional.of(query),
                Optional.empty(),
                Optional.ofNullable(InvocationEnum.NORMAL_OPERATION)
            ));
            //}
        }
        return result;
    }

    private List<DrillThroughAction> getMappingDrillThroughActionWithFilter(
        List<DrillThroughAction> actions,
        Optional<String> oActionName
    ) {
        if (oActionName.isPresent()) {
            return actions.stream().filter(a -> oActionName.get().equals(a.getName())).toList();
        }
        return actions;
    }

    private List<Schema> getSchemasWithFilter(List<Schema> schemas, Optional<String> oSchemaName) {
        if (oSchemaName.isPresent()) {
            return schemas.stream().filter(s -> oSchemaName.get().equals(s.getName())).toList();
        }
        return schemas;
    }

    private static List<Cube> getCubesWithFilter(List<Cube> cubes, String cubeName) {
        if (cubeName != null) {
            return cubes.stream().filter(c -> cubeName.equals(c.getName())).toList();
        }
        return cubes;
    }
}
