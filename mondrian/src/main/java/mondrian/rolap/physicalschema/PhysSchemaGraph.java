package mondrian.rolap.physicalschema;

import mondrian.rolap.util.DirectedGraph;
import mondrian.util.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A view onto a schema containing all of the nodes (relations), all of
 * the arcs (directed links between relations) and perhaps some extra arcs.
 */
public class PhysSchemaGraph {
    private final PhysSchema physSchema;

    private final DirectedGraph<PhysRelation, PhysLink> graph =
        new DirectedGraph<PhysRelation, PhysLink>();

    /**
     * Creates a PhysSchemaGraph.
     *
     * @param physSchema Schema
     * @param linkList Links of the graph; a subset of the links in the
     * schema
     */
    public PhysSchemaGraph(
        PhysSchema physSchema,
        Collection<PhysLink> linkList)
    {
        this.physSchema = physSchema;

        // Populate the graph. Check that every link connects a pair of
        // nodes in the schema, and is registered in the schema.
        for (PhysLink link : linkList) {
            addLink(link);
        }
    }

    /**
     * Adds a link to this graph. The link and nodes at the ends of the link
     * must belong to the same {@link mondrian.rolap.RolapSchema.PhysSchema}
     * as the graph. If the graph already contains this link, does not add
     * it again.
     *
     * @param link Link
     * @return Whether link was added
     */
    public boolean addLink(PhysLink link) {
        assert link.getFrom().getSchema() == physSchema;
        assert link.getTo().getSchema() == physSchema;
        assert physSchema.linkSet.contains(link);

        if (!graph.edgeList().contains(link)) {
            graph.addEdge(link);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds to a list the hops necessary to go from one relation to another.
     *
     *
     * @param pathBuilder Path builder to which to add path
     * @param prevRelation Relation to start at
     * @param nextRelations Relation to jump to
     * @param directed Whether to treat graph as directed
     * @throws PhysSchemaException if there is not a unique path
     */
    private void addHopsBetween(
        PhysPathBuilder pathBuilder,
        PhysRelation prevRelation,
        Set<PhysRelation> nextRelations,
        boolean directed)
        throws PhysSchemaException
    {
        if (nextRelations.contains(prevRelation)) {
            return;
        }
        if (nextRelations.size() == 0) {
            throw new IllegalArgumentException("nextRelations is empty");
        }
        if (directed) {
            final List<PhysLink> path =
                findUniquePath(prevRelation, nextRelations);
            for (PhysLink link : path) {
                if (nextRelations.contains(link.targetRelation)) {
                    break;
                }
                pathBuilder.add(link, link.sourceKey.relation, true);
            }
        } else {
            List<Pair<PhysLink, Boolean>> path =
                findUniquePathUndirected(prevRelation, nextRelations);
            for (Pair<PhysLink, Boolean> pair : path) {
                final PhysLink link = pair.left;
                final boolean forward = pair.right;
                PhysRelation targetRelation =
                    forward
                        ? link.targetRelation
                        : link.sourceKey.relation;
                PhysRelation sourceRelation =
                    forward
                        ? link.sourceKey.relation
                        : link.targetRelation;
                if (nextRelations.contains(targetRelation)) {
                    break;
                }
                pathBuilder.add(link, sourceRelation, forward);
            }
        }
    }

    private List<PhysLink> findUniquePath(
        PhysRelation prevRelation,
        Set<PhysRelation> nextRelations)
        throws PhysSchemaException
    {
        for (PhysRelation nextRelation : nextRelations) {
            final List<List<PhysLink>> pathList =
                graph.findAllPaths(prevRelation, nextRelation);
            switch (pathList.size()) {
                case 0:
                    continue;
                case 1:
                    return pathList.get(0);
                default:
                    throw new PhysSchemaException(
                        "Needed to find exactly one path from " + prevRelation
                            + " to " + nextRelation + ", but found "
                            + pathList.size() + " (" + pathList + ")");
            }
        }
        throw new PhysSchemaException(
            "Could not find a path from " + prevRelation
                + " to any of " + nextRelations);
    }

    private List<Pair<PhysLink, Boolean>> findUniquePathUndirected(
        PhysRelation prevRelation,
        Set<PhysRelation> nextRelations)
        throws PhysSchemaException
    {
        for (PhysRelation nextRelation : nextRelations) {
            List<List<Pair<PhysLink, Boolean>>> pathList =
                graph.findAllPathsUndirected(prevRelation, nextRelation);
            switch (pathList.size()) {
                case 0:
                    continue;
                case 1:
                    return pathList.get(0);
                default:
                    // When more than one path is possible,
                    // we use the one with the least amount of joins.
                    List<Pair<PhysLink, Boolean>> smallest = null;
                    for (List<Pair<PhysLink, Boolean>> path : pathList) {
                        if (smallest == null
                            || smallest.size() > path.size())
                        {
                            smallest = path;
                        }
                    }
                    return smallest;
            }
        }
        throw new PhysSchemaException(
            "Could not find a path from " + prevRelation
                + " to any of " + nextRelations);
    }

    /**
     * Creates a path from one relation to another.
     *
     * @param relation Start relation
     * @param relation1 Relation to jump to
     * @return path, never null
     *
     * @throws PhysSchemaException if there is not a unique path
     */
    public PhysPath findPath(
        PhysRelation relation,
        PhysRelation relation1)
        throws PhysSchemaException
    {
        return findPath(relation, Collections.singleton(relation1), true);
    }

    /**
     * Creates a path from one relation to another.
     *
     *
     * @param relation Start relation
     * @param targetRelations Relation to jump to
     * @param directed Whether to treat graph as directed
     * @return path, never null
     *
     * @throws PhysSchemaException if there is not a unique path
     */
    public PhysPath findPath(
        PhysRelation relation,
        Set<PhysRelation> targetRelations,
        boolean directed)
        throws PhysSchemaException
    {
        final PhysPathBuilder pathBuilder = new PhysPathBuilder(relation);
        addHopsBetween(
            pathBuilder,
            relation,
            targetRelations,
            directed);
        return pathBuilder.done();
    }

    /**
     * Appends to a path builder a path from the last step of the path
     * in the path builder to the given relation.
     *
     * <p>If there is no such path, throws.
     *
     * @param pathBuilder Path builder
     * @param relation Relation to hop to
     * @throws mondrian.rolap.RolapSchema.PhysSchemaException If no path
     *   can be found
     */
    public void findPath(
        PhysPathBuilder pathBuilder,
        PhysRelation relation)
        throws PhysSchemaException
    {
        addHopsBetween(
            pathBuilder,
            pathBuilder.hopList.get(
                pathBuilder.hopList.size() - 1).relation,
            Collections.<PhysRelation>singleton(relation), true);
    }
}
