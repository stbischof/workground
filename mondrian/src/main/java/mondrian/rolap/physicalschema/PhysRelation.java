package mondrian.rolap.physicalschema;

import java.util.Collection;
import java.util.List;

public interface PhysRelation {

    /**
     * Returns the column in this relation with a given name. If the column
     * is not found, throws an error (if {@code fail} is true) or returns
     * null (if {@code fail} is false).
     *
     * @param columnName Column name
     * @param fail Whether to fail if column is not found
     * @return Column, or null if column is not found and fail is false
     */
    PhysColumn getColumn(String columnName, boolean fail);

    String getAlias();

    PhysSchema getSchema();

    /**
     * Defines a key in this relation.
     *
     * @param keyName Name of the key; must not be null, by convention, the
     *     key is called "primary" if user did not explicitly name it
     * @param keyColumnList List of columns in the key. May be empty if
     *     the columns have not been resolved yet
     * @return Key
     */
    PhysKey addKey(String keyName, List<PhysColumn> keyColumnList);

    /**
     * Looks up a key by the constituent columns, optionally creating the
     * key if not found.
     *
     * <p>Returns null if the key is not found and {@code add} is false.
     *
     * @param physColumnList Key columns
     * @param add Whether to add if not found
     * @return Key, if found or created, otherwise null
     */
    PhysKey lookupKey(List<PhysColumn> physColumnList, boolean add);

    /**
     * Looks up a key by name.
     *
     * @param key Key name
     * @return Key, or null if not found
     */
    PhysKey lookupKey(String key);

    Collection<PhysKey> getKeyList();

    /**
     * Returns the volume of the table. (Number of rows multiplied by the
     * size of a row in bytes.)
     */
    long getVolume();

    /**
     * Returns the number of rows in the table.
     */
    long getRowCount();

    void addColumn(PhysColumn column);

    PhysRelation cloneWithAlias(String newAlias);
}
