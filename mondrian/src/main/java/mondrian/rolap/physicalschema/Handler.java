package mondrian.rolap.physicalschema;

/**
 * Handler for errors that arise while loading a schema.
 */
//interface Handler extends MappingHandler {
public interface Handler {

    void warning(String message);

    void error(String message);

    Exception fatal(String message);

    /**
     * Checks whether there were (ignored) errors, and if so throws.
     */
    void check() throws MondrianMultipleSchemaException;

}
