package mondrian.rolap.physicalschema;

public class PhysHop {
    public final PhysRelation relation;
    public final PhysLink link;
    public final boolean forward;

    /**
     * Creates a hop.
     *
     * @param relation Target relation
     * @param link Link from source to target relation
     * @param forward Whether hop is in the default direction of the link
     */
    public PhysHop(
        PhysRelation relation,
        PhysLink link,
        boolean forward)
    {
        assert relation != null;
        // link is null for the first hop in a path
        this.relation = relation;
        this.link = link;
        this.forward = forward;
    }

    public boolean equals(Object obj) {
        return obj instanceof PhysHop
            && relation.equals(((PhysHop) obj).relation)
            && Util.equals(link, ((PhysHop) obj).link)
            && forward == ((PhysHop) obj).forward;
    }

    public int hashCode() {
        return Util.hashV(0, relation, link, forward);
    }

    public final PhysRelation fromRelation() {
        return forward
            ? link.sourceKey.relation
            : link.targetRelation;
    }

    public final PhysRelation toRelation() {
        return forward
            ? link.targetRelation
            : link.sourceKey.relation;
    }
}
