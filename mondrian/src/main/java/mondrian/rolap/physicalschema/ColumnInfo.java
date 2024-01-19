package mondrian.rolap.physicalschema;

import org.eclipse.daanse.db.dialect.api.Datatype;

class ColumnInfo {
    String name;
    Datatype datatype;
    int size;

    public ColumnInfo(String name, Datatype datatype, int size) {
        this.name = name;
        this.datatype = datatype;
        this.size = size;
    }
}
