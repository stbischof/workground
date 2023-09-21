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
package org.eclipse.daanse.olap.xmla.bridge.discover;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.xmla.bridge.ContextListSupplyer;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;

import static org.eclipse.daanse.olap.xmla.bridge.discover.Utils.getMdSchemaCubesResponseRow;

public class MDSchemaDiscoverService {
	private ContextListSupplyer contextsListSupplyer;

	public MDSchemaDiscoverService(ContextListSupplyer contextsListSupplyer) {
		this.contextsListSupplyer = contextsListSupplyer;
	}

	public List<MdSchemaActionsResponseRow> mdSchemaActions(MdSchemaActionsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaCubesResponseRow> mdSchemaCubes(MdSchemaCubesRequest request) {
        String catalogName = request.restrictions().catalogName();
        Optional<String> cubeName = request.restrictions().cubeName();
        Optional<String> schemaName = request.restrictions().schemaName();
        Optional<String> baseCubeName = request.restrictions().baseCubeName();
        Optional<CubeSourceEnum> cubeSource = request.restrictions().cubeSource();

        Optional<Context> oContext = contextsListSupplyer.tryGetFirstByName(catalogName);
        if (oContext.isPresent()) {
            Context context = oContext.get();
            return getMdSchemaCubesResponseRow(context, schemaName, cubeName, baseCubeName, cubeSource);
        }
		return List.of();
	}

    public List<MdSchemaDimensionsResponseRow> mdSchemaDimensions(MdSchemaDimensionsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaFunctionsResponseRow> mdSchemaFunctions(MdSchemaFunctionsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaHierarchiesResponseRow> mdSchemaHierarchies(MdSchemaHierarchiesRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaKpisResponseRow> mdSchemaKpis(MdSchemaKpisRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaLevelsResponseRow> mdSchemaLevels(MdSchemaLevelsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaMeasureGroupDimensionsResponseRow> mdSchemaMeasureGroupDimensions(
			MdSchemaMeasureGroupDimensionsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaMeasureGroupsResponseRow> mdSchemaMeasureGroups(MdSchemaMeasureGroupsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaMeasuresResponseRow> mdSchemaMeasures(MdSchemaMeasuresRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaMembersResponseRow> mdSchemaMembers(MdSchemaMembersRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaPropertiesResponseRow> mdSchemaProperties(MdSchemaPropertiesRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MdSchemaSetsResponseRow> mdSchemaSets(MdSchemaSetsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
