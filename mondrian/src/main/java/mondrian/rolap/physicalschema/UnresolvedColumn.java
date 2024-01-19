package mondrian.rolap.physicalschema;

import org.eclipse.daanse.db.dialect.api.Datatype;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;

public abstract class UnresolvedColumn extends PhysColumn {
    private State state = State.UNRESOLVED;
    private final String tableName;
    private final String name;

    private final MappingSQL xml;

    public UnresolvedColumn(
        PhysRelation relation,
        String tableName,
        String name,
        MappingSQL xml)
    {
        // Boolean datatype is a dummy value, to keep an assert happy.
        super(relation, name, 0, Datatype.BOOLEAN, null);
        assert tableName != null;
        assert name != null;
        this.tableName = tableName;
        this.name = name;
        this.xml = xml;
    }

    public abstract void onResolve(PhysColumn column);

    public abstract String getContext();

    public String toString() {
        return tableName + "." + name;
    }

    public String toSql() {
        throw new UnsupportedOperationException(
            "unresolved column " + this);
    }

    public enum State {
        UNRESOLVED,
        ACTIVE,
        RESOLVED,
        ERROR
    }

    public MappingSQL getXml() {
        return xml;
    }

    public State getState() {
        return state;
    }

    public String getTableName() {
        return tableName;
    }

    public String getName() {
        return name;
    }

    public void setState(State state) {
        this.state = state;
    }
}

