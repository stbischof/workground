package org.eclipse.daanse.olap.api.result;

import org.eclipse.daanse.olap.api.element.Hierarchy;

import java.util.List;

public interface CellSetAxisMetaData {
    IAxis getAxisOrdinal();

    List<Hierarchy> getHierarchies();

    List<Property> getProperties();
}
