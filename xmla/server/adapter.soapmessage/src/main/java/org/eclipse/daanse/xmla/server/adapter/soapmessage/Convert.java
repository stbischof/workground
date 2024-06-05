/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.*;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.xml.soap.SOAPHeader;
import org.eclipse.daanse.xmla.api.common.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.AuthenticationModeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.InvocationEnum;
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.MemberTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ObjectExpansionEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ProviderTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TreeOpEnum;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.common.properties.PropertyListElementDefinition;
import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.engine300.AttributeHierarchyProcessingState;
import org.eclipse.daanse.xmla.api.engine300.CalculationPropertiesVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.DimensionAttributeVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.HierarchyVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.RelationshipEndVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300_300.Relationship;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEnd;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEndTranslation;
import org.eclipse.daanse.xmla.api.engine300_300.Relationships;
import org.eclipse.daanse.xmla.api.engine300_300.XEvent;
import org.eclipse.daanse.xmla.api.xmla.*;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.engine.ImpersonationInfoR;
import org.eclipse.daanse.xmla.model.record.engine200_200.ExpressionBindingR;
import org.eclipse.daanse.xmla.model.record.engine200_200.RowNumberBindingR;
import org.eclipse.daanse.xmla.model.record.engine300.CalculationPropertiesVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300.DimensionAttributeVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300.HierarchyVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300.RelationshipEndVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipEndR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipEndTranslationR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipsR;
import org.eclipse.daanse.xmla.model.record.engine300_300.XEventR;
import org.eclipse.daanse.xmla.model.record.xmla.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPElement;

public class Convert {

	private Convert() {
        // constructor
    }

    public static DiscoverPropertiesRestrictionsR discoverPropertiesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverPropertiesRestrictionsR(
            Optional.ofNullable(m.get(PROPERTY_NAME))
        );
    }

    public static PropertiesR propertiestoProperties(SOAPElement propertiesElement) {

        Iterator<Node> nodeIterator = propertiesElement.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement propertyList
                && Constants.MSXMLA.QN_PROPERTY_LIST.equals(propertyList.getElementQName())) {
                return propertyListToProperties(propertyList);
            }

        }
        return new PropertiesR();

    }

    private static PropertiesR propertyListToProperties(SOAPElement propertyList) {
        PropertiesR properties = new PropertiesR();

        Iterator<Node> nodeIteratorPropertyList = propertyList.getChildElements();
        while (nodeIteratorPropertyList.hasNext()) {
            Node n = nodeIteratorPropertyList.next();

            if (n instanceof SOAPElement propertyListElement) {
                String name = propertyListElement.getLocalName();
                Optional<PropertyListElementDefinition> opd = PropertyListElementDefinition.byNameValue(name);
                if (opd.isPresent()) {
                    opd.ifPresent(pd -> properties.addProperty(pd, propertyListElement.getTextContent()));
                } else {
                    properties.setByname(name, propertyListElement.getTextContent());
                }
            }
        }
        return properties;
    }

    public static MdSchemaFunctionsRestrictionsR discoverMdSchemaFunctionsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaFunctionsRestrictionsR(
            Optional.ofNullable(m.get(FUNCTION_NAME)),
            Optional.ofNullable(OriginEnum.fromValue(m.get(ORIGIN))),
            Optional.ofNullable(InterfaceNameEnum.fromValue(m.get(INTERFACE_NAME))),
            Optional.ofNullable(m.get(LIBRARY_NAME))
        );
    }


    public static MdSchemaDimensionsRestrictionsR discoverMdSchemaDimensionsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaDimensionsRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(DIMENSION_NAME)),
            Optional.ofNullable(m.get(DIMENSION_UNIQUE_NAME)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(VisibilityEnum.fromValue(m.get(DIMENSION_VISIBILITY)))
        );
    }

    public static MdSchemaCubesRestrictionsR discoverMdSchemaCubesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaCubesRestrictionsR(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(CubeTypeEnum.fromValue(m.get(CUBE_TYPE))),
            Optional.ofNullable(m.get(BASE_CUBE_NAME)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))));
    }

    public static MdSchemaMeasureGroupsRestrictionsR discoverMdSchemaMeasureGroups(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaMeasureGroupsRestrictionsR(Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(MEASUREGROUP_NAME))
        );
    }

    public static MdSchemaKpisRestrictionsR discoverMdSchemaKpisRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaKpisRestrictionsR(Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(KPI_NAME)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE)))
        );
    }

    public static MdSchemaSetsRestrictionsR discoverMdSchemaSetsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaSetsRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(SET_NAME)),
            Optional.ofNullable(ScopeEnum.fromValue(m.get(SCOPE))),
            Optional.ofNullable(m.get(SET_CAPTION)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(m.get(HIERARCHY_UNIQUE_NAME))
        );
    }

    public static MdSchemaPropertiesRestrictionsR discoverMdSchemaPropertiesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaPropertiesRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(DIMENSION_UNIQUE_NAME)),
            Optional.ofNullable(m.get(HIERARCHY_UNIQUE_NAME)),
            Optional.ofNullable(m.get(LEVEL_UNIQUE_NAME)),
            Optional.ofNullable(m.get(MEMBER_UNIQUE_NAME)),
            Optional.ofNullable(m.get(PROPERTY_NAME2)),
            Optional.ofNullable(PropertyTypeEnum.fromValue(m.get(PROPERTY_TYPE))),
            Optional.ofNullable(PropertyContentTypeEnum.fromValue(m.get(PROPERTY_CONTENT_TYPE))),
            Optional.ofNullable(PropertyOriginEnum.fromValue(m.get(PROPERTY_ORIGIN))),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(VisibilityEnum.fromValue(m.get(PROPERTY_VISIBILITY)))
        );
    }

    public static MdSchemaMembersRestrictionsR discoverMdSchemaMembersRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaMembersRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(DIMENSION_UNIQUE_NAME)),
            Optional.ofNullable(m.get(HIERARCHY_UNIQUE_NAME)),
            Optional.ofNullable(m.get(LEVEL_UNIQUE_NAME)),
            Optional.ofNullable(toInteger(m.get(LEVEL_NUMBER))),
            Optional.ofNullable(m.get(MEMBER_NAME)),
            Optional.ofNullable(m.get(MEMBER_UNIQUE_NAME)),
            Optional.ofNullable(MemberTypeEnum.fromValue(m.get(MEMBER_TYPE))),
            Optional.ofNullable(m.get(MEMBER_CAPTION)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(TreeOpEnum.fromValue(m.get(TREE_OP)))
        );
    }

    public static MdSchemaMeasuresRestrictionsR discoverMdSchemaMeasuresRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaMeasuresRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(MEASURE_NAME)),
            Optional.ofNullable(m.get(MEASURE_UNIQUE_NAME)),
            Optional.ofNullable(m.get(MEASUREGROUP_NAME)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(VisibilityEnum.fromValue(m.get(MEASURE_VISIBILITY)))
        );
    }

    public static MdSchemaMeasureGroupDimensionsRestrictionsR discoverMdSchemaMeasureGroupDimensionsRestrictions(
        SOAPElement restriction
    ) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaMeasureGroupDimensionsRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(DIMENSION_UNIQUE_NAME)),
            Optional.ofNullable(m.get(MEASUREGROUP_NAME)),
            Optional.ofNullable(VisibilityEnum.fromValue(m.get(DIMENSION_VISIBILITY)))
        );
    }

    public static MdSchemaLevelsRestrictionsR discoverMdSchemaLevelsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaLevelsRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(DIMENSION_UNIQUE_NAME)),
            Optional.ofNullable(m.get(HIERARCHY_UNIQUE_NAME)),
            Optional.ofNullable(m.get(LEVEL_NAME)),
            Optional.ofNullable(m.get(LEVEL_UNIQUE_NAME)),
            Optional.empty(),
            Optional.empty(),
            // how to handle cubesource?
//            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(VisibilityEnum.fromValue(m.get(DIMENSION_VISIBILITY)))
        );
    }

    public static MdSchemaHierarchiesRestrictionsR discoverMdSchemaHierarchiesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaHierarchiesRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME)),
            Optional.ofNullable(m.get(DIMENSION_UNIQUE_NAME)),
            Optional.ofNullable(m.get(HIERARCHY_NAME)),
            Optional.ofNullable(m.get(HIERARCHY_UNIQUE_NAME)),
            Optional.ofNullable(toInteger(m.get(HIERARCHY_ORIGIN))),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
            Optional.ofNullable(VisibilityEnum.fromValue(m.get(HIERARCHY_VISIBILITY)))
        );
    }


    public static DbSchemaTablesInfoRestrictionsR discoverDbSchemaTablesInfo(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaTablesInfoRestrictionsR(
            Optional.ofNullable(m.get(TABLE_CATALOG)),
            Optional.ofNullable(m.get(TABLE_SCHEMA)),
            m.get(TABLE_NAME),
            TableTypeEnum.fromValue(m.get(TABLE_TYPE))
        );
    }

    public static DbSchemaSourceTablesRestrictionsR discoverDbSchemaSourceTablesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaSourceTablesRestrictionsR(
            Optional.ofNullable(m.get(TABLE_CATALOG)),
            Optional.ofNullable(m.get(TABLE_SCHEMA)),
            m.get(TABLE_NAME),
            TableTypeEnum.fromValue(m.get(TABLE_TYPE))
        );
    }

    public static DbSchemaSchemataRestrictionsR discoverDbSchemaSchemataRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaSchemataRestrictionsR(
            m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME),
            m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME),
            m.get(SCHEMA_OWNER)
        );
    }

    public static DbSchemaProviderTypesRestrictionsR discoverDbSchemaProviderTypesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaProviderTypesRestrictionsR(
            Optional.ofNullable(LevelDbTypeEnum.fromValue(m.get(DATA_TYPE))),
            Optional.ofNullable(Boolean.valueOf(m.get(BEST_MATCH)))
        );
    }

    public static DbSchemaColumnsRestrictionsR discoverDbSchemaColumnsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaColumnsRestrictionsR(
            Optional.ofNullable(m.get(TABLE_CATALOG)),
            Optional.ofNullable(m.get(TABLE_SCHEMA)),
            Optional.ofNullable(m.get(TABLE_NAME)),
            Optional.ofNullable(m.get(COLUMN_NAME)),
            Optional.ofNullable(ColumnOlapTypeEnum.fromValue(m.get(COLUMN_OLAP_TYPE)))
        );
    }

    public static DiscoverXmlMetaDataRestrictionsR discoverDiscoverXmlMetaDataRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverXmlMetaDataRestrictionsR(
            Optional.ofNullable(m.get(OBJECT_TYPE)),
            Optional.ofNullable(m.get(DATABASE_ID)),
            Optional.ofNullable(m.get(DIMENSION_ID)),
            Optional.ofNullable(m.get(CUBE_ID)),
            Optional.ofNullable(m.get(MEASURE_GROUP_ID)),
            Optional.ofNullable(m.get(PARTITION_ID)),
            Optional.ofNullable(m.get(PERSPECTIVE_ID)),
            Optional.ofNullable(m.get(DIMENSION_PERMISSION_ID)),
            Optional.ofNullable(m.get(ROLE_ID)),
            Optional.ofNullable(m.get(DATABASE_PERMISSION_ID)),
            Optional.ofNullable(m.get(MINING_MODEL_ID)),
            Optional.ofNullable(m.get(MINING_MODEL_PERMISSION_ID)),
            Optional.ofNullable(m.get(DATA_SOURCE_ID)),
            Optional.ofNullable(m.get(MINING_STRUCTURE_ID)),
            Optional.ofNullable(m.get(AGGREGATION_DESIGN_ID)),
            Optional.ofNullable(m.get(TRACE_ID)),
            Optional.ofNullable(m.get(MINING_STRUCTURE_PERMISSION_ID)),
            Optional.ofNullable(m.get(CUBE_PERMISSION_ID)),
            Optional.ofNullable(m.get(ASSEMBLY_ID)),
            Optional.ofNullable(m.get(MDX_SCRIPT_ID)),
            Optional.ofNullable(m.get(DATA_SOURCE_VIEW_ID)),
            Optional.ofNullable(m.get(DATA_SOURCE_PERMISSION_ID)),
            Optional.ofNullable(ObjectExpansionEnum.fromValue(m.get(OBJECT_EXPANSION)))
        );
    }

    public static DiscoverDataSourcesRestrictionsR discoverDiscoverDataSourcesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverDataSourcesRestrictionsR(
            m.get(DATA_SOURCE_NAME),
            Optional.ofNullable(m.get(DATA_SOURCE_DESCRIPTION)),
            Optional.ofNullable(m.get(URL)),
            Optional.ofNullable(m.get(DATA_SOURCE_INFO)),
            m.get(PROVIDER_NAME),
            Optional.ofNullable(ProviderTypeEnum.fromValue(m.get(PROVIDER_TYPE))),
            Optional.ofNullable(AuthenticationModeEnum.fromValue(m.get(AUTHENTICATION_MODE)))
        );
    }

    public static DbSchemaCatalogsRestrictionsR discoverDbSchemaCatalogsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaCatalogsRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME))
        );
    }

    public static DiscoverSchemaRowsetsRestrictionsR discoverSchemaRowsetsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverSchemaRowsetsRestrictionsR(
            Optional.ofNullable(m.get(SCHEMA_NAME_LOW))
        );
    }

    public static DiscoverEnumeratorsRestrictionsR discoverDiscoverEnumerators(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverEnumeratorsRestrictionsR(
            Optional.ofNullable(m.get(ENUM_NAME))
        );
    }

    public static DiscoverKeywordsRestrictionsR discoverKeywordsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverKeywordsRestrictionsR(
            Optional.ofNullable(m.get(KEYWORD))
        );
    }

    public static DiscoverLiteralsRestrictionsR discoverLiteralsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DiscoverLiteralsRestrictionsR(
            Optional.ofNullable(m.get(LITERAL_NAME))
        );
    }

    public static DbSchemaTablesRestrictionsR discoverDbSchemaTablesRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new DbSchemaTablesRestrictionsR(
            Optional.ofNullable(m.get(TABLE_CATALOG)),
            Optional.ofNullable(m.get(TABLE_SCHEMA)),
            Optional.ofNullable(m.get(TABLE_NAME)),
            Optional.ofNullable(m.get(TABLE_TYPE))
        );
    }


    public static MdSchemaActionsRestrictionsR discoverMdSchemaActionsRestrictions(SOAPElement restriction) {
        Map<String, String> m = getMapValuesByTag(restriction, RESTRICTION_LIST);
        return new MdSchemaActionsRestrictionsR(
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.CATALOG_NAME)),
            Optional.ofNullable(m.get(Constants.ROWSET.ROW_PROPERTY.SCHEMA_NAME)),
            m.get(Constants.ROWSET.ROW_PROPERTY.CUBE_NAME),
            Optional.ofNullable(m.get(ACTION_NAME)),
            //Optional.ofNullable(ActionTypeEnum.fromValue(m.get(ACTION_TYPE))), //wrong value from excel
            Optional.empty(),
            Optional.ofNullable(m.get(COORDINATE)),
            CoordinateTypeEnum.fromValue(m.get(COORDINATE_TYPE)),
            m.get(INVOCATION)==null ? null : InvocationEnum.fromValue(m.get(INVOCATION)),
            Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE)))
        );
    }

    public static Command commandtoCommand(SOAPElement element) {
        Iterator<Node> nodeIterator = element.getChildElements();
        while (nodeIterator.hasNext()) {
            Node n= nodeIterator.next();
            if (n instanceof SOAPElement) {
                Command command  = getCommand(n);
                if (command != null) {
                    return command;
                }
            }
        }
        return null;
    }

    public static Optional<Session> getSession(SOAPElement soapHeader) {
        NodeList nl = soapHeader.getElementsByTagName("Session");
        if (nl != null && nl.getLength() > 0) {
            NamedNodeMap nnm = nl.item(0).getAttributes();
            org.w3c.dom.Node n = nnm.getNamedItem("SessionId");
            org.w3c.dom.Node n1 = nnm.getNamedItem("mustUnderstand");
            String sId = null;
            Integer mustUnderstand = null;
            if (n != null) {
                sId = n.getNodeValue();
            }
            if (n1 != null) {
                mustUnderstand = toInteger(n1.getNodeValue());
            }
            return Optional.of(new SessionR(sId, mustUnderstand));
        }
        return Optional.empty();
    }

    public static Optional<EndSession> getEndSession(SOAPElement soapHeader) {
        NodeList nl = soapHeader.getElementsByTagName("EndSession");
        if (nl != null && nl.getLength() > 0) {
            NamedNodeMap nnm = nl.item(0).getAttributes();
            org.w3c.dom.Node n = nnm.getNamedItem("SessionId");
            org.w3c.dom.Node n1 = nnm.getNamedItem("mustUnderstand");
            String sId = null;
            Integer mustUnderstand = null;
            if (n != null) {
                sId = n.getNodeValue();
            }
            if (n1 != null) {
                mustUnderstand = toInteger(n1.getNodeValue());
            }
            return Optional.of(new EndSessionR(sId, mustUnderstand));
        }
        return Optional.empty();
    }

    public static Optional<BeginSession> getBeginSession(SOAPElement soapHeader) {
        NodeList nl = soapHeader.getElementsByTagName("BeginSession");
        if (nl != null && nl.getLength() > 0) {
            NamedNodeMap nnm = nl.item(0).getAttributes();
            org.w3c.dom.Node n = nnm.getNamedItem("mustUnderstand");
            Integer mustUnderstand = null;
            if (n != null) {
                mustUnderstand = toInteger(n.getNodeValue());
            }
            return Optional.of(new BeginSessionR(mustUnderstand));
        }
        return Optional.empty();
    }


    private static Command getCommand(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node n = nl.item(i);
            Command command  = getCommand(n);
            if (command != null) {
                return command;
            }
        }
        throw new IllegalArgumentException("Illegal command");
    }

    private static Command getCommand(org.w3c.dom.Node n) {
        String nodeName = n.getNodeName();
        if (STATEMENT.equals(nodeName)) {
            return new StatementR(n.getTextContent());
        }
        if (ALTER.equals(nodeName)) {
            return getAlterCommand(n.getChildNodes());
        }
        if (CLEAR_CACHE.equals(nodeName)) {
            return getClearCacheCommand(n.getChildNodes());
        }
        if (CANCEL.equals(nodeName)) {
            return getCancelCommand(n.getChildNodes());
        }
        return null;
    }

    private static Command getCancelCommand(NodeList nl) {
        Map<String, String> map = getMapValues(nl);
        BigInteger connectionID = toBigInteger(map.get(CONNECTION_ID));
        String sessionID = map.get(SESSION_ID);
        BigInteger spid = toBigInteger(map.get(SPID));
        Boolean cancelAssociated = toBoolean(map.get(CANCEL_ASSOCIATED));

        return new CancelR(connectionID, sessionID, spid, cancelAssociated);
    }

    private static Command getClearCacheCommand(NodeList nl) {
        ObjectReference object = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null && OBJECT2.equals(node.getNodeName())) {
                object = getObjectReference(node.getChildNodes());
                break;
            }
        }
        return new ClearCacheR(object);
    }

    private static ObjectReference getObjectReference(NodeList nl) {
        Map<String, String> map = getMapValues(nl);
        return new ObjectReferenceR(
            map.get(SERVER_ID),
            map.get(DATABASE_ID),
            map.get(ROLE_ID),
            map.get(TRACE_ID),
            map.get(ASSEMBLY_ID),
            map.get(DIMENSION_ID),
            map.get(DIMENSION_PERMISSION_ID),
            map.get(DATA_SOURCE_ID),
            map.get(DATA_SOURCE_PERMISSION_ID),
            map.get(DATABASE_PERMISSION_ID),
            map.get(DATA_SOURCE_VIEW_ID),
            map.get(CUBE_ID),
            map.get(MINING_STRUCTURE_ID),
            map.get(MEASURE_GROUP_ID),
            map.get(PERSPECTIVE_ID),
            map.get(CUBE_PERMISSION_ID),
            map.get(MDX_SCRIPT_ID),
            map.get(PARTITION_ID),
            map.get(AGGREGATION_DESIGN_ID),
            map.get(MINING_MODEL_ID),
            map.get(MINING_MODEL_PERMISSION_ID),
            map.get(MINING_STRUCTURE_PERMISSION_ID)
        );
    }

    private static Command getAlterCommand(NodeList nl) {
        ObjectReference object = null;
        MajorObject objectDefinition = null;
        Scope scope = null;
        Boolean allowCreate = null;
        ObjectExpansion objectExpansion = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (OBJECT2.equals(node.getNodeName())) {
                    object = getObjectReference(node.getChildNodes());
                }
                if (OBJECT_DEFINITION.equals(node.getNodeName())) {
                    objectDefinition = getMajorObject(node.getChildNodes());
                }
                if (SCOPE2.equals(node.getNodeName())) {
                    scope = Scope.fromValue(node.getTextContent());
                }
                if (ALLOW_CREATE.equals(node.getNodeName())) {
                    allowCreate = toBoolean(node.getTextContent());
                }
                if (OBJECT_EXPANSION.equals(node.getNodeName())) {
                    objectExpansion = ObjectExpansion.fromValue(node.getTextContent());
                }
            }

        }
        return new AlterR(object,
            objectDefinition,
            scope,
            allowCreate,
            objectExpansion);
    }

    private static MajorObject getMajorObject(NodeList nl) {
        AggregationDesign aggregationDesign = null;
        Assembly assembly = null;
        Cube cube = null;
        Database database = null;
        DataSource dataSource = null;
        DataSourceView dataSourceView = null;
        Dimension dimension = null;
        MdxScript mdxScript = null;
        MeasureGroup measureGroup = null;
        MiningModel miningModel = null;
        MiningStructure miningStructure = null;
        Partition partition = null;
        Permission permission = null;
        Perspective perspective = null;
        Role role = null;
        Server server = null;
        Trace trace = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("AggregationDesign".equals(node.getNodeName())) {
                    aggregationDesign = getAggregationDesign(node.getChildNodes());
                }
                if (ASSEMBLY2.equals(node.getNodeName())) {
                    assembly = getAssembly(node.getChildNodes());
                }
                if (CUBE2.equals(node.getNodeName())) {
                    cube = getCube(node.getChildNodes());
                }
                if ("Database".equals(node.getNodeName())) {
                    database = getDatabase(node.getChildNodes());
                }
                if ("DataSource".equals(node.getNodeName())) {
                    dataSource = getDataSource(node.getChildNodes());
                }
                if ("DataSourceView".equals(node.getNodeName())) {
                    dataSourceView = getDataSourceView(node.getChildNodes());
                }
                if (DIMENSION.equals(node.getNodeName())) {
                    dimension = getDimension(node.getChildNodes());
                }
                if ("MdxScript".equals(node.getNodeName())) {
                    mdxScript = getMdxScript(node.getChildNodes());
                }
                if (MEASURE_GROUP.equals(node.getNodeName())) {
                    measureGroup = getMeasureGroup(node.getChildNodes());
                }
                if ("MiningModel".equals(node.getNodeName())) {
                    miningModel = getMiningModel(node.getChildNodes());
                }
                if ("MiningStructure".equals(node.getNodeName())) {
                    miningStructure = getMiningStructure(node.getChildNodes());
                }
                if ("Partition".equals(node.getNodeName())) {
                    partition = getPartition(node.getChildNodes());
                }
                if ("Permission".equals(node.getNodeName())) {
                    permission = getPermission(node.getChildNodes());
                }
                if ("Perspective".equals(node.getNodeName())) {
                    perspective = getPerspective(node.getChildNodes());
                }
                if (ROLE2.equals(node.getNodeName())) {
                    role = getRole(node.getChildNodes());
                }
                if ("Server".equals(node.getNodeName())) {
                    server = getServer(node.getChildNodes());
                }
                if ("Trace".equals(node.getNodeName())) {
                    trace = getTrace(node.getChildNodes());
                }
            }
        }
        return new MajorObjectR(
            aggregationDesign,
            assembly,
            cube,
            database,
            dataSource,
            dataSourceView,
            dimension,
            mdxScript,
            measureGroup,
            miningModel,
            miningStructure,
            partition,
            permission,
            perspective,
            role,
            server,
            trace
        );
    }

    private static Trace getTrace(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String logFileName = null;
        Boolean logFileAppend = null;
        Long logFileSize = null;
        Boolean audit = null;
        Boolean logFileRollover = null;
        Boolean autoRestart = null;
        Instant stopTime = null;
        TraceFilter filter = null;
        EventType eventType = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("LogFileName".equals(node.getNodeName())) {
                    logFileName = node.getTextContent();
                }
                if ("LogFileAppend".equals(node.getNodeName())) {
                    logFileAppend = toBoolean(node.getTextContent());
                }
                if ("LogFileSize".equals(node.getNodeName())) {
                    logFileSize = toLong(node.getTextContent());
                }
                if ("Audit".equals(node.getNodeName())) {
                    audit = toBoolean(node.getTextContent());
                }
                if ("LogFileRollover".equals(node.getNodeName())) {
                    logFileRollover = toBoolean(node.getTextContent());
                }
                if ("AutoRestart".equals(node.getNodeName())) {
                    autoRestart = toBoolean(node.getTextContent());
                }
                if ("StopTime".equals(node.getNodeName())) {
                    stopTime = toInstant(node.getTextContent());
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = getTraceFilter(node.getChildNodes());
                }
                if ("EventType".equals(node.getNodeName())) {
                    eventType = getEventType(node.getChildNodes());
                }
            }
        }
        return new TraceR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            logFileName,
            logFileAppend,
            logFileSize,
            audit,
            logFileRollover,
            autoRestart,
            stopTime,
            filter,
            eventType
        );
    }

    private static EventType getEventType(NodeList nl) {
        List<Event> events = null;
        XEvent xEvent = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Events".equals(node.getNodeName())) {
                    events = getEventList(node.getChildNodes());
                }
                if ("XEvent".equals(node.getNodeName())) {
                    xEvent = getXEvent(node.getChildNodes());
                }
            }
        }
        return new EventTypeR(events, xEvent);
    }

    private static XEvent getXEvent(NodeList nl) {
        EventSession eventSession = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("event_session".equals(node.getNodeName()))) {
                    eventSession = getEventSession(node.getChildNodes());
            }
        }
        return new XEventR(eventSession);
    }

    private static EventSession getEventSession(NodeList nl) {
        String templateCategory = null;
        String templateName = null;
        String templateDescription = null;
        List<Object> event = new ArrayList<>();
        List<java.lang.Object> target = new ArrayList<>();
        String name = null;
        BigInteger maxMemory = null;
        RetentionModes eventRetentionMode = null;
        Long dispatchLatency = null;
        Long maxEventSize = null;
        PartitionModes memoryPartitionMode = null;
        Boolean trackCausality = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("templateCategory".equals(node.getNodeName())) {
                    templateCategory = node.getTextContent();
                }
                if ("templateName".equals(node.getNodeName())) {
                    templateName = node.getTextContent();
                }
                if ("templateDescription".equals(node.getNodeName())) {
                    templateDescription = node.getTextContent();
                }
                if ("event".equals(node.getNodeName())) {
                    event.add(node.getTextContent());
                }
                if ("target".equals(node.getNodeName())) {
                    target.add(node.getTextContent());
                }
                NamedNodeMap nm = node.getAttributes();
                name = getAttribute(nm, "name");
                maxMemory = toBigInteger(getAttribute(nm, "maxMemory"));
                eventRetentionMode = RetentionModes.fromValue(getAttribute(nm, "eventRetentionMode"));
                dispatchLatency = toLong(getAttribute(nm, "dispatchLatency"));
                maxEventSize = toLong(getAttribute(nm, "maxEventSize"));
                memoryPartitionMode = PartitionModes.fromValue(getAttribute(nm, "memoryPartitionMode"));
                trackCausality = toBoolean(getAttribute(nm, "trackCausality"));
            }
        }
        return new EventSessionR(
            templateCategory,
            templateName,
            templateDescription,
            event,
            target,
            name,
            maxMemory,
            eventRetentionMode,
            dispatchLatency,
            maxEventSize,
            memoryPartitionMode,
            trackCausality
        );
    }

    private static List<Event> getEventList(NodeList nl) {
        List<Event> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Event".equals(node.getNodeName()))) {
                    list.add(getEvent(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Event getEvent(NodeList nl) {
        String eventID = null;
        EventColumnID columns = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (EVENT_ID.equals(node.getNodeName())) {
                    eventID = node.getTextContent();
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getEventColumnID(node.getChildNodes());
                }
            }
        }
        return new EventR(eventID, columns);
    }

    private static EventColumnID getEventColumnID(NodeList nl) {
        List<String> columnID = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (EVENT_ID.equals(node.getNodeName()))) {
                    columnID.add(node.getTextContent());
            }
        }
        return new EventColumnIDR(columnID);
    }

    private static TraceFilter getTraceFilter(NodeList nl) {
        NotType not = null;
        AndOrType or = null;
        AndOrType and = null;
        BoolBinop isEqual = null;
        BoolBinop notEqual = null;
        BoolBinop less = null;
        BoolBinop lessOrEqual = null;
        BoolBinop greater = null;
        BoolBinop greaterOrEqual = null;
        BoolBinop like = null;
        BoolBinop notLike = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Not".equals(node.getNodeName())) {
                    not = getNotType(node.getChildNodes());
                }
                if ("Or".equals(node.getNodeName())) {
                    or = getAndOrType(node.getChildNodes());
                }
                if ("Or".equals(node.getNodeName())) {
                    or = getAndOrType(node.getChildNodes());
                }
                if ("And".equals(node.getNodeName())) {
                    and = getAndOrType(node.getChildNodes());
                }
                if (EQUAL.equals(node.getNodeName())) {
                    isEqual = getBoolBinop(node.getChildNodes());
                }
                if (NOT_EQUAL.equals(node.getNodeName())) {
                    notEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Less".equals(node.getNodeName())) {
                    less = getBoolBinop(node.getChildNodes());
                }
                if (LESS_OR_EQUAL.equals(node.getNodeName())) {
                    lessOrEqual = getBoolBinop(node.getChildNodes());
                }
                if (GREATER.equals(node.getNodeName())) {
                    greater = getBoolBinop(node.getChildNodes());
                }
                if (GREATER_OR_EQUAL.equals(node.getNodeName())) {
                    greaterOrEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Like".equals(node.getNodeName())) {
                    like = getBoolBinop(node.getChildNodes());
                }
                if (NOT_LIKE.equals(node.getNodeName())) {
                    notLike = getBoolBinop(node.getChildNodes());
                }
            }
        }
        return new TraceFilterR(
            not,
            or,
            and,
            isEqual,
            notEqual,
            less,
            lessOrEqual,
            greater,
            greaterOrEqual,
            like,
            notLike
        );
    }

    private static BoolBinop getBoolBinop(NodeList nl) {
        String columnID = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ColumnID".equals(node.getNodeName())) {
                    columnID = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new BoolBinopR(columnID, value);
    }

    private static AndOrType getAndOrType(NodeList nl) {
        List<AndOrTypeEnum> notOrOrOrAnd = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Not".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Not);
                }
                if ("Or".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Or);
                }
                if ("And".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.And);
                }
                if (EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Equal);
                }
                if (NOT_EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.NotEqual);
                }
                if ("Less".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Less);
                }
                if (LESS_OR_EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.LessOrEqual);
                }
                if (GREATER.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Greater);
                }
                if (GREATER_OR_EQUAL.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.GreaterOrEqual);
                }
                if ("Like".equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.Like);
                }
                if (NOT_LIKE.equals(node.getNodeName())) {
                    notOrOrOrAnd.add(AndOrTypeEnum.NotLike);
                }
            }
        }
        return new AndOrTypeR(notOrOrOrAnd);
    }

    private static NotType getNotType(NodeList nl) {
        NotType not = null;
        AndOrType or = null;
        AndOrType and = null;
        BoolBinop isEqual = null;
        BoolBinop notEqual = null;
        BoolBinop less = null;
        BoolBinop lessOrEqual = null;
        BoolBinop greater = null;
        BoolBinop greaterOrEqual = null;
        BoolBinop like = null;
        BoolBinop notLike = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Not".equals(node.getNodeName())) {
                    not = getNotType(node.getChildNodes());
                }
                if ("Or".equals(node.getNodeName())) {
                    or = getAndOrType(node.getChildNodes());
                }
                if ("And".equals(node.getNodeName())) {
                    and = getAndOrType(node.getChildNodes());
                }
                if (EQUAL.equals(node.getNodeName())) {
                    isEqual = getBoolBinop(node.getChildNodes());
                }
                if (NOT_EQUAL.equals(node.getNodeName())) {
                    notEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Less".equals(node.getNodeName())) {
                    less = getBoolBinop(node.getChildNodes());
                }
                if (LESS_OR_EQUAL.equals(node.getNodeName())) {
                    lessOrEqual = getBoolBinop(node.getChildNodes());
                }
                if (GREATER.equals(node.getNodeName())) {
                    greater = getBoolBinop(node.getChildNodes());
                }
                if (GREATER_OR_EQUAL.equals(node.getNodeName())) {
                    greaterOrEqual = getBoolBinop(node.getChildNodes());
                }
                if ("Like".equals(node.getNodeName())) {
                    like = getBoolBinop(node.getChildNodes());
                }
                if (NOT_LIKE.equals(node.getNodeName())) {
                    notLike = getBoolBinop(node.getChildNodes());
                }

            }
        }
        return new NotTypeR(
            not,
            or,
            and,
            isEqual,
            notEqual,
            less,
            lessOrEqual,
            greater,
            greaterOrEqual,
            like,
            notLike);
    }

    private static Instant toInstant(String it) {
        return it != null ? Instant.parse(it) : null;
    }

    private static Server getServer(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String productName = null;
        String edition = null;
        Long editionID = null;
        String version = null;
        String serverMode = null;
        String productLevel = null;
        Long defaultCompatibilityLevel = null;
        String supportedCompatibilityLevels = null;
        List<Database> databases = null;
        List<Assembly> assemblies = null;
        List<Trace> traces = null;
        List<Role> roles = null;
        List<ServerProperty> serverProperties = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ProductName".equals(node.getNodeName())) {
                    productName = node.getTextContent();
                }
                if ("Edition".equals(node.getNodeName())) {
                    edition = node.getTextContent();
                }
                if ("EditionID".equals(node.getNodeName())) {
                    editionID = toLong(node.getTextContent());
                }
                if ("Version".equals(node.getNodeName())) {
                    version = node.getTextContent();
                }
                if ("ServerMode".equals(node.getNodeName())) {
                    serverMode = node.getTextContent();
                }
                if ("ProductLevel".equals(node.getNodeName())) {
                    productLevel = node.getTextContent();
                }
                if ("DefaultCompatibilityLevel".equals(node.getNodeName())) {
                    defaultCompatibilityLevel = toLong(node.getTextContent());
                }
                if ("SupportedCompatibilityLevels".equals(node.getNodeName())) {
                    supportedCompatibilityLevels = node.getTextContent();
                }
                if ("Databases".equals(node.getNodeName())) {
                    databases = getDatabaseList(node.getChildNodes());
                }
                if ("Assemblies".equals(node.getNodeName())) {
                    assemblies = getAssemblyList(node.getChildNodes());
                }
                if ("Traces".equals(node.getNodeName())) {
                    traces = getTraceList(node.getChildNodes());
                }
                if ("Roles".equals(node.getNodeName())) {
                    roles = getRoleList(node.getChildNodes());
                }
                if ("ServerProperties".equals(node.getNodeName())) {
                    serverProperties = getServerPropertyList(node.getChildNodes());
                }
            }
        }
        return new ServerR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            productName,
            edition,
            editionID,
            version,
            serverMode,
            productLevel,
            defaultCompatibilityLevel,
            supportedCompatibilityLevels,
            databases,
            assemblies,
            traces,
            roles,
            serverProperties
        );
    }

    private static List<Role> getRoleList(NodeList nl) {
        List<Role> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ROLE2.equals(node.getNodeName()))) {
                    list.add(getRole(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<ServerProperty> getServerPropertyList(NodeList nl) {
        List<ServerProperty> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("ServerProperty".equals(node.getNodeName()))) {
                    list.add(getServerProperty(node.getChildNodes()));
            }
        }
        return list;
    }

    private static ServerProperty getServerProperty(NodeList nl) {
        String name = null;
        String value = null;
        Boolean requiresRestart = null;
        java.lang.Object pendingValue = null;
        java.lang.Object defaultValue = null;
        Boolean displayFlag = null;
        String type = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
                if ("RequiresRestart".equals(node.getNodeName())) {
                    requiresRestart = toBoolean(node.getTextContent());
                }
                if ("PendingValue".equals(node.getNodeName())) {
                    pendingValue = node.getTextContent();
                }
                if ("DefaultValue".equals(node.getNodeName())) {
                    pendingValue = node.getTextContent();
                }
                if ("DisplayFlag".equals(node.getNodeName())) {
                    displayFlag = toBoolean(node.getTextContent());
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
            }
        }
        return new ServerPropertyR(
            name,
            value,
            requiresRestart,
            pendingValue,
            defaultValue,
            displayFlag,
            type);
    }

    private static List<Trace> getTraceList(NodeList nl) {
        List<Trace> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Trace".equals(node.getNodeName()))) {
                list.add(getTrace(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Assembly> getAssemblyList(NodeList nl) {
        List<Assembly> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ASSEMBLY2.equals(node.getNodeName()))) {
                    list.add(getAssembly(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Database> getDatabaseList(NodeList nl) {
        List<Database> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Database".equals(node.getNodeName()))) {
                list.add(getDatabase(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Role getRole(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        List<Member> members = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }

                if ("Members".equals(node.getNodeName())) {
                    members = getMemberList(node.getChildNodes());
                }
            }
        }
        return new RoleR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            Optional.ofNullable(members)
        );
    }

    private static List<Member> getMemberList(NodeList nl) {
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Member".equals(node.getNodeName()))) {
                list.add(getMember(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Member getMember(NodeList nl) {
        String name = null;
        String sid = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Sid".equals(node.getNodeName())) {
                    sid = node.getTextContent();
                }
            }
        }
        return new MemberR(Optional.ofNullable(name),
            Optional.ofNullable(sid));
    }

    private static Perspective getPerspective(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        List<Translation> translations = null;
        String defaultMeasure = null;
        List<PerspectiveDimension> dimensions = null;
        List<PerspectiveMeasureGroup> measureGroups = null;
        List<PerspectiveCalculation> calculations = null;
        List<PerspectiveKpi> kpis = null;
        List<PerspectiveAction> actions = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if ("DefaultMeasure".equals(node.getNodeName())) {
                    defaultMeasure = node.getTextContent();
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = getPerspectiveDimensionList(node.getChildNodes());
                }
                if ("MeasureGroups".equals(node.getNodeName())) {
                    measureGroups = getPerspectiveMeasureGroupList(node.getChildNodes());
                }
                if ("Calculations".equals(node.getNodeName())) {
                    calculations = getPerspectiveCalculationList(node.getChildNodes());
                }
                if ("Kpis".equals(node.getNodeName())) {
                    kpis = getPerspectiveKpiList(node.getChildNodes());
                }
                if ("Actions".equals(node.getNodeName())) {
                    actions = getPerspectiveActionList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            translations,
            defaultMeasure,
            dimensions,
            measureGroups,
            calculations,
            kpis,
            actions);
    }

    private static List<PerspectiveAction> getPerspectiveActionList(NodeList nl) {
        List<PerspectiveAction> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ACTION.equals(node.getNodeName()))) {
                list.add(getPerspectiveAction(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveAction getPerspectiveAction(NodeList nl) {
        String actionID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ActionID".equals(node.getNodeName())) {
                    actionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveActionR(actionID, Optional.ofNullable(annotations));
    }

    private static List<PerspectiveKpi> getPerspectiveKpiList(NodeList nl) {
        List<PerspectiveKpi> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ACTION.equals(node.getNodeName()))) {
                list.add(getPerspectiveKpi(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveKpi getPerspectiveKpi(NodeList nl) {
        String kpiID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("KpiID".equals(node.getNodeName())) {
                    kpiID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveKpiR(kpiID, Optional.ofNullable(annotations));
    }

    private static List<PerspectiveCalculation> getPerspectiveCalculationList(NodeList nl) {
        List<PerspectiveCalculation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Calculation".equals(node.getNodeName()))) {
                list.add(getPerspectiveCalculation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveCalculation getPerspectiveCalculation(NodeList nl) {

        String name = null;
        String type = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveCalculationR(name, type, Optional.ofNullable(annotations));
    }

    private static List<PerspectiveMeasureGroup> getPerspectiveMeasureGroupList(NodeList nl) {
        List<PerspectiveMeasureGroup> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (MEASURE_GROUP.equals(node.getNodeName()))) {
                list.add(getPerspectiveMeasureGroup(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveMeasureGroup getPerspectiveMeasureGroup(NodeList nl) {
        String measureGroupID = null;
        List<PerspectiveMeasure> measures = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (MEASURE_GROUP_ID.equals(node.getNodeName())) {
                    measureGroupID = node.getTextContent();
                }
                if (MEASURES.equals(node.getNodeName())) {
                    measures = getPerspectiveMeasureList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveMeasureGroupR(
            measureGroupID,
            Optional.ofNullable(measures),
            Optional.ofNullable(annotations)
        );
    }

    private static List<PerspectiveMeasure> getPerspectiveMeasureList(NodeList nl) {
        List<PerspectiveMeasure> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (MEASURE.equals(node.getNodeName()))) {
                list.add(getPerspectiveMeasure(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveMeasure getPerspectiveMeasure(NodeList nl) {
        String measureID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (MEASURE_ID.equals(node.getNodeName())) {
                    measureID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveMeasureR(measureID,
            annotations);
    }

    private static List<PerspectiveDimension> getPerspectiveDimensionList(NodeList nl) {
        List<PerspectiveDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (DIMENSION.equals(node.getNodeName()))) {
                list.add(getPerspectiveDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveDimension getPerspectiveDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<PerspectiveAttribute> attributes = null;
        List<PerspectiveHierarchy> hierarchies = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getPerspectiveAttributeList(node.getChildNodes());
                }
                if (HIERARCHIES.equals(node.getNodeName())) {
                    hierarchies = getPerspectiveHierarchyList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveDimensionR(cubeDimensionID,
            Optional.ofNullable(attributes),
            Optional.ofNullable(hierarchies),
            Optional.ofNullable(annotations));
    }

    private static List<PerspectiveHierarchy> getPerspectiveHierarchyList(NodeList nl) {
        List<PerspectiveHierarchy> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (HIERARCHY.equals(node.getNodeName()))) {
                list.add(getPerspectiveHierarchy(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveHierarchy getPerspectiveHierarchy(NodeList nl) {
        String hierarchyID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("HierarchyID".equals(node.getNodeName())) {
                    hierarchyID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveHierarchyR(hierarchyID,
            Optional.ofNullable(annotations));
    }

    private static List<PerspectiveAttribute> getPerspectiveAttributeList(NodeList nl) {
        List<PerspectiveAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE.equals(node.getNodeName()))) {
                list.add(getPerspectiveAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static PerspectiveAttribute getPerspectiveAttribute(NodeList nl) {
        String attributeID = null;
        Boolean attributeHierarchyVisible = null;
        String defaultMember = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if (ATTRIBUTE_HIERARCHY_VISIBLE.equals(node.getNodeName())) {
                    attributeHierarchyVisible = toBoolean(node.getTextContent());
                }
                if (DEFAULT_MEMBER.equals(node.getNodeName())) {
                    defaultMember = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new PerspectiveAttributeR(
            attributeID,
            Optional.ofNullable(attributeHierarchyVisible),
            Optional.ofNullable(defaultMember),
            Optional.ofNullable(annotations)
        );
    }

    private static List<Translation> getTranslationList(NodeList nl, String nodeName) {
        List<Translation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (nodeName.equals(node.getNodeName()))) {
                list.add(getTranslation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Translation getTranslation(NodeList nl) {
        long language = 0;
        String caption = null;
        String description = null;
        String displayFolder = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toLong(node.getTextContent());
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new TranslationR(
            language,
            caption,
            description,
            displayFolder,
            annotations);
    }

    private static Permission getPermission(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new PermissionR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write));
    }

    private static Partition getPartition(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        TabularBinding source = null;
        BigInteger processingPriority = null;
        String aggregationPrefix = null;
        Partition.StorageMode storageMode = null;
        String processingMode = null;
        ErrorConfiguration errorConfiguration = null;
        String storageLocation = null;
        String remoteDatasourceID = null;
        String slice = null;
        ProactiveCaching proactiveCaching = null;
        String type = null;
        Long estimatedSize = null;
        Long estimatedRows = null;
        Partition.CurrentStorageMode currentStorageMode = null;
        String aggregationDesignID = null;
        List<AggregationInstance> aggregationInstances = null;
        DataSourceViewBinding aggregationInstanceSource = null;
        Instant lastProcessed = null;
        String state = null;
        Integer stringStoresCompatibilityLevel = null;
        Integer currentStringStoresCompatibilityLevel = null;
        String directQueryUsage = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (SOURCE.equals(node.getNodeName())) {
                    source = getTabularBinding(node.getChildNodes(), getNodeType(node));
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (AGGREGATION_PREFIX.equals(node.getNodeName())) {
                    aggregationPrefix = node.getTextContent();
                }
                if (STORAGE_MODE.equals(node.getNodeName())) {
                    storageMode = getPartitionStorageMode(node.getChildNodes());
                }
                if (PROCESSING_MODE.equals(node.getNodeName())) {
                    processingMode = node.getTextContent();
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = getErrorConfiguration(node.getChildNodes());
                }
                if (STORAGE_LOCATION.equals(node.getNodeName())) {
                    storageLocation = node.getTextContent();
                }
                if ("RemoteDatasourceID".equals(node.getNodeName())) {
                    remoteDatasourceID = node.getTextContent();
                }
                if ("Slice".equals(node.getNodeName())) {
                    slice = node.getTextContent();
                }
                if (PROACTIVE_CACHING.equals(node.getNodeName())) {
                    proactiveCaching = getProactiveCaching(node.getChildNodes());
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (ESTIMATED_SIZE.equals(node.getNodeName())) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (ESTIMATED_ROWS.equals(node.getNodeName())) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if ("CurrentStorageMode".equals(node.getNodeName())) {
                    currentStorageMode = getPartitionCurrentStorageMode(node.getChildNodes());
                }
                if (AGGREGATION_DESIGN_ID.equals(node.getNodeName())) {
                    aggregationDesignID = node.getTextContent();
                }
                if ("AggregationInstances".equals(node.getNodeName())) {
                    aggregationInstances = getAggregationInstanceList(node.getChildNodes());
                }
                if ("AggregationInstanceSource".equals(node.getNodeName())) {
                    aggregationInstanceSource = getDataSourceViewBinding(node.getChildNodes());
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("StringStoresCompatibilityLevel".equals(node.getNodeName())) {
                    stringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if ("CurrentStringStoresCompatibilityLevel".equals(node.getNodeName())) {
                    currentStringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if ("DirectQueryUsage".equals(node.getNodeName())) {
                    directQueryUsage = node.getTextContent();
                }
            }
        }

        return new PartitionR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            source,
            processingPriority,
            aggregationPrefix,
            storageMode,
            processingMode,
            errorConfiguration,
            storageLocation,
            remoteDatasourceID,
            slice,
            proactiveCaching,
            type,
            estimatedSize,
            estimatedRows,
            currentStorageMode,
            aggregationDesignID,
            aggregationInstances,
            aggregationInstanceSource,
            lastProcessed,
            state,
            stringStoresCompatibilityLevel,
            currentStringStoresCompatibilityLevel,
            directQueryUsage
        );
    }

    private static DataSourceViewBinding getDataSourceViewBinding(NodeList childNodes) {
        Map<String, String> map  = getMapValues(childNodes);
        return new DataSourceViewBindingR(map.get("DataSourceViewID"));
    }

    private static List<AggregationInstance> getAggregationInstanceList(NodeList nl) {
        List<AggregationInstance> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("AggregationInstance".equals(node.getNodeName()))) {
                list.add(getAggregationInstance(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstance getAggregationInstance(NodeList nl) {
        String id = null;
        String name = null;
        String aggregationType = null;
        TabularBinding source = null;
        List<AggregationInstanceDimension> dimensions = null;
        List<AggregationInstanceMeasure> measures = null;
        List<Annotation> annotations = null;
        String description = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("AggregationType".equals(node.getNodeName())) {
                    aggregationType = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getTabularBinding(node.getChildNodes(), getNodeType(node));
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = getAggregationInstanceDimensionList(node.getChildNodes());
                }
                if (MEASURES.equals(node.getNodeName())) {
                    measures = getAggregationInstanceMeasureList(node.getChildNodes());
                }
            }
        }
        return new AggregationInstanceR(
            id,
            name,
            aggregationType,
            source,
            dimensions,
            measures,
            annotations,
            description);
    }

    private static List<AggregationInstanceMeasure> getAggregationInstanceMeasureList(NodeList nl) {
        List<AggregationInstanceMeasure> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (MEASURE.equals(node.getNodeName()))) {
                list.add(getAggregationInstanceMeasure(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstanceMeasure getAggregationInstanceMeasure(NodeList nl) {
        String measureID = null;
        ColumnBinding source = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (MEASURE_ID.equals(node.getNodeName())) {
                    measureID = node.getTextContent();
                }
                if (MEASURE_ID.equals(node.getNodeName())) {
                    source = getColumnBinding(node.getChildNodes());
                }
            }
        }
        return new AggregationInstanceMeasureR(measureID,
            source);
    }

    private static ColumnBinding getColumnBinding(NodeList nl) {
        String tableID = null;
        String columnID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (TABLE_ID.equals(node.getNodeName())) {
                    tableID = node.getTextContent();
                }
                if ("ColumnID".equals(node.getNodeName())) {
                    columnID = node.getTextContent();
                }
            }
        }
        return new ColumnBindingR(tableID, columnID);
    }

    private static List<AggregationInstanceDimension> getAggregationInstanceDimensionList(NodeList nl) {
        List<AggregationInstanceDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (DIMENSION.equals(node.getNodeName()))) {
                    list.add(getAggregationInstanceDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstanceDimension getAggregationInstanceDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<AggregationInstanceAttribute> attributes = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getAggregationInstanceAttributeList(node.getChildNodes());
                }
            }
        }
        return new AggregationInstanceDimensionR(cubeDimensionID, Optional.ofNullable(attributes));
    }

    private static List<AggregationInstanceAttribute> getAggregationInstanceAttributeList(NodeList nl) {
        List<AggregationInstanceAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE.equals(node.getNodeName()))) {
                list.add(getAggregationInstanceAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationInstanceAttribute getAggregationInstanceAttribute(NodeList nl) {
        String attributeID = null;
        List<DataItem> keyColumns = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    keyColumns = getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
            }
        }
        return new AggregationInstanceAttributeR(attributeID,
            Optional.ofNullable(keyColumns));
    }

    private static List<DataItem> getDataItemList(NodeList nl, String nodeName) {
        List<DataItem> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (nodeName.equals(node.getNodeName()))) {
                list.add(getDataItem(node.getChildNodes()));
            }
        }
        return list;
    }

    private static DataItem getDataItem(NodeList nl) {
        String dataType = null;
        Integer dataSize = null;
        String mimeType = null;
        NullProcessingEnum nullProcessing = null;
        String trimming = null;
        InvalidXmlCharacterEnum invalidXmlCharacters = null;
        String collation = null;
        DataItemFormatEnum format = null;
        Binding source = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("DataType".equals(node.getNodeName())) {
                    dataType = node.getTextContent();
                }
                if ("DataSize".equals(node.getNodeName())) {
                    dataSize = toInteger(node.getTextContent());
                }
                if ("MimeType".equals(node.getNodeName())) {
                    mimeType = node.getTextContent();
                }
                if ("NullProcessing".equals(node.getNodeName())) {
                    nullProcessing = NullProcessingEnum.fromValue(node.getTextContent());
                }
                if ("Trimming".equals(node.getNodeName())) {
                    trimming = node.getTextContent();
                }
                if ("InvalidXmlCharacters".equals(node.getNodeName())) {
                    invalidXmlCharacters = InvalidXmlCharacterEnum.fromValue(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if ("Format".equals(node.getNodeName())) {
                    format = DataItemFormatEnum.fromValue(node.getTextContent());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getBinding(node.getChildNodes(),  getNodeType(node));
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new DataItemR(dataType,
            Optional.ofNullable(dataSize),
            Optional.ofNullable(mimeType),
            Optional.ofNullable(nullProcessing),
            Optional.ofNullable(trimming),
            Optional.ofNullable(invalidXmlCharacters),
            Optional.ofNullable(collation),
            Optional.ofNullable(format),
            Optional.ofNullable(source),
            Optional.ofNullable(annotations));
    }

    private static Binding getBinding(NodeList nl, String type) {
        if ("ColumnBinding".equals(type)) {
            return getColumnBinding(nl);
        }
        if ("RowBinding".equals(type)) {
            return getRowBinding(nl);
        }
        if ("DataSourceViewBinding".equals(type)) {
            return getDataSourceViewBinding(nl);
        }
        if ("AttributeBinding".equals(type)) {
            return getAttributeBinding(nl);
        }
        if ("UserDefinedGroupBinding".equals(type)) {
            return getUserDefinedGroupBinding(nl);
        }
        if ("MeasureBinding".equals(type)) {
            return getMeasureBinding(nl);
        }
        if ("CubeAttributeBinding".equals(type)) {
            return getCubeAttributeBinding(nl);
        }
        if ("DimensionBinding".equals(type)) {
            return getDimensionBinding(nl);
        }
        if ("CubeDimensionBinding".equals(type)) {
            return getCubeDimensionBinding(nl);
        }
        if ("MeasureGroupBinding".equals(type)) {
            return getMeasureGroupBinding(nl);
        }
        if ("MeasureGroupDimensionBinding".equals(type)) {
            return getMeasureGroupDimensionBinding(nl);
        }
        if ("TimeBinding".equals(type)) {
            return getTimeBinding(nl);
        }
        if ("TimeAttributeBinding".equals(type)) {
            return getTimeAttributeBinding();
        }
        if ("InheritedBinding".equals(type)) {
            return getInheritedBinding();
        }
        if ("CalculatedMeasureBinding".equals(type)) {
            return getCalculatedMeasureBinding(nl);
        }
        if ("RowNumberBinding".equals(type)) {
            return getRowNumberBinding();
        }
        if ("ExpressionBinding".equals(type)) {
            return getExpressionBinding(nl);
        }
        return null;
    }

    private static Binding getExpressionBinding(NodeList nl) {
        String expression = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (EXPRESSION.equals(node.getNodeName()))) {
                expression = node.getTextContent();
            }
        }
        return new ExpressionBindingR(expression);
    }

    private static Binding getRowNumberBinding() {
        return new RowNumberBindingR();
    }

    private static Binding getCalculatedMeasureBinding(NodeList nl) {
        String measureName = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MeasureName".equals(node.getNodeName()))) {
                    measureName = node.getTextContent();
            }
        }
        return new CalculatedMeasureBindingR(measureName);
    }

    private static Binding getInheritedBinding() {
        return new InheritedBindingR();
    }

    private static Binding getTimeAttributeBinding() {
        return new TimeAttributeBindingR();
    }

    private static Binding getTimeBinding(NodeList nl) {
        Instant calendarStartDate = null;
        Instant calendarEndDate = null;
        Integer firstDayOfWeek = null;
        BigInteger calendarLanguage = null;
        Integer fiscalFirstMonth = null;
        Integer fiscalFirstDayOfMonth = null;
        FiscalYearNameEnum fiscalYearName = null;
        Integer reportingFirstMonth = null;
        String reportingFirstWeekOfMonth = null;
        ReportingWeekToMonthPatternEnum reportingWeekToMonthPattern = null;
        Integer manufacturingFirstMonth = null;
        Integer manufacturingFirstWeekOfMonth = null;
        Integer manufacturingExtraMonthQuarter = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("CalendarStartDate".equals(node.getNodeName())) {
                    calendarStartDate = toInstant(node.getTextContent());
                }
                if ("CalendarEndDate".equals(node.getNodeName())) {
                    calendarEndDate = toInstant(node.getTextContent());
                }
                if ("FirstDayOfWeek".equals(node.getNodeName())) {
                    firstDayOfWeek = toInteger(node.getTextContent());
                }
                if ("CalendarLanguage".equals(node.getNodeName())) {
                    calendarLanguage = toBigInteger(node.getTextContent());
                }
                if ("FiscalFirstMonth".equals(node.getNodeName())) {
                    fiscalFirstMonth = toInteger(node.getTextContent());
                }
                if ("FiscalFirstDayOfMonth".equals(node.getNodeName())) {
                    fiscalFirstDayOfMonth = toInteger(node.getTextContent());
                }
                if ("FiscalYearName".equals(node.getNodeName())) {
                    fiscalYearName = FiscalYearNameEnum.fromValue(node.getTextContent());
                }
                if ("ReportingFirstMonth".equals(node.getNodeName())) {
                    reportingFirstMonth = toInteger(node.getTextContent());
                }
                if ("ReportingFirstWeekOfMonth".equals(node.getNodeName())) {
                    reportingFirstWeekOfMonth = node.getTextContent();
                }
                if ("ReportingWeekToMonthPattern".equals(node.getNodeName())) {
                    reportingWeekToMonthPattern = ReportingWeekToMonthPatternEnum.fromValue(node.getTextContent());
                }
                if ("ManufacturingFirstMonth".equals(node.getNodeName())) {
                    manufacturingFirstMonth = toInteger(node.getTextContent());
                }
                if ("ManufacturingFirstWeekOfMonth".equals(node.getNodeName())) {
                    manufacturingFirstWeekOfMonth = toInteger(node.getTextContent());
                }
                if ("ManufacturingExtraMonthQuarter".equals(node.getNodeName())) {
                    manufacturingExtraMonthQuarter = toInteger(node.getTextContent());
                }
            }
        }
        return new TimeBindingR(
            calendarStartDate,
            calendarEndDate,
            Optional.ofNullable(firstDayOfWeek),
            Optional.ofNullable(calendarLanguage),
            Optional.ofNullable(fiscalFirstMonth),
            Optional.ofNullable(fiscalFirstDayOfMonth),
            Optional.ofNullable(fiscalYearName),
            Optional.ofNullable(reportingFirstMonth),
            Optional.ofNullable(reportingFirstWeekOfMonth),
            Optional.ofNullable(reportingWeekToMonthPattern),
            Optional.ofNullable(manufacturingFirstMonth),
            Optional.ofNullable(manufacturingFirstWeekOfMonth),
            Optional.ofNullable(manufacturingExtraMonthQuarter)
        );
    }

    private static Binding getMeasureGroupDimensionBinding(NodeList nl) {
        String cubeDimensionID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (CUBE_DIMENSION_ID.equals(node.getNodeName()))) {
                cubeDimensionID = node.getTextContent();
            }
        }
        return new MeasureGroupDimensionBindingR(cubeDimensionID);

    }

    private static Binding getCubeDimensionBinding(NodeList nl) {
        String dataSourceID = null;
        String cubeID = null;
        String cubeDimensionID = null;
        String filter = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if (CUBE_ID.equals(node.getNodeName())) {
                    cubeID = node.getTextContent();
                }
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
            }
        }
        return new CubeDimensionBindingR(
            dataSourceID,
            cubeID,
            cubeDimensionID,
            Optional.ofNullable(filter)
        );

    }

    private static Binding getDimensionBinding(NodeList nl) {
        String dataSourceID = null;
        String dimensionID = null;
        PersistenceEnum persistence = null;
        RefreshPolicyEnum refreshPolicy = null;
        Duration refreshInterval = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if (DIMENSION_ID.equals(node.getNodeName())) {
                    dimensionID = node.getTextContent();
                }
                if ("Persistence".equals(node.getNodeName())) {
                    persistence = PersistenceEnum.fromValue(node.getTextContent());
                }
                if ("RefreshPolicy".equals(node.getNodeName())) {
                    refreshPolicy = RefreshPolicyEnum.fromValue(node.getTextContent());
                }
                if (REFRESH_INTERVAL.equals(node.getNodeName())) {
                    refreshInterval = toDuration(node.getTextContent());
                }
            }
        }
        return new DimensionBindingR(
            dataSourceID,
            dimensionID,
            Optional.ofNullable(persistence),
            Optional.ofNullable(refreshPolicy),
            Optional.ofNullable(refreshInterval)
        );

    }

    private static Binding getCubeAttributeBinding(NodeList nl) {
        String cubeID = null;
        String cubeDimensionID = null;
        String attributeID = null;
        AttributeBindingTypeEnum type = null;
        List<BigInteger> ordinal = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_ID.equals(node.getNodeName())) {
                    cubeID = node.getTextContent();
                }
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = AttributeBindingTypeEnum.fromValue(node.getTextContent());
                }
                if (ORDINAL.equals(node.getNodeName())) {
                    ordinal = getOrdinalList(node.getChildNodes());
                }
            }
        }

        return new CubeAttributeBindingR(
            cubeID,
            cubeDimensionID,
            attributeID,
            type,
            Optional.ofNullable(ordinal)
        );

    }

    private static List<BigInteger> getOrdinalList(NodeList nl) {
        List<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ORDINAL.equals(node.getNodeName()))) {
                list.add(toBigInteger(node.getTextContent()));
            }
        }
        return list;
    }

    private static Binding getMeasureBinding(NodeList nl) {
        String measureID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (MEASURE_ID.equals(node.getNodeName()))) {
                measureID = node.getTextContent();
            }
        }
        return new MeasureBindingR(measureID);
    }

    private static Binding getUserDefinedGroupBinding(NodeList nl) {
        String attributeID = null;
        List<Group> groups = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("Groups".equals(node.getNodeName())) {
                    groups = getGroupList(node.getChildNodes());
                }
            }
        }
        return new UserDefinedGroupBindingR(attributeID, Optional.ofNullable(groups));
    }

    private static List<Group> getGroupList(NodeList nl) {
        List<Group> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Group".equals(node.getNodeName()))) {
                list.add(getGroup(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Group getGroup(NodeList nl) {
        String name = null;
        List<String> members = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Members".equals(node.getNodeName())) {
                    members = getMemberStringList(node.getChildNodes());
                }
            }
        }
        return new GroupR(
            name,
            Optional.ofNullable(members)
        );
    }

    private static List<String> getMemberStringList(NodeList nl) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Member".equals(node.getNodeName()))) {
                    list.add(node.getTextContent());
            }
        }
        return list;
    }

    private static Binding getAttributeBinding(NodeList nl) {
        String attributeID = null;
        AttributeBindingTypeEnum type = null;
        Integer ordinal = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = AttributeBindingTypeEnum.fromValue(node.getTextContent());
                }
                if (ORDINAL.equals(node.getNodeName())) {
                    ordinal = toInteger(node.getTextContent());
                }
            }
        }
        return new AttributeBindingR(attributeID, type, Optional.ofNullable(ordinal));
    }

    private static Binding getRowBinding(NodeList nl) {
        String tableID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (TABLE_ID.equals(node.getNodeName()))) {
                    tableID = node.getTextContent();
            }
        }
        return new RowBindingR(tableID);
    }

    private static Partition.CurrentStorageMode getPartitionCurrentStorageMode(NodeList nl) {
        PartitionCurrentStorageModeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = PartitionCurrentStorageModeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new PartitionR.CurrentStorageMode(value, valuens);
    }

    private static ProactiveCaching getProactiveCaching(NodeList nl) {
        String onlineMode = null;
        String aggregationStorage = null;
        ProactiveCachingBinding source = null;
        Duration silenceInterval = null;
        Duration latency = null;
        Duration silenceOverrideInterval = null;
        Duration forceRebuildInterval = null;
        Boolean enabled = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("OnlineMode".equals(node.getNodeName())) {
                    onlineMode = node.getTextContent();
                }
                if ("AggregationStorage".equals(node.getNodeName())) {
                    aggregationStorage = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getProactiveCachingBinding(node.getChildNodes(),
                        getNodeType(node));
                }
                if ("SilenceInterval".equals(node.getNodeName())) {
                    silenceInterval = toDuration(node.getTextContent());
                }
                if ("Latency".equals(node.getNodeName())) {
                    latency = toDuration(node.getTextContent());
                }
                if ("SilenceOverrideInterval".equals(node.getNodeName())) {
                    silenceOverrideInterval = toDuration(node.getTextContent());
                }
                if ("ForceRebuildInterval".equals(node.getNodeName())) {
                    forceRebuildInterval = toDuration(node.getTextContent());
                }
                if ("Enabled".equals(node.getNodeName())) {
                    enabled = toBoolean(node.getTextContent());
                }
            }
        }
        return new ProactiveCachingR(Optional.ofNullable(onlineMode),
            Optional.ofNullable(aggregationStorage),
            Optional.ofNullable(source),
            Optional.ofNullable(silenceInterval),
            Optional.ofNullable(latency),
            Optional.ofNullable(silenceOverrideInterval),
            Optional.ofNullable(forceRebuildInterval),
            Optional.ofNullable(enabled));
    }

    private static Duration toDuration(String it) {
        return it != null ? Duration.parse(it) : null;
    }

    private static ProactiveCachingBinding getProactiveCachingBinding(NodeList childNodes, String type) {
        if ("ProactiveCachingQueryBinding".equals(type)) {
            return getProactiveCachingQueryBinding();
        }
        if ("ProactiveCachingIncrementalProcessingBinding".equals(type)) {
            return getProactiveCachingIncrementalProcessingBinding(childNodes);
        }
        return null;
    }

    private static ProactiveCachingBinding getProactiveCachingIncrementalProcessingBinding(NodeList nl) {
        Duration refreshInterval = null;
        List<IncrementalProcessingNotification> incrementalProcessingNotifications = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (REFRESH_INTERVAL.equals(node.getNodeName())) {
                    refreshInterval = toDuration(node.getTextContent());
                }
                if ("IncrementalProcessingNotifications".equals(node.getNodeName())) {
                    incrementalProcessingNotifications = getIncrementalProcessingNotificationList(node.getChildNodes());
                }

            }
        }
        return new ProactiveCachingIncrementalProcessingBindingR(Optional.ofNullable(refreshInterval),
            incrementalProcessingNotifications);
    }

    private static List<IncrementalProcessingNotification> getIncrementalProcessingNotificationList(NodeList nl) {
        List<IncrementalProcessingNotification> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("IncrementalProcessingNotification".equals(node.getNodeName()))) {
                list.add(getIncrmentalProcessingNotification(node.getChildNodes()));
            }
        }
        return list;
    }

    private static IncrementalProcessingNotification getIncrmentalProcessingNotification(NodeList nl) {
        String tableID = null;
        String processingQuery = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (TABLE_ID.equals(node.getNodeName())) {
                    tableID = node.getTextContent();
                }
                if ("ProcessingQuery".equals(node.getNodeName())) {
                    processingQuery = node.getTextContent();
                }
            }
        }
        return new IncrementalProcessingNotificationR(tableID, processingQuery);
    }

    private static ProactiveCachingBinding getProactiveCachingQueryBinding() {
        return new ProactiveCachingBindingR();
    }

    private static ErrorConfiguration getErrorConfiguration(NodeList nl) {
        Long keyErrorLimit = null;
        String keyErrorLogFile = null;
        String keyErrorAction = null;
        String keyErrorLimitAction = null;
        String keyNotFound = null;
        String keyDuplicate = null;
        String nullKeyConvertedToUnknown = null;
        String nullKeyNotAllowed = null;
        String calculationError = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("KeyErrorLimit".equals(node.getNodeName())) {
                    keyErrorLimit = toLong(node.getTextContent());
                }
                if ("KeyErrorLogFile".equals(node.getNodeName())) {
                    keyErrorLogFile = node.getTextContent();
                }
                if ("KeyErrorAction".equals(node.getNodeName())) {
                    keyErrorAction = node.getTextContent();
                }
                if ("KeyErrorLimitAction".equals(node.getNodeName())) {
                    keyErrorLimitAction = node.getTextContent();
                }
                if ("KeyNotFound".equals(node.getNodeName())) {
                    keyNotFound = node.getTextContent();
                }
                if ("KeyDuplicate".equals(node.getNodeName())) {
                    keyDuplicate = node.getTextContent();
                }
                if ("NullKeyConvertedToUnknown".equals(node.getNodeName())) {
                    nullKeyConvertedToUnknown = node.getTextContent();
                }
                if ("NullKeyNotAllowed".equals(node.getNodeName())) {
                    nullKeyNotAllowed = node.getTextContent();
                }
                if ("CalculationError".equals(node.getNodeName())) {
                    calculationError = node.getTextContent();
                }
            }
        }
        return new ErrorConfigurationR(
            Optional.ofNullable(keyErrorLimit),
            Optional.ofNullable(keyErrorLogFile),
            Optional.ofNullable(keyErrorAction),
            Optional.ofNullable(keyErrorLimitAction),
            Optional.ofNullable(keyNotFound),
            Optional.ofNullable(keyDuplicate),
            Optional.ofNullable(nullKeyConvertedToUnknown),
            Optional.ofNullable(nullKeyNotAllowed),
            Optional.ofNullable(calculationError));
    }

    private static Partition.StorageMode getPartitionStorageMode(NodeList nl) {
        PartitionStorageModeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = PartitionStorageModeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new PartitionR.StorageMode(value, valuens);
    }

    private static TabularBinding getTabularBinding(NodeList nl, String type) {
        if ("TableBinding".equals(type)) {
            return getTableBinding(nl);
        }
        if ("QueryBinding".equals(type)) {
            return getQueryBinding(nl);
        }
        if ("DSVTableBinding".equals(type)) {
            return getDSVTableBinding(nl);
        }
        return null;
    }

    private static TabularBinding getDSVTableBinding(NodeList nl) {
        String dataSourceViewID = null;
        String tableID = null;
        String dataEmbeddingStyle = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_VIEW_ID.equals(node.getNodeName())) {
                    dataSourceViewID = node.getTextContent();
                }
                if (TABLE_ID.equals(node.getNodeName())) {
                    tableID = node.getTextContent();
                }
                if ("DataEmbeddingStyle".equals(node.getNodeName())) {
                    dataEmbeddingStyle = node.getTextContent();
                }
            }
        }
        return new DSVTableBindingR(
            Optional.ofNullable(dataSourceViewID),
            tableID,
            Optional.ofNullable(dataEmbeddingStyle)
        );
    }

    private static TabularBinding getQueryBinding(NodeList nl) {
        String dataSourceID = null;
        String queryDefinition = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if ("QueryDefinition".equals(node.getNodeName())) {
                    queryDefinition = node.getTextContent();
                }
            }
        }
        return new QueryBindingR(
            Optional.ofNullable(dataSourceID),
            queryDefinition
        );
    }

    private static TabularBinding getTableBinding(NodeList nl) {
        String dataSourceID = null;
        String dbTableName = null;
        String dbSchemaName = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if ("DbTableName".equals(node.getNodeName())) {
                    dbTableName = node.getTextContent();
                }
                if ("DbSchemaName".equals(node.getNodeName())) {
                    dbSchemaName = node.getTextContent();
                }
            }
        }
        return new TableBindingR(
            Optional.ofNullable(dataSourceID),
            dbTableName,
            Optional.ofNullable(dbSchemaName)
        );
    }

    private static MiningStructure getMiningStructure(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Binding source = null;
        Instant lastProcessed = null;
        List<Translation> translations = null;
        BigInteger language = null;
        String collation = null;
        ErrorConfiguration errorConfiguration = null;
        String cacheMode = null;
        Integer holdoutMaxPercent = null;
        Integer holdoutMaxCases = null;
        Integer holdoutSeed = null;
        Integer holdoutActualSize = null;
        List<MiningStructureColumn> columns = null;
        String state = null;
        List<MiningStructurePermission> miningStructurePermissions = null;
        List<MiningModel> miningModels = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (SOURCE.equals(node.getNodeName())) {
                    source = getBinding(node.getChildNodes(), getNodeType(node));
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = getErrorConfiguration(node.getChildNodes());
                }
                if ("CacheMode".equals(node.getNodeName())) {
                    cacheMode = node.getTextContent();
                }
                if ("HoldoutMaxPercent".equals(node.getNodeName())) {
                    holdoutMaxPercent = toInteger(node.getTextContent());
                }
                if ("HoldoutMaxCases".equals(node.getNodeName())) {
                    holdoutMaxCases = toInteger(node.getTextContent());
                }
                if ("HoldoutSeed".equals(node.getNodeName())) {
                    holdoutSeed = toInteger(node.getTextContent());
                }
                if ("HoldoutActualSize".equals(node.getNodeName())) {
                    holdoutSeed = toInteger(node.getTextContent());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningStructureColumnList(node.getChildNodes());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("MiningStructurePermissions".equals(node.getNodeName())) {
                    miningStructurePermissions = getMiningStructurePermissionList(node.getChildNodes());
                }
                if ("MiningModels".equals(node.getNodeName())) {
                    miningModels = getMiningModelList(node.getChildNodes());
                }
            }
        }
        return new MiningStructureR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            Optional.ofNullable(source),
            Optional.ofNullable(lastProcessed),
            Optional.ofNullable(translations),
            Optional.ofNullable(language),
            Optional.ofNullable(collation),
            Optional.ofNullable(errorConfiguration),
            Optional.ofNullable(cacheMode),
            Optional.ofNullable(holdoutMaxPercent),
            Optional.ofNullable(holdoutMaxCases),
            Optional.ofNullable(holdoutSeed),
            Optional.ofNullable(holdoutActualSize),
            columns,
            Optional.ofNullable(state),
            Optional.ofNullable(miningStructurePermissions),
            Optional.ofNullable(miningModels)
        );
    }

    private static List<MiningModel> getMiningModelList(NodeList nl) {
        List<MiningModel> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MiningModel".equals(node.getNodeName()))) {
                    list.add(getMiningModel(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<MiningStructurePermission> getMiningStructurePermissionList(NodeList nl) {
        List<MiningStructurePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MiningStructurePermission".equals(node.getNodeName()))) {
                list.add(getMiningStructurePermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static MiningStructurePermission getMiningStructurePermission(NodeList nl) {
        Boolean allowDrillThrough = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ALLOW_DRILL_THROUGH.equals(node.getNodeName())) {
                    allowDrillThrough = toBoolean(node.getTextContent());
                }
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new MiningStructurePermissionR(
            Optional.ofNullable(allowDrillThrough),
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write)
        );
    }

    private static List<MiningStructureColumn> getMiningStructureColumnList(NodeList nl) {
        List<MiningStructureColumn> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MiningStructurePermission".equals(node.getNodeName()))) {
                list.add(
                    getMiningStructureColumn(
                        node.getChildNodes(),
                        getNodeType(node)
                    )
                );
            }
        }
        return list;
    }

    private static MiningStructureColumn getMiningStructureColumn(
        NodeList nl,
        String type) {
        if ("ScalarMiningStructureColumn".equals(type)) {
            return  getScalarMiningStructureColumn(nl);
        }
        if ("ScalarMiningStructureColumn".equals(type)) {
            return  getTableMiningStructureColumn(nl);
        }
        return null;
    }

    private static MiningStructureColumn getTableMiningStructureColumn(NodeList nl) {
        List<DataItem> foreignKeyColumns = null;
        MeasureGroupBinding sourceMeasureGroup = null;
        List<MiningStructureColumn> columns = null;
        List<Translation> translations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ForeignKeyColumns".equals(node.getNodeName())) {
                    foreignKeyColumns = getDataItemList(node.getChildNodes(), "ForeignKeyColumn");
                }
                if ("SourceMeasureGroup".equals(node.getNodeName())) {
                    sourceMeasureGroup = getMeasureGroupBinding(node.getChildNodes());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningStructureColumnList(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
            }
        }
        return new TableMiningStructureColumnR(
            Optional.ofNullable(foreignKeyColumns),
            Optional.ofNullable(sourceMeasureGroup),
            Optional.ofNullable(columns),
            Optional.ofNullable(translations)
        );
    }

    private static MiningStructureColumn getScalarMiningStructureColumn(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String type = null;
        List<Annotation> annotations = null;
        Boolean isKey = null;
        Binding source = null;
        String distribution = null;
        List<MiningModelingFlag> modelingFlags = null;
        String content = null;
        List<String> classifiedColumns = null;
        String discretizationMethod = null;
        BigInteger discretizationBucketCount = null;
        List<DataItem> keyColumns = null;
        DataItem nameColumn = null;
        List<Translation> translations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("IsKey".equals(node.getNodeName())) {
                    isKey = toBoolean(node.getTextContent());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getBinding(node.getChildNodes(), getNodeType(node));
                }
                if ("Distribution".equals(node.getNodeName())) {
                    distribution = node.getTextContent();
                }
                if ("ModelingFlags".equals(node.getNodeName())) {
                    modelingFlags = getMiningModelingFlagList(node.getChildNodes());
                }
                if ("Content".equals(node.getNodeName())) {
                    content = node.getTextContent();
                }
                if ("ClassifiedColumns".equals(node.getNodeName())) {
                    classifiedColumns = getClassifiedColumnList(node.getChildNodes());
                }
                if ("DiscretizationMethod".equals(node.getNodeName())) {
                    discretizationMethod = node.getTextContent();
                }
                if ("DiscretizationBucketCount".equals(node.getNodeName())) {
                    discretizationBucketCount = toBigInteger(node.getTextContent());
                }
                if (KEY_COLUMNS.equals(node.getNodeName())) {
                    keyColumns = getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
                if ("NameColumn".equals(node.getNodeName())) {
                    nameColumn = getDataItem(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
            }
        }
        return new ScalarMiningStructureColumnR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(description),
            Optional.ofNullable(type),
            Optional.ofNullable(annotations),
            Optional.ofNullable(isKey),
            Optional.ofNullable(source),
            Optional.ofNullable(distribution),
            Optional.ofNullable(modelingFlags),
            content,
            Optional.ofNullable(classifiedColumns),
            Optional.ofNullable(discretizationMethod),
            Optional.ofNullable(discretizationBucketCount),
            Optional.ofNullable(keyColumns),
            Optional.ofNullable(nameColumn),
            Optional.ofNullable(translations)
        );
    }

    private static List<String> getClassifiedColumnList(NodeList nl) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("ClassifiedColumn".equals(node.getNodeName()))) {
                list.add(node.getTextContent());
            }
        }
        return list;
    }

    private static MiningModel getMiningModel(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String algorithm = null;
        Instant lastProcessed = null;
        List<AlgorithmParameter> algorithmParameters = null;
        Boolean allowDrillThrough = null;
        List<AttributeTranslation> translations = null;
        List<MiningModelColumn> columns = null;
        String state = null;
        FoldingParameters foldingParameters = null;
        String filter = null;
        List<MiningModelPermission> miningModelPermissions = null;
        String language = null;
        String collation = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Algorithm".equals(node.getNodeName())) {
                    algorithm = node.getTextContent();
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if ("AlgorithmParameters".equals(node.getNodeName())) {
                    algorithmParameters = getAlgorithmParameterList(node.getChildNodes());
                }
                if (ALLOW_DRILL_THROUGH.equals(node.getNodeName())) {
                    allowDrillThrough = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getAttributeTranslationList(node.getChildNodes());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningModelColumnList(node.getChildNodes());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("FoldingParameters".equals(node.getNodeName())) {
                    foldingParameters = getFoldingParameters(node.getChildNodes());
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
                if ("MiningModelPermissions".equals(node.getNodeName())) {
                    miningModelPermissions = getMiningModelPermissionList(node.getChildNodes());
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = node.getTextContent();
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
            }
        }
        return new MiningModelR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            algorithm,
            Optional.ofNullable(lastProcessed),
            Optional.ofNullable(algorithmParameters),
            Optional.ofNullable(allowDrillThrough),
            Optional.ofNullable(translations),
            Optional.ofNullable(columns),
            Optional.ofNullable(state),
            Optional.ofNullable(foldingParameters),
            Optional.ofNullable(filter),
            Optional.ofNullable(miningModelPermissions),
            Optional.ofNullable(language),
            Optional.ofNullable(collation)
        );
    }

    private static List<MiningModelPermission> getMiningModelPermissionList(NodeList nl) {
        List<MiningModelPermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MiningModelPermission".equals(node.getNodeName()))) {
                list.add(getMiningModelPermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static MiningModelPermission getMiningModelPermission(NodeList nl) {
        Boolean allowDrillThrough = null;
        Boolean allowBrowsing = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ALLOW_DRILL_THROUGH.equals(node.getNodeName())) {
                    allowDrillThrough = toBoolean(node.getTextContent());
                }
                if ("AllowBrowsing".equals(node.getNodeName())) {
                    allowBrowsing = toBoolean(node.getTextContent());
                }
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new MiningModelPermissionR(
            Optional.ofNullable(allowDrillThrough),
            Optional.ofNullable(allowBrowsing),
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write)
        );
    }

    private static FoldingParameters getFoldingParameters(NodeList nl) {
        Integer foldIndex = null;
        Integer foldCount = null;
        Long foldMaxCases = null;
        String foldTargetAttribute = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("FoldIndex".equals(node.getNodeName())) {
                    foldIndex = toInteger(node.getTextContent());
                }
                if ("FoldIndex".equals(node.getNodeName())) {
                    foldCount = toInteger(node.getTextContent());
                }
                if ("FoldMaxCases".equals(node.getNodeName())) {
                    foldMaxCases = toLong(node.getTextContent());
                }
                if ("FoldTargetAttribute".equals(node.getNodeName())) {
                    foldTargetAttribute = node.getTextContent();
                }
            }
        }
        return new FoldingParametersR(
            foldIndex,
            foldCount,
            Optional.ofNullable(foldMaxCases),
            Optional.ofNullable(foldTargetAttribute)
        );
    }

    private static List<MiningModelColumn> getMiningModelColumnList(NodeList nl) {
        List<MiningModelColumn> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MiningModelPermission".equals(node.getNodeName()))) {
                list.add(getMiningModelColumn(node.getChildNodes()));
            }
        }
        return list;
    }

    private static MiningModelColumn getMiningModelColumn(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String sourceColumnID = null;
        String usage = null;
        String filter = null;
        List<Translation> translations = null;
        List<MiningModelColumn> columns = null;
        List<MiningModelingFlag> modelingFlags = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("SourceColumnID".equals(node.getNodeName())) {
                    sourceColumnID = node.getTextContent();
                }
                if ("Usage".equals(node.getNodeName())) {
                    usage = node.getTextContent();
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningModelColumnList(node.getChildNodes());
                }
                if ("ModelingFlags".equals(node.getNodeName())) {
                    modelingFlags = getMiningModelingFlagList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }

        return new MiningModelColumnR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(description),
            Optional.ofNullable(sourceColumnID),
            Optional.ofNullable(usage),
            Optional.ofNullable(filter),
            Optional.ofNullable(translations),
            Optional.ofNullable(columns),
            Optional.ofNullable(modelingFlags),
            Optional.ofNullable(annotations)
        );
    }

    private static List<MiningModelingFlag> getMiningModelingFlagList(NodeList nl) {
        List<MiningModelingFlag> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("AlgorithmParameter".equals(node.getNodeName()))) {
                list.add(getMiningModelingFlag(node.getChildNodes()));
            }
        }
        return list;
    }

    private static MiningModelingFlag getMiningModelingFlag(NodeList nl) {
        String modelingFlag = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("ModelingFlag".equals(node.getNodeName()))) {
                    modelingFlag = node.getTextContent();
                    break;
            }
        }
        return new MiningModelingFlagR(Optional.ofNullable(modelingFlag));
    }

    private static List<AttributeTranslation> getAttributeTranslationList(NodeList nl) {
        List<AttributeTranslation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (TRANSLATION.equals(node.getNodeName()))) {
                list.add(getAttributeTranslation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AttributeTranslation getAttributeTranslation(NodeList nl) {
        long language = 0;
        String caption = null;
        String description = null;
        String displayFolder = null;
        List<Annotation> annotations = null;
        DataItem captionColumn = null;
        String membersWithDataCaption = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toLong(node.getTextContent());
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("CaptionColumn".equals(node.getNodeName())) {
                    captionColumn = getDataItem(node.getChildNodes());
                }
                if ("MembersWithDataCaption".equals(node.getNodeName())) {
                    membersWithDataCaption = node.getTextContent();
                }
            }
        }
        return new AttributeTranslationR(
            language,
            Optional.ofNullable(caption),
            Optional.ofNullable(description),
            Optional.ofNullable(displayFolder),
            Optional.ofNullable(annotations),
            Optional.ofNullable(captionColumn),
            Optional.ofNullable(membersWithDataCaption)
        );
    }

    private static List<AlgorithmParameter> getAlgorithmParameterList(NodeList nl) {
        List<AlgorithmParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("AlgorithmParameter".equals(node.getNodeName()))) {
                    list.add(getAlgorithmParameter(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AlgorithmParameter getAlgorithmParameter(NodeList nl) {
        String name = null;
        java.lang.Object value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new AlgorithmParameterR(
            name,
            value
        );
    }

    private static MeasureGroup getMeasureGroup(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Instant lastProcessed = null;
        List<Translation> translations = null;
        String type = null;
        String state = null;
        List<Measure> measures = null;
        String dataAggregation = null;
        MeasureGroupBinding source = null;
        MeasureGroup.StorageMode storageMode = null;
        String storageLocation = null;
        Boolean ignoreUnrelatedDimensions = null;
        ProactiveCaching proactiveCaching = null;
        Long estimatedRows = null;
        ErrorConfiguration errorConfiguration = null;
        Long estimatedSize = null;
        String processingMode = null;
        List<org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension> dimensions = null;
        List<Partition> partitions = null;
        String aggregationPrefix = null;
        BigInteger processingPriority = null;
        List<AggregationDesign> aggregationDesigns = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if (MEASURES.equals(node.getNodeName())) {
                    measures = getMeasureList(node.getChildNodes());
                }
                if ("DataAggregation".equals(node.getNodeName())) {
                    dataAggregation = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getMeasureGroupBinding(node.getChildNodes());
                }
                if (STORAGE_MODE.equals(node.getNodeName())) {
                    storageMode = getMeasureGroupStorageMode(node.getChildNodes());
                }
                if (STORAGE_LOCATION.equals(node.getNodeName())) {
                    storageLocation = node.getTextContent();
                }
                if ("IgnoreUnrelatedDimensions".equals(node.getNodeName())) {
                    ignoreUnrelatedDimensions = toBoolean(node.getTextContent());
                }
                if (PROACTIVE_CACHING.equals(node.getNodeName())) {
                    proactiveCaching = getProactiveCaching(node.getChildNodes());
                }
                if (ESTIMATED_ROWS.equals(node.getNodeName())) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = getErrorConfiguration(node.getChildNodes());
                }
                if (ESTIMATED_SIZE.equals(node.getNodeName())) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (PROCESSING_MODE.equals(node.getNodeName())) {
                    processingMode = node.getTextContent();
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = getMeasureGroupDimensionList(node.getChildNodes());
                }
                if ("Partitions".equals(node.getNodeName())) {
                    partitions = getPartitionList(node.getChildNodes());
                }
                if (AGGREGATION_PREFIX.equals(node.getNodeName())) {
                    aggregationPrefix = node.getTextContent();
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if ("AggregationDesigns".equals(node.getNodeName())) {
                    aggregationDesigns = getAggregationDesignList(node.getChildNodes());
                }

            }
        }
        return new MeasureGroupR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            lastProcessed,
            translations,
            type,
            state,
            measures,
            dataAggregation,
            source,
            storageMode,
            storageLocation,
            ignoreUnrelatedDimensions,
            proactiveCaching,
            estimatedRows,
            errorConfiguration,
            estimatedSize,
            processingMode,
            dimensions,
            partitions,
            aggregationPrefix,
            processingPriority,
            aggregationDesigns
        );
    }

    private static List<AggregationDesign> getAggregationDesignList(NodeList nl) {
        List<AggregationDesign> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("AggregationDesigns".equals(node.getNodeName()))) {
                list.add(getAggregationDesign(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Partition> getPartitionList(NodeList nl) {
        List<Partition> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Partition".equals(node.getNodeName()))) {
                list.add(getPartition(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension> getMeasureGroupDimensionList(NodeList nl) {
        List<org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (DIMENSIONS.equals(node.getNodeName()))) {
                list.add(getMeasureGroupDimension(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    private static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getMeasureGroupDimension(NodeList nl, String type) {
        if ("ManyToManyMeasureGroupDimension".equals(type)) {
            return getManyToManyMeasureGroupDimension(nl);
        }
        if ("RegularMeasureGroupDimension".equals(type)) {
            return getRegularMeasureGroupDimension(nl);
        }
        if ("ReferenceMeasureGroupDimension".equals(type)) {
            return getReferenceMeasureGroupDimension(nl);
        }
        if ("DegenerateMeasureGroupDimension".equals(type)) {
            return getDegenerateMeasureGroupDimension(nl);
        }
        if ("DataMiningMeasureGroupDimension".equals(type)) {
            return getDataMiningMeasureGroupDimension(nl);
        }
        return null;
    }

    private static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getDataMiningMeasureGroupDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String caseCubeDimensionID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = (MeasureGroupDimensionBinding)getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if ("CaseCubeDimensionID".equals(node.getNodeName())) {
                    caseCubeDimensionID = node.getTextContent();
                }
            }
        }
        return new DataMiningMeasureGroupDimensionR(
            cubeDimensionID,
            annotations,
            source,
            caseCubeDimensionID
        );
    }

    private static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getDegenerateMeasureGroupDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String shareDimensionStorage = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = (MeasureGroupDimensionBinding)getMeasureGroupDimensionBinding(node.getChildNodes());
                }

                if ("ShareDimensionStorage".equals(node.getNodeName())) {
                    shareDimensionStorage = node.getTextContent();
                }
            }
        }
        return new DegenerateMeasureGroupDimensionR(
            cubeDimensionID,
            annotations,
            source,
            shareDimensionStorage
        );

    }

    private static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getReferenceMeasureGroupDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String intermediateCubeDimensionID = null;
        String intermediateGranularityAttributeID = null;
        String materialization = null;
        String processingState = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = (MeasureGroupDimensionBinding)getMeasureGroupDimensionBinding(node.getChildNodes());
                }

                if ("IntermediateCubeDimensionID".equals(node.getNodeName())) {
                    intermediateCubeDimensionID = node.getTextContent();
                }
                if ("IntermediateGranularityAttributeID".equals(node.getNodeName())) {
                    intermediateGranularityAttributeID = node.getTextContent();
                }
                if ("Materialization".equals(node.getNodeName())) {
                    materialization = node.getTextContent();
                }
                if (PROCESSING_STATE.equals(node.getNodeName())) {
                    processingState = node.getTextContent();
                }
            }
        }
        return new ReferenceMeasureGroupDimensionR(
            cubeDimensionID,
            annotations,
            source,
            intermediateCubeDimensionID,
            intermediateGranularityAttributeID,
            materialization,
            processingState
        );
    }

    private static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getRegularMeasureGroupDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String cardinality = null;
        List<MeasureGroupAttribute> attributes = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = (MeasureGroupDimensionBinding)getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if ("Cardinality".equals(node.getNodeName())) {
                    cardinality = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getMeasureGroupAttributeList(node.getChildNodes());
                }
            }
        }
        return new RegularMeasureGroupDimensionR(
            cubeDimensionID,
            annotations,
            source,
            cardinality,
            attributes
        );
    }

    private static List<MeasureGroupAttribute> getMeasureGroupAttributeList(NodeList nl) {
        List<MeasureGroupAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE.equals(node.getNodeName()))) {
                list.add(getMeasureGroupAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static MeasureGroupAttribute getMeasureGroupAttribute(NodeList nl) {
        String attributeID = null;
        List<DataItem> keyColumns = null;
        String type = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if (KEY_COLUMNS.equals(node.getNodeName())) {
                    keyColumns = getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new MeasureGroupAttributeR(
            attributeID,
            keyColumns,
            type,
            annotations
        );
    }

    private static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getManyToManyMeasureGroupDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String measureGroupID = null;
        String directSlice = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = (MeasureGroupDimensionBinding)getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if (MEASURE_GROUP_ID.equals(node.getNodeName())) {
                    measureGroupID = node.getTextContent();
                }
                if ("DirectSlice".equals(node.getNodeName())) {
                    directSlice = node.getTextContent();
                }
            }
        }
        return new ManyToManyMeasureGroupDimensionR(
            cubeDimensionID,
            annotations,
            source,
            measureGroupID,
            directSlice
        );
    }

    private static MeasureGroup.StorageMode getMeasureGroupStorageMode(NodeList nl) {
        MeasureGroupStorageModeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = MeasureGroupStorageModeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new MeasureGroupR.StorageMode(value, valuens);
    }

    private static MeasureGroupBinding getMeasureGroupBinding(NodeList nl) {
        String dataSourceID = null;
        String cubeID = null;
        String measureGroupID = null;
        PersistenceEnum persistence = null;
        RefreshPolicyEnum refreshPolicy = null;
        Duration refreshInterval = null;
        String filter = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if (CUBE_ID.equals(node.getNodeName())) {
                    cubeID = node.getTextContent();
                }
                if (MEASURE_GROUP_ID.equals(node.getNodeName())) {
                    measureGroupID = node.getTextContent();
                }
                if ("Persistence".equals(node.getNodeName())) {
                    persistence = PersistenceEnum.fromValue(node.getTextContent());
                }
                if ("RefreshPolicy".equals(node.getNodeName())) {
                    refreshPolicy = RefreshPolicyEnum.fromValue(node.getTextContent());
                }
                if (REFRESH_INTERVAL.equals(node.getNodeName())) {
                    refreshInterval = toDuration(node.getTextContent());
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
            }
        }
        return new MeasureGroupBindingR(dataSourceID,
            cubeID,
            measureGroupID,
            Optional.ofNullable(persistence),
            Optional.ofNullable(refreshPolicy),
            Optional.ofNullable(refreshInterval),
            Optional.ofNullable(filter));
    }

    private static List<Measure> getMeasureList(NodeList nl) {
        List<Measure> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (MEASURE.equals(node.getNodeName()))) {
                list.add(getMeasure(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Measure getMeasure(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String aggregateFunction = null;
        String dataType = null;
        DataItem source = null;
        Boolean visible = null;
        String measureExpression = null;
        String displayFolder = null;
        String formatString = null;
        String backColor = null;
        String foreColor = null;
        String fontName = null;
        String fontSize = null;
        String fontFlags = null;
        List<Translation> translations = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("AggregateFunction".equals(node.getNodeName())) {
                    aggregateFunction = node.getTextContent();
                }
                if ("DataType".equals(node.getNodeName())) {
                    dataType = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getDataItem(node.getChildNodes());
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("MeasureExpression".equals(node.getNodeName())) {
                    measureExpression = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if ("FormatString".equals(node.getNodeName())) {
                    formatString = node.getTextContent();
                }
                if ("BackColor".equals(node.getNodeName())) {
                    backColor = node.getTextContent();
                }
                if ("ForeColor".equals(node.getNodeName())) {
                    foreColor = node.getTextContent();
                }
                if ("FontName".equals(node.getNodeName())) {
                    fontName = node.getTextContent();
                }
                if ("FontSize".equals(node.getNodeName())) {
                    fontSize = node.getTextContent();
                }
                if ("FontFlags".equals(node.getNodeName())) {
                    fontFlags = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }

        return new MeasureR(
            name,
            id,
            description,
            aggregateFunction,
            dataType,
            source,
            visible,
            measureExpression,
            displayFolder,
            formatString,
            backColor,
            foreColor,
            fontName,
            fontSize,
            fontFlags,
            translations,
            annotations
        );
    }

    private static MdxScript getMdxScript(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        List<Command> commands = null;
        Boolean defaultScript = null;
        List<CalculationProperty> calculationProperties = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Commands".equals(node.getNodeName())) {
                    commands = getCommandList(node.getChildNodes());
                }
                if ("DefaultScript".equals(node.getNodeName())) {
                    defaultScript = toBoolean(node.getTextContent());
                }
                if ("CalculationProperties".equals(node.getNodeName())) {
                    calculationProperties = getCalculationPropertyList(node.getChildNodes());
                }
            }
        }
        return new MdxScriptR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            commands,
            defaultScript,
            calculationProperties
        );
    }

    private static List<CalculationProperty> getCalculationPropertyList(NodeList nl) {
        List<CalculationProperty> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("CalculationProperty".equals(node.getNodeName()))) {
                list.add(getCalculationProperty(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Command> getCommandList(NodeList nl) {
        List<Command> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (COMMAND.equals(node.getNodeName()))) {
                list.add(getCommand(node.getChildNodes()));
            }
        }
        return list;
    }

    private static CalculationProperty getCalculationProperty(NodeList nl) {
        String calculationReference = null;
        String calculationType = null;
        List<Translation> translations = null;
        String description = null;
        Boolean visible = null;
        BigInteger solveOrder = null;
        String formatString = null;
        String foreColor = null;
        String backColor = null;
        String fontName = null;
        String fontSize = null;
        String fontFlags = null;
        String nonEmptyBehavior = null;
        String associatedMeasureGroupID = null;
        String displayFolder = null;
        BigInteger language = null;
        CalculationPropertiesVisualizationProperties visualizationProperties = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("CalculationReference".equals(node.getNodeName())) {
                    calculationReference = node.getTextContent();
                }
                if ("CalculationType".equals(node.getNodeName())) {
                    calculationType = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("SolveOrder".equals(node.getNodeName())) {
                    solveOrder = toBigInteger(node.getTextContent());
                }
                if ("FormatString".equals(node.getNodeName())) {
                    formatString = node.getTextContent();
                }
                if ("ForeColor".equals(node.getNodeName())) {
                    foreColor = node.getTextContent();
                }
                if ("BackColor".equals(node.getNodeName())) {
                    backColor = node.getTextContent();
                }
                if ("FontName".equals(node.getNodeName())) {
                    fontName = node.getTextContent();
                }
                if ("FontSize".equals(node.getNodeName())) {
                    fontSize = node.getTextContent();
                }
                if ("NonEmptyBehavior".equals(node.getNodeName())) {
                    nonEmptyBehavior = node.getTextContent();
                }
                if ("AssociatedMeasureGroupID".equals(node.getNodeName())) {
                    associatedMeasureGroupID = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (VISUALIZATION_PROPERTIES.equals(node.getNodeName())) {
                    visualizationProperties = getCalculationPropertiesVisualizationProperties(node.getChildNodes());
                }
            }
        }

        return new CalculationPropertyR(
            calculationReference,
            calculationType,
            translations,
            description,
            visible,
            solveOrder,
            formatString,
            foreColor,
            backColor,
            fontName,
            fontSize,
            fontFlags,
            nonEmptyBehavior,
            associatedMeasureGroupID,
            displayFolder,
            language,
            visualizationProperties
        );
    }

    private static CalculationPropertiesVisualizationProperties getCalculationPropertiesVisualizationProperties(NodeList nl) {
        BigInteger folderPosition = null;
        String contextualNameRule = null;
        String alignment = null;
        Boolean isFolderDefault = null;
        Boolean isRightToLeft = null;
        String sortDirection = null;
        String units = null;
        BigInteger width = null;
        Boolean isDefaultMeasure = null;
        BigInteger defaultDetailsPosition = null;
        BigInteger sortPropertiesPosition = null;
        Boolean isSimpleMeasure = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (FOLDER_POSITION.equals(node.getNodeName())) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
                if (CONTEXTUAL_NAME_RULE.equals(node.getNodeName())) {
                    contextualNameRule = node.getTextContent();
                }
                if ("Alignment".equals(node.getNodeName())) {
                    alignment = node.getTextContent();
                }
                if ("IsFolderDefault".equals(node.getNodeName())) {
                    isFolderDefault = toBoolean(node.getTextContent());
                }
                if ("IsRightToLeft".equals(node.getNodeName())) {
                    isRightToLeft = toBoolean(node.getTextContent());
                }
                if ("SortDirection".equals(node.getNodeName())) {
                    sortDirection = node.getTextContent();
                }
                if ("Units".equals(node.getNodeName())) {
                    units = node.getTextContent();
                }
                if ("Width".equals(node.getNodeName())) {
                    width = toBigInteger(node.getTextContent());
                }
                if (DEFAULT_DETAILS_POSITION.equals(node.getNodeName())) {
                    defaultDetailsPosition = toBigInteger(node.getTextContent());
                }
                if (SORT_PROPERTIES_POSITION.equals(node.getNodeName())) {
                    sortPropertiesPosition = toBigInteger(node.getTextContent());
                }
                if ("IsDefaultMeasure".equals(node.getNodeName())) {
                    isDefaultMeasure = toBoolean(node.getTextContent());
                }
                if ("IsSimpleMeasure".equals(node.getNodeName())) {
                    isSimpleMeasure = toBoolean(node.getTextContent());
                }
            }
        }

        return new CalculationPropertiesVisualizationPropertiesR(
            folderPosition,
            contextualNameRule,
            alignment,
            isFolderDefault,
            isRightToLeft,
            sortDirection,
            units,
            width,
            isDefaultMeasure,
            defaultDetailsPosition,
            sortPropertiesPosition,
            isSimpleMeasure
        );
    }

    private static Dimension getDimension(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Binding source = null;
        String miningModelID = null;
        String type = null;
        Dimension.UnknownMember unknownMember = null;
        String mdxMissingMemberMode = null;
        ErrorConfiguration errorConfiguration = null;
        String storageMode = null;
        Boolean writeEnabled = null;
        BigInteger processingPriority = null;
        Instant lastProcessed = null;
        List<DimensionPermission> dimensionPermissions = null;
        String dependsOnDimensionID = null;
        BigInteger language = null;
        String collation = null;
        String unknownMemberName = null;
        List<Translation> unknownMemberTranslations = null;
        String state = null;
        ProactiveCaching proactiveCaching = null;
        String processingMode = null;
        String processingGroup = null;
        Dimension.CurrentStorageMode currentStorageMode = null;
        List<Translation> translations = null;
        List<DimensionAttribute> attributes = null;
        String attributeAllMemberName = null;
        List<Translation> attributeAllMemberTranslations = null;
        List<Hierarchy> hierarchies = null;
        String processingRecommendation = null;
        Relationships relationships = null;
        Integer stringStoresCompatibilityLevel = null;
        Integer currentStringStoresCompatibilityLevel = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getBinding(node.getChildNodes(), getNodeType(node));
                }
                if (MINING_MODEL_ID.equals(node.getNodeName())) {
                    miningModelID = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if ("UnknownMember".equals(node.getNodeName())) {
                    unknownMember = getDimensionUnknownMember(node.getChildNodes());
                }
                if ("MdxMissingMemberMode".equals(node.getNodeName())) {
                    mdxMissingMemberMode = node.getTextContent();
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = getErrorConfiguration(node.getChildNodes());
                }
                if (STORAGE_MODE.equals(node.getNodeName())) {
                    storageMode = node.getTextContent();
                }
                if ("WriteEnabled".equals(node.getNodeName())) {
                    writeEnabled = toBoolean(node.getTextContent());
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if ("DimensionPermissions".equals(node.getNodeName())) {
                    dimensionPermissions = getDimensionPermissionList(node.getChildNodes());
                }
                if ("DependsOnDimensionID".equals(node.getNodeName())) {
                    dependsOnDimensionID = node.getTextContent();
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if ("UnknownMemberName".equals(node.getNodeName())) {
                    unknownMemberName = node.getTextContent();
                }
                if ("UnknownMemberTranslations".equals(node.getNodeName())) {
                    unknownMemberTranslations = getTranslationList(node.getChildNodes(), "UnknownMemberTranslation");
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if (PROACTIVE_CACHING.equals(node.getNodeName())) {
                    proactiveCaching = getProactiveCaching(node.getChildNodes());
                }
                if (PROCESSING_MODE.equals(node.getNodeName())) {
                    processingMode = node.getTextContent();
                }
                if ("ProcessingGroup".equals(node.getNodeName())) {
                    processingGroup = node.getTextContent();
                }
                if ("CurrentStorageMode".equals(node.getNodeName())) {
                    processingGroup = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getDimensionAttributeList(node.getChildNodes());
                }
                if ("AttributeAllMemberName".equals(node.getNodeName())) {
                    attributeAllMemberName = node.getTextContent();
                }
                if ("AttributeAllMemberTranslations".equals(node.getNodeName())) {
                    attributeAllMemberTranslations = getTranslationList(node.getChildNodes(),
                        "AttributeAllMemberTranslation");
                }
                if (HIERARCHIES.equals(node.getNodeName())) {
                    hierarchies = getHierarchyList(node.getChildNodes());
                }
                if ("ProcessingRecommendation".equals(node.getNodeName())) {
                    processingRecommendation = node.getTextContent();
                }
                if ("Relationships".equals(node.getNodeName())) {
                    relationships = getRelationships(node.getChildNodes());
                }
                if ("StringStoresCompatibilityLevel".equals(node.getNodeName())) {
                    stringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if ("CurrentStringStoresCompatibilityLevel".equals(node.getNodeName())) {
                    currentStringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
            }
        }
        return new DimensionR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            source,
            miningModelID,
            type,
            unknownMember,
            mdxMissingMemberMode,
            errorConfiguration,
            storageMode,
            writeEnabled,
            processingPriority,
            lastProcessed,
            dimensionPermissions,
            dependsOnDimensionID,
            language,
            collation,
            unknownMemberName,
            unknownMemberTranslations,
            state,
            proactiveCaching,
            processingMode,
            processingGroup,
            currentStorageMode,
            translations,
            attributes,
            attributeAllMemberName,
            attributeAllMemberTranslations,
            hierarchies,
            processingRecommendation,
            relationships,
            stringStoresCompatibilityLevel,
            currentStringStoresCompatibilityLevel);
    }

    private static List<DimensionPermission> getDimensionPermissionList(NodeList nl) {
        List<DimensionPermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("DimensionPermission".equals(node.getNodeName()))) {
                list.add(getDimensionPermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static DimensionPermission getDimensionPermission(NodeList nl) {
        List<AttributePermission> attributePermissions = null;
        String allowedRowsExpression = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }

                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }

                if ("AttributePermissions".equals(node.getNodeName())) {
                    attributePermissions = getAttributePermissionList(node.getChildNodes());
                }
                if ("AllowedRowsExpression".equals(node.getNodeName())) {
                    allowedRowsExpression = node.getTextContent();
                }
            }
        }

        return new DimensionPermissionR(
            Optional.ofNullable(attributePermissions),
            Optional.ofNullable(allowedRowsExpression),
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write)
        );
    }

    private static List<AttributePermission> getAttributePermissionList(NodeList nl) {
        List<AttributePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("AttributePermission".equals(node.getNodeName()))) {
                    list.add(getAttributePermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AttributePermission getAttributePermission(NodeList nl) {
        String attributeID = null;
        String description = null;
        String defaultMember = null;
        String visualTotals = null;
        String allowedSet = null;
        String deniedSet = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (DEFAULT_MEMBER.equals(node.getNodeName())) {
                    defaultMember = node.getTextContent();
                }
                if ("VisualTotals".equals(node.getNodeName())) {
                    visualTotals = node.getTextContent();
                }
                if ("AllowedSet".equals(node.getNodeName())) {
                    allowedSet = node.getTextContent();
                }
                if ("DeniedSet".equals(node.getNodeName())) {
                    deniedSet = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AttributePermissionR(
            attributeID,
            Optional.ofNullable(description),
            Optional.ofNullable(defaultMember),
            Optional.ofNullable(visualTotals),
            Optional.ofNullable(allowedSet),
            Optional.ofNullable(deniedSet),
            Optional.ofNullable(annotations)
        );
    }

    private static Dimension.UnknownMember getDimensionUnknownMember(NodeList nl) {
        UnknownMemberEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = UnknownMemberEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new DimensionR.UnknownMember(value, valuens);
    }

    private static List<DimensionAttribute> getDimensionAttributeList(NodeList nl) {
        List<DimensionAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE.equals(node.getNodeName()))) {
                list.add(getDimensionAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static DimensionAttribute getDimensionAttribute(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        DimensionAttribute.Type type = null;
        String usage = null;
        Binding source = null;
        Long estimatedCount = null;
        List<DataItem> keyColumns = null;
        DataItem nameColumn = null;
        DataItem valueColumn = null;
        List<AttributeTranslation> translations = null;
        List<AttributeRelationship> attributeRelationships = null;
        String discretizationMethod = null;
        BigInteger discretizationBucketCount = null;
        String rootMemberIf = null;
        String orderBy = null;
        String defaultMember = null;
        String orderByAttributeID = null;
        DataItem skippedLevelsColumn = null;
        String namingTemplate = null;
        String membersWithData = null;
        String membersWithDataCaption = null;
        List<Translation> namingTemplateTranslations = null;
        DataItem customRollupColumn = null;
        DataItem customRollupPropertiesColumn = null;
        DataItem unaryOperatorColumn = null;
        Boolean attributeHierarchyOrdered = null;
        Boolean memberNamesUnique = null;
        Boolean isAggregatable = null;
        Boolean attributeHierarchyEnabled = null;
        String attributeHierarchyOptimizedState = null;
        Boolean attributeHierarchyVisible = null;
        String attributeHierarchyDisplayFolder = null;
        Boolean keyUniquenessGuarantee = null;
        String groupingBehavior = null;
        String instanceSelection = null;
        List<Annotation> annotations = null;
        String processingState = null;
        AttributeHierarchyProcessingState attributeHierarchyProcessingState = null;
        DimensionAttributeVisualizationProperties visualizationProperties = null;
        String extendedType = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = getDimensionAttributeType(node.getChildNodes());
                }
                if ("Usage".equals(node.getNodeName())) {
                    usage = node.getTextContent();
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getBinding(node.getChildNodes(), getNodeType(node));
                }
                if ("EstimatedCount".equals(node.getNodeName())) {
                    estimatedCount = toLong(node.getTextContent());
                }
                if (KEY_COLUMNS.equals(node.getNodeName())) {
                    keyColumns = getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
                if ("NameColumn".equals(node.getNodeName())) {
                    nameColumn = getDataItem(node.getChildNodes());
                }
                if ("ValueColumn".equals(node.getNodeName())) {
                    valueColumn = getDataItem(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getAttributeTranslationList(node.getChildNodes());
                }
                if ("AttributeRelationships".equals(node.getNodeName())) {
                    translations = getAttributeRelationshipsList(node.getChildNodes());
                }
                if ("DiscretizationMethod".equals(node.getNodeName())) {
                    discretizationMethod = node.getTextContent();
                }
                if ("DiscretizationBucketCount".equals(node.getNodeName())) {
                    discretizationBucketCount = toBigInteger(node.getTextContent());
                }
                if ("RootMemberIf".equals(node.getNodeName())) {
                    rootMemberIf = node.getTextContent();
                }
                if ("OrderBy".equals(node.getNodeName())) {
                    orderBy = node.getTextContent();
                }
                if (DEFAULT_MEMBER.equals(node.getNodeName())) {
                    defaultMember = node.getTextContent();
                }
                if ("OrderByAttributeID".equals(node.getNodeName())) {
                    orderByAttributeID = node.getTextContent();
                }
                if ("SkippedLevelsColumn".equals(node.getNodeName())) {
                    skippedLevelsColumn = getDataItem(node.getChildNodes());
                }
                if ("NamingTemplate".equals(node.getNodeName())) {
                    namingTemplate = node.getTextContent();
                }
                if ("MembersWithData".equals(node.getNodeName())) {
                    membersWithData = node.getTextContent();
                }
                if ("MembersWithDataCaption".equals(node.getNodeName())) {
                    membersWithDataCaption = node.getTextContent();
                }
                if ("NamingTemplateTranslations".equals(node.getNodeName())) {
                    namingTemplateTranslations = getNamingTemplateTranslationList(node.getChildNodes());
                }
                if ("CustomRollupColumn".equals(node.getNodeName())) {
                    customRollupColumn = getDataItem(node.getChildNodes());
                }
                if ("CustomRollupPropertiesColumn".equals(node.getNodeName())) {
                    customRollupPropertiesColumn = getDataItem(node.getChildNodes());
                }
                if ("unaryOperatorColumn".equals(node.getNodeName())) {
                    unaryOperatorColumn = getDataItem(node.getChildNodes());
                }
                if ("AttributeHierarchyOrdered".equals(node.getNodeName())) {
                    attributeHierarchyOrdered = toBoolean(node.getTextContent());
                }
                if ("MemberNamesUnique".equals(node.getNodeName())) {
                    memberNamesUnique = toBoolean(node.getTextContent());
                }
                if ("IsAggregatable".equals(node.getNodeName())) {
                    isAggregatable = toBoolean(node.getTextContent());
                }
                if ("AttributeHierarchyEnabled".equals(node.getNodeName())) {
                    attributeHierarchyEnabled = toBoolean(node.getTextContent());
                }
                if ("AttributeHierarchyOptimizedState".equals(node.getNodeName())) {
                    attributeHierarchyOptimizedState = node.getTextContent();
                }
                if (ATTRIBUTE_HIERARCHY_VISIBLE.equals(node.getNodeName())) {
                    attributeHierarchyVisible = toBoolean(node.getTextContent());
                }
                if ("AttributeHierarchyDisplayFolder".equals(node.getNodeName())) {
                    attributeHierarchyDisplayFolder = node.getTextContent();
                }
                if ("KeyUniquenessGuarantee".equals(node.getNodeName())) {
                    keyUniquenessGuarantee = toBoolean(node.getTextContent());
                }
                if ("GroupingBehavior".equals(node.getNodeName())) {
                    groupingBehavior = node.getTextContent();
                }
                if ("InstanceSelection".equals(node.getNodeName())) {
                    instanceSelection = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (PROCESSING_STATE.equals(node.getNodeName())) {
                    processingState = node.getTextContent();
                }
                if ("AttributeHierarchyProcessingState".equals(node.getNodeName())) {
                    attributeHierarchyProcessingState =
                        AttributeHierarchyProcessingState.fromValue(node.getTextContent());
                }
                if (VISUALIZATION_PROPERTIES.equals(node.getNodeName())) {
                    visualizationProperties = getDimensionAttributeVisualizationProperties(node.getChildNodes());
                }
                if ("ExtendedType".equals(node.getNodeName())) {
                    extendedType = node.getTextContent();
                }
            }
        }
        return new DimensionAttributeR(
            name,
            id,
            description,
            type,
            usage,
            source,
            estimatedCount,
            keyColumns,
            nameColumn,
            valueColumn,
            translations,
            attributeRelationships,
            discretizationMethod,
            discretizationBucketCount,
            rootMemberIf,
            orderBy,
            defaultMember,
            orderByAttributeID,
            skippedLevelsColumn,
            namingTemplate,
            membersWithData,
            membersWithDataCaption,
            namingTemplateTranslations,
            customRollupColumn,
            customRollupPropertiesColumn,
            unaryOperatorColumn,
            attributeHierarchyOrdered,
            memberNamesUnique,
            isAggregatable,
            attributeHierarchyEnabled,
            attributeHierarchyOptimizedState,
            attributeHierarchyVisible,
            attributeHierarchyDisplayFolder,
            keyUniquenessGuarantee,
            groupingBehavior,
            instanceSelection,
            annotations,
            processingState,
            attributeHierarchyProcessingState,
            visualizationProperties,
            extendedType);
    }

    private static DimensionAttributeVisualizationProperties getDimensionAttributeVisualizationProperties(NodeList nl) {
        BigInteger folderPosition = null;
        String contextualNameRule = null;
        String alignment = null;
        Boolean isFolderDefault = null;
        Boolean isRightToLeft = null;
        String sortDirection = null;
        String units = null;
        BigInteger width = null;
        BigInteger defaultDetailsPosition = null;
        BigInteger commonIdentifierPosition = null;
        BigInteger sortPropertiesPosition = null;
        BigInteger displayKeyPosition = null;
        Boolean isDefaultImage = null;
        String defaultAggregateFunction = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (FOLDER_POSITION.equals(node.getNodeName())) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
                if (CONTEXTUAL_NAME_RULE.equals(node.getNodeName())) {
                    contextualNameRule = node.getTextContent();
                }
                if ("Alignment".equals(node.getNodeName())) {
                    alignment = node.getTextContent();
                }
                if ("IsFolderDefault".equals(node.getNodeName())) {
                    isFolderDefault = toBoolean(node.getTextContent());
                }
                if ("IsRightToLeft".equals(node.getNodeName())) {
                    isRightToLeft = toBoolean(node.getTextContent());
                }
                if ("SortDirection".equals(node.getNodeName())) {
                    sortDirection = node.getTextContent();
                }
                if ("Units".equals(node.getNodeName())) {
                    units = node.getTextContent();
                }
                if ("Width".equals(node.getNodeName())) {
                    width = toBigInteger(node.getTextContent());
                }
                if (DEFAULT_DETAILS_POSITION.equals(node.getNodeName())) {
                    defaultDetailsPosition = toBigInteger(node.getTextContent());
                }
                if (SORT_PROPERTIES_POSITION.equals(node.getNodeName())) {
                    sortPropertiesPosition = toBigInteger(node.getTextContent());
                }
                if ("CommonIdentifierPosition".equals(node.getNodeName())) {
                    commonIdentifierPosition = toBigInteger(node.getTextContent());
                }
                if ("DisplayKeyPosition".equals(node.getNodeName())) {
                    displayKeyPosition = toBigInteger(node.getTextContent());
                }
                if ("IsDefaultImage".equals(node.getNodeName())) {
                    isDefaultImage = toBoolean(node.getTextContent());
                }
                if ("DefaultAggregateFunction".equals(node.getNodeName())) {
                    defaultAggregateFunction = node.getTextContent();
                }
            }
        }
        return new DimensionAttributeVisualizationPropertiesR(
            folderPosition,
            contextualNameRule,
            alignment,
            isFolderDefault,
            isRightToLeft,
            sortDirection,
            units,
            width,
            defaultDetailsPosition,
            commonIdentifierPosition,
            sortPropertiesPosition,
            displayKeyPosition,
            isDefaultImage,
            defaultAggregateFunction
        );
    }

    private static List<Translation> getNamingTemplateTranslationList(NodeList nl) {
        List<Translation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("NamingTemplateTranslation".equals(node.getNodeName()))) {
                list.add(getTranslation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<AttributeTranslation> getAttributeRelationshipsList(NodeList nl) {
        List<AttributeTranslation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("AttributeRelationship".equals(node.getNodeName()))) {
                list.add(getAttributeTranslation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static DimensionAttribute.Type getDimensionAttributeType(NodeList nl) {
        DimensionAttributeTypeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = DimensionAttributeTypeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
                break;
            }
        }
        return new DimensionAttributeR.Type(value,
            valuens);
    }

    private static List<Hierarchy> getHierarchyList(NodeList nl) {
        List<Hierarchy> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (HIERARCHY.equals(node.getNodeName()))) {
                    list.add(getHierarchy(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Hierarchy getHierarchy(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String processingState = null;
        String structureType = null;
        String displayFolder = null;
        List<Translation> translations = null;
        String allMemberName = null;
        List<Translation> allMemberTranslations = null;
        Boolean memberNamesUnique = null;
        String memberKeysUnique = null;
        Boolean allowDuplicateNames = null;
        List<Level> levels = null;
        List<Annotation> annotations = null;
        HierarchyVisualizationProperties visualizationProperties = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (PROCESSING_STATE.equals(node.getNodeName())) {
                    processingState = node.getTextContent();
                }
                if ("StructureType".equals(node.getNodeName())) {
                    structureType = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if ("AllMemberName".equals(node.getNodeName())) {
                    allMemberName = node.getTextContent();
                }
                if ("AllMemberTranslations".equals(node.getNodeName())) {
                    allMemberTranslations = getTranslationList(node.getChildNodes(), "AllMemberTranslation");
                }
                if ("MemberNamesUnique".equals(node.getNodeName())) {
                    memberNamesUnique = toBoolean(node.getTextContent());
                }
                if ("MemberKeysUnique".equals(node.getNodeName())) {
                    memberKeysUnique = node.getTextContent();
                }
                if ("AllowDuplicateNames".equals(node.getNodeName())) {
                    allowDuplicateNames = toBoolean(node.getTextContent());
                }
                if ("Levels".equals(node.getNodeName())) {
                    levels = getLevelList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (VISUALIZATION_PROPERTIES.equals(node.getNodeName())) {
                    visualizationProperties = getHierarchyVisualizationProperties(node.getChildNodes());
                }
            }
        }
        return new HierarchyR(
            name,
            id,
            description,
            processingState,
            structureType,
            displayFolder,
            translations,
            allMemberName,
            allMemberTranslations,
            memberNamesUnique,
            memberKeysUnique,
            allowDuplicateNames,
            levels,
            annotations,
            visualizationProperties
        );
    }

    private static HierarchyVisualizationProperties getHierarchyVisualizationProperties(NodeList nl) {
        String contextualNameRule = null;
        BigInteger folderPosition = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CONTEXTUAL_NAME_RULE.equals(node.getNodeName())) {
                    contextualNameRule = node.getTextContent();
                }
                if (FOLDER_POSITION.equals(node.getNodeName())) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
            }
        }
        return new HierarchyVisualizationPropertiesR(
            contextualNameRule,
            folderPosition
        );
    }

    private static List<Level> getLevelList(NodeList nl) {
        List<Level> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Level".equals(node.getNodeName()))) {
                    list.add(getLevel(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Level getLevel(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String sourceAttributeID = null;
        String hideMemberIf = null;
        List<Translation> translations = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("SourceAttributeID".equals(node.getNodeName())) {
                    sourceAttributeID = node.getTextContent();
                }
                if ("HideMemberIf".equals(node.getNodeName())) {
                    hideMemberIf = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new LevelR(
            name,
            id,
            description,
            sourceAttributeID,
            hideMemberIf,
            translations,
            annotations
        );

    }

    private static Relationships getRelationships(NodeList nl) {
        List<Relationship> relationship = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Relationship".equals(node.getNodeName()))) {
                relationship.add(getRelationship(node.getChildNodes()));
                break;
            }
        }
        return new RelationshipsR(relationship);
    }

    private static Relationship getRelationship(NodeList nl) {
        String id = null;
        boolean visible = false;
        RelationshipEnd fromRelationshipEnd = null;
        RelationshipEnd toRelationshipEnd = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("FromRelationshipEnd".equals(node.getNodeName())) {
                    fromRelationshipEnd = getRelationshipEnd(node.getChildNodes());
                }
                if ("ToRelationshipEnd".equals(node.getNodeName())) {
                    toRelationshipEnd = getRelationshipEnd(node.getChildNodes());
                }
            }
        }
        return new RelationshipR(
            id,
            visible,
            fromRelationshipEnd,
            toRelationshipEnd
        );
    }

    private static RelationshipEnd getRelationshipEnd(NodeList nl) {
        String role = null;
        String multiplicity = null;
        String dimensionID = null;
        List<String> attributes = null;
        List<RelationshipEndTranslation> translations = null;
        RelationshipEndVisualizationProperties visualizationProperties = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ROLE2.equals(node.getNodeName())) {
                    role = node.getTextContent();
                }
                if ("Multiplicity".equals(node.getNodeName())) {
                    multiplicity = node.getTextContent();
                }
                if (DIMENSION_ID.equals(node.getNodeName())) {
                    dimensionID = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getRelationshipEndAttributes(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getRelationshipEndTranslations(node.getChildNodes());
                }
                if (VISUALIZATION_PROPERTIES.equals(node.getNodeName())) {
                    visualizationProperties = getRelationshipEndVisualizationProperties(node.getChildNodes());
                }

            }
        }
        return new RelationshipEndR(role,
            multiplicity,
            dimensionID,
            attributes,
            translations,
            visualizationProperties);
    }

    private static RelationshipEndVisualizationProperties getRelationshipEndVisualizationProperties(NodeList nl) {
        BigInteger folderPosition = null;
        String contextualNameRule = null;
        BigInteger defaultDetailsPosition = null;
        BigInteger displayKeyPosition = null;
        BigInteger commonIdentifierPosition = null;
        Boolean isDefaultMeasure = null;
        Boolean isDefaultImage = null;
        BigInteger sortPropertiesPosition = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (FOLDER_POSITION.equals(node.getNodeName())) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
                if (CONTEXTUAL_NAME_RULE.equals(node.getNodeName())) {
                    contextualNameRule = node.getTextContent();
                }
                if (DEFAULT_DETAILS_POSITION.equals(node.getNodeName())) {
                    defaultDetailsPosition = toBigInteger(node.getTextContent());
                }
                if ("DisplayKeyPosition".equals(node.getNodeName())) {
                    displayKeyPosition = toBigInteger(node.getTextContent());
                }
                if ("CommonIdentifierPosition".equals(node.getNodeName())) {
                    commonIdentifierPosition = toBigInteger(node.getTextContent());
                }
                if ("IsDefaultMeasure".equals(node.getNodeName())) {
                    isDefaultMeasure = toBoolean(node.getTextContent());
                }
                if ("IsDefaultImage".equals(node.getNodeName())) {
                    isDefaultImage = toBoolean(node.getTextContent());
                }
                if (SORT_PROPERTIES_POSITION.equals(node.getNodeName())) {
                    sortPropertiesPosition = toBigInteger(node.getTextContent());
                }
            }
        }

        return new RelationshipEndVisualizationPropertiesR(
            folderPosition,
            contextualNameRule,
            defaultDetailsPosition,
            displayKeyPosition,
            commonIdentifierPosition,
            isDefaultMeasure,
            isDefaultImage,
            sortPropertiesPosition
        );
    }

    private static List<RelationshipEndTranslation> getRelationshipEndTranslations(NodeList nl) {
        List<RelationshipEndTranslation> translations = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (TRANSLATION.equals(node.getNodeName()))) {
                translations.add(getRelationshipEndTranslation(node.getChildNodes()));
            }
        }
        return translations;
    }

    private static RelationshipEndTranslation getRelationshipEndTranslation(NodeList nl) {
        long language = 0;
        String caption = null;
        String collectionCaption = null;
        String description = null;
        String displayFolder = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toLong(node.getTextContent());
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("CollectionCaption".equals(node.getNodeName())) {
                    collectionCaption = node.getTextContent();
                }
            }
        }

        return new RelationshipEndTranslationR(
            language,
            caption,
            collectionCaption,
            description,
            displayFolder,
            annotations
        );
    }

    private static List<String> getRelationshipEndAttributes(NodeList nl) {
        List<String> attributes = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE.equals(node.getNodeName()))) {
                    attributes.add(getAttributeId(node.getChildNodes()));
            }
        }
        return attributes;
    }

    private static String getAttributeId(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE_ID.equals(node.getNodeName()))) {
                    return node.getTextContent();
            }
        }
        return null;
    }

    private static DataSourceView getDataSourceView(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String dataSourceID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
            }
        }
        return new DataSourceViewR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            dataSourceID
        );
    }

    private static DataSource getDataSource(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String managedProvider = null;
        String connectionString = null;
        String connectionStringSecurity = null;
        ImpersonationInfo impersonationInfo = null;
        String isolation = null;
        BigInteger maxActiveConnections = null;
        Duration timeout = null;
        List<DataSourcePermission> dataSourcePermissions = null;
        ImpersonationInfo queryImpersonationInfo = null;
        String queryHints = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("ManagedProvider".equals(node.getNodeName())) {
                    managedProvider = node.getTextContent();
                }
                if ("ConnectionString".equals(node.getNodeName())) {
                    connectionString = node.getTextContent();
                }
                if ("ConnectionStringSecurity".equals(node.getNodeName())) {
                    connectionStringSecurity = node.getTextContent();
                }
                if ("ImpersonationInfo".equals(node.getNodeName())) {
                    impersonationInfo = getImpersonationInfo(node.getChildNodes());
                }
                if ("Isolation".equals(node.getNodeName())) {
                    isolation = node.getTextContent();
                }
                if ("MaxActiveConnections".equals(node.getNodeName())) {
                    maxActiveConnections = toBigInteger(node.getTextContent());
                }
                if ("Timeout".equals(node.getNodeName())) {
                    timeout = toDuration(node.getTextContent());
                }
                if ("DataSourcePermissions".equals(node.getNodeName())) {
                    dataSourcePermissions = getDataSourcePermissionList(node.getChildNodes());
                }
                if ("QueryImpersonationInfo".equals(node.getNodeName())) {
                    queryImpersonationInfo = getImpersonationInfo(node.getChildNodes());
                }
                if ("QueryHints".equals(node.getNodeName())) {
                    queryHints = node.getTextContent();
                }
            }
        }
        return new DataSourceR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            managedProvider,
            connectionString,
            connectionStringSecurity,
            impersonationInfo,
            isolation,
            maxActiveConnections,
            timeout,
            dataSourcePermissions,
            queryImpersonationInfo,
            queryHints);
    }

    private static List<DataSourcePermission> getDataSourcePermissionList(NodeList nl) {
        List<DataSourcePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("DataSourcePermission".equals(node.getNodeName()))) {
                list.add(getDataSourcePermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static DataSourcePermission getDataSourcePermission(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }

                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new DataSourcePermissionR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write));
    }

    private static ImpersonationInfo getImpersonationInfo(NodeList nl) {
        String impersonationMode = null;
        String account = null;
        String password = null;
        String impersonationInfoSecurity = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ImpersonationMode".equals(node.getNodeName())) {
                    impersonationMode = node.getTextContent();
                }
                if ("Account".equals(node.getNodeName())) {
                    account = node.getTextContent();
                }
                if ("Password".equals(node.getNodeName())) {
                    password = node.getTextContent();
                }
                if ("ImpersonationInfoSecurity".equals(node.getNodeName())) {
                    impersonationInfoSecurity = node.getTextContent();
                }
            }
        }

        return new ImpersonationInfoR(impersonationMode,
            Optional.ofNullable(account),
            Optional.ofNullable(password),
            Optional.ofNullable(impersonationInfoSecurity));
    }

    private static Database getDatabase(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Instant lastUpdate = null;
        String state = null;
        String readWriteMode = null;
        String dbStorageLocation = null;
        String aggregationPrefix = null;
        BigInteger processingPriority = null;
        Long estimatedSize = null;
        Instant lastProcessed = null;
        BigInteger language = null;
        String collation = null;
        Boolean visible = null;
        String masterDataSourceID = null;
        ImpersonationInfo dataSourceImpersonationInfo = null;
        List<Account> accounts = null;
        List<DataSource> dataSources = null;
        List<DataSourceView> dataSourceViews = null;
        List<Dimension> dimensions = null;
        List<Cube> cubes = null;
        List<MiningStructure> miningStructures = null;
        List<Role> roles = null;
        List<Assembly> assemblies = null;
        List<DatabasePermission> databasePermissions = null;
        List<Translation> translations = null;
        String storageEngineUsed = null;
        String imagePath = null;
        String imageUrl = null;
        String imageUniqueID = null;
        String imageVersion = null;
        String token = null;
        BigInteger compatibilityLevel = null;
        String directQueryMode = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }

                if ("LastUpdate".equals(node.getNodeName())) {
                    lastUpdate = toInstant(node.getTextContent());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("ReadWriteMode".equals(node.getNodeName())) {
                    readWriteMode = node.getTextContent();
                }
                if ("DbStorageLocation".equals(node.getNodeName())) {
                    dbStorageLocation = node.getTextContent();
                }
                if (AGGREGATION_PREFIX.equals(node.getNodeName())) {
                    aggregationPrefix = node.getTextContent();
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (ESTIMATED_SIZE.equals(node.getNodeName())) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("MasterDataSourceID".equals(node.getNodeName())) {
                    masterDataSourceID = node.getTextContent();
                }
                if ("DataSourceImpersonationInfo".equals(node.getNodeName())) {
                    dataSourceImpersonationInfo = getImpersonationInfo(node.getChildNodes());
                }
                if ("Accounts".equals(node.getNodeName())) {
                    accounts = getAccountList(node.getChildNodes());
                }
                if ("DataSources".equals(node.getNodeName())) {
                    dataSources = getDataSourceList(node.getChildNodes());
                }
                if ("DataSourceViews".equals(node.getNodeName())) {
                    dataSourceViews = getDataSourceViewList(node.getChildNodes());
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = getDimensionList(node.getChildNodes());
                }
                if ("Cubes".equals(node.getNodeName())) {
                    cubes = getCubeList(node.getChildNodes());
                }
                if ("MiningStructures".equals(node.getNodeName())) {
                    miningStructures = getMiningStructureList(node.getChildNodes());
                }
                if ("Roles".equals(node.getNodeName())) {
                    roles = getRoleList(node.getChildNodes());
                }
                if ("Assemblies".equals(node.getNodeName())) {
                    assemblies = getAssemblyList(node.getChildNodes());
                }
                if ("DatabasePermissions".equals(node.getNodeName())) {
                    databasePermissions = getDatabasePermissionList(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if ("StorageEngineUsed".equals(node.getNodeName())) {
                    storageEngineUsed = node.getTextContent();
                }
                if ("ImagePath".equals(node.getNodeName())) {
                    imagePath = node.getTextContent();
                }
                if ("ImageUrl".equals(node.getNodeName())) {
                    imageUrl = node.getTextContent();
                }
                if ("ImageUniqueID".equals(node.getNodeName())) {
                    imageUniqueID = node.getTextContent();
                }
                if ("ImageVersion".equals(node.getNodeName())) {
                    imageVersion = node.getTextContent();
                }
                if ("Token".equals(node.getNodeName())) {
                    token = node.getTextContent();
                }
                if ("CompatibilityLevel".equals(node.getNodeName())) {
                    compatibilityLevel = toBigInteger(node.getTextContent());
                }
                if ("DirectQueryMode".equals(node.getNodeName())) {
                    directQueryMode = node.getTextContent();
                }
            }
        }
        return new DatabaseR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            lastUpdate,
            state,
            readWriteMode,
            dbStorageLocation,
            aggregationPrefix,
            processingPriority,
            estimatedSize,
            lastProcessed,
            language,
            collation,
            visible,
            masterDataSourceID,
            dataSourceImpersonationInfo,
            accounts,
            dataSources,
            dataSourceViews,
            dimensions,
            cubes,
            miningStructures,
            roles,
            assemblies,
            databasePermissions,
            translations,
            storageEngineUsed,
            imagePath,
            imageUrl,
            imageUniqueID,
            imageVersion,
            token,
            compatibilityLevel,
            directQueryMode
        );
    }

    private static List<DatabasePermission> getDatabasePermissionList(NodeList nl) {
        List<DatabasePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("DatabasePermission".equals(node.getNodeName()))) {
                list.add(getDatabasePermission(node.getChildNodes()));
            }
        }
        return list;

    }

    private static List<MiningStructure> getMiningStructureList(NodeList nl) {
        List<MiningStructure> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MiningStructure".equals(node.getNodeName()))) {
                list.add(getMiningStructure(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Cube> getCubeList(NodeList nl) {
        List<Cube> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (CUBE2.equals(node.getNodeName()))) {
                    list.add(getCube(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Dimension> getDimensionList(NodeList nl) {
        List<Dimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (DIMENSION.equals(node.getNodeName()))) {
                    list.add(getDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<DataSourceView> getDataSourceViewList(NodeList nl) {
        List<DataSourceView> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("DataSourceView".equals(node.getNodeName()))) {
                list.add(getDataSourceView(node.getChildNodes()));
            }
        }
        return list;

    }

    private static List<DataSource> getDataSourceList(NodeList nl) {
        List<DataSource> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("DataSource".equals(node.getNodeName()))) {
                list.add(getDataSource(node.getChildNodes()));
            }
        }
        return list;

    }

    private static List<Account> getAccountList(NodeList nl) {
        List<Account> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Account".equals(node.getNodeName()))) {
                list.add(getAccount(node.getChildNodes()));
            }
        }
        return list;

    }

    private static Account getAccount(NodeList nl) {
        String accountType = null;
        String aggregationFunction = null;
        List<String> aliases = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("AccountType".equals(node.getNodeName())) {
                    accountType = node.getTextContent();
                }
                if ("AggregationFunction".equals(node.getNodeName())) {
                    aggregationFunction = node.getTextContent();
                }
                if ("Aliases".equals(node.getNodeName())) {
                    aliases = getAliasList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AccountR(accountType,
            aggregationFunction,
            aliases,
            annotations);
    }

    private static List<String> getAliasList(NodeList nl) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Alias".equals(node.getNodeName()))) {
                list.add(node.getTextContent());
            }
        }
        return list;
    }

    private static DatabasePermission getDatabasePermission(NodeList nl) {
        Boolean administer = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if ("Administer".equals(node.getNodeName())) {
                    administer = toBoolean(node.getTextContent());
                }
            }
        }
        return new DatabasePermissionR(
            Optional.ofNullable(administer),
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write)
        );
    }

    private static Cube getCube(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        BigInteger language = null;
        String collation = null;
        List<Translation> translations = null;
        List<CubeDimension> dimensions = null;
        List<CubePermission> cubePermissions = null;
        List<MdxScript> mdxScripts = null;
        List<Perspective> perspectives = null;
        String state = null;
        String defaultMeasure = null;
        Boolean visible = null;
        List<MeasureGroup> measureGroups = null;
        DataSourceViewBinding source = null;
        String aggregationPrefix = null;
        BigInteger processingPriority = null;
        Cube.StorageMode storageMode = null;
        String processingMode = null;
        String scriptCacheProcessingMode = null;
        String scriptErrorHandlingMode = null;
        String daxOptimizationMode = null;
        ProactiveCaching proactiveCaching = null;
        List<Kpi> kpis = null;
        ErrorConfiguration errorConfiguration = null;
        List<Action> actions = null;
        String storageLocation = null;
        Long estimatedRows = null;
        Instant lastProcessed = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = getCubeDimensionList(node.getChildNodes());
                }
                if ("CubePermissions".equals(node.getNodeName())) {
                    cubePermissions = getCubePermissionList(node.getChildNodes());
                }
                if ("MdxScripts".equals(node.getNodeName())) {
                    mdxScripts = getMdxScriptList(node.getChildNodes());
                }
                if ("Perspectives".equals(node.getNodeName())) {
                    perspectives = getPerspectiveList(node.getChildNodes());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("DefaultMeasure".equals(node.getNodeName())) {
                    defaultMeasure = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("MeasureGroups".equals(node.getNodeName())) {
                    measureGroups = getMeasureGroupList(node.getChildNodes());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = getDataSourceViewBinding(node.getChildNodes());
                }
                if (AGGREGATION_PREFIX.equals(node.getNodeName())) {
                    aggregationPrefix = node.getTextContent();
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (STORAGE_MODE.equals(node.getNodeName())) {
                    storageMode = getCubeStorageMode(node.getChildNodes());
                }
                if (PROCESSING_MODE.equals(node.getNodeName())) {
                    processingMode = node.getTextContent();
                }
                if ("ScriptCacheProcessingMode".equals(node.getNodeName())) {
                    scriptCacheProcessingMode = node.getTextContent();
                }
                if ("ScriptErrorHandlingMode".equals(node.getNodeName())) {
                    scriptErrorHandlingMode = node.getTextContent();
                }
                if ("DaxOptimizationMode".equals(node.getNodeName())) {
                    daxOptimizationMode = node.getTextContent();
                }
                if (PROACTIVE_CACHING.equals(node.getNodeName())) {
                    proactiveCaching = getProactiveCaching(node.getChildNodes());
                }
                if ("Kpis".equals(node.getNodeName())) {
                    kpis = getKpiList(node.getChildNodes());
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = getErrorConfiguration(node.getChildNodes());
                }
                if ("Actions".equals(node.getNodeName())) {
                    actions = getActionList(node.getChildNodes());
                }
                if (STORAGE_LOCATION.equals(node.getNodeName())) {
                    storageLocation = node.getTextContent();
                }
                if (ESTIMATED_ROWS.equals(node.getNodeName())) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
            }
        }
        return new CubeR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            language,
            collation,
            translations,
            dimensions,
            cubePermissions,
            mdxScripts,
            perspectives,
            state,
            defaultMeasure,
            visible,
            measureGroups,
            source,
            aggregationPrefix,
            processingPriority,
            storageMode,
            processingMode,
            scriptCacheProcessingMode,
            scriptErrorHandlingMode,
            daxOptimizationMode,
            proactiveCaching,
            kpis,
            errorConfiguration,
            actions,
            storageLocation,
            estimatedRows,
            lastProcessed);
    }

    private static List<Action> getActionList(NodeList nl) {
        List<Action> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ACTION.equals(node.getNodeName()))) {
                list.add(getAction(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    private static List<Kpi> getKpiList(NodeList nl) {
        List<Kpi> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Kpi".equals(node.getNodeName()))) {
                list.add(getKpi(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<MeasureGroup> getMeasureGroupList(NodeList nl) {
        List<MeasureGroup> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (MEASURE_GROUP.equals(node.getNodeName()))) {
                list.add(getMeasureGroup(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<Perspective> getPerspectiveList(NodeList nl) {
        List<Perspective> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Perspective".equals(node.getNodeName()))) {
                list.add(getPerspective(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<MdxScript> getMdxScriptList(NodeList nl) {
        List<MdxScript> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("MdxScript".equals(node.getNodeName()))) {
                list.add(getMdxScript(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<CubePermission> getCubePermissionList(NodeList nl) {
        List<CubePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("CubePermission".equals(node.getNodeName()))) {
                    list.add(getCubePermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<CubeDimension> getCubeDimensionList(NodeList nl) {
        List<CubeDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("CubeDimension".equals(node.getNodeName()))) {
                    list.add(getCubeDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Kpi getKpi(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        List<Translation> translations = null;
        String displayFolder = null;
        String associatedMeasureGroupID = null;
        String value = null;
        String goal = null;
        String status = null;
        String trend = null;
        String weight = null;
        String trendGraphic = null;
        String statusGraphic = null;
        String currentTimeMember = null;
        String parentKpiID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DISPLAY_FOLDER.equals(node.getNodeName())) {
                    displayFolder = node.getTextContent();
                }
                if ("AssociatedMeasureGroupID".equals(node.getNodeName())) {
                    associatedMeasureGroupID = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
                if ("Goal".equals(node.getNodeName())) {
                    goal = node.getTextContent();
                }
                if ("Status".equals(node.getNodeName())) {
                    status = node.getTextContent();
                }
                if ("Trend".equals(node.getNodeName())) {
                    trend = node.getTextContent();
                }
                if ("Weight".equals(node.getNodeName())) {
                    weight = node.getTextContent();
                }
                if ("TrendGraphic".equals(node.getNodeName())) {
                    trendGraphic = node.getTextContent();
                }
                if ("StatusGraphic".equals(node.getNodeName())) {
                    statusGraphic = node.getTextContent();
                }
                if ("CurrentTimeMember".equals(node.getNodeName())) {
                    currentTimeMember = node.getTextContent();
                }
                if ("ParentKpiID".equals(node.getNodeName())) {
                    parentKpiID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new KpiR(
            name,
            id,
            description,
            translations,
            displayFolder,
            associatedMeasureGroupID,
            value,
            goal,
            status,
            trend,
            weight,
            trendGraphic,
            statusGraphic,
            currentTimeMember,
            parentKpiID,
            annotations);
    }

    private static Cube.StorageMode getCubeStorageMode(NodeList nl) {
        CubeStorageModeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = CubeStorageModeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
                break;
            }
        }
        return new CubeR.StorageMode(value,
            valuens);
    }

    private static Action getAction(NodeList nl, String type) {
        if ("StandardAction".equals(type)) {
            return getStandardAction(nl);
        }
        if ("ReportAction".equals(type)) {
            return getReportAction(nl);
        }
        if ("ReportAction".equals(type)) {
            return getDrillThroughAction(nl);
        }
        return null;
    }

    private static Action getDrillThroughAction(NodeList nl) {
        String name = null;
        String id = null;
        String caption = null;
        Boolean captionIsMdx = null;
        List<Translation> translations = null;
        TargetTypeEnum targetType = null;
        String target = null;
        String condition = null;
        TypeEnum type = null;
        String invocation = null;
        String application = null;
        String description = null;
        List<Annotation> annotations = null;
        Boolean defaultAction = null;
        List<Binding> columns = null;
        Integer maximumRows = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (CAPTION_IS_MDX.equals(node.getNodeName())) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (TARGET_TYPE.equals(node.getNodeName())) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (TARGET.equals(node.getNodeName())) {
                    target = node.getTextContent();
                }
                if (CONDITION.equals(node.getNodeName())) {
                    condition = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (INVOCATION_LOW.equals(node.getNodeName())) {
                    invocation = node.getTextContent();
                }
                if (APPLICATION.equals(node.getNodeName())) {
                    application = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }

                if ("Default".equals(node.getNodeName())) {
                    defaultAction = toBoolean(node.getTextContent());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getBindingList(node.getChildNodes());
                }
                if ("MaximumRows".equals(node.getNodeName())) {
                    maximumRows = toInteger(node.getTextContent());
                }
            }
        }
        return new DrillThroughActionR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(caption),
            Optional.ofNullable(captionIsMdx),
            Optional.ofNullable(translations),
            targetType,
            Optional.ofNullable(target),
            Optional.ofNullable(condition),
            type,
            Optional.ofNullable(invocation),
            Optional.ofNullable(application),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            Optional.ofNullable(defaultAction),
            Optional.ofNullable(columns),
            Optional.ofNullable(maximumRows)
        );
    }

    private static List<Binding> getBindingList(NodeList nl) {
        List<Binding> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("CubeDimension".equals(node.getNodeName()))) {
                list.add(getBinding(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    private static Action getReportAction(NodeList nl) {
        String name = null;
        String id = null;
        String caption = null;
        Boolean captionIsMdx = null;
        List<Translation> translations = null;
        TargetTypeEnum targetType = null;
        String target = null;
        String condition = null;
        TypeEnum type = null;
        String invocation = null;
        String application = null;
        String description = null;
        List<Annotation> annotations = null;
        String reportServer = null;
        String path = null;
        List<ReportParameter> reportParameters = null;
        List<ReportFormatParameter> reportFormatParameters = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (CAPTION_IS_MDX.equals(node.getNodeName())) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (TARGET_TYPE.equals(node.getNodeName())) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (TARGET.equals(node.getNodeName())) {
                    target = node.getTextContent();
                }
                if (CONDITION.equals(node.getNodeName())) {
                    condition = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (INVOCATION_LOW.equals(node.getNodeName())) {
                    invocation = node.getTextContent();
                }
                if (APPLICATION.equals(node.getNodeName())) {
                    application = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("ReportServer".equals(node.getNodeName())) {
                    reportServer = node.getTextContent();
                }
                if ("Path".equals(node.getNodeName())) {
                    path = node.getTextContent();
                }
                if ("ReportParameters".equals(node.getNodeName())) {
                    reportParameters = getReportParameterList(node.getChildNodes());
                }
                if ("ReportFormatParameters".equals(node.getNodeName())) {
                    reportFormatParameters = getReportFormatParametersList(node.getChildNodes());
                }
            }
        }
        return new ReportActionR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(caption),
            Optional.ofNullable(captionIsMdx),
            Optional.ofNullable(translations),
            targetType,
            Optional.ofNullable(target),
            Optional.ofNullable(condition),
            type,
            Optional.ofNullable(invocation),
            Optional.ofNullable(application),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            reportServer,
            Optional.ofNullable(path),
            Optional.ofNullable(reportParameters),
            Optional.ofNullable(reportFormatParameters)
        );
    }

    private static List<ReportFormatParameter> getReportFormatParametersList(NodeList nl) {
        List<ReportFormatParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("ReportFormatParameter".equals(node.getNodeName()))) {
                list.add(getReportFormatParameter(node.getChildNodes()));
            }
        }
        return list;
    }

    private static ReportFormatParameter getReportFormatParameter(NodeList nl) {
        String name = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new ReportFormatParameterR(name, value);
    }

    private static List<ReportParameter> getReportParameterList(NodeList nl) {
        List<ReportParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("ReportParameter".equals(node.getNodeName()))) {
                list.add(getReportParameter(node.getChildNodes()));
            }
        }
        return list;
    }

    private static ReportParameter getReportParameter(NodeList nl) {
        String name = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new ReportParameterR(name, value);
    }

    private static Action getStandardAction(NodeList nl) {
        String name = null;
        String id = null;
        String caption = null;
        Boolean captionIsMdx = null;
        List<Translation> translations = null;
        TargetTypeEnum targetType = null;
        String target = null;
        String condition = null;
        TypeEnum type = null;
        String invocation = null;
        String application = null;
        String description = null;
        List<Annotation> annotations = null;
        String expression = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CAPTION.equals(node.getNodeName())) {
                    caption = node.getTextContent();
                }
                if (CAPTION_IS_MDX.equals(node.getNodeName())) {
                    captionIsMdx = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (TARGET_TYPE.equals(node.getNodeName())) {
                    targetType = TargetTypeEnum.fromValue(node.getTextContent());
                }
                if (TARGET.equals(node.getNodeName())) {
                    target = node.getTextContent();
                }
                if (CONDITION.equals(node.getNodeName())) {
                    condition = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = TypeEnum.fromValue(node.getTextContent());
                }
                if (INVOCATION_LOW.equals(node.getNodeName())) {
                    invocation = node.getTextContent();
                }
                if (APPLICATION.equals(node.getNodeName())) {
                    application = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if (EXPRESSION.equals(node.getNodeName())) {
                    expression = node.getTextContent();
                }
            }
        }
        return new StandardActionR(
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(caption),
            Optional.ofNullable(captionIsMdx),
            Optional.ofNullable(translations),
            targetType,
            Optional.ofNullable(target),
            Optional.ofNullable(condition),
            type,
            Optional.ofNullable(invocation),
            Optional.ofNullable(application),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            expression
            );
    }

    private static CubeDimension getCubeDimension(NodeList nl) {
        String id = null;
        String name = null;
        String description = null;
        List<Translation> translations = null;
        String dimensionID = null;
        Boolean visible = null;
        String allMemberAggregationUsage = null;
        String hierarchyUniqueNameStyle = null;
        String memberUniqueNameStyle = null;
        List<CubeAttribute> attributes = null;
        List<CubeHierarchy> hierarchies = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DIMENSION_ID.equals(node.getNodeName())) {
                    dimensionID = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("AllMemberAggregationUsage".equals(node.getNodeName())) {
                    allMemberAggregationUsage = node.getTextContent();
                }
                if ("HierarchyUniqueNameStyle".equals(node.getNodeName())) {
                    hierarchyUniqueNameStyle = node.getTextContent();
                }
                if ("MemberUniqueNameStyle".equals(node.getNodeName())) {
                    memberUniqueNameStyle = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getCubeAttributeList(node.getChildNodes());
                }
                if (HIERARCHIES.equals(node.getNodeName())) {
                    hierarchies = getCubeHierarchyList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeDimensionR(
            id,
            name,
            description,
            translations,
            dimensionID,
            visible,
            allMemberAggregationUsage,
            hierarchyUniqueNameStyle,
            memberUniqueNameStyle,
            attributes,
            hierarchies,
            annotations);
    }

    private static List<CubeHierarchy> getCubeHierarchyList(NodeList nl) {
        List<CubeHierarchy> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (HIERARCHY.equals(node.getNodeName()))) {
                    list.add(getCubeHierarchy(node.getChildNodes()));
            }
        }
        return list;
    }

    private static List<CubeAttribute> getCubeAttributeList(NodeList nl) {
        List<CubeAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (ATTRIBUTE.equals(node.getNodeName()))) {
                list.add(getCubeAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static CubeAttribute getCubeAttribute(NodeList nl) {
        String attributeID = null;
        String aggregationUsage = null;
        String attributeHierarchyOptimizedState = null;
        Boolean attributeHierarchyEnabled = null;
        Boolean attributeHierarchyVisible = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("AggregationUsage".equals(node.getNodeName())) {
                    aggregationUsage = node.getTextContent();
                }
                if ("AttributeHierarchyOptimizedState".equals(node.getNodeName())) {
                    attributeHierarchyOptimizedState = node.getTextContent();
                }
                if ("AttributeHierarchyEnabled".equals(node.getNodeName())) {
                    attributeHierarchyEnabled = toBoolean(node.getTextContent());
                }
                if (ATTRIBUTE_HIERARCHY_VISIBLE.equals(node.getNodeName())) {
                    attributeHierarchyVisible = toBoolean(node.getTextContent());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeAttributeR(
            attributeID,
            aggregationUsage,
            attributeHierarchyOptimizedState,
            attributeHierarchyEnabled,
            attributeHierarchyVisible,
            annotations);
    }

    private static CubeHierarchy getCubeHierarchy(NodeList nl) {
        String hierarchyID = null;
        String optimizedState = null;
        Boolean visible = null;
        Boolean enabled = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("HierarchyID".equals(node.getNodeName())) {
                    hierarchyID = node.getTextContent();
                }
                if ("OptimizedState".equals(node.getNodeName())) {
                    optimizedState = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("Enabled".equals(node.getNodeName())) {
                    enabled = toBoolean(node.getTextContent());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeHierarchyR(
            hierarchyID,
            optimizedState,
            visible,
            enabled,
            annotations);
    }

    private static CubePermission getCubePermission(NodeList nl) {
        String readSourceData = null;
        List<CubeDimensionPermission> dimensionPermissions = null;
        List<CellPermission> cellPermissions = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if ("ReadSourceData".equals(node.getNodeName())) {
                    readSourceData = node.getTextContent();
                }
                if ("DimensionPermissions".equals(node.getNodeName())) {
                    dimensionPermissions = getCubeDimensionPermissionList(node.getChildNodes());
                }
                if ("CellPermissions".equals(node.getNodeName())) {
                    cellPermissions = getCellPermissionList(node.getChildNodes());
                }
            }
        }
        return new CubePermissionR(
            Optional.ofNullable(readSourceData),
            Optional.ofNullable(dimensionPermissions),
            Optional.ofNullable(cellPermissions),
            name,
            Optional.ofNullable(id),
            Optional.ofNullable(createdTimestamp),
            Optional.ofNullable(lastSchemaUpdate),
            Optional.ofNullable(description),
            Optional.ofNullable(annotations),
            roleID,
            Optional.ofNullable(process),
            Optional.ofNullable(readDefinition),
            Optional.ofNullable(read),
            Optional.ofNullable(write)
        );
    }

    private static List<CellPermission> getCellPermissionList(NodeList nl) {
        List<CellPermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("CellPermission".equals(node.getNodeName()))) {
                    list.add(getCellPermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static CellPermission getCellPermission(NodeList nl) {
        AccessEnum access = null;
        String description = null;
        String expression = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Access".equals(node.getNodeName())) {
                    access = AccessEnum.fromValue(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (EXPRESSION.equals(node.getNodeName())) {
                    expression = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }

        return new CellPermissionR(
            Optional.ofNullable(access),
            Optional.ofNullable(description),
            Optional.ofNullable(expression),
            Optional.ofNullable(annotations)
        );
    }

    private static List<CubeDimensionPermission> getCubeDimensionPermissionList(NodeList nl) {
        List<CubeDimensionPermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("DimensionPermission".equals(node.getNodeName()))) {
                list.add(getCubeDimensionPermission(node.getChildNodes()));
            }
        }
        return list;
    }

    private static CubeDimensionPermission getCubeDimensionPermission(NodeList nl) {
        String cubeDimensionID = null;
        String description = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        List<AttributePermission> attributePermissions = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if ("AttributePermissions".equals(node.getNodeName())) {
                    attributePermissions = getAttributePermissionList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }

        return new CubeDimensionPermissionR(
            cubeDimensionID,
            Optional.ofNullable(description),
            Optional.ofNullable(read),
            Optional.ofNullable(write),
            Optional.ofNullable(attributePermissions),
            Optional.ofNullable(annotations));
    }

    private static Assembly getAssembly(NodeList nl) {
        String id = null;
        String name = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        ImpersonationInfo impersonationInfo = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
                if ("ImpersonationInfo".equals(node.getNodeName())) {
                    impersonationInfo = getImpersonationInfo(node.getChildNodes());
                }
            }
        }
        return new AssemblyR(
            id,
            name,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            impersonationInfo);
    }

    private static AggregationDesign getAggregationDesign(NodeList nl) {
        String name = null;
        Optional<String> id = Optional.empty();
        Optional<Instant> createdTimestamp = Optional.empty();
        Optional<Instant> lastSchemaUpdate = Optional.empty();
        Optional<String> description = Optional.empty();
        Optional<List<Annotation>> annotations = Optional.empty();
        Optional<Long> estimatedRows = Optional.empty();
        Optional<List<AggregationDesignDimension>> dimensions = Optional.empty();
        Optional<List<Aggregation>> aggregations = Optional.empty();
        Optional<Integer> estimatedPerformanceGain = Optional.empty();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = Optional.ofNullable(node.getTextContent());
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = Optional.ofNullable(toInstant(node.getTextContent()));
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = Optional.ofNullable(toInstant(node.getTextContent()));
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = Optional.ofNullable(node.getTextContent());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = Optional.ofNullable(getAnnotationList(node.getChildNodes()));
                }
                if (ESTIMATED_ROWS.equals(node.getNodeName())) {
                    estimatedRows = Optional.ofNullable(toLong(node.getTextContent()));
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = Optional.ofNullable(getAggregationDesignDimensionList(node.getChildNodes()));
                }
                if ("Aggregations".equals(node.getNodeName())) {
                    aggregations = Optional.ofNullable(getAggregationList(node.getChildNodes()));
                }
                if ("EstimatedPerformanceGain".equals(node.getNodeName())) {
                    estimatedPerformanceGain = Optional.ofNullable(toInteger(node.getTextContent()));
                }
            }
        }
        return new AggregationDesignR(
            name,
            id,
            createdTimestamp,
            lastSchemaUpdate,
            description,
            annotations,
            estimatedRows,
            dimensions,
            aggregations,
            estimatedPerformanceGain);
    }

    private static List<Annotation> getAnnotationList(NodeList nl) {
        List<Annotation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Aggregation".equals(node.getNodeName()))) {
                list.add(getAnnotation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Annotation getAnnotation(NodeList nl) {
        String name = null;
        Optional<String> visibility = Optional.empty();
        Optional<java.lang.Object> value = Optional.empty();

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Visibility".equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = Optional.ofNullable(node.getTextContent());
                }
            }
        }

        return new AnnotationR(
            name,
            visibility,
            value);
    }

    private static List<Aggregation> getAggregationList(NodeList nl) {
        List<Aggregation> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && ("Aggregation".equals(node.getNodeName()))) {
                list.add(getAggregation(node.getChildNodes()));
            }
        }
        return list;
    }

    private static Aggregation getAggregation(NodeList nl) {
        Optional<String> id = Optional.empty();
        String name = null;
        Optional<List<AggregationDimension>> dimensions = Optional.empty();
        Optional<List<Annotation>> annotations = Optional.empty();
        Optional<String> description = Optional.empty();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ID.equals(node.getNodeName())) {
                    id = Optional.ofNullable(node.getTextContent());
                }
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = Optional.ofNullable(node.getTextContent());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = Optional.ofNullable(getAnnotationList(node.getChildNodes()));
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = Optional.ofNullable(getAggregationDimensionList(node.getChildNodes()));
                }
            }
        }
        return new AggregationR(id, name, dimensions, annotations, description);
    }

    private static List<AggregationDimension> getAggregationDimensionList(NodeList nl) {
        List<AggregationDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null)
                && (DIMENSION.equals(node.getNodeName()))) {
                list.add(getAggregationDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationDimension getAggregationDimension(NodeList nl) {
        String cubeDimensionID = null;
        Optional<List<AggregationAttribute>> attributes = Optional.empty();
        Optional<List<Annotation>> annotations = Optional.empty();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = Optional.ofNullable(getAggregationAttributeList(node.getChildNodes()));
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = Optional.ofNullable(getAnnotationList(node.getChildNodes()));
                }
            }
        }
        return new AggregationDimensionR(cubeDimensionID, attributes, annotations);
    }

    private static List<AggregationAttribute> getAggregationAttributeList(NodeList nl) {
        List<AggregationAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null && ATTRIBUTE.equals(node.getNodeName())) {
                list.add(getAggregationAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationAttribute getAggregationAttribute(NodeList nl) {
        String attributeID = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AggregationAttributeR(attributeID, Optional.ofNullable(annotations));
    }

    private static List<AggregationDesignDimension> getAggregationDesignDimensionList(NodeList nl) {
        List<AggregationDesignDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null && DIMENSION.equals(node.getNodeName())) {
                list.add(getAggregationDesignDimension(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationDesignDimension getAggregationDesignDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<AggregationDesignAttribute> attributes = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTES.equals(node.getNodeName())) {
                    attributes = getAggregationDesignAttributeList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AggregationDesignDimensionR(
            cubeDimensionID,
            Optional.ofNullable(attributes),
            Optional.ofNullable(annotations)
        );
    }

    private static List<AggregationDesignAttribute> getAggregationDesignAttributeList(NodeList nl) {
        List<AggregationDesignAttribute> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null && ATTRIBUTE.equals(node.getNodeName())) {
                list.add(getAggregationDesignAttribute(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AggregationDesignAttribute getAggregationDesignAttribute(NodeList nl) {
        String attributeID = null;
        Long estimatedCount = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("EstimatedCount".equals(node.getNodeName())) {
                    estimatedCount = toLong(node.getTextContent());
                }
            }
        }
        return new AggregationDesignAttributeR(attributeID, Optional.ofNullable(estimatedCount));
    }

    private static Map<String, String> getMapValuesByTag(SOAPElement el, String tagName) {
        NodeList nodeList = el.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return getMapValues(nodeList.item(0).getChildNodes());
        }
        return Map.of();
    }

    private static Map<String, String> getMapValues(NodeList nl) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node n = nl.item(i);
            result.put(n.getNodeName(), n.getTextContent());
        }
        return result;
    }

    private static String getAttribute(NamedNodeMap namedNodeMap, String name) {
        if (namedNodeMap != null) {
            org.w3c.dom.Node nameNode = namedNodeMap.getNamedItem(name);
            if (nameNode != null) {
                return nameNode.getTextContent();
            }
        }
        return null;
    }

    private static String getNodeType(org.w3c.dom.Node node) {
        return getAttribute(node.getAttributes(), XSI_TYPE);
    }

    private static Boolean toBoolean(String it) {
        return it != null ? Boolean.valueOf(it) : null;
    }

    private static Long toLong(String it) {
        return it != null ? Long.valueOf(it) : null;
    }

    private static Integer toInteger(String it) {
        return it != null ? Integer.valueOf(it) : null;
    }

    private static BigInteger toBigInteger(String it) {
        return it != null ? new BigInteger(it) : null;
    }
}
