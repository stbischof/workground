package mondrian.resource;

import java.text.MessageFormat;

public class ResourceDefinition {

    public String baseMessage;
    private String key;
    private String[] props;

    public ResourceDefinition(String key, String baseMessage, String[] props) {
        this.key = key;
        this.baseMessage = baseMessage;
        this.props = props;
    }

    protected String instantiate(MondrianResource mondrianResource, Object[] objects) {
        MessageFormat format = new MessageFormat(baseMessage);
        return format.format(objects);
    }
}
