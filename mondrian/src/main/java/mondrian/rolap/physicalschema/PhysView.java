package mondrian.rolap.physicalschema;

import org.eigenbase.xom.NodeDef;

import java.util.List;

/**
 * A relation defined by a SQL string.
 *
 * <p>Column names and types are resolved, in
 * {@link PhysRelationImpl#populateColumns(RolapSchemaLoader, org.eigenbase.xom.NodeDef, int[])},
 * by preparing a query based on the SQL string.
 *
 * <p>In Mondrian's schema file, each {@link Dialect} can have its own SQL
 * string, but here we already know which dialect we are dealing with, so
 * there is a single SQL string.
 */
public class PhysView
    extends PhysRelationImpl
    implements PhysRelation
{
    private final String sqlString;

    /**
     * Creates a view.
     *
     * @param physSchema Schema
     * @param alias Alias
     * @param sqlString SQL string
     */
    public PhysView(
        PhysSchema physSchema,
        String alias,
        String sqlString)
    {
        super(physSchema, alias);
        this.sqlString = sqlString;
        assert sqlString != null && sqlString.length() > 0 : sqlString;
    }

    public PhysRelation cloneWithAlias(String newAlias) {
        PhysView physView = new PhysView(physSchema, newAlias, sqlString);
        physView.addAllColumns(this);
        physView.addAllKeys(this);
        physView.setPopulated(true);
        return physView;
    }

    /**
     * Returns the SQL query that defines this view in the current dialect.
     *
     * @return SQL query
     */
    public String getSqlString() {
        return sqlString;
    }

    public int hashCode() {
        return Util.hashV(0, physSchema, alias, sqlString);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PhysView) {
            PhysView that = (PhysView) obj;
            return this.alias.equals(that.alias)
                && this.sqlString.equals(that.sqlString)
                && this.physSchema.equals(that.physSchema);
        }
        return false;
    }

    protected boolean populateColumns(
        RolapSchemaLoader loader,
        NodeDef xmlNode,
        int[] rowCountAndSize)
    {
        final List<ColumnInfo> columnInfoList =
            physSchema.describe(loader, xmlNode, sqlString);
        if (columnInfoList == null) {
            return false;
        }
        for (ColumnInfo columnInfo : columnInfoList) {
            addColumn(
                new PhysRealColumn(
                    this,
                    columnInfo.name,
                    columnInfo.datatype,
                    null,
                    columnInfo.size));
        }
        final int rowCount = 1; // TODO:
        int rowByteCount = 0;
        for (PhysColumn physColumn : columnsByName.values()) {
            rowByteCount += physColumn.getColumnSize();
        }
        rowCountAndSize[0] = rowCount;
        rowCountAndSize[1] = rowByteCount;
        return true;
    }
}
