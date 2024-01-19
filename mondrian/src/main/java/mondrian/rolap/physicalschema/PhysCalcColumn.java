package mondrian.rolap.physicalschema;

import mondrian.rolap.SqlStatement;
import mondrian.rolap.sql.SqlQuery;
import org.eclipse.daanse.db.dialect.api.Datatype;
import org.eigenbase.xom.NodeDef;

import java.util.List;

public final class PhysCalcColumn extends PhysColumn {
    private RolapSchemaLoader loader; // cleared once compute succeeds
    private NodeDef xmlNode; // cleared once compute succeeds
    final List<PhysExpr> list;
    private String sql;

    PhysCalcColumn(
        RolapSchemaLoader loader,
        NodeDef xmlNode,
        PhysRelation table,
        String name,
        Datatype datatype,
        SqlStatement.Type internalType,
        List<PhysExpr> list)
    {
        super(table, name, 4, datatype, internalType);
        this.loader = loader;
        this.xmlNode = xmlNode;
        this.list = list;
        compute();
    }

    public void compute() {
        if (loader != null
            && !list.isEmpty()
            && getUnresolvedColumnCount() == 0)
        {
            sql = deriveSql();
            if (datatype == null) {
                final PhysSchema physSchema = relation.getSchema();
                final SqlQuery query = new SqlQuery(physSchema.getDialect());
                query.addSelect(sql, null);
                query.addFrom(relation, relation.getAlias(), true);
                final List<ColumnInfo> columnInfoList =
                    physSchema.describe(loader, xmlNode, query.toSql());
                if (columnInfoList != null
                    && columnInfoList.size() == 1)
                {
                    datatype = columnInfoList.get(0).datatype;
                }
            }
            loader = null;
            xmlNode = null;
        }
    }

    private int getUnresolvedColumnCount() {
        int unresolvedCount = 0;
        for (PhysExpr expr : list) {
            if (expr instanceof UnresolvedColumn) {
                ++unresolvedCount;
            }
        }
        return unresolvedCount;
    }

    public String toSql() {
        return sql;
    }

    protected String deriveSql() {
        final StringBuilder buf = new StringBuilder();
        for (PhysExpr expr : list) {
            buf.append(expr.toSql());
        }
        return buf.toString();
    }

    @Override
    public void foreachColumn(Util.Function1<PhysColumn, Void> callback) {
        for (PhysExpr physExpr : list) {
            physExpr.foreachColumn(callback);
        }
    }

    public List<PhysExpr> getList() {
        return list;
    }
}
