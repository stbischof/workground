package mondrian.rolap.physicalschema;

/**
 * Callback that can give an expression a path to a root relation.
 *
 * <p>The most typical implementation is the one that knows the measure
 * group and joining dimension, and can therefore find the path of an
 * expression (say an attribute's key) to the fact table.</p>
 *
 * <p>If the {@link #path} method returns false, that means that the column
 * does not need to be joined to the fact table.</p>
 */
public interface PhysRouter {
    /**
     * Returns the path by which the column should be joined to the fact
     * table.
     *
     * <p>A {@code null} return value is not an error; it means that the
     * column does not need to be joined.</p>
     *
     * @param column Column
     * @return Path by which column should be joined, or null
     */
    PhysPath path(PhysColumn column);
}
