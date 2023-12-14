package org.eclipse.daanse.olap.api.result;

import org.eclipse.daanse.olap.api.Statement;

import java.util.List;

public interface CellSet {

    CellSetMetaData getMetaData();

    List<CellSetAxis> getAxes();

    CellSetAxis getFilterAxis();

    Cell getCell(List<Integer> pos);

    Statement getStatement();
}
