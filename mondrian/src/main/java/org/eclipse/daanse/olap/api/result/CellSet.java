package org.eclipse.daanse.olap.api.result;
import java.util.List;

public interface CellSet {

    CellSetMetaData getMetaData();

    List<CellSetAxis> getAxes();

    CellSetAxis getFilterAxis();
}
