package mondrian.rolap.physicalschema;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;

import java.util.List;

class UnresolvedCalcColumn extends UnresolvedColumn {

    private final PhysCalcColumn physCalcColumn;
    private final List<PhysExpr> list;
    private final int index;

    /**
     * Creates an unresolved column reference.
     *
     * @param physTable Table that column belongs to
     * @param tableName Name of table
     * @param columnRef Column definition
     * @param sql       SQL
     * @param list      List of expressions
     * @param index     Index within parent table
     */
    public UnresolvedCalcColumn(
        PhysTable physTable,
        String tableName,
        MappingColumn columnRef,
        MappingSQL sql,
        PhysCalcColumn physCalcColumn,
        List<PhysExpr> list,
        int index
    ) {
        super(physTable, tableName, columnRef.name(), sql);
        this.physCalcColumn = physCalcColumn;
        this.list = list;
        this.index = index;
    }

    public String getContext() {
        return ", in definition of calculated column '"
            + physCalcColumn.relation.getAlias() + "'.'"
            + physCalcColumn.name + "'";
    }

    public void onResolve(
        PhysColumn column
    ) {
        list.set(index, column);
        physCalcColumn.compute();
    }
}
