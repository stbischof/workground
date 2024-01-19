package mondrian.rolap.physicalschema;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

/**
 * A path is a sequence of {@link PhysHop hops}.
 *
 * <p>It connects a pair of {@link PhysRelation relations} with a sequence
 * of link traversals. In general, a path between relations R<sub>1</sub>
 * and R<sub>n</sub> consists of the following hops:</p>
 *
 * <pre>
 *    { Hop(R<sub>1</sub>, null),
 *      Hop(R<sub>2</sub>, Link(R<sub>1</sub>, R<sub>2</sub>)),
 *      Hop(R<sub>3</sub>, Link(R<sub>2</sub>, R<sub>3</sub>)),
 *      ...
 *      Hop(R<sub>n</sub>, Link(R<sub>n-1</sub>, R<sub>n</sub>)) }
 * </pre>
 *
 * <p>Paths are immutable. The best way to create them is to uSe a
 * {@link PhysPathBuilder}.</p>
 *
 * <p>REVIEW: Is it worth making paths canonical? That is, if two paths
 * within a schema are equal, then they will always be the same object.</p>
 */
public class PhysPath {
    public final List<PhysHop> hopList;

    public static final PhysPath EMPTY =
        new PhysPath(Collections.<PhysHop>emptyList());

    /**
     * Creates a path.
     *
     * @param hopList List of hops
     */
    public PhysPath(List<PhysHop> hopList) {
        this.hopList = hopList;
        for (int i = 0; i < hopList.size(); i++) {
            PhysHop hop = hopList.get(i);
            if (i == 0) {
                assert hop.link == null;
            } else {
                assert hop.relation == hop.fromRelation();
                assert hopList.get(i - 1).relation == hop.toRelation();
            }
        }
    }

    /**
     * Returns list of links.
     *
     * @return list of links
     */
    public List<PhysLink> getLinks() {
        return new AbstractList<PhysLink>() {
            public PhysLink get(int index) {
                return hopList.get(index + 1).link;
            }

            public int size() {
                return hopList.size() - 1;
            }
        };
    }
}
