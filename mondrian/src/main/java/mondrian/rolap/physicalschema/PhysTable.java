package mondrian.rolap.physicalschema;

import mondrian.rolap.RolapConnection;
import mondrian.rolap.aggmatcher.JdbcSchema;
import org.eigenbase.xom.NodeDef;

import java.sql.SQLException;
import java.util.Map;

public class PhysTable extends PhysRelationImpl {

    final String schemaName;
    final String name;
    private Map<String, String> hintMap;
    private long rowCount;
    private Hook hook;

    /**
     * Creates a table.
     *
     * <p>Does not populate the column definitions from JDBC; see
     * {@link mondrian.rolap.RolapSchema.PhysRelationImpl#ensurePopulated}
     * for that.
     *
     * <p>The {@code hintMap} parameter is a map from hint type to hint
     * text. It is never null, but frequently empty. It is treated as
     * immutable; the constructor does not clone the collection. How to
     * generate hints into SQL is up to the {@link Dialect}.
     *
     * @param physSchema Schema
     * @param schemaName Schema name
     * @param name       Table name
     * @param alias      Table alias that identifies this use of the table, must
     *                   be unique within relations in this schema
     * @param hintMap    Map from hint type to hint text
     */
    public PhysTable(
        PhysSchema physSchema,
        String schemaName,
        String name,
        String alias,
        Map<String, String> hintMap
    ) {
        super(physSchema, alias);
        this.schemaName = schemaName;
        this.name = name;
        this.hintMap = hintMap;
        assert name != null;
        assert alias != null;
    }

    public PhysRelation cloneWithAlias(String newAlias) {
        PhysTable physTable = new PhysTable(
            physSchema, schemaName, name, newAlias, hintMap);
        physTable.addAllColumns(this);
        physTable.addAllKeys(this);
        physTable.setPopulated(true);
        physTable.setRowCount(this.getRowCount());
        return physTable;
    }

    private void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public String toString() {
        return (schemaName == null ? "" : (schemaName + '.'))
            + name
            + (name.equals(alias) ? "" : (" as " + alias));
    }

    public int hashCode() {
        return Util.hashV(
            0, physSchema, schemaName, name, alias);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PhysTable) {
            PhysTable that = (PhysTable) obj;
            return alias.equals(that.alias)
                && name.equals(that.name)
                && schemaName.equals(that.schemaName)
                && physSchema.equals(that.physSchema);
        }
        return false;
    }

    /**
     * Returns the name of the database schema this table resides in.
     *
     * @return name of database schema, may be null
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * Returns the name of the table in the database.
     *
     * @return table name
     */
    public String getName() {
        return name;
    }

    protected boolean populateColumns(
        RolapSchemaLoader loader, int[] rowCountAndSize
    ) {
        JdbcSchema.Table jdbcTable =
            physSchema.jdbcSchema.getTable(name);
        if (jdbcTable == null) {
            if (hook == null) {
                loader.getHandler().warning(
                    "Table '" + name + "' does not exist in database.");
                return false;
            }
            hook.apply(
                this,
                loader.getSchema().getInternalConnection());
            hook = null;
            try {
                jdbcTable = physSchema.jdbcSchema.reloadTable(name);
            } catch (SQLException e) {
                throw Util.newError(
                    "Error while re-loading table '" + name + "'");
            }
        }
        try {
            jdbcTable.load();
        } catch (SQLException e) {
            throw Util.newError(
                "Error while loading columns of table '" + name + "'");
        }

        rowCount =
            physSchema.statistic.getRelationCardinality(
                this,
                alias,
                -1);

        for (JdbcSchema.Table.Column jdbcColumn : jdbcTable.getColumns()) {
            PhysColumn column =
                columnsByName.get(
                    jdbcColumn.getName());
            if (column == null) {
                column =
                    new PhysRealColumn(
                        this,
                        jdbcColumn.getName(),
                        jdbcColumn.getDatatype(),
                        null,
                        jdbcColumn.getColumnSize());
                addColumn(column);
            }
        }
        return true;
    }

    @Override
    public long getRowCount() {
        return rowCount;
    }

    public Map<String, String> getHintMap() {
        return hintMap;
    }

    public void setHook(Hook hook) {
        this.hook = hook;
    }

    interface Hook {

        boolean apply(
            PhysTable table,
            RolapConnection connection
        );
    }
}


