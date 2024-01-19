package mondrian.rolap.physicalschema;

import mondrian.rolap.SqlStatement;
import org.eclipse.daanse.db.dialect.api.Datatype;

public class PhysTextExpr extends PhysExpr {
    private final String text;

    PhysTextExpr(String s) {
        this.text = s;
    }

    public String toSql() {
        return text;
    }

    public void foreachColumn(Util.Function1<PhysColumn, Void> callback) {
        // nothing
    }

    public Datatype getDatatype() {
        return null; // not known
    }

    public SqlStatement.Type getInternalType() {
        return null; // not known
    }
}
