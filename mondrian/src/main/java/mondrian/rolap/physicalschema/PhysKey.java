package mondrian.rolap.physicalschema;

import java.util.List;

/**
 * A column (later, perhaps a collection of columns) that identifies a
 * record in a table. The main purpose is as a target for a
 * {@link mondrian.rolap.RolapSchema.PhysLink}.
 *
 * <p>Unlike a primary or unique key in a database, a PhysKey is
 * not necessarily unique. For instance, the time dimension table may have
 * one record per day, but a particular fact table may link at the month
 * level. This is fine because Mondrian automatically eliminates duplicates
 * when reading any level.
 *
 * <p>REVIEW: Should one of the keys be flagged as the 'main' key of a
 * relation? Should keys be flagged as 'unique'?
 */
public class PhysKey {
    final PhysRelation relation;
    final List<PhysColumn> columnList;
    final String name;

    /**
     * Creates a PhysKey.
     *
     * @param relation Relation that the key belongs to
     * @param name Name of key
     * @param columnList List of columns
     */
    public PhysKey(
        PhysRelation relation,
        String name,
        List<PhysColumn> columnList)
    {
        assert relation != null;
        assert name != null;
        assert columnList != null;
        this.relation = relation;
        this.name = name;
        this.columnList = columnList;
    }

    @Override
    public int hashCode() {
        return Util.hashV(
            0,
            relation,
            columnList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PhysKey) {
            final PhysKey that = (PhysKey) obj;
            return this.relation.equals(that.relation)
                && this.columnList.equals(that.columnList);
        }
        return false;
    }

    public String toString() {
        return "[Key " + relation + " (" + columnList + ")]";
    }
}
