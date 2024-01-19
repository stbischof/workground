package mondrian.rolap.physicalschema;

import mondrian.rolap.SqlStatement;
import mondrian.rolap.sql.SqlQueryBuilder;
import org.eclipse.daanse.db.dialect.api.Datatype;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class PhysExpr {
    /**
     * Returns the SQL string for this expression.
     *
     * <p>The expression is used as a key for cell requests, so this method
     * must be fast. Preferably return a pre-computed result.</p>
     */
    public abstract String toSql();

    /**
     * Calls a callback for each embedded PhysColumn.
     */
    public abstract void foreachColumn(
        Util.Function1<PhysColumn, Void> callback);

    /**
     * Calls a callback for each embedded PhysColumn.
     *
     * @param queryBuilder Query builder
     * @param joiner Joiner
     */
    public final void foreachColumn(
        final SqlQueryBuilder queryBuilder,
        final SqlQueryBuilder.Joiner joiner)
    {
        foreachColumn(
            new Util.Function1<PhysColumn, Void>() {
                public Void apply(PhysColumn column) {
                    joiner.addColumn(queryBuilder, column);
                    return null;
                }
            }
        );
    }

    /**
     * Returns the data type of this expression, or null if not known.
     *
     * @return Data type
     */
    public abstract Datatype getDatatype();

    /**
     * Returns the type to be used for in-memory representation of this
     * expression.
     *
     * @return Internal type
     */
    public abstract SqlStatement.Type getInternalType();

    public Iterable<? extends PhysColumn> columns() {
        final Set<PhysColumn> set = new LinkedHashSet<PhysColumn>();
        foreachColumn(
            null,
            new SqlQueryBuilder.Joiner() {
                public void addColumn(
                    SqlQueryBuilder queryBuilder, PhysColumn column)
                {
                    set.add(column);
                }

                public void addRelation(
                    SqlQueryBuilder queryBuilder, PhysRelation relation)
                {
                }
            });
        return set;
    }
}
