package org.eclipse.daanse.olap.impl;

import org.eclipse.daanse.olap.api.Statement;
import org.eclipse.daanse.olap.api.element.Cube;
import org.eclipse.daanse.olap.api.query.component.Query;
import org.eclipse.daanse.olap.api.result.CellSetAxisMetaData;
import org.eclipse.daanse.olap.api.result.CellSetMetaData;
import org.eclipse.daanse.olap.api.result.Property;

import java.util.List;

public class CellSetMetaDataImpl implements CellSetMetaData {

    private final Statement statement;
    private final Query query;

    public CellSetMetaDataImpl(Statement statement, Query query) {
        this.statement = statement;
        this.query = query;
    }

    @Override
    public List<Property> getCellProperties() {
        return null;
    }

    @Override
    public Cube getCube() {
        return null;
    }

    @Override
    public List<CellSetAxisMetaData> getAxesMetaData() {
        return null;
    }

    @Override
    public CellSetAxisMetaData getFilterAxisMetaData() {
        return null;
    }
}
