package org.eclipse.daanse.olap.rolap.dbmapper.model.api;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;

public interface MappingRealOrCalcColumnDef {

    String name();

    TypeEnum type();

    boolean internalType();
}
