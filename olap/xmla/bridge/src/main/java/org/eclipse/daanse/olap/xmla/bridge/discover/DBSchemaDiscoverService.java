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

import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Schema;
import org.eclipse.daanse.olap.xmla.bridge.ContextListSupplyer;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsResponseRowR;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.eclipse.daanse.olap.xmla.bridge.discover.Utils.getDbSchemaColumnsResponseRow;
import static org.eclipse.daanse.olap.xmla.bridge.discover.Utils.getDbSchemaSchemataResponseRow;
import static org.eclipse.daanse.olap.xmla.bridge.discover.Utils.getRoles;

public class DBSchemaDiscoverService {

    private ContextListSupplyer contextsListSupplyer;

    public DBSchemaDiscoverService(ContextListSupplyer contextsListSupplyer) {
        this.contextsListSupplyer = contextsListSupplyer;
    }

    public List<DbSchemaCatalogsResponseRow> dbSchemaCatalogs(DbSchemaCatalogsRequest request) {

        Optional<String> oName = request.restrictions().catalogName();
        Optional<Context> oContext = oName.flatMap(name -> contextsListSupplyer.tryGetFirstByName(name));
        if (oContext.isPresent()) {
            Context context = oContext.get();
            return context.getDatabaseMappingSchemaProviders().stream().map(p -> {
                    Schema s = p.get();
                    return (DbSchemaCatalogsResponseRow) new DbSchemaCatalogsResponseRowR(
                        Optional.ofNullable(s.name()),
                        Optional.ofNullable(s.description()),
                        getRoles(s.roles()),
                        Optional.of(LocalDateTime.now()),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty());
                }
            ).toList();
        }
        return List.of();
    }

    public List<DbSchemaColumnsResponseRow> dbSchemaColumns(DbSchemaColumnsRequest request) {
        Optional<String> oCatalog = request.restrictions().tableCatalog();
        Optional<String> oTableSchema = request.restrictions().tableSchema();
        Optional<String> oTableName = request.restrictions().tableName();
        Optional<String> oColumnName = request.restrictions().columnName();
        Optional<ColumnOlapTypeEnum> oColumnOlapType = request.restrictions().columnOlapType();
        List<DbSchemaColumnsResponseRow> result = new ArrayList<>();
        if (oCatalog.isPresent()) {
            Optional<Context> oContext = oCatalog.flatMap(name -> contextsListSupplyer.tryGetFirstByName(name));
            if (oContext.isPresent()) {
                Context context = oContext.get();
                result.addAll(getDbSchemaColumnsResponseRow(context, oTableSchema, oTableName, oColumnName,
                    oColumnOlapType));
            }
        } else {
            result.addAll(contextsListSupplyer.get().stream()
                .map(c -> getDbSchemaColumnsResponseRow(c, oTableSchema, oTableName, oColumnName, oColumnOlapType))
                .flatMap(Collection::stream).toList());
        }

        return result;
    }

    public List<DbSchemaProviderTypesResponseRow> dbSchemaProviderTypes(DbSchemaProviderTypesRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<DbSchemaSchemataResponseRow> dbSchemaSchemata(DbSchemaSchemataRequest request) {
        String catalogName = request.restrictions().catalogName();
        String schemaName = request.restrictions().schemaName();
        String schemaOwner = request.restrictions().schemaOwner();
        List<DbSchemaSchemataResponseRow> result = new ArrayList<>();
        if (catalogName != null) {
            Optional<Context> oContext = contextsListSupplyer.tryGetFirstByName(catalogName);
            if (oContext.isPresent()) {
                Context context = oContext.get();
                result.addAll(getDbSchemaSchemataResponseRow(context, schemaName, schemaOwner));
            }
        } else {
            result.addAll(contextsListSupplyer.get().stream()
                .map(c -> getDbSchemaSchemataResponseRow(c, schemaName, schemaOwner))
                .flatMap(Collection::stream).toList());
        }
        return result;
    }

    public List<DbSchemaSourceTablesResponseRow> dbSchemaSourceTables(DbSchemaSourceTablesRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<DbSchemaTablesResponseRow> dbSchemaTables(DbSchemaTablesRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<DbSchemaTablesInfoResponseRow> dbSchemaTablesInfo(DbSchemaTablesInfoRequest request) {
        // TODO Auto-generated method stub
        return null;
    }


}

