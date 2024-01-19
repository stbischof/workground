package mondrian.rolap.physicalschema;

import org.eclipse.daanse.olap.api.access.Role;

import java.util.Map;

/** Role factory that always returns the same role. */
public class ConstantRoleFactory implements RoleFactory {
    private final Role role;

    ConstantRoleFactory(Role role) {
        this.role = role;
    }

    public Role create(Map<String, Object> context) {
        return role;
    }
}
