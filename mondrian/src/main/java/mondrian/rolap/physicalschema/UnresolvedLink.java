package mondrian.rolap.physicalschema;

import java.util.List;

class UnresolvedLink {
    final PhysKey sourceKey;
    final PhysRelation targetRelation;
    final List<PhysColumn> columnList;

    public UnresolvedLink(
        PhysKey sourceKey,
        PhysRelation targetRelation,
        List<PhysColumn> columnList)
    {
        this.sourceKey = sourceKey;
        this.targetRelation = targetRelation;
        this.columnList = columnList;
    }
}
