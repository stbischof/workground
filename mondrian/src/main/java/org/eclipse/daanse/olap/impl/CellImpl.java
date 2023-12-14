package org.eclipse.daanse.olap.impl;

import mondrian.rolap.RolapCell;
import mondrian.rolap.SqlStatement;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.api.result.AllocationPolicy;
import org.eclipse.daanse.olap.api.result.Cell;
import org.eclipse.daanse.olap.api.result.CellSet;
import org.eclipse.daanse.olap.api.result.Property;
import org.eclipse.daanse.olap.api.result.Scenario;
import org.slf4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CellImpl implements Cell {
    private final int[] coordinates;
    private final CellSet olap4jCellSet;
    final RolapCell cell;

    /**
     * Creates a MondrianOlap4jCell.
     *
     * @param coordinates Coordinates
     * @param olap4jCellSet Cell set
     * @param cell Cell in native Mondrian representation
     */
    CellImpl(
        int[] coordinates,
        CellSet olap4jCellSet,
        RolapCell cell)
    {
        assert coordinates != null;
        assert olap4jCellSet != null;
        assert cell != null;
        this.coordinates = coordinates;
        this.olap4jCellSet = olap4jCellSet;
        this.cell = cell;
    }


    public CellSet getCellSet() {
        return olap4jCellSet;
    }

    public RolapCell getRolapCell(){
        return this.cell;
    }


    public int getOrdinal() {
        return (Integer) cell.getPropertyValue(
            mondrian.olap.Property.CELL_ORDINAL.name);
    }

    @Override
    public List<Integer> getCoordinateList() {
        ArrayList<Integer> list = new ArrayList<>(coordinates.length);
        for (int coordinate : coordinates) {
            list.add(coordinate);
        }
        return list;
    }


    public Object getPropertyValue(Property property) {
        // We assume that mondrian properties have the same name as olap4j
        // properties.
        return cell.getPropertyValue(property.getName());
    }


    public boolean isEmpty() {
        // FIXME
        return cell.isNull();
    }

    @Override
    public boolean isError() {
        return cell.isError();
    }

    @Override
    public String getDrillThroughSQL(boolean extendedContext) {
        return null;
    }

    @Override
    public boolean canDrillThrough() {
        return false;
    }

    @Override
    public int getDrillThroughCount() {
        return 0;
    }

    @Override
    public Object getPropertyValue(String propertyName) {
        return null;
    }

    @Override
    public Member getContextMember(Hierarchy hierarchy) {
        return null;
    }

    @Override
    public void setValue(
        Scenario scenario,
        Object newValue,
        AllocationPolicy allocationPolicy,
        Object... allocationArgs
    ) {

    }

    @Override
    public boolean isNull() {
        return cell.isNull();
    }


    public double getDoubleValue() {
        Object o = cell.getValue();
        if (o instanceof Number number) {
            return number.doubleValue();
        }
        throw new RuntimeException("not a number");
    }


    public String getErrorText() {
        Object o = cell.getValue();
        if (o instanceof Throwable throwable) {
            return throwable.getMessage();
        } else {
            return null;
        }
    }

    @Override
    public Object getValue() {
        return cell.getValue();
    }

    @Override
    public String getCachedFormatString() {
        return null;
    }

    @Override
    public String getFormattedValue() {
        return cell.getFormattedValue();
    }


    public ResultSet drillThrough() {
        return drillThroughInternal(
            -1,
            -1,
            new ArrayList<>(),
            false,
            null,
            null);
    }

    /**
     * Executes drill-through on this cell.
     *
     * <p>Not a part of the public API. Package-protected because this method
     * also implements the DRILLTHROUGH statement.
     *
     * @param maxRowCount Maximum number of rows to retrieve, <= 0 if unlimited
     * @param firstRowOrdinal Ordinal of row to skip to (1-based), or 0 to
     *   start from beginning
     * @param fields            List of fields to return, expressed as MDX
     *                          expressions.
     * @param extendedContext   If true, add non-constraining columns to the
     *                          query for levels below each current member.
     *                          This additional context makes the drill-through
     *                          queries easier for humans to understand.
     * @param logger Logger. If not null and debug is enabled, log SQL here
     * @param rowCountSlot Slot into which the number of fact rows is written
     * @return Result set
     */
    ResultSet drillThroughInternal(
        int maxRowCount,
        int firstRowOrdinal,
        List<OlapElement> fields,
        boolean extendedContext,
        Logger logger,
        int[] rowCountSlot)
    {
        if (!cell.canDrillThrough()) {
            return null;
        }
        if (rowCountSlot != null) {
            rowCountSlot[0] = cell.getDrillThroughCount();
        }
        final SqlStatement sqlStmt =
            cell.drillThroughInternal(
                maxRowCount, firstRowOrdinal, fields, extendedContext,
                logger);
        return sqlStmt.getWrappedResultSet();
    }

}
