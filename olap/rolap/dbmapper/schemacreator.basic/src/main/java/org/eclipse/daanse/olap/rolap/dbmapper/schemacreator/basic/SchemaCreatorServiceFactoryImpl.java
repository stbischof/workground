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
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.basic;

import org.eclipse.daanse.common.jdbc.db.api.DatabaseService;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaCreatorService;
import org.eclipse.daanse.olap.rolap.dbmapper.schemacreator.api.SchemaCreatorServiceFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.sql.DataSource;

@Component(service = SchemaCreatorServiceFactory.class, scope = ServiceScope.SINGLETON)
public class SchemaCreatorServiceFactoryImpl implements SchemaCreatorServiceFactory {

    @Reference
    DatabaseService databaseService;

    @Override
    public SchemaCreatorService create(DataSource dataSource) {
        return new SchemaCreatorServiceImpl(dataSource, databaseService);
    }

}
