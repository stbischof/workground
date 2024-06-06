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

import org.eclipse.daanse.olap.action.api.UrlAction;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.converter.Converter;
import org.osgi.util.converter.Converters;

import java.util.Map;
import java.util.Optional;

@Component(service = UrlAction.class)
@Designate(factory = true, ocd = UrlActionConfig.class)
public class UrlActionImpl implements UrlAction {

    private static final Converter CONVERTER = Converters.standardConverter();
    private UrlActionConfig config;

    public UrlActionImpl() {

    }

    @Activate
    void activate(Map<String, Object> props) {
        this.config = CONVERTER.convert(props).to(UrlActionConfig.class);
    }

    @Override
    public Optional<String> catalogName() {
        return Optional.ofNullable(config.catalogName());
    }

    @Override
    public Optional<String> schemaName() {
        return Optional.ofNullable(config.schemaName());
    }

    @Override
    public String cubeName() {
        return config.cubeName();
    }

    @Override
    public Optional<String> actionName() {
        return Optional.ofNullable(config.actionName());
    }

    @Override
    public Optional<String> actionCaption() {
        return Optional.ofNullable(config.actionCaption());
    }

    @Override
    public Optional<String> description() {
        return Optional.ofNullable(config.actionDescription());
    }

    @Override
    public String coordinate() {
        return config.actionCoordinate();
    }

    @Override
    public CoordinateTypeEnum coordinateType() {
        return CoordinateTypeEnum.valueOf(config.actionCoordinateType());
    }

    @Override
    public String url() {
        return config.actionUrl();
    }
}
