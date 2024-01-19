package mondrian.rolap.physicalschema;

import mondrian.rolap.SqlStatement;
import org.eclipse.daanse.db.dialect.api.Datatype;

public abstract class PhysColumn extends PhysExpr {
    public final PhysRelation relation;
    public final String name;
    Datatype datatype; // may be null, temporarily
    protected final int columnSize;
    private final int ordinal;
    SqlStatement.Type internalType; // may be null

    public PhysColumn(
        PhysRelation relation,
        String name,
        int columnSize,
        Datatype datatype,
        SqlStatement.Type internalType)
    {
        assert relation != null;
        assert name != null;
        this.name = name;
        this.relation = relation;
        this.columnSize = columnSize;
        this.datatype = datatype;
        this.internalType = internalType;
        this.ordinal = relation.getSchema().getColumnCount() + 1;
        relation.getSchema().setColumnCount(this.ordinal);
    }

    public String toString() {
        return toSql();
    }

    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }

    public Datatype getDatatype() {
        return datatype;
    }

    public SqlStatement.Type getInternalType() {
        return internalType;
    }

    public void foreachColumn(Util.Function1<PhysColumn, Void> callback) {
        callback.apply(this);
    }

    /**
     * Returns the size in bytes of the column in the database.
     */
    public int getColumnSize() {
        return columnSize;
    }

    /**
     * Ordinal of column; non-negative, and unique within its schema.
     *
     * @return Ordinal of column.
     */
    public final int ordinal() {
        return ordinal;
    }

    public void setInternalType(SqlStatement.Type internalType) {
        this.internalType = internalType;
    }

    PhysColumn cloneWithAlias(PhysRelation newRelation) {
        return this;
    }
}
