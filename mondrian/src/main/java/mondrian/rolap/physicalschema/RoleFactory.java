package mondrian.rolap.physicalschema;

import org.eclipse.daanse.olap.api.access.Role;

import java.util.Map;

/** Creates roles. Generally called when a connection is created. */
interface RoleFactory {
    Role create(Map<String, Object> context);
}
