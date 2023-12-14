package org.eclipse.daanse.olap.impl;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.result.CellSetMetaData;

public class PreparedStatement extends StatementImpl {

    public PreparedStatement(Connection connection) {
        super(connection);
    }

    public CellSetMetaData getCellSetMetaData() {
        //TODO
        return null;
    }
}
