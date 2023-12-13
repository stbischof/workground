package org.eclipse.daanse.olap.impl;

import mondrian.olap.MondrianException;
import mondrian.rolap.RolapCell;
import mondrian.server.Statement;
import org.eclipse.daanse.olap.api.result.Cell;
import org.eclipse.daanse.olap.api.result.CellSet;
import org.eclipse.daanse.olap.api.result.CellSetAxis;
import org.eclipse.daanse.olap.api.result.CellSetMetaData;
import org.eclipse.daanse.olap.api.result.Result;

import java.util.ArrayList;
import java.util.List;

public class CellSetImpl implements CellSet {

    private CellSetMetaData metaData;
    private List<CellSetAxis> axisList = new ArrayList<>();
    private CellSetAxis filterAxis;
    private Result result;

    public CellSetImpl(StatementImpl statement) {
    }

    @Override
    public CellSetMetaData getMetaData() {
        return this.metaData;
    }

    @Override
    public List<CellSetAxis> getAxes() {
        return axisList;
    }

    @Override
    public CellSetAxis getFilterAxis() {
        return filterAxis;
    }

    @Override
    public Cell getCell(List<Integer> coordinates) {
        int[] coords = new int[coordinates.size()];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = coordinates.get(i);
        }
        return getCellInternal(coords);
    }

    @Override
    public Statement getStatement() {
        return null;
    }

    private Cell getCellInternal(int[] pos) {
        RolapCell cell;
        try {
            cell = (RolapCell) result.getCell(pos);
        } catch (MondrianException e) {
            if (e.getMessage().indexOf("coordinates out of range") >= 0) {
                int[] dimensions = new int[getAxes().size()];
                for (int i = 0; i < axisList.size(); i++) {
                    dimensions[i] = axisList.get(i).getPositions().size();
                }
                throw new IndexOutOfBoundsException(
                    new StringBuilder("Cell coordinates (").append(getCoordsAsString(pos))
                        .append(") fall outside CellSet bounds (")
                        .append(getCoordsAsString(dimensions)).append(")").toString());
            } else if (e.getMessage().indexOf(
                "coordinates should have dimension") >= 0)
            {
                throw new IllegalArgumentException(
                    "Cell coordinates should have dimension "
                        + axisList.size());
            } else {
                throw e;
            }
        }
        return new CellImpl(pos, this, cell);
    }

    private static String getCoordsAsString(int[] pos) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            int po = pos[i];
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(po);
        }
        return buf.toString();
    }

    public void close() {
    }

    public void execute() {
    }
}
