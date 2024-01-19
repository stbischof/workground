package mondrian.rolap.physicalschema;

import java.util.List;

/**
 * Exception that schema load failed. It contains one or more exceptions.
 */
public class MondrianMultipleSchemaException
    extends RuntimeException
{
    public final List<Exception> exceptionList;

    /** Creates a MondrianMultipleSchemaException. */
    public MondrianMultipleSchemaException(
        String message,
        List<Exception> exceptionList)
    {
        super(message);
        this.exceptionList = exceptionList;
        assert exceptionList.size() > 0;
    }
}
