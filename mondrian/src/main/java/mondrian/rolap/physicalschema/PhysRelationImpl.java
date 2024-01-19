package mondrian.rolap.physicalschema;

import mondrian.olap.Util;
import org.eigenbase.xom.NodeDef;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

abstract class PhysRelationImpl implements PhysRelation {
    final PhysSchema physSchema;
    final String alias;
    final LinkedHashMap<String, PhysColumn> columnsByName =
        new LinkedHashMap<String, PhysColumn>();
    final LinkedHashMap<String, PhysKey> keysByName =
        new LinkedHashMap<String, PhysKey>();
    private boolean populated;
    private int totalColumnByteCount;
    private long rowCount;

    PhysRelationImpl(
        PhysSchema physSchema,
        String alias)
    {
        this.alias = alias;
        this.physSchema = physSchema;
    }

    public LinkedHashMap<String, PhysColumn> getColumnsByName() {
        return columnsByName;
    }

    public void setPopulated(boolean populated) {
        this.populated = populated;
    }

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public PhysSchema getSchema() {
        return physSchema;
    }

    void addAllColumns(PhysRelationImpl relation) {
        for (PhysColumn column : relation.getColumnsByName().values()) {
            addColumn(column.cloneWithAlias(this));
        }
    }

    void addAllKeys(PhysRelationImpl relation) {
        for (PhysKey physKey : relation.getKeyList()) {
            addKey(physKey.name, physKey.columnList);
        }
    }

    public PhysColumn getColumn(String columnName, boolean fail) {
        PhysColumn column = columnsByName.get(columnName);

        if (column == null && fail) {
            throw Util.newError(
                "Column '" + columnName + "' not found in relation '"
                    + this + "'");
        }
        return column;
    }

    public String getAlias() {
        return alias;
    }

    public Collection<PhysKey> getKeyList() {
        return keysByName.values();
    }

    public long getVolume() {
        return getTotalColumnSize() * getRowCount();
    }

    protected int getTotalColumnSize() {
        return totalColumnByteCount;
    }

    public long getRowCount() {
        return rowCount;
    }

    public PhysKey lookupKey(
        List<PhysColumn> physColumnList, boolean add)
    {
        for (PhysKey key : keysByName.values()) {
            if (key.columnList.equals(physColumnList)) {
                return key;
            }
        }
        if (add) {
            // generate a name of the key, unique within the table
            int i = keysByName.size();
            String keyName;
            for (;;) {
                keyName = "key$" + i;
                if (!keysByName.containsKey(keyName)) {
                    break;
                }
                ++i;
            }
            return addKey(keyName, physColumnList);
        }
        return null;
    }

    public PhysKey lookupKey(String keyName) {
        return keysByName.get(keyName);
    }

    public PhysKey addKey(String keyName, List<PhysColumn> keyColumnList) {
        final PhysKey key = new PhysKey(this, keyName, keyColumnList);
        final PhysKey previous = keysByName.put(keyName, key);
        if (previous != null) {
            // OK if the table already has a key, as long as it is
            // identical.
            assert previous.equals(key);
            keysByName.put(keyName, previous);
            return previous;
        }
        return key;
    }

    /**
     * Loads this table's column definitions from the schema, if that has
     * not been done already. Returns whether the columns were successfully
     * populated this time or previously.
     *
     * <p>If the table does not exist or the view is invalid, returns false,
     * and calls {@link mondrian.rolap.RolapSchemaLoader.Handler#warning} to indicate
     * the problem.
     *
     * @param loader Schema loader
     * @param xmlNode XML element
     * @return whether was populated successfully this call or previously
     */
    public boolean ensurePopulated(
        RolapSchemaLoader loader,
        NodeDef xmlNode)
    {
        if (!populated) {
            final int[] rowCountAndSize = new int[2];
            populated = populateColumns(loader, xmlNode, rowCountAndSize);
            rowCount = rowCountAndSize[0];
            totalColumnByteCount = rowCountAndSize[1];
        }
        return populated;
    }

    /**
     * Populates the columns of a table by querying JDBC metadata.
     *
     * <p>Returns whether populated successfully. If there was an error (say
     * if table was not found or view had an error), posts a warning and
     * returns false.
     *
     * @return Whether table was found
     * @param loader Schema (for logging errors)
     * @param xmlNode XML element
     * @param rowCountAndSize Output array, to hold the number of rows in
     */
    protected abstract boolean populateColumns(
        RolapSchemaLoader loader,
        NodeDef xmlNode,
        int[] rowCountAndSize);

    public void addColumn(PhysColumn column) {
        columnsByName.put(
            column.name,
            column);
    }
}


