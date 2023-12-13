package org.eclipse.daanse.olap.impl;

import mondrian.rolap.RolapCell;
import org.eclipse.daanse.olap.api.element.Hierarchy;
import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.element.OlapElement;
import org.eclipse.daanse.olap.api.result.Cell;
import org.olap4j.AllocationPolicy;
import org.olap4j.Scenario;

import java.sql.ResultSet;
import java.util.List;

public class CellImpl implements Cell {

    public CellImpl(int[] pos, CellSetImpl cellSet, RolapCell cell) {
    }

    @Override
    public List<Integer> getCoordinateList() {
        return null;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String getCachedFormatString() {
        return null;
    }

    @Override
    public String getFormattedValue() {
        return null;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isError() {
        return false;
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

    public ResultSet drillThroughInternal(int maxRowCount, int firstRowOrdinal, List<OlapElement> fields, boolean b, Object o, int[] rowCountSlot) {
        //TODO
        return null;
    }
}
