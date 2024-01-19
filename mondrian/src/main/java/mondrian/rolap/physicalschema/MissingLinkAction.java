package mondrian.rolap.physicalschema;

import org.eigenbase.xom.NodeDef;

public enum MissingLinkAction {
    IGNORE {
        public void handle(
            Handler handler, String message, NodeDef xml, String attr)
        {
            // do nothing
        }
    },
    WARNING {
        public void handle(
            Handler handler, String message, NodeDef xml, String attr)
        {
            handler.warning(message);
        }
    },
    ERROR {
        public void handle(
            Handler handler, String message, NodeDef xml, String attr)
        {
            handler.error(message);
        }
    };

    public abstract void handle(
        Handler handler, String message, NodeDef xml, String attr);
}
