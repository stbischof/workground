package mondrian.rolap.physicalschema;

import mondrian.olap.Util;
import mondrian.rolap.SqlStatement;
import org.eclipse.daanse.db.dialect.api.Datatype;

public final class PhysRealColumn extends PhysColumn {
    private final String sql;

    PhysRealColumn(
        PhysRelation relation,
        String name,
        Datatype datatype,
        SqlStatement.Type internalType,
        int columnSize)
    {
        super(relation, name, columnSize, datatype, internalType);
        this.sql = deriveSql();
    }

    @Override
    PhysColumn cloneWithAlias(PhysRelation newRelation) {
        return new PhysRealColumn(
            newRelation, name, datatype, internalType, columnSize);
    }

    protected String deriveSql() {
        return new StringBuilder(relation.getSchema().getDialect().quoteIdentifier(
            relation.getAlias()))
            .append('.')
            .append(relation.getSchema().getDialect().quoteIdentifier(name)).toString();
    }

    public int hashCode() {
        return Util.hash(name.hashCode(), relation);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PhysRealColumn) {
            PhysRealColumn that = (PhysRealColumn) obj;
            return name.equals(that.name)
                && relation.equals(that.relation);
        }
        return false;
    }

    public String toSql() {
        return sql;
    }
}
