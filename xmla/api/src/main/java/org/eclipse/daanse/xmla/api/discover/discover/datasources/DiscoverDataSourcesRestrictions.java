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
package org.eclipse.daanse.xmla.api.discover.discover.datasources;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.AuthenticationModeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ProviderTypeEnum;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface DiscoverDataSourcesRestrictions {
    String RESTRICTIONS_DATA_SOURCE_NAME = "DataSourceName";
    String RESTRICTIONS_DATA_SOURCE_DESCRIPTION = "DataSourceDescription";
    String RESTRICTIONS_URL = "URL";
    String RESTRICTIONS_DATA_SOURCE_INFO = "DataSourceInfo";
    String RESTRICTIONS_PROVIDER_NAME = "ProviderName";
    String RESTRICTIONS_PROVIDER_TYPE = "ProviderType";
    String RESTRICTIONS_AUTHENTICATION_MODE = "AuthenticationMode";

    /**
     * @return The name of the data source.
     */
    @Restriction(name = RESTRICTIONS_DATA_SOURCE_NAME, type = XSD_STRING, order = 0)
    String dataSourceName();

    /**
     * @return The description of the data source.
     */
    @Restriction(name = RESTRICTIONS_DATA_SOURCE_DESCRIPTION, type = XSD_STRING, order = 1)
    Optional<String> dataSourceDescription();

    /**
     * @return The unique path of the data source.
     */
    Optional<String> url();

    /**
     * @return A string that contains any additional information required to
     * connect to the data source.
     */
    @Restriction(name = RESTRICTIONS_DATA_SOURCE_INFO, type = XSD_STRING, order = 2)
    Optional<String> dataSourceInfo();

    /**
     * @return The name of the provider for the data source.
     */
    @Restriction(name = RESTRICTIONS_PROVIDER_NAME, type = XSD_STRING, order = 3)
    String providerName();

    /**
     * @return This array specifies the types of data supported by the
     * server. It can include one or more of the following types:
     * MDP: multidimensional data provider.
     * TDP: tabular data provider.
     * DMP: data mining provider (implements the OLE for DB
     * for Data Mining specification).
     */
    @Restriction(name = RESTRICTIONS_PROVIDER_TYPE, type = XSD_STRING, order = 4)
    Optional<ProviderTypeEnum> providerType();

    /**
     * @return A string that specifies what type of security mode the data
     * source uses. Values can be one of the following:
     * Unauthenticated: No user ID or password has to be
     * sent.
     * Authenticated: User ID and password MUST be included
     * in the information required to connect to the data
     * source.
     * Integrated: The data source uses the underlying
     * security to determine authorization.
     */
    @Restriction(name = RESTRICTIONS_AUTHENTICATION_MODE, type = XSD_STRING, order = 5)
    Optional<AuthenticationModeEnum> authenticationMode();
}
