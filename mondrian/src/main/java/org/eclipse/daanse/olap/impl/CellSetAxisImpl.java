package org.eclipse.daanse.olap.impl;

import mondrian.rolap.RolapConnection;
import mondrian.server.Locus;
import org.eclipse.daanse.olap.api.query.component.AxisOrdinal;
import org.eclipse.daanse.olap.api.query.component.QueryAxis;
import org.eclipse.daanse.olap.api.result.Axis;
import org.eclipse.daanse.olap.api.result.IAxis;
import org.eclipse.daanse.olap.api.result.CellSet;
import org.eclipse.daanse.olap.api.result.CellSetAxis;
import org.eclipse.daanse.olap.api.result.CellSetAxisMetaData;
import org.eclipse.daanse.olap.api.result.Position;

import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;

public class CellSetAxisImpl implements CellSetAxis {

    private final CellSet cellSet;
    private final QueryAxis queryAxis;
    private final Axis axis;

    public CellSetAxisImpl(CellSet cellSet, QueryAxis queryAxis, Axis axis) {
        assert cellSet != null;
        assert queryAxis != null;
        assert axis != null;

        this.cellSet  = cellSet;
        this.queryAxis = queryAxis;
        this.axis = axis;
    }

    @Override
    public IAxis getAxisOrdinal() {
        return IAxis.Factory.forOrdinal(
            queryAxis.getAxisOrdinal().logicalOrdinal());

    }

    @Override
    public CellSet getCellSet() {
        return cellSet;
    }

    @Override
    public CellSetAxisMetaData getAxisMetaData() {
        final AxisOrdinal axisOrdinal = queryAxis.getAxisOrdinal();
        if (axisOrdinal.isFilter()) {
            return cellSet.getMetaData().getFilterAxisMetaData();
        } else {
            return cellSet.getMetaData().getAxesMetaData().get(
                axisOrdinal.logicalOrdinal());
        }
    }

    @Override
    public List<Position> getPositions() {
        return new AbstractList<>() {
            @Override
            public Position get(final int index) {
                return new PositionImpl(axis.getTupleList(), index);
            }

            @Override
            public int size() {
                return Locus.execute(
                    (RolapConnection) cellSet.getStatement().getConnection(),
                    "Getting List<Position>.size", new Locus.Action<Integer>() {
                        @Override
                        public Integer execute() {
                            return axis.getTupleList().size();
                        }
                    });
            }
        };

    }

    @Override
    public int getPositionCount() {
        return getPositions().size();
    }

    @Override
    public ListIterator<Position> iterator() {
        return getPositions().listIterator();
    }
}
