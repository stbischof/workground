package mondrian.rolap.physicalschema;

/**
 * Checked exception for signaling errors in physical schemas.
 * These are intended to be caught and converted into validation exceptions.
 */
public class PhysSchemaException extends Exception {
    /**
     * Creates a PhysSchemaException.
     *
     * @param message Message
     */
    public PhysSchemaException(String message) {
        super(message);
    }
}
