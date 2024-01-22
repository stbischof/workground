package mondrian.rolap.physicalschema;

import org.eigenbase.xom.NodeDef;

import java.util.ArrayList;
import java.util.List;

/**
 * Relation defined by a fixed set of explicit row values. The number of
 * rows is generally small.
 */
public class PhysInlineTable
    extends PhysRelationImpl
    implements PhysRelation
{

    final List<String[]> rowList = new ArrayList<String[]>();

    /**
     * Creates an inline table.
     *
     * @param physSchema Schema
     * @param alias Name of inline table within schema
     */
    PhysInlineTable(
        PhysSchema physSchema,
        String alias)
    {
        super(physSchema, alias);
        assert alias != null;
    }

    public PhysRelation cloneWithAlias(String newAlias) {
        PhysInlineTable physInlineTable =
            new PhysInlineTable(this.physSchema, newAlias);
        physInlineTable.addAllColumns(this);
        physInlineTable.addAllKeys(this);
        physInlineTable.setPopulated(true);
        physInlineTable.setRowList(rowList);
        return physInlineTable;
    }

    private void setRowList(List<String[]> rowList) {
        this.rowList.addAll(rowList);
    }

    @Override
    public String toString() {
        return alias;
    }

    public int hashCode() {
        return Util.hashV(0, physSchema, alias);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PhysInlineTable) {
            PhysInlineTable that = (PhysInlineTable) obj;
            return this.alias.equals(that.alias)
                && this.physSchema.equals(that.physSchema);
        }
        return false;
    }

    protected boolean populateColumns(
        RolapSchemaLoader loader, int[] rowCountAndSize)
    {
        // not much to do; was populated on creation
        rowCountAndSize[0] = rowList.size();
        rowCountAndSize[1] = columnsByName.size() * 4;
        return true;
    }

    public List<String[]> getRowList() {
        return rowList;
    }
}
