package mondrian.rolap.physicalschema;

import mondrian.rolap.SqlStatement;
import org.eclipse.daanse.db.dialect.api.Datatype;

import java.util.List;

/** experimental - alternative to {@link PhysCalcColumn} */
public final class PhysCalcExpr extends PhysExpr {
    final List<PhysExpr> list;
    private final String sql;

    PhysCalcExpr(
        List<PhysExpr> list)
    {
        assert list != null;
        this.list = list;
        this.sql = deriveSql();
    }

    public Datatype getDatatype() {
        return null;
    }

    public SqlStatement.Type getInternalType() {
        return null;
    }

    public String toSql() {
        return sql;
    }

    protected String deriveSql() {
        final StringBuilder buf = new StringBuilder();
        for (PhysExpr o : list) {
            buf.append(o.toSql());
        }
        return buf.toString();
    }

    public void foreachColumn(Util.Function1<PhysColumn, Void> fn) {
        for (Object o : list) {
            if (o instanceof PhysExpr) {
                ((PhysExpr) o).foreachColumn(fn);
            }
        }
    }
}
