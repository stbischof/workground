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

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import org.eclipse.daanse.xmla.api.common.enums.ItemTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.ParameterInfo;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MeasureGroupDimension;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.api.engine200.WarningColumn;
import org.eclipse.daanse.xmla.api.engine200.WarningLocationObject;
import org.eclipse.daanse.xmla.api.engine200.WarningMeasure;
import org.eclipse.daanse.xmla.api.exception.ErrorType;
import org.eclipse.daanse.xmla.api.exception.Exception;
import org.eclipse.daanse.xmla.api.exception.MessageLocation;
import org.eclipse.daanse.xmla.api.exception.Messages;
import org.eclipse.daanse.xmla.api.exception.StartEnd;
import org.eclipse.daanse.xmla.api.exception.WarningType;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.mddataset.Axes;
import org.eclipse.daanse.xmla.api.mddataset.AxesInfo;
import org.eclipse.daanse.xmla.api.mddataset.Axis;
import org.eclipse.daanse.xmla.api.mddataset.AxisInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellData;
import org.eclipse.daanse.xmla.api.mddataset.CellInfo;
import org.eclipse.daanse.xmla.api.mddataset.CellInfoItem;
import org.eclipse.daanse.xmla.api.mddataset.CellSetType;
import org.eclipse.daanse.xmla.api.mddataset.CellType;
import org.eclipse.daanse.xmla.api.mddataset.CellTypeError;
import org.eclipse.daanse.xmla.api.mddataset.CubeInfo;
import org.eclipse.daanse.xmla.api.mddataset.HierarchyInfo;
import org.eclipse.daanse.xmla.api.mddataset.Mddataset;
import org.eclipse.daanse.xmla.api.mddataset.MemberType;
import org.eclipse.daanse.xmla.api.mddataset.MembersType;
import org.eclipse.daanse.xmla.api.mddataset.NormTupleSet;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfo;
import org.eclipse.daanse.xmla.api.mddataset.OlapInfoCube;
import org.eclipse.daanse.xmla.api.mddataset.RowSet;
import org.eclipse.daanse.xmla.api.mddataset.RowSetRow;
import org.eclipse.daanse.xmla.api.mddataset.RowSetRowItem;
import org.eclipse.daanse.xmla.api.mddataset.SetListType;
import org.eclipse.daanse.xmla.api.mddataset.TupleType;
import org.eclipse.daanse.xmla.api.mddataset.TuplesType;
import org.eclipse.daanse.xmla.api.mddataset.Type;
import org.eclipse.daanse.xmla.api.mddataset.Union;
import org.eclipse.daanse.xmla.api.mddataset.Value;
import org.eclipse.daanse.xmla.api.msxmla.MemberRef;
import org.eclipse.daanse.xmla.api.msxmla.MembersLookup;
import org.eclipse.daanse.xmla.api.msxmla.NormTuple;
import org.eclipse.daanse.xmla.api.msxmla.NormTuplesType;
import org.eclipse.daanse.xmla.api.xmla.Restriction;
import org.eclipse.daanse.xmla.api.xmla_empty.Emptyresult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CATALOG_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_TYPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION_UC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_IS_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EXPRESSION_UC;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHY_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LEVEL_UNIQUE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASUREGROUP_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NUMERIC_PRECISION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NUMERIC_SCALE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROW;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SCHEMA_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SCOPE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TABLE_CATALOG;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TABLE_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TABLE_SCHEMA;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TABLE_TYPE;

public class SoapUtil {

	private static final String MSXMLA = "msxmla";
	private static final String EMPTY = "empty";
    private static final String ROWSET = "rowset";
    private static final String MDDATASET = "mddataset";
	private static final Logger LOGGER = LoggerFactory.getLogger(SoapUtil.class);

    private SoapUtil() {
        //constructor
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static void toMdSchemaFunctions(List<MdSchemaFunctionsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaFunctionsRoot(body);
        rows.forEach(r ->
            addMdSchemaFunctionsResponseRow(root, r)
        );
    }

    public static void toMdSchemaDimensions(List<MdSchemaDimensionsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaDimensionsRoot(body);
        rows.forEach(r ->
            addMdSchemaDimensionsResponseRow(root, r)
        );
    }

    public static void toDiscoverProperties(List<DiscoverPropertiesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverPropertiesRoot(body);
        rows.forEach(r ->
            addDiscoverPropertiesResponseRow(root, r)
        );
    }

    public static void toMdSchemaCubes(List<MdSchemaCubesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaCubesRoot(body);
        rows.forEach(r ->
            addMdSchemaCubesResponseRow(root, r)
        );
    }

    public static void toMdSchemaMeasureGroups(List<MdSchemaMeasureGroupsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaMeasureGroupsRoot(body);
        rows.forEach(r ->
            addMdSchemaMeasureGroupsResponseRow(root, r)
        );
    }

    public static void toMdSchemaKpis(List<MdSchemaKpisResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverPropertiesRoot(body);
        rows.forEach(r ->
            addMdSchemaKpisResponseRow(root, r)
        );
    }

    public static void toMdSchemaSets(List<MdSchemaSetsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverPropertiesRoot(body);
        rows.forEach(r ->
            addMdSchemaSetsResponseRow(root, r)
        );
    }
    public static void toMdSchemaProperties(List<MdSchemaPropertiesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaPropertiesRoot(body);
        rows.forEach(r ->
            addMdSchemaPropertiesResponseRow(root, r)
        );
    }

    public static void toMdSchemaMembers(List<MdSchemaMembersResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaMembersRoot(body);
        rows.forEach(r ->
            addMdSchemaMembersResponseRow(root, r)
        );
    }

    public static void toMdSchemaMeasures(List<MdSchemaMeasuresResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaMeasuresRoot(body);
        rows.forEach(r ->
            addMdSchemaMeasuresResponseRow(root, r)
        );
    }

    public static void toMdSchemaMeasureGroupDimensions(List<MdSchemaMeasureGroupDimensionsResponseRow> rows
        , SOAPBody body) {
        SOAPElement root = addMdSchemaMeasureGroupDimensionsRoot(body);
        rows.forEach(r ->
            addMdSchemaMeasureGroupDimensionsResponseRow(root, r)
        );
    }

    public static void toMdSchemaLevels(List<MdSchemaLevelsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaLevelsRoot(body);
        rows.forEach(r ->
            addMdSchemaLevelsResponseRow(root, r)
        );
    }

    public static void toMdSchemaHierarchies(List<MdSchemaHierarchiesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaHierarchiesRoot(body);
        rows.forEach(r ->
            addMdSchemaHierarchiesResponseRow(root, r)
        );
    }

    public static void toDbSchemaTablesInfo(List<DbSchemaTablesInfoResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaTablesInfoRoot(body);
        rows.forEach(r ->
            addDbSchemaTablesInfoResponseRow(root, r)
        );
    }

    public static void toDbSchemaSourceTables(List<DbSchemaSourceTablesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaSourceTablesRoot(body);
        rows.forEach(r ->
            addDbSchemaSourceTablesResponseRow(root, r)
        );
    }

    public static void toDbSchemaSchemata(List<DbSchemaSchemataResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaSchemataRoot(body);
        rows.forEach(r ->
            addDbSchemaSchemataResponseRow(root, r)
        );
    }

    public static void toDbSchemaProviderTypes(List<DbSchemaProviderTypesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaProviderTypesRoot(body);
        rows.forEach(r ->
            addDbSchemaProviderTypesResponseRow(root, r)
        );
    }

    public static void toDbSchemaColumns(List<DbSchemaColumnsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaColumnsRoot(body);
        rows.forEach(r ->
            addDbSchemaColumnsResponseRow(root, r)
        );
    }

    public static void toDiscoverXmlMetaData(List<DiscoverXmlMetaDataResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverXmlMetaDataRoot(body);
        rows.forEach(r ->
            addDiscoverXmlMetaDataResponseRow(root, r)
        );
    }

    public static void toDiscoverDataSources(List<DiscoverDataSourcesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverDataSourcesRoot(body);
        rows.forEach(r ->
            addDiscoverDataSourcesResponseRow(root, r)
        );
    }

    public static void toDbSchemaCatalogs(List<DbSchemaCatalogsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaCatalogsRoot(body);
        rows.forEach(r ->
            addDbSchemaCatalogsResponseRow(root, r)
        );
    }

    public static void toDiscoverSchemaRowsets(List<DiscoverSchemaRowsetsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverRowSetsRoot(body);
        rows.forEach(r ->
            addDiscoverSchemaRowsetsResponseRow(root, r)
        );
    }

    public static void toDiscoverEnumerators(List<DiscoverEnumeratorsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverEnumeratorsRoot(body);
        rows.forEach(r ->
            addDiscoverEnumeratorsResponseRow(root, r)
        );
    }

    public static void toDiscoverKeywords(List<DiscoverKeywordsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverKeywordsRoot(body);
        rows.forEach(r ->
            addDiscoverKeywordsResponseRow(root, r)
        );
    }

    public static void toDiscoverLiterals(List<DiscoverLiteralsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDiscoverLiteralsRoot(body);
        rows.forEach(r ->
            addDiscoverLiteralsResponseRow(root, r)
        );
    }

    public static void toDbSchemaTables(List<DbSchemaTablesResponseRow> rows, SOAPBody body) {
        SOAPElement root = addDbSchemaTablesRoot(body);
        rows.forEach(r ->
            addDbSchemaTablesResponseRow(root, r)
        );

    }

    public static void toMdSchemaActions(List<MdSchemaActionsResponseRow> rows, SOAPBody body) {
        SOAPElement root = addMdSchemaActionsRoot(body);
        rows.forEach(r ->
            addMdSchemaActionsResponseRow(root, r)
        );
    }

    public static void toStatementResponse(StatementResponse statementResponse, SOAPBody body) {
        if (statementResponse.mdDataSet() != null) {
            SOAPElement root = addMddatasetRoot(body);
            if (statementResponse != null) {
                addMdDataSet(root, statementResponse.mdDataSet());
            }
        }
        if (statementResponse.rowSet() != null) {
            SOAPElement root = addRowSetRoot(body, statementResponse.rowSet());
            if (statementResponse.rowSet().rowSetRows() != null) {
                statementResponse.rowSet().rowSetRows().forEach(it -> addRowSetRow(root, it));
            }
        }
        if (statementResponse.mdDataSet() == null && statementResponse.rowSet() == null) {
            addEmptyRoot(body);
        }
    }


    private static void addRowSetRow(SOAPElement e, RowSetRow it) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(e, ROW, prefix);
        if (it.rowSetRowItem() != null) {
            it.rowSetRowItem().forEach(i -> addRowSetRowItem(row, i));
        }
    }

    private static void addRowSetRowItem(SOAPElement e, RowSetRowItem it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, it.tagName(), ROWSET);
            el.setTextContent(it.value());
            it.type().ifPresent(v -> setAttribute(el, "type", v.getValue()));
        }
    }

    public static void toAlterResponse(AlterResponse alterResponse, SOAPBody body) {
        SOAPElement root = addEmptyRoot(body);
        if (alterResponse != null) {
            addEmptyresult(root, alterResponse.emptyresult());
        }
    }

    public static void toClearCacheResponse(ClearCacheResponse clearCacheResponse, SOAPBody body) {
        SOAPElement root = addEmptyRoot(body);
        if (clearCacheResponse != null) {
            addEmptyresult(root, clearCacheResponse.emptyresult());
        }
    }

    public static void toCancelResponse(CancelResponse cancelResponse, SOAPBody body) {
        SOAPElement root = addEmptyRoot(body);
        if (cancelResponse != null) {
            addEmptyresult(root, cancelResponse.emptyresult());
        }
    }

    private static void addEmptyresult(SOAPElement root, Emptyresult emptyresult) {
        addException(root, emptyresult.exception());
        addMessages(root, emptyresult.messages());
    }

    private static void addMdSchemaActionsResponseRow(SOAPElement root, MdSchemaActionsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        addChildElement(row, CUBE_NAME, prefix, r.cubeName());
        r.actionName().ifPresent(v -> addChildElement(row, "ACTION_NAME", prefix, v));
        r.actionType().ifPresent(v -> addChildElement(row, "ACTION_TYPE", prefix, String.valueOf(v.getValue())));
        addChildElement(row, "COORDINATE", prefix, r.coordinate());
        addChildElement(row, "COORDINATE_TYPE", prefix, String.valueOf(r.coordinateType().getValue()));
        r.actionCaption().ifPresent(v -> addChildElement(row, "ACTION_CAPTION", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.content().ifPresent(v -> addChildElement(row, "CONTENT", prefix, v));
        r.application().ifPresent(v -> addChildElement(row, "APPLICATION", prefix, v));
        r.invocation().ifPresent(v -> addChildElement(row, "INVOCATION", prefix, String.valueOf(v.getValue())));
    }

    private static void addDbSchemaTablesResponseRow(SOAPElement root, DbSchemaTablesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.tableCatalog().ifPresent(v -> addChildElement(row, TABLE_CATALOG, prefix, v));
        r.tableSchema().ifPresent(v -> addChildElement(row, TABLE_SCHEMA, prefix, v));
        r.tableName().ifPresent(v -> addChildElement(row, TABLE_NAME, prefix, v));
        r.tableType().ifPresent(v -> addChildElement(row, TABLE_TYPE, prefix, v));
        r.tableGuid().ifPresent(v -> addChildElement(row, "TABLE_GUID", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.tablePropId().ifPresent(v -> addChildElement(row, "TABLE_PROP_ID", prefix, String.valueOf(v)));
        r.dateCreated().ifPresent(v -> addChildElement(row, "DATE_CREATED", prefix, String.valueOf(v)));
        r.dateModified().ifPresent(v -> addChildElement(row, "DATE_MODIFIED", prefix, String.valueOf(v)));
    }

    private static void addDiscoverLiteralsResponseRow(SOAPElement root, DiscoverLiteralsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "LiteralName", prefix, r.literalName());
        addChildElement(row, "LiteralValue", prefix, r.literalValue());
        addChildElement(row, "LiteralInvalidChars", prefix, r.literalInvalidChars());
        addChildElement(row, "LiteralInvalidStartingChars", prefix, r.literalInvalidStartingChars());
        addChildElement(row, "LiteralMaxLength", prefix, String.valueOf(r.literalMaxLength()));
        addChildElement(row, "LiteralNameEnumValue", prefix, String.valueOf(r.literalNameEnumValue().getValue()));
    }

    private static void addDiscoverKeywordsResponseRow(SOAPElement root, DiscoverKeywordsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "Keyword", prefix, r.keyword());
    }

    private static void addDiscoverEnumeratorsResponseRow(SOAPElement root, DiscoverEnumeratorsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "EnumName", prefix, r.enumName());
        r.enumDescription().ifPresent(v -> addChildElement(row, "EnumDescription", prefix, v));
        addChildElement(row, "EnumType", prefix, r.enumType());
        addChildElement(row, "ElementName", prefix, r.elementName());
        r.elementDescription().ifPresent(v -> addChildElement(row, "ElementDescription", prefix, v));
        r.elementValue().ifPresent(v -> addChildElement(row, "ElementValue", prefix, v));
    }

    private static void addDiscoverSchemaRowsetsResponseRow(SOAPElement root, DiscoverSchemaRowsetsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "SchemaName", prefix, r.schemaName());
        r.schemaGuid().ifPresent(v -> addChildElement(row, "SchemaGuid", prefix, v));
        r.restrictions().ifPresent(v -> addRestrictionList(row, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION, prefix, v));
        r.restrictionsMask().ifPresent(v -> addChildElement(row, "RestrictionsMask", prefix, String.valueOf(v)));
    }

    private static void addRestrictionList(SOAPElement el, List<Restriction> list) {
        if (list != null) {
            list.forEach(it -> addRestriction(el, it));
        }
    }

    private static void addRestriction(SOAPElement e, Restriction it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Restrictions", ROWSET);
            SOAPElement name = addChildElement(el, "Name", ROWSET);
            name.setTextContent(it.name());
            SOAPElement type = addChildElement(el, "Type", ROWSET);
            type.setTextContent(it.type());
        }
    }

    private static void addDbSchemaCatalogsResponseRow(SOAPElement root, DbSchemaCatalogsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.roles().ifPresent(v -> addChildElement(row, "ROLES", prefix, v));
        r.dateModified().ifPresent(v -> addChildElement(row, "DATE_MODIFIED", prefix, v.format(formatter)));
        r.compatibilityLevel().ifPresent(v -> addChildElement(row, "COMPATIBILITY_LEVEL", prefix, String.valueOf(v)));
        r.type().ifPresent(v -> addChildElement(row, "TYPE", prefix, String.valueOf(v.getValue())));
        r.version().ifPresent(v -> addChildElement(row, "VERSION", prefix, String.valueOf(v)));
        r.databaseId().ifPresent(v -> addChildElement(row, "DATABASE_ID", prefix, v));
        r.dateQueried().ifPresent(v -> addChildElement(row, "DATE_QUERIED", prefix, String.valueOf(v)));
        r.currentlyUsed().ifPresent(v -> addChildElement(row, "CURRENTLY_USED", prefix, String.valueOf(v)));
        r.popularity().ifPresent(v -> addChildElement(row, "POPULARITY", prefix, String.valueOf(v)));
        r.weightedPopularity().ifPresent(v -> addChildElement(row, "WEIGHTEDPOPULARITY", prefix, String.valueOf(v)));
        r.clientCacheRefreshPolicy().ifPresent(v -> addChildElement(row, "CLIENTCACHEREFRESHPOLICY",
            prefix,
            String.valueOf(v.getValue())));
    }

    private static void addDiscoverDataSourcesResponseRow(SOAPElement root, DiscoverDataSourcesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "DataSourceName", prefix, r.dataSourceName());
        r.dataSourceDescription().ifPresent(v -> addChildElement(row, "DataSourceDescription", prefix, v));
        r.url().ifPresent(v -> addChildElement(row, "URL", prefix, v));
        r.dataSourceInfo().ifPresent(v -> addChildElement(row, "DataSourceInfo", prefix, v));
        addChildElement(row, "ProviderName", prefix, r.providerName());
        r.providerType().ifPresent(v -> addChildElement(row, "ProviderType", prefix, v.name()));
        r.authenticationMode().ifPresent(v -> addChildElement(row, "AuthenticationMode", prefix, v.getValue()));
    }

    private static void addDiscoverXmlMetaDataResponseRow(SOAPElement root, DiscoverXmlMetaDataResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "MetaData", prefix, r.metaData());
    }

    private static void addDbSchemaColumnsResponseRow(SOAPElement root, DbSchemaColumnsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.tableCatalog().ifPresent(v -> addChildElement(row, TABLE_CATALOG, prefix, v));
        r.tableSchema().ifPresent(v -> addChildElement(row, TABLE_SCHEMA, prefix, v));
        r.tableName().ifPresent(v -> addChildElement(row, TABLE_NAME, prefix, v));
        r.columnName().ifPresent(v -> addChildElement(row, "COLUMN_NAME", prefix, v));
        r.columnGuid().ifPresent(v -> addChildElement(row, "COLUMN_GUID", prefix, String.valueOf(v)));
        r.columnPropId().ifPresent(v -> addChildElement(row, "COLUMN_PROPID", prefix, String.valueOf(v)));
        r.ordinalPosition().ifPresent(v -> addChildElement(row, "ORDINAL_POSITION", prefix, String.valueOf(v)));
        r.columnHasDefault().ifPresent(v -> addChildElement(row, "COLUMN_HAS_DEFAULT", prefix, String.valueOf(v)));
        r.columnDefault().ifPresent(v -> addChildElement(row, "COLUMN_DEFAULT", prefix, v));
        r.columnFlags().ifPresent(v -> addChildElement(row, "COLUMN_FLAG", prefix, String.valueOf(v.getValue())));
        r.isNullable().ifPresent(v -> addChildElement(row, "IS_NULLABLE", prefix, String.valueOf(v)));
        r.dataType().ifPresent(v -> addChildElement(row, DATA_TYPE, prefix, String.valueOf(v)));
        r.typeGuid().ifPresent(v -> addChildElement(row, "TYPE_GUID", prefix, String.valueOf(v)));
        r.characterMaximum().ifPresent(v -> addChildElement(row, "CHARACTER_MAXIMUM_LENGTH", prefix, String.valueOf(v)));
        r.characterOctetLength().ifPresent(v -> addChildElement(row, "CHARACTER_OCTET_LENGTH", prefix, String.valueOf(v)));
        r.numericPrecision().ifPresent(v -> addChildElement(row, NUMERIC_PRECISION, prefix, String.valueOf(v)));
        r.numericScale().ifPresent(v -> addChildElement(row, NUMERIC_SCALE, prefix, String.valueOf(v)));
        r.dateTimePrecision().ifPresent(v -> addChildElement(row, "DATETIME_PRECISION", prefix, String.valueOf(v)));
        r.characterSetCatalog().ifPresent(v -> addChildElement(row, "CHARACTER_SET_CATALOG", prefix, v));
        r.characterSetSchema().ifPresent(v -> addChildElement(row, "CHARACTER_SET_SCHEMA", prefix, v));
        r.characterSetName().ifPresent(v -> addChildElement(row, "CHARACTER_SET_NAME", prefix, v));
        r.collationCatalog().ifPresent(v -> addChildElement(row, "COLLATION_CATALOG", prefix, v));
        r.collationSchema().ifPresent(v -> addChildElement(row, "COLLATION_SCHEMA", prefix, v));
        r.collationName().ifPresent(v -> addChildElement(row, "COLLATION_NAME", prefix, v));
        r.domainCatalog().ifPresent(v -> addChildElement(row, "DOMAIN_CATALOG", prefix, v));
        r.domainSchema().ifPresent(v -> addChildElement(row, "DOMAIN_SCHEMA", prefix, v));
        r.domainName().ifPresent(v -> addChildElement(row, "DOMAIN_NAME", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.columnOlapType().ifPresent(v -> addChildElement(row, "COLUMN_OLAP_TYPE", prefix, v.name()));
    }

    private static void addDbSchemaProviderTypesResponseRow(SOAPElement root, DbSchemaProviderTypesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.typeName().ifPresent(v -> addChildElement(row, "TYPE_NAME", prefix, v));
        r.dataType().ifPresent(v -> addChildElement(row, DATA_TYPE, prefix, String.valueOf(v.getValue())));
        r.columnSize().ifPresent(v -> addChildElement(row, "COLUMN_SIZE", prefix, String.valueOf(v)));
        r.literalPrefix().ifPresent(v -> addChildElement(row, "LITERAL_PREFIX", prefix, v));
        r.literalSuffix().ifPresent(v -> addChildElement(row, "LITERAL_SUFFIX", prefix, v));
        r.createParams().ifPresent(v -> addChildElement(row, "CREATE_PARAMS", prefix, v));
        r.isNullable().ifPresent(v -> addChildElement(row, "IS_NULLABLE", prefix, String.valueOf(v)));
        r.caseSensitive().ifPresent(v -> addChildElement(row, "CASE_SENSITIVE", prefix, String.valueOf(v)));
        r.searchable().ifPresent(v -> addChildElement(row, "SEARCHABLE", prefix, String.valueOf(v.getValue())));
        r.unsignedAttribute().ifPresent(v -> addChildElement(row, "UNSIGNED_ATTRIBUTE", prefix, String.valueOf(v)));
        r.fixedPrecScale().ifPresent(v -> addChildElement(row, "FIXED_PREC_SCALE", prefix, String.valueOf(v)));
        r.autoUniqueValue().ifPresent(v -> addChildElement(row, "AUTO_UNIQUE_VALUE", prefix, String.valueOf(v)));
        r.localTypeName().ifPresent(v -> addChildElement(row, "LOCAL_TYPE_NAME", prefix, v));
        r.minimumScale().ifPresent(v -> addChildElement(row, "MINIMUM_SCALE", prefix, String.valueOf(v)));
        r.maximumScale().ifPresent(v -> addChildElement(row, "MAXIMUM_SCALE", prefix, String.valueOf(v)));
        r.guid().ifPresent(v -> addChildElement(row, "GUID", prefix, String.valueOf(v)));
        r.typeLib().ifPresent(v -> addChildElement(row, "TYPE_LIB", prefix, v));
        r.version().ifPresent(v -> addChildElement(row, "VERSION", prefix, v));
        r.isLong().ifPresent(v -> addChildElement(row, "IS_LONG", prefix, String.valueOf(v)));
        r.bestMatch().ifPresent(v -> addChildElement(row, "BEST_MATCH", prefix, String.valueOf(v)));
        r.isFixedLength().ifPresent(v -> addChildElement(row, "IS_FIXEDLENGTH", prefix, String.valueOf(v)));
    }

    private static void addDbSchemaSchemataResponseRow(SOAPElement root, DbSchemaSchemataResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, CATALOG_NAME, prefix, r.catalogName());
        addChildElement(row, SCHEMA_NAME, prefix, r.schemaName());
        addChildElement(row, "SCHEMA_OWNER", prefix, r.schemaOwner());
    }

    private static void addDbSchemaSourceTablesResponseRow(SOAPElement root, DbSchemaSourceTablesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, TABLE_CATALOG, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, TABLE_SCHEMA, prefix, v));
        addChildElement(row, TABLE_NAME, prefix, r.tableName());
        addChildElement(row, TABLE_TYPE, prefix, r.tableType().getValue());
    }

    private static void addDbSchemaTablesInfoResponseRow(SOAPElement root, DbSchemaTablesInfoResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, TABLE_CATALOG, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, TABLE_SCHEMA, prefix, v));
        addChildElement(row, TABLE_NAME, prefix, r.tableName());
        addChildElement(row, TABLE_TYPE, prefix, r.tableType());
        r.tableGuid().ifPresent(v -> addChildElement(row, "TABLE_GUID", prefix, String.valueOf(v)));
        r.bookmarks().ifPresent(v -> addChildElement(row, "BOOKMARKS", prefix, String.valueOf(v)));
        r.bookmarkType().ifPresent(v -> addChildElement(row, "BOOKMARK_TYPE", prefix, String.valueOf(v)));
        r.bookmarkDataType().ifPresent(v -> addChildElement(row, "BOOKMARK_DATA_TYPE", prefix, String.valueOf(v)));
        r.bookmarkMaximumLength().ifPresent(v -> addChildElement(row, "BOOKMARK_MAXIMUM_LENGTH", prefix, String.valueOf(v)));
        r.bookmarkInformation().ifPresent(v -> addChildElement(row, "BOOKMARK_INFORMATION", prefix, String.valueOf(v)));
        r.tableVersion().ifPresent(v -> addChildElement(row, "TABLE_VERSION", prefix, String.valueOf(v)));
        r.cardinality().ifPresent(v -> addChildElement(row, "CARDINALITY", prefix, String.valueOf(v)));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.tablePropId().ifPresent(v -> addChildElement(row, "TABLE_PROP_ID", prefix, String.valueOf(v)));
    }

    private static void addMdSchemaHierarchiesResponseRow(SOAPElement root, MdSchemaHierarchiesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, DIMENSION_UNIQUE_NAME, prefix, v));
        r.hierarchyName().ifPresent(v -> addChildElement(row, "HIERARCHY_NAME", prefix, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, HIERARCHY_UNIQUE_NAME, prefix, v));
        r.hierarchyGuid().ifPresent(v -> addChildElement(row, "HIERARCHY_GUID", prefix, String.valueOf(v)));
        r.hierarchyCaption().ifPresent(v -> addChildElement(row, "HIERARCHY_CAPTION", prefix, v));
        r.dimensionType().ifPresent(v -> addChildElement(row, "DIMENSION_TYPE", prefix, String.valueOf(v.getValue())));
        r.hierarchyCardinality().ifPresent(v -> addChildElement(row, "HIERARCHY_CARDINALITY", prefix, String.valueOf(v)));
        r.defaultMember().ifPresent(v -> addChildElement(row, "DEFAULT_MEMBER", prefix, v));
        r.allMember().ifPresent(v -> addChildElement(row, "ALL_MEMBER", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.structure().ifPresent(v -> addChildElement(row, "STRUCTURE", prefix, String.valueOf(v.getValue())));
        r.isVirtual().ifPresent(v -> addChildElement(row, "IS_VIRTUAL", prefix, String.valueOf(v)));
        r.isReadWrite().ifPresent(v -> addChildElement(row, "IS_READWRITE", prefix, String.valueOf(v)));
        r.dimensionUniqueSettings().ifPresent(v -> addChildElement(row, "DIMENSION_UNIQUE_SETTINGS",
            prefix,
            String.valueOf(v.getValue())));
        r.dimensionMasterUniqueName().ifPresent(v -> addChildElement(row, "DIMENSION_MASTER_UNIQUE_NAME", prefix, v));
        r.dimensionIsVisible().ifPresent(v -> addChildElement(row, DIMENSION_IS_VISIBLE, prefix, String.valueOf(v)));
        r.hierarchyOrdinal().ifPresent(v -> addChildElement(row, "HIERARCHY_ORDINAL", prefix, String.valueOf(v)));
        r.dimensionIsShared().ifPresent(v -> addChildElement(row, "DIMENSION_IS_SHARED", prefix, String.valueOf(v)));
        r.hierarchyIsVisible().ifPresent(v -> addChildElement(row, "HIERARCHY_IS_VISIBLE", prefix, String.valueOf(v)));
        r.hierarchyOrigin().ifPresent(v -> addChildElement(row, "HIERARCHY_ORIGIN", prefix, String.valueOf(v.getValue())));
        r.hierarchyDisplayFolder().ifPresent(v -> addChildElement(row, "HIERARCHY_DISPLAY_FOLDER", prefix, v));
        r.instanceSelection().ifPresent(v -> addChildElement(row, "INSTANCE_SELECTION", prefix, String.valueOf(v.getValue())));
        r.groupingBehavior().ifPresent(v -> addChildElement(row, "GROUPING_BEHAVIOR", prefix, String.valueOf(v.getValue())));
        r.structureType().ifPresent(v -> addChildElement(row, "STRUCTURE_TYPE", prefix, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaLevelsResponseRow(SOAPElement root, MdSchemaLevelsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);

        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, DIMENSION_UNIQUE_NAME, prefix, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, HIERARCHY_UNIQUE_NAME, prefix, v));
        r.levelName().ifPresent(v -> addChildElement(row, "LEVEL_NAME", prefix, v));
        r.levelUniqueName().ifPresent(v -> addChildElement(row, LEVEL_UNIQUE_NAME, prefix, v));
        r.levelGuid().ifPresent(v -> addChildElement(row, "LEVEL_GUID", prefix, String.valueOf(v)));
        r.levelCaption().ifPresent(v -> addChildElement(row, "LEVEL_CAPTION", prefix, v));
        r.levelNumber().ifPresent(v -> addChildElement(row, "LEVEL_NUMBER", prefix, String.valueOf(v)));
        r.levelCardinality().ifPresent(v -> addChildElement(row, "LEVEL_CARDINALITY", prefix, String.valueOf(v)));
        r.levelType().ifPresent(v -> addChildElement(row, "LEVEL_TYPE", prefix, String.valueOf(v.getValue())));
        r.customRollupSetting().ifPresent(v -> addChildElement(row, "CUSTOM_ROLLUP_SETTINGS",
            prefix, String.valueOf(v.getValue())));
        r.levelUniqueSettings().ifPresent(v -> addChildElement(row, "LEVEL_UNIQUE_SETTINGS",
            prefix, String.valueOf(v.getValue())));
        r.levelIsVisible().ifPresent(v -> addChildElement(row, "LEVEL_IS_VISIBLE", prefix, String.valueOf(v)));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        //absent in old mondrian
        //r.levelOrderingProperty().ifPresent(v -> addChildElement(row, "LEVEL_ORDERING_PROPERTY", prefix, v));
        //r.levelDbType().ifPresent(v -> addChildElement(row, "LEVEL_DBTYPE", prefix, String.valueOf(v.getValue())));
        //r.levelMasterUniqueName().ifPresent(v -> addChildElement(row, "LEVEL_MASTER_UNIQUE_NAME", prefix, v));
        //r.levelNameSqlColumnName().ifPresent(v -> addChildElement(row, "LEVEL_NAME_SQL_COLUMN_NAME", prefix, v));
        //r.levelKeySqlColumnName().ifPresent(v -> addChildElement(row, "LEVEL_KEY_SQL_COLUMN_NAME", prefix, v));
        //r.levelUniqueNameSqlColumnName().ifPresent(v -> addChildElement(row, "LEVEL_UNIQUE_NAME_SQL_COLUMN_NAME", prefix, v));
        //r.levelAttributeHierarchyName().ifPresent(v -> addChildElement(row, "LEVEL_ATTRIBUTE_HIERARCHY_NAME", prefix, v));
        //r.levelKeyCardinality().ifPresent(v -> addChildElement(row, "LEVEL_KEY_CARDINALITY", prefix, String.valueOf(v)));
        r.levelOrigin().ifPresent(v -> addChildElement(row, "LEVEL_ORIGIN", prefix, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaMeasureGroupDimensionsResponseRow(
        SOAPElement root,
        MdSchemaMeasureGroupDimensionsResponseRow r
    ) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.measureGroupName().ifPresent(v -> addChildElement(row, MEASUREGROUP_NAME, prefix, v));
        r.measureGroupCardinality().ifPresent(v -> addChildElement(row, "MEASUREGROUP_CARDINALITY", prefix, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, DIMENSION_UNIQUE_NAME, prefix, v));
        r.dimensionCardinality().ifPresent(v -> addChildElement(row, "DIMENSION_CARDINALITY", prefix, v.name()));
        r.dimensionIsVisible().ifPresent(v -> addChildElement(row, DIMENSION_IS_VISIBLE, prefix, String.valueOf(v)));
        r.dimensionIsFactDimension().ifPresent(v -> addChildElement(row, "DIMENSION_IS_FACT_DIMENSION", prefix,
            String.valueOf(v)));
        r.dimensionPath().ifPresent(v -> addMeasureGroupDimensionXmlList(row, v));
        r.dimensionGranularity().ifPresent(v -> addChildElement(row, "DIMENSION_GRANULARITY", ROWSET, v));
    }

    private static void addMeasureGroupDimensionXmlList(SOAPElement el, List<MeasureGroupDimension> list) {
        if (list != null) {
            SOAPElement e = addChildElement(el, "DIMENSION_PATH", ROWSET);
            list.forEach(it -> addMeasureGroupDimensionXml(e, it));
        }
    }

    private static void addMeasureGroupDimensionXml(SOAPElement el, MeasureGroupDimension it) {
        addChildElement(el, "MeasureGroupDimension", ROWSET, it.measureGroupDimension());
    }

    private static void addMdSchemaMeasuresResponseRow(SOAPElement root, MdSchemaMeasuresResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));
        r.measureName().ifPresent(v -> addChildElement(row, "MEASURE_NAME", prefix, v));
        r.measureUniqueName().ifPresent(v -> addChildElement(row, "MEASURE_UNIQUE_NAME", prefix, v));
        r.measureCaption().ifPresent(v -> addChildElement(row, "MEASURE_CAPTION", prefix, v));
        r.measureGuid().ifPresent(v -> addChildElement(row, "MEASURE_GUID", prefix, String.valueOf(v)));
        r.measureAggregator().ifPresent(v -> addChildElement(row, "MEASURE_AGGREGATOR", prefix, String.valueOf(v.getValue())));
        r.dataType().ifPresent(v -> addChildElement(row, DATA_TYPE, prefix, String.valueOf(v.getValue())));
        r.numericPrecision().ifPresent(v -> addChildElement(row, NUMERIC_PRECISION, prefix, String.valueOf(v)));
        r.numericScale().ifPresent(v -> addChildElement(row, NUMERIC_SCALE, prefix, String.valueOf(v)));
        r.measureUnits().ifPresent(v -> addChildElement(row, "MEASURE_UNITS", prefix, v));
        r.expression().ifPresent(v -> addChildElement(row, EXPRESSION_UC, prefix, v));
        r.measureIsVisible().ifPresent(v -> addChildElement(row, "MEASURE_IS_VISIBLE", prefix, String.valueOf(v)));
        r.levelsList().ifPresent(v -> addChildElement(row, "LEVELS_LIST", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.measureNameSqlColumnName().ifPresent(v -> addChildElement(row, "MEASURE_NAME_SQL_COLUMN_NAME", prefix, v));
        r.measureUnqualifiedCaption().ifPresent(v -> addChildElement(row, "MEASURE_UNQUALIFIED_CAPTION", prefix, v));
        r.measureGroupName().ifPresent(v -> addChildElement(row, MEASUREGROUP_NAME, prefix, v));
        r.measureDisplayFolder().ifPresent(v -> addChildElement(row, "MEASURE_DISPLAY_FOLDER", prefix, v));
        r.defaultFormatString().ifPresent(v -> addChildElement(row, "DEFAULT_FORMAT_STRING", prefix, v));
        r.cubeSource().ifPresent(v -> addChildElement(row, "CUBE_SOURCE", prefix, String.valueOf(v.getValue())));
        r.measureVisibility().ifPresent(v -> addChildElement(row, "MEASURE_VISIBILITY", prefix, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaMembersResponseRow(SOAPElement root, MdSchemaMembersResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);

        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, DIMENSION_UNIQUE_NAME, prefix, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, HIERARCHY_UNIQUE_NAME, prefix, v));
        r.levelUniqueName().ifPresent(v -> addChildElement(row, LEVEL_UNIQUE_NAME, prefix, v));
        r.levelNumber().ifPresent(v -> addChildElement(row, "LEVEL_NUMBER", prefix, String.valueOf(v)));
        r.memberOrdinal().ifPresent(v -> addChildElement(row, "MEMBER_ORDINAL", prefix, String.valueOf(v)));
        r.memberName().ifPresent(v -> addChildElement(row, "MEMBER_NAME", prefix, v));
        r.memberUniqueName().ifPresent(v -> addChildElement(row, "MEMBER_UNIQUE_NAME", prefix, v));
        r.memberType().ifPresent(v -> addChildElement(row, "MEMBER_TYPE", prefix, String.valueOf(v.getValue())));
        r.memberGuid().ifPresent(v -> addChildElement(row, "MEMBER_GUID", prefix, String.valueOf(v)));
        r.memberCaption().ifPresent(v -> addChildElement(row, "MEMBER_CAPTION", prefix, v));
        r.childrenCardinality().ifPresent(v -> addChildElement(row, "CHILDREN_CARDINALITY", prefix, String.valueOf(v)));
        r.parentLevel().ifPresent(v -> addChildElement(row, "PARENT_LEVEL", prefix, String.valueOf(v)));
        r.parentUniqueName().ifPresent(v -> addChildElement(row, "PARENT_UNIQUE_NAME", prefix, v));
        r.parentCount().ifPresent(v -> addChildElement(row, "PARENT_COUNT", prefix, String.valueOf(v)));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.expression().ifPresent(v -> addChildElement(row, EXPRESSION_UC, prefix, v));
        r.memberKey().ifPresent(v -> addChildElement(row, "MEMBER_KEY", prefix, v));
        r.isPlaceHolderMember().ifPresent(v -> addChildElement(row, "IS_PLACEHOLDERMEMBER", prefix, String.valueOf(v)));
        r.isDataMember().ifPresent(v -> addChildElement(row, "IS_DATAMEMBER", prefix, String.valueOf(v)));
        r.scope().ifPresent(v -> addChildElement(row, SCOPE, prefix, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaPropertiesResponseRow(SOAPElement root, MdSchemaPropertiesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, DIMENSION_UNIQUE_NAME, prefix, v));
        r.hierarchyUniqueName().ifPresent(v -> addChildElement(row, HIERARCHY_UNIQUE_NAME, prefix, v));
        r.levelUniqueName().ifPresent(v -> addChildElement(row, LEVEL_UNIQUE_NAME, prefix, v));
        r.memberUniqueName().ifPresent(v -> addChildElement(row, "MEMBER_UNIQUE_NAME", prefix, v));
        r.propertyType().ifPresent(v -> addChildElement(row, "PROPERTY_TYPE",
            prefix, String.valueOf(v.getValue())));
        r.propertyName().ifPresent(v -> addChildElement(row, "PROPERTY_NAME", prefix, v));
        r.propertyCaption().ifPresent(v -> addChildElement(row, "PROPERTY_CAPTION", prefix, v));
        r.dataType().ifPresent(v -> addChildElement(row, DATA_TYPE,
            prefix, String.valueOf(v.getValue())));
        //r.characterMaximumLength().ifPresent(v -> addChildElement(row, "CHARACTER_MAXIMUM_LENGTH"
        //    , prefix, String.valueOf(v)));
        //r.characterOctetLength().ifPresent(v -> addChildElement(row, "CHARACTER_OCTET_LENGTH",
        //    prefix, String.valueOf(v)));
        //r.numericPrecision().ifPresent(v -> addChildElement(row, NUMERIC_PRECISION
        //    , prefix, String.valueOf(v)));
        //r.numericScale().ifPresent(v -> addChildElement(row, NUMERIC_SCALE, prefix, String.valueOf(v)));
        r.propertyContentType().ifPresent(v -> addChildElement(row, "PROPERTY_CONTENT_TYPE", prefix,
            String.valueOf(v.getValue())));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.sqlColumnName().ifPresent(v -> addChildElement(row, "SQL_COLUMN_NAME", prefix, v));
        //r.language().ifPresent(v -> addChildElement(row, "LANGUAGE", prefix, String.valueOf(v)));
        r.propertyOrigin().ifPresent(v -> addChildElement(row, "PROPERTY_ORIGIN"
            , prefix, String.valueOf(v.getValue())));
        //r.propertyAttributeHierarchyName().ifPresent(v -> addChildElement(row
        //    , "PROPERTY_ATTRIBUTE_HIERARCHY_NAME", prefix, v));
        //r.propertyCardinality().ifPresent(v -> addChildElement(row, "PROPERTY_CARDINALITY"
        //    , prefix, v.name()));
        //r.mimeType().ifPresent(v -> addChildElement(row, "MIME_TYPE"
        //    , prefix, v));
        r.cubeSource().ifPresent(v -> addChildElement(row, "CUBE_SOURCE", prefix, String.valueOf(v.getValue())));
        r.propertyIsVisible().ifPresent(v -> addChildElement(row, "PROPERTY_VISIBILITY",
            prefix, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaSetsResponseRow(SOAPElement root, MdSchemaSetsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.setName().ifPresent(v -> addChildElement(row, "SET_NAME", prefix, v));
        r.scope().ifPresent(v -> addChildElement(row, SCOPE, prefix, String.valueOf(v.getValue())));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.expression().ifPresent(v -> addChildElement(row, EXPRESSION_UC, prefix, v));
        r.dimension().ifPresent(v -> addChildElement(row, "DIMENSIONS", prefix, v));
        r.setCaption().ifPresent(v -> addChildElement(row, "SET_CAPTION", prefix, v));
        r.setDisplayFolder().ifPresent(v -> addChildElement(row, "SET_DISPLAY_FOLDER", prefix, v));
        r.setEvaluationContext().ifPresent(v -> addChildElement(row, "SET_EVALUATION_CONTEXT", prefix,
            String.valueOf(v.getValue())));
    }

    private static void addMdSchemaKpisResponseRow(SOAPElement root, MdSchemaKpisResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.measureGroupName().ifPresent(v -> addChildElement(row, MEASUREGROUP_NAME, prefix, v));
        r.kpiName().ifPresent(v -> addChildElement(row, "KPI_NAME", prefix, v));
        r.kpiCaption().ifPresent(v -> addChildElement(row, "KPI_CAPTION", prefix, v));
        r.kpiDescription().ifPresent(v -> addChildElement(row, "KPI_DESCRIPTION", prefix, v));
        r.kpiDisplayFolder().ifPresent(v -> addChildElement(row, "KPI_DISPLAY_FOLDER", prefix, v));
        r.kpiValue().ifPresent(v -> addChildElement(row, "KPI_VALUE", prefix, v));
        r.kpiGoal().ifPresent(v -> addChildElement(row, "KPI_GOAL", prefix, v));
        r.kpiStatus().ifPresent(v -> addChildElement(row, "KPI_STATUS", prefix, v));
        r.kpiTrend().ifPresent(v -> addChildElement(row, "KPI_TREND", prefix, v));
        r.kpiStatusGraphic().ifPresent(v -> addChildElement(row, "KPI_STATUS_GRAPHIC", prefix, v));
        r.kpiTrendGraphic().ifPresent(v -> addChildElement(row, "KPI_TREND_GRAPHIC", prefix, v));
        r.kpiWight().ifPresent(v -> addChildElement(row, "KPI_WEIGHT", prefix, v));
        r.kpiCurrentTimeMember().ifPresent(v -> addChildElement(row, "KPI_CURRENT_TIME_MEMBER", prefix, v));
        r.kpiParentKpiName().ifPresent(v -> addChildElement(row, "KPI_PARENT_KPI_NAME", prefix, v));
        r.annotation().ifPresent(v -> addChildElement(row, "ANNOTATIONS", prefix, v));
        r.scope().ifPresent(v -> addChildElement(row, SCOPE,
            prefix, String.valueOf(v.getValue())));
    }

    private static void addMdSchemaMeasureGroupsResponseRow(SOAPElement root, MdSchemaMeasureGroupsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.measureGroupName().ifPresent(v -> addChildElement(row, MEASUREGROUP_NAME, prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.isWriteEnabled().ifPresent(v -> addChildElement(row, "IS_WRITE_ENABLED", prefix, String.valueOf(v)));
        r.measureGroupCaption().ifPresent(v -> addChildElement(row, "MEASUREGROUP_CAPTION", prefix, v));
    }

    private static void addMdSchemaCubesResponseRow(SOAPElement root, MdSchemaCubesResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, CATALOG_NAME, prefix, r.catalogName());
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.cubeType().ifPresent(v -> addChildElement(row, "CUBE_TYPE", prefix, v.name()));
        r.cubeGuid().ifPresent(v -> addChildElement(row, "CUBE_GUID", prefix, String.valueOf(v)));
        r.createdOn().ifPresent(v -> addChildElement(row, "CREATED_ON", prefix, String.valueOf(v)));
        r.lastSchemaUpdate().ifPresent(v -> addChildElement(row, "LAST_SCHEMA_UPDATE", prefix, String.valueOf(v)));
        r.schemaUpdatedBy().ifPresent(v -> addChildElement(row, "SCHEMA_UPDATED_BY", prefix, v));
        r.lastDataUpdate().ifPresent(v -> addChildElement(row, "LAST_DATA_UPDATE", prefix, String.valueOf(v)));
        r.dataUpdateDBy().ifPresent(v -> addChildElement(row, "DATA_UPDATED_BY", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.isDrillThroughEnabled().ifPresent(v -> addChildElement(row, "IS_DRILLTHROUGH_ENABLED", prefix, String.valueOf(v)));
        r.isLinkable().ifPresent(v -> addChildElement(row, "IS_LINKABLE", prefix, String.valueOf(v)));
        r.isWriteEnabled().ifPresent(v -> addChildElement(row, "IS_WRITE_ENABLED", prefix, String.valueOf(v)));
        r.isSqlEnabled().ifPresent(v -> addChildElement(row, "IS_SQL_ENABLED", prefix, String.valueOf(v)));
        r.cubeCaption().ifPresent(v -> addChildElement(row, "CUBE_CAPTION", prefix, v));
        r.baseCubeName().ifPresent(v -> addChildElement(row, "BASE_CUBE_NAME", prefix, v));
        r.cubeSource().ifPresent(v -> addChildElement(row, "CUBE_SOURCE", prefix, String.valueOf(v.getValue())));
        r.preferredQueryPatterns().ifPresent(v -> addChildElement(row, "PREFERRED_QUERY_PATTERNS", prefix,
            String.valueOf(v.getValue())));
    }

    private static void addMdSchemaDimensionsResponseRow(SOAPElement root, MdSchemaDimensionsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.catalogName().ifPresent(v -> addChildElement(row, CATALOG_NAME, prefix, v));
        r.schemaName().ifPresent(v -> addChildElement(row, SCHEMA_NAME, prefix, v));
        r.cubeName().ifPresent(v -> addChildElement(row, CUBE_NAME, prefix, v));

        r.dimensionName().ifPresent(v -> addChildElement(row, "DIMENSION_NAME", prefix, v));
        r.dimensionUniqueName().ifPresent(v -> addChildElement(row, DIMENSION_UNIQUE_NAME, prefix, v));
        r.dimensionGuid().ifPresent(v -> addChildElement(row, "DIMENSION_GUID", prefix,
            String.valueOf(v)));
        r.dimensionCaption().ifPresent(v -> addChildElement(row, "DIMENSION_CAPTION", prefix, v));
        r.dimensionOptional().ifPresent(v -> addChildElement(row, "DIMENSION_ORDINAL", prefix,
            String.valueOf(v)));
        r.dimensionType().ifPresent(v -> addChildElement(row, "DIMENSION_TYPE", prefix,
            String.valueOf(v.getValue())));
        r.dimensionCardinality().ifPresent(v -> addChildElement(row, "DIMENSION_CARDINALITY", prefix,
            String.valueOf(v)));
        r.defaultHierarchy().ifPresent(v -> addChildElement(row, "DEFAULT_HIERARCHY", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        r.isVirtual().ifPresent(v -> addChildElement(row, "IS_VIRTUAL", prefix, String.valueOf(v)));
        r.isReadWrite().ifPresent(v -> addChildElement(row, "IS_READWRITE", prefix, String.valueOf(v)));
        r.dimensionUniqueSetting().ifPresent(v -> addChildElement(row, "DIMENSION_UNIQUE_SETTINGS",
            prefix, String.valueOf(v.getValue())));
        r.dimensionMasterName().ifPresent(v -> addChildElement(row, "DIMENSION_MASTER_NAME", prefix, v));
        r.dimensionIsVisible().ifPresent(v -> addChildElement(row, DIMENSION_IS_VISIBLE, prefix,
            String.valueOf(v)));
    }

    private static void addMdSchemaFunctionsResponseRow(SOAPElement root, MdSchemaFunctionsResponseRow r) {
        String prefix = ROWSET;
        SOAPElement row = addChildElement(root, ROW, prefix);
        r.functionalName().ifPresent(v -> addChildElement(row, "FUNCTION_NAME", prefix, v));
        r.description().ifPresent(v -> addChildElement(row, DESCRIPTION_UC, prefix, v));
        addChildElement(row, "PARAMETER_LIST", prefix, r.parameterList());
        r.returnType().ifPresent(v -> addChildElement(row, "RETURN_TYPE", prefix, String.valueOf(v)));
        r.origin().ifPresent(v -> addChildElement(row, "ORIGIN", prefix, String.valueOf(v.getValue())));
        r.interfaceName().ifPresent(v -> addChildElement(row, "INTERFACE_NAME", prefix, v.name()));
        r.libraryName().ifPresent(v -> addChildElement(row, "LIBRARY_NAME", prefix, v));
        r.dllName().ifPresent(v -> addChildElement(row, "DLL_NAME", prefix, v));
        r.helpFile().ifPresent(v -> addChildElement(row, "HELP_FILE", prefix, v));
        r.helpContext().ifPresent(v -> addChildElement(row, "HELP_CONTEXT", prefix, v));
        r.object().ifPresent(v -> addChildElement(row, "OBJECT", prefix, v));
        r.caption().ifPresent(v -> addChildElement(row, "CAPTION", prefix, v));
        r.parameterInfo().ifPresent(v -> addParameterInfoXmlList(row, v));
        r.directQueryPushable().ifPresent(v -> addChildElement(row, "DIRECTQUERY_PUSHABLE",
        		prefix, String.valueOf(v.getValue())));
    }

    private static void addParameterInfoXmlList(SOAPElement root, List<ParameterInfo> list) {
        if (list != null) {
            list.forEach(it -> addParameterInfoXml(root, it));
        }
    }

    private static void addParameterInfoXml(SOAPElement root, ParameterInfo it) {
        String prefix = ROWSET;
        SOAPElement el = addChildElement(root, "PARAMETERINFO", prefix);
        addChildElement(el, "NAME", prefix, it.name());
        addChildElement(el, DESCRIPTION_UC, prefix, it.description());
        addChildElement(el, "OPTIONAL", prefix, String.valueOf(it.optional()));
        addChildElement(el, "REPEATABLE", prefix, String.valueOf(it.repeatable()));
        addChildElement(el, "REPEATGROUP", prefix, String.valueOf(it.repeatGroup()));
    }

    private static void addDiscoverPropertiesResponseRow(SOAPElement root, DiscoverPropertiesResponseRow r) {
        String prefix = ROWSET;
        //String prefix = null;
        SOAPElement row = addChildElement(root, ROW, prefix);
        addChildElement(row, "PropertyName", prefix, r.propertyName());
        r.propertyDescription().ifPresent(v -> addChildElement(row, "PropertyDescription", prefix, v));
        r.propertyType().ifPresent(v -> addChildElement(row, "PropertyType", prefix, v));
        addChildElement(row, "PropertyAccessType", prefix, r.propertyAccessType().getValue());
        r.required().ifPresent(v -> addChildElement(row, "IsRequired", prefix, String.valueOf(v)));
        r.value().ifPresent(v -> addChildElement(row, "Value", prefix, v));
    }

    private static void addMdDataSet(SOAPElement el, Mddataset it) {
        if (it != null) {
            addOlapInfo(el, it.olapInfo());
            addAxes(el, it.axes());
            addCellData(el, it.cellData());
            addException(el, it.exception());
            addMessages(el, it.messages());
        }
    }

    private static void addMessages(SOAPElement e, Messages it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Messages", prefix);
            addExceptionTypeList(el, it.warningOrError());

        }
    }

    private static void addExceptionTypeList(
        SOAPElement e,
        List<org.eclipse.daanse.xmla.api.exception.Type> list) {
        if (list != null) {
            list.forEach(it -> addExceptionType(e, it));
        }
    }

    private static void addExceptionType(SOAPElement e, org.eclipse.daanse.xmla.api.exception.Type it) {
        if (it != null) {
            if (it instanceof WarningType warningType) {
                addWarningType(e, warningType);
            }
            if (it instanceof ErrorType errorType) {
                addErrorType(e, errorType);
            }
        }
    }

    private static void addErrorType(SOAPElement e, ErrorType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Error", null);

            addChildElement(el, "Callstack", null, it.callstack());
            addChildElement(el, "ErrorCode", null, String.valueOf(it.errorCode()));
            addMessageLocation(el, it.location());
            setAttribute(el, DESCRIPTION, it.description());
            setAttribute(el, "Source", it.source());
            setAttribute(el, "HelpFile", it.helpFile());
        }
    }

    private static void setAttribute(SOAPElement el, String name, String value) {
        if (value != null) {
        	el.setAttribute(name, value);
        }
    }

    private static void addWarningType(SOAPElement e, WarningType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Warning", null);
            addChildElement(el, "WarningCode", null, String.valueOf(it.warningCode()));
            addMessageLocation(el, it.location());
            addChildElement(el, DESCRIPTION, null, it.description());
            addChildElement(el, "Source", null,  it.source());
            addChildElement(el, "HelpFile", null,  it.helpFile());
        }
    }

    private static void addMessageLocation(SOAPElement e, MessageLocation it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "Location", null);
            addStartEnd(el, "Start", it.start());
            addStartEnd(el, "End", it.end());
            addChildElement(el, "LineOffset", null, String.valueOf(it.lineOffset()));
            addChildElement(el, "TextLength", null, String.valueOf(it.textLength()));
            addWarningLocationObject(el, "SourceObject", it.sourceObject());
            addWarningLocationObject(el, "DependsOnObject", it.dependsOnObject());
            addChildElement(el, "RowNumber", null, String.valueOf(it.rowNumber()));
        }

    }

    private static void addWarningLocationObject(SOAPElement e, String tagName, WarningLocationObject it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, tagName, null);
            addWarningColumn(el, it.warningColumn());
            addWarningMeasure(el, it.warningMeasure());
        }
    }

    private static void addWarningMeasure(SOAPElement e, WarningMeasure it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "WarningMeasure", "engine200");
            addChildElement(el, "Cube", null, it.cube());
            addChildElement(el, "MeasureGroup", null, it.measureGroup());
            addChildElement(el, "MeasureName", null, it.measureName());
        }
    }

    private static void addWarningColumn(SOAPElement e, WarningColumn it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "WarningColumn", "engine200");
            addChildElement(el, "Dimension", null, it.dimension());
            addChildElement(el, "Attribute", null, it.attribute());
        }
    }

    private static void addStartEnd(SOAPElement e, String tagName, StartEnd it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, tagName, null);
            addChildElement(el, "Line", null, String.valueOf(it.line()));
            addChildElement(el, "Column", null, String.valueOf(it.column()));
        }
    }


    private static void addException(SOAPElement e, Exception it) {
        if (it != null) {
            addChildElement(e, "Exception", null);
        }
    }

    private static void addCellData(SOAPElement e, CellData it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "CellData", prefix);
            addCellTypeList(el, it.cell());
            //addCellSetType(el, it.cellSet());
        }
    }

    private static void addCellSetType(SOAPElement e, CellSetType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "CellSet", ROWSET);
            addDataList(el, it.data());
        }
    }

    private static void addDataList(SOAPElement el, List<byte[]> list) {
        if (list != null) {
            list.forEach(it -> addData(el, it));
        }
    }

    private static void addData(SOAPElement e, byte[] it) {
        if (it != null) {
            addChildElement(e, "Data", new String(it, UTF_8));
        }
    }

    private static void addCellTypeList(SOAPElement el, List<CellType> list) {
        if (list != null) {
            list.forEach(it -> addCellType(el, it));
        }
    }

    private static void addCellType(SOAPElement e, CellType it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Cell", prefix);
            addCellTypeValue(el, it.value());
            addCellInfoItemListName(el, it.any());
            setAttribute(el, "CellOrdinal", String.valueOf(it.cellOrdinal()));
        }
    }

    private static void addCellTypeValue(SOAPElement e, Value it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Value", prefix);
            el.setAttribute("xsi:type", it.type().getValue());
            el.setTextContent(it.value());
            addCellTypeErrorList(el, it.error());
        }
    }

    private static void addCellTypeErrorList(SOAPElement el, List<CellTypeError> list) {
        if (list != null) {
            list.forEach(it -> addCellTypeError(el, it));
        }
    }

    private static void addCellTypeError(SOAPElement e, CellTypeError it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Error", prefix);
            setAttribute(el, "ErrorCode", String.valueOf(it.errorCode()));
            setAttribute(el, DESCRIPTION, it.description());
        }
    }

    private static void addAxes(SOAPElement e, Axes it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Axes", prefix);
            addAxisList(el, it.axis());
        }
    }

    private static void addAxisList(SOAPElement e, List<Axis> list) {
        if (list != null) {
            list.forEach(it -> addAxis(e, it));
        }
    }

    private static void addAxis(SOAPElement e, Axis it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Axis", prefix);
            addTypeList(el, it.setType());
            setAttribute(el, "name", it.name());
        }
    }

    private static void addTypeList(SOAPElement e, List<Type> list) {
        if (list != null) {
            list.forEach(it -> addType(e, it));
        }
    }

    private static void addType(SOAPElement e, Type it) {
        if (it != null) {
            if (it instanceof MembersType membersType) {
                addMembersType(e, membersType);
            }
            if (it instanceof TuplesType tuplesType) {
                addTuplesType(e, tuplesType);
            }
            if (it instanceof SetListType setListType) {
                addSetListType(e, setListType);
            }
            if (it instanceof NormTupleSet normTupleSet) {
                addNormTupleSet(e, normTupleSet);
            }
            if (it instanceof Union union) {
                addUnion(e, union);
            }
        }
    }

    private static void addUnion(SOAPElement e, Union it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Union", prefix);
            addTypeList(el, it.setType());
        }
    }

    private static void addNormTupleSet(SOAPElement e, NormTupleSet it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "NormTupleSet", prefix);
            addNormTuplesType(el, it.normTuples());
            addTupleTypeList(el, it.membersLookup());
        }
    }

    private static void addTupleTypeList(SOAPElement e, MembersLookup list) {
        if (list != null) {
            SOAPElement el = addChildElement(e, "MembersLookup", ROWSET);
            if (list.members() != null) {
                list.members().forEach(it -> addTupleType(el, "Members", it));
            }
        }
    }

    private static void addNormTuplesType(SOAPElement e, NormTuplesType it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "NormTuples", ROWSET);
            addNormTupleList(el, it.normTuple());
        }
    }

    private static void addNormTupleList(SOAPElement el, List<NormTuple> list) {
        if (list  != null) {
            list.forEach(it -> addNormTuple(el, it));
        }
    }

    private static void addNormTuple(SOAPElement e, NormTuple it) {
        if (it != null) {
            SOAPElement el = addChildElement(e, "NormTuple", ROWSET);
            addMemberRefList(el, it.memberRef());
        }
    }

    private static void addMemberRefList(SOAPElement e, List<MemberRef> list) {
        if (list  != null) {
            list.forEach(it -> addMemberRef(e, it));
        }
    }

    private static void addMemberRef(SOAPElement e, MemberRef it) {
        if (it != null) {
            String prefix = ROWSET;
            SOAPElement el = addChildElement(e, "MemberRef", prefix);
            addChildElement(el, "MemberOrdinal", prefix, String.valueOf(it.memberOrdinal()));
            addChildElement(el, "MemberDispInfo", prefix, String.valueOf(it.memberDispInfo()));
        }
    }

    private static void addSetListType(SOAPElement e, SetListType it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "CrossProduct", prefix);
            addTypeList(el, it.setType());
            addChildElement(el, "Size", prefix, String.valueOf(it.size()));
        }
    }

    private static void addTuplesType(SOAPElement e, TuplesType it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Tuples", prefix);
            addTuplesTypeList(el, it.tuple());
        }

    }

    private static void addTuplesTypeList(SOAPElement e, List<TupleType> list) {
        if (list != null) {
            list.forEach(it -> addTupleType(e, "Tuple", it));
        }
    }

    private static void addTupleType(SOAPElement e, String tagName, TupleType it) {
        if (it != null) {
            addMemberTypeList(e, tagName, it.member());
        }

    }

    private static void addMemberTypeList(SOAPElement e, String tagName, List<MemberType> list) {
        if (list != null) {
            list.forEach(it -> addMemberType(e, tagName, it));
        }
    }

    private static void addMemberType(SOAPElement e, String tagName, MemberType it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, tagName, prefix);
            SOAPElement el1 = addChildElement(el, "Member", prefix);
            addCellInfoItemList(el1, it.any());
            setAttribute(el, "Hierarchy", it.hierarchy());
        }
    }

    private static void addMembersType(SOAPElement e, MembersType it) {
        if (it != null) {
            addMemberTypeList(e, "Members", it.member());
            setAttribute(e, "Hierarchy", it.hierarchy());
        }
    }

    private static void addOlapInfo(SOAPElement e, OlapInfo it) {
        String prefix = MDDATASET;
        if (it != null) {
            SOAPElement el = addChildElement(e, "OlapInfo", prefix);
            addCubeInfo(el, it.cubeInfo());
            addAxesInfo(el, it.axesInfo());
            addCellInfo(el, it.cellInfo());
        }
    }

    private static void addCellInfo(SOAPElement e, CellInfo it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "CellInfo", prefix);
            addCellInfoItemListName(el, it.any());
        }
    }

    private static void addCellInfoItemListName(SOAPElement e, List<CellInfoItem> list) {
        if (list != null) {
            list.forEach(it -> addCellInfoItemName(e, it));
        }
    }

    private static void addCellInfoItemList(SOAPElement e, List<CellInfoItem> list) {
        if (list != null) {
            list.forEach(it -> addCellInfoItem(e, it));
        }
    }

    private static void addCellInfoItemName(SOAPElement e, CellInfoItem it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, it.tagName(), prefix);
            setAttribute(el,"name", it.name());
            it.type().ifPresent(v -> setAttribute(el, "type", v));
        }
    }

    private static void addCellInfoItem(SOAPElement e, CellInfoItem it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, it.tagName(), prefix);
            el.setTextContent(it.name());
            it.type().ifPresent(v -> setAttribute(el, "type", v));
        }
    }

    private static void addAxesInfo(SOAPElement e, AxesInfo it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "AxesInfo", prefix);
            addAxisInfoList(el, it.axisInfo());
        }
    }

    private static void addAxisInfoList(SOAPElement el, List<AxisInfo> list) {
        if (list != null) {
            list.forEach(it -> addAxisInfo(el, it));
        }
    }

    private static void addAxisInfo(SOAPElement e, AxisInfo it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "AxisInfo", prefix);
            setAttribute(el, "name", it.name());
            addHierarchyInfoList(el, it.hierarchyInfo());
        }
    }

    private static void addHierarchyInfoList(SOAPElement el, List<HierarchyInfo> list) {
        if (list != null) {
            list.forEach(it -> addHierarchyInfo(el, it));
        }
    }

    private static void addHierarchyInfo(SOAPElement e, HierarchyInfo it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "HierarchyInfo", prefix);
            addCellInfoItemListName(el, it.any());
            setAttribute(el, "name", it.name());
        }
    }

    private static void addCubeInfo(SOAPElement e, CubeInfo it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "CubeInfo", prefix);
            addOlapInfoCubeList(el, it.cube());
        }
    }

    private static void addOlapInfoCubeList(SOAPElement e, List<OlapInfoCube> list) {
        if (list != null) {
            list.forEach(it -> addOlapInfoCube(e, it));
        }
    }

    private static void addOlapInfoCube(SOAPElement e, OlapInfoCube it) {
        if (it != null) {
            String prefix = MDDATASET;
            SOAPElement el = addChildElement(e, "Cube", prefix);
            addChildElement(el, "CubeName", prefix, it.cubeName());
            addChildElement(el, "LastDataUpdate", "engine", instantToString(it.lastDataUpdate()));
            addChildElement(el, "LastSchemaUpdate", "engine", instantToString(it.lastSchemaUpdate()));
        }
    }

    private static String instantToString(Instant instant) {
        return instant != null ? formatter.format(instant) : null;
    }
    private static SOAPElement addDiscoverRowSetsRoot(SOAPElement body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:EX", "urn:schemas-microsoft-com:xml-analysis:exception");
        SOAPElement schema  = addChildElement(root, "schema", "xsd");
        schema.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        schema.setAttribute("xmlns", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        schema.setAttribute("xmlns:sql", "urn:schemas-microsoft-com:xml-sql");
        schema.setAttribute("targetNamespace", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement el  = addChildElement(schema, "element", "xsd");
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, "complexType", "xsd");
        SOAPElement s = addChildElement(ct, "sequence", "xsd");
        SOAPElement se = addChildElement(s, "element", "xsd");
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st  = addChildElement(schema, "simpleType", "xsd");
        st.setAttribute("name", "uuid");
        SOAPElement r  = addChildElement(st, "restriction", "xsd");
        r.setAttribute("base", "xsd:string");
        SOAPElement p  = addChildElement(r, "pattern", "xsd");
        p.setAttribute("value", "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}");

        SOAPElement ct1  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "row");
        SOAPElement s1  = addChildElement(ct1, "sequence", "xsd");
        SOAPElement s1e1  = addChildElement(s1, "element", "xsd");
        s1e1.setAttribute("sql:field", "SchemaName");
        s1e1.setAttribute("name", "SchemaName");
        s1e1.setAttribute("type", "xsd:string");
        SOAPElement s1e2  = addChildElement(s1, "element", "xsd");
        s1e2.setAttribute("sql:field", "SchemaGuid");
        s1e2.setAttribute("name", "SchemaGuid");
        s1e2.setAttribute("type", "uuid");
        s1e2.setAttribute("minOccurs", "0");
        SOAPElement s1e3  = addChildElement(s1, "element", "xsd");
        s1e3.setAttribute("sql:field", "Restrictions");
        s1e3.setAttribute("name", "Restrictions");
        s1e3.setAttribute("minOccurs", "0");
        s1e3.setAttribute("maxOccurs", "unbounded");
        SOAPElement s1e3ct  = addChildElement(s1e3, "complexType", "xsd");
        SOAPElement s1e3cts  = addChildElement(s1e3ct, "sequence", "xsd");
        SOAPElement s1e3ctse1  = addChildElement(s1e3cts, "element", "xsd");
        s1e3ctse1.setAttribute("name", "Name");
        s1e3ctse1.setAttribute("type", "xsd:string");
        s1e3ctse1.setAttribute("sql:field", "Name");
        SOAPElement s1e3ctse2  = addChildElement(s1e3cts, "element", "xsd");
        s1e3ctse2.setAttribute("name", "Type");
        s1e3ctse2.setAttribute("type", "xsd:string");
        s1e3ctse2.setAttribute("sql:field", "Type");

        SOAPElement s1e4  = addChildElement(s1, "element", "xsd");
        s1e4.setAttribute("sql:field", "Description");
        s1e4.setAttribute("name", "Description");
        s1e4.setAttribute("type", "xsd:string");

        SOAPElement s1e5  = addChildElement(s1, "element", "xsd");
        s1e5.setAttribute("sql:field", "RestrictionsMask");
        s1e5.setAttribute("name", "RestrictionsMask");
        s1e5.setAttribute("type", "xsd:unsignedLong");
        s1e5.setAttribute("minOccurs", "0");

        return root;
    }

    private static SOAPElement addDiscoverPropertiesRoot(SOAPElement body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:msxmla", "http://schemas.microsoft.com/analysisservices/2003/xmla");
        SOAPElement schema  = addChildElement(root, "schema", "xsd");
        schema.setAttribute("targetNamespace", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("xmlns:sql", "urn:schemas-microsoft-com:xml-sql");
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement el  = addChildElement(schema, "element", "xsd");
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, "complexType", "xsd");
        SOAPElement s = addChildElement(ct, "sequence", "xsd");
        SOAPElement se = addChildElement(s, "element", "xsd");
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st  = addChildElement(schema, "simpleType", "xsd");
        st.setAttribute("name", "uuid");
        SOAPElement r  = addChildElement(st, "restriction", "xsd");
        r.setAttribute("base", "xsd:string");
        SOAPElement p  = addChildElement(r, "pattern", "xsd");
        p.setAttribute("value", "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}");

        SOAPElement ct1  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "xmlDocument");
        SOAPElement s1  = addChildElement(ct1, "sequence", "xsd");
        SOAPElement a  = addChildElement(s1, "any", "xsd");

        SOAPElement ct2  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "row");
        SOAPElement s2  = addChildElement(ct2, "sequence", "xsd");
        SOAPElement s2e1  = addChildElement(s2, "element", "xsd");
        s2e1.setAttribute("sql:field", "PropertyName");
        s2e1.setAttribute("name", "PropertyName");
        s2e1.setAttribute("type", "xsd:string");

        SOAPElement s2e2  = addChildElement(s2, "element", "xsd");
        s2e2.setAttribute("sql:field", "PropertyDescription");
        s2e2.setAttribute("name", "PropertyDescription");
        s2e2.setAttribute("type", "xsd:string");
        s2e2.setAttribute("minOccurs", "0");

        SOAPElement s2e3  = addChildElement(s2, "element", "xsd");
        s2e3.setAttribute("sql:field", "PropertyType");
        s2e3.setAttribute("name", "PropertyType");
        s2e3.setAttribute("type", "xsd:string");
        s2e3.setAttribute("minOccurs", "0");

        SOAPElement s2e4  = addChildElement(s2, "element", "xsd");
        s2e4.setAttribute("sql:field", "PropertyAccessType");
        s2e4.setAttribute("name", "PropertyAccessType");
        s2e4.setAttribute("type", "xsd:string");

        SOAPElement s2e5  = addChildElement(s2, "element", "xsd");
        s2e5.setAttribute("sql:field", "IsRequired");
        s2e5.setAttribute("name", "IsRequired");
        s2e5.setAttribute("type", "xsd:boolean");
        s2e5.setAttribute("minOccurs", "0");

        SOAPElement s2e6  = addChildElement(s2, "element", "xsd");
        s2e6.setAttribute("sql:field", "Value");
        s2e6.setAttribute("name", "Value");
        s2e6.setAttribute("type", "xsd:string");
        s2e6.setAttribute("minOccurs", "0");

        return root;
    }

    private static SOAPElement addDbSchemaCatalogsRoot(SOAPElement body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);

        SOAPElement ct1  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "row");
        SOAPElement s1  = addChildElement(ct1, "sequence", "xsd");
        SOAPElement s1e1  = addChildElement(s1, "element", "xsd");
        s1e1.setAttribute("sql:field", "CATALOG_NAME");
        s1e1.setAttribute("name", "CATALOG_NAME");
        s1e1.setAttribute("type", "xsd:string");

        SOAPElement s1e2  = addChildElement(s1, "element", "xsd");
        s1e2.setAttribute("sql:field", "DESCRIPTION");
        s1e2.setAttribute("name", "DESCRIPTION");
        s1e2.setAttribute("type", "xsd:string");
        s1e2.setAttribute("minOccurs", "0");

        SOAPElement s1e3  = addChildElement(s1, "element", "xsd");
        s1e3.setAttribute("sql:field", "ROLES");
        s1e3.setAttribute("name", "ROLES");
        s1e3.setAttribute("type", "xsd:string");

        SOAPElement s1e4  = addChildElement(s1, "element", "xsd");
        s1e4.setAttribute("sql:field", "DATE_MODIFIED");
        s1e4.setAttribute("name", "DATE_MODIFIED");
        s1e4.setAttribute("type", "xsd:dateTime");
        s1e4.setAttribute("minOccurs", "0");
        return root;
    }

    private static SOAPElement addDiscoverEnumeratorsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);

        SOAPElement ct1  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "row");
        SOAPElement s1  = addChildElement(ct1, "sequence", "xsd");
        SOAPElement s1e1  = addChildElement(s1, "element", "xsd");
        s1e1.setAttribute("sql:field", "EnumName");
        s1e1.setAttribute("name", "EnumName");
        s1e1.setAttribute("type", "xsd:string");

        SOAPElement s1e2  = addChildElement(s1, "element", "xsd");
        s1e2.setAttribute("sql:field", "EnumDescription");
        s1e2.setAttribute("name", "EnumDescription");
        s1e2.setAttribute("type", "xsd:string");
        s1e2.setAttribute("minOccurs", "0");

        SOAPElement s1e3  = addChildElement(s1, "element", "xsd");
        s1e3.setAttribute("sql:field", "EnumType");
        s1e3.setAttribute("name", "EnumType");
        s1e3.setAttribute("type", "xsd:string");


        SOAPElement s1e4  = addChildElement(s1, "element", "xsd");
        s1e4.setAttribute("sql:field", "ElementName");
        s1e4.setAttribute("name", "ElementName");
        s1e4.setAttribute("type", "xsd:string");

        SOAPElement s1e5  = addChildElement(s1, "element", "xsd");
        s1e5.setAttribute("sql:field", "ElementDescription");
        s1e5.setAttribute("name", "ElementDescription");
        s1e5.setAttribute("type", "xsd:string");
        s1e5.setAttribute("minOccurs", "0");

        SOAPElement s1e6  = addChildElement(s1, "element", "xsd");
        s1e6.setAttribute("sql:field", "ElementValue");
        s1e6.setAttribute("name", "ElementValue");
        s1e6.setAttribute("type", "xsd:string");
        s1e6.setAttribute("minOccurs", "0");

        return root;
    }

    private static SOAPElement addDiscoverKeywordsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);

        SOAPElement ct1  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "row");
        SOAPElement s1  = addChildElement(ct1, "sequence", "xsd");
        SOAPElement s1e1  = addChildElement(s1, "element", "xsd");
        s1e1.setAttribute("sql:field", "Keyword");
        s1e1.setAttribute("name", "Keyword");
        s1e1.setAttribute("type", "xsd:string");

        return root;
    }

    private static SOAPElement addDiscoverLiteralsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);

        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        SOAPElement se1  = addChildElement(s, "element", "xsd");
        se1.setAttribute("sql:field", "LiteralName");
        se1.setAttribute("name", "LiteralName");
        se1.setAttribute("type", "xsd:string");

        SOAPElement se2  = addChildElement(s, "element", "xsd");
        se2.setAttribute("sql:field", "LiteralValue");
        se2.setAttribute("name", "LiteralValue");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3  = addChildElement(s, "element", "xsd");
        se3.setAttribute("sql:field", "LiteralInvalidChars");
        se3.setAttribute("name", "LiteralInvalidChars");
        se3.setAttribute("type", "xsd:string");
        se3.setAttribute("minOccurs", "0");

        SOAPElement se4  = addChildElement(s, "element", "xsd");
        se4.setAttribute("sql:field", "LiteralInvalidStartingChars");
        se4.setAttribute("name", "LiteralInvalidStartingChars");
        se4.setAttribute("type", "xsd:string");
        se4.setAttribute("minOccurs", "0");

        SOAPElement se5  = addChildElement(s, "element", "xsd");
        se5.setAttribute("sql:field", "LiteralMaxLength");
        se5.setAttribute("name", "LiteralMaxLength");
        se5.setAttribute("type", "xsd:int");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6  = addChildElement(s, "element", "xsd");
        se6.setAttribute("sql:field", "LiteralNameEnumValue");
        se6.setAttribute("name", "LiteralNameEnumValue");
        se6.setAttribute("type", "xsd:int");
        se6.setAttribute("minOccurs", "0");

        return root;
    }

    private static SOAPElement addDbSchemaTablesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);

        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        SOAPElement se1  = addChildElement(s, "element", "xsd");
        se1.setAttribute("sql:field", "TABLE_CATALOG");
        se1.setAttribute("name", "TABLE_CATALOG");
        se1.setAttribute("type", "xsd:string");

        SOAPElement se2  = addChildElement(s, "element", "xsd");
        se2.setAttribute("sql:field", "TABLE_SCHEMA");
        se2.setAttribute("name", "TABLE_SCHEMA");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3  = addChildElement(s, "element", "xsd");
        se3.setAttribute("sql:field", "TABLE_NAME");
        se3.setAttribute("name", "TABLE_NAME");
        se3.setAttribute("type", "xsd:string");

        SOAPElement se4  = addChildElement(s, "element", "xsd");
        se4.setAttribute("sql:field", "TABLE_TYPE");
        se4.setAttribute("name", "TABLE_TYPE");
        se4.setAttribute("type", "xsd:string");

        SOAPElement se5  = addChildElement(s, "element", "xsd");
        se5.setAttribute("sql:field", "TABLE_GUID");
        se5.setAttribute("name", "TABLE_GUID");
        se5.setAttribute("type", "uuid");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6  = addChildElement(s, "element", "xsd");
        se6.setAttribute("sql:field", "DESCRIPTION");
        se6.setAttribute("name", "DESCRIPTION");
        se6.setAttribute("type", "xsd:string");
        se6.setAttribute("minOccurs", "0");

        SOAPElement se7  = addChildElement(s, "element", "xsd");
        se7.setAttribute("sql:field", "TABLE_PROPID");
        se7.setAttribute("name", "TABLE_PROPID");
        se7.setAttribute("type", "xsd:unsignedInt");
        se7.setAttribute("minOccurs", "0");

        SOAPElement se8  = addChildElement(s, "element", "xsd");
        se8.setAttribute("sql:field", "DATE_CREATED");
        se8.setAttribute("name", "DATE_CREATED");
        se8.setAttribute("type", "xsd:dateTime");
        se8.setAttribute("minOccurs", "0");

        SOAPElement se9  = addChildElement(s, "element", "xsd");
        se9.setAttribute("sql:field", "DATE_MODIFIED");
        se9.setAttribute("name", "DATE_MODIFIED");
        se9.setAttribute("type", "xsd:dateTime");
        se9.setAttribute("minOccurs", "0");

        return root;
    }

    private static SOAPElement addMdSchemaCubesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);

        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");

        SOAPElement se1  = addChildElement(s, "element", "xsd");
        se1.setAttribute("sql:field", "CATALOG_NAME");
        se1.setAttribute("name", "CATALOG_NAME");
        se1.setAttribute("type", "xsd:string");
        se1.setAttribute("minOccurs", "0");

        SOAPElement se2  = addChildElement(s, "element", "xsd");
        se2.setAttribute("sql:field", "SCHEMA_NAME");
        se2.setAttribute("name", "SCHEMA_NAME");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3  = addChildElement(s, "element", "xsd");
        se3.setAttribute("sql:field", "CUBE_NAME");
        se3.setAttribute("name", "CUBE_NAME");
        se3.setAttribute("type", "xsd:string");

        SOAPElement se4  = addChildElement(s, "element", "xsd");
        se4.setAttribute("sql:field", "CUBE_TYPE");
        se4.setAttribute("name", "CUBE_TYPE");
        se4.setAttribute("type", "xsd:string");

        SOAPElement se5  = addChildElement(s, "element", "xsd");
        se5.setAttribute("sql:field", "CUBE_GUID");
        se5.setAttribute("name", "CUBE_GUID");
        se5.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se6  = addChildElement(s, "element", "xsd");
        se6.setAttribute("sql:field", "CREATED_ON");
        se6.setAttribute("name", "CREATED_ON");
        se6.setAttribute("type", "xsd:dateTime");
        se6.setAttribute("minOccurs", "0");

        SOAPElement se7  = addChildElement(s, "element", "xsd");
        se7.setAttribute("sql:field", "LAST_SCHEMA_UPDATE");
        se7.setAttribute("name", "LAST_SCHEMA_UPDATE");
        se7.setAttribute("type", "xsd:dateTime");
        se7.setAttribute("minOccurs", "0");

        SOAPElement se8  = addChildElement(s, "element", "xsd");
        se8.setAttribute("sql:field", "SCHEMA_UPDATED_BY");
        se8.setAttribute("name", "SCHEMA_UPDATED_BY");
        se8.setAttribute("type", "xsd:string");
        se8.setAttribute("minOccurs", "0");

        SOAPElement se9  = addChildElement(s, "element", "xsd");
        se9.setAttribute("sql:field", "LAST_DATA_UPDATE");
        se9.setAttribute("name", "LAST_DATA_UPDATE");
        se9.setAttribute("type", "xsd:dateTime");
        se9.setAttribute("minOccurs", "0");

        SOAPElement se10  = addChildElement(s, "element", "xsd");
        se10.setAttribute("sql:field", "DATA_UPDATED_BY");
        se10.setAttribute("name", "DATA_UPDATED_BY");
        se10.setAttribute("type", "xsd:string");
        se10.setAttribute("minOccurs", "0");

        SOAPElement se11  = addChildElement(s, "element", "xsd");
        se11.setAttribute("sql:field", "DESCRIPTION");
        se11.setAttribute("name", "DESCRIPTION");
        se11.setAttribute("type", "xsd:string");
        se11.setAttribute("minOccurs", "0");

        SOAPElement se12  = addChildElement(s, "element", "xsd");
        se12.setAttribute("sql:field", "IS_DRILLTHROUGH_ENABLED");
        se12.setAttribute("name", "IS_DRILLTHROUGH_ENABLED");
        se12.setAttribute("type", "xsd:boolean");

        SOAPElement se13  = addChildElement(s, "element", "xsd");
        se13.setAttribute("sql:field", "IS_LINKABLE");
        se13.setAttribute("name", "IS_LINKABLE");
        se13.setAttribute("type", "xsd:boolean");

        SOAPElement se14  = addChildElement(s, "element", "xsd");
        se14.setAttribute("sql:field", "IS_WRITE_ENABLED");
        se14.setAttribute("name", "IS_WRITE_ENABLED");
        se14.setAttribute("type", "xsd:boolean");

        SOAPElement se15  = addChildElement(s, "element", "xsd");
        se15.setAttribute("sql:field", "IS_SQL_ENABLED");
        se15.setAttribute("name", "IS_SQL_ENABLED");
        se15.setAttribute("type", "xsd:boolean");

        SOAPElement se16  = addChildElement(s, "element", "xsd");
        se16.setAttribute("sql:field", "CUBE_CAPTION");
        se16.setAttribute("name", "CUBE_CAPTION");
        se16.setAttribute("type", "xsd:string");
        se16.setAttribute("minOccurs", "0");

        SOAPElement se17  = addChildElement(s, "element", "xsd");
        se17.setAttribute("sql:field", "BASE_CUBE_NAME");
        se17.setAttribute("name", "BASE_CUBE_NAME");
        se17.setAttribute("type", "xsd:string");
        se17.setAttribute("minOccurs", "0");

        SOAPElement se18  = addChildElement(s, "element", "xsd");
        se18.setAttribute("sql:field", "DIMENSIONS");
        se18.setAttribute("name", "DIMENSIONS");
        se18.setAttribute("minOccurs", "0");

        SOAPElement se19  = addChildElement(s, "element", "xsd");
        se19.setAttribute("sql:field", "SETS");
        se19.setAttribute("name", "SETS");
        se19.setAttribute("minOccurs", "0");

        SOAPElement se20  = addChildElement(s, "element", "xsd");
        se20.setAttribute("sql:field", "MEASURES");
        se20.setAttribute("name", "MEASURES");
        se20.setAttribute("minOccurs", "0");

        SOAPElement se21  = addChildElement(s, "element", "xsd");
        se21.setAttribute("sql:field", "CUBE_SOURCE");
        se21.setAttribute("name", "CUBE_SOURCE");
        se21.setAttribute("type", "xsd:int");
        se21.setAttribute("minOccurs", "0");

        return root;
    }

    private static SOAPElement addMdSchemaHierarchiesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME","xsd:string", null);
        addElement(s, "HIERARCHY_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_GUID", "uuid", "0");
        addElement(s, "HIERARCHY_CAPTION", "xsd:string", null);
        addElement(s, "DIMENSION_TYPE", "xsd:short", null);
        addElement(s, "HIERARCHY_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "DEFAULT_MEMBER", "xsd:string", "0");
        addElement(s, "ALL_MEMBER", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "STRUCTURE", "xsd:short", null);
        addElement(s, "IS_VIRTUAL", "xsd:boolean", null);
        addElement(s, "IS_READWRITE", "xsd:boolean", null);
        addElement(s, "DIMENSION_UNIQUE_SETTINGS", "xsd:int", null);
        addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "HIERARCHY_ORDINAL", "xsd:unsignedInt", null);
        addElement(s, "DIMENSION_IS_SHARED", "xsd:boolean", null);
        addElement(s, "HIERARCHY_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "HIERARCHY_ORIGIN", "xsd:unsignedShort", "0");
        addElement(s, "HIERARCHY_DISPLAY_FOLDER", "xsd:string", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "HIERARCHY_VISIBILITY", "xsd:unsignedShort", "0");
        addElement(s, "PARENT_CHILD", "xsd:boolean", "0");
        addElement(s, "LEVELS", null, "0");
        return root;
    }

    private static SOAPElement addMdSchemaMeasuresRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASURE_NAME", "xsd:string", null);
        addElement(s, "MEASURE_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "MEASURE_CAPTION", "xsd:string", null);
        addElement(s, "MEASURE_GUID", "uuid", "0");
        addElement(s, "MEASURE_AGGREGATOR", "xsd:int", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "MEASURE_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "LEVELS_LIST", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "MEASURE_DISPLAY_FOLDER", "xsd:string", "0");
        addElement(s, "DEFAULT_FORMAT_STRING", "xsd:string","0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "MEASURE_VISIBILITY", "xsd:unsignedShort", "0");
        return root;
    }

    private static SOAPElement addMdSchemaDimensionsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_GUID", "uuid", "0");
        addElement(s, "DIMENSION_CAPTION", "xsd:string", null);
        addElement(s, "DIMENSION_ORDINAL", "xsd:unsignedInt", null);
        addElement(s, "DIMENSION_TYPE", "xsd:short", null);
        addElement(s, "DIMENSION_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "DEFAULT_HIERARCHY", "xsd:string", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "IS_VIRTUAL", "xsd:boolean", "0");
        addElement(s, "IS_READWRITE", "xsd:boolean", "0");
        addElement(s, "DIMENSION_UNIQUE_SETTINGS", "xsd:int", "0");
        addElement(s, "DIMENSION_MASTER_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", "0");
        addElement(s, "HIERARCHIES", null, "0");
        return root;
    }

    private static SOAPElement addMdSchemaMeasureGroupsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "IS_WRITE_ENABLED", "xsd:boolean", "0");
        addElement(s, "MEASUREGROUP_CAPTION", "xsd:string", "0");
        return root;
    }

    private static SOAPElement addMdSchemaMeasureGroupDimensionsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        addElement(s, "MEASUREGROUP_CARDINALITY", "xsd:string", "0");
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "DIMENSION_CARDINALITY", "xsd:string", "0");
        addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", "0");
        addElement(s, "DIMENSION_IS_FACT_DIMENSION", "xsd:boolean", "0");
        addElement(s, "DIMENSION_PATH", "xsd:string", "0");
        addElement(s, "DIMENSION_GRANULARITY", "xsd:string", "0");
        return root;
    }

    private static SOAPElement addMdSchemaLevelsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_NAME", "xsd:string", null);
        addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_GUID", "uuid", "0");
        addElement(s, "LEVEL_CAPTION", "xsd:string", null);
        addElement(s, "LEVEL_NUMBER", "xsd:unsignedInt", null);
        addElement(s, "LEVEL_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "LEVEL_TYPE", "xsd:int", null);
        addElement(s, "CUSTOM_ROLLUP_SETTINGS", "xsd:int", null);
        addElement(s, "LEVEL_UNIQUE_SETTINGS", "xsd:int", null);
        addElement(s, "LEVEL_IS_VISIBLE", "xsd:boolean", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "LEVEL_ORIGIN", "xsd:unsignedShort", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "LEVEL_VISIBILITY", "xsd:unsignedShort", "0");
        return root;
    }

    private static SOAPElement addMdSchemaPropertiesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "MEMBER_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "PROPERTY_TYPE", "xsd:short", null);
        addElement(s, "PROPERTY_NAME", "xsd:string", null);
        addElement(s, "PROPERTY_CAPTION", "xsd:string", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "PROPERTY_CONTENT_TYPE", "xsd:short", "0");
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "PROPERTY_ORIGIN", "xsd:unsignedShort", "0");
        addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        addElement(s, "PROPERTY_VISIBILITY", "xsd:unsignedShort", "0");
        return root;
    }

    private static SOAPElement addMdSchemaMembersRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string","0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "LEVEL_NUMBER", "xsd:unsignedInt", null);
        addElement(s, "MEMBER_ORDINAL", "xsd:unsignedInt", null);
        addElement(s, "MEMBER_NAME","xsd:string", null);
        addElement(s, "MEMBER_UNIQUE_NAME", "xsd:string", null);
        addElement(s, "MEMBER_TYPE", "xsd:int", null);
        addElement(s, "MEMBER_GUID", "uuid", "0");
        addElement(s, "MEMBER_CAPTION","xsd:string", null);
        addElement(s, "CHILDREN_CARDINALITY", "xsd:unsignedInt", null);
        addElement(s, "PARENT_LEVEL", "xsd:unsignedInt", null);
        addElement(s, "PARENT_UNIQUE_NAME", "xsd:string", "0");
        addElement(s, "PARENT_COUNT", "xsd:unsignedInt", null);
        addElement(s, "TREE_OP", "xsd:string", "0");
        addElement(s, "DEPTH", "xsd:int", "0");
        return root;
    }

    private static SOAPElement addDbSchemaTablesInfoRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "TABLE_CATALOG", "xsd:string", "0");
        addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        addElement(s, "TABLE_NAME", "xsd:string", null);
        addElement(s, "TABLE_TYPE", "xsd:string", null);
        addElement(s, "TABLE_GUID", "uuid", "0");
        addElement(s, "BOOKMARKS", "xsd:boolean", null);
        addElement(s, "BOOKMARK_TYPE", "xsd:int", "0");
        addElement(s, "BOOKMARK_DATATYPE", "xsd:unsignedShort", "0");
        addElement(s, "BOOKMARK_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        addElement(s, "BOOKMARK_INFORMATION", "xsd:unsignedInt", "0");
        addElement(s, "TABLE_VERSION", "xsd:long", "0");
        addElement(s, "CARDINALITY", "xsd:unsignedLong", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "TABLE_PROPID", "xsd:unsignedInt", "0");
        return root;
    }

    private static SOAPElement addDbSchemaSourceTablesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "TABLE_CATALOG","xsd:string","0");
        addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        addElement(s, "TABLE_NAME", "xsd:string", null);
        addElement(s, "TABLE_TYPE", "xsd:string", null);
        return root;
    }

    private static SOAPElement addDbSchemaSchemataRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME","xsd:string",null);
        addElement(s, "SCHEMA_NAME", "xsd:string", null);
        addElement(s, "SCHEMA_OWNER", "xsd:string", null);
        return root;
    }

    private static SOAPElement addDbSchemaProviderTypesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "TYPE_NAME", "xsd:string", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "COLUMN_SIZE", "xsd:unsignedInt", null);
        addElement(s, "LITERAL_PREFIX", "xsd:string", "0");
        addElement(s, "LITERAL_SUFFIX", "xsd:string", "0");
        addElement(s, "IS_NULLABLE", "xsd:boolean", "0");
        addElement(s, "CASE_SENSITIVE", "xsd:boolean", "0");
        addElement(s, "SEARCHABLE", "xsd:unsignedInt", "0");
        addElement(s, "UNSIGNED_ATTRIBUTE", "xsd:boolean", "0");
        addElement(s, "FIXED_PREC_SCALE", "xsd:boolean", "0");
        addElement(s, "AUTO_UNIQUE_VALUE", "xsd:boolean", "0");
        addElement(s, "IS_LONG", "xsd:boolean", "0");
        addElement(s, "BEST_MATCH", "xsd:boolean", "0");
        return root;
    }

    private static SOAPElement addDbSchemaColumnsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "TABLE_CATALOG", "xsd:string", null);
        addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        addElement(s, "TABLE_NAME", "xsd:string", null);
        addElement(s, "COLUMN_NAME", "xsd:string", null);
        addElement(s, "ORDINAL_POSITION", "xsd:unsignedInt", null);
        addElement(s, "COLUMN_HAS_DEFAULT", "xsd:boolean", "0");
        addElement(s, "COLUMN_FLAGS", "xsd:unsignedInt", null);
        addElement(s, "IS_NULLABLE", "xsd:boolean", null);
        addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        addElement(s, "CHARACTER_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        addElement(s, "CHARACTER_OCTET_LENGTH", "xsd:unsignedInt", "0");
        addElement(s, "NUMERIC_PRECISION", "xsd:unsignedShort", "0");
        addElement(s, "NUMERIC_SCALE", "xsd:short", "0");
        return root;
    }

    private static SOAPElement addDiscoverXmlMetaDataRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "METADATA", "xsd:string", null);
        addElement(s, "ObjectType", "xsd:string", "0");
        addElement(s, "DatabaseID", "xsd:string", "0");
        return root;
    }

    private static SOAPElement addDiscoverDataSourcesRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "LiteralName", "xsd:string", null);
        addElement(s, "LiteralValue", "xsd:string", "0");
        addElement(s, "LiteralInvalidChars", "xsd:string", "0");
        addElement(s, "LiteralInvalidStartingChars", "xsd:string", "0");
        addElement(s, "LiteralMaxLength", "xsd:int", "0");
        addElement(s, "LiteralNameEnumValue", "xsd:int", "0");
        return root;
    }

    private static SOAPElement addMdSchemaActionsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "CATALOG_NAME", "xsd:string", "0");
        addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        addElement(s, "CUBE_NAME", "xsd:string", null);
        addElement(s, "ACTION_NAME", "xsd:string", null);
        addElement(s, "COORDINATE", "xsd:string", null);
        addElement(s, "COORDINATE_TYPE", "xsd:int", null);
        return root;
    }

    private static SOAPElement addMdSchemaFunctionsRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "DiscoverResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        SOAPElement schema = fillRoot(root);
        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement s  = addChildElement(ct, "sequence", "xsd");
        addElement(s, "FUNCTION_NAME", "xsd:string", null);
        addElement(s, "DESCRIPTION", "xsd:string", "0");
        addElement(s, "PARAMETER_LIST", "xsd:string", "0");
        addElement(s, "RETURN_TYPE", "xsd:int", null);
        addElement(s, "ORIGIN", "xsd:int", null);
        addElement(s, "INTERFACE_NAME", "xsd:string", null);
        addElement(s, "LIBRARY_NAME", "xsd:string", "0");
        addElement(s, "CAPTION", "xsd:string", "0");
        return root;
    }


    private static void addElement(SOAPElement s, String name, String type, String minOccurs) {
        SOAPElement se  = addChildElement(s, "element", "xsd");
        se.setAttribute("sql:field", name);
        se.setAttribute("name", name);
        if (type != null) {
            se.setAttribute("type", type);
        }
        if (minOccurs != null) {
            se.setAttribute("minOccurs", minOccurs);
        }
    }

    private static SOAPElement fillRoot(SOAPElement root) {
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:EX", "urn:schemas-microsoft-com:xml-analysis:exception");
        SOAPElement schema  = addChildElement(root, "schema", "xsd");
        schema.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        schema.setAttribute("xmlns", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        schema.setAttribute("xmlns:sql", "urn:schemas-microsoft-com:xml-sql");
        schema.setAttribute("targetNamespace", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("elementFormDefault", "qualified");

        SOAPElement el  = addChildElement(schema, "element", "xsd");
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, "complexType", "xsd");
        SOAPElement s = addChildElement(ct, "sequence", "xsd");
        SOAPElement se = addChildElement(s, "element", "xsd");
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st  = addChildElement(schema, "simpleType", "xsd");
        st.setAttribute("name", "uuid");
        SOAPElement r  = addChildElement(st, "restriction", "xsd");
        r.setAttribute("base", "xsd:string");
        SOAPElement p  = addChildElement(r, "pattern", "xsd");
        p.setAttribute("value", "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}");
        return schema;
    }

    private static SOAPElement addEmptyRoot(SOAPBody body) {
        SOAPElement response = addChildElement(body, "ExecuteResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", EMPTY);
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:EX", "urn:schemas-microsoft-com:xml-analysis:exception");
        return root;
    }

    private static SOAPElement addMddatasetRoot(SOAPElement body) {
        SOAPElement response = addChildElement(body, "ExecuteResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", MDDATASET);
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:EX", "urn:schemas-microsoft-com:xml-analysis:exception");
        addMddatasetSchema(root);
        return root;
    }

    private static SOAPElement addRowSetRoot(SOAPBody body, RowSet rowSet) {
        SOAPElement response = addChildElement(body, "ExecuteResponse", MSXMLA);
        SOAPElement ret = addChildElement(response, "return", MSXMLA);
        SOAPElement root = addChildElement(ret, "root", ROWSET);
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:EX", "urn:schemas-microsoft-com:xml-analysis:exception");
        addRowsetSchema(root, rowSet);
        return root;
    }


    private static void addRowsetSchema(SOAPElement root, RowSet rowSet) {
        SOAPElement schema  = addChildElement(root, "schema", "xsd");
        schema.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        schema.setAttribute("targetNamespace", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("xmlns", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        schema.setAttribute("xmlns:sql", "urn:schemas-microsoft-com:xml-sql");
        schema.setAttribute("elementFormDefault", "qualified");

        SOAPElement el1  = addChildElement(schema, "element", "xsd");
        el1.setAttribute("name", "root");
        SOAPElement el1complexType  = addChildElement(el1, "complexType", "xsd");
        SOAPElement el1complexTypeSequence  = addChildElement(el1complexType, "sequence", "xsd");
        SOAPElement el1complexTypeSequenceEl  = addChildElement(el1complexTypeSequence, "element", "xsd");
        el1complexTypeSequenceEl.setAttribute("maxOccurs", "unbounded");
        el1complexTypeSequenceEl.setAttribute("minOccurs", "0");
        el1complexTypeSequenceEl.setAttribute("name", "row");
        el1complexTypeSequenceEl.setAttribute("type", "row");

        SOAPElement simpleType  = addChildElement(schema, "simpleType", "xsd");
        simpleType.setAttribute("name", "uuid");
        SOAPElement restriction  = addChildElement(simpleType, "restriction", "xsd");
        restriction.setAttribute("base", "xsd:string");
        SOAPElement pattern  = addChildElement(restriction, "restriction", "xsd");
        pattern.setAttribute("value", "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        SOAPElement ct  = addChildElement(schema, "complexType", "xsd");
        ct.setAttribute("name", "row");
        SOAPElement ctSequence  = addChildElement(ct, "sequence", "xsd");
        if ( rowSet.rowSetRows() != null && rowSet.rowSetRows().size() > 0
            && rowSet.rowSetRows().get(0) != null && rowSet.rowSetRows().get(0).rowSetRowItem() != null) {
            for (RowSetRowItem item : rowSet.rowSetRows().get(0).rowSetRowItem()) {
                SOAPElement ctSequenceEl1  = addChildElement(ctSequence, "element", "xsd");
                ItemTypeEnum type = item.type().orElse(ItemTypeEnum.STRING);
                ctSequenceEl1.setAttribute("minOccurs", "0");
                ctSequenceEl1.setAttribute("name", item.tagName());
                ctSequenceEl1.setAttribute("sql:field", item.tagName());
                ctSequenceEl1.setAttribute("type", type.getValue());
            }
        }
    }

    private static void addMddatasetSchema(SOAPElement root) {
        SOAPElement schema  = addChildElement(root, "schema", "xsd");
        schema.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        schema.setAttribute("targetNamespace", "urn:schemas-microsoft-com:xml-analysis:mddataset");
        schema.setAttribute("xmlns", "urn:schemas-microsoft-com:xml-analysis:mddataset");
        schema.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        schema.setAttribute("xmlns:sql", "urn:schemas-microsoft-com:xml-sql");
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement ct1  = addChildElement(schema, "complexType", "xsd");
        ct1.setAttribute("name", "MemberType");
        SOAPElement ct1Sequence = addChildElement(ct1, "sequence", "xsd");
        SOAPElement e1  = addChildElement(ct1Sequence, "element", "xsd");
        e1.setAttribute("name", "UName");
        e1.setAttribute("type", "xsd:string");
        SOAPElement e2  = addChildElement(ct1Sequence, "element", "xsd");
        e2.setAttribute("name", "Caption");
        e2.setAttribute("type", "xsd:string");
        SOAPElement e3  = addChildElement(ct1Sequence, "element", "xsd");
        e3.setAttribute("name", "LName");
        e3.setAttribute("type", "xsd:string");
        SOAPElement e4  = addChildElement(ct1Sequence, "element", "xsd");
        e4.setAttribute("name", "LNum");
        e4.setAttribute("type", "xsd:unsignedInt");
        SOAPElement e5  = addChildElement(ct1Sequence, "element", "xsd");
        e5.setAttribute("name", "DisplayInfo");
        e5.setAttribute("type", "xsd:unsignedInt");
        SOAPElement s  = addChildElement(ct1Sequence, "sequence", "xsd");
        s.setAttribute("maxOccurs", "unbounded");
        s.setAttribute("minOccurs", "0");
        SOAPElement any  = addChildElement(ct1Sequence, "any", "xsd");
        any.setAttribute("processContents", "lax");
        any.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct1Attribute = addChildElement(ct1, "attribute", "xsd");
        ct1Attribute.setAttribute("name", "Hierarchy");
        ct1Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct2  = addChildElement(schema, "complexType", "xsd");
        ct2.setAttribute("name", "PropType");
        SOAPElement ct2Attribute  = addChildElement(ct2, "attribute", "xsd");
        ct2Attribute.setAttribute("name", "name");
        ct2Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct3  = addChildElement(schema, "complexType", "xsd");
        ct3.setAttribute("name", "TupleType");
        SOAPElement ct3Sequence  = addChildElement(ct3, "sequence", "xsd");
        ct3Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct3SequenceElement  = addChildElement(ct3Sequence, "element", "xsd");
        ct3SequenceElement.setAttribute("name", "Member");
        ct3SequenceElement.setAttribute("type", "MemberType");

        SOAPElement ct4  = addChildElement(schema, "complexType", "xsd");
        ct4.setAttribute("name", "MembersType");
        SOAPElement ct4Sequence  = addChildElement(ct4, "sequence", "xsd");
        ct4Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct4SequenceElement  = addChildElement(ct4Sequence, "element", "xsd");
        ct4SequenceElement.setAttribute("name", "Member");
        ct4SequenceElement.setAttribute("type", "MemberType");
        SOAPElement ct4Attribute  = addChildElement(ct4, "attribute", "xsd");
        ct4Attribute.setAttribute("name", "Hierarchy");
        ct4Attribute.setAttribute("type", "xsd:string");

        SOAPElement ct5  = addChildElement(schema, "complexType", "xsd");
        ct5.setAttribute("name", "TuplesType");
        SOAPElement ct5Sequence  = addChildElement(ct5, "sequence", "xsd");
        ct5Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct5SequenceElement  = addChildElement(ct5Sequence, "element", "xsd");
        ct5SequenceElement.setAttribute("name", "Tuple");
        ct5SequenceElement.setAttribute("type", "TupleType");

        SOAPElement ct6  = addChildElement(schema, "complexType", "xsd");
        ct6.setAttribute("name", "CrossProductType");
        SOAPElement ct6Sequence  = addChildElement(ct6, "sequence", "xsd");
        ct6Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct6SequenceChoice  = addChildElement(ct6Sequence, "choice", "xsd");
        ct6SequenceChoice.setAttribute("minOccurs", "0");
        ct6SequenceChoice.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct6SequenceChoiceE1  = addChildElement(ct6SequenceChoice, "element", "xsd");
        ct6SequenceChoiceE1.setAttribute("name", "Members");
        ct6SequenceChoiceE1.setAttribute("type", "MembersType");
        SOAPElement ct6SequenceChoiceE2  = addChildElement(ct6SequenceChoice, "element", "xsd");
        ct6SequenceChoiceE2.setAttribute("name", "Tuples");
        ct6SequenceChoiceE2.setAttribute("type", "TuplesType");
        SOAPElement ct6Attribute  = addChildElement(ct6, "attribute", "xsd");
        ct6Attribute.setAttribute("name", "Size");
        ct6Attribute.setAttribute("type", "xsd:unsignedInt");

        SOAPElement ct7  = addChildElement(schema, "complexType", "xsd");
        ct7.setAttribute("name", "OlapInfo");
        SOAPElement ct7Sequence  = addChildElement(ct7, "sequence", "xsd");
        SOAPElement ct7SequenceElement1  = addChildElement(ct7Sequence, "element", "xsd");
        ct7SequenceElement1.setAttribute("name", "CubeInfo");

        SOAPElement ct7SequenceElement1Ct  = addChildElement(ct7SequenceElement1, "complexType", "xsd");
        SOAPElement ct7SequenceElement1CtSequence  = addChildElement(ct7SequenceElement1Ct, "sequence", "xsd");
        SOAPElement ct7SequenceElement1CtSequenceEl  = addChildElement(ct7SequenceElement1CtSequence, "element", "xsd");
        ct7SequenceElement1CtSequenceEl.setAttribute("name", "Cube");
        ct7SequenceElement1CtSequenceEl.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement1CtSequenceElCt  = addChildElement(ct7SequenceElement1CtSequenceEl, "complexType", "xsd");
        SOAPElement ct7SequenceElement1CtSequenceElCtSequence  = addChildElement(ct7SequenceElement1CtSequenceElCt, "sequence", "xsd");
        SOAPElement ct7SequenceElement1CtSequenceElCtSequenceEl  = addChildElement(ct7SequenceElement1CtSequenceElCtSequence, "element", "xsd");
        ct7SequenceElement1CtSequenceElCtSequenceEl.setAttribute("name", "CubeName");
        ct7SequenceElement1CtSequenceElCtSequenceEl.setAttribute("type", "xsd:string");

        SOAPElement ct7SequenceElement2  = addChildElement(ct7Sequence, "element", "xsd");
        ct7SequenceElement2.setAttribute("name", "AxesInfo");
        SOAPElement ct7SequenceElement2Ct  = addChildElement(ct7SequenceElement2, "complexType", "xsd");
        SOAPElement ct7SequenceElement2CtSequence  = addChildElement(ct7SequenceElement2Ct, "sequence", "xsd");
        SOAPElement ct7SequenceElement2CtSequenceEl  = addChildElement(ct7SequenceElement2CtSequence, "element", "xsd");
        ct7SequenceElement2CtSequenceEl.setAttribute("name", "AxisInfo");
        ct7SequenceElement2CtSequenceEl.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCt  = addChildElement(ct7SequenceElement2CtSequenceEl, "complexType", "xsd");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequence  = addChildElement(ct7SequenceElement2CtSequenceElCt, "sequence", "xsd");

        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElement  = addChildElement(ct7SequenceElement2CtSequenceElCtSequence, "element", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("name", "HierarchyInfo");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceElement.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCt  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceElement, "complexType", "xsd");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceElementCt, "Sequence", "xsd");

        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence, "sequence", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E1  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceSequence1, "element", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E1.setAttribute("name", "UName");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E1.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E2  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceSequence1, "element", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E2.setAttribute("name", "Caption");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E2.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E3  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceSequence1, "element", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E3.setAttribute("name", "LName");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E3.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E4  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceSequence1, "element", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E4.setAttribute("name", "LNum");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E4.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence1E5  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceSequence1, "element", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("name", "DisplayInfo");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("type", "PropType");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceSequence1E5.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence2  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceElementCtSequence, "sequence", "xsd");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceSequence2Any  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceSequence2, "any", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("processContents", "lax");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceElCtSequenceSequence2Any.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute  = addChildElement(ct7SequenceElement2CtSequenceElCtSequenceElementCt, "attribute", "xsd");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("name", "name");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("type", "xsd:string");
        ct7SequenceElement2CtSequenceElCtSequenceElementCtAttribute.setAttribute("use", "required");
        SOAPElement ct7SequenceElement2CtAttribute  = addChildElement(ct7SequenceElement2CtSequenceElCt, "attribute", "xsd");
        ct7SequenceElement2CtAttribute.setAttribute("name", "name");
        ct7SequenceElement2CtAttribute.setAttribute("type", "xsd:string");

        SOAPElement ct7SequenceElement3  = addChildElement(ct7Sequence, "element", "xsd");
        ct7SequenceElement3.setAttribute("name", "CellInfo");
        SOAPElement ct7SequenceElement3Ct  = addChildElement(ct7SequenceElement3, "complexType", "xsd");
        SOAPElement ct7SequenceElement3CtSequence  = addChildElement(ct7SequenceElement3Ct, "sequence", "xsd");
        SOAPElement ct7SequenceElement2CtSequenceSequence1  = addChildElement(ct7SequenceElement3CtSequence, "sequence", "xsd");
        ct7SequenceElement2CtSequenceSequence1.setAttribute("minOccurs", "0");
        ct7SequenceElement2CtSequenceSequence1.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct7SequenceElement2CtSequenceSequence1Ch  = addChildElement(ct7SequenceElement2CtSequenceSequence1, "choice", "xsd");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE1  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE1.setAttribute("name", "Value");
        ct7SequenceElement2CtSequenceSequence1ChE1.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE2  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE2.setAttribute("name", "FmtValue");
        ct7SequenceElement2CtSequenceSequence1ChE2.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE3  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE3.setAttribute("name", "BackColor");
        ct7SequenceElement2CtSequenceSequence1ChE3.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE4  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE4.setAttribute("name", "ForeColor");
        ct7SequenceElement2CtSequenceSequence1ChE4.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE5  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE5.setAttribute("name", "FontName");
        ct7SequenceElement2CtSequenceSequence1ChE5.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE6  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE6.setAttribute("name", "FontSize");
        ct7SequenceElement2CtSequenceSequence1ChE6.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE7  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE7.setAttribute("name", "FontFlags");
        ct7SequenceElement2CtSequenceSequence1ChE7.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE8  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE8.setAttribute("name", "FormatString");
        ct7SequenceElement2CtSequenceSequence1ChE8.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE9  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE9.setAttribute("name", "NonEmptyBehavior");
        ct7SequenceElement2CtSequenceSequence1ChE9.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE10  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE10.setAttribute("name", "SolveOrder");
        ct7SequenceElement2CtSequenceSequence1ChE10.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE11  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE11.setAttribute("name", "Updateable");
        ct7SequenceElement2CtSequenceSequence1ChE11.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE12  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE12.setAttribute("name", "Visible");
        ct7SequenceElement2CtSequenceSequence1ChE12.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence1ChE13  = addChildElement(ct7SequenceElement2CtSequenceSequence1Ch, "element", "xsd");
        ct7SequenceElement2CtSequenceSequence1ChE13.setAttribute("name", "Expression");
        ct7SequenceElement2CtSequenceSequence1ChE13.setAttribute("type", "PropType");
        SOAPElement ct7SequenceElement2CtSequenceSequence2  = addChildElement(ct7SequenceElement3CtSequence, "sequence", "xsd");
        ct7SequenceElement2CtSequenceSequence2.setAttribute("maxOccurs", "unbounded");
        ct7SequenceElement2CtSequenceSequence2.setAttribute("minOccurs", "0");
        SOAPElement ct7SequenceElement2CtSequenceSequence2Any  = addChildElement(ct7SequenceElement2CtSequenceSequence2, "any", "xsd");
        ct7SequenceElement2CtSequenceSequence2Any.setAttribute("processContents", "lax");
        ct7SequenceElement2CtSequenceSequence2Any.setAttribute("maxOccurs", "unbounded");

        SOAPElement ct8  = addChildElement(schema, "complexType", "xsd");
        ct8.setAttribute("name", "Axes");
        SOAPElement ct8Sequence  = addChildElement(ct8, "sequence", "xsd");
        ct8Sequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct8SequenceElement  = addChildElement(ct8Sequence, "element", "xsd");
        ct8SequenceElement.setAttribute("name", "Axis");
        SOAPElement ct8SequenceElementComplexType  = addChildElement(ct8SequenceElement, "complexType", "xsd");
        SOAPElement ct8SequenceElementComplexTypeChoice  = addChildElement(ct8SequenceElementComplexType, "choice", "xsd");
        ct8SequenceElementComplexTypeChoice.setAttribute("minOccurs", "0");
        ct8SequenceElementComplexTypeChoice.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct8SequenceElementComplexTypeChoiceE1  = addChildElement(ct8SequenceElementComplexTypeChoice, "element", "xsd");
        ct8SequenceElementComplexTypeChoiceE1.setAttribute("name", "CrossProduct");
        ct8SequenceElementComplexTypeChoiceE1.setAttribute("type", "CrossProductType");
        SOAPElement ct8SequenceElementComplexTypeChoiceE2  = addChildElement(ct8SequenceElementComplexTypeChoice, "element", "xsd");
        ct8SequenceElementComplexTypeChoiceE2.setAttribute("name", "Tuples");
        ct8SequenceElementComplexTypeChoiceE2.setAttribute("type", "TuplesType");
        SOAPElement ct8SequenceElementComplexTypeChoiceE3  = addChildElement(ct8SequenceElementComplexTypeChoice, "element", "xsd");
        ct8SequenceElementComplexTypeChoiceE3.setAttribute("name", "Members");
        ct8SequenceElementComplexTypeChoiceE3.setAttribute("type", "MembersType");
        SOAPElement ct8SequenceElementComplexTypeAttribute  = addChildElement(ct8SequenceElementComplexType, "attribute", "xsd");
        ct8SequenceElementComplexTypeAttribute.setAttribute("name", "name");
        ct8SequenceElementComplexTypeAttribute.setAttribute("type", "xsd:string");

        SOAPElement ct9  = addChildElement(schema, "complexType", "xsd");
        ct9.setAttribute("name", "CellData");
        SOAPElement ct9Sequence  = addChildElement(ct9, "sequence", "xsd");
        SOAPElement ct9SequenceElement  = addChildElement(ct9Sequence, "element", "xsd");
        ct9SequenceElement.setAttribute("name", "Cell");
        ct9SequenceElement.setAttribute("minOccurs", "0");
        ct9SequenceElement.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct9SequenceElementComplexType  = addChildElement(ct9SequenceElement, "complexType", "xsd");
        SOAPElement ct9SequenceElementComplexTypeSequence  = addChildElement(ct9SequenceElementComplexType, "sequence", "xsd");
        ct9SequenceElementComplexTypeSequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoice  = addChildElement(ct9SequenceElementComplexTypeSequence, "choice", "xsd");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE1  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE1.setAttribute("name", "Value");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE2  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE2.setAttribute("name", "FmtValue");
        ct9SequenceElementComplexTypeSequenceChoiceE2.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE3  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE3.setAttribute("name", "BackColor");
        ct9SequenceElementComplexTypeSequenceChoiceE3.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE4  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE4.setAttribute("name", "ForeColor");
        ct9SequenceElementComplexTypeSequenceChoiceE4.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE5  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE5.setAttribute("name", "FontName");
        ct9SequenceElementComplexTypeSequenceChoiceE5.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE6  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE6.setAttribute("name", "FontSize");
        ct9SequenceElementComplexTypeSequenceChoiceE6.setAttribute("type", "xsd:unsignedShort");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE7  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE7.setAttribute("name", "FontFlags");
        ct9SequenceElementComplexTypeSequenceChoiceE7.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE8  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE8.setAttribute("name", "FormatString");
        ct9SequenceElementComplexTypeSequenceChoiceE8.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE9  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE9.setAttribute("name", "NonEmptyBehavior");
        ct9SequenceElementComplexTypeSequenceChoiceE9.setAttribute("type", "xsd:unsignedShort");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE10  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE10.setAttribute("name", "SolveOrder");
        ct9SequenceElementComplexTypeSequenceChoiceE10.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE11  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE11.setAttribute("name", "Updateable");
        ct9SequenceElementComplexTypeSequenceChoiceE11.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE12  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE12.setAttribute("name", "Visible");
        ct9SequenceElementComplexTypeSequenceChoiceE12.setAttribute("type", "xsd:unsignedInt");
        SOAPElement ct9SequenceElementComplexTypeSequenceChoiceE13  = addChildElement(ct9SequenceElementComplexTypeSequenceChoice, "element", "xsd");
        ct9SequenceElementComplexTypeSequenceChoiceE13.setAttribute("name", "Expression");
        ct9SequenceElementComplexTypeSequenceChoiceE13.setAttribute("type", "xsd:string");
        SOAPElement ct9SequenceElementComplexTypeAttribute  = addChildElement(ct9SequenceElementComplexType, "attribute", "xsd");
        ct9SequenceElementComplexTypeAttribute.setAttribute("name", "CellOrdinal");
        ct9SequenceElementComplexTypeAttribute.setAttribute("type", "xsd:unsignedInt");
        ct9SequenceElementComplexTypeAttribute.setAttribute("use", "required");

        SOAPElement element  = addChildElement(schema, "element", "xsd");
        element.setAttribute("name", "root");
        SOAPElement elementComplexType  = addChildElement(element, "complexType", "xsd");
        SOAPElement elementComplexTypeSequence  = addChildElement(elementComplexType, "sequence", "xsd");
        elementComplexTypeSequence.setAttribute("maxOccurs", "unbounded");
        SOAPElement elementComplexTypeSequenceE1  = addChildElement(elementComplexTypeSequence, "element", "xsd");
        elementComplexTypeSequenceE1.setAttribute("name", "OlapInfo");
        elementComplexTypeSequenceE1.setAttribute("type", "OlapInfo");
        SOAPElement elementComplexTypeSequenceE2  = addChildElement(elementComplexTypeSequence, "element", "xsd");
        elementComplexTypeSequenceE2.setAttribute("name", "Axes");
        elementComplexTypeSequenceE2.setAttribute("type", "Axes");
        SOAPElement elementComplexTypeSequenceE3  = addChildElement(elementComplexTypeSequence, "element", "xsd");
        elementComplexTypeSequenceE3.setAttribute("name", "CellData");
        elementComplexTypeSequenceE3.setAttribute("type", "CellData");
    }

    private static void addChildElement(SOAPElement element, String childElementName, String prefix, String value) {
        try {
            if (value != null) {
                if (prefix != null) {
                    element.addChildElement(childElementName, prefix).setTextContent(value);
                } else {
                    element.addChildElement(childElementName).setTextContent(value);
                }

            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new RuntimeException("addChildElement error", e);
        }
    }

    private static SOAPElement addChildElement(SOAPElement element, String childElementName, String prefix) {
        try {
            if (prefix == null) {
                return element.addChildElement(childElementName);

            }
            else {
                return element.addChildElement(childElementName, prefix);
            }
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", childElementName);
            throw new RuntimeException("addChildElement error", e);
        }
    }

}
