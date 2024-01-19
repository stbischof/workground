package mondrian.rolap.physicalschema;

import org.eclipse.daanse.olap.impl.UnmodifiableArrayList;

import java.util.ArrayList;
import java.util.List;

public class PhysPathBuilder {
    protected final List<PhysHop> hopList = new ArrayList<PhysHop>();

    private PhysPathBuilder() {
    }

    public PhysPathBuilder(PhysRelation relation) {
        this();
        hopList.add(new PhysHop(relation, null, true));
    }

    public PhysPathBuilder(PhysPath path) {
        this();
        hopList.addAll(path.hopList);
    }

    public PhysPathBuilder add(
        PhysKey sourceKey,
        List<PhysColumn> columnList)
    {
        final PhysHop prevHop = hopList.get(hopList.size() - 1);
        add(
            new PhysLink(sourceKey, prevHop.relation, columnList),
            sourceKey.relation,
            true);
        return this;
    }

    public PhysPathBuilder add(
        PhysLink link,
        PhysRelation relation,
        boolean forward)
    {
        return add(new PhysHop(relation, link, forward));
    }

    public PhysPathBuilder add(PhysHop hop)
    {
        hopList.add(hop);
        return this;
    }

    public PhysPathBuilder prepend(
        PhysKey sourceKey,
        List<PhysColumn> columnList)
    {
        final PhysHop prevHop = hopList.get(0);
        prepend(
            new PhysLink(sourceKey, prevHop.relation, columnList),
            sourceKey.relation,
            true);
        return this;
    }

    public PhysPathBuilder prepend(
        PhysLink link,
        PhysRelation relation,
        boolean forward)
    {
        if (hopList.size() == 0) {
            assert link == null;
        } else {
            final PhysHop hop0 = hopList.get(0);
            hopList.set(0, new PhysHop(hop0.relation, link, forward));
        }
        hopList.add(0, new PhysHop(relation, null, true));
        return this;
    }

    public PhysPath done() {
        return new PhysPath(UnmodifiableArrayList.of(hopList));
    }

    @SuppressWarnings({
        "CloneDoesntCallSuperClone",
        "CloneDoesntDeclareCloneNotSupportedException"
    })
    public PhysPathBuilder clone() {
        final PhysPathBuilder pathBuilder = new PhysPathBuilder();
        pathBuilder.hopList.addAll(hopList);
        return pathBuilder;
    }
}
