package org.eclipse.daanse.olap.action.impl;

import org.eclipse.daanse.olap.action.api.DrillThroughAction;
import org.eclipse.daanse.xmla.api.common.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.converter.Converter;
import org.osgi.util.converter.Converters;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.eclipse.daanse.olap.action.impl.DrillThroughUtils.getCoordinateElements;

@Component(service = DrillThroughAction.class)
@Designate(factory = true, ocd = DrillThroughActionConfig.class)
public class DrillThroughActionImpl implements DrillThroughAction {

    private static final Converter CONVERTER = Converters.standardConverter();
    private DrillThroughActionConfig config;

    @Activate
    void activate(Map<String, Object> props) {
        this.config = CONVERTER.convert(props).to(DrillThroughActionConfig.class);
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
    public String content(String coordinate, String cubeName) {
        List<String> coordinateElements = getCoordinateElements(coordinate);
        return DrillThroughUtils.getDrillThroughQueryByColumns(coordinateElements, catalogs().orElse(List.of()), cubeName);
    }

    @Override
    public ActionTypeEnum actionType() {
        return ActionTypeEnum.DRILL_THROUGH;
    }

    @Override
    public Optional<List<String>> catalogs() {
        return Optional.ofNullable(config.columns());
    }
}
